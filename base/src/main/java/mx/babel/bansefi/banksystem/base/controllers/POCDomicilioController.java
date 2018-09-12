package mx.babel.bansefi.banksystem.base.controllers;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import mx.babel.arq.catalogo.beans.CatalogoGeoBean;
import mx.babel.bansefi.banksystem.base.beans.domain.DomicilioTipoBean;
import mx.babel.bansefi.banksystem.base.enums.NavegacionEnum;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@ManagedBean(name="pocDomicilioController")
@ViewScoped
@Component
@Scope("view")
public class POCDomicilioController implements Serializable {

	private static final long serialVersionUID = 2612159040560350463L;
	private static final Logger LOGGER = LogManager.getLogger(POCDomicilioController.class);
	
	private String rfcFisica;
	private String rfcMoral;
	private boolean calleReadOnly = false;
	private boolean tipoCalleDisabled = false;
	
	@ManagedProperty("#{domicilioController}")
	private DomicilioController domicilioController;
	
	@PostConstruct
	public void init() {
		//super.init();
		LOGGER.debug("Entrando al controller del POC-Domicilio, inicializa variables.");
		if( domicilioController.getDomicilioBean()!=null){
			domicilioController.setDomicilioBean(new DomicilioTipoBean());
		}
		
	}
	
	public String guardaAlgo(){
		LOGGER.debug("\n\n\nBean código postal:\n" + domicilioController.getDomicilioBean().getCodigoPostalCatGeo().toString() );
		LOGGER.debug("\n\n\nBean municipio:\n" + domicilioController.getDomicilioBean().getMunicipioCatGeo().toString() );
		LOGGER.debug("\n\n\ndatos finales:\n" + domicilioController.getDomicilioBean().getDatosFinalesCatGeo().toString() );
		return NavegacionEnum.INICIO.getRuta();
	}
	
	public String goToDashBoard(){
		return NavegacionEnum.INICIO.getRuta();
	}
	
	public void manejarComponenteDomicilio() {
		if( domicilioController.getDomicilioBean().getCodigoPostalCatGeo()==null && domicilioController.getDomicilioBean().getMunicipioCatGeo()==null ){
			domicilioController.limpiarCamposDomicilio();
		}else{
			domicilioController.cargaDatosDomicilio();
		}
	}
	
	public List<CatalogoGeoBean> getCodigosPostales(String query){
		if ( StringUtils.isBlank(query) ){
			domicilioController.getDomicilioBean().setCodigoPostalCatGeo(null);
			if(domicilioController.getDomicilioBean().getMunicipioCatGeo()==null){
				domicilioController.limpiarCamposDomicilio();
			}
			return null;
		}
		
		return domicilioController.getCatalogoCodigosPostales(query);
	}
	
	public  List<CatalogoGeoBean> getMunicipios(String query){
		if ( StringUtils.isBlank(query) ){
			domicilioController.getDomicilioBean().setMunicipioCatGeo(null);
			if(domicilioController.getDomicilioBean().getCodigoPostalCatGeo()==null){
				domicilioController.limpiarCamposDomicilio();
			}
			
			return null;
		}
		
		return domicilioController.getCatalogoMunicipios(query);
	}
	
	
	
	public boolean isTipoCalleDisabled() {
		return tipoCalleDisabled;
	}

	public void setTipoCalleDisabled(boolean tipoCalleDisabled) {
		this.tipoCalleDisabled = tipoCalleDisabled;
	}

	public boolean isCalleReadOnly() {
		return calleReadOnly;
	}

	public void setCalleReadOnly(boolean calleReadOnly) {
		this.calleReadOnly = calleReadOnly;
	}

	public String getRfcFisica() {
		return rfcFisica;
	}
	
	public void setRfcFisica(String rfcFisica) {
		this.rfcFisica = rfcFisica;
	}
	
	public String getRfcMoral() {
		return rfcMoral;
	}

	public void setRfcMoral(String rfcMoral) {
		this.rfcMoral = rfcMoral;
	}

	public DomicilioController getDomicilioController() {
		return domicilioController;
	}

	public void setDomicilioController(DomicilioController domicilioController) {
		this.domicilioController = domicilioController;
	}

}
