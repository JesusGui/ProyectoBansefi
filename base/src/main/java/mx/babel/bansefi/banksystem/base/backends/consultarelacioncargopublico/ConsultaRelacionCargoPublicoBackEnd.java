package mx.babel.bansefi.banksystem.base.backends.consultarelacioncargopublico;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.domain.UsoCuentaBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.utils.BooleanToStringConverter;
import mx.babel.bansefi.banksystem.base.webservices.consultarelacioncargopublico.ConsultaRelacionCargoPublicoServicio;
import mx.babel.bansefi.banksystem.base.webservices.consultarelacioncargopublico.Ejecutar;
import mx.babel.bansefi.banksystem.base.webservices.consultarelacioncargopublico.Ejecutar.IPECONSINDVPERFTRANT;
import mx.babel.bansefi.banksystem.base.webservices.consultarelacioncargopublico.EjecutarResult;
import mx.babel.bansefi.banksystem.base.webservices.consultarelacioncargopublico.ResponseBansefi;
import mx.babel.bansefi.banksystem.base.webservices.consultarelacioncargopublico.ResponseBansefi.OPECONSINDVPERFTRANT.PECONSINDVPERFTRANEV.PEPERSRLFPUBLCE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Back end para consulta de los datos de cargo publico en el perfil transaccional de una persona en especifico
 * 
 * @author javier.martinnino
 *
 */
@Component
public class ConsultaRelacionCargoPublicoBackEnd extends BackEndBean{

	private static final long serialVersionUID = 6576046561414182031L;
	
	private static final String CONSULTA_NIVEL_RIESGO = "PE_PERFIL_TRANS_CL";
	private static final String BAJO_RIESGO = "BR";
	
	@Autowired
	ServicioWebUtils servicioWebUtils;
	
	/**
	 * Función encargada de consultar si el cliente es de riesgo o no.
	 * 
	 * @param idInterno id de la persona a consultar
	 * @return Boolean valida si el cliente es de riesgo
	 */
	public Boolean ejecutarTRN(Integer idInterno,boolean consultaRiesgo){
				
		Ejecutar.IPECONSINDVPERFTRANT contexto = new Ejecutar.IPECONSINDVPERFTRANT();		
		Ejecutar.IPECONSINDVPERFTRANT.STDTRNIPARMV contextoEntrada = new Ejecutar.IPECONSINDVPERFTRANT.STDTRNIPARMV();
				
		super.initialize(contexto);
		
		contextoEntrada.setCODTX(CodTxConstants.COD_TX_PE_CONS_INDV_USO_CTA_TRN);
		contextoEntrada.setIDINTERNOTERMTN(super.getTerminal());
		
		contexto.setSTDTRNIPARMV(contextoEntrada);
		
		contexto.getPECONSINDVPERFTRANEV().getPEPERFILTRANSCLP().setCODNRBEEN(super.getEntidad());
		if (idInterno != null){ 
			contexto.getPECONSINDVPERFTRANEV().getPEPERFILTRANSCLP().setIDINTERNOPE(idInterno);
		}
		contexto.getPECONSINDVPERFTRANEV().setINDPERFTRAN(ConsultaRelacionCargoPublicoBackEnd.CONSULTA_NIVEL_RIESGO);		
		
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
		return (!ConsultaRelacionCargoPublicoBackEnd.BAJO_RIESGO.equals(respuesta.getResponseBansefi().getOPECONSINDVPERFTRANT().getPECONSINDVPERFTRANEV().getPEPERFILTRANSCLE().getIDPERFILTRANSCL()));	
	}
	
	/**
	 * Función encargada de consultar las preguntas de relacion de cargo publico
	 * del perfil transaccional de una persona por medio de servicios web.
	 * 
	 * @param idInterno id de la persona a consultar
	 * @return UsoCuentaBean asociadas a un cliente
	 */
	public UsoCuentaBean ejecutarTRN(Integer idInterno){
		IPECONSINDVPERFTRANT contexto = initPeticion(idInterno);
		EjecutarResult respuesta = ejecutarWS(contexto);
		try{
			super.verificaRespuesta(respuesta);
		}catch (ControlableException ce){
			if (ce.getRtncod() != RETURN_COD_SIN_DATOS){
				throw ce;
			}else{
				return null;
			}
		}
		if(verificaResponseBansefi(respuesta)){
			return getRelacionCargoPublico(respuesta.getResponseBansefi());
		}
		return null;
	}
	
	/**
	 * Función que construye la consulta de cargos publicos a partir de la respuesta al servidor.
	 * 
	 * @param response
	 * @return
	 */
	private UsoCuentaBean getRelacionCargoPublico(ResponseBansefi response){
		UsoCuentaBean resultado = null;		
		if(verificaRespuestaCliente(response)){
			PEPERSRLFPUBLCE perfPub = response.getOPECONSINDVPERFTRANT().getPECONSINDVPERFTRANEV().getPEPERSRLFPUBLCE();
			resultado = new UsoCuentaBean();
			BooleanToStringConverter converter = new BooleanToStringConverter();
			resultado.setFuncionarioPublico(converter.convertFrom(perfPub.getINDTXT1()));
			resultado.setFuncionarioPublicoCargo(perfPub.getTXTRESPUESTA1().trim());
			resultado.setAsociadoFuncionarioPublico(converter.convertFrom(perfPub.getINDTXT2()));
			resultado.setAsociadoFuncionarioPublicoCargo(perfPub.getTXTRESPUESTA2().trim());
			resultado.setAsociadoFuncionarioPublicoNombre(perfPub.getNOMBRERESPUESTA2().trim());
						
		}
		return resultado;
	}
	
	
	/**
	 * Función para inicializar los atributos del objeto de peticíon al servicio web.
	 * 
	 * @param idInterno Id del cliente de tipo persona física
	 * @return Objeto de petición al web service
	 */
	private Ejecutar.IPECONSINDVPERFTRANT initPeticion(Integer idInterno){
		Ejecutar.IPECONSINDVPERFTRANT contexto = new Ejecutar.IPECONSINDVPERFTRANT();
		
		Ejecutar.IPECONSINDVPERFTRANT.STDTRNIPARMV contextoEntrada = 
				new Ejecutar.IPECONSINDVPERFTRANT.STDTRNIPARMV();
				
		super.initialize(contexto);
		
		contextoEntrada.setCODTX(CodTxConstants.COD_TX_PE_CONS_INDV_USO_CTA_TRN);
		contextoEntrada.setCODTXDI("");
		contextoEntrada.setNUMSEC(0);
		contextoEntrada.setIDEMPLAUT("");
		contextoEntrada.setIDINTERNOTERMTN(super.getTerminal());
		
		contexto.setSTDTRNIPARMV(contextoEntrada);
		
		contexto.getPECONSINDVPERFTRANEV().getPEPERFILTRANSCLP().setCODNRBEEN(super.getEntidad());
		contexto.getPECONSINDVPERFTRANEV().getPEPERSRLFPUBLCP().setCODNRBEEN(super.getEntidad());
		if (idInterno != null){ 
			contexto.getPECONSINDVPERFTRANEV().getPEPERFILTRANSCLP().setIDINTERNOPE(idInterno);
			contexto.getPECONSINDVPERFTRANEV().getPEPERSRLFPUBLCP().setIDINTERNOPE(idInterno);
		}
		
		return contexto;
	}
	
	/**
	 * Función para invocar al servicio web y obtener su respuesta.
	 * 
	 * @param contexto Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	private EjecutarResult ejecutarWS(Ejecutar.IPECONSINDVPERFTRANT contexto){
		EjecutarResult respuesta = null;
		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(ConsultaRelacionCargoPublicoServicio.class, contexto);
		}catch(NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de consulta de la "
					+ "relacion con cargo publico del perfil transaccional.", e);
		}
		return respuesta;
	}
	
	/**
	 * Función que valida que la respuesta del servidor no este vacía. 
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
	 * de la persona física.
	 * 
	 * @param response Respuesta Bansefi con datos de la persona moral
	 * @return <code>true</code> si la respuesta Bansefi contiene elemento no nulos
	 */
	private Boolean verificaRespuestaCliente(ResponseBansefi response){
		Boolean noNulo = false;
		if(response!= null && response.getOPECONSINDVPERFTRANT() != null &&
				response.getOPECONSINDVPERFTRANT().getPECONSINDVPERFTRANEV() != null &&
						response.getOPECONSINDVPERFTRANT().getPECONSINDVPERFTRANEV().getPEPERFILTRANSCLE() != null
						&& response.getOPECONSINDVPERFTRANT().getPECONSINDVPERFTRANEV().getPEPERSRLFPUBLCE() != null){
			noNulo = true;
		}
		return noNulo;
	}
	
}
