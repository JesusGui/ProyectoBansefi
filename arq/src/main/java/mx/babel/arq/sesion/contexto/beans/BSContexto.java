package mx.babel.arq.sesion.contexto.beans;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Component;

/**
 * Clase que contiene la informacion almacenada en contexto.
 * 
 * @author joseluis.pena
 * 
 */
@Component
public class BSContexto implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 556227671465566982L;

	private UsuarioBean usuario;
	private List<MenuBean> operPermitidas;
	private List<SubmenuBean> operFrecuentes;
	private Map<String, Object> configuracion;
	private List<NotificacionBean> notificaciones;
	private List<String> opBusquedas;
	private String mensajeJSON;
	private String banksystemVersion;

	/**
	 * Constructor.
	 */
	public BSContexto() {
		super();
	}

	/**
	 * Método que devuelve un objeto tipo UsuarioBean.
	 * 
	 * @return usuario
	 */
	public UsuarioBean getUsuario() {
		return usuario;
	}

	/**
	 * Método que establece un objeto tipo UsuarioBean.
	 * 
	 * @param usuario
	 */
	public void setUsuario(final UsuarioBean usuario) {
		this.usuario = usuario;
	}

	/**
	 * Método que devuelve una lista de operativas permitidas.
	 * 
	 * @return operPermitidas
	 */
	public List<MenuBean> getOperPermitidas() {
		return operPermitidas;
	}

	/**
	 * Método que establece una lista de operativas permitidas.
	 * 
	 * @param operPermitidas
	 */
	public void setOperPermitidas(final List<MenuBean> operPermitidas) {
		this.operPermitidas = operPermitidas;
	}

	/**
	 * Método que devuelve una lista de operativas frecuentes.
	 * 
	 * @return operFrecuentes
	 */
	public List<SubmenuBean> getOperFrecuentes() {
		return operFrecuentes;
	}

	/**
	 * Método que establece una lista de operativas frecuentes.
	 * 
	 * @param operFrecuentes
	 */
	public void setOperFrecuentes(final List<SubmenuBean> operFrecuentes) {
		this.operFrecuentes = operFrecuentes;
	}

	/**
	 * Método que devuelve un mapa de configuración.
	 * 
	 * @return configuracion
	 */
	public Map<String, Object> getConfiguracion() {
		return configuracion;
	}

	/**
	 * Método que establece un mapa de configuración.
	 * 
	 * @param configuracion
	 */
	public void setConfiguracion(final Map<String, Object> configuracion) {
		this.configuracion = new HashMap<String, Object>();
		if (null != configuracion) {
			final Set<String> keys = configuracion.keySet();
			for (final String key : keys) {
				this.configuracion.put(key, configuracion.get(key));
			}
		}
	}

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
	public void setNotificaciones(final List<NotificacionBean> notificaciones) {
		this.notificaciones = notificaciones;
	}

	/**
	 * Método que consulta las operaciones de busquedas permitidas para el
	 * buscador
	 * 
	 * @return
	 */
	public List<String> getOpBusquedas() {
		return opBusquedas;
	}

	/**
	 * Método que establece las opciones de busqueda para el buscador.
	 * 
	 * @param opBusquedas
	 */
	public void setOpBusquedas(final List<String> opBusquedas) {
		this.opBusquedas = opBusquedas;
	}

	/**
	 * Método que devuelve una cadena de texto relacionada con la utilidad de
	 * notificaciones intrusivas.
	 * 
	 * @return mensajeJSON
	 */
	public String getMensajeJSON() {
		return mensajeJSON;
	}

	/**
	 * Método que establece una cadena de texto para la utilidad de
	 * notificaciones intrusivas.
	 * 
	 * @param mensajeJSON
	 */
	public void setMensajeJSON(final String mensajeJSON) {
		this.mensajeJSON = mensajeJSON;
	}

	/**
	 * Método que devuelve una cadena de texto con la version del banksystem.
	 *
	 * @return mensajeJSON
	 */
	public String getBanksystemVersion() {
		return banksystemVersion;
	}

	/**
	 * Método que establece una cadena de texto para la version del banksystem.
	 *
	 * @param banksystemVersion
	 */
	public void setBanksystemVersion(String banksystemVersion) {
		this.banksystemVersion = banksystemVersion;
	}
	
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
}