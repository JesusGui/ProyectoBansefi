package mx.babel.bansefi.banksystem.cuentas.backends;

import java.util.ArrayList;
import java.util.List;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.domain.relacioncuenta.RelacionadoBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.cuentas.beans.HistoricoRelacionadoBean;
import mx.babel.bansefi.banksystem.cuentas.webservices.historicorelacionpersonacuenta.HistoricoRelacionPersonaCuentaServicio;
import mx.babel.bansefi.banksystem.cuentas.webservices.historicorelacionpersonacuenta.Ejecutar;
import mx.babel.bansefi.banksystem.cuentas.webservices.historicorelacionpersonacuenta.EjecutarResult;
import mx.babel.bansefi.banksystem.cuentas.webservices.historicorelacionpersonacuenta.ResponseBansefi;
import mx.babel.bansefi.banksystem.cuentas.wrappers.RelacionesCuentaWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HistoricoRelacionPersonaCuentaBackEnd extends BackEndBean{
	
	private static final long serialVersionUID = -5349549337723877576L;
	
	@Autowired
	ServicioWebUtils servicioWebUtils;
	
	@Autowired
	RelacionesCuentaWrapper relacionesCuentaWrapper;
	
	public List<HistoricoRelacionadoBean> ejecutarTRN(RelacionadoBean relacionadoBean, Long numeroCuenta){
		List<HistoricoRelacionadoBean> historico = new ArrayList<HistoricoRelacionadoBean>();
		
		Ejecutar.ITRCONSULTAECVRPTRNI contexto = initPeticion(relacionadoBean, numeroCuenta);
		
		EjecutarResult respuesta = ejecutarWS(contexto);
		
		try{
			super.verificaRespuesta(respuesta);
		}catch (ControlableException ce){
			if (ce.getRtncod() != RETURN_COD_SIN_DATOS){
				throw ce;
			}else{
				return historico;
			}
		}
		
		if(verificaResponseBansefi(respuesta)){
			historico = getResultado(respuesta.getResponseBansefi(), relacionadoBean);
		}
		return historico;
	}
	
	private List<HistoricoRelacionadoBean> getResultado(ResponseBansefi respuesta, RelacionadoBean relacionadoBean){
		List<HistoricoRelacionadoBean> historico = new ArrayList<HistoricoRelacionadoBean>();
		
		if(verificaRespuesta(respuesta)){
			for (int i = 0; i < respuesta.getOTRCONSULTAECVRPTRNO().getNUMBEROFRECORDS(); i++) {
				HistoricoRelacionadoBean estado = 
						relacionesCuentaWrapper.wrappBean(
								respuesta.getOTRCONSULTAECVRPTRNO().getTRCONSULTAECVRPEVTZ().get(i));
				historico.add(estado);
			}
		}
		return historico;
	}
	
	/**
	 * Función para invocar al servicio web y obtener su respuesta.
	 * 
	 * @param contexto Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	private EjecutarResult ejecutarWS(Ejecutar.ITRCONSULTAECVRPTRNI contexto){
		EjecutarResult respuesta = null;
		
		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
					HistoricoRelacionPersonaCuentaServicio.class, contexto);
		}catch(NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de consulta histórico "
					+ "relación persona-cuenta.", e);
		}
		return respuesta;
	}
	
	private Ejecutar.ITRCONSULTAECVRPTRNI initPeticion(RelacionadoBean relacionadoBean, Long numeroCuenta){
		Ejecutar.ITRCONSULTAECVRPTRNI contexto = new Ejecutar.ITRCONSULTAECVRPTRNI();
		Ejecutar.ITRCONSULTAECVRPTRNI.TRCONSULTAECVRPEVTY detalles =
				new Ejecutar.ITRCONSULTAECVRPTRNI.TRCONSULTAECVRPEVTY();
		Ejecutar.ITRCONSULTAECVRPTRNI.STDTRNIPARMV cuerpoContexto = 
				new Ejecutar.ITRCONSULTAECVRPTRNI.STDTRNIPARMV();
		Ejecutar.ITRCONSULTAECVRPTRNI.TRCONSULTAECVRPEVTY.RPECVPERSACP relacion =
				new Ejecutar.ITRCONSULTAECVRPTRNI.TRCONSULTAECVRPEVTY.RPECVPERSACP();
		contexto.setTRCONSULTAECVRPEVTY(detalles);
		contexto.setSTDTRNIPARMV(cuerpoContexto);
		detalles.setRPECVPERSACP(relacion);
		super.initialize(contexto);
		
		
		cuerpoContexto.setCODTX(CodTxConstants.COD_TX_TR_CONSULTA_ECV_RP_TRN);
		cuerpoContexto.setIDINTERNOTERMTN(super.getTerminal());
		
		relacion.setCODNRBEEN(super.getEntidad());
		relacion.setIDINTERNOPE(relacionadoBean.getPersona().getIdInterna());
		relacion.setCODRLPERSAC(relacionadoBean.getTipo().getCodigo());
		relacion.setNUMSECAC(numeroCuenta);
		
		return contexto;
	}
	
	/**
	 * Función que valida que la respues del servidor no este vacía. 
	 *  
	 * @param respuesta Objeto respuesta del servicio web
	 * @return <code>true</code> si la respuesta no esta vacía.
	 */
	private Boolean verificaResponseBansefi(EjecutarResult respuesta){
		Boolean noNulo = false;
		if(respuesta != null && respuesta.getResponseBansefi() != null){
			noNulo = true;
		}
		return noNulo;
	}
	
	/**
	 * Función que valida que la respuesta Bansefi contenga un objeto con los datos
	 * de la relación.
	 * 
	 * @param response Respuesta Bansefi con datos de la relación
	 * @return <code>true</code> si la respuesta Bansefi contiene un histórico de la relación
	 */
	private Boolean verificaRespuesta(ResponseBansefi response){
		Boolean noNulo = false;
		if(response != null && response.getOTRCONSULTAECVRPTRNO() != null){
			noNulo = true;
		}
		return noNulo;
	}

}
