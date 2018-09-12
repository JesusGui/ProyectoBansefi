package mx.babel.bansefi.banksystem.cajas.beans;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class AutorizacionPeticionTotalesBean {

	private Date fechaAbastecimiento;
	
	private String centro;
	
	private BigDecimal importePedido;
	private BigDecimal importeAutorizado;
	private BigDecimal saldoAnterior;
	private BigDecimal saldoActual;
	private BigDecimal totalPedido;
	private BigDecimal totalAutorizado;
	
	private List<AutorizacionPeticionTotalesBean> listaPeticiones;

	public Date getFechaAbastecimiento() {
		return fechaAbastecimiento;
	}

	public void setFechaAbastecimiento(Date fechaAbastecimiento) {
		this.fechaAbastecimiento = fechaAbastecimiento;
	}

	public String getCentro() {
		return centro;
	}

	public void setCentro(String centro) {
		this.centro = centro;
	}

	public BigDecimal getImportePedido() {
		return importePedido;
	}

	public void setImportePedido(BigDecimal importePedido) {
		this.importePedido = importePedido;
	}

	public BigDecimal getImporteAutorizado() {
		return importeAutorizado;
	}

	public void setImporteAutorizado(BigDecimal importeAutorizado) {
		this.importeAutorizado = importeAutorizado;
	}

	public BigDecimal getSaldoAnterior() {
		return saldoAnterior;
	}

	public void setSaldoAnterior(BigDecimal saldoAnterior) {
		this.saldoAnterior = saldoAnterior;
	}

	public BigDecimal getSaldoActual() {
		return saldoActual;
	}

	public void setSaldoActual(BigDecimal saldoActual) {
		this.saldoActual = saldoActual;
	}

	public BigDecimal getTotalPedido() {
		return totalPedido;
	}

	public void setTotalPedido(BigDecimal totalPedido) {
		this.totalPedido = totalPedido;
	}

	public BigDecimal getTotalAutorizado() {
		return totalAutorizado;
	}

	public void setTotalAutorizado(BigDecimal totalAutorizado) {
		this.totalAutorizado = totalAutorizado;
	}

	public List<AutorizacionPeticionTotalesBean> getListaPeticiones() {
		return listaPeticiones;
	}

	public void setListaPeticiones(
			List<AutorizacionPeticionTotalesBean> listaPeticiones) {
		this.listaPeticiones = listaPeticiones;
	}
}
