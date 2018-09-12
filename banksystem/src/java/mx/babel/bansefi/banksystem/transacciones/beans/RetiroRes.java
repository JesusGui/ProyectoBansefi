package mx.babel.bansefi.banksystem.transacciones.beans;

import java.math.BigDecimal;
import java.math.BigInteger;

import mx.babel.bansefi.banksystem.base.backends.RespuestaGenerica;

/**
 * Retiro Response.
 * @author alejandro.perez
 *
 */

public class RetiroRes extends RespuestaGenerica {

   private static final long serialVersionUID = 1L;

   private BigInteger acuerdo;

   private BigDecimal importe;

   private String estatus;

   private String movimiento;

   private String terminal;

   private String fechaoprcn;

   private String horaoprcn;

   private String centro;

   private String plaza;

   private String digito;

   private String titular;

   private BigInteger secuencia;
   
   private BigDecimal saldo;

   /**
    * Constructor.
    */
   public RetiroRes() {
	   super();
   }

   /**
    * Response de Retiro.
    * @param acuerdo acuerdo
    * @param importe - importe
    * @param estatus - estatus
    * @param movimiento - movimiento
    * @param terminal - terminal
    * @param fechaoprcn - fecha operacion
    * @param horaoprcn - hora operacion
    * @param centro - centro
    * @param plaza - plaza
    * @param digito - digito
    * @param titular - titular
    * @param secuencia - secuencia
    * @param saldo - saldo
    */
   public RetiroRes(BigInteger acuerdo, BigDecimal importe, String estatus,
			String movimiento, String terminal, String fechaoprcn,
			String horaoprcn, String centro, String plaza, String digito,
			String titular, BigInteger secuencia, BigDecimal saldo) {
		super();
		this.acuerdo = acuerdo;
		this.importe = importe;
		this.estatus = estatus;
		this.movimiento = movimiento;
		this.terminal = terminal;
		this.fechaoprcn = fechaoprcn;
		this.horaoprcn = horaoprcn;
		this.centro = centro;
		this.plaza = plaza;
		this.digito = digito;
		this.titular = titular;
		this.secuencia = secuencia;
		this.saldo = saldo;
	}

	/**
	 * Getter & Setters.
	 * @return the acuerdo
	 */
	public BigInteger getAcuerdo() {
		return acuerdo;
	}

	/**
	 * Getter & Setters.
	 * @param acuerdo the acuerdo to set
	 */
	public void setAcuerdo(BigInteger acuerdo) {
		this.acuerdo = acuerdo;
	}

	/**
	 * Getter & Setters.
	 * @return the importe
	 */
	public BigDecimal getImporte() {
		return importe;
	}

	/**
	 * Getter & Setters.
	 * @param importe the importe to set
	 */
	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}

	/**
	 * Getter & Setters.
	 * @return the estatus
	 */
	public String getEstatus() {
		return estatus;
	}

	/**
	 * Getter & Setters.
	 * @param estatus the estatus to set
	 */
	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	/**
	 * Getter & Setters.
	 * @return the movimiento
	 */
	public String getMovimiento() {
		return movimiento;
	}

	/**
	 * Getter & Setters.
	 * @param movimiento the movimiento to set
	 */
	public void setMovimiento(String movimiento) {
		this.movimiento = movimiento;
	}

	/**
	 * Getter & Setters.
	 * @return the terminal
	 */
	public String getTerminal() {
		return terminal;
	}

	/**
	 * Getter & Setters.
	 * @param terminal the terminal to set
	 */
	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}

	/**
	 * Getter & Setters.
	 * @return the fechaoprcn
	 */
	public String getFechaoprcn() {
		return fechaoprcn;
	}

	/**
	 * Getter & Setters.
	 * @param fechaoprcn the fechaoprcn to set
	 */
	public void setFechaoprcn(String fechaoprcn) {
		this.fechaoprcn = fechaoprcn;
	}

	/**
	 * Getter & Setters.
	 * @return the horaoprcn
	 */
	public String getHoraoprcn() {
		return horaoprcn;
	}

	/**
	 * Getter & Setters.
	 * @param horaoprcn the horaoprcn to set
	 */
	public void setHoraoprcn(String horaoprcn) {
		this.horaoprcn = horaoprcn;
	}

	/**
	 * Getter & Setters.
	 * @return the centro
	 */
	public String getCentro() {
		return centro;
	}

	/**
	 * Getter & Setters.
	 * @param centro the centro to set
	 */
	public void setCentro(String centro) {
		this.centro = centro;
	}

	/**
	 * Getter & Setters.
	 * @return the plaza
	 */
	public String getPlaza() {
		return plaza;
	}

	/**
	 * Getter & Setters.
	 * @param plaza the plaza to set
	 */
	public void setPlaza(String plaza) {
		this.plaza = plaza;
	}

	/**
	 * Getter & Setters.
	 * @return the digito
	 */
	public String getDigito() {
		return digito;
	}

	/**
	 * Getter & Setters.
	 * @param digito the digito to set
	 */
	public void setDigito(String digito) {
		this.digito = digito;
	}

	/**
	 * Getter & Setters.
	 * @return the titular
	 */
	public String getTitular() {
		return titular;
	}

	/**
	 * Getter & Setters.
	 * @param titular the titular to set
	 */
	public void setTitular(String titular) {
		this.titular = titular;
	}

	/**
	 * Getter & Setters.
	 * @return the secuencia
	 */
	public BigInteger getSecuencia() {
		return secuencia;
	}

	/**
	 * Getter & Setters.
	 * @param secuencia the secuencia to set
	 */
	public void setSecuencia(BigInteger secuencia) {
		this.secuencia = secuencia;
	}

	/**
	 * Getter & Setters.
	 * @return the saldo
	 */
	public BigDecimal getSaldo() {
		return saldo;
	}

	/**
	 * Getter & Setters.
	 * @param saldo the saldo to set
	 */
	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}

}
