package mx.babel.bansefi.banksystem.administracion.backends.mantenimientoempleado;

import java.io.Serializable;

import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.administracion.webservices.altaperfilempleado.AltaPerfilEmpleadoServicio;
import mx.babel.bansefi.banksystem.administracion.webservices.altaperfilempleado.Ejecutar;
import mx.babel.bansefi.banksystem.administracion.webservices.altaperfilempleado.EjecutarResult;
import mx.babel.bansefi.banksystem.administracion.webservices.altaperfilempleado.ResponseBansefi;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.domain.EmpleadoBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Back end para el alta del perfil de un empleado en especifico
 * 
 * @author javier.martinnino
 *
 */
@Component
public class AltaPerfilEmpleadoTCBBackEnd extends BackEndBean implements Serializable{
	
	private static final long serialVersionUID = -987351945293477621L;
	
	@Autowired
	ServicioWebUtils servicioWebUtils;
	
	/**
	 * Función encargada del alta de perfil empleados
	 * por medio de servicios web.
	 * 
	 * @param EmpleadoBean empleado a dar de alta
	 * @return Codigo de respuesta del servicio
	 */
	public Integer ejecutarTRN(EmpleadoBean empleado){
		Ejecutar.ITRALTAPERFILEMPLTRN contexto = initPeticion(empleado);
		EjecutarResult respuesta = ejecutarWS(contexto);
		super.verificaRespuesta(respuesta);
		if(verificaResponseBansefi(respuesta)){
			return altaPerfilEmpleado(respuesta.getResponseBansefi());
		}
		return null;
	}
	
	/**
	 * Función que construye la respuesta al servidor.
	 * 
	 * @param response
	 * @return
	 */
	private Integer altaPerfilEmpleado(ResponseBansefi response){
		Integer resultado = null;
		if(verificaRespuestaCliente(response)){
			resultado = response.getOTRALTAPERFILEMPLTRN().getRTRNCD();		
		}
		return resultado;
	}
	
	
	/**
	 * Función para inicializar los atributos del objeto de peticíon al servicio web.
	 * 
	 * @param empleado a dar de alta
	 * @return Objeto de petición al web service
	 */
	private Ejecutar.ITRALTAPERFILEMPLTRN initPeticion(EmpleadoBean empleado){
		Ejecutar.ITRALTAPERFILEMPLTRN contexto = new Ejecutar.ITRALTAPERFILEMPLTRN();
		Ejecutar.ITRALTAPERFILEMPLTRN.STDTRNIPARMV contextoEntrada = 
				new Ejecutar.ITRALTAPERFILEMPLTRN.STDTRNIPARMV();
		Ejecutar.ITRALTAPERFILEMPLTRN.TRALTAPERFILEMPLEVTY empl =
				new Ejecutar.ITRALTAPERFILEMPLTRN.TRALTAPERFILEMPLEVTY();
		Ejecutar.ITRALTAPERFILEMPLTRN.TRALTAPERFILEMPLEVTY.EPEMPLPERFILTXE emplDatos =
				new Ejecutar.ITRALTAPERFILEMPLTRN.TRALTAPERFILEMPLEVTY.EPEMPLPERFILTXE();
				
		super.initialize(contexto);
		
		contextoEntrada.setCODTX(CodTxConstants.COD_TX_TR_ALTA_PERFIL_EMPL_TRN);
		contextoEntrada.setCODTXDI("");
		contextoEntrada.setNUMSEC(0);
		contextoEntrada.setIDEMPLAUT("");
		contextoEntrada.setIDINTERNOTERMTN(super.getTerminal());
		
		emplDatos.setCODNRBEEN(super.getEntidad());
		emplDatos.setCODPERFIL("1");
		emplDatos.setNOMPERFILEN(empleado.getPerfilTCB());
		emplDatos.setIDINTERNOEMPLEP(empleado.getNumEmpleado());
		
		empl.setEPEMPLPERFILTXE(emplDatos);		
		contexto.setTRALTAPERFILEMPLEVTY(empl);
		contexto.setSTDTRNIPARMV(contextoEntrada);
		
		super.initialize(contexto);
		
		return contexto;
	}
	
	/**
	 * Función para invocar al servicio web y obtener su respuesta.
	 * 
	 * @param contexto Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	private EjecutarResult ejecutarWS(Ejecutar.ITRALTAPERFILEMPLTRN contexto){
		EjecutarResult respuesta = null;
		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
					AltaPerfilEmpleadoServicio.class, contexto);
		}catch(NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de alta de "
					+ "perfil de empleado.", e);
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
		if(response!= null && response.getOTRALTAPERFILEMPLTRN() != null &&
				response.getOTRALTAPERFILEMPLTRN().getTRALTAPERFILEMPLEVTZ() != null &&
				response.getOTRALTAPERFILEMPLTRN().getTRALTAPERFILEMPLEVTZ().getEPEMPLPERFILTXP() != null){
			noNulo = true;
		}
		return noNulo;
	}
}
