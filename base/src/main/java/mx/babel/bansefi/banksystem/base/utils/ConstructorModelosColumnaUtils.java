package mx.babel.bansefi.banksystem.base.utils;

import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import mx.babel.arq.comun.annotations.CampoResultadoAnnotation;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * Clase que construye las columnas que se van a mostrar en la tabla de
 * Resultados de Busqueda en la ventana del Buscador.
 * 
 * @author alejandro.pineda
 * 
 */
public class ConstructorModelosColumnaUtils {


	/**
	 * Método que construye las columnas a mostrar en la tabla de Resultados de
	 * Búsqueda.
	 * 
	 * @param modeloClase
	 *            Clase de la cual se cargará la lista de columnas.
	 * @param opcion
	 *            Opción de las columnas que se mostraran.
	 * 
	 * @return Lista de columnas a pintar en la tabla
	 */
	public static List<ModeloColumnaUtils> construir(Class modeloClase,
			int opcion, boolean tablaIntermedia) {
		List<ModeloColumnaUtils> columnas = new ArrayList<ModeloColumnaUtils>(0);

		List<PropertyDescriptor> propertyDescriptors = new ArrayList<PropertyDescriptor>(
				Arrays.asList(PropertyUtils.getPropertyDescriptors(modeloClase)));

		for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
			if (!propertyDescriptor.getName().equals("class")) {
				try {
					Field field = null;
					try {
						field = modeloClase.getDeclaredField(propertyDescriptor
								.getName());
					} catch (NoSuchFieldException n) {
						field = modeloClase.getSuperclass().getDeclaredField(
								propertyDescriptor.getName());
					}

					if (field
							.isAnnotationPresent(CampoResultadoAnnotation.class)) {
						Annotation annotations[] = field.getAnnotations();
						int posicion = -1;
						int desplegar = 0;
						boolean campoTablaIntermedia = false;
						String nombreColumna = "";
						for (int i = 0; i < annotations.length; i++) {
							if (annotations[i] instanceof CampoResultadoAnnotation) {
								CampoResultadoAnnotation campoResultado = (CampoResultadoAnnotation) annotations[i];
								posicion = campoResultado.posicion();
								desplegar = campoResultado.desplegar();
								campoTablaIntermedia = campoResultado.resultadoIntermedio();
								nombreColumna = campoResultado.tituloColumna();
							}
						}
						if(!tablaIntermedia){
							if (desplegar == 0 || desplegar == opcion) {
								ModeloColumnaUtils columnaDescriptor = new ModeloColumnaUtils();
								columnaDescriptor.setPropiedad(propertyDescriptor
										.getName());
								if("".equals(nombreColumna)){
									columnaDescriptor
											.setHeader(StringUtils.capitalize
													(StringUtils.join(
													StringUtils
														.splitByCharacterTypeCamelCase
															(propertyDescriptor
																	.getName()), " ")));
								}else{
									columnaDescriptor.setHeader(nombreColumna);
								}
								columnaDescriptor.setTipo(propertyDescriptor
										.getPropertyType());
								columnaDescriptor.setPosicion(posicion);
								columnas.add(columnaDescriptor);
							}
						}else{
							if(campoTablaIntermedia){
								ModeloColumnaUtils columnaDescriptor = new ModeloColumnaUtils();
								columnaDescriptor.setPropiedad(propertyDescriptor
										.getName());
								
								if("".equals(nombreColumna)){
									columnaDescriptor
											.setHeader(StringUtils.capitalize
													(StringUtils.join(
													StringUtils
														.splitByCharacterTypeCamelCase
															(propertyDescriptor
																	.getName()), " ")));
								}else{
									columnaDescriptor.setHeader(nombreColumna);
								}
								columnaDescriptor.setTipo(propertyDescriptor
										.getPropertyType());
								columnaDescriptor.setPosicion(posicion);
								columnas.add(columnaDescriptor);
							}
						}
					}
				} catch (NoSuchFieldException | SecurityException e) {
					e.printStackTrace();
				}
			}
		}
		//Ordena la lista de columnas en base a la posición
		Comparator<ModeloColumnaUtils> columnSortComparator = new Comparator<ModeloColumnaUtils>() {
			public int compare(ModeloColumnaUtils o1, ModeloColumnaUtils o2) {
				return o1.getPosicion() < o2.getPosicion() ? -1 : o1
						.getPosicion() == o2.getPosicion() ? 0 : 1;
			}
		};
		Collections.sort(columnas, columnSortComparator);
		return columnas;
	}

}
