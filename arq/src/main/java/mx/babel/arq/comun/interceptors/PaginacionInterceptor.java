package mx.babel.arq.comun.interceptors;

import java.util.List;

import mx.babel.arq.comun.annotations.MetodoPaginacionAnnotation;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.lang3.reflect.MethodUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;


/**
 * Interceptor para componente de paginación dinámica
 * @author gerard.chavez
 *
 */
@Component
public class PaginacionInterceptor implements MethodInterceptor {
    
    private static final Logger LOGGER = LogManager.getLogger(PaginacionInterceptor.class.getName());
    
    @Override
    public Object invoke(MethodInvocation invocacion) throws Throwable {
        MetodoPaginacionAnnotation anotacion = invocacion.getMethod().getAnnotation(MetodoPaginacionAnnotation.class);
        LOGGER.debug("Method: "+invocacion.getMethod()+" is called on: "+
                invocacion.getThis().getClass()+" with args: "+invocacion.getArguments());
        Object o = invocacion.getThis();
        
        //1. Si existen más páginas se debe invocar nuevamente al metodo de búsqueda
        
        //2. Invocar al método de busqueda (especificado en la anotación) con un nuevo parámetro 
        //   para indicar que se desea la siguiente página 
        List<Object> resultadoFinal = (List<Object>) MethodUtils.invokeExactMethod(o, anotacion.metodoBusqueda(), null);
        
        //3. Agregar al return los datos obtenidos.
        List<Object> resultadoInicial = (List<Object>) invocacion.proceed();
        resultadoFinal.addAll(resultadoInicial);
        
        //1.1 Si no existen mas páginas guardar objeto de búsqueda para poder invocar a futuro
       
        return resultadoFinal;
    }

}
