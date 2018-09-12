package mx.babel.bansefi.banksystem.cajas.controllers;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
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
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.sesion.utils.ContextoUtils;
import mx.babel.bansefi.banksystem.base.beans.parrilla.ColumnaParrillaEnum;
import mx.babel.bansefi.banksystem.base.beans.parrilla.ExistenciaDenominacionBean;
import mx.babel.bansefi.banksystem.base.beans.parrilla.ParrillaBean;
import mx.babel.bansefi.banksystem.base.enums.CatalogoEnum;
import mx.babel.bansefi.banksystem.base.enums.NavegacionEnum;
import mx.babel.bansefi.banksystem.base.enums.ParametrosFlashEnum;
import mx.babel.bansefi.banksystem.cajas.utils.FechaUtils;
import mx.babel.bansefi.banksystem.base.utils.CatalogoCentrosLoaderTask;
import mx.babel.bansefi.banksystem.base.utils.CatalogoUtils;
import mx.babel.bansefi.banksystem.base.utils.PdfUtils;
import mx.babel.bansefi.banksystem.cajas.backend.ConsultaPeticionEfectivoBackEnd;
import mx.babel.bansefi.banksystem.cajas.beans.RecogidaEfectivoBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@ManagedBean(name = "detalleRecogidaEfectivoController")
@Component
@ViewScoped
@Scope("view")
public class DetalleRecogidaEfectivoController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8028056935663975950L;

	@Autowired
	ContextoUtils contextoUtils;
	@Autowired
	PdfUtils pdfUtils;
	@Autowired
	ConsultaPeticionEfectivoBackEnd consultaPeticionEfectivoBackEnd;
	@Autowired
	CatalogoUtils catalogoUtils;
	@Autowired
	CatalogoCentrosLoaderTask catalogoCentrosLoaderTask;

	private RecogidaEfectivoBean recogidaEfectivoBean;

	private ParrillaBean parrilla;

	private Boolean editable;
	private Boolean precinto;
	private Boolean filtro;
	private Boolean mostrarUrgente;
	private boolean mostrarEstatus;

	public RecogidaEfectivoBean getRecogidaEfectivoBean() {
		return recogidaEfectivoBean;
	}

	public void setRecogidaEfectivoBean(
			RecogidaEfectivoBean recogidaEfectivoBean) {
		this.recogidaEfectivoBean = recogidaEfectivoBean;
	}

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

	public Boolean getFiltro() {
		return filtro;
	}

	public void setFiltro(Boolean filtro) {
		this.filtro = filtro;
	}

	public Boolean getMostrarUrgente() {
		return mostrarUrgente;
	}

	public void setMostrarUrgente(Boolean mostrarUrgente) {
		this.mostrarUrgente = mostrarUrgente;
	}

	public boolean isMostrarEstatus() {
		return mostrarEstatus;
	}

	public void setMostrarEstatus(boolean mostrarEstatus) {
		this.mostrarEstatus = mostrarEstatus;
	}

	public Flash obtieneFlash() {
		return FacesContext.getCurrentInstance().getExternalContext()
				.getFlash();
	}

	public String irInicio() {
		return NavegacionEnum.INICIO.getRuta();
	}

	public String irRecogidaEfectivo() {
		this.obtieneFlash().put(ParametrosFlashEnum.RECOGIDA_EFECTIVO_BEAN.getParamFlash(), this.recogidaEfectivoBean);
		return NavegacionEnum.RECOGIDA_EFECTIVO.getRuta();
	}

	@PostConstruct
	public void init() {
		if (this.obtieneFlash().get(
				ParametrosFlashEnum.RECOGIDA_EFECTIVO_BEAN.getParamFlash()) != null) {
			this.recogidaEfectivoBean = (RecogidaEfectivoBean) this
					.obtieneFlash().get(
							ParametrosFlashEnum.RECOGIDA_EFECTIVO_BEAN
									.getParamFlash());

			this.parrilla = new ParrillaBean();
			this.parrilla.getColumnas().add(ColumnaParrillaEnum.UNIDADES);
			this.parrilla.getColumnas().add(ColumnaParrillaEnum.IMPORTE_A_ENVIAR);
			for (ExistenciaDenominacionBean existenciaDenominacionBean : this.recogidaEfectivoBean.getListaDenominaciones()) {
				if (existenciaDenominacionBean.getImporteAEnviar().compareTo(BigDecimal.ZERO) > 0) {
					existenciaDenominacionBean.setImporteModificable(existenciaDenominacionBean.getImporteAEnviar());
					this.parrilla.getListaDenominaciones().add(existenciaDenominacionBean);
				}
			}
			
			setFiltro(false);
			setEditable(false);
			setPrecinto(false);

			try {
				FechaUtils fechaUtils = new FechaUtils();
				if (this.recogidaEfectivoBean.getTipoPeticion() == 1) {
					setMostrarUrgente(false);
					
					consultaPeticionEfectivoBackEnd.ejecutarTRN(fechaUtils.formateoFecha(this.recogidaEfectivoBean.getFechaRecogida()), this.recogidaEfectivoBean.getIndicadorUrgencia());
					
					RecogidaEfectivoBean recodiga = consultaPeticionEfectivoBackEnd.getRecogidaEfectivoBean();
					
					final CatalogoBean catalogo = catalogoUtils.getCatalogoBean(CatalogoEnum.ESTATUS_PETICION, recodiga.getEstatusC());
					
					this.recogidaEfectivoBean.setEstatusC(catalogo.getClaveFila());
					this.recogidaEfectivoBean.setEstatusL(catalogo.getDescripcionL());
					
					setMostrarEstatus(true);
				} else {
					setMostrarUrgente(true);
					if(this.recogidaEfectivoBean.getIndicadorUrgencia() > 0){
						consultaPeticionEfectivoBackEnd.ejecutarTRN(fechaUtils.formateoFecha(this.recogidaEfectivoBean.getFechaRecogida()), this.recogidaEfectivoBean.getIndicadorUrgencia());
					}else{
						consultaPeticionEfectivoBackEnd.ejecutarTRN(fechaUtils.formateoFecha(this.recogidaEfectivoBean.getFechaRecogida()), this.recogidaEfectivoBean.getComboUrgentesBean().getListaIndicadoresUrgentes().size() + 1);
						this.recogidaEfectivoBean.setIndicadorUrgencia(this.recogidaEfectivoBean.getComboUrgentesBean().getListaIndicadoresUrgentes().size() + 1);
					}
					RecogidaEfectivoBean recodiga = consultaPeticionEfectivoBackEnd.getRecogidaEfectivoBean();
					
					final CatalogoBean catalogo = catalogoUtils.getCatalogoBean(CatalogoEnum.ESTATUS_PETICION, recodiga.getEstatusC());
					
					this.recogidaEfectivoBean.setEstatusC(catalogo.getClaveFila());
					this.recogidaEfectivoBean.setEstatusL(catalogo.getDescripcionL());
					
					setMostrarEstatus(true);
				}
			} catch (ParseException e) {
				
			}catch (ControlableException c) {
				if(c.getRtncod() == 7
						&& recogidaEfectivoBean.getTituloResumen().contains("ELIMINAC")){
					setMostrarEstatus(false);
				}
			}
		}
	}

	public String formateaNumero() {
		DecimalFormat formateador = new DecimalFormat("#,##0.00");
		return formateador.format(this.recogidaEfectivoBean.getTotalRecogida())
				+ " MXN";
	}

	public void imprimirDetalle() {
		Map<String, String> images = new HashMap<String, String>();
		images.put("Logo_bsfi_bn.png", "LOGO");

		Map<String, Object> params = new HashMap<String, Object>();

		if (this.recogidaEfectivoBean.getListaDenominaciones() != null) {
			params.put("FECHA_REPORTE", new Date());
			
			final CatalogoBean oficina = catalogoCentrosLoaderTask.getCatalogoBean(contextoUtils.getEntidad(), contextoUtils.getSucursal());
			final CatalogoBean plaza = catalogoUtils.getCatalogoBean(CatalogoEnum.TP_PLAZA_BANCARIA, contextoUtils.getPlazaBancaria());
			
			params.put("OFICINA", oficina.getDescripcionL());
			params.put("PLAZA", plaza.getDescripcionL());
			params.put("USUARIO", contextoUtils.getNombre());
			params.put("TERMINAL", contextoUtils.getTerminal());
			params.put("CENTRO", this.recogidaEfectivoBean.getCentro());
			params.put("TOTAL_A_ENVIAR", this.recogidaEfectivoBean.getTotalRecogida());
			params.put("URGENTE", this.recogidaEfectivoBean.getIndicadorUrgencia());
			params.put("TITULO", this.recogidaEfectivoBean.getTituloResumen());
			params.put("ESTATUS", this.recogidaEfectivoBean.getEstatusL());
			params.put("OBSERVACIONES", this.recogidaEfectivoBean.getObservacion());

			final List<ParrillaBean> listaBeans = new ArrayList<ParrillaBean>();
			listaBeans.add(this.parrilla);

			final Map<String, String> subReportes = new HashMap<String, String>();
			subReportes.put("Denominacion.jrxml", "SUBREPORT_DENOMINACION");

			pdfUtils.generaPdf("ResumenRecogida.jrxml", params, images,
					subReportes, "ResumenPeticion", listaBeans);
		} else {
			throw new NoControlableException(
					"No se han podido obtener datos de la petición.",
					"No se han podido obtener datos de la petición.");
		}
	}
}
