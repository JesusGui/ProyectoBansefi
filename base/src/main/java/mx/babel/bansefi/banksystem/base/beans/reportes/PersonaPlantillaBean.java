package mx.babel.bansefi.banksystem.base.beans.reportes;

import java.io.Serializable;
import java.math.BigDecimal;

public class PersonaPlantillaBean implements Serializable{

	private static final long serialVersionUID = -742297103192313838L;

	private String nombre;
	
	private BigDecimal cantidad;
	
	private String tipoidentificacion;

	private String numeroIdentificacion;
	
	private String curp;

	/**
	 * @return Atributo nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre Atributo nombre a definir
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return Atributo cantidad
	 */
	public BigDecimal getCantidad() {
		return cantidad;
	}

	/**
	 * @param cantidad Atributo cantidad a definir
	 */
	public void setCantidad(BigDecimal cantidad) {
		this.cantidad = cantidad;
	}

	/**
	 * @return Atributo tipoidentificacion
	 */
	public String getTipoidentificacion() {
		return tipoidentificacion;
	}

	/**
	 * @param tipoidentificacion Atributo tipoidentificacion a definir
	 */
	public void setTipoidentificacion(String tipoidentificacion) {
		this.tipoidentificacion = tipoidentificacion;
	}

	/**
	 * @return Atributo numeroIdentificacion
	 */
	public String getNumeroIdentificacion() {
		return numeroIdentificacion;
	}

	/**
	 * @param numeroIdentificacion Atributo numeroIdentificacion a definir
	 */
	public void setNumeroIdentificacion(String numeroIdentificacion) {
		this.numeroIdentificacion = numeroIdentificacion;
	}

	/**
	 * @return Atributo curp
	 */
	public String getCurp() {
		return curp;
	}

	/**
	 * @param curp Atributo curp a definir
	 */
	public void setCurp(String curp) {
		this.curp = curp;
	}
	
}
