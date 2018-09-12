package mx.babel.bansefi.banksystem.base.beans.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * Clase para definir el modelo de datos de riesgo de clientes que son personas físicas.
 * @author javier.martinnino
 * 
 */
public class RiesgoClientePersonaFisicaBean implements Serializable{


	private static final long serialVersionUID = -5551780278905354823L;

	private String opcion1;
	
	private String opcion2;
	
	private String opcion3;

	private String opcion4;
	
	private String opcion5;
	
	private String opcion6;
	
	private String producto;
	
	private Integer numSucursales;
	
	private Integer numEmpleados;
	
	private BigDecimal activo;
	
	private BigDecimal pasivo;
	
	private BigDecimal factAnual;
	
	private BigDecimal capital;
	
	private BigDecimal exportaciones;
	
	private String denominacionEmpresa;
	
	private String calle;
	
	private String numExterior;
	
	private String numInterior;
	
	private String municipio;
	
	private String codigoPostal;
	
	private String colonia;
	
	private String estado;
	
	private List<ReferenciaPersonalBean> referenciasPersonales;
	
	/**
	 * @return the opcion1
	 */
	public String getOpcion1() {
		return opcion1;
	}

	/**
	 * @param opcion1 the opcion1 to set
	 */
	public void setOpcion1(String opcion1) {
		this.opcion1 = opcion1;
	}

	/**
	 * @return the opcion2
	 */
	public String getOpcion2() {
		return opcion2;
	}

	/**
	 * @param opcion2 the opcion2 to set
	 */
	public void setOpcion2(String opcion2) {
		this.opcion2 = opcion2;
	}

	/**
	 * @return the opcion3
	 */
	public String getOpcion3() {
		return opcion3;
	}

	/**
	 * @param opcion3 the opcion3 to set
	 */
	public void setOpcion3(String opcion3) {
		this.opcion3 = opcion3;
	}

	/**
	 * @return the opcion4
	 */
	public String getOpcion4() {
		return opcion4;
	}

	/**
	 * @param opcion4 the opcion4 to set
	 */
	public void setOpcion4(String opcion4) {
		this.opcion4 = opcion4;
	}

	/**
	 * @return the opcion5
	 */
	public String getOpcion5() {
		return opcion5;
	}

	/**
	 * @param opcion5 the opcion5 to set
	 */
	public void setOpcion5(String opcion5) {
		this.opcion5 = opcion5;
	}

	/**
	 * @return the opcion6
	 */
	public String getOpcion6() {
		return opcion6;
	}

	/**
	 * @param opcion6 the opcion6 to set
	 */
	public void setOpcion6(String opcion6) {
		this.opcion6 = opcion6;
	}

	/**
	 * @return the producto
	 */
	public String getProducto() {
		return producto;
	}

	/**
	 * @param producto the producto to set
	 */
	public void setProducto(String producto) {
		this.producto = producto;
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
	 * @return the activo
	 */
	public BigDecimal getActivo() {
		return activo;
	}

	/**
	 * @param activo the activo to set
	 */
	public void setActivo(BigDecimal activo) {
		this.activo = activo;
	}

	/**
	 * @return the pasivo
	 */
	public BigDecimal getPasivo() {
		return pasivo;
	}

	/**
	 * @param pasivo the pasivo to set
	 */
	public void setPasivo(BigDecimal pasivo) {
		this.pasivo = pasivo;
	}

	/**
	 * @return the factAnual
	 */
	public BigDecimal getFactAnual() {
		return factAnual;
	}

	/**
	 * @param factAnual the factAnual to set
	 */
	public void setFactAnual(BigDecimal factAnual) {
		this.factAnual = factAnual;
	}

	/**
	 * @return the capital
	 */
	public BigDecimal getCapital() {
		return capital;
	}

	/**
	 * @param capital the capital to set
	 */
	public void setCapital(BigDecimal capital) {
		this.capital = capital;
	}

	/**
	 * @return the exportaciones
	 */
	public BigDecimal getExportaciones() {
		return exportaciones;
	}

	/**
	 * @param exportaciones the exportaciones to set
	 */
	public void setExportaciones(BigDecimal exportaciones) {
		this.exportaciones = exportaciones;
	}

	/**
	 * @return the denominacionEmpresa
	 */
	public String getDenominacionEmpresa() {
		return denominacionEmpresa;
	}

	/**
	 * @param denominacionEmpresa the denominacionEmpresa to set
	 */
	public void setDenominacionEmpresa(String denominacionEmpresa) {
		this.denominacionEmpresa = denominacionEmpresa;
	}

	/**
	 * @return the calle
	 */
	public String getCalle() {
		return calle;
	}

	/**
	 * @param calle the calle to set
	 */
	public void setCalle(String calle) {
		this.calle = calle;
	}
	

	/**
	 * @return the numExterior
	 */
	public String getNumExterior() {
		return numExterior;
	}

	/**
	 * @param numExterior the numExterior to set
	 */
	public void setNumExterior(String numExterior) {
		this.numExterior = numExterior;
	}

	/**
	 * @return the numInterior
	 */
	public String getNumInterior() {
		return numInterior;
	}

	/**
	 * @param numInterior the numInterior to set
	 */
	public void setNumInterior(String numInterior) {
		this.numInterior = numInterior;
	}

	/**
	 * @return the municipio
	 */
	public String getMunicipio() {
		return municipio;
	}

	/**
	 * @param municipio the municipio to set
	 */
	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	/**
	 * @return the codigoPostal
	 */
	public String getCodigoPostal() {
		return codigoPostal;
	}

	/**
	 * @param codigoPostal the codigoPostal to set
	 */
	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	/**
	 * @return the colonia
	 */
	public String getColonia() {
		return colonia;
	}

	/**
	 * @param colonia the colonia to set
	 */
	public void setColonia(String colonia) {
		this.colonia = colonia;
	}

	/**
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * @param estado the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * @return the referenciasPersonales
	 */
	public List<ReferenciaPersonalBean> getReferenciasPersonales() {
		return referenciasPersonales;
	}
	
	/**
	 * @param referenciasPersonales the referenciasPersonales to set
	 */
	public void setReferenciasPersonales(List<ReferenciaPersonalBean> referenciasPersonales) {
		this.referenciasPersonales = referenciasPersonales;
	}	
	
}
