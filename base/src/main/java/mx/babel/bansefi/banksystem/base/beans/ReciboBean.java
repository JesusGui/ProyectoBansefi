package mx.babel.bansefi.banksystem.base.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Bean para el manejo de los recibos no domiciliados
 * @author manuel.nieto
 *
 */
public class ReciboBean implements Serializable{

	private static final long serialVersionUID = -6184679620089322790L;
	
	private String emisora;
	private String referencia;
	
	private String longitudReferencia;

	private String tipoComision;
	private String comisionConCuenta;
	private String comisionSinCuenta;
	
	private String concepto;
	/**
	 * 1: efectivo
	 * 2: cargo a cuenta
	 */
	private String formaCobro;
	
	private String numeroCuenta;
	private String titular;
	private String centro;
	
	private BigDecimal importeEntregado;
	private BigDecimal importeComision;
	private BigDecimal importeIva;
	private BigDecimal importeSubtotal;
	private BigDecimal importeTotal;
	
	private Date fechaOperacion;
	private Date horaOperacion;
	
	private String idTransaccion;
	
	private String numeroOperacion;
	
	private String formaPago;
	
	
	/**
	 * @return the emisora
	 */
	public String getEmisora() {
		return emisora;
	}
	/**
	 * @param emisora the emisora to set
	 */
	public void setEmisora(String emisora) {
		this.emisora = emisora;
	}
	/**
	 * @return the referencia
	 */
	public String getReferencia() {
		return referencia;
	}
	/**
	 * @param referencia the referencia to set
	 */
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}
	/**
	 * @return the longitudReferencia
	 */
	public String getLongitudReferencia() {
		return longitudReferencia;
	}
	/**
	 * @param longitudReferencia the longitudReferencia to set
	 */
	public void setLongitudReferencia(String longitudReferencia) {
		this.longitudReferencia = longitudReferencia;
	}
	public String getTipoComision() {
		return tipoComision;
	}
	public void setTipoComision(String tipoComision) {
		this.tipoComision = tipoComision;
	}
	public String getComisionConCuenta() {
		return comisionConCuenta;
	}
	public void setComisionConCuenta(String comisionConCuenta) {
		this.comisionConCuenta = comisionConCuenta;
	}
	public String getComisionSinCuenta() {
		return comisionSinCuenta;
	}
	public void setComisionSinCuenta(String comisionSinCuenta) {
		this.comisionSinCuenta = comisionSinCuenta;
	}

	public String getConcepto() {
		return concepto;
	}
	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}
	/**
	 * 1: efectivo
	 * 2: cargo a cuenta
	 * @return boolean
	 */
	public String getFormaCobro() {
		return formaCobro;
	}
	/**
	 * 1: efectivo
	 * 2: cargo a cuenta
	 * @param formaCobro
	 */
	public void setFormaCobro(String formaCobro) {
		this.formaCobro = formaCobro;
	}
	public String getNumeroCuenta() {
		return numeroCuenta;
	}
	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}
	public String getTitular() {
		return titular;
	}
	public void setTitular(String titular) {
		this.titular = titular;
	}
	public String getCentro() {
		return centro;
	}
	public void setCentro(String centro) {
		this.centro = centro;
	}
	public BigDecimal getImporteEntregado() {
		return importeEntregado;
	}
	public void setImporteEntregado(BigDecimal importeEntregado) {
		this.importeEntregado = importeEntregado;
	}
	public BigDecimal getImporteComision() {
		return importeComision;
	}
	public void setImporteComision(BigDecimal importeComision) {
		this.importeComision = importeComision;
	}
	public BigDecimal getImporteIva() {
		return importeIva;
	}
	public void setImporteIva(BigDecimal importeIva) {
		this.importeIva = importeIva;
	}
	public BigDecimal getImporteTotal() {
		return importeTotal;
	}
	public void setImporteTotal(BigDecimal importeTotal) {
		this.importeTotal = importeTotal;
	}
	public Date getFechaOperacion() {
		return fechaOperacion;
	}
	public void setFechaOperacion(Date fechaOperacion) {
		this.fechaOperacion = fechaOperacion;
	}
	public Date getHoraOperacion() {
		return horaOperacion;
	}
	public void setHoraOperacion(Date horaOperacion) {
		this.horaOperacion = horaOperacion;
	}
	public String getIdTransaccion() {
		return idTransaccion;
	}
	public void setIdTransaccion(String idTransaccion) {
		this.idTransaccion = idTransaccion;
	}
	public String getNumeroOperacion() {
		return numeroOperacion;
	}
	public void setNumeroOperacion(String numeroOperacion) {
		this.numeroOperacion = numeroOperacion;
	}
	public String getFormaPago() {
		return formaPago;
	}
	public void setFormaPago(String formaPago) {
		this.formaPago = formaPago;
	}
	public BigDecimal getImporteSubtotal() {
		return importeSubtotal;
	}
	public void setImporteSubtotal(BigDecimal importeSubtotal) {
		this.importeSubtotal = importeSubtotal;
	}
		
}
