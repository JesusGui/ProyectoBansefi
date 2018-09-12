package mx.babel.arq.sesion.contexto.beans;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

/**
 * Clase que define los atributos de los notificaciones que serán mostrados a
 * los usuarios de la aplicación.
 * 
 * @author omar.marquez
 * 
 */
public class NotificacionBean implements Serializable {

	private static final long serialVersionUID = 696452938493521294L;

	private BigInteger clave;
	private String indUrgencia;
	private String indEstado;
	private String entidad;
	private String region;
	private String centro;
	private String terminal;
	private String destinatario;
	private String texto;
	private Date fechaVigenteDesde;
	private Date fechaVigenteHasta;
	private String usuarioCreacion;
	private Date fechaCreacion;

	/**
	 * Constructor.
	 */
	public NotificacionBean() {
		super();
	}

	/**
	 * Método que devuelve la clave de la notificación.
	 * 
	 * @return clave
	 */
	public BigInteger getClave() {
		return clave;
	}

	/**
	 * Método que establece la clave de la notificación.
	 * 
	 * @param clave
	 */
	public void setClave(BigInteger clave) {
		this.clave = clave;
	}

	/**
	 * Método que devuelve 'SI' sí la notificación es urgente o 'NO' sí es
	 * normal.
	 * 
	 * @return indUrgencia
	 */
	public String getIndUrgencia() {
		return indUrgencia;
	}

	/**
	 * Método que establece la urgencia de la notificación.
	 * 
	 * @param indUrgencia
	 */
	public void setIndUrgencia(String indUrgencia) {
		this.indUrgencia = indUrgencia;
	}

	/**
	 * Método que devuelve 'SI' sí la notificación se encuentra activa o 'NO' sí
	 * ha expirado.
	 * 
	 * @return indEstado
	 */
	public String getIndEstado() {
		return indEstado;
	}

	/**
	 * Método que establece el estado de la notificación.
	 * 
	 * @param indEstado
	 */
	public void setIndEstado(String indEstado) {
		this.indEstado = indEstado;
	}

	/**
	 * Método que devuelve la entidad de la notificación.
	 * 
	 * @return entidad
	 */
	public String getEntidad() {
		return entidad;
	}

	/**
	 * Método que establece la entidad de la notificación.
	 * 
	 * @param entidad
	 */
	public void setEntidad(String entidad) {
		this.entidad = entidad;
	}

	/**
	 * Método que devuelve la región de la notificación.
	 * 
	 * @return region
	 */
	public String getRegion() {
		return region;
	}

	/**
	 * Método que establece la región de la notificación.
	 * 
	 * @param region
	 */
	public void setRegion(String region) {
		this.region = region;
	}

	/**
	 * Método que devuelve el centro de la notificación.
	 * 
	 * @return centro
	 */
	public String getCentro() {
		return centro;
	}

	/**
	 * Método que establece el centro de la notificación.
	 * 
	 * @param centro
	 */
	public void setCentro(String centro) {
		this.centro = centro;
	}

	/**
	 * Método que devuelve la terminal de la notificación.
	 * 
	 * @return terminal
	 */
	public String getTerminal() {
		return terminal;
	}

	/**
	 * Método que establece la terminal de la notificación.
	 * 
	 * @param terminal
	 */
	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}

	/**
	 * Método que devuelve el ID del usuario al que va dirigida la notificación.
	 * 
	 * @return destinatario
	 */
	public String getDestinatario() {
		return destinatario;
	}

	/**
	 * Método que establece el ID del usuario al que va dirigida la
	 * notificación.
	 * 
	 * @param destinatario
	 */
	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}

	/**
	 * Método que devuelve el detalle (texto de la notificación).
	 * 
	 * @return texto
	 */
	public String getTexto() {
		return texto;
	}

	/**
	 * Método que establece el detalle (texto de la notificiación).
	 * 
	 * @param texto
	 */
	public void setTexto(String texto) {
		this.texto = texto;
	}

	/**
	 * Método que devuelve la fecha de inicio de la notificación.
	 * 
	 * @return fechaVigenteDesde
	 */
	public Date getFechaVigenteDesde() {
		return fechaVigenteDesde;
	}

	/**
	 * Método que establece la fecha de inicio de la notificación.
	 * 
	 * @param fechaVigenteDesde
	 */
	public void setFechaVigenteDesde(Date fechaVigenteDesde) {
		this.fechaVigenteDesde = fechaVigenteDesde;
	}

	/**
	 * Método que devuelve la fecha de término de la notificación.
	 * 
	 * @return fechaVigenteHasta
	 */
	public Date getFechaVigenteHasta() {
		return fechaVigenteHasta;
	}

	/**
	 * Método que establece la fecha de término de la notificación.
	 * 
	 * @param fechaVigenteHasta
	 */
	public void setFechaVigenteHasta(Date fechaVigenteHasta) {
		this.fechaVigenteHasta = fechaVigenteHasta;
	}

	/**
	 * Método que devuelve el ID del usuario autor de la notificación.
	 * 
	 * @return usuarioCreación.
	 */
	public String getUsuarioCreacion() {
		return usuarioCreacion;
	}

	/**
	 * Método que establece el ID del usuario autor de la notificación.
	 * 
	 * @param usuarioCreacion
	 */
	public void setUsuarioCreacion(String usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}

	/**
	 * Método que devuelve la fecha de creación de la notificación.
	 * 
	 * @return fechaCreacion
	 */
	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	/**
	 * Método que establece la fecha de creación de la notificación.
	 * 
	 * @param fechaCreacion
	 */
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

}