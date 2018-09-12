package mx.babel.bansefi.banksystem.base.beans.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class DonativosPersonaMoralBean implements Serializable{

	private static final long serialVersionUID = 5612181831987404876L;
	
	private List<String> frecuencia;
	
	private List<String> entrega;
	
	private List<String> personas;
	
	private String personasEspecificas;
	
	private BigDecimal activoFijo;
	
	private BigDecimal activoCirculante;
	
	private BigDecimal pasivoCortoPlazo;
	
	private BigDecimal pasivoLargoPlazo;
	
	private BigDecimal capitalContable;
	
	private String moneda;
	
	private BigDecimal ingresoAnual;
	
	private BigDecimal costoVentas;
	
	private BigDecimal gastos;
	
	private BigDecimal utilidad;
	
	private Integer numEmpleados;
	
	private Integer numSucursales;
	
	private BigDecimal ingresoExp;
	
	private BigDecimal pagoExp;
	
	private BigDecimal usDolares;
	
	private String indicarMoneda;
	
	/**
	 * @return the frecuencia
	 */
	public List<String> getFrecuencia() {
		return frecuencia;
	}

	/**
	 * @param frecuencia the frecuencia to set
	 */
	public void setFrecuencia(List<String> frecuencia) {
		this.frecuencia = frecuencia;
	}

	/**
	 * @return the entrega
	 */
	public List<String> getEntrega() {
		return entrega;
	}

	/**
	 * @param entrega the entrega to set
	 */
	public void setEntrega(List<String> entrega) {
		this.entrega = entrega;
	}

	/**
	 * @return the personas
	 */
	public List<String> getPersonas() {
		return personas;
	}

	/**
	 * @param personas the personas to set
	 */
	public void setPersonas(List<String> personas) {
		this.personas = personas;
	}

	/**
	 * @return the activoFijo
	 */
	public BigDecimal getActivoFijo() {
		return activoFijo;
	}

	/**
	 * @param activoFijo the activoFijo to set
	 */
	public void setActivoFijo(BigDecimal activoFijo) {
		this.activoFijo = activoFijo;
	}

	/**
	 * @return the activoCirculante
	 */
	public BigDecimal getActivoCirculante() {
		return activoCirculante;
	}

	/**
	 * @param activoCirculante the activoCirculante to set
	 */
	public void setActivoCirculante(BigDecimal activoCirculante) {
		this.activoCirculante = activoCirculante;
	}

	/**
	 * @return the pasivoCortoPlazo
	 */
	public BigDecimal getPasivoCortoPlazo() {
		return pasivoCortoPlazo;
	}

	/**
	 * @param pasivoCortoPlazo the pasivoCortoPlazo to set
	 */
	public void setPasivoCortoPlazo(BigDecimal pasivoCortoPlazo) {
		this.pasivoCortoPlazo = pasivoCortoPlazo;
	}

	/**
	 * @return the pasivoLargoPlazo
	 */
	public BigDecimal getPasivoLargoPlazo() {
		return pasivoLargoPlazo;
	}

	/**
	 * @param pasivoLargoPlazo the pasivoLargoPlazo to set
	 */
	public void setPasivoLargoPlazo(BigDecimal pasivoLargoPlazo) {
		this.pasivoLargoPlazo = pasivoLargoPlazo;
	}

	/**
	 * @return the capitalContable
	 */
	public BigDecimal getCapitalContable() {
		return capitalContable;
	}

	/**
	 * @param capitalContable the capitalContable to set
	 */
	public void setCapitalContable(BigDecimal capitalContable) {
		this.capitalContable = capitalContable;
	}

	/**
	 * @return the moneda
	 */
	public String getMoneda() {
		return moneda;
	}

	/**
	 * @param moneda the moneda to set
	 */
	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	/**
	 * @return the ingresoAnual
	 */
	public BigDecimal getIngresoAnual() {
		return ingresoAnual;
	}

	/**
	 * @param ingresoAnual the ingresoAnual to set
	 */
	public void setIngresoAnual(BigDecimal ingresoAnual) {
		this.ingresoAnual = ingresoAnual;
	}

	/**
	 * @return the costoVentas
	 */
	public BigDecimal getCostoVentas() {
		return costoVentas;
	}

	/**
	 * @param costoVentas the costoVentas to set
	 */
	public void setCostoVentas(BigDecimal costoVentas) {
		this.costoVentas = costoVentas;
	}

	/**
	 * @return the gastos
	 */
	public BigDecimal getGastos() {
		return gastos;
	}

	/**
	 * @param gastos the gastos to set
	 */
	public void setGastos(BigDecimal gastos) {
		this.gastos = gastos;
	}

	/**
	 * @return the utilidad
	 */
	public BigDecimal getUtilidad() {
		return utilidad;
	}

	/**
	 * @param utilidad the utilidad to set
	 */
	public void setUtilidad(BigDecimal utilidad) {
		this.utilidad = utilidad;
	}

	/**
	 * @return the numEmpleados
	 */
	public Integer getNumEmpleados() {
		return numEmpleados;
	}

	/**
	 * @param numEmpleados the numEmpleados to set
	 */
	public void setNumEmpleados(Integer numEmpleados) {
		this.numEmpleados = numEmpleados;
	}

	/**
	 * @return the numSucursales
	 */
	public Integer getNumSucursales() {
		return numSucursales;
	}

	/**
	 * @param numSucursales the numSucursales to set
	 */
	public void setNumSucursales(Integer numSucursales) {
		this.numSucursales = numSucursales;
	}

	/**
	 * @return the ingresoExp
	 */
	public BigDecimal getIngresoExp() {
		return ingresoExp;
	}

	/**
	 * @param ingresoExp the ingresoExp to set
	 */
	public void setIngresoExp(BigDecimal ingresoExp) {
		this.ingresoExp = ingresoExp;
	}

	/**
	 * @return the pagoExp
	 */
	public BigDecimal getPagoExp() {
		return pagoExp;
	}

	/**
	 * @param pagoExp the pagoExp to set
	 */
	public void setPagoExp(BigDecimal pagoExp) {
		this.pagoExp = pagoExp;
	}

	/**
	 * @return the usDolares
	 */
	public BigDecimal getUsDolares() {
		return usDolares;
	}

	/**
	 * @param usDolares the usDolares to set
	 */
	public void setUsDolares(BigDecimal usDolares) {
		this.usDolares = usDolares;
	}

	/**
	 * @return the indicarMoneda
	 */
	public String getIndicarMoneda() {
		return indicarMoneda;
	}

	/**
	 * @param indicarMoneda the indicarMoneda to set
	 */
	public void setIndicarMoneda(String indicarMoneda) {
		this.indicarMoneda = indicarMoneda;
	}

	public String getPersonasEspecificas() {
		return personasEspecificas;
	}

	public void setPersonasEspecificas(String personasEspecificas) {
		this.personasEspecificas = personasEspecificas;
	}
	
}
