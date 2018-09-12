package mx.babel.bansefi.banksystem.base.controllers;

import java.io.Serializable;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.servlet.http.HttpServletRequest;

import mx.babel.arq.catalogo.beans.CatalogoBean;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.sesion.contexto.beans.NotificacionBean;
import mx.babel.arq.sesion.utils.ContextoUtils;
import mx.babel.bansefi.banksystem.base.backends.login.NotificacionBackEnd;
import mx.babel.bansefi.banksystem.base.enums.NavegacionEnum;
import mx.babel.bansefi.banksystem.base.enums.ParametrosFlashEnum;
import mx.babel.bansefi.banksystem.base.utils.ManagedBeanStateRecover;
import mx.babel.bansefi.banksystem.base.utils.StringToDateConverter;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.DateTimeComparator;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Controlador encargado del alta, mantenimiento y consulta de los mensajes
 * intrusivos que serán mostrados al usuario.
 * 
 * @author omar.marquez
 * 
 */
@ManagedBean
@ViewScoped
@Component
@Scope("view")
public class NotificacionController implements Serializable {

	private static final long serialVersionUID = -8673421812831108134L;
	private static final Logger LOGGER = LogManager
			.getLogger(NotificacionController.class);

	// Constantes para los destinatarios de las notificaciones.
	private static final String ENTIDAD = "ENTIDAD";
	private static final String CENTRO = "CENTRO";

	// Atributos para la consulta de notificaciones.
	private List<NotificacionBean> notificaciones;
	private boolean panelDetalleDisponible;
	private NotificacionBean notificacionSeleccionada;

	// Atributos para el alta y mantenimiento de las notificaciones.
	private String destinatario;
	private boolean botonAnadirDeshabilitado;
	private CatalogoBean centroSeleccionado;
	private NotificacionBean notificacionNueva;
	private boolean notificacionUrgente;
	private boolean fechaVigenteDesdeDeshabilitada;

	// Atributos para la navegación y el retorno de datos.
	private NavegacionEnum navegacionEnumOrigen;
	private Object controladorOrigen;

	@Autowired
	ContextoUtils contextoUtils;

	@Autowired
	NotificacionBackEnd notificacionBackEnd;

	@Autowired
	ManagedBeanStateRecover managedBeanStateRecover;

	/**
	 * Constructor.
	 */
	public NotificacionController() {
		this.notificaciones = new ArrayList<>();
		this.panelDetalleDisponible = false;
		this.notificacionSeleccionada = null;
		this.destinatario = "";
		this.botonAnadirDeshabilitado = false;
		this.centroSeleccionado = new CatalogoBean();
		this.notificacionNueva = new NotificacionBean();
		this.notificacionUrgente = false;
		this.fechaVigenteDesdeDeshabilitada = true;
	}

	@PostConstruct
	public void init() {
		if (this.obtenerFlash().get(
				ParametrosFlashEnum.NAVEGACION_MANAGED_BEAN.getParamFlash()) != null) {
			if ((Boolean) this.obtenerFlash()
					.get(ParametrosFlashEnum.NAVEGACION_MANAGED_BEAN
							.getParamFlash())) {
				this.navegacionEnumOrigen = this.managedBeanStateRecover
						.getDestino();
				this.controladorOrigen = this.managedBeanStateRecover
						.getController();
				this.initData();
			} else {
				this.managedBeanStateRecover.recuperaController(this);
			}
		} else {
			this.initData();
		}
	}

	/**
	 * Método privado que inicializa la carga de datos.
	 */
	private void initData() {
		String[] arrUno, arrDos, arrTres = null;
		arrUno = StringUtils
				.split(NavegacionEnum.NOTIFICACIONES.getRuta(), '?');
		arrDos = StringUtils.split(NavegacionEnum.ALTA_NOTIFICACION.getRuta(),
				'?');
		arrTres = StringUtils.split(
				NavegacionEnum.DETALLE_NOTIFICACION.getRuta(), '?');
		if (this.obtenerUrlVistaDestino().contains(arrUno[0])) {
			// Sí la vista corresponde a la de consulta de notificaciones.
			LOGGER.debug("Usuario pretende navegar a la consulta de notificaciones.");
			this.loadNotificationList();
		} else if (this.obtenerUrlVistaDestino().contains(arrDos[0])) {
			// Sí la vista corresponde a la de alta de notificaciones.
			LOGGER.debug("Usuario pretende navegar al alta de notificaciones.");
		} else if (this.obtenerUrlVistaDestino().contains(arrTres[0])) {
			// Sí la vista corresponde a la de detalle de la notificación.
			LOGGER.debug("Usuario pretende navegar al detalle de la notificación.");
			this.loadDataForDetailView();
		}
	}

	// INICIA DECLARACIÓN DE GETTERS Y SETTERS

	/**
	 * Método que devuelve una lista de notificaciones.
	 * 
	 * @return notificaciones
	 */
	public List<NotificacionBean> getNotificaciones() {
		return notificaciones;
	}

	/**
	 * Método que establece una lista de notificaciones.
	 * 
	 * @param notificaciones
	 */
	public void setNotificaciones(List<NotificacionBean> notificaciones) {
		this.notificaciones = notificaciones;
	}

	/**
	 * Método que devuelve un indicador booleano para determinar si el panel de
	 * detalle debe mostrarse o no.
	 * 
	 * @return indicador booleano
	 */
	public boolean isPanelDetalleDisponible() {
		return panelDetalleDisponible;
	}

	/**
	 * Método que establece el valor del indicador booleano para mostrar el
	 * panel de detalle.
	 * 
	 * @param panelDetalleDisponible
	 */
	public void setPanelDetalleDisponible(boolean panelDetalleDisponible) {
		this.panelDetalleDisponible = panelDetalleDisponible;
	}

	/**
	 * Método que devuelve la notificación seleccionada por el usuario.
	 * 
	 * @return notificacionSeleccionada
	 */
	public NotificacionBean getNotificacionSeleccionada() {
		return notificacionSeleccionada;
	}

	/**
	 * Método que establece un objeto tipo Notificacion que representa la
	 * notificacionSeleccionada.
	 * 
	 * @param notificacionSeleccionada
	 */
	public void setNotificacionSeleccionada(
			NotificacionBean notificacionSeleccionada) {
		this.notificacionSeleccionada = notificacionSeleccionada;
	}

	/**
	 * Método que devuelve el destinatario del mensaje.
	 * 
	 * @return destinatario
	 */
	public String getDestinatario() {
		return destinatario;
	}

	/**
	 * Método que establece el destinatario del mensaje.
	 * 
	 * @param destinatario
	 */
	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}

	/**
	 * Método que devuelve un indicador para determinar sí se debe deshabilitar
	 * o no el botón Añadir de la vista.
	 * 
	 * @return indicador booleano
	 */
	public boolean isBotonAnadirDeshabilitado() {
		return botonAnadirDeshabilitado;
	}

	/**
	 * Método que establece el valor del indicador para deshabilitar el botón
	 * Añadir de la vista.
	 * 
	 * @param botonAnadirDeshabilitado
	 */
	public void setBotonAnadirDeshabilitado(boolean botonAnadirDeshabilitado) {
		this.botonAnadirDeshabilitado = botonAnadirDeshabilitado;
	}

	/**
	 * Método que a partir de la selección del usuario devuelve un objeto tipo
	 * CatalogoBean con la información del centro.
	 * 
	 * @return centroSeleccionado
	 */
	public CatalogoBean getCentroSeleccionado() {
		return centroSeleccionado;
	}

	/**
	 * Método que establece un objeto tipo CatalogoBean con la información del
	 * centro seleccionado.
	 * 
	 * @param centroSeleccionado
	 */
	public void setCentroSeleccionado(CatalogoBean centroSeleccionado) {
		this.centroSeleccionado = centroSeleccionado;
	}

	/**
	 * Método que devuelve un objeto tipo NotificacionBean.
	 * 
	 * @return notificacionNueva
	 */
	public NotificacionBean getNotificacionNueva() {
		return notificacionNueva;
	}

	/**
	 * Método que establece un objeto tipo NotificacionBean.
	 * 
	 * @param notificacionNueva
	 */
	public void setNotificacionNueva(NotificacionBean notificacionNueva) {
		this.notificacionNueva = notificacionNueva;
	}

	/**
	 * Método que devuelve un indicador para determinar sí la notificación es
	 * urgente o no.
	 * 
	 * @return indicador booleano
	 */
	public boolean isNotificacionUrgente() {
		return notificacionUrgente;
	}

	/**
	 * Método que establece el valor del indicador para determinar que una
	 * notificación es urgente.
	 * 
	 * @param notificacionUrgente
	 */
	public void setNotificacionUrgente(boolean notificacionUrgente) {
		this.notificacionUrgente = notificacionUrgente;
	}

	/**
	 * Método que devuelve un indicador para determinar si la fecha vigente
	 * desde debe ser inhibida o no. Este método sólamente se utiliza en la
	 * vista detalleNotificacion.xhtml
	 * 
	 * @return indicador booleano
	 */
	public boolean isFechaVigenteDesdeDeshabilitada() {
		return fechaVigenteDesdeDeshabilitada;
	}

	/**
	 * Método que establece el valor del indicador para deshabilitar la fecha
	 * vigente desde.
	 * 
	 * @param fechaVigenteDesdeDeshabilitada
	 */
	public void setFechaVigenteDesdeDeshabilitada(
			boolean fechaVigenteDesdeDeshabilitada) {
		this.fechaVigenteDesdeDeshabilitada = fechaVigenteDesdeDeshabilitada;
	}

	/**
	 * Método que devuelve un NavegacionEnum para volver al origen.
	 * 
	 * @return navegacionEnumOrigen
	 */
	public NavegacionEnum getNavegacionEnumOrigen() {
		return navegacionEnumOrigen;
	}

	/**
	 * Método que establece un NavegacionEnum para volver al origen.
	 * 
	 * @param navegacionEnumOrigen
	 */
	public void setNavegacionEnumOrigen(NavegacionEnum navegacionEnumOrigen) {
		this.navegacionEnumOrigen = navegacionEnumOrigen;
	}

	/**
	 * Método que devuelve un objeto con el controlador de origen.
	 * 
	 * @return controladorOrigen
	 */
	public Object getControladorOrigen() {
		return controladorOrigen;
	}

	/**
	 * Método que establece un objeto con el controlador de origen.
	 * 
	 * @param controladorOrigen
	 */
	public void setControladorOrigen(Object controladorOrigen) {
		this.controladorOrigen = controladorOrigen;
	}

	// INICIA DECLARACIÓN DE MÉTODOS PRINCIPALES

	/**
	 * Método privado que genera una lista de notificaciones de manera dinámica.
	 */
	private void loadNotificationList() {
		if (contextoUtils != null
				&& contextoUtils.getNotificacionesUsuario() != null) {
			notificaciones = contextoUtils.getNotificacionesUsuario();
			for (NotificacionBean notificacion : notificaciones) {
				if (notificacion.getCentro() != null
						&& !notificacion.getCentro().trim().isEmpty()) {
					notificacion.setDestinatario(NotificacionController.CENTRO);
				} else {
					notificacion
							.setDestinatario(NotificacionController.ENTIDAD);
				}
			}
		} else {
			notificaciones = new ArrayList<NotificacionBean>();
		}
	}

	/**
	 * Método privado que carga los datos procedentes del buscador cuando se
	 * requiere visualizar el detalle de una notificación.
	 */
	private void loadDataForDetailView() {
		this.notificacionSeleccionada = new NotificacionBean();
		if (this.obtenerFlash().get("indUrgencia") != null) {
			String indUrgencia = (String) this.obtenerFlash()
					.get("indUrgencia");
			if ("SI".equals(indUrgencia)) {
				this.notificacionUrgente = true;
			} else {
				this.notificacionUrgente = false;
			}
		}
		if (this.obtenerFlash().get("indEstado") != null) {
			this.notificacionSeleccionada.setIndEstado((String) this
					.obtenerFlash().get("indEstado"));
		}
		if (this.obtenerFlash().get("fechaVigenteDesde") != null) {
			Date fechaVigenteDesde = (Date) this.obtenerFlash().get(
					"fechaVigenteDesde");
			this.notificacionSeleccionada
					.setFechaVigenteDesde(fechaVigenteDesde);
			if (this.compararFechaConSistema(fechaVigenteDesde)) {
				this.fechaVigenteDesdeDeshabilitada = false;
			}
		}
		if (this.obtenerFlash().get("fechaVigenteHasta") != null) {
			this.notificacionSeleccionada.setFechaVigenteHasta((Date) this
					.obtenerFlash().get("fechaVigenteHasta"));
		}
		if (this.obtenerFlash().get("texto") != null) {
			this.notificacionSeleccionada.setTexto((String) this.obtenerFlash()
					.get("texto"));
		}
		if (this.obtenerFlash().get("centro") != null) {
			String centro = (String) this.obtenerFlash().get("centro");
			if (!centro.trim().isEmpty()) {
				this.notificacionSeleccionada.setCentro(centro);
			} else {
				this.notificacionSeleccionada
						.setDestinatario(NotificacionController.ENTIDAD);
			}
		}
		// TODO Implementar cuando se tenga el nuevo WS de notificaciones.
		// if (this.obtenerFlash().get("destinatario") != null) {
		// this.notificacionSeleccionada.setDestinatario((String) this
		// .obtenerFlash().get("destinatario"));
		// }
		if (this.obtenerFlash().get("clave") != null) {
			this.notificacionSeleccionada.setClave((BigInteger) this
					.obtenerFlash().get("clave"));
		}
	}

	/**
	 * Método que devuelve la URL de la vista destino.
	 * 
	 * @return urlVistaDestino
	 */
	private String obtenerUrlVistaDestino() {
		HttpServletRequest request = (HttpServletRequest) FacesContext
				.getCurrentInstance().getExternalContext().getRequest();
		return request.getRequestURL().toString();
	}

	/**
	 * Método privado que obtiene el objeto Flash proveniente del contexto
	 * externo.
	 * 
	 * @return Flash
	 */
	private Flash obtenerFlash() {
		return FacesContext.getCurrentInstance().getExternalContext()
				.getFlash();
	}

	/**
	 * Método que se ejecuta al seleccionar una fila desde la vista.
	 * 
	 * @param evento
	 */
	public void onRowSelect(SelectEvent event) {
		notificacionSeleccionada = (NotificacionBean) event.getObject();
		panelDetalleDisponible = true;
	}

	/**
	 * Método que redirige al usuario al alta de notificaciones.
	 * 
	 * @return ruta del recurso a mostrar
	 */
	public String inicio() {
		return NavegacionEnum.ALTA_NOTIFICACION.getRuta();
	}

	/**
	 * Método que redirige al usuario a la página de inicio.
	 * 
	 * @return ruta del recurso a mostrar
	 */
	public String redirigirAInicio() {
		return NavegacionEnum.INICIO.getRuta();
	}

	/**
	 * Método que redirige al usuario al buscador genérico o en su defecto al
	 * inicio.
	 * 
	 * @return ruta del recurso a mostrar
	 */
	public String volver() {

		if (navegacionEnumOrigen != null) {
			obtenerFlash().put(ParametrosFlashEnum.RETURN_AND_REFRESH.getParamFlash(), true);
			managedBeanStateRecover.finNavegacion(controladorOrigen);
			return navegacionEnumOrigen.getRuta();
		} else {
			managedBeanStateRecover.finNavegacion(controladorOrigen);
			return NavegacionEnum.INICIO.getRuta();
		}
	}

	/**
	 * Método que redirige al usuario a la página de inicio.
	 * 
	 * @return ruta del recurso a mostrar
	 */
	public String cancelarAltaNotificacion() {
		return redirigirAInicio();
	}

	/**
	 * Método que redirige al usuario a la página de inicio.
	 * 
	 * @return ruta del recurso a mostrar
	 */
	public String cancelarModificacionNotificacion() {
		return volver();
	}

	/**
	 * Método que permite añadir una notificación dirigida a toda la entidad o a
	 * un centro en específico.
	 */
	public void anadirNotificacion() {
		botonAnadirDeshabilitado = true;
		RequestContext.getCurrentInstance().update(":frmAltaNotificaciones");
	}

	/**
	 * Método que invoca al servicio de notificaciones con la opción de alta
	 * (A).
	 */
	public void crearNotificacion() {
		if(validaLongitudCampo()){
			try {
				// Verificamos el destinatario.
				if (NotificacionController.CENTRO.contentEquals(destinatario)) {
					notificacionNueva.setCentro(centroSeleccionado.getClaveFila());
					notificacionNueva
							.setDestinatario(NotificacionController.CENTRO);
				} else {
					notificacionNueva
							.setDestinatario(NotificacionController.ENTIDAD);
				}
				// Verificamos el valor del control de notificacionUrgente.
				if (notificacionUrgente) {
					notificacionNueva.setIndUrgencia("SI");
				} else {
					notificacionNueva.setIndUrgencia("NO");
				}		
				notificacionBackEnd.ejecutarWS("A", null, notificacionNueva);
				// TODO Descomentar línea de abajo para habilitar notificaciones.
				// RequestContext.getCurrentInstance().execute(
				// "enviarNotificacion('" + crearMensajeJSON() + "')");
				RequestContext.getCurrentInstance().execute(
						"PF('dlgOperacionExitosa').show()");
			} catch (NullPointerException | NoControlableException e) {
				RequestContext.getCurrentInstance().execute(
						"PF('dlgOperacionFallida').show()");
			}
		}else{



			RequestContext.getCurrentInstance().execute(
					"PF('dlgLongitudMax').show()");

		}
	}

	/**
	 * Método utilizado para construir un JSON con el mensaje a mostrar a los
	 * usuarios
	 * 
	 * @return String con el mensaje creado
	 */
	private String crearMensajeJSON() {
		StringBuffer msgJson = new StringBuffer();
		msgJson.append("\"destinatario\":\""
				+ notificacionNueva.getDestinatario() + "\",");
		msgJson.append("\"indUrgencia\":\""
				+ notificacionNueva.getIndUrgencia() + "\",");
		msgJson.append("\"texto\":\"" + notificacionNueva.getTexto() + "\",");
		StringToDateConverter converter = new StringToDateConverter();
		msgJson.append("\"fechaVigenteDesde\":\""
				+ converter.convertFrom(notificacionNueva
						.getFechaVigenteDesde()) + "\",");
		msgJson.append("\"fechaVigenteHasta\":\""
				+ converter.convertFrom(notificacionNueva
						.getFechaVigenteHasta()) + "\",");
		DateFormat dateFormatter = new SimpleDateFormat("yyyyMMdd");
		String fechaCreacion = dateFormatter.format(Calendar.getInstance()
				.getTime());
		msgJson.append("\"fechaCreacion\":\"" + fechaCreacion + "\"");
		return msgJson.toString();
	}

	/**
	 * Método que reinicia los valores mostrados en la vista para poder
	 * continuar con el alta de notificaciones.
	 */
	public void reiniciarVista() {
		notificacionNueva = new NotificacionBean();
		centroSeleccionado = new CatalogoBean();
		botonAnadirDeshabilitado = false;
		destinatario = new String();
	}

	/**
	 * Método que invoca al servicio de notificaciones con la opción de
	 * modificación (M).
	 */
	public void modificarNotificacion() {
		if(validaLongitudCampo()){
			try {
				// Extraemos cada una de las cadenas que se encuentren separadas.
				if (notificacionSeleccionada.getCentro() != null
						&& !notificacionSeleccionada.getCentro().trim().isEmpty()) {
					String valoresCentro[] = notificacionSeleccionada.getCentro()
							.trim().split("-");
					notificacionSeleccionada.setCentro(valoresCentro[0].trim());
				}
				// Verificamos el valor del control de notificacionUrgente.
				if (notificacionUrgente) {
					notificacionSeleccionada.setIndUrgencia("SI");
				} else {
					notificacionSeleccionada.setIndUrgencia("NO");
				}
				notificacionBackEnd.ejecutarWS("M", null, notificacionSeleccionada);
				// Verificamos la fecha hasta para actualizar el estado.
				if (compararFechaConSistema(notificacionSeleccionada
						.getFechaVigenteHasta())) {
					notificacionSeleccionada.setIndEstado("SI");
				} else {
					notificacionSeleccionada.setIndEstado("NO");
				}
				RequestContext.getCurrentInstance().execute(
						"PF('dlgOperacionExitosa').show()");
			} catch (NullPointerException | NoControlableException e) {
				RequestContext.getCurrentInstance().execute(
						"PF('dlgOperacionFallida').show()");
			}
		}else{



			RequestContext.getCurrentInstance().execute(
					"PF('dlgLongitudMax').show()");

		}
	}

	/**
	 * Método privado que devuelve 'true' sí la fecha recibida como parámetro es
	 * mayor o igual a la fecha actual.
	 * 
	 * @param fecha
	 * @return indicador booleano
	 */
	private boolean compararFechaConSistema(Date fecha) {
		return DateTimeComparator.getDateOnlyInstance().compare(fecha,
				Calendar.getInstance().getTime()) >= 0;
	}
	
	public String getCurrentDate() {
		return new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime());
	}
	
	/**
	 * Método que valida el rango de caracteres que puede contener el campo 'TEXTO'
	 * 
	 * */
	private Boolean validaLongitudCampo(){
		Boolean valido = false;
		if(notificacionNueva != null
				&& notificacionNueva.getTexto() != null){
			if(notificacionNueva.getTexto().length() > 200){
				
			}else{
				valido = true;
			}
		}else if(notificacionSeleccionada != null
				&& notificacionSeleccionada.getTexto() != null){
			if(notificacionSeleccionada.getTexto().length() > 200){
				
			}else{
				valido = true;
			}
		}
		return valido;
	}

}