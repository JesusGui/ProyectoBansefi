package mx.babel.bansefi.banksystem.base.beans.domain;

import java.io.Serializable;
import java.util.Date;

import mx.babel.arq.catalogo.beans.CatalogoBean;
import mx.babel.bansefi.banksystem.base.enums.EstadoListadosEnum;

/**
 * Clase que define los atributos de una persona que podrá ser relacionada a un
 * cliente (titular).
 * 
 * @author omar.marquez
 * 
 */
public class PersonaRelacionadaBean implements Serializable {

	private static final long serialVersionUID = -2192372315661812082L;

	private Integer idInterna;
	private String idExterna;
	private String codIdExterna;
	private String nombre;
	private CatalogoBean relacionSeleccionada;
	private String observaciones;
	private Date fechaCreacion;
	private Date fechaInicio;
	private Date fechaFin;
	private EstadoListadosEnum estado;
	private String respaldo;

	/**
	 * Constructor.
	 */
	public PersonaRelacionadaBean() {
		this.idInterna = null;
		this.idExterna = null;
		this.codIdExterna = null;
		this.nombre = null;
		this.relacionSeleccionada = new CatalogoBean();
		this.observaciones = null;
		this.fechaCreacion = null;
		this.fechaInicio = null;
		this.fechaFin = null;
		this.respaldo = null;
	}

	/**
	 * Método que devuelve la ID interna de la persona a relacionar.
	 * 
	 * @return idInterna
	 */
	public Integer getIdInterna() {
		return idInterna;
	}

	/**
	 * Método que establece la ID interna de la persona a relacionar.
	 * 
	 * @param idInterna
	 */
	public void setIdInterna(Integer idInterna) {
		if (idInterna == 0) {
			this.idInterna = null;
		} else {
			this.idInterna = idInterna;
		}
	}

	/**
	 * Método que devuelve la ID externa de la persona a relacionar.
	 * 
	 * @return idExterna
	 */
	public String getIdExterna() {
		return idExterna;
	}

	/**
	 * Método que establece la ID externa de la persona a relacionar.
	 * 
	 * @param idExterna
	 */
	public void setIdExterna(String idExterna) {
		this.idExterna = idExterna;
	}

	/**
	 * Método que devuelve el código de la ID externa de la persona a
	 * relacionar.
	 * 
	 * @return codIdExterna
	 */
	public String getCodIdExterna() {
		return codIdExterna;
	}

	/**
	 * Método que establece el código de la ID externa de la persona a
	 * relacionar.
	 * 
	 * @param codIdExterna
	 */
	public void setCodIdExterna(String codIdExterna) {
		this.codIdExterna = codIdExterna;
	}

	/**
	 * Método que devuelve el nombre de la persona a relacionar.
	 * 
	 * @return nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Método que establece el nombre de la persona a relacionar.
	 * 
	 * @param nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Método que devuelve la selección del catálogo.
	 * 
	 * @return relacionSeleccionada
	 */
	public CatalogoBean getRelacionSeleccionada() {
		return relacionSeleccionada;
	}

	/**
	 * Método que establece la selección del catálogo.
	 * 
	 * @param relacionSeleccionada
	 */
	public void setRelacionSeleccionada(CatalogoBean relacionSeleccionada) {
		this.relacionSeleccionada = relacionSeleccionada;
	}

	/**
	 * Método que devuelve una cadena de texto con las observaciones.
	 * 
	 * @return observaciones
	 */
	public String getObservaciones() {
		return observaciones;
	}

	/**
	 * Método que establece una cadena de texto con las observaciones.
	 * 
	 * @param observaciones
	 */
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	/**
	 * Método que devuelve la fecha de creación de la relación entre el cliente
	 * y la persona.
	 * 
	 * @return fechaCreacion
	 */
	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	/**
	 * Método que establece la fecha de creación de la relación entre el cliente
	 * y la persona.
	 * 
	 * @param fechaCreacion
	 */
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	/**
	 * Método que devuelve la fecha de inicio.
	 * 
	 * @return fechaInicio
	 */
	public Date getFechaInicio() {
		return fechaInicio;
	}

	/**
	 * Método que establece la fecha de inicio.
	 * 
	 * @param fechaInicio
	 */
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	/**
	 * Método que devuelve la fecha de fin.
	 * 
	 * @return fechaFin
	 */
	public Date getFechaFin() {
		return fechaFin;
	}

	/**
	 * Método que establece la fecha de fin.
	 * 
	 * @param fechaFin
	 */
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	/**
	 * Método que devuelve el estado del bean para tratar los listados y los
	 * diferentes estilos. Los valores del enumerado pueden ser: ACTIVO,
	 * INACTIVO, MODIFICADO y ELIMINADO.
	 * 
	 * @return estado
	 */
	public EstadoListadosEnum getEstado() {
		return estado;
	}

	/**
	 * Método que establece el estado del bean para poder tratar los listados y
	 * los diferentes estilos.
	 * 
	 * @param estado
	 */
	public void setEstado(EstadoListadosEnum estado) {
		this.estado = estado;
	}

	/**
	 * Método que devuelve una cadena que representa un objeto
	 * PersonaRelacionadaBean serializado mediante JSON.
	 * 
	 * @return respaldo
	 */
	public String getRespaldo() {
		return respaldo;
	}

	/**
	 * Método que establece en una cadena de texto, el resultado de serializar
	 * un objeto mediante JSON.
	 * 
	 * @param respaldo
	 */
	public void setRespaldo(String respaldo) {
		this.respaldo = respaldo;
	}

}