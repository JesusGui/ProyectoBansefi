package mx.babel.bansefi.banksystem.cajas.controllers;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
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
import mx.babel.bansefi.banksystem.base.utils.CatalogoUtils;
import mx.babel.bansefi.banksystem.base.utils.PdfUtils;
import mx.babel.bansefi.banksystem.cajas.backend.AutorizacionPeticionEfectivoBackend;
import mx.babel.bansefi.banksystem.cajas.backend.ConsultaPeticionesEfectivoBackEnd;
import mx.babel.bansefi.banksystem.cajas.backend.ConsultaParillaBilletesBackend;
import mx.babel.bansefi.banksystem.cajas.backend.SimulaAutorizacionPeticionBackend;
import mx.babel.bansefi.banksystem.cajas.beans.AutorizacionPeticionEfectivoBean;
import mx.babel.bansefi.banksystem.cajas.beans.CambioDenominacionBean;
import mx.babel.bansefi.banksystem.cajas.utils.FechaUtils;

import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Controlador de vistas para flujo de autorización de petición de efectivo
 * @author
 *
 */
@ManagedBean(name = "autorizacionPeticionController")
@Component
@Scope("view")
public class AutorizacionPeticionController implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8544214898582094282L;
	
	@Autowired
	private ContextoUtils contextoUtils;
	@Autowired
	private CatalogoUtils catalogoUtils;
	@Autowired
	private ConsultaPeticionesEfectivoBackEnd consultaPeticionesEfectivoBackend;
	@Autowired
	private ConsultaParillaBilletesBackend consultaParrillaBilletesBackend;
	@Autowired
	private SimulaAutorizacionPeticionBackend simularAutorizacionPeticionBackend;
	@Autowired
	private AutorizacionPeticionEfectivoBackend autorizacionPeticionEfectivoBackend;
	@Autowired
	private PdfUtils PdfUtils;
	
	@Autowired
	private AutorizacionPeticionTotalesController autorizacionPeticionTotalesController;
	
	@Autowired
	private AutorizacionPeticionUrgenteController autorizacionPeticionUrgenteController;
	
	private FechaUtils utils;
	
	private AutorizacionPeticionEfectivoBean autorizacionPeticionBean;
	private AutorizacionPeticionEfectivoBean autorizacionPeticionBeanImporteDe;
	private AutorizacionPeticionEfectivoBean autorizacionPeticionBeanImporteA;
	
	private ParrillaBean parrilla;
	
	private Boolean editable;
	private Boolean precinto = false;
	private Boolean filtro;
	private Boolean elementosVisibles;
	private Boolean elementosBloqueados;
	private Boolean botonBloqueado;
	private Boolean mostrarMensaje;
	private Boolean botonAutorizar;
	private Boolean botonVisible;
	
	private String tipoAutorizacion;
	private String mensajeError;
	
	private Date fechaSolicitud;
	
	public AutorizacionPeticionEfectivoBean getAutorizacionPeticionBean() {
		return autorizacionPeticionBean;
	}

	public void setAutorizacionPeticionBean(
			AutorizacionPeticionEfectivoBean autorizacionPeticionBean) {
		this.autorizacionPeticionBean = autorizacionPeticionBean;
	}

	public AutorizacionPeticionEfectivoBean getAutorizacionPeticionBeanImporteDe() {
		return autorizacionPeticionBeanImporteDe;
	}

	public void setAutorizacionPeticionBeanImporteDe(
			AutorizacionPeticionEfectivoBean autorizacionPeticionBeanImporteDe) {
		this.autorizacionPeticionBeanImporteDe = autorizacionPeticionBeanImporteDe;
	}

	public AutorizacionPeticionEfectivoBean getAutorizacionPeticionBeanImporteA() {
		return autorizacionPeticionBeanImporteA;
	}

	public void setAutorizacionPeticionBeanImporteA(
			AutorizacionPeticionEfectivoBean autorizacionPeticionBeanImporteA) {
		this.autorizacionPeticionBeanImporteA = autorizacionPeticionBeanImporteA;
	}

	public String getTipoAutorizacion() {
		return tipoAutorizacion;
	}

	public void setTipoAutorizacion(String tipoAutorizacion) {
		this.tipoAutorizacion = tipoAutorizacion;
	}
	
	public String getMensajeError() {
		return mensajeError;
	}

	public void setMensajeError(String mensajeError) {
		this.mensajeError = mensajeError;
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

	public Boolean getElementosVisibles() {
		return elementosVisibles;
	}

	public void setElementosVisibles(Boolean elementosVisibles) {
		this.elementosVisibles = elementosVisibles;
	}

	public Boolean getElementosBloqueados() {
		return elementosBloqueados;
	}

	public void setElementosBloqueados(Boolean elementosBloqueados) {
		this.elementosBloqueados = elementosBloqueados;
	}

	public Boolean getBotonBloqueado() {
		return botonBloqueado;
	}

	public void setBotonBloqueado(Boolean botonBloqueado) {
		this.botonBloqueado = botonBloqueado;
	}
	
	public Boolean getMostrarMensaje() {
		return mostrarMensaje;
	}

	public void setMostrarMensaje(Boolean mostrarMensaje) {
		this.mostrarMensaje = mostrarMensaje;
	}

	public Boolean getBotonAutorizar() {
		return botonAutorizar;
	}

	public void setBotonAutorizar(Boolean botonAutorizar) {
		this.botonAutorizar = botonAutorizar;
	}

	public Boolean getBotonVisible() {
		return botonVisible;
	}

	public void setBotonVisible(Boolean botonVisible) {
		this.botonVisible = botonVisible;
	}

	public Date getFechaSolicitud() {
		return fechaSolicitud;
	}

	public void setFechaSolicitud(Date fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}
	
	/***
	 *Método inicializa los campos 
	 * 
	 */
	@PostConstruct
	public void init(){
		this.tipoAutorizacion = "PE";
		this.utils = new FechaUtils();
		this.parrilla = new ParrillaBean();
		this.autorizacionPeticionBean = new AutorizacionPeticionEfectivoBean();
		this.autorizacionPeticionBeanImporteDe = new AutorizacionPeticionEfectivoBean();
		this.autorizacionPeticionBeanImporteA = new AutorizacionPeticionEfectivoBean();
		this.autorizacionPeticionBean.setListaAutorizacionCambiosDenominacion(new ArrayList<CambioDenominacionBean>());
		this.autorizacionPeticionBeanImporteDe.setImporteACambiar(new BigDecimal(0.00));
		this.autorizacionPeticionBeanImporteDe.setExistencia("E");
		this.autorizacionPeticionBeanImporteA.setExistencia("E");
		setElementosVisibles(false);
		setElementosBloqueados(true);
		setFiltro(true);
		setBotonBloqueado(true);
		setBotonAutorizar(true);
		setBotonVisible(true);
		this.autorizacionPeticionBean.setSaldoAnterior(new BigDecimal(0.00));
		this.autorizacionPeticionBean.setSaldoActual(new BigDecimal(0.00));
		this.autorizacionPeticionBean.setTotalPedido(new BigDecimal(0.00));
		this.autorizacionPeticionBean.setTotalAutorizado(new BigDecimal(0.00));
		this.parrilla.getColumnas().add(ColumnaParrillaEnum.EXISTENCIAS);
		this.parrilla.getColumnas().add(ColumnaParrillaEnum.IMPORTE_PEDIDO);
		this.parrilla.getColumnas().add(ColumnaParrillaEnum.IMPORTE_AUTORIZADO);
		this.parrilla.getColumnas().add(ColumnaParrillaEnum.DIFERENCIA);
		this.parrilla.iniciaParrilla(true);
	}
	
	/**
	 * Mètodo para navegar a las autorizaciones de peticiones de efectivo
	 * @return ruta de autorizaciones de peticiones de efectivo
	 */
	public String inicio(){
		this.obtieneFlash().put(ParametrosFlashEnum.AUTORIZACION_PETICION_BEAN.getParamFlash(), this.autorizacionPeticionBean);
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
	 * Método para inicializar la lista de denominaciones de la parrilla.
	 */
	public void iniciaListaDenominaciones(){
		
		consultaParrillaBilletesBackend.ejecutarTRN(this.parrilla.getListaDenominaciones());
		this.autorizacionPeticionBean.setListaBilletes(consultaParrillaBilletesBackend.getListaExistencias());
				
		if(!this.autorizacionPeticionBean.getListaBilletes().isEmpty() 
				&& this.autorizacionPeticionBean.getListaBilletes().size() > 0 
				&& this.autorizacionPeticionBean.getListaBilletes() != null){
			this.autorizacionPeticionBean.setListaDenominaciones(this.autorizacionPeticionBean.getListaBilletes());
		}
		
		this.parrilla.setListaDenominaciones(this.autorizacionPeticionBean.getListaDenominaciones());
	}
	
	/**
	 * Método que consulta la petición de efectivo en base a la fecha ingresada.  
	 * 
	 */	
	public void consultaPeticionesEfectivo(){
		
		if(elementosVisibles){
			setElementosVisibles(true);
			setBotonBloqueado(false);
			setBotonVisible(true);
		}
		
		iniciaListaDenominaciones();
		this.autorizacionPeticionBean.getListaAutorizacionCambiosDenominacion().clear();;
		if(getFechaSolicitud() != null && !getFechaSolicitud().toString().equals("")){
			try{
				consultaPeticionesEfectivoBackend.ejecutarTRN(utils.formateoFecha(getFechaSolicitud()), this.parrilla.getListaDenominaciones());
				this.autorizacionPeticionBean.setListaAutorizacionPeticionEfectivoBean(consultaPeticionesEfectivoBackend.getListaPeticiones());
				this.autorizacionPeticionBean.setListaExistenciaDenominacionBean(consultaPeticionesEfectivoBackend.getListaDenominaciones());
				
				obtenerImportes();
				obtenerDatos();
				
				calculaSaldoAnterior();
				calculaSaldoActual();
				calculaTotalPedido();
				calculaTotalAutorizado();
				
				this.parrilla.setListaDenominaciones(this.autorizacionPeticionBean.getListaDenominaciones());
				
				if(consultaPeticionesEfectivoBackend.getListaPeticiones().size() > 0){
					setElementosVisibles(true);
					setBotonBloqueado(false);
					setElementosBloqueados(true);
					obtenListaBilletes();
					setMostrarMensaje(false);
					
					RequestContext.getCurrentInstance().execute("resizeParrillaColumns()");
					
				}else{
					setElementosVisibles(false);
				}	
			}catch (ControlableException ce){
				if(ce.getRtncod() != 1){
					setMensajeError(ce.getMensajeDetalle());
					RequestContext.getCurrentInstance().execute("PF('dlgError').show();");
				}
			}catch (ParseException pe){
				setMensajeError("La fecha no es valida.");
				RequestContext.getCurrentInstance().execute("PF('dlgError').show();");
			}
		}
	}
    
    /**
 	 * Método que obtiene los importes pedidos y autorizados de la parrilla en respuesta del WS
 	 */
    public void obtenerImportes(){
    	for(AutorizacionPeticionEfectivoBean peticionBean : this.autorizacionPeticionBean.getListaAutorizacionPeticionEfectivoBean()){
			for(int i = 0; i < this.autorizacionPeticionBean.getListaDenominaciones().size(); i++){
				if(peticionBean.getCodigoFacial().equals(this.autorizacionPeticionBean.getListaDenominaciones().get(i).getValorFacial().trim())
						&& peticionBean.getCodigoDestino().equals(this.autorizacionPeticionBean.getListaDenominaciones().get(i).getOrigen().trim())){
					
					this.autorizacionPeticionBean.getListaDenominaciones().get(i).setImportePedido(peticionBean.getImportePedido());
					this.autorizacionPeticionBean.getListaDenominaciones().get(i).setImporteAutorizado(peticionBean.getImporteAutorizado());
											
					break;
				}
			}
		}
    }
    
    /**
 	 * Método que obtiene las existencias y diferencias de la lista en respuesta del WS
 	 */
    public void obtenerDatos(){
    	for(ExistenciaDenominacionBean existenciaBean : this.autorizacionPeticionBean.getListaExistenciaDenominacionBean()){
			for (int i = 0; i < this.autorizacionPeticionBean.getListaDenominaciones().size(); i++){
				
				if(existenciaBean.getValorFacial().equals(this.autorizacionPeticionBean.getListaDenominaciones().get(i).getValorFacial())
						&& existenciaBean.getOrigen().equals(this.autorizacionPeticionBean.getListaDenominaciones().get(i).getOrigen())){
					
					if(this.autorizacionPeticionBean.getListaDenominaciones().get(i).getExistencias() == null){
						this.autorizacionPeticionBean.getListaDenominaciones().get(i).setExistencias(existenciaBean.getExistencias());
					}
					
					if(this.autorizacionPeticionBean.getListaDenominaciones().get(i).getExistencias() != null
							&& this.autorizacionPeticionBean.getListaDenominaciones().get(i).getImportePedido() == null
							&& this.autorizacionPeticionBean.getListaDenominaciones().get(i).getImporteAutorizado() == null){
						this.autorizacionPeticionBean.getListaDenominaciones().get(i).setDiferencia(
								calculaDiferencia(
									this.autorizacionPeticionBean.getListaDenominaciones().get(i).getExistencias(), 
									new BigDecimal(0.00)));
						
						break;
					}else if(this.autorizacionPeticionBean.getListaDenominaciones().get(i).getExistencias() != null
							&& this.autorizacionPeticionBean.getListaDenominaciones().get(i).getImportePedido() != null
							&& this.autorizacionPeticionBean.getListaDenominaciones().get(i).getImporteAutorizado() != null){
						this.autorizacionPeticionBean.getListaDenominaciones().get(i).setDiferencia(
							calculaDiferencia(
								this.autorizacionPeticionBean.getListaDenominaciones().get(i).getExistencias(), 
								this.autorizacionPeticionBean.getListaDenominaciones().get(i).getImporteAutorizado()));
						
						break;
					}else if(this.autorizacionPeticionBean.getListaDenominaciones().get(i).getExistencias() == null
							&& this.autorizacionPeticionBean.getListaDenominaciones().get(i).getImportePedido() == null
							&& this.autorizacionPeticionBean.getListaDenominaciones().get(i).getImporteAutorizado() != null){
						this.autorizacionPeticionBean.getListaDenominaciones().get(i).setDiferencia(
								calculaDiferencia(
								new BigDecimal(0.00), 
								this.autorizacionPeticionBean.getListaDenominaciones().get(i).getImporteAutorizado()));
						
						break;
					}else if(this.autorizacionPeticionBean.getListaDenominaciones().get(i).getExistencias() != null
							&& this.autorizacionPeticionBean.getListaDenominaciones().get(i).getImportePedido() == null
							&& this.autorizacionPeticionBean.getListaDenominaciones().get(i).getImporteAutorizado() != null){
						this.autorizacionPeticionBean.getListaDenominaciones().get(i).setDiferencia(
								calculaDiferencia(
										this.autorizacionPeticionBean.getListaDenominaciones().get(i).getExistencias(),
								this.autorizacionPeticionBean.getListaDenominaciones().get(i).getImporteAutorizado()));
						
						break;
						
					}
				}	
			}
		}
    	
    	for(int i = 0; i < this.autorizacionPeticionBean.getListaDenominaciones().size(); i++){
			if(this.autorizacionPeticionBean.getListaDenominaciones().get(i).getExistencias() == null
					&& this.autorizacionPeticionBean.getListaDenominaciones().get(i).getImporteAutorizado() != null
					&& this.autorizacionPeticionBean.getListaDenominaciones().get(i).getImportePedido() != null){
					this.autorizacionPeticionBean.getListaDenominaciones().get(i).setExistencias(new BigDecimal(0.00));
				this.autorizacionPeticionBean.getListaDenominaciones().get(i).setDiferencia(
						
					calculaDiferencia(
							this.autorizacionPeticionBean.getListaDenominaciones().get(i).getExistencias(), 
							this.autorizacionPeticionBean.getListaDenominaciones().get(i).getImporteAutorizado()));
				
			}else if(this.autorizacionPeticionBean.getListaDenominaciones().get(i).getExistencias() == null
					&& this.autorizacionPeticionBean.getListaDenominaciones().get(i).getImportePedido() == null
					&& this.autorizacionPeticionBean.getListaDenominaciones().get(i).getImporteAutorizado() == null
					&& this.autorizacionPeticionBean.getListaDenominaciones().get(i).getDiferencia() == null){
				
				this.autorizacionPeticionBean.getListaDenominaciones().get(i).setExistencias(new BigDecimal(0.00));
				this.autorizacionPeticionBean.getListaDenominaciones().get(i).setDiferencia(new BigDecimal(0.00));
				
			}
		}
    }
    
    /**
	 * Método que calcula el saldo anterior
	 */
    public void calculaSaldoAnterior(){
    	this.autorizacionPeticionBean.setSaldoAnterior(new BigDecimal(0.00));
    	BigDecimal saldoAnterior = new BigDecimal(0.00);
    	for(ExistenciaDenominacionBean existenciaBean : this.autorizacionPeticionBean.getListaDenominaciones()){
    		if(existenciaBean.getExistencias() != null){
    			saldoAnterior = saldoAnterior.add(existenciaBean.getExistencias());
    		}
    	}
    	this.autorizacionPeticionBean.setSaldoAnterior(saldoAnterior);
    }
    
    /**
	 * Método que calcula el saldo actual
	 */
    public void calculaSaldoActual(){
    	BigDecimal saldoActual = new BigDecimal(0.00);
    	for(ExistenciaDenominacionBean existenciaBean : this.autorizacionPeticionBean.getListaDenominaciones()){
    		if(existenciaBean.getDiferencia() != null){
    			saldoActual = saldoActual.add(existenciaBean.getDiferencia());
    		}
    	}
    	this.autorizacionPeticionBean.setSaldoActual(saldoActual);
    }
    
    /**
	 * Método que calcula el importe total pedido
	 */
    public void calculaTotalPedido(){
    	BigDecimal totalPedido = new BigDecimal(0.00);
    	for(ExistenciaDenominacionBean existenciaBean : this.autorizacionPeticionBean.getListaDenominaciones()){
    		if(existenciaBean.getImportePedido() != null){
    			totalPedido = totalPedido.add(existenciaBean.getImportePedido());
    		}
    	}
    	this.autorizacionPeticionBean.setTotalPedido(totalPedido);
    }
    
    /**
	 * Método que calcula el importe total autorizado
	 */
    public void calculaTotalAutorizado(){
    	BigDecimal totalAutorizado = new BigDecimal(0.00);
    	for(ExistenciaDenominacionBean existenciaBean : this.autorizacionPeticionBean.getListaDenominaciones()){
    		if(existenciaBean.getImporteAutorizado() != null){
    			totalAutorizado = totalAutorizado.add(existenciaBean.getImporteAutorizado());
    		}
    	}
    	this.autorizacionPeticionBean.setTotalAutorizado(totalAutorizado);
    }
    
    /**
	 * Método que calcula la diferencia 
	 * @param existencia
	 * @param importeAutorizado
	 * @return Diferencia calculada
	 */
    public BigDecimal calculaDiferencia(BigDecimal existencia, BigDecimal importeAutorizado){
    	BigDecimal diferencia = new BigDecimal(0.00);
    	diferencia = existencia.subtract(importeAutorizado);
    	return diferencia;
    }
    
    private Boolean verificaDatosVacios(){
    	
    	Boolean isValido = false;
    	
    	if(this.autorizacionPeticionBeanImporteDe.getImporteACambiar() != null
    			&& this.autorizacionPeticionBeanImporteDe.getImporteDe() != null
    			&& this.autorizacionPeticionBeanImporteDe.getTipoMoneda() != null
    			&& this.autorizacionPeticionBeanImporteDe.getExistencia() != null
    			&& this.autorizacionPeticionBeanImporteDe.getOrigen() != null
    			&& this.autorizacionPeticionBeanImporteA.getImporteA() != null
    			&& this.autorizacionPeticionBeanImporteA.getTipoMoneda() != null
    			&& this.autorizacionPeticionBeanImporteA.getExistencia() != null
    			&& this.autorizacionPeticionBeanImporteA.getOrigen() != null){
	    	
	    	if(this.autorizacionPeticionBeanImporteDe.getImporteACambiar().compareTo(BigDecimal.ZERO) != 0
	    			&& this.autorizacionPeticionBeanImporteDe.getImporteDe().compareTo(BigDecimal.ZERO) != 0
	    			&& !this.autorizacionPeticionBeanImporteDe.getTipoMoneda().isEmpty()
	    			&& !this.autorizacionPeticionBeanImporteDe.getExistencia().isEmpty()
	    			&& !this.autorizacionPeticionBeanImporteDe.getOrigen().isEmpty()
	    			&& this.autorizacionPeticionBeanImporteA.getImporteA().compareTo(BigDecimal.ZERO) != 0
	    			&& !this.autorizacionPeticionBeanImporteA.getTipoMoneda().isEmpty()
		    		&& !this.autorizacionPeticionBeanImporteA.getExistencia().isEmpty()
		    		&& !this.autorizacionPeticionBeanImporteA.getOrigen().isEmpty()){
	    		isValido = true;
	    	}
    	}
    	return isValido;
    }
    
    /**
     * Método que obtiene el valor facial en funcion al valor del billete, al origen y al tipo de moneda
     * @param valor
     * @param origen
     * @param tipoMoneda
     * @return Código valor facial
     * @throws Exception 
     */    
    private String obtenerValorFacial(BigDecimal valor, String origen, String tipoMoneda) throws Exception{
    	String valorFacial = "";
    	
    	for(ExistenciaDenominacionBean existenciaBean : this.autorizacionPeticionBean.getListaBilletes()){
			if(existenciaBean.getValor().equals(valor)
					&& existenciaBean.getOrigen().equals(origen)
					&& existenciaBean.getMoneda() == tipoMoneda.equals("M")){
				valorFacial = existenciaBean.getValorFacial();
				setMostrarMensaje(false);
				break;
			}
		}
    	
    	if(valorFacial.isEmpty()){
    		throw new Exception("Por favor verifica los datos ingresados.");
    	}
    	return valorFacial;
    }
    
    /**
     * Método que realiza la simulación del intercambio de denominaciones,
     * validando que los campos no esten vacios y que la denominacin origen y la denominacón
     * destino sean multiplos del importe a cambiar. 
     */    
    public void realizaSimulacion(){
    	
    	if(validaMultiplos() && verificaDatosVacios()){
	    	try{
    			this.autorizacionPeticionBeanImporteDe.setCodigoFacial(obtenerValorFacial(
		    			this.autorizacionPeticionBeanImporteDe.getImporteDe(),
		    			this.autorizacionPeticionBeanImporteDe.getOrigen(),
		    			this.autorizacionPeticionBeanImporteDe.getTipoMoneda()));

	    		this.autorizacionPeticionBeanImporteA.setCodigoFacial(obtenerValorFacial(
	    			this.autorizacionPeticionBeanImporteA.getImporteA(), 
	    			this.autorizacionPeticionBeanImporteA.getOrigen(),
	    			this.autorizacionPeticionBeanImporteA.getTipoMoneda()));
	    		
	    		agregaCambioDenominacion(getAutorizacionPeticionBeanImporteDe().getImporteACambiar(),
						getAutorizacionPeticionBeanImporteDe().getOrigen(),
						getAutorizacionPeticionBeanImporteA().getOrigen(),
						getAutorizacionPeticionBeanImporteDe().getCodigoFacial(),
						getAutorizacionPeticionBeanImporteA().getCodigoFacial());
		    		
		    	/*for(ExistenciaDenominacionBean existenciasBean : this.autorizacionPeticionBean.getListaDenominaciones()){
		        	if(existenciasBean.getImporteAutorizado() != null){
		        		existenciasBean.setImporteAutorizado(null);
		        	}
	        	}*/
    			
        		final List<ExistenciaDenominacionBean> listaSimulacion = simularAutorizacionPeticionBackend.ejecutarTRN(utils.formateoFecha(getFechaSolicitud()), this.autorizacionPeticionBean.getListaAutorizacionCambiosDenominacion());
        		
        		for(ExistenciaDenominacionBean existenciaDenominacionBean : listaSimulacion){
        			for(ExistenciaDenominacionBean existenciaDenominacion : this.autorizacionPeticionBean.getListaDenominaciones()){
            			if(existenciaDenominacion.getValorFacial().equals(existenciaDenominacionBean.getValorFacial())
            					&& existenciaDenominacion.getOrigen().equals(existenciaDenominacionBean.getOrigen())){
            				existenciaDenominacion.setImporteAutorizado(existenciaDenominacionBean.getImporteAutorizado());
            				
            				if(existenciaDenominacion.getImportePedido() == null){
            					existenciaDenominacion.setImportePedido(existenciaDenominacionBean.getImportePedido());
            				}
            				
            				existenciaDenominacion.setDiferencia(calculaDiferencia(existenciaDenominacion.getExistencias(), existenciaDenominacion.getImporteAutorizado()));
            				
            				break;
            			}
            		}
        		}
        		
        		obtenerDatos();
        		calculaTotalAutorizado();
        		calculaSaldoActual();
        		
        		if(autorizacionPeticionBean.getListaAutorizacionPeticionEfectivoBean().size() == 0){
					setBotonAutorizar(true);
				}else{
					setBotonAutorizar(false);
				}
        		
        		this.parrilla.setListaDenominaciones(this.autorizacionPeticionBean.getListaDenominaciones());
        		setMostrarMensaje(false);
        		
        		this.autorizacionPeticionBeanImporteA = new AutorizacionPeticionEfectivoBean();
        		this.autorizacionPeticionBeanImporteDe = new AutorizacionPeticionEfectivoBean();
        		
	    	}catch (ParseException pe){
	    		setMensajeError("La fecha no es valida.");
				RequestContext.getCurrentInstance().execute("PF('dlgError').show();");
	    	}catch (ControlableException ce) {
	    		if(ce.getRtncod() != 1 && ce.getRtncod() != 7){
	    			setMensajeError(ce.getMensajeDetalle());
					RequestContext.getCurrentInstance().execute("PF('dlgError').show();");
	    		}
			}catch(Exception e){
				setMensajeError(e.getMessage());
				RequestContext.getCurrentInstance().execute("PF('dlgError').show();");
    		}
    	}else{
    		FacesContext.getCurrentInstance() .addMessage( "errorSimulacion", new FacesMessage(FacesMessage.SEVERITY_WARN,
					"","Por favor verifica los datos ingresados."));
    		setMostrarMensaje(true);
    	}
    }
    
    /**
     * Método que ingresa a una lista las denominaciones origen y destino para realizar la simulación del
     * intercambio y la autorización de la petición de efectivo.
     * 
     * @param importeACambiar
     * @param origenDe
     * @param origenA
     * @param valorFacialDe
     * @param valorFacialA
     * 
     */
    public void agregaCambioDenominacion(BigDecimal importeACambiar, String origenDe, String origenA, String valorFacialDe, String valorFacialA){
    	
    	CambioDenominacionBean cambioDenominacionBean = new CambioDenominacionBean();
    	
    	cambioDenominacionBean.setImporteACambiar(importeACambiar);
    	cambioDenominacionBean.setOrigenDe(origenDe);
    	cambioDenominacionBean.setOrigenA(origenA);
    	cambioDenominacionBean.setValorFacialDe(valorFacialDe);
    	cambioDenominacionBean.setValorFacialA(valorFacialA);
    		
    	this.autorizacionPeticionBean.getListaAutorizacionCambiosDenominacion().add(cambioDenominacionBean);
    }
    
    /**
     * Método que valida los multiplos de la denominación origen y la denominación destino
     * contra el importe a cambiar.
     */
    private Boolean validaMultiplos(){
    	
    	Boolean isValido = false;
    	
    	if(this.autorizacionPeticionBeanImporteDe.getImporteACambiar() != null
    		&& this.autorizacionPeticionBeanImporteDe.getImporteDe() != null
    		&& this.autorizacionPeticionBeanImporteA.getImporteA() != null){
    		
    		if(this.autorizacionPeticionBeanImporteDe.getImporteACambiar().compareTo(BigDecimal.ZERO) != 0
    				&& this.autorizacionPeticionBeanImporteDe.getImporteDe().compareTo(BigDecimal.ZERO) != 0
    				&& this.autorizacionPeticionBeanImporteA.getImporteA().compareTo(BigDecimal.ZERO) != 0){
    			
    			if(this.autorizacionPeticionBeanImporteDe.getImporteACambiar().remainder(this.autorizacionPeticionBeanImporteDe.getImporteDe()).compareTo(BigDecimal.ZERO) == 0
    					&& this.autorizacionPeticionBeanImporteDe.getImporteACambiar().remainder(this.autorizacionPeticionBeanImporteA.getImporteA()).compareTo(BigDecimal.ZERO) == 0){
    				setMostrarMensaje(false);
        			isValido = true;
            	}else{
            		FacesContext.getCurrentInstance() .addMessage( "errorSimulacion", new FacesMessage(FacesMessage.SEVERITY_ERROR,
        					"", "Los importes seleccionados no son multiplos del importe a cambiar"));
            		setMostrarMensaje(true);
            	}
    		}else{
    			FacesContext.getCurrentInstance() .addMessage( "errorSimulacion", new FacesMessage(FacesMessage.SEVERITY_ERROR,
    					"", "Los importes no pueden ser cero."));
    			setMostrarMensaje(true);
    		}
    	}
    	
    	return isValido;
    }
    
    /**
     * Método que valida los valores faciales de la parrilla y quita los duplicados.  
     */    
    public List<BigDecimal> obtenListaBilletes(){
    	
    	List<BigDecimal> listaBilletes = new ArrayList<BigDecimal>();
    	Set<BigDecimal> existencias = new LinkedHashSet<BigDecimal>();
    	
    	for(ExistenciaDenominacionBean valorFacial : this.autorizacionPeticionBean.getListaBilletes()){
        	existencias.add(valorFacial.getValor());
    	}
    	
    	listaBilletes.clear();
    	listaBilletes.addAll(existencias);
    	return listaBilletes;	
    }
    
    /**
     * Método que inicaliza los valores de la parrilla si el campo de pedido autorizado esta en cero,
     * coloca el valor del importe pedido en el importe autorizado.
     * 
     */
    public void inicializarIntercambio(){
    	
    	for(ExistenciaDenominacionBean existenciaBean : this.autorizacionPeticionBean.getListaDenominaciones()){
    		if(existenciaBean.getImportePedido() != null){
    			existenciaBean.setImporteAutorizado(existenciaBean.getImportePedido());
    		}
    	}
    	
    	obtenerDatos();
    	calculaTotalAutorizado();
    	calculaSaldoActual();
    	
		setElementosBloqueados(false);
		setBotonVisible(false);
    }
    
    /**
     * Método que autoriza la petición de efectivo validando que al menos se haya realizado
     * una simulación.
     * 
     *  @return ruta de resumen.
     */
    public String autorizaPeticionEfectivo(){
    	int resultado = 0;
    	String ruta = "";
		if(!this.autorizacionPeticionBean.getListaAutorizacionCambiosDenominacion().isEmpty()){
	    
	    	try{
	    		resultado = autorizacionPeticionEfectivoBackend.ejecutarTRN(
	    			utils.formateoFecha(getFechaSolicitud()), 
	    			this.autorizacionPeticionBean.getTotalPedido(), 
	    			this.autorizacionPeticionBean.getTotalAutorizado(), 
	    			this.autorizacionPeticionBean.getListaAutorizacionCambiosDenominacion());
	    		
	    		if(resultado == 1){
	    			this.obtieneFlash().put(ParametrosFlashEnum.AUTORIZACION_PETICION_BEAN.getParamFlash(), this.autorizacionPeticionBean);
	            	
	            	ruta = NavegacionEnum.RESUMEN_AUTORIZACION_PETICION.getRuta();
	    		}
	    		
	    	}catch (ControlableException ce) {
	    		FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "¡Atención!",
						ce.getMensajeDetalle());
				RequestContext.getCurrentInstance()
					.showMessageInDialog(message);
			}catch (ParseException pe){
				FacesMessage message = new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "¡Atención!",
							"Fecha no valida.");
					RequestContext.getCurrentInstance()
						.showMessageInDialog(message);
			}
		}else{
			RequestContext.getCurrentInstance().execute("PF('dlgConfirmarAutorizacion').hide();");
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "¡Atención!",
						"Se debe realizar al menos una simulación");
				RequestContext.getCurrentInstance()
					.showMessageInDialog(message);
		}
		return ruta;
    }
    
	/**
	 * Método que limpia la parrilla
	 */
	public void limpiarPantalla(){
		setMostrarMensaje(false);
		setElementosVisibles(false);
		setElementosBloqueados(true);
		setBotonBloqueado(true);
		setFechaSolicitud(null);
		setBotonVisible(true);
		this.autorizacionPeticionBean.setSaldoAnterior(null);
		this.autorizacionPeticionBean.setSaldoActual(null);
		this.autorizacionPeticionBean.setTotalPedido(null);
		this.autorizacionPeticionBean.setTotalAutorizado(null);
		this.autorizacionPeticionBean.getListaDenominaciones().clear();
		this.autorizacionPeticionBeanImporteDe = new AutorizacionPeticionEfectivoBean();
		this.autorizacionPeticionBeanImporteA = new AutorizacionPeticionEfectivoBean();
		this.autorizacionPeticionBean.getListaDenominaciones().clear();
		this.autorizacionPeticionBean.getListaAutorizacionCambiosDenominacion().clear();
		this.autorizacionPeticionBean.getListaAutorizacionPeticionEfectivoBean().clear();
		this.autorizacionPeticionBean.getListaExistenciaDenominacionBean().clear();
		this.parrilla.limpiarParrilla();
	}
}