package mx.babel.bansefi.banksystem.oficina.controllers;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

import mx.babel.arq.catalogo.beans.CatalogoBean;
import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.sesion.utils.ContextoUtils;
import mx.babel.bansefi.banksystem.base.backends.consultatitular.ConsultaTitularBackend;
import mx.babel.bansefi.banksystem.base.backends.consultatitular.ConsultaTitularRes;
import mx.babel.bansefi.banksystem.base.beans.ApuntesBean;
import mx.babel.bansefi.banksystem.base.constants.ConstantesFuncionales;
import mx.babel.bansefi.banksystem.base.controllers.MovimientosController;
import mx.babel.bansefi.banksystem.base.enums.BusquedaEnum;
import mx.babel.bansefi.banksystem.base.enums.CatalogoEnum;
import mx.babel.bansefi.banksystem.base.enums.NavegacionEnum;
import mx.babel.bansefi.banksystem.base.enums.ParametrosFlashEnum;
import mx.babel.bansefi.banksystem.base.utils.CatalogoCentrosLoaderTask;
import mx.babel.bansefi.banksystem.base.utils.CatalogoUtils;
import mx.babel.bansefi.banksystem.base.utils.ManagedBeanStateRecover;
import mx.babel.bansefi.banksystem.base.utils.PdfUtils;
import mx.babel.bansefi.banksystem.oficina.backends.AltaApuntesBackEnd;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@ManagedBean(name = "altaApunteController")
@ViewScoped
@Component
@Scope("view")
public class AltaApunteController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -258977379952829639L;

	private static final String DATE_FORMAT = "dd/MM/yyyy";
	private static final String CTE_ESPACIO = " ";
	private static final String CTE_CERO = "0";
	private static final int ERROR_TIPO_CUENTA = 8429;
	private static final String CARGO = "DGE33OOU";
	private static final String ABONO = "DGE34OOU";
	private static final double CTE_MOSTRAR_UNIDADES_MILLON = 1000000;
	private static final Logger LOGGER = LogManager
			.getLogger(MovimientosController.class.getName());

	private static final String ALTA_APUNTE = "altaApunte";

	private Date fechaActual = new Date();
	private boolean muestraTitular;
	private boolean muestraTitularError;
	private boolean todoOk;
	private boolean errorTipoCuenta;
	private boolean deshabilitarRadio;
	private String fechaMinima = ConstantesFuncionales.MIN_FECHA_INICIO;

	private List<CatalogoBean> codigosOperacion;
	private ApuntesBean apuntesBean;

	@Autowired
	AltaApuntesBackEnd altaApuntesBackEnd;

	@Autowired
	ConsultaTitularBackend consultaTitular;

	@Autowired
	CatalogoCentrosLoaderTask catalogoCentros;

	@Autowired
	private ManagedBeanStateRecover managedBeanStateRecover;

	@Autowired
	CatalogoUtils catalogoUtils;

	@Autowired
	ContextoUtils contextoUtils;

	@Autowired
	private PdfUtils pdfUtils;

	/**
	 * Super.
	 */
	public AltaApunteController() {
		super();
	}

	@PostConstruct
	public void init() {

		if (obtieneFlash()
				.get(ParametrosFlashEnum.APUNTES_BEAN.getParamFlash()) != null) {
			this.apuntesBean = (ApuntesBean) obtieneFlash().get(
					ParametrosFlashEnum.APUNTES_BEAN.getParamFlash());
			this.generaPlantillaDeposito();
		} else {
			this.apuntesBean = new ApuntesBean();
			this.inicializarBean();
			this.setCodigosOperacion(catalogoUtils
					.getCatalogo(CatalogoEnum.TP_CLOP));
		}

		// Cargamos los datos de la búsqueda
		if (this.obtieneFlash().get(
				ParametrosFlashEnum.NUMERO_CUENTA.getParamFlash()) != null) {
			managedBeanStateRecover.recuperaController(this);
			Long cuenta = (Long) this.obtieneFlash().get(
					ParametrosFlashEnum.NUMERO_CUENTA.getParamFlash());
			String titular = (String) this.obtieneFlash().get(
					ParametrosFlashEnum.TITULAR_CUENTA.getParamFlash());
			this.apuntesBean.setNumCuenta(cuenta);
			this.muestraTitular = true;
			this.apuntesBean.setTitular(titular);
			comprobarTitular();
		}
	}

	/**
	 * Inicializa un nuevo apuntes Bean
	 * 
	 * @return
	 */
	private void inicializarBean() {
		this.apuntesBean.setMostrarUnidadesMillon(false);
		this.setTodoOk(false);
		this.setErrorTipoCuenta(false);
		this.apuntesBean.setFechaOperacion(new Date());
		this.apuntesBean.setHoraOperacion("00:00:00");
		this.apuntesBean.setCentro(this.contextoUtils.getSucursal());
		this.apuntesBean.setTerminal(this.contextoUtils.getTerminal());
		this.apuntesBean.setPuesto(this.contextoUtils.getEntidad());
		this.fechaActual = this.contextoUtils.getFechaContableActual();
		this.apuntesBean.setFechaValor(this.fechaActual);
		this.setDeshabilitarRadio(true);
	}

	/**
	 * Instancia el flash.
	 * 
	 * @return - Devuelve una instancia flash.
	 */
	public Flash obtieneFlash() {
		return FacesContext.getCurrentInstance().getExternalContext()
				.getFlash();
	}

	/**
	 * Función para adicionar alertas globales en la vista.
	 * 
	 * @param severity
	 *            Severidad de la alerta.
	 * @param title
	 *            Titulo de la alerta.
	 * @param message
	 *            Mensaje desplegado en la alerta.
	 */
	private void addMessage(FacesMessage.Severity severity, String title,
			String message) {
		FacesMessage facesMessage = new FacesMessage(severity, title, message);
		FacesContext.getCurrentInstance().addMessage(null, facesMessage);
	}

	/**
	 * Métodos navegación
	 * 
	 * @return
	 */

	public String inicio() {
		return NavegacionEnum.REALIZAR_ALTA_APUNTE.getRuta();
	}

	public String cancelar() {
		return NavegacionEnum.INICIO.getRuta();
	}

	/**
	 * Métodos de las vistas
	 */

	/**
	 * Limpia el Bean
	 */
	public void limpiar() {
		this.apuntesBean = new ApuntesBean();
		this.init();
		// this.inicializarBean();
	}

	/**
	 * Comprueba que el número de cuenta introducido corresponde a un cliente y
	 * en caso afirmativo muestra el nombre del cliente.
	 */
	public void comprobarTitular() {
		this.setMuestraTitular(false);
		this.setMuestraTitularError(false);
		ConsultaTitularRes respuesta = this.consultaTitular
				.ejecutarWS(this.apuntesBean.getNumCuenta().toString());
		if (respuesta != null && "0".equals(respuesta.getEstatus())) {
			this.muestraTitular = true;
			this.muestraTitularError = false;
			this.apuntesBean.setTitular(parseText(respuesta.getNombre()));
		} else {
			this.muestraTitularError = true;
			this.muestraTitular = true;
//			this.apuntesBean.setNumCuenta(null);
			this.apuntesBean.setTitular(parseText(respuesta.getMensaje()));
		}
	}
	
	/**
	 * Funcion que cambia el texto ACUERDO por LA CUENTA
	 * @param mensaje
	 * @return
	 */
	public String parseText(String mensaje){
		if(StringUtils.isNotBlank(mensaje)){
			mensaje.replace("ACUERDO", "LA CUENTA");
		}
		
		return mensaje;
	}

	/**
	 * Método para buscar mediante el buscador el número de cuenta
	 * 
	 * @return
	 */
	public String buscarPersona() {
		obtieneFlash().put(ParametrosFlashEnum.TIPO_BUSQUEDA.getParamFlash(),
				BusquedaEnum.CUENTAS.getBusquedaValor());
		managedBeanStateRecover.enviaController(this,
				NavegacionEnum.REALIZAR_ALTA_APUNTE);
		return NavegacionEnum.BUSQUEDA.getRuta();

	}

	/**
	 * Comprueba si el importe es superior o igual a 1M y muestra el campo
	 * unidades de millón si es cierto.
	 */
	public void mostrarUnidadesMillon() {
		Double importe = this.apuntesBean.getImporte().doubleValue();
		if (importe != null
				&& importe >= AltaApunteController.CTE_MOSTRAR_UNIDADES_MILLON) {
			this.apuntesBean.setMostrarUnidadesMillon(true);
		} else {
			this.apuntesBean.setMostrarUnidadesMillon(false);
			this.apuntesBean.setUnidadesMillon(null);
		}
	}

	/**
	 * Formatea la cadena del tipo de apunte.
	 * 
	 * @return
	 */
	public String mostrarTipo() {
		String tipo = this.apuntesBean.getTipoApunte();
		if ("C".equals(tipo)) {
			return "CARGO";
		} else {
			return "ABONO";
		}
	}

	/**
	 * Muestra el codigo de la transacción segun el tipo de apunte.
	 * 
	 * @return
	 */
	public String obtenerCodigoTransaccion() {
		String tipo = this.apuntesBean.getTipoApunte();
		if ("C".equals(tipo)) {
			return AltaApunteController.CARGO;
		} else {
			return AltaApunteController.ABONO;
		}
	}

	/**
	 * Obtiene la descripción de la operación desde el catálogo.
	 * 
	 * @return
	 */
	public String obtenerCodigoOperacion() {
		String descripcion = null;
		List<CatalogoBean> operaciones = this.catalogoUtils
				.getCatalogo(CatalogoEnum.TP_CLOP);
		for (CatalogoBean operacion : operaciones) {
			if (this.apuntesBean.getCodigoOperacion().equals(
					operacion.getClaveFila())) {
				descripcion = operacion.getDescripcionL();
				break;
			}
		}

		return descripcion;
	}

	/**
	 * Realizar la trn de apunte
	 */
	public String realizarApunte() {
		this.apuntesBean.setStatus(0);
		this.setTodoOk(false);
		this.apuntesBean = this.altaApuntesBackEnd
				.ejecutarTRN(this.apuntesBean);
		if (this.apuntesBean.getStatus() != 0) {
			if (AltaApunteController.ERROR_TIPO_CUENTA == this.apuntesBean
					.getStatus()) {
				this.setErrorTipoCuenta(true);
			} else {
				addMessage(FacesMessage.SEVERITY_FATAL, "¡Aviso!",
						"No se ha podido realizar la transacción debido a un error en el sistema.");

			}
			return null;

		} else {
			obtieneFlash().put(
					ParametrosFlashEnum.APUNTES_BEAN.getParamFlash(),
					this.apuntesBean);
			FacesContext.getCurrentInstance().getExternalContext().getFlash();
			return NavegacionEnum.DETALLE_ALTA_APUNTE.getRuta();
		}
	}

	/**
	 * Genera plantilla pdf.
	 */
	public void generaPlantillaDeposito() {
		Map<String, String> images = new HashMap<String, String>();
		images.put("Logo_bsfi_bn.png", "LOGO");

		StringBuffer nombrePdf = new StringBuffer(this.contextoUtils.getId());
		Map<String, Object> params = new HashMap<String, Object>();
		// params.put("plaza",this.buscarPlaza(this.plaza)); ejemplo.
		params.put("tipo", this.mostrarTipo());
		params.put("cuenta", String.valueOf(this.apuntesBean.getNumCuenta()));
		params.put("titular", this.apuntesBean.getTitular());
		params.put("codigo operacion", this.apuntesBean.getCodigoOperacion());
		params.put("operacion", this.obtenerCodigoOperacion());
		params.put("codigo transaccion", this.obtenerCodigoTransaccion());
		params.put("concepto", this.apuntesBean.getConcepto());
		params.put("fecha valor", this.apuntesBean.getFechaValor());
		params.put("importe", this.apuntesBean.getImporte().doubleValue());
		params.put("id transaccion", this.apuntesBean.getNumTransaccion());
		params.put("plaza",
				this.buscarPlaza(this.contextoUtils.getPlazaBancaria()));
		params.put("num oficina", this.contextoUtils.getSucursal());
		params.put("oficina",
				this.obtenerDescripcionCentro(this.contextoUtils.getSucursal()));

		pdfUtils.generaPdf("reporteApunteMutuaAjena.jrxml", params, images,
				null, nombrePdf.toString(), null);

	}

	public void mostrarConfirmacion() {
		CatalogoBean opcionCatalogo = catalogoUtils.getCatalogoBean(CatalogoEnum.TP_CLOP, this.apuntesBean.getCodigoOperacion());
		String claveInhabil = opcionCatalogo.getClaveFila().substring(opcionCatalogo.getClaveFila().length() - 4, 
				opcionCatalogo.getClaveFila().length());
		if("9999".equals(claveInhabil) || opcionCatalogo.getContenido().charAt(0) == 'I'){
			this.setTodoOk(false);
			RequestContext.getCurrentInstance().execute("PF('dlgOperacionNoPermitida').show()");
		}else{
			this.setTodoOk(true);
		}
	}

	public String buscarPlaza(String clave) {
		String descripcion = clave;
		List<CatalogoBean> catalogo = catalogoUtils
				.getCatalogo(CatalogoEnum.TP_PLAZA_BANCARIA);
		for (CatalogoBean elemento : catalogo) {
			if (elemento.getClaveFila().equals(clave.trim())) {
				descripcion = elemento.getDescripcionL();
			}
		}
		return descripcion;
	}

	public String obtenerDescripcionCentro(final String clave) {
		try {
			return catalogoCentros.getCatalogoBean(contextoUtils.getEntidad(),
					clave).getDescripcionC();
		} catch (ControlableException ce) {
			LOGGER.debug("Error al intentar obtener la descripcion del centro a partir del codigo: "
					+ clave);
			return "";
		}
	}
	
	public void cargarRadioOperacion(){
		CatalogoBean opcionCatalogo = catalogoUtils.getCatalogoBean(CatalogoEnum.TP_CLOP, this.apuntesBean.getCodigoOperacion());
		if(opcionCatalogo != null){
			if(opcionCatalogo.getContenido().charAt(1) == 'D' || opcionCatalogo.getContenido().charAt(1) == 'A'){
				this.apuntesBean.setTipoApunte("A");
				if(opcionCatalogo.getContenido().charAt(1) == 'A'){
					this.setDeshabilitarRadio(false);
				}else{
					this.setDeshabilitarRadio(true);
				}
			} else if(opcionCatalogo.getContenido().charAt(1) == 'H') {
				this.apuntesBean.setTipoApunte("C");
			}
		}
	}

	/**
	 * GETTERS & SETTERS
	 */

	/**
	 * @return the dateFormat
	 */
	public static String getDateFormat() {
		return DATE_FORMAT;
	}

	/**
	 * @return the cteEspacio
	 */
	public static String getCteEspacio() {
		return CTE_ESPACIO;
	}

	/**
	 * @return the cteCero
	 */
	public static String getCteCero() {
		return CTE_CERO;
	}

	/**
	 * @return the fechaActual
	 */
	public Date getFechaActual() {
		return fechaActual;
	}

	/**
	 * @param fechaActual
	 *            the fechaActual to set
	 */
	public void setFechaActual(Date fechaActual) {
		this.fechaActual = fechaActual;
	}

	/**
	 * @return the muestraTitular
	 */
	public boolean isMuestraTitular() {
		return muestraTitular;
	}

	/**
	 * @param muestraTitular
	 *            the muestraTitular to set
	 */
	public void setMuestraTitular(boolean muestraTitular) {
		this.muestraTitular = muestraTitular;
	}

	/**
	 * @return the muestraTitularError
	 */
	public boolean isMuestraTitularError() {
		return muestraTitularError;
	}

	/**
	 * @param muestraTitularError
	 *            the muestraTitularError to set
	 */
	public void setMuestraTitularError(boolean muestraTitularError) {
		this.muestraTitularError = muestraTitularError;
	}

	/**
	 * @return the altaApunte
	 */
	public static String getAltaApunte() {
		return ALTA_APUNTE;
	}

	/**
	 * @return the codigosOperacion
	 */
	public List<CatalogoBean> getCodigosOperacion() {
		return codigosOperacion;
	}

	/**
	 * @param codigosIdentificacion
	 *            the codigosOperacion to set
	 */
	public void setCodigosOperacion(List<CatalogoBean> codigosOperacion) {
		this.codigosOperacion = codigosOperacion;
	}

	/**
	 * @return the apuntesBean
	 */
	public ApuntesBean getApuntesBean() {
		return apuntesBean;
	}

	/**
	 * @param apuntesBean
	 *            the apuntesBean to set
	 */
	public void setApuntesBean(ApuntesBean apuntesBean) {
		this.apuntesBean = apuntesBean;
	}

	/**
	 * @return the todoOk
	 */
	public boolean isTodoOk() {
		return todoOk;
	}

	/**
	 * @param todoOk
	 *            the todoOk to set
	 */
	public void setTodoOk(boolean todoOk) {
		this.todoOk = todoOk;
	}

	public boolean isErrorTipoCuenta() {
		return errorTipoCuenta;
	}

	public void setErrorTipoCuenta(boolean errorTipoCuenta) {
		this.errorTipoCuenta = errorTipoCuenta;
	}

	public static Integer getErrorTipoCuenta() {
		return ERROR_TIPO_CUENTA;
	}

	public static String getCargo() {
		return CARGO;
	}

	public static String getAbono() {
		return ABONO;
	}

	public String getFechaMinima() {
		return fechaMinima;
	}

	public void setFechaMinima(String fechaMinima) {
		this.fechaMinima = fechaMinima;
	}

	public boolean isDeshabilitarRadio() {
		return deshabilitarRadio;
	}

	public void setDeshabilitarRadio(boolean deshabilitarRadio) {
		this.deshabilitarRadio = deshabilitarRadio;
	}

}
