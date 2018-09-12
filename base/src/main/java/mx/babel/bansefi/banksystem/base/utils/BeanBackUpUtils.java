package mx.babel.bansefi.banksystem.base.utils;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import mx.babel.arq.comun.exceptions.NoControlableException;

import org.apache.commons.beanutils.PropertyUtilsBean;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Componente para respaldar bean en un arreglo o individualmente y poder recuperar su valor.
 * 
 * Como requisito los beans deben tener el atributo String respaldo con su correspondiente getter y setter
 * 
 * @author mario.montesdeoca
 */
@Component
public class BeanBackUpUtils {

	/**
	 * Fuciòn para respaldar arrays
	 * @param array array con la informaciòn
	 * @throws NoControlableException e caso de no poder generar el respaldo
	 */
	public void respaldaArray(ArrayList<?> array) throws NoControlableException {
		try {
			for (Object object : array) {
				object.getClass().getMethod("setRespaldo", String.class)
						.invoke(object, generaRespaldo(object));
			}
		} catch (NoSuchMethodException | SecurityException
				| IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			throw new NoControlableException("Error al generar respaldo.",
					e.getCause());
		}

	}

	/**
	 * Mètodo para recupoerar el respaldo de un bean
	 * @param object bean a respaldar
	 * @return string con json del objeto
	 * @throws NoControlableException
	 */
	public String generaRespaldo(Object object) throws NoControlableException {
		try {
			ObjectMapper mapper = new ObjectMapper();
			return mapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			throw new NoControlableException("Error al generar respaldo.",
					e.getCause());
		}
	}

	/**
	 * Mètodo para recuperar un estado del bean enviado.
	 * @param object bean a recuperar
	 */
	public void recuperaBean(Object object) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,	false);
			PropertyUtilsBean copyBean = new PropertyUtilsBean();
			copyBean.copyProperties(object,
					mapper.readValue((String)object.getClass().getMethod("getRespaldo").invoke(object), object.getClass()));
			object.getClass().getMethod("setRespaldo", String.class).invoke(object, generaRespaldo(object));
		} catch (IOException | IllegalAccessException
				| InvocationTargetException | NoSuchMethodException | NullPointerException e) {
			throw new NoControlableException("Error al recuperar respaldo.",
					e.getCause());
		}
	}
}
