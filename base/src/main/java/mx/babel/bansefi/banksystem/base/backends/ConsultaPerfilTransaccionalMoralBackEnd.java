package mx.babel.bansefi.banksystem.base.backends;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.beans.domain.UsoCuentaBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.webservices.consultaperfiltransaccionalmoral.ConsultaPerfilTransaccionalMoralServicio;
import mx.babel.bansefi.banksystem.base.webservices.consultaperfiltransaccionalmoral.Ejecutar;
import mx.babel.bansefi.banksystem.base.webservices.consultaperfiltransaccionalmoral.Ejecutar.IPECONSORGPERFTRANTR;
import mx.babel.bansefi.banksystem.base.webservices.consultaperfiltransaccionalmoral.EjecutarResult;
import mx.babel.bansefi.banksystem.base.webservices.consultaperfiltransaccionalmoral.ResponseBansefi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConsultaPerfilTransaccionalMoralBackEnd extends BackEndBean {

	private static final long serialVersionUID = 4627195348376940874L;

	private static final String CONSULTA_NIVEL_RIESGO = "PE_PERFIL_TRANS_CL";
	private static final String BAJO_RIESGO = "BR";
	
	@Autowired
	ServicioWebUtils servicioWebUtils;

	public Boolean ejecutarTRN(Integer idInterna, boolean riesgo){
				
		Ejecutar.IPECONSORGPERFTRANTR contexto = new Ejecutar.IPECONSORGPERFTRANTR();
		
		Ejecutar.IPECONSORGPERFTRANTR.STDTRNIPARMV contextoEntrada = new Ejecutar.IPECONSORGPERFTRANTR.STDTRNIPARMV();

		super.initialize(contexto);

		contextoEntrada.setCODTX(CodTxConstants.COD_TX_PE_CONS_ORG_PERF_TRAN_TRN);
		contextoEntrada.setCODTXDI("");
		contextoEntrada.setNUMSEC(0);
		contextoEntrada.setIDINTERNOTERMTN(super.getTerminal());

		contexto.getPECONSORGPERFTRANEVT().getPEPERFILTRANSCLP().setCODNRBEEN(super.getEntidad());
		contexto.getPECONSORGPERFTRANEVT().getPEPERFILTRANSCLP().setIDINTERNOPE(idInterna);

		contexto.getPECONSORGPERFTRANEVT().getPEPERSRLFPUBLCP().setCODNRBEEN(super.getEntidad());
		contexto.getPECONSORGPERFTRANEVT().getPEPERSRLFPUBLCP().setIDINTERNOPE(idInterna);
		
		contexto.getPECONSORGPERFTRANEVT().setINDPERFTRAN(CONSULTA_NIVEL_RIESGO);		

		contexto.setSTDTRNIPARMV(contextoEntrada);

		EjecutarResult respuesta = ejecutarWS(contexto);
		
		try{
			super.verificaRespuesta(respuesta);
		}catch (ControlableException ce){
			if (ce.getRtncod() != RETURN_COD_SIN_DATOS){
				throw ce;
			}else{
				return false;
			}
		}
		return (!ConsultaPerfilTransaccionalMoralBackEnd.BAJO_RIESGO.equals(
				respuesta.getResponseBansefi().getOPECONSORGPERFTRANTR().getPECONSORGPERFTRANEVT().
				getPEPERFILTRANSCLE().getIDPERFILTRANSCL().trim()));
	}

	
	public UsoCuentaBean ejecutarTRN(Integer idInterna){
		Ejecutar.IPECONSORGPERFTRANTR contexto = initPeticion(idInterna);
		EjecutarResult respuesta = ejecutarWS(contexto);
		try{
			super.verificaRespuesta(respuesta);
		}catch (ControlableException ce){
			if (ce.getRtncod() != RETURN_COD_SIN_DATOS){
				throw ce;
			}else{				
				return new UsoCuentaBean();
			}
		}	
		return consultaPerfilTransaccionalMoral(respuesta.getResponseBansefi());
	}
	
	private UsoCuentaBean consultaPerfilTransaccionalMoral(ResponseBansefi response) {
		
		UsoCuentaBean resultado = new UsoCuentaBean();			
		if (verificaRespuestaCliente(response)) {
			boolean asociadoFuncionarioPublico = this.obtenerBoolean(response.getOPECONSORGPERFTRANTR().getPECONSORGPERFTRANEVT().getPEPERSRLFPUBLCE().getINDTXT2());
			boolean funcionarioPublico = this.obtenerBoolean(response.getOPECONSORGPERFTRANTR().getPECONSORGPERFTRANEVT().getPEPERSRLFPUBLCE().getINDTXT1());
			
			resultado.setAsociadoFuncionarioPublico(asociadoFuncionarioPublico);
			resultado.setFuncionarioPublico(funcionarioPublico);
			
			if (asociadoFuncionarioPublico) {
				resultado.setAsociadoFuncionarioPublicoCargo(response.getOPECONSORGPERFTRANTR().getPECONSORGPERFTRANEVT().getPEPERSRLFPUBLCE().getTXTRESPUESTA2().trim());
				resultado.setAsociadoFuncionarioPublicoNombre(response.getOPECONSORGPERFTRANTR().getPECONSORGPERFTRANEVT().getPEPERSRLFPUBLCE().getNOMBRERESPUESTA2().trim());
			}
			if (funcionarioPublico) {
				resultado.setFuncionarioPublicoCargo(response.getOPECONSORGPERFTRANTR().getPECONSORGPERFTRANEVT().getPEPERSRLFPUBLCE().getTXTRESPUESTA1().trim());
			}			
		}
		return resultado;
	}

	private boolean verificaRespuestaCliente(ResponseBansefi response) {
		Boolean noNulo = false;
		if (response != null && response.getOPECONSORGPERFTRANTR() != null) {
			noNulo = true;
		}
		return noNulo;
	}

	private EjecutarResult ejecutarWS(IPECONSORGPERFTRANTR contexto) {
		EjecutarResult respuesta = null;
		try {
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(ConsultaPerfilTransaccionalMoralServicio.class, contexto);
		} catch (NoControlableException e) {
			throw new NoControlableException("Error al invocar servicio web de alta de perfil transaccional de clientes.", e);
		}
		return respuesta;
	}

	private IPECONSORGPERFTRANTR initPeticion(Integer idInterno) {

		Ejecutar.IPECONSORGPERFTRANTR.STDTRNIPARMV contextoEntrada = new Ejecutar.IPECONSORGPERFTRANTR.STDTRNIPARMV();

		Ejecutar.IPECONSORGPERFTRANTR p = new Ejecutar.IPECONSORGPERFTRANTR();

		Ejecutar.IPECONSORGPERFTRANTR.PECONSORGPERFTRANEVT perfTrans = new Ejecutar.IPECONSORGPERFTRANTR.PECONSORGPERFTRANEVT();

		Ejecutar.IPECONSORGPERFTRANTR.PECONSORGPERFTRANEVT.PEPERFILTRANSCLP perfTransCLP = new Ejecutar.IPECONSORGPERFTRANTR.PECONSORGPERFTRANEVT.PEPERFILTRANSCLP();

		Ejecutar.IPECONSORGPERFTRANTR.PECONSORGPERFTRANEVT.PEPERSRLFPUBLCP perfTransPublic = new Ejecutar.IPECONSORGPERFTRANTR.PECONSORGPERFTRANEVT.PEPERSRLFPUBLCP();

		super.initialize(perfTrans);

		contextoEntrada.setCODTX(CodTxConstants.COD_TX_PE_CONS_ORG_PERF_TRAN_TRN);
		contextoEntrada.setCODTXDI("");
		contextoEntrada.setNUMSEC(0);
		contextoEntrada.setIDINTERNOTERMTN(super.getTerminal());

		perfTransCLP.setCODNRBEEN(super.getEntidad());
		perfTransCLP.setIDINTERNOPE(idInterno);

		perfTransPublic.setCODNRBEEN(super.getEntidad());
		perfTransPublic.setIDINTERNOPE(idInterno);
		perfTrans.setPEPERFILTRANSCLP(perfTransCLP);
		perfTrans.setPEPERSRLFPUBLCP(perfTransPublic);		

		p.setSTDTRNIPARMV(contextoEntrada);
		p.setPECONSORGPERFTRANEVT(perfTrans);

		return p;
	}

	private boolean obtenerBoolean(String indtxt1) {
		if ("S".equals(indtxt1)) {
			return true;
		}
		return false;
		
	}

}
