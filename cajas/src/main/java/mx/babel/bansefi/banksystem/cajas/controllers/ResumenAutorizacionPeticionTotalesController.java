package mx.babel.bansefi.banksystem.cajas.controllers;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
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
import mx.babel.bansefi.banksystem.base.enums.CatalogoEnum;
import mx.babel.bansefi.banksystem.base.enums.NavegacionEnum;
import mx.babel.bansefi.banksystem.base.enums.ParametrosFlashEnum;
import mx.babel.bansefi.banksystem.base.utils.CatalogoCentrosLoaderTask;
import mx.babel.bansefi.banksystem.base.utils.CatalogoUtils;
import mx.babel.bansefi.banksystem.base.utils.PdfUtils;
import mx.babel.bansefi.banksystem.cajas.beans.AutorizacionPeticionTotalesBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@ManagedBean(name="resumenAutorizacionPeticionTotalesController")
@ViewScoped
@Scope("view")
public class ResumenAutorizacionPeticionTotalesController {
	
	@Autowired
	PdfUtils pdfUtils;
	@Autowired
	ContextoUtils contextoUtils;
	@Autowired
	CatalogoUtils catalogoUtils;
	@Autowired
	CatalogoCentrosLoaderTask catalogoCentrosLoaderTask;
	
	private AutorizacionPeticionTotalesBean autorizacionPeticionTotalesBean;
	
	private BigDecimal totalAutorizado;

	public AutorizacionPeticionTotalesBean getAutorizacionPeticionTotalesBean() {
		return autorizacionPeticionTotalesBean;
	}

	public void setAutorizacionPeticionTotalesBean(
			AutorizacionPeticionTotalesBean autorizacionPeticionTotalesBean) {
		this.autorizacionPeticionTotalesBean = autorizacionPeticionTotalesBean;
	}

	public BigDecimal getTotalAutorizado() {
		return totalAutorizado;
	}

	public void setTotalAutorizado(BigDecimal totalAutorizado) {
		this.totalAutorizado = totalAutorizado;
	}
	
	/**
	 * Método que obtiene los datos del flash. 
	 */
	@PostConstruct
	public void init(){
		if(null != this.obtieneFlash().get(ParametrosFlashEnum.AUTORIZACION_PETICION_TOTALES_BEAN.getParamFlash())
				&& null != this.obtieneFlash().get(ParametrosFlashEnum.TOTAL_AUTORIZADO.getParamFlash())){
			
			this.autorizacionPeticionTotalesBean = (AutorizacionPeticionTotalesBean) this.obtieneFlash().get(ParametrosFlashEnum.AUTORIZACION_PETICION_TOTALES_BEAN.getParamFlash());
			this.totalAutorizado = (BigDecimal) this.obtieneFlash().get(ParametrosFlashEnum.TOTAL_AUTORIZADO.getParamFlash());
			
		}
	}
	
	/**
	 * Método que imprime el resumen. Solamente esta para el boton de imprimir.
	 */
	public void imprimirResumen(){
		Map<String, String> images = new HashMap<String, String>();
		images.put("Logo_bsfi_bn.png", "LOGO");
		
		Map<String, Object> params = new HashMap<String, Object>();
		
		if(autorizacionPeticionTotalesBean != null){
			
			final CatalogoBean oficina = catalogoCentrosLoaderTask.getCatalogoBean(contextoUtils.getEntidad(), contextoUtils.getSucursal());
			final CatalogoBean plaza = catalogoUtils.getCatalogoBean(CatalogoEnum.TP_PLAZA_BANCARIA, contextoUtils.getPlazaBancaria());
			
			params.put("OFICINA", oficina.getDescripcionL());
			params.put("PLAZA", plaza.getDescripcionL());
			params.put("USUARIO", contextoUtils.getNombre());
			params.put("TERMINAL", contextoUtils.getTerminal());
			params.put("FECHA_REPORTE", contextoUtils.getFechaContableActual());
			params.put("TOTAL_AUTORIZADO", getTotalAutorizado());
			params.put("FECHA_PETICION", autorizacionPeticionTotalesBean.getFechaAbastecimiento());
			
			final Map<String, String> subReportes = new HashMap<String, String>();
			subReportes.put("listadoAutorizacionesTotales.jrxml", "SUBREPORT_PETICIONES_TOTALES");
			
			final List<AutorizacionPeticionTotalesBean> listaBeans = new ArrayList<AutorizacionPeticionTotalesBean>();
			listaBeans.add(autorizacionPeticionTotalesBean);
			
			pdfUtils.generaPdf("ResumenTotales.jrxml", params, images, subReportes, "ResumenTotales", listaBeans);
		}else{
			throw new NoControlableException("No se han podido obtener datos de la petición.", "No se han podido obtener datos de la petición");
		}
	}
	
	public String formateaNumero(){
		DecimalFormat formateador = new DecimalFormat("#,##0.00");
		return formateador.format(this.autorizacionPeticionTotalesBean.getTotalAutorizado()) + " MXN";
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
