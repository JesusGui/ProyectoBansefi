package mx.babel.bansefi.banksystem.administracion.backends.atribuciones;

import java.io.Serializable;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.administracion.webservices.bajaatribuciones.BajaAtribucionesServicio;
import mx.babel.bansefi.banksystem.administracion.webservices.bajaatribuciones.Ejecutar;
import mx.babel.bansefi.banksystem.administracion.webservices.bajaatribuciones.EjecutarResult;
import mx.babel.bansefi.banksystem.administracion.webservices.bajaatribuciones.ResponseBansefi;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.domain.AtribucionBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.utils.IntegerToDateConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Back end para la baja de atribuciones de un empleado
 * 
 * @author javier.martinnino
 *
 */
@Component
public class BajaAtribucionesBackEnd extends BackEndBean implements Serializable{
	
	private static final long serialVersionUID = -6020643104774989839L;
	
	@Autowired
	ServicioWebUtils servicioWebUtils;
	
	/**
	 * Función encargada de la baja de atribuciones a partir de un empleado
	 * por medio de servicios web.
	 * 
	 * @param idEmpleado al cual dar de alta atribuciones
	 * @param AtribucionBean atribucion a eliminar
	 * @return Codigo de respuesta del servicio
	 */
	public Integer ejecutarTRN(String idEmpleado, AtribucionBean atribucion){
		
		Ejecutar.ITRBAJAATRIBEMPLTRNI contexto = initPeticion(idEmpleado,atribucion);
		
		EjecutarResult respuesta = ejecutarWS(contexto);
		
		Integer resultado = null;
		
		try{
			super.verificaRespuesta(respuesta);
		}catch(ControlableException ce){
			if(ce.getRtncod()!=RETURN_COD_SIN_DATOS){
				throw ce;
			}else{
				return resultado;
			}
		}
		
		if(verificaResponseBansefi(respuesta)){
			return modificacionAtribucion(respuesta.getResponseBansefi());
		}
		return null;
	}
	
	/**
	 * Función que construye la respuesta a partir de la respuesta del servidor.
	 * 
	 * @param response
	 * @return
	 */
	private Integer modificacionAtribucion(ResponseBansefi response){
		Integer resultado = null;		
		if(verificaRespuestaCliente(response)){
			resultado = response.getOTRBAJAATRIBEMPLTRNO().getRTRNCD();		 	
		}
		return resultado;
	}
	
	
	/**
	 * Función para inicializar los atributos del objeto de peticíon al servicio web.
	 * 
	 * @param idEmpleado a consultar
	 * @return Objeto de petición al web service
	 */
	private Ejecutar.ITRBAJAATRIBEMPLTRNI initPeticion(String idEmpleado, AtribucionBean atribucion){
		Ejecutar.ITRBAJAATRIBEMPLTRNI contexto = new Ejecutar.ITRBAJAATRIBEMPLTRNI();
		
		Ejecutar.ITRBAJAATRIBEMPLTRNI.STDTRNIPARMV contextoEntrada = 
				new Ejecutar.ITRBAJAATRIBEMPLTRNI.STDTRNIPARMV();
		
		Ejecutar.ITRBAJAATRIBEMPLTRNI.TRBAJAATRIBEMPLEVTY.EPATRIBUCIONESP datos =
				new Ejecutar.ITRBAJAATRIBEMPLTRNI.TRBAJAATRIBEMPLEVTY.EPATRIBUCIONESP();
				
		IntegerToDateConverter intDateCon = new IntegerToDateConverter();
		
		super.initialize(contexto);
		
		contextoEntrada.setCODTX(CodTxConstants.COD_TX_TR_BAJA_ATRIB_EMPL_TRN);
		contextoEntrada.setCODTXDI("");
		contextoEntrada.setNUMSEC(0);
		contextoEntrada.setIDEMPLAUT("");
		contextoEntrada.setIDINTERNOTERMTN(super.getTerminal());
				
		datos.setCODNRBEEN(super.getEntidad());
		datos.setIDINTERNOEMPLEP(idEmpleado);		
		datos.setFECHAINICTEMPEP(intDateCon.convertFrom(atribucion.getFechaInicio()));
		
		contexto.getTRBAJAATRIBEMPLEVTY().setEPATRIBUCIONESP(datos);
		contexto.setSTDTRNIPARMV(contextoEntrada);
		
		return contexto;
	}
	
	/**
	 * Función para invocar al servicio web y obtener su respuesta.
	 * 
	 * @param contexto Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	private EjecutarResult ejecutarWS(Ejecutar.ITRBAJAATRIBEMPLTRNI contexto){
		EjecutarResult respuesta = null;
		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
					BajaAtribucionesServicio.class, contexto);
		}catch(NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de baja de atribuciones de empleados.", e);
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
		if(response!= null && response.getOTRBAJAATRIBEMPLTRNO() != null){
			noNulo = true;
		}
		return noNulo;
	}
	
}
