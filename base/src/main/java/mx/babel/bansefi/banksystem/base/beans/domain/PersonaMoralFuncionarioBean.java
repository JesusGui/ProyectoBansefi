package mx.babel.bansefi.banksystem.base.beans.domain;

import java.io.Serializable;

import mx.babel.bansefi.banksystem.base.enums.EstadoListadosEnum;

public class PersonaMoralFuncionarioBean implements Serializable {

	private static final long serialVersionUID = -8912851766596622426L;

	private int idInterno;
	private String nombre;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private String rfc;
	private String puesto;
	private EstadoListadosEnum estado;
	private String respaldo;

	/**
	 * Método que devuelve el ID interno o número de secuencia del funcionario.
	 * 
	 * @return idInterno
	 */
	public int getIdInterno() {
		return idInterno;
	}

	/**
	 * Método que establece el ID interno o número de secuencia del funcionario.
	 * 
	 * @param idInterno
	 */
	public void setIdInterno(int idInterno) {
		this.idInterno = idInterno;
	}

	/**
	 * Método que devuelve el nombre del funcionario.
	 * 
	 * @return nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Método que establece el nombre del funcionario.
	 * 
	 * @param nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Método que devuelve el apellido paterno del funcionario.
	 * 
	 * @return apellidoPaterno
	 */
	public String getApellidoPaterno() {
		return apellidoPaterno;
	}

	/**
	 * Método que establece el apellido paterno del funcionario.
	 * 
	 * @param apellidoPaterno
	 */
	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}

	/**
	 * Método que devuelve el apellido materno del funcionario.
	 * 
	 * @return apellidoMaterno
	 */
	public String getApellidoMaterno() {
		return apellidoMaterno;
	}

	/**
	 * Método que establece el apellido materno del funcionario.
	 * 
	 * @param apellidoMaterno
	 */
	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}

	/**
	 * Método que devuelve el RFC del funcionario.
	 * 
	 * @return rfc
	 */
	public String getRfc() {
		return rfc;
	}

	/**
	 * Método que establece el RFC del funcionario.
	 * 
	 * @param rfc
	 */
	public void setRfc(String rfc) {
		this.rfc = rfc;
	}

	/**
	 * Método que devuelve el puesto del funcionario.
	 * 
	 * @return puesto
	 */
	public String getPuesto() {
		return puesto;
	}

	/**
	 * Método que establece el puesto del funcionario.
	 * 
	 * @param puesto
	 */
	public void setPuesto(String puesto) {
		this.puesto = puesto;
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
	 * PersonaMoralFuncionarioBean serializado mediante JSON.
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