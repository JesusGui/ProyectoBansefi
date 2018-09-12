package mx.babel.bansefi.banksystem.base.beans.domain;

import java.io.Serializable;
import java.util.Date;

public class GestorBean implements Serializable{

	private static final long serialVersionUID = -1820387781244019691L;

	private String nombreGestor;
	
	private Date fechaAlta;
	
	private String estado;
	
	private Boolean gestorComercial;
	
	private Boolean gestorContacto;
	
	private Boolean gestorSituacionEspecial;
	
	private String indicadorExterno;

	/**
	 * @return Atributo nombreGestor
	 */
	public String getNombreGestor() {
		return nombreGestor;
	}

	/**
	 * @param nombreGestor Atributo nombreGestor a definir
	 */
	public void setNombreGestor(String nombreGestor) {
		this.nombreGestor = nombreGestor;
	}

	/**
	 * @return Atributo fechaAlta
	 */
	public Date getFechaAlta() {
		return fechaAlta;
	}

	/**
	 * @param fechaAlta Atributo fechaAlta a definir
	 */
	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	/**
	 * @return Atributo estado
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * @param estado Atributo estado a definir
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * @return Atributo gestorComercial
	 */
	public Boolean getGestorComercial() {
		return gestorComercial;
	}

	/**
	 * @param gestorComercial Atributo gestorComercial a definir
	 */
	public void setGestorComercial(Boolean gestorComercial) {
		this.gestorComercial = gestorComercial;
	}

	/**
	 * @return Atributo gestorContacto
	 */
	public Boolean getGestorContacto() {
		return gestorContacto;
	}

	/**
	 * @param gestorContacto Atributo gestorContacto a definir
	 */
	public void setGestorContacto(Boolean gestorContacto) {
		this.gestorContacto = gestorContacto;
	}

	/**
	 * @return Atributo gestorSituacionEspecial
	 */
	public Boolean getGestorSituacionEspecial() {
		return gestorSituacionEspecial;
	}

	/**
	 * @param gestorSituacionEspecial Atributo gestorSituacionEspecial a definir
	 */
	public void setGestorSituacionEspecial(Boolean gestorSituacionEspecial) {
		this.gestorSituacionEspecial = gestorSituacionEspecial;
	}

	/**
	 * @return Atributo indicadorExterno
	 */
	public String getIndicadorExterno() {
		return indicadorExterno;
	}

	/**
	 * @param indicadorExterno Atributo indicadorExterno a definir
	 */
	public void setIndicadorExterno(String indicadorExterno) {
		this.indicadorExterno = indicadorExterno;
	}
	
	public String getTipoGestor(){
		String tipo = "";
		if(gestorComercial){
			tipo = tipo + "COMERCIAL, ";
		}
		if(gestorContacto){
			tipo = tipo + "CONTACTO, ";
		}
		if(gestorSituacionEspecial){
			tipo = tipo + "SITUACIÓN ESPECIAL";
		}else{
			if(gestorComercial || gestorContacto){
				tipo = tipo.substring(tipo.length() -3);
			}
		}
		return tipo;
	}
}
