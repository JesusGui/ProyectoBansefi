package mx.babel.bansefi.banksystem.base.beans;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Clase que define los atributos necesarios para la consulta de datos de demora
 * de una liquidación.
 * 
 * @author omar.marquez
 */
public class LiquidacionConceptoDemoraBean {

	private String indCodTramo;
	private String idpds;
	private String idparmcd;
	private Date fechaDesdeTramo;
	private Date fechaHastaTramo;
	private BigDecimal valorBase;
	private BigDecimal intereses; // CD_PCT
	private BigDecimal interesDevengado; // IMP_TRAMO
	private int referencia; // NUM_SUBAC
	private BigDecimal interesesFranquicia; // CD_PCT_F
	private BigDecimal valorBaseFranquicia; // VALOR_BASE_F
	private int numTramo;

	public String getIndCodTramo() {
		return indCodTramo;
	}

	public void setIndCodTramo(String indCodTramo) {
		this.indCodTramo = indCodTramo;
	}

	public String getIdpds() {
		return idpds;
	}

	public void setIdpds(String idpds) {
		this.idpds = idpds;
	}

	public String getIdparmcd() {
		return idparmcd;
	}

	public void setIdparmcd(String idparmcd) {
		this.idparmcd = idparmcd;
	}

	public Date getFechaDesdeTramo() {
		return fechaDesdeTramo;
	}

	public void setFechaDesdeTramo(Date fechaDesdeTramo) {
		this.fechaDesdeTramo = fechaDesdeTramo;
	}

	public Date getFechaHastaTramo() {
		return fechaHastaTramo;
	}

	public void setFechaHastaTramo(Date fechaHastaTramo) {
		this.fechaHastaTramo = fechaHastaTramo;
	}

	public BigDecimal getValorBase() {
		return valorBase;
	}

	public void setValorBase(BigDecimal valorBase) {
		this.valorBase = valorBase;
	}

	public BigDecimal getIntereses() {
		return intereses;
	}

	public void setIntereses(BigDecimal intereses) {
		this.intereses = intereses;
	}

	public BigDecimal getInteresDevengado() {
		return interesDevengado;
	}

	public void setInteresDevengado(BigDecimal interesDevengado) {
		this.interesDevengado = interesDevengado;
	}

	public int getReferencia() {
		return referencia;
	}

	public void setReferencia(int referencia) {
		this.referencia = referencia;
	}

	public BigDecimal getInteresesFranquicia() {
		return interesesFranquicia;
	}

	public void setInteresesFranquicia(BigDecimal interesesFranquicia) {
		this.interesesFranquicia = interesesFranquicia;
	}

	public BigDecimal getValorBaseFranquicia() {
		return valorBaseFranquicia;
	}

	public void setValorBaseFranquicia(BigDecimal valorBaseFranquicia) {
		this.valorBaseFranquicia = valorBaseFranquicia;
	}

	public int getNumTramo() {
		return numTramo;
	}

	public void setNumTramo(int numTramo) {
		this.numTramo = numTramo;
	}

}