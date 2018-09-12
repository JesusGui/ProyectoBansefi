package mx.babel.bansefi.banksystem.oficina.controllers;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

import mx.babel.arq.catalogo.beans.CatalogoBean;
import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.sesion.utils.ContextoUtils;
import mx.babel.bansefi.banksystem.base.enums.CatalogoEnum;
import mx.babel.bansefi.banksystem.base.enums.NavegacionEnum;
import mx.babel.bansefi.banksystem.base.utils.CatalogoUtils;
import mx.babel.bansefi.banksystem.oficina.backends.ContadoresPuestoHostBackEnd;
import mx.babel.bansefi.banksystem.oficina.beans.ContadoresCentroBean;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Clase controlador para manejar el flujo de información con los Contadores de
 * Puesto
 * 
 * @author manuel.nieto
 * 
 */
@ManagedBean(name = "contadoresPuestoController")
@ViewScoped
@Component
@Scope("view")
public class ContadoresPuestoController implements Serializable {

	private static final long serialVersionUID = -6095395648127231804L;
	
	private static final Logger LOGGER = LogManager
			.getLogger(ContadoresPuestoController.class);

	private String centro;
	private String puesto;
	private String terminal;
	private String tipoContador;
	
	/**
	 * Opcion elegida en el autocomplete de centros
	 */
	private CatalogoBean centroSelected;
	
	/**
	 * Variable que indica si se muestra el panel de resultados o no
	 */
	private boolean muestraResultados;
	/**
	 * Variable que cambia el mensaje en boton de busqueda para indicar cuando
	 * se puede realizar una nueva busqueda o cuando el sistema se encuentra
	 * procesando una busqueda
	 */
	private String mensajeBotonBuscar;
	/**
	 * Variable que habilita o deshabilita el boton de busqueda dependiendo de
	 * si esta realizando una busqueda o no
	 */
	private boolean disableBotonBuscar;
	/**
	 * Variable que indica si se muestran o no los botones para borrar
	 * contadores On/Off Esto dependerá de que se esté realizando una consulta
	 * en Local, y que se esté consultando el mismo centro y terminal con el que
	 * se encuentra firmado el usuario
	 */
	private boolean muestraBotonesBorrar;
	
	private boolean borrarContadoresOff;
	
	/**
	 * Variable que indica que la busqueda no origino ningún resultado
	 */
	private boolean busquedaSinResultados;

	/**
	 * Beans
	 */
	private ContadoresCentroBean contadoresPuestoBean;

	/**
	 * Backends
	 */
	@Autowired
	private ContadoresPuestoHostBackEnd contadoresPuestoHostBackEnd;
	
	/**
	 * Contexto de sesión
	 */
	@Autowired
	private ContextoUtils contextoUtils;
	@Autowired
	CatalogoUtils catalogoUtils;

	/**
	 * Funcion que retorna la ruta para acceder a la vista perteneciente a este
	 * controlador
	 * 
	 * @return String, ruta de la pagina
	 */
	public String inicio() {
		return NavegacionEnum.CONTADORES_PUESTO.getRuta();
	}

	/**
	 * Funcion que inicializa el controlador
	 */
	@PostConstruct
	public void init() {
		LOGGER.debug("Inicializando vista Contadores Puesto...");

		this.terminal = getCodigoTerminal();
		this.muestraResultados = false;
		this.mensajeBotonBuscar = "Buscar";
		this.disableBotonBuscar = false;
		this.muestraBotonesBorrar = false;
		//Por ahora solo busquedas en Host
		this.tipoContador = "host";
		
		this.centro = contextoUtils.getSucursal();

	}

	/**
	 * Funcion que de acuerdo al evento de keyup en los inputs de centro y
	 * puesto, imprime el codigo de la terminal en el formulario.
	 */
	public void actualizarTerminal() {
		LOGGER.debug("Contadores de Puesto: actualizar terminal");
		
		if (this.centro != null && this.puesto != null) {
			this.terminal = getCodigoTerminal() + centro + puesto;
//			FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("formaContadores\\\\:terminal");
//			RequestContext.getCurrentInstance().update("formaContadores\\\\:terminal");
		}

	}
	
	/**
	 * Funcion que obtiene el valor de la terminal de un catalogo
	 * @return
	 */
	public String getCodigoTerminal(){		
		CatalogoBean catalogoTerminal = catalogoUtils.getCatalogoBean(CatalogoEnum.TP_ENTIDAD_CR, contextoUtils.getEntidad());
		//TODO: pendiente saber exactamente que valor del catalogo retornar
		if(catalogoTerminal != null){
			return catalogoTerminal.getDescripcionC();
		}else{
			return null;
		}		
	}

	/**
	 * Metodo que ejecuta la busqueda de los contadores de puesto al pulsar el
	 * boton de busqueda
	 */
	public void buscar() {
		LOGGER.debug("Contadores de Puesto: Buscar");
		this.mensajeBotonBuscar = "Buscando...";
		this.disableBotonBuscar = true;
		this.muestraResultados = true;
		this.muestraBotonesBorrar = false;
		this.busquedaSinResultados = false;

		// Verificar la terminal
		if (this.terminal.length() != 8) {
			if (this.centro != null && this.puesto != null) {
				this.terminal = getCodigoTerminal() + this.centro
						+ this.puesto;
			} else {
				addMessage("Error",
						"Ocurrió un error. Por favor ingrese de nuevo el Centro y el Puesto.");
				
				return;
			}
		}

		contadoresPuestoBean = new ContadoresCentroBean();
		contadoresPuestoBean.setCodigoCentro(this.centro);
		contadoresPuestoBean.setCodigoEntidad(this.contextoUtils.getEntidad());
		contadoresPuestoBean.setCodigoTerminal(this.terminal);
		contadoresPuestoBean.setCodigoMoneda("MXN");

		if (("local").equals(this.tipoContador)) {
			LOGGER.debug("Contadores de Puesto: Buscar -> Tipo Local, TRN Pendiente");
			/**
			 * TRN Pendiente, hacer backend con datos dummy
			 */
//			contadoresPuestoBean = contadoresPuestoLocalBackEnd
//					.ejecutarTRN(contadoresPuestoBean);
//			validaMostrarBotonesBorrado();

			LOGGER.debug("Contadores de Puesto: Buscar -> Tipo LOCAL");
		} else if (("host").equals(this.tipoContador)) {
			// Llama a TRN TR_TN_CONT_HOST_CNS_TRN
			LOGGER.debug("Contadores de Puesto: Buscar -> Tipo HOST");
			try {
				contadoresPuestoBean = contadoresPuestoHostBackEnd
						.ejecutarTRN(contadoresPuestoBean);
			} catch (NoControlableException | ControlableException ex) {
				LOGGER.debug("Error al consultar TRN, los atributos pudieron ser incorrectos, o puede que no esté funcionando.");
				this.muestraResultados = false;
//				addMessage("La búsqueda no originó ningún resultado",
//						"La busqueda no originó ningun resultado. Verifique sus datos.");
				this.busquedaSinResultados = true;

			}
		}

		this.mensajeBotonBuscar = "Buscar";
		this.disableBotonBuscar = false;
	}

	/**
	 * Funcion que borra los contadores en Off, en Local
	 */
	public void borrarContadoresOff() {
//		LOGGER.debug("Contadores de Puesto: Borrar contadores off");
//
//		contadoresPuestoBean = borrarContadoresPuestoOffLocalBackEnd
//				.ejecutarTRN(contadoresPuestoBean);
//
//		addMessage("Contadores Off borrados correctamente.", "Contadores Off borrados correctamente.");
	}

	/**
	 * Funcion que borra los contadores en On, en Local
	 */
	public void borrarContadoresOn() {
		LOGGER.debug("Contadores de Puesto: Borrar contadores on");

//		contadoresPuestoBean = borrarContadoresPuestoOnLocalBackEnd
//				.ejecutarTRN(contadoresPuestoBean);

		addMessage("Contadores On borrados correctamente.", "Contadores On borrados correctamente.");
	}

	/**
	 * Funcion que valida si se pueden mostrar los botones de boorado de
	 * contadores
	 */
	public void validaMostrarBotonesBorrado() {
		LOGGER.debug("Contadores de Puesto: validaMostrarBotonesBorrado()");
		LOGGER.debug("Terminal en contextoUtils: "
				+ contextoUtils.getTerminal());
		LOGGER.debug("Terminal en controlador: " + this.terminal);

		if (contextoUtils.getTerminal().equals(this.terminal)) {
			LOGGER.debug("Se mostraran los botones para borrar");
			this.muestraBotonesBorrar = true;
		} else {
			LOGGER.debug("No se mostraran los botones para borrar");
			this.muestraBotonesBorrar = false;
		}
	}

	/**
	 * Agregar mensaje para ventana de confirmación
	 * 
	 * @param summary
	 * @param detail
	 */
	public void addMessage(String summary, String detail) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
				summary, detail);
		FacesContext.getCurrentInstance().addMessage(null, message);
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
	 * Se encarga de obtener el flash.
	 * 
	 * @return Flash con los datos de la pagina
	 */
	public Flash obtieneFlash() {
		return FacesContext.getCurrentInstance().getExternalContext()
				.getFlash();
	}

//	/**
//	 * Función para adicionar alertas globales en la vista
//	 * 
//	 * @param severity
//	 *            Severidad de la alerta.
//	 * @param title
//	 *            Titulo de la alerta.
//	 * @param message
//	 *            Mensaje desplegado en la alerta.
//	 */
//	private void addMessage(FacesMessage.Severity severity, String title,
//			String message) {
//		FacesMessage facesMessage = new FacesMessage(severity, title, message);
//		FacesContext.getCurrentInstance().addMessage("contadoresCentro",
//				facesMessage);
//	}

	/**
	 * Funcion que obtiene el objeto de la instancia ContadoresCentroBean
	 * @return ContadoresCentroBean
	 */
	public ContadoresCentroBean getContadoresPuestoBean() {
		return contadoresPuestoBean;
	}

	public void setContadoresPuestoBean(
			ContadoresCentroBean contadoresPuestoBean) {
		this.contadoresPuestoBean = contadoresPuestoBean;
	}

	public String getCentro() {
		return centro;
	}

	public void setCentro(String centro) {
		this.centro = centro;
	}

	public String getPuesto() {
		return puesto;
	}

	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}

	public String getTerminal() {
		return terminal;
	}

	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}

	public boolean isMuestraResultados() {
		return muestraResultados;
	}

	public void setMuestraResultados(boolean muestraResultados) {
		this.muestraResultados = muestraResultados;
	}

	public String getTipoContador() {
		return tipoContador;
	}

	public void setTipoContador(String tipoContador) {
		this.tipoContador = tipoContador;
	}

	public String getMensajeBotonBuscar() {
		return mensajeBotonBuscar;
	}

	public void setMensajeBotonBuscar(String mensajeBotonBuscar) {
		this.mensajeBotonBuscar = mensajeBotonBuscar;
	}

	public boolean isDisableBotonBuscar() {
		return disableBotonBuscar;
	}

	public void setDisableBotonBuscar(boolean disableBotonBuscar) {
		this.disableBotonBuscar = disableBotonBuscar;
	}

	public boolean isMuestraBotonesBorrar() {
		return muestraBotonesBorrar;
	}

	public void setMuestraBotonesBorrar(boolean muestraBotonesBorrar) {
		this.muestraBotonesBorrar = muestraBotonesBorrar;
	}

	public boolean isBorrarContadoresOff() {
		return borrarContadoresOff;
	}

	public void setBorrarContadoresOff(boolean borrarContadoresOff) {
		this.borrarContadoresOff = borrarContadoresOff;
	}

	public CatalogoBean getCentroSelected() {
		return centroSelected;
	}

	public void setCentroSelected(CatalogoBean centroSelected) {
		this.centroSelected = centroSelected;
	}

	public boolean isBusquedaSinResultados() {
		return busquedaSinResultados;
	}

	public void setBusquedaSinResultados(boolean busquedaSinResultados) {
		this.busquedaSinResultados = busquedaSinResultados;
	}
	
	
	

}
