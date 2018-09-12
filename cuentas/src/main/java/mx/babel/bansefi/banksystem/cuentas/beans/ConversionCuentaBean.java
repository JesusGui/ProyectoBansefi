package mx.babel.bansefi.banksystem.cuentas.beans;

import java.io.Serializable;

public class ConversionCuentaBean implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String cuenta;
	private String centro;
	private String cuentaAntigua;
	private String seleccion;
	private String mensajeSalidaCuenta;
	private String mensajeSalidaCentro;
	private String cuentaResult,centroResult;
	/**
	 * @return the cuenta
	 */
	public String getCuenta() {
		return cuenta;
	}
	
	/**
	 * @param cuenta the cuenta to set
	 */
	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}
	/**
	 * @return the centro
	 */
	public String getCentro() {
		return centro;
	}
	/**
	 * @param centro the centro to set
	 */
	public void setCentro(String centro) {
		this.centro = centro;
	}
	/**
	 * @return the cuentaAntigua
	 */
	public String getCuentaAntigua() {
		return cuentaAntigua;
	}
	/**
	 * @param cuentaAntigua the cuentaAntigua to set
	 */
	public void setCuentaAntigua(String cuentaAntigua) {
		this.cuentaAntigua = cuentaAntigua;
	}
	/**
	 * @return the seleccion
	 */
	public String getSeleccion() {
		return seleccion;
	}
	/**
	 * @param seleccion the seleccion to set
	 */
	public void setSeleccion(String seleccion) {
		this.seleccion = seleccion;
	}
	/**
	 * @return the mensajeSalidaCuenta
	 */
	public String getMensajeSalidaCuenta() {
		return mensajeSalidaCuenta;
	}
	/**
	 * @param mensajeSalidaCuenta the mensajeSalidaCuenta to set
	 */
	public void setMensajeSalidaCuenta(String mensajeSalidaCuenta) {
		this.mensajeSalidaCuenta = mensajeSalidaCuenta;
	}
	/**
	 * @return the mensajeSalidaCentro
	 */
	public String getMensajeSalidaCentro() {
		return mensajeSalidaCentro;
	}
	/**
	 * @param mensajeSalidaCentro the mensajeSalidaCentro to set
	 */
	public void setMensajeSalidaCentro(String mensajeSalidaCentro) {
		this.mensajeSalidaCentro = mensajeSalidaCentro;
	}

	/**
	 * @return the cuentaResult
	 */
	public String getCuentaResult() {
		return cuentaResult;
	}
	/**
	 * @param cuentaResult the cuentaResult to set
	 */
	public void setCuentaResult(String cuentaResult) {
		this.cuentaResult = cuentaResult;
	}
	/**
	 * @return the centroResult
	 */
	public String getCentroResult() {
		return centroResult;
	}
	/**
	 * @param centroResult the centroResult to set
	 */
	public void setCentroResult(String centroResult) {
		this.centroResult = centroResult;
	}
	
}
