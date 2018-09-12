package mx.babel.bansefi.banksystem.oficina.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Bean que almacena la respuesta a una anulación de un servicio central
 * @author manuel.nieto
 *
 */
public class AnulacionServiciosCentralesRespuestaBean implements Serializable {

	private static final long serialVersionUID = 3369017917224024430L;
	
	private String estatus;
	private String movimiento;
	private String acuerdo;
	private BigDecimal montoTotal;
	private String terminal;
	private Date fechaOperacion;
	private Date horaOperacion;
	private String oficina;
	private String plaza;
	private String digito;
	private String titular;
	public String getEstatus() {
		return estatus;
	}
	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}
	public String getMovimiento() {
		return movimiento;
	}
	public void setMovimiento(String movimiento) {
		this.movimiento = movimiento;
	}
	public String getAcuerdo() {
		return acuerdo;
	}
	public void setAcuerdo(String acuerdo) {
		this.acuerdo = acuerdo;
	}
	public BigDecimal getMontoTotal() {
		return montoTotal;
	}
	public void setMontoTotal(BigDecimal montoTotal) {
		this.montoTotal = montoTotal;
	}
	public String getTerminal() {
		return terminal;
	}
	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}
	public Date getFechaOperacion() {
		return fechaOperacion;
	}
	public void setFechaOperacion(Date fechaOperacion) {
		this.fechaOperacion = fechaOperacion;
	}
	public String getOficina() {
		return oficina;
	}
	public void setOficina(String oficina) {
		this.oficina = oficina;
	}
	public String getPlaza() {
		return plaza;
	}
	public void setPlaza(String plaza) {
		this.plaza = plaza;
	}
	public String getDigito() {
		return digito;
	}
	public void setDigito(String digito) {
		this.digito = digito;
	}
	public String getTitular() {
		return titular;
	}
	public void setTitular(String titular) {
		this.titular = titular;
	}
	public Date getHoraOperacion() {
		return horaOperacion;
	}
	public void setHoraOperacion(Date horaOperacion) {
		this.horaOperacion = horaOperacion;
	}
	
	

}
