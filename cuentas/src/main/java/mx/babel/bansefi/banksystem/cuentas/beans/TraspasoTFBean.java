package mx.babel.bansefi.banksystem.cuentas.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * @author manuel.nieto
 *
 */
public class TraspasoTFBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private long idTransferencia;
	private Date fechaRecepcion;
	private Date fechaValor;
	
	private String origenEntidad;
	private String origenCentro;
	private String origenOrdenante;
	private Date origenFechaEmision;
	
	private String destinoCuenta;
	private String destinoCentro;
	private String destinoBeneficiario;
	
	private BigDecimal importe;	
	private String tipoTransferencia;
	private String codigoTipoTransferencia;
	private String concepto;
	private String claseTransaccion;
	private String codigoClaseTransaccion;
	
	private String referenciaOrdenante;
	private String referenciaBeneficiario;
	private String idBeneficiario;
	private String tipoIdBeneficiario;
	private String motivoDevolucion;
	private String codigoMotivoDevolucion;
	
	private int codigoTransferencia;	
	
	public int getCodigoTransferencia() {
		return codigoTransferencia;
	}
	public void setCodigoTransferencia(int codigoTransferencia) {
		this.codigoTransferencia = codigoTransferencia;
	}
	public String getCodigoTipoTransferencia() {
		return codigoTipoTransferencia;
	}
	public void setCodigoTipoTransferencia(String codigoTipoTransferencia) {
		this.codigoTipoTransferencia = codigoTipoTransferencia;
	}
	public String getCodigoClaseTransaccion() {
		return codigoClaseTransaccion;
	}
	public void setCodigoClaseTransaccion(String codigoClaseTransaccion) {
		this.codigoClaseTransaccion = codigoClaseTransaccion;
	}
	public String getTipoIdBeneficiario() {
		return tipoIdBeneficiario;
	}
	public void setTipoIdBeneficiario(String tipoIdBeneficiario) {
		this.tipoIdBeneficiario = tipoIdBeneficiario;
	}
	public String getCodigoMotivoDevolucion() {
		return codigoMotivoDevolucion;
	}
	public void setCodigoMotivoDevolucion(String codigoMotivoDevolucion) {
		this.codigoMotivoDevolucion = codigoMotivoDevolucion;
	}
	
	public long getIdTransferencia() {
		return idTransferencia;
	}
	public void setIdTransferencia(long idTransferencia) {
		this.idTransferencia = idTransferencia;
	}
	public Date getFechaRecepcion() {
		return fechaRecepcion;
	}
	public void setFechaRecepcion(Date fechaRecepcion) {
		this.fechaRecepcion = fechaRecepcion;
	}
	public Date getFechaValor() {
		return fechaValor;
	}
	public void setFechaValor(Date fechaValor) {
		this.fechaValor = fechaValor;
	}
	public String getOrigenEntidad() {
		return origenEntidad;
	}
	public void setOrigenEntidad(String origenEntidad) {
		this.origenEntidad = origenEntidad;
	}
	public String getOrigenCentro() {
		return origenCentro;
	}
	public void setOrigenCentro(String origenCentro) {
		this.origenCentro = origenCentro;
	}
	
	public String getOrigenOrdenante() {
		return origenOrdenante;
	}
	public void setOrigenOrdenante(String origenOrdenante) {
		this.origenOrdenante = origenOrdenante;
	}
	public Date getOrigenFechaEmision() {
		return origenFechaEmision;
	}
	public void setOrigenFechaEmision(Date origenFechaEmision) {
		this.origenFechaEmision = origenFechaEmision;
	}
	public String getDestinoCuenta() {
		return destinoCuenta;
	}
	public void setDestinoCuenta(String destinoCuenta) {
		this.destinoCuenta = destinoCuenta;
	}
	public String getDestinoCentro() {
		return destinoCentro;
	}
	public void setDestinoCentro(String destinoCentro) {
		this.destinoCentro = destinoCentro;
	}
	public String getDestinoBeneficiario() {
		return destinoBeneficiario;
	}
	public void setDestinoBeneficiario(String destinoBeneficiario) {
		this.destinoBeneficiario = destinoBeneficiario;
	}
	public BigDecimal getImporte() {
		return importe;
	}
	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}
	public String getTipoTransferencia() {
		return tipoTransferencia;
	}
	public void setTipoTransferencia(String tipoTransferencia) {
		this.tipoTransferencia = tipoTransferencia;
	}
	public String getConcepto() {
		return concepto;
	}
	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}
	public String getClaseTransaccion() {
		return claseTransaccion;
	}
	public void setClaseTransaccion(String claseTransaccion) {
		this.claseTransaccion = claseTransaccion;
	}
	public String getReferenciaOrdenante() {
		return referenciaOrdenante;
	}
	public void setReferenciaOrdenante(String referenciaOrdenante) {
		this.referenciaOrdenante = referenciaOrdenante;
	}
	public String getReferenciaBeneficiario() {
		return referenciaBeneficiario;
	}
	public void setReferenciaBeneficiario(String referenciaBeneficiario) {
		this.referenciaBeneficiario = referenciaBeneficiario;
	}
	public String getIdBeneficiario() {
		return idBeneficiario;
	}
	public void setIdBeneficiario(String idBeneficiario) {
		this.idBeneficiario = idBeneficiario;
	}
	public String getMotivoDevolucion() {
		return motivoDevolucion;
	}
	public void setMotivoDevolucion(String motivoDevolucion) {
		this.motivoDevolucion = motivoDevolucion;
	}
	
	

}
