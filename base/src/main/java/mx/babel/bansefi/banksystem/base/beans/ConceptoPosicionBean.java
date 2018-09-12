package mx.babel.bansefi.banksystem.base.beans;

import java.math.BigDecimal;
import java.util.List;

/**
 * Bean que contiene los objetos para modelar un concepto de la posición
 * 
 * @author babel
 *
 */
public class ConceptoPosicionBean {
	
	private String codDescripcion;

	private String descripcion;
	
	private List<BigDecimal> importes;

	private String estilo;
	
	/**
	 * @return the importes
	 */
	public List<BigDecimal> getImportes() {
		return importes;
	}

	/**
	 * @param importes the importes to set
	 */
	public void setImportes(List<BigDecimal> importes) {
		this.importes = importes;
	}

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * @return the codDescripcion
	 */
	public String getCodDescripcion() {
		return codDescripcion;
	}

	/**
	 * @param codDescripcion the codDescripcion to set
	 */
	public void setCodDescripcion(String codDescripcion) {
		this.codDescripcion = codDescripcion;
	}

	/**
	 * @return the estilo
	 */
	public String getEstilo() {
		return estilo;
	}

	/**
	 * @param estilo the estilo to set
	 */
	public void setEstilo(String estilo) {
		this.estilo = estilo;
	}
	
}
