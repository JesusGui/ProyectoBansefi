package mx.babel.bansefi.banksystem.oficina.converters;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.bansefi.banksystem.base.utils.FechaUtils;
import mx.babel.bansefi.banksystem.base.utils.TraduccionParametros;
import mx.babel.bansefi.banksystem.oficina.beans.DiarioElectronicoDetalleRespuestaBean;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

/**
 * Clase que convierte la respuesta del detalle del diario electronico y la
 * convierte en objetos del sistema
 * 
 * @author manuel.nieto
 * 
 */
@Component
public class DiarioElectronicoConverter implements Serializable {

	private static final long serialVersionUID = -753233363664921343L;
	private static final Logger LOGGER = LogManager
			.getLogger(DiarioElectronicoConverter.class);

	/**
	 * Metodo que le da tratamiento a los datos de la consulta detalle del
	 * diario electronico
	 * 
	 * @param respuesta
	 * @return
	 */
	public DiarioElectronicoDetalleRespuestaBean convertResponse(
			DiarioElectronicoDetalleRespuestaBean respuesta) {
		// Traduccion de parametros
		respuesta.setCamposEntradas(TraduccionParametros.translate(respuesta
				.getCamposEntradas()));

		respuesta.setCamposSalidas(TraduccionParametros.translate(respuesta
				.getCamposSalidas()));

		Map<String, String> mapEntrada = respuesta.getCamposEntradas();
		Map<String, String> mapSalida = respuesta.getCamposSalidas();
		Map<String, String> unificacion = new HashMap<String, String>();
		unificacion.putAll(mapEntrada);
		unificacion.putAll(mapSalida);

		// Centro
		respuesta.setCentro(extractVal(unificacion, "CENTRO"));

		// Entidad
		respuesta.setEntidad(extractVal(unificacion, "ENTIDAD"));

		// Plaza
		respuesta.setPlaza(extractVal(unificacion, "PLAZA"));

		// Fecha valor
		if (!StringUtils.isBlank(extractVal(unificacion, "FECHA VALOR"))) {
			try {
				respuesta.setFechaValor(FechaUtils.formateaFecha(
						extractVal(unificacion, "FECHA VALOR"), "dd/MM/yyyy"));
			} catch (NullPointerException e) {
				LOGGER.debug("FECHA VALOR NULA");
			} catch (ControlableException p) {
				respuesta.setFechaValor(FechaUtils.formateaFecha(
						extractVal(unificacion, "FECHA VALOR"), "dd-MM-yyyy"));
			}
		}

		// Fecha de operacion
		if (!StringUtils.isBlank(extractVal(unificacion, "FECHA VALOR"))) {
			try {
				respuesta.setFechaOperacion(FechaUtils.formateaFecha(
						extractVal(unificacion, "FECHA DE OPERACION"),
						"yyyy-MM-dd"));
			} catch (NullPointerException e) {
				LOGGER.debug("FECHA DE OPERACION NULA");
			}
		}

		// Digito
		respuesta.setDigito(extractVal(unificacion, "DIGITO"));

		// Estatus
		respuesta.setEstatus(extractVal(unificacion, "ESTATUS"));

		// Titular
		respuesta.setTitular(extractVal(unificacion, "TITULAR"));

		// Tipo Identificacion
		respuesta.setTipoIdentificacion(extractVal(unificacion, "IDREFE"));
		if (StringUtils.isBlank(respuesta.getTipoIdentificacion())
				&& unificacion.containsKey("IFE")) {
			respuesta.setTipoIdentificacion(extractVal(unificacion, "CVEIDOF"));
			respuesta.setNoIdentificacion(extractVal(unificacion, "IFE"));
		} else {
			// Numero de identificacion
			respuesta
					.setNoIdentificacion(extractVal(unificacion, "REFERENCIA"));
		}

		// Movimiento
		respuesta.setMovimiento(extractVal(unificacion, "MOVIMIENTO"));

		// Codigo de operacion
		respuesta.setCodigoOperacion(extractVal(unificacion,
				"CODIGO DE OPERACION"));

		// Concepto
		respuesta.setConcepto(extractVal(unificacion, "CONCEPTO"));

		return respuesta;
	}

	/**
	 * Funcion que extrae un valor de una clase Map, y lo elimina
	 * 
	 * @param map
	 * @param value
	 * @return
	 */
	public String extractVal(Map<String, String> map, String value) {
		String val = (String) map.get(value);
		map.remove(value);
		return val;
	}

}
