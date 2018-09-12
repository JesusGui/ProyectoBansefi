package mx.babel.bansefi.banksystem.base.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import mx.babel.arq.catalogo.beans.CatalogoBean;

/**
 * Funcion que se utiliza para dar de alta apuntes manuales
 * @author manuel.nieto
 *
 */
public class ApunteManualBean extends ApuntesBean implements Serializable{

	private static final long serialVersionUID = 6931357022542527662L;
	
	private String cuenta;
	private CuentaContableBean cuentaContableBean;
	private boolean apunteManual;
	private boolean apunteContable;
	private boolean noForzoso;
	private CatalogoBean oficinaDestino;
	private String datosContrapartida;
	private CatalogoBean claveOperacion;
	private long numeroApunte;
	private Date horaOperacion2;
	private String entidad;
	private int numeroSecuenciaMultiple;
	private Date fechaContable;
	
	public String getCuenta() {
		return cuenta;
	}
	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}
	public CuentaContableBean getCuentaContableBean() {
		return cuentaContableBean;
	}
	public void setCuentaContableBean(CuentaContableBean cuentaContableBean) {
		this.cuentaContableBean = cuentaContableBean;
	}
	public boolean isApunteManual() {
		return apunteManual;
	}
	public void setApunteManual(boolean apunteManual) {
		this.apunteManual = apunteManual;
	}
	public boolean isApunteContable() {
		return apunteContable;
	}
	public void setApunteContable(boolean apunteContable) {
		this.apunteContable = apunteContable;
	}
	public boolean isNoForzoso() {
		return noForzoso;
	}
	public void setNoForzoso(boolean noForzoso) {
		this.noForzoso = noForzoso;
	}
	public CatalogoBean getOficinaDestino() {
		return oficinaDestino;
	}
	public void setOficinaDestino(CatalogoBean oficinaDestino) {
		this.oficinaDestino = oficinaDestino;
	}
	public String getDatosContrapartida() {
		return datosContrapartida;
	}
	public void setDatosContrapartida(String datosContrapartida) {
		this.datosContrapartida = datosContrapartida;
	}
	public CatalogoBean getClaveOperacion() {
		return claveOperacion;
	}
	public void setClaveOperacion(CatalogoBean claveOperacion) {
		this.claveOperacion = claveOperacion;
	}
	public long getNumeroApunte() {
		return numeroApunte;
	}
	public void setNumeroApunte(long numeroApunte) {
		this.numeroApunte = numeroApunte;
	}
	public Date getHoraOperacion2() {
		return horaOperacion2;
	}
	public void setHoraOperacion2(Date horaOperacion2) {
		this.horaOperacion2 = horaOperacion2;
	}
	public String getEntidad() {
		return entidad;
	}
	public void setEntidad(String entidad) {
		this.entidad = entidad;
	}
	public int getNumeroSecuenciaMultiple() {
		return numeroSecuenciaMultiple;
	}
	public void setNumeroSecuenciaMultiple(int numeroSecuenciaMultiple) {
		this.numeroSecuenciaMultiple = numeroSecuenciaMultiple;
	}
	public Date getFechaContable() {
		return fechaContable;
	}
	public void setFechaContable(Date fechaContable) {
		this.fechaContable = fechaContable;
	}
	
	
}
