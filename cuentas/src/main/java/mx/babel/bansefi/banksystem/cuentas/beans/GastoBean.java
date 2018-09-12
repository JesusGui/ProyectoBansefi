package mx.babel.bansefi.banksystem.cuentas.beans;

import java.math.BigDecimal;

public class GastoBean {

	private String descripcionGasto;
	private BigDecimal importe;
	private String codigoDescripcion;
	private String estatusGastos;
	private Boolean imputar;
	private Boolean eliminar;
	private int numSecuencia;
	private String ind1;
	private String ind2;
	private String ind3;
	private Long cuentaAsociada;
	private String plazaBancaria;
	private String codDig;
	private String moneda;
	
	
	
	public String getMoneda() {
		return moneda;
	}
	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}
	public String getCodDig() {
		return codDig;
	}
	public void setCodDig(String codDig) {
		this.codDig = codDig;
	}
	public String getPlazaBancaria() {
		return plazaBancaria;
	}
	public void setPlazaBancaria(String plazaBancaria) {
		this.plazaBancaria = plazaBancaria;
	}
	public Long getCuentaAsociada() {
		return cuentaAsociada;
	}
	public void setCuentaAsociada(Long cuentaAsociada) {
		this.cuentaAsociada = cuentaAsociada;
	}
	public String getInd1() {
		return ind1;
	}
	public void setInd1(String ind1) {
		this.ind1 = ind1;
	}
	public String getInd2() {
		return ind2;
	}
	public void setInd2(String ind2) {
		this.ind2 = ind2;
	}
	public String getInd3() {
		return ind3;
	}
	public void setInd3(String ind3) {
		this.ind3 = ind3;
	}
	public int getNumSecuencia() {
		return numSecuencia;
	}
	public void setNumSecuencia(int numSecuencia) {
		this.numSecuencia = numSecuencia;
	}
	public String getDescripcionGasto() {
		return descripcionGasto;
	}
	public void setDescripcionGasto(String descripcionGasto) {
		this.descripcionGasto = descripcionGasto;
	}
	public BigDecimal getImporte() {
		return importe;
	}
	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}
	public String getCodigoDescripcion() {
		return codigoDescripcion;
	}
	public void setCodigoDescripcion(String codigoDescripcion) {
		this.codigoDescripcion = codigoDescripcion;
	}
	public String getEstatusGastos() {
		return estatusGastos;
	}
	public void setEstatusGastos(String estatusGastos) {
		this.estatusGastos = estatusGastos;
	}
	public Boolean getImputar() {
		return imputar;
	}
	public void setImputar(Boolean imputar) {
		this.imputar = imputar;
	}
	public Boolean getEliminar() {
		return eliminar;
	}
	public void setEliminar(Boolean eliminar) {
		this.eliminar = eliminar;
	}
	
	
	
}
