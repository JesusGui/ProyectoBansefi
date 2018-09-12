package mx.babel.bansefi.banksystem.cajas.beans;

import java.math.BigDecimal;
import java.util.List;

import mx.babel.bansefi.banksystem.base.beans.parrilla.ExistenciaDenominacionBean;

public class AutorizacionPeticionEfectivoBean {

	private List<ExistenciaDenominacionBean> listaDenominaciones;
	private List<AutorizacionPeticionEfectivoBean> listaAutorizacionPeticionEfectivoBean;
	private List<ExistenciaDenominacionBean> listaExistenciaDenominacionBean;
	private List<ExistenciaDenominacionBean> listaBilletes;
	private List<CambioDenominacionBean> listaAutorizacionCambiosDenominacion;
	
	private BigDecimal totalPeticion;
	private BigDecimal importePedido;
	private BigDecimal importeAutorizado;
	private BigDecimal importeExistencias;
	
	private BigDecimal saldoAnterior;
	private BigDecimal saldoActual;
	private BigDecimal totalPedido;
	private BigDecimal totalAutorizado;
	
	private String tipoMoneda;
	private String origen;
	private String existencia;
	private String codigoDestino;
	private String entidad;
	private String centro;
	private String codigoMoneda;
	private String codigoSit;
	private String codigoFacial;
	
	private int fechaExistencia;
		
	private BigDecimal importeACambiar;
	private BigDecimal importeDe;
	private BigDecimal importeA;
	
	public List<ExistenciaDenominacionBean> getListaDenominaciones() {
		return listaDenominaciones;
	}

	public void setListaDenominaciones(
			List<ExistenciaDenominacionBean> listaDenominaciones) {
		this.listaDenominaciones = listaDenominaciones;
	}

	public List<AutorizacionPeticionEfectivoBean> getListaAutorizacionPeticionEfectivoBean() {
		return listaAutorizacionPeticionEfectivoBean;
	}

	public void setListaAutorizacionPeticionEfectivoBean(
			List<AutorizacionPeticionEfectivoBean> listaAutorizacionPeticionEfectivoBean) {
		this.listaAutorizacionPeticionEfectivoBean = listaAutorizacionPeticionEfectivoBean;
	}

	public List<ExistenciaDenominacionBean> getListaExistenciaDenominacionBean() {
		return listaExistenciaDenominacionBean;
	}

	public void setListaExistenciaDenominacionBean(
			List<ExistenciaDenominacionBean> listaExistenciaDenominacionBean) {
		this.listaExistenciaDenominacionBean = listaExistenciaDenominacionBean;
	}

	public List<ExistenciaDenominacionBean> getListaBilletes() {
		return listaBilletes;
	}

	public void setListaBilletes(List<ExistenciaDenominacionBean> listaBilletes) {
		this.listaBilletes = listaBilletes;
	}

	public List<CambioDenominacionBean> getListaAutorizacionCambiosDenominacion() {
		return listaAutorizacionCambiosDenominacion;
	}

	public void setListaAutorizacionCambiosDenominacion(
			List<CambioDenominacionBean> listaAutorizacionCambiosDenominacion) {
		this.listaAutorizacionCambiosDenominacion = listaAutorizacionCambiosDenominacion;
	}

	public BigDecimal getTotalPeticion() {
		return totalPeticion;
	}

	public void setTotalPeticion(BigDecimal totalPeticion) {
		this.totalPeticion = totalPeticion;
	}

	public BigDecimal getImportePedido() {
		return importePedido;
	}

	public void setImportePedido(BigDecimal importePedido) {
		this.importePedido = importePedido;
	}

	public BigDecimal getImporteAutorizado() {
		return importeAutorizado;
	}

	public void setImporteAutorizado(BigDecimal importeAutorizado) {
		this.importeAutorizado = importeAutorizado;
	}

	public BigDecimal getImporteExistencias() {
		return importeExistencias;
	}

	public void setImporteExistencias(BigDecimal importeExistencias) {
		this.importeExistencias = importeExistencias;
	}

	public BigDecimal getSaldoAnterior() {
		return saldoAnterior;
	}

	public void setSaldoAnterior(BigDecimal saldoAnterior) {
		this.saldoAnterior = saldoAnterior;
	}

	public BigDecimal getSaldoActual() {
		return saldoActual;
	}

	public void setSaldoActual(BigDecimal saldoActual) {
		this.saldoActual = saldoActual;
	}

	public BigDecimal getTotalPedido() {
		return totalPedido;
	}

	public void setTotalPedido(BigDecimal totalPedido) {
		this.totalPedido = totalPedido;
	}

	public BigDecimal getTotalAutorizado() {
		return totalAutorizado;
	}

	public void setTotalAutorizado(BigDecimal totalAutorizado) {
		this.totalAutorizado = totalAutorizado;
	}

	public String getTipoMoneda() {
		return tipoMoneda;
	}

	public void setTipoMoneda(String tipoMoneda) {
		this.tipoMoneda = tipoMoneda;
	}

	public String getOrigen() {
		return origen;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}

	public String getExistencia() {
		return existencia;
	}

	public void setExistencia(String existencia) {
		this.existencia = existencia;
	}

	public String getCodigoDestino() {
		return codigoDestino;
	}

	public void setCodigoDestino(String codigoDestino) {
		this.codigoDestino = codigoDestino;
	}

	public String getEntidad() {
		return entidad;
	}

	public void setEntidad(String entidad) {
		this.entidad = entidad;
	}

	public String getCentro() {
		return centro;
	}

	public void setCentro(String centro) {
		this.centro = centro;
	}

	public String getCodigoMoneda() {
		return codigoMoneda;
	}

	public void setCodigoMoneda(String codigoMoneda) {
		this.codigoMoneda = codigoMoneda;
	}

	public String getCodigoSit() {
		return codigoSit;
	}

	public void setCodigoSit(String codigoSit) {
		this.codigoSit = codigoSit;
	}

	public String getCodigoFacial() {
		return codigoFacial;
	}

	public void setCodigoFacial(String codigoFacial) {
		this.codigoFacial = codigoFacial;
	}

	public int getFechaExistencia() {
		return fechaExistencia;
	}

	public void setFechaExistencia(int fechaExistencia) {
		this.fechaExistencia = fechaExistencia;
	}

	public BigDecimal getImporteACambiar() {
		return importeACambiar;
	}

	public void setImporteACambiar(BigDecimal importeACambiar) {
		this.importeACambiar = importeACambiar;
	}

	public BigDecimal getImporteDe() {
		return importeDe;
	}

	public void setImporteDe(BigDecimal importeDe) {
		this.importeDe = importeDe;
	}

	public BigDecimal getImporteA() {
		return importeA;
	}

	public void setImporteA(BigDecimal importeA) {
		this.importeA = importeA;
	}
}