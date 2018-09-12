package mx.babel.arq.comun.controller;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import mx.babel.arq.cache.aspect.CacheAspect;
import mx.babel.arq.task.ICatalogoCentrosLoaderTask;
import mx.babel.arq.task.ICatalogoGeoLoaderTask;
import mx.babel.arq.task.ICatalogoListaEmpleadosLoaderTask;
import mx.babel.arq.task.ICatalogoLoaderTask;
import mx.babel.arq.task.ICatalogoMonedaLoaderTask;
import mx.babel.arq.task.ICatalogoPaisesLoaderTask;
import mx.babel.arq.task.ICatalogoPerfilesTCBLoaderTask;
import mx.babel.arq.task.ILoaderTask;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@ManagedBean
@ViewScoped
@Component
@Scope("view")
public class RecargaCatalogosController implements Serializable {

	private static final long serialVersionUID = -8474688499122872510L;
	private static final Logger LOGGER = LogManager.getLogger(RecargaCatalogosController.class);
	
	private static final String GENERALES_BACK_END = "ConsultaCatalogoBackend";
	private static final String TARIFAS_BACK_END = "ConsultaGrpPrdVendBackend";
	private static final String CENTROS_BACK_END = "BusquedaCentroNombreBackEnd";
	private static final String EMPLEADOS_BACK_END = "ConsultaListaEmpleadosBackEnd";
	private static final String PAISES_BACK_END = "ConsultaPaisesBackEnd";
	private static final String PERFILES_BACK_END = "ConsultaListaPerfilesTCBBackEnd";
	private static final String MONEDAS_BACK_END = "CatalogoMonedaBackEnd";
	
	@Autowired
	CacheManager cacheManager;
	
	@Autowired
	private ICatalogoLoaderTask catalogoUtils;
	
	@Autowired
	private ILoaderTask tarifasLoaderTask;
	
	@Autowired
	private ICatalogoGeoLoaderTask catalogoGeoUtils;
	
	@Autowired
	private ICatalogoCentrosLoaderTask catalogoCentrosLoaderTask;
	
	@Autowired
	private ICatalogoPaisesLoaderTask catalogoPaisesLoaderTask;
	
	@Autowired
	private ICatalogoMonedaLoaderTask catalogoMonedaLoaderTask;
	
	@Autowired
	private ICatalogoPerfilesTCBLoaderTask catalogoPerfilesTCBLoaderTask;
	
	@Autowired
	private ICatalogoListaEmpleadosLoaderTask catalogoListaEmpleadosLoaderTask;
	
	
	
	private Map<String,Integer> cantidadCatalogosGeneralesMap = null;
	
	private Map<String,Integer> cantidadCatalogosGEOMap = null;
	
	private Map<String,Integer> cantidadCatalogoTarifasMap = null;
	
	private Map<String,Integer> cantidadCatalogoCentrosMap = null;
	
	private Map<String,Integer> cantidadCatalogoPaisesMap = null;
	
	private Map<String,Integer> cantidadCatalogoMonedasMap = null;
	
	private Map<String,Integer> cantidadCatalogoPerfilesTCBMap = null;
	
	private Map<String,Integer> cantidadCatalogoListaEmpleadosMap = null;
	
	@PostConstruct
	public void init(){
		this.cargaDatosIniciales();
	}
	
	public String goToDashBoard(){
		return "/views/inicio/inicio?faces-redirect=true";
	}
	
	private void cargaDatosIniciales(){
    	this.setCantidadCatalogosGeneralesMap(catalogoUtils.getCantidadCatalogosMap());
		this.setCantidadCatalogoTarifasMap(tarifasLoaderTask.getCantidadCatalogosMap());
    	this.setCantidadCatalogosGEOMap(catalogoGeoUtils.getCantidadCatalogosMap());
		this.setCantidadCatalogoCentrosMap(catalogoCentrosLoaderTask.getCantidadCatalogosMap());
		this.setCantidadCatalogoPaisesMap(catalogoPaisesLoaderTask.getCantidadCatalogosMap());
		this.setCantidadCatalogoMonedasMap(catalogoMonedaLoaderTask.getCantidadCatalogosMap());
		this.setCantidadCatalogoPerfilesTCBMap(catalogoPerfilesTCBLoaderTask.getCantidadCatalogosMap());
		this.setCantidadCatalogoListaEmpleadosMap(catalogoListaEmpleadosLoaderTask.getCantidadCatalogosMap());
	}
	
	public void recargaCatalogosGenerales(){
        try{
        	removeCache(CacheAspect.DEFAULT_CACHE_NAME, GENERALES_BACK_END);
        	catalogoUtils.loadTask();
        	this.setCantidadCatalogosGeneralesMap(catalogoUtils.getCantidadCatalogosMap());
        }catch(Exception e){
        	LOGGER.error("Falla en la recarga de catálogos generales.",e);
        }
	}
	
	public void recargarCatalogosTarifas(){
		try {
			removeCache(CacheAspect.DEFAULT_CACHE_NAME, TARIFAS_BACK_END);
			tarifasLoaderTask.loadTask();
			this.setCantidadCatalogoTarifasMap(tarifasLoaderTask.getCantidadCatalogosMap());
		} catch (Exception e) {
			LOGGER.error("Falla en la recarga de catálogos GEO.",e);
		}
	}
	
	public void recargarCatalogosGEO(){
        try {
        	final Ehcache cache = cacheManager.getEhcache(CacheAspect.GEO_CACHE_NAME);
        	cache.removeAll();
        	catalogoGeoUtils.ejecutarCargaDesdeWS();
        	this.setCantidadCatalogosGEOMap(catalogoGeoUtils.getCantidadCatalogosMap());
		} catch (Exception e) {
        	LOGGER.error("Falla en la recarga de catálogos GEO.",e);
		}
	}

	public void recargarCatalogoCentros(){
        try {
        	removeCache(CacheAspect.DEFAULT_CACHE_NAME, CENTROS_BACK_END);
			catalogoCentrosLoaderTask.loadTask();
			this.setCantidadCatalogoCentrosMap(catalogoCentrosLoaderTask.getCantidadCatalogosMap());
		} catch (Exception e) {
			LOGGER.error("Falla al cargar catálogo de centros.",e);
		}
	}
	
	public void recargarCatalogoPaises(){
        try {
        	removeCache(CacheAspect.DEFAULT_CACHE_NAME, PAISES_BACK_END);
			catalogoPaisesLoaderTask.loadTask();
			this.setCantidadCatalogoPaisesMap(catalogoPaisesLoaderTask.getCantidadCatalogosMap());
		} catch (Exception e) {
			LOGGER.error("Falla al cargar catálogo de paises.",e);
		}
	}
	
	public void recargarCatalogoMonedas(){
        try {
        	removeCache(CacheAspect.DEFAULT_CACHE_NAME, MONEDAS_BACK_END);
			catalogoMonedaLoaderTask.loadTask();
			this.setCantidadCatalogoMonedasMap(catalogoMonedaLoaderTask.getCantidadCatalogosMap());
		} catch (Exception e) {
			LOGGER.error("Falla al cargar catálogo de monedas.",e);
		}
	}
	
	public void recargarCatalogoPerfilesTCB(){
		try {
			removeCache(CacheAspect.DEFAULT_CACHE_NAME, PERFILES_BACK_END);
			catalogoPerfilesTCBLoaderTask.loadTask();
			this.setCantidadCatalogoPerfilesTCBMap(catalogoPerfilesTCBLoaderTask.getCantidadCatalogosMap());
		} catch (Exception e) {
			LOGGER.error("Falla al cargar catálogo de perfiles TCB.",e);
		}
	}
	
	public void recargarCatalogoListaEmpleados(){
        try {
        	removeCache(CacheAspect.DEFAULT_CACHE_NAME, EMPLEADOS_BACK_END);
			catalogoListaEmpleadosLoaderTask.loadTask();
			this.setCantidadCatalogoListaEmpleadosMap(catalogoListaEmpleadosLoaderTask.getCantidadCatalogosMap());
		} catch (Exception e) {
			LOGGER.error("Falla al cargar catálogo de lista de empleados.",e);
		}
	}
	
	
	public Map<String, Integer> getCantidadCatalogosGeneralesMap() {
		return cantidadCatalogosGeneralesMap;
	}

	public void setCantidadCatalogosGeneralesMap(
			Map<String, Integer> cantidadCatalogosGeneralesMap) {
		this.cantidadCatalogosGeneralesMap = cantidadCatalogosGeneralesMap;
		LOGGER.info("registros:"+this.cantidadCatalogosGeneralesMap.size());
	}

	public Map<String, Integer> getCantidadCatalogosGEOMap() {
		return cantidadCatalogosGEOMap;
	}

	public void setCantidadCatalogosGEOMap(
			Map<String, Integer> cantidadCatalogosGEOMap) {
		this.cantidadCatalogosGEOMap = cantidadCatalogosGEOMap;
	}

	public Map<String, Integer> getCantidadCatalogoCentrosMap() {
		return cantidadCatalogoCentrosMap;
	}

	public void setCantidadCatalogoCentrosMap(
			Map<String, Integer> cantidadCatalogoCentrosMap) {
		this.cantidadCatalogoCentrosMap = cantidadCatalogoCentrosMap;
	}

	public Map<String, Integer> getCantidadCatalogoTarifasMap() {
		return cantidadCatalogoTarifasMap;
	}

	public void setCantidadCatalogoTarifasMap(
			Map<String, Integer> cantidadCatalogoTarifasMap) {
		this.cantidadCatalogoTarifasMap = cantidadCatalogoTarifasMap;
	}

	public Map<String, Integer> getCantidadCatalogoPaisesMap() {
		return cantidadCatalogoPaisesMap;
	}

	public void setCantidadCatalogoPaisesMap(
			Map<String, Integer> cantidadCatalogoPaisesMap) {
		this.cantidadCatalogoPaisesMap = cantidadCatalogoPaisesMap;
	}

	public Map<String, Integer> getCantidadCatalogoMonedasMap() {
		return cantidadCatalogoMonedasMap;
	}

	public void setCantidadCatalogoMonedasMap(
			Map<String, Integer> cantidadCatalogoMonedasMap) {
		this.cantidadCatalogoMonedasMap = cantidadCatalogoMonedasMap;
	}

	public Map<String, Integer> getCantidadCatalogoPerfilesTCBMap() {
		return cantidadCatalogoPerfilesTCBMap;
	}

	public void setCantidadCatalogoPerfilesTCBMap(
			Map<String, Integer> cantidadCatalogoPerfilesTCBMap) {
		this.cantidadCatalogoPerfilesTCBMap = cantidadCatalogoPerfilesTCBMap;
	}

	public Map<String, Integer> getCantidadCatalogoListaEmpleadosMap() {
		return cantidadCatalogoListaEmpleadosMap;
	}

	public void setCantidadCatalogoListaEmpleadosMap(
			Map<String, Integer> cantidadCatalogoListaEmpleadosMap) {
		this.cantidadCatalogoListaEmpleadosMap = cantidadCatalogoListaEmpleadosMap;
	}

	private void removeCache(String cacheName, String backEnd){
		Ehcache cache = cacheManager.getEhcache(cacheName);
		List<?> keys = cache.getKeys();
		for (Object key : keys) {
			if(((String)key).contains(backEnd)){
				cache.remove(key);
			}
		}
	}

}
