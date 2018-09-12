//package mx.babel.bansefi.banksystem.transacciones.backends;
//
//import java.io.Serializable;
//
//import mx.babel.arq.comun.exceptions.ControlableException;
//import mx.babel.arq.comun.exceptions.NoControlableException;
//import mx.babel.arq.comun.utils.EstadoEnum;
//import mx.babel.arq.comun.utils.ServicioWebUtils;
//import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
//import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
//import mx.babel.bansefi.banksystem.transacciones.beans.ResumenTransaccionBean;
//import mx.babel.bansefi.banksystem.transacciones.webservices.altarecibonodomiciliado.Ejecutar;
//import mx.babel.bansefi.banksystem.transacciones.webservices.altarecibonodomiciliado.EjecutarResult;
//import mx.babel.bansefi.banksystem.transacciones.webservices.altarecibonodomiciliado.ResponseBansefi;
//import mx.babel.bansefi.banksystem.transacciones.webservices.anulacionrecibonodomiciliado.AnulacionReciboNoDomiciliadoServicio;
//import mx.babel.bansefi.banksystem.transacciones.wrappers.RecibosNoDomiciliadosWrapper;
//
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
///**
// * Back en para la anulacion de los recibos no domiciliados
// * @author manuel.nieto
// *
// */
//@Component
//public class AnulacionReciboNoDomiciliadoBackEnd extends BackEndBean implements Serializable{
//
////	@Autowired
////	ServicioWebUtils servicioWebUtils;
////
////	@Autowired
////	RecibosNoDomiciliadosWrapper recibosNoDomiciliadosWrapper;
////
////	private static final Logger LOGGER = LogManager
////			.getLogger(AltaReciboNoDomiciliadoBackEnd.class);
////
////	/**
////	 * Función principal que descencadena todo el llamado de la TRN/WebService
////	 * 
////	 * @param contadores
////	 *            Bean generico para la consulta de Contadores de Centro y
////	 *            Puesto
////	 * @return ContadoresCentroBean
////	 * @throws NoControlableException
////	 * @throws ControlableException
////	 */
////	public ResumenTransaccionBean ejecutarTRN(String noInternoDocumento)
////			throws NoControlableException, ControlableException {
////		Ejecutar.ITRALTARVNORM57TRNI contexto = initPeticion(noInternoDocumento);
////		EjecutarResult respuesta = ejecutarWS(contexto);
////		if (verificaResponseBansefi(respuesta)) {
////			return respuestaAltaRecibo(respuesta.getResponseBansefi());
////		}
////		return null;
////	}
////
////	/**
////	 * Funcion que recibe la respuesta de la TRN y Wrappea a un objeto propio
////	 * del sistema
////	 * 
////	 * @param response
////	 * @return ContadoresCentroBean
////	 * @throws NoControlableException
////	 * @throws ControlableException
////	 */
////	private ResumenTransaccionBean respuestaAltaRecibo(ResponseBansefi response)
////			throws NoControlableException, ControlableException {
////		ResumenTransaccionBean respuesta = null;
////		verificaResultado(response);
////		if (verificaRespuestaCliente(response)) {
////			// Se recuperan y wrappean los datos de la cuenta
//////			respuesta = recibosNoDomiciliadosWrapper
//////					.wrappRespuestaConsultaRecibos(response
//////							.getOTRALTARVNORM57TRNO()
//////							.getTRALTARVNORM57EVTZ());
////		}
////
////		return respuesta;
////	}
////
////	/**
////	 * Función que inicializa los parametros de entrada que necesita el servicio
////	 * web para ser ejecutado
////	 * 
////	 * @param reciboBean
////	 * @return Ejecutar.ITRALTARVNORM57TRNI
////	 */
////	private Ejecutar.ITRALTARVNORM57TRNI initPeticion(String noInternoDocumento) {
////		Ejecutar.ITRALTARVNORM57TRNI contexto = new Ejecutar.ITRALTARVNORM57TRNI();
////		Ejecutar.ITRALTARVNORM57TRNI.STDTRNIPARMV contextoEntrada = new Ejecutar.ITRALTARVNORM57TRNI.STDTRNIPARMV();
////		Ejecutar.ITRALTARVNORM57TRNI.TRALTARVNORM57EVTY datosEntrada = new Ejecutar.ITRALTARVNORM57TRNI.TRALTARVNORM57EVTY();
////
////		initialize(contexto);
////
////		contextoEntrada
////				.setCODTX(CodTxConstants.COD_TX_TR_ANULACION_APLCCN_RV_TRN);
////		contextoEntrada.setCODTXDI("");
////		contextoEntrada.setNUMSEC(0);
////		contextoEntrada.setIDEMPLAUT("");
////		contextoEntrada.setIDINTERNOTERMTN(getTerminal());
////
////		// datosEntrada = recibosNoDomiciliadosWrapper
////		// .wrappConsultaReciboNoDomiciliado(reciboBean);
////
////		// TODO: Integrar en los datos de entrada el numero interno del
////		// documento
////
////		contexto.setTRALTARVNORM57EVTY(datosEntrada);
////		contexto.setSTDTRNIPARMV(contextoEntrada);
////		initialize(contexto);
////
////		return contexto;
////
////	}
////
////	/**
////	 * Función para invocar al servicio web y obtener su respuesta.
////	 * 
////	 * @param contexto
////	 *            Objeto de petición al servicio web
////	 * @return La respuesta del servicio web.
////	 */
////	private EjecutarResult ejecutarWS(Ejecutar.ITRALTARVNORM57TRNI contexto) {
////		EjecutarResult respuesta = null;
////		try {
////			respuesta = (EjecutarResult) this.servicioWebUtils.ejecutarWS(
////					AnulacionReciboNoDomiciliadoServicio.class, contexto);
////		} catch (NoControlableException e) {
////			LOGGER.error("error", e);
////			throw new NoControlableException(
////					"Error al invocar servicio web de consulta de "
////							+ "recibos no domiciliados.", e);
////		}
////		return respuesta;
////	}
////
////	/**
////	 * Función que valida que la respuesta del servidor no este vacía.
////	 * 
////	 * @param respuesta
////	 *            Objeto respuesta del servicio web
////	 * @return <code>true</code> si la respuesta no esta vacía.
////	 */
////	private Boolean verificaResponseBansefi(EjecutarResult respuesta) {
////		Boolean noNulo = false;
////		if (respuesta != null && respuesta.getResponseBansefi() != null) {
////			noNulo = true;
////		}
////		return noNulo;
////	}
////
////	/**
////	 * Función que valida que la respuesta Bansefi contenga un objeto con los
////	 * datos de la consulta.
////	 * 
////	 * @param response
////	 *            Respuesta Bansefi con datos de la consulta
////	 * @return <code>true</code> si la respuesta Bansefi contiene elemento no
////	 *         nulos
////	 */
////	private Boolean verificaRespuestaCliente(ResponseBansefi response) {
////		Boolean noNulo = false;
////		if (response != null
////				&& response.getOTRALTARVNORM57TRNO() != null
////				&& response.getOTRALTARVNORM57TRNO()
////						.getTRALTARVNORM57EVTZ() != null) {
////			noNulo = true;
////		}
////		return noNulo;
////	}
////
////	/**
////	 * Método que verifica los códigos de respuesta entregados por el servicio
////	 * web.
////	 * 
////	 * @param response
////	 *            Respuesta Bansefi con mensajes
////	 * @throws NoControlableException
////	 *             Excepción controlada de errores en front end
////	 * @throws ControlableException
////	 *             Excepción no controlada de errores en host
////	 */
////	private void verificaResultado(ResponseBansefi response)
////			throws NoControlableException, ControlableException {
////		int codigo = 1;
////		if (response.getOTRALTARVNORM57TRNO() != null
////				&& response.getOTRALTARVNORM57TRNO().getRTRNCD() != BackEndBean.RETURN_COD_OK) {
////			for (ResponseBansefi.OTRALTARVNORM57TRNO.STDTRNMSJPARMV mensaje : response
////					.getOTRALTARVNORM57TRNO().getSTDTRNMSJPARMV()) {
////				if (mensaje != null) {
////					codigo = mensaje.getTEXTCODE();
////				}
////			}
////		}
////		if (codigo != BackEndBean.RETURN_COD_OK) {
////			EstadoEnum.mensajesError("trn", codigo);
////		}
////	}
//}
