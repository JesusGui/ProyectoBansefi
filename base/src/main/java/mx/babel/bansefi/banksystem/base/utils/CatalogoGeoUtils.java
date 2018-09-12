package mx.babel.bansefi.banksystem.base.utils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mx.babel.arq.catalogo.beans.CatalogoBean;
import mx.babel.arq.catalogo.beans.CatalogoGeoBean;
import mx.babel.arq.task.ICatalogoGeoLoaderTask;
import mx.babel.bansefi.banksystem.base.backends.ConsultaCatalogoGeoBackend;
import mx.babel.bansefi.banksystem.base.beans.domain.DomicilioBean;
import mx.babel.bansefi.banksystem.base.beans.domain.DomicilioTipoBean;
import mx.babel.bansefi.banksystem.base.wrappers.CatalogoGeoBeanToDomicilioBeanWrapper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Utils que enmascara la llamada al webservice de catalogos para poder cachear
 * su resultado.
 * 
 * @author Babel
 * 
 */
@Component
public class CatalogoGeoUtils implements ICatalogoGeoLoaderTask, Serializable {

	private static final long serialVersionUID = 5406084417471208549L;
	public static final String CATALOGO_CP = "CODIGOS POSTALES";
	public static final String CATALOGO_MUNICIPIO = "MUNICIPIOS";
	public static final String ESTADOS = "ESTADOS";
	public static final String PAISES = "PAISES";
	
	private static final Logger LOGGER = LogManager.getLogger(CatalogoGeoUtils.class.getName());

	@Autowired
	ConsultaCatalogoGeoBackend consultaCatalogoBackend;
	@Autowired
	private CatalogoGeoBeanToDomicilioBeanWrapper catalogoGeoBeanWrapper;
	
	private Map<String,Integer> cantidadCatalogosMap = new HashMap<String, Integer>();;


	@Override
	public List<CatalogoBean> getCatalogo(final String catalogo) {

		List<CatalogoBean> resultado = null;

//		if (CATALOGO_CP.equals(catalogo)) {
//
//			resultado = consultaCatalogoBackend.getCodigosPostales();
//
//		} else if (CATALOGO_MUNICIPIO.equals(catalogo)) {
//
//			resultado = consultaCatalogoBackend.getCatalogoBeanMunicipios();
//
//		} else if (ESTADOS.equals(catalogo)) {
//
//			resultado = consultaCatalogoBackend.getCatalogoBeanEstados();
//
//		} else {
//
//			resultado = consultaCatalogoBackend.getCatalogoBeanPaises();
//
//		}

		return resultado;
	}
	
	/**
	 * Funcion que recibe un bean tipo DomicilioBean, obtiene su CatalogoGeoBean para
	 * inicializar los parametros fundamentales
	 * @param domicilio
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public DomicilioBean initDomicilioBean(DomicilioBean domicilio) {
		for (CatalogoGeoBean bean : getCatalogoGeoBean()) {
			if (bean.getCodigoPostal().equalsIgnoreCase(domicilio.getCodigoPostal())) {
//				domicilio = catalogoGeoBeanWrapper.wrappCatalogoGeoBeanToDomicilioBean(bean);
				domicilio.setCodigoPostalCatGeo(bean);
				domicilio.setMunicipioCatGeo(bean);
				return domicilio;
			}
		}
		return domicilio;
	}
	
	/**
	 * Funcion que recibe un bean tipo DomicilioTipoBean, obtiene su CatalogoGeoBean
	 * para inicializar los parametros fundamentales
	 * @param domicilio
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public DomicilioTipoBean initDomicilioTipoBean(DomicilioTipoBean domicilio) {
		for (CatalogoGeoBean bean : getCatalogoGeoBean()) {
			if (bean.getCodigoPostal().equalsIgnoreCase(domicilio.getCodigoPostal())) {
				//domicilio = catalogoGeoBeanWrapper.wrappCatalogoGeoBeanToDomicilioTipoBean(bean);
//				bean.setAuxiliar("codigoPostal");
				domicilio.setCodigoPostalCatGeo(bean);
				domicilio.setMunicipioCatGeo(bean);
				domicilio.setDatosFinalesCatGeo(bean);
				domicilio.setEstado(bean.getNombreComunidadAutonoma());
				domicilio.setPais(bean.getNombrePais());
				return domicilio;
			}
		}
		return domicilio;
	}
	
	/**
	 * Funcion que recibe un codigo postal y retorna un CatalogoGeoBean
	 * @param codigoPostal
	 * @return
	 */
	public CatalogoGeoBean getCatalogoGeoBean(String codigoPostal) {
		for (CatalogoGeoBean bean : getCatalogoGeoBean()) {
			if (bean.getCodigoPostal().equalsIgnoreCase(codigoPostal)) {
				return bean;
			}
		}
		return null;
	}

	@SuppressWarnings("unused")
	@Override
	public void loadTask() throws Exception{
		LOGGER.info("GEO::Lanza carga de catálogo de datos Geográficos.");
		
		try{
			LOGGER.debug("GEO::Procesamiento de archivo.");
			this.consultaCatalogoBackend.ejecutarCargaDeArchivo();
			LOGGER.debug("GEO:: catalogo procesado.");
			//únicamente se recibe el resultado para que suba a cache
			LOGGER.debug("GEO::Cacheando Catálogo Geográfico...");
			Object o = this.consultaCatalogoBackend.getCatalogoGeoBean();
			LOGGER.debug("GEO::Catálogo Geográfico cargado en caché.");
			this.cantidadCatalogosMap.put("CATALOGO_GEO", this.consultaCatalogoBackend.getCantidadCatalogosGeo());
			LOGGER.debug("GEO::cantidad de catálogos cargados para interfaz de recarga ha sido indicado.");
		}catch(Exception e){
			LOGGER.error("GEO::Error cargando Catálogo Geográfico.", e);
			//se relanza para provocar falla y provocar la recalendarización.
			throw new Exception("GEO::Error cargando Catálogo Geográfico.", e);			
		}
		LOGGER.info("GEO::Catálogo Geográfico cargado en caché");
	}
	
	@SuppressWarnings("unused")
	@Override
	public void ejecutarCargaDesdeWS() throws Exception{
		LOGGER.info("GEO::Lanza carga de catálogo de datos Geográficos.");
		
		try{
			LOGGER.debug("GEO::Procesamiento de trns y sus respuestas.");
			this.consultaCatalogoBackend.ejecutarTRN();
			LOGGER.debug("GEO:: catalogo procesado.");
			//únicamente se recibe el resultado para que suba a cache
			LOGGER.debug("GEO::Cacheando Catálogo Geográfico...");
			Object o = this.consultaCatalogoBackend.getCatalogoGeoBean();
			LOGGER.debug("GEO::Catálogo Geográfico cargado en caché.");
			this.cantidadCatalogosMap.put("CATALOGO_GEO", this.consultaCatalogoBackend.getCantidadCatalogosGeo());
			LOGGER.debug("GEO::cantidad de catálogos cargados para interfaz de recarga ha sido indicado.");
		}catch(Exception e){
			LOGGER.error("GEO::Error cargando Catálogo Geográfico.", e);
			//se relanza para provocar falla y provocar la recalendarización.
			throw new Exception("GEO::Error cargando Catálogo Geográfico.", e);			
		}
		LOGGER.info("GEO::Catálogo Geográfico cargado en caché");
	}
	
    public List<CatalogoGeoBean> getCatalogoGeoBean(){
    	return this.consultaCatalogoBackend.getCatalogoGeoBean();
    }
    
    public List<CatalogoBean> getCatalogoBeanPaises(){
    	return this.consultaCatalogoBackend.getCatalogoBeanPaises();
    }
    
	@Override
	public Map<String, Integer> getCantidadCatalogosMap() {
		return 	this.cantidadCatalogosMap;
	}

	public void setCantidadCatalogosMap(Map<String, Integer> cantidadCatalogosMap) {
		this.cantidadCatalogosMap = cantidadCatalogosMap;
	}	

}
