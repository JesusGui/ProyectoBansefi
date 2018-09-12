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
import mx.babel.bansefi.banksystem.cajas.beans.AutorizacionPeticionUrgenteBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@ManagedBean(name="resumenAutorizacionPeticionUrgenteController")
@ViewScoped
@Scope("view")
public class ResumenAutorizacionPeticionUrgenteController{
	
	@Autowired
	ContextoUtils contextoUtils;	
	@Autowired
	PdfUtils pdfUtils;
	@Autowired
	CatalogoCentrosLoaderTask catalogoCentrosLoaderTask;
	@Autowired
	CatalogoUtils catalogoUtils;
	
	private ParrillaBean parrilla;
	
	private List<ExistenciaDenominacionBean> listaAutorizados;
	
	private Boolean editable;
	private Boolean precinto = false;
	
	private AutorizacionPeticionUrgenteBean autorizacionPeticionUrgenteBean;

	public ParrillaBean getParrilla() {
		return parrilla;
	}

	public void setParrilla(ParrillaBean parrilla) {
		this.parrilla = parrilla;
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
	
	public List<ExistenciaDenominacionBean> getListaAutorizados() {
		return listaAutorizados;
	}

	public void setListaAutorizados(
			List<ExistenciaDenominacionBean> listaAutorizados) {
		this.listaAutorizados = listaAutorizados;
	}

	public AutorizacionPeticionUrgenteBean getAutorizacionPeticionUrgenteBean() {
		return autorizacionPeticionUrgenteBean;
	}

	public void setAutorizacionPeticionUrgenteBean(
			AutorizacionPeticionUrgenteBean autorizacionPeticionUrgenteBean) {
		this.autorizacionPeticionUrgenteBean = autorizacionPeticionUrgenteBean;
	}
	
	/**
	 * Método que inicializa los valores de la parrilla y obtiene los datos del flash. 
	 */
	@PostConstruct
	public void init(){
		this.listaAutorizados = new ArrayList<ExistenciaDenominacionBean>();
		this.parrilla = new ParrillaBean();
		this.parrilla.iniciaParrilla(true);
		this.parrilla.getColumnas().add(ColumnaParrillaEnum.UNIDADES);
		this.parrilla.getColumnas().add(ColumnaParrillaEnum.IMPORTE_PEDIDO);
		this.parrilla.getColumnas().add(ColumnaParrillaEnum.IMPORTE_AUTORIZADO);
		
		if(this.obtieneFlash().get(ParametrosFlashEnum.AUTORIZACION_PETICION_URGENTE_BEAN.getParamFlash()) != null){
			
			this.autorizacionPeticionUrgenteBean = (AutorizacionPeticionUrgenteBean) obtieneFlash().get(ParametrosFlashEnum.AUTORIZACION_PETICION_URGENTE_BEAN.getParamFlash());
			
			this.listaAutorizados = this.autorizacionPeticionUrgenteBean.getListaDenominaciones();
			this.parrilla.setListaDenominaciones(this.listaAutorizados);
			
			for(ExistenciaDenominacionBean existenciaDenominacionBean : this.parrilla.getListaDenominaciones()){
				if(existenciaDenominacionBean.getImporteAutorizado() != null
						&& existenciaDenominacionBean.getImporteAutorizado().compareTo(BigDecimal.ZERO) > 0){
					existenciaDenominacionBean.setImporteModificable(existenciaDenominacionBean.getImporteAutorizado());
				}
			}
			this.autorizacionPeticionUrgenteBean.setEstatusPeticionC("F");
			this.autorizacionPeticionUrgenteBean.setEstatusPeticionL("CONFIRMADO");
		}
	}
	
	/**
	 * Método que imprime el resumen. Solamente esta para el boton de imprimir.
	 */
	public void imprimirResumen(){
		Map<String, String> images = new HashMap<String, String>();
		images.put("Logo_bsfi_bn.png", "LOGO");
		
		Map<String, Object> params = new HashMap<String, Object>();
		
		if(autorizacionPeticionUrgenteBean != null){
			
			final CatalogoBean oficina = catalogoCentrosLoaderTask.getCatalogoBean(contextoUtils.getEntidad(), contextoUtils.getSucursal());
			final CatalogoBean plaza = catalogoUtils.getCatalogoBean(CatalogoEnum.TP_PLAZA_BANCARIA, contextoUtils.getPlazaBancaria());
			final CatalogoBean centroAutorizacion = catalogoCentrosLoaderTask.getCatalogoBean(contextoUtils.getEntidad(), autorizacionPeticionUrgenteBean.getCentro());
			
			params.put("TERMINAL", contextoUtils.getTerminal());
			params.put("FECHA_REPORTE", new Date());
			params.put("USUARIO", contextoUtils.getNombre());
			params.put("OFICINA", oficina.getDescripcionL());
			params.put("PLAZA", plaza.getDescripcionL());
			params.put("CENTRO", centroAutorizacion.getDescripcionL());
			params.put("TOTAL_AUTORIZADO", this.autorizacionPeticionUrgenteBean.getTotalAutorizado());
			params.put("ESTATUS", this.autorizacionPeticionUrgenteBean.getEstatusPeticionL());
			params.put("TITULO", "RESUMEN DE AUTORIZACIÓN DE PETICIÓN DE EFECTIVO POR VALOR DE " + formateaNumero());
			params.put("TITULO2", "DEL CENTRO " + this.autorizacionPeticionUrgenteBean.getCentro());
			params.put("URGENTE", this.autorizacionPeticionUrgenteBean.getNoUrgente());
			
			final List<ParrillaBean> listaBeans = new ArrayList<ParrillaBean>();
			listaBeans.add(parrilla);
			
			final Map<String, String> subReportes = new HashMap<String, String>();
			subReportes.put("Denominacion.jrxml", "SUBREPORT_DENOMINACION");
			
			pdfUtils.generaPdf("ResumenUrgente.jrxml", params, images, subReportes,
					"ResumenPeticionUrgente", listaBeans);
		}else{
			throw new NoControlableException("No se han podido obtener datos de la petición.", "No se han podido obtener datos de la petición");
		}
	}
	
	public String formateaNumero(){
		DecimalFormat formateador = new DecimalFormat("#,##0.00");
		return formateador.format(this.autorizacionPeticionUrgenteBean.getTotalAutorizado()) + " MXN";
	}
	
	/**
     * Se encarga de obtener el flash.
     * 
     * @return Flash con los datos de la página
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
