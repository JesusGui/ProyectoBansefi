package mx.babel.arq.comun.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Anotación para interceptar paginación
 * @author gerard.chavez
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface MetodoPaginacionAnnotation {
    String metodoBusqueda() default "";
}
