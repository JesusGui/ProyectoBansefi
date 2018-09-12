package mx.babel.bansefi.banksystem.base.controllers;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UISelectOne;
import javax.faces.component.behavior.AjaxBehavior;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.model.SelectItem;

import mx.babel.arq.banksystem.components.SelectOneRadioSlider;
import mx.babel.arq.catalogo.beans.CatalogoBean;
import mx.babel.arq.comun.annotations.ArregloBusquedasPreviasAnnotation;
import mx.babel.arq.comun.annotations.BusquedaPreviaAnnotation;
import mx.babel.arq.comun.annotations.CampoBusquedaAnnotation;
import mx.babel.arq.comun.annotations.NavegaAnnotation;
import mx.babel.arq.comun.beans.PaginacionBean;
import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.sesion.contexto.beans.UsuarioBean;
import mx.babel.arq.sesion.utils.ContextoUtils;
import mx.babel.bansefi.banksystem.base.backends.ConsultaApuntesManualesBackEnd;
import mx.babel.bansefi.banksystem.base.backends.ConsultaCuentasContablesBackEnd;
import mx.babel.bansefi.banksystem.base.backends.ConsultaDatosGestorBackEnd;
import mx.babel.bansefi.banksystem.base.backends.ConsultaDomiciliosPersonaBackEnd;
import mx.babel.bansefi.banksystem.base.backends.ConsultaReciboNoDomiciliadoBackEnd;
import mx.babel.bansefi.banksystem.base.backends.anotaciones.ConsultaListaAnotacionesBackEnd;
import mx.babel.bansefi.banksystem.base.backends.busquedas.BusquedaCentroCodigoBackEnd;
import mx.babel.bansefi.banksystem.base.backends.busquedas.BusquedaCentroNombreBackEnd;
import mx.babel.bansefi.banksystem.base.backends.busquedas.BusquedaCuentaBackEnd;
import mx.babel.bansefi.banksystem.base.backends.busquedas.BusquedaGrupoBackEnd;
import mx.babel.bansefi.banksystem.base.backends.busquedas.BusquedaIdExternaBackEnd;
import mx.babel.bansefi.banksystem.base.backends.busquedas.BusquedaNombreBackEnd;
import mx.babel.bansefi.banksystem.base.backends.busquedas.BusquedaPersonaFisicaBackEnd;
import mx.babel.bansefi.banksystem.base.backends.busquedas.BusquedaPersonaMoralBackEnd;
import mx.babel.bansefi.banksystem.base.backends.consultaempleados.ConsultaEmpleadoBackEnd;
import mx.babel.bansefi.banksystem.base.backends.consultalistaempleados.ConsultaListaEmpleadosBackEnd;
import mx.babel.bansefi.banksystem.base.backends.consultalistaentidades.ConsultaListaEntidadesBackend;
import mx.babel.bansefi.banksystem.base.backends.login.NotificacionBackEnd;
import mx.babel.bansefi.banksystem.base.beans.CuentaContableBean;
import mx.babel.bansefi.banksystem.base.beans.ReciboBean;
import mx.babel.bansefi.banksystem.base.beans.busqueda.AnotacionClienteBusquedaBean;
import mx.babel.bansefi.banksystem.base.beans.busqueda.AnotacionCuentaBusquedaBean;
import mx.babel.bansefi.banksystem.base.beans.busqueda.ApunteManualBusquedaBean;
import mx.babel.bansefi.banksystem.base.beans.busqueda.CentroBusquedaBean;
import mx.babel.bansefi.banksystem.base.beans.busqueda.CuentaBusquedaBean;
import mx.babel.bansefi.banksystem.base.beans.busqueda.DomiciliosClienteBusquedaBean;
import mx.babel.bansefi.banksystem.base.beans.busqueda.EmpleadoBusquedaBean;
import mx.babel.bansefi.banksystem.base.beans.busqueda.EntidadBusquedaBean;
import mx.babel.bansefi.banksystem.base.beans.busqueda.GrupoBusquedaBean;
import mx.babel.bansefi.banksystem.base.beans.busqueda.NotificacionBusquedaBean;
import mx.babel.bansefi.banksystem.base.beans.busqueda.PersonaGestorBusquedaBean;
import mx.babel.bansefi.banksystem.base.beans.busqueda.PersonaMoralBusquedaBean;
import mx.babel.bansefi.banksystem.base.beans.busqueda.PersonasClienteBusquedaBean;
import mx.babel.bansefi.banksystem.base.beans.busqueda.RecibosNoDomiciliadosBusquedaBean;
import mx.babel.bansefi.banksystem.base.beans.domain.ClienteBean;
import mx.babel.bansefi.banksystem.base.beans.domain.CuentaBean;
import mx.babel.bansefi.banksystem.base.beans.domain.GestorBean;
import mx.babel.bansefi.banksystem.base.beans.domain.TipoCliente;
import mx.babel.bansefi.banksystem.base.enums.BusquedaEnum;
import mx.babel.bansefi.banksystem.base.enums.CatalogoEnum;
import mx.babel.bansefi.banksystem.base.enums.NavegacionEnum;
import mx.babel.bansefi.banksystem.base.enums.ParametrosFlashEnum;
import mx.babel.bansefi.banksystem.base.utils.CatalogoUtils;
import mx.babel.bansefi.banksystem.base.utils.ConstructorModelosColumnaUtils;
import mx.babel.bansefi.banksystem.base.utils.DynaFormModelUtils;
import mx.babel.bansefi.banksystem.base.utils.DynaPropertyModel;
import mx.babel.bansefi.banksystem.base.utils.ManagedBeanStateRecover;
import mx.babel.bansefi.banksystem.base.utils.ModeloColumnaUtils;
import mx.babel.bansefi.banksystem.base.utils.ParametrosFlashUtils;
import mx.babel.bansefi.banksystem.base.utils.ProveedorMensajeUtils;
import mx.babel.bansefi.banksystem.base.utils.SelectItemIntValueComparator;
import mx.babel.bansefi.banksystem.base.wrappers.NotificacionWrapper;
import mx.babel.bansefi.banksystem.base.wrappers.WrapperBusquedasUtils;

import org.apache.commons.beanutils.MethodUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.SlideEndEvent;
import org.primefaces.extensions.model.dynaform.DynaFormControl;
import org.primefaces.extensions.model.dynaform.DynaFormModel;
import org.primefaces.extensions.model.dynaform.DynaFormRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Clase controladora de la ventana Buscador.
 * 
 * @author alejandro.pineda
 * 
 */
@ManagedBean
@ViewScoped
@Component
@Scope("view")
public class BusquedaController implements Serializable {

	private static final long serialVersionUID = 8436342677109047579L;

	private static final Logger LOGGER = LogManager
			.getLogger(BusquedaController.class.getName());

	@Autowired
	private ContextoUtils contextoUtils;

	@Autowired
	private CatalogoUtils catalogoUtils;

	@Autowired
	private ApplicationContext appContext;

	@Autowired
	private DynaFormModelUtils dynaFormModelUtils;

	@Autowired
	private WrapperBusquedasUtils wrapperBeanService;

	@Autowired
	private NotificacionWrapper notificacionWrapper;

	@Autowired
	private ManagedBeanStateRecover managedBeanStateRecover;

	// Indicador para aplicar un estilo u otro a la tabla de resultados.
	private boolean tablaNotificaciones;

	private boolean tablaApuntesManuales;

	/**
	 * Enum para definir a donde se redireccionará al seleccionar un objeto de
	 * la tabla de resultados.
	 */
	private NavegacionEnum destino;
	private Object destinoController;
	private boolean confirmaSeleccion = false;

	private DynaFormModel modelo;
	private DynaFormModel modeloTabla;
	private List<ModeloColumnaUtils> columnas = new ArrayList<>(0);
	private List<SelectItem> listaRadio = new ArrayList<>(0);
	private List<Object> datos;

	private String seleccionTipo;
	private String claseActual;
	private String claseIntermediaActual;
	private String metodoWebService;
	private Object claseActualObjeto;

	private boolean pintarTablaResultados = false;
	private boolean pintarTablaResultadosIntermedios = false;
	private boolean masDatos = false;
	private boolean webservice = false;
	private List<String[]> filtros;

	private int opcion = 0;
	private int pagina = 0;

	private SelectEvent eventPasoIntermedio;
	private boolean busquedaIntermedia;

	// Para busqueda de apuntes contables
	private List<CuentaContableBean> listaCuentasContables;

	@PostConstruct
	public void init() {
		this.datos = new ArrayList<>();
		filtros = new ArrayList<>();
		if (this.obtieneFlash().get(
				ParametrosFlashEnum.NAVEGACION_MANAGED_BEAN.getParamFlash()) != null) {
			if ((boolean) this.obtieneFlash()
					.get(ParametrosFlashEnum.NAVEGACION_MANAGED_BEAN
							.getParamFlash())) {
				this.destino = managedBeanStateRecover.getDestino();
				this.destinoController = managedBeanStateRecover
						.getController();
				if (this.destinoController != null) {
					// si se viene de otro flujo es necesario mostrar el boton
					// aceptar para seleccionar un elemento
					confirmaSeleccion = true;
				}
				this.iniciarDatos();
				this.initModelo();
			} else {
				this.managedBeanStateRecover.recuperaController(this);

				if (obtieneFlash().get(
						ParametrosFlashEnum.RETURN_AND_REFRESH.getParamFlash()) != null
						&& (boolean) obtieneFlash().get(
								ParametrosFlashEnum.RETURN_AND_REFRESH
										.getParamFlash())) {

					if (this.busquedaIntermedia) {
						this.buscarPasoDosIntermedio(this.eventPasoIntermedio);
					} else {
						this.buscar();
					}
				}
			}
		} else {
			this.iniciarDatos();
			this.initModelo();
		}
	}

	/**
	 * Método que devuelve un indicador booleano para determinar si se debe
	 * aplicar un estilo especial a la tabla de resultados de búsqueda o no.
	 * 
	 * @return indicador booleano
	 *         true-->styleClass="tabla-resultados-notificaciones"
	 *         false-->styleClass="tabla-buscador"
	 */
	public boolean isTablaNotificaciones() {
		return tablaNotificaciones;
	}

	/**
	 * Método que establece el valor del indicador para aplicar un estilo
	 * especial a la tabla de resultados de búsqueda.
	 * 
	 * @param tablaNotificaciones
	 */
	public void setTablaNotificaciones(boolean tablaNotificaciones) {
		this.tablaNotificaciones = tablaNotificaciones;
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
	 * Método que inicializa los datos de busqueda.
	 */
	public void iniciarDatos() {
		if (this.obtieneFlash().get(
				ParametrosFlashEnum.TIPO_BUSQUEDA.getParamFlash()) == null) {
			this.claseActual = "";
			this.seleccionTipo = "";
		} else {
			this.seleccionTipo = (String) this.obtieneFlash().get(
					ParametrosFlashEnum.TIPO_BUSQUEDA.getParamFlash());
			this.claseActual = ProveedorMensajeUtils
					.getValoresTipos(this.seleccionTipo);
			try {
				this.claseActualObjeto = Class.forName(this.claseActual)
						.newInstance();
			} catch (InstantiationException | IllegalAccessException
					| ClassNotFoundException e) {
				LOGGER.debug("Error al construir el objeto de la clase Actual");
				throw new NoControlableException(
						"Se ha producido un error al crear el Objeto del bean de busqueda seleccionado",
						e);
			}
		}
		if (!("").equals(this.seleccionTipo)) {
			this.listaRadio = opcionesRadio();
		}
	}

	/**
	 * Método que regresa DynaFormModel a pintar en la zona de los campos de
	 * búsqueda en la ventana del buscador.
	 * 
	 * @return DynaFormModel que se pintara en la zona de los campos de
	 *         búsqueda.
	 */
	public void initModelo() {
		if (this.seleccionTipo.equals("")) {
			this.modelo = new DynaFormModel();
		} else {
			this.listaRadio = opcionesRadio();
			this.modelo = this.dynaFormModelUtils.construyeModelo(
					this.claseActualObjeto, this.opcion);
		}
	}

	/**
	 * Método para actualizar la vista con base en la navegación desde otro
	 * flujo
	 */
	public void actualizaVistaPreRender() {
		if (this.obtieneFlash().get(
				ParametrosFlashEnum.NAVEGACION_MANAGED_BEAN.getParamFlash()) != null) {
			if ((boolean) this.obtieneFlash()
					.get(ParametrosFlashEnum.NAVEGACION_MANAGED_BEAN
							.getParamFlash())) {
				if (this.claseActual.equals(CuentaBusquedaBean.class.getName())) {
					// Si venimos desde otro flujo
					// debemos mover el slider para busqueda por PAN
					SelectOneRadioSlider slider = (SelectOneRadioSlider) FacesContext
							.getCurrentInstance()
							.getViewRoot()
							.findComponent(
									"formularioBuscador:sliderOpcionesBusqueda");
					slider.setStyleClass("left: 100%;");
					this.opcion = 1;
					this.initModelo();
				}
				if (this.claseActual.equals(PersonasClienteBusquedaBean.class
						.getName())
						|| this.claseActual.equals(GrupoBusquedaBean.class
								.getName())
								|| this.claseActual.equals(PersonaMoralBusquedaBean.class
										.getName())) {
					// Si venimos desde otro flujo
					// debemos mover el slider para busqueda por Identificación
					SelectOneRadioSlider slider = (SelectOneRadioSlider) FacesContext
							.getCurrentInstance()
							.getViewRoot()
							.findComponent(
									"formularioBuscador:sliderOpcionesBusqueda");
					this.opcion = 1;
					slider.setStyleClass("left: 50%;");
					this.initModelo();
					if (this.obtieneFlash().get(
							ParametrosFlashEnum.BEAN_DATOS_BUSQUEDA
									.getParamFlash()) != null) {
						this.claseActualObjeto = this.obtieneFlash().get(
								ParametrosFlashEnum.BEAN_DATOS_BUSQUEDA
										.getParamFlash());
						this.recorrerBeanDatos();
						if(this.obtieneFlash().get(
								ParametrosFlashEnum.ACCION_BUSQUEDA.getParamFlash()) != null){
							if((Boolean)this.obtieneFlash().get(
									ParametrosFlashEnum.ACCION_BUSQUEDA.getParamFlash())){
								RequestContext
								.getCurrentInstance()
								.execute(
										"$('#formularioBuscador\\\\:btnBuscar').click()");
							}
						}else{
							RequestContext
							.getCurrentInstance()
							.execute(
									"$('#formularioBuscador\\\\:btnBuscar').click()");
						}
						
					}
				}
			}
		}
	}

	/**
	 * Método privado que inspecciona los métodos del bean de búsqueda recibido
	 * como parámetro y establece los valores en la vista (buscador.xhtml).
	 * 
	 * @author omar.marquez
	 * @throws IntrospectionException
	 */
	private void recorrerBeanDatos() {
		try {
			// Para cada método GET del bean de datos.
			for (PropertyDescriptor propertyDescriptor : Introspector
					.getBeanInfo(this.claseActualObjeto.getClass())
					.getPropertyDescriptors()) {
				// Comparamos contra el nombre de los elementos del modelo.
				for (DynaFormControl dynaFormControl : this.modelo
						.getControls()) {
					DynaPropertyModel dynaPropertyModel = ((DynaPropertyModel) dynaFormControl
							.getData());
					String nombreCampo = StringUtils
							.capitalize(StringUtils.join(StringUtils
									.splitPreserveAllTokens(
											dynaPropertyModel.getNombre(), " ",
											0), ""));
					if (propertyDescriptor.getReadMethod().getName()
							.equalsIgnoreCase("get" + nombreCampo)) {
						int pos = nombreCampo.indexOf("Catalogo");
						if (pos != -1) {
							nombreCampo = nombreCampo.substring(0, pos);
						}
						Object valor = MethodUtils.invokeMethod(
								this.claseActualObjeto, "get" + nombreCampo,
								null);
						dynaPropertyModel.setValor(valor);
					}
				}
			}
		} catch (IntrospectionException | NoSuchMethodException
				| IllegalAccessException | InvocationTargetException e) {
			LOGGER.error("Error al establecer los valores del bean en los campos de búsqueda."
					+ this.getClass().getName() + ":" + e.getMessage());
			throw new NoControlableException(
					"Error al establecer los valores del bean en los campos de búsqueda.",
					this.getClass().getName() + ":" + e.getMessage());
		}
	}

	/**
	 * Permite mostrar el autocompletado del campo Tipo.
	 * 
	 * @param query
	 *            Texto ingresado en el autocomplete.
	 * @return Lista de tipo String.
	 */
	public List<String> autocompletarTipo(String query) {
		List<String> todosDatos = this.contextoUtils.getOpBusquedas();
		List<String> filtro = new ArrayList<>();
		for (int i = 0; i < todosDatos.size(); i++) {
			String texto = todosDatos.get(i);
			if (texto.toUpperCase().contains(query.toUpperCase())) {
				filtro.add(texto);
			}
		}

		// cuando no selecciono una opcion válida restear buscador
		if (filtro.isEmpty()) {

			this.pagina = 0;
			this.tablaNotificaciones = false;
			filtros = new ArrayList<>();
			if (this.datos != null) {
				this.datos.clear();
			}

			masDatos = false;
			webservice = false;

			// Resetear valores para busqueda intermedia
			this.pintarTablaResultados = false;
			this.pintarTablaResultadosIntermedios = false;
			this.claseIntermediaActual = null;

			this.listaRadio = new ArrayList<>();
		}
		return filtro;
	}

	/**
	 * Método que se ejecuta al seleccionar un tipo de consulta en el buscador.
	 * 
	 * @param event
	 *            Evento del Autocomplete al seleccionar un tipo.
	 */
	public void seleccionarTipo(SelectEvent event) {
		this.opcion = 0;
		this.seleccionTipo = event.getObject().toString();
		this.claseActual = ProveedorMensajeUtils
				.getValoresTipos(this.seleccionTipo);
		try {
			this.claseActualObjeto = Class.forName(this.claseActual)
					.newInstance();
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException e) {
			LOGGER.debug("Error al construir el objeto de la clase Actual");
			throw new NoControlableException(
					"Se ha producido un error al crear el Objeto del bean de busqueda seleccionado",
					e);
		}
		this.pintarTablaResultados = false;
		this.pintarTablaResultadosIntermedios = false;
		this.listaRadio = this.opcionesRadio();
		this.initModelo();
	}

	/**
	 * Método para generar la Lista de las opciones de Radio botones según el
	 * tipo de búsqueda seleccionada.
	 * 
	 * @return Lista de opciones de los radio botones.
	 */
	public List<SelectItem> opcionesRadio() {
		Field[] camposClase = this.claseActualObjeto.getClass()
				.getDeclaredFields();
		Map<Integer, SelectItem> opciones = new HashMap<>();
		for (int i = 0; i < camposClase.length; i++) {

			if (camposClase[i]
					.isAnnotationPresent(CampoBusquedaAnnotation.class)) {
				Field campo = camposClase[i];
				String tituloOpcion = "";

				if ("".equals(campo
						.getAnnotation(CampoBusquedaAnnotation.class)
						.tituloCampo())) {
					tituloOpcion = StringUtils.capitalize(StringUtils.join(
							StringUtils.splitByCharacterTypeCamelCase(campo
									.getName()), " "));
				} else {
					tituloOpcion = campo.getAnnotation(
							CampoBusquedaAnnotation.class).tituloCampo();
				}

				int key = campo.getAnnotation(CampoBusquedaAnnotation.class)
						.desplegar();
				// - cuando desplegar es -1 se muestra en buscador pero no como
				// opcion
				// en slider
				// - cuando se usa -2 en desplegar se muestra en el slider
				// pero no como campo en el buscador
				if (!opciones.containsKey(key) && key != -1) {
					opciones.put(key, new SelectItem(key, tituloOpcion));
				}
			}
		}
		List<SelectItem> listaRadios = new ArrayList<>(opciones.values());
		Collections.sort(listaRadios, new SelectItemIntValueComparator());
		return listaRadios;
	}

	/**
	 * Método que es llamado al generar un evento en los radio botones y cambiar
	 * el valor de la opcion.
	 * 
	 * @param event
	 *            del radio seleccionado en la ventana.
	 */
	public void actualizarCampos(SlideEndEvent event) {
		this.opcion = new Integer(event.getValue());
		try {
			this.claseActualObjeto = Class.forName(this.claseActual)
					.newInstance();
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException e) {
			LOGGER.debug("Error al construir el objeto de la clase Actual");
			throw new NoControlableException(
					"Se ha producido un error al crear el Objeto del bean de busqueda seleccionado",
					e);
		}
		this.pintarTablaResultadosIntermedios = false;
		this.claseIntermediaActual = "";
		this.initModelo();
	}

	/**
	 * Método que ejecuta la acción al presionar el botón Buscar.
	 * 
	 * @return String
	 */
	public String buscar() {
		String ruta = "";
		this.pagina = 0;
		this.tablaNotificaciones = false;
		this.tablaApuntesManuales = false;
		filtros = new ArrayList<>();
		if (this.datos != null) {
			this.datos.clear();
		}

		masDatos = false;
		webservice = false;

		// Resetear valores para busqueda intermedia
		this.pintarTablaResultados = false;
		this.pintarTablaResultadosIntermedios = false;
		this.claseIntermediaActual = null;

		// Obtiene los controles declarados en el modelo que
		// se muestra en la ventana
		for (DynaFormControl dynaFormControl : this.modelo.getControls()) {
			DynaPropertyModel dynaPropertyModel = ((DynaPropertyModel) dynaFormControl
					.getData());

			String nombreCampo = StringUtils.capitalize(StringUtils.join(
					StringUtils.splitPreserveAllTokens(
							dynaPropertyModel.getNombre(), " ", 0), ""));
			Object valor = null;

			if (dynaFormControl.getType().equals("int")) {
				try {
					valor = Integer.valueOf(dynaPropertyModel.getValor()
							.toString().trim());
				} catch (NumberFormatException nfe) {
					RequestContext
							.getCurrentInstance()
							.execute(
									"$('#formularioBuscador\\\\:dynaFormCampos\\\\:"
											+ dynaFormControl.getKey()
											+ "\\\\:txtInt').addClass('ui-state-error');");
					LOGGER.error("Formato incorrecto para número",
							"Formato incorrecto para número");
					return "";
				}
			} else if (dynaFormControl.getType().equals("long")) {
				try {
					valor = Long.valueOf(dynaPropertyModel.getValor()
							.toString().trim());
				} catch (NumberFormatException nfe) {
					RequestContext
							.getCurrentInstance()
							.execute(
									"$('#formularioBuscador\\\\:dynaFormCampos\\\\:"
											+ dynaFormControl.getKey()
											+ "\\\\:txtLong').addClass('ui-state-error');");
					LOGGER.error("Formato incorrecto para número",
							"Formato incorrecto para número");
					return "";
				}
			} else if (dynaFormControl.getType().equals("date")) {
				valor = (Date) dynaPropertyModel.getValor();
			} else if (dynaFormControl.getType().equals("boolean")) {
				valor = Boolean.valueOf(dynaPropertyModel.getValor().toString()
						.trim());
			} else if (dynaFormControl.getType().equals("autocompletarcentros")) {
				valor = ((CatalogoBean) dynaPropertyModel.getValor())
						.getClaveFila();
			} else if (dynaFormControl.getType().equals(
					"autocompletarcuentascontables")) {
				valor = ((CuentaContableBean) dynaPropertyModel.getValor())
						.getIdCuentaContable();
			} else {
				valor = ((String) dynaPropertyModel.getValor()).trim()
						.toUpperCase();
			}

			try {
				// Se busca si el campo de busqueda es de tipo Catalogo para
				// realizar la busqueda mediante un campo
				// adicional con el mismo String sin la palabra catalogo
				int pos = nombreCampo.indexOf("Catalogo");
				if (pos != -1) {
					nombreCampo = nombreCampo.substring(0, pos);
				}
				// Inicializa los valores en el bean
				if (valor != null) {
					MethodUtils.invokeMethod(this.claseActualObjeto, "set"
							+ nombreCampo, valor);
				} else {
					if (valor == null
							&& this.claseActualObjeto instanceof NotificacionBusquedaBean) {
						MethodUtils.invokeMethod(this.claseActualObjeto, "set"
								+ nombreCampo, valor);
					}
				}
			} catch (NoSuchMethodException | IllegalAccessException
					| InvocationTargetException e) {
				LOGGER.debug("Error al guardar los valores de los campos para la busqueda: "
						+ nombreCampo);
				throw new NoControlableException(
						"Error al guardar los valores de los campos para la busqueda: "
								+ nombreCampo, e);
			}

		}

		// Validar si se tiene que hacer una búsqueda intermedia
		if (claseActualObjeto.getClass().isAnnotationPresent(
				ArregloBusquedasPreviasAnnotation.class)
				&& (("".equals(this.claseIntermediaActual)) || this.claseIntermediaActual == null)) {
			if (buscarPasoIntermedio()) {
				this.guardarFiltros();
				return "";
			}
		}

		// Obtiene el metodo que ejecutará el WebService según el tipo de
		// búsqueda
		this.metodoWebService = ProveedorMensajeUtils
				.getValoresTipos("BUSQUEDA" + this.seleccionTipo + this.opcion);
		try {

			// Se ejecuta método obtenido
			ruta = (String) MethodUtils.invokeMethod(this,
					this.metodoWebService, this.claseActualObjeto);
			if (confirmaSeleccion) {
				ruta = null;
			}
			// Se ejecuta método getMasDatos y ultimo valor de lista.
			if (this.claseActualObjeto instanceof PaginacionBean) {

				this.masDatos = (Boolean) MethodUtils.invokeMethod(
						this.claseActualObjeto, "getMasDatos", null);
			}
		} catch (NoSuchMethodException | IllegalAccessException
				| InvocationTargetException e) {
			LOGGER.debug("Error al obtener los valores de los campos para el resultado de la busqueda");
			if (e.getCause() instanceof ControlableException) {
				throw new ControlableException(
						((ControlableException) e.getCause())
								.getMensajeUsuario(),
						((ControlableException) e.getCause())
								.getMensajeDetalle());
			} else {
				
				if (e.getCause().getMessage()!=null && e.getCause().getMessage().indexOf("IP HEADER-LOGON INCORRECTA")!=-1){
					throw new NoControlableException(
							"",
							e.getCause());
				}
				else 
					throw new NoControlableException(
						"Se ha producido un error al obtener los valores de los campos para el resultado de la busqueda",
						e.getCause());
			}

		}

		try {
			this.columnas = ConstructorModelosColumnaUtils.construir(
					Class.forName(this.claseActual), this.opcion, false);
		} catch (ClassNotFoundException e) {
			this.columnas = null;
		}
		this.guardarFiltros();
		this.pintarTablaResultados = true;
		return ruta;
	}

	public boolean buscarPasoIntermedio() {
		LOGGER.debug("Anotación de búsqueda previa encontrada...");
		BusquedaPreviaAnnotation[] busquedasPreviasAnnotation = claseActualObjeto
				.getClass()
				.getAnnotation(ArregloBusquedasPreviasAnnotation.class).value();
		for (int i = 0; i < busquedasPreviasAnnotation.length; i++) {
			BusquedaPreviaAnnotation busquedaPreviaAnnotation = busquedasPreviasAnnotation[i];

			if (busquedaPreviaAnnotation.opcionBusqueda() == opcion) {
				Object backend = null;
				try {

					backend = appContext.getBean(busquedaPreviaAnnotation
							.backend());

					if (backend != null) {
						Object objBusqueda = null;
						if (busquedaPreviaAnnotation.construyeBean()) {
							objBusqueda = Class.forName(
									busquedaPreviaAnnotation.objetoBusqueda())
									.newInstance();
						}
						Method[] metodos = backend.getClass()
								.getDeclaredMethods();

						Method metodoInvocarBackend = null;
						LOGGER.debug("num params de búsqueda en vista: "
								+ modelo.getControls().size()
								+ ". Params necesarios: "
								+ busquedaPreviaAnnotation.parametros().length
								+ " necesario crear bean: "
								+ busquedaPreviaAnnotation.construyeBean());

						// setear parámetros y objeto en caso de ser necesario
						Object[] paramsToBackend = new Object[busquedaPreviaAnnotation
								.parametros().length];
						Object[] paramsToBackendTypes = new Object[busquedaPreviaAnnotation
								.parametros().length];

						for (int j = 0; j < paramsToBackend.length; j++) {

							String[] paramValue = StringUtils.split(
									busquedaPreviaAnnotation.parametros()[j],
									':');
							if (paramValue.length == 1) {

								paramsToBackend[j] = MethodUtils
										.invokeExactMethod(
												claseActualObjeto,
												"get"
														+ StringUtils
																.capitalize(paramValue[0]),
												null);
								paramsToBackendTypes[j] = claseActualObjeto
										.getClass()
										.getDeclaredField(paramValue[0])
										.getType();

							} else {
								paramsToBackend[j] = paramValue[1];
								paramsToBackendTypes[j] = "java.lang.String";

							}
							if (busquedaPreviaAnnotation.construyeBean()) {
								MethodUtils
										.invokeExactMethod(
												objBusqueda,
												"set"
														+ StringUtils
																.capitalize(paramValue[0]),
												paramsToBackend[j]);
							}
							LOGGER.debug("Parámetro en " + j + " : "
									+ paramsToBackend[j] + " de tipo: "
									+ paramsToBackendTypes[j]);
						}

						// Buscar el método que corresponda contra los
						// parámetros establecidos
						for (int k = 0; k < metodos.length; k++) {
							boolean metodoOk = true;
							if (("ejecutar" + busquedaPreviaAnnotation
									.tipoServicio()).equals(metodos[k]
									.getName())
									&& busquedaPreviaAnnotation.parametros().length == metodos[k]
											.getParameterTypes().length
									&& !busquedaPreviaAnnotation
											.construyeBean()) {
								metodoInvocarBackend = metodos[k];
								LOGGER.debug("Método: "
										+ metodoInvocarBackend.getName()
										+ " de clase: "
										+ busquedaPreviaAnnotation.backend()
										+ " --- con "
										+ metodoInvocarBackend
												.getParameterTypes().length
										+ " parámetro(s). ");

								Class[] tipoParams = metodoInvocarBackend
										.getParameterTypes();

								for (int l = 0; l < tipoParams.length; l++) {
									LOGGER.debug("Tipo parámetro del método: "
											+ tipoParams[l].getSimpleName()
											+ " ,parámetro enviado: "
											+ paramsToBackendTypes[l]);

									if (!tipoParams[l].getSimpleName().equals(
											paramsToBackendTypes[l].toString())) {
										metodoOk = false;
									}
								}
								if (metodoOk) {
									break;
								}
							}
							if (("ejecutar" + busquedaPreviaAnnotation
									.tipoServicio()).equals(metodos[k]
									.getName())
									&& busquedaPreviaAnnotation.construyeBean()) {
								metodoInvocarBackend = metodos[k];
								LOGGER.debug("Método: "
										+ metodoInvocarBackend.getName()
										+ " de clase: "
										+ busquedaPreviaAnnotation.backend()
										+ " --- con Objeto: "
										+ busquedaPreviaAnnotation
												.objetoBusqueda()
										+ " como parámetro. ");

								Class[] tipoParams = metodoInvocarBackend
										.getParameterTypes();
								if (tipoParams.length == 1
										&& tipoParams[0]
												.isInstance(Object.class)) {
									break;
								}
							}

						}

						if (metodoInvocarBackend != null) {
							if (busquedaPreviaAnnotation.construyeBean()) {
								this.datos = (List<Object>) metodoInvocarBackend
										.invoke(backend, objBusqueda);
							} else {
								this.datos = (List<Object>) metodoInvocarBackend
										.invoke(backend, paramsToBackend);
							}

							if (this.datos != null && this.datos.size() == 1) {
								this.pintarTablaResultados = true;
								this.pintarTablaResultadosIntermedios = false;
								SelectEvent irAPaso2 = new SelectEvent(
										new UISelectOne(), new AjaxBehavior(),
										this.datos.get(0));
								this.buscarPasoDosIntermedio(irAPaso2);
							} else {

								if (!datos.isEmpty()) {
									// mostrar tabla intermedia de resultados y
									// no la principal
									this.pintarTablaResultadosIntermedios = true;
									this.pintarTablaResultados = false;
									this.busquedaIntermedia = true;
								} else {
									// pintar tabla principal para mostrar
									// mensaje de "resultados no encontrados"
									this.pintarTablaResultados = true;
									this.pintarTablaResultadosIntermedios = false;
								}
								this.claseIntermediaActual = busquedaPreviaAnnotation
										.objetoBusqueda();

								try {
									this.columnas = ConstructorModelosColumnaUtils
											.construir(
													Class.forName(busquedaPreviaAnnotation
															.objetoBusqueda()),
													this.opcion, true);
									pagina++;
									if (metodoInvocarBackend.getName()
											.contains("WS")
											&& masDatos == false) {
										webservice = true;
									}
								} catch (ClassNotFoundException e) {
									this.columnas = null;
								}
							}

						} else {
							throw new NoControlableException(
									"No se ha podido invocar el método de busqueda necesario.",
									"No se ha podido encontrar el método de busqueda necesario en el backend.");
						}
					} else {
						throw new NoControlableException(
								"No se ha podido invocar el método de busqueda necesario.",
								"No se ha podido encontrar el objeto backend necesario");
					}
				} catch (IllegalAccessException e) {
					throw new NoControlableException(
							"No se ha podido invocar el método intermedio de busqueda necesario.",
							e);
				} catch (NoSuchMethodException e) {
					throw new NoControlableException(
							"No se ha podido invocar el método intermedio de busqueda necesario.",
							e);
				} catch (InvocationTargetException e) {
					throw new NoControlableException(
							"No se ha podido invocar el método intermedio de busqueda necesario.",
							e);
				} catch (NoSuchFieldException e) {
					throw new NoControlableException(
							"No se ha podido invocar el método intermedio de busqueda necesario.",
							e);
				} catch (SecurityException e) {
					throw new NoControlableException(
							"No se ha podido invocar el método intermedio de busqueda necesario.",
							e);
				} catch (InstantiationException e) {
					throw new NoControlableException(
							"No se ha podido invocar el método intermedio de busqueda necesario. No fue posible instansiar el objeto requerido.",
							e);
				} catch (ClassNotFoundException e) {
					throw new NoControlableException(
							"No se ha podido invocar el método intermedio de busqueda necesario. No fue posible encontrar la clase del objeto requerido.",
							e);
				}
				return true;
			}
		}
		return false;
	}

	public void buscarPasoDosIntermedio(SelectEvent event) {
		this.eventPasoIntermedio = event;
		Object fila = event.getObject();

		if (destino == null
				&& this.claseActualObjeto instanceof DomiciliosClienteBusquedaBean) {

			ClienteBean cliente = new ClienteBean();

			if (fila instanceof PersonasClienteBusquedaBean) {
				Integer idInterna = ((PersonasClienteBusquedaBean) fila)
						.getIdInterna();
				String nombre = ((PersonasClienteBusquedaBean) fila)
						.getNombreCompleto();
				cliente.setIdInterna(idInterna);
				cliente.setNombre(nombre);

				obtieneFlash().put(ParametrosFlashEnum.CLIENTE.getParamFlash(),
						cliente);
				obtieneFlash().put(
						ParametrosFlashEnum.TIPO_CLIENTE.getParamFlash(),
						TipoCliente.PERSONA_FISICA);
				obtieneFlash().put(
						ParametrosFlashEnum.NOMBRE_CLIENTE.getParamFlash(),
						nombre);

			} else if (fila instanceof PersonaMoralBusquedaBean) {
				Integer idInterna = ((PersonaMoralBusquedaBean) fila)
						.getIdInterna();
				String nombre = ((PersonaMoralBusquedaBean) fila)
						.getRazonSocial();
				cliente.setIdInterna(idInterna);
				cliente.setNombre(nombre);

				obtieneFlash().put(ParametrosFlashEnum.CLIENTE.getParamFlash(),
						cliente);
				obtieneFlash().put(
						ParametrosFlashEnum.TIPO_CLIENTE.getParamFlash(),
						TipoCliente.PERSONA_MORAL);
				obtieneFlash().put(
						ParametrosFlashEnum.NOMBRE_CLIENTE.getParamFlash(),
						nombre);
			}

			ConfigurableNavigationHandler configurableNavigationHandler = (ConfigurableNavigationHandler) FacesContext
					.getCurrentInstance().getApplication()
					.getNavigationHandler();
			final String ruta = NavegacionEnum.DOMICILIO_PERSONA.getRuta();
			managedBeanStateRecover.enviaController(this,
					NavegacionEnum.BUSQUEDA);
			configurableNavigationHandler.performNavigation(ruta);
			return;

		}

		BusquedaPreviaAnnotation[] busquedasPreviasAnnotation = claseActualObjeto
				.getClass()
				.getAnnotation(ArregloBusquedasPreviasAnnotation.class).value();
		for (int i = 0; i < busquedasPreviasAnnotation.length; i++) {
			BusquedaPreviaAnnotation busquedaPreviaAnnotation = busquedasPreviasAnnotation[i];

			if (busquedaPreviaAnnotation.opcionBusqueda() == this.opcion) {
				try {
					Object valorSet = MethodUtils
							.invokeExactMethod(
									fila,
									"get"
											+ StringUtils
													.capitalize(busquedaPreviaAnnotation
															.paramSegundaBusqueda()),
									null);
					MethodUtils
							.invokeMethod(
									claseActualObjeto,
									"set"
											+ StringUtils
													.capitalize(busquedaPreviaAnnotation
															.paramSegundaBusqueda()),
									valorSet);
					LOGGER.debug("Set de parámetro: idInterna con valor recuperado de búsqueda intermedia: "
							+ valorSet);

					// Obtiene el metodo que ejecutará el WebService según el
					// tipo de búsqueda
					String metodoWebService = ProveedorMensajeUtils
							.getValoresTipos("BUSQUEDA"
									+ this.seleccionTipo
									+ busquedaPreviaAnnotation
											.opcionSegundaBusqueda());
					try {
						// Se ejecuta método obtenido
						MethodUtils.invokeMethod(this, metodoWebService,
								this.claseActualObjeto);
					} catch (NoSuchMethodException | IllegalAccessException
							| InvocationTargetException e) {
						LOGGER.debug("Error al obtener los valores de los campos para el resultado de la busqueda");
						throw new NoControlableException(
								"Se ha producido un error al obtener los valores de los campos para el resultado de la busqueda",
								e);
					}

					this.pintarTablaResultados = true;
					this.pintarTablaResultadosIntermedios = false;
					this.claseIntermediaActual = "";

					try {
						this.columnas = ConstructorModelosColumnaUtils
								.construir(Class.forName(this.claseActual),
										this.opcion, false);
					} catch (ClassNotFoundException e) {
						this.columnas = null;
					}

				} catch (IllegalAccessException e) {
					throw new NoControlableException(
							"No se ha podido invocar el método intermedio de busqueda necesario.",
							e);
				} catch (InvocationTargetException e) {
					throw new NoControlableException(
							"No se ha podido invocar el método intermedio de busqueda necesario.",
							e);
				} catch (SecurityException e) {
					throw new NoControlableException(
							"No se ha podido invocar el método intermedio de busqueda necesario.",
							e);
				} catch (NoSuchMethodException e) {
					throw new NoControlableException(
							"No se ha podido invocar el método intermedio de busqueda necesario.",
							e);
				}
			}
		}
	}

	/**
	 * MÃ©todo que guarda los filtros con los que se ejecutÃ³ la busqueda.
	 */
	public void guardarFiltros() {
		for (DynaFormControl dynaFormControl : this.modelo.getControls()) {
			DynaPropertyModel dynaPropertyModel = ((DynaPropertyModel) dynaFormControl
					.getData());
			String nombreCampo = "";
			if ("".equals(dynaPropertyModel.getTituloCampo())) {
				nombreCampo = dynaPropertyModel.getNombre();
			} else {
				nombreCampo = dynaPropertyModel.getTituloCampo();
			}
			Object valor = "";

			if (dynaFormControl.getType().equals("date")
					&& dynaPropertyModel.getValor() != null) {
				SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
				valor = formatter.format((Date) dynaPropertyModel.getValor());
			} else if (dynaFormControl.getType().equals("boolean")) {
				if ((boolean) dynaPropertyModel.getValor()) {
					valor = "Si";
				} else {
					valor = "No";
				}
			} else if (dynaFormControl.getType().equals("autocompletarcentros")
					&& dynaPropertyModel.getValor() != null) {
				CatalogoBean catalogoBean = (CatalogoBean) dynaPropertyModel
						.getValor();
				valor = catalogoBean.getDescripcionL();
			} else if (dynaFormControl.getType().equals(
					"autocompletarcuentascontables")
					&& dynaPropertyModel.getValor() != null) {
				CuentaContableBean cuentaContableBean = (CuentaContableBean) dynaPropertyModel
						.getValor();
				valor = cuentaContableBean.getIdCuentaContable();
			} else if (dynaFormControl.getType().equals("list")
					&& dynaPropertyModel.getValor() != null) {
				for (SelectItem selectItem : dynaPropertyModel.getSelectItems()) {
					if (selectItem.getValue().equals(
							dynaPropertyModel.getValor())) {
						valor = selectItem.getLabel();
					}
				}
			} else {

				if (dynaPropertyModel.getValor() != null) {
					valor = ((String) dynaPropertyModel.getValor())
							.toUpperCase();
				}
			}

			if (!"".equals(valor)) {
				String[] elemento = { nombreCampo, valor.toString() };
				filtros.add(elemento);
			}
		}
	}

	/**
	 * Método que ingresa valores para la ejecución del webservice de búsqueda
	 * por nombre de Gestor.
	 * 
	 * @param obj
	 *            Objeto instanciado.
	 * @return Ruta de navegación.
	 * @throws NoControlableException
	 *             Excepción no controlable.
	 */
	public String setValoresBusquedaNombre(Object obj)
			throws NoControlableException {
		String rutaDestino = null;
		try {

			BusquedaNombreBackEnd busquedaNombreBackEnd = (BusquedaNombreBackEnd) appContext
					.getBean("busquedaNombreBackEnd");

			this.datos.addAll(busquedaNombreBackEnd.ejecutarTRN(obj));
			this.pagina++;
			if (this.datos.size() == 1) {

				// Obtiene el constante del enum TipoCliente
				String valorEnum = ProveedorMensajeUtils
						.getValoresTipos("ENUM_" + seleccionTipo);

				this.obtieneFlash().put("tipoCliente",
						Enum.valueOf(TipoCliente.class, valorEnum));

				// Se guarda id Interno en el Flash para mandarlo.
				this.obtieneFlash().putAll(
						ParametrosFlashUtils.obtenerParametros(
								this.datos.get(0), claseActual));

				managedBeanStateRecover.enviaController(this,
						NavegacionEnum.BUSQUEDA);

				if (destino == null) {
					this.obtieneFlash()
							.put(ParametrosFlashEnum.ORIGEN_BUSQUEDA
									.getParamFlash(),
									true);
					rutaDestino = NavegacionEnum.FICHA_CLIENTE.getRuta();
				} else {
					rutaDestino = destino.getRuta();
					this.managedBeanStateRecover
							.finNavegacion(destinoController);
				}
			}
		} catch (ControlableException c) {
			FacesMessage message = null;
			if (c.getRtncod() != 7) {
				message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
						"Busqueda Genérica", c.getMensajeDetalle());
				RequestContext.getCurrentInstance()
						.showMessageInDialog(message);
			}
		}
		return rutaDestino;
	}

	/**
	 * Método que ingresa valores para la ejecución del webservice de búsqueda
	 * por Id Externa.
	 * 
	 * @param obj
	 *            Objeto instanciado.
	 * @return Ruta de navegación.
	 * @throws NoControlableException
	 *             Excepción no controlable.
	 */
	public String setValoresBusquedaIdExterna(Object obj)
			throws NoControlableException {
		String rutaDestino = null;
		try {
			BusquedaIdExternaBackEnd busquedaIdExternaBackEnd = (BusquedaIdExternaBackEnd) appContext
					.getBean("busquedaIdExternaBackEnd");

			this.datos.addAll(busquedaIdExternaBackEnd.ejecutarTRN(obj));
			this.pagina++;
			if (this.datos.size() == 1) {

				// Obtiene el constante del enum TipoCliente
				String valorEnum = ProveedorMensajeUtils
						.getValoresTipos("ENUM_" + seleccionTipo);

				this.obtieneFlash().put("tipoCliente",
						Enum.valueOf(TipoCliente.class, valorEnum));

				// Se guarda id Interno en el Flash para mandarlo.
				this.obtieneFlash().putAll(
						ParametrosFlashUtils.obtenerParametros(
								this.datos.get(0), claseActual));

				managedBeanStateRecover.enviaController(this,
						NavegacionEnum.BUSQUEDA);

				if (destino == null) {
					this.obtieneFlash()
							.put(ParametrosFlashEnum.ORIGEN_BUSQUEDA
									.getParamFlash(),
									true);
					rutaDestino = NavegacionEnum.FICHA_CLIENTE.getRuta();
				} else {
					rutaDestino = destino.getRuta();
					this.managedBeanStateRecover
							.finNavegacion(destinoController);
				}
			}
		} catch (ControlableException c) {
			FacesMessage message = null;
			if (c.getRtncod() != 7) {
				message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
						"Busqueda Genérica", c.getMensajeDetalle());
				RequestContext.getCurrentInstance()
						.showMessageInDialog(message);
			}
		}
		return rutaDestino;
	}

	/**
	 * Método que ingresa valores para la ejecución del webservice de búsqueda
	 * por Id Interna.
	 * 
	 * @param obj
	 *            Objeto instanciado.
	 * @return String
	 */
	public String setValoresBusquedaIdInterna(Object obj) {
		String rutaDestino = null;
		try {

			ConsultaDatosGestorBackEnd consultaDatosGestorBackEnd = (ConsultaDatosGestorBackEnd) appContext
					.getBean("consultaDatosGestorBackEnd");

			GestorBean gestor = consultaDatosGestorBackEnd
					.ejecutarTRN(((PersonaGestorBusquedaBean) obj)
							.getIdInterna());
			if (gestor != null) {

				// Obtiene el constante del enum TipoCliente
				String valorEnum = ProveedorMensajeUtils
						.getValoresTipos("ENUM_" + seleccionTipo);

				this.obtieneFlash().put("tipoCliente",
						Enum.valueOf(TipoCliente.class, valorEnum));

				this.obtieneFlash().putAll(
						ParametrosFlashUtils.obtenerParametros(
								(PersonaGestorBusquedaBean) obj, claseActual));
				managedBeanStateRecover.enviaController(this,
						NavegacionEnum.BUSQUEDA);

				if (destino == null) {
					this.obtieneFlash()
							.put(ParametrosFlashEnum.ORIGEN_BUSQUEDA
									.getParamFlash(),
									true);
					rutaDestino = NavegacionEnum.FICHA_CLIENTE.getRuta();
				} else {
					rutaDestino = destino.getRuta();
					this.managedBeanStateRecover
							.finNavegacion(destinoController);
				}
			}
		} catch (ControlableException c) {
			if (c.getRtncod() != 7) {
				FacesMessage message = new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "Busqueda Genérica",
						c.getMensajeUsuario());
				RequestContext.getCurrentInstance()
						.showMessageInDialog(message);
			}
		}
		return rutaDestino;
	}

	/**
	 * Método para ejecutar el servicio para la búsqueda de Grupos.
	 * 
	 * @param obj
	 *            Bean con el grupo a buscar
	 * @return Ruta a direccionar
	 * @throws NoControlableException
	 *             Excepción no controlable.
	 */
	public String setValoresBusquedaGrupo(Object obj)
			throws NoControlableException {
		String rutaDestino = null;
		try {

			BusquedaGrupoBackEnd busquedaGrupoBackEnd = (BusquedaGrupoBackEnd) appContext
					.getBean("busquedaGrupoBackEnd");

			this.datos.addAll(busquedaGrupoBackEnd.ejecutarTRN(obj));
			pagina++;
			if (this.datos != null && !this.datos.isEmpty()
					&& this.datos.size() == 1) {
				// Se guarda id Interno en el Flash para mandarlo a la ficha de
				// Cliente
				this.obtieneFlash().putAll(
						ParametrosFlashUtils.obtenerParametros(
								this.datos.get(0), claseActual));

				// Obtiene el constante del enum TipoCliente
				String valorEnum = ProveedorMensajeUtils
						.getValoresTipos("ENUM_" + seleccionTipo);

				// Se guarda el tipo de Cliente en el Flash para mandarlo a la
				// ficha de Cliente
				this.obtieneFlash().put("tipoCliente",
						Enum.valueOf(TipoCliente.class, valorEnum));

				managedBeanStateRecover.enviaController(this,
						NavegacionEnum.BUSQUEDA);
				if (destino == null) {
					this.obtieneFlash()
							.put(ParametrosFlashEnum.ORIGEN_BUSQUEDA
									.getParamFlash(),
									true);
					rutaDestino = NavegacionEnum.FICHA_CLIENTE.getRuta();
				} else {
					rutaDestino = destino.getRuta();
					this.managedBeanStateRecover
							.finNavegacion(destinoController);
				}
			}
		} catch (ControlableException c) {
			FacesMessage message = null;
			if (c.getRtncod() != 7) {
				message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
						"Busqueda Genérica", c.getMensajeDetalle());
				RequestContext.getCurrentInstance()
						.showMessageInDialog(message);
			}
		}
		return rutaDestino;

	}

	/**
	 * Método que ingresa valores para la ejecución del webservice de búsqueda
	 * por codigo de entidades.
	 * 
	 * @param obj
	 *            Objeto instanciado.
	 */
	public String setValoresBusquedaEntidad(Object obj) {
		String rutaDestino = null;
		EntidadBusquedaBean entidadBuqueda = (EntidadBusquedaBean) obj;

		ConsultaListaEntidadesBackend consultaListaEntidadesBackend = (ConsultaListaEntidadesBackend) appContext
				.getBean("consultaListaEntidadesBackend");

		this.datos.addAll(consultaListaEntidadesBackend
				.ejectuarTRN(entidadBuqueda));
		pagina++;
		if (this.datos != null && !this.datos.isEmpty()
				&& this.datos.size() == 1) {
			this.obtieneFlash().putAll(
					ParametrosFlashUtils.obtenerParametros(this.datos.get(0),
							claseActual));

			managedBeanStateRecover.enviaController(this,
					NavegacionEnum.BUSQUEDA);
			if (destino == null) {
				rutaDestino = NavegacionEnum.CONSULTA_ENTIDAD.getRuta();
			} else {
				rutaDestino = destino.getRuta();
				this.managedBeanStateRecover.finNavegacion(destinoController);
			}
		}
		return rutaDestino;

	}

	/**
	 * Método que ingresa valores para la ejecución del webservice de búsqueda
	 * por codigo de centro.
	 * 
	 * @param obj
	 *            Objeto instanciado.
	 * @return String Ruta a direccionar
	 * @throws NoControlableException
	 *             Excepción No Controlable del servicio.
	 */
	public String setValoresBusquedaCentroCodigo(Object obj)
			throws NoControlableException {
		String rutaDestino = null;
		try {

			BusquedaCentroCodigoBackEnd busquedaCentroCodigoBackEnd = (BusquedaCentroCodigoBackEnd) appContext
					.getBean("busquedaCentroCodigoBackEnd");

			CentroBusquedaBean centro = busquedaCentroCodigoBackEnd
					.ejecutarTRN(obj);
			if (centro != null) {
				this.obtieneFlash().putAll(
						ParametrosFlashUtils.obtenerParametros(centro,
								claseActual));
				managedBeanStateRecover.enviaController(this,
						NavegacionEnum.BUSQUEDA);

				if (destino == null) {
					rutaDestino = NavegacionEnum.ALTA_CENTRO.getRuta();
				} else {
					rutaDestino = destino.getRuta();
					this.managedBeanStateRecover
							.finNavegacion(destinoController);
				}

			}
		} catch (ControlableException c) {
			if (c.getRtncod() != 7) {
				FacesMessage message = new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "Busqueda Genérica",
						c.getMensajeDetalle());
				RequestContext.getCurrentInstance()
						.showMessageInDialog(message);
			}
		}

		return rutaDestino;
	}

	/**
	 * Método que ingresa valores para la ejecución del webservice de búsqueda
	 * por nombre de centro.
	 * 
	 * @param obj
	 *            Objeto instanciado.
	 * @return String Ruta a direccionar
	 * @throws NoControlableException
	 *             Excepción No Controlable del servicio.
	 */
	public String setValoresBusquedaCentroNombre(Object obj)
			throws NoControlableException {
		String rutaDestino = null;

		try {

			BusquedaCentroNombreBackEnd busquedaCentroNombreBackEnd = (BusquedaCentroNombreBackEnd) appContext
					.getBean("busquedaCentroNombreBackEnd");

			this.datos.addAll(busquedaCentroNombreBackEnd.ejecutarTRN(obj));
			this.pagina++;
			if (this.datos.size() == 1) {
				// Se guarda id Interno en el Flash para mandarlo.
				this.obtieneFlash().putAll(
						ParametrosFlashUtils.obtenerParametros(
								this.datos.get(0), claseActual));

				managedBeanStateRecover.enviaController(this,
						NavegacionEnum.BUSQUEDA);

				if (destino == null) {
					rutaDestino = NavegacionEnum.ALTA_CENTRO.getRuta();
				} else {
					rutaDestino = destino.getRuta();
					this.managedBeanStateRecover
							.finNavegacion(destinoController);
				}
			}
		} catch (ControlableException c) {
			if (c.getRtncod() != 7) {
				FacesMessage message = new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "Busqueda Genérica",
						c.getMensajeDetalle());
				RequestContext.getCurrentInstance()
						.showMessageInDialog(message);
			}
		}

		return rutaDestino;
	}

	/**
	 * Método que ingresa valores para la ejecución del servicio de búsqueda por
	 * pan de cuenta.
	 * 
	 * @param obj
	 *            Objeto instanciado
	 * @return String Ruta a direccionar.
	 * @throws NoControlableException
	 *             Excepción No Controlable del servicio.
	 */
	public String setValoresBusquedaCuenta(Object obj)
			throws NoControlableException {
		String rutaDestino = null;
		try {

			BusquedaCuentaBackEnd busquedaCuentaBackEnd = (BusquedaCuentaBackEnd) appContext
					.getBean("busquedaCuentaBackEnd");

			this.datos = busquedaCuentaBackEnd.ejecutarWS(obj);
			pagina++;
			if (datos != null && datos.size() > 0) {
				if (!confirmaSeleccion) {
					this.obtieneFlash().put(
							ParametrosFlashEnum.CUENTA_BEAN.getParamFlash(),
							(CuentaBean) datos.get(0));
					managedBeanStateRecover.enviaController(this,
							NavegacionEnum.BUSQUEDA);
					if (destino == null) {
						this.obtieneFlash()
								.put(ParametrosFlashEnum.ORIGEN_BUSQUEDA
										.getParamFlash(),
										true);
						rutaDestino = NavegacionEnum.FICHA_CUENTA.getRuta();
					} else {
						rutaDestino = destino.getRuta();
						this.managedBeanStateRecover
								.finNavegacion(destinoController);
					}
				} else {
					CuentaBean cuentaBean = (CuentaBean) datos.get(0);
					CuentaBusquedaBean cuentaBusquedaBean = new CuentaBusquedaBean();
					cuentaBusquedaBean.setNumeroCuenta(cuentaBean
							.getNumeroCuenta());
					cuentaBusquedaBean.setNumPan(Long.parseLong(cuentaBean
							.getPan()));
					cuentaBusquedaBean.setEstado(catalogoUtils.getCatalogoDesc(
							CatalogoEnum.TP_ECV_AC, cuentaBean.getEstado()));
					cuentaBusquedaBean.setTipoDeCuenta(cuentaBean
							.getTipoCuenta());
					cuentaBusquedaBean
							.setTitular(cuentaBean.getNombreTitular());
					cuentaBusquedaBean.setNivelCuenta(cuentaBean
							.getNivelCuenta());
					cuentaBusquedaBean.setCentro(cuentaBean.getCentro());
					datos.remove(0);
					datos.add(cuentaBusquedaBean);
				}
			}
		} catch (ControlableException c) {
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "Busqueda Genérica",
					c.getMensajeDetalle());
			RequestContext.getCurrentInstance().showMessageInDialog(message);
		}
		return rutaDestino;
	}

	/**
	 * Método que ingresa valores para la ejecución del servicio de búsqueda de
	 * Persona Física.
	 * 
	 * @param obj
	 *            Objeto instanciado
	 * @return String Ruta a direccionar.
	 * @throws NoControlableException
	 *             Excepción No Controlable del servicio.
	 */
	public String setValoresBusquedaPersonaFisica(Object obj)
			throws NoControlableException {
		String rutaDestino = null;
		try {

			BusquedaPersonaFisicaBackEnd busquedaPersonaFisicaBackEnd = (BusquedaPersonaFisicaBackEnd) appContext
					.getBean("busquedaPersonaFisicaBackEnd");

			this.datos.addAll(busquedaPersonaFisicaBackEnd.ejecutarWS(obj));
			pagina++;
			webservice = true;
			if (this.datos.size() == 1) {
				// Se guarda id Interno en el Flash para mandarlo a la ficha de
				// Cliente
				this.obtieneFlash().putAll(
						ParametrosFlashUtils.obtenerParametros(
								this.datos.get(0), claseActual));

				// Obtiene el constante del enum TipoCliente
				String valorEnum = ProveedorMensajeUtils
						.getValoresTipos("ENUM_" + seleccionTipo);

				// Se guarda el tipo de Cliente en el Flash para mandarlo a la
				// ficha de Cliente
				this.obtieneFlash().put("tipoCliente",
						Enum.valueOf(TipoCliente.class, valorEnum));
				managedBeanStateRecover.enviaController(this,
						NavegacionEnum.BUSQUEDA);

				if (destino == null) {
					this.obtieneFlash()
							.put(ParametrosFlashEnum.ORIGEN_BUSQUEDA
									.getParamFlash(),
									true);
//					rutaDestino =
//							mx.babel.bansefi.banksystem.base.enums.integracionAppWhere.NavegacionEnum.FICHAPERSONAFISICA.getRuta();
					rutaDestino = NavegacionEnum.FICHA_CLIENTE.getRuta();
				} else {
					rutaDestino = destino.getRuta();
					this.managedBeanStateRecover
							.finNavegacion(destinoController);
				}
			}

		} catch (ControlableException c) {
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "Busqueda Genérica",
					c.getMensajeDetalle());
			RequestContext.getCurrentInstance().showMessageInDialog(message);
		}

		return rutaDestino;
	}

	/**
	 * Método que ingresa valores para la ejecución del servicio de búsqueda de
	 * Persona Moral.
	 * 
	 * @param obj
	 *            Objeto instanciado
	 * @return String a direccionar.
	 * @throws NoControlableException
	 *             Excepción No Controlable del servicio.
	 */
	public String setValoresBusquedaPersonaMoral(Object obj)
			throws NoControlableException {
		String rutaDestino = null;
		try {

			BusquedaPersonaMoralBackEnd busquedaPersonaMoralBackEnd = (BusquedaPersonaMoralBackEnd) appContext
					.getBean("busquedaPersonaMoralBackEnd");

			this.datos.addAll(busquedaPersonaMoralBackEnd.ejecutarWS(obj));
			pagina++;
			webservice = true;
			if (this.datos.size() == 1) {
				// Se guarda id Interno en el Flash para mandarlo a la ficha de
				// Cliente
				this.obtieneFlash().putAll(
						ParametrosFlashUtils.obtenerParametros(
								this.datos.get(0), claseActual));

				// Obtiene el constante del enum TipoCliente
				String valorEnum = ProveedorMensajeUtils
						.getValoresTipos("ENUM_" + seleccionTipo);

				// Se guarda el tipo de Cliente en el Flash para mandarlo a la
				// ficha de Cliente
				this.obtieneFlash().put("tipoCliente",
						Enum.valueOf(TipoCliente.class, valorEnum));
				managedBeanStateRecover.enviaController(this,
						NavegacionEnum.BUSQUEDA);

				if (destino == null) {
					this.obtieneFlash()
							.put(ParametrosFlashEnum.ORIGEN_BUSQUEDA
									.getParamFlash(),
									true);
//					rutaDestino =
//							mx.babel.bansefi.banksystem.base.enums.integracionAppWhere.NavegacionEnum.FICHAPERSONAMORAL.getRuta();
					rutaDestino = NavegacionEnum.FICHA_CLIENTE.getRuta();
				} else {
					rutaDestino = destino.getRuta();
					this.managedBeanStateRecover
							.finNavegacion(destinoController);
				}
			}

		} catch (ControlableException c) {
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "Busqueda Genérica",
					c.getMensajeDetalle());
			RequestContext.getCurrentInstance().showMessageInDialog(message);
		}
		return rutaDestino;
	}

	/**
	 * Método que ingresa valores para la ejecución del webservice de búsqueda
	 * de Empleados a traves del id de Empleado.
	 * 
	 * @param obj
	 *            Objeto instanciado.
	 */
	public String setValoresBusquedaIdEmpleado(Object obj) {

		String rutaDestino = null;

		String idEmpleado = ((EmpleadoBusquedaBean) obj).getIdEmpleado();
		String tipoResultado = "resultadosBusquedaEmpleadosIdEmpleado";

		ConsultaEmpleadoBackEnd consultaEmpleadoBackEnd = (ConsultaEmpleadoBackEnd) appContext
				.getBean("consultaEmpleadoBackEnd");

		this.datos = consultaEmpleadoBackEnd.ejecutarTRN(idEmpleado,
				tipoResultado);

		if (this.datos != null && this.datos.size() == 1) {
			this.obtieneFlash().put(
					ParametrosFlashEnum.ID_EMPLEADO.getParamFlash(),
					((EmpleadoBusquedaBean) this.datos.get(0)).getIdEmpleado());

			this.obtieneFlash().putAll(
					ParametrosFlashUtils.obtenerParametros(this.datos.get(0),
							claseActual));

			managedBeanStateRecover.enviaController(this,
					NavegacionEnum.BUSQUEDA);

			if (destino == null) {
				rutaDestino = NavegacionEnum.FICHA_EMPLEADO.getRuta();
			} else {
				rutaDestino = destino.getRuta();
				this.managedBeanStateRecover.finNavegacion(destinoController);
			}
		}
		return rutaDestino;

	}

	/**
	 * Método que ingresa valores para la ejecución del webservice de búsqueda
	 * de Empleados a traves del id interno del Empleado.
	 * 
	 * @param obj
	 *            Objeto instanciado.
	 */
	public String setValoresBusquedaIdInternaEmpleado(Object obj) {

		String rutaDestino = null;

		int idInterna = ((EmpleadoBusquedaBean) obj).getIdInterna();
		String tipoResultado = "resultadosBusquedaEmpleadosIdInterna";

		ConsultaEmpleadoBackEnd consultaEmpleadoBackEnd = (ConsultaEmpleadoBackEnd) appContext
				.getBean("consultaEmpleadoBackEnd");

		this.datos = consultaEmpleadoBackEnd.ejecutarTRN(idInterna,
				tipoResultado);

		if (this.datos != null && this.datos.size() == 1) {
			this.obtieneFlash().put(
					ParametrosFlashEnum.ID_EMPLEADO.getParamFlash(),
					((EmpleadoBusquedaBean) this.datos.get(0)).getIdEmpleado());

			this.obtieneFlash().putAll(
					ParametrosFlashUtils.obtenerParametros(this.datos.get(0),
							claseActual));

			managedBeanStateRecover.enviaController(this,
					NavegacionEnum.BUSQUEDA);

			if (destino == null) {
				rutaDestino = NavegacionEnum.FICHA_EMPLEADO.getRuta();
			} else {
				rutaDestino = destino.getRuta();
				this.managedBeanStateRecover.finNavegacion(destinoController);
			}
		}
		return rutaDestino;
	}

	/**
	 * Método que ingresa valores para la ejecución del webservice de búsqueda
	 * de Empleados a traves del codigo del centro
	 * 
	 * @param obj
	 *            Objeto instanciado.
	 */
	public void setValoresBusquedaCentroEmpleado(Object obj) {
		String tipoBusqueda = "CENTROS";
		ConsultaListaEmpleadosBackEnd consultaListaEmpleadosBackEnd = (ConsultaListaEmpleadosBackEnd) appContext
				.getBean("consultaListaEmpleadosBackEnd");

		List<Object> resultado = consultaListaEmpleadosBackEnd.ejecutarTRN(obj,
				tipoBusqueda);
		this.datos.addAll(resultado);
		this.pagina++;
	}

	/**
	 * Método utilizado para las busquedas de anotaciones a partir de busquedas
	 * de clientes.
	 * 
	 * @param obj
	 *            Objeto instanciado.
	 */
	public void setValoresBusquedaAnotacionesCliente(Object obj) {
		int idInterna = ((AnotacionClienteBusquedaBean) obj).getIdInterna();

		ConsultaListaAnotacionesBackEnd consultaListaAnotacionesBackEnd = (ConsultaListaAnotacionesBackEnd) appContext
				.getBean("consultaListaAnotacionesBackEnd");
		;
		this.datos = consultaListaAnotacionesBackEnd.ejecutarTRN(idInterna,
				false, true);
		pagina++;
	}

	/**
	 * Método utilizado para las busquedas de anotaciones a partir de busquedas
	 * de clientes.
	 * 
	 * @param obj
	 *            Objeto instanciado.
	 */
	public void setValoresBusquedaAnotacionesCuenta(Object obj) {
		long numCuenta = ((AnotacionCuentaBusquedaBean) obj).getNumeroCuenta();
		ConsultaListaAnotacionesBackEnd consultaListaAnotacionesBackEnd = (ConsultaListaAnotacionesBackEnd) appContext
				.getBean("consultaListaAnotacionesBackEnd");
		this.datos = new ArrayList<Object>(
				consultaListaAnotacionesBackEnd.ejecutarTRN(numCuenta, false,
						true));
	}

	/**
	 * Método utilizado para las busquedas de domicilios a partir de busquedas
	 * de clientes.
	 * 
	 * @param obj
	 *            Objeto instanciado.
	 */
	public void setValoresBusquedaDomiciliosCliente(Object obj) {
		int idInterna = ((DomiciliosClienteBusquedaBean) obj).getIdInterna();

		ConsultaDomiciliosPersonaBackEnd consultaDomiciliosPersonaBackEnd = (ConsultaDomiciliosPersonaBackEnd) appContext
				.getBean("consultaDomiciliosPersonaBackEnd");

		this.datos = new ArrayList<Object>(
				wrapperBeanService
						.wrappBusquedaDomiciliosBean(consultaDomiciliosPersonaBackEnd
								.ejecutarTRN(idInterna, null)));
		for (Object domicilio : this.datos) {
			((DomiciliosClienteBusquedaBean) domicilio).setIdInterna(idInterna);
		}

	}

	/**
	 * Método utilizado para la búsqueda de notificaciones.
	 * 
	 * @param obj
	 */
	public void setValoresBusquedaNotificaciones(Object obj) {
		UsuarioBean usuarioBean = new UsuarioBean();
		usuarioBean.setId(contextoUtils.getId());
		usuarioBean.setPwd(contextoUtils.getPwd());
		usuarioBean.setDireccionIp(contextoUtils.getIp());
		usuarioBean.setEntidad(contextoUtils.getEntidad());
		usuarioBean.setSucursal(((NotificacionBusquedaBean) obj).getCentro());
		NotificacionBackEnd notificacionBackEnd = (NotificacionBackEnd) appContext
				.getBean("notificacionBackEnd");
		this.datos = new ArrayList<Object>(
				notificacionWrapper.wrappBusquedaNotificacionesBean(obj,
						notificacionBackEnd
								.obtenerListaNotificaciones(usuarioBean)));
		this.pagina++;
		if (this.claseActual.equalsIgnoreCase(NotificacionBusquedaBean.class
				.getName())) {
			this.tablaNotificaciones = true;
		}
	}

	/**
	 * Método que ingresa valores para la ejecución del webservice de búsqueda
	 * por Id Interna.
	 * 
	 * @param obj
	 *            Objeto instanciado.
	 * @return String
	 */
	public String setValoresBusquedaIdTransaccion(Object obj) {
		String rutaDestino = null;
		String numOperacion = ((RecibosNoDomiciliadosBusquedaBean) obj)
				.getNumOperacion();
		ConsultaReciboNoDomiciliadoBackEnd consultaReciboNoDomiciliadoBackEnd = (ConsultaReciboNoDomiciliadoBackEnd) appContext
				.getBean("consultaReciboNoDomiciliadoBackEnd");
		ReciboBean recibo = consultaReciboNoDomiciliadoBackEnd
				.ejecutarTRN(numOperacion);
		if (recibo != null) {

			this.obtieneFlash().put(
					ParametrosFlashEnum.RECIBO_BEAN.getParamFlash(), recibo);

			managedBeanStateRecover.enviaController(this,
					NavegacionEnum.BUSQUEDA);

			if (destino == null) {
				rutaDestino = NavegacionEnum.RESUMEN_TRANSACCION_RECIBO_NO_DOMICILIADO
						.getRuta();
			} else {
				rutaDestino = destino.getRuta();
				this.managedBeanStateRecover.finNavegacion(destinoController);
			}
		}
		return rutaDestino;
	}

	/**
	 * Método utilizado para la búsqueda de notificaciones.
	 * 
	 * @param obj
	 */
	public void setValoresBusquedaApuntesManuales(Object obj) {
		ConsultaApuntesManualesBackEnd consultaApuntesManualesBackEnd = (ConsultaApuntesManualesBackEnd) appContext
				.getBean("consultaApuntesManualesBackEnd");

		ApunteManualBusquedaBean busquedaBean = (ApunteManualBusquedaBean) obj;

		this.datos = new ArrayList<Object>(
				consultaApuntesManualesBackEnd.ejecutarTRN(
						busquedaBean.getIdCuentaContable(),
						busquedaBean.getFechaInicio(),
						busquedaBean.getFechaFin()));
		this.pagina++;
		this.tablaApuntesManuales = true;
		// if (this.claseActual.equalsIgnoreCase(ApunteManualBusquedaBean.class
		// .getName())) {
		// this.tablaNotificaciones = true;
		// }
	}

	/**
	 * Método que ejecuta la ficha Cliente de la fila seleccionada.
	 * 
	 * @param event
	 *            Evento de la tabla al seleccionar una fila
	 */
	public void mostrarDetalle(SelectEvent event) {
		Object fila = event.getObject();

		// Se guarda el tipo de Cliente en el Flash para mandarlo a la ficha de
		// Cliente

		if (seleccionTipo.equals(BusquedaEnum.PERSONA_CLIENTE
				.getBusquedaValor())
				|| seleccionTipo.equals(BusquedaEnum.PERSONA_MORAL
						.getBusquedaValor())
				|| seleccionTipo.equals(BusquedaEnum.PERSONA_GRUPO
						.getBusquedaValor())
				|| seleccionTipo.equals(BusquedaEnum.PERSONA_GESTOR
						.getBusquedaValor())) {
			// Obtiene el constante del enum TipoCliente
			String valorEnum = ProveedorMensajeUtils.getValoresTipos("ENUM_"
					+ seleccionTipo);
			this.obtieneFlash().put("tipoCliente",
					Enum.valueOf(TipoCliente.class, valorEnum));
		}

		this.obtieneFlash().put(
				ParametrosFlashEnum.ORIGEN_BUSQUEDA.getParamFlash(), true);
		// Se guarda id Interno en el Flash para mandarlo a la ficha Cliente
		this.obtieneFlash().putAll(
				ParametrosFlashUtils.obtenerParametros(fila, claseActual));

		if (!confirmaSeleccion) {

			ConfigurableNavigationHandler configurableNavigationHandler = (ConfigurableNavigationHandler) FacesContext
					.getCurrentInstance().getApplication()
					.getNavigationHandler();

			String ruta = obtieneRutaRedireccion();

			configurableNavigationHandler.performNavigation(ruta);
		}
	}

	/**
	 * Método para navegar una vez seleccionado un objeto. Este método se
	 * ejecuta sólo cuando se viene de otro flujo
	 * 
	 * @param o
	 *            el objeto seleccionado de la tabla
	 */
	public void irADestino(Object o) {
		if (o != null) {
			SelectEvent irADestino = new SelectEvent(new UISelectOne(),
					new AjaxBehavior(), o);
			confirmaSeleccion = false; // ya no se debe confirmar la seeccion
										// puesto que ya se escogió un objeto
			mostrarDetalle(irADestino);
		}
	}

	/**
	 * Método para obtener llave principal.
	 * 
	 * @param fila
	 *            Fila de la cual se obtendrá el id interna.
	 * @return Object con valor de llave principal
	 */
	public Object obtenerKey(Object fila) {
		if (!this.pintarTablaResultadosIntermedios) {
			Object key = ParametrosFlashUtils
					.obtenerKey(fila, this.claseActual);
			if (key != null) {
				return key;
			} else {
				return "";
			}
		} else {
			return ParametrosFlashUtils.obtenerKey(fila,
					this.claseIntermediaActual);
		}
	}

	/**
	 * Método que redirige al usuario a la página de inicio que contiene el
	 * dashboard.
	 * 
	 * @return ruta del recurso a mostrar
	 */
	public String redirigirAInicio() {
		String rutaDestino = null;
		if (destino == null) {
			rutaDestino = NavegacionEnum.INICIO.getRuta();
		} else {
			rutaDestino = destino.getRuta();
			managedBeanStateRecover.finNavegacion(destinoController);
		}
		return rutaDestino;
	}

	/**
	 * Método que regresa ruta de ventana de Búsqueda.
	 * 
	 * @return String Ruta de Búsqueda
	 */
	public String inicio() {
		return NavegacionEnum.BUSQUEDA.getRuta();
	}

	/**
	 * Mètodo que obtiene la ruta a la cual se redireccionarà el valor
	 * seleccionado de la bùsqueda.
	 * 
	 * @return
	 */
	public String obtieneRutaRedireccion() {
		String ruta = "";
		if (destino == null) {
			String valorEnumRuta = "";
			try {
				valorEnumRuta = Class.forName(claseActual)
						.getAnnotation(NavegaAnnotation.class).campoEnum();
			} catch (ClassNotFoundException e) {
				LOGGER.debug("Error al obtener la navegacion para mostrar el detalle para la seleccion de tipo: "
						+ seleccionTipo);
			}
			ruta = Enum.valueOf(NavegacionEnum.class, valorEnumRuta).getRuta();
//			ruta = seleccionTipo.equals(BusquedaEnum.PERSONA_CLIENTE.getBusquedaValor())
//					? mx.babel.bansefi.banksystem.base.enums.integracionAppWhere.NavegacionEnum.FICHAPERSONAFISICA.getRuta()
//					: seleccionTipo.equals(BusquedaEnum.PERSONA_MORAL.getBusquedaValor())
//						? mx.babel.bansefi.banksystem.base.enums.integracionAppWhere.NavegacionEnum.FICHAPERSONAMORAL.getRuta()
//						: ruta;
			managedBeanStateRecover.enviaController(this,
					NavegacionEnum.BUSQUEDA);
		} else {
			ruta = destino.getRuta();
			this.managedBeanStateRecover.finNavegacion(destinoController);
		}
		return ruta;
	}

	public void verMas() {
		try {
			if ((this.pagina * 10 % PaginacionBean.LONGITUD_PAGINA) == 0) {
				if (masDatos) {
					MethodUtils.invokeMethod(this, metodoWebService,
							claseActualObjeto);
				} else if (!masDatos && webservice) {
					if (this.pagina * 10 < this.datos.size()) {
						pagina++;
					}
				}
			} else {
				if (this.pagina * 10 < this.datos.size()) {
					pagina++;
				}
			}
		} catch (NoSuchMethodException | IllegalAccessException
				| InvocationTargetException e) {

			LOGGER.debug("Error al obtener los valores de los campos para el resultado de la busqueda");
			throw new NoControlableException(
					"Se ha producido un error al obtener los valores de los campos para el resultado de la busqueda",
					e);
		}
	}

	/**
	 * Funcion que genera la consulta para llenar el autocomplete de numero de
	 * cuenta contables
	 * 
	 * @param query
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<CuentaContableBean> getCuentasContables(String query) {
		LOGGER.debug("Consulta cuentas contables con: " + query);
		List<CuentaContableBean> listaCuentas = new ArrayList<CuentaContableBean>();

		// Verifica si ya esta cargada la lista de cuentas contables
		if (listaCuentasContables == null || listaCuentasContables.isEmpty()
				|| listaCuentasContables.size() == 0) {
			ConsultaCuentasContablesBackEnd consultaCuentasContablesBackEnd = (ConsultaCuentasContablesBackEnd) appContext
					.getBean("consultaCuentasContablesBackEnd");
			listaCuentasContables = consultaCuentasContablesBackEnd
					.ejecutarTRN();
		}

		if (StringUtils.isBlank(query)) {
			return listaCuentas;
		}

		for (CuentaContableBean cuentaContable : listaCuentasContables) {
			if (cuentaContable.getNombreCuentaContable().toUpperCase()
					.contains(query.toUpperCase())
					|| cuentaContable.getIdCuentaContable().contains(
							query.toUpperCase())) {
				listaCuentas.add(cuentaContable);
			}
		}

		return listaCuentas;
	}

	public List<ModeloColumnaUtils> getColumnas() {
		return columnas;
	}

	public void setColumnas(List<ModeloColumnaUtils> columnas) {
		this.columnas = columnas;
	}

	public String getClaseActual() {
		return claseActual;
	}

	public void setClaseActual(String claseActual) {
		this.claseActual = claseActual;
	}

	public String getSeleccionTipo() {
		return seleccionTipo;
	}

	public void setSeleccionTipo(String seleccionTipo) {
		this.seleccionTipo = seleccionTipo;
	}

	public boolean isPintarTablaResultados() {
		return pintarTablaResultados;
	}

	public boolean getPintarTablaResultados() {
		return pintarTablaResultados;
	}

	public void setPintarTablaResultados(boolean pintarTablaResultados) {
		this.pintarTablaResultados = pintarTablaResultados;
	}

	public boolean isPintarTablaResultadosIntermedios() {
		return pintarTablaResultadosIntermedios;
	}

	public boolean getPintarTablaResultadosIntermedios() {
		return pintarTablaResultadosIntermedios;
	}

	public void setPintarTablaResultadosIntermedios(
			boolean pintarTablaResultadosIntermedios) {
		this.pintarTablaResultadosIntermedios = pintarTablaResultadosIntermedios;
	}

	public boolean isMasDatos() {
		return masDatos;
	}

	public void setMasDatos(boolean masDatos) {
		this.masDatos = masDatos;
	}

	public List<SelectItem> getListaRadio() {
		return listaRadio;
	}

	public void setListaRadio(List<SelectItem> listaRadio) {
		this.listaRadio = listaRadio;
	}

	public int getOpcion() {
		return opcion;
	}

	public void setOpcion(int opcion) {
		this.opcion = opcion;
	}

	public int getPagina() {
		return pagina!=0 ? pagina: 1;
	}

	public void setPagina(int pagina) {
		this.pagina = pagina;
	}

	public Object getClaseActualObjeto() {
		return claseActualObjeto;
	}

	public void setClaseActualObjeto(Object claseActualObjeto) {
		this.claseActualObjeto = claseActualObjeto;
	}

	public List<Object> getDatos() {
		return datos;
	}

	public void setDatos(List<Object> datos) {
		this.datos = datos;
	}

	public boolean isConfirmaSeleccion() {
		return confirmaSeleccion;
	}

	public void setConfirmaSeleccion(boolean confirmaSeleccion) {
		this.confirmaSeleccion = confirmaSeleccion;
	}

	/**
	 * Método que regresa DynaFormModel a pintar en la zona de la tabla en la
	 * ventana del buscador.
	 * 
	 * @return DynaFormModel que se pintara en la zona de la tabla.
	 */
	public DynaFormModel getTabla() {
		modeloTabla = new DynaFormModel();
		DynaFormRow row = modeloTabla.createRegularRow();
		if (pintarTablaResultados) {
			row.addControl(new DynaPropertyModel(), "tablaPagina", 4, 1);
		}
		if (pintarTablaResultadosIntermedios) {
			row.addControl(new DynaPropertyModel(), "tablaIntermediaPagina", 4,
					1);
		}
		return modeloTabla;
	}

	public DynaFormModel getModelo() {
		if (modelo == null) {
			modelo = new DynaFormModel();
		}
		return modelo;
	}

	public void setModelo(DynaFormModel modelo) {
		this.modelo = modelo;
	}

	public List<String[]> getFiltros() {
		return filtros;
	}

	public void setFiltros(List<String[]> filtros) {
		this.filtros = filtros;
	}

	/**
	 * @return the claseIntermediaActual
	 */
	public String getClaseIntermediaActual() {
		return claseIntermediaActual;
	}

	/**
	 * @param claseIntermediaActual
	 *            the claseIntermediaActual to set
	 */
	public void setClaseIntermediaActual(final String claseIntermediaActual) {
		this.claseIntermediaActual = claseIntermediaActual;
	}

	public boolean isTablaApuntesManuales() {
		return tablaApuntesManuales;
	}

	public void setTablaApuntesManuales(boolean tablaApuntesManuales) {
		this.tablaApuntesManuales = tablaApuntesManuales;
	}

	public SelectEvent getEventPasoIntermedio() {
		return eventPasoIntermedio;
	}

	public void setEventPasoIntermedio(SelectEvent eventPasoIntermedio) {
		this.eventPasoIntermedio = eventPasoIntermedio;
	}

	public boolean isBusquedaIntermedia() {
		return busquedaIntermedia;
	}

	public void setBusquedaIntermedia(boolean busquedaIntermedia) {
		this.busquedaIntermedia = busquedaIntermedia;
	}

}
