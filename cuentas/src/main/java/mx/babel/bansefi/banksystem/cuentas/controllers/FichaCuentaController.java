package mx.babel.bansefi.banksystem.cuentas.controllers;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.sesion.utils.ContextoUtils;
import mx.babel.bansefi.banksystem.base.backends.ConsultaCuentaBackend;
import mx.babel.bansefi.banksystem.base.backends.ConsultaRelacionPersonaCuentaBackEnd;
import mx.babel.bansefi.banksystem.base.backends.anotaciones.ConsultaListaAnotacionesBackEnd;
import mx.babel.bansefi.banksystem.base.backends.busquedas.BusquedaCuentaBackEnd;
import mx.babel.bansefi.banksystem.base.backends.busquedas.BusquedaNivelCuentaBackEnd;
import mx.babel.bansefi.banksystem.base.beans.DepositoIPFBean;
import mx.babel.bansefi.banksystem.base.beans.domain.AnotacionBean;
import mx.babel.bansefi.banksystem.base.beans.domain.CuentaBean;
import mx.babel.bansefi.banksystem.base.beans.domain.TipoCliente;
import mx.babel.bansefi.banksystem.base.beans.domain.relacioncuenta.CuentaRelacionadaBean;
import mx.babel.bansefi.banksystem.base.beans.domain.relacioncuenta.RelacionadoBean;
import mx.babel.bansefi.banksystem.base.beans.domain.relacioncuenta.TipoRelacionPersonaCuenta;
import mx.babel.bansefi.banksystem.base.constants.ConstantesFuncionales;
import mx.babel.bansefi.banksystem.base.enums.BusquedaEnum;
import mx.babel.bansefi.banksystem.base.enums.CatalogoEnum;
import mx.babel.bansefi.banksystem.base.enums.EstadosCuentaEnum;
import mx.babel.bansefi.banksystem.base.enums.NavegacionEnum;
import mx.babel.bansefi.banksystem.base.enums.ParametrosFlashEnum;
import mx.babel.bansefi.banksystem.base.utils.AnotacionPrioridadComparator;
import mx.babel.bansefi.banksystem.base.utils.CatalogoCentrosLoaderTask;
import mx.babel.bansefi.banksystem.base.utils.CatalogoUtils;
import mx.babel.bansefi.banksystem.base.utils.EstadosCuentaEnumUtils;
import mx.babel.bansefi.banksystem.base.utils.ManagedBeanStateRecover;
import mx.babel.bansefi.banksystem.base.utils.PdfUtils;
import mx.babel.bansefi.banksystem.cuentas.backends.AnularConstitucionAcuerdoBackend;
import mx.babel.bansefi.banksystem.cuentas.backends.ConsultaPlazoBackend;
import mx.babel.bansefi.banksystem.cuentas.backends.ConsultaRelacionesCuentaCuentasBackEnd;
import mx.babel.bansefi.banksystem.cuentas.backends.ConsultaStatusCuentaBackend;
import mx.babel.bansefi.banksystem.cuentas.beans.ConsultaAnulacionCuentaBean;
import mx.babel.bansefi.banksystem.cuentas.beans.TarifaBean;
import mx.babel.bansefi.banksystem.cuentas.utils.AltaIPFUtils;
import mx.babel.bansefi.banksystem.cuentas.utils.CambiosEstadosCuentasUtils;
import mx.babel.bansefi.banksystem.cuentas.utils.TarifasUtils;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Controlador encargado del manejo de la ficha de cuenta.
 * 
 * @author javier.martinnino
 * 
 */
@ManagedBean(name = "fichaCuentaController")
@ViewScoped
@Component
@Scope("view")
public class FichaCuentaController implements Serializable {

	private static final long serialVersionUID = 3776319667811267778L;

	private static final Logger LOGGER = LogManager
			.getLogger(FichaCuentaController.class.getName());

	private static final String NOMBREPDF = "cuentaClabeReporte.jrxml";
	private static final String COD_LINEA_STATUS = "01";

	private static final int NUM_ANOTACIONES = 6;

	@Autowired
	PdfUtils pdfUtils;

	@Autowired
	ContextoUtils contextoUtils;

	@Autowired
	EstadosCuentaEnumUtils estadosCuentaEnumUtils;

	@Autowired
	CambiosEstadosCuentasUtils cambiosEstadosCuentasUtils;

	@Autowired
	CatalogoUtils catalogoUtils;

	@Autowired
	AltaIPFUtils altaIPFUtils;

	@Autowired
	CatalogoCentrosLoaderTask catalogoCentros;

	@Autowired
	ConsultaCuentaBackend consultaCuentaBackend;

	@Autowired
	BusquedaNivelCuentaBackEnd busquedaNivelCuentaBackEnd;

	@Autowired
	ConsultaStatusCuentaBackend consultaStatusCuentaBackend;

	@Autowired
	ConsultaRelacionPersonaCuentaBackEnd consultaRelacionPersonaCuentaBackEnd;

	@Autowired
	ConsultaRelacionesCuentaCuentasBackEnd consultaRelacionesCuentaCuentasBackEnd;

	@Autowired
	ConsultaListaAnotacionesBackEnd consultaListaAnotacionesBackEnd;

	@Autowired
	ManagedBeanStateRecover managedBeanStateRecover;

	@Autowired
	TarifasUtils tarifaUtils;

	@Autowired
	AnularConstitucionAcuerdoBackend anularConstitucionAcuerdoBackend;

	@Autowired
	private BusquedaCuentaBackEnd busquedaCuentaBackEnd;

	@Autowired
	ConsultaPlazoBackend consultaPlazoBackend;

	/**
	 * Variable utilizada para guardar la redireccion al flujo de entrada a la
	 * consulta de ficha de cuenta.
	 */
	private NavegacionEnum destino;

	/**
	 * Variable utilizada para guardar el contenido del controller origen a la
	 * consulta de ficha de cuenta.
	 */
	private Object destinoController;

	/**
	 * Variable para almacenar un cuentaBean.
	 */
	private CuentaBean cuentaBean;

	/**
	 * Variable para saber si hay que mostrar el desplegable de
	 * personasRelacionadas.
	 */
	private boolean mostrarPersonasRelacionadas;

	/**
	 * Variable para saber si hay que mostrar el desplegable de status.
	 */
	private boolean mostrarStatus;

	/**
	 * Variable para saber si el status es de cartera vigente o vencida.
	 */
	private boolean statusVigente;

	/**
	 * Variable para saber si se pulso alguna vez el detalle de status para no
	 * volver a llamar a la TRN.
	 */
	private boolean statusDetalle;

	/**
	 * Variable para saber si se pulso alguna vez el detalle de
	 * cuentasRelacionadas para no volver a llamarla.
	 */
	private boolean cuentasRelacionadasDetalle;

	/**
	 * Variable para saber cuantas anotaciones se renderizan en la cabecera de
	 * la ficha de cuenta.
	 */
	private int anotacionesVisibles;

	/**
	 * Variable para saber si se recupero informacion para renderizar en la
	 * vista.
	 */
	private boolean elementosVisibles;

	/**
	 * Opcion elegida para el estatus de estado de cuenta a modificar
	 */
	private EstadosCuentaEnum estadoCuentaSeleccionado;

	/**
	 * Variable para alamcenar TarifaBean
	 */
	private TarifaBean tarifa;

	/**
	 * Variable para indicar si la cuenta pertenece a una tarifa tipo plazo
	 */
	private boolean esPlazo;

	/**
	 * Variable para almacenar el titular 1 de la cuenta
	 */
	private RelacionadoBean titular1;

	// Mensaje de respuesta al cambio de estado en la cuenta
	private String mensaje;
	//Class dialogo respuesta cambio
	private String claseEstadoRespuesta;
	// Mensaje dinamico de confirmacion
	private String mensajeConfirmacion;
	private Map<Boolean, String> respuestaCambioEstado;
	
	private Boolean origenBusqueda;
	
	private Exception initException;

	@PostConstruct
	public void init() {

		try{
			if (this.obtieneFlash().get(
					ParametrosFlashEnum.NAVEGACION_MANAGED_BEAN.getParamFlash()) != null) {
				if ((Boolean) this.obtieneFlash()
						.get(ParametrosFlashEnum.NAVEGACION_MANAGED_BEAN
								.getParamFlash())) {
					LOGGER.debug("Accedemos al metodo de inicio init(), iniciando la navegacion tras volver de otro flujo");
					this.destino = this.managedBeanStateRecover.getDestino();
					this.destinoController = this.managedBeanStateRecover
							.getController();
					this.inicializaDatosFichaCuenta();
				} else {
					LOGGER.debug("Accedemos al metodo de inicio init(), recuperando controller tras navegacion");
					this.managedBeanStateRecover.recuperaController(this);
					this.recargaRelacionesCuenta();
    // RAV 2016 03 30 INI REFRESCAR LA VISTA DEL GRUPO DE PERSONAS RELACIONADAS EN LA FICHA DE CUENTA CUANDO SE DAN DE ALTA.
					this.mostrarPersonasRelacionadas = this.comprobarMostrarPersonasRelacionadas();					
    // RAV 2016 03 30 FIN REFRESCAR LA VISTA DEL GRUPO DE PERSONAS RELACIONADAS EN LA FICHA DE CUENTA CUANDO SE DAN DE ALTA.
				}
			} else {
				LOGGER.debug("Accedemos al metodo de inicio init() directamente sin acceso de navegacion");
				this.inicializaDatosFichaCuenta();
			}
			if(this.obtieneFlash().get(
					ParametrosFlashEnum.ORIGEN_BUSQUEDA.getParamFlash()) != null){
				this.origenBusqueda = (Boolean) this.obtieneFlash().get(
						ParametrosFlashEnum.ORIGEN_BUSQUEDA.getParamFlash());
			}else{
				this.origenBusqueda = false;
			}
		}catch(ControlableException c){
			initException = c;
		}catch(NoControlableException n){
			initException = n;
		}

	}

	/**
	 * @return Metodo utilizado para inicializar el controller
	 * 
	 */
	public void inicializaDatosFichaCuenta() {
		LOGGER.debug("Accedemos al metodo de inicio init() de la ficha de cuenta: ENTRADA");

		this.elementosVisibles = false;
		this.mostrarStatus = false;

		if (obtieneFlash().get(ParametrosFlashEnum.CUENTA_BEAN.getParamFlash()) != null) {

			// Recupero el controler y redireccion origen
			this.destino = this.managedBeanStateRecover.getDestino();
			this.destinoController = this.managedBeanStateRecover
					.getController();

			LOGGER.debug("Objeto CuentaBean encontrado: Procedemos a recuperarlo");
			this.cuentaBean = (CuentaBean) obtieneFlash().get(
					ParametrosFlashEnum.CUENTA_BEAN.getParamFlash());

			LOGGER.debug("Consultamos los datos de cuenta");
			try{
				this.cuentaBean = this.consultaCuentaBackend
					.ejecutarTRN(this.cuentaBean.getNumeroCuenta());
			}catch(ControlableException ce){
			}
			if (this.cuentaBean == null){
				LOGGER.debug("Objeto CuentaBean no encontrado: Procedemos a mostrar mensaje de cuenta no disponible");
				FacesContext
						.getCurrentInstance()
						.addMessage(
								"messages",
								new FacesMessage(FacesMessage.SEVERITY_WARN,
										"¡Atención!",
										"La información de la cuenta no se encuentra disponible por el momento."));
				return;
			}
			
			// Si los elementos ya están cargados no se recargan
			if (!(this.cuentaBean.getPersonasRelacionadas() != null && this.cuentaBean
					.getPersonasRelacionadas().size() > 0)) {
				try{
					this.cuentaBean.setNivelCuenta(this.busquedaNivelCuentaBackEnd
						.ejecutarTRN(this.cuentaBean.getNumeroCuenta()));
				}catch(ControlableException e){
				}

				LOGGER.debug("Consultamos los datos de personas relacionadas a cuenta y comprobamos si hay que mostrarlos");
				try{
					this.cuentaBean
						.setPersonasRelacionadas(this.consultaRelacionPersonaCuentaBackEnd
								.ejecutarTRN(this.cuentaBean.getNumeroCuenta(),
										false));
				}catch(ControlableException e){
				}

				LOGGER.debug("Consultamos las anotaciones de cuentas y ordenamos por prioridad y fecha");
				try{
					this.cuentaBean
						.setAnotaciones(this.consultaListaAnotacionesBackEnd
								.ejecutarTRN(this.cuentaBean.getNumeroCuenta(),
										true));
				}catch(ControlableException e){
				}
			}

			this.titular1 = encontrarTitular();

			Collections.sort(this.cuentaBean.getAnotaciones(),
					new AnotacionPrioridadComparator());

			LOGGER.debug("Establecemos el numero de anotaciones que se visualizaran inicialmente en la vista");
			this.establecerAnotacionesVisibles();

			LOGGER.debug("Comprobamos si hay que mostrar los datos de personas relacionadas");
			this.mostrarPersonasRelacionadas = this
					.comprobarMostrarPersonasRelacionadas();

			LOGGER.debug("Comprobamos si hay que mostrar el fieldset de status de cuenta");
			this.mostrarStatus = this.comprobarMostrarStatus();

			this.elementosVisibles = true;
			final TarifaBean tarifaBean = new TarifaBean();
			tarifaBean.setLinea(cuentaBean.getCodLinea());
			tarifaBean.setGrupo(cuentaBean.getIdGrupoProducto());
			this.esPlazo = TarifasUtils.esPlazo(tarifaBean);
			if (this.esPlazo()) {
				final List<DepositoIPFBean> datosSubacuerdos = consultaPlazoBackend
						.ejecutarTRN(this.cuentaBean.getNumeroCuenta(), "TODOS");
				if (null != datosSubacuerdos) {
					this.cuentaBean.setIpfs(datosSubacuerdos);
				}
			}
		} else {

			LOGGER.debug("Objeto CuentaBean no encontrado: Procedemos a mostrar mensaje de cuenta no disponible");
			FacesContext
					.getCurrentInstance()
					.addMessage(
							"messages",
							new FacesMessage(FacesMessage.SEVERITY_WARN,
									"¡Atención!",
									"La información de la cuenta no se encuentra disponible por el momento."));
		}

		LOGGER.debug("Accedemos al metodo de inicio init() de la ficha de cuenta: SALIDA");
	}
	
	/**
	 * Método encargado de verificar si hay excepciones en el postConstruct.
	 */
	public void verificarAlertas(){
		if(initException != null){
			if(initException instanceof NoControlableException){
				throw (NoControlableException)initException;
			}else{
				throw (ControlableException)initException;
			}
		}
	}

	/**
	 * Función para obtener la descripcion del tipo de documento.
	 * 
	 * @return la descripcion del tipo de documento
	 */
	public String obtenerDescripcionTpDoc(final String clave) {
		if(StringUtils.isNotBlank(clave)){
			try {
				return catalogoUtils.getCatalogoBean(CatalogoEnum.TP_ID_EXT_PERS,
						clave).getDescripcionL();
			} catch (final ControlableException ce) {
				LOGGER.debug(
						"Error al intentar obtener la descripcion del tipo de documento a partir del codigo: "
								+ clave, ce);
				
			}
		}
		
		return "";
	}

	/**
	 * Función para obtener la descripcion del nombre del centro.
	 * 
	 * @return la descripcion del centro
	 */
	public String obtenerDescripcionCentro(final String clave) {
		if(StringUtils.isNotBlank(clave)){
			try {
				return catalogoCentros.getCatalogoBean(contextoUtils.getEntidad(),
						clave).getDescripcionC();
			} catch (final ControlableException ce) {
				LOGGER.debug(
						"Error al intentar obtener la descripcion del centro a partir del codigo: "
								+ clave, ce);				
			}
		}
		
		return "";
	}

	/**
	 * Función para obtener la descripcion del estado de la cuenta.
	 * 
	 * @return la descripcion del estado de la cuenta
	 */
	public String obtenerDescripcionEstado(final String clave) {
		if(StringUtils.isNotBlank(clave)){
			try {
				return catalogoUtils.getCatalogoBean(CatalogoEnum.TP_ECV_AC, clave)
						.getDescripcionL();
			} catch (final ControlableException ce) {
				LOGGER.debug(
						"Error al intentar obtener la descripcion del estado a partir del codigo: "
								+ clave, ce);			
			}
		}
		
		return "";
	}

	/**
	 * Función para obtener la descripcion del tipo de relacion de cuenta
	 * 
	 * @return la descripcion del tipo de relacion de la cuenta
	 */
	public String obtenerDescripcionTipoRelacion(final String clave) {
		if(StringUtils.isNotBlank(clave)){
			try {
				return catalogoUtils.getCatalogoBean(CatalogoEnum.TP_RL_AC_AC,
						clave).getDescripcionL();
			} catch (final ControlableException ce) {
				LOGGER.debug(
						"Error al intentar obtener la descripcion del tipo de relacion a partir del codigo: "
								+ clave, ce);			
			}
		}
		return "";
	}

	/**
	 * @return String - La descripcion del subcodigo de la anotacion buscada
	 * 
	 */
	public String obtenerSubCodigoAnotacion(final String clave) {
		if(StringUtils.isNotBlank(clave)){
			try {
				return catalogoUtils.getCatalogoBean(CatalogoEnum.TP_ANTCN, clave)
						.getDescripcionL();
			} catch (final ControlableException ce) {
				LOGGER.debug(
						"Error al intentar obtener la descripcion del subcodigo de anotacion a partir del codigo: "
								+ clave, ce);				
			}
		}
		return "";
	}

	/**
	 * Función para obtener la descripcion del tipo de la anotacion.
	 * 
	 * @return la descripcion del tipo de la anotacion
	 */
	public String getTipoAnotacion(final String clave) {
		if(StringUtils.isNotBlank(clave)){
			try {
				return catalogoUtils.getCatalogoBean(CatalogoEnum.TIPO_ANOTACION,
						clave).getDescripcionL();
			} catch (final ControlableException ce) {
				LOGGER.debug(
						"Error al intentar obtener la descripcion del tipo de anotacion a partir del codigo: "
								+ clave, ce);			
			}
		}
		return "";
	}

	/**
	 * Método utilizado para comprobar si procede visualiza el fieldset de
	 * personas relacionadas
	 * 
	 */
	public void establecerAnotacionesVisibles() {
		int numAnotacionesVisibles = 0;
		if (this.cuentaBean != null && this.cuentaBean.getAnotaciones() != null) {
			if (this.cuentaBean.getAnotaciones().size() > NUM_ANOTACIONES) {
				numAnotacionesVisibles = FichaCuentaController.NUM_ANOTACIONES;
			} else {
				numAnotacionesVisibles = this.cuentaBean.getAnotaciones()
						.size();
			}
		}
		this.anotacionesVisibles = numAnotacionesVisibles;
	}

	/**
	 * Método para encontrar el titular 1 de la cuenta
	 * 
	 * @return titular 1
	 */
	private RelacionadoBean encontrarTitular() {
		for (final RelacionadoBean relacionado : this.cuentaBean
				.getPersonasRelacionadas()) {
			if (TipoRelacionPersonaCuenta.TITULAR.equals(relacionado.getTipo())) {
				if (relacionado.getNumero() == 1) {
					return relacionado;
				}
			}
		}
		return null;
	}

	/**
	 * Método para verificar si una relación es de tipo titular 1.
	 * 
	 * @param relacionado
	 *            Bean de la relación a verificar
	 * @return <code>true</code>. si la relación es de tipo titular.
	 */
	public Boolean isTitular1(final RelacionadoBean relacionado) {
		Boolean esTitular1 = false;
		if (relacionado != null 
				&&TipoRelacionPersonaCuenta.TITULAR.equals(relacionado.getTipo())
				&& relacionado.getNumero() == 1) {
			esTitular1 = true;
		}
		return esTitular1;
	}

	/**
	 * Método utilizado para comprobar si procede visualiza el fieldset de
	 * personas relacionadas
	 * 
	 */
	public boolean comprobarMostrarPersonasRelacionadas() {
		boolean visualizar = true;
		if (this.cuentaBean != null 
				&&this.cuentaBean.getPersonasRelacionadas() != null
				&& this.cuentaBean.getPersonasRelacionadas().size() == 1
				&& this.cuentaBean.getPersonasRelacionadas().get(0).getTipo()
						.equals(TipoRelacionPersonaCuenta.TITULAR)
				&& this.cuentaBean.getPersonasRelacionadas().get(0).getNumero() == 1) {
			visualizar = false;
		} else if (this.cuentaBean != null 
				&& (this.cuentaBean.getPersonasRelacionadas() == null
				|| this.cuentaBean.getPersonasRelacionadas().isEmpty())) {
			visualizar = false;
		}
		return visualizar;
	}

	/**
	 * Método utilizado para comprobar si procede visualizar el fieldset del
	 * status de cuenta.
	 * 
	 */
	public boolean comprobarMostrarStatus() {
		boolean visualizar = false;
		if (this.cuentaBean != null 
				&& this.cuentaBean.getCuentasRelacionadas() != null
				&& COD_LINEA_STATUS
						.equals(this.cuentaBean.getCodLinea())) {
			visualizar = true;
		}
		return visualizar;
	}

	/**
	 * Método utilizado para consultar el detalle del status de la cuenta.
	 * 
	 */
	public void consultaStatusDetalle() {
		if (this.cuentaBean != null 
				&& !this.statusDetalle
				&& this.cuentaBean.getNumeroCuenta() != null) {
			setStatusDetalle(true);
			// TODO: Incluir la logica para pintar el status de cuenta vigente o
			// vencida y llamar solo en caso de productos que proceden
			// TODO: Incluir el mensaje de no hay informacion para el producto
			// en caso de que no vengan datos
			this.cuentaBean
					.setStatusCuentaBean(this.consultaStatusCuentaBackend
							.ejecutarTRN(this.cuentaBean.getNumeroCuenta()));
			// TODO: Obtener status para ver que se pinta si vigente o vencida
			this.statusVigente = true;
		}
	}

	/**
	 * Método utilizado para consultar el detalle de las cuentas relacionadas a
	 * la cuenta.
	 * 
	 */
	public void consultaCuentasRelacionadas() {
		if (!this.cuentasRelacionadasDetalle) {
			setCuentasRelacionadasDetalle(true);
			recargaRelacionesCuenta();
		}
	}
	
	public void recargaRelacionesCuenta(){
		this.cuentaBean
		.setCuentasRelacionadas(this.consultaRelacionesCuentaCuentasBackEnd
				.ejecutarTRN(this.cuentaBean.getNumeroCuenta(),true));
	}

	/**
	 * Método utilizado para mostrar todas las anotaciones.
	 * 
	 */
	public void mostrarTodasAnotaciones() {
		if(this.cuentaBean != null && this.cuentaBean.getAnotaciones() != null){
			this.setAnotacionesVisibles(this.cuentaBean.getAnotaciones().size());
		}
	}

	/**
	 * Método que invoca a la plantilla 'cuentaClabeReporte.jrxml' para generar
	 * la impresion del reporte
	 */
	public void generarReporteCuentaClabe() {
		if (cuentaBean != null && cuentaBean.getNumeroCuenta() != null
				&& cuentaBean.getNombreTitular() != null
				&& cuentaBean.getTipoCuenta() != null
				&& cuentaBean.getMoneda() != null
				&& cuentaBean.getCuentaClabe() != null) {
			final Map<String, String> imagen = new HashMap<String, String>();
			imagen.put("logo_top.png", "logo");

			final Map<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("numeroCuenta", cuentaBean.getNumeroCuenta());
			parametros.put("nombreTitular", cuentaBean.getNombreTitular());
			parametros.put("tipoCuenta", cuentaBean.getTipoCuenta());
			parametros.put("moneda", cuentaBean.getMoneda());
			parametros.put("cuentaClabe", cuentaBean.getCuentaClabe());

			pdfUtils.generaPdf(NOMBREPDF, parametros, imagen, null,
					contextoUtils.getId(), null);
		} else {
			RequestContext.getCurrentInstance().execute(
					"PF('dlgErrorReporte').show()");
		}
	}

	/**
	 * Método privado que obtiene el objeto Flash proveniente del contexto
	 * externo.
	 * 
	 * @return Flash
	 */
	private Flash obtieneFlash() {
		return FacesContext.getCurrentInstance().getExternalContext()
				.getFlash();
	}

	/**
	 * Método que consulta la ficha de cliente del cliente seleccionado en las
	 * personas relacionadas.
	 * 
	 * @return La URL a la que redireccionará
	 */
	public String verFichaCliente(final RelacionadoBean persona) {
		if(persona != null && StringUtils.isNotBlank(persona.getCodPe())){
			obtieneFlash().put(ParametrosFlashEnum.ID_INTERNA.getParamFlash(),
					persona.getPersona().getIdInterna());
			obtieneFlash().put(ParametrosFlashEnum.TIPO_CLIENTE.getParamFlash(),
					TipoCliente.codPeDe(persona.getCodPe()));
			managedBeanStateRecover.enviaController(this,
					NavegacionEnum.FICHA_CUENTA);
			return NavegacionEnum.FICHA_CLIENTE.getRuta();
		}else
			return null;
	}

	/**
	 * Método que consulta la ficha de cuenta de la cuenta seleccionada entre
	 * las cuentas relacionadas.
	 * 
	 * @return La URL a la que redireccionará
	 */
	public String verFichaCuenta(final CuentaRelacionadaBean cuenta) {
		if(cuenta != null){
			obtieneFlash().put(ParametrosFlashEnum.CUENTA_BEAN.getParamFlash(),
					cuenta.getCuenta());
			managedBeanStateRecover.enviaController(this,
					NavegacionEnum.FICHA_CUENTA);
			return NavegacionEnum.FICHA_CUENTA.getRuta();
		}else
			return null;
	}

	public boolean isCreditoPlazo() {
		boolean plazo, credito;
		tarifa = new TarifaBean();
		this.tarifa.setLinea(this.cuentaBean.getCodLinea());
		this.tarifa.setGrupo(this.cuentaBean.getIdGrupoProducto());
		this.tarifa.setProducto(this.cuentaBean.getIdProducto());
		this.tarifa.setId(this.cuentaBean.getIdTarifaProducto());
		plazo = TarifasUtils.esPlazo(this.tarifa);
		credito = TarifasUtils.isPrestamoCredito(this.tarifa);
		if (plazo == true || credito == true) {
			return false;
		}
		return true;
	}

	/**
	 * Método que consulta el detalle de la anotacion seleccionada
	 * 
	 * @return La URL a la que redireccionará
	 */
	public String detalleAnotacion(final AnotacionBean anotacionBean) {
		obtieneFlash().put(ParametrosFlashEnum.ANOTACION_BEAN.getParamFlash(),
				anotacionBean);
		managedBeanStateRecover.enviaController(this,
				NavegacionEnum.FICHA_CUENTA);
		return NavegacionEnum.DETALLE_ANOTACIONES.getRuta();
	}

	/**
	 * Método que consulta los informes de clasificaciones.
	 * 
	 * @return La URL a la que redireccionará
	 */
	public String consultaClasificacion() {
		obtieneFlash().put(ParametrosFlashEnum.CUENTA_BEAN.getParamFlash(),
				this.cuentaBean);
		managedBeanStateRecover.enviaController(this,
				NavegacionEnum.FICHA_CUENTA);
		return NavegacionEnum.CLASIFICACION_CUENTA.getRuta();
	}

	/**
	 * Método que consulta la informaacion derivada de la cuenta
	 * 
	 * @return La URL a la que redireccionará
	 */
	public String consultaDerivada() {
		obtieneFlash().put(ParametrosFlashEnum.CUENTA_BEAN.getParamFlash(),
				this.cuentaBean);
		managedBeanStateRecover.enviaController(this,
				NavegacionEnum.FICHA_CUENTA);
		return NavegacionEnum.INFORMACION_DERIVADA.getRuta();
	}

	/**
	 * Método que consulta las planificaciones
	 */
	public String consultaPlanificaciones() {
		obtieneFlash().put(ParametrosFlashEnum.CUENTA_BEAN.getParamFlash(),
				this.cuentaBean);
		managedBeanStateRecover.enviaController(this,
				NavegacionEnum.FICHA_CUENTA);
		return NavegacionEnum.CONSULTA_PLANIFICACIONES.getRuta();
	}

	/**
	 * Función para avanzar a la ventana de consulta posición.
	 * 
	 * @return La URL a la que redireccionará
	 */
	public String consultaPosicion() {
		obtieneFlash().put(ParametrosFlashEnum.CUENTA_BEAN.getParamFlash(),
				this.cuentaBean);
		managedBeanStateRecover.enviaController(this,
				NavegacionEnum.FICHA_CUENTA);
		return NavegacionEnum.CONSULTA_POSICION.getRuta();
	}

	/**
	 * Función para avanzar a la ventana de consulta de liquidaciones.
	 * 
	 * @return La URL a la que redireccionará
	 */
	public String consultaLiquidaciones() {
		obtieneFlash().put(ParametrosFlashEnum.CUENTA_BEAN.getParamFlash(),
				this.cuentaBean);
		managedBeanStateRecover.enviaController(this,
				NavegacionEnum.FICHA_CUENTA);
		return NavegacionEnum.CONSULTA_LIQUIDACIONES.getRuta();
	}

	/**
	 * Función para avanzar a la ventana de consulta domicilios.
	 * 
	 * @return La URL a la que redireccionará
	 */
	public String consultaDomicilios() {
		obtieneFlash().put(ParametrosFlashEnum.CUENTA_BEAN.getParamFlash(),
				this.cuentaBean);
		managedBeanStateRecover.enviaController(this,
				NavegacionEnum.FICHA_CUENTA);
		return NavegacionEnum.DOMICILIOS_CUENTA.getRuta();
	}

	/**
	 * Función para avanzar a la ventana de consulta de bloqueos de cuenta.
	 * 
	 * @return La URL a la que redireccionará
	 */
	public String consultaBloqueos() {
		obtieneFlash().put(ParametrosFlashEnum.CUENTA_BEAN.getParamFlash(),
				this.cuentaBean);
		managedBeanStateRecover.enviaController(this,
				NavegacionEnum.FICHA_CUENTA);
		return NavegacionEnum.BLOQUEO_CUENTA_1.getRuta();
	}

	/**
	 * Función para avanzar a la ventana de consulta general de apuntes.
	 * 
	 * @return La URL a la que redireccionará
	 */
	public String consultaApuntes() {
		obtieneFlash().put(ParametrosFlashEnum.CUENTA_BEAN.getParamFlash(),
				this.cuentaBean);
		this.managedBeanStateRecover.enviaController(this,
				NavegacionEnum.FICHA_CUENTA);
		return NavegacionEnum.BUSQUEDA_APUNTE.getRuta();
	}

	/**
	 * Función para avanzar a la ventana de consulta general de saldos.
	 * 
	 * @return La URL a la que redireccionará
	 */
	public String consultaSaldos() {
		obtieneFlash().put(ParametrosFlashEnum.CUENTA_BEAN.getParamFlash(),
				this.cuentaBean);
		managedBeanStateRecover.enviaController(this,
				NavegacionEnum.FICHA_CUENTA);
		return NavegacionEnum.SALDOS_CUENTA.getRuta();
	}

	/**
	 * Obtiene la URL que apunta a Datos Adacionales
	 * 
	 * @return URL de datos adicionales
	 * @author Aramis Oswaldo Gadea Gerónimo &lt;gadea@uanlmexico.mx&gt;
	 */
	public String consultaDatosAdicionales() {
		obtieneFlash().put(ParametrosFlashEnum.CUENTA_BEAN.getParamFlash(),
				this.cuentaBean);
		managedBeanStateRecover.enviaController(this,
				NavegacionEnum.FICHA_CUENTA);
		return NavegacionEnum.DATOS_ADICIONALES.getRuta();
	}

	/**
	 * Obtiene la URL que apunta a Centro Asociado
	 * 
	 * @return URL de centro asociado
	 * @author Natalio Iván Mendoza Sánchez &lt;nmendoza@uanlmexico.mx&gt;
	 */
	public String consultaCentroAsociado() {
		obtieneFlash().put(ParametrosFlashEnum.CUENTA_BEAN.getParamFlash(),
				this.cuentaBean);
		managedBeanStateRecover.enviaController(this,
				NavegacionEnum.FICHA_CUENTA);
		return NavegacionEnum.CENTRO_ASOCIADO.getRuta();
	}

	/**
	 * Función para avanzar a la ventana de consulta de auditoria de cuentas.
	 * 
	 * @return La URL a la que redireccionará
	 */
	public String consultaAuditoriaCuentas() {
		obtieneFlash().put(ParametrosFlashEnum.CUENTA_BEAN.getParamFlash(),
				this.cuentaBean);
		managedBeanStateRecover.enviaController(this,
				NavegacionEnum.FICHA_CUENTA);

		return NavegacionEnum.CONSULTA_AUDITORIA_CUENTAS.getRuta();
	}

	/**
	 * Función para avanzar a la vista de relacionar personas con cuenta
	 * 
	 * @return URL de la vista a redireccionar
	 */
	public String relacionarPersonas() {
		obtieneFlash().put(ParametrosFlashEnum.CUENTA_BEAN.getParamFlash(),
				this.cuentaBean);
		obtieneFlash().put(
				ParametrosFlashEnum.MODIFICACION_CUENTA.getParamFlash(), true);
		managedBeanStateRecover.enviaController(this,
				NavegacionEnum.FICHA_CUENTA);
		return NavegacionEnum.RELACIONES_CUENTA_PERSONAS.getRuta();
	}

	/**
	 * Función para avanzar a la vista de emitir documentos
	 * 
	 * @return URL de la vista a redireccionar
	 */
	public String emisionDocumentos() {
		obtieneFlash().put(ParametrosFlashEnum.CUENTA_BEAN.getParamFlash(),
				this.cuentaBean);
		obtieneFlash().put(
				ParametrosFlashEnum.MODIFICACION_CUENTA.getParamFlash(), true);
		managedBeanStateRecover.enviaController(this,
				NavegacionEnum.FICHA_CUENTA);
		return NavegacionEnum.EMITIR_DOCUMENTOS.getRuta();
	}

	/**
	 * Función para avanzar a la vista de documentos relacionados
	 * 
	 * @return URL de la vista a redireccionar
	 */
	public String consultaDocumentosCuentas() {
		obtieneFlash().put(ParametrosFlashEnum.CUENTA_BEAN.getParamFlash(),
				this.cuentaBean);
		obtieneFlash().put(
				ParametrosFlashEnum.MODIFICACION_CUENTA.getParamFlash(), true);
		managedBeanStateRecover.enviaController(this,
				NavegacionEnum.FICHA_CUENTA);
		return NavegacionEnum.RELACIONES_CUENTA_DOCUMENTOS.getRuta();
	}

	/**
	 * Función para avanzar a la vista de relacionar cuentas con la cuenta
	 * 
	 * @return URL de la vista a redireccionar
	 */
	public String relacionarCuentas() {
		obtieneFlash().put(ParametrosFlashEnum.CUENTA_BEAN.getParamFlash(),
				this.cuentaBean);
		obtieneFlash().put(
				ParametrosFlashEnum.MODIFICACION_CUENTA.getParamFlash(), true);
		managedBeanStateRecover.enviaController(this,
				NavegacionEnum.FICHA_CUENTA);
		return NavegacionEnum.RELACIONES_CUENTA_CUENTAS.getRuta();
	}

	/**
	 * Función para avanzar a la vista de cambiar tarifa
	 *
	 * @return URL de la vista a redireccionar
	 */
	public String cambiarTarifa() {
		obtieneFlash().put(ParametrosFlashEnum.CUENTA_BEAN.getParamFlash(),
				this.cuentaBean);
		obtieneFlash().put(
				ParametrosFlashEnum.MODIFICACION_CUENTA.getParamFlash(), true);
		managedBeanStateRecover.enviaController(this,
				NavegacionEnum.FICHA_CUENTA);
		return mx.babel.bansefi.banksystem.base.enums.integracionAppWhere.NavegacionEnum.CAMBIOTARIFA.getRuta();
	}

	/**
	 * Función para avanzar a la vista de consulta de producto simple
	 * 
	 * @return URL de la vista a redireccionar
	 */
	public String irAProductoSimple() {
		obtieneFlash().put(ParametrosFlashEnum.CUENTA_BEAN.getParamFlash(),
				this.cuentaBean);
		obtieneFlash().put(
				ParametrosFlashEnum.MODIFICACION_CUENTA.getParamFlash(),
				estadosCuentaEnumUtils.verificaEstado(
						EstadosCuentaEnum.SOLICITADO,
						this.cuentaBean.getEstado()));
		managedBeanStateRecover.enviaController(this,
				NavegacionEnum.FICHA_CUENTA);
		return NavegacionEnum.ALTA_CUENTA2.getRuta();
	}

	/**
	 * Función para avanzar a la vista de condiciones de tarifa
	 * 
	 * @return URL de la vista a redireccionar
	 */
	public String irACondicionesTarifa() {
		obtieneFlash().put(ParametrosFlashEnum.CUENTA_BEAN.getParamFlash(),
				this.cuentaBean);
		obtieneFlash().put(
				ParametrosFlashEnum.MODIFICACION_CUENTA.getParamFlash(),
				estadosCuentaEnumUtils.verificaEstado(
						EstadosCuentaEnum.SOLICITADO,
						this.cuentaBean.getEstado()));
		managedBeanStateRecover.enviaController(this,
				NavegacionEnum.FICHA_CUENTA);
		return NavegacionEnum.ALTA_CUENTA3.getRuta();
	}

	/**
	 * Método para avanzar a la vista de relaciones de pan
	 * 
	 * return URl de la vista a redireccionar
	 */
	public String irRelacionesPan() {
		obtieneFlash().put(ParametrosFlashEnum.CUENTA_BEAN.getParamFlash(),
				this.cuentaBean);
		obtieneFlash().put(
				ParametrosFlashEnum.MODIFICACION_CUENTA.getParamFlash(), true);
		managedBeanStateRecover.enviaController(this,
				NavegacionEnum.FICHA_CUENTA);
		return NavegacionEnum.RELACIONES_CUENTA_PAN.getRuta();
	}

	/**
	 * Función para avanzar a la vista de depositos
	 * 
	 * @return URL de la vista a redireccionar
	 */
	public String irDepositos() {
		obtieneFlash().put(ParametrosFlashEnum.NUMERO_CUENTA.getParamFlash(),this.cuentaBean.getNumeroCuenta());
		obtieneFlash().put(ParametrosFlashEnum.TITULAR_CUENTA.getParamFlash(),this.cuentaBean.getNombreTitular());
		obtieneFlash().put(ParametrosFlashEnum.NIVEL_CUENTA.getParamFlash(),this.cuentaBean.getNivelCuenta());
		managedBeanStateRecover.enviaController(this,NavegacionEnum.FICHA_CUENTA);
		return NavegacionEnum.REALIZAR_DEPOSITO.getRuta();
	}
	
	/**
	 * Método para verificar si una cuenta es de tipo Medios de pago
	 * 
	 * @return
	 */
	public Boolean isMediosPago() {
		if(this.cuentaBean != null 
				&& StringUtils.isNotBlank(this.cuentaBean.getCodLinea())
				&& StringUtils.isNotBlank(this.cuentaBean.getIdProducto())){
			return ConstantesFuncionales.isMediosPago(
					this.cuentaBean.getCodLinea(),
					this.cuentaBean.getIdGrupoProducto());
		}else{
			return false;
		}
	}

	/**
	 * Método para verificar si una cuenta es de tipo Vista
	 * 
	 * @return
	 */
	public Boolean isVista() {
		if(this.cuentaBean != null 
				&& StringUtils.isNotBlank(this.cuentaBean.getCodLinea())
				&& StringUtils.isNotBlank(this.cuentaBean.getIdProducto())){
			return ConstantesFuncionales.isVista(this.cuentaBean.getCodLinea(),
					this.cuentaBean.getIdGrupoProducto());
		}else
			return false;
	}

	/**
	 * @return Metodo utilizado para realizar la accion del boton de volver, a
	 *         la vista de la busqueda de cuentas.
	 */
	public String volver() {
		String ruta = "";
		obtieneFlash().put(ParametrosFlashEnum.TIPO_BUSQUEDA.getParamFlash(),
				BusquedaEnum.CUENTAS.getBusquedaValor());
		if (this.destino == null) {
			ruta = NavegacionEnum.BUSQUEDA.getRuta();
		} else {
			ruta = destino.getRuta();
			managedBeanStateRecover.finNavegacion(destinoController);
		}
		return ruta;
	}
	
	public String volverInicio(){
		return NavegacionEnum.INICIO.getRuta();
	}

	/**
	 * Funcion que lee el estado del cuentaBean y lo interpreta de acuerdo al
	 * catalogo de estados en host
	 * 
	 * @return
	 */
	// public String getEstado() {
	// if (this.getCuentaBean() != null
	// && !StringUtils.isBlank(this.getCuentaBean().getEstado())) {
	// return estadosCuentaEnumUtils.getEstado(
	// this.getCuentaBean().getEstado()).getNombre();
	// } else {
	// return null;
	// }
	// }

	/**
	 * Metodo que prepara las opciones del combo de los cambios de estados
	 * disponibles.
	 * 
	 * @return
	 */
	public EstadosCuentaEnum[] getEstadosDisponibles() {
		List<EstadosCuentaEnum> estadosDisponibles = null;
		if (this.cuentaBean != null && this.cuentaBean.getEstadoEnum() != null) {
			estadosDisponibles = cambiosEstadosCuentasUtils
					.obtenerCambiosEstadosCuentaDisponibles((this.cuentaBean));
		} else if (this.cuentaBean != null
				&& this.cuentaBean.getEstado() != null) {
			this.cuentaBean.setEstadoEnum(estadosCuentaEnumUtils
					.getEstado(this.cuentaBean.getEstado()));
			estadosDisponibles = cambiosEstadosCuentasUtils
					.obtenerCambiosEstadosCuentaDisponibles((this.cuentaBean));
		} else {
			return null;
		}
		if (estadosDisponibles != null && !estadosDisponibles.isEmpty()){
			return (EstadosCuentaEnum[]) estadosDisponibles.toArray();
		}
		return new EstadosCuentaEnum[0];

	}

	/**
	 * Funcion valida la solicitud del cambio de estado de la cuenta
	 * 
	 * @return
	 */
	public String solicitaCambiarEstadoCuenta(
			final EstadosCuentaEnum estadoSeleccionado) {
		this.estadoCuentaSeleccionado = estadoSeleccionado;
		this.mensajeConfirmacion = cambiosEstadosCuentasUtils
				.mensajeConfirmacion(this.cuentaBean.getEstadoEnum(),
						this.estadoCuentaSeleccionado);
		if (this.cuentaBean != null
				&& !StringUtils.isBlank(this.cuentaBean.getEstado())) {
			// if ACTIVO -> APROBADO
			if (this.cuentaBean.getEstadoEnum()
					.equals(EstadosCuentaEnum.ACTIVO)
					&& estadoSeleccionado.equals(EstadosCuentaEnum.APROBADO)) {
				ConsultaAnulacionCuentaBean consultaAnulacion = null;

				try {
					consultaAnulacion = this.anularConstitucionAcuerdoBackend
							.ejecutarTRN(this.cuentaBean);
				} catch (final NoControlableException e) {
					LOGGER.debug("No hay movimientos de apuntes que consultar");
				}

				if (consultaAnulacion != null
						&& consultaAnulacion.getListaApuntes() != null
						&& !consultaAnulacion.getListaApuntes().isEmpty()) {
					obtieneFlash().put(
							ParametrosFlashEnum.APUNTE_BEAN.getParamFlash(),
							consultaAnulacion);
					return NavegacionEnum.ANULAR_CONSTITUCION.getRuta();
				} else {
					RequestContext.getCurrentInstance().execute(
							"PF('dlgConfirmacionEstado').show()");
				}
				// Cancelar cuenta
			} else if (estadoSeleccionado.equals(EstadosCuentaEnum.CANCELADO)) {
				obtieneFlash().put(
						ParametrosFlashEnum.CUENTA_BEAN.getParamFlash(),
						this.cuentaBean);

				managedBeanStateRecover.enviaController(this,
						NavegacionEnum.FICHA_CUENTA);

				return NavegacionEnum.CANCELAR_CUENTA.getRuta();
			} else if (estadoSeleccionado != null) {
				RequestContext.getCurrentInstance().execute(
						"PF('dlgConfirmacionEstado').show()");
			} else {
				RequestContext.getCurrentInstance().execute(
						"PF('dlgErrorEstado').show()");
			}
		} else {
			// TODO: Error de aplicacion
		}

		return null;
	}

	/**
	 * Funcion que cambia el estado de la cuenta
	 * 
	 * @return
	 */
	public String cambiarEstadoCuenta() {
		try {
			final Map<Boolean, String> respuesta = cambiosEstadosCuentasUtils
					.cambiarEstadoCuenta(this.cuentaBean.getEstadoEnum(),
							estadoCuentaSeleccionado, this.cuentaBean);

			RequestContext.getCurrentInstance().execute(
					"PF('dlgConfirmacionEstado').hide()");

			if (respuesta != null && !respuesta.isEmpty()) {
				respuestaCambioEstado = respuesta;
				// Repuesta correcta
				if (respuesta.get(Boolean.TRUE) != null) {
					this.mensaje = respuesta.get(Boolean.TRUE);
					this.claseEstadoRespuesta = "ui-messages-exito-icon exito-dialog";
				} else if (respuesta.get(Boolean.FALSE) != null) {
					this.mensaje = respuesta.get(Boolean.FALSE);
					this.claseEstadoRespuesta = "ui-messages-error-icon error-dialog";
				}

				RequestContext.getCurrentInstance().execute(
						"PF('dlgRespuestaCambio').show()");
			}
		} catch (ControlableException ce) {
			LOGGER.debug("Error controlable");
			this.mensaje = ce.getMensajeUsuario() + "\n" + ce.getMensajeDetalle();
			this.claseEstadoRespuesta = "ui-messages-error-icon error-dialog";
			RequestContext.getCurrentInstance().execute(
					"PF('dlgRespuestaCambio').show()");
		}
		return null;
	}

	/**
	 * Funcion que se ejecuta al confirmar la respuesta del cambio de estado de
	 * la cuenta Puede permanecer en la pagina o puede enviarse a otro flujo
	 * 
	 * @return
	 */
	public String confirmaRespuesta() {
		obtieneFlash().put(ParametrosFlashEnum.CUENTA_BEAN.getParamFlash(),this.cuentaBean);
		return NavegacionEnum.FICHA_CUENTA.getRuta();
	}

	/**
	 * Método que recupera la ruta de destino.
	 * 
	 */
	public NavegacionEnum getDestino() {
		return destino;
	}

	/**
	 * Método que guarda la ruta de destino.
	 * 
	 * @param destino
	 */
	public void setDestino(final NavegacionEnum destino) {
		this.destino = destino;
	}

	/**
	 * Método que recupera el controller de destino.
	 * 
	 */
	public Object getDestinoController() {
		return destinoController;
	}

	/**
	 * Método que guarda el controller de destino.
	 * 
	 * @param destinoController
	 */
	public void setDestinoController(final Object destinoController) {
		this.destinoController = destinoController;
	}

	/**
	 * Método que devuelve un objeto tipo CuentaBean.
	 * 
	 * @return cuentaBean
	 */
	public CuentaBean getCuentaBean() {
		return cuentaBean;
	}

	/**
	 * Método que establece un objeto tipo CuentaBean.
	 * 
	 * @param cuentaBean
	 */
	public void setCuentaBean(final CuentaBean cuentaBean) {
		this.cuentaBean = cuentaBean;
	}

	/**
	 * Método que devuelve un indicador booleano para mostrar u ocultar el las
	 * personas relacionadas a cuenta.
	 * 
	 * @return indicador booleando para mostrar u ocultar las personas
	 *         relacionadas a cuenta
	 */
	public boolean getMostrarPersonasRelacionadas() {
		return mostrarPersonasRelacionadas;
	}

	/**
	 * Método que establece el valor del indicador para mostrar u ocultar las
	 * personas relacionadas a cuenta.
	 * 
	 * @param mostrarPersonasRelacionadas
	 */
	public void setMostrarPersonasRelacionadas(
			final boolean mostrarPersonasRelacionadas) {
		this.mostrarPersonasRelacionadas = mostrarPersonasRelacionadas;
	}

	/**
	 * Método que devuelve un indicador booleano para mostrar u ocultar el
	 * status.
	 * 
	 * @return indicador booleando para mostrar u ocultar el status
	 */
	public boolean getMostrarStatus() {
		return mostrarStatus;
	}

	/**
	 * Método que establece el valor del indicador para mostrar u ocultar el
	 * status.
	 * 
	 * @param mostrarStatus
	 */
	public void setMostrarStatus(final boolean mostrarStatus) {
		this.mostrarStatus = mostrarStatus;
	}

	/**
	 * Método que devuelve si la cartera es vigente.
	 *
	 */
	public boolean getStatusVigente() {
		return statusVigente;
	}

	/**
	 * Método que guarda si la cartera es vigente.
	 * 
	 * @param statusVigente
	 */
	public void setStatusVigente(final boolean statusVigente) {
		this.statusVigente = statusVigente;
	}

	/**
	 * Método que controla si ya se ha pulsado alguna vez el detalle de status.
	 * 
	 */
	public boolean getStatusDetalle() {
		return statusDetalle;
	}

	/**
	 * Método que guarda si ya se ha pulsado alguna vez el detalle de status.
	 * 
	 * @param statusDetalle
	 */
	public void setStatusDetalle(final boolean statusDetalle) {
		this.statusDetalle = statusDetalle;
	}

	/**
	 * Método que controla si ya se ha pulsado alguna vez el detalle de cuentas
	 * relacionadas.
	 * 
	 */
	public boolean isCuentasRelacionadasDetalle() {
		return cuentasRelacionadasDetalle;
	}

	/**
	 * Método que guarda si ya se ha pulsado alguna vez el detalle de cuentas
	 * relacionadas.
	 * 
	 * @param cuentasRelacionadasDetalle
	 */
	public void setCuentasRelacionadasDetalle(
			final boolean cuentasRelacionadasDetalle) {
		this.cuentasRelacionadasDetalle = cuentasRelacionadasDetalle;
	}

	/**
	 * Método que devuelve el numero de anotaciones a renderizar en la vista.
	 * 
	 * @return indicador del numero de anotaciones a visualizar
	 */
	public int getAnotacionesVisibles() {
		return anotacionesVisibles;
	}

	/**
	 * Método que establece el numero de anotaciones visibles en la vista
	 * 
	 * @param anotacionesVisibles
	 */
	public void setAnotacionesVisibles(final int anotacionesVisibles) {
		this.anotacionesVisibles = anotacionesVisibles;
	}

	/**
	 * Método que devuelve un indicador booleano para mostrar u ocultar los
	 * elementos de la vista.
	 * 
	 * @return indicador booleando para mostrar u ocultar elementos
	 */
	public boolean getElementosVisibles() {
		return elementosVisibles;
	}

	/**
	 * Método que establece el valor del indicador para mostrar u ocultar los
	 * elementos de la vista.
	 * 
	 * @param elementosVisibles
	 */
	public void setElementosVisibles(final boolean elementosVisibles) {
		this.elementosVisibles = elementosVisibles;
	}

	public EstadosCuentaEnum getEstadoCuentaSeleccionado() {
		return estadoCuentaSeleccionado;
	}

	public void setEstadoCuentaSeleccionado(
			final EstadosCuentaEnum estadoCuentaSeleccionado) {
		this.estadoCuentaSeleccionado = estadoCuentaSeleccionado;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(final String mensaje) {
		this.mensaje = mensaje;
	}

	/**
	 * @return Atributo origenBusqueda
	 */
	public Boolean getOrigenBusqueda() {
		return origenBusqueda;
	}

	/**
	 * @param origenBusqueda Atributo origenBusqueda a definir
	 */
	public void setOrigenBusqueda(Boolean origenBusqueda) {
		this.origenBusqueda = origenBusqueda;
	}

	public String verificarAltaIPF() {
		String navegacion = null;
		this.consultaCuentasRelacionadas();
		if (altaIPFUtils.noSuperaImporteMinimoIPF(cuentaBean,
				cuentaBean.getProductosSimples())) {
			RequestContext.getCurrentInstance().execute(
					"PF('dlgFaltaImporteMinimoIPF').show()");
		} else {
			navegacion = irAAltaIPF();
		}
		return navegacion;
	}

	public String irAConsultaIPF() {
		return altaIPFUtils.logicaRedireccionConsultaIPF(cuentaBean,
				cuentaBean.getProductosSimples(), this.cuentaBean.getIpfs());
	}

	public String irAAltaIPF() {
		return altaIPFUtils.logicaRedireccionAltaIPF(cuentaBean,
				cuentaBean.getProductosSimples(), true);
	}

	public String irAPagoCuotaIPF() {
		obtieneFlash().put(ParametrosFlashEnum.CUENTA_BEAN.getParamFlash(),
				this.cuentaBean);

		return NavegacionEnum.PAGO_CUOTA_1.getRuta();
	}

	public String irADepositoEnCuenta() {
		return altaIPFUtils.logicaRedireccionRealizarDesposito(cuentaBean,
				cuentaBean.getProductosSimples(), true);
	}

	public boolean esPlazo() {
		return this.esPlazo
				&& EstadosCuentaEnum.ACTIVO.equals(this.cuentaBean
						.getEstadoEnum());
	}

	public boolean esPlazoConIPF() {
		return this.esPlazo() && this.cuentaBean.getIpfs() != null
				&& !this.cuentaBean.getIpfs().isEmpty();
	}

	public boolean esPlazoActivoConCuota() {
		if (this.esPlazo && this.cuentaBean.getIpfs() != null) {
			for (final DepositoIPFBean depo : this.cuentaBean.getIpfs()) {
				if (StringUtils.equals(ConstantesFuncionales.IPF_ESTADO_ACTIVO,
						depo.getEstado())) {
					final TarifaBean tarifaBean = new TarifaBean();
					tarifaBean.setLinea(cuentaBean.getCodLinea());
					tarifaBean.setGrupo(cuentaBean.getIdGrupoProducto());
					tarifaBean.setProducto(cuentaBean.getIdProducto());
					return TarifasUtils.esPlazoConCuota(tarifaBean);
				}
			}
		}
		return false;
	}

	public String getMensajeConfirmacion() {
		return mensajeConfirmacion;
	}

	public void setMensajeConfirmacion(final String mensajeConfirmacion) {
		this.mensajeConfirmacion = mensajeConfirmacion;
	}

	/**
	 * @return Atributo titular1
	 */
	public RelacionadoBean getTitular1() {
		return titular1;
	}

	/**
	 * @param titular1
	 *            Atributo titular1 a definir
	 */
	public void setTitular1(final RelacionadoBean titular1) {
		this.titular1 = titular1;
	}
	

    /**
     * @return the esPlazo
     */
    public boolean getEsPlazo() {
        return esPlazo;
    }

    /**
     * @param esPlazo the esPlazo to set
     */
    public void setEsPlazo(final boolean esPlazo) {
        this.esPlazo = esPlazo;
    }

	public String getClaseEstadoRespuesta() {
		return claseEstadoRespuesta;
	}

	public void setClaseEstadoRespuesta(String claseEstadoRespuesta) {
		this.claseEstadoRespuesta = claseEstadoRespuesta;
	}

}