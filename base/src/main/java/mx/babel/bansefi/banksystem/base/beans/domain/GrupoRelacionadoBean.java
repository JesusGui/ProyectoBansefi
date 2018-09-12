package mx.babel.bansefi.banksystem.base.beans.domain;

import java.io.Serializable;

import mx.babel.bansefi.banksystem.base.enums.EstadoListadosEnum;

/**
 * Clase que define los atributos de un grupo que podrá ser relacionado a un
 * cliente (titular).
 * 
 * @author omar.marquez
 * 
 */
public class GrupoRelacionadoBean implements Serializable{

	private static final long serialVersionUID = -5360742082262717759L;
	
	private Integer idInterna;
	private String idExterna;
	private String tipoGrupo;
	private String nombre;
	private String motivoBaja;
	private EstadoListadosEnum estado;

	/**
	 * Constructor.
	 */
	public GrupoRelacionadoBean() {
		this.idInterna = null;
		this.idExterna = null;
		this.tipoGrupo = null;
		this.nombre = null;
		this.motivoBaja = null;
	}

	/**
	 * Método que devuelve la ID interna del grupo a relacionar.
	 * 
	 * @return idInterna
	 */
	public Integer getIdInterna() {
		return idInterna;
	}

	/**
	 * Método que establece la ID interna del grupo a relacionar.
	 * 
	 * @param idInterna
	 */
	public void setIdInterna(Integer idInterna) {
		this.idInterna = idInterna;
	}
	
	/**
	 * Método que devuelve la ID externa del grupo a relacionar.
	 * 
	 * @return idExterna
	 */
	public String getIdExterna() {
		return idExterna;
	}

	/**
	 * Método que establece la ID externa del grupo a relacionar.
	 * 
	 * @param idExterna
	 */
	public void setIdExterna(String idExterna) {
		this.idExterna = idExterna;
	}
	
	/**
	 * Método que devuelve el tipo de grupo a relacionar.
	 * 
	 * @return tipoGrupo
	 */
	public String getTipoGrupo() {
		return tipoGrupo;
	}

	/**
	 * Método que establece el tipo de grupo a relacionar.
	 * 
	 * @param tipoGrupo
	 */
	public void setTipoGrupo(String tipoGrupo) {
		this.tipoGrupo = tipoGrupo;
	}

	/**
	 * Método que devuelve el nombre del grupo a relacionar.
	 * 
	 * @return nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Método que establece el nombre del grupo a relacionar.
	 * 
	 * @param nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Método que devuelve el motivo de la baja del grupo.
	 * 
	 * @return motivoBaja
	 */
	public String getMotivoBaja() {
		return motivoBaja;
	}

	/**
	 * Método que establece el motivo de la baja del grupo.
	 * 
	 * @param motivoBaja
	 */
	public void setMotivoBaja(String motivoBaja) {
		this.motivoBaja = motivoBaja;
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

}