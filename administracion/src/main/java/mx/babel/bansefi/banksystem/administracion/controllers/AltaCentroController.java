package mx.babel.bansefi.banksystem.administracion.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

import mx.babel.arq.catalogo.beans.CatalogoBean;
import mx.babel.arq.catalogo.beans.CatalogoGeoBean;
import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.sesion.utils.ContextoUtils;
import mx.babel.arq.storage.annotations.StoreStep;
import mx.babel.bansefi.banksystem.administracion.backends.mantenimientocentros.AltaAcuerdosInstruBackend;
import mx.babel.bansefi.banksystem.administracion.backends.mantenimientocentros.AltaCentroBackend;
import mx.babel.bansefi.banksystem.administracion.backends.mantenimientocentros.AltaCentroControladorBackEnd;
import mx.babel.bansefi.banksystem.administracion.backends.mantenimientocentros.BajaCentroControladorBackEnd;
import mx.babel.bansefi.banksystem.administracion.backends.mantenimientocentros.ConsultaAcuerdosInstruBackend;
import mx.babel.bansefi.banksystem.administracion.backends.mantenimientocentros.ConsultaCentrosControladoresBackEnd;
import mx.babel.bansefi.banksystem.administracion.backends.mantenimientocentros.DetalleCentroBackend;
import mx.babel.bansefi.banksystem.administracion.backends.mantenimientocentros.ModificacionCentroBackend;
import mx.babel.bansefi.banksystem.administracion.beans.centro.AcuerdoInstrumentalBean;
import mx.babel.bansefi.banksystem.administracion.beans.centro.AltaCentroBean;
import mx.babel.bansefi.banksystem.administracion.beans.centro.CentroControladorBean;
import mx.babel.bansefi.banksystem.base.backends.ConsultaPersonaMoralBackEnd;
import mx.babel.bansefi.banksystem.base.backends.ConsultaPersonasRelacionadasBackEnd;
import mx.babel.bansefi.banksystem.base.backends.consultadomicilio.ConsultaDomicilioBackend;
import mx.babel.bansefi.banksystem.base.backends.consultaempleados.ConsultaEmpleadoBackEnd;
import mx.babel.bansefi.banksystem.base.beans.busqueda.EmpleadoBusquedaBean;
import mx.babel.bansefi.banksystem.base.beans.domain.ClienteBean;
import mx.babel.bansefi.banksystem.base.beans.domain.ClientePersonaMoralBean;
import mx.babel.bansefi.banksystem.base.beans.domain.DomicilioTipoBean;
import mx.babel.bansefi.banksystem.base.beans.domain.PersonaRelacionadaBean;
import mx.babel.bansefi.banksystem.base.constants.ConstantesFuncionales;
import mx.babel.bansefi.banksystem.base.controllers.DomicilioController;
import mx.babel.bansefi.banksystem.base.enums.CatalogoEnum;
import mx.babel.bansefi.banksystem.base.enums.DialogoListadoEnum;
import mx.babel.bansefi.banksystem.base.enums.NavegacionEnum;
import mx.babel.bansefi.banksystem.base.enums.ParametrosFlashEnum;
import mx.babel.bansefi.banksystem.base.utils.CatalogoCentrosLoaderTask;
import mx.babel.bansefi.banksystem.base.utils.CatalogoGruposAcuerdosInstrumentalesLoaderTask;
import mx.babel.bansefi.banksystem.base.utils.CatalogoUtils;
import mx.babel.bansefi.banksystem.base.utils.DialogoListadoUtils;
import mx.babel.bansefi.banksystem.base.utils.DomicilioUtils;
import mx.babel.bansefi.banksystem.base.utils.ManagedBeanStateRecover;
import mx.babel.bansefi.banksystem.base.backends.AltaRelacionClientePersonaBackEnd;
import mx.babel.bansefi.banksystem.base.backends.ModificacionPersonaMoralBackEnd;

import org.apache.commons.lang3.SerializationUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Clase controladora de la ventana de Alta de Centro.
 * 
 * @author alejandro.pineda
 * 
 */
@ManagedBean
@ViewScoped
@Component
@Scope("view")
public class AltaCentroController implements Serializable {

	private static final long serialVersionUID = -9213173965752723093L;

	private static final Logger LOGGER = LogManager
			.getLogger(AltaCentroController.class);

	@Autowired
	private AltaCentroBackend alta;

	@Autowired
	private ModificacionCentroBackend modificar;

	@Autowired
	private DetalleCentroBackend detalle;

	@Autowired
	private ConsultaCentrosControladoresBackEnd centrosControladoresBackEnd;

	@Autowired
	private ConsultaAcuerdosInstruBackend consultaCuentas;

	@Autowired
	private AltaAcuerdosInstruBackend altaCuentas;

	@Autowired
	private AltaCentroControladorBackEnd altaCentroControladorBackEnd;

	@Autowired
	private BajaCentroControladorBackEnd bajaCentroControladorBackEnd;

	@Autowired
	private DomicilioUtils domicilioUtils;

	@Autowired
	private ConsultaDomicilioBackend consultaDomicilio;

	@Autowired
	private CatalogoUtils catalogos;

	@Autowired
	private CatalogoCentrosLoaderTask catalogoCentros;

	@Autowired
	private CatalogoGruposAcuerdosInstrumentalesLoaderTask catalogoGrupos;

	@Autowired
	private ContextoUtils contextoUtils;

	@Autowired
	private ManagedBeanStateRecover managedBeanStateRecover;

	@Autowired
	private ConsultaEmpleadoBackEnd consultaEmpleadoBackEnd;

	@Autowired
	private DomicilioController domicilioController;

	@Autowired
	private ConsultaPersonaMoralBackEnd clienteConsultaPersonaMoral;

	@Autowired
	private ModificacionPersonaMoralBackEnd modificacionPersonaMoralBackEnd;

	@Autowired
	private ConsultaPersonasRelacionadasBackEnd consultaPersonasRelacionadasBackEnd;

	@Autowired
	private AltaRelacionClientePersonaBackEnd altaRelacionClientePersonaBackEnd;

	/**
	 * Enum para definir a donde se redireccionará al seleccionar un objeto de
	 * la tabla de resultados.
	 */
	private NavegacionEnum destino;

	private Object destinoController;

	private DialogoListadoUtils dialogoUtils;

	private DialogoListadoEnum dialogoGuardado;

	private CatalogoBean catalogoSeleccionado;

	private AltaCentroBean respaldoCentro;

	private AltaCentroBean altaCentro;

	private ClientePersonaMoralBean cliente;

	private AcuerdoInstrumentalBean mantenimientoAcuerdo;

	private List<CentroControladorBean> aplicaciones;

	private List<AcuerdoInstrumentalBean> acuerdos;

	private List<String> aplicacionesSeleccionadas;

	private List<PersonaRelacionadaBean> listaRelaciones;

	private String codigoCentro;

	private String nombreLineaInstrumentales;

	private String nombreGrupoGestionIncidencia;

	private String nombreGrupoEmisionCheques;

	private String nombreLineaDepositos;

	private String nombreGrupoIntervencion;

	private String mensajeError;

	private int idInternoCentro;

	private Integer posicionLista;

	private boolean modificacion;

	private boolean primeraAltaCentro;

	private boolean existeCuenta = true;

	private boolean existenCuentas;

	private boolean ventanaAcuerdos;

	private boolean pintarCampoAplicacion;

	/**
	 * Constructor.
	 */
	public AltaCentroController() {

	}

	/**
	 * Método para inicializar el controlador.
	 */
	@PostConstruct
	public void init() {
		refreshEnums();
		LOGGER.debug("entrando a AltaCentroController...");
		aplicacionesSeleccionadas = new ArrayList<>();
		nombreLineaInstrumentales = catalogos.getCatalogoBean(
				CatalogoEnum.TP_LINEA_ASES,
				ConstantesFuncionales.CTE_INSTRUMENTALES).getDescripcionC();
		nombreGrupoGestionIncidencia = catalogoGrupos.getCatalogoDescripcion(
				ConstantesFuncionales.CTE_INSTRUMENTALES,
				ConstantesFuncionales.CTE_GESTION_INCIDENCIAS);
		nombreGrupoEmisionCheques = catalogoGrupos.getCatalogoDescripcion(
				ConstantesFuncionales.CTE_INSTRUMENTALES,
				ConstantesFuncionales.CTE_EMISION_CHEQUES);
		nombreLineaDepositos = catalogos
				.getCatalogoBean(CatalogoEnum.TP_LINEA_ASES,
						ConstantesFuncionales.CTE_DEPOSITOS).getDescripcionC();
		nombreGrupoIntervencion = catalogoGrupos.getCatalogoDescripcion(
				ConstantesFuncionales.CTE_DEPOSITOS,
				ConstantesFuncionales.CTE_INTERVENCION);

		try {
			if (this.obtieneFlash()
					.get(ParametrosFlashEnum.NAVEGACION_MANAGED_BEAN
							.getParamFlash()) != null) {
				if ((Boolean) this.obtieneFlash().get(
						ParametrosFlashEnum.NAVEGACION_MANAGED_BEAN
								.getParamFlash())) {
					destino = managedBeanStateRecover.getDestino();
					destinoController = managedBeanStateRecover.getController();
					initDatos();
				} else {
					managedBeanStateRecover.recuperaController(this);
					if (this.obtieneFlash().get(
							ParametrosFlashEnum.NOMBRE.getParamFlash()) != null
							&& this.obtieneFlash().get(
									ParametrosFlashEnum.ID_INTERNA
											.getParamFlash()) != null) {
						if (this.posicionLista != null) {
							this.acuerdos.get(this.posicionLista).setNombre(
									(String) this.obtieneFlash().get(
											ParametrosFlashEnum.NOMBRE
													.getParamFlash()));
							this.acuerdos
									.get(this.posicionLista)
									.setIdApoderado(
											(Integer) this
													.obtieneFlash()
													.get(ParametrosFlashEnum.ID_INTERNA
															.getParamFlash()));
						} else {
							this.mantenimientoAcuerdo.setNombre((String) this
									.obtieneFlash().get(
											ParametrosFlashEnum.NOMBRE
													.getParamFlash()));
							this.mantenimientoAcuerdo
									.setIdApoderado((Integer) this
											.obtieneFlash()
											.get(ParametrosFlashEnum.ID_INTERNA
													.getParamFlash()));
						}
					}
				}
			} else {
				initDatos();
			}

		} catch (ControlableException c) {
			if (c.getRtncod() != 7) {
				FacesMessage message = new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "Mantenimiento Centros",
						"Se ha producido un error al consultar los datos.");
				RequestContext.getCurrentInstance()
						.showMessageInDialog(message);
			}
		}
	}

	private void refreshEnums() {
		DialogoListadoEnum.CONFIRMA_ELIMINAR.setMostrar(false);
		DialogoListadoEnum.ALERTA.setMostrar(false);
		DialogoListadoEnum.ALTA.setMostrar(false);
		DialogoListadoEnum.ELIMINAR.setMostrar(false);
		DialogoListadoEnum.ERROR.setMostrar(false);
		DialogoListadoEnum.MODIFICACION.setMostrar(false);
		DialogoListadoEnum.SIN_CAMBIOS.setMostrar(false);
		DialogoListadoEnum.CONFIRMA_ELIMINAR.setMensaje("");
		DialogoListadoEnum.ALERTA.setMensaje("");
		DialogoListadoEnum.ALTA.setMensaje("");
		DialogoListadoEnum.ELIMINAR.setMensaje("");
		DialogoListadoEnum.ERROR.setMensaje("");
		DialogoListadoEnum.MODIFICACION.setMensaje("");
		DialogoListadoEnum.SIN_CAMBIOS.setMensaje("");
	}

	/**
	 * Método que inicia Datos generales.
	 * 
	 * @throws NoControlableException
	 *             Excepción no controlable del servicio.
	 * @throws ControlableException
	 *             Excepción controlabre del servicio.
	 */
	@SuppressWarnings("unchecked")
	public void initDatos() throws NoControlableException, ControlableException {
		if (this.obtieneFlash().get(
				ParametrosFlashEnum.CODIGO_CENTRO.getParamFlash()) != null) {
			this.codigoCentro = (String) this.obtieneFlash().get(
					ParametrosFlashEnum.CODIGO_CENTRO.getParamFlash());
			this.altaCentro = this.detalleCentro();

			try {
				this.aplicaciones = this.centrosControladoresBackEnd
						.ejecutarTRN(codigoCentro);
			} catch (ControlableException c) {
				if (c.getRtncod() == 7) {
					this.aplicaciones = new ArrayList<>();
				} else {
					throw c;
				}
			}
			for (CentroControladorBean aplicacion : this.aplicaciones) {
				aplicacionesSeleccionadas.add(aplicacion
						.getCodigoCentroControlador());
			}
			try {
				domicilioController
						.setDomicilioBean((DomicilioTipoBean) this.consultaDomicilio
								.ejectuarTRN(null,
										this.altaCentro.getCodDomicilio()));
				if (domicilioController.getDomicilioBean() == null) {
					domicilioController
							.setDomicilioBean(new DomicilioTipoBean());
				}
			} catch (ControlableException c) {
				if (c.getRtncod() == 7) {
					domicilioController
							.setDomicilioBean(new DomicilioTipoBean());
				} else {
					throw c;
				}
			}

			this.altaCentro
					.setDomicilio(domicilioController.getDomicilioBean());
			this.respaldoCentro = SerializationUtils.clone(this.altaCentro);
			pintarCampoAplicacion = altaCentro.isControlador();
			this.modificacion = true;
			this.primeraAltaCentro = true;

			if (this.obtieneFlash().get("recarga") != null) {
				if ((Boolean) this.obtieneFlash().get("recarga")) {
					if (this.obtieneFlash().get("detalleError") != null) {
						this.dialogoGuardado = DialogoListadoEnum.SIN_CAMBIOS;
						this.dialogoGuardado
								.setMensaje(this.obtieneFlash()
										.get("detalleError").toString());
						this.dialogoGuardado.setMostrar(true);
					} else {
						this.dialogoGuardado = DialogoListadoEnum.ALTA;
						if (!(Boolean) this.obtieneFlash().get(
								"primeraAltaCentro")) {
							this.dialogoGuardado
									.setMensaje("El centro se dio de alta correctamente.");
						} else {
							this.dialogoGuardado
									.setMensaje("El centro se ha modificado correctamente.");
						}
						this.dialogoGuardado.setMostrar(true);
					}
				}
			}
		} else {
			if (this.obtieneFlash().get(
					ParametrosFlashEnum.CENTRO.getParamFlash()) != null) {
				this.altaCentro = (AltaCentroBean) this.obtieneFlash().get(
						ParametrosFlashEnum.CENTRO.getParamFlash());
				domicilioController.setDomicilioBean(this.altaCentro
						.getDomicilio());
				this.modificacion = (Boolean) this.obtieneFlash().get(
						"modificacion");
				this.primeraAltaCentro = modificacion;
				this.aplicacionesSeleccionadas = (List<String>) this
						.obtieneFlash().get(
								ParametrosFlashEnum.APLICACIONES_CENTROS_SELEC
										.getParamFlash());
				this.aplicaciones = (List<CentroControladorBean>) this
						.obtieneFlash().get(
								ParametrosFlashEnum.APLICACIONES_CENTROS_CTRLS
										.getParamFlash());
				this.pintarCampoAplicacion = (Boolean) this.obtieneFlash().get(
						"pintarAplicacion");

				if (this.obtieneFlash().get(
						ParametrosFlashEnum.ACUERDOS_INSTRUMENTALES
								.getParamFlash()) != null) {
					this.acuerdos = (List<AcuerdoInstrumentalBean>) this
							.obtieneFlash().get(
									ParametrosFlashEnum.ACUERDOS_INSTRUMENTALES
											.getParamFlash());
					this.existenCuentas = (boolean) this.obtieneFlash()
							.get(ParametrosFlashEnum.EXISTEN_CUENTAS
									.getParamFlash());
					this.mantenimientoAcuerdo = (AcuerdoInstrumentalBean) this
							.obtieneFlash().get(
									ParametrosFlashEnum.MANTENIMIENTO_ACUERDO
											.getParamFlash());
				} else {
					consultarCuentas(this.altaCentro.getNumero());
				}
				this.respaldoCentro = (AltaCentroBean) this.obtieneFlash().get(
						ParametrosFlashEnum.RESPALDO_CENTRO.getParamFlash());
			} else {
				inicializaDatosVacios();
			}
		}
	}

	public void clonarBean() {
		this.respaldoCentro = SerializationUtils.clone(this.altaCentro);
	}
	
	public String validarCancelar(){
		if(this.modificacion){
			boolean cambios = false;
			for (String aplicacionSeleccionada : this.aplicacionesSeleccionadas) {
				boolean encontrado = false;
				for (CentroControladorBean aplicacion : this.aplicaciones) {
					if (aplicacionSeleccionada.equals(aplicacion
							.getCodigoCentroControlador())) {
						encontrado = true;
					}
				}
				if (!encontrado) {
					cambios = true;
				}
			}
			
			for (CentroControladorBean aplicacion : this.aplicaciones) {
				boolean encontrado = false;
				for (String aplicacionSeleccionada : this.aplicacionesSeleccionadas) {
					if (aplicacionSeleccionada.equals(aplicacion
							.getCodigoCentroControlador())) {
						encontrado = true;
					}
				}
				if (!encontrado) {
					cambios = true;
				}
			}
			
			if(this.acuerdos != null){
				for(AcuerdoInstrumentalBean acuerdo : this.acuerdos){
					if(acuerdo.isAlta()){
						cambios = true;
					}
				}
			}
			
			if(!this.respaldoCentro.equals(this.altaCentro) || cambios){
				RequestContext.getCurrentInstance().execute("PF('dlgCancelar').show()");
			}else{
				return redirigirInicio();
			}
		}else{
			RequestContext.getCurrentInstance().execute("PF('dlgCancelar').show()");
		}
		return null;
	}

	public CatalogoBean obtenerCentroSeleccionado(String claveCentro) {
		CatalogoBean centroSeleccionado = null;
		try {
			centroSeleccionado = catalogoCentros.getCatalogoBean(
					contextoUtils.getEntidad(), claveCentro);
		} catch (ControlableException c) {
			centroSeleccionado = null;
		}
		return centroSeleccionado;
	}

	/**
	 * Metodo que carga datos del componente de domicilio.
	 */
	public void manejarComponenteDomicilio() {
		if (domicilioController.getDomicilioBean().getCodigoPostalCatGeo() == null
				&& domicilioController.getDomicilioBean().getMunicipioCatGeo() == null) {
			domicilioController.limpiarCamposDomicilio();
		} else {
			domicilioController.cargaDatosDomicilio();
		}
	}

	/**
	 * Metodo que carga datos del componente de domicilio.
	 */
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

	/**
	 * Metodo que carga datos del componente de domicilio.
	 */
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
	 * Método que inicializa datos vacíos.
	 */
	public void inicializaDatosVacios() {
		this.aplicaciones = new ArrayList<>();
		this.aplicacionesSeleccionadas = new ArrayList<>();
		this.altaCentro = new AltaCentroBean();
		domicilioController.setDomicilioBean(new DomicilioTipoBean());
		this.altaCentro.setDomicilio(domicilioController.getDomicilioBean());
		this.modificacion = false;
		this.primeraAltaCentro = false;
	}

	/**
	 * Método para mostrar o no el campo aplicación en la ventana de Centros.
	 */
	public void cambioSwitch() {
		if (pintarCampoAplicacion) {
			pintarCampoAplicacion = false;
		} else {
			pintarCampoAplicacion = true;
		}
	}

	/**
	 * Método que formatea el numero de centro.
	 */
	public void formatearCentro() {
		if (this.altaCentro.getNumero() != null) {
			this.altaCentro.setNumero(String.format("%04d",
					Integer.parseInt(this.altaCentro.getNumero())));
		}
	}

	private boolean errorApoderado() {
		RequestContext context = RequestContext.getCurrentInstance();
		if (this.modificacion) {
			for (int i = 0; i < getAcuerdos().size(); i++) {
				AcuerdoInstrumentalBean cuenta = getAcuerdos().get(i);
				if (cuenta.isAlta()) {
					if ("".equals(cuenta.getNombre())
							|| cuenta.getNombre() == null) {
						String id = "input[id$=txtApoderado" + i + "]";
						context.execute("scrollTo('" + id + "');");
						FacesContext.getCurrentInstance().validationFailed();
						List<UIComponent> componentes = FacesContext
								.getCurrentInstance().getViewRoot()
								.getChildren();
						this.setValidationFalseRecursively(componentes, i, true);
						return true;
					}
				}
			}
		}
		return false;
	}

	private void setValidationFalseRecursively(List<UIComponent> componentes,
			int index, boolean cuentas) {
		if (componentes != null && !componentes.isEmpty()) {
			for (UIComponent componente : componentes) {
				if (cuentas) {
					if (componente.getClientId().contains(
							"txtApoderado" + index)) {
						((UIInput) componente).setValid(false);
					}
				} else {
					if (componente.getClientId().contains("txtApoderadoAdd")) {
						((UIInput) componente).setValid(false);
					}
				}
				this.setValidationFalseRecursively(componente.getChildren(),
						index, cuentas);
			}
		}
	}

	/**
	 * Método para guardar los datos de mantenimiento de centros.
	 * 
	 * @return Ruta para recargar página.
	 * @throws NoControlableException
	 *             Excepción no controlable del servicio.
	 */
	public String guardarDatos() throws NoControlableException {
		String ruta = null;
		mensajeError = "";
		if (errorApoderado()) {
			this.dialogoGuardado = DialogoListadoEnum.ERROR;
			this.dialogoGuardado
					.setMensaje("Por favor, ingrese un apoderado válido");
			this.dialogoGuardado.setDetalles("");
			this.dialogoGuardado.setMostrar(true);
			return ruta;
		} else {
			try {
				Integer codigo = -1;
				if (modificacion) {
					codigo = modificarCentro(codigo);
				} else {
					codigo = altaCentro(codigo);
				}
				codigo = agregarCentrosControladores(codigo);
				codigo = eliminarCentrosControladores(codigo);

				cliente = (ClientePersonaMoralBean) clienteConsultaPersonaMoral
						.ejecutarTRN(this.altaCentro.getIdInterno());

				if (!cliente.getIndCliente()) {
					codigo = modificacionPersonaMoralBackEnd.ejecutarTRN(
							cliente, cliente);
				}

				if (this.primeraAltaCentro) {
					HashMap<Integer, Integer> listaApoderados = new HashMap<>();
					this.listaRelaciones = consultaPersonasRelacionadasBackEnd
							.ejecutarTRN(this.altaCentro.getIdInterno());

					if (!this.listaRelaciones.isEmpty()) {
						for (AcuerdoInstrumentalBean acuerdo : this.acuerdos) {
							boolean altaRelacion = false;
							for (PersonaRelacionadaBean relacion : this.listaRelaciones) {
								if (acuerdo.isAlta()) {
									if (ConstantesFuncionales.APODERADO_DE
											.equals(relacion
													.getRelacionSeleccionada()
													.getClaveFila())
											&& relacion.getIdInterna()
													.intValue() == acuerdo
													.getIdApoderado()
													.intValue()) {
										altaRelacion = true;
									}
								}
							}
							if (!altaRelacion && acuerdo.isAlta()) {
								if (!listaApoderados.containsKey(acuerdo
										.getIdApoderado().intValue())) {
									try {
										altaRelacionClientePersonaBackEnd
												.ejecutarTRN(
														this.altaCentro
																.getIdInterno(),
														acuerdo.getIdApoderado()
																.intValue(),
														catalogos.getCatalogoBean(CatalogoEnum.TP_PERS_RL_PERS,
																ConstantesFuncionales.APODERADO_DE),
														null, null, null);
										listaApoderados.put(acuerdo
												.getIdApoderado().intValue(),
												acuerdo.getIdApoderado()
														.intValue());
									} catch (ControlableException c) {
										mensajeError = c.getMensajeUsuario() + " " + c.getMensajeDetalle();
									}
								}
							}
						}
					} else {

						for (AcuerdoInstrumentalBean acuerdo : this.acuerdos) {
							listaApoderados.put(acuerdo.getIdApoderado()
									.intValue(), acuerdo.getIdApoderado()
									.intValue());
						}

						Iterator<Integer> iterador = listaApoderados.keySet()
								.iterator();
						while (iterador.hasNext()) {
							Integer llave = iterador.next();
							try {
								altaRelacionClientePersonaBackEnd.ejecutarTRN(
										this.altaCentro.getIdInterno(),
										listaApoderados.get(llave),
										catalogos.getCatalogoBean(CatalogoEnum.TP_PERS_RL_PERS,
												ConstantesFuncionales.APODERADO_DE),
										null, null, null);
							} catch (ControlableException c) {
								mensajeError = c.getMensajeUsuario() + " " + c.getMensajeDetalle();
							}
						}
					}

					codigo = guardarCuentas(codigo);
				}

				if (codigo.intValue() != -1 || !"".equals(mensajeError)) {
					if (!"".equals(mensajeError)) {
						return recargarPagina(mensajeError);
					} else {
						RequestContext.getCurrentInstance().execute(
								"PF('dlgFinalizacionMod').show()");
					}
				} else {
					this.dialogoGuardado = DialogoListadoEnum.SIN_CAMBIOS;
					this.dialogoGuardado
							.setMensaje("No se han realizado cambios");
					RequestContext.getCurrentInstance().execute(
							"PF('dlgGuardado').show()");
				}

			} catch (ControlableException c) {
				this.dialogoGuardado = DialogoListadoEnum.SIN_CAMBIOS;
				this.dialogoGuardado.setMensaje(c.getMensajeUsuario() + " - " + c.getMensajeDetalle());
				RequestContext.getCurrentInstance().execute(
						"PF('dlgGuardado').show()");
			}
		}
		return ruta;
	}

	/**
	 * Método que ejecuta el servicio de alta de cuentas instrumentales.
	 * 
	 * @return codigo de retorno del servicio.
	 */
	public int guardarCuentas(int codigo) throws NoControlableException {
		for (AcuerdoInstrumentalBean cuenta : getAcuerdos()) {
			if (cuenta.isAlta()) {
				String[] linea = cuenta.getLinea().split("-");
				String[] grupo = cuenta.getGrupo().split("-");
				cuenta.setLinea(linea[0]);
				cuenta.setGrupo(grupo[0]);
				cuenta.setCodigoCentro(this.altaCentro.getNumero());
				cuenta.setIdInternoCentro(this.altaCentro.getIdInterno());

				try {
					codigo = this.altaCuentas.ejecutarWS(cuenta);
				} catch (ControlableException c) {
					mensajeError = c.getMensajeUsuario() + " " + c.getMensajeDetalle();
				}
			}
		}
		return codigo;
	}

	/**
	 * Metodo que agrega centros controladores.
	 * 
	 * @return Codigo de retorno del servicio.
	 * @throws NoControlableException
	 *             Excepción no controlable del servicio
	 * @throws ControlableException
	 *             Excepción controlable del servicio
	 */
	public int agregarCentrosControladores(int codigo)
			throws NoControlableException {
		for (String aplicacionSeleccionada : this.aplicacionesSeleccionadas) {
			boolean encontrado = false;
			for (CentroControladorBean aplicacion : this.aplicaciones) {
				if (aplicacionSeleccionada.equals(aplicacion
						.getCodigoCentroControlador())) {
					encontrado = true;
				}
			}
			if (!encontrado) {
				CentroControladorBean centroCtrl = new CentroControladorBean();
				centroCtrl.setCodigoCentroControlador(aplicacionSeleccionada);
				centroCtrl.setCodigoCentro(this.altaCentro.getNumero());
				try {
					codigo = this.altaCentroControladorBackEnd
							.ejecutarTRN(centroCtrl);
				} catch (ControlableException c) {
					mensajeError = c.getMensajeUsuario() + " " + c.getMensajeDetalle();
				}

			}
		}
		return codigo;
	}

	/**
	 * Metodo que elimina centros controladores.
	 * 
	 * @return Codigo de retorno del servicio.
	 * @throws NoControlableException
	 *             Excepción no controlable del servicio
	 * @throws ControlableException
	 *             Excepción controlable del servicio
	 */
	public int eliminarCentrosControladores(int codigo)
			throws NoControlableException {
		for (CentroControladorBean aplicacion : this.aplicaciones) {
			boolean encontrado = false;
			for (String aplicacionSeleccionada : this.aplicacionesSeleccionadas) {
				if (aplicacionSeleccionada.equals(aplicacion
						.getCodigoCentroControlador())) {
					encontrado = true;
				}
			}
			if (!encontrado) {
				try {
					codigo = this.bajaCentroControladorBackEnd
							.ejecutarTRN(aplicacion);
				} catch (ControlableException c) {
					mensajeError = c.getMensajeUsuario() + " " + c.getMensajeDetalle();
				}
			}
		}
		return codigo;
	}

	/**
	 * Método que ejecuta los webservices de alta o modificación del centro.
	 * 
	 * @return int Codigo de retorno del servicio.
	 * @throws NoControlableException
	 *             Excepción no controlable del servicio.
	 * @throws ControlableException
	 *             Excepción controlable del servicio.
	 */
	public int altaCentro(int codigo) throws NoControlableException,
			ControlableException {
		codigo = alta.ejecutarTRN(altaCentro);
		return codigo;
	}

	/**
	 * Método que ejecuta la modificación de un centro.
	 * 
	 * @return int Código del retorno del servicio.
	 * @throws NoControlableException
	 *             Excepción no controlable del servicio.
	 * @throws ControlableException
	 *             Excepción controlable del servicio.
	 */
	public int modificarCentro(int codigo) throws NoControlableException,
			ControlableException {
		if (!this.respaldoCentro.equals(this.altaCentro)) {
			codigo = modificar.ejecutarTRN(altaCentro);
		}
		return codigo;
	}

	private String recargarPagina(String error) {
		this.obtieneFlash().put(
				ParametrosFlashEnum.CODIGO_CENTRO.getParamFlash(),
				this.altaCentro.getNumero());
		obtieneFlash().put("modificado", this.modificacion);
		obtieneFlash().put("primeraAltaCentro", this.primeraAltaCentro);
		obtieneFlash().put("recarga", true);
		if (!"".equals(error)) {
			obtieneFlash().put("detalleError", error);
		}
		this.obtieneFlash().put(
				ParametrosFlashEnum.CONTROLLER_DESTINO.getParamFlash(),
				destinoController);
		this.obtieneFlash().put(
				ParametrosFlashEnum.NAVEGACION_BUSQUEDA.getParamFlash(),
				destino);
		this.obtieneFlash().put(
				ParametrosFlashEnum.NAVEGACION_MANAGED_BEAN.getParamFlash(),
				true);
		return NavegacionEnum.ALTA_CENTRO.getRuta();
	}

	/**
	 * Método para consultar las cuentas instrumentales de un centro consultado.
	 * 
	 * @param codigo
	 *            Código del centro.
	 */
	public void consultarCuentas(String codigo) throws ControlableException,
			NoControlableException {
		this.acuerdos = new ArrayList<>();
		boolean cuenta1 = false;
		boolean cuenta2 = false;
		boolean cuenta3 = false;
		if (modificacion) {
			try {
				this.acuerdos = this.consultaCuentas.ejecutarTRN(codigo);
			} catch (ControlableException c) {
				if (c.getRtncod() == 7) {
					this.acuerdos = new ArrayList<>();
				} else {
					throw c;
				}
			}
		}

		if (this.acuerdos.isEmpty()) {
			this.addAcuerdo1();
			this.addAcuerdo2();
			this.addAcuerdo3();
		} else {
			for (AcuerdoInstrumentalBean acuerdo : this.acuerdos) {
				String linea = acuerdo.getLinea();
				String grupo = acuerdo.getGrupo();
				if ((ConstantesFuncionales.CTE_INSTRUMENTALES).equals(linea
						.trim())
						&& (ConstantesFuncionales.CTE_GESTION_INCIDENCIAS)
								.equals(grupo)) {
					cuenta1 = true;
				} else if ((ConstantesFuncionales.CTE_INSTRUMENTALES)
						.equals(linea.trim())
						&& (ConstantesFuncionales.CTE_EMISION_CHEQUES)
								.equals(grupo)) {
					cuenta2 = true;
				} else if ((ConstantesFuncionales.CTE_DEPOSITOS).equals(linea
						.trim())
						&& (ConstantesFuncionales.CTE_INTERVENCION)
								.equals(grupo)) {
					cuenta3 = true;
				}
				String nombreLinea = catalogos.getCatalogoBean(
						CatalogoEnum.TP_LINEA_ASES, linea).getDescripcionC();
				String nombreGrupo = catalogoGrupos.getCatalogoDescripcion(
						linea, grupo);
				acuerdo.setLinea(linea + "-" + nombreLinea);
				acuerdo.setGrupo(grupo + "-" + nombreGrupo);
				acuerdo.setAlta(false);
			}
			if (!cuenta1) {
				this.addAcuerdo1();
			}
			if (!cuenta2) {
				this.addAcuerdo2();
			}
			if (!cuenta3) {
				this.addAcuerdo3();
			}
		}
	}

	private void addAcuerdo1() {
		AcuerdoInstrumentalBean acuerdo1 = new AcuerdoInstrumentalBean();
		acuerdo1.setLinea(ConstantesFuncionales.CTE_INSTRUMENTALES + "-"
				+ nombreLineaInstrumentales);
		acuerdo1.setGrupo(ConstantesFuncionales.CTE_GESTION_INCIDENCIAS + "-"
				+ nombreGrupoGestionIncidencia);
		acuerdo1.setAlta(true);
		acuerdos.add(acuerdo1);
	}

	private void addAcuerdo2() {
		AcuerdoInstrumentalBean acuerdo2 = new AcuerdoInstrumentalBean();
		acuerdo2.setLinea(ConstantesFuncionales.CTE_INSTRUMENTALES + "-"
				+ nombreLineaInstrumentales);
		acuerdo2.setGrupo(ConstantesFuncionales.CTE_EMISION_CHEQUES + "-"
				+ nombreGrupoEmisionCheques);
		acuerdo2.setAlta(true);
		acuerdos.add(acuerdo2);
	}

	private void addAcuerdo3() {
		AcuerdoInstrumentalBean acuerdo3 = new AcuerdoInstrumentalBean();
		acuerdo3.setLinea(ConstantesFuncionales.CTE_DEPOSITOS + "-"
				+ nombreLineaDepositos);
		acuerdo3.setGrupo(ConstantesFuncionales.CTE_INTERVENCION + "-"
				+ nombreGrupoIntervencion);
		acuerdo3.setAlta(true);
		acuerdos.add(acuerdo3);
	}

	/**
	 * Método utilizado para cancelar el alta de Centros.
	 * 
	 * @return página de inicio
	 * 
	 */
	public String cancelarAlta() {
		FacesContext.getCurrentInstance().getExternalContext().getFlash();
		return NavegacionEnum.INICIO.getRuta();
	}

	/**
	 * Método para ejecutar el servicio de detalle del Centro en el BackEnd.
	 * 
	 * @return AltaCentroBean Bean con los datos obtenidos del servicio.
	 */
	public AltaCentroBean detalleCentro() {
		return this.detalle.ejecutarTRN(codigoCentro);
	}

	/**
	 * Método que navega a la pagina de Alta de Acuerdos instrumentales.
	 * 
	 * @return Ruta de Acuerdos instrumentales.
	 */
	public String siguiente() {
		if (this.catalogoSeleccionado != null) {
			this.altaCentro.setPlazaBancaria(this.catalogoSeleccionado
					.getClaveFila());
		} else {
			this.altaCentro.setPlazaBancaria("");
		}
		if (!modificacion) {
			if (this.obtenerCentroSeleccionado(this.altaCentro.getNumero()) != null) {
				this.dialogoGuardado = DialogoListadoEnum.ALERTA;
				this.dialogoGuardado
						.setMensaje("El número de centro ya existe");
				this.dialogoGuardado.setMostrar(true);
			} else {
				enviarParametrosSiguiente();
				return NavegacionEnum.ALTA_CENTRO2.getRuta();
			}
		} else {
			enviarParametrosSiguiente();
			return NavegacionEnum.ALTA_CENTRO2.getRuta();
		}

		return null;
	}

	/**
	 * Méotod que envia parámetros para la siguiente ventana.
	 */
	public void enviarParametrosSiguiente() {

		this.altaCentro.setDomicilio(domicilioController.getDomicilioBean());

		obtieneFlash().put(ParametrosFlashEnum.CENTRO.getParamFlash(),
				this.altaCentro);
		obtieneFlash().put(ParametrosFlashEnum.RESPALDO_CENTRO.getParamFlash(),
				this.respaldoCentro);
		obtieneFlash().put("modificacion", this.modificacion);
		obtieneFlash().put("pintarAplicacion", this.pintarCampoAplicacion);
		this.obtieneFlash().put(
				ParametrosFlashEnum.APLICACIONES_CENTROS_SELEC.getParamFlash(),
				this.aplicacionesSeleccionadas);
		this.obtieneFlash().put(
				ParametrosFlashEnum.APLICACIONES_CENTROS_CTRLS.getParamFlash(),
				this.aplicaciones);
		this.obtieneFlash().put("ventanaAcuerdos", this.ventanaAcuerdos);
		this.obtieneFlash().put(
				ParametrosFlashEnum.CONTROLLER_DESTINO.getParamFlash(),
				destinoController);
		this.obtieneFlash().put(
				ParametrosFlashEnum.NAVEGACION_BUSQUEDA.getParamFlash(),
				destino);
		this.obtieneFlash().put(
				ParametrosFlashEnum.NAVEGACION_MANAGED_BEAN.getParamFlash(),
				true);

		if (this.acuerdos != null) {
			this.obtieneFlash()
					.put(ParametrosFlashEnum.ACUERDOS_INSTRUMENTALES
							.getParamFlash(),
							this.acuerdos);

			this.obtieneFlash().put(
					ParametrosFlashEnum.EXISTEN_CUENTAS.getParamFlash(),
					this.existenCuentas);

			this.obtieneFlash().put(
					ParametrosFlashEnum.MANTENIMIENTO_ACUERDO.getParamFlash(),
					this.mantenimientoAcuerdo);
		}

	}

	/**
	 * Método que regresa ruta de ventana de Alta Centro.
	 * 
	 * @return String Ruta de Alta de Centro
	 */
	public String volverPrincipal() {
		obtieneFlash().put(ParametrosFlashEnum.CENTRO.getParamFlash(),
				this.altaCentro);
		obtieneFlash().put(ParametrosFlashEnum.RESPALDO_CENTRO.getParamFlash(),
				this.respaldoCentro);
		obtieneFlash().put("modificacion", this.modificacion);
		obtieneFlash().put("pintarAplicacion", this.pintarCampoAplicacion);
		this.obtieneFlash().put(
				ParametrosFlashEnum.APLICACIONES_CENTROS_SELEC.getParamFlash(),
				this.aplicacionesSeleccionadas);
		this.obtieneFlash().put(
				ParametrosFlashEnum.APLICACIONES_CENTROS_CTRLS.getParamFlash(),
				this.aplicaciones);
		this.obtieneFlash().put(
				ParametrosFlashEnum.CONTROLLER_DESTINO.getParamFlash(),
				destinoController);
		this.obtieneFlash().put(
				ParametrosFlashEnum.NAVEGACION_BUSQUEDA.getParamFlash(),
				destino);
		this.obtieneFlash().put(
				ParametrosFlashEnum.NAVEGACION_MANAGED_BEAN.getParamFlash(),
				true);

		this.obtieneFlash().put(
				ParametrosFlashEnum.ACUERDOS_INSTRUMENTALES.getParamFlash(),
				this.acuerdos);

		this.obtieneFlash().put(
				ParametrosFlashEnum.EXISTEN_CUENTAS.getParamFlash(),
				this.existenCuentas);

		this.obtieneFlash().put(
				ParametrosFlashEnum.MANTENIMIENTO_ACUERDO.getParamFlash(),
				this.mantenimientoAcuerdo);

		return NavegacionEnum.ALTA_CENTRO.getRuta();
	}
	
	
	public String inicio(){
		return NavegacionEnum.ALTA_CENTRO.getRuta();
	}

	/**
	 * Método que realiza la busqueda de IdInterna al tabular.
	 * 
	 * @param acuerdo
	 *            Acuerdo Instrumental
	 * @throws NoControlableException
	 *             Excepción no controlable del servicio.
	 */
	public void busquedaIdEmpleadoBlur(AcuerdoInstrumentalBean acuerdo)
			throws NoControlableException {
		if (acuerdo.getIdApoderado() != null) {
			String tipoResultado = "resultadosBusquedaEmpleadosIdInterna";
			try {
				List<Object> datos = this.consultaEmpleadoBackEnd.ejecutarTRN(
						acuerdo.getIdApoderado(), tipoResultado);
				if (datos != null && !datos.isEmpty()) {
					EmpleadoBusquedaBean empleadoBusquedaBean = (EmpleadoBusquedaBean) datos
							.get(0);
					acuerdo.setNombre(empleadoBusquedaBean.getNombre());
				} else {
					acuerdo.setNombre("");
					this.dialogoGuardado = DialogoListadoEnum.SIN_CAMBIOS;
					this.dialogoGuardado
							.setMensaje("No se han encontrado datos");
					RequestContext.getCurrentInstance().execute(
							"PF('dlgGuardado').show()");
				}
			} catch (ControlableException c) {
				FacesMessage message = null;
				if (c.getRtncod() != 7) {
					this.dialogoGuardado = DialogoListadoEnum.SIN_CAMBIOS;
					this.dialogoGuardado.setMensaje("");
					this.dialogoGuardado.setDetalles(c.getMensajeUsuario()
							+ " - " + c.getMensajeDetalle());
					RequestContext.getCurrentInstance().execute(
							"PF('dlgGuardado').show()");
				} else {
					this.dialogoGuardado = DialogoListadoEnum.SIN_CAMBIOS;
					this.dialogoGuardado
							.setMensaje("No se han encontrado datos");
					RequestContext.getCurrentInstance().execute(
							"PF('dlgGuardado').show()");
				}

			}
		} else {
			acuerdo.setNombre("");
		}
	}

	/**
	 * Método que redirige a la busqueda de empleado.
	 * 
	 * @param acuerdo
	 *            Cuenta instrumental.
	 * @return Ruta del buscador.
	 */
	public String busquedaEmpleado(AcuerdoInstrumentalBean acuerdo) {
		this.posicionLista = this.acuerdos.indexOf(acuerdo);
		this.obtieneFlash().put(
				ParametrosFlashEnum.TIPO_BUSQUEDA.getParamFlash(), "EMPLEADOS");
		managedBeanStateRecover.enviaController(this,
				NavegacionEnum.ALTA_CENTRO2);
		return NavegacionEnum.BUSQUEDA.getRuta();
	}

	/**
	 * Método que redirige a la busqueda de empleado para deposito.
	 * 
	 * @return Ruta del buscador.
	 */
	public String busquedaEmpleadoDeposito() {
		this.obtieneFlash().put(
				ParametrosFlashEnum.TIPO_BUSQUEDA.getParamFlash(), "EMPLEADOS");
		managedBeanStateRecover.enviaController(this,
				NavegacionEnum.ALTA_CENTRO2);
		return NavegacionEnum.BUSQUEDA.getRuta();
	}

	/**
	 * Método que regresa ruta de ventana de Inicio.
	 * 
	 * @return String Ruta de Inicio
	 */
	public String redirigirInicio() {
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
	 * Método que regresa ruta de ventana de Inicio.
	 * 
	 * @return String Ruta de Inicio
	 */
	public String menuPrincipal() {
		return NavegacionEnum.INICIO.getRuta();
	}

	/**
	 * Obtiene memoria flash para variables en scope view.
	 * 
	 * @return Metodo utilizado para recuperar una instancia de Flash
	 */
	public Flash obtieneFlash() {
		return FacesContext.getCurrentInstance().getExternalContext()
				.getFlash();
	}

	public AltaCentroBean getAltaCentro() {
		return altaCentro;
	}

	public void setAltaCentro(AltaCentroBean altaCentro) {
		this.altaCentro = altaCentro;
	}

	public AltaCentroBean getRespaldoCentro() {
		return respaldoCentro;
	}

	public void setRespaldoCentro(AltaCentroBean respaldoCentro) {
		this.respaldoCentro = respaldoCentro;
	}

	public boolean isPintarCampoAplicacion() {
		return pintarCampoAplicacion;
	}

	public void setPintarCampoAplicacion(boolean pintarCampoAplicacion) {
		this.pintarCampoAplicacion = pintarCampoAplicacion;
	}

	public AcuerdoInstrumentalBean getMantenimientoAcuerdo() {
		return mantenimientoAcuerdo;
	}

	public void setMantenimientoAcuerdo(
			AcuerdoInstrumentalBean mantenimientoAcuerdo) {
		this.mantenimientoAcuerdo = mantenimientoAcuerdo;
	}

	public boolean isModificacion() {
		return modificacion;
	}

	public void setModificacion(boolean modificacion) {
		this.modificacion = modificacion;
	}

	public int getIdInternoCentro() {
		return idInternoCentro;
	}

	public void setIdInternoCentro(int idInternoCentro) {
		this.idInternoCentro = idInternoCentro;
	}

	public int getPosicionLista() {
		return posicionLista;
	}

	public void setPosicionLista(int posicionLista) {
		this.posicionLista = posicionLista;
	}

	public boolean isExisteCuenta() {
		return existeCuenta;
	}

	public void setExisteCuenta(boolean existeCuenta) {
		this.existeCuenta = existeCuenta;
	}

	public boolean isExistenCuentas() {
		return existenCuentas;
	}

	public void setExistenCuentas(boolean existenCuentas) {
		this.existenCuentas = existenCuentas;
	}

	public boolean isVentanaAcuerdos() {
		return ventanaAcuerdos;
	}

	public void setVentanaAcuerdos(boolean ventanaAcuerdos) {
		this.ventanaAcuerdos = ventanaAcuerdos;
	}

	public List<String> getAplicacionesSeleccionadas() {
		return aplicacionesSeleccionadas;
	}

	public void setAplicacionesSeleccionadas(
			List<String> aplicacionesSeleccionadas) {
		this.aplicacionesSeleccionadas = aplicacionesSeleccionadas;
	}

	public List<CentroControladorBean> getAplicaciones() {
		return aplicaciones;
	}

	public void setAplicaciones(List<CentroControladorBean> aplicaciones) {
		this.aplicaciones = aplicaciones;
	}

	public NavegacionEnum getDestino() {
		return destino;
	}

	public void setDestino(NavegacionEnum destino) {
		this.destino = destino;
	}

	public Object getDestinoController() {
		return destinoController;
	}

	public void setDestinoController(Object destinoController) {
		this.destinoController = destinoController;
	}

	public DialogoListadoUtils getDialogoUtils() {
		return dialogoUtils;
	}

	public void setDialogoUtils(DialogoListadoUtils dialogoUtils) {
		this.dialogoUtils = dialogoUtils;
	}

	public DialogoListadoEnum getDialogoGuardado() {
		return dialogoGuardado;
	}

	public void setDialogoGuardado(DialogoListadoEnum dialogoGuardado) {
		this.dialogoGuardado = dialogoGuardado;
	}

	public void setAcuerdos(List<AcuerdoInstrumentalBean> acuerdos) {
		this.acuerdos = acuerdos;
	}

	/**
	 * Método para obtener la lista de cuentas instrumentales para desplegarla
	 * en la vista.
	 * 
	 * @return Lista de cuentas instrumentales.
	 */
	public List<AcuerdoInstrumentalBean> getAcuerdos() {

		return acuerdos;
	}

	/**
	 * Método que obtiene la opción del catalogo seleccionado.
	 * 
	 * @return CatalogoBean Catalogo seleccionado.
	 */
	public CatalogoBean getCatalogoSeleccionado() {
		if (this.altaCentro.getPlazaBancaria() != null
				&& !("").equals(this.altaCentro.getPlazaBancaria().trim())) {
			this.catalogoSeleccionado = catalogos.getCatalogoBean(
					CatalogoEnum.TP_PLAZA_BANCARIA,
					this.altaCentro.getPlazaBancaria());
		}
		return this.catalogoSeleccionado;
	}

	public void setCatalogoSeleccionado(CatalogoBean catalogoSeleccionado) {
		this.catalogoSeleccionado = catalogoSeleccionado;
	}

	public DomicilioController getDomicilioController() {
		return domicilioController;
	}

	public void setDomicilioController(DomicilioController domicilioController) {
		this.domicilioController = domicilioController;
	}

}
