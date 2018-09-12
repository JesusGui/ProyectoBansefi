package mx.babel.bansefi.banksystem.oficina.backends;

import java.io.Serializable;

import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.utils.FechaUtils;
import mx.babel.bansefi.banksystem.oficina.beans.ContadoresCentroBean;
import mx.babel.bansefi.banksystem.oficina.webservices.contadorescentro.ContadoresCentroServicio;
import mx.babel.bansefi.banksystem.oficina.webservices.contadorescentro.Ejecutar;
import mx.babel.bansefi.banksystem.oficina.webservices.contadorescentro.Ejecutar.ITROBTECONTTRNI.TRCONSUOTNCONTEVTY;
import mx.babel.bansefi.banksystem.oficina.webservices.contadorescentro.EjecutarResult;
import mx.babel.bansefi.banksystem.oficina.webservices.contadorescentro.ResponseBansefi;
import mx.babel.bansefi.banksystem.oficina.wrappers.ContadoresCentroWrapper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Clase encargada de implementar los servicios web para la consulta de
 * Contadores de Centro
 * 
 * @author manuel.nieto
 * 
 */
@Component
public class ContadoresCentroBackEnd extends BackEndBean implements Serializable {

	private static final long serialVersionUID = 2603601772454084076L;

	private static final Logger LOGGER = LogManager
			.getLogger(ContadoresCentroBackEnd.class);

	@Autowired
	ServicioWebUtils servicioWebUtils;
	@Autowired
	ContadoresCentroWrapper contadoresCentroWrapper;

	/**
	 * Función principal que descencadena todo el llamado de la TRN/WebService
	 * 
	 * @param contadores
	 *            Bean generico para la consulta de Contadores de Centro y
	 *            Puesto
	 * @return ContadoresCentroBean
	 */
	public ContadoresCentroBean ejecutarTRN(ContadoresCentroBean contadores){
		Ejecutar.ITROBTECONTTRNI contexto = initPeticion(contadores);
		EjecutarResult respuesta = ejecutarWS(contexto);
		super.verificaRespuesta(respuesta);
		if (verificaResponseBansefi(respuesta)) {
			return consultaContadoresCentro(respuesta.getResponseBansefi());
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
	private ContadoresCentroBean consultaContadoresCentro(ResponseBansefi response){
		ContadoresCentroBean contadores = null;		
		if (verificaRespuestaCliente(response)) {
			// Se recuperan y wrappean los datos de la cuenta
			contadores = contadoresCentroWrapper
					.wrappRespuestaContadoresCentro(response
							.getOTROBTECONTTRNO().getTRCONSUOTNCONTEVTZ());

			// Datos Calculables
			contadores.calcularDiferencias();
			contadores.calcularTotales();
			contadores.getContadoresCentroRegistrosOn().calculaTotales();
		}

		return contadores;
	}

	/**
	 * Función que inicializa los parametros de entrada que necesita el servicio
	 * web para ser ejecutado
	 * 
	 * @param contadoresCentroBean
	 * @return Ejecutar.ITROBTECONTTRNI
	 */
	private Ejecutar.ITROBTECONTTRNI initPeticion(
			ContadoresCentroBean contadoresCentroBean) {
		Ejecutar.ITROBTECONTTRNI contexto = new Ejecutar.ITROBTECONTTRNI();
		Ejecutar.ITROBTECONTTRNI.STDTRNIPARMV contextoEntrada = new Ejecutar.ITROBTECONTTRNI.STDTRNIPARMV();
		Ejecutar.ITROBTECONTTRNI.TRCONSUOTNCONTEVTY consultaCentro = new Ejecutar.ITROBTECONTTRNI.TRCONSUOTNCONTEVTY();

		initialize(contexto);

		contextoEntrada.setCODTX(CodTxConstants.COD_TX_TR_OBTE_CONT_TRN);
		contextoEntrada.setCODTXDI("");
		contextoEntrada.setNUMSEC(0);
		contextoEntrada.setIDEMPLAUT("");
		contextoEntrada.setIDINTERNOTERMTN(getTerminal());

		TRCONSUOTNCONTEVTY datosEntrada = contadoresCentroWrapper
				.wrappConsultaContadoresCentro(contadoresCentroBean);
		datosEntrada.setCODNRBEEN(getEntidad());
		datosEntrada.setCODINTERNOUO(getSucursal());
		datosEntrada.setCODNUMRCOMONEDA("MXN");

		datosEntrada.setFECHACTBLE(Integer.parseInt(FechaUtils.formatFecha(
				super.getFechaSistema(), "yyyyMMdd")));

		consultaCentro = datosEntrada;

		contexto.setTRCONSUOTNCONTEVTY(consultaCentro);
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
	private EjecutarResult ejecutarWS(Ejecutar.ITROBTECONTTRNI contexto) {
		EjecutarResult respuesta = null;
		try {
			respuesta = (EjecutarResult) this.servicioWebUtils.ejecutarWS(
					ContadoresCentroServicio.class, contexto);
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
				&& response.getOTROBTECONTTRNO() != null
				&& response.getOTROBTECONTTRNO().getTRCONSUOTNCONTEVTZ()
						.getEXEXISTMONEDAE().getCAJACOBROSON() != null
				&& response.getOTROBTECONTTRNO().getTRCONSUOTNCONTEVTZ()
						.getEXEXISTMONEDAE().getCAJAPAGOSON() != null
				&& response.getOTROBTECONTTRNO().getTRCONSUOTNCONTEVTZ()
						.getEXEXISTMONEDAE().getCAJACOBROSOFF() != null
				&& response.getOTROBTECONTTRNO().getTRCONSUOTNCONTEVTZ()
						.getEXEXISTMONEDAE().getCAJAPAGOSOFF() != null
				&& response.getOTROBTECONTTRNO().getTRCONSUOTNCONTEVTZ()
						.getEXEXISTMONEDAE().getINTEDEBEOFF() != null
				&& response.getOTROBTECONTTRNO().getTRCONSUOTNCONTEVTZ()
						.getEXEXISTMONEDAE().getINTEDEBEON() != null
				&& response.getOTROBTECONTTRNO().getTRCONSUOTNCONTEVTZ()
						.getEXEXISTMONEDAE().getINTEHABEROFF() != null
				&& response.getOTROBTECONTTRNO().getTRCONSUOTNCONTEVTZ()
						.getEXEXISTMONEDAE().getINTEHABERON() != null) {
			noNulo = true;
		}
		return noNulo;
	}

}
