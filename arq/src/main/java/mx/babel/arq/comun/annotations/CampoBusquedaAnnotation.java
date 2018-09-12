package mx.babel.arq.comun.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Anotación que se utiliza para definir los campos que apareceran
 * dinamicamente.
 * 
 * @author gerard.chavez
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface CampoBusquedaAnnotation {
	
	/**
	 * @return String que identifica el tipo de componente
	 * 
	 */
	String tipo() default "";

	/**
	 * @return String que representa cualquier caracter 0 o más veces.
	 */
	String regex() default "/[a-z0-9_]/i";

	/**
	 * @return boolean que define si un componente debe requerir un valor.
	 */
	boolean requerido() default false;

	/**
	 * @return int que representa la opcion sobre la cual se despliega el componente.
	 */
	int desplegar() default -1;

	/**
	 * @return int que representa la longitud maxima del campo.
	 */
	int longitud() default 1;

	/**
	 * @return string que devuelve el titulo a desplegar.
	 */
	String tituloCampo() default "";

	/**
	 * @return string que devuelve el nombre del catalogo que se quiere cargar.
	 */
	String nombreCatalogo() default "";

	/**
	 * @return string que representa el marcador de posición que se desea mostrar en el componente.
	 */
	String placeHolder() default "";

}