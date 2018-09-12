package mx.babel.bansefi.banksystem.personas.beans;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Representa un registro del balance.
 * */
public class RegistroBalanceBean implements Serializable{

	private static final long serialVersionUID = 1453937857326318701L;

	private String id;
	private int tab;
	private int nivel;
	private int grupo;
	private boolean editable;
	private boolean visible;
	private boolean ratio;
	private boolean resumen;
	private String texto;
	private BigDecimal valor;
	private BigDecimal porcentaje;
	private String estilo;
	private String estiloTitulo;
	
	public RegistroBalanceBean() {
		
	}
	
	public RegistroBalanceBean(String id) {
		this.id = id;
	}
	
	/**
	 * @return the tab
	 */
	public int getTab() {
		return tab;
	}
	/**
	 * @param tab the tab to set
	 */
	public void setTab(int tab) {
		this.tab = tab;
	}
	/**
	 * @return the nivel
	 */
	public int getNivel() {
		return nivel;
	}
	/**
	 * @param nivel the nivel to set
	 */
	public void setNivel(int nivel) {
		this.nivel = nivel;
	}
	/**
	 * @return the editable
	 */
	public boolean isEditable() {
		return editable;
	}
	/**
	 * @param editable the editable to set
	 */
	public void setEditable(boolean editable) {
		this.editable = editable;
	}
	/**
	 * @return the texto
	 */
	public String getTexto() {
		return texto;
	}
	/**
	 * @param texto the texto to set
	 */
	public void setTexto(String texto) {
		this.texto = texto;
	}
	/**
	 * @return the valor
	 */
	public BigDecimal getValor() {
		return valor;	
		
	}
	/**
	 * @param valor the valor to set
	 */
	public void setValor(BigDecimal valor) {
		if(valor.compareTo(BigDecimal.ZERO) == 0){
			this.valor=null;
		}else{
			this.valor = valor;
		}
		
	}
	
	public void guardarValor(BigDecimal valor){
		if(valor == null){
			this.valor = new BigDecimal(0) ;
		}else{
			this.valor = valor;
		}
	}
	/**
	 * @return the porcentaje
	 */
	public BigDecimal getPorcentaje() {
		return porcentaje;
	}
	/**
	 * @param porcentaje the porcentaje to set
	 */
	public void setPorcentaje(BigDecimal porcentaje) {
		this.porcentaje = porcentaje;
	}
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the grupo
	 */
	public int getGrupo() {
		return grupo;
	}
	/**
	 * @param grupo the grupo to set
	 */
	public void setGrupo(int grupo) {
		this.grupo = grupo;
	}

	/**
	 * @return the visibilidad
	 */
	public boolean isVisible() {
		return visible;
	}

	/**
	 * @param visibilidad the visibilidad to set
	 */
	public void setVisible(boolean visibilidad) {
		this.visible = visibilidad;
	}

	/**
	 * @return the ratio
	 */
	public boolean isRatio() {
		return ratio;
	}

	/**
	 * @param ratio the ratio to set
	 */
	public void setRatio(boolean ratio) {
		this.ratio = ratio;
	}

	/**
	 * @return the resumen
	 */
	public boolean isResumen() {
		return resumen;
	}

	/**
	 * @param resumen the resumen to set
	 */
	public void setResumen(boolean resumen) {
		this.resumen = resumen;
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

	/**
	 * @return the estiloTitulo
	 */
	public String getEstiloTitulo() {
		return estiloTitulo;
	}

	/**
	 * @param estiloTitulo the estiloTitulo to set
	 */
	public void setEstiloTitulo(String estiloTitulo) {
		this.estiloTitulo = estiloTitulo;
	}
	
}
