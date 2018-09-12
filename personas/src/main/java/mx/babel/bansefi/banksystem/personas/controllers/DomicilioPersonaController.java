package mx.babel.bansefi.banksystem.personas.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

import mx.babel.arq.catalogo.beans.CatalogoBean;
import mx.babel.arq.catalogo.beans.CatalogoGeoBean;
import mx.babel.arq.comun.beans.PaginacionBean;
import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.bansefi.banksystem.base.backends.ConsultaDomiciliosPersonaBackEnd;
import mx.babel.bansefi.banksystem.base.backends.consultadomicilio.ConsultaDomicilioBackend;
import mx.babel.bansefi.banksystem.base.beans.AltaDomicilioRespuestaBean;
import mx.babel.bansefi.banksystem.base.beans.domain.ClienteBean;
import mx.babel.bansefi.banksystem.base.beans.domain.DomicilioBean;
import mx.babel.bansefi.banksystem.base.beans.domain.DomicilioCompartidoBean;
import mx.babel.bansefi.banksystem.base.beans.domain.DomicilioTipoBean;
import mx.babel.bansefi.banksystem.base.beans.domain.TipoCliente;
import mx.babel.bansefi.banksystem.base.controllers.DomicilioController;
import mx.babel.bansefi.banksystem.base.enums.BusquedaEnum;
import mx.babel.bansefi.banksystem.base.enums.CatalogoEnum;
import mx.babel.bansefi.banksystem.base.enums.EstadoListadosEnum;
import mx.babel.bansefi.banksystem.base.enums.NavegacionEnum;
import mx.babel.bansefi.banksystem.base.enums.ParametrosFlashEnum;
import mx.babel.bansefi.banksystem.base.enums.TipoDomicilioEnum;
import mx.babel.bansefi.banksystem.base.utils.CatalogoGeoUtils;
import mx.babel.bansefi.banksystem.base.utils.CatalogoUtils;
import mx.babel.bansefi.banksystem.base.utils.DomicilioUtils;
import mx.babel.bansefi.banksystem.base.utils.DomicilioWrapper;
import mx.babel.bansefi.banksystem.base.utils.ManagedBeanStateRecover;
import mx.babel.bansefi.banksystem.personas.backend.AltaDomicilioPersonaBackEnd;
import mx.babel.bansefi.banksystem.personas.backend.BajaDomicilioPersonaBackEnd;
import mx.babel.bansefi.banksystem.personas.backend.CompartirDomicilioBackEnd;
import mx.babel.bansefi.banksystem.personas.backend.ConsultaDomicilioCompartidoBackEnd;
import mx.babel.bansefi.banksystem.personas.backend.ModificaDomicilioPersonaBackEnd;
import mx.babel.bansefi.banksystem.personas.beans.CompartirDomicilioBean;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang3.SerializationUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.component.panel.Panel;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Clase controlador para la sección de Domicilio de Personas
 * 
 * @author manuel.nieto
 * 
 */
@ManagedBean(name = "domicilioPersonaController")
@ViewScoped
@Component
@Scope("view")
public class DomicilioPersonaController implements Serializable {

	private static final long serialVersionUID = -2759946378341243679L;

	/**
	 * Parametros de entrada
	 */
	private String tipoAlta;
	private String nombreCliente;
	private TipoCliente tipoCliente;
	private ClienteBean clienteBean;

	/**
	 * Beans
	 */
	// private DomicilioTipoBean nuevoDomicilio;
	private DomicilioTipoBean compartirDomicilio; // Bean que se muestra en
													// formulario
	private CompartirDomicilioBean compartirDomicilioBean; // Datos de bean para
															// compartir
															// domicilio
															// private
															// List<DomicilioTipoBean>
															// domiciliosList;
	private List<DomicilioTipoBean> domiciliosListBackUp; // Para poder revertir
															// cambios
	private DomicilioCompartidoBean domicilioCompartidoSeleccionado;

	// Render flags
	private boolean renderAnadirDomicilio;
	private boolean renderCompartirDomicilio;
	private boolean renderMasDatos; // bandera para boton de mostrar mas datos
									// en el alta de domicilio

	// Bean de paginación para domicilios de personas
	private PaginacionBean paginacionDomiciliosPersona;

	@Autowired
	CatalogoUtils catalogoUtils;
	@Autowired
	DomicilioUtils domicilioUtils;

	@Autowired
	ManagedBeanStateRecover managedBeanStateRecover;

	private TipoDomicilioEnum tipoDomicilio;

	private static final Logger LOGGER = LogManager
			.getLogger(DomicilioPersonaController.class);

	/**
	 * Backends
	 */
	@Autowired
	private ConsultaDomicilioBackend consultaDetalleDomicilioBackEnd;
	@Autowired
	private ConsultaDomiciliosPersonaBackEnd consultaDomiciliosPersonaBackEnd;
	@Autowired
	private AltaDomicilioPersonaBackEnd altaDomicilioPersonaBackEnd;
	@Autowired
	private ConsultaDomicilioCompartidoBackEnd consultaDomicilioCompartidoBackEnd;
	@Autowired
	private ModificaDomicilioPersonaBackEnd modificaDomicilioPersonaBackEnd;
	@Autowired
	private BajaDomicilioPersonaBackEnd bajaDomicilioPersonaBackEnd;
	@Autowired
	private CompartirDomicilioBackEnd compartirDomicilioBackEnd;

	/**
	 * Wrappers
	 */
	@Autowired
	private DomicilioWrapper domicilioWrapper;

	@Autowired
	CatalogoGeoUtils catalogoGeoUtils;

	/**
	 * Variables para control de logica
	 */

	// Variables temporales que almacenaran el domicilio a eliminar
	@SuppressWarnings("unused")
	private int indiceDomicilioEliminar;
	@SuppressWarnings("unused")
	private String idContenedorDomicilioEliminar;

	// Guarda la edicion para todos los domicilios
	@SuppressWarnings("unused")
	private boolean guardaEdicionTodos = false;

	/**
	 * Variable que almacena el index actual del acordeón
	 */
	private int activeIndex;

	/**
	 * Enum para definir a donde se redireccionará al seleccionar un objeto de
	 * la tabla de resultados.
	 */
	private NavegacionEnum destino;
	private Object destinoController;

	private String mensajeError;
	
	private String mensajeInfo;

	public String getMensajeInfo() {
		return mensajeInfo;
	}

	public void setMensajeInfo(String mensajeInfo) {
		this.mensajeInfo = mensajeInfo;
	}

	@Autowired
	private DomicilioController domicilioController;

	/**
	 * Método para inicializar la ventana de Domicio de Persona
	 */
	@PostConstruct
	public void init() {
		if (this.obtieneFlash()
				.get(ParametrosFlashEnum.DESTINO.getParamFlash()) != null
				&& this.obtieneFlash().get(
						ParametrosFlashEnum.DESTINO_CONTROLLER.getParamFlash()) != null) {
			destino = (NavegacionEnum) this.obtieneFlash().get(
					ParametrosFlashEnum.DESTINO.getParamFlash());
			destinoController = this.obtieneFlash().get(
					ParametrosFlashEnum.DESTINO_CONTROLLER.getParamFlash());
			initializeData();
		} else if (this.obtieneFlash().get(
				ParametrosFlashEnum.NAVEGACION_MANAGED_BEAN.getParamFlash()) != null) {
			if ((Boolean) this.obtieneFlash()
					.get(ParametrosFlashEnum.NAVEGACION_MANAGED_BEAN
							.getParamFlash())) {
				destino = managedBeanStateRecover.getDestino();
				destinoController = managedBeanStateRecover.getController();
				initializeData();
			} else {
				managedBeanStateRecover.recuperaController(this);
				initCompartirDomicilio();
				validaEdicionDomicilios();
			}
		} else {
			initializeData();
		}
	}

	/**
	 * Mètodo para incializar los datos de la vista cuando no se tiene un estado
	 * previo.
	 */
	@SuppressWarnings("unchecked")
	private void initializeData() {
		this.tipoAlta = "nuevo";
		this.renderAnadirDomicilio = false;
		this.renderCompartirDomicilio = false;

		if (obtieneFlash().get(ParametrosFlashEnum.CLIENTE.getParamFlash()) != null
				&& obtieneFlash().get(
						ParametrosFlashEnum.NOMBRE_CLIENTE.getParamFlash()) != null
				&& obtieneFlash().get(
						ParametrosFlashEnum.TIPO_CLIENTE.getParamFlash()) != null) {

			// Muestra posibles mensajes en la pagina
			if (obtieneFlash().get("mensajesGuardado") != null) {
				List<String> mensajes = (List<String>) obtieneFlash().get(
						"mensajesGuardado");
				if (mensajes.isEmpty()) {
					RequestContext.getCurrentInstance().execute(
							"PF('dlgConfirmacionNoGuardado').show()");
				} else {
					RequestContext.getCurrentInstance().execute(
							"PF('dlgConfirmacionGuardado').show()");
				}
			}

			this.clienteBean = (ClienteBean) obtieneFlash().get(
					ParametrosFlashEnum.CLIENTE.getParamFlash());
			this.nombreCliente = (String) obtieneFlash().get(
					ParametrosFlashEnum.NOMBRE_CLIENTE.getParamFlash());
			this.tipoCliente = (TipoCliente) obtieneFlash().get(
					ParametrosFlashEnum.TIPO_CLIENTE.getParamFlash());

			try {
				LOGGER.debug("***TRN-Consulta");
				LOGGER.debug("Consultar domicilios, id interna:"
						+ this.clienteBean.getIdInterna());

				try {
					domicilioController
							.setDomiciliosList(consultaDomiciliosPersonaBackEnd
									.ejecutarTRN(
											this.clienteBean.getIdInterna(),
											paginacionDomiciliosPersona));
				} catch (Exception e) {
					LOGGER.debug("Error al consultar TRN de Domicilios");
					LOGGER.debug("Error", e);
//					FacesMessage message = new FacesMessage(
//							FacesMessage.SEVERITY_ERROR,
//							"Domicilio de Persona",
//							"Se ha producido un error al obtener datos de ficha cliente. (TRN)");
//					RequestContext.getCurrentInstance().showMessageInDialog(
//							message);
					displayMensajeError("Se ha producido un error al obtener datos de ficha cliente. (TRN)");
				}

				if (domicilioController.getDomiciliosList() != null
						&& domicilioController.getDomiciliosList().size() > 0) {
					for (DomicilioTipoBean domicilio : domicilioController
							.getDomiciliosList()) {
						/**
						 * Si no tiene los datos minimos para mostrar el resumen
						 * Entonces consulta el detalle completo del domicilio
						 */
						if (!domicilio.tieneDatosResumen()) {
							domicilio.setIdInternoPersona(this.clienteBean
									.getIdInterna() + "");
							domicilio = consultaDetalleDomicilio(domicilio);
							domicilio.setLoadedAllData(true);
						}
					}
				}

				this.domiciliosListBackUp = SerializationUtils
						.clone((ArrayList<DomicilioTipoBean>) this.domicilioController
								.getDomiciliosList());

			} catch (ControlableException c) {
				// FacesMessage message = new FacesMessage(
				// FacesMessage.SEVERITY_ERROR, "Domicilio de Persona",
				// "Se ha producido un error al consultar datos.\n"
				// + c.getMensajeDetalle());
				// RequestContext.getCurrentInstance()
				// .showMessageInDialog(message);
				displayMensajeError("Se ha producido un error al consultar datos.\n"
						+ c.getMensajeDetalle());
			}
		} else {
			// FacesMessage message = new FacesMessage(
			// FacesMessage.SEVERITY_ERROR, "Domicilio de Persona",
			// "Se ha producido un error al obtener datos de ficha cliente.");
			// RequestContext.getCurrentInstance().showMessageInDialog(message);
			displayMensajeError("Se ha producido un error al obtener datos de ficha cliente.");
		}
	}

	/**
	 * Funcion que muestra el dialogo de error
	 * 
	 * @param mensaje
	 */
	public void displayMensajeError(String mensaje) {
		this.mensajeError = mensaje;
		RequestContext.getCurrentInstance().execute(
				"PF('dlgErrorDatos').show()");
	}

	/**
	 * Funcion que redirige al inicio de la aplicacion
	 * 
	 * @return
	 */
	public String inicio() {
		return NavegacionEnum.INICIO.getRuta();
	}

	/**
	 * Esta funcion se ejecuta cuando recuperamos la vista de un flujo anterior
	 * Funcion que verifica los domicilios en estado editado para cambiar su
	 * estilo
	 */
	public void validaEdicionDomicilios() {
		if (domicilioController.getDomiciliosList() != null
				&& !domicilioController.getDomiciliosList().isEmpty()) {
			for (int i = 0; i < domicilioController.getDomiciliosList().size(); i++) {
				if (domicilioController.getDomiciliosList().get(i)
						.getEstadoBean().equals(EstadoListadosEnum.MODIFICADO)) {
					DomicilioTipoBean domicilio = domicilioController
							.getDomiciliosList().get(i);
					domicilioController.getDomiciliosList().get(i)
							.setTooglePanel(false);
					LOGGER.debug("HABILITAR EDICION");
					if (!domicilio.isLoadedAllData()) {
						domicilio.setIdInternoPersona(this.clienteBean
								.getIdInterna() + "");
						domicilio = consultaDetalleDomicilio(domicilio);
						domicilio.setLoadedAllData(true);
						LOGGER.debug("Se ha cargado todo el detalle del comicilio.");
					}
					openPanel(i);

					RequestContext.getCurrentInstance().execute(
							"$('#form\\\\:panelEdicion" + i
									+ "').removeClass('consultado')");
					RequestContext.getCurrentInstance().execute(
							"$('#form\\\\:panelEdicion" + i
									+ "').addClass('modificado')");
				} else if (domicilioController.getDomiciliosList().get(i)
						.getEstadoBean().equals(EstadoListadosEnum.ELIMINADO)) {
					try {
						aplicarEstiloEliminado("form\\:panelEdicion" + i);
					} catch (NullPointerException e2) {
						LOGGER.debug("No se pudo aplicar el estilo de edición sobre el panel.");
						// Intentar con jQuery
						RequestContext.getCurrentInstance().execute(
								"$('#form\\\\:panelEdicion" + i
										+ "').removeClass('consultado')");
						RequestContext.getCurrentInstance().execute(
								"$('#form\\\\:panelEdicion" + i
										+ "').addClass('eliminado')");
					}
				}
			}
		}
	}

	/**
	 * Funcion que verifica si se viene del flujo de busqueda para compartir un
	 * domicilio
	 */
	public void initCompartirDomicilio() {

		if (this.obtieneFlash().get("tipo") != null
				&& this.obtieneFlash().get(
						ParametrosFlashEnum.ID_INTERNA.getParamFlash()) != null
				&& this.obtieneFlash().get(
						ParametrosFlashEnum.NUMERO_DIRECCION.getParamFlash()) != null) {

			Integer idCompartir = (Integer) this.obtieneFlash().get(
					ParametrosFlashEnum.ID_INTERNA.getParamFlash());

			String numeroDireccion = (String) this.obtieneFlash().get(
					ParametrosFlashEnum.NUMERO_DIRECCION.getParamFlash());

			// Valida si no se comparte domicilio consigo mismo
			if (!this.clienteBean.getIdInterna().equals(idCompartir)) {

				// // Valida que ese domicilio no este compartido ya
				if (!isNumeroDireccion(Integer.valueOf(numeroDireccion))) {
					// Se llena el bean necesario para compartir el domicilio
					compartirDomicilioBean = new CompartirDomicilioBean();
					compartirDomicilioBean.setIdInternoPersona(this.clienteBean
							.getIdInterna() + "");
					compartirDomicilioBean.setIdInternoPersona2(idCompartir
							+ "");
					compartirDomicilioBean.setNumeroDireccion(numeroDireccion);
					compartirDomicilioBean.setIdInternoBien("0");
					compartirDomicilioBean.setIndicaMasDirecciones("S");

					// Se carga informacion adicional del domicilio para mostrar
					// mas
					// datos
					compartirDomicilio = new DomicilioTipoBean();
					compartirDomicilio.setNumeroDireccion(Integer
							.parseInt(compartirDomicilioBean
									.getNumeroDireccion()));
					this.compartirDomicilio
							.setIdInternoPersona(compartirDomicilioBean
									.getIdInternoPersona2());
					this.compartirDomicilio = consultaDetalleDomicilio(compartirDomicilio);
					this.renderCompartirDomicilio = true;
					this.tipoAlta = "existente";

					// Si tiene telefono el domicilio, se asigna
					if (this.compartirDomicilio.getTelefono() != null
							&& StringUtils.isBlank(this.compartirDomicilio
									.getTelefono())) {
						compartirDomicilioBean
								.setValorDireccionElectronica(this.compartirDomicilio
										.getTelefono());
						compartirDomicilioBean
								.setLongitudValorDireccionElectronica(this.compartirDomicilio
										.getTelefono().length());
					} else {
						compartirDomicilioBean
								.setValorDireccionElectronica("0");
						compartirDomicilioBean
								.setLongitudValorDireccionElectronica(1);
					}

					// Ubica la vista en el panel de anadir domicilio compartido
					RequestContext.getCurrentInstance().execute(
							"scrollTo('#form\\\\:panelCompartirDomicilio')");
				} else {
					addMessage(
							"Error, este domicilio ya ha sido compartido anteriormente.",
							"");
				}
			} else {
				addMessage(
						"Error, no puede compartir domicilio con el mismo usuario origen y destino",
						"");
			}
		}

	}

	/**
	 * Funcion en la que se validarán las altas, modificaciones, eliminaciones
	 * de domicilios
	 */
	public String guardar() {
		LOGGER.debug("//////////////GUARDAR////////////");
		// Mensajes de retroalimentacion
		List<String> mensajes = new ArrayList<String>();
		// Errores
		boolean errores = false;

		// Validacion minima
		// if (!validaUnDomicilio()) {
		// addMessage("Debe tener al menos un domicilio", "");
		// errores = true;
		// }

		// Validaciones previas antes de enviar formulario COMENTADO
		// TEMPORALMENTE

		if (!validaCantidadDomicilioFiscal()) {
			addMessage("No puede haber más de un domicilio FISCAL", "");
			errores = true;
		}

		if (isPersonaFisica()) {
			// Valida que haya al menos un domicilio habitual
			if (!isDomicilioHabitual()) {
				addMessage(
						"Debe haber al menos un domicilio HABITUAL para una persona física",
						"");
				errores = true;
			}
			// Valida que no haya mas de un domicilio habitual
			if (!validaCantidadDomicilioHabitual()) {
				addMessage("No puede haber más de un domicilio HABITUAL", "");
				errores = true;
			}
		}

		if (isPersonaMoral()) {
			if (!isDomicilioSocial()) {
				addMessage(
						"Debe haber al menos un domicilio SOCIAL para una persona moral",
						"");
				errores = true;
			}
		}

		if (errores) {
			return null;
		}

		// Valida si se va a guardar uno nuevo
		if ("nuevo".equals(this.tipoAlta) && this.renderAnadirDomicilio
				&& this.domicilioController.getDomicilioBean() != null) {
			// Debug tags
			LOGGER.debug("*********************************************************************************");
			LOGGER.debug("TAGS de nuevo domicilio:"
					+ this.domicilioController.getDomicilioBean()
							.getTagsTipoDomicilio());
			for (String tipo : this.domicilioController.getDomicilioBean()
					.getTagsTipoDomicilio()) {
				LOGGER.debug(tipo);
			}
			LOGGER.debug("*********************************************************************************");

			// Genera agrupamiento de tags de acuerdo a los checks elegidos
			// this.domicilioController.getDomicilioBean().generaAgrupamiento();

			// Completa datos
			this.domicilioController.getDomicilioBean().setIdInternoPersona(
					this.clienteBean.getIdInterna() + "");

			// Llama a TRN
			try {
				LOGGER.debug(domicilioController.getDomicilioBean().toString());

				AltaDomicilioRespuestaBean respuesta = altaDomicilioPersonaBackEnd
						.ejecutarTRN(domicilioController.getDomicilioBean());

				LOGGER.debug("*-*-*-*-*-*Alta de domicilio correcta*-*-*-*-*");
				LOGGER.debug("Numero de direccion:"
						+ respuesta.getNumeroDireccion());
				LOGGER.debug("Entidad:" + respuesta.getCodigoEntidad());

				mensajes.add("Alta de domicilio: "
						+ domicilioController.getDomicilioBean()
								.tipoDomicilioToString()
						+ ", "
						+ domicilioController.getDomicilioBean()
								.getNombreCalle() + " correcta.");
				this.renderAnadirDomicilio = false;
			} catch (NoControlableException ex) {
				LOGGER.debug(ex);
				errores = true;
				addMessage("Error al ejecutar el servicio web.",
						"Ocurrió un error al ingresar un nuevo domicilio.");
			}

		} else if ("existente".equals(this.tipoAlta)
				&& this.renderCompartirDomicilio
				&& this.compartirDomicilio != null) {
			// Compartir domicilio
			if (compartirDomicilioBean != null) {
				try {
					compartirDomicilioBean.setTipoDomicilio(compartirDomicilio
							.getGrouping());
					CompartirDomicilioBean compartir = compartirDomicilioBackEnd
							.ejecutarTRN(compartirDomicilioBean);

					if (compartir != null
							&& compartir.getIndicaMasDirecciones() != null) {
						mensajes.add("Alta de domicilio compartido: "
								+ compartirDomicilio.tipoDomicilioToString()
								+ ", " + compartirDomicilio.getNombreCalle()
								+ " correcta. Con el usuario: "
								+ compartirDomicilioBean.getIdInternoPersona2());
					}
				} catch (NoControlableException ex) {
					LOGGER.debug(ex);
					errores = true;
					addMessage("Error",
							"Ocurrió un error al ingresar un nuevo domicilio compartido.");
				}

			} else {
				errores = true;
				addMessage("Error", "Ocurrió un error al compartir domicilio.");
			}
		}

		// Edicion

		// Verificar si hubo cambios
		LOGGER.debug("-------------EDICION DE DOMICILIO-------------");
		for (DomicilioTipoBean domicilio : domicilioController
				.getDomiciliosList()) {
			// Modificacion de domicilios
			if (domicilio.getEstadoBean().equals(EstadoListadosEnum.MODIFICADO)) {
				// //Verifica si el domicilio es compartido con mas personas
				if (domicilio.getDomiciliosCompartidos() != null
						&& !domicilio.getDomiciliosCompartidos().isEmpty()
						&& domicilio.isEditaCompartidos()) {
					LOGGER.debug("--GUARDA EDICION PARA TODOS");

					int numDir = modificaDomicilioPersonaBackEnd.ejecutarTRN(
							domicilio, this.clienteBean.getIdInterna(), "S");

					LOGGER.debug("--DOMICILIO MODIFICADO:" + numDir);
					if (numDir == 0) {// No se realizo correctamente
						errores = true;
						mensajes.add("Se presentó un error al modificar el domicilio:"
								+ domicilio.getNombreCalle());
					} else {
						mensajes.add("Edición de domicilio: "
								+ domicilio.tipoDomicilioToString() + ", "
								+ domicilio.getNombreCalle() + " correcta.");
					}
				} else {
					// Se guarda el domicilio solo para esta persona
					int numDir = modificaDomicilioPersonaBackEnd.ejecutarTRN(
							domicilio, this.clienteBean.getIdInterna(), "N");

					LOGGER.debug("--DOMICILIO MODIFICADO:" + numDir);
					if (numDir == 0) {// No se realizo correctamente
						errores = true;
						mensajes.add("Se presentó un error al modificar el domicilio:"
								+ domicilio.getNombreCalle());
					} else {
						mensajes.add("Edición de domicilio: "
								+ domicilio.tipoDomicilioToString() + ", "
								+ domicilio.getNombreCalle() + " correcta.");
					}
				}

			}
			// Eliminacion de domicilios
			if (domicilio.getEstadoBean().equals(EstadoListadosEnum.ELIMINADO)) {
				LOGGER.debug("*-*-*--*-*ELIMINANDO DOMICILIOS*-*-*-*-*");
				// Eliminar por cada tipo de domicilio
				for (TipoDomicilioEnum tipoDomicilio : domicilio.getGrouping()) {
					if (!domicilio.isLoadedAllData()) {
						domicilio.setIdInternoPersona(String
								.valueOf(this.clienteBean.getIdInterna()));
						domicilio = consultaDetalleDomicilio(domicilio);
						domicilio.setLoadedAllData(true);
						bajaDomicilioPersonaBackEnd.ejecutarTRN(
								this.clienteBean.getIdInterna(),
								domicilio.getNumeroDireccion(), tipoDomicilio,
								domicilio.getTelefono());
						mensajes.add("Baja de domicilio: "
								+ domicilio.tipoDomicilioToString() + ", "
								+ domicilio.getNombreCalle() + " correcta.");
					}
					
				}
			}
		}

		if (errores) {
			muestraMensajes(mensajes);
			return null;
		} else {
			return reload(mensajes);
		}

	}

	/**
	 * Funcion que valida si ya hay cambios que realizar
	 * 
	 * @return
	 */
	public boolean validaPerdidaCambios() {
		// Valida si se va a guardar uno nuevo
		if ("nuevo".equals(this.tipoAlta) && this.renderAnadirDomicilio
				&& this.domicilioController.getDomicilioBean() != null) {
			return true;
		} else if ("existente".equals(this.tipoAlta)
				&& this.renderCompartirDomicilio
				&& this.compartirDomicilio != null) {
			return true;
		}

		// Edicion
		for (DomicilioTipoBean domicilio : domicilioController
				.getDomiciliosList()) {
			// Modificacion de domicilios
			if (domicilio.getEstadoBean().equals(EstadoListadosEnum.MODIFICADO)) {
				// //Verifica si el domicilio es compartido con mas personas
				return true;
			}
			// Eliminacion de domicilios
			if (domicilio.getEstadoBean().equals(EstadoListadosEnum.ELIMINADO)) {
				return true;
			}
		}

		return false;
	}

	/**
	 * Funcion que cancale la adicion de un domicilio a través de uno existente
	 */
	public void eliminarDomicilioCompartido() {
		if (this.compartirDomicilio != null
				&& this.compartirDomicilioBean != null) {
			this.renderCompartirDomicilio = false;
			compartirDomicilio = null;
			compartirDomicilioBean = null;
		}
	}

	/**
	 * Funcion que valida si hay que confirmar con el usuario si desea eliminar
	 * los domicilios marcados
	 * 
	 * @return
	 */
	public String validaEliminacionDomicilios() {
		boolean domicilioEliminados = false;
		if (domicilioController.getDomiciliosList() != null) {
			for (DomicilioTipoBean domicilio : domicilioController
					.getDomiciliosList()) {
				if (domicilio.getEstadoBean().equals(
						EstadoListadosEnum.ELIMINADO)) {

					RequestContext.getCurrentInstance().execute(
							"PF('dlgConfirmacionEliminacion').show()");
					domicilioEliminados = true;

				}
			}
		}
		// Si no tiene ningun domicilio compartido manda a guardar directamente
		if (!domicilioEliminados) {
			return guardar();
		} else {
			return null;
		}
	}

	/**
	 * Funcion que valida si hay que confirmar con el usuario si desea modificar
	 * la direccion para todos los usuario compartidos si los hubiera
	 */
	// public String validaMultiEdicion() {
	// boolean domicilioCompartidos = false;
	// if (domicilioController.getDomiciliosList() != null) {
	// for (DomicilioTipoBean domicilio :
	// domicilioController.getDomiciliosList()) {
	// if (domicilio.getEstadoBean().equals(
	// EstadoListadosEnum.MODIFICADO)) {
	// // //Verifica si el domicilio es compartido con mas personas
	// if (domicilio.getDomiciliosCompartidos() != null
	// && domicilio.getDomiciliosCompartidos().size() > 0) {
	// RequestContext.getCurrentInstance().execute(
	// "PF('dlgConfirmacionModificacion').show()");
	// domicilioCompartidos = true;
	// }
	// }
	// }
	// }
	// // Si no tiene ningun domicilio compartido manda a guardar directamente
	// if (!domicilioCompartidos) {
	// return guardar();
	// } else {
	// return null;
	// }
	//
	// }

	/**
	 * Funcion que valida si ya existe un numero de dirección para evitar ser
	 * compartido de nuevo
	 * 
	 * @return
	 */
	public boolean isNumeroDireccion(Integer numeroDireccion) {
		if (domicilioController.getDomiciliosList() != null
				&& !domicilioController.getDomiciliosList().isEmpty()) {
			for (DomicilioTipoBean domicilio : domicilioController
					.getDomiciliosList()) {
				if (domicilio.getNumeroDireccion() == numeroDireccion) {
					return true;
				}
			}
		}

		return false;
	}

	/**
	 * Respuesta del usuario, desea modificar el domicilio solo para el
	 * 
	 * @param domicilio
	 */
	public String guardarEdicionUno() {
		LOGGER.debug("-----------------GUARDAR EDICION SOLO PARA UNO-------------");
		this.guardaEdicionTodos = false;
		return guardar();
	}

	/**
	 * Respuesta del usuario, desa modificar el domicilio para todos
	 * 
	 * @param domicilio
	 */
	public String guardarEdicionTodos() {
		LOGGER.debug("----------------GUARDAR EDICION PARA TODOS--------------------");
		this.guardaEdicionTodos = true;
		return guardar();
	}

	/**
	 * Funcion que guarda temporalmente el indice y el id del domicilio que se
	 * pretende eliminar
	 * 
	 * @param indice
	 * @param idContenedor
	 */
	// public void preConfirmEliminar(int indice, String idContenedor) {
	// LOGGER.debug("-------------------PRECONFIRM ELIMINAR-----------------");
	// if (indice != 0 && idContenedor != null) {
	// LOGGER.debug("Se confirmará si se desea eliminar el domicilio: "
	// + indice + " en el contendedor: " + idContenedor);
	// this.indiceDomicilioEliminar = indice;
	// this.idContenedorDomicilioEliminar = idContenedor;
	// RequestContext.getCurrentInstance().execute(
	// "PF('dlgConfirmacionEliminacion').show()");
	// }
	// }

	/**
	 * Funcion que consulta todo el detalle de un domicilio
	 * 
	 * @param domicilio
	 * @return
	 */
	public DomicilioTipoBean consultaDetalleDomicilio(
			DomicilioTipoBean domicilio) {

		if (domicilio != null && domicilio.getIdInternoPersona() != null
				&& domicilio.getNumeroDireccion() != 0) {

			LOGGER.debug("/*/*/*/*/*/Numero de domicilio: "
					+ domicilio.getNumeroDireccion());

			DomicilioBean domicilioBean = consultaDetalleDomicilioBackEnd
					.ejectuarTRN(
							Integer.valueOf(domicilio.getIdInternoPersona()),
							domicilio.getNumeroDireccion());
			// Se hace un wrapp para que no se pierdan los datos del
			// agrupamiento
			// de tipo de domicilio
			domicilioWrapper.wrappDomicilio(domicilio, domicilioBean);

			// Verificar el grouping
			LOGGER.debug("-----------------Detalle domicilio---------");
			LOGGER.debug("El domicilio tiene los siguientes tags de tipo de domicilio: "
					+ domicilio.getGrouping().size());
			for (TipoDomicilioEnum tipo : domicilio.getGrouping()) {
				if (tipo != null && tipo.getTipoDomicilio() != null) {
					LOGGER.debug(tipo.getTipoDomicilio());
				}
			}

			// Cargar los domicilios compartidos
			LOGGER.debug("/+/+/+/+Consulta compartidos./+/+/+/+/");
			domicilio
					.setDomiciliosCompartidos(consultarDomicilioCompartido(domicilio
							.getNumeroDireccion()));

			return domicilio;
		} else {
			return domicilio;
		}
	}

	/**
	 * Funcion que consulta los domicilios compartidos de un domicilio y omite
	 * el del ClienteBean, para que no se repita el mismo usuario en la lista.
	 * 
	 * @param numeroDireccion
	 * @return
	 */
	public List<DomicilioCompartidoBean> consultarDomicilioCompartido(
			int numeroDireccion) {

		if (numeroDireccion != 0) {
			LOGGER.debug("Consulta domicilios compartidos filtro");
			List<DomicilioCompartidoBean> compartidos = consultaDomicilioCompartidoBackEnd
					.ejecutarTRN(numeroDireccion);
			if (compartidos != null) {
				for (int i = 0; i < compartidos.size(); i++) {
					LOGGER.debug("**IdInterno List: "
							+ compartidos.get(i).getIdInternoPersona());
					if (clienteBean != null
							&& clienteBean.getIdInterna() != null
							&& Integer.parseInt(compartidos.get(i)
									.getIdInternoPersona()) == clienteBean
									.getIdInterna()) {
						LOGGER.debug("Se encontró una coincidencia, se eliminará el cliente de la lista");
						compartidos.remove(i);
					}
				}
			}

			return compartidos;
		} else {
			return null;
		}

	}

	/**
	 * Habilitar elementos para su modificacion
	 */
	public void habilitarEdicion(DomicilioTipoBean domicilio,
			String idContenedor, int indice) {
		if (domicilio != null) {
			LOGGER.debug("HABILITAR EDICION");
			if (!domicilio.isLoadedAllData()) {
				domicilio.setIdInternoPersona(this.clienteBean.getIdInterna()
						+ "");
				domicilio = consultaDetalleDomicilio(domicilio);
				domicilio.setLoadedAllData(true);
				LOGGER.debug("Se ha cargado todo el detalle del comicilio.");
			}
			domicilio.setEstadoBean(EstadoListadosEnum.MODIFICADO);
			aplicarEstiloModificado(domicilio, idContenedor, indice);
			openPanel(indice);
		}
	}

	/**
	 * Restaurar un domicilio a su valor original
	 * 
	 * @param idContenedor
	 */
	public void restaurarDomicilio(int indice, String idContenedor) {
		LOGGER.debug("RESTAURAR DOMICILIO");
		// domicilioController.getDomiciliosList().set(this.activeIndex,
		// domiciliosListBackUp.get(this.activeIndex));
		if (idContenedor != null
				&& domicilioController.getDomiciliosList() != null
				&& domiciliosListBackUp != null) {
			// Cierra el panel
			closePanel(indice);

			domicilioController.getDomiciliosList().set(
					indice,
					SerializationUtils
							.clone((DomicilioTipoBean) domiciliosListBackUp
									.get(indice)));

			// Cambia el estilo del panel
			aplicarEstiloActivo(idContenedor);

		}
	}

	/**
	 * Eliminar un domicilio
	 * 
	 * @param domicilio
	 * @param idContenedor
	 */
	public void eliminarDomicilio(DomicilioTipoBean domicilio,
			String idContenedor) {
		LOGGER.debug("ELIMINAR DOMICILIO");
		if (domicilioController.getDomiciliosList() != null
				&& !domicilioController.getDomiciliosList().isEmpty()) {
			domicilio.setEstadoBean(EstadoListadosEnum.ELIMINADO);
			domicilio.setLoadedAllData(false);
			aplicarEstiloEliminado(idContenedor);
			closePanel(domicilioController.getDomiciliosList().indexOf(
					domicilio));
		}
	}

	/**
	 * Método que con base al idContenedor, permite actualizar un elemento único
	 * de la vista.
	 * 
	 * @param idContenedor
	 */
	private void aplicarEstiloActivo(String idContenedor) {
		String bloques[] = idContenedor.split(":");
		String idElementoVistaActualizar = bloques[0] + ":" + bloques[1];
		Panel panel = (Panel) FacesContext.getCurrentInstance().getViewRoot()
				.findComponent(idElementoVistaActualizar);
		panel.setStyleClass("panel-dinamico consultado panel-domicilio");
	}

	/**
	 * Método que con base al idContenedor, permite actualizar un elemento único
	 * de la vista.
	 * 
	 * @param idContenedor
	 */
	private void aplicarEstiloEliminado(String idContenedor) {
		String bloques[] = idContenedor.split(":");
		String idElementoVistaActualizar = bloques[0] + ":" + bloques[1];
		Panel panel = (Panel) FacesContext.getCurrentInstance().getViewRoot()
				.findComponent(idElementoVistaActualizar);
		panel.setStyleClass("panel-dinamico eliminado panel-domicilio");
	}

	/**
	 * Funcion que da formato a una cadena para que se encuentre el
	 * identificador del componente en la vista
	 * 
	 * @param idContenedor
	 * @return
	 */
	private String formatoIdVista(String idContenedor) {
		String bloques[] = idContenedor.split(":");
		String idElementoVistaActualizar = bloques[0] + ':' + bloques[1];
		return idElementoVistaActualizar;
	}

	/**
	 * Método que con base al idContenedor, permite actualizar un elemento único
	 * de la vista.
	 * 
	 * @param idContenedor
	 */
	private void aplicarEstiloModificado(DomicilioTipoBean domicilio,
			String idContenedor, int indice) {
		try {
			Panel panel = (Panel) FacesContext.getCurrentInstance()
					.getViewRoot().findComponent(formatoIdVista(idContenedor));
			panel.setStyleClass("panel-dinamico modificado panel-domicilio");
		} catch (NullPointerException e) {
			try {
				Panel panel = (Panel) FacesContext.getCurrentInstance()
						.getViewRoot()
						.findComponent("form\\\\:panelEdicion" + indice);
				panel.setStyleClass("panel-dinamico modificado panel-domicilio");
			} catch (NullPointerException e2) {
				LOGGER.debug("No se pudo aplicar el estilo de edición sobre el panel.");
				// Intentar con jQuery
				RequestContext.getCurrentInstance().execute(
						"$('#form\\\\:panelEdicion" + indice
								+ "').removeClass('consultado')");
				RequestContext.getCurrentInstance().execute(
						"$('#form\\\\:panelEdicion" + indice
								+ "').addClass('modificado')");
			}
		}

	}

	/**
	 * Funcion que dependiendo del estado del bean imprime el estilo en una
	 * cadena
	 * 
	 * @param domicilio
	 * @return
	 */
	public String imprimeEstiloDomicilio(DomicilioTipoBean domicilio) {
		if (domicilio.getEstadoBean().equals(EstadoListadosEnum.NUEVO)) {
			return "panel-dinamico nuevo panel-domicilio";
		} else if (domicilio.getEstadoBean().equals(
				EstadoListadosEnum.MODIFICADO)) {
			return "panel-dinamico modificado panel-domicilio";
		} else if (domicilio.getEstadoBean().equals(
				EstadoListadosEnum.ELIMINADO)) {
			return "panel-dinamico eliminado panel-domicilio";
		}

		return null;
	}

	/**
	 * Funcion que cancela la acción de agregar nuevo domicilio
	 */
	public void cancelarNuevoDomicilio() {
		this.renderAnadirDomicilio = false;
		this.domicilioController.setDomicilioBean(null);
	}

	/**
	 * Evento que se ejecuta al cambio del combo de tipo de alta Activa los
	 * botones de nuevo
	 */
	public void cambiaTipoAlta() {
		RequestContext.getCurrentInstance().execute("PF('btnAnadir').enable()");
		RequestContext.getCurrentInstance().execute("PF('btnBuscar').enable()");
	}

	@SuppressWarnings("unused")
	private UIComponent getComponentById(String id) {
		return FacesContext.getCurrentInstance().getViewRoot()
				.findComponent(id);
	}

	/**
	 * Funcion que de acuerdo a una lista de strings muestra los mensajes en
	 * pantalla
	 * 
	 * @param mensajes
	 */
	public void muestraMensajes(List<String> mensajes) {
		LOGGER.debug("+-+-+-+-+-Mensajes+-+-+-+-+-+");
		if (mensajes != null) {
			for (String mensaje : mensajes) {
				addMessage(mensaje, "");
				LOGGER.debug(mensaje);
			}
		}
	}

	/**
	 * Función para recargar la pagina despues de guardar datos agregando los
	 * parametros necesarios en la Flash
	 * 
	 * @param msjs
	 *            Posible lista de mensajes de confirmacion sobre las tareas
	 *            efectuadas
	 * @return direccion de recarga
	 */
	public String reload(List<String> msjs) {
		try {
			obtieneFlash().put(ParametrosFlashEnum.CLIENTE.getParamFlash(),
					this.clienteBean);
			obtieneFlash().put(
					ParametrosFlashEnum.NOMBRE_CLIENTE.getParamFlash(),
					this.clienteBean.getNombreCompleto());
			obtieneFlash().put(
					ParametrosFlashEnum.TIPO_CLIENTE.getParamFlash(),
					this.tipoCliente);

			// Logica sin sentido para no perder el flujo al que debemos
			// retornar
			this.obtieneFlash().put(
					ParametrosFlashEnum.DESTINO.getParamFlash(), destino);
			this.obtieneFlash().put(
					ParametrosFlashEnum.DESTINO_CONTROLLER.getParamFlash(),
					destinoController);
		} catch (NullPointerException e) {
			LOGGER.debug("Error al cargar los datos en la flash para recargar la pagina");
			LOGGER.debug(e);
			return NavegacionEnum.INICIO.getRuta();
		}

		if (msjs != null) {
			obtieneFlash().put("mensajesGuardado", msjs);
		}

		return NavegacionEnum.DOMICILIO_PERSONA.getRuta();
	}

	/**
	 * Metodo que se activa cuando se abre una nueva pestaña *
	 * 
	 * @param event
	 *            , index del panel
	 */
	public void onPanelToogle(int activeIndex) {
		// LOGGER.debug("/-/-/-/-/-/-/onToggleEvent/-/-/-/-/-");
		// LOGGER.debug("Se selecciono el acordeon numero: " + activeIndex);
		// if (domicilioController.getDomiciliosList() != null &&
		// !domicilioController.getDomiciliosList().isEmpty()) {
		// boolean estado =
		// domicilioController.getDomiciliosList().get(activeIndex).isTooglePanel();
		// estado = !estado; // Cambia el estado del panel abierto/cerrado
		// domicilioController.getDomiciliosList().get(activeIndex).setTooglePanel(estado);
		// }
	}

	/**
	 * Funcion que detecta un cierre de panel y asigna la variable de
	 * TooglePanel del domicilio en falso.
	 * 
	 * @param activeIndex
	 */
	public void onPanelClose(int activeIndex) {
		LOGGER.debug("/-/-/-/-/-/-/onCloseEvent/-/-/-/-/-");
		LOGGER.debug("Se selecciono el acordeon numero: " + activeIndex);
		if (domicilioController.getDomiciliosList() != null
				&& !domicilioController.getDomiciliosList().isEmpty()) {
			domicilioController.getDomiciliosList().get(activeIndex)
					.setTooglePanel(false);
		}
	}

	/**
	 * Funcion que cierra un panel en especifico de la lista de domicilios
	 * 
	 * @param index
	 */
	public void closePanel(int index) {
		if (domicilioController.getDomiciliosList() != null
				&& !domicilioController.getDomiciliosList().isEmpty()) {
			if (domicilioController.getDomiciliosList().get(index)
					.isTooglePanel()) {
				RequestContext.getCurrentInstance().execute(
						"PF('panelEdicion" + index + "').toggle()");
				domicilioController.getDomiciliosList().get(index)
						.setTooglePanel(false);

			}
		}
	}

	/**
	 * Funcion que abre un panel en especifico de la lista de domicilios
	 * 
	 * @param index
	 */
	public void openPanel(int index) {
		if (domicilioController.getDomiciliosList() != null
				&& !domicilioController.getDomiciliosList().isEmpty()) {
			if (!domicilioController.getDomiciliosList().get(index)
					.isTooglePanel()) {
				RequestContext.getCurrentInstance().execute(
						"PF('panelEdicion" + index + "').toggle()");
				domicilioController.getDomiciliosList().get(index)
						.setTooglePanel(true);
			}
		}
	}

	/**
	 * Evento que se activa al pulsar un renglon de la tabla de domicilios
	 * compartidos
	 * 
	 * @return
	 */
	public void onRowSelectEvent(SelectEvent event) {
		try {
			obtieneFlash().put(
					ParametrosFlashEnum.DOMICILIO_COMPARTIDO.getParamFlash(),
					(DomicilioCompartidoBean) event.getObject());

		} catch (NullPointerException e) {
			LOGGER.debug("Error al cargar datos en la flash para su redireccion al detalle de cliente");
			LOGGER.debug("error", e);
		}
		this.managedBeanStateRecover.enviaController(this,
				NavegacionEnum.DOMICILIO_PERSONA);

		ConfigurableNavigationHandler configurableNavigationHandler = (ConfigurableNavigationHandler) FacesContext
				.getCurrentInstance().getApplication().getNavigationHandler();

		configurableNavigationHandler
				.performNavigation(NavegacionEnum.DETALLE_PERSONA.getRuta());
	}

	/**
	 * Funcion que concatena el tipo de identificacion oficial asi como su
	 * identificador
	 * 
	 * @return
	 */
	public String getDomicilioCompartidoIdExternoToString(
			DomicilioCompartidoBean domicilioC) {
		if (domicilioC != null
				&& domicilioC.getCodigoIdExternoPersona() != null
				&& domicilioC.getIdExternoPersona() != null) {

			LOGGER.debug("/////////////getIdToString()");
			LOGGER.debug("codigoIdExternoPersona: "
					+ domicilioC.getCodigoIdExternoPersona());
			LOGGER.debug("idExternoPersona: "
					+ domicilioC.getIdExternoPersona());

			try {
				CatalogoBean catalogo = catalogoUtils.getCatalogoBean(
						CatalogoEnum.TP_ID_EXT_PERS,
						domicilioC.getCodigoIdExternoPersona());

				String tipoId = catalogo.getDescripcionC();

				return tipoId + " " + domicilioC.getIdExternoPersona();
			} catch (NullPointerException | ControlableException e) {
				LOGGER.debug("Error al consultar el catalogo de id exterior personas");
				LOGGER.debug(e);
				return domicilioC.getIdExternoPersona();
			}
		} else {
			return null;
		}
	}

	/**
	 * Funcion que redirecciona al detalle de la persona con los parametros
	 * necesarios
	 * 
	 * @param domicilioC
	 * @return
	 */
	public String direccionaDetallePersona(DomicilioCompartidoBean domicilioC) {
		try {
			obtieneFlash().put(ParametrosFlashEnum.CLIENTE.getParamFlash(),
					this.clienteBean);
			obtieneFlash().put(
					ParametrosFlashEnum.NOMBRE_CLIENTE.getParamFlash(),
					this.clienteBean.getNombreCompleto());
			obtieneFlash().put(
					ParametrosFlashEnum.TIPO_CLIENTE.getParamFlash(),
					this.tipoCliente);
			obtieneFlash().put(
					ParametrosFlashEnum.DOMICILIO_COMPARTIDO.getParamFlash(),
					domicilioC);
			obtieneFlash().put("nuevoDomicilio",
					this.domicilioController.getDomicilioBean());
			obtieneFlash().put("domiciliosList",
					this.domicilioController.getDomiciliosList());
			obtieneFlash().put("domiciliosListBackUp",
					this.domiciliosListBackUp);
			obtieneFlash().put("renderAnadirDomicilio",
					this.renderAnadirDomicilio);
			obtieneFlash().put("tipoAlta", this.tipoAlta);

		} catch (NullPointerException e) {
			LOGGER.debug("Error al cargar datos en la flash para su redireccion al detalle de cliente");
			LOGGER.debug("error", e);
		}

		obtieneFlash().setRedirect(true);

		// FacesContext.getCurrentInstance().getExternalContext()
		// .redirect("detallePersona.xhtml?faces-redirect=true");
		ConfigurableNavigationHandler configurableNavigationHandler = (ConfigurableNavigationHandler) FacesContext
				.getCurrentInstance().getApplication().getNavigationHandler();

		String ruta = "detallePersona.xhtml?faces-redirect=true";

		configurableNavigationHandler.performNavigation(ruta);
		return NavegacionEnum.DETALLE_PERSONA.getRuta();
	}

	/**
	 * Funcion que se ejecuta al pulsar el boton de añadir
	 */
	public void anadirDomicilio() {
		if (this.renderAnadirDomicilio) {
			RequestContext.getCurrentInstance().execute(
					"PF('dlgAnadirDomicilio').show()");
		} else {
			this.renderAnadirDomicilio = true;
			this.domicilioController.setDomicilioBean(new DomicilioTipoBean());
		}
	}

	/**
	 * Funcion que procesa el evento de los checkboxes para tipos de domicilios
	 */
	public void onCheckClick() {
		// addMessage("Cambio de tipo de domicilio", "");

		if (isPersonaFisica()) {
			if (!isDomicilioHabitual()) {
				addMessage(
						"Debe haber al menos un domicilio HABITUAL para una persona física.",
						"");

			}
		}

		if (isPersonaMoral()) {
			if (!isDomicilioSocial()) {
				addMessage(
						"Debe haber al menos un domicilio SOCIAL para una persona moral.",
						"");

			}
		}
	}

	/**
	 * Funcion temporal que valida haya al menos un domicilio De cualquier tipo
	 * 
	 * @return
	 */
	public boolean validaUnDomicilio() {
		// Si está agregando uno nuevo
		if (this.domicilioController.getDomicilioBean() != null) {
			return true;
		}
		// Si no hay domicilio nuevo pero quedan editados
		if (domicilioController.getDomiciliosList() != null
				&& !domicilioController.getDomiciliosList().isEmpty()) {
			for (DomicilioTipoBean domicilio : domicilioController
					.getDomiciliosList()) {
				if (domicilio.getEstadoBean().equals(
						EstadoListadosEnum.MODIFICADO)
						|| domicilio.getEstadoBean().equals(
								EstadoListadosEnum.NUEVO)) {
					return true;
				}
			}
		}

		return false;
	}

	/**
	 * Valida que no haya mas de un domicilio fiscal
	 * 
	 * @return
	 */
	public boolean validaCantidadDomicilioFiscal() {
		int nDoms = 0;
		if (domicilioController.getDomiciliosList() != null) {
			for (DomicilioTipoBean domicilio : domicilioController
					.getDomiciliosList()) {
				if (domicilio.isDomicilioFiscalGrouping()) {
					nDoms++;
				}
			}
		}

		if (this.domicilioController.getDomicilioBean() != null) {
			if (domicilioController.getDomicilioBean()
					.isDomicilioFiscalGrouping()) {
				nDoms++;
			}
		}

		if (compartirDomicilio != null) {
			if (compartirDomicilio.isDomicilioFiscalGrouping()) {
				nDoms++;
			}
		}

		if (nDoms > 1) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Valida que no haya mas de un domicilio habitual
	 * 
	 * @return
	 */
	public boolean validaCantidadDomicilioHabitual() {
		int nDoms = 0;
		if (domicilioController.getDomiciliosList() != null) {
			for (DomicilioTipoBean domicilio : domicilioController
					.getDomiciliosList()) {
				if (domicilio.isDomicilioHabitualGrouping()) {
					nDoms++;
				}
			}
		}

		if (this.domicilioController.getDomicilioBean() != null) {
			if (domicilioController.getDomicilioBean()
					.isDomicilioHabitualGrouping()) {
				nDoms++;
			}
		}

		if (compartirDomicilio != null) {
			if (compartirDomicilio.isDomicilioHabitualGrouping()) {
				nDoms++;
			}
		}

		if (nDoms > 1) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Funcion que verifica si ya hay algun domicilio fiscal en la lista Solo
	 * puede haber un maxico de 1 domicilio fiscal
	 * 
	 * @return true
	 */
	public boolean isDomicilioFiscal() {
		if (domicilioController.getDomiciliosList() != null) {
			for (DomicilioTipoBean domicilio : domicilioController
					.getDomiciliosList()) {
				if (domicilio.isDomicilioFiscalGrouping()) {
					return true;
				}
			}
		}
		if (this.domicilioController.getDomicilioBean() != null
				&& domicilioController.getDomicilioBean().getGrouping() != null) {
			if (domicilioController.getDomicilioBean()
					.isDomicilioFiscalGrouping()) {
				return true;
			}
		}
		if (compartirDomicilio != null
				&& compartirDomicilio.getGrouping() != null) {
			if (compartirDomicilio.isDomicilioFiscalGrouping()) {
				return true;
			}
		}

		return false;
	}

	/**
	 * Funcion que verifica si ya hay algun domicilio fiscal en la lista Solo
	 * puede haber un maximo de 1 domicilio fiscal Omite un index en especifico
	 * 
	 * @return true
	 */
	public boolean isDomicilioFiscal(int index) {
		if (domicilioController.getDomiciliosList() != null) {
			for (int i = 0; i < domicilioController.getDomiciliosList().size(); i++) {
				if (domicilioController.getDomiciliosList().get(i)
						.isDomicilioFiscalGrouping()
						&& i != index) {
					return true;
				}
			}
		}

		if (this.domicilioController.getDomicilioBean() != null
				&& domicilioController.getDomicilioBean().getGrouping() != null
				&& index != -1) {
			if (domicilioController.getDomicilioBean()
					.isDomicilioFiscalGrouping()) {
				return true;
			}
		}

		if (compartirDomicilio != null
				&& compartirDomicilio.getGrouping() != null && index != -1) {
			if (compartirDomicilio.isDomicilioFiscalGrouping()) {
				return true;
			}
		}

		return false;

	}

	/**
	 * Funcion que verifica si ya hay algun domicilio habitual en la lista Solo
	 * puede haber un domicilio habitual por persona fisica
	 * 
	 * @return
	 */
	public boolean isDomicilioHabitual() {
		if (domicilioController.getDomiciliosList() != null) {
			for (DomicilioTipoBean domicilio : domicilioController
					.getDomiciliosList()) {
				if (domicilio.isDomicilioHabitualGrouping()) {
					return true;
				}
			}
		}

		if (this.domicilioController.getDomicilioBean() != null
				&& domicilioController.getDomicilioBean().getGrouping() != null) {
			if (domicilioController.getDomicilioBean()
					.isDomicilioHabitualGrouping()) {
				return true;
			}
		}

		if (compartirDomicilio != null
				&& compartirDomicilio.getGrouping() != null) {
			if (compartirDomicilio.isDomicilioHabitualGrouping()) {
				return true;
			}
		}

		return false;
	}

	/**
	 * Funcion que verifica si ya hay algun domicilio habitual en la lista Solo
	 * puede haber un domicilio habitual por persona fisica Omite un index en
	 * especifico
	 * 
	 * @return
	 */
	public boolean isDomicilioHabitual(int index) {
		if (domicilioController.getDomiciliosList() != null) {
			for (int i = 0; i < domicilioController.getDomiciliosList().size(); i++) {
				if (domicilioController.getDomiciliosList().get(i)
						.isDomicilioHabitualGrouping()
						&& i != index) {
					return true;
				}
			}
		}
		if (this.domicilioController.getDomicilioBean() != null
				&& domicilioController.getDomicilioBean().getGrouping() != null
				&& index != -1) {
			if (domicilioController.getDomicilioBean()
					.isDomicilioHabitualGrouping()) {
				return true;
			}
		}

		if (compartirDomicilio != null
				&& compartirDomicilio.getGrouping() != null && index != -1) {
			if (compartirDomicilio.isDomicilioHabitualGrouping()) {
				return true;
			}
		}

		return false;
	}

	/**
	 * Funcion que devuelve la direccion del domicilio principal, el cual es
	 * para persona fisica el habitual, y para moral el social.
	 * 
	 * @return
	 */
	public int getDomicilioPrincipal() {
		if (domicilioController.getDomiciliosList() != null) {
			for (DomicilioTipoBean domicilio : domicilioController
					.getDomiciliosList()) {
				if (isPersonaFisica()) {
					if (domicilio.isDomicilioHabitualGrouping()) {
						return domicilio.getNumeroDireccion();
					}
				} else {
					if (domicilio.isDomicilioSocialGrouping()) {
						return domicilio.getNumeroDireccion();
					}
				}

			}
		}
		return 0;
	}

	/**
	 * Funcion que verifica si ya hay algun domicilio social en la lista Solo
	 * puede haber un domicilio social por persona moral
	 * 
	 * @return
	 */
	public boolean isDomicilioSocial() {
		if (domicilioController.getDomiciliosList() != null) {
			for (DomicilioTipoBean domicilio : domicilioController
					.getDomiciliosList()) {
				if (domicilio.isDomicilioSocialGrouping()) {
					return true;
				}
			}
		}

		if (this.domicilioController.getDomicilioBean() != null
				&& domicilioController.getDomicilioBean().getGrouping() != null) {
			if (domicilioController.getDomicilioBean()
					.isDomicilioSocialGrouping()) {
				return true;
			}
		}

		if (compartirDomicilio != null
				&& compartirDomicilio.getGrouping() != null) {
			if (compartirDomicilio.isDomicilioSocialGrouping()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Funcion que verifica si ya hay algun domicilio social en la lista Solo
	 * puede haber un domicilio social por persona moral Omitiendo uno en
	 * especifico
	 * 
	 * @return
	 */
	public boolean isDomicilioSocial(int index) {
		if (domicilioController.getDomiciliosList() != null) {
			for (int i = 0; i < domicilioController.getDomiciliosList().size(); i++) {
				if (domicilioController.getDomiciliosList().get(i)
						.isDomicilioSocialGrouping()
						&& i != index) {
					return true;
				}
			}
		}
		if (this.domicilioController.getDomicilioBean() != null
				&& domicilioController.getDomicilioBean().getGrouping() != null
				&& index != -1) {
			if (domicilioController.getDomicilioBean()
					.isDomicilioSocialGrouping()) {
				return true;
			}
		}
		if (compartirDomicilio != null
				&& compartirDomicilio.getGrouping() != null && index != -1) {
			if (compartirDomicilio.isDomicilioSocialGrouping()) {
				return true;
			}
		}

		return false;
	}

	/**
	 * Evento para el campo de codigo postal
	 * 
	 * @param event
	 */
	// public void cargaDatos(DomicilioTipoBean domicilio) {
	// DomicilioBean domicilioBean = null;
	// domicilioBean = domicilioUtils.cargaDatos((DomicilioBean) domicilio);
	// domicilio.setMunicipio(domicilioBean.getMunicipio());
	// domicilio.setEstado(domicilioBean.getEstado());
	// domicilio.setPais(domicilioBean.getPais());
	// }

	public void manejarComponenteDomicilio() {
		if (domicilioController.getDomicilioBean().getCodigoPostalCatGeo() == null
				&& domicilioController.getDomicilioBean().getMunicipioCatGeo() == null) {
			domicilioController.limpiarCamposDomicilio();
		} else {
			domicilioController.cargaDatosDomicilio();
		}
	}

	public void manejarComponenteDomicilio(DomicilioTipoBean bean) {
		if (bean.getCodigoPostalCatGeo() == null
				&& bean.getMunicipioCatGeo() == null) {
			domicilioController.limpiarCamposDomicilio(bean);
		} else {
			domicilioController.cargaDatosDomicilio(bean);
		}
	}

	public List<CatalogoGeoBean> getCodigosPostales(String query) {
		if (StringUtils.isBlank(query)) {
			domicilioController.getDomicilioBean().setCodigoPostalCatGeo(null);
			if (domicilioController.getDomicilioBean().getMunicipioCatGeo() == null) {
				domicilioController.limpiarCamposDomicilio();
			}
			return null;
		}

		return domicilioController.getCatalogoCodigosPostales(query);
	}

	public List<CatalogoGeoBean> getMunicipios(String query) {
		if (StringUtils.isBlank(query)) {
			domicilioController.getDomicilioBean().setMunicipioCatGeo(null);
			if (domicilioController.getDomicilioBean().getCodigoPostalCatGeo() == null) {
				domicilioController.limpiarCamposDomicilio();
			}

			return null;
		}

		return domicilioController.getCatalogoMunicipios(query);
	}

	/**
	 * Funcion que devuelve el numero de domicilios que se eliminarán
	 * 
	 * @return
	 */
	public int nEliminaciones() {
		int nEliminaciones = 0;
		if (this.domicilioController.getDomiciliosList() != null
				&& !this.domicilioController.getDomiciliosList().isEmpty()) {
			for (DomicilioTipoBean domicilio : this.domicilioController
					.getDomiciliosList()) {
				if (domicilio.getEstadoBean().equals(
						EstadoListadosEnum.ELIMINADO)) {
					nEliminaciones++;
				}
			}
		}
		return nEliminaciones;
	}

	/**
	 * Mètodo para navegaciòn a inicio
	 * 
	 * @return ruta de inicio
	 */
	public String rutaInicio() {
		return NavegacionEnum.INICIO.getRuta();
	}

	/**
	 * Funcion que verifica si la persona es de tipo fisica
	 * 
	 * @return
	 */
	public boolean isPersonaFisica() {
		if (this.tipoCliente != null) {
			if (TipoCliente.PERSONA_FISICA.equals(this.tipoCliente)) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	/**
	 * Funcion que verifica si la persona es de tipo moral
	 * 
	 * @return
	 */
	public boolean isPersonaMoral() {
		if (this.tipoCliente != null) {
			if (TipoCliente.PERSONA_MORAL.equals(this.tipoCliente)) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	/**
	 * Método que redirige al usuario a la ficha del cliente o en su defecto al
	 * inicio.
	 * 
	 * @return ruta del recurso a mostrar
	 */
	public String volver() {
		if (destino != null) {
			managedBeanStateRecover.finNavegacion(destinoController);
			return destino.getRuta();
		} else {
			return NavegacionEnum.BUSQUEDA.getRuta();
		}
	}

	/**
	 * Boton que valida si hay que solicitar confirmación para salir de la
	 * operativa
	 */
	public String volverCancelar() {
		if (validaPerdidaCambios()) {
			RequestContext.getCurrentInstance().execute(
					"PF('dlgCancelar').show()");
		} else {
			return volver();
		}
		return null;
	}

	/**
	 * Mètodo para redirigir a la bùsqueda de domicilios
	 * 
	 * @return ruta de bùsqueda de domicilios
	 */
	public String buscarDomicilio() {
		this.managedBeanStateRecover.enviaController(this,
				NavegacionEnum.DOMICILIO_PERSONA);
		this.obtieneFlash().put(
				ParametrosFlashEnum.TIPO_BUSQUEDA.getParamFlash(),
				BusquedaEnum.DOMICILIOS_CLIENTE.getBusquedaValor());
		return NavegacionEnum.BUSQUEDA.getRuta();
	}

	/**
	 * Agregar mensaje para ventana de confirmación
	 * 
	 * @param summary
	 * @param detail
	 */
	public void addMessage(String summary, String detail) {
		if ("".equals(detail)) {
			detail = summary;
			if(detail.toLowerCase().contains("error")){
				this.mensajeError = detail;
				RequestContext.getCurrentInstance().execute(
						"PF('dlgErrorDato').show();");
			}else{
				this.mensajeInfo = detail;
				RequestContext.getCurrentInstance().execute(
						"PF('dlgInfoDomicilios').show();");
			}
		}



	}

	/**
	 * @return Metodo utilizado para recuperar una instancia de Flash
	 */
	public Flash obtieneFlash() {
		return FacesContext.getCurrentInstance().getExternalContext()
				.getFlash();
	}

	public TipoCliente getTipoCliente() {
		return tipoCliente;
	}

	public void setTipoCliente(TipoCliente tipoCliente) {
		this.tipoCliente = tipoCliente;
	}

	public ClienteBean getClienteBean() {
		return clienteBean;
	}

	public void setClienteBean(ClienteBean clienteBean) {
		this.clienteBean = clienteBean;
	}

	public String getTipoAlta() {
		return tipoAlta;
	}

	public void setTipoAlta(String tipoAlta) {
		this.tipoAlta = tipoAlta;
	}

	public String getNombreCliente() {
		return nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	// public List<DomicilioTipoBean> getDomiciliosList() {
	// return domiciliosList;
	// }
	//
	// public void setDomiciliosList(List<DomicilioTipoBean> domiciliosList) {
	// this.domiciliosList = domiciliosList;
	// }

	public boolean isRenderAnadirDomicilio() {
		return renderAnadirDomicilio;
	}

	public void setRenderAnadirDomicilio(boolean renderAnadirDomicilio) {
		this.renderAnadirDomicilio = renderAnadirDomicilio;
	}

	public boolean isRenderMasDatos() {
		return renderMasDatos;
	}

	public void setRenderMasDatos(boolean renderMasDatos) {
		this.renderMasDatos = renderMasDatos;
	}

	public int getActiveIndex() {
		return activeIndex;
	}

	public void setActiveIndex(int activeIndex) {
		this.activeIndex = activeIndex;
	}

	public DomicilioCompartidoBean getDomicilioCompartidoSeleccionado() {
		return domicilioCompartidoSeleccionado;
	}

	public void setDomicilioCompartidoSeleccionado(
			DomicilioCompartidoBean domicilioCompartidoSeleccionado) {
		this.domicilioCompartidoSeleccionado = domicilioCompartidoSeleccionado;
	}

	public DomicilioTipoBean getCompartirDomicilio() {
		return compartirDomicilio;
	}

	public void setCompartirDomicilio(DomicilioTipoBean compartirDomicilio) {
		this.compartirDomicilio = compartirDomicilio;
	}

	public boolean isRenderCompartirDomicilio() {
		return renderCompartirDomicilio;
	}

	public void setRenderCompartirDomicilio(boolean renderCompartirDomicilio) {
		this.renderCompartirDomicilio = renderCompartirDomicilio;
	}

	/**
	 * @return Atributo destino
	 */
	public NavegacionEnum getDestino() {
		return destino;
	}

	/**
	 * @param destino
	 *            Atributo destino a definir
	 */
	public void setDestino(NavegacionEnum destino) {
		this.destino = destino;
	}

	/**
	 * @return Atributo destinoController
	 */
	public Object getDestinoController() {
		return destinoController;
	}

	/**
	 * @param destinoController
	 *            Atributo destinoController a definir
	 */
	public void setDestinoController(Object destinoController) {
		this.destinoController = destinoController;
	}

	public List<DomicilioTipoBean> getDomiciliosListBackUp() {
		return domiciliosListBackUp;
	}

	public void setDomiciliosListBackUp(
			List<DomicilioTipoBean> domiciliosListBackUp) {
		this.domiciliosListBackUp = domiciliosListBackUp;
	}

	public TipoDomicilioEnum getTipoDomicilio() {
		return tipoDomicilio;
	}

	public void setTipoDomicilio(TipoDomicilioEnum tipoDomicilio) {
		this.tipoDomicilio = tipoDomicilio;
	}

	public CompartirDomicilioBean getCompartirDomicilioBean() {
		return compartirDomicilioBean;
	}

	public void setCompartirDomicilioBean(
			CompartirDomicilioBean compartirDomicilioBean) {
		this.compartirDomicilioBean = compartirDomicilioBean;
	}

	public ManagedBeanStateRecover getManagedBeanStateRecover() {
		return managedBeanStateRecover;
	}

	public void setManagedBeanStateRecover(
			ManagedBeanStateRecover managedBeanStateRecover) {
		this.managedBeanStateRecover = managedBeanStateRecover;
	}

	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(obj, this,
				new String[] { "serialVersionUID" });
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this,
				new String[] { "serialVersionUID" });
	}

	public DomicilioController getDomicilioController() {
		return domicilioController;
	}

	public void setDomicilioController(DomicilioController domicilioController) {
		this.domicilioController = domicilioController;
	}

	public String getMensajeError() {
		return mensajeError;
	}

	public void setMensajeError(String mensajeError) {
		this.mensajeError = mensajeError;
	}

}
