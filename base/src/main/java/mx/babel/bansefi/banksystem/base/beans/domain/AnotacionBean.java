package mx.babel.bansefi.banksystem.base.beans.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Clase para definir el modelo de Anotaciones
 * @author javier.martinnino
 * 
 */
public class AnotacionBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private long numero;
	private String tipo;
	private String dirigidoA;
	private List<String> identificadores;
	private List<String> identificadoresDescripciones;
	private String prioridad;
	private String subcodigo;
	private String area;
	private Date fechaInicio;
	private Date fechaCierre;
	private String descripcionCorta;
	private String descripcionLarga;
	private String codEstadoAnotacion;
	private String idEmpleado;
	private String nombreEmpleado;
private Boolean registrada;
	
	
	
	public Boolean getRegistrada() {
		return registrada;
	}

	public void setRegistrada(Boolean registrada) {
		this.registrada = registrada;
	}
	
	private String identificadorBusqueda;
	private List<AnotacionBean> respuestas;
	
	private List<String> relacionados;
	
	public long getNumero() {
		return numero;
	}

	public void setNumero(long numero) {
		this.numero = numero;
	}

	public String getTipo() {
		return tipo;
	}
	
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public String getDirigidoA() {
		return dirigidoA;
	}
	
	public void setDirigidoA(String dirigidoA) {
		this.dirigidoA = dirigidoA;
	}
	
	public List<String> getIdentificadores() {
		return identificadores;
	}
	
	public void setIdentificadores(List<String> identificadores) {
		this.identificadores = identificadores;
	}
	
	/**
	 * @return the identificadoresDescripciones
	 */
	public List<String> getIdentificadoresDescripciones() {
		return identificadoresDescripciones;
	}

	/**
	 * @param identificadoresDescripciones the identificadoresDescripciones to set
	 */
	public void setIdentificadoresDescripciones(
			List<String> identificadoresDescripciones) {
		this.identificadoresDescripciones = identificadoresDescripciones;
	}

	public String getPrioridad() {
		return prioridad;
	}
	
	public void setPrioridad(String prioridad) {
		this.prioridad = prioridad;
	}
	
	public String getSubcodigo() {
		return subcodigo;
	}
	
	public void setSubcodigo(String subcodigo) {
		this.subcodigo = subcodigo;
	}
	
	public String getArea() {
		return area;
	}
	
	public void setArea(String area) {
		this.area = area;
	}
	
	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaCierre() {
		return fechaCierre;
	}
	
	public void setFechaCierre(Date fechaCierre) {
		this.fechaCierre = fechaCierre;
	}
	
	public String getDescripcionCorta() {
		return descripcionCorta;
	}
	
	public void setDescripcionCorta(String descripcionCorta) {
		this.descripcionCorta = descripcionCorta;
	}
	
	public String getDescripcionLarga() {
		return descripcionLarga;
	}
	
	public void setDescripcionLarga(String descripcionLarga) {
		this.descripcionLarga = descripcionLarga;
	}

	public String getCodEstadoAnotacion() {
		return codEstadoAnotacion;
	}

	public void setCodEstadoAnotacion(String codEstadoAnotacion) {
		this.codEstadoAnotacion = codEstadoAnotacion;
	}

	public String getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(String idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	public String getNombreEmpleado() {
		return nombreEmpleado;
	}

	public void setNombreEmpleado(String nombreEmpleado) {
		this.nombreEmpleado = nombreEmpleado;
	}

	public String getIdentificadorBusqueda() {
		return identificadorBusqueda;
	}

	public void setIdentificadorBusqueda(String identificadorBusqueda) {
		this.identificadorBusqueda = identificadorBusqueda;
	}

	/**
	 * @return Atributo respuestas
	 */
	public List<AnotacionBean> getRespuestas() {
		return respuestas;
	}

	/**
	 * @param respuestas Atributo respuestas a definir
	 */
	public void setRespuestas(List<AnotacionBean> respuestas) {
		this.respuestas = respuestas;
	}

	public List<String> getRelacionados() {
		return relacionados;
	}

	public void setRelacionados(List<String> relacionados) {
		this.relacionados = relacionados;
	}
}
