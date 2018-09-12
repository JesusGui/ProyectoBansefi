package mx.babel.bansefi.banksystem.personas.backend;

import java.io.Serializable;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.personas.beans.CompartirDomicilioBean;
import mx.babel.bansefi.banksystem.personas.webservices.compartirdomicilio.CompartirDomicilioServicio;
import mx.babel.bansefi.banksystem.personas.webservices.compartirdomicilio.Ejecutar;
import mx.babel.bansefi.banksystem.personas.webservices.compartirdomicilio.Ejecutar.ITRPECOMPARTIRDOMICTR.TRPECOMPARTIRDOMICEVT.INDMASDIRV;
import mx.babel.bansefi.banksystem.personas.webservices.compartirdomicilio.EjecutarResult;
import mx.babel.bansefi.banksystem.personas.webservices.compartirdomicilio.ResponseBansefi;
import mx.babel.bansefi.banksystem.personas.wrappers.CompartirDomicilioWrapper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CompartirDomicilioBackEnd extends BackEndBean implements
		Serializable {

	private static final long serialVersionUID = 7780950585309060020L;

	@Autowired
	ServicioWebUtils servicioWebUtils;

	@Autowired
	CompartirDomicilioWrapper compartirDomicilioWrapper;

	private static final Logger LOGGER = LogManager
			.getLogger(AltaDomicilioPersonaBackEnd.class);

	/**
	 * Función principal que descencadena todo el llamado de la TRN/WebService
	 * 
	 * @param contadores Bean generico para la consulta de Contadores de Centro y Puesto
	 * @return ContadoresCentroBean
	 */
	public CompartirDomicilioBean ejecutarTRN(CompartirDomicilioBean compartirDomicilioBean){
		Ejecutar.ITRPECOMPARTIRDOMICTR contexto = initPeticion(compartirDomicilioBean);
		EjecutarResult respuesta = ejecutarWS(contexto);
		
		super.verificaRespuesta(respuesta);
		
		if (verificaResponseBansefi(respuesta)) {
			return respuestaCompartirDomicilio(respuesta.getResponseBansefi());
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
	private CompartirDomicilioBean respuestaCompartirDomicilio(ResponseBansefi response){
		CompartirDomicilioBean compartirDomicilioBean = null;		
		if (verificaRespuestaCliente(response)) {
			// Se recuperan y wrappean los datos de la cuenta
			compartirDomicilioBean = new CompartirDomicilioBean();
			compartirDomicilioBean.setIndicaMasDirecciones(response
					.getOTRPECOMPARTIRDOMICTR().getTRPECOMPARTIRDOMICEVT()
					.getINDMASDIRV().getINDMASDIR());
		}

		return compartirDomicilioBean;
	}

	/**
	 * Función que inicializa los parametros de entrada que necesita el servicio
	 * web para ser ejecutado
	 * 
	 * @param contadoresCentroBean
	 * @return Ejecutar.ITROBTECONTTRNI
	 */
	private Ejecutar.ITRPECOMPARTIRDOMICTR initPeticion(
			CompartirDomicilioBean compartirDomicilioBean) {
		Ejecutar.ITRPECOMPARTIRDOMICTR contexto = new Ejecutar.ITRPECOMPARTIRDOMICTR();
		Ejecutar.ITRPECOMPARTIRDOMICTR.STDTRNIPARMV contextoEntrada = new Ejecutar.ITRPECOMPARTIRDOMICTR.STDTRNIPARMV();
		Ejecutar.ITRPECOMPARTIRDOMICTR.TRPECOMPARTIRDOMICEVT datosEntrada = new Ejecutar.ITRPECOMPARTIRDOMICTR.TRPECOMPARTIRDOMICEVT();

		initialize(contexto);

		contextoEntrada
				.setCODTX(CodTxConstants.COD_TX_TR_PE_COMPARTIR_DOMIC_TRN);
		contextoEntrada.setCODTXDI("");
		contextoEntrada.setNUMSEC(0);
		contextoEntrada.setIDEMPLAUT("");
		contextoEntrada.setIDINTERNOTERMTN(getTerminal());

		datosEntrada = compartirDomicilioWrapper
				.wrappCompartirDomicilio(compartirDomicilioBean);

		// Valida que se estén enviando los tipos de domicilio
		if (datosEntrada.getCODPERSRLDIRV() != null
				&& datosEntrada.getCODPERSRLDIRV().isEmpty()) {
			throw new ControlableException(
					"Error al insertar un nuevo domicilio",
					"No se ha especificado el tipo/tipos de domicilio.");
		}
		
		INDMASDIRV indmasdir = new INDMASDIRV();
		indmasdir.setINDMASDIR("S");
		datosEntrada.setINDMASDIRV(indmasdir);

		datosEntrada.setCODNRBEEN(getEntidad());
		datosEntrada.setCODDIR("1");

		contexto.setTRPECOMPARTIRDOMICEVT(datosEntrada);
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
	private EjecutarResult ejecutarWS(Ejecutar.ITRPECOMPARTIRDOMICTR contexto) {
		EjecutarResult respuesta = null;
		try {
			respuesta = (EjecutarResult) this.servicioWebUtils.ejecutarWS(
					CompartirDomicilioServicio.class, contexto);
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
				&& response.getOTRPECOMPARTIRDOMICTR() != null
				&& response.getOTRPECOMPARTIRDOMICTR()
						.getTRPECOMPARTIRDOMICEVT().getINDMASDIRV() != null) {
			noNulo = true;
		}
		return noNulo;
	}
}
