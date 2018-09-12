package mx.babel.bansefi.banksystem.oficina.beans;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Bean para almacenar datos de consulta ContadoresCentro
 * 
 * @author manuel.nieto
 * 
 */
public class ContadoresCentroBean implements Serializable{
	
	private static final long serialVersionUID = -8910514487098759432L;
	// Variables de entrada
	private String codigoEntidad;
	private String codigoCentro;
	private String codigoTerminal;
	private String fechaContable;
	private String codigoMoneda;
	
	private ContadoresCentroRegistrosBean contadoresCentroRegistrosOn;

	// Caja
	// On
	private BigDecimal cobrosOnCaja;
	private BigDecimal pagosOnCaja;
	private BigDecimal diferenciaOnCaja;

	// Off
	private BigDecimal cobrosOffCaja;
	private BigDecimal pagosOffCaja;
	private BigDecimal diferenciaOffCaja;

	// Mov
	private BigDecimal cobrosMovCaja;
	private BigDecimal pagosMovCaja;
	private BigDecimal diferenciaMovCaja;

	// Totales
	private BigDecimal totalCobrosCaja;
	private BigDecimal totalPagosCaja;
	private BigDecimal totalDiferenciaCaja;

	// Intervención
	// On
	private BigDecimal deberOnIntervencion;
	private BigDecimal haberOnIntervencion;
	private BigDecimal diferenciaOnIntervencion;

	// Off
	private BigDecimal deberOffIntervencion;
	private BigDecimal haberOffIntervencion;
	private BigDecimal diferenciaOffIntervencion;

	// Mov
	private BigDecimal deberMovIntervencion;
	private BigDecimal haberMovIntervencion;
	private BigDecimal diferenciaMovIntervencion;

	// Totales
	private BigDecimal totalDebeIntervencion;
	private BigDecimal totalHaberIntervencion;
	private BigDecimal totalDiferenciaIntervencion;

	/**
	 * Funcion que realiza el calculo de los campos de diferencias on, off y mov
	 * en caja e intervencion
	 */
	public void calcularDiferencias() {
		// Calculos Caja
		this.diferenciaOnCaja = opBigDecimal(this.cobrosOnCaja,
				this.pagosOnCaja, false);
		this.diferenciaOffCaja = opBigDecimal(this.cobrosOffCaja,
				this.pagosOffCaja, false);
		this.diferenciaMovCaja = opBigDecimal(this.cobrosMovCaja,
				this.pagosMovCaja, false);

		// Calculos Intervencion
		this.diferenciaOnIntervencion = opBigDecimal(this.deberOnIntervencion,
				this.haberOnIntervencion, false);
		this.diferenciaOffIntervencion = opBigDecimal(
				this.deberOffIntervencion, this.haberOffIntervencion, false);
		this.diferenciaMovIntervencion = opBigDecimal(
				this.deberMovIntervencion, this.haberMovIntervencion, false);
	}

	/**
	 * Funcion que realiza el calculos de los totales: Total Cobros Total Pagos
	 * Total Diferencia en caja e intervencion
	 */
	public void calcularTotales() {
		// Calculos Caja
		this.totalCobrosCaja = opBigDecimal(this.cobrosOnCaja,
				this.cobrosOffCaja, true);
		this.totalCobrosCaja = opBigDecimal(this.totalCobrosCaja,
				this.cobrosMovCaja, true);

		this.totalPagosCaja = opBigDecimal(this.pagosOnCaja, this.pagosOffCaja,
				true);
		this.totalPagosCaja = opBigDecimal(this.totalPagosCaja,
				this.pagosMovCaja, true);

		this.totalDiferenciaCaja = opBigDecimal(this.diferenciaOnCaja,
				this.diferenciaOffCaja, true);
		this.totalDiferenciaCaja = opBigDecimal(this.totalDiferenciaCaja,
				this.diferenciaMovCaja, true);

		// Calculos Intervencion
		this.totalDebeIntervencion = opBigDecimal(this.deberOnIntervencion,
				this.deberOffIntervencion, true);
		this.totalDebeIntervencion = opBigDecimal(this.totalDebeIntervencion,
				this.deberMovIntervencion, true);

		this.totalHaberIntervencion = opBigDecimal(this.haberOnIntervencion,
				this.haberOffIntervencion, true);
		this.totalHaberIntervencion = opBigDecimal(this.totalHaberIntervencion,
				this.haberMovIntervencion, true);

		this.totalDiferenciaIntervencion = opBigDecimal(
				this.diferenciaOnIntervencion, this.diferenciaOffIntervencion,
				true);
		this.totalDiferenciaIntervencion = opBigDecimal(
				this.totalDiferenciaIntervencion,
				this.diferenciaMovIntervencion, true);
	}

	
	/**
	 * Operacion BigDecimal Mètodo para realizar sumas y restas entre
	 * BigDecimals
	 * 
	 * @param value
	 *            Valor base
	 * @param augend
	 *            Valor a sumar o restarse
	 * @param suma
	 *            boolean para definir si es suma o resta
	 * @return BigDecimal con resultado de la operaciòn.
	 */
	public BigDecimal opBigDecimal(BigDecimal value, BigDecimal augend,
			Boolean suma) {
//		if (augend.compareTo(augend.negate()) != 0)
//			suma = false;
		if (value == null) {
			value = BigDecimal.ZERO;
		}

		if (augend == null) {
			augend = BigDecimal.ZERO;
		}
		if (suma) {
			return value.add(augend);
		} else {
			return value.add(augend.negate());
		}
	}

	/**
	 * Funcion que obtiene el valor de la variable codigoTerminal
	 * @return String
	 */
	public String getCodigoTerminal() {
		return codigoTerminal;
	}

	/**
	 * Funcion que asigna el valor de la variable codigoTerminal
	 * @param codigoTerminal
	 */
	public void setCodigoTerminal(String codigoTerminal) {
		this.codigoTerminal = codigoTerminal;
	}	

	/**
	 * Funcion que obtiene el valor de la variable codigoEntidad
	 * @return String
	 */
	public String getCodigoEntidad() {
		return codigoEntidad;
	}

	/**
	 * Funcion que asigna el valor de la variable codigoEntidad
	 * @param codigoEntidad
	 */
	public void setCodigoEntidad(String codigoEntidad) {
		this.codigoEntidad = codigoEntidad;
	}

	/**
	 * Funcion que obtiene el valor de la variable codigoCentro
	 * @return String
	 */
	public String getCodigoCentro() {
		return codigoCentro;
	}

	/**
	 * Funcion que asigna el valor de la variable codigoCentro
	 * @param codigoCentro
	 */
	public void setCodigoCentro(String codigoCentro) {
		this.codigoCentro = codigoCentro;
	}

	/**
	 * Funcion que obtiene el valor de la variable fechaContable
	 * @return String
	 */
	public String getFechaContable() {
		return fechaContable;
	}

	/**
	 * Funcion que asigna el valor de la variable fechaContable
	 * @param fechaContable
	 */
	public void setFechaContable(String fechaContable) {
		this.fechaContable = fechaContable;
	}

	/**
	 * Funcion que obtiene el valor de la variable codigoMoneda
	 * @return String
	 */
	public String getCodigoMoneda() {
		return codigoMoneda;
	}

	/**
	 * Funcion que asigna el valor de la variable codigoMoneda
	 * @param codigoMoneda
	 */
	public void setCodigoMoneda(String codigoMoneda) {
		this.codigoMoneda = codigoMoneda;
	}

	/**
	 * Funcion que obtiene el valor de la variable cobrosOnCaja
	 * @return BigDecimal
	 */
	public BigDecimal getCobrosOnCaja() {
		return cobrosOnCaja;
	}

	/**
	 * Funcion que asigna el valor de la vriable cobrosOnCaja
	 * @param cobrosOnCaja
	 */
	public void setCobrosOnCaja(BigDecimal cobrosOnCaja) {
		this.cobrosOnCaja = cobrosOnCaja;
	}

	/**
	 * Funcion que obtiene el valor de la vriable pagosOnCaja
	 * @return BigDecimal
	 */
	public BigDecimal getPagosOnCaja() {
		return pagosOnCaja;
	}

	/**
	 * Funcion que asigna el valor de la vriable pagosOnCaja
	 * @param pagosOnCaja
	 */
	public void setPagosOnCaja(BigDecimal pagosOnCaja) {
		this.pagosOnCaja = pagosOnCaja;
	}

	/**
	 * Funcion que obtiene el valor de la variable diferenciaOnCaja
	 * @return BigDecimal
	 */
	public BigDecimal getDiferenciaOnCaja() {
		return diferenciaOnCaja;
	}

	/**
	 * Funcion que asigna el valor de la variable diferenciaOnCaja
	 */
	public void setDiferenciaOnCaja(BigDecimal diferenciaOnCaja) {
		this.diferenciaOnCaja = diferenciaOnCaja;
	}

	/**
	 * Funcion que obtiene el valor de la variable cobrosOffCaja
	 * @return BigDecimal
	 */
	public BigDecimal getCobrosOffCaja() {
		return cobrosOffCaja;
	}

	/**
	 * Funcion que asigna el valor de la variable cobrosOffCaja
	 * @param cobrosOffCaja
	 */
	public void setCobrosOffCaja(BigDecimal cobrosOffCaja) {
		this.cobrosOffCaja = cobrosOffCaja;
	}

	/**
	 * Funcion que obtiene el valor de la variable pagosOffCaja
	 * @return BigDecimal
	 */
	public BigDecimal getPagosOffCaja() {
		return pagosOffCaja;
	}

	/**
	 * Funcion que asigna el valor de la vriable pagosOffCaja
	 * @param pagosOffCaja
	 */
	public void setPagosOffCaja(BigDecimal pagosOffCaja) {
		this.pagosOffCaja = pagosOffCaja;
	}

	/**
	 * Funcion que obtiene el valor de la variable diferenciaOffCaja
	 * @return BigDecimal
	 */
	public BigDecimal getDiferenciaOffCaja() {
		return diferenciaOffCaja;
	}

	/**
	 * Funcion que asigna el valor de la variable diferenciaOffCaja
	 * @param diferenciaOffCaja
	 */
	public void setDiferenciaOffCaja(BigDecimal diferenciaOffCaja) {
		this.diferenciaOffCaja = diferenciaOffCaja;
	}

	/**
	 * Funcion que obtiene el valor de la variable cobrosMovCaja
	 * @return BigDecimal
	 */
	public BigDecimal getCobrosMovCaja() {
		return cobrosMovCaja;
	}

	/**
	 * Funcion que asigna el valor de la variable cobrosMovCaja
	 * @param cobrosMovCaja
	 */
	public void setCobrosMovCaja(BigDecimal cobrosMovCaja) {
		this.cobrosMovCaja = cobrosMovCaja;
	}

	/**
	 * Funcion que obtiene el valor de la variable pagosMovCaja
	 * @return BigDecimal
	 */
	public BigDecimal getPagosMovCaja() {
		return pagosMovCaja;
	}

	/**
	 * Funcion que asigna el valor de la variable pagosMovCaja
	 * @param pagosMovCaja
	 */
	public void setPagosMovCaja(BigDecimal pagosMovCaja) {
		this.pagosMovCaja = pagosMovCaja;
	}

	/**
	 * Funcion que obtiene el valor de diferenciaMovCaja
	 * @return BigDecimal
	 */
	public BigDecimal getDiferenciaMovCaja() {
		return diferenciaMovCaja;
	}

	/**
	 * Funcion que asigna el valor de la variable diferenciaMovCaja
	 * @param diferenciaMovCaja
	 */
	public void setDiferenciaMovCaja(BigDecimal diferenciaMovCaja) {
		this.diferenciaMovCaja = diferenciaMovCaja;
	}

	/**
	 * Funcion que obtiene el valor de la variable totalCobrosCaja
	 * @return BigDecimal
	 */
	public BigDecimal getTotalCobrosCaja() {
		return totalCobrosCaja;
	}

	/**
	 * Funcion que asigna el valor de la variable totalCobrosCaja
	 * @param totalCobrosCaja
	 */
	public void setTotalCobrosCaja(BigDecimal totalCobrosCaja) {
		this.totalCobrosCaja = totalCobrosCaja;
	}

	/**
	 * Funcion que obtiene el valor de la variable totalPagosCaja
	 * @return BigDecimal
	 */
	public BigDecimal getTotalPagosCaja() {
		return totalPagosCaja;
	}

	/**
	 * Funcion que asigna el valor de la variable totalPagosCaja
	 * @param totalPagosCaja
	 */
	public void setTotalPagosCaja(BigDecimal totalPagosCaja) {
		this.totalPagosCaja = totalPagosCaja;
	}

	/**
	 * Funcion que obtiene el valor de la variable totalDiferenciaCaja
	 * @return BigDecimal
	 */
	public BigDecimal getTotalDiferenciaCaja() {
		return totalDiferenciaCaja;
	}

	/**
	 * Funcion que obtiene el valor de la variable totalDiferenciaCaja
	 * @param totalDiferenciaCaja
	 */
	public void setTotalDiferenciaCaja(BigDecimal totalDiferenciaCaja) {
		this.totalDiferenciaCaja = totalDiferenciaCaja;
	}

	/**
	 * Funcion que obtiene el valor de la variable deberOnIntervencion
	 * @return BigDecimal
	 */
	public BigDecimal getDeberOnIntervencion() {
		return deberOnIntervencion;
	}

	/**
	 * Funcion que asigna el valor de la variable deberOnIntervencion
	 * @param deberOnIntervencion
	 */
	public void setDeberOnIntervencion(BigDecimal deberOnIntervencion) {
		this.deberOnIntervencion = deberOnIntervencion;
	}

	/**
	 * Funcion que obtiene el valor de la variable haberOnIntervencion
	 * @return BigDecimal
	 */
	public BigDecimal getHaberOnIntervencion() {
		return haberOnIntervencion;
	}

	/**
	 * Funcion que asigna el valor de la variable haberOnIntervencion
	 * @param haberOnIntervencion
	 */
	public void setHaberOnIntervencion(BigDecimal haberOnIntervencion) {
		this.haberOnIntervencion = haberOnIntervencion;
	}

	/**
	 * Funcion que
	 * @return BigDecimal
	 */
	public BigDecimal getDiferenciaOnIntervencion() {
		return diferenciaOnIntervencion;
	}

	/**
	 * Funcion que asigna el valor de la vriable diferenciaOnIntervencion
	 * @param diferenciaOnIntervencion
	 */
	public void setDiferenciaOnIntervencion(BigDecimal diferenciaOnIntervencion) {
		this.diferenciaOnIntervencion = diferenciaOnIntervencion;
	}

	/**
	 * Funcion que obtiene el valor de la variable deberOffIntervencion
	 * @return BigDecimal
	 */
	public BigDecimal getDeberOffIntervencion() {
		return deberOffIntervencion;
	}

	/**
	 * Funcion que asigna el valor de la variable deberOffIntervencion
	 * @param deberOffIntervencion
	 */
	public void setDeberOffIntervencion(BigDecimal deberOffIntervencion) {
		this.deberOffIntervencion = deberOffIntervencion;
	}

	/**
	 * Funcion que obtiene el valor de la variable haberOffIntervencion
	 * @return BigDecimal
	 */
	public BigDecimal getHaberOffIntervencion() {
		return haberOffIntervencion;
	}

	/**
	 * Funcionn que asigna el valor de la variable haberOffIntervencion
	 * @param haberOffIntervencion
	 */
	public void setHaberOffIntervencion(BigDecimal haberOffIntervencion) {
		this.haberOffIntervencion = haberOffIntervencion;
	}

	/**
	 * Funcion que obtiene el valor de la variable diferenciaOffIntervencion
	 * @return BigDecimal
	 */
	public BigDecimal getDiferenciaOffIntervencion() {
		return diferenciaOffIntervencion;
	}

	/**
	 * Funcion que asigna el valor de la variable diferenciaOffIntervencion
	 * @param diferenciaOffIntervencion
	 */
	public void setDiferenciaOffIntervencion(
			BigDecimal diferenciaOffIntervencion) {
		this.diferenciaOffIntervencion = diferenciaOffIntervencion;
	}

	/**
	 * Funcion que obtiene el valor de la variable deberMovIntervencion
	 * @return BigDecimal
	 */
	public BigDecimal getDeberMovIntervencion() {
		return deberMovIntervencion;
	}

	/**
	 * Funcion que asigna el valor de la variable deberMovIntervencion
	 * @param deberMovIntervencion
	 */
	public void setDeberMovIntervencion(BigDecimal deberMovIntervencion) {
		this.deberMovIntervencion = deberMovIntervencion;
	}

	/**
	 * Funcion que obtiene el valor de la variable haberMovIntervencion
	 * @return BigDecimal
	 */
	public BigDecimal getHaberMovIntervencion() {
		return haberMovIntervencion;
	}

	/**
	 * Funcion que asigna el valor de la variable haberMovIntervencion
	 * @param haberMovIntervencion
	 */
	public void setHaberMovIntervencion(BigDecimal haberMovIntervencion) {
		this.haberMovIntervencion = haberMovIntervencion;
	}

	/**
	 * Funcion que obtiene el valor de la variable diferenciaMovIntervencion
	 * @return BigDecimal
	 */
	public BigDecimal getDiferenciaMovIntervencion() {
		return diferenciaMovIntervencion;
	}

	/**
	 * Funcion que asigna el valor de la variable diferenciaMovIntervencion
	 * @param diferenciaMovIntervencion
	 */
	public void setDiferenciaMovIntervencion(
			BigDecimal diferenciaMovIntervencion) {
		this.diferenciaMovIntervencion = diferenciaMovIntervencion;
	}

	/**
	 * Funcion que obtiene el valor de la variable totalDebeIntervencion
	 * @return BigDecimal
	 */
	public BigDecimal getTotalDebeIntervencion() {
		return totalDebeIntervencion;
	}

	/**
	 * Funcion que asigna el valor de la variable totalDebeIntervencion
	 * @param totalDebeIntervencion
	 */
	public void setTotalDebeIntervencion(BigDecimal totalDebeIntervencion) {
		this.totalDebeIntervencion = totalDebeIntervencion;
	}

	/**
	 * Funcion que obtiene el valor de la variable totalHaberIntervencion
	 * @return BigDecimal
	 */
	public BigDecimal getTotalHaberIntervencion() {
		return totalHaberIntervencion;
	}

	/**
	 * Funcion que
	 * @param totalHaberIntervencion
	 */
	public void setTotalHaberIntervencion(BigDecimal totalHaberIntervencion) {
		this.totalHaberIntervencion = totalHaberIntervencion;
	}

	/**
	 * Funcion que obtiene el valor de la variable totalDiferenciaIntervencion
	 * @return BigDecimal
	 */
	public BigDecimal getTotalDiferenciaIntervencion() {
		return totalDiferenciaIntervencion;
	}

	/**
	 * Funcion que asigna el valor de la variable totalDiferenciaIntervencion
	 * @param totalDiferenciaIntervencion
	 */
	public void setTotalDiferenciaIntervencion(
			BigDecimal totalDiferenciaIntervencion) {
		this.totalDiferenciaIntervencion = totalDiferenciaIntervencion;
	}

	public ContadoresCentroRegistrosBean getContadoresCentroRegistrosOn() {
		return contadoresCentroRegistrosOn;
	}

	public void setContadoresCentroRegistrosOn(
			ContadoresCentroRegistrosBean contadoresCentroRegistrosOn) {
		this.contadoresCentroRegistrosOn = contadoresCentroRegistrosOn;
	}
	
	

}
