package mx.babel.bansefi.banksystem.personas.backend;

import java.io.Serializable;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.domain.DomicilioTipoBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.webservices.modificadomicilio.Ejecutar;
import mx.babel.bansefi.banksystem.base.webservices.modificadomicilio.EjecutarResult;
import mx.babel.bansefi.banksystem.base.webservices.modificadomicilio.ModificaDomicilioPersonaServicio;
import mx.babel.bansefi.banksystem.base.webservices.modificadomicilio.ResponseBansefi;
import mx.babel.bansefi.banksystem.personas.utils.DomicilioToTrnConverter;
import mx.babel.bansefi.banksystem.personas.wrappers.ModificaDomicilioPersonaWrapper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ModificaDomicilioPersonaBackEnd extends BackEndBean implements
		Serializable {

	private static final long serialVersionUID = 5997821713242774983L;

	@Autowired
	ServicioWebUtils servicioWebUtils;

	@Autowired
	DomicilioToTrnConverter domicilioConverter;

	@Autowired
	ModificaDomicilioPersonaWrapper modificaDomicilioPersonaWrapper;

	private static final Logger LOGGER = LogManager
			.getLogger(ModificaDomicilioPersonaBackEnd.class);

	/**
	 * Función principal que descencadena todo el llamado de la TRN/WebService
	 * La varialbe modificaSolo indica si se modifica el domicilio para solo
	 * este domicilio con S o para todos los domicilios con N
	 * 
	 * @param contadores
	 *            Bean generico para la consulta de Contadores de Centro y
	 *            Puesto
	 * @return ContadoresCentroBean
	 */
	public int ejecutarTRN(DomicilioTipoBean domicilioBean,
			int idInternoPersona, String modificaSolo) {
		Ejecutar.ITRPEMODIDOMICTRNI contexto = initPeticion(domicilioBean,
				idInternoPersona, modificaSolo);
		EjecutarResult respuesta = ejecutarWS(contexto);
		super.verificaRespuesta(respuesta);
		if (verificaResponseBansefi(respuesta)) {
			return respuestaModificacion(respuesta.getResponseBansefi());
		}
		return 0;
	}

	/**
	 * Funcion que recibe la respuesta de la TRN y Wrappea a un objeto propio
	 * del sistema
	 * 
	 * @param response
	 * @return String
	 * @throws NoControlableException
	 * @throws ControlableException
	 */
	private int respuestaModificacion(ResponseBansefi response) {
		int numeroDireccion = 0;
		if (verificaRespuestaCliente(response)) {
			// Se recuperan y wrappean los datos de la cuenta
			numeroDireccion = response.getOTRPEMODIDOMICTRNO()
					.getTRPEMODIDOMICEVTZ().getDRDOMICP().getNUMDIR();
		}

		return numeroDireccion;
	}

	/**
	 * Función que inicializa los parametros de entrada que necesita el servicio
	 * web para ser ejecutado
	 * 
	 * @param contadoresCentroBean
	 * @return Ejecutar.ITROBTECONTTRNI
	 */
	private Ejecutar.ITRPEMODIDOMICTRNI initPeticion(
			DomicilioTipoBean domicilioBean, int idInternoPersona,
			String modificaSolo) {
		Ejecutar.ITRPEMODIDOMICTRNI contexto = new Ejecutar.ITRPEMODIDOMICTRNI();
		Ejecutar.ITRPEMODIDOMICTRNI.STDTRNIPARMV contextoEntrada = new Ejecutar.ITRPEMODIDOMICTRNI.STDTRNIPARMV();
		Ejecutar.ITRPEMODIDOMICTRNI.TRPEMODIDOMICEVTY datosEntrada = new Ejecutar.ITRPEMODIDOMICTRNI.TRPEMODIDOMICEVTY();

		initialize(contexto);

		contextoEntrada.setCODTX(CodTxConstants.COD_TX_TR_PE_MODI_DOMIC_TRN);
		contextoEntrada.setCODTXDI("");
		contextoEntrada.setNUMSEC(0);
		contextoEntrada.setIDEMPLAUT("");
		contextoEntrada.setIDINTERNOTERMTN(getTerminal());

		datosEntrada = modificaDomicilioPersonaWrapper
				.wrappModificaDomicilio(domicilioBean);

		// Valida que se estén enviando los tipos de domicilio
		if (datosEntrada.getCODPERSRLDIRV() != null
				&& datosEntrada.getCODPERSRLDIRV().isEmpty()) {
			throw new ControlableException(
					"Error al insertar un nuevo domicilio",
					"No se ha especificado el tipo/tipos de domicilio.");
		}

		// Envia la entidad y el id interno del usuario
		Ejecutar.ITRPEMODIDOMICTRNI.TRPEMODIDOMICEVTY.PEPERSP pepersp = new Ejecutar.ITRPEMODIDOMICTRNI.TRPEMODIDOMICEVTY.PEPERSP();
		pepersp.setCODNRBEEN(getEntidad());
		pepersp.setIDINTERNOPE(idInternoPersona);

		datosEntrada.setPEPERSP(pepersp);

		Ejecutar.ITRPEMODIDOMICTRNI.TRPEMODIDOMICEVTY.CODARGEODOMICILIOV codArGeo = new Ejecutar.ITRPEMODIDOMICTRNI.TRPEMODIDOMICEVTY.CODARGEODOMICILIOV();
		codArGeo.setCODARGEO("07");
		datosEntrada.setCODARGEODOMICILIOV(codArGeo);

		Ejecutar.ITRPEMODIDOMICTRNI.TRPEMODIDOMICEVTY.NUMARGEODOMICILIOV numArGeo = new Ejecutar.ITRPEMODIDOMICTRNI.TRPEMODIDOMICEVTY.NUMARGEODOMICILIOV();
		try {
			numArGeo.setNUMARGEO(Integer.parseInt(domicilioBean
					.getDatosFinalesCatGeo().getNumArGeo()));
		} catch (NumberFormatException e) {
			numArGeo.setNUMARGEO(domicilioBean.getNumArGeo());
		}
		datosEntrada.setNUMARGEODOMICILIOV(numArGeo);

		// Indicadores
		datosEntrada
				.setINDMENSAJEMODIFDOMICV(new Ejecutar.ITRPEMODIDOMICTRNI.TRPEMODIDOMICEVTY.INDMENSAJEMODIFDOMICV(
						modificaSolo));
		datosEntrada
				.setINDSOLOMODIPERLDIRV(new Ejecutar.ITRPEMODIDOMICTRNI.TRPEMODIDOMICEVTY.INDSOLOMODIPERLDIRV(
						"N"));
		datosEntrada
				.setINDMASDIRV(new Ejecutar.ITRPEMODIDOMICTRNI.TRPEMODIDOMICEVTY.INDMASDIRV(
						"S"));
		// datosEntrada.setINDGRABPEPERSV(new
		// Ejecutar.ITRPEMODIDOMICTRNI.TRPEMODIDOMICEVTY.INDGRABPEPERSV("N"));
		datosEntrada.setINDMASDOMIC("S");

		// Envia la entidad y el numero de la direccion
		Ejecutar.ITRPEMODIDOMICTRNI.TRPEMODIDOMICEVTY.DRDOMICP drdomicp = new Ejecutar.ITRPEMODIDOMICTRNI.TRPEMODIDOMICEVTY.DRDOMICP();
		drdomicp.setCODNRBEEN(getEntidad());
		drdomicp.setNUMDIR(domicilioBean.getNumeroDireccion());

		// if (domicilioPrincipal == 0) {
		// datosEntrada
		// .setNUMDIRPRALV(new
		// Ejecutar.ITRPEMODIDOMICTRNI.TRPEMODIDOMICEVTY.NUMDIRPRALV(
		// domicilioBean.getNumeroDireccion()));
		// } else {
		datosEntrada
				.setNUMDIRPRALV(new Ejecutar.ITRPEMODIDOMICTRNI.TRPEMODIDOMICEVTY.NUMDIRPRALV(
						0));
		// }

		datosEntrada.setDRDOMICP(drdomicp);

		datosEntrada.setCOMPDOMICV(domicilioConverter
				.convertDomicilioToTrnModificacion(domicilioBean));
		// datosEntrada.setCODPERSRLDIRV(tipoDomicilioConverter
		// .convierteTiposDomicilioToTrnModificacion(domicilioBean
		// .getGrouping()));

		// Indicador mas datos

		// TODO: Integrar a la funcionalidad el codigo del pais
		// datosEntrada.setCODPAISAG("412");
		// TODO: Verificar de donde se obtiene el codigo de la direccion
		datosEntrada
				.setCODDIRDOMICILIOV(new Ejecutar.ITRPEMODIDOMICTRNI.TRPEMODIDOMICEVTY.CODDIRDOMICILIOV(
						"1"));

		contexto.setTRPEMODIDOMICEVTY(datosEntrada);
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
	private EjecutarResult ejecutarWS(Ejecutar.ITRPEMODIDOMICTRNI contexto) {
		EjecutarResult respuesta = null;
		try {
			respuesta = (EjecutarResult) this.servicioWebUtils.ejecutarWS(
					ModificaDomicilioPersonaServicio.class, contexto);
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
				&& response.getOTRPEMODIDOMICTRNO() != null
				&& response.getOTRPEMODIDOMICTRNO().getTRPEMODIDOMICEVTZ() != null) {
			noNulo = true;
		}
		return noNulo;
	}

}
