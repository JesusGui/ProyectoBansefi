package mx.babel.bansefi.banksystem.base.beans;

/**
 * Clase que define los atributos que tendrá cada tarea que el usuario vaya
 * dejando pendiente.
 * 
 * @author omar.marquez
 * 
 */
public class TareaPendienteBean {

	private String clave;
	private String hora;
	private String nombre;

	/**
	 * Constructor.
	 * 
	 * @param clave
	 * @param hora
	 * @param nombre
	 */
	public TareaPendienteBean(String clave, String hora, String nombre) {
		this.clave = clave;
		this.hora = hora;
		this.nombre = nombre;
	}

	/**
	 * Método que devuelve la clave de la tarea.
	 * 
	 * @return clave
	 */
	public String getClave() {
		return clave;
	}

	/**
	 * Método que establece la clave de la tarea.
	 * 
	 * @param clave
	 */
	public void setClave(String clave) {
		this.clave = clave;
	}

	/**
	 * Método que devuelve una cadena de texto con la hora formateada.
	 * 
	 * @return hora en formato hh:mm am/pm
	 */
	public String getHora() {
		return hora;
	}

	/**
	 * Método que establece la hora en formato hh:mm am/pm.
	 * 
	 * @param hora
	 */
	public void setHora(String hora) {
		this.hora = hora;
	}

	/**
	 * Método que devuelve el nombre / descripción de la tarea.
	 * 
	 * @return nombre de la tarea
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Método que establece el nombre que tendrá la tarea.
	 * 
	 * @param nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}