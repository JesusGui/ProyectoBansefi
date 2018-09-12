package mx.babel.arq.storage.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**Anotacion que indica que se debe almacenar el resultado
 * del metodo anotado.
 * @author joseluis.pena
 *
 */
@Target(ElementType.METHOD)
public @interface StoreStep {
}
