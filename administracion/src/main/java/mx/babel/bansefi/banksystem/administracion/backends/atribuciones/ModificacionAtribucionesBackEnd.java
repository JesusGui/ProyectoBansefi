package mx.babel.bansefi.banksystem.administracion.backends.atribuciones;

import java.io.Serializable;
import java.math.BigDecimal;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.administracion.webservices.modificacionatribuciones.ModificacionAtribucionesServicio;
import mx.babel.bansefi.banksystem.administracion.webservices.modificacionatribuciones.Ejecutar;
import mx.babel.bansefi.banksystem.administracion.webservices.modificacionatribuciones.EjecutarResult;
import mx.babel.bansefi.banksystem.administracion.webservices.modificacionatribuciones.ResponseBansefi;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.domain.AtribucionBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.utils.BooleanToStringConverter;
import mx.babel.bansefi.banksystem.base.utils.IntegerToDateConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Back end para la modificacion de atribuciones de un empleado
 * 
 * @author javier.martinnino
 *
 */
@Component
public class ModificacionAtribucionesBackEnd extends BackEndBean implements Serializable{
	
	private static final long serialVersionUID = -6020643104774989839L;
	
	private static final String PCT_MAX = "999.999";
	
	@Autowired
	ServicioWebUtils servicioWebUtils;
	
	/**
	 * Función encargada de la modificacion de atribuciones a partir de un empleado
	 * por medio de servicios web.
	 * 
	 * @param idEmpleado al cual dar de alta atribuciones
	 * @param AtribucionBean atribucion a crear
	 * @return Codigo de respuesta del servicio
	 */
	public Integer ejecutarTRN(String idEmpleado, AtribucionBean atribucion){
		
		Ejecutar.ITREPMODIFATRIBEMPLT contexto = initPeticion(idEmpleado,atribucion);
		
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
			resultado = response.getOTREPMODIFATRIBEMPLT().getRTRNCD();		 	
		}
		return resultado;
	}
	
	
	/**
	 * Función para inicializar los atributos del objeto de peticíon al servicio web.
	 * 
	 * @param idEmpleado a consultar
	 * @return Objeto de petición al web service
	 */
	private Ejecutar.ITREPMODIFATRIBEMPLT initPeticion(String idEmpleado, AtribucionBean atribucion){
		Ejecutar.ITREPMODIFATRIBEMPLT contexto = new Ejecutar.ITREPMODIFATRIBEMPLT();
		
		Ejecutar.ITREPMODIFATRIBEMPLT.STDTRNIPARMV contextoEntrada = 
				new Ejecutar.ITREPMODIFATRIBEMPLT.STDTRNIPARMV();
		
		Ejecutar.ITREPMODIFATRIBEMPLT.TREPMODIFATRIBEMPLEV.EPATRIBUCIONESP datos =
				new Ejecutar.ITREPMODIFATRIBEMPLT.TREPMODIFATRIBEMPLEV.EPATRIBUCIONESP();
		
		Ejecutar.ITREPMODIFATRIBEMPLT.TREPMODIFATRIBEMPLEV.EPATRIBPERFTRANE datosPerfTran =
				new Ejecutar.ITREPMODIFATRIBEMPLT.TREPMODIFATRIBEMPLEV.EPATRIBPERFTRANE();
		
		BooleanToStringConverter boolStringCon = new BooleanToStringConverter();
		IntegerToDateConverter intDateCon = new IntegerToDateConverter();
		
		super.initialize(contexto);
		
		contextoEntrada.setCODTX(CodTxConstants.COD_TX_TR_EP_MODIF_ATRIB_EMPL_TRN);
		contextoEntrada.setCODTXDI("");
		contextoEntrada.setNUMSEC(0);
		contextoEntrada.setIDEMPLAUT("");
		contextoEntrada.setIDINTERNOTERMTN(super.getTerminal());
				
		datos.setCODNRBEEN(super.getEntidad());
		datos.setIDINTERNOEMPLEP(idEmpleado);		
		datos.setFECHAINICTEMPEP(intDateCon.convertFrom(atribucion.getFechaInicio()));
		
		contexto.getTREPMODIFATRIBEMPLEV().setFECHAFINTEMPEP(intDateCon.convertFrom(atribucion.getFechaFin()));
		
		contexto.getTREPMODIFATRIBEMPLEV().setPCTACTIVOEP(atribucion.getPorcActivo());
		contexto.getTREPMODIFATRIBEMPLEV().setPCTPSVEP(atribucion.getPorcPasivo());
		contexto.getTREPMODIFATRIBEMPLEV().setPCTINTERMEDEP(atribucion.getPorcDesintermediacion());
		contexto.getTREPMODIFATRIBEMPLEV().setPCTSERVICIOSEP(atribucion.getPorcServicios());
		contexto.getTREPMODIFATRIBEMPLEV().setPCTRSGOPDEP(atribucion.getPorcRiesgoProducto());
		contexto.getTREPMODIFATRIBEMPLEV().setPCTRSGOCLIENTEP(atribucion.getPorcRiesgoCliente());
		contexto.getTREPMODIFATRIBEMPLEV().setPCTCLIGARREAEP(atribucion.getPorcRiesgoGarantiaReal());
		contexto.getTREPMODIFATRIBEMPLEV().setPCTCLIGARPEREP(atribucion.getPorcRiesgoGarantiaPersonal());
		
		// Se incluye a 999.999 el valor del pct de Extranjero para mantener el funcionamiento
		// correcto en el TCB aunque el campo ya no aparece en nuestro formulario
		contexto.getTREPMODIFATRIBEMPLEV().setPCTEXTRANJEROEP(new BigDecimal(ModificacionAtribucionesBackEnd.PCT_MAX));
		
		contexto.getTREPMODIFATRIBEMPLEV().setINDAUTSITESPEP(boolStringCon.convertTo(atribucion.getAutorizacionSE()));
		contexto.getTREPMODIFATRIBEMPLEV().setINDAUTMODPERS(boolStringCon.convertTo(atribucion.getAutorizacionMP()));
		
		contexto.getTREPMODIFATRIBEMPLEV().setNUMDIASVALOREP(atribucion.getDiasValor());
		contexto.getTREPMODIFATRIBEMPLEV().setNIVATRIBCMSNEP(atribucion.getNivelAtribucion());
		
		datosPerfTran.setCODNRBEEN(super.getEntidad());
		datosPerfTran.setIDINTERNOEMPLEP(idEmpleado);
		datosPerfTran.setFECHAINICTEMPEP(intDateCon.convertFrom(atribucion.getFechaInicio()));
		
		datosPerfTran.setINDAUTBAJORSGO(boolStringCon.convertTo(atribucion.getAutorizacionPerfBR()));
		datosPerfTran.setINDAUTMEDIORSGO(boolStringCon.convertTo(atribucion.getAutorizacionPerfMR()));
		datosPerfTran.setINDAUTALTORSGO(boolStringCon.convertTo(atribucion.getAutorizacionPerfAR()));
		
		contexto.getTREPMODIFATRIBEMPLEV().setEPATRIBUCIONESP(datos);
		contexto.getTREPMODIFATRIBEMPLEV().setEPATRIBPERFTRANE(datosPerfTran);
		contexto.setSTDTRNIPARMV(contextoEntrada);
		
		return contexto;
	}
	
	/**
	 * Función para invocar al servicio web y obtener su respuesta.
	 * 
	 * @param contexto Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	private EjecutarResult ejecutarWS(Ejecutar.ITREPMODIFATRIBEMPLT contexto){
		EjecutarResult respuesta = null;
		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
					ModificacionAtribucionesServicio.class, contexto);
		}catch(NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de modificacion de atribuciones de empleados.", e);
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
		if(response!= null && response.getOTREPMODIFATRIBEMPLT() != null){
			noNulo = true;
		}
		return noNulo;
	}
	
}
