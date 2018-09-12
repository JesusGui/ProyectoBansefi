package mx.babel.arq.sesion.aspects;

import java.util.List;

import javax.annotation.Resource;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.stereotype.Component;

/**
 * @author joseluis.pena
 *
 */
@Component
public class AccesoSesionInterceptor implements MethodInterceptor  {
        
    @Resource(name = "accesoSesionList")
    private List<String> accesoSesionList;
    
    @Override
    public Object invoke(MethodInvocation invocacion) throws Throwable {
        StackTraceElement[] trace = Thread.currentThread().getStackTrace();
        if (!checkStack(trace))  {            
            throw new IllegalAccessException("No tienes permisos para acceder al contexto de sesion");
        }
        else {
            return invocacion.proceed();
        }
    }
    
    private boolean checkStack(StackTraceElement[] trace)  {
        if(null != accesoSesionList && !accesoSesionList.isEmpty()){
            for (StackTraceElement traceElement : trace)  {
                if (accesoSesionList.contains(traceElement.getClassName())) {
                    return true;
                }
            }
        }
        return false;
    }
}

