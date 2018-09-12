package mx.babel.arq.storage.aspect;

import mx.babel.arq.storage.beans.StorageMgrBean;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/**Aspecto que almacena el resultado del metodo anotado
 * en la clase padre.
 * @author joseluis.pena
 *
 */
@Aspect
public class StorageAspect {

	@Around("@annotation(mx.babel.arq.storage.annotations.StoreStep)")
    public Object storeStep(final ProceedingJoinPoint joinPoint) throws Throwable {
		Object result;
        result = joinPoint.proceed();
        
		final Object target = joinPoint.getTarget();
		if(null != target && target instanceof StorageMgrBean && result instanceof String){
			((StorageMgrBean)target).setLastAction((String)result);
		}
		return result;
	}


}
