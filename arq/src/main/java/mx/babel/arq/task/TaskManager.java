package mx.babel.arq.task;

import java.util.Calendar;
import java.util.Date;

import javax.faces.event.AbortProcessingException;

import mx.babel.arq.cache.aspect.CacheAspect;
import mx.babel.arq.sesion.utils.ContextoUtils;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;

/**Tarea encargada de vaciar la cache de catalogos y recargar
 * aquellos catalogos demasiado grandes.
 * @author joseluis.pena
 *
 */
@Component
public class TaskManager {

	private static final Logger LOGGER = LogManager.getLogger(TaskManager.class);
			
	@Autowired
	ICatalogoLoaderTask catalogoUtils;
	
	@Autowired
	ICatalogoGeoLoaderTask catalogoGeoUtils;

	@Autowired
    ILoaderTask tarifasLoaderTask;
	
	@Autowired
	ICatalogoCentrosLoaderTask catalogoCentrosLoaderTask;
	
	@Autowired
	ICatalogoListaEmpleadosLoaderTask catalogoListaEmpleadosLoaderTask;
	
	@Autowired
	ICatalogoPaisesLoaderTask catalogoPaisesLoaderTask;
	
	@Autowired
	ICatalogoPerfilesTCBLoaderTask catalogoPerfilesTCBLoaderTask;
	
	@Autowired
	ICatalogoMonedaLoaderTask catalogoMonedaLoaderTask;
	
	@Autowired
	ContextoUtils contextoUtils;

	@Autowired
	CacheManager cacheManager;
	
	@Autowired
	ThreadPoolTaskScheduler taskScheduler;

	@Scheduled(initialDelay = 4000, fixedDelay = 600000000)
	public void dispararCargaCatalogo() throws Exception {
		LOGGER.debug("Sesiones activas:"+contextoUtils.getNumeroSesionesActivas());
		if (contextoUtils.getNumeroSesionesActivas() < 50) {
			LOGGER.debug("Se empiezan a regenerar los catálogos.");
			this.regenerarCatalogo();
		}
	}
  
    @Async
    public void regenerarCatalogo() throws AbortProcessingException {
        Ehcache cache = cacheManager.getEhcache(CacheAspect.DEFAULT_CACHE_NAME);
        cache.removeAll();

        try{
        	catalogoUtils.loadTask();
        }catch(Exception ignorar){
        	LOGGER.error("Falla al cargar los catálogos generales.",ignorar);
        }
                
        try {
			catalogoCentrosLoaderTask.loadTask();
		} catch (Exception ignorar) {
			LOGGER.error("Falla al cargar catálogo de centros.",ignorar);
		}
        
        
        try {
			catalogoListaEmpleadosLoaderTask.loadTask();
		} catch (Exception ignorar) {
			LOGGER.error("Falla al cargar catálogo de lista de empleados.",ignorar);
		}
        
        try {
			catalogoPaisesLoaderTask.loadTask();
		} catch (Exception ignorar) {
			LOGGER.error("Falla al cargar catálogo de paises.",ignorar);
		}
        
        try {
			catalogoPerfilesTCBLoaderTask.loadTask();
		} catch (Exception ignorar) {
			LOGGER.error("Falla al cargar catálogo de perfiles TCB.",ignorar);
		}
        
        try {
			catalogoMonedaLoaderTask.loadTask();
		} catch (Exception ignorar) {
			LOGGER.error("Falla al cargar catálogo de monedas.",ignorar);
		}
        
        try{
        	tarifasLoaderTask.loadTask();
        }catch(Exception ignorar){
        	LOGGER.error("Falla al cargar catálogo de tarifas.",ignorar);
        }
        
        cache = cacheManager.getEhcache(CacheAspect.GEO_CACHE_NAME);
        cache.removeAll();
        
        try {
			catalogoGeoUtils.loadTask();
		} catch (Exception ignorar) {
			/* 
			 * Se calendariza la carga de este catálogo. 
			 * La exception se maneja desde CacheAspect.scheduleAfterThrowing() para todas
			 * las clases que implementan el método public void loadTask() throws Exception de 
			 * la interfaz ILoaderTask 
			 */
		}
    }

    @Async
    public void ejecutarTarea(Long tiempoEnMilis, Object proceso) {
    	Calendar today = null;
    	Long executionTime = 0L;
    	
        today = Calendar.getInstance();
        executionTime = today.getTimeInMillis()+tiempoEnMilis;
        EjecutorDeTareas ejecutorDeTareas = new EjecutorDeTareas();
        ejecutorDeTareas.setProceso(proceso);
        taskScheduler.schedule(ejecutorDeTareas, new Date(executionTime));
    }
    
}