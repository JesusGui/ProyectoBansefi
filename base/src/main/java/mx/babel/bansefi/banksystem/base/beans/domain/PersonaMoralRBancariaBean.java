package mx.babel.bansefi.banksystem.base.beans.domain;

import java.io.Serializable;

import mx.babel.bansefi.banksystem.base.enums.EstadoListadosEnum;

public class PersonaMoralRBancariaBean implements Serializable {

	private static final long serialVersionUID = -531202280509260533L;

	private int idInterno;
	private String banco;
	private String numCuenta;
	private String tipoCuenta;
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
	 * Método que devuelve el nombre del banco.
	 * 
	 * @return banco
	 */
	public String getBanco() {
		return banco;
	}

	/**
	 * Método que establece el nombre del banco.
	 * 
	 * @param banco
	 */
	public void setBanco(String banco) {
		this.banco = banco;
	}

	/**
	 * Método que devuelve el número de cuenta.
	 * 
	 * @return numCuenta
	 */
	public String getNumCuenta() {
		return numCuenta;
	}

	/**
	 * Método que establece el número de cuenta.
	 * 
	 * @param numCuenta
	 */
	public void setNumCuenta(String numCuenta) {
		this.numCuenta = numCuenta;
	}

	/**
	 * Método que devuelve el tipo de cuenta.
	 * 
	 * @return tipoCuenta
	 */
	public String getTipoCuenta() {
		return tipoCuenta;
	}

	/**
	 * Método que establece el tipo de cuenta.
	 * 
	 * @param tipoCuenta
	 */
	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
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
	 * PersonaMoralRBancariaBean serializado mediante JSON.
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