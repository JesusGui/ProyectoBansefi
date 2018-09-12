package mx.babel.bansefi.banksystem.base.beans.busqueda;

import java.io.Serializable;

import mx.babel.arq.comun.annotations.CampoBusquedaAnnotation;
import mx.babel.arq.comun.annotations.CampoResultadoAnnotation;
import mx.babel.arq.comun.beans.PaginacionBean;

/**
 * Modelo de Clase Cuenta
 * @author alejandro.pineda
 *
 */
public class CuentaBusquedaBean extends PaginacionBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8503402659801079829L;
	// Entrada/Salida
	@CampoBusquedaAnnotation(requerido = true, desplegar = 0, regex = "/^[0-9]*$/", longitud = 10, tituloCampo="Número de cuenta")
	@CampoResultadoAnnotation(posicion = 2, parametro = true, key = true, tituloColumna="Número de cuenta")
	private long numeroCuenta;
	@CampoBusquedaAnnotation(requerido = true, desplegar = 1, regex = "/^[0-9]*$/", longitud = 16, tituloCampo="Número de PAN")
	@CampoResultadoAnnotation(posicion = 1, desplegar = 2, tituloColumna="Número de pan")
	private long numPan;
	
	
	
	// Salida
	@CampoResultadoAnnotation(posicion = 3, parametro = true)
	private String tipoDeCuenta;
	
	@CampoResultadoAnnotation(posicion = 4, parametro = true)
	private String titular;
	
	@CampoResultadoAnnotation(posicion = 5)
	private String estado;
	
	@CampoResultadoAnnotation(desplegar = -1,parametro = true)
	private String centro;
	
	@CampoResultadoAnnotation(desplegar = -1, parametro = true)
	private String nivelCuenta;
	
	
	public CuentaBusquedaBean(){
		
	}
	
	public Long getNumeroCuenta() {
		return numeroCuenta;
	}
	
	public void setNumeroCuenta(Long numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}
	
	public long getNumPan() {
		return numPan;
	}
	
	public void setNumPan(long noPan) {
		this.numPan = noPan;
	}

	public String getTipoDeCuenta() {
		return tipoDeCuenta;
	}
	
	public void setTipoDeCuenta(String tipoDeCuenta) {
		this.tipoDeCuenta = tipoDeCuenta;
	}
	
	public String getTitular() {
		return titular;
	}
	
	public void setTitular(String titular) {
		this.titular = titular;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getNivelCuenta() {
		return nivelCuenta;
	}

	public void setNivelCuenta(String nivelCuenta) {
		this.nivelCuenta = nivelCuenta;
	}
	
	public String getCentro() {
		return centro;
	}

	public void setCentro(String centro) {
		this.centro = centro;
	}
	
}
