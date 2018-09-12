package mx.babel.arq.comun.aspects;

import java.util.Arrays;

import javax.servlet.http.HttpSession;

import mx.babel.arq.sesion.contexto.beans.BSContexto;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * Aspecto utlizado para log de todo método (entrada y salida)
 * @author gerard.chavez
 *
 */
@Component
@Aspect
public class LoggingAspect {
    private static final Logger LOGGER = LogManager.getLogger(LoggingAspect.class.getName());
    
    @Pointcut("within(*..CatalogoGeoUtils)")   
    private void inCatalogoGeoUtilsClase() {
        //Metodo en blanco ya que solo se trata de un pointcut
    }       
    
    @Pointcut("within(*..ConsultaCatalogoGeoBackend)")   
    private void inCatalogoBackendClase() {
        //Metodo en blanco ya que solo se trata de un pointcut
    }
    
    @Pointcut("within(*..ProgressBean)")   
    private void inProgressBeanClase() {
        //Metodo en blanco ya que solo se trata de un pointcut
    }
    
    
    @Pointcut("within(*..CatalogoUtils)")   
    private void inCatalogoUtilsClase() {
        //Metodo en blanco ya que solo se trata de un pointcut
    }
    
    @Pointcut("within(*..ConsultaCatalogoBackend)")   
    private void inConsultaCatalogoBackendClase() {
        //Metodo en blanco ya que solo se trata de un pointcut
    }
 
    
	@Around("execution(* mx.babel..*.*(..)) && !inCatalogoGeoUtilsClase() && !inCatalogoBackendClase() && !inProgressBeanClase() && ! inCatalogoUtilsClase() && !inConsultaCatalogoBackendClase()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
		
		try {
			ThreadContext.put("loginId", "["+((BSContexto) getSession().getAttribute("bsctxt")).getUsuario().getDireccionIp().replace(".","")+((BSContexto) getSession().getAttribute("bsctxt")).getUsuario().getId()+"]");
        } catch (IllegalStateException | NullPointerException e) {
        	
        }
		
		LOGGER.debug("*****Entrando a método : " + joinPoint.getSignature().getName()+"*****");
        LOGGER.debug("Argumentos : " + Arrays.toString(joinPoint.getArgs()));

        Object result = joinPoint.proceed(); // continue on the intercepted method
        LOGGER.debug("Resultado: " + result);
        LOGGER.debug("*****Saliendo de método : " + joinPoint.getSignature().getName()+"*****");
        return result;

    }
    
	private HttpSession getSession() {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder
				.currentRequestAttributes();
		return attr.getRequest().getSession(false);
	}
    
}