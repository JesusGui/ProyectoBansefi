package mx.babel.bansefi.banksystem.base.utils;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.faces.context.FacesContext;

import mx.babel.arq.comun.exceptions.ControlableException;

/**
 * Clase utilidad que accede a los properties para retornar mensajes del
 * properties: mensaje_vista.properties y para acceder a catalogo tipos y
 * opciones de busqueda.
 * 
 * @author eliot.vasquez
 * 
 */

public class ProveedorMensajeUtils {

	final static FacesContext FACES_CONTEXT = FacesContext.getCurrentInstance();
	private final static ResourceBundle APPLICATION_BUNDLE = FACES_CONTEXT
			.getApplication().getResourceBundle(FACES_CONTEXT, "msg");
	private final static ResourceBundle BUNDLE_TIPOS_BUSQUEDA = ResourceBundle
			.getBundle("META-INF/resources/properties/catalogo_busquedas");
	private final static ResourceBundle BUNDLE_SERVICIOS_WEB = ResourceBundle
			.getBundle("META-INF/resources/properties/servicios_web");

	public static FacesContext getFacesContext() {
		return FACES_CONTEXT;
	}

	public static ResourceBundle getApplicationBundle() {
		return APPLICATION_BUNDLE;
	}

	public static ResourceBundle getBundleTiposBusqueda() {
		return BUNDLE_TIPOS_BUSQUEDA;
	}

	public static ResourceBundle getBundleServiciosWeb() {
		return BUNDLE_SERVICIOS_WEB;
	}
	
	/**
	 * Método con el que regresamos el texto asignado en el properties
	 * de aplicacion.
	 * @param key Nombre de la propiedad
	 * @return String Valor de la propiedad especificada
	 */
	public static String getValue(String key) {

		String result = null;
		try {
			result = getApplicationBundle().getString(key);
		} catch (MissingResourceException e) {
			result = "???" + key + "??? no encontrado!";
		}
		return result;
	}

	/**
	 * Metodo con el que regresamos el texto asignado en el properties
	 * de tipos.
	 * @param key Nombre de la propiedad
	 * @return String Valor de la propiedad especificada
	 */
	public static String getValoresTipos(String key) {
		String result = null;
		try {
			result = getBundleTiposBusqueda().getString(key);
		} catch (MissingResourceException e) {
			throw new ControlableException("No se puede ejecutar la operación de búsqueda, valor no encontrado.", "La opción de búsqueda solicitada no existe");
		}
		return result;
	}

	/**
	 * Metodo con el que regresamos el texto asignado en el properties
	 * de Servicios Web.
	 * @param key Nombre de la propiedad
	 * @return String Valor de la propiedad especificada
	 */
	public static String getValoresServiciosWeb(String key) {
		String result = null;
		try {
			result = getBundleServiciosWeb().getString(key);
		} catch (MissingResourceException e) {
			result = "Valor " + key + " no encontrado";
		}
		return result;
	}
	
	
	/**
	 * Metodo con el que regresamos el texto asignado en el properties
	 * de Servicios Web.
	 * @param key Nombre de la propiedad
	 * @return String Valor de la propiedad especificada
	 */
	
}
