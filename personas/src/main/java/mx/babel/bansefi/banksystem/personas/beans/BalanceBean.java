package mx.babel.bansefi.banksystem.personas.beans;

import java.io.Serializable;
import java.util.List;

/**
 * Representa un balance del listado.
 * */
public class BalanceBean implements Serializable {

	private static final long serialVersionUID = -3892589163333930524L;
	
	private Integer idInternoDoc;

	private String fechaRevision;
	
	private String anyo;
	
	private String descripcion;
	
	private String codDocumento;
	
	private String indVigencia;
	
	private List<RegistroBalanceBean> registros;

	/**
	 * @return Atributo idInternoDoc
	 */
	public Integer getIdInternoDoc() {
		return idInternoDoc;
	}

	/**
	 * @param idInternoDoc Atributo idInternoDoc a definir
	 */
	public void setIdInternoDoc(Integer idInternoDoc) {
		this.idInternoDoc = idInternoDoc;
	}

	/**
	 * @return the fechaRevision
	 */
	public String getFechaRevision() {
		return fechaRevision;
	}

	/**
	 * @param fechaRevision the fechaRevision to set
	 */
	public void setFechaRevision(String fechaRevision) {
		this.fechaRevision = fechaRevision;
	}

	/**
	 * @return the anyo
	 */
	public String getAnyo() {
		return anyo;
	}

	/**
	 * @param anyo the anyo to set
	 */
	public void setAnyo(String anyo) {
		this.anyo = anyo;
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
	 * @return the registros
	 */
	public List<RegistroBalanceBean> getRegistros() {
		return registros;
	}

	/**
	 * @param registros the registros to set
	 */
	public void setRegistros(List<RegistroBalanceBean> registros) {
		this.registros = registros;
	}

	/**
	 * @return the codDocumento
	 */
	public String getCodDocumento() {
		return codDocumento;
	}

	/**
	 * @param codDocumento the codDocumento to set
	 */
	public void setCodDocumento(String codDocumento) {
		this.codDocumento = codDocumento;
	}

	/**
	 * @return the indVigencia
	 */
	public String getIndVigencia() {
		return indVigencia;
	}

	/**
	 * @param indVigencia the indVigencia to set
	 */
	public void setIndVigencia(String indVigencia) {
		this.indVigencia = indVigencia;
	}
	
	
	
}
