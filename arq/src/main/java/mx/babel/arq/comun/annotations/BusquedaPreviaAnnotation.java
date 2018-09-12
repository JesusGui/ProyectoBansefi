package mx.babel.arq.comun.annotations;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({})
public @interface BusquedaPreviaAnnotation {
	/**
	 * Metodo que indica el backend a llamar.
	 * @return string con el nombre completo de la clase backend.
	 */
    public String backend() default "";
    /**
	 * Metodo que indica el tipo de servicio a llamar (TRN o WS).
	 * @return string con el nombre del tipo de servicio.
	 */
    public String tipoServicio() default "";
    /**
	 * Metodo que regresa la opción de búsqueda sobre la cual disparar consulta intermedia.
	 * @return int que representa la opción de búsqueda.
	 */
    public int opcionBusqueda() default -1;
    /**
	 * Metodo que regresa el objeto búsqueda sobre el cual disparar consulta intermedia.
	 * @return int que representa la opción de búsqueda.
	 */
    public String objetoBusqueda() default "";
    /**
	 * Metodo que indica los nombres de los parámetros de búsqueda a pasar al backend.
	 * @return strings con los nombres de los parámetros.
	 */
    public String[] parametros() default "";
    /**
	 * Metodo que indica si se debe construir un bean del tipo objetoBusqueda
	 * con parametros
	 */
    public boolean construyeBean() default false;
    /**
	 * Metodo que indica los nombres de las collumnas para mostrar como resultado.
	 * @return strings con los nombres de los campos que representan columnas.
	 */
    public String[] columnasResultado() default "";
    /**
 	 * Metodo que indica el tipo de búsqueda final a realizar
 	 */
    public int opcionSegundaBusqueda() default -1;
    /**
 	 * Metodo que indica el parámetro a utilizar en la segunda busqueda
 	 */
    public String paramSegundaBusqueda() default "";
        
}
