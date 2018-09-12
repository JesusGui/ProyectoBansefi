package mx.babel.arq.cache.aspect;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mx.babel.arq.cache.annotation.Cache;
import mx.babel.arq.comun.utils.ProveedorMensajeSpringUtils;
import mx.babel.arq.task.TaskManager;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import net.sf.ehcache.statistics.StatisticsGateway;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;



/**Aspecto encargado de almacenar en ehcache el resultado de los
 * metodos anotados con @cache.
 * @author joseluis.pena
 *
 */
@Aspect
public class CacheAspect {

	private static final Logger LOGGER = LogManager.getLogger(CacheAspect.class);

    public static final String DEFAULT_CACHE_NAME = "catalogoCache";
    
    public static final String GEO_CACHE_NAME = "catalogoGeoCache";

    private CacheManager cacheManager;

    private Map<String, Ehcache> caches;

    private final Gson gson;

    public CacheAspect() {
        gson = new GsonBuilder().create();
    }

    /*@Pointcut("within(*..CatalogoGeoUtils)")
    private void inCatalogoUtilsClase() {
        //Metodo en blanco ya que solo se trata de un pointcut
    }


    @Pointcut("within(*..ConsultaCatalogoGeoBackend)")
    private void inCatalogoBackendClase() {
        //Metodo en blanco ya que solo se trata de un pointcut
    }

    @Around("@annotation(mx.babel.arq.cache.annotation.Cache) && !inCatalogoUtilsClase() && !inCatalogoBackendClase()")*/
    @Around("@annotation(mx.babel.arq.cache.annotation.Cache)")
    public Object cache(final ProceedingJoinPoint joinPoint) throws Throwable {
    	Object result = null;
		this.getStatistics("entrada");

        //Saco la anotacion Cache para ver los valores de configuracion
        final Method method = getMethod(joinPoint);

        final Cache cacheAnnotation = method.getAnnotation(Cache.class);

        if (cacheAnnotation != null && cacheAnnotation.cache()) {
            final Ehcache cache = getCache(cacheAnnotation.cacheName());
            final boolean ignoreEmpty = cacheAnnotation.ignoreEmpty();
            if (cache != null) {
                final String nameElement = getCacheKey(joinPoint, method);
                if (cache.getKeys() != null && cache.getKeys().contains(nameElement)) {
                    final Element e = cache.get(nameElement);
                    if (e != null && e.getObjectValue() != null) {
                    	LOGGER.debug("CACHE Encontrado: Elemento" +nameElement);

                    	result = e.getObjectValue();
                    } else {
                    	LOGGER.debug("CACHE Encontrado con valor nulo: Elemento" +nameElement);
                    	result = cacheElement(joinPoint, cache, nameElement, ignoreEmpty);
                    }
                } else {
                	LOGGER.debug("CACHE No encontrado: Elemento" +nameElement);
                	result = cacheElement(joinPoint, cache, nameElement, ignoreEmpty);
                }
            }
        }else{
	        LOGGER.debug("Anotacion no encontrada: Elemento" +joinPoint.getTarget().getClass().getName()+"." +method.getName());
	        //No hay anotacion o esta desactivada la cache
	        result = joinPoint.proceed();
        }
        this.getStatistics("salida");
        return result;
    }

   private void getStatistics(final String punto) {
	    LOGGER.debug("CacheAspect: estadisticas "+punto+"¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬");
        final Ehcache cache = cacheManager.getCache(DEFAULT_CACHE_NAME);
        final StatisticsGateway stats = cache.getStatistics();
        LOGGER.debug(String.format("%s: %s objects, %s hits, %s misses",
        		DEFAULT_CACHE_NAME,
                stats.cachePutAddedCount() ,
                stats.cacheHitCount(),
                stats.cacheMissCount()
              ));
        LOGGER.debug(String.format("%s: %s size, %s localHeapSize %s bytes,  %s localDiskSize %s bytes, remainLocalHeap %s bytes",
        		DEFAULT_CACHE_NAME,
                stats.getSize(),
                stats.getLocalHeapSize(),
                stats.getLocalHeapSizeInBytes(),
                stats.getLocalDiskSize(),
                stats.getLocalDiskSizeInBytes(),
                cache.getCacheConfiguration().getMaxBytesLocalHeap() - stats.getLocalHeapSizeInBytes()
        		));
        LOGGER.debug("CacheAspect: estadisticas Geo "+punto+"¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬");
        final Ehcache cacheGeo = cacheManager.getCache(GEO_CACHE_NAME);
        final StatisticsGateway statsGeo = cacheGeo.getStatistics();
        LOGGER.debug(String.format("%s: %s objects, %s hits, %s misses",
        		GEO_CACHE_NAME,
        		statsGeo.cachePutAddedCount() ,
        		statsGeo.cacheHitCount(),
        		statsGeo.cacheMissCount()
              ));
        LOGGER.debug(String.format("%s: %s size, %s localHeapSize %s bytes,  %s localDiskSize %s bytes, remainLocalHeap %s bytes",
        		GEO_CACHE_NAME,
        		statsGeo.getSize(),
        		statsGeo.getLocalHeapSize(),
        		statsGeo.getLocalHeapSizeInBytes(),
        		statsGeo.getLocalDiskSize(),
        		statsGeo.getLocalDiskSizeInBytes(),
        		cacheGeo.getCacheConfiguration().getMaxBytesLocalHeap() - statsGeo.getLocalHeapSizeInBytes()
        		));
	}

	private Object cacheElement(final ProceedingJoinPoint joinPoint,
			final Ehcache cache, final String nameElement, final Boolean ignoreEmpty) throws Throwable {
        final Object result = joinPoint.proceed();
        if (ignoreEmpty || isNotListAndNotNull(result)|| isNotEmptyList(result)) {
            LOGGER.debug("CACHE PUT DATA FOR " +nameElement);
            cache.put(new Element(nameElement, result));
        }
        return result;
    }

    private boolean isNotEmptyList(final Object result) {
        if(result instanceof List && !((List<?>)result).isEmpty()){
            return true;
        }
        return false;
    }

    private boolean isNotListAndNotNull(final Object result){
        if (result != null && !(result instanceof List)){
            return true;
        }
        return false;
    }

    private Method getMethod(final ProceedingJoinPoint joinPoint) {
        final MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        if (method.getDeclaringClass().isInterface()) {
            try {
                method = joinPoint.getTarget().getClass().
                		getDeclaredMethod(joinPoint.getSignature().getName(),
                        method.getParameterTypes());
            } catch (final SecurityException exception) {
            	LOGGER.debug(exception.getMessage(), exception.getCause());
            } catch (final NoSuchMethodException exception) {
            	LOGGER.debug(exception.getMessage(), exception.getCause());
            }
        }
        return method;
    }

    private String getCacheKey(final ProceedingJoinPoint joinPoint, final Method method) {
        final StringBuilder result = new StringBuilder();
        result.append(joinPoint.getTarget().getClass().getName());
        result.append(method.getName());
        final Object[] args = filterArguments(joinPoint.getArgs());
        if (args != null && args.length > 0) {
            try {
                result.append(gson.toJson(args));
            } catch (final Exception e) {
            	LOGGER.debug(e.getMessage(), e.getCause());
                result.append(StringUtils.join(args));
            }
        }
        LOGGER.debug("CACHE LOOKING FOR " +result);
        return result.toString();
    }

    private Object[] filterArguments(final Object... arguments) {
        if (arguments != null) {
            final List<Object> result = new ArrayList<Object>();
            for (final Object obj : arguments) {
                    result.add(obj);
            }
            return result.toArray();
        }
        return ArrayUtils.EMPTY_OBJECT_ARRAY;
    }

    private Ehcache getCache(final String name) {
    	String cacheName = name;
        if (StringUtils.isBlank(name)) {
        	cacheName = DEFAULT_CACHE_NAME;
        }
        if (caches == null) {
            caches = new HashMap<String, Ehcache>();
        }
        if (!caches.containsKey(cacheName)) {
            this.caches.put(cacheName, cacheManager.getEhcache(cacheName));
        }
        return caches.get(cacheName);
    }

    public void setCacheManager(final CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    @Autowired
    private TaskManager taskManager;

    @AfterThrowing(pointcut = "execution(* mx.babel.arq.task.ILoaderTask.loadTask(..))", throwing= "error")
    public void scheduleAfterThrowing(final JoinPoint joinPoint, final Throwable error){
    	final Object cargaFallida = joinPoint.getThis();
    	Long tiempoDeNuevaEjecucion = 300000L;//5 minutos por default
		String valor = null;

    	try{
    		valor = ProveedorMensajeSpringUtils.getValorConfiguracion("ejecutar.en.milisegundos");
    		tiempoDeNuevaEjecucion = Long.valueOf(valor);
    	}catch(final Exception e){
    		LOGGER.error("No logra obtener el tiempo de recalendarización. Se ejecutará la tarea en 5 minutos.");
    	}

		LOGGER.debug("Se calendariza "+cargaFallida+" por falla. Se ejecutará en:"+(tiempoDeNuevaEjecucion/1000)+" segundos.");
		taskManager.ejecutarTarea(tiempoDeNuevaEjecucion, cargaFallida);
    }

}
