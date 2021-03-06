package mx.babel.bansefi.banksystem.administracion.backends.mantenimientoempleado;

import java.io.Serializable;

import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.administracion.webservices.modificacionperfilempleado.Ejecutar;
import mx.babel.bansefi.banksystem.administracion.webservices.modificacionperfilempleado.EjecutarResult;
import mx.babel.bansefi.banksystem.administracion.webservices.modificacionperfilempleado.ModificacionPerfilEmpleadoServicio;
import mx.babel.bansefi.banksystem.administracion.webservices.modificacionperfilempleado.ResponseBansefi;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.domain.EmpleadoBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Back end para la modificacion del perfil de un empleado en especifico
 * 
 * @author javier.martinnino
 *
 */
@Component
public class ModificacionPerfilEmpleadoTCBBackEnd extends BackEndBean implements Serializable{
	
	private static final long serialVersionUID = 1583507486505372537L;
	
	@Autowired
	ServicioWebUtils servicioWebUtils;

	/**
	 * Función encargada de la modificacion del perfil empleados
	 * por medio de servicios web.
	 * 
	 * @param EmpleadoBean empleado a dar de alta
	 * @return Codigo de respuesta del servicio
	 */
	public Integer ejecutarTRN(EmpleadoBean empleado){
		Ejecutar.ITRMODIFPERFILEMPLTRN contexto = initPeticion(empleado);
		EjecutarResult respuesta = ejecutarWS(contexto);
		super.verificaRespuesta(respuesta);
		if(verificaResponseBansefi(respuesta)){
			return modificacionPerfilEmpleado(respuesta.getResponseBansefi());
		}
		return null;
	}
	
	/**
	 * Función que construye la respuesta del servidor.
	 * 
	 * @param response
	 * @return
	 */
	private Integer modificacionPerfilEmpleado(ResponseBansefi response){
		Integer resultado = null;
		if(verificaRespuestaCliente(response)){
			resultado = response.getOTRMODIFPERFILEMPLTRN().getRTRNCD();		
		}
		return resultado;
	}
	
	
	/**
	 * Función para inicializar los atributos del objeto de peticíon al servicio web.
	 * 
	 * @param empleado a dar de alta
	 * @return Objeto de petición al web service
	 */
	private Ejecutar.ITRMODIFPERFILEMPLTRN initPeticion(EmpleadoBean empleado){
		Ejecutar.ITRMODIFPERFILEMPLTRN contexto = new Ejecutar.ITRMODIFPERFILEMPLTRN();
		Ejecutar.ITRMODIFPERFILEMPLTRN.STDTRNIPARMV contextoEntrada = 
				new Ejecutar.ITRMODIFPERFILEMPLTRN.STDTRNIPARMV();
		Ejecutar.ITRMODIFPERFILEMPLTRN.TRMODIFPERFILEMPLEVT empl =
				new Ejecutar.ITRMODIFPERFILEMPLTRN.TRMODIFPERFILEMPLEVT();
		Ejecutar.ITRMODIFPERFILEMPLTRN.TRMODIFPERFILEMPLEVT.EPEMPLPERFILTXE emplDatosE =
				new Ejecutar.ITRMODIFPERFILEMPLTRN.TRMODIFPERFILEMPLEVT.EPEMPLPERFILTXE();
		Ejecutar.ITRMODIFPERFILEMPLTRN.TRMODIFPERFILEMPLEVT.EPEMPLPERFILTXP emplDatosP =
				new Ejecutar.ITRMODIFPERFILEMPLTRN.TRMODIFPERFILEMPLEVT.EPEMPLPERFILTXP();
		
		super.initialize(contexto);
		
		contextoEntrada.setCODTX(CodTxConstants.COD_TX_TR_MODIF_PERFIL_EMPL_TRN);
		contextoEntrada.setCODTXDI("");
		contextoEntrada.setNUMSEC(0);
		contextoEntrada.setIDEMPLAUT("");
		contextoEntrada.setIDINTERNOTERMTN(super.getTerminal());
		
		emplDatosP.setCODNRBEEN(super.getEntidad());
		emplDatosP.setNOMPERFILEN(empleado.getPerfil());
		emplDatosP.setIDINTERNOEMPLEP(empleado.getNumEmpleado());
				
		emplDatosE.setCODNRBEEN(super.getEntidad());
		emplDatosE.setCODPERFIL("1");
		emplDatosE.setNOMPERFILEN(empleado.getPerfilTCB());
		emplDatosE.setIDINTERNOEMPLEP(empleado.getNumEmpleado());
		
		empl.setEPEMPLPERFILTXE(emplDatosE);
		empl.setEPEMPLPERFILTXP(emplDatosP);
		contexto.setTRMODIFPERFILEMPLEVT(empl);
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
	private EjecutarResult ejecutarWS(Ejecutar.ITRMODIFPERFILEMPLTRN contexto){
		EjecutarResult respuesta = null;
		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
					ModificacionPerfilEmpleadoServicio.class, contexto);
		}catch(NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de modificacion de "
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
		if(response!= null && response.getOTRMODIFPERFILEMPLTRN() != null &&
				response.getOTRMODIFPERFILEMPLTRN().getTRMODIFPERFILEMPLEVT() != null &&
						response.getOTRMODIFPERFILEMPLTRN().getTRMODIFPERFILEMPLEVT().getEPEMPLPERFILTXP() != null){
			noNulo = true;
		}
		return noNulo;
	}	
}
