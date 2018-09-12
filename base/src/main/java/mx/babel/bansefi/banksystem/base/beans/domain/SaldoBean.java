package mx.babel.bansefi.banksystem.base.beans.domain;

import java.math.BigDecimal;

import mx.babel.bansefi.banksystem.base.backends.RespuestaGenerica;

public class SaldoBean extends RespuestaGenerica{
	private static final long serialVersionUID = 1L;
	private String titular;
	private BigDecimal saldoContable;
	private BigDecimal totalRetenido;
	private BigDecimal totalAutorizado;
	private BigDecimal disponible;
	private String centro;
	private String dc;
	private String moneda;
	
	/**
	 * @return Atributo titular
	 */
	public String getTitular() {
		return titular;
	}

	/**
	 * @param titular Atributo titular a definir
	 */
	public void setTitular(String titular) {
		this.titular = titular;
	}

	/**
	 * @return Atributo saldoContable
	 */
	public BigDecimal getSaldoContable() {
		return saldoContable;
	}

	/**
	 * @param saldoContable Atributo saldoContable a definir
	 */
	public void setSaldoContable(BigDecimal saldoContable) {
		this.saldoContable = saldoContable;
	}

	/**
	 * @return Atributo totalRetenido
	 */
	public BigDecimal getTotalRetenido() {
		return totalRetenido;
	}

	/**
	 * @param totalRetenido Atributo totalRetenido a definir
	 */
	public void setTotalRetenido(BigDecimal totalRetenido) {
		this.totalRetenido = totalRetenido;
	}

	/**
	 * @return Atributo totalAutorizado
	 */
	public BigDecimal getTotalAutorizado() {
		return totalAutorizado;
	}

	/**
	 * @param totalAutorizado Atributo totalAutorizado a definir
	 */
	public void setTotalAutorizado(BigDecimal totalAutorizado) {
		this.totalAutorizado = totalAutorizado;
	}

	/**
	 * @return Atributo disponible
	 */
	public BigDecimal getDisponible() {
		return disponible;
	}

	/**
	 * @param disponible Atributo disponible a definir
	 */
	public void setDisponible(BigDecimal disponible) {
		this.disponible = disponible;
	}

	/**
	 * @return Atributo centro
	 */
	public String getCentro() {
		return centro;
	}

	/**
	 * @param centro Atributo centro a definir
	 */
	public void setCentro(String centro) {
		this.centro = centro;
	}

	/**
	 * @return Atributo dc
	 */
	public String getDc() {
		return dc;
	}

	/**
	 * @param dc Atributo dc a definir
	 */
	public void setDc(String dc) {
		this.dc = dc;
	}

	/**
	 * @return Atributo moneda
	 */
	public String getMoneda() {
		return moneda;
	}

	/**
	 * @param moneda Atributo moneda a definir
	 */
	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

}
