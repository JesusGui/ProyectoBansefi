package mx.babel.bansefi.banksystem.cajas.controllers;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

import mx.babel.arq.catalogo.beans.CatalogoBean;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.sesion.utils.ContextoUtils;
import mx.babel.bansefi.banksystem.base.beans.parrilla.ColumnaParrillaEnum;
import mx.babel.bansefi.banksystem.base.beans.parrilla.ExistenciaDenominacionBean;
import mx.babel.bansefi.banksystem.base.beans.parrilla.ParrillaBean;
import mx.babel.bansefi.banksystem.base.enums.CatalogoEnum;
import mx.babel.bansefi.banksystem.base.enums.NavegacionEnum;
import mx.babel.bansefi.banksystem.base.enums.ParametrosFlashEnum;
import mx.babel.bansefi.banksystem.base.utils.CatalogoCentrosLoaderTask;
import mx.babel.bansefi.banksystem.base.utils.CatalogoUtils;
import mx.babel.bansefi.banksystem.base.utils.PdfUtils;
import mx.babel.bansefi.banksystem.cajas.backend.AutorizacionPeticionEfectivoBackend;
import mx.babel.bansefi.banksystem.cajas.beans.AutorizacionPeticionEfectivoBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@ManagedBean(name="resumenAutorizacionPeticionController")
@ViewScoped
@Component
@Scope("view")
public class ResumenAutorizacionPeticionController{

	@Autowired
	PdfUtils pdfUtils;
	@Autowired
	ContextoUtils contextoUtils;
	@Autowired
	AutorizacionPeticionEfectivoBackend autorizacionPeticionEfectivoBackend;
	@Autowired
	CatalogoCentrosLoaderTask catalogoCentrosLoaderTask;
	@Autowired
	CatalogoUtils catalogoUtils;
	
	private List<ExistenciaDenominacionBean> listaAutorizados;
	
	private ParrillaBean parrilla;
	
	private AutorizacionPeticionEfectivoBean autorizacionPeticionBean;
	
	private Boolean editable;
	private Boolean precinto = false;

	public ParrillaBean getParrilla() {
		return parrilla;
	}

	public void setParrilla(ParrillaBean parrilla) {
		this.parrilla = parrilla;
	}

	public AutorizacionPeticionEfectivoBean getAutorizacionPeticionBean() {
		return autorizacionPeticionBean;
	}

	public void setAutorizacionPeticionEfectivoBean(
			AutorizacionPeticionEfectivoBean autorizacionPeticionBean) {
		this.autorizacionPeticionBean = autorizacionPeticionBean;
	}

	public Boolean getEditable() {
		return editable;
	}

	public void setEditable(Boolean editable) {
		this.editable = editable;
	}

	public Boolean getPrecinto() {
		return precinto;
	}

	public void setPrecinto(Boolean precinto) {
		this.precinto = precinto;
	}
	
	/**
	 * Método que inicializa los valores de la parrilla y obtiene los datos del flash.
	 * */
	@PostConstruct
	public void init(){
		this.listaAutorizados = new ArrayList<ExistenciaDenominacionBean>();
		this.parrilla = new ParrillaBean();
		this.parrilla.iniciaParrilla(true);
		this.parrilla.getColumnas().add(ColumnaParrillaEnum.EXISTENCIAS);
		this.parrilla.getColumnas().add(ColumnaParrillaEnum.IMPORTE_PEDIDO);
		this.parrilla.getColumnas().add(ColumnaParrillaEnum.IMPORTE_AUTORIZADO);
		this.parrilla.getColumnas().add(ColumnaParrillaEnum.DIFERENCIA);
		
		if(obtieneFlash().get(ParametrosFlashEnum.AUTORIZACION_PETICION_BEAN.getParamFlash()) != null){
			setAutorizacionPeticionEfectivoBean((AutorizacionPeticionEfectivoBean) obtieneFlash().get(ParametrosFlashEnum.AUTORIZACION_PETICION_BEAN.getParamFlash()));
		}
		
		for(ExistenciaDenominacionBean existenciaDenominacionBean : this.autorizacionPeticionBean.getListaDenominaciones()){
			if(null != existenciaDenominacionBean.getImporteAutorizado()
					&& existenciaDenominacionBean.getImporteAutorizado().compareTo(BigDecimal.ZERO) != 0){
				this.listaAutorizados.add(existenciaDenominacionBean);
			}
		}
		
		this.parrilla.setListaDenominaciones(this.listaAutorizados);
	}
	
	/**
	 * Método que imprime el resumen.
	 */
	public void imprimirResumen(){
		Map<String, String> images = new HashMap<String, String>();
		images.put("Logo_bsfi_bn.png", "LOGO");
		 
		Map<String, Object> params = new HashMap<String, Object>();
		 
		if(this.autorizacionPeticionBean.getListaDenominaciones() != null){
			
			final CatalogoBean oficina = catalogoCentrosLoaderTask.getCatalogoBean(contextoUtils.getEntidad(), contextoUtils.getSucursal());
			final CatalogoBean plaza = catalogoUtils.getCatalogoBean(CatalogoEnum.TP_PLAZA_BANCARIA, contextoUtils.getPlazaBancaria());
			
			params.put("FECHA_REPORTE", new Date());
			params.put("OFICINA", oficina.getDescripcionL());
			params.put("PLAZA", plaza.getDescripcionL());
			params.put("USUARIO", contextoUtils.getNombre());
			params.put("TERMINAL", contextoUtils.getTerminal());
			params.put("TITULO", "POR VALOR DE " + formateaNumero());
		
			final List<ParrillaBean> listaBeans = new ArrayList<ParrillaBean>();
			listaBeans.add(parrilla);
			
			final Map<String, String> subReportes = new HashMap<String, String>();
			subReportes.put("denominacion_efectivo.jrxml", "SUBREPORT_PETICIONES");
			
			pdfUtils.generaPdf("ResumenPeticion.jrxml", params, images, subReportes, "ResumenPeticion", listaBeans);
		 }else{
			 throw new NoControlableException("No se han podido obtener datos de la petición.", "No se han podido obtener datos de la petición.");
		 }
	 }
	
	public String formateaNumero(){
		DecimalFormat formateador = new DecimalFormat("#,##0.00");
		return formateador.format(this.autorizacionPeticionBean.getTotalAutorizado()) + " MXN";
	}
	 
	 /**
     * Se encarga de obtener el flash.
     * 
     * @return Flash con los datos de la pagina
     */
	 public Flash obtieneFlash() {
	        return FacesContext.getCurrentInstance().getExternalContext().getFlash();
	 }
	 
	 /**
	  * Método para navegar a la autorización de peticiones.
	  * 
	  * @return ruta autorización de peticiones. 
	  */
	 public String irAutorizaciones(){
		 return NavegacionEnum.AUTORIZACION_PETICION.getRuta();
	 }
	 
	 /**
	  * Método para navegar al inicio.
	  * 
	  * @return ruta de inicio. 
	  */	 
	 public String irInicio(){
		 return NavegacionEnum.INICIO.getRuta();
	 }
}
