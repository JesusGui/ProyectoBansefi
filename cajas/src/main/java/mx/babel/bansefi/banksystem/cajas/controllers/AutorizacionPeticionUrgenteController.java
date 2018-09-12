package mx.babel.bansefi.banksystem.cajas.controllers;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.sesion.utils.ContextoUtils;
import mx.babel.bansefi.banksystem.base.beans.parrilla.ColumnaParrillaEnum;
import mx.babel.bansefi.banksystem.base.beans.parrilla.ExistenciaDenominacionBean;
import mx.babel.bansefi.banksystem.base.beans.parrilla.ParrillaBean;
import mx.babel.bansefi.banksystem.base.enums.NavegacionEnum;
import mx.babel.bansefi.banksystem.base.enums.ParametrosFlashEnum;
import mx.babel.bansefi.banksystem.base.utils.PdfUtils;
import mx.babel.bansefi.banksystem.cajas.backend.AutorizacionPeticionUrgenteBackend;
import mx.babel.bansefi.banksystem.cajas.backend.ConsultaParillaBilletesBackend;
import mx.babel.bansefi.banksystem.cajas.backend.ConsultaPeticionUrgenteBackend;
import mx.babel.bansefi.banksystem.cajas.backend.SuprimirPeticionUrgenteBackend;
import mx.babel.bansefi.banksystem.cajas.backend.ValidaEntidadBackend;
import mx.babel.bansefi.banksystem.cajas.beans.AutorizacionPeticionUrgenteBean;
import mx.babel.bansefi.banksystem.cajas.beans.ConsultaPeticionUrgenteBean;
import mx.babel.bansefi.banksystem.cajas.utils.FechaUtils;

import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Controlador de vistas para flujo de autorización de petición de efectivo.
 * 
 * @author aaron.lopez
 * 
 */
@ManagedBean(name="autorizacionPeticionUrgenteController")
@Component
@Scope("view")
public class AutorizacionPeticionUrgenteController {
	
	@Autowired
	private ContextoUtils contextoUtils;
	@Autowired
	private ConsultaParillaBilletesBackend consultaParillaBilletesBackend;
	@Autowired
	private ConsultaPeticionUrgenteBackend consultaPeticionUrgenteBackend;
	@Autowired
	private AutorizacionPeticionUrgenteBackend autorizacionPeticionUrgenteBackend;
	@Autowired
	private ValidaEntidadBackend validaEntidadBackend;
	@Autowired
	private SuprimirPeticionUrgenteBackend suprimirPeticionUrgenteBackend;
	@Autowired
	private PdfUtils PdfUtils;

	private Date fechaSolicitud;
	
	private ParrillaBean parrilla;
	
	private AutorizacionPeticionUrgenteBean autorizacionPeticionUrgenteBean;
	
	private Boolean filtro;
	private Boolean editable;
	private Boolean precinto = false;
	private Boolean importeFijoEdicion = true;
	private Boolean elementosVisibles;
	private Boolean mostrarMensaje;
	private Boolean botonBloqueado;
	private Boolean mismoDesglose;
	private Boolean mismoDesgloseBloqueado;
	
	private String mensajeError;
	
	public Date getFechaSolicitud() {
		return fechaSolicitud;
	}

	public void setFechaSolicitud(Date fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}

	public ParrillaBean getParrilla() {
		return parrilla;
	}

	public void setParrilla(ParrillaBean parrilla) {
		this.parrilla = parrilla;
	}
	
	public AutorizacionPeticionUrgenteBean getAutorizacionPeticionUrgenteBean() {
		return autorizacionPeticionUrgenteBean;
	}

	public void setAutorizacionPeticionUrgenteBean(
			AutorizacionPeticionUrgenteBean autorizacionPeticionUrgenteBean) {
		this.autorizacionPeticionUrgenteBean = autorizacionPeticionUrgenteBean;
	}

	public Boolean getFiltro() {
		return filtro;
	}

	public void setFiltro(Boolean filtro) {
		this.filtro = filtro;
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

	public Boolean getImporteFijoEdicion() {
		return importeFijoEdicion;
	}

	public void setImporteFijoEdicion(Boolean importeFijoEdicion) {
		this.importeFijoEdicion = importeFijoEdicion;
	}
	
	public Boolean getElementosVisibles() {
		return elementosVisibles;
	}

	public void setElementosVisibles(Boolean elementosVisibles) {
		this.elementosVisibles = elementosVisibles;
	}

	public Boolean getMostrarMensaje() {
		return mostrarMensaje;
	}

	public void setMostrarMensaje(Boolean mostrarMensaje) {
		this.mostrarMensaje = mostrarMensaje;
	}

	public Boolean getBotonBloqueado() {
		return botonBloqueado;
	}

	public void setBotonBloqueado(Boolean botonBloqueado) {
		this.botonBloqueado = botonBloqueado;
	}

	public Boolean getMismoDesglose() {
		return mismoDesglose;
	}

	public void setMismoDesglose(Boolean mismoDesglose) {
		this.mismoDesglose = mismoDesglose;
	}

	public Boolean getMismoDesgloseBloqueado() {
		return mismoDesgloseBloqueado;
	}

	public void setMismoDesgloseBloqueado(Boolean mismoDesgloseBloqueado) {
		this.mismoDesgloseBloqueado = mismoDesgloseBloqueado;
	}

	public String getMensajeError() {
		return mensajeError;
	}

	public void setMensajeError(String mensajeError) {
		this.mensajeError = mensajeError;
	}

	/**
	 * Método para navegar a las autorizaciones de peticiones de efectivo
	 * @return ruta de autorizaciones de peticiones de efectivo
	 */
	public String inicio(){
		this.obtieneFlash().put(ParametrosFlashEnum.AUTORIZACION_PETICION_URGENTE_BEAN.getParamFlash(), this.autorizacionPeticionUrgenteBean);
		return NavegacionEnum.AUTORIZACION_PETICION.getRuta();
	}
	
	/**
	 * Método para navegación a inicio
	 * @return ruta de inicio
	 */
	public String irInicio(){
		return NavegacionEnum.INICIO.getRuta();
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
	 * Método que inicializa los valores y oculta los componentes en la vista.
	 */
	@PostConstruct
	public void init(){
		setEditable(true);
		setFiltro(true);
		setElementosVisibles(false);
		setFechaSolicitud(contextoUtils.getFechaContableActual());
		this.parrilla = new ParrillaBean();
		this.parrilla.iniciaParrilla(true);
		this.parrilla.setImporteAEditar(ColumnaParrillaEnum.IMPORTE_AUTORIZADO);
		this.parrilla.getColumnas().add(ColumnaParrillaEnum.IMPORTE_PEDIDO);
		this.autorizacionPeticionUrgenteBean = new AutorizacionPeticionUrgenteBean();
		this.autorizacionPeticionUrgenteBean.setTotalPedido(new BigDecimal(0.00));
		this.autorizacionPeticionUrgenteBean.setTotalAutorizado(new BigDecimal(0.00));
	}
	
	/**
	 * Método que inicializa los valores de la parrilla en base a la consulta
	 * del web service de consulta de billetes.  
	 */
	public void inicializaValores(){
		consultaParillaBilletesBackend.ejecutarTRN(this.parrilla.getListaDenominaciones());
		this.parrilla.setListaDenominaciones(consultaParillaBilletesBackend.getListaExistencias());
	}
	
	/**
	 * Método que consulta la petición urgente en base a la fecha contable del sistema, 
	 * al centro ingresado y al número de petición urgente.
	 *   
	 */
	public void consultaPeticionesUrgentes(){
		if(validaCampos()){
			FechaUtils utils = new FechaUtils();
			try{
				inicializaValores();
				consultaPeticionUrgenteBackend.ejecutarTRN(
						utils.formateoFecha(getFechaSolicitud()), 
						autorizacionPeticionUrgenteBean.getCentro(), 
						autorizacionPeticionUrgenteBean.getNoUrgente());
				
				this.autorizacionPeticionUrgenteBean = consultaPeticionUrgenteBackend.getAutorizacionPeticionUrgenteBean();
				this.autorizacionPeticionUrgenteBean.setListaDenominacionesPeticion(consultaPeticionUrgenteBackend.getListaPeticiones());
				
				obtenDatosConsulta();
				iniciaUnidades();
				
				setElementosVisibles(true);
				setMostrarMensaje(false);
				
				if(this.autorizacionPeticionUrgenteBean.getTotalPedido().equals(this.autorizacionPeticionUrgenteBean.getTotalAutorizado())){
					setMismoDesglose(true);
				}else{
					setMismoDesglose(false);
				}
				this.actualizarForm();
				RequestContext.getCurrentInstance().execute("resizeParrillaColumns()");
			}catch (ControlableException ce){
				if(ce.getRtncod() == 7){
					FacesContext.getCurrentInstance().addMessage("messages", new FacesMessage(FacesMessage.SEVERITY_WARN,
						"","No existen peticiones con los datos ingresados."));
					setMostrarMensaje(true);
					setElementosVisibles(false);
				}
			}catch (ParseException pe){
				FacesContext.getCurrentInstance() .addMessage("messages", new FacesMessage(FacesMessage.SEVERITY_WARN,
						"","Fecha no valida."));
			}
		}
		if(autorizacionPeticionUrgenteBean.getEstatusPeticionC() != null){
			if(autorizacionPeticionUrgenteBean.getEstatusPeticionC().equals("S")){
				 setBotonBloqueado(false);
			 }else{
				 setMismoDesgloseBloqueado(true);
				 setBotonBloqueado(true);
			 }
		}	
	}
	
	/**
	 * Método que actualiza el formulario con ajax
	 * */
	private void actualizarForm(){
		RequestContext.getCurrentInstance().update("formAutorizacionPeticionEfectivo"); 
	}
	
	/**
	 * Método que autoriza la petición urgente.
	 * 
	 * @return ruta del resumen.  
	 */
	public void validaEntidad(){
		try{
			final Integer resultadoValidacion = ejecutarTRNValidaEntidad();
			ejecutarTRNAlta(resultadoValidacion);
			
		}catch (ControlableException ce){
			if(ce.getRtncod() != 1){
				setMensajeError(ce.getMensajeDetalle());
				RequestContext.getCurrentInstance().update("dlgErrorUrgente");
				RequestContext.getCurrentInstance().execute("PF('dlgErrorUrgente').show();");
				RequestContext.getCurrentInstance().update("dlgErrorUrgente");
			}
		}catch (NoControlableException nce){
			
		}
	}
	
	private void ejecutarTRNAlta(final Integer codigoRetornoValidacion) throws NoControlableException, ControlableException{
		Integer resultadoAutorizacion = 0;
		List<ExistenciaDenominacionBean> listaAutorizados = new ArrayList<ExistenciaDenominacionBean>();
		
		if(codigoRetornoValidacion == 1){
			for(ExistenciaDenominacionBean existenciaDenominacionBean : this.parrilla.getListaDenominaciones()){
				for(ConsultaPeticionUrgenteBean consultaPeticionUrgenteBean : this.autorizacionPeticionUrgenteBean.getListaDenominacionesPeticion()){
					if(existenciaDenominacionBean.getImportePedido() != null
							&& existenciaDenominacionBean.getImporteAutorizado() != null
							&& existenciaDenominacionBean.getOrigen().equals(consultaPeticionUrgenteBean.getOrigen())
							&& existenciaDenominacionBean.getValorFacial().equals(consultaPeticionUrgenteBean.getCodigoValorFacial())){
						
						consultaPeticionUrgenteBean.setImportePedido(existenciaDenominacionBean.getImportePedido());
						consultaPeticionUrgenteBean.setImporteAutorizado(existenciaDenominacionBean.getImporteAutorizado());
						
						actualizaTotalAutorizado();
						
						break;
					}
				}
			}
			
			if(this.autorizacionPeticionUrgenteBean.getTotalAutorizado().compareTo(BigDecimal.ZERO) > 0){
				resultadoAutorizacion = ejecutarTRNAutorizacion();
				
				if(resultadoAutorizacion == 1){
					
					for(ExistenciaDenominacionBean existenciaDenominacionBean : this.parrilla.getListaDenominaciones()){
						if(existenciaDenominacionBean.getImporteAutorizado() != null
								&& existenciaDenominacionBean.getImporteAutorizado().compareTo(BigDecimal.ZERO) > 0){
							listaAutorizados.add(existenciaDenominacionBean);
						}
					}
					this.autorizacionPeticionUrgenteBean.setListaDenominaciones(new ArrayList<ExistenciaDenominacionBean>());
					this.autorizacionPeticionUrgenteBean.setListaDenominaciones(listaAutorizados);
					RequestContext.getCurrentInstance().execute("PF('dlgOperacionExitosa').show();");
				}
				
			}else{
				RequestContext.getCurrentInstance().execute("PF('dlgImporteCero').show();");
			}
		}
	}
	
	/**
	 * Método que suprime la petición urgente, validando si la petición urgente no ha
	 * sido autorizada anteriormente.
	 */
	public void suprimirPeticionUrgente(){
		try{
			if(ejecutarTRNSuprimir() == 1){
				limpiarParrilla();
				RequestContext.getCurrentInstance().execute("PF('dlgOperacionExitosaSupr').show();");
			}
		}catch (ControlableException ce){
			if(ce.getRtncod() != 1){
				setMensajeError(ce.getMensajeDetalle());
				RequestContext.getCurrentInstance().execute("PF('dlgErrorUrgente').show();");
			}	
		}
	}
	
	public String irDetalle(){
		this.obtieneFlash().put(ParametrosFlashEnum.AUTORIZACION_PETICION_URGENTE_BEAN.getParamFlash(), this.autorizacionPeticionUrgenteBean);
		return NavegacionEnum.RESUMEN_AUTORIZACION_PETICION_URGENTE.getRuta();
	}
	
	/**
	 * Método que obtiene los datos de la consulta y los ingresa a la parrilla
	 * 
	 */
	public void obtenDatosConsulta(){
		for(ConsultaPeticionUrgenteBean consultaPeticionUrgenteBean : this.autorizacionPeticionUrgenteBean.getListaDenominacionesPeticion()){
			for(ExistenciaDenominacionBean existenciaDenominacionBean : this.parrilla.getListaDenominaciones()){
				if(consultaPeticionUrgenteBean.getOrigen().trim().equals(existenciaDenominacionBean.getOrigen().trim())
						&& consultaPeticionUrgenteBean.getCodigoValorFacial().equals(existenciaDenominacionBean.getValorFacial())){
					existenciaDenominacionBean.setImportePedido(consultaPeticionUrgenteBean.getImportePedido());
					existenciaDenominacionBean.setImporteAutorizado(consultaPeticionUrgenteBean.getImporteAutorizado());					
					existenciaDenominacionBean.setImporteModificable(consultaPeticionUrgenteBean.getImporteAutorizado());
					setMostrarMensaje(false);
				}
			}
		}
		
		for(ExistenciaDenominacionBean existenciaDenominacionBean : this.parrilla.getListaDenominaciones()){
			if(existenciaDenominacionBean.getImporteAutorizado() != null){
				if(existenciaDenominacionBean.getImporteAutorizado().compareTo(BigDecimal.ZERO) == 0){
					existenciaDenominacionBean.setUnidades(null);
					existenciaDenominacionBean.setImporteAutorizado(null);
					existenciaDenominacionBean.setImporteModificable(null);
				}
			}
		}
	}
	
	/**
	 *Método que actualiza los datos de la parrilla.
	 * 
	 */
	private void iniciaUnidades(){
		for (ExistenciaDenominacionBean existenciaDenominacionBean : this.parrilla.getListaDenominaciones()) {
			actualizaUnidades(existenciaDenominacionBean);
		}
	}
	
	/**
	 * Método que actualiza las unidades de una denominación en función al importe ingresado.
	 * @param denominacion DEnomincación a evaluar
	 */
	public void actualizaUnidades(ExistenciaDenominacionBean denominacion){
		if(denominacion.getValor() != null && denominacion.getImporteModificable() != null){
			Long unidades = denominacion.getImporteModificable().divide(denominacion.getValor()).longValue();
			denominacion.setUnidades(unidades);
		}
		actualizaTotalPeticion();
		actualizaTotalAutorizado();
	}
	
	/**
	 * Método que actualiza el total de la peticion  en relación a los valores ingresados en la parrilla de denominaciones.
	 */
	public void actualizaTotalPeticion(){
		BigDecimal total = new BigDecimal(0);
		for (ExistenciaDenominacionBean denominacion : this.parrilla.getListaDenominaciones()) {
			if(denominacion.getImportePedido() != null){
				total = total.add(denominacion.getImportePedido());
			}
		}
		this.autorizacionPeticionUrgenteBean.setTotalPedido(total);
		
	}
	
	/**
	 * Método que actualiza el total de autorizado en relación a los valores ingresados en la parrilla de denominaciones.
	 */
	public void actualizaTotalAutorizado(){
		BigDecimal totalAutorizado = new BigDecimal(0);
		for(ExistenciaDenominacionBean existenciaDenominacionBean : this.parrilla.getListaDenominaciones()){
			if(existenciaDenominacionBean.getImporteAutorizado() != null){
				totalAutorizado	= totalAutorizado.add(existenciaDenominacionBean.getImporteAutorizado());
			}
		}
		this.autorizacionPeticionUrgenteBean.setTotalAutorizado(totalAutorizado);
		
		if(!this.autorizacionPeticionUrgenteBean.getTotalPedido().equals(this.autorizacionPeticionUrgenteBean.getTotalAutorizado())){
			setMismoDesgloseBloqueado(false);
		}
		RequestContext.getCurrentInstance().execute("resizeParrillaColumns()");
	}
	
	/**
	 * Método que valida los campos al momento de consultar una petición.
	 * */
	public Boolean validaCampos(){
		
		Boolean noNulos = false;
		
		if(this.autorizacionPeticionUrgenteBean.getNoUrgente() != null
				&& this.autorizacionPeticionUrgenteBean.getNoUrgente() > 0
				&& this.autorizacionPeticionUrgenteBean.getCentro() != null
				&& !this.autorizacionPeticionUrgenteBean.getCentro().isEmpty()){
			noNulos = true;
		}
		
		return noNulos;
	}
	
	public void colocarMismoDesglose(){
		if(getMismoDesglose()){
			for(ExistenciaDenominacionBean existenciaDenominacionBean : this.parrilla.getListaDenominaciones()){
				if(existenciaDenominacionBean.getImportePedido() != null){
					if(existenciaDenominacionBean.getImportePedido().compareTo(BigDecimal.ZERO) > 0){
						existenciaDenominacionBean.setImporteAutorizado(existenciaDenominacionBean.getImportePedido());
						this.parrilla.actualizaUnidades(existenciaDenominacionBean, existenciaDenominacionBean.getImporteAutorizado());
					}
				}else{
					existenciaDenominacionBean.setImporteAutorizado(null);
					existenciaDenominacionBean.setUnidades(null);	
				}
			}
		}else{
			for(ExistenciaDenominacionBean existenciaDenominacionBean : this.parrilla.getListaDenominaciones()){
				if(existenciaDenominacionBean.getImportePedido() != null){
					existenciaDenominacionBean.setImporteAutorizado(null);
					existenciaDenominacionBean.setUnidades(null);
				}
			}
		}
		actualizaTotalAutorizado();
	}
	
	public void validaTotal(){
		if(this.autorizacionPeticionUrgenteBean.getTotalAutorizado().compareTo(BigDecimal.ZERO) > 0){
			RequestContext.getCurrentInstance().execute("PF('dlgConfirmarAutorizacionUrgente').show();");
		}else{
			RequestContext.getCurrentInstance().execute("PF('dlgImporteCero').show();");
		}
	}
	
	private Integer ejecutarTRNAutorizacion() throws ControlableException, NoControlableException{
		Integer codigoRetorno = autorizacionPeticionUrgenteBackend.ejecutarTRN(this.autorizacionPeticionUrgenteBean);
		return codigoRetorno;
	}
	
	private Integer ejecutarTRNValidaEntidad() throws ControlableException, NoControlableException{
		Integer codigoRetorno = validaEntidadBackend.ejecutarTRN();
		return codigoRetorno;
	}
	
	private Integer ejecutarTRNSuprimir() throws ControlableException, NoControlableException{
		Integer codigoRetorno = suprimirPeticionUrgenteBackend.ejecutarTRN(getAutorizacionPeticionUrgenteBean());
		return codigoRetorno;
	}
	
	/**
	 * Método que limpia la parrilla y los datos de la pantalla 
	 */
	public void limpiarParrilla(){
		setBotonBloqueado(true);
		setElementosVisibles(false);
		this.autorizacionPeticionUrgenteBean = new AutorizacionPeticionUrgenteBean();
		this.parrilla.setListaDenominaciones(new ArrayList<ExistenciaDenominacionBean>());
	}
}