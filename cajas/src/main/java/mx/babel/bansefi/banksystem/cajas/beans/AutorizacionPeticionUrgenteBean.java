package mx.babel.bansefi.banksystem.cajas.beans;

import java.math.BigDecimal;
import java.util.List;

import mx.babel.bansefi.banksystem.base.beans.parrilla.ExistenciaDenominacionBean;

public class AutorizacionPeticionUrgenteBean {
	
	public AutorizacionPeticionUrgenteBean(){
		this.totalAutorizado = new BigDecimal(0.00);
		this.totalPedido = new BigDecimal(0.00);
	}
	
	private Integer fechaSolicitud;
	
	private String centro;
	private String entidad;
	private String codigoDistribucion;
	private String centroControlador;
	private String estatusPeticionL;
	private String estatusPeticionC;
	private String idEmpleado;
	private String observaciones;
	private String tipoMoneda;
	
	private Integer noUrgente;
	
	private int fechaAbastecimiento;
	private int fechaProceso;
	
	private BigDecimal totalPedido;
	private BigDecimal totalAutorizado;
	
	private BigDecimal importeAutorizado;
	private BigDecimal importePedido;
	private BigDecimal importeRecibido;
	private BigDecimal importeCertificado;
	
	private List<ExistenciaDenominacionBean> listaDenominaciones;
	private List<ConsultaPeticionUrgenteBean> listaDenominacionesPeticion;

	public Integer getFechaSolicitud() {
		return fechaSolicitud;
	}

	public void setFechaSolicitud(Integer fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}

	public String getCentro() {
		return centro;
	}

	public void setCentro(String centro) {
		this.centro = centro;
	}

	public String getEntidad() {
		return entidad;
	}

	public void setEntidad(String entidad) {
		this.entidad = entidad;
	}

	public String getCodigoDistribucion() {
		return codigoDistribucion;
	}

	public void setCodigoDistribucion(String codigoDistribucion) {
		this.codigoDistribucion = codigoDistribucion;
	}

	public String getCentroControlador() {
		return centroControlador;
	}

	public void setCentroControlador(String centroControlador) {
		this.centroControlador = centroControlador;
	}

	public String getEstatusPeticionL() {
		return estatusPeticionL;
	}

	public void setEstatusPeticionL(String estatusPeticionL) {
		this.estatusPeticionL = estatusPeticionL;
	}

	public String getEstatusPeticionC() {
		return estatusPeticionC;
	}

	public void setEstatusPeticionC(String estatusPeticionC) {
		this.estatusPeticionC = estatusPeticionC;
	}

	public String getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(String idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public String getTipoMoneda() {
		return tipoMoneda;
	}

	public void setTipoMoneda(String tipoMoneda) {
		this.tipoMoneda = tipoMoneda;
	}

	public Integer getNoUrgente() {
		return noUrgente;
	}

	public void setNoUrgente(Integer noUrgente) {
		this.noUrgente = noUrgente;
	}

	public int getFechaAbastecimiento() {
		return fechaAbastecimiento;
	}

	public void setFechaAbastecimiento(int fechaAbastecimiento) {
		this.fechaAbastecimiento = fechaAbastecimiento;
	}

	public int getFechaProceso() {
		return fechaProceso;
	}

	public void setFechaProceso(int fechaProceso) {
		this.fechaProceso = fechaProceso;
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

	public BigDecimal getImporteAutorizado() {
		return importeAutorizado;
	}

	public void setImporteAutorizado(BigDecimal importeAutorizado) {
		this.importeAutorizado = importeAutorizado;
	}

	public BigDecimal getImportePedido() {
		return importePedido;
	}

	public void setImportePedido(BigDecimal importePedido) {
		this.importePedido = importePedido;
	}

	public BigDecimal getImporteRecibido() {
		return importeRecibido;
	}

	public void setImporteRecibido(BigDecimal importeRecibido) {
		this.importeRecibido = importeRecibido;
	}

	public BigDecimal getImporteCertificado() {
		return importeCertificado;
	}

	public void setImporteCertificado(BigDecimal importeCertificado) {
		this.importeCertificado = importeCertificado;
	}

	public List<ExistenciaDenominacionBean> getListaDenominaciones() {
		return listaDenominaciones;
	}

	public void setListaDenominaciones(
			List<ExistenciaDenominacionBean> listaDenominaciones) {
		this.listaDenominaciones = listaDenominaciones;
	}

	public List<ConsultaPeticionUrgenteBean> getListaDenominacionesPeticion() {
		return listaDenominacionesPeticion;
	}

	public void setListaDenominacionesPeticion(
			List<ConsultaPeticionUrgenteBean> listaDenominacionesPeticion) {
		this.listaDenominacionesPeticion = listaDenominacionesPeticion;
	}
	
}