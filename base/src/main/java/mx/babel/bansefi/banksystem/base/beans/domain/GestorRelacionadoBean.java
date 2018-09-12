package mx.babel.bansefi.banksystem.base.beans.domain;

import java.io.Serializable;

import mx.babel.bansefi.banksystem.base.enums.EstadoListadosEnum;

/**
 * Clase que define los atributos de un gestor que podrá ser relacionado a un
 * cliente (titular).
 * 
 * @author omar.marquez
 * 
 */
public class GestorRelacionadoBean implements Serializable {

	private static final long serialVersionUID = -4916295203666315680L;

	private Integer idInterna;
	private String idExterna;
	private String nombre;
	private boolean chkComercial;
	private boolean chkContacto;
	private boolean chkEspecial;
	private String motivoBaja;
	private EstadoListadosEnum estado;
	private String respaldo;

	/**
	 * Constructor.
	 */
	public GestorRelacionadoBean() {
		this.idInterna = null;
		this.idExterna = null;
		this.nombre = null;
		this.chkComercial = false;
		this.chkContacto = false;
		this.chkEspecial = false;
		this.motivoBaja = null;
		this.respaldo = null;
	}

	/**
	 * Método que devuelve la ID interna del gestor a relacionar.
	 * 
	 * @return idInterna
	 */
	public Integer getIdInterna() {
		return idInterna;
	}

	/**
	 * Método que establece la ID interna del gestor a relacionar.
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
	 * Método que devuelve la ID externa del gestor a relacionar.
	 * 
	 * @return idExterna
	 */
	public String getIdExterna() {
		return idExterna;
	}

	/**
	 * Método que establece la ID externa del gestor a relacionar.
	 * 
	 * @param idExterna
	 */
	public void setIdExterna(String idExterna) {
		this.idExterna = idExterna;
	}

	/**
	 * Método que devuelve el nombre del gestor a relacionar.
	 * 
	 * @return nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Método que establece el nombre del gestor a relacionar.
	 * 
	 * @param nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Método que devuelve un indicador booleano para determinar si el tipo de
	 * gestión es comercial o no.
	 * 
	 * @return indicador booleano para el tipo de gestión
	 */
	public boolean getChkComercial() {
		return chkComercial;
	}

	/**
	 * Método que establece el valor del indicador de gestión comercial.
	 * 
	 * @param chkComercial
	 */
	public void setChkComercial(boolean chkComercial) {
		this.chkComercial = chkComercial;
	}

	/**
	 * Método que devuelve un indicador booleano para determinar si el tipo de
	 * gestión es de contacto o no.
	 * 
	 * @return indicador booleano para el tipo de gestión
	 */
	public boolean getChkContacto() {
		return chkContacto;
	}

	/**
	 * Método que establece el valor del indicador de gestión de contacto.
	 * 
	 * @param chkContacto
	 */
	public void setChkContacto(boolean chkContacto) {
		this.chkContacto = chkContacto;
	}

	/**
	 * Método que devuelve un indicador booleano para determinar si el tipo de
	 * gestión es situación especial o no.
	 * 
	 * @return indicador booleano para el tipo de gestión
	 */
	public boolean getChkEspecial() {
		return chkEspecial;
	}

	/**
	 * Método que establece el valor del indicador de situación especial.
	 * 
	 * @param chkEspecial
	 */
	public void setChkEspecial(boolean chkEspecial) {
		this.chkEspecial = chkEspecial;
	}

	/**
	 * Método que devuelve el motivo de la baja del gestor.
	 * 
	 * @return motivoBaja
	 */
	public String getMotivoBaja() {
		return motivoBaja;
	}

	/**
	 * Método que establece el motivo de la baja del gestor.
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

	/**
	 * Método que devuelve una cadena que representa un objeto
	 * GestorRelacionadoBean serializado mediante JSON.
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