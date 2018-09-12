package mx.babel.bansefi.banksystem.cuentas.beans;

import java.util.Date;

public class EstadoDocumentoBean {

	private Date fechaOperacion;
	private String horaOperacion;
	private String empleado;
	private String estado;
	/**
	 * @return Atributo fechaOperacion
	 */
	public Date getFechaOperacion() {
		return fechaOperacion;
	}
	/**
	 * @param fechaOperacion Atributo fechaOperacion a definir
	 */
	public void setFechaOperacion(Date fechaOperacion) {
		this.fechaOperacion = fechaOperacion;
	}
	/**
	 * @return Atributo horaOperacion
	 */
	public String getHoraOperacion() {
		return horaOperacion;
	}
	/**
	 * @param horaOperacion Atributo horaOperacion a definir
	 */
	public void setHoraOperacion(String horaOperacion) {
		this.horaOperacion = horaOperacion;
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
}
