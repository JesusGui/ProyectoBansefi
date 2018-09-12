package mx.babel.bansefi.banksystem.personas.beans;

import java.io.Serializable;
import java.util.Date;

import mx.babel.arq.catalogo.beans.CatalogoGeoBean;
import mx.babel.bansefi.banksystem.base.enums.EstadoListadosEnum;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Bean para Direcciones Registrales.
 * @author alejandro.pineda
 *
 */
public class DireccionRegistralBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer idInterno;
	
	private Integer numDir;
	
	private String entidad;
	
	private String codDatoResgistral;
	
	private String descripcionDato;
	
	private CatalogoGeoBean localidad;
	
	private String nombreLocalidad;
	
	private String numRegistro;
	
	private String numRegistral;
	
	private String tomo;
	
	private String libro;
	
	private String folio;
	
	private String numInscripcion;
	
	private String finca;
	
	private Date fExpedicion;
	
	private Integer actionType;
	
	private EstadoListadosEnum estado;
	
	private String respaldo;
	
	public DireccionRegistralBean(){
		
	}
	
	public Integer getIdInterno() {
		return idInterno;
	}

	public void setIdInterno(Integer idInterno) {
		this.idInterno = idInterno;
	}
	
	public String getEntidad() {
		return entidad;
	}

	public void setEntidad(String entidad) {
		this.entidad = entidad;
	}

	public String getCodDatoResgistral() {
		return codDatoResgistral;
	}

	public void setCodDatoResgistral(String codDatoResgistral) {
		this.codDatoResgistral = codDatoResgistral;
	}

	public String getDescripcionDato() {
		return descripcionDato;
	}

	public void setDescripcionDato(String descripcionDato) {
		this.descripcionDato = descripcionDato;
	}

	public CatalogoGeoBean getLocalidad() {
		return localidad;
	}

	public void setLocalidad(CatalogoGeoBean localidad) {
		this.localidad = localidad;
	}
	
	public String getNombreLocalidad() {
		return nombreLocalidad;
	}

	public void setNombreLocalidad(String nombreLocalidad) {
		this.nombreLocalidad = nombreLocalidad;
	}

	public String getNumRegistro() {
		return numRegistro;
	}

	public void setNumRegistro(String numRegistro) {
		this.numRegistro = numRegistro;
	}

	public String getNumRegistral() {
		return numRegistral;
	}

	public void setNumRegistral(String numRegistral) {
		this.numRegistral = numRegistral;
	}

	public String getTomo() {
		return tomo;
	}

	public void setTomo(String tomo) {
		this.tomo = tomo;
	}

	public String getLibro() {
		return libro;
	}

	public void setLibro(String libro) {
		this.libro = libro;
	}

	public String getFolio() {
		return folio;
	}

	public void setFolio(String folio) {
		this.folio = folio;
	}

	public String getNumInscripcion() {
		return numInscripcion;
	}

	public void setNumInscripcion(String numInscripcion) {
		this.numInscripcion = numInscripcion;
	}

	public String getFinca() {
		return finca;
	}

	public void setFinca(String finca) {
		this.finca = finca;
	}

	public Date getfExpedicion() {
		return fExpedicion;
	}

	public void setfExpedicion(Date fExpedicion) {
		this.fExpedicion = fExpedicion;
	}

	public Integer getNumDir() {
		return numDir;
	}

	public void setNumDir(Integer numDir) {
		this.numDir = numDir;
	}
	
	public Integer getActionType() {
		return actionType;
	}

	public void setActionType(Integer actionType) {
		this.actionType = actionType;
	}
	
	public EstadoListadosEnum getEstado() {
		return estado;
	}

	public void setEstado(EstadoListadosEnum estado) {
		this.estado = estado;
	}
	
	public String getRespaldo() {
		return respaldo;
	}

	public void setRespaldo(String respaldo) {
		this.respaldo = respaldo;
	}

	@Override
	public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

	@Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }
	
}
