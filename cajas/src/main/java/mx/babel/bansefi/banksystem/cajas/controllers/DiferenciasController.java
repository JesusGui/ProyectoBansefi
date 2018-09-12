package mx.babel.bansefi.banksystem.cajas.controllers;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

import mx.babel.arq.catalogo.beans.CatalogoBean;
import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.sesion.utils.ContextoUtils;
import mx.babel.bansefi.banksystem.base.beans.parrilla.ColumnaParrillaEnum;
import mx.babel.bansefi.banksystem.base.beans.parrilla.ExistenciaDenominacionBean;
import mx.babel.bansefi.banksystem.base.enums.NavegacionEnum;
import mx.babel.bansefi.banksystem.base.enums.ParametrosFlashEnum;
import mx.babel.bansefi.banksystem.base.utils.CatalogoCentrosLoaderTask;
import mx.babel.bansefi.banksystem.base.utils.CatalogoUtils;
import mx.babel.bansefi.banksystem.base.beans.parrilla.ParrillaBean;
import mx.babel.bansefi.banksystem.cajas.backend.ConsultaDesgloseDiferencias1Backend;
import mx.babel.bansefi.banksystem.cajas.backend.ConsultaDesgloseDiferencias2Backend;
import mx.babel.bansefi.banksystem.cajas.backend.ConsultaDiferenciasBackend;
import mx.babel.bansefi.banksystem.cajas.backend.ConsultaParillaBilletesBackend;
import mx.babel.bansefi.banksystem.cajas.backend.CorregirDiferenciasBackend;
import mx.babel.bansefi.banksystem.cajas.beans.DiferenciaBean;
import mx.babel.bansefi.banksystem.cajas.utils.FechaUtils;

import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@ManagedBean(name = "diferenciasController")
@Component
@Scope("view")
public class DiferenciasController implements Serializable{

	private static final long serialVersionUID = -3465656789004094509L;
	
	@Autowired
	ContextoUtils contextoUtils;
	@Autowired
	FechaUtils fechaUtils;
	@Autowired
	CatalogoUtils catalogoUtils;
	@Autowired
	CatalogoCentrosLoaderTask catalogoCentrosLoaderTask;
	@Autowired
	ConsultaDiferenciasBackend consultaDiferenciasBackend;
	@Autowired
	ConsultaDesgloseDiferencias1Backend consultaDesgloseDiferencias1Backend;
	@Autowired
	ConsultaDesgloseDiferencias2Backend consultaDesgloseDiferencias2Backend;
	@Autowired
	ConsultaParillaBilletesBackend consultaParillaBilletesBackend;
	@Autowired
	CorregirDiferenciasBackend corregirDiferenciasBackend;
	
	private DiferenciaBean diferenciaBean;
	private DiferenciaBean diferenciaBeanPrincipal;
	private DiferenciaBean diferenciaCorregir;
	
	private List<DiferenciaBean> listaDiferencias;
	
	private String mensajeError;
	private String mensajeAdvertencia;
	private String mensajeInfo;
	
	private Date fechaMinima;
	private Date fechaMaxima;
	
	private boolean fechasDisabled = false;
	
	public DiferenciasController() throws ParseException{
		
		this.diferenciaBean = new DiferenciaBean();
		this.diferenciaBeanPrincipal = new DiferenciaBean();
		this.diferenciaBeanPrincipal.setParrilla(new ParrillaBean());
		listaDiferencias = new ArrayList<DiferenciaBean>();
		setFechaMinima(new SimpleDateFormat("yyyy-MM-dd").parse("1111-11-11"));
		setFechaMaxima(new SimpleDateFormat("yyyy-MM-dd").parse("9999-12-31"));
	}
	
	public String inicio(){
		return NavegacionEnum.DIFERENCIAS.getRuta();
	}
	
	public String irInicio(){
		return NavegacionEnum.INICIO.getRuta();
	}
	
	@PostConstruct
	public void init(){
		if(this.obtieneFlash().get(ParametrosFlashEnum.DIFERENCIA_BEAN_PRINCIPAL.getParamFlash()) != null
				&& this.obtieneFlash().get(ParametrosFlashEnum.DIFERENCIA_BEAN.getParamFlash()) != null){
			
			this.diferenciaBeanPrincipal = (DiferenciaBean) this.obtieneFlash().get(ParametrosFlashEnum.DIFERENCIA_BEAN_PRINCIPAL.getParamFlash());
			this.diferenciaBean = (DiferenciaBean) this.obtieneFlash().get(ParametrosFlashEnum.DIFERENCIA_BEAN.getParamFlash());
			
		}else if(this.obtieneFlash().get(ParametrosFlashEnum.DIFERENCIA_BEAN_PRINCIPAL.getParamFlash()) != null){
			
			this.diferenciaBean = (DiferenciaBean) this.obtieneFlash().get(ParametrosFlashEnum.DIFERENCIA_BEAN_PRINCIPAL.getParamFlash());
			consultarDiferencias();
		}
	}
	
	public void consultarDiferencias(){
		try{
			if(diferenciaBean.getFechaDesde() == null && diferenciaBean.getFechaHasta() == null){
				mostrarMensajeAdvertencia("Debes informar al menos una fecha en el rango de fechas.");
			}else{
				if(diferenciaBean.getFechaDesde() == null){
		 			this.diferenciaBean.setFechaDesde(fechaMinima);
		 		}
		 		if(diferenciaBean.getFechaHasta() == null){
		 			this.diferenciaBean.setFechaHasta(fechaMaxima);
		 		}
	 		
		 		this.listaDiferencias = consultaDiferencias(diferenciaBean.getFechaDesde(), diferenciaBean.getFechaHasta());
		 		
		 		setFechasDisabled(true);
			}
		}catch (ParseException p){
			mostrarMensajeError("La fecha no es una fecha valida. Error al parsear la fecha.");
		}catch (ControlableException ce){
			if(ce.getRtncod() == 7){
				mostrarMensajeInfo("No existen diferencias para el rango de fechas indicado.");
			}else{
				mostrarMensajeError(ce.getMensajeDetalle());
			}
		}
	}
	
	public void verDetalleDiferencia(DiferenciaBean diferencia){
		
		List<ExistenciaDenominacionBean> listaExistencias = new ArrayList<ExistenciaDenominacionBean>();
		List<ExistenciaDenominacionBean> listaExistencias2 = new ArrayList<ExistenciaDenominacionBean>();
		
		try {
			listaExistencias = consultaDesgloseDiferencias1Backend.ejecutaTRN(
					diferencia.getCentrOrigen(), 
					diferencia.getFechaPeticion(), 
					diferencia.getCodigoMoneda(),
					diferencia.getCodigoDistribucion().getClaveFila(), 
					diferencia.getNumeroUrgente(), 
					diferencia.getCentroDesino());
			
			listaExistencias2 = consultaDesgloseDiferencias2Backend.ejecutarTRN(
					listaExistencias, 
					diferencia.getCentrOrigen(),
					diferencia.getCentroDesino(),
					diferencia.getCodigoMoneda(),
					diferencia.getCodigoDistribucion().getClaveFila(),
					diferencia.getFechaPeticion(), 
					diferencia.getNumeroUrgente());
			
			diferencia.setParrilla(new ParrillaBean());
			diferencia.getParrilla().iniciaParrilla(true);
			diferencia.getParrilla().getColumnas().add(ColumnaParrillaEnum.IMPORTE_AUTORIZADO);
			diferencia.getParrilla().getColumnas().add(ColumnaParrillaEnum.IMPORTE_RECIBIDO);
			consultaParillaBilletesBackend.ejecutarTRN(diferencia.getParrilla().getListaDenominaciones());
			diferencia.getParrilla().setListaDenominaciones(consultaParillaBilletesBackend.getListaExistencias());
			
			for(ExistenciaDenominacionBean existencia : listaExistencias2){
				for(ExistenciaDenominacionBean existencias : diferencia.getParrilla().getListaDenominaciones()){
					if(existencia.getImporteAEnviar() != null
							&& existencia.getImporteAEnviar().compareTo(BigDecimal.ZERO) == 1
							&& existencia.getImporteRecibido() != null
							&& existencia.getImporteRecibido().compareTo(BigDecimal.ZERO) == 1){
						if(existencia.getOrigen().equals(existencias.getOrigen()) && existencia.getValorFacial().equals(existencias.getValorFacial())){
							
							existencias.setImporteAutorizado(existencia.getImporteAutorizado());
							existencias.setImporteRecibido(existencia.getImporteRecibido());
						}
					}
				}
			}
			
			this.obtieneFlash().put(ParametrosFlashEnum.DIFERENCIA_BEAN_PRINCIPAL.getParamFlash(), diferenciaBean);
			this.obtieneFlash().put(ParametrosFlashEnum.DIFERENCIA_BEAN.getParamFlash(), diferencia);
			
			ConfigurableNavigationHandler navigationHandler = (ConfigurableNavigationHandler) FacesContext
					.getCurrentInstance().getApplication().getNavigationHandler();

			navigationHandler.performNavigation(NavegacionEnum.DETALLE_DIFERENCIAS.getRuta());
			
		} catch (ParseException e) {
			mostrarMensajeError("La fecha no es una fecha valida. Error al parsear la fecha.");
		}catch (ControlableException ce){
			if(ce.getRtncod() == 7){
				mostrarMensajeInfo("No se ha encontrado el detalle de desgloses para la diferencia seleccionada.");
			}else{
				mostrarMensajeError(ce.getMensajeDetalle());
			}
		}
	}
	
	public String regresarDetalle(){
		this.obtieneFlash().put(ParametrosFlashEnum.DIFERENCIA_BEAN_PRINCIPAL.getParamFlash(), diferenciaBeanPrincipal);
		return NavegacionEnum.DIFERENCIAS.getRuta();
	}
	
	public void cargarCorreccionDiferencia(DiferenciaBean diferencia){
		this.diferenciaCorregir = diferencia;
		RequestContext.getCurrentInstance().execute("PF('dlgConfirmarCorreccion').show();");
		RequestContext.getCurrentInstance().update("dlgConfirmarCorreccion");
	}
	
	public void corregirDiferencias(){
		try{
			int codigoResultado = corregirDiferenciasBackend.ejecutarTRN(diferenciaCorregir);
			
			if(codigoResultado == 1){
				RequestContext.getCurrentInstance().execute("PF('dlgOperacionExitosa').show();");
			}
		}catch (ControlableException ce){
			if(ce.getRtncod() != 7){
				mostrarMensajeError(ce.getMensajeUsuario() + " - " + ce.getMensajeDetalle());
			}
		}
	}
	
	private List<DiferenciaBean> consultaDiferencias(Date fechaDesde, Date fechaHasta) throws ParseException{
		return consultaDiferenciasBackend.ejecutarTRN(fechaUtils.formateoFecha(fechaDesde), fechaUtils.formateoFecha(fechaHasta));
	}
	
	private void mostrarMensajeError(final String mensaje){
		setMensajeError(mensaje);
		RequestContext.getCurrentInstance().execute("PF('dlgMensajeError').show();");
		RequestContext.getCurrentInstance().update("dlgMensajeError");
	}
	
	private void mostrarMensajeAdvertencia(final String mensaje){
		setMensajeAdvertencia(mensaje);
		RequestContext.getCurrentInstance().execute("PF('dlgMensajeAdvertencia').show();");
		RequestContext.getCurrentInstance().update("dlgMensajeAdvertencia");
	}
	
	private void mostrarMensajeInfo(final String mensaje){
		setMensajeInfo(mensaje);
		RequestContext.getCurrentInstance().execute("PF('dlgMensajeInfo').show();");
		RequestContext.getCurrentInstance().update("dlgMensajeInfo");
	}
	
	public Date formateoFecha(int fecha) throws ParseException {
		Date fechaNueva = new SimpleDateFormat("yyyyMMdd").parse(new Integer(fecha).toString());
		return fechaNueva;
	}
	
	public String formateoFechaString(int fecha) throws ParseException {
		Date fechaNueva = new SimpleDateFormat("yyyyMMdd").parse(new Integer(fecha).toString());
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String fechaFormateada = sdf.format(fechaNueva);
		return fechaFormateada;
	}
	
	public BigDecimal calculcaDiferencia(BigDecimal autorizado, BigDecimal recibido){
		BigDecimal total = new BigDecimal(0.00);
		total = recibido.subtract(autorizado);
		return total;
	}
	
	public void limpiar(){
		setFechasDisabled(false);
		listaDiferencias.clear();
		diferenciaBean.setFechaDesde(null);
		diferenciaBean.setFechaHasta(null);
	}
	
	/**
     * Se encarga de obtener el flash.
     * 
     * @return Flash con los datos de la pagina
     */
	public Flash obtieneFlash() {
		 return FacesContext.getCurrentInstance().getExternalContext().getFlash();
	}
	
	public CatalogoBean obtenerDescripcionCentro(String clave){
		return catalogoCentrosLoaderTask.getCatalogoBean(contextoUtils.getEntidad(), clave);
	}
	
	/* Inicia declaración de getters y setters */
	
	public DiferenciaBean getDiferenciaBean() {
		return diferenciaBean;
	}

	public void setDiferenciaBean(DiferenciaBean diferenciaBean) {
		this.diferenciaBean = diferenciaBean;
	}
	
	public DiferenciaBean getDiferenciaBeanPrincipal() {
		return diferenciaBeanPrincipal;
	}

	public void setDiferenciaBeanPrincipal(DiferenciaBean diferenciaBeanPrincipal) {
		this.diferenciaBeanPrincipal = diferenciaBeanPrincipal;
	}

	public DiferenciaBean getDiferenciaCorregir() {
		return diferenciaCorregir;
	}

	public void setDiferenciaCorregir(DiferenciaBean diferenciaCorregir) {
		this.diferenciaCorregir = diferenciaCorregir;
	}

	public List<DiferenciaBean> getListaDiferencias() {
		return listaDiferencias;
	}

	public void setListaDiferencias(List<DiferenciaBean> listaDiferencias) {
		this.listaDiferencias = listaDiferencias;
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

	public String getMensajeInfo() {
		return mensajeInfo;
	}

	public void setMensajeInfo(String mensajeInfo) {
		this.mensajeInfo = mensajeInfo;
	}

	public Date getFechaMinima() {
		return fechaMinima;
	}

	public void setFechaMinima(Date fechaMinima) {
		this.fechaMinima = fechaMinima;
	}

	public Date getFechaMaxima() {
		return fechaMaxima;
	}

	public void setFechaMaxima(Date fechaMaxima) {
		this.fechaMaxima = fechaMaxima;
	}

	public boolean isFechasDisabled() {
		return fechasDisabled;
	}

	public void setFechasDisabled(boolean fechasDisabled) {
		this.fechasDisabled = fechasDisabled;
	}
	
	/* Termina declaración de getters y setters */
}
