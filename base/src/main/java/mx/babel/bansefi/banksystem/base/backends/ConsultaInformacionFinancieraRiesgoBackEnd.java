package mx.babel.bansefi.banksystem.base.backends;

import java.io.Serializable;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.beans.domain.RiesgoClientePersonaFisicaBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.webservices.consultainformacionfinancierariesgo.ConsultaInformacionFinancieraRiesgoServicio;
import mx.babel.bansefi.banksystem.base.webservices.consultainformacionfinancierariesgo.Ejecutar;
import mx.babel.bansefi.banksystem.base.webservices.consultainformacionfinancierariesgo.EjecutarResult;
import mx.babel.bansefi.banksystem.base.webservices.consultainformacionfinancierariesgo.ResponseBansefi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Back end para consulta de informacion financiera para personas de riesgo
 * 
 * @author javier.martinnino
 *
 */
@Component
public class ConsultaInformacionFinancieraRiesgoBackEnd extends BackEndBean implements Serializable {
	
	private static final long serialVersionUID = -5397617229403876960L;
			
	@Autowired
	ServicioWebUtils servicioWebUtils;
	
	/**
	 * Función encargada de informacino financiera de una persona de riesgo por medio de servicios web.
	 * 
	 * @param idInterna del cliente a consultar
	 * @param RiesgoClientePersonaFisicaBean los datos del cliente ya consultados
	 * @return RiesgoClientePersonaFisicaBean datos de riesgo del cliente completos
	 */
	public RiesgoClientePersonaFisicaBean ejecutarTRN(Integer idInterna,RiesgoClientePersonaFisicaBean datosRiesgo){
		Ejecutar.ITRCNSINFFINANCTRNI contexto = initPeticion(idInterna);
		EjecutarResult respuesta = ejecutarWS(contexto);
		try{
			super.verificaRespuesta(respuesta);
		}catch (ControlableException ce){
			if (ce.getRtncod() != RETURN_COD_SIN_DATOS){
				throw ce;
			}else{
				return datosRiesgo;
			}
		}
		if(verificaResponseBansefi(respuesta)){
			return consultaInformacionFinanciera(respuesta.getResponseBansefi(), datosRiesgo);
		}
		return null;
	}
		
	/**
	 * Función que construye los datos de riesgo a devolver
	 * 
	 * @param response
	 * @return RiesgoClientePersonaFisicaBean datosRiesgo
	 */
	private RiesgoClientePersonaFisicaBean consultaInformacionFinanciera(ResponseBansefi response,RiesgoClientePersonaFisicaBean datosRiesgo){
		if(verificaRespuestaCliente(response)){		
			
			datosRiesgo.setOpcion1(response.getOTRCNSINFFINANCTRNO().getTRCNSINFFINANCEVTZ().getPEINFFINANPZAV().getPLAZAOPER01().trim());
			datosRiesgo.setOpcion2(response.getOTRCNSINFFINANCTRNO().getTRCNSINFFINANCEVTZ().getPEINFFINANPZAV().getPLAZAOPER02().trim());
			datosRiesgo.setOpcion3(response.getOTRCNSINFFINANCTRNO().getTRCNSINFFINANCEVTZ().getPEINFFINANPZAV().getPLAZAOPER03().trim());
			datosRiesgo.setOpcion4(response.getOTRCNSINFFINANCTRNO().getTRCNSINFFINANCEVTZ().getPEINFFINANPZAV().getPLAZAOPER04().trim());
			datosRiesgo.setOpcion5(response.getOTRCNSINFFINANCTRNO().getTRCNSINFFINANCEVTZ().getPEINFFINANPZAV().getPLAZAOPER05().trim());
			datosRiesgo.setOpcion6(response.getOTRCNSINFFINANCTRNO().getTRCNSINFFINANCEVTZ().getPEINFFINANPZAV().getPLAZAOPER06().trim());
			
			datosRiesgo.setProducto(response.getOTRCNSINFFINANCTRNO().getTRCNSINFFINANCEVTZ().getPEINFFINANADICV().getDESCRPROCTO().trim());
			datosRiesgo.setNumSucursales(response.getOTRCNSINFFINANCTRNO().getTRCNSINFFINANCEVTZ().getPEINFFINANADICV().getNUMSUCURSAL());
			datosRiesgo.setNumEmpleados(response.getOTRCNSINFFINANCTRNO().getTRCNSINFFINANCEVTZ().getPEINFFINANADICV().getNUMEMPLEADO());
			datosRiesgo.setActivo(response.getOTRCNSINFFINANCTRNO().getTRCNSINFFINANCEVTZ().getPEINFFINANADICV().getIMPACTIVO());
			datosRiesgo.setPasivo(response.getOTRCNSINFFINANCTRNO().getTRCNSINFFINANCEVTZ().getPEINFFINANADICV().getIMPPASIVO());
			datosRiesgo.setFactAnual(response.getOTRCNSINFFINANCTRNO().getTRCNSINFFINANCEVTZ().getPEINFFINANADICV().getIMPANUALFAC());
			datosRiesgo.setCapital(response.getOTRCNSINFFINANCTRNO().getTRCNSINFFINANCEVTZ().getPEINFFINANADICV().getIMPCAPITAL());
			datosRiesgo.setExportaciones(response.getOTRCNSINFFINANCTRNO().getTRCNSINFFINANCEVTZ().getPEINFFINANADICV().getIMPEXPRTCN());
			
		}
		return datosRiesgo;
	}
	
	
	/**
	 * Función para inicializar los atributos del objeto de peticíon al servicio web.
	 * 
	 * @param idInterno Id del cliente de tipo persona física
	 * @return Objeto de petición al web service
	 */
	private Ejecutar.ITRCNSINFFINANCTRNI initPeticion(Integer idInterna){
		Ejecutar.ITRCNSINFFINANCTRNI contexto = new Ejecutar.ITRCNSINFFINANCTRNI();
		
		Ejecutar.ITRCNSINFFINANCTRNI.STDTRNIPARMV contextoEntrada = 
				new Ejecutar.ITRCNSINFFINANCTRNI.STDTRNIPARMV();
		
		Ejecutar.ITRCNSINFFINANCTRNI.TRCNSINFFINANCEVTY.PEPERSP informacionFinanciera =
				new Ejecutar.ITRCNSINFFINANCTRNI.TRCNSINFFINANCEVTY.PEPERSP();
		
		super.initialize(contexto);
		
		contextoEntrada.setCODTX(CodTxConstants.COD_TX_TR_CNS_INF_FINANC_TRNN);
		contextoEntrada.setCODTXDI("");
		contextoEntrada.setNUMSEC(0);
		contextoEntrada.setIDEMPLAUT("");
		contextoEntrada.setIDINTERNOTERMTN(super.getTerminal());
		
		informacionFinanciera.setCODNRBEEN(super.getEntidad());
		informacionFinanciera.setIDINTERNOPE(idInterna);
		
		contexto.getTRCNSINFFINANCEVTY().setPEPERSP(informacionFinanciera);
		contexto.setSTDTRNIPARMV(contextoEntrada);
				
		return contexto;
	}
		
	/**
	 * Función para invocar al servicio web y obtener su respuesta.
	 * 
	 * @param contexto Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	private EjecutarResult ejecutarWS(Ejecutar.ITRCNSINFFINANCTRNI contexto){
		EjecutarResult respuesta = null;
		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(ConsultaInformacionFinancieraRiesgoServicio.class, contexto);
		}catch(NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de consulta de informacion financiera"
					+ "para clientes de riesgo.", e);
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
		if(response!= null && response.getOTRCNSINFFINANCTRNO() != null &&
				response.getOTRCNSINFFINANCTRNO().getTRCNSINFFINANCEVTZ() != null &&
				response.getOTRCNSINFFINANCTRNO().getTRCNSINFFINANCEVTZ().getPEINFFINANADICV() != null){
			noNulo = true;
		}
		return noNulo;
	}
	
}
