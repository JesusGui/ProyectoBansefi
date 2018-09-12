package mx.babel.bansefi.banksystem.base.backends.consultaempleados;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.webservices.consultaperfilempleado.ConsultaPerfilEmpleadoServicio;
import mx.babel.bansefi.banksystem.base.webservices.consultaperfilempleado.Ejecutar;
import mx.babel.bansefi.banksystem.base.webservices.consultaperfilempleado.EjecutarResult;
import mx.babel.bansefi.banksystem.base.webservices.consultaperfilempleado.ResponseBansefi;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Back end para consulta del perfil de un empleado en especifico
 * 
 * @author javier.martinnino
 *
 */
@Component
public class ConsultaPerfilEmpleadoTCBBackEnd extends BackEndBean{

	private static final long serialVersionUID = -5249874278771809187L;
	private static final Logger LOGGER = LogManager.getLogger(ConsultaPerfilEmpleadoTCBBackEnd.class.getName());

	@Autowired
	ServicioWebUtils servicioWebUtils;
	
	/**
	 * Función encargada de consultar el perfil de un empleado
	 * por medio de servicios web.
	 * 
	 * @param idEmpleado del empleado a consultar
	 * @return perfil del empleado
	 */
	public String ejecutarTRN(String idEmpleado){
		Ejecutar.ITREPCONPERFILEMPLTR contexto = initPeticion(idEmpleado);
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
			return consultaPerfilEmpleado(respuesta.getResponseBansefi());
		}
		return null;
	}
		
	/**
	 * Función que construye el perfil del empleado consultado.
	 * 
	 * @param response
	 * @return
	 */
	private String consultaPerfilEmpleado(ResponseBansefi response){
		String perfil= null;		
		if(verificaRespuestaCliente(response)){//			
			// Se recuperan y wrappean los datos del empleado
			perfil = response.getOTREPCONPERFILEMPLTR().getTREPCONPERFILEMPLEVT().getEPEMPLPERFILTXE().getNOMPERFILEN().trim();
		}
		LOGGER.debug("Valor del perfil obtenido: "+ perfil);
		return perfil;
	}
	
	
	/**
	 * Función para inicializar los atributos del objeto de peticíon al servicio web.
	 * 
	 * @param idInterno Id del cliente de tipo persona física
	 * @return Objeto de petición al web service
	 */
	private Ejecutar.ITREPCONPERFILEMPLTR initPeticion(String idInterno){
		LOGGER.debug("Se inicializa el contexto: ENTRADA");
		Ejecutar.ITREPCONPERFILEMPLTR contexto = new Ejecutar.ITREPCONPERFILEMPLTR();
		Ejecutar.ITREPCONPERFILEMPLTR.STDTRNIPARMV contextoEntrada = 
				new Ejecutar.ITREPCONPERFILEMPLTR.STDTRNIPARMV();
		Ejecutar.ITREPCONPERFILEMPLTR.TREPCONPERFILEMPLEVT empleado =
				new Ejecutar.ITREPCONPERFILEMPLTR.TREPCONPERFILEMPLEVT();
		Ejecutar.ITREPCONPERFILEMPLTR.TREPCONPERFILEMPLEVT.EPEMPLPERFILTXP clavePerfil =
				new Ejecutar.ITREPCONPERFILEMPLTR.TREPCONPERFILEMPLEVT.EPEMPLPERFILTXP();
				
		contextoEntrada.setCODTX(CodTxConstants.COD_TX_TR_CONS_EMPL_TRN);
		contextoEntrada.setCODTXDI("");
		contextoEntrada.setNUMSEC(0);
		contextoEntrada.setIDEMPLAUT("");
		contextoEntrada.setIDINTERNOTERMTN(super.getTerminal());
		
		clavePerfil.setCODNRBEEN(super.getEntidad());
		clavePerfil.setIDINTERNOEMPLEP(idInterno);
		
		contexto.setTREPCONPERFILEMPLEVT(empleado);
		contexto.getTREPCONPERFILEMPLEVT().setEPEMPLPERFILTXP(clavePerfil);
		contexto.setSTDTRNIPARMV(contextoEntrada);
		super.initialize(contexto);
		LOGGER.debug("Se inicializa el contexto: SALIDA");
		return contexto;
	}
	
	
	/**
	 * Función para invocar al servicio web y obtener su respuesta.
	 * 
	 * @param contexto Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	private EjecutarResult ejecutarWS(Ejecutar.ITREPCONPERFILEMPLTR contexto){
		EjecutarResult respuesta = null;
		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(ConsultaPerfilEmpleadoServicio.class, contexto);
		}catch(NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de consulta del "
					+ "perfil de empleado.", e);
		}
		LOGGER.debug("Se obtiene respuesta en la llamada");
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
		if(response!= null && response.getOTREPCONPERFILEMPLTR() != null &&
				response.getOTREPCONPERFILEMPLTR().getTREPCONPERFILEMPLEVT() != null &&
				response.getOTREPCONPERFILEMPLTR().getTREPCONPERFILEMPLEVT().getEPEMPLPERFILTXE() != null){
			noNulo = true;
		}
		return noNulo;
	}
		
}
