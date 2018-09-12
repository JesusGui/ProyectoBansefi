package mx.babel.bansefi.banksystem.base.controllers;

import java.io.Serializable;
import java.math.BigInteger;
import java.net.UnknownHostException;
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
import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.sesion.utils.ContextoUtils;
import mx.babel.bansefi.banksystem.base.backends.ConsultaCuentaBackend;
//import mx.babel.bansefi.banksystem.base.backends.ConsultaAutorizacionesBackEnd;
//import mx.babel.bansefi.banksystem.base.backends.ConsultaBloqueosBackEnd;
import mx.babel.bansefi.banksystem.base.backends.ConsultaRelacionPersonaCuentaBackEnd;
import mx.babel.bansefi.banksystem.base.backends.anotaciones.ConsultaListaAnotacionesBackEnd;
import mx.babel.bansefi.banksystem.base.backends.busquedas.BusquedaNivelCuentaBackEnd;
//import mx.babel.bansefi.banksystem.base.backends.ConsultaRetencionesBackEnd;
import mx.babel.bansefi.banksystem.base.backends.consultasaldo.ConsultaSaldoBackEnd;
import mx.babel.bansefi.banksystem.base.backends.consultatitular.ConsultaTitularBackend;
import mx.babel.bansefi.banksystem.base.beans.domain.AnotacionBean;
import mx.babel.bansefi.banksystem.base.beans.domain.CuentaBean;
import mx.babel.bansefi.banksystem.base.beans.movimientos.ConsultaMovimientosBean;
import mx.babel.bansefi.banksystem.base.enums.BusquedaEnum;
import mx.babel.bansefi.banksystem.base.enums.CatalogoEnum;
import mx.babel.bansefi.banksystem.base.enums.NavegacionEnum;
import mx.babel.bansefi.banksystem.base.enums.ParametrosFlashEnum;
import mx.babel.bansefi.banksystem.base.utils.CatalogoCentrosLoaderTask;
import mx.babel.bansefi.banksystem.base.utils.CatalogoUtils;
import mx.babel.bansefi.banksystem.base.utils.DetalleCuentaUtils;
import mx.babel.bansefi.banksystem.base.utils.ManagedBeanStateRecover;
import mx.babel.bansefi.banksystem.base.utils.PdfUtils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@ManagedBean(name="saldosController")
@ViewScoped
@Component
@Scope("view")
public class SaldosController implements Serializable{
	
	private static final Logger LOGGER = LogManager.getLogger(SaldosController.class.getName());
	
	@Autowired
	ManagedBeanStateRecover managedBeanStateRecover;
	
	@Autowired
	ConsultaRelacionPersonaCuentaBackEnd consultaRelacionPersonaCuentaBackEnd;
	
	@Autowired
	ConsultaSaldoBackEnd consultaSaldoBackend;
	
	@Autowired
	BusquedaNivelCuentaBackEnd busquedaNivelCuentaBackEnd;
	
//	@Autowired
//	ConsultaAutorizacionesBackEnd consultaAutorizacionesBackEnd;
//	
//	@Autowired
//	ConsultaBloqueosBackEnd consultaBloqueosBackEnd;
//	
//	@Autowired
//	ConsultaRetencionesBackEnd consultaRetencionesBackEnd;

    @Autowired
    private ConsultaTitularBackend consultaTitular;
    
    @Autowired
    private ConsultaCuentaBackend consultaCuentaBackend;
	
	@Autowired
	private ContextoUtils contextoUtils;

	@Autowired
	CatalogoUtils catalogoUtils;
	
	@Autowired
    private PdfUtils pdfUtils;
	
	@Autowired
	ConsultaListaAnotacionesBackEnd consultaAnotacionescuentaBackEnd;
	
	@Autowired
	CatalogoCentrosLoaderTask catalogoCentros;
	
	private ConsultaMovimientosBean saldoBean;
	private CuentaBean cuentaBean;
	private static final long serialVersionUID = 1L;
	private static final int NUM_ANOTACIONES = 6;
	private int anotacionesVisibles;
		
	/**
	 * @return the saldoBean
	 */
	public ConsultaMovimientosBean getSaldoBean() {
		return saldoBean;
	}

	/**
	 * @param saldoBean the saldoBean to set
	 */
	public void setSaldoBean(ConsultaMovimientosBean saldoBean) {
		this.saldoBean = saldoBean;
	}
	
	@PostConstruct
	public void init(){
		if(obtieneFlash().get(ParametrosFlashEnum.SALDO_BEAN.getParamFlash()) != null) {
            this.saldoBean = (ConsultaMovimientosBean) obtieneFlash().get(ParametrosFlashEnum.SALDO_BEAN.getParamFlash());
            this.cuentaBean = (CuentaBean) obtieneFlash().get(ParametrosFlashEnum.CUENTA_BEAN.getParamFlash());

            //SE REALIZA LA RECLASIFICACIÓN DE LA CUENTA
            busquedaNivelCuentaBackEnd.EjecutarTRN(this.cuentaBean.getNumeroCuenta().toString());
            
            this.imprimirReporte();
    	} else {
    		List<CatalogoBean> codigosIdentificacion = catalogoUtils.getCatalogo(CatalogoEnum.TP_ID_EXT_PERS);
    		this.saldoBean = new ConsultaMovimientosBean();
    		this.saldoBean.setDetalleCuentaUtils(
    				new DetalleCuentaUtils(contextoUtils, 
    						consultaTitular, 
    						consultaRelacionPersonaCuentaBackEnd, 
    						consultaAnotacionescuentaBackEnd, 
    						codigosIdentificacion));
    	}
		
		//cuando se retorna de flujos 1)busqueda de cuenta y 2)consulta anotación
		if(this.obtieneFlash().get(ParametrosFlashEnum.NAVEGACION_MANAGED_BEAN.getParamFlash()) != null) {
    		this.managedBeanStateRecover.recuperaController(this);
    	}
		
		
		//Cuando se retorna de la búsqueda de cuenta 
		if(this.obtieneFlash().get(ParametrosFlashEnum.NUMERO_CUENTA.getParamFlash()) != null){
			Long cuenta = (Long)this.obtieneFlash().get(ParametrosFlashEnum.NUMERO_CUENTA.getParamFlash());
			String titular = (String)this.obtieneFlash().get(ParametrosFlashEnum.TITULAR_CUENTA.getParamFlash());
			this.saldoBean.getDetalleCuentaUtils().busqueda(cuenta, titular);
			this.saldoBean.getDetalleCuentaUtils().ejecutarAnotaciones(cuenta);
			this.establecerAnotacionesVisibles();
		}
	}
	
	/** 
	 * Consulta la trn de intervenientes, si la persona interviene en esta cuenta, continua,
	 * si no, muestra el modal de aviso.
	 */
	public String comprobarInterveniente() {
			boolean titular = this.saldoBean.getDetalleCuentaUtils().getMuestraTitular();
			if(titular){
				boolean interveniente = this.saldoBean.getDetalleCuentaUtils().verificarDocumento();
				this.saldoBean.getDetalleCuentaUtils().verificarDocumento();
				if (interveniente) {
					return this.continuar(); 
				}
			}
			return null;
	}
	
	/**
	 * Realiza las consultas necesarias para tener el detalle de la consulta de saldos.
	 * @return - ruta de la página de detalle de consulta de saldos
	 * @throws NoControlableException
	 * @throws ControlableException
	 */
	public String continuar() throws NoControlableException, ControlableException{
		
		this.consultarSaldoCuenta();
		cuentaBean = consultaCuentaBackend.ejecutarTRN(new Long(this.saldoBean.getDetalleCuentaUtils().getNumeroCuenta()));
		if(cuentaBean!= null){
			obtieneFlash().put(ParametrosFlashEnum.SALDO_BEAN.getParamFlash(), this.saldoBean);
			obtieneFlash().put(ParametrosFlashEnum.CUENTA_BEAN.getParamFlash(), this.cuentaBean);
			return NavegacionEnum.DETALLE_SALDOS.getRuta();
		}else{
			throw new NoControlableException("No se han podido obtener datos de la cuenta","No se han podido obtener datos de la cuenta");
		}
	}
	
	/**
	 * Obtiene el saldo de la cuenta.
	 * @throws UnknownHostException
	 */
	public void consultarSaldoCuenta(){
		BigInteger numeroCuenta;
		String codigoDocumento;
		String idExterna;
		try {
			numeroCuenta = new BigInteger(this.saldoBean.getDetalleCuentaUtils().getNumeroCuenta().toString());
			codigoDocumento = this.saldoBean.getDetalleCuentaUtils().getCodigoDocumento();
			idExterna = this.saldoBean.getDetalleCuentaUtils().getIdExterno();
		}catch (NullPointerException npe){
	        throw new ControlableException("No se puede consultar los movimientos", npe);
	    }
	    catch (NumberFormatException nfe){
	        throw new ControlableException("No se puede consultar los movimientos", nfe);
	    }
		this.saldoBean.setSaldoCuenta(this.consultaSaldoBackend.consultaSaldo(numeroCuenta,codigoDocumento,idExterna));
				
	}
	
	/**
	 * Método que no llama a nada, sólo se encuentra para boton imprimir
	 */
	public void imprimirReporte(){
		Map<String, String> images = new HashMap<String, String>();
        images.put("Logo_bsfi_bn.png", "LOGO");
        
        StringBuffer nombrePdf = new StringBuffer(this.contextoUtils.getId());
        Map<String, Object> params = new HashMap<String, Object>();
        
        if(cuentaBean != null){
	        try{
	        	params.put("plaza",catalogoUtils.getCatalogoBean(CatalogoEnum.TP_PLAZA_BANCARIA, this.cuentaBean.getPlazaBancaria()).getDescripcionL());
			} catch (ControlableException | NullPointerException e) {
				LOGGER.debug("Error al intentar obtener la descripcion del tipo de liquidacióm a partir del codigo: "+ this.cuentaBean.getPlazaBancaria(), e);
			}
	        params.put("oficina", this.obtenerDescripcionCentro(contextoUtils.getSucursal()));
	        params.put("cuenta",this.saldoBean.getDetalleCuentaUtils().getNumeroCuenta());
	        params.put("titular", this.saldoBean.getDetalleCuentaUtils().getNombreTitular());
	        params.put("saldoContable", this.saldoBean.getSaldoCuenta().getSaldoContable());
	        params.put("saldoDisponible", this.saldoBean.getSaldoCuenta().getDisponible());   
	        
	        final List<ConsultaMovimientosBean> listaSaldos = new ArrayList<ConsultaMovimientosBean>();
	        listaSaldos.add(saldoBean);
	        
	        pdfUtils.generaPdf("saldoReporte.jrxml", params, images, null,
	        		 nombrePdf.toString(), listaSaldos);
        }else{
        	throw new NoControlableException("No se han podido obtener datos de la cuenta","No se han podido obtener datos de la cuenta");
        }
	}
	
	/**
	 * Función para obtener la descripcion del nombre del centro.
	 *
	 * @return la descripcion del centro
	 */
	public String obtenerDescripcionCentro(final String clave) {
		try{
			return clave + " " + catalogoCentros.getCatalogoBean(contextoUtils.getEntidad(), clave).getDescripcionC();
		}catch (ControlableException ce){
			LOGGER.debug("Error al intentar obtener la descripcion del centro a partir del codigo: "+ clave);
			return "";
		}
	}
	
	/**
	 * Regresa a la pantalla de consulta de saldos
	 * @return Ruta pantalla consulta de Saldos
	 */
	public String otraConsulta(){
		return NavegacionEnum.CONSULTA_SALDOS.getRuta();
	}
	
	/**
	 * Regresa a la pantalla de consulta de saldos
	 * @return Ruta pantalla consulta de Saldos
	 */
	public String inicio() {
		return NavegacionEnum.CONSULTA_SALDOS.getRuta();
	}
	
	/**
	 * Va a la pantalla de inicio
	 * @return Ruta pantalla inicio
	 */
	public String irInicio(){
		return NavegacionEnum.INICIO.getRuta();
	}
	
	/**
	 * Va a la pantalla detalle de saldos
	 * @return Ruta pantalla detalle de saldos
	 */
	public String irDetalle(){
		return NavegacionEnum.DETALLE_SALDOS.getRuta();
	}
	

	/**
     * Se encarga de obtener el flash.
     * 
     * @return Flash con los datos de la pagina
     */
	public Flash obtieneFlash(){
		return FacesContext.getCurrentInstance().getExternalContext().getFlash();
	}
	
	/**
	 * Se encarga de ir hacia el buscador
	 * @return datos de busqueda
	 */
	public String buscarCuenta(){
		obtieneFlash().put(ParametrosFlashEnum.TIPO_BUSQUEDA.getParamFlash(), BusquedaEnum.CUENTAS.getBusquedaValor());
		managedBeanStateRecover.enviaController(this, NavegacionEnum.CONSULTA_SALDOS);
		return NavegacionEnum.BUSQUEDA.getRuta();
			
	}
	
	/**
	 * Método que consulta el detalle de la anotacion seleccionada
	 * 
	 * @return La URL a la que redireccionará
	 */
	public String detalleAnotacion(AnotacionBean anotacionBean) {
		managedBeanStateRecover.enviaController(this, NavegacionEnum.CONSULTA_SALDOS);
		obtieneFlash().put(ParametrosFlashEnum.ANOTACION_BEAN.getParamFlash(),anotacionBean);
		return NavegacionEnum.DETALLE_ANOTACIONES.getRuta();
	}
	
	public void establecerAnotacionesVisibles(){
		int numAnotacionesVisibles = 0;
		if(this.getSaldoBean().getDetalleCuentaUtils().getAnotaciones() != null){
			if(this.getSaldoBean().getDetalleCuentaUtils().getAnotaciones().size() > NUM_ANOTACIONES){
				numAnotacionesVisibles = SaldosController.NUM_ANOTACIONES;
			}else{
				numAnotacionesVisibles = this.getSaldoBean().getDetalleCuentaUtils().getAnotaciones().size();
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
		this.setAnotacionesVisible(this.getSaldoBean().getDetalleCuentaUtils().getAnotaciones().size());
	}
	
	public void buscaTitular(){
		this.getSaldoBean().getDetalleCuentaUtils().buscaTitular();
		this.establecerAnotacionesVisibles();
	}
	

}
