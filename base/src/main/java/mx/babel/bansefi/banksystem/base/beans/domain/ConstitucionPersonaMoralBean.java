package mx.babel.bansefi.banksystem.base.beans.domain;

import java.math.BigDecimal;
import java.util.Date;

import mx.babel.arq.catalogo.beans.CatalogoGeoBean;

public class ConstitucionPersonaMoralBean extends ClientePersonaMoralBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2544653516978452428L;
	
	private int numDirRegistro;
	
	private String tipoRegistro;

	public String observaciones;
	
	private CatalogoGeoBean municipioRegistro;
	
	private Date fechaConstitucion;
	
	private Date fechaCierre;
	
	private String numRegistro;
	
	private String numRegistral;
	
	private String tomo;
	
	private String libro;
	
	private String folio;
	
	private String numInscripcion;
	
	private String finca;
	
	private Date fechaExpedicion;
	
	private Date fechaAlta;
	
	private Date fechaBaja;
	
	private String observacionesPoderLegal;
	
	private BigDecimal importeCapitalSocial;
	
	private String distribucion;
	
	private String idioma;
	
	private String numOficina;
	
	private boolean correspondencia;
	
	private boolean correspondenciaOficina;

	/**
	 * @return the tipoRegistro
	 */
	public String getTipoRegistro() {
		return tipoRegistro;
	}

	public int getNumDirRegistro() {
		return numDirRegistro;
	}

	public void setNumDirRegistro(int numDirRegistro) {
		this.numDirRegistro = numDirRegistro;
	}

	/**
	 * @param tipoRegistro the tipoRegistro to set
	 */
	public void setTipoRegistro(String tipoRegistro) {
		this.tipoRegistro = tipoRegistro;
	}
	
	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	/**
	 * @return the municipioRegistro
	 */
	public CatalogoGeoBean getMunicipioRegistro() {
		return municipioRegistro;
	}

	/**
	 * @param municipioRegistro the municipioRegistro to set
	 */
	public void setMunicipioRegistro(CatalogoGeoBean municipioRegistro) {
		this.municipioRegistro = municipioRegistro;
	}

	/**
	 * @return the fechaConstitucion
	 */
	public Date getFechaConstitucion() {
		return fechaConstitucion;
	}

	/**
	 * @param fechaConstitucion the fechaConstitucion to set
	 */
	public void setFechaConstitucion(Date fechaConstitucion) {
		this.fechaConstitucion = fechaConstitucion;
	}

	/**
	 * @return the fechaCierre
	 */
	public Date getFechaCierre() {
		return fechaCierre;
	}

	/**
	 * @param fechaCierre the fechaCierre to set
	 */
	public void setFechaCierre(Date fechaCierre) {
		this.fechaCierre = fechaCierre;
	}

	/**
	 * @return the numRegistro
	 */
	public String getNumRegistro() {
		return numRegistro;
	}

	/**
	 * @param numRegistro the numRegistro to set
	 */
	public void setNumRegistro(String numRegistro) {
		this.numRegistro = numRegistro;
	}

	/**
	 * @return the numRegistral
	 */
	public String getNumRegistral() {
		return numRegistral;
	}

	/**
	 * @param numRegistral the numRegistral to set
	 */
	public void setNumRegistral(String numRegistral) {
		this.numRegistral = numRegistral;
	}

	/**
	 * @return the tomo
	 */
	public String getTomo() {
		return tomo;
	}

	/**
	 * @param tomo the tomo to set
	 */
	public void setTomo(String tomo) {
		this.tomo = tomo;
	}

	/**
	 * @return the libro
	 */
	public String getLibro() {
		return libro;
	}

	/**
	 * @param libro the libro to set
	 */
	public void setLibro(String libro) {
		this.libro = libro;
	}

	/**
	 * @return the folio
	 */
	public String getFolio() {
		return folio;
	}

	/**
	 * @param folio the folio to set
	 */
	public void setFolio(String folio) {
		this.folio = folio;
	}

	/**
	 * @return the numInscripcion
	 */
	public String getNumInscripcion() {
		return numInscripcion;
	}

	/**
	 * @param numInscripcion the numInscripcion to set
	 */
	public void setNumInscripcion(String numInscripcion) {
		this.numInscripcion = numInscripcion;
	}

	/**
	 * @return the finca
	 */
	public String getFinca() {
		return finca;
	}

	/**
	 * @param finca the finca to set
	 */
	public void setFinca(String finca) {
		this.finca = finca;
	}

	/**
	 * @return the fechaExpedicion
	 */
	public Date getFechaExpedicion() {
		return fechaExpedicion;
	}

	/**
	 * @param fechaExpedicion the fechaExpedicion to set
	 */
	public void setFechaExpedicion(Date fechaExpedicion) {
		this.fechaExpedicion = fechaExpedicion;
	}

	/**
	 * @return the fechaAlta
	 */
	public Date getFechaAlta() {
		return fechaAlta;
	}

	/**
	 * @param fechaAlta the fechaAlta to set
	 */
	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	/**
	 * @return the fechaBaja
	 */
	public Date getFechaBaja() {
		return fechaBaja;
	}

	/**
	 * @param fechaBaja the fechaBaja to set
	 */
	public void setFechaBaja(Date fechaBaja) {
		this.fechaBaja = fechaBaja;
	}

	/**
	 * @return the observacionesPoderLegal
	 */
	public String getObservacionesPoderLegal() {
		return observacionesPoderLegal;
	}

	/**
	 * @param observacionesPoderLegal the observacionesPoderLegal to set
	 */
	public void setObservacionesPoderLegal(String observacionesPoderLegal) {
		this.observacionesPoderLegal = observacionesPoderLegal;
	}

	/**
	 * @return the importeCapitalSocial
	 */
	public BigDecimal getImporteCapitalSocial() {
		return importeCapitalSocial;
	}

	/**
	 * @param importeCapitalSocial the importeCapitalSocial to set
	 */
	public void setImporteCapitalSocial(BigDecimal importeCapitalSocial) {
		this.importeCapitalSocial = importeCapitalSocial;
	}

	/**
	 * @return the observacionesCapitalSocial
	 */
	public String getDistribucion() {
		return distribucion;
	}

	/**
	 * @param observacionesCapitalSocial the observacionesCapitalSocial to set
	 */
	public void setDistribucion(String distribucion) {
		this.distribucion = distribucion;
	}

	/**
	 * @return the idioma
	 */
	public String getIdioma() {
		return idioma;
	}

	/**
	 * @param idioma the idioma to set
	 */
	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}
	
	
	public String getNumOficina() {
		return numOficina;
	}

	public void setNumOficina(String numOficina) {
		this.numOficina = numOficina;
	}

	public boolean isCorrespondencia() {
		return correspondencia;
	}

	public void setCorrespondencia(boolean correspondencia) {
		this.correspondencia = correspondencia;
	}

	public boolean isCorrespondenciaOficina() {
		return correspondenciaOficina;
	}

	public void setCorrespondenciaOficina(boolean correspondenciaOficina) {
		this.correspondenciaOficina = correspondenciaOficina;
	}

}
