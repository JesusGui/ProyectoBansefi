package mx.babel.bansefi.banksystem.cajas.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import mx.babel.arq.catalogo.beans.CatalogoBean;
import mx.babel.bansefi.banksystem.base.beans.parrilla.ExistenciaDenominacionBean;
import mx.babel.bansefi.banksystem.base.beans.parrilla.ParrillaBean;

public class DiferenciaBean implements Serializable {

	private static final long serialVersionUID = 4895011401612820900L;

	private List<ExistenciaDenominacionBean> listaDenominaciones;
	
	private ParrillaBean parrilla;
	
	private CatalogoBean estatus;
	private CatalogoBean codigoDistribucion;

	private Date fechaDesde;
	private Date fechaHasta;

	private String entidad;
	private String centrOrigen;
	private String codigoMoneda;
	private String centroDesino;
	private String centroControlador;
	private String idInternoEmpleado;
	private String observaciones;

	private int fechaPeticion;
	private int numeroUrgente;

	private BigDecimal totalAutorizado;
	private BigDecimal totalPedido;
	private BigDecimal totalRecibido;
	private BigDecimal totalCertificado;

	public List<ExistenciaDenominacionBean> getListaDenominaciones() {
		return listaDenominaciones;
	}

	public void setListaDenominaciones(
			List<ExistenciaDenominacionBean> listaDenominaciones) {
		this.listaDenominaciones = listaDenominaciones;
	}

	public ParrillaBean getParrilla() {
		return parrilla;
	}

	public void setParrilla(ParrillaBean parrilla) {
		this.parrilla = parrilla;
	}

	public CatalogoBean getEstatus() {
		return estatus;
	}

	public void setEstatus(CatalogoBean estatus) {
		this.estatus = estatus;
	}

	public CatalogoBean getCodigoDistribucion() {
		return codigoDistribucion;
	}

	public void setCodigoDistribucion(CatalogoBean codigoDistribucion) {
		this.codigoDistribucion = codigoDistribucion;
	}

	public Date getFechaDesde() {
		return fechaDesde;
	}

	public void setFechaDesde(Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	public Date getFechaHasta() {
		return fechaHasta;
	}

	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

	public String getEntidad() {
		return entidad;
	}

	public void setEntidad(String entidad) {
		this.entidad = entidad;
	}

	public String getCentrOrigen() {
		return centrOrigen;
	}

	public void setCentrOrigen(String centrOrigen) {
		this.centrOrigen = centrOrigen;
	}

	public String getCodigoMoneda() {
		return codigoMoneda;
	}

	public void setCodigoMoneda(String codigoMoneda) {
		this.codigoMoneda = codigoMoneda;
	}

	public String getCentroDesino() {
		return centroDesino;
	}

	public void setCentroDesino(String centroDesino) {
		this.centroDesino = centroDesino;
	}

	public String getCentroControlador() {
		return centroControlador;
	}

	public void setCentroControlador(String centroControlador) {
		this.centroControlador = centroControlador;
	}
	
	public String getIdInternoEmpleado() {
		return idInternoEmpleado;
	}

	public void setIdInternoEmpleado(String idInternoEmpleado) {
		this.idInternoEmpleado = idInternoEmpleado;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public int getFechaPeticion() {
		return fechaPeticion;
	}

	public void setFechaPeticion(int fechaPeticion) {
		this.fechaPeticion = fechaPeticion;
	}

	public int getNumeroUrgente() {
		return numeroUrgente;
	}

	public void setNumeroUrgente(int numeroUrgente) {
		this.numeroUrgente = numeroUrgente;
	}

	public BigDecimal getTotalAutorizado() {
		return totalAutorizado;
	}

	public void setTotalAutorizado(BigDecimal totalAutorizado) {
		this.totalAutorizado = totalAutorizado;
	}

	public BigDecimal getTotalPedido() {
		return totalPedido;
	}

	public void setTotalPedido(BigDecimal totalPedido) {
		this.totalPedido = totalPedido;
	}

	public BigDecimal getTotalRecibido() {
		return totalRecibido;
	}

	public void setTotalRecibido(BigDecimal totalRecibido) {
		this.totalRecibido = totalRecibido;
	}

	public BigDecimal getTotalCertificado() {
		return totalCertificado;
	}

	public void setTotalCertificado(BigDecimal totalCertificado) {
		this.totalCertificado = totalCertificado;
	}
	
}
