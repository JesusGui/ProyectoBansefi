package mx.babel.bansefi.banksystem.cuentas.beans;

import java.io.Serializable;
import java.util.Date;

import mx.babel.bansefi.banksystem.base.beans.domain.CuentaBean;

/**
 * Clase para almacenar los datos necesarios
 * para suspender una cuenta
 * @author manuel.nieto
 *
 */
public class SuspenderCuentaBean implements Serializable{

	private static final long serialVersionUID = -595091141086683507L;

	private CuentaBean cuentaBean;
	private Date fechaDesde;
	private Date fechaHasta;
	private Date fechaAnteriorDesde;
	private Date fechaAnteriorHasta;
	private Date horaPlanificada;
	public CuentaBean getCuentaBean() {
		return cuentaBean;
	}
	public void setCuentaBean(CuentaBean cuentaBean) {
		this.cuentaBean = cuentaBean;
	}
	public Date getFechaDesde() {
		return fechaDesde;
	}
	public void setFechaDesde(Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}
	public Date getFechaHasta() {
		return fechaHasta;
	}
	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
	}
	public Date getFechaAnteriorDesde() {
		return fechaAnteriorDesde;
	}
	public void setFechaAnteriorDesde(Date fechaAnteriorDesde) {
		this.fechaAnteriorDesde = fechaAnteriorDesde;
	}
	public Date getFechaAnteriorHasta() {
		return fechaAnteriorHasta;
	}
	public void setFechaAnteriorHasta(Date fechaAnteriorHasta) {
		this.fechaAnteriorHasta = fechaAnteriorHasta;
	}
	public Date getHoraPlanificada() {
		return horaPlanificada;
	}
	public void setHoraPlanificada(Date horaPlanificada) {
		this.horaPlanificada = horaPlanificada;
	}
	
	
}
