package mx.babel.bansefi.banksystem.cajas.beans;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import mx.babel.bansefi.banksystem.base.beans.parrilla.ExistenciaDenominacionBean;

public class AjusteExistenciasActualesBean {
	
	private Date fechaCierre;
	
	private String codigoMoneda = "MXN";
	private String sucursal;
	
	private BigDecimal diferencia;
	private BigDecimal totalExistencias;
	private BigDecimal totalEfectivo;
	
	private List<ExistenciaDenominacionBean> existenciaDenominacion;
	private List<AjusteExistenciasActualesBean> listaAjuste;
	
	public Date getFechaCierre() {
		return fechaCierre;
	}

	public void setFechaCierre(Date fechaCierre) {
		this.fechaCierre = fechaCierre;
	}

	public String getCodigoMoneda() {
		return codigoMoneda;
	}
	
	public void setCodigoMoneda(String codigoMoneda) {
		this.codigoMoneda = codigoMoneda;
	}
	
	public String getSucursal() {
		return sucursal;
	}
	
	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}
	
	public BigDecimal getDiferencia() {
		return diferencia;
	}

	public void setDiferencia(BigDecimal diferencia) {
		this.diferencia = diferencia;
	}

	public BigDecimal getTotalExistencias() {
		return totalExistencias;
	}

	public void setTotalExistencias(BigDecimal totalExistencias) {
		this.totalExistencias = totalExistencias;
	}

	public BigDecimal getTotalEfectivo() {
		return totalEfectivo;
	}
	
	public void setTotalEfectivo(BigDecimal totalEfectivo) {
		this.totalEfectivo = totalEfectivo;
	}
	
	public List<ExistenciaDenominacionBean> getExistenciaDenominacion() {
		return existenciaDenominacion;
	}

	public void setExistenciaDenominacion(
			List<ExistenciaDenominacionBean> existenciaDenominacion) {
		this.existenciaDenominacion = existenciaDenominacion;
	}

	public List<AjusteExistenciasActualesBean> getListaAjuste() {
		return listaAjuste;
	}
	
	public void setListaAjuste(List<AjusteExistenciasActualesBean> listaAjuste) {
		this.listaAjuste = listaAjuste;
	}	
}
