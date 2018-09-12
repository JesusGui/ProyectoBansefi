package mx.babel.bansefi.banksystem.base.utils;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

/**
 * Clase encargada de realizar una diccionario de parámetros propios de la
 * estructura de datos de Bansefi, a la aplicación Web.
 * 
 * @author manuel.nieto
 * 
 */
public class TraduccionParametros {

	public static final Map<String, String> diccionario;

	static {
		diccionario = new HashMap<String, String>();

		diccionario.put("COD_NRBE_EN", "ENTIDAD");
		diccionario.put("ID_INTERNO_PE", "ID CLIENTE");
		diccionario.put("ID_INTERNO_TERM_TN", "TERMINAL");
		diccionario.put("ID_EMPL_AUT", "ID EMPLEADO");
		diccionario.put("NUM_SEC_AC", "NUMERO DE CUENTA");
		diccionario.put("MONTO_TOTAL", "MONTO TOTAL");
		diccionario.put("FECHA_CTBLE", "FECHA CONTABLE");
		diccionario.put("COD_NUMRCO_MONEDA", "CODIGO DE MONEDA");
		diccionario.put("COD_LINEA", "LINEA TARIFA");
		diccionario.put("ID_GRP_PD", "ID GRUPO");
		diccionario.put("COD_RL_AC_AC", "TIPO DE RELACION CUENTA CON CUENTA");
		diccionario
				.put("COD_RL_PERS_AC", "TIPO DE RELACION PERSONA CON CUENTA");
		diccionario.put("COD_ECV_AC", "ESTADO DE LA CUENTA");
		diccionario.put("ID_EXT_PE", "NUMERO DE IDENTIFICACION");
		diccionario.put("COD_ID_EXT_PERS", "CODIGO DE IDENTIFICACION");
		diccionario.put("IDTASK", "ID DE TRANSACCION");
		diccionario.put("FECHAOPRCN", "FECHA DE OPERACION");
		diccionario.put("HORAOPRCN", "HORA DE OPERACION");
		diccionario.put("ACUERDO", "NUMERO DE CUENTA");
		diccionario.put("CODOPER", "CODIGO DE OPERACION");
		diccionario.put("FECVALOR", "FECHA VALOR");
		diccionario.put("FECHAVALOR", "FECHA VALOR");
		diccionario.put("FECHAOPERACION", "FECHA DE OPERACION");
		diccionario.put("HORAOPERACION", "HORA DE OPERACION");

	}

	/**
	 * Funcion que recibe un parametro y busca una coincidencia en el
	 * diccionario. Si no encuentra ninguna coincidencia retorna el parametro
	 * tal cual.
	 * 
	 * @param parametro
	 * @return
	 */
	public static String translate(String parametro) {
		String value = diccionario.get(parametro.trim().toUpperCase());
		if (!StringUtils.isBlank(value)) {
			return value;
		}

		return parametro.trim().toUpperCase();
	}

	/**
	 * Funcion que recibe una lista de campos en un HashMap<String, String> y
	 * los traduce a nombres propios de la aplicacion
	 * 
	 * @param campos
	 * @return
	 */
	public static Map<String, String> translate(Map<String, String> campos) {
		Map<String, String> salida = new HashMap<String, String>();

		for (Map.Entry<String, String> entry : campos.entrySet()) {
			salida.put(translate(entry.getKey()), entry.getValue().toUpperCase().trim());
		}

		return salida;
	}

}
