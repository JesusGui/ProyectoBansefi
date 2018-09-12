package mx.babel.bansefi.banksystem.base.beans.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class TransaccionalidadBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5711788858816669856L;

	private Integer numDepositos;
	
	private BigDecimal montoDepositos;
	
	private Integer numRetiros;
	
	private BigDecimal montoRetiros;
	
	private BigDecimal montoMensualIngresos;
	
	private BigDecimal montoAnualIngresos;
	
	private List<String> transaccionalidad;
		
	private String provieneOtros;

	/**
	 * @return Atributo numDepositos
	 */
	public Integer getNumDepositos() {
		return numDepositos;
	}

	/**
	 * @param numDepositos Atributo numDepositos a definir
	 */
	public void setNumDepositos(Integer numDepositos) {
		this.numDepositos = numDepositos;
	}

	/**
	 * @return Atributo montoDepositos
	 */
	public BigDecimal getMontoDepositos() {
		return montoDepositos;
	}

	/**
	 * @param montoDepositos Atributo montoDepositos a definir
	 */
	public void setMontoDepositos(BigDecimal montoDepositos) {
		this.montoDepositos = montoDepositos;
	}

	/**
	 * @return Atributo numRetiros
	 */
	public Integer getNumRetiros() {
		return numRetiros;
	}

	/**
	 * @param numRetiros Atributo numRetiros a definir
	 */
	public void setNumRetiros(Integer numRetiros) {
		this.numRetiros = numRetiros;
	}

	/**
	 * @return Atributo montoRetiros
	 */
	public BigDecimal getMontoRetiros() {
		return montoRetiros;
	}

	/**
	 * @param montoRetiros Atributo montoRetiros a definir
	 */
	public void setMontoRetiros(BigDecimal montoRetiros) {
		this.montoRetiros = montoRetiros;
	}

	/**
	 * @return Atributo montoMensualIngresos
	 */
	public BigDecimal getMontoMensualIngresos() {
		return montoMensualIngresos;
	}

	/**
	 * @param montoMensualIngresos Atributo montoMensualIngresos a definir
	 */
	public void setMontoMensualIngresos(BigDecimal montoMensualIngresos) {
		this.montoMensualIngresos = montoMensualIngresos;
	}

	/**
	 * @return Atributo provieneOtros
	 */
	public String getProvieneOtros() {
		return provieneOtros;
	}

	/**
	 * @param provieneOtros Atributo provieneOtros a definir
	 */
	public void setProvieneOtros(String provieneOtros) {
		this.provieneOtros = provieneOtros;
	}
	

	/**
	 * @return the transaccionalidad
	 */
	public List<String> getTransaccionalidad() {
		return transaccionalidad;
	}

	/**
	 * @param transaccionalidad the transaccionalidad to set
	 */
	public void setTransaccionalidad(List<String> transaccionalidad) {
		this.transaccionalidad = transaccionalidad;
	}

	/**
	 * @return the montoAnualIngresos
	 */
	public BigDecimal getMontoAnualIngresos() {
		return montoAnualIngresos;
	}

	/**
	 * @param montoAnualIngresos the montoAnualIngresos to set
	 */
	public void setMontoAnualIngresos(BigDecimal montoAnualIngresos) {
		this.montoAnualIngresos = montoAnualIngresos;
	}
	
}
