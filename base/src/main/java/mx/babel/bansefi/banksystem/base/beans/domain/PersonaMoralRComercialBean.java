package mx.babel.bansefi.banksystem.base.beans.domain;

import java.io.Serializable;

import mx.babel.bansefi.banksystem.base.enums.EstadoListadosEnum;

public class PersonaMoralRComercialBean implements Serializable {

	private static final long serialVersionUID = 5442046728000628612L;

	private int idInterno;
	private String nombre;
	private String giro;
	private String domicilio;
	private String telefono;
	private String relacion;
	private EstadoListadosEnum estado;
	private String respaldo;

	/**
	 * Método que devuelve el ID interno o número de secuencia de la referencia.
	 * 
	 * @return idInterno
	 */
	public int getIdInterno() {
		return idInterno;
	}

	/**
	 * Método que establece el ID interno o número de secuencia de la
	 * referencia.
	 * 
	 * @param idInterno
	 */
	public void setIdInterno(int idInterno) {
		this.idInterno = idInterno;
	}

	/**
	 * Método que devuelve el nombre de la referencia.
	 * 
	 * @return nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Método que establece el nombre de la referencia.
	 * 
	 * @param nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Método que devuelve el giro de la referencia.
	 * 
	 * @return giro
	 */
	public String getGiro() {
		return giro;
	}

	/**
	 * Método que establece el giro de la referencia.
	 * 
	 * @param giro
	 */
	public void setGiro(String giro) {
		this.giro = giro;
	}

	/**
	 * Método que devuelve el domicilio de la referencia.
	 * 
	 * @return domicilio
	 */
	public String getDomicilio() {
		return domicilio;
	}

	/**
	 * Método que establece el domicilio de la referencia.
	 * 
	 * @param domicilio
	 */
	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	/**
	 * Método que devuelve el teléfono de la referencia.
	 * 
	 * @return telefono
	 */
	public String getTelefono() {
		return telefono;
	}

	/**
	 * Método que establece el teléfono de la referencia.
	 * 
	 * @param telefono
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	/**
	 * Método que devuelve el campo con la relación.
	 * 
	 * @return relacion
	 */
	public String getRelacion() {
		return relacion;
	}

	/**
	 * Método que establece el campo con la relación.
	 * 
	 * @param relacion
	 */
	public void setRelacion(String relacion) {
		this.relacion = relacion;
	}

	/**
	 * Método que devuelve el estado del bean para tratar los listados y los
	 * diferentes estilos. Los valores del enumerado pueden ser: ACTIVO,
	 * INACTIVO, MODIFICADO y ELIMINADO.
	 * 
	 * @return estado
	 */
	public EstadoListadosEnum getEstado() {
		return estado;
	}

	/**
	 * Método que establece el estado del bean para poder tratar los listados y
	 * los diferentes estilos.
	 * 
	 * @param estado
	 */
	public void setEstado(EstadoListadosEnum estado) {
		this.estado = estado;
	}

	/**
	 * Método que devuelve una cadena que representa un objeto
	 * PersonaMoralRComercialBean serializado mediante JSON.
	 * 
	 * @return respaldo
	 */
	public String getRespaldo() {
		return respaldo;
	}

	/**
	 * Método que establece en una cadena de texto, el resultado de serializar
	 * un objeto mediante JSON.
	 * 
	 * @param respaldo
	 */
	public void setRespaldo(String respaldo) {
		this.respaldo = respaldo;
	}

}