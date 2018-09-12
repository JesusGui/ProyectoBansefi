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
import mx.babel.bansefi.banksystem.base.backends.ConsultaBloqueosBackEnd;
import mx.babel.bansefi.banksystem.base.backends.ConsultaMovimientosBackEnd;
import mx.babel.bansefi.banksystem.base.backends.ConsultaRelacionPersonaCuentaBackEnd;
import mx.babel.bansefi.banksystem.base.backends.ConsultaRetencionesBackEnd;
import mx.babel.bansefi.banksystem.base.backends.GuardaDoumentoBackEnd;
import mx.babel.bansefi.banksystem.base.backends.anotaciones.ConsultaListaAnotacionesBackEnd;
import mx.babel.bansefi.banksystem.base.backends.busquedas.BusquedaNivelCuentaBackEnd;
import mx.babel.bansefi.banksystem.base.backends.consultaintervenientescuenta.ConsultaIntervenientesBackEnd;
import mx.babel.bansefi.banksystem.base.backends.consultasaldo.ConsultaSaldoBackEnd;
import mx.babel.bansefi.banksystem.base.backends.consultatitular.ConsultaTitularBackend;
import mx.babel.bansefi.banksystem.base.beans.domain.AnotacionBean;
import mx.babel.bansefi.banksystem.base.beans.domain.CuentaBean;
import mx.babel.bansefi.banksystem.base.beans.movimientos.ConsultaMovimientosBean;
import mx.babel.bansefi.banksystem.base.beans.movimientos.MovimientoBean;
import mx.babel.bansefi.banksystem.base.enums.BusquedaEnum;
import mx.babel.bansefi.banksystem.base.enums.CatalogoEnum;
import mx.babel.bansefi.banksystem.base.enums.NavegacionEnum;
import mx.babel.bansefi.banksystem.base.enums.ParametrosFlashEnum;
import mx.babel.bansefi.banksystem.base.utils.CatalogoCentrosLoaderTask;
import mx.babel.bansefi.banksystem.base.utils.CatalogoUtils;
import mx.babel.bansefi.banksystem.base.utils.DetalleCuentaUtils;
import mx.babel.bansefi.banksystem.base.utils.ManagedBeanStateRecover;
import mx.babel.bansefi.banksystem.base.utils.PdfUtils;
import mx.babel.bansefi.banksystem.base.constants.ConstantesFuncionales;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Controlador encargado de gestionar las vistas de consulta de movimientos.
 * 
 * @author mario.montesdeoca
 * 
 */
@ManagedBean(name = "movimientosController")
@ViewScoped
@Component
@Scope("view")
public class MovimientosController implements Serializable {

	@Autowired
	private PdfUtils pdfUtils;

	@Autowired
	private ContextoUtils contextoUtils;
	@Autowired
	CatalogoUtils catalogoUtils;
	@Autowired
	ConsultaTitularBackend consultaTitularBackend;
	@Autowired
	ConsultaSaldoBackEnd consultaSaldo;
	@Autowired
	ConsultaIntervenientesBackEnd consultaIntervenientesBackend;
	@Autowired
	ConsultaMovimientosBackEnd consultaMovimientosBackEnd;
	@Autowired
	ConsultaBloqueosBackEnd consultaBloqueosBackEnd;
	@Autowired
	ConsultaRetencionesBackEnd consultaRetencionesBackEnd;
	@Autowired
	GuardaDoumentoBackEnd guardaDoumentoBackEnd;
	@Autowired
	ManagedBeanStateRecover managedBeanStateRecover;
	@Autowired
	ConsultaRelacionPersonaCuentaBackEnd consultaRelacionPersonaCuentaBackEnd;
	@Autowired
	CatalogoCentrosLoaderTask catalogoCentros;
	@Autowired
	ConsultaListaAnotacionesBackEnd consultaAnotacionescuentaBackEnd;
	@Autowired
	BusquedaNivelCuentaBackEnd busquedaNivelCuentaBackEnd;

	private static final String BEAN_MOVIMIENTOS = "consultaMovimientosBean";
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LogManager.getLogger(MovimientosController.class.getName());
	private static final int NUM_ANOTACIONES = 6;
	private String fechaMinima = ConstantesFuncionales.MIN_FECHA_INICIO;
	private ConsultaMovimientosBean consultaMovimientosBean;
	private CuentaBean cuentaBean;
	private String errorRetenciones;
	private String errorBloqueos;
	private String errorMovimientos;
	private int pagina;
	private boolean mostrarBoton;
	private int anotacionesVisibles;
	

	/**
	 * @return Atributo consultaMovimientosBean
	 */
	public ConsultaMovimientosBean getConsultaMovimientosBean() {
		return consultaMovimientosBean;
	}

	/**
	 * @param consultaMovimientosBean
	 *            Atributo consultaMovimientosBean a definir
	 */
	public void setConsultaMovimientosBean(
			ConsultaMovimientosBean consultaMovimientosBean) {
		this.consultaMovimientosBean = consultaMovimientosBean;
	}

	/**
	 * @return Atributo pagina
	 */
	public int getPagina() {
		return pagina;
	}

	/**
	 * @param pagina
	 *            Atributo pagina a definir
	 */
	public void setPagina(int pagina) {
		this.pagina = pagina;
	}

	/**
	 * @return Atributo cuentaBean
	 */
	public CuentaBean getCuentaBean() {
		return cuentaBean;
	}

	/**
	 * @param cuentaBean
	 * 			Atributo cuentaBean a definir
	 */
	public void setCuentaBean(CuentaBean cuentaBean) {
		this.cuentaBean = cuentaBean;
	}

	public ContextoUtils getContextoUtils() {
		return contextoUtils;
	}

	public void setContextoUtils(ContextoUtils contextoUtils) {
		this.contextoUtils = contextoUtils;
	}

	public String getErrorRetenciones() {
		return errorRetenciones;
	}

	public void setErrorRetenciones(String errorRetenciones) {
		this.errorRetenciones = errorRetenciones;
	}

	public String getErrorBloqueos() {
		return errorBloqueos;
	}

	public void setErrorBloqueos(String errorBloqueos) {
		this.errorBloqueos = errorBloqueos;
	}

	public String getErrorMovimientos() {
		return errorMovimientos;
	}

	public void setErrorMovimientos(String errorMovimientos) {
		this.errorMovimientos = errorMovimientos;
	}

	public boolean isMostrarBoton() {
		return mostrarBoton;
	}

	public void setMostrarBoton(boolean mostrarBoton) {
		this.mostrarBoton = mostrarBoton;
	}

	/**
	 * 
	 * @return
	 */
	public boolean tieneMasDatos() {
		if (pagina <= this.consultaMovimientosBean.getMovimientos().size()) {
			return true;
		} else {
			return false;
		}

	}

	public Boolean muestroBoton() {
		if (pagina < this.consultaMovimientosBean.getMovimientos().size()) {
			return false;
		} else {
			return true;
		}
	}

	public String getFechaMinima() {
		return fechaMinima;
	}

	public void setFechaMinima(String fechaMinima) {
		this.fechaMinima = fechaMinima;
	}

	/**
	 * Clase para incializar bean de ConsultaMovimientosBean.
	 */
	@PostConstruct
	public void init() {
		
		if (this.obtieneFlash().get(MovimientosController.BEAN_MOVIMIENTOS) != null) {
			this.consultaMovimientosBean = (ConsultaMovimientosBean) this
					.obtieneFlash().get(MovimientosController.BEAN_MOVIMIENTOS);
			
			//SE REALIZA LA RECLASIFICACIÓN DE LA CUENTA
			busquedaNivelCuentaBackEnd.EjecutarTRN(this.consultaMovimientosBean
					.getDetalleCuentaUtils().getNumeroCuenta().toString());
			
			this.consultaMovimientosBean.setFechaHasta(contextoUtils.getFechaContableActual());
			//REALIZAMOS LA PAGINACION SI HAY MOVIMIENTOS
			if(this.consultaMovimientosBean.getTieneMovimientos()){
				this.pagina = 10;
				this.mostrarBoton = this.tieneMasDatos();
			}
			if(this.consultaMovimientosBean.isErrorRetenciones()){
				this.errorRetenciones = "No se han podido recuperar los datos. Pruebe a realizar la consulta de nuevo.";
			}
			if(this.consultaMovimientosBean.isErrorBloqueos()){
				this.errorBloqueos = "No se han podido recuperar los datos. Pruebe a realizar la consulta de nuevo.";
			}
			if(this.consultaMovimientosBean.isErrorMovimientos()){
				this.errorMovimientos = "No se han podido recuperar los datos. Pruebe a realizar la consulta de nuevo.";
			}
		} else if(this.obtieneFlash().get(ParametrosFlashEnum.NAVEGACION_MANAGED_BEAN.getParamFlash()) != null) {
			this.consultaMovimientosBean = (ConsultaMovimientosBean) this
					.obtieneFlash().get(MovimientosController.BEAN_MOVIMIENTOS);
			managedBeanStateRecover.recuperaController(this);
		} else {
			List<CatalogoBean> codigosIdentificacion = catalogoUtils
					.getCatalogo(CatalogoEnum.TP_ID_EXT_PERS);
			this.consultaMovimientosBean = new ConsultaMovimientosBean();
			this.consultaMovimientosBean
					.setDetalleCuentaUtils(new DetalleCuentaUtils(
							contextoUtils, consultaTitularBackend,
							consultaRelacionPersonaCuentaBackEnd,
							consultaAnotacionescuentaBackEnd,
							codigosIdentificacion));
			this.consultaMovimientosBean.setFechaHasta(contextoUtils.getFechaContableActual());
			
		}
		
		//SE OBTIENE EL VALOR DE LA BUSQUEDA DE CUENTAS
		if(this.obtieneFlash().get(ParametrosFlashEnum.NUMERO_CUENTA.getParamFlash()) != null){
			
			Long cuenta = (Long)this.obtieneFlash().get(ParametrosFlashEnum.NUMERO_CUENTA.getParamFlash());
			String titular = (String)this.obtieneFlash().get(ParametrosFlashEnum.TITULAR_CUENTA.getParamFlash());
			this.consultaMovimientosBean
					.getDetalleCuentaUtils()
						.setNumeroCuenta(cuenta);
			this.consultaMovimientosBean.getDetalleCuentaUtils().setMuestraTitular(true);
			this.consultaMovimientosBean
					.getDetalleCuentaUtils()
						.setNombreTitular(titular);
			this.consultaMovimientosBean.getDetalleCuentaUtils().ejecutarAnotaciones(cuenta);
			this.establecerAnotacionesVisibles();
		}
	}

	/**
	 * Obtiene el saldo de la cuenta.
	 * 
	 * @throws UnknownHostException
	 */
	public void consultarSaldoCuenta() {
		BigInteger numeroCuenta;
		String codigoDocumento;
		String idExterna;
		try {
			numeroCuenta = new BigInteger(this.consultaMovimientosBean
					.getDetalleCuentaUtils().getNumeroCuenta().toString());
			codigoDocumento = this.consultaMovimientosBean
					.getDetalleCuentaUtils().getCodigoDocumento();
			idExterna = this.consultaMovimientosBean.getDetalleCuentaUtils()
					.getIdExterno();
		} catch (NullPointerException npe) {
			throw new ControlableException(
					"No se puede consultar los movimientos", npe);
		} catch (NumberFormatException nfe) {
			throw new ControlableException(
					"No se puede consultar los movimientos", nfe);
		}
		this.consultaMovimientosBean.setSaldoCuenta(this.consultaSaldo
				.consultaSaldo(numeroCuenta, codigoDocumento, idExterna));
	}

	/**
	 * Clase para comprobar si el número de documento pertenece a alguno de los
	 * intervenientes de la cuenta.
	 * 
	 * @throws ControlableException
	 *             Cuando los datos ingresados no son correctos
	 * @throws NoControlableException
	 *             Cuando existe un fallo en la ejecución del servicio web
	 */
	public String comprobarInterveniente() throws ControlableException,
			NoControlableException {
		boolean interveniente = this.consultaMovimientosBean
				.getDetalleCuentaUtils().verificarDocumento();
		if (interveniente) {
			return this.continuar();
		}
		return null;
	}

	/**
	 * Clase para continuar con la consulta desde la ventana de filtro hacia la
	 * ventana de detalle.
	 * 
	 * @return Ruta de la ventana de detalle.
	 * @throws NoControlableException
	 *             Cuando ocurre un fallo en la ejecución de los servicios web
	 * @throws ControlableException
	 *             Cuando ocurre un error en los datos ingresados.
	 */
	public String continuar() throws NoControlableException,
			ControlableException {
		consultarSaldoCuenta();
		consultarMovimientos();
		obtieneFlash().put(MovimientosController.BEAN_MOVIMIENTOS,
				this.consultaMovimientosBean);
		return NavegacionEnum.DETALLE_MOVIMIENTOS.getRuta();
	}

	/**
	 * Método encargado de realizar las consultas de movimientos, retenciones,
	 * bloqueos y autorización a través de back ends.
	 */
	public void consultarMovimientos() throws ControlableException,
			NoControlableException {

		//CONSULTA DE RETENCIONES
		try {
			consultaMovimientosBean.setRetenciones(consultaRetencionesBackEnd
					.ejecutarTRN(consultaMovimientosBean
							.getDetalleCuentaUtils().getNumeroCuenta().toString()));
		} catch (ControlableException ce) {
				consultaMovimientosBean
						.setRetenciones(new ArrayList<MovimientoBean>());
				if(ce.getRtncod() != 7){
					consultaMovimientosBean
					.setErrorRetenciones(true);
				}				
		} catch(NoControlableException nce){
				consultaMovimientosBean
					.setRetenciones(new ArrayList<MovimientoBean>());
				consultaMovimientosBean
					.setErrorRetenciones(true);
		}

		//CONSULTA DE BLOQUEOS
		try {
			consultaMovimientosBean.setBloqueos(consultaBloqueosBackEnd
					.ejecutarTRN(consultaMovimientosBean
							.getDetalleCuentaUtils().getNumeroCuenta().toString()));
		} catch (ControlableException ce) {
				consultaMovimientosBean
					.setBloqueos(new ArrayList<MovimientoBean>());
				consultaMovimientosBean
					.setErrorBloqueos(true);
		} catch(NoControlableException nce){
				consultaMovimientosBean
					.setBloqueos(new ArrayList<MovimientoBean>());
				consultaMovimientosBean
					.setErrorBloqueos(true);
		}

		// SE QUITA LA LLAMADA A CONSULTA DE AUTORIZACIONES POR PETICION DEL USUARIO
		// try{
		// consultaMovimientosBean.setAutorizaciones(consultaAutorizacionesBackEnd.ejecutarTRN(
		// consultaMovimientosBean.getDetalleCuentaUtils().getNumeroCuenta()));
		// }catch(ControlableException ce){
		// if(ce.getRtncod()==7){
		// consultaMovimientosBean.setAutorizaciones(new
		// ArrayList<MovimientoBean>());
		// }
		// }

		//CONSULTA DE MOVIMIENTOS
		try{
			consultaMovimientosBean.setMovimientos(consultaMovimientosBackEnd
					.ejecutarWS(consultaMovimientosBean));
		} catch(ControlableException ce){
			consultaMovimientosBean
				.setMovimientos(new ArrayList<MovimientoBean>());
			consultaMovimientosBean
				.setErrorMovimientos(true);
		} catch(NoControlableException nce){
			consultaMovimientosBean
				.setMovimientos(new ArrayList<MovimientoBean>());
			consultaMovimientosBean
				.setErrorMovimientos(true);
		}
		
	}

	/**
	 * Método para obtener la ruta a la pantalla incial del flujo.
	 * 
	 * @return ruta de la ventana de filtro para la consulta de movimientos.
	 */
	public String inicio() {
		return NavegacionEnum.CONSULTA_MOVIMIENTOS.getRuta();
	}

	/**
	 * Método para obtener la ruta de la pantalla de inicio.
	 * 
	 * @return ruta de la pantalla de inicio.
	 */
	public String regresa() {
		return NavegacionEnum.INICIO.getRuta();
	}

	/**
	 * Se encarga de obtener el flash.
	 * 
	 * @return Flash con los datos de la pagina
	 */
	public Flash obtieneFlash() {
		return FacesContext.getCurrentInstance().getExternalContext()
				.getFlash();
	}
	
	/**
	 * Método que consulta el detalle de la anotacion seleccionada
	 * 
	 * @return La URL a la que redireccionará
	 */
	public String detalleAnotacion(AnotacionBean anotacionBean) {
		managedBeanStateRecover.enviaController(this, NavegacionEnum.CONSULTA_MOVIMIENTOS);
		obtieneFlash().put(ParametrosFlashEnum.ANOTACION_BEAN.getParamFlash(),anotacionBean);
		return NavegacionEnum.DETALLE_ANOTACIONES.getRuta();
	}

	/**
	 * @return valor Verdadero si no hay datos que mostrar Falso si hay al menos
	 *         una tabla que mostrar
	 */
	public Boolean mostrarMensaje() {
		if (!consultaMovimientosBean.getTieneRetenciones()
				&& !consultaMovimientosBean.getTieneBloqueos()
				&& !consultaMovimientosBean.getTieneMovimientos()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Metodo que genera el PDF para imprimir la consulta de movimientos.
	 */
	public void imprimeJasper() {

		StringBuffer nombrePdf = new StringBuffer(this.contextoUtils.getId());
		Map<String, Object> params = new HashMap<String, Object>();
		Map<String, String> imagenes = new HashMap<String, String>();

		params.put("PLAZA", this.buscarPlaza(this.contextoUtils.getPlazaBancaria()));
		params.put("COF", this.contextoUtils.getSucursal());
		params.put("NOF", this.obtenerDescripcionCentro(contextoUtils.getSucursal()));
		params.put("ACUERDO", this.consultaMovimientosBean
				.getDetalleCuentaUtils().getNumeroCuenta());
		params.put("TITULAR", this.consultaMovimientosBean
				.getDetalleCuentaUtils().getNombreTitular());
		params.put("DESDE", this.consultaMovimientosBean.getFechaDesde());
		params.put("HASTA", this.consultaMovimientosBean.getFechaHasta());
		params.put("CENTRO", contextoUtils.getSucursal());
		params.put("SALDO", consultaMovimientosBean.getSaldoCuenta().getDisponible());

		imagenes.put("Logo_bsfi_bn.png", "LOGO");

		this.pdfUtils.generaPdf("comprobanteMovimientos.jrxml", params,
				imagenes, null, nombrePdf.toString(),
				consultaMovimientosBean.getMovimientos());

	}
	
	/**
	 * Función para obtener la descripcion del nombre del centro.
	 *
	 * @return la descripcion del centro
	 */
	public String obtenerDescripcionCentro(final String clave) {
		try{
			return catalogoCentros.getCatalogoBean(contextoUtils.getEntidad(), clave).getDescripcionC();
		}catch (ControlableException ce){
			LOGGER.debug("Error al intentar obtener la descripcion del centro a partir del codigo: "+ clave);
			return "";
		}
	}
	
	/**
     * 
     * @param clave de la plaza a buscar
     * @return Descripcion de la plaza
     */
    public String buscarPlaza(String clave){
    	String descripcion = clave;
    	List<CatalogoBean> catalogo = catalogoUtils.getCatalogo(CatalogoEnum.TP_PLAZA_BANCARIA);
    	
    	for (CatalogoBean elemento : catalogo) {
			if(elemento.getClaveFila().equals(clave.trim())){
				descripcion = elemento.getDescripcionL();
			}
		}
    	
    	return descripcion;
    }

	/**
	 * Función empleada para redireccionar a la búsqueda de cuentas
	 * 
	 * @return ruta a la búsqueda de cuentas
	 */
	public String buscarCuentas() {
		managedBeanStateRecover.enviaController(this,
				NavegacionEnum.CONSULTA_MOVIMIENTOS);
		obtieneFlash().put(ParametrosFlashEnum.TIPO_BUSQUEDA.getParamFlash(),
				BusquedaEnum.CUENTAS.getBusquedaValor());
		return NavegacionEnum.BUSQUEDA.getRuta();
	}

	/**
	 * Método empleado para cargar más datos en la tabla de movimientos
	 */
	public void masDatos() {
		pagina += 10;

		if (!tieneMasDatos()
				&& consultaMovimientosBean.getPaginacionMovimientos()
						.getMasDatos()) {
			consultaMovimientosBean
					.setMasMovimientos(consultaMovimientosBackEnd
							.ejecutarWS(consultaMovimientosBean));
		}
		if(!tieneMasDatos()){
			this.mostrarBoton = false;
		}

	}
	
	public void establecerAnotacionesVisibles(){
		int numAnotacionesVisibles = 0;
		if(this.consultaMovimientosBean.getDetalleCuentaUtils().getAnotaciones() != null){
			if(this.consultaMovimientosBean.getDetalleCuentaUtils().getAnotaciones().size() > NUM_ANOTACIONES){
				numAnotacionesVisibles = MovimientosController.NUM_ANOTACIONES;
			}else{
				numAnotacionesVisibles = this.consultaMovimientosBean.getDetalleCuentaUtils().getAnotaciones().size();
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
		this.setAnotacionesVisible(this.getConsultaMovimientosBean().getDetalleCuentaUtils().getAnotaciones().size());
	}
	
	public void buscaTitular(){
		this.getConsultaMovimientosBean().getDetalleCuentaUtils().buscaTitular();
		this.establecerAnotacionesVisibles();
	}

}
