package mx.babel.bansefi.banksystem.personas.backend;

import java.io.Serializable;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.AltaDomicilioRespuestaBean;
import mx.babel.bansefi.banksystem.base.beans.domain.DomicilioTipoBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.constants.ConstantesFuncionales;
import mx.babel.bansefi.banksystem.base.webservices.altadomicilio.AltaDomicilioPersonaServicio;
import mx.babel.bansefi.banksystem.base.webservices.altadomicilio.Ejecutar;
import mx.babel.bansefi.banksystem.base.webservices.altadomicilio.EjecutarResult;
import mx.babel.bansefi.banksystem.base.webservices.altadomicilio.ResponseBansefi;
import mx.babel.bansefi.banksystem.personas.utils.DomicilioToTrnConverter;
import mx.babel.bansefi.banksystem.personas.wrappers.AltaDomicilioPersonaWrapper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Clase para dar de alta domicilios
 * @author manuel.nieto
 *
 */
@Component
public class AltaDomicilioPersonaBackEnd extends BackEndBean implements Serializable{

	private static final long serialVersionUID = 5757057298633936770L;
	
	@Autowired
	ServicioWebUtils servicioWebUtils;
	
	@Autowired
	DomicilioToTrnConverter domicilioConverter;
	
	@Autowired
	private AltaDomicilioPersonaWrapper altaDomicilioPersonaWrapper;
	
	private static final Logger LOGGER = LogManager
			.getLogger(AltaDomicilioPersonaBackEnd.class);
	
	/**
	 * Función principal que descencadena todo el llamado de la TRN/WebService
	 * 
	 * @param contadores
	 *            Bean generico para la consulta de Contadores de Centro y
	 *            Puesto
	 * @return ContadoresCentroBean
	 */
	public AltaDomicilioRespuestaBean ejecutarTRN(DomicilioTipoBean domicilioBean){
		Ejecutar.ITRPEALTADOMICTRNI contexto = initPeticion(domicilioBean);
		EjecutarResult respuesta = ejecutarWS(contexto);
		super.verificaRespuesta(respuesta);
		if (verificaResponseBansefi(respuesta)) {
			return respuestaAltaDomicilio(respuesta.getResponseBansefi());
		}
		return null;
	}
	
	/**
	 * Funcion que recibe la respuesta de la TRN y Wrappea a un objeto propio
	 * del sistema
	 * 
	 * @param response
	 * @return ContadoresCentroBean
	 */
	private AltaDomicilioRespuestaBean respuestaAltaDomicilio(
			ResponseBansefi response){
		AltaDomicilioRespuestaBean altaDomicilio = null;
		if (verificaRespuestaCliente(response)) {
			// Se recuperan y wrappean los datos de la cuenta
			altaDomicilio = altaDomicilioPersonaWrapper
					.wrappRespuestaAlta(response
							.getOTRPEALTADOMICTRNO().getTRPEALTADOMICEVTZ()
							.getDRDOMICP());			
		}

		return altaDomicilio;
	}
	
	/**
	 * Función que inicializa los parametros de entrada que necesita el servicio web para ser ejecutado
	 * @param contadoresCentroBean
	 * @return Ejecutar.ITROBTECONTTRNI
	 */
	private Ejecutar.ITRPEALTADOMICTRNI initPeticion(DomicilioTipoBean domicilioBean) {
		Ejecutar.ITRPEALTADOMICTRNI contexto = new Ejecutar.ITRPEALTADOMICTRNI();
		Ejecutar.ITRPEALTADOMICTRNI.STDTRNIPARMV contextoEntrada = new Ejecutar.ITRPEALTADOMICTRNI.STDTRNIPARMV();
		Ejecutar.ITRPEALTADOMICTRNI.TRPEALTADOMICEVTY datosEntrada = new Ejecutar.ITRPEALTADOMICTRNI.TRPEALTADOMICEVTY();

		initialize(contexto);

		contextoEntrada.setCODTX(CodTxConstants.COD_TX_TR_PE_ALTA_DOMIC_TRN);
		contextoEntrada.setCODTXDI("");
		contextoEntrada.setNUMSEC(0);
		contextoEntrada.setIDEMPLAUT("");
		contextoEntrada.setIDINTERNOTERMTN(getTerminal());			
		
		datosEntrada = altaDomicilioPersonaWrapper
				.wrappAltaDomicilio(domicilioBean);
		
		// Valida que se estén enviando los tipos de domicilio
		if (datosEntrada.getCODPERSRLDIRV() != null
				&& datosEntrada.getCODPERSRLDIRV().isEmpty()) {
			throw new ControlableException(
					"Error al insertar un nuevo domicilio",
					"No se ha especificado el tipo/tipos de domicilio.");
		}
		
		datosEntrada.setCODNRBEEN(getEntidad());
		
		datosEntrada.setCOMPDOMICV(domicilioConverter.convertDomicilioToTrnAlta(domicilioBean));
		
		//Indicador mas datos
		datosEntrada.setINDMASDIRV(new Ejecutar.ITRPEALTADOMICTRNI.TRPEALTADOMICEVTY.INDMASDIRV("S"));
		datosEntrada.setINDMASDOMIC("S");
		datosEntrada.setINDGRABPEPERSV(new Ejecutar.ITRPEALTADOMICTRNI.TRPEALTADOMICEVTY.INDGRABPEPERSV("N"));
		
		Ejecutar.ITRPEALTADOMICTRNI.TRPEALTADOMICEVTY.CODARGEODOMICILIOV codArGeo = new Ejecutar.ITRPEALTADOMICTRNI.TRPEALTADOMICEVTY.CODARGEODOMICILIOV();
		codArGeo.setCODARGEO(domicilioBean.getCodArGeo());
		datosEntrada.setCODARGEODOMICILIOV(codArGeo);
		
		Ejecutar.ITRPEALTADOMICTRNI.TRPEALTADOMICEVTY.NUMARGEODOMICILIOV numArGeo = new Ejecutar.ITRPEALTADOMICTRNI.TRPEALTADOMICEVTY.NUMARGEODOMICILIOV();
		numArGeo.setNUMARGEO(domicilioBean.getNumArGeo());
		datosEntrada.setNUMARGEODOMICILIOV(numArGeo);
		
		datosEntrada.setCODPAISAG(ConstantesFuncionales.COD_PAIS_MEXICO);
		//TODO: Verificar de donde se obtiene el codigo de la direccion
		datosEntrada.setCODDIR("1");
		datosEntrada.setIDINTERNOBI(0);
		
		contexto.setTRPEALTADOMICEVTY(datosEntrada);
		contexto.setSTDTRNIPARMV(contextoEntrada);
		initialize(contexto);

		return contexto;

	}
	
	/**
	 * Función para invocar al servicio web y obtener su respuesta.
	 * 
	 * @param contexto
	 *            Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	private EjecutarResult ejecutarWS(Ejecutar.ITRPEALTADOMICTRNI contexto) {
		EjecutarResult respuesta = null;
		try {
			respuesta = (EjecutarResult) this.servicioWebUtils.ejecutarWS(
					AltaDomicilioPersonaServicio.class, contexto);
		} catch (NoControlableException e) {
			LOGGER.error("error", e);
			throw new NoControlableException(
					"Error al invocar servicio web de consulta de "
							+ "contadores de centro.", e);
		}
		return respuesta;
	}
	
	/**
	 * Función que valida que la respuesta del servidor no este vacía.
	 * 
	 * @param respuesta
	 *            Objeto respuesta del servicio web
	 * @return <code>true</code> si la respuesta no esta vacía.
	 */
	private Boolean verificaResponseBansefi(EjecutarResult respuesta) {
		Boolean noNulo = false;
		if (respuesta != null && respuesta.getResponseBansefi() != null) {
			noNulo = true;
		}
		return noNulo;
	}
	
	/**
	 * Función que valida que la respuesta Bansefi contenga un objeto con los
	 * datos de la consulta.
	 * 
	 * @param response
	 *            Respuesta Bansefi con datos de la consulta
	 * @return <code>true</code> si la respuesta Bansefi contiene elemento no
	 *         nulos
	 */
	private Boolean verificaRespuestaCliente(ResponseBansefi response) {
		Boolean noNulo = false;
		if (response != null
				&& response.getOTRPEALTADOMICTRNO() != null
				&& response.getOTRPEALTADOMICTRNO().getTRPEALTADOMICEVTZ()
						.getDRDOMICP().getCODNRBEEN() != null
						&& response.getOTRPEALTADOMICTRNO().getTRPEALTADOMICEVTZ().getDRDOMICP().getNUMDIR() != 0) {
			noNulo = true;
		}
		return noNulo;
	}
	
}
