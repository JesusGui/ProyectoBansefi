package mx.babel.bansefi.banksystem.oficina.backends;

import java.io.Serializable;

import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.oficina.beans.ContadoresCentroBean;
import mx.babel.bansefi.banksystem.oficina.webservices.contadorespuestohost.ContadoresPuestoHostServicio;
import mx.babel.bansefi.banksystem.oficina.webservices.contadorespuestohost.Ejecutar;
import mx.babel.bansefi.banksystem.oficina.webservices.contadorespuestohost.Ejecutar.ITRTNCONTHOSTCNSTRN.TRCONSCONTHOSTEVTY;
import mx.babel.bansefi.banksystem.oficina.webservices.contadorespuestohost.EjecutarResult;
import mx.babel.bansefi.banksystem.oficina.webservices.contadorespuestohost.ResponseBansefi;
import mx.babel.bansefi.banksystem.oficina.wrappers.ContadoresPuestoWrapper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Clase que manipula la conexión con la TRN en Contadores de puesto para busquedas en Host
 * @author manuel.nieto
 *
 */
@Component
public class ContadoresPuestoHostBackEnd extends BackEndBean implements Serializable{
	
	private static final long serialVersionUID = 6956570181629741913L;
	private static final Logger LOGGER = LogManager
			.getLogger(ContadoresPuestoHostBackEnd.class);
	
	@Autowired
	ServicioWebUtils servicioWebUtils;
	
	@Autowired
	ContadoresPuestoWrapper contadoresPuestoWrapper;

	/**
	 * Funcion principal que descencadena la ejecución de la TRN
	 * @param contadores Bean generico para Contadores de Centro y Puesto
	 * @return ContadoresCentroBean
	 */
	public ContadoresCentroBean ejecutarTRN(ContadoresCentroBean contadores){
		Ejecutar.ITRTNCONTHOSTCNSTRN contexto = initPeticion(contadores);
		EjecutarResult respuesta = ejecutarWS(contexto);
		super.verificaRespuesta(respuesta);
		if (verificaResponseBansefi(respuesta)) {
			return consultaContadoresPuesto(respuesta.getResponseBansefi());
		}
		return null;
	}

	/**
	 * Funcion que recibe la respuesta de la TRN para analizarla y wrappear la respuesta
	 * @param response respuesta de la transacción
	 * @return ContadoresCentroBean
	 */
	private ContadoresCentroBean consultaContadoresPuesto(ResponseBansefi response){
		ContadoresCentroBean contadores = null;		
		if (verificaRespuestaCliente(response)) {
			// Se recuperan y wrappean los datos de la cuenta
			contadores = contadoresPuestoWrapper
					.wrappRespuestaContadoresPuestoHost(response
							.getOTRTNCONTHOSTCNSTRN().getTRCONSCONTHOSTEVTZ()
							.getTNCONTHOSTE());

			// Datos Calculables
			contadores.calcularDiferencias();
			contadores.calcularTotales();
		}

		return contadores;
	}

	/**
	 * Inicializa los datos de entrada que necesita el contexto de la TRN para realizar la petición
	 * @param contadoresCentroBean
	 * @return Ejecutar.ITRTNCONTHOSTCNSTRN
	 */
	private Ejecutar.ITRTNCONTHOSTCNSTRN initPeticion(
			ContadoresCentroBean contadoresCentroBean) {
		Ejecutar.ITRTNCONTHOSTCNSTRN contexto = new Ejecutar.ITRTNCONTHOSTCNSTRN();
		Ejecutar.ITRTNCONTHOSTCNSTRN.STDTRNIPARMV contextoEntrada = new Ejecutar.ITRTNCONTHOSTCNSTRN.STDTRNIPARMV();
		Ejecutar.ITRTNCONTHOSTCNSTRN.TRCONSCONTHOSTEVTY consultaCentro = new Ejecutar.ITRTNCONTHOSTCNSTRN.TRCONSCONTHOSTEVTY();

		initialize(contexto);

		contextoEntrada.setCODTX(CodTxConstants.COD_TR_TN_CONT_HOST_CNS_TRN);
		contextoEntrada.setCODTXDI("");
		contextoEntrada.setNUMSEC(0);
		contextoEntrada.setIDEMPLAUT("");
		contextoEntrada.setIDINTERNOTERMTN(contadoresCentroBean
				.getCodigoTerminal());

		TRCONSCONTHOSTEVTY.TNCONTHOSTP datosEntrada = contadoresPuestoWrapper
				.wrappConsultaContadoresPuestoHost(contadoresCentroBean);

		consultaCentro.setTNCONTHOSTP(datosEntrada);	
		
		//Logger de datos
		LOGGER.debug("Contexto de entrada, idinernotermtn: " + contextoEntrada.getIDINTERNOTERMTN());
		LOGGER.debug("Consulta Centro Puesto-----------------)");
		LOGGER.debug("ID_INTERNO_TERM_TN: " + consultaCentro.getTNCONTHOSTP().getIDINTERNOTERMTN());
		LOGGER.debug("COD_NRBE_EN: " + consultaCentro.getTNCONTHOSTP().getCODNRBEEN());
		LOGGER.debug("COD_INTERNO_UO: " + consultaCentro.getTNCONTHOSTP().getCODINTERNOUO());
		LOGGER.debug("COD_NUMRCO_MONEDA: " + consultaCentro.getTNCONTHOSTP().getCODNUMRCOMONEDA());
		LOGGER.debug("COD_CONT_TERM: " + consultaCentro.getTNCONTHOSTP().getCODCONTTERM());
		
		contexto.setTRCONSCONTHOSTEVTY(consultaCentro);
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
	private EjecutarResult ejecutarWS(Ejecutar.ITRTNCONTHOSTCNSTRN contexto) {
		EjecutarResult respuesta = null;
		try {
			respuesta = (EjecutarResult) this.servicioWebUtils.ejecutarWS(
					ContadoresPuestoHostServicio.class, contexto);
		} catch (NoControlableException e) {
			LOGGER.error("error", e);
			throw new NoControlableException(
					"Error al invocar servicio web de consulta de "
							+ "contadores de puesto host.", e);
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
				&& response.getOTRTNCONTHOSTCNSTRN() != null
				&& response.getOTRTNCONTHOSTCNSTRN().getTRCONSCONTHOSTEVTZ()
						.getTNCONTHOSTE() != null
				&& response.getOTRTNCONTHOSTCNSTRN().getTRCONSCONTHOSTEVTZ()
						.getTNCONTHOSTE().size() == 4) {
			noNulo = true;
		}
		return noNulo;
	}	
}
