package mx.babel.bansefi.banksystem.base.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ApuntesBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2233252148329565959L;
	
	private Integer status;
	
	private Long numCuenta;
	
	private String titular;
	
	private String tipoApunte;
	
	private BigDecimal importe;
	
	private Integer unidadesMillon;
	
	private boolean mostrarUnidadesMillon;
	
	private String tipoOperacion;
	
	private String codigoOperacion;
	
	private Date fechaValor;
	
	private String concepto;
	
	private String numTransaccion;
	
	private String horaOperacion;
	
	private Date fechaOperacion;
	
	private String terminal;
	
	private String puesto;
	
	private String centro;
	
	
	/**
	 * @return the numCuenta
	 */
	public Long getNumCuenta() {
		return numCuenta;
	}

	/**
	 * @param numCuenta the numCuenta to set
	 */
	public void setNumCuenta(Long numCuenta) {
		this.numCuenta = numCuenta;
	}

	/**
	 * @return the titular
	 */
	public String getTitular() {
		return titular;
	}

	/**
	 * @param titular the titular to set
	 */
	public void setTitular(String titular) {
		this.titular = titular;
	}

	/**
	 * @return the tipoApunte
	 */
	public String getTipoApunte() {
		return tipoApunte;
	}

	/**
	 * @param tipoApunte the tipoApunte to set
	 */
	public void setTipoApunte(String tipoApunte) {
		this.tipoApunte = tipoApunte;
	}

	/**
	 * @return the importe
	 */
	public BigDecimal getImporte() {
		return importe;
	}

	/**
	 * @param importe the importe to set
	 */
	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}

	/**
	 * @return the unidadesDeMillon
	 */
	public Integer getUnidadesMillon() {
		return unidadesMillon;
	}

	/**
	 * @param unidadesDeMillon the unidadesDeMillon to set
	 */
	public void setUnidadesMillon(Integer unidadesMillon) {
		this.unidadesMillon = unidadesMillon;
	}

	/**
	 * @return the mostrarUnidadesMillon
	 */
	public boolean isMostrarUnidadesMillon() {
		return mostrarUnidadesMillon;
	}

	/**
	 * @param mostrarUnidadesMillon the mostrarUnidadesMillon to set
	 */
	public void setMostrarUnidadesMillon(boolean mostrarUnidadesMillon) {
		this.mostrarUnidadesMillon = mostrarUnidadesMillon;
	}

	/**
	 * @return the codigoOperacion
	 */
	public String getCodigoOperacion() {
		return codigoOperacion;
	}

	/**
	 * @param codigoOperacion the codigoOperacion to set
	 */
	public void setCodigoOperacion(String codigoOperacion) {
		this.codigoOperacion = codigoOperacion;
	}

	/**
	 * @return the fechaOperacion
	 */
	public Date getFechaOperacion() {
		return fechaOperacion;
	}

	/**
	 * @param fechaOperacion the fechaOperacion to set
	 */
	public void setFechaOperacion(Date fechaOperacion) {
		this.fechaOperacion = fechaOperacion;
	}

	/**
	 * @return the concepto
	 */
	public String getConcepto() {
		return concepto;
	}

	/**
	 * @param concepto the concepto to set
	 */
	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	/**
	 * @return the numTransaccion
	 */
	public String getNumTransaccion() {
		return numTransaccion;
	}

	/**
	 * @param string the numTransaccion to set
	 */
	public void setNumTransaccion(String string) {
		this.numTransaccion = string;
	}

	/**
	 * @return the horaOperacion
	 */
	public String getHoraOperacion() {
		return horaOperacion;
	}

	/**
	 * @param horaOperacion the horaOperacion to set
	 */
	public void setHoraOperacion(String horaOperacion) {
		this.horaOperacion = horaOperacion;
	}

	/**
	 * @return the fechaValor
	 */
	public Date getFechaValor() {
		return fechaValor;
	}

	/**
	 * @param fechaValor the fechaValor to set
	 */
	public void setFechaValor(Date fechaValor) {
		this.fechaValor = fechaValor;
	}

	/**
	 * @return the terminal
	 */
	public String getTerminal() {
		return terminal;
	}

	/**
	 * @param terminal the terminal to set
	 */
	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}

	/**
	 * @return the puesto
	 */
	public String getPuesto() {
		return puesto;
	}

	/**
	 * @param puesto the puesto to set
	 */
	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}

	/**
	 * @return the centro
	 */
	public String getCentro() {
		return centro;
	}

	/**
	 * @param centro the centro to set
	 */
	public void setCentro(String centro) {
		this.centro = centro;
	}

	/**
	 * @return the status
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getTipoOperacion() {
		return tipoOperacion;
	}

	public void setTipoOperacion(String tipoOperacion) {
		this.tipoOperacion = tipoOperacion;
	}
	
	
}
