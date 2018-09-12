package mx.babel.bansefi.banksystem.cuentas.beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mx.babel.bansefi.banksystem.base.beans.domain.DocumentoBean;
import org.apache.commons.lang.StringUtils;

/**
 * Bean para emisión de documentos
 * @author mario.montesdeoca
 *
 */
public class EmisionDocumentosBean extends DocumentoBean{

	private static final long serialVersionUID = 1L;
	private Integer idInterno;
	private String obligatorio;
	private String estado;
	private String fichero;
	private Integer formulario;
	private String idioma;
	private Date fechaEmision;
	private Date fechaFormalizacion;
	private Integer numCopias;
	private List<EstadoDocumentoBean> estados;
	private List<CampoDocumentoBean> campos;
	private List<EmisionDocumentosBean> historico;
	private Boolean muestraCampos;
	private Boolean emisionCompleta;
	
	/**
	 * @return Atributo idInterno
	 */
	public Integer getIdInterno() {
		return idInterno;
	}
	/**
	 * @param idInterno Atributo idInterno a definir
	 */
	public void setIdInterno(Integer idInterno) {
		this.idInterno = idInterno;
	}
	/**
	 * @return Atributo obligatorio
	 */
	public String getObligatorio() {
		return obligatorio;
	}
	/**
	 * @param obligatorio Atributo obligatorio a definir
	 */
	public void setObligatorio(String obligatorio) {
		this.obligatorio = obligatorio;
	}
	/**
	 * @return Atributo emitido
	 */
	public String getEstado() {
		return estado;
	}
	/**
	 * @param estado Atributo emitido a definir
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}
	/**
	 * @return Atributo fichero
	 */
	public String getFichero() {
		return fichero;
	}
	/**
	 * @param fichero Atributo fichero a definir
	 */
	public void setFichero(String fichero) {
		this.fichero = fichero;
	}
	/**
	 * @return Atributo formulario
	 */
	public Integer getFormulario() {
		return formulario;
	}
	/**
	 * @param formulario Atributo formulario a definir
	 */
	public void setFormulario(Integer formulario) {
		this.formulario = formulario;
	}
	/**
	 * @return Atributo idioma
	 */
	public String getIdioma() {
		return idioma;
	}
	/**
	 * @param idioma Atributo idioma a definir
	 */
	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}
	/**
	 * @return Atributo fechaEmision
	 */
	public Date getFechaEmision() {
		return fechaEmision;
	}
	/**
	 * @param fechaEmision Atributo fechaEmision a definir
	 */
	public void setFechaEmision(Date fechaEmision) {
		this.fechaEmision = fechaEmision;
	}
	/**
	 * @return Atributo fechaFormalizacion
	 */
	public Date getFechaFormalizacion() {
		return fechaFormalizacion;
	}
	/**
	 * @param fechaFormalizacion Atributo fechaFormalizacion a definir
	 */
	public void setFechaFormalizacion(Date fechaFormalizacion) {
		this.fechaFormalizacion = fechaFormalizacion;
	}
	/**
	 * @return Atributo numCopias
	 */
	public Integer getNumCopias() {
		return numCopias;
	}
	/**
	 * @param numCopias Atributo numCopias a definir
	 */
	public void setNumCopias(Integer numCopias) {
		this.numCopias = numCopias;
	}
	/**
	 * @return Atributo estados
	 */
	public List<EstadoDocumentoBean> getEstados() {
		return estados;
	}
	/**
	 * @param estados Atributo estados a definir
	 */
	public void setEstados(List<EstadoDocumentoBean> estados) {
		this.estados = estados;
	}
	/**
	 * @return Atributo campos
	 */
	public List<CampoDocumentoBean> getCampos() {
		return campos;
	}
	/**
	 * @param campos Atributo campos a definir
	 */
	public void setCampos(List<CampoDocumentoBean> campos) {
		for(CampoDocumentoBean campo : campos) {
			campo.setDescripcion(StringUtils.capitalize(campo.getDescripcionVariable().replaceAll("_", " ")));
		}
		this.campos = campos;
	}
	/**
	 * @return Atributo historico
	 */
	public List<EmisionDocumentosBean> getHistorico() {
		if(historico == null){
			historico = new ArrayList<EmisionDocumentosBean>();
		}
		return historico;
	}
	/**
	 * @param historico Atributo historico a definir
	 */
	public void setHistorico(List<EmisionDocumentosBean> historico) {
		this.historico = historico;
	}
	/**
	 * @return Atributo muestraCampos
	 */
	public Boolean getMuestraCampos() {
		return muestraCampos;
	}
	/**
	 * @param muestraCampos Atributo muestraCampos a definir
	 */
	public void setMuestraCampos(Boolean muestraCampos) {
		this.muestraCampos = muestraCampos;
	}
	/**
	 * @return Atributo emisionCompleta
	 */
	public Boolean getEmisionCompleta() {
		return emisionCompleta;
	}
	/**
	 * @param emisionCompleta Atributo emisionCompleta a definir
	 */
	public void setEmisionCompleta(Boolean emisionCompleta) {
		this.emisionCompleta = emisionCompleta;
	}
}
