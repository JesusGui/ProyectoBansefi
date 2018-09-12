package mx.babel.bansefi.banksystem.cuentas.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import mx.babel.arq.comun.beans.PaginacionBean;

/**
 * Clase que define los atributos de un saldo.
 * 
 * @author omar.marquez
 * 
 */
public class SaldoCuentaBean extends PaginacionBean implements Serializable {

	private static final long serialVersionUID = 6299999780600381751L;

	private String codigoSaldo;
	private Date ultimoMovimiento;
	private BigDecimal saldo;
	private Integer debe;
	private Integer haber;
	private String codigoCuenta;
	private Integer numSec;

	/**
	 * Método que devuelve el código de saldo.
	 * 
	 * @return codigoSaldo
	 */
	public String getCodigoSaldo() {
		return codigoSaldo;
	}

	/**
	 * Método que establece el código de saldo.
	 * 
	 * @param codigoSaldo
	 */
	public void setCodigoSaldo(String codigoSaldo) {
		this.codigoSaldo = codigoSaldo;
	}

	/**
	 * Método que devuelve la fecha del último movimiento realizado.
	 * 
	 * @return ultimoMovimiento
	 */
	public Date getUltimoMovimiento() {
		return ultimoMovimiento;
	}

	/**
	 * Método que establece la fecha del último movimiento realizado.
	 * 
	 * @param ultimoMovimiento
	 */
	public void setUltimoMovimiento(Date ultimoMovimiento) {
		this.ultimoMovimiento = ultimoMovimiento;
	}

	/**
	 * Método que devuelve el importe del saldo.
	 * 
	 * @return saldo
	 */
	public BigDecimal getSaldo() {
		return saldo;
	}

	/**
	 * Método que establece el importe del saldo.
	 * 
	 * @param saldo
	 */
	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}

	/**
	 * Método que devuelve el movimiento correspondiente al debe.
	 * 
	 * @return debe
	 */
	public Integer getDebe() {
		return debe;
	}

	/**
	 * Método que establece el movimiento correspondiente al debe.
	 * 
	 * @param debe
	 */
	public void setDebe(Integer debe) {
		this.debe = debe;
	}

	/**
	 * Método que devuelve el movimiento correspondiente al haber.
	 * 
	 * @return haber
	 */
	public Integer getHaber() {
		return haber;
	}

	/**
	 * Método que establece el movimiento correspondiente al haber.
	 * 
	 * @param haber
	 */
	public void setHaber(Integer haber) {
		this.haber = haber;
	}

	/**
	 * Método que devuelve el código de cuenta.
	 * 
	 * @return codigoCuenta
	 */
	public String getCodigoCuenta() {
		return codigoCuenta;
	}

	/**
	 * Método que establece el código de cuenta.
	 * 
	 * @param codigoCuenta
	 */
	public void setCodigoCuenta(String codigoCuenta) {
		this.codigoCuenta = codigoCuenta;
	}

	/**
	 * Método que devuelve el número de secuencia.
	 * 
	 * @return codigoCuenta
	 */
	public Integer getNumSec() {
		return numSec;
	}

	/**
	 * Método que establece el número de secuencia.
	 * 
	 * @param codigoCuenta
	 */
	public void setNumSec(Integer numSec) {
		this.numSec = numSec;
	}
}