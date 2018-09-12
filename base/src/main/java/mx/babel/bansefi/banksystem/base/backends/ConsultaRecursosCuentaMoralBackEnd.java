package mx.babel.bansefi.banksystem.base.backends;

import java.util.ArrayList;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.beans.domain.ClientePersonaMoralBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.webservices.consultarecursoscuentamoral.ConsultaRecursosCuentaMoralServicio;
import mx.babel.bansefi.banksystem.base.webservices.consultarecursoscuentamoral.Ejecutar;
import mx.babel.bansefi.banksystem.base.webservices.consultarecursoscuentamoral.Ejecutar.IPECONSORGRECCTADST;
import mx.babel.bansefi.banksystem.base.webservices.consultarecursoscuentamoral.EjecutarResult;
import mx.babel.bansefi.banksystem.base.webservices.consultarecursoscuentamoral.ResponseBansefi;
import mx.babel.bansefi.banksystem.base.wrappers.ClienteWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConsultaRecursosCuentaMoralBackEnd extends BackEndBean{

	private static final long serialVersionUID = -2553908605781732432L;

	@Autowired
	ServicioWebUtils servicioWebUtils;
	
	@Autowired
	ClienteWrapper clienteWrapper;
	
	
	/**
	 * Función encargada de consultar el uso de la cuenta de una persona
	 * por medio de servicios web.
	 * 
	 * @param idInterno id de la persona a consultar
	 * @return bean de uso de cuenta
	 */
	public ClientePersonaMoralBean ejecutarTRN(Integer idInterno){
		Ejecutar.IPECONSORGRECCTADST contexto = initPeticion(idInterno);
		EjecutarResult respuesta = ejecutarWS(contexto);
		try{
			super.verificaRespuesta(respuesta);
		}catch (ControlableException ce){
			if (ce.getRtncod() != RETURN_COD_SIN_DATOS){
				throw ce;
			}else{
				ClientePersonaMoralBean personaMoral = new ClientePersonaMoralBean();
				personaMoral.setRecursos(new ArrayList<String>());
				return personaMoral;
			}
		}
		return getRecursosCuenta(respuesta.getResponseBansefi());
	}
	
	private ClientePersonaMoralBean getRecursosCuenta(ResponseBansefi response) {
		ClientePersonaMoralBean resultado = new ClientePersonaMoralBean();		
		if(verificaRespuestaCliente(response)){
			resultado  = this.clienteWrapper.wrappRecursosCuenta(response.getOPECONSORGRECCTADST().getPECONSORGRECCTADSEV().getPEPERSRCSOSCTAE());
		}
		return resultado;
	}

	private boolean verificaRespuestaCliente(ResponseBansefi response) {
		Boolean noNulo = false;
		if(response!= null && response.getOPECONSORGRECCTADST() != null){
			noNulo = true;
		}
		return noNulo;
	}

	private EjecutarResult ejecutarWS(IPECONSORGRECCTADST contexto) {
		EjecutarResult respuesta = null;
		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(ConsultaRecursosCuentaMoralServicio.class, contexto);
		}catch(NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de consulta de "
					+ "recursos de cuenta.", e);
		}
		return respuesta;
	}

	private IPECONSORGRECCTADST initPeticion(Integer idInterno) {
		Ejecutar.IPECONSORGRECCTADST contexto = new Ejecutar.IPECONSORGRECCTADST();
		
		Ejecutar.IPECONSORGRECCTADST.STDTRNIPARMV contextoEntrada = new Ejecutar.IPECONSORGRECCTADST.STDTRNIPARMV();
		
		Ejecutar.IPECONSORGRECCTADST.PECONSORGRECCTADSEV cuerpoContexto = new Ejecutar.IPECONSORGRECCTADST.PECONSORGRECCTADSEV();
		
		contextoEntrada.setCODTX(CodTxConstants.COD_TX_PE_CONS_INDV_USO_CTA_TRN);
		contextoEntrada.setCODTXDI("");
		contextoEntrada.setNUMSEC(0);
		contextoEntrada.setIDEMPLAUT("");
		contextoEntrada.setIDINTERNOTERMTN(super.getTerminal());		
		
		cuerpoContexto.setCODNRBEEN(super.getEntidad());
		cuerpoContexto.setIDINTERNOPE(idInterno);
		
		contexto.setPECONSORGRECCTADSEV(cuerpoContexto);
		contexto.setSTDTRNIPARMV(contextoEntrada);
		
		return contexto;
	}
	

	
}
