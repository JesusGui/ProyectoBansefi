package mx.babel.bansefi.banksystem.base.utils;

import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.faces.model.SelectItem;

import mx.babel.arq.catalogo.beans.CatalogoBean;
import mx.babel.arq.comun.annotations.CampoBusquedaAnnotation;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.bansefi.banksystem.base.beans.busqueda.GrupoBusquedaBean;
import mx.babel.bansefi.banksystem.base.constants.ConstantesFuncionales;

import org.apache.commons.beanutils.MethodUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.extensions.model.dynaform.DynaFormModel;
import org.primefaces.extensions.model.dynaform.DynaFormRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Clase de utilidad para construir de manera dinámica un
 * <class>DynaFormModel</class>.
 * 
 * @author gerard.chavez
 * 
 */
@Component
public class DynaFormModelUtils implements Serializable {

	private static final long serialVersionUID = 3430752484576338625L;

	@Autowired
	private CatalogoUtils catalogoUtils;

	public DynaFormModelUtils() {

	}

	/**
	 * Método para construir un DynaFormModel de manera din�mica.
	 * 
	 * @param obj
	 *            Objeto del cual se construir el modelo
	 * @param opcion
	 *            entero que representa la opcion de desplegado seleccionada
	 * 
	 * @return DynaFormModel modelo que representa el formulario de búsqueda
	 */
	@SuppressWarnings("unchecked")
	public DynaFormModel construyeModelo(Object obj, int opcion) {
		DynaFormModel modelo = new DynaFormModel();
		int mostrar = opcion;
		for (Field field : obj.getClass().getDeclaredFields()) {
			if (field.isAnnotationPresent(CampoBusquedaAnnotation.class)) {
				Annotation annotations[] = field.getAnnotations();
				String inputType = "";
				boolean requerido = false;
				String regex = "";
				int desplegar = -1;
				String tituloCampo = "";
				String nombreCatalogo = "";
				int longitud = 1;
				String placeHolder = "";
				for (int i = 0; i < annotations.length; i++) {
					if (annotations[i] instanceof CampoBusquedaAnnotation) {
						CampoBusquedaAnnotation campoBusqueda = (CampoBusquedaAnnotation) annotations[i];
						inputType = campoBusqueda.tipo();
						requerido = campoBusqueda.requerido();
						if(obj.getClass().equals(GrupoBusquedaBean.class)){
							regex = "/^[-a-zA-ZÑñ ]*$/";
						}else{
							regex = campoBusqueda.regex();
						}
						desplegar = campoBusqueda.desplegar();
						tituloCampo = campoBusqueda.tituloCampo();
						longitud = campoBusqueda.longitud();
						nombreCatalogo = campoBusqueda.nombreCatalogo();
						placeHolder = campoBusqueda.placeHolder();
					}
				}
				// validar si se debe agregar el componente
				if (desplegar == -1 || mostrar == desplegar) {
					DynaFormRow fila = modelo.createRegularRow();
					DynaPropertyModel nuevoRenglon = new DynaPropertyModel(
							field.getName(), requerido);
					nuevoRenglon.setValidador(regex);
					nuevoRenglon.setLongitud(longitud);
					nuevoRenglon.setPlaceHolder(placeHolder);
					// agregar componente
					fila.addControl(nuevoRenglon, inputType.equals("") ? field
							.getType().getSimpleName().toLowerCase()
							: inputType);
					// guardar el nombre del campo
					nuevoRenglon.setNombre(StringUtils.capitalize(StringUtils
							.join(StringUtils
									.splitByCharacterTypeCamelCase(field
											.getName()), " ")));
					if ("".equals(tituloCampo)) {
						nuevoRenglon.setTituloCampo(StringUtils
								.capitalize(StringUtils.join(StringUtils
										.splitByCharacterTypeCamelCase(field
												.getName()), " ")));
					} else {
						nuevoRenglon.setTituloCampo(tituloCampo);
					}
					if (field.getType().getSimpleName().toLowerCase()
							.equals("list")) {

						List<PropertyDescriptor> propertyDescriptors = new ArrayList<PropertyDescriptor>(
								Arrays.asList(PropertyUtils
										.getPropertyDescriptors(obj.getClass())));

						for (int i = 0; i < propertyDescriptors.size(); i++) {
							PropertyDescriptor propertyDescriptor = (PropertyDescriptor) propertyDescriptors
									.get(i);
							if (propertyDescriptor.getName().equals(
									field.getName())) {
								try {
									List<SelectItem> lista = new ArrayList<SelectItem>();
									if (!("").equals(nombreCatalogo)) {
										if (!nombreCatalogo
												.equals(ConstantesFuncionales.CATALOGO_CENTROS)) {
											for (CatalogoBean catalogoBean : catalogoUtils
													.getCatalogo(nombreCatalogo)) {
												SelectItem item = new SelectItem();
												item.setValue(catalogoBean
														.getClaveFila());
												item.setLabel(catalogoBean
														.getDescripcionL());
												lista.add(item);
											}
											MethodUtils.invokeMethod(obj,
													propertyDescriptor
															.getWriteMethod()
															.getName(), lista);
										}
									}
									if (!nombreCatalogo
											.equals(ConstantesFuncionales.CATALOGO_CENTROS)) {
										nuevoRenglon
												.setSelectItems((List<SelectItem>) PropertyUtils
														.getProperty(obj,
																field.getName()));
									}
								} catch (IllegalAccessException e) {
									throw new NoControlableException(
											"Error creando el catalogo "
													+ field.getName(), e);
								} catch (InvocationTargetException e) {
									throw new NoControlableException(
											"Error creando el catalogo "
													+ field.getName(), e);
								} catch (NoSuchMethodException e) {
									throw new NoControlableException(
											"Error creando el catalogo "
													+ field.getName(), e);
								}
							}
						}
					} // Fin bloque if (type = list)
				} // Fin segundo bloque if
			} // Fin primer bloque if
		} // Fin bucle for
		return modelo;
	}
}