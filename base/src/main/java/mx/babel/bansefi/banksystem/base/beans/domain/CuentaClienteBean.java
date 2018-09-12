package mx.babel.bansefi.banksystem.base.beans.domain;

import java.io.Serializable;
import java.math.BigDecimal;

public class CuentaClienteBean implements Serializable{

	private static final long serialVersionUID = -1765584380047469223L;
	
	private CuentaBean cuenta;
	private Integer idCliente;
	private String relacion;
	private BigDecimal saldo;
	/**
	 * @return Atributo numeroCuenta
	 */
	public CuentaBean getCuenta() {
		return cuenta;
	}
	/**
	 * @param numeroCuenta Atributo numeroCuenta a definir
	 */
	public void setCuenta(CuentaBean cuenta) {
		this.cuenta = cuenta;
	}
	/**
	 * @return Atributo idCliente
	 */
	public Integer getIdCliente() {
		return idCliente;
	}
	/**
	 * @param idCliente Atributo idCliente a definir
	 */
	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}
	/**
	 * @return Atributo relacion
	 */
	public String getRelacion() {
		return relacion;
	}
	/**
	 * @param relacion Atributo relacion a definir
	 */
	public void setRelacion(String relacion) {
		this.relacion = relacion;
	}
	/**
	 * @return Atributo saldo
	 */
	public BigDecimal getSaldo() {
		return saldo;
	}
	/**
	 * @param saldo Atributo saldo a definir
	 */
	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}
	@Override
	public String toString() {
		return "CuentaClienteBean [cuenta=" + cuenta + ", idCliente="
				+ idCliente + ", relacion=" + relacion + ", saldo=" + saldo
				+ "]";
	}
	
	
}
