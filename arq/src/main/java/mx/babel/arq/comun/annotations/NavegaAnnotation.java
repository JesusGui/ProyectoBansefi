package mx.babel.arq.comun.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Anotación para regresar el campo donde se encuentra la 
 * ruta de navegación
 * @author alejandro.pineda
 *
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface NavegaAnnotation {
	
	/**
	 * Método que regresa la variable que contiene la ruta navegar.
	 * @return String
	 */
	String campoEnum() default "";
}
