package mx.babel.bansefi.banksystem.base.utils;

import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mx.babel.arq.comun.annotations.CampoResultadoAnnotation;

import org.apache.commons.beanutils.MethodUtils;
import org.apache.commons.beanutils.PropertyUtils;

/**
 * Clase que obtiene los parametros que se van a enviar al seleccionar un
 * detalle en los resultados de búsqueda.
 * 
 * @author alejandro.pineda
 * 
 */
public class ParametrosFlashUtils {

	/**
	 * Método que obtiene los parametros a enviar al seleccionar en la tabla de
	 * Resultados de Búsqueda una fila.
	 * 
	 * @param filaDetalle
	 *            Fila seleccionada en la tabla de resultados.
	 * @param clase
	 *            Clase que ejecutará el método.
	 * 
	 * @return Mapeos que se cargaran en la flash del FacesContext
	 */
	public static Map<String, Object> obtenerParametros(Object filaDetalle,
			String clase) {
		HashMap<String, Object> parametros = new HashMap<String, Object>();

		List<PropertyDescriptor> propertyDescriptors = new ArrayList<PropertyDescriptor>(
				Arrays.asList(PropertyUtils.getPropertyDescriptors(filaDetalle
						.getClass())));

		for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
			if (!propertyDescriptor.getName().equals("class")) {
				try {
					Field field = null;
					try {
						field = Class.forName(clase).getDeclaredField(
								propertyDescriptor.getName());
					} catch (NoSuchFieldException n) {
						field = Class.forName(clase).getSuperclass()
								.getDeclaredField(propertyDescriptor.getName());
					}

					if (field
							.isAnnotationPresent(CampoResultadoAnnotation.class)) {
						Annotation annotations[] = field.getAnnotations();
						boolean parametro = false;
						for (int i = 0; i < annotations.length; i++) {
							if (annotations[i] instanceof CampoResultadoAnnotation) {
								CampoResultadoAnnotation campoResultado = (CampoResultadoAnnotation) annotations[i];
								parametro = campoResultado.parametro();
							}
						}
						if (parametro) {

							Object valor = null;

							try {
								// Se ejecuta método de la propiedad que tiene
								// la anotación
								valor = MethodUtils.invokeMethod(
										Class.forName(clase).cast(filaDetalle),
										propertyDescriptor.getReadMethod()
												.getName(), null);
							} catch (ClassNotFoundException
									| NoSuchMethodException
									| IllegalAccessException
									| InvocationTargetException e) {
								e.printStackTrace();
							}
							parametros.put(propertyDescriptor.getName(), valor);
						}
					}
				} catch (NoSuchFieldException | SecurityException
						| ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		}
		return parametros;
	}

	/**
	 * Método que obtiene los parametros a enviar al seleccionar en la tabla de
	 * Resultados de Búsqueda una fila.
	 * 
	 * @param fila
	 *            Fila seleccionada en la tabla de resultados.
	 * @param clase
	 *            Clase que ejecutará el método.
	 * 
	 * @return Mapeos que se cargaran en la flash del FacesContext
	 */
	public static Object obtenerKey(Object fila, String clase) {
		Object valor = null;
		List<PropertyDescriptor> propertyDescriptors = new ArrayList<PropertyDescriptor>(
				Arrays.asList(PropertyUtils.getPropertyDescriptors(fila
						.getClass())));

		for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
			if (!propertyDescriptor.getName().equals("class")) {
				try {
					Field field = null;
					try {
						field = Class.forName(clase).getDeclaredField(
								propertyDescriptor.getName());
					} catch (NoSuchFieldException n) {
						field = Class.forName(clase).getSuperclass()
								.getDeclaredField(propertyDescriptor.getName());
					}

					if (field
							.isAnnotationPresent(CampoResultadoAnnotation.class)) {
						Annotation annotations[] = field.getAnnotations();
						boolean key = false;
						for (int i = 0; i < annotations.length; i++) {
							if (annotations[i] instanceof CampoResultadoAnnotation) {
								CampoResultadoAnnotation campoResultado = (CampoResultadoAnnotation) annotations[i];
								key = campoResultado.key();
							}
						}
						if (key) {

							try {
								// Se ejecuta método de la propiedad que tiene
								// la anotación
								valor = MethodUtils.invokeMethod(
										Class.forName(clase).cast(fila),
										propertyDescriptor.getReadMethod()
												.getName(), null);
							} catch (ClassNotFoundException
									| NoSuchMethodException
									| IllegalAccessException
									| InvocationTargetException e) {
								e.printStackTrace();
							}
						}
					}
				} catch (NoSuchFieldException | SecurityException
						| ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		}
		return valor;
	}
}
