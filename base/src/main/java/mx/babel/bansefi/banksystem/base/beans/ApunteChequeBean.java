package mx.babel.bansefi.banksystem.base.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ApunteChequeBean implements Serializable{

	private static final long serialVersionUID = -5587001700127566009L;
	
	private long numeroCuenta;
	private long numeroCuentaCargo;
	private String numCheque;
	private String conformado;
	private String titular;
	private String talonario;
	private String tipoTalonario;
	private String disposicion;
	private String centro;
	private String estado;
	private String situacionPago;
	private String codidcheq;
	private String codcjchqpg;
	private BigDecimal importeCheque;
	private BigDecimal importePendiente;
	private Date fechaCaducidad;
	/**
	 * @return Atributo numeroCuenta
	 */
	public long getNumeroCuenta() {
		return numeroCuenta;
	}
	/**
	 * @param numeroCuenta Atributo numeroCuenta a definir
	 */
	public void setNumeroCuenta(long numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}
	/**
	 * @return Atributo numeroCuentaCargo
	 */
	public long getNumeroCuentaCargo() {
		return numeroCuentaCargo;
	}
	/**
	 * @param numeroCuentaCargo Atributo numeroCuentaCargo a definir
	 */
	public void setNumeroCuentaCargo(long numeroCuentaCargo) {
		this.numeroCuentaCargo = numeroCuentaCargo;
	}
	/**
	 * @return Atributo numCheque
	 */
	public String getNumCheque() {
		return numCheque;
	}
	/**
	 * @param numCheque Atributo numCheque a definir
	 */
	public void setNumCheque(String numCheque) {
		this.numCheque = numCheque;
	}
	/**
	 * @return Atributo conformado
	 */
	public String getConformado() {
		return conformado;
	}
	/**
	 * @param conformado Atributo conformado a definir
	 */
	public void setConformado(String conformado) {
		this.conformado = conformado;
	}
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
	 * @return Atributo talonario
	 */
	public String getTalonario() {
		return talonario;
	}
	/**
	 * @param talonario Atributo talonario a definir
	 */
	public void setTalonario(String talonario) {
		this.talonario = talonario;
	}
	/**
	 * @return Atributo tipoTalonario
	 */
	public String getTipoTalonario() {
		return tipoTalonario;
	}
	/**
	 * @param tipoTalonario Atributo tipoTalonario a definir
	 */
	public void setTipoTalonario(String tipoTalonario) {
		this.tipoTalonario = tipoTalonario;
	}
	/**
	 * @return Atributo disposicion
	 */
	public String getDisposicion() {
		return disposicion;
	}
	/**
	 * @param disposicion Atributo disposicion a definir
	 */
	public void setDisposicion(String disposicion) {
		this.disposicion = disposicion;
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
	 * @return Atributo estado
	 */
	public String getEstado() {
		return estado;
	}
	/**
	 * @param estado Atributo estado a definir
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}
	/**
	 * @return Atributo situacionPago
	 */
	public String getSituacionPago() {
		return situacionPago;
	}
	/**
	 * @param situacionPago Atributo situacionPago a definir
	 */
	public void setSituacionPago(String situacionPago) {
		this.situacionPago = situacionPago;
	}
	/**
	 * @return Atributo codidcheq
	 */
	public String getCodidcheq() {
		return codidcheq;
	}
	/**
	 * @param codidcheq Atributo codidcheq a definir
	 */
	public void setCodidcheq(String codidcheq) {
		this.codidcheq = codidcheq;
	}
	/**
	 * @return Atributo codcjchqpg
	 */
	public String getCodcjchqpg() {
		return codcjchqpg;
	}
	/**
	 * @param codcjchqpg Atributo codcjchqpg a definir
	 */
	public void setCodcjchqpg(String codcjchqpg) {
		this.codcjchqpg = codcjchqpg;
	}
	/**
	 * @return Atributo importeCheque
	 */
	public BigDecimal getImporteCheque() {
		return importeCheque;
	}
	/**
	 * @param importeCheque Atributo importeCheque a definir
	 */
	public void setImporteCheque(BigDecimal importeCheque) {
		this.importeCheque = importeCheque;
	}
	/**
	 * @return Atributo importePendiente
	 */
	public BigDecimal getImportePendiente() {
		return importePendiente;
	}
	/**
	 * @param importePendiente Atributo importePendiente a definir
	 */
	public void setImportePendiente(BigDecimal importePendiente) {
		this.importePendiente = importePendiente;
	}
	/**
	 * @return Atributo fechaCaducidad
	 */
	public Date getFechaCaducidad() {
		return fechaCaducidad;
	}
	/**
	 * @param fechaCaducidad Atributo fechaCaducidad a definir
	 */
	public void setFechaCaducidad(Date fechaCaducidad) {
		this.fechaCaducidad = fechaCaducidad;
	}
	
}
