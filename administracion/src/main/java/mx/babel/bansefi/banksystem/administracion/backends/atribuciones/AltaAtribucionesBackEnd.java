package mx.babel.bansefi.banksystem.administracion.backends.atribuciones;

import java.io.Serializable;
import java.math.BigDecimal;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.administracion.webservices.altaatribuciones.AltaAtribucionesServicio;
import mx.babel.bansefi.banksystem.administracion.webservices.altaatribuciones.Ejecutar;
import mx.babel.bansefi.banksystem.administracion.webservices.altaatribuciones.EjecutarResult;
import mx.babel.bansefi.banksystem.administracion.webservices.altaatribuciones.ResponseBansefi;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.domain.AtribucionBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.utils.BooleanToStringConverter;
import mx.babel.bansefi.banksystem.base.utils.IntegerToDateConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Back end para el alta de atribuciones de un empleado
 * 
 * @author javier.martinnino
 *
 */
@Component
public class AltaAtribucionesBackEnd extends BackEndBean implements Serializable{
	
	private static final long serialVersionUID = -6020643104774989839L;
	private static final String PCT_MAX = "999.999";
	
	@Autowired
	ServicioWebUtils servicioWebUtils;
	
	/**
	 * Función encargada de la consulta de atribuciones a partir de un empleado
	 * por medio de servicios web.
	 * 
	 * @param idEmpleado al cual dar de alta atribuciones
	 * @param AtribucionBean atribucion a crear
	 * @return Codigo de respuesta del servicio
	 */
	public Integer ejecutarTRN(String idEmpleado, AtribucionBean atribucion){
		
		Ejecutar.ITRALTAATRIBEMPLTRNI contexto = initPeticion(idEmpleado,atribucion);
		
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
			return altaAtribucion(respuesta.getResponseBansefi());
		}
		return resultado;
	}
	
	/**
	 * Función que construye la respuesta a partir de la respuesta del servidor.
	 * 
	 * @param response
	 * @return
	 */
	private Integer altaAtribucion(ResponseBansefi response){
		Integer resultado = null;		
		if(verificaRespuestaCliente(response)){
			resultado = response.getOTRALTAATRIBEMPLTRNO().getRTRNCD();		 	
		}
		return resultado;
	}
	
	
	/**
	 * Función para inicializar los atributos del objeto de peticíon al servicio web.
	 * 
	 * @param idEmpleado a consultar
	 * @return Objeto de petición al web service
	 */
	private Ejecutar.ITRALTAATRIBEMPLTRNI initPeticion(String idEmpleado, AtribucionBean atribucion){
		Ejecutar.ITRALTAATRIBEMPLTRNI contexto = new Ejecutar.ITRALTAATRIBEMPLTRNI();
		
		Ejecutar.ITRALTAATRIBEMPLTRNI.STDTRNIPARMV contextoEntrada = 
				new Ejecutar.ITRALTAATRIBEMPLTRNI.STDTRNIPARMV();
		
		Ejecutar.ITRALTAATRIBEMPLTRNI.TRALTAATRIBEMPLEVTY.EPATRIBUCIONESE datos =
				new Ejecutar.ITRALTAATRIBEMPLTRNI.TRALTAATRIBEMPLEVTY.EPATRIBUCIONESE();
		
		Ejecutar.ITRALTAATRIBEMPLTRNI.TRALTAATRIBEMPLEVTY.EPATRIBPERFTRANV datosPerfTran =
				new Ejecutar.ITRALTAATRIBEMPLTRNI.TRALTAATRIBEMPLEVTY.EPATRIBPERFTRANV();
		
		BooleanToStringConverter boolStringCon = new BooleanToStringConverter();
		IntegerToDateConverter intDateCon = new IntegerToDateConverter();
		
		super.initialize(contexto);
		
		contextoEntrada.setCODTX(CodTxConstants.COD_TX_TR_ALTA_ATRIB_EMPL_TRN);
		contextoEntrada.setCODTXDI("");
		contextoEntrada.setNUMSEC(0);
		contextoEntrada.setIDEMPLAUT("");
		contextoEntrada.setIDINTERNOTERMTN(super.getTerminal());
				
		datos.setCODNRBEEN(super.getEntidad());
		datos.setIDINTERNOEMPLEP(idEmpleado);
		
		datos.setFECHAINICTEMPEP(intDateCon.convertFrom(atribucion.getFechaInicio()));
		datos.setFECHAFINTEMPEP(intDateCon.convertFrom(atribucion.getFechaFin()));
		
		datos.setPCTACTIVOEP(atribucion.getPorcActivo());
		datos.setPCTPSVEP(atribucion.getPorcPasivo());
		datos.setPCTINTERMEDEP(atribucion.getPorcDesintermediacion());
		datos.setPCTSERVICIOSEP(atribucion.getPorcServicios());
		datos.setPCTRSGOPDEP(atribucion.getPorcRiesgoProducto());
		datos.setPCTRSGOCLIENTEP(atribucion.getPorcRiesgoCliente());
		datos.setPCTCLIGARREAEP(atribucion.getPorcRiesgoGarantiaReal());
		datos.setPCTCLIGARPEREP(atribucion.getPorcRiesgoGarantiaPersonal());
		
		// Se incluye a 999.999 el valor del pct de Extranjero para mantener el funcionamiento
		// correcto en el TCB aunque el campo ya no aparece en nuestro formulario
		datos.setPCTEXTRANJEROEP(new BigDecimal(AltaAtribucionesBackEnd.PCT_MAX));
		
		datos.setINDAUTSITESPEP(boolStringCon.convertTo(atribucion.getAutorizacionSE()));
		datos.setINDAUTMODPERS(boolStringCon.convertTo(atribucion.getAutorizacionMP()));
		
		datos.setNUMDIASVALOREP(atribucion.getDiasValor());
		datos.setNIVATRIBCMSNEP(atribucion.getNivelAtribucion());
		
		datosPerfTran.setINDAUTBAJORSGO(boolStringCon.convertTo(atribucion.getAutorizacionPerfBR()));
		datosPerfTran.setINDAUTMEDIORSGO(boolStringCon.convertTo(atribucion.getAutorizacionPerfMR()));
		datosPerfTran.setINDAUTALTORSGO(boolStringCon.convertTo(atribucion.getAutorizacionPerfAR()));
		
		contexto.getTRALTAATRIBEMPLEVTY().setEPATRIBUCIONESE(datos);
		contexto.getTRALTAATRIBEMPLEVTY().setEPATRIBPERFTRANV(datosPerfTran);
		contexto.setSTDTRNIPARMV(contextoEntrada);
		
		return contexto;
	}
	
	/**
	 * Función para invocar al servicio web y obtener su respuesta.
	 * 
	 * @param contexto Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	private EjecutarResult ejecutarWS(Ejecutar.ITRALTAATRIBEMPLTRNI contexto){
		EjecutarResult respuesta = null;
		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
					AltaAtribucionesServicio.class, contexto);
		}catch(NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de alta de atribuciones de empleados.", e);
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
		if(response!= null && response.getOTRALTAATRIBEMPLTRNO() != null){
			noNulo = true;
		}
		return noNulo;
	}
	
}
