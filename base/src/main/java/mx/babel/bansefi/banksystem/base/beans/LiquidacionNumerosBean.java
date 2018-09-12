package mx.babel.bansefi.banksystem.base.beans;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import mx.babel.bansefi.banksystem.base.beans.LiquidacionNumerosDetalleBean;

public class LiquidacionNumerosBean {

	private Date fechaPeriodoDesde;
	private Date fechaPeriodoHasta;
	private String codOperacion;
	private String descOperacion;
	private BigDecimal smAcreedores;
	private BigDecimal smDeudores;
	private BigDecimal smDeudoresAut;
	private BigDecimal smExcedidos;
	private Integer smDias;

	private List<LiquidacionNumerosDetalleBean> registrosNumeros;
	
	public LiquidacionNumerosBean(){
		 smAcreedores = new BigDecimal(0);
		 smDeudores = new BigDecimal(0);
		 smDeudoresAut = new BigDecimal(0);
		 smExcedidos = new BigDecimal(0);
		 smDias = 0;
	}

	public Date getFechaPeriodoDesde() {
		return fechaPeriodoDesde;
	}

	public void setFechaPeriodoDesde(Date fechaPeriodoDesde) {
		this.fechaPeriodoDesde = fechaPeriodoDesde;
	}

	public Date getFechaPeriodoHasta() {
		return fechaPeriodoHasta;
	}

	public void setFechaPeriodoHasta(Date fechaPeriodoHasta) {
		this.fechaPeriodoHasta = fechaPeriodoHasta;
	}

	public String getCodOperacion() {
		return codOperacion;
	}

	public void setCodOperacion(String codOperacion) {
		this.codOperacion = codOperacion;
	}

	public String getDescOperacion() {
		return descOperacion;
	}

	public void setDescOperacion(String descOperacion) {
		this.descOperacion = descOperacion;
	}

	public List<LiquidacionNumerosDetalleBean> getRegistrosNumeros() {
		return registrosNumeros;
	}

	public void setRegistrosNumeros(
			List<LiquidacionNumerosDetalleBean> registrosNumeros) {
		this.registrosNumeros = registrosNumeros;
	}

	public BigDecimal getSmAcreedores() {
		return smAcreedores;
	}

	public void setSmAcreedores(BigDecimal smAcreedores) {
		this.smAcreedores = smAcreedores;
	}

	public BigDecimal getSmDeudores() {
		return smDeudores;
	}

	public void setSmDeudores(BigDecimal smDeudores) {
		this.smDeudores = smDeudores;
	}

	public BigDecimal getSmDeudoresAut() {
		return smDeudoresAut;
	}

	public void setSmDeudoresAut(BigDecimal smDeudoresAut) {
		this.smDeudoresAut = smDeudoresAut;
	}

	public BigDecimal getSmExcedidos() {
		return smExcedidos;
	}

	public void setSmExcedidos(BigDecimal smExcedidos) {
		this.smExcedidos = smExcedidos;
	}

	public Integer getSmDias() {
		return smDias;
	}

	public void setSmDias(Integer smDias) {
		this.smDias = smDias;
	}
	
}