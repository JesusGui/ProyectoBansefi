package mx.babel.bansefi.banksystem.transacciones.controllers;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

import mx.babel.arq.catalogo.beans.CatalogoBean;
import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.sesion.utils.ContextoUtils;
import mx.babel.bansefi.banksystem.base.backends.ConsultaCuentaBackend;
import mx.babel.bansefi.banksystem.base.backends.ConsultaRelacionPersonaCuentaBackEnd;
import mx.babel.bansefi.banksystem.base.backends.anotaciones.ConsultaListaAnotacionesBackEnd;
import mx.babel.bansefi.banksystem.base.backends.busquedas.BusquedaNivelCuentaBackEnd;
import mx.babel.bansefi.banksystem.base.backends.consultatitular.ConsultaTitularBackend;
import mx.babel.bansefi.banksystem.base.backends.consultatitular.ConsultaTitularRes;
import mx.babel.bansefi.banksystem.base.beans.domain.AnotacionBean;
import mx.babel.bansefi.banksystem.base.beans.domain.CuentaBean;
import mx.babel.bansefi.banksystem.base.beans.domain.relacioncuenta.RelacionadoBean;
import mx.babel.bansefi.banksystem.base.constants.ConstantesFuncionales;
import mx.babel.bansefi.banksystem.base.enums.BusquedaEnum;
import mx.babel.bansefi.banksystem.base.enums.CatalogoEnum;
import mx.babel.bansefi.banksystem.base.enums.NavegacionEnum;
import mx.babel.bansefi.banksystem.base.enums.ParametrosFlashEnum;
import mx.babel.bansefi.banksystem.base.utils.CatalogoUtils;
import mx.babel.bansefi.banksystem.base.utils.FechaUtils;
import mx.babel.bansefi.banksystem.base.utils.ManagedBeanStateRecover;
import mx.babel.bansefi.banksystem.base.utils.NumberToLetterConverter;
import mx.babel.bansefi.banksystem.base.utils.PdfUtils;
import mx.babel.bansefi.banksystem.transacciones.backends.RetiroBackEnd;
import mx.babel.bansefi.banksystem.transacciones.beans.RetiroBean;
import mx.babel.bansefi.banksystem.transacciones.beans.RetiroReq;
import mx.babel.bansefi.banksystem.transacciones.beans.RetiroRes;
import mx.babel.bansefi.banksystem.transacciones.enums.ConceptoRetiroEnum;

import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Controller para el flujo de retiro.
 * @author alejandro.perez
 *
 */


@ManagedBean(name = "retiroController")
@Component
@Scope(value="view")
@ViewScoped
public class RetiroController implements Serializable{

	private static final long serialVersionUID = -2561487090047913349L;

    private static final String DATE_FORMAT = "dd/MM/yyyy";
    private static final String  CTE_ESPACIO = " ";
    private static final double CTE_MOSTRAR_UNIDADES_MILLON = 1000000;
    private static final String  CTE_CERO = "0";
	private static final int NUM_ANOTACIONES = 6;
    
	@ManagedProperty(value = "retiro")
	private RetiroBean retiro;
	

	private Date fechaActual = new Date();
	private boolean interveniente;
	private boolean todoOk;
	private boolean muestraTitular;
    private boolean muestraTitularError;
    private boolean saldoError;
    private boolean excesoSaldo;
    private boolean cuentaBloqueada;
    private boolean cuentaIncorrecta;
    private boolean cuentaInvalida;
    private boolean cuentaNoVista;
    private boolean cuentaInactiva;
    private String plaza;
    private String digito;
    private String terminal;
    private String movimiento;
    private String fecha;
    private String hora;
    private String sucursal;
    private CuentaBean cuentaBean;
    private String fechaMinima = ConstantesFuncionales.MIN_FECHA_INICIO;
    private int anotacionesVisibles;
                   
    @Autowired
    private ContextoUtils contextoUtils; 
    
    @Autowired
    private ConsultaCuentaBackend consultaCuentaBackend;
    
    @Autowired
    private ManagedBeanStateRecover managedBeanStateRecover;
    
    @Autowired
    private ConsultaTitularBackend consultaTitular;
    
    @Autowired
    private RetiroBackEnd retiroBackEnd;
    
	@Autowired
	private ConsultaRelacionPersonaCuentaBackEnd consultaRelacionPersonaCuentaBackEnd;
	
	@Autowired
    private PdfUtils pdfUtils;
	
	@Autowired
	CatalogoUtils catalogoUtils;
	
	@Autowired
	ConsultaListaAnotacionesBackEnd consultaAnotacionesCuentaBackEnd;
	
	@Autowired
	BusquedaNivelCuentaBackEnd busquedaNivelCuentaBackEnd;
	
	private String nombreClienteEncontrado;
	
	private RetiroRes retiroRes;
	
	/**
	 *  Super.
	 */
	public RetiroController() {
		super();
	}
	
	/**
	 * Se inicializan las variables de la vista.
	 */
	@PostConstruct
	public void init(){
		
		if(this.obtieneFlash().get(ParametrosFlashEnum.RETIRO.getParamFlash()) != null){
			this.retiro = (RetiroBean) this.obtieneFlash().get(ParametrosFlashEnum.RETIRO.getParamFlash());
			if(this.obtieneFlash().get(ParametrosFlashEnum.OBJETO_ENTRADA_PLANTILLA.getParamFlash()) != null){
				cuentaBean = consultaCuentaBackend.ejecutarTRN(this.retiro.getCuentaRetiro());
				retiroRes = (RetiroRes) this.obtieneFlash().get(ParametrosFlashEnum.OBJETO_ENTRADA_PLANTILLA.getParamFlash());
				//TODO revisar uso de objetos para respuestas y entradas de la TRN
				plaza = retiroRes.getPlaza().replaceAll("\\*", "").trim();
				digito = retiroRes.getDigito();
				terminal = retiroRes.getTerminal();
				movimiento = retiroRes.getMovimiento();
				fecha = retiroRes.getFechaoprcn();
				hora = retiroRes.getHoraoprcn();
				sucursal = retiroRes.getCentro();
				this.generaPlantillaRetiro();
			}
		}else if(this.obtieneFlash().get(ParametrosFlashEnum.NAVEGACION_MANAGED_BEAN.getParamFlash()) != null) {
			this.retiro = (RetiroBean) obtieneFlash().get(ParametrosFlashEnum.RETIRO.getParamFlash()); 
			managedBeanStateRecover.recuperaController(this);
    	}else{
    		this.retiro = new RetiroBean();
            this.retiro.setMostrarUnidadesMillon(false);
            this.retiro.setCuentaPuenteRetiro(false);
            this.retiro.setImprimirSaldoRetiro(true);
            this.retiro.setFechaValorRetiro(new Date());
            this.setInterveniente(false);
            this.setTodoOk(false);
            this.retiro.setOperacionRetiro("010001");
            this.calculaConcepto();
            this.retiro.setFechaValorRetiro(contextoUtils.getFechaContableActual());
            this.setFechaActual(contextoUtils.getFechaContableActual());
            
    		
    	}
    	
		if(this.obtieneFlash().get(ParametrosFlashEnum.NUMERO_CUENTA.getParamFlash()) != null){
			managedBeanStateRecover.recuperaController(this);
			this.retiro.setCuentaRetiro((Long)this.obtieneFlash().get(ParametrosFlashEnum.NUMERO_CUENTA.getParamFlash()));
			this.muestraTitular = true;
			this.retiro.setUsuarioRetiro((String)this.obtieneFlash().get(ParametrosFlashEnum.TITULAR_CUENTA.getParamFlash()));
			this.retiro.setNivelCuenta((String)this.obtieneFlash().get(ParametrosFlashEnum.NIVEL_CUENTA.getParamFlash()));
			this.retiro.setAnotaciones(consultaAnotacionesCuentaBackEnd.ejecutarTRN(this.retiro.getCuentaRetiro(),true));
			this.establecerAnotacionesVisibles();
						
		}
				
	}
	
	/**
	 * Obtiene nombre de flujo	
	 * @return 
	 */
	public String getNombreFlujo() {
		return "Operación de retiro";
	}
	
	
	/**
	 * Instancia el flash.
	 * @return - Devuelve una instancia flash.
	 */
	public Flash obtieneFlash(){
		return FacesContext.getCurrentInstance()
				.getExternalContext().getFlash();
	}
	
	
	/**
	 * Al cambiar la operación rellena el campo de concepto con el mismo texto.
	 */
	public void calculaConcepto() {
		String codigoRetiro = retiro.getOperacionRetiro();
		for (ConceptoRetiroEnum concepto : ConceptoRetiroEnum.values()) {
			if ( codigoRetiro.equals(concepto.getCodigo())) {
				retiro.setConceptoRetiro(concepto.getConcepto());
			}
		}
		this.cambiarPuente();
		
	}
	
	/**
	 * Muestra si se pide o no una cuenta puente.
	 * @return Si o no.
	 */
	public String isCuentaPuente() {
		
		if(this.retiro.isCuentaPuenteRetiro()) {
			return "Sí";
		} else {
			return "No";
		}
	}
	
	/**
	 * Comprueba si el importe es superior o igual a 1M y muestra el campo
	 *  unidades de millón si es cierto.
	 */
	public void mostrarUnidadesMillon() {
		if(this.retiro.getImporteRetiro() != null
				&& this.retiro.getImporteRetiro().doubleValue() >= RetiroController.CTE_MOSTRAR_UNIDADES_MILLON){
			this.retiro.setMostrarUnidadesMillon(true);
		}else{
			this.retiro.setUnidadesMillon(null);
			this.retiro.setMostrarUnidadesMillon(false);
		}
		
	}
	
	/**
	 * cambia el puente dependiendo de la opción elegida
	 */
	public void cambiarPuente(){
		if("990001".equals(this.retiro.getOperacionRetiro())){
			this.retiro.setCuentaPuenteRetiro(true);
			
		}else{
			this.retiro.setCuentaPuenteRetiro(false);
		}
	}
	
	public void cambiarOperacion(){
		if(this.retiro.isCuentaPuenteRetiro()){
			 this.retiro.setOperacionRetiro("990001");
		}else{
			this.retiro.setOperacionRetiro("010001");
		}
		this.calculaConcepto();
		
	}
	
	
	/**
	 * Método para validar puente haya elegido la opción correcta
	 * @return comrpobrarPuente
	 */
	public boolean comprobarPuente(){
		if("990001".equals(this.retiro.getOperacionRetiro()) && !this.retiro.isCuentaPuenteRetiro()){
			addMessage(FacesMessage.SEVERITY_ERROR, 
					"operacionRetiro","ERROR",	
					"Operación y Cuenta puente no concuerdan");
			return false;
			
		}
		else if("010001".equals(this.retiro.getOperacionRetiro()) && this.retiro.isCuentaPuenteRetiro()){
			addMessage(FacesMessage.SEVERITY_ERROR, 
					"operacionRetiro","ERROR",	
					"Operación y Cuenta puente no concuerdan");
			return false;
		}else{
			return true;
		}
		
	}
	
	/**
	 * Decide que modal mostrar, si el de aviso de 
	 * que no hay intervenientes en una cuenta
	 * o directamente el de confirmar la operación.
	 */
	public void submitForm() {
		if (this.comprobarPuente()){
			if(this.muestraTitular){
				boolean intervenientes = this.comprobarInternvenientesCuenta();
				if (intervenientes == true) {
					this.setInterveniente(false);
					this.setTodoOk(true);
					
				} else {
					this.setInterveniente(true);
					this.setTodoOk(false);
				}
			}else{
				this.setTodoOk(false);
			}
		}else{
			this.setInterveniente(false);
			this.setTodoOk(false);
		}

	}
	
	public void mostrarConfirmacion() {
		this.setTodoOk(true);
	}
	
	/**
	 * Comprueba que el número de cuenta introducido 
	 * corresponde a un cliente y en caso afirmativo
	 * muestra el nombre del cliente.
	 */
	public void comprobarCuentaRetiro() {
        
        this.setMuestraTitular(false);
        this.setMuestraTitularError(false);
        ConsultaTitularRes respuesta = this.consultaTitular
                .ejecutarWS(this.retiro.getCuentaRetiro().toString());
        if (respuesta != null && "0".equals(respuesta.getEstatus())) {
            this.muestraTitular = true;
            this.retiro.setUsuarioRetiro(respuesta.getNombre());
            this.retiro.setNivelCuenta(respuesta.getNivelCuenta());
            this.retiro.setAnotaciones(consultaAnotacionesCuentaBackEnd.ejecutarTRN(this.retiro.getCuentaRetiro(),true));
            this.establecerAnotacionesVisibles();
        } else {
        	this.muestraTitularError = true;
        }
    }
	
	/**
	 * Comprueba si el cliente introducido en la identificación interviene
	 *  con el número de cuenta introducido.
	 * @return - true si todo ha ido bien
	 */
	public boolean comprobarInternvenientesCuenta() {
		List<RelacionadoBean> relacionado = null; 
		Boolean existeRelacion = false;
		try{
			relacionado = consultaRelacionPersonaCuentaBackEnd.ejecutarTRN(this.retiro.getCuentaRetiro(), false);
		}catch (NumberFormatException nfe){
			throw new ControlableException("No se puede realizar la consulta", "El formato de alguno de los parámetros es erroneo");
		}
				
		for (RelacionadoBean relacionadoBean : relacionado) {
			if (relacionadoBean.getPersona().getNumIdentificacion().equals(this.retiro.getNumeroIdentificacionClienteRetiro().toUpperCase()) && 
					relacionadoBean.getPersona().getTipoIdentificacion().equals(this.retiro.getIdentificacionClienteRetiro())){
				existeRelacion = true;
			}
		}
		
		if(existeRelacion){
			return true;
		} else {
			return false;
		}
		
	}
	
	/**
	 * Realiza la operación de retiro con los campos introducidos.
	 * @return - true si el retiro se ha realizado con éxito.
	 */
	public boolean realizarRetiro() {

		RetiroReq retiroReq = new RetiroReq();
		retiroRes = new RetiroRes();
		try {
			retiroReq.setCodope(this.retiro.getOperacionRetiro());
			retiroReq.setCuenta(String.valueOf(this.retiro.getCuentaRetiro()));
			retiroReq.setCveidof(this.retiro.getIdentificacionClienteRetiro());
			retiroReq.setEntidad(this.contextoUtils.getEntidad());
			retiroReq.setFecvalor(
					FechaUtils.formatFecha(this.retiro.getFechaValorRetiro(), 
							RetiroController.getDateFormat()));
			retiroReq.setImporte(this.retiro.getImporteRetiro());
			retiroReq.setIpheader(this.contextoUtils.getIp());
			retiroReq.setPassheader(this.contextoUtils.getPwd());
			retiroReq.setIfe(
					this.retiro.getNumeroIdentificacionClienteRetiro());
			retiroReq.setConcepto(this.retiro.getConceptoRetiro());
			retiroReq.setUsuarioId(this.contextoUtils.getId());
		} catch (NullPointerException npe){
	        throw new ControlableException(
	        		"No se puede realizar el retiro", npe);
	    } catch (NumberFormatException nfe){
	        throw new ControlableException(
	        		"No se puede realizar el retiro", nfe);
	    }
		
		try{
			retiroRes = retiroBackEnd.realizaRetiro(retiroReq);
		} catch(NoControlableException nce) {
			return false;
		}
		
		if("0".equals(retiroRes.getEstatus().trim())){
			plaza = retiroRes.getPlaza().replaceAll("\\*", "").trim();
			digito = retiroRes.getDigito();
			terminal = retiroRes.getTerminal();
			movimiento = retiroRes.getMovimiento();
			fecha = retiroRes.getFechaoprcn();
			hora = retiroRes.getHoraoprcn();
			sucursal = retiroRes.getCentro();
			
			return true;
		} else {
			return false;
		}
				
	}
	
	/**
	 * Genera plantilla pdf.
	 */
    public void generaPlantillaRetiro(){
    	Map<String, String> images = new HashMap<String, String>();
        images.put("Logo_bsfi_bn.png", "rutaImagen");
        
        StringBuffer nombrePdf = new StringBuffer(this.contextoUtils.getId());
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("plaza",this.buscarPlaza(this.plaza));
        params.put("oficina", this.sucursal);
        params.put("tipoCuenta", this.cuentaBean.getTipoCuenta());
        params.put("nombreTitular", this.retiro.getUsuarioRetiro());
        params.put("clabe", this.creaClabe(this.contextoUtils.getEntidad(),
                this.plaza, this.retiro.getCuentaRetiro().toString(), this.digito));
        params.put("importeEfectivo", 
        		this.retiro.getImporteRetiro().doubleValue());
        params.put("terminal", this.terminal);
        params.put("movimiento", this.movimiento);
        params.put("fecha", this.fecha.replaceAll("-", ""));
        params.put("hora", this.hora.replaceAll("\\.", ""));
        try{
        	params.put("importeLetra", (NumberToLetterConverter
                    .convertirImporteAImporteEnletra(this.retiro.getImporteRetiro().doubleValue())));
        }catch(final NumberFormatException nfe){
        	params.put("importeLetra", "No es posible realizar la conversión.");
        }
        params.put("total", this.retiro.getImporteRetiro().doubleValue());
        pdfUtils.generaPdf("retiroReporte.jrxml", params, images, null,
        		 nombrePdf.toString(), null);
        
    }
	
	/**
	 * En caso de que la operación de retiro haya sido exitosa, 
	 * pasa a la ventana de detalle de retiro, sino muestra un mensaje de error.
	 * @return - Ruta de siguiente pantalla.
	 */
	public String continuarRetiro() {
		String mensaje = null;
		String retorno = null;			

		boolean retiroRealizado = this.realizarRetiro();
		if(retiroRealizado) {
			//SE REALIZA LA RECLASIFICACIÓN DE LA CUENTA
			busquedaNivelCuentaBackEnd.EjecutarTRN(this.retiro.getCuentaRetiro().toString());
			
			cuentaBean = consultaCuentaBackend.ejecutarTRN(this.retiro.getCuentaRetiro());
			obtieneFlash().put(ParametrosFlashEnum.RETIRO.getParamFlash(), this.retiro);
			obtieneFlash().put(ParametrosFlashEnum.OBJETO_ENTRADA_PLANTILLA.getParamFlash(), retiroRes);
			
		    FacesContext.getCurrentInstance().getExternalContext().getFlash();
			retorno = NavegacionEnum.DETALLE_RETIRO.getRuta();
			
		} else if(this.saldoError) {
			RequestContext.getCurrentInstance().execute("PF('dlgConfirmarRetiro').hide();");
			RequestContext.getCurrentInstance().execute("PF('dlgAvisoNoSaldo').show();");
		} else if(this.cuentaBloqueada){
			RequestContext.getCurrentInstance().execute("PF('dlgConfirmarRetiro').hide();");
			RequestContext.getCurrentInstance().execute("PF('dlgCuentaError').show();");
		}else if(this.cuentaIncorrecta){
			RequestContext.getCurrentInstance().execute("PF('dlgConfirmarRetiro').hide();");
			RequestContext.getCurrentInstance().execute("PF('dlgAvisoCuentaIncorrecta').show();");
		}else if(this.cuentaInvalida){
			RequestContext.getCurrentInstance().execute("PF('dlgConfirmarRetiro').hide();");
			RequestContext.getCurrentInstance().execute("PF('dlgAvisoCuentaInvalida').show();");
		}else if(this.cuentaNoVista){
			RequestContext.getCurrentInstance().execute("PF('dlgConfirmarRetiro').hide();");
			RequestContext.getCurrentInstance().execute("PF('dlgAvisoCuentaNoVista').show();");
		}else if(this.excesoSaldo){
			RequestContext.getCurrentInstance().execute("PF('dlgConfirmarRetiro').hide();");
			RequestContext.getCurrentInstance().execute("PF('dlgAvisoExcesoSaldo').show();");			
		}
		else{
			throw new NoControlableException(
                    "Se ha producido un error al invocar al servicio de realizar retiro",mensaje);
                    
        }
		return retorno;
	}
	
	/**
	 * Vuelve al inicio del flujo.
	 * @return - Ruta pantalla de inicio.
	 */
	public String inicio() {
		return NavegacionEnum.REALIZAR_RETIRO.getRuta();
	}
	
	/**
	 * Va al inicio de la aplicacion
	 * @return - Ruta pantalla de inicio.
	 */
	public String volverInicio() {
		return NavegacionEnum.INICIO.getRuta();
	}

	/**
	 * Función para adicionar alertas globales en la vista.
	 * 
	 * @param severity Severidad de la alerta.
	 * @param title Titulo de la alerta.
	 * @param message Mensaje desplegado en la alerta.
	 */
	private void addMessage(FacesMessage.Severity severity, 
			String component, String title, String message){
		FacesMessage facesMessage =  new FacesMessage(severity,title, message);
		FacesContext.getCurrentInstance().addMessage(component, facesMessage);
	}
	
	/**
	 * Función para crear la clabe de usuario e imprimirla en el pdf.
	 * @param entidad - entidad
	 * @param plaza - plaza
	 * @param cuenta - cuenta
	 * @param digito - dígito
	 * @return - clabe
	 */
	public String creaClabe(String entidad, String plaza, 
								String cuenta, String digito){
		
		cuenta = org.apache.commons.lang.StringUtils.leftPad(cuenta, 10, RetiroController.CTE_CERO);
		
		StringBuffer clabeBuffer = 
								new StringBuffer().append(entidad.substring(1)).append(plaza.trim())
										.append(RetiroController.CTE_ESPACIO)
										.append(RetiroController.CTE_CERO)
										.append(cuenta).append(" ")
										.append(digito);
		return clabeBuffer.toString();
	}


	/**
	 * Getters & Setters.
	 * @return the retiro
	 */
	public RetiroBean getRetiro() {
		return retiro;
	}


	/**
	 * Getters & Setters.
	 * @param retiro the retiro to set
	 */
	public void setRetiro(RetiroBean retiro) {
		this.retiro = retiro;
	}


	/**
	 * Getters & Setters.
	 * @return the fechaActual
	 */
	public Date getFechaActual() {
		return fechaActual;
	}


	/**
	 * Getters & Setters.
	 * @param fechaActual the fechaActual to set
	 */
	public void setFechaActual(Date fechaActual) {
		this.fechaActual = fechaActual;
	}
	
	/**
	 * Getters & Setters.
	 * @return saldoError
	 */
	public boolean isSaldoError(){
		return saldoError;
	}


	/**
	 * Getters & Setters.
	 * @return the interveniente
	 */
	public boolean isInterveniente() {
		return interveniente;
	}


	/**
	 * Getters & Setters.
	 * @param interveniente the interveniente to set
	 */
	public void setInterveniente(boolean interveniente) {
		this.interveniente = interveniente;
	}


	/**
	 * Getters & Setters.
	 * @return the todoOk
	 */
	public boolean isTodoOk() {
		return todoOk;
	}


	/**
	 * Getters & Setters.
	 * @param todoOk the todoOk to set
	 */
	public void setTodoOk(boolean todoOk) {
		this.todoOk = todoOk;
	}


	/**
	 * Getters & Setters.
	 * @return the muestraTitular
	 */
	public boolean isMuestraTitular() {
		return muestraTitular;
	}


	/**
	 * Getters & Setters.
	 * @param muestraTitular the muestraTitular to set
	 */
	public void setMuestraTitular(boolean muestraTitular) {
		this.muestraTitular = muestraTitular;
	}


	/**
	 * Getters & Setters.
	 * @return the muestraTitularError
	 */
	public boolean isMuestraTitularError() {
		return muestraTitularError;
	}


	/**
	 * Getters & Setters.
	 * @param muestraTitularError the muestraTitularError to set
	 */
	public void setMuestraTitularError(boolean muestraTitularError) {
		this.muestraTitularError = muestraTitularError;
	}
	
	/**
	 * Getters & Setters.
	 * @return the saldoError
	 */
	public boolean getSaldoError(){
		return saldoError;
	}
	
	/**
	 * Getters & Setters.
	 * @param saldoError
	 */
	public void setSaldoError(boolean saldoError){
		this.saldoError = saldoError;
	}

	/**
	 * Getters & Setters.
	 * @return cuentaBloqueada
	 */
	public boolean isCuentaBloqueada() {
		return cuentaBloqueada;
	}
	
	/**
	 * Getters & Setters.
	 * @param cuentaBloqueada
	 */
	public void setCuentaBloqueada(boolean cuentaBloqueada) {
		this.cuentaBloqueada = cuentaBloqueada;
	}
	
	public boolean getCuentaIncorrecta(){
		return cuentaIncorrecta;
	}

	
	public void setCuentaIncorrecta(boolean cuentaIncorrecta){
		this.cuentaIncorrecta = cuentaIncorrecta;
	}
	
	public boolean getCuentaInactiva(){
		return cuentaInactiva;
	}
	
	public boolean getExcesoSaldo(){
		return excesoSaldo;
	}
	
	public void setExcesoSaldo(boolean excesoSaldo){
		this.excesoSaldo = excesoSaldo;
	}
	
	public void setCuentaInactiva(boolean cuentaInactiva){
		this.cuentaInactiva = cuentaInactiva;
	}
	
	public boolean getCuentaNoVista(){
		return cuentaNoVista;
	}
	
	public void setCuentaNoVista(boolean cuentaNoVista){
		this.cuentaNoVista = cuentaNoVista;
	}
	/**
	 * Getters & Setters.
	 * @return the contextoUtils
	 */
	public ContextoUtils getContextoUtils() {
		return contextoUtils;
	}


	/**
	 * Getters & Setters.
	 * @param contextoUtils the contextoUtils to set
	 */
	public void setContextoUtils(ContextoUtils contextoUtils) {
		this.contextoUtils = contextoUtils;
	}


	/**
	 * Getters & Setters.
	 * @return the consultaTitular
	 */
	public ConsultaTitularBackend getConsultaTitular() {
		return consultaTitular;
	}


	/**
	 * Getters & Setters.
	 * @param consultaTitular the consultaTitular to set
	 */
	public void setConsultaTitular(ConsultaTitularBackend consultaTitular) {
		this.consultaTitular = consultaTitular;
	}


	/**
	 * Getters & Setters.
	 * @return the operacionRetiro
	 */
	public RetiroBackEnd getOperacionRetiro() {
		return retiroBackEnd;
	}


	/**
	 * Getters & Setters.
	 * @param operacionRetiro the operacionRetiro to set
	 */
	public void setOperacionRetiro(RetiroBackEnd retiroBackEnd) {
		this.retiroBackEnd = retiroBackEnd;
	}


	/**
	 * Getters & Setters.
	 * @return the pdfUtils
	 */
	public PdfUtils getPdfUtils() {
		return pdfUtils;
	}


	/**
	 * Getters & Setters.
	 * @param pdfUtils the pdfUtils to set
	 */
	public void setPdfUtils(PdfUtils pdfUtils) {
		this.pdfUtils = pdfUtils;
	}


	/**
	 * Getters & Setters.
	 * @return the dateFormat
	 */
	public static String getDateFormat() {
		return DATE_FORMAT;
	}
	
	/**
	 * Método para buscar mediante el buscador el número de cuenta
	 * @return 
	 */
	public String buscarPersona(){
		obtieneFlash().put(ParametrosFlashEnum.TIPO_BUSQUEDA.getParamFlash(), BusquedaEnum.CUENTAS.getBusquedaValor());
		managedBeanStateRecover.enviaController(this,NavegacionEnum.REALIZAR_RETIRO );
		return NavegacionEnum.BUSQUEDA.getRuta();
				
	}
	
	/**
	 * Getters & Setters.	
	 * @return nombreClienteEncontrado
	 */
	public String getNombreClienteEncontrado(){
		return nombreClienteEncontrado;
	}
	
	/**
	 * Getters & Setters.
	 * @param nombreClienteEncontrado
	 */
	public void setNombreClienteEncontrado(String nombreClienteEncontrado){
		this.nombreClienteEncontrado = nombreClienteEncontrado;
	}
	
	/**
	 * Getters & Setters.
	 * @return fechaMinima
	 */
	public String getFechaMinima(){
		return fechaMinima;
	}
	
	/**
	 * Getters & Setters.
	 * @param fechaMinima
	 */
	public void setFechaMinima(String fechaMinima){
		this.fechaMinima = fechaMinima;
	}
	
	
	
	public RetiroRes getRetiroRes() {
		return retiroRes;
	}

	public void setRetiroRes(RetiroRes retiroRes) {
		this.retiroRes = retiroRes;
	}

	public String buscarPlaza(String clave){
		String descripcion = clave;
		List<CatalogoBean> catalogo = catalogoUtils.getCatalogo(CatalogoEnum.TP_PLAZA_BANCARIA);
		for(CatalogoBean elemento : catalogo){
			if(elemento.getClaveFila().equals(clave.trim())){
				descripcion = elemento.getDescripcionL();
			}
		}
		return descripcion;
	}
	
	/**
	 * Método que consulta el detalle de la anotacion seleccionada
	 * 
	 * @return La URL a la que redireccionará
	 */
	public String detalleAnotacion(AnotacionBean anotacionBean) {
		managedBeanStateRecover.enviaController(this, NavegacionEnum.REALIZAR_RETIRO);
		obtieneFlash().put(ParametrosFlashEnum.ANOTACION_BEAN.getParamFlash(),anotacionBean);
		return NavegacionEnum.DETALLE_ANOTACIONES.getRuta();
	}
	
	public void establecerAnotacionesVisibles(){
		int numAnotacionesVisibles = 0;
		if(this.retiro.getAnotaciones() != null){
			if(this.retiro.getAnotaciones().size() > NUM_ANOTACIONES){
				numAnotacionesVisibles = RetiroController.NUM_ANOTACIONES;
			}else{
				numAnotacionesVisibles = this.retiro.getAnotaciones().size();
			}
		}
		this.anotacionesVisibles = numAnotacionesVisibles;
	}
	
	public int getAnotacionesVisibles(){
		return anotacionesVisibles;
	}
	
	public void setAnotacionesVisible(final int anotacionesVisibles){
		this.anotacionesVisibles = anotacionesVisibles;
	}
	
	public String getTipoAnotacion(final String clave){
		try{
			return catalogoUtils.getCatalogoBean(CatalogoEnum.TIPO_ANOTACION,
					clave).getDescripcionL();
			
		}catch(final ControlableException ce){
			return "";			
		}
	}
	
	public String obtenerSubCodigoAnotacion(final String clave){
		try{
			return catalogoUtils.getCatalogoBean(CatalogoEnum.TP_ANTCN, clave).getDescripcionL();
		}catch(final ControlableException ce){
			return "";			
		}
	}
	
	public void mostrarTodasAnotaciones(){
		this.setAnotacionesVisible(this.retiro.getAnotaciones().size());
	}
	
}
