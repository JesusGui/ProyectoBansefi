package mx.babel.bansefi.banksystem.base.beans.domain;

import java.io.Serializable;

public class DocumentoBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String tipoDocumento;
	
	private String descripcion;
	
	private String codigo;

	/**
	 * @return Atributo tipoDocumento
	 */
	public String getTipoDocumento() {
		return tipoDocumento;
	}

	/**
	 * @param tipoDocumento Atributo tipoDocumento a definir
	 */
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	/**
	 * @return Atributo descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion Atributo descripcion a definir
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * @return Atributo codigo
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo Atributo codigo a definir
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	@Override
	public String toString() {
		return "DocumentoBean [tipoDocumento=" + tipoDocumento
				+ ", descripcion=" + descripcion + ", codigo=" + codigo + "]";
	}
	
	
}
