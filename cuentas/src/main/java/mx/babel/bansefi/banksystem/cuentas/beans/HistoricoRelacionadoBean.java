package mx.babel.bansefi.banksystem.cuentas.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Bean para obtener el historico de una relación
 * @author mario.montesdeoca
 *
 */
public class HistoricoRelacionadoBean implements Serializable{

	private static final long serialVersionUID = -8557969439459575583L;

	private String estado;
	
	private Date fechaInicio;
	
	private Date fechaContable;
	
	private String empleado;
	
	private String autorizador;
	
	private String centro;
	
	private String terminal;
	
	private Date fechaTrasn;
	
	private String horaTrans;
	
	private String codTrans;
	
	private String centroAc;
	
	private BigDecimal importe;
	
	private String moneda;

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
	 * @return Atributo fechaInicio
	 */
	public Date getFechaInicio() {
		return fechaInicio;
	}

	/**
	 * @param fechaInicio Atributo fechaInicio a definir
	 */
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	/**
	 * @return Atributo fechaContable
	 */
	public Date getFechaContable() {
		return fechaContable;
	}

	/**
	 * @param fechaContable Atributo fechaContable a definir
	 */
	public void setFechaContable(Date fechaContable) {
		this.fechaContable = fechaContable;
	}

	/**
	 * @return Atributo empleado
	 */
	public String getEmpleado() {
		return empleado;
	}

	/**
	 * @param empleado Atributo empleado a definir
	 */
	public void setEmpleado(String empleado) {
		this.empleado = empleado;
	}

	/**
	 * @return Atributo autorizador
	 */
	public String getAutorizador() {
		return autorizador;
	}

	/**
	 * @param autorizador Atributo autorizador a definir
	 */
	public void setAutorizador(String autorizador) {
		this.autorizador = autorizador;
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
	 * @return Atributo terminal
	 */
	public String getTerminal() {
		return terminal;
	}

	/**
	 * @param terminal Atributo terminal a definir
	 */
	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}

	/**
	 * @return Atributo fechaTrasn
	 */
	public Date getFechaTrasn() {
		return fechaTrasn;
	}

	/**
	 * @param fechaTrasn Atributo fechaTrasn a definir
	 */
	public void setFechaTrasn(Date fechaTrasn) {
		this.fechaTrasn = fechaTrasn;
	}

	/**
	 * @return Atributo horaTrans
	 */
	public String getHoraTrans() {
		return horaTrans;
	}

	/**
	 * @param horaTrans Atributo horaTrans a definir
	 */
	public void setHoraTrans(String horaTrans) {
		this.horaTrans = horaTrans;
	}

	/**
	 * @return Atributo codTrans
	 */
	public String getCodTrans() {
		return codTrans;
	}

	/**
	 * @param codTrans Atributo codTrans a definir
	 */
	public void setCodTrans(String codTrans) {
		this.codTrans = codTrans;
	}

	/**
	 * @return Atributo centroAc
	 */
	public String getCentroAc() {
		return centroAc;
	}

	/**
	 * @param centroAc Atributo centroAc a definir
	 */
	public void setCentroAc(String centroAc) {
		this.centroAc = centroAc;
	}

	/**
	 * @return Atributo importe
	 */
	public BigDecimal getImporte() {
		return importe;
	}

	/**
	 * @param importe Atributo importe a definir
	 */
	public void setImporte(BigDecimal importe) {
		this.importe = importe;
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
	
	public String getHoraFormateada(){
		String resultado = this.horaTrans;
		while(resultado.length() < 6){
			resultado = "0"+ resultado;
		}
		return resultado.substring(0,2) + ":" + resultado.substring(2,4)+ ":" + resultado.substring(4);
	}
}
