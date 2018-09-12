package mx.babel.bansefi.banksystem.base.beans.domain.relacioncuenta;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mx.babel.bansefi.banksystem.base.beans.domain.CuentaBean;
import mx.babel.bansefi.banksystem.base.beans.domain.CuentaClienteBean;
import mx.babel.bansefi.banksystem.base.enums.EstadoListadosEnum;

public class CuentaRelacionadaBean implements Serializable {
	/**
     *
     */
    private static final long serialVersionUID = 7943340110516802070L;
    private CuentaBean cuenta;
	private String tipoRelacion;
	private BigDecimal garantia;
	private BigDecimal abono;
	private BigDecimal cargo;
	private Date alta;
	private Date inicio;
	private Date cierre;
	private Date inicioIf;
	private int numero;
	private Integer numeroSecuencial;
	private String estadoRelacion;
	private String razonCambio;
	private EstadoListadosEnum estadoListado;
	private List<CuentaClienteBean> cuentasRelacionables;
	private String respaldo;
	/**
	 * @return Atributo cuenta
	 */
	public CuentaBean getCuenta() {
		return cuenta;
	}
	/**
	 * @param cuenta Atributo cuenta a definir
	 */
	public void setCuenta(final CuentaBean cuenta) {
		this.cuenta = cuenta;
	}
	/**
	 * @return Atributo tipoRelacion
	 */
	public String getTipoRelacion() {
		return tipoRelacion;
	}
	/**
	 * @param tipoRelacion Atributo tipoRelacion a definir
	 */
	public void setTipoRelacion(final String tipoRelacion) {
		this.tipoRelacion = tipoRelacion;
	}
	/**
	 * @return Atributo garantia
	 */
	public BigDecimal getGarantia() {
		return garantia;
	}
	/**
	 * @param garantia Atributo garantia a definir
	 */
	public void setGarantia(final BigDecimal garantia) {
		this.garantia = garantia;
	}
	/**
	 * @return Atributo abono
	 */
	public BigDecimal getAbono() {
		return abono;
	}
	/**
	 * @param abono Atributo abono a definir
	 */
	public void setAbono(final BigDecimal abono) {
		this.abono = abono;
	}
	/**
	 * @return Atributo cargo
	 */
	public BigDecimal getCargo() {
		return cargo;
	}
	/**
	 * @param cargo Atributo cargo a definir
	 */
	public void setCargo(final BigDecimal cargo) {
		this.cargo = cargo;
	}
	/**
	 * @return Atributo alta
	 */
	public Date getAlta() {
		return alta;
	}
	/**
	 * @param alta Atributo alta a definir
	 */
	public void setAlta(final Date alta) {
		this.alta = alta;
	}
	/**
	 * @return Atributo incio
	 */
	public Date getInicio() {
		return inicio;
	}
	/**
	 * @param incio Atributo incio a definir
	 */
	public void setInicio(final Date inicio) {
		this.inicio = inicio;
	}
	/**
	 * @return Atributo cierre
	 */
	public Date getCierre() {
		return cierre;
	}
	/**
	 * @param cierre Atributo cierre a definir
	 */
	public void setCierre(final Date cierre) {
		this.cierre = cierre;
	}
	/**
	 * @return Atributo inicioIf
	 */
	public Date getInicioIf() {
		return inicioIf;
	}
	/**
	 * @param inicioIf Atributo inicioIf a definir
	 */
	public void setInicioIf(final Date inicioIf) {
		this.inicioIf = inicioIf;
	}
	/**
	 * @return Atributo numeroSecuencial
	 */
	public Integer getNumeroSecuencial() {
		return numeroSecuencial;
	}
	/**
	 * @param numeroSecuencial Atributo numeroSecuencial a definir
	 */
	public void setNumeroSecuencial(final Integer numeroSecuencial) {
		this.numeroSecuencial = numeroSecuencial;
	}
	/**
	 * @return Atributo estadoRelacion
	 */
	public String getEstadoRelacion() {
		return estadoRelacion;
	}
	/**
	 * @param estadoRelacion Atributo estadoRelacion a definir
	 */
	public void setEstadoRelacion(final String estadoRelacion) {
		this.estadoRelacion = estadoRelacion;
	}
	/**
	 * @return Atributo numero
	 */
	public int getNumero() {
		return numero;
	}
	/**
	 * @param numero Atributo numero a definir
	 */
	public void setNumero(final int numero) {
		this.numero = numero;
	}
	/**
	 * @return Atributo razonCambio
	 */
	public String getRazonCambio() {
		return razonCambio;
	}
	/**
	 * @param razonCambio Atributo razonCambio a definir
	 */
	public void setRazonCambio(final String razonCambio) {
		this.razonCambio = razonCambio;
	}
	/**
	 * @return Atributo estadoListado
	 */
	public EstadoListadosEnum getEstadoListado() {
		return estadoListado;
	}
	/**
	 * @param estadoListado Atributo estadoListado a definir
	 */
	public void setEstadoListado(final EstadoListadosEnum estadoListado) {
		this.estadoListado = estadoListado;
	}
	/**
	 * @return Atributo cuentasRelacionables
	 */

	public List<CuentaClienteBean> getCuentasRelacionables() {
		if(cuentasRelacionables == null){
			cuentasRelacionables = new ArrayList<CuentaClienteBean>();
		}
		return cuentasRelacionables;
	}

	/**
	 * @param cuentasRelacionables Atributo cuentasRelacionables a definir
	 */
	public void setCuentasRelacionables(final List<CuentaClienteBean> cuentasRelacionables) {
		this.cuentasRelacionables = cuentasRelacionables;
	}
	/**
	 * @return Atributo respaldo
	 */
	public String getRespaldo() {
		return respaldo;
	}
	/**
	 * @param respaldo Atributo respaldo a definir
	 */
	public void setRespaldo(final String respaldo) {
		this.respaldo = respaldo;
	}
	@Override
	public String toString() {
		return "CuentaRelacionadaBean [cuenta=" + cuenta + ", tipoRelacion="
				+ tipoRelacion + ", garantia=" + garantia + ", abono=" + abono
				+ ", cargo=" + cargo + ", alta=" + alta
				+ ", estadoRelacion=" + estadoRelacion + ", numero=" + numero
				+ ", razonCambio=" + razonCambio + ", cuentasRelacionables="
				+ cuentasRelacionables + "]";
	}


}
