package mx.babel.bansefi.banksystem.cuentas.beans;

import java.math.BigDecimal;
import java.util.List;

public class GastosExternosBean {

	
	private List<TipoGastosBean> lTipoGasto;
     private BigDecimal importe;
     private List<GastoBean>parrillaGastos;

     private GastoBean gastoSeleccionado;
     
     private BigDecimal importeTotal;
     private BigDecimal importePendiente;
     private String destinoImputacion;
     private Long otraCuenta;
     

	
	public Long getOtraCuenta() {
		return otraCuenta;
	}

	public void setOtraCuenta(Long otraCuenta) {
		this.otraCuenta = otraCuenta;
	}

	public String getDestinoImputacion() {
		return destinoImputacion;
	}

	public void setDestinoImputacion(String destinoImputacion) {
		this.destinoImputacion = destinoImputacion;
	}

	public BigDecimal getImporteTotal() {
		return importeTotal;
	}

	public void setImporteTotal(BigDecimal importeTotal) {
		this.importeTotal = importeTotal;
	}

	public BigDecimal getImportePendiente() {
		return importePendiente;
	}

	public void setImportePendiente(BigDecimal importePendiente) {
		this.importePendiente = importePendiente;
	}

	public GastoBean getGastoSeleccionado() {
		return gastoSeleccionado;
	}

	public void setGastoSeleccionado(GastoBean gastoSeleccionado) {
		this.gastoSeleccionado = gastoSeleccionado;
	}

	public List<GastoBean> getParrillaGastos() {
		return parrillaGastos;
	}

	public void setParrillaGastos(List<GastoBean> parrillaGastos) {
		this.parrillaGastos = parrillaGastos;
	}

	public BigDecimal getImporte() {
		return importe;
	}

	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}

	public List<TipoGastosBean> getlTipoGasto() {
		return lTipoGasto;
	}

	public void setlTipoGasto(List<TipoGastosBean> lTipoGasto) {
		this.lTipoGasto = lTipoGasto;
	}
	
}
