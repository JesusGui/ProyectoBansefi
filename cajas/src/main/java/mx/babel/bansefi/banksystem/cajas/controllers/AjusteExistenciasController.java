package mx.babel.bansefi.banksystem.cajas.controllers;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.ParseException;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.sesion.utils.ContextoUtils;
import mx.babel.bansefi.banksystem.base.beans.parrilla.ColumnaParrillaEnum;
import mx.babel.bansefi.banksystem.base.beans.parrilla.ExistenciaDenominacionBean;
import mx.babel.bansefi.banksystem.base.beans.parrilla.ParrillaBean;
import mx.babel.bansefi.banksystem.base.enums.NavegacionEnum;
import mx.babel.bansefi.banksystem.base.enums.ParametrosFlashEnum;
import mx.babel.bansefi.banksystem.cajas.backend.AjustaExistenciasBackend;
import mx.babel.bansefi.banksystem.cajas.backend.ConsultaExistenciasActualesBackend;
import mx.babel.bansefi.banksystem.cajas.beans.AjusteExistenciasActualesBean;
import mx.babel.bansefi.banksystem.cajas.utils.FechaUtils;

import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@ManagedBean(name="ajusteExistenciasController")
@Component
@Scope("view")
public class AjusteExistenciasController implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2771886429955166398L;
	
	@Autowired
	ContextoUtils contextoUtils;
	@Autowired
	ConsultaExistenciasActualesBackend consultaExistenciasActualesBackend;
	@Autowired
	AjustaExistenciasBackend ajustaExistenciasBackend;
	
	private Integer fechaCierreInteger;
	
	private AjusteExistenciasActualesBean ajusteExistenciasActualesBean;
	
	private ParrillaBean parrilla;
	
	private boolean editable;
	private boolean precinto = false;
	private boolean importeFijoEdicion = true;
	private boolean filtro;
	private boolean botonesBloqueados;
	
	private String mensajeError;
	private String mensajeAdvertencia;
	
	public Integer getFechaCierreInteger() {
		return fechaCierreInteger;
	}

	public void setFechaCierreInteger(Integer fechaCierreInteger) {
		this.fechaCierreInteger = fechaCierreInteger;
	}

	public AjusteExistenciasActualesBean getAjusteExistenciasActualesBean() {
		return ajusteExistenciasActualesBean;
	}

	public void setAjusteExistenciasActualesBean(
			AjusteExistenciasActualesBean ajusteExistenciasActualesBean) {
		this.ajusteExistenciasActualesBean = ajusteExistenciasActualesBean;
	}

	public ParrillaBean getParrilla() {
		return parrilla;
	}

	public void setParrilla(ParrillaBean parrilla) {
		this.parrilla = parrilla;
	}

	public boolean getEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	public boolean getPrecinto() {
		return precinto;
	}

	public void setPrecinto(boolean precinto) {
		this.precinto = precinto;
	}

	public boolean getImporteFijoEdicion() {
		return importeFijoEdicion;
	}

	public void setImporteFijoEdicion(boolean importeFijoEdicion) {
		this.importeFijoEdicion = importeFijoEdicion;
	}

	public boolean getFiltro() {
		return filtro;
	}

	public void setFiltro(boolean filtro) {
		this.filtro = filtro;
	}

	public boolean getBotonesBloqueados() {
		return botonesBloqueados;
	}

	public void setBotonesBloqueados(boolean botonesBloqueados) {
		this.botonesBloqueados = botonesBloqueados;
	}

	public String getMensajeError() {
		return mensajeError;
	}

	public void setMensajeError(String mensajeError) {
		this.mensajeError = mensajeError;
	}

	public String getMensajeAdvertencia() {
		return mensajeAdvertencia;
	}

	public void setMensajeAdvertencia(String mensajeAdvertencia) {
		this.mensajeAdvertencia = mensajeAdvertencia;
	}

	@PostConstruct
	public void init() {
		FechaUtils fechaUtils = new FechaUtils();
		this.parrilla = new ParrillaBean();
		this.ajusteExistenciasActualesBean = new AjusteExistenciasActualesBean();
		this.ajusteExistenciasActualesBean.setFechaCierre(contextoUtils.getFechaContableActual());
		this.ajusteExistenciasActualesBean.setTotalEfectivo(new BigDecimal(0.00));
		this.ajusteExistenciasActualesBean.setDiferencia(new BigDecimal(0.00));
		try{
			this.fechaCierreInteger = fechaUtils.formateoFecha(this.ajusteExistenciasActualesBean.getFechaCierre());
		}catch (ParseException pe) {}
		this.editable = true;
		this.filtro = false;
		this.botonesBloqueados = false;
		this.parrilla.iniciaParrilla(true);
		this.parrilla.getColumnas().add(ColumnaParrillaEnum.EXISTENCIAS);
		this.parrilla.setImporteAEditar(ColumnaParrillaEnum.IMPORTE_NUEVO);
		
		try{
			consultaExistenciasActualesBackend.ejecutarTRN();
			
			this.ajusteExistenciasActualesBean.setExistenciaDenominacion(consultaExistenciasActualesBackend.getListaExistencias());
			this.parrilla.setListaDenominaciones(this.ajusteExistenciasActualesBean.getExistenciaDenominacion());
			
			this.ajusteExistenciasActualesBean.setTotalExistencias(this.consultaExistenciasActualesBackend.getAjusteExistenciasActualesBean().getTotalExistencias());
			
			actualizaDiferencia();
		}catch (ControlableException ce){
			if(ce.getRtncod() != 1){
				setMensajeError(ce.getMensajeDetalle());
				RequestContext.getCurrentInstance().execute("PF('dlgErrorAjuste').show()");
				
				this.editable = false;
				this.parrilla.getColumnas().add(ColumnaParrillaEnum.IMPORTE_NUEVO);
				
				this.ajusteExistenciasActualesBean.setTotalEfectivo(new BigDecimal(0.00));
				this.ajusteExistenciasActualesBean.setTotalExistencias(new BigDecimal(0.00));
				this.ajusteExistenciasActualesBean.setDiferencia(new BigDecimal(0.00));
				this.botonesBloqueados = true;
				
				for(ExistenciaDenominacionBean existenciaDenominacionBean : this.parrilla.getListaDenominaciones()){
					existenciaDenominacionBean.setExistencias(new BigDecimal(0.00));
					existenciaDenominacionBean.setImporteNuevo(new BigDecimal(0.00));
				}
			}
		}
	}
	
	public String inicio(){
		this.obtieneFlash().put(ParametrosFlashEnum.AJUSTE_EXISTENCIAS_BEAN.getParamFlash(), this.ajusteExistenciasActualesBean);
		return NavegacionEnum.AJUSTE_EXISTENCIAS.getRuta();
	}
	
	/**
     * Se encarga de obtener el flash.
     * 
     * @return Flash con los datos de la pagina
     */
	public Flash obtieneFlash() {
		 return FacesContext.getCurrentInstance().getExternalContext().getFlash();
	}
	 
	public String irInicio(){
		return NavegacionEnum.INICIO.getRuta();
	}
	
	public void inicializaValores(){
		for(ExistenciaDenominacionBean existenciaDenominacionBean : this.parrilla.getListaDenominaciones()){
			existenciaDenominacionBean.setImporteNuevo(existenciaDenominacionBean.getExistencias());
		}
		
		this.ajusteExistenciasActualesBean.setTotalEfectivo(this.ajusteExistenciasActualesBean.getTotalExistencias());
		this.ajusteExistenciasActualesBean.setDiferencia(new BigDecimal(0.00));
	}
	
	public void actualizaTotalNuevo(){
		BigDecimal totalNuevo = new BigDecimal(0.00);
		for(ExistenciaDenominacionBean existenciaDenominacionBean : this.parrilla.getListaDenominaciones()){
			if(existenciaDenominacionBean.getImporteNuevo() != null){
				totalNuevo = totalNuevo.add(existenciaDenominacionBean.getImporteNuevo());
			}
		}
		this.ajusteExistenciasActualesBean.setTotalEfectivo(totalNuevo);
		actualizaDiferencia();
	}
	
	public void actualizaDiferencia(){
		BigDecimal diferencia = new BigDecimal(0.00);
		diferencia = this.ajusteExistenciasActualesBean.getTotalExistencias().subtract(this.ajusteExistenciasActualesBean.getTotalEfectivo());
		this.ajusteExistenciasActualesBean.setDiferencia(diferencia);
	}
	
	public void validaDatos(ExistenciaDenominacionBean existenciaDenominacionBean){
		actualizaTotalNuevo();
	}
	
	public void ajustaExistencias(){
		
		if(this.ajusteExistenciasActualesBean.getTotalEfectivo().compareTo(BigDecimal.ZERO) > 0){
			try{
				this.ajusteExistenciasActualesBean.setExistenciaDenominacion(this.parrilla.getListaDenominaciones());
				int codigoRetorno = ajustaExistenciasBackend.ejecutarTRN(this.ajusteExistenciasActualesBean);
				
				if(codigoRetorno == 1){
					RequestContext.getCurrentInstance().execute("PF('dlgOperacionExitosa').show();");
					
					setEditable(false);
					setBotonesBloqueados(true);
					this.parrilla.getColumnas().add(ColumnaParrillaEnum.IMPORTE_NUEVO);

					for(ExistenciaDenominacionBean existenciaDenominacionBean : this.parrilla.getListaDenominaciones()){
						if(existenciaDenominacionBean.getImporteNuevo().compareTo(BigDecimal.ZERO) > 0){
							existenciaDenominacionBean.setExistencias(existenciaDenominacionBean.getImporteNuevo());
						}
					}
				}
			}catch (ControlableException ce){
				if(ce.getRtncod() != 1){
					setMensajeError(ce.getMensajeDetalle());
					RequestContext.getCurrentInstance().execute("PF('dlgErrorAjuste').show()");
				}
			}
		}else{
			setMensajeAdvertencia("El valor de Total nuevo debe ser mayor a cero.");
			RequestContext.getCurrentInstance().execute("PF('dlgAdvertencia').hide();");
		}
	}
	
	public void limpiar(){
		init();
	}
}
