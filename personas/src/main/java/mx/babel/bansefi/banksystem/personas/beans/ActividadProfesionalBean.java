package mx.babel.bansefi.banksystem.personas.beans;

import java.io.Serializable;
import java.util.Date;

import mx.babel.bansefi.banksystem.base.enums.EstadoListadosEnum;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Bean de Actividad Profesional para la ventana de Datos Económicos.
 * @author alejandro.pineda
 *
 */
public class ActividadProfesionalBean implements Serializable{
	
	public static final long serialVersionUID = 1L;
	
	private Integer idInterno;
	
	private String entidad;
	
	private Integer numActProfIn;
	
	private String codActividadProf;
	
	private String descripcionActividad;
	
	private Date fechaAntLaboral;
	
	private String idEmpresa;
	
	private String nombreEmpresa;
	
	private String codTipoContrato;
	
	private String cargo;
	
	private Date fechaAntPuesto;
	
	private String nivelSalarial;
	
	private String duracion;
	
	private String continuidad;
	
	private String jornada;
	
	private String dedicacion;
	
	private boolean consultaDetalle;
	
	private EstadoListadosEnum estado;
	
	private String respaldo;
	
	public ActividadProfesionalBean(){
		
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
	
	public Integer getNumActProfIn() {
		return numActProfIn;
	}

	public void setNumActProfIn(Integer numActProfIn) {
		this.numActProfIn = numActProfIn;
	}

	public String getCodActividadProf() {
		return codActividadProf;
	}

	public void setCodActividadProf(String codActividadProf) {
		this.codActividadProf = codActividadProf;
	}
	
	public String getDescripcionActividad() {
		return descripcionActividad;
	}

	public void setDescripcionActividad(String descripcionActividad) {
		this.descripcionActividad = descripcionActividad;
	}

	public Date getFechaAntLaboral() {
		return fechaAntLaboral;
	}

	public void setFechaAntLaboral(Date fechaAntLaboral) {
		this.fechaAntLaboral = fechaAntLaboral;
	}

	public String getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(String idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public String getNombreEmpresa() {
		return nombreEmpresa;
	}

	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}

	public String getCodTipoContrato() {
		return codTipoContrato;
	}

	public void setCodTipoContrato(String codTipoContrato) {
		this.codTipoContrato = codTipoContrato;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public Date getFechaAntPuesto() {
		return fechaAntPuesto;
	}

	public void setFechaAntPuesto(Date fechaAntPuesto) {
		this.fechaAntPuesto = fechaAntPuesto;
	}

	public String getNivelSalarial() {
		return nivelSalarial;
	}

	public void setNivelSalarial(String nivelSalarial) {
		this.nivelSalarial = nivelSalarial;
	}

	public String getDuracion() {
		return duracion;
	}

	public void setDuracion(String duracion) {
		this.duracion = duracion;
	}

	public String getContinuidad() {
		return continuidad;
	}

	public void setContinuidad(String continuidad) {
		this.continuidad = continuidad;
	}

	public String getJornada() {
		return jornada;
	}

	public void setJornada(String jornada) {
		this.jornada = jornada;
	}

	public String getDedicacion() {
		return dedicacion;
	}

	public void setDedicacion(String dedicacion) {
		this.dedicacion = dedicacion;
	}
	
	public boolean isConsultaDetalle() {
		return consultaDetalle;
	}

	public void setConsultaDetalle(boolean consultaDetalle) {
		this.consultaDetalle = consultaDetalle;
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
