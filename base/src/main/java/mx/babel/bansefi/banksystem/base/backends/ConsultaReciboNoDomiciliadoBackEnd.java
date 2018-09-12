
package mx.babel.bansefi.banksystem.base.backends;

import java.io.Serializable;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.beans.ReciboBean;
import mx.babel.bansefi.banksystem.base.webservices.consultarecibonodomiciliado.ConsultaReciboNoDomiciliadoServicio;
import mx.babel.bansefi.banksystem.base.webservices.consultarecibonodomiciliado.Ejecutar;
import mx.babel.bansefi.banksystem.base.webservices.consultarecibonodomiciliado.EjecutarResult;
import mx.babel.bansefi.banksystem.base.wrappers.RecibosNoDomiciliadosWrapper;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Backend para la consulta de recibos no domiciliados
 * 
 * @author manuel.nieto
 * 
 */
@Component
public class ConsultaReciboNoDomiciliadoBackEnd extends BackEndBean implements
		Serializable {

	private static final long serialVersionUID = 8064711642577684599L;

	@Autowired
	ServicioWebUtils servicioWebUtils;

	@Autowired
	RecibosNoDomiciliadosWrapper recibosNoDomiciliadosWrapper;

	private static final Logger LOGGER = LogManager
			.getLogger(ConsultaReciboNoDomiciliadoBackEnd.class);

	/**
	 * Función principal que descencadena todo el llamado de la TRN/WebService
	 * 
	 * @param contadores
	 *            Bean generico para la consulta de Contadores de Centro y
	 *            Puesto
	 * @return ContadoresCentroBean
	 */
	public ReciboBean ejecutarTRN(String numOperacion)
			throws NoControlableException, ControlableException {
		Ejecutar contexto = initPeticion(numOperacion);
		EjecutarResult respuesta = ejecutarWS(contexto);
		if (verificaResponseBansefi(respuesta)) {
			return respuestaAltaRecibo(respuesta);
		}
		return null;
	}

	/**
	 * Funcion que recibe la respuesta de la TRN y Wrappea a un objeto propio
	 * del sistema
	 * 
	 * @param response
	 * @return ContadoresCentroBean
	 * @throws NoControlableException
	 * @throws ControlableException
	 */
	private ReciboBean respuestaAltaRecibo(EjecutarResult response){
		if (response != null && response.getESTATUS().trim().equals(BackEndBean.ESTATUS_OK)){
			ReciboBean recibo = recibosNoDomiciliadosWrapper
					.wrappRespuestaConsultaRecibos(response.getResponseBansefi());
			
			if(!StringUtils.isBlank(recibo.getConcepto()) && recibo.getConcepto().contains("REF")){
				int index = recibo.getConcepto().indexOf("REF");
				String concepto = recibo.getConcepto().substring(0, index);
				String referencia = recibo.getConcepto().substring(index + 4);
				
				recibo.setConcepto(concepto);
				recibo.setReferencia(referencia);
			}
			
			return recibo;
		}		

		return null;
	}

	/**
	 * Función que inicializa los parametros de entrada que necesita el servicio
	 * web para ser ejecutado
	 * 
	 * @param numOperacion
	 * @return Ejecutar.ITRALTARVNORM57TRNI
	 */
	private Ejecutar initPeticion(String numOperacion) {
		Ejecutar consulta = new Ejecutar();

		initialize(consulta);

		consulta.setFOLIORPO(numOperacion);

		return consulta;

	}

	/**
	 * Función para invocar al servicio web y obtener su respuesta.
	 * 
	 * @param contexto
	 *            Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	private EjecutarResult ejecutarWS(Ejecutar contexto) {
		EjecutarResult respuesta = null;
		try {
			respuesta = (EjecutarResult) this.servicioWebUtils.ejecutarWS(
					ConsultaReciboNoDomiciliadoServicio.class,
					contexto.getFOLIORPO());

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

}
