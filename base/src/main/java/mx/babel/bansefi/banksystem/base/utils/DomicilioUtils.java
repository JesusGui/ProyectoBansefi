package mx.babel.bansefi.banksystem.base.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import mx.babel.arq.catalogo.beans.CatalogoGeoBean;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Clase con utilidades para la consulta/modificacion de domicilios
 * @author luis.gcastellano
 *
 */
@Component
public class DomicilioUtils implements Serializable{

	private static final long serialVersionUID = -3533111676986725729L;
	
	private static final Logger LOGGER = LogManager.getLogger(DomicilioUtils.class.getName());
	
    @Autowired
	private CatalogoGeoUtils catalogoGeoUtils;
	
	@SuppressWarnings("unchecked")
	public List<CatalogoGeoBean> getCodigosPostales(String query, List<CatalogoGeoBean>... lstCatalogoGeoBeanArr){
		List<CatalogoGeoBean> lstCatalogoGeoBean = null;
		List<CatalogoGeoBean> lst = null;
		CatalogoGeoBean cp = null;
		
		if ( StringUtils.isBlank(query) ){
			return null;
		}
		
		if( lstCatalogoGeoBeanArr==null || lstCatalogoGeoBeanArr.length==0 ){
			lstCatalogoGeoBean = catalogoGeoUtils.getCatalogoGeoBean();
		}else{
			lstCatalogoGeoBean = lstCatalogoGeoBeanArr[0];
		}
		lst = new ArrayList<CatalogoGeoBean>();
		
		for (int i=0 ; i<lstCatalogoGeoBean.size() ; i++) {
			cp = lstCatalogoGeoBean.get(i);
			if( cp.getCodigoPostal().startsWith(query) && !lst.contains(cp) ){
				lst.add(cp);
				if(lst.size()>=5)break;
			}
		}
		LOGGER.debug("regresando "+((lst!=null&&lst.isEmpty())?lst.size():0)+" códigos postales.");
		
		return lst;
	}
	
	public List<CatalogoGeoBean> getMunicipios(String query){
		List<CatalogoGeoBean> lst = null;
		List<CatalogoGeoBean> lstCatalogoGeoBean = null;
		CatalogoGeoBean mun = null;
		boolean existe = true;
		
		if( StringUtils.isBlank(query) ){
			return null;
		}
		
		lstCatalogoGeoBean = catalogoGeoUtils.getCatalogoGeoBean();
		lst = new ArrayList<CatalogoGeoBean>();
		
		for(int i=0 ; i<lstCatalogoGeoBean.size() ; i++){
			mun = lstCatalogoGeoBean.get(i);
//			if(mun.getNombreMunicipio().trim().toUpperCase().contains(query.trim().toUpperCase()) && !lst.contains(mun)){
			if(mun.getMunicipioLocalidad().trim().toUpperCase().contains(query.trim().toUpperCase()) && !lst.contains(mun)){
				for(int x=0 ; x<lst.size() ; x++){
					existe = lst.get(x).getMunicipioLocalidad().trim().equalsIgnoreCase(mun.getMunicipioLocalidad().trim());
					if( existe )
						break;
				}
				
				if( !existe || lst.size()==0 ){
					lst.add(mun);
					existe = true;
					if(lst.size()>=5)break;
				}
			}
		}
		LOGGER.debug("regresando "+((lst!=null&&lst.isEmpty())?lst.size():0)+" municipios.");
		
		return lst;
	}
	
	public List<CatalogoGeoBean> getEstados(String query){
		List<CatalogoGeoBean> lst = null;
		List<CatalogoGeoBean> lstCatalogoGeoBean = null;
		
		if(lstCatalogoGeoBean==null || lstCatalogoGeoBean.isEmpty()){
			lstCatalogoGeoBean = catalogoGeoUtils.getCatalogoGeoBean();
		}
		
		lst = new ArrayList<CatalogoGeoBean>();
		CatalogoGeoBean est = null;
		boolean existe = false;
		for(int i=0 ; i<lstCatalogoGeoBean.size() ; i++){
			est = lstCatalogoGeoBean.get(i);
			existe = false;
			if( est.getNombreProvincia().contains(query.toUpperCase()) && !lst.contains(est)){
				for(int x=0 ; x<lst.size() ; x++){
					if( lst.get(x).getNombreProvincia().equalsIgnoreCase(est.getNombreProvincia())){
						existe = true;
						break;
					}
				}
				if( !existe ){
					lst.add(est);
					existe = false;
					if(lst.size()>=5)break;
				}
			}
		}
		
		return lst;
	}
	
	public List<CatalogoGeoBean> getCodigosPostalesPorNombreMunicipio(String nombreMunicipio){
		List<CatalogoGeoBean> lstCatalogoGeoBean = null;
		List<CatalogoGeoBean> lst = null;
		CatalogoGeoBean bean = null;
		
		lstCatalogoGeoBean = catalogoGeoUtils.getCatalogoGeoBean();
		lst = new ArrayList<CatalogoGeoBean>();
		for(int i=0 ; i<lstCatalogoGeoBean.size() ; i++){
			bean = lstCatalogoGeoBean.get(i);
			if(bean.getMunicipioLocalidad().trim().equalsIgnoreCase(nombreMunicipio.trim())){
				lst.add(bean);
			}
		}
		
		return lst;
	}
	
}
