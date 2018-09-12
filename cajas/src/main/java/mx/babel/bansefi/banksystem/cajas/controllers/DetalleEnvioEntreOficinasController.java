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
import mx.babel.arq.comun.exceptions.ControlableException;
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
import mx.babel.bansefi.banksystem.cajas.backend.ConsultaEnvioEntreOficinasBackEnd;
import mx.babel.bansefi.banksystem.cajas.beans.EnvioEntreOficinasBean;
import mx.babel.bansefi.banksystem.cajas.enums.EstadoPeticionEnum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@ManagedBean(name = "detalleEnvioEntreOficinasController")
@ViewScoped
@Scope("view")
public class DetalleEnvioEntreOficinasController {
	
	@Autowired
	PdfUtils pdfUtils;
	@Autowired
	ContextoUtils contextoUtils;
	@Autowired
	CatalogoCentrosLoaderTask catalogoCentrosLoaderTask;
	@Autowired
	CatalogoUtils catalogoUtils;
	@Autowired
	ConsultaEnvioEntreOficinasBackEnd consultaEnvioEntreOficinasBackEnd;
	
	private EnvioEntreOficinasBean envioEntreOficinasBean;
	private EnvioEntreOficinasBean detalleEnvioEntreOficinasBean;
	
	private ParrillaBean parrilla;

	public EnvioEntreOficinasBean getEnvioEntreOficinasBean() {
		return envioEntreOficinasBean;
	}

	public void setEnvioEntreOficinasBean(
			EnvioEntreOficinasBean envioEntreOficinasBean) {
		this.envioEntreOficinasBean = envioEntreOficinasBean;
	}
	
	public EnvioEntreOficinasBean getDetalleEnvioEntreOficinasBean() {
		return detalleEnvioEntreOficinasBean;
	}

	public void setDetalleEnvioEntreOficinasBean(
			EnvioEntreOficinasBean detalleEnvioEntreOficinasBean) {
		this.detalleEnvioEntreOficinasBean = detalleEnvioEntreOficinasBean;
	}

	public ParrillaBean getParrilla() {
		return parrilla;
	}

	public void setParrilla(ParrillaBean parrilla) {
		this.parrilla = parrilla;
	}

	/**
     * Se encarga de obtener el flash.
     * 
     * @return Flash con los datos de la pagina
     */
	public Flash obtieneFlash() {
		 return FacesContext.getCurrentInstance().getExternalContext().getFlash();
	}
	
	@PostConstruct
	public void init(){
		if(this.obtieneFlash().get(ParametrosFlashEnum.ENVIO_ENTRE_OFICINAS_BEAN.getParamFlash()) != null){
			this.envioEntreOficinasBean = (EnvioEntreOficinasBean) this.obtieneFlash().get(ParametrosFlashEnum.ENVIO_ENTRE_OFICINAS_BEAN.getParamFlash());
			this.detalleEnvioEntreOficinasBean = (EnvioEntreOficinasBean) this.obtieneFlash().get(ParametrosFlashEnum.ENVIO_ENTRE_OFICINAS_BEAN.getParamFlash());
			initData();
		}
	}
	
	private void initData(){
		this.detalleEnvioEntreOficinasBean.setPrecinto(true);
		this.detalleEnvioEntreOficinasBean.setEditable(false);
		this.parrilla = new ParrillaBean();
		this.parrilla.getColumnas().add(ColumnaParrillaEnum.UNIDADES);
		this.parrilla.getColumnas().add(ColumnaParrillaEnum.IMPORTE_A_ENVIAR);
		
		try{
			EnvioEntreOficinasBean envioEntreOficinasBean = consultaEnvioEntreOficinasBackEnd.ejecutarTRN1(detalleEnvioEntreOficinasBean);
			
			this.detalleEnvioEntreOficinasBean.setEstatusC(envioEntreOficinasBean.getEstatusC());
			this.detalleEnvioEntreOficinasBean.setEstatusL(envioEntreOficinasBean.getEstatusL());
		}catch (ControlableException ce){
			if(ce.getRtncod() == 7){
				this.detalleEnvioEntreOficinasBean.setEstatusC(null);
				this.detalleEnvioEntreOficinasBean.setEstatusL(null);
				this.envioEntreOficinasBean.setEstadoPeticionEnum(EstadoPeticionEnum.ELIMINADO);
			}
		}
		
		for(ExistenciaDenominacionBean existenciaDenominacionBean : this.detalleEnvioEntreOficinasBean.getParrilla().getListaDenominaciones()){
			if(existenciaDenominacionBean.getImporteAEnviar() != null
					&& existenciaDenominacionBean.getImporteAEnviar().compareTo(BigDecimal.ZERO) > 0){
				existenciaDenominacionBean.setImporteModificable(existenciaDenominacionBean.getImporteAEnviar());
				this.parrilla.getListaDenominaciones().add(existenciaDenominacionBean);
			}
		}
	}
	
	public String formateaNumero(){
		DecimalFormat formateador = new DecimalFormat("#,##0.00");
		return formateador.format(this.detalleEnvioEntreOficinasBean.getTotalAEnviar()) + " MXN";
	}
	
	public void imprimirDetalle(){
		Map<String, String> images = new HashMap<String, String>();
		images.put("Logo_bsfi_bn.png", "LOGO");
		 
		Map<String, Object> params = new HashMap<String, Object>();
		
		final CatalogoBean oficina = catalogoCentrosLoaderTask.getCatalogoBean(contextoUtils.getEntidad(), contextoUtils.getSucursal());
		final CatalogoBean plaza = catalogoUtils.getCatalogoBean(CatalogoEnum.TP_PLAZA_BANCARIA, contextoUtils.getPlazaBancaria());
		final CatalogoBean centroOrigen = catalogoCentrosLoaderTask.getCatalogoBean(contextoUtils.getEntidad(), detalleEnvioEntreOficinasBean.getCentroOrigen());
		final CatalogoBean centroDestino = catalogoCentrosLoaderTask.getCatalogoBean(contextoUtils.getEntidad(), detalleEnvioEntreOficinasBean.getCentroDestino());
		
		params.put("FECHA_REPORTE", new Date());
		params.put("OFICINA", oficina.getDescripcionL());
		params.put("PLAZA", plaza.getDescripcionL());
		params.put("USUARIO", contextoUtils.getNombre());
		params.put("TERMINAL", contextoUtils.getTerminal());
		params.put("TOTAL_A_ENVIAR", detalleEnvioEntreOficinasBean.getTotalAEnviar());
		params.put("CENTRO_ORIGEN", centroOrigen.getDescripcionL());
		params.put("CENTRO_DESTINO", centroDestino.getDescripcionL());
		params.put("FECHA_ENVIO", detalleEnvioEntreOficinasBean.getFechaContableEnvio());
		params.put("ESTATUS", detalleEnvioEntreOficinasBean.getEstatusL());
		params.put("TITULO", detalleEnvioEntreOficinasBean.getTituloResumen() + " " + formateaNumero());
		
		final List<ParrillaBean> listaBeans = new ArrayList<ParrillaBean>();
		listaBeans.add(this.detalleEnvioEntreOficinasBean.getParrilla());
		
		final Map<String, String> subReportes = new HashMap<String, String>();
		subReportes.put("Denominacion.jrxml", "SUBREPORT_DENOMINACION");
			
		pdfUtils.generaPdf("DetalleEnvioEntreOficinas.jrxml", params, images, subReportes, "DetalleEnvioEntreOficinas", listaBeans);
	}
	
	public String irEnvioEntreOficinas(){
		this.obtieneFlash().put(ParametrosFlashEnum.ENVIO_ENTRE_OFICINAS_BEAN.getParamFlash(), this.envioEntreOficinasBean);
		return NavegacionEnum.ENVIO_ENTRE_OFICINAS.getRuta();
	}
	
	public String irInicio(){
		return NavegacionEnum.INICIO.getRuta();
	}
}
