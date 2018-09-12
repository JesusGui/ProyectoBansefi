package mx.babel.arq.cache.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * Anotacion que permite cachear el resultado de un metodo.
 * @author joseluis.pena
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Cache {
    public boolean cache() default true;
    public String cacheName() default "catalogoCache";
    public boolean ignoreEmpty() default false;
}
