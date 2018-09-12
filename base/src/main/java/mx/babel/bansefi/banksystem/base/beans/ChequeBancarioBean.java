package mx.babel.bansefi.banksystem.base.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Clase que define los atributos de un cheque bancario.
 * 
 * @author omar.marquez
 */
public class ChequeBancarioBean implements Serializable {

	private static final long serialVersionUID = -875531137003961356L;

	private Integer numOperacion;
	private String tipoCheque;
	private String acuerdoLibrado;
	private BigDecimal importe;
	private String numDocumento;
	private Integer codSeguridad;
	private String digitoIntercambio;
	private String digitoPremarcado;
	private Date fechaPresentacion;
	private String clausulas;
	private String indSegPres;

	public Integer getNumOperacion() {
		return numOperacion;
	}

	public void setNumOperacion(Integer numOperacion) {
		this.numOperacion = numOperacion;
	}

	public String getTipoCheque() {
		return tipoCheque;
	}

	public void setTipoCheque(String tipoCheque) {
		this.tipoCheque = tipoCheque;
	}

	public String getAcuerdoLibrado() {
		return acuerdoLibrado;
	}

	public void setAcuerdoLibrado(String acuerdoLibrado) {
		this.acuerdoLibrado = acuerdoLibrado;
	}

	public BigDecimal getImporte() {
		return importe;
	}

	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}

	public String getNumDocumento() {
		return numDocumento;
	}

	public void setNumDocumento(String numDocumento) {
		this.numDocumento = numDocumento;
	}

	public Integer getCodSeguridad() {
		return codSeguridad;
	}

	public void setCodSeguridad(Integer codSeguridad) {
		this.codSeguridad = codSeguridad;
	}

	public String getDigitoIntercambio() {
		return digitoIntercambio;
	}

	public void setDigitoIntercambio(String digitoIntercambio) {
		this.digitoIntercambio = digitoIntercambio;
	}

	public String getDigitoPremarcado() {
		return digitoPremarcado;
	}

	public void setDigitoPremarcado(String digitoPremarcado) {
		this.digitoPremarcado = digitoPremarcado;
	}

	public Date getFechaPresentacion() {
		return fechaPresentacion;
	}

	public void setFechaPresentacion(Date fechaPresentacion) {
		this.fechaPresentacion = fechaPresentacion;
	}

	public String getClausulas() {
		return clausulas;
	}

	public void setClausulas(String clausulas) {
		this.clausulas = clausulas;
	}

	public String getIndSegPres() {
		return indSegPres;
	}

	public void setIndSegPres(String indSegPres) {
		this.indSegPres = indSegPres;
	}

}