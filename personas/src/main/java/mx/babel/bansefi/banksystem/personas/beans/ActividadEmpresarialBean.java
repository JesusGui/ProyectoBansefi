package mx.babel.bansefi.banksystem.personas.beans;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Clase Bean para la ventana de Actividad Empresarial.
 * 
 * @author alejandro.pineda
 * 
 */
public class ActividadEmpresarialBean implements Serializable {

	public static final long serialVersionUID = 1L;

	private Integer idInterno;

	private String tipoEmpresa;

	private String sectorEconomico;

	private Integer numEmpleados;

	private Integer numSucursales;

	private Integer numSocios;

	private Double cuotaMercado;

	private Date inicioVacacional;

	private Date finVacacional;

	private Date inicioSociedad;

	private Date finSociedad;

	private String diaCierreEjercicio;

	private String mesCierreEjercicio;

	private String fechaCierreEjercicio;

	private Date fechaTransformacion;

	private Date fechaPresentacion;

	private String jornadaLaboral;

	private String presentado;

	private String calificacion;

	private Date fechaCalificacion;

	private String sociedadCalif;

	public ActividadEmpresarialBean() {

	}

	public Integer getIdInterno() {
		return idInterno;
	}

	public void setIdInterno(Integer idInterno) {
		this.idInterno = idInterno;
	}

	public String getTipoEmpresa() {
		return tipoEmpresa;
	}

	public void setTipoEmpresa(String tipoEmpresa) {
		this.tipoEmpresa = tipoEmpresa;
	}

	public String getSectorEconomico() {
		return sectorEconomico;
	}

	public void setSectorEconomico(String sectorEconomico) {
		this.sectorEconomico = sectorEconomico;
	}

	public Integer getNumEmpleados() {
		return numEmpleados;
	}

	public void setNumEmpleados(Integer numEmpleados) {
		this.numEmpleados = numEmpleados;
	}

	public Integer getNumSucursales() {
		return numSucursales;
	}

	public void setNumSucursales(Integer numSucursales) {
		this.numSucursales = numSucursales;
	}

	public Integer getNumSocios() {
		return numSocios;
	}

	public void setNumSocios(Integer numSocios) {
		this.numSocios = numSocios;
	}

	public Double getCuotaMercado() {
		return cuotaMercado;
	}

	public void setCuotaMercado(Double cuotaMercado) {
		this.cuotaMercado = cuotaMercado;
	}

	public Date getInicioVacacional() {
		return inicioVacacional;
	}

	public void setInicioVacacional(Date inicioVacacional) {
		this.inicioVacacional = inicioVacacional;
	}

	public Date getFinVacacional() {
		return finVacacional;
	}

	public void setFinVacacional(Date finVacacional) {
		this.finVacacional = finVacacional;
	}

	public Date getInicioSociedad() {
		return inicioSociedad;
	}

	public void setInicioSociedad(Date inicioSociedad) {
		this.inicioSociedad = inicioSociedad;
	}

	public Date getFinSociedad() {
		return finSociedad;
	}

	public void setFinSociedad(Date finSociedad) {
		this.finSociedad = finSociedad;
	}

	public String getDiaCierreEjercicio() {
		return diaCierreEjercicio;
	}

	public void setDiaCierreEjercicio(String diaCierreEjercicio) {
		this.diaCierreEjercicio = diaCierreEjercicio;
	}

	public String getMesCierreEjercicio() {
		return mesCierreEjercicio;
	}

	public void setMesCierreEjercicio(String mesCierreEjercicio) {
		this.mesCierreEjercicio = mesCierreEjercicio;
	}

	public String getFechaCierreEjercicio() {
		return fechaCierreEjercicio;
	}

	public void setFechaCierreEjercicio(String fechaCierreEjercicio) {
		this.fechaCierreEjercicio = fechaCierreEjercicio;
	}

	public Date getFechaTransformacion() {
		return fechaTransformacion;
	}

	public void setFechaTransformacion(Date fechaTransformacion) {
		this.fechaTransformacion = fechaTransformacion;
	}

	public Date getFechaPresentacion() {
		return fechaPresentacion;
	}

	public void setFechaPresentacion(Date fechaPresentacion) {
		this.fechaPresentacion = fechaPresentacion;
	}

	public String getJornadaLaboral() {
		return jornadaLaboral;
	}

	public void setJornadaLaboral(String jornadaLaboral) {
		this.jornadaLaboral = jornadaLaboral;
	}

	public String getPresentado() {
		return presentado;
	}

	public void setPresentado(String presentado) {
		this.presentado = presentado;
	}

	public String getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(String calificacion) {
		this.calificacion = calificacion;
	}

	public Date getFechaCalificacion() {
		return fechaCalificacion;
	}

	public void setFechaCalificacion(Date fechaCalificacion) {
		this.fechaCalificacion = fechaCalificacion;
	}

	public String getSociedadCalif() {
		return sociedadCalif;
	}

	public void setSociedadCalif(String sociedadCalif) {
		this.sociedadCalif = sociedadCalif;
	}

	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}

	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

}
