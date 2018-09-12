package mx.babel.bansefi.banksystem.oficina.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mx.babel.bansefi.banksystem.base.beans.parrilla.ExistenciaDenominacionBean;

/**
 * Bean con detalles de arqueo de centro
 * @author mario.montesdeoca
 *
 */
public class ArqueoCentroBean implements Serializable{

	private static final long serialVersionUID = 1L;

	private Boolean cuadrada;
	private Boolean arqueada;
	private BigDecimal totalArqueo;
	private BigDecimal saldoResultante;
	private BigDecimal restante;
	private String numeroCentro;
	private String situacion;
	private BigDecimal saldoContableCajaAnterior;
	private BigDecimal saldoContableCajaActual;
	private BigDecimal totalEfectivo;
	private BigDecimal cobrosOn;
	private BigDecimal pagosOn;
	private BigDecimal cobrosOff;
	private BigDecimal cobrosMovil;
	private BigDecimal pagosOff;
	private BigDecimal pagosMovil;
	private BigDecimal haberOn;
	private BigDecimal haberOff;
	private BigDecimal haberMovil;
	private BigDecimal debeOn;
	private BigDecimal debeOff;
	private BigDecimal debeMovil;
	private List<ExistenciaDenominacionBean> existenciaDenominaciones;
	private BigDecimal totalArqueoPuestos;
	private BigDecimal netoTraspasosPuestos;
	private BigDecimal saldoPuestos;
	private List<CuadrePuestoBean> cuadrePuestos;
	private List<String> puestos;
	private Date fechaArqueo;

	/**
	 * @return Atributo cuadrada
	 */
	public Boolean getCuadrada() {
		return cuadrada;
	}

	/**
	 * @param cuadrada Atributo cuadrada a definir
	 */
	public void setCuadrada(Boolean cuadrada) {
		this.cuadrada = cuadrada;
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
		if(totalArqueo == null){
			totalArqueo = BigDecimal.ZERO; 
			for (ExistenciaDenominacionBean denominacion : getExistenciaDenominaciones()) {
				totalArqueo = totalArqueo.add(denominacion.getImporte());
			}
		}
		return totalArqueo;
	}

	/**
	 * @param totalArqueo Atributo totalArqueo a definir
	 */
	public void setTotalArqueo(BigDecimal totalArqueo) {
		this.totalArqueo = totalArqueo;
	}

	/**
	 * @return Atributo saldoResultante
	 */
	public BigDecimal getSaldoResultante() {
		return saldoResultante;
	}

	/**
	 * @param saldoResultante Atributo saldoResultante a definir
	 */
	public void setSaldoResultante(BigDecimal saldoResultante) {
		this.saldoResultante = saldoResultante;
	}

	/**
	 * @return Atributo restante
	 */
	public BigDecimal getRestante() {
		return restante;
	}

	/**
	 * @param restante Atributo restante a definir
	 */
	public void setRestante(BigDecimal restante) {
		this.restante = restante;
	}

	/**
	 * @return Atributo numeroCentro
	 */
	public String getNumeroCentro() {
		return numeroCentro;
	}

	/**
	 * @param numeroCentro Atributo numeroCentro a definir
	 */
	public void setNumeroCentro(String numeroCentro) {
		this.numeroCentro = numeroCentro;
	}

	/**
	 * @return Atributo situacion
	 */
	public String getSituacion() {
		return situacion;
	}

	/**
	 * @param situacion Atributo situacion a definir
	 */
	public void setSituacion(String situacion) {
		this.situacion = situacion;
	}

	/**
	 * @return Atributo saldoContableCajaAnterior
	 */
	public BigDecimal getSaldoContableCajaAnterior() {
		return saldoContableCajaAnterior;
	}

	/**
	 * @param saldoContableCajaAnterior Atributo saldoContableCajaAnterior a definir
	 */
	public void setSaldoContableCajaAnterior(BigDecimal saldoContableCajaAnterior) {
		this.saldoContableCajaAnterior = saldoContableCajaAnterior;
	}

	/**
	 * @return Atributo saldoContableCajaActual
	 */
	public BigDecimal getSaldoContableCajaActual() {
		return saldoContableCajaActual;
	}

	/**
	 * @param saldoContableCajaActual Atributo saldoContableCajaActual a definir
	 */
	public void setSaldoContableCajaActual(BigDecimal saldoContableCajaActual) {
		this.saldoContableCajaActual = saldoContableCajaActual;
	}

	/**
	 * @return Atributo totalEfectivo
	 */
	public BigDecimal getTotalEfectivo() {
		return totalEfectivo;
	}

	/**
	 * @param totalEfectivo Atributo totalEfectivo a definir
	 */
	public void setTotalEfectivo(BigDecimal totalEfectivo) {
		this.totalEfectivo = totalEfectivo;
	}

	/**
	 * @return Atributo cobrosOn
	 */
	public BigDecimal getCobrosOn() {
		return cobrosOn;
	}

	/**
	 * @param cobrosOn Atributo cobrosOn a definir
	 */
	public void setCobrosOn(BigDecimal cobrosOn) {
		this.cobrosOn = cobrosOn;
	}

	/**
	 * @return Atributo pagosOn
	 */
	public BigDecimal getPagosOn() {
		return pagosOn;
	}

	/**
	 * @param pagosOn Atributo pagosOn a definir
	 */
	public void setPagosOn(BigDecimal pagosOn) {
		this.pagosOn = pagosOn;
	}

	/**
	 * @return Atributo cobrosOff
	 */
	public BigDecimal getCobrosOff() {
		return cobrosOff;
	}

	/**
	 * @param cobrosOff Atributo cobrosOff a definir
	 */
	public void setCobrosOff(BigDecimal cobrosOff) {
		this.cobrosOff = cobrosOff;
	}

	/**
	 * @return Atributo cobrosMovil
	 */
	public BigDecimal getCobrosMovil() {
		return cobrosMovil;
	}

	/**
	 * @param cobrosMovil Atributo cobrosMovil a definir
	 */
	public void setCobrosMovil(BigDecimal cobrosMovil) {
		this.cobrosMovil = cobrosMovil;
	}

	/**
	 * @return Atributo pagosOff
	 */
	public BigDecimal getPagosOff() {
		return pagosOff;
	}

	/**
	 * @param pagosOff Atributo pagosOff a definir
	 */
	public void setPagosOff(BigDecimal pagosOff) {
		this.pagosOff = pagosOff;
	}

	/**
	 * @return Atributo pagosMovil
	 */
	public BigDecimal getPagosMovil() {
		return pagosMovil;
	}

	/**
	 * @param pagosMovil Atributo pagosMovil a definir
	 */
	public void setPagosMovil(BigDecimal pagosMovil) {
		this.pagosMovil = pagosMovil;
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
	 * @return Atributo haberOff
	 */
	public BigDecimal getHaberOff() {
		return haberOff;
	}

	/**
	 * @param haberOff Atributo haberOff a definir
	 */
	public void setHaberOff(BigDecimal haberOff) {
		this.haberOff = haberOff;
	}

	/**
	 * @return Atributo haberMovil
	 */
	public BigDecimal getHaberMovil() {
		return haberMovil;
	}

	/**
	 * @param haberMovil Atributo haberMovil a definir
	 */
	public void setHaberMovil(BigDecimal haberMovil) {
		this.haberMovil = haberMovil;
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
	 * @return Atributo debeOff
	 */
	public BigDecimal getDebeOff() {
		return debeOff;
	}

	/**
	 * @param debeOff Atributo debeOff a definir
	 */
	public void setDebeOff(BigDecimal debeOff) {
		this.debeOff = debeOff;
	}

	/**
	 * @return Atributo debeMovil
	 */
	public BigDecimal getDebeMovil() {
		return debeMovil;
	}

	/**
	 * @param debeMovil Atributo debeMovil a definir
	 */
	public void setDebeMovil(BigDecimal debeMovil) {
		this.debeMovil = debeMovil;
	}

	/**
	 * @return Atributo existenciaDenominaciones
	 */
	public List<ExistenciaDenominacionBean> getExistenciaDenominaciones() {
		if(existenciaDenominaciones == null){
			existenciaDenominaciones = new ArrayList<ExistenciaDenominacionBean>();
		}
		return existenciaDenominaciones;
	}

	/**
	 * @param existenciaDenominaciones Atributo existenciaDenominaciones a definir
	 */
	public void setExistenciaDenominaciones(
			List<ExistenciaDenominacionBean> existenciaDenominaciones) {
		this.existenciaDenominaciones = existenciaDenominaciones;
	}

	/**
	 * @return Atributo totalArqueoPuestos
	 */
	public BigDecimal getTotalArqueoPuestos() {
		return totalArqueoPuestos;
	}

	/**
	 * @param totalArqueoPuestos Atributo totalArqueoPuestos a definir
	 */
	public void setTotalArqueoPuestos(BigDecimal totalArqueoPuestos) {
		this.totalArqueoPuestos = totalArqueoPuestos;
	}

	/**
	 * @return Atributo netoTraspasosPuestos
	 */
	public BigDecimal getNetoTraspasosPuestos() {
		return netoTraspasosPuestos;
	}

	/**
	 * @param netoTraspasosPuestos Atributo netoTraspasosPuestos a definir
	 */
	public void setNetoTraspasosPuestos(BigDecimal netoTraspasosPuestos) {
		this.netoTraspasosPuestos = netoTraspasosPuestos;
	}

	/**
	 * @return Atributo saldoPuestos
	 */
	public BigDecimal getSaldoPuestos() {
		return saldoPuestos;
	}

	/**
	 * @param saldoPuestos Atributo saldoPuestos a definir
	 */
	public void setSaldoPuestos(BigDecimal saldoPuestos) {
		this.saldoPuestos = saldoPuestos;
	}

	/**
	 * @return Atributo cuadrePuestos
	 */
	public List<CuadrePuestoBean> getCuadrePuestos() {
		if(cuadrePuestos == null){
			cuadrePuestos = new ArrayList<CuadrePuestoBean>();
		}
		return cuadrePuestos;
	}

	/**
	 * @param cuadrePuestos Atributo cuadrePuestos a definir
	 */
	public void setCuadrePuestos(List<CuadrePuestoBean> cuadrePuestos) {
		this.cuadrePuestos = cuadrePuestos;
	}

	/**
	 * @return Atributo puestos
	 */
	public List<String> getPuestos() {
		return puestos;
	}

	/**
	 * @param puestos Atributo puestos a definir
	 */
	public void setPuestos(List<String> puestos) {
		this.puestos = puestos;
	}

	/**
	 * @return Atributo fechaArqueo
	 */
	public Date getFechaArqueo() {
		return fechaArqueo;
	}

	/**
	 * @param fechaArqueo Atributo fechaArqueo a definir
	 */
	public void setFechaArqueo(Date fechaArqueo) {
		this.fechaArqueo = fechaArqueo;
	}
}
