package mx.babel.arq.comun.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.Properties;

import mx.babel.arq.comun.exceptions.NoControlableException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Clase utilidad que accede a los properties para retornar mensajes del
 * properties via Spring.
 *
 * @author joseluis.pena
 *
 */

@Component
public class ProveedorMensajeSpringUtils {

    private static final Logger LOGGER = LogManager.getLogger(ProveedorMensajeSpringUtils.class.getName());
    
	private static Properties staticProperties = null;

	@Autowired
	Properties propertiesLoader;

	@Autowired
	public void setStaticProperties(final Properties propertiesLoader) {
		ProveedorMensajeSpringUtils.staticProperties = propertiesLoader;
	}

	/**
     * Metodo con el que regresamos el texto asignado en el properties
     * de configuración del etorno.
     * @param key Nombre de la propiedad
     * @return String Valor de la propiedad especificada
     * @throws NoControlableException si la propiedad
     * no se ha encontrado
     */
	public static String getValorConfiguracion(final String key){
        try {
//            String entornoConfig = System.getenv("RUTA_CONFIG_LPARC");
            String entornoConfig = System.getenv("RUTA_CONFIG");
            LOGGER.debug("Ruta archivo config: "+entornoConfig);
            Properties prop = new Properties();
            InputStream inputStream = new FileInputStream(entornoConfig);
            prop.load(inputStream);
            
             return (String)prop.get(key);
        } catch (IOException e) {
            throw new NoControlableException("Error al consultar archivo de configuración de entorno.",e);
        }        
	}

	/**
	 * Metodo con el que regresamos el texto asignado en el properties
	 * de Servicios Web.
	 * @param key Nombre de la propiedad
	 * @return String Valor de la propiedad especificada
	 * @throws NoControlableException si la propiedad
	 * no se ha encontrado
	 */
	public static String getValoresServiciosWeb(final String key) {
		final String result = staticProperties.getProperty(key);
		checkResult(key, result);
		return result;
	}


	/**
	 * Metodo con el que regresamos el texto asignado en el properties
	 * de Servicios Web.
	 * @param key Nombre de la propiedad
	 * @return String Valor de la propiedad especificada
	 * @throws NoControlableException si la propiedad
	 */
	public static String getValoresMensajesError(final String key) {
		final String result = staticProperties.getProperty(key);
		checkResult(key, result);
		return result;
	}

	/**
	 * Metodo obtenemos una propiedad de un properties con parametros y
	 * los sustituimos por los valores recibidos.
	 * @param key Nombre de la propiedad
	 * @param params listado de parametros a sustituir
	 * @return String Valor de la propiedad especificada
	 * @throws NoControlableException si la propiedad no existe
	 */
	public static String getValoresMensajesError(final String key, final Object... params) {
		final String result = ProveedorMensajeSpringUtils.getValoresMensajesError(key);
		return MessageFormat.format(result, params);
	}


	private static void checkResult(final String key, final String result) {
		if(result == null || "".equalsIgnoreCase(result)){
			final String mensajeUsuario = "Valor " + key + " no encontrado";
			throw new NoControlableException(mensajeUsuario,
				ProveedorMensajeSpringUtils.class.getName()+": "+mensajeUsuario);
		}
	}


}
