package mx.babel.bansefi.banksystem.base.backends.atribuciones;

import java.io.Serializable;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.domain.AtribucionBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.enums.EstadoListadosEnum;
import mx.babel.bansefi.banksystem.base.utils.BooleanToStringConverter;
import mx.babel.bansefi.banksystem.base.utils.IntegerToDateConverter;
import mx.babel.bansefi.banksystem.base.webservices.consultadetalleatribuciones.ConsultaDetalleAtribucionesServicio;
import mx.babel.bansefi.banksystem.base.webservices.consultadetalleatribuciones.Ejecutar;
import mx.babel.bansefi.banksystem.base.webservices.consultadetalleatribuciones.EjecutarResult;
import mx.babel.bansefi.banksystem.base.webservices.consultadetalleatribuciones.ResponseBansefi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Back end para la consulta del detalle de atribuciones de un empleado
 * 
 * @author javier.martinnino
 *
 */
@Component
public class ConsultaDetalleAtribucionesBackEnd extends BackEndBean implements Serializable{
	
	private static final long serialVersionUID = -6020643104774989839L;
	
	@Autowired
	ServicioWebUtils servicioWebUtils;

	/**
	 * Función encargada de la consulta del detalle atribuciones de un empleado
	 * por medio de servicios web.
	 * 
	 * @param idEmpleado empleado a consultar
	 * @param fecha a consultar
	 * @return AtribucionBean atribucion consultada
	 */
	public AtribucionBean ejecutarTRN(String idEmpleado,Integer fecha){
		Ejecutar.ITREPCONATRIBEMPL1T contexto = initPeticion(idEmpleado,fecha);
		EjecutarResult respuesta = ejecutarWS(contexto);
		try{
			super.verificaRespuesta(respuesta);
		}catch(ControlableException ce){
			if(ce.getRtncod()==RETURN_COD_SIN_DATOS){
				return new AtribucionBean();
			}else{
				throw ce;
			}
		}
		if(verificaResponseBansefi(respuesta)){
			return consultaDetalleAtribuciones(respuesta.getResponseBansefi());
		}
		return null;
	}
	
	/**
	 * Función que la lista de atribuciones a partir de la respuesta al servidor.
	 * 
	 * @param response
	 * @return
	 */
	private AtribucionBean consultaDetalleAtribuciones(ResponseBansefi response){
		AtribucionBean atribucion = new AtribucionBean();		
		if(verificaRespuestaCliente(response)){
			BooleanToStringConverter boolStringCon = new BooleanToStringConverter();
			IntegerToDateConverter intDateCon = new IntegerToDateConverter();
			
			atribucion.setEstado(EstadoListadosEnum.CONSULTADO);
			
			atribucion.setFechaInicio(intDateCon.convertTo(response.getOTREPCONATRIBEMPL1T().getTREPCONATRIBEMPL1EV().getEPATRIBUCIONESE().getFECHAINICTEMPEP()));
			atribucion.setFechaFin(intDateCon.convertTo(response.getOTREPCONATRIBEMPL1T().getTREPCONATRIBEMPL1EV().getEPATRIBUCIONESE().getFECHAFINTEMPEP()));
			
			atribucion.setPorcPasivo(response.getOTREPCONATRIBEMPL1T().getTREPCONATRIBEMPL1EV().getEPATRIBUCIONESE().getPCTPSVEP());			
			atribucion.setPorcActivo(response.getOTREPCONATRIBEMPL1T().getTREPCONATRIBEMPL1EV().getEPATRIBUCIONESE().getPCTACTIVOEP());
			atribucion.setPorcDesintermediacion(response.getOTREPCONATRIBEMPL1T().getTREPCONATRIBEMPL1EV().getEPATRIBUCIONESE().getPCTINTERMEDEP());
			atribucion.setPorcServicios(response.getOTREPCONATRIBEMPL1T().getTREPCONATRIBEMPL1EV().getEPATRIBUCIONESE().getPCTSERVICIOSEP());
			atribucion.setPorcRiesgoProducto(response.getOTREPCONATRIBEMPL1T().getTREPCONATRIBEMPL1EV().getEPATRIBUCIONESE().getPCTRSGOPDEP());
			atribucion.setPorcRiesgoCliente(response.getOTREPCONATRIBEMPL1T().getTREPCONATRIBEMPL1EV().getEPATRIBUCIONESE().getPCTRSGOCLIENTEP());
			atribucion.setPorcRiesgoGarantiaReal(response.getOTREPCONATRIBEMPL1T().getTREPCONATRIBEMPL1EV().getEPATRIBUCIONESE().getPCTCLIGARREAEP());
			atribucion.setPorcRiesgoGarantiaPersonal(response.getOTREPCONATRIBEMPL1T().getTREPCONATRIBEMPL1EV().getEPATRIBUCIONESE().getPCTCLIGARPEREP());
			
			atribucion.setAutorizacionPerfAR(boolStringCon.convertFrom(response.getOTREPCONATRIBEMPL1T().getTREPCONATRIBEMPL1EV().getEPATRIBPERFTRANV().getINDAUTALTORSGO().trim()));
			atribucion.setAutorizacionPerfMR(boolStringCon.convertFrom(response.getOTREPCONATRIBEMPL1T().getTREPCONATRIBEMPL1EV().getEPATRIBPERFTRANV().getINDAUTMEDIORSGO().trim()));
			atribucion.setAutorizacionPerfBR(boolStringCon.convertFrom(response.getOTREPCONATRIBEMPL1T().getTREPCONATRIBEMPL1EV().getEPATRIBPERFTRANV().getINDAUTBAJORSGO().trim()));
			
			atribucion.setAutorizacionSE(boolStringCon.convertFrom(response.getOTREPCONATRIBEMPL1T().getTREPCONATRIBEMPL1EV().getEPATRIBUCIONESE().getINDAUTSITESPEP().trim()));
			atribucion.setAutorizacionMP(boolStringCon.convertFrom(response.getOTREPCONATRIBEMPL1T().getTREPCONATRIBEMPL1EV().getEPATRIBUCIONESE().getINDAUTMODPERS().trim()));
			
			atribucion.setNivelAtribucion(response.getOTREPCONATRIBEMPL1T().getTREPCONATRIBEMPL1EV().getEPATRIBUCIONESE().getNIVATRIBCMSNEP().trim());
			atribucion.setDiasValor(response.getOTREPCONATRIBEMPL1T().getTREPCONATRIBEMPL1EV().getEPATRIBUCIONESE().getNUMDIASVALOREP());
		}
		return atribucion;
	}
	
	
	/**
	 * Función para inicializar los atributos del objeto de peticíon al servicio web.
	 * 
	 * @param idEmpleado a consultar
	 * @return Objeto de petición al web service
	 */
	private Ejecutar.ITREPCONATRIBEMPL1T initPeticion(String idEmpleado,Integer fecha){
		Ejecutar.ITREPCONATRIBEMPL1T contexto = new Ejecutar.ITREPCONATRIBEMPL1T();
		
		Ejecutar.ITREPCONATRIBEMPL1T.STDTRNIPARMV contextoEntrada = 
				new Ejecutar.ITREPCONATRIBEMPL1T.STDTRNIPARMV();
		
		Ejecutar.ITREPCONATRIBEMPL1T.TREPCONATRIBEMPL1EV.EPATRIBUCIONESP datosConsulta =
				new Ejecutar.ITREPCONATRIBEMPL1T.TREPCONATRIBEMPL1EV.EPATRIBUCIONESP();
		
		super.initialize(contexto);
		
		contextoEntrada.setCODTX(CodTxConstants.COD_TX_TR_EP_CON_ATRIB_EMPL_1_TRN);
		contextoEntrada.setCODTXDI("");
		contextoEntrada.setNUMSEC(0);
		contextoEntrada.setIDEMPLAUT("");
		contextoEntrada.setIDINTERNOTERMTN(super.getTerminal());
				
		datosConsulta.setCODNRBEEN(super.getEntidad());
		datosConsulta.setIDINTERNOEMPLEP(idEmpleado);
		datosConsulta.setFECHAINICTEMPEP(fecha);
		
		contexto.getTREPCONATRIBEMPL1EV().setEPATRIBUCIONESP(datosConsulta);
		contexto.setSTDTRNIPARMV(contextoEntrada);
		
		return contexto;
	}
	
	/**
	 * Función para invocar al servicio web y obtener su respuesta.
	 * 
	 * @param contexto Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	private EjecutarResult ejecutarWS(Ejecutar.ITREPCONATRIBEMPL1T contexto){
		EjecutarResult respuesta = null;
		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(ConsultaDetalleAtribucionesServicio.class, contexto);
		}catch(NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de consulta de detalles de atribuciones de empleados.", e);
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
	 * no nulos.
	 * 
	 * @param response Respuesta Bansefi 
	 * @return <code>true</code> si la respuesta Bansefi contiene elemento no nulos
	 */
	private Boolean verificaRespuestaCliente(ResponseBansefi response){
		Boolean noNulo = false;
		if(response!= null && response.getOTREPCONATRIBEMPL1T() != null &&
				response.getOTREPCONATRIBEMPL1T().getTREPCONATRIBEMPL1EV() != null &&
				response.getOTREPCONATRIBEMPL1T().getTREPCONATRIBEMPL1EV().getEPATRIBPERFTRANV() != null &&
				response.getOTREPCONATRIBEMPL1T().getTREPCONATRIBEMPL1EV().getEPATRIBUCIONESE() != null){
			noNulo = true;
		}
		return noNulo;
	}
	
}
