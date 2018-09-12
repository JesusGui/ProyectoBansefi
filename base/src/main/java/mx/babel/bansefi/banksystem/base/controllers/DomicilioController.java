package mx.babel.bansefi.banksystem.base.controllers;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import mx.babel.arq.catalogo.beans.CatalogoGeoBean;
import mx.babel.bansefi.banksystem.base.beans.domain.DomicilioTipoBean;
import mx.babel.bansefi.banksystem.base.utils.DomicilioUtils;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@ManagedBean
@ViewScoped
@Component
@Scope("view")
public class DomicilioController implements Serializable {

	private static final long serialVersionUID = 5450352657266181332L;
	private static final Logger LOGGER = LogManager.getLogger(DomicilioController.class);
	
	@Autowired
	private DomicilioUtils domicilioUtils; 
	
	@Autowired
	protected DomicilioTipoBean domicilioBean;
	
	private List<DomicilioTipoBean> domiciliosList;
    
	private List<CatalogoGeoBean> lstCodigosPostalesRelacionadosAlMunicipio = null;
	
	@PostConstruct
	public void init() {
		LOGGER.debug("Entrando a DomicilioController, inicializa variables.");
	}
	
	@PreDestroy
	public void limpiaBean(){
		this.limpiarCamposDomicilio();
	}

    
	@SuppressWarnings("deprecation")
	public void cargaDatosDomicilio() {
		String mpio = null;
		String edo = null;
		String pais = null;
		CatalogoGeoBean bean = null;
		CatalogoGeoBean municipio = null;
		CatalogoGeoBean datosFinales = null;
		
		LOGGER.debug("código postal del bean:"+domicilioBean.getCodigoPostalCatGeo() );
		LOGGER.debug("municipio del bean:"+domicilioBean.getMunicipioCatGeo() );
		
		if( (domicilioBean.getCodigoPostalCatGeo()!=null && domicilioBean.getMunicipioCatGeo() == null) ||
				(domicilioBean.getCodigoPostalCatGeo()!=null && domicilioBean.getMunicipioCatGeo() != null ) ){
			bean = domicilioBean.getCodigoPostalCatGeo();
			
			municipio = (CatalogoGeoBean)bean.clone();
			domicilioBean.setMunicipioCatGeo(municipio);
		}else if( domicilioBean.getMunicipioCatGeo() != null && domicilioBean.getCodigoPostalCatGeo()==null ){
			bean = domicilioBean.getMunicipioCatGeo();
			if( CollectionUtils.isEmpty(lstCodigosPostalesRelacionadosAlMunicipio) ){
				List<CatalogoGeoBean> lstAux = null;
				lstAux = this.domicilioUtils.getCodigosPostalesPorNombreMunicipio(bean.getMunicipioLocalidad());
				lstAux = (!CollectionUtils.isEmpty(lstAux)&&lstAux.size()>5)?lstAux.subList(0, 5):lstAux;
						
				//se agrega al primer elemento el bean del municipio
				if( !lstAux.contains(bean) ){
					lstAux.set(0, bean);
				}
				RequestContext.getCurrentInstance().execute("PF('acCodigoPostal').search("+
								lstAux.get(0).getCodigoPostal().substring(0,1) +")");
				lstCodigosPostalesRelacionadosAlMunicipio = lstAux;
			}
		}
		
		datosFinales = (CatalogoGeoBean)bean.clone();
		domicilioBean.setDatosFinalesCatGeo(datosFinales);
		
		mpio = bean.getNombreMunicipio();
		edo = bean.getNombreComunidadAutonoma();
		pais = bean.getNombrePais();
		
		domicilioBean.setMunicipio( mpio );
		domicilioBean.setEstado(edo);
		domicilioBean.setPais(pais);
		domicilioBean.setCodArGeo(bean.getCodArGeo());
		if(bean.getNumArGeo()!=null){
			domicilioBean.setNumArGeo( Integer.valueOf(bean.getNumArGeo()) );
		}
	}
	
	@SuppressWarnings("deprecation")
	public void cargaDatosDomicilio(DomicilioTipoBean domTipoBean) {
		String mpio = null;
		String edo = null;
		String pais = null;
		CatalogoGeoBean bean = null;
		CatalogoGeoBean municipio = null;
		CatalogoGeoBean datosFinales = null;
		
		LOGGER.debug("código postal del bean:"+domicilioBean.getCodigoPostalCatGeo() );
		LOGGER.debug("municipio del bean:"+domicilioBean.getMunicipioCatGeo() );
		
		if( (domTipoBean.getCodigoPostalCatGeo()!=null && domTipoBean.getMunicipioCatGeo() == null) ||
				(domTipoBean.getCodigoPostalCatGeo()!=null && domTipoBean.getMunicipioCatGeo() != null ) ){
			bean = domTipoBean.getCodigoPostalCatGeo();
			
			municipio = (CatalogoGeoBean)bean.clone();
			domTipoBean.setMunicipioCatGeo(municipio);
		}else if( domTipoBean.getMunicipioCatGeo() != null && domTipoBean.getCodigoPostalCatGeo()==null ){
			bean = domTipoBean.getMunicipioCatGeo();
			if( CollectionUtils.isEmpty(lstCodigosPostalesRelacionadosAlMunicipio) ){
				lstCodigosPostalesRelacionadosAlMunicipio = this.domicilioUtils.getCodigosPostalesPorNombreMunicipio(bean.getMunicipioLocalidad());
				/*RequestContext.getCurrentInstance().execute("PF('acCodigoPostal').search("+
						lstCodigosPostalesRelacionadosAlMunicipio.get(0).getCodigoPostal().substring(0,1) +
						")");*/
			}
		}
		
		datosFinales = (CatalogoGeoBean)bean.clone();
		domTipoBean.setDatosFinalesCatGeo(datosFinales);
		
		mpio = bean.getNombreMunicipio();
		edo = bean.getNombreComunidadAutonoma();
		pais = bean.getNombrePais();
		
		domTipoBean.setMunicipio( mpio );
		domTipoBean.setEstado(edo);
		domTipoBean.setPais(pais);
		domTipoBean.setCodArGeo(bean.getCodArGeo());
		if(bean.getNumArGeo()!=null){
			domTipoBean.setNumArGeo( Integer.valueOf(bean.getNumArGeo()) );
		}
	}


	@SuppressWarnings("deprecation")
	public void limpiarCamposDomicilio(){
		LOGGER.debug("limpiando el componente de domicilio...");
		this.domicilioBean.setCodigoPostalCatGeo(null);
		this.domicilioBean.setMunicipioCatGeo(null);
		this.domicilioBean.setDatosFinalesCatGeo(null);
		this.domicilioBean.setMunicipio(StringUtils.EMPTY);
		this.domicilioBean.setEstado(StringUtils.EMPTY);
		this.domicilioBean.setPais(StringUtils.EMPTY);
		this.domicilioBean.setCodArGeo(StringUtils.EMPTY);
		this.domicilioBean.setNumArGeo(0);
		this.lstCodigosPostalesRelacionadosAlMunicipio = null;
	}
	
	@SuppressWarnings("deprecation")
	public void limpiarCamposDomicilio(DomicilioTipoBean domTipoBean){
		LOGGER.debug("limpiando el componente de domicilio...");
		domTipoBean.setCodigoPostalCatGeo(null);
		domTipoBean.setMunicipioCatGeo(null);
		domTipoBean.setDatosFinalesCatGeo(null);
		domTipoBean.setMunicipio(StringUtils.EMPTY);
		domTipoBean.setEstado(StringUtils.EMPTY);
		domTipoBean.setPais(StringUtils.EMPTY);
		domTipoBean.setCodArGeo(StringUtils.EMPTY);
		domTipoBean.setNumArGeo(0);
		this.lstCodigosPostalesRelacionadosAlMunicipio = null;
	}
	
	@SuppressWarnings("unchecked")
	public List<CatalogoGeoBean> getCatalogoCodigosPostales(String query){
		LOGGER.debug("consulta codigos postales que empiecen con:"+query);
		List<CatalogoGeoBean> lstCodigosPostalesDisponibles = null;
		
		if(this.lstCodigosPostalesRelacionadosAlMunicipio!=null){
			lstCodigosPostalesDisponibles = domicilioUtils.getCodigosPostales(query, this.lstCodigosPostalesRelacionadosAlMunicipio);
		}else{
			lstCodigosPostalesDisponibles = domicilioUtils.getCodigosPostales(query);
		}
		
		return lstCodigosPostalesDisponibles;
	}
	
	public List<CatalogoGeoBean> getCatalogoMunicipios(String query){
		LOGGER.debug("consulta municipios que contengan:"+query);
		List<CatalogoGeoBean> lstMunicipiosDisponibles = null;
		
		lstMunicipiosDisponibles = domicilioUtils.getMunicipios(query);
		
		return lstMunicipiosDisponibles;
	}

	
	public DomicilioTipoBean getDomicilioBean() {
		return domicilioBean;
	}


	public void setDomicilioBean(DomicilioTipoBean domicilioBean) {
		this.domicilioBean = domicilioBean;
	}


	public List<DomicilioTipoBean> getDomiciliosList() {
		return domiciliosList;
	}


	public void setDomiciliosList(List<DomicilioTipoBean> domiciliosList) {
		this.domiciliosList = domiciliosList;
	}
	
}
