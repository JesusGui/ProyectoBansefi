package mx.babel.arq.comun.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Anotación que identifica que columnas se van a mostrar en la 
 * tabla de resultados.
 * @author alejandro.pineda
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface CampoResultadoAnnotation {
	/**
	 * Metodo que regresa la posición de la columna.
	 * @return int que representa la posición sobre la cual se despliega la columna.
	 */
	int posicion() default -1;
	/**
	 * Método que indica si se despliega o no un campo.
	 * @return int que representa la opcion sobre la cual se despliega el componente. 
	 */
	int desplegar() default 0;
	/**
	 * Método que indica si el campo se mandará como parametro en la flash.
	 * @return boolean que representa si el atributo se mandará como parametro. 
	 */
	boolean parametro() default false;
	/**
	 * Método que indica si el campo es llave principal.
	 * @return boolean que representa si el atributo es llave principal. 
	 */
	boolean key() default false;
	/**
	 * Método que indica si el campo es un resultado intermedio.
	 * @return boolean que representa si el atributo es un resultado intermedio. 
	 */
	boolean resultadoIntermedio() default false;
	/**
	 * @return string que devuelve el titulo a desplegar. 
	 */
	String tituloColumna() default "";
}
