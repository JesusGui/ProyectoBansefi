package mx.babel.bansefi.banksystem.oficina.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import mx.babel.bansefi.banksystem.base.beans.parrilla.ExistenciaDenominacionBean;

/**
 * Bean con detalles del arqueo de un puesto
 * @author mario.montesdeoca
 *
 */
public class ArqueoPuestoBean implements Serializable{

	private static final long serialVersionUID = 1L;
	private String puesto;
	private Boolean arqueada;
	private BigDecimal totalArqueo;
	private BigDecimal netoTraspasos;
	private BigDecimal cobroOn;
	private BigDecimal pagoOn;
	private BigDecimal debeOn;
	private BigDecimal haberOn;
	private BigDecimal difCajaOn;
	private BigDecimal difCuentaOn;
	private BigDecimal descuadre;
	private List<ExistenciaDenominacionBean> listaDenominaciones;
	/**
	 * @return Atributo puestoConsulta
	 */
	public String getPuesto() {
		return puesto;
	}
	/**
	 * @param puestoConsulta Atributo puestoConsulta a definir
	 */
	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}
	/**
	 * @return Atributo arqueada
	 */
	public Boolean getArqueada() {
		return arqueada;
	}
	/**
	 * @param arqueada Atributo arqueada a definir
	 */
	public void setArqueada(Boolean arqueada) {
		this.arqueada = arqueada;
	}
	/**
	 * @return Atributo totalArqueo
	 */
	public BigDecimal getTotalArqueo() {
		return totalArqueo;
	}
	/**
	 * @param totalArqueo Atributo totalArqueo a definir
	 */
	public void setTotalArqueo(BigDecimal totalArqueo) {
		this.totalArqueo = totalArqueo;
	}
	/**
	 * @return Atributo netoTraspasos
	 */
	public BigDecimal getNetoTraspasos() {
		return netoTraspasos;
	}
	/**
	 * @param netoTraspasos Atributo netoTraspasos a definir
	 */
	public void setNetoTraspasos(BigDecimal netoTraspasos) {
		this.netoTraspasos = netoTraspasos;
	}
	/**
	 * @return Atributo cobroOn
	 */
	public BigDecimal getCobroOn() {
		return cobroOn;
	}
	/**
	 * @param cobroOn Atributo cobroOn a definir
	 */
	public void setCobroOn(BigDecimal cobroOn) {
		this.cobroOn = cobroOn;
	}
	/**
	 * @return Atributo pagoOn
	 */
	public BigDecimal getPagoOn() {
		return pagoOn;
	}
	/**
	 * @param pagoOn Atributo pagoOn a definir
	 */
	public void setPagoOn(BigDecimal pagoOn) {
		this.pagoOn = pagoOn;
	}
	/**
	 * @return Atributo debeOn
	 */
	public BigDecimal getDebeOn() {
		return debeOn;
	}
	/**
	 * @param debeOn Atributo debeOn a definir
	 */
	public void setDebeOn(BigDecimal debeOn) {
		this.debeOn = debeOn;
	}
	/**
	 * @return Atributo haberOn
	 */
	public BigDecimal getHaberOn() {
		return haberOn;
	}
	/**
	 * @param haberOn Atributo haberOn a definir
	 */
	public void setHaberOn(BigDecimal haberOn) {
		this.haberOn = haberOn;
	}
	/**
	 * @return Atributo difCajaOn
	 */
	public BigDecimal getDifCajaOn() {
		return difCajaOn;
	}
	/**
	 * @param difCajaOn Atributo difCajaOn a definir
	 */
	public void setDifCajaOn(BigDecimal difCajaOn) {
		this.difCajaOn = difCajaOn;
	}
	/**
	 * @return Atributo difCuentaOn
	 */
	public BigDecimal getDifCuentaOn() {
		return difCuentaOn;
	}
	/**
	 * @param difCuentaOn Atributo difCuentaOn a definir
	 */
	public void setDifCuentaOn(BigDecimal difCuentaOn) {
		this.difCuentaOn = difCuentaOn;
	}
	/**
	 * @return Atributo descuadre
	 */
	public BigDecimal getDescuadre() {
		return descuadre;
	}
	/**
	 * @param descuadre Atributo descuadre a definir
	 */
	public void setDescuadre(BigDecimal descuadre) {
		this.descuadre = descuadre;
	}
	/**
	 * @return Atributo listaDenominaciones
	 */
	public List<ExistenciaDenominacionBean> getListaDenominaciones() {
		return listaDenominaciones;
	}
	/**
	 * @param listaDenominaciones Atributo listaDenominaciones a definir
	 */
	public void setListaDenominaciones(
			List<ExistenciaDenominacionBean> listaDenominaciones) {
		this.listaDenominaciones = listaDenominaciones;
	}
	
	/**
	 * Mètodo para verificar si la caja del puesto està cuadrada
	 * @return <code>true</code> si la caja esta cuadrada
	 */
	public Boolean cajaCuadrada(){
		if(this.descuadre.compareTo(BigDecimal.ZERO) == 0){
			return true;
		}
		return false;
	}
	
	/**
	 * Mètodo para verificar si la cuenta de intervenciòn del puesto està cuadrada
	 * @return <code>true</code> si la cuenta esta cuadrada
	 */
	public Boolean cuentaCuadrada(){
		if(this.difCuentaOn.compareTo(BigDecimal.ZERO) == 0){
			return true;
		}
		return false;
	}
}
