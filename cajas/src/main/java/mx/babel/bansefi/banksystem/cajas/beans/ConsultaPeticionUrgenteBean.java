package mx.babel.bansefi.banksystem.cajas.beans;

import java.math.BigDecimal;

public class ConsultaPeticionUrgenteBean {
	
	private String entidad;
	
	private String centro;
	
	private int fechaSolicitud;
	
	private String codigoMoneda;
	
	private String formato = "EXISTENCIA";
	
	private String codigoDistribucion;
	
	private int noUrgente;
	
	private String centroControlador;
	
	private String origen;
	
	private String codigoValorFacial;
	
	private BigDecimal importeAutorizado;
	
	private BigDecimal importePedido;
	
	private BigDecimal importeRecibido;
	
	
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
	
	public int getFechaSolicitud() {
		return fechaSolicitud;
	}
	
	public void setFechaSolicitud(int fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}
	
	public String getCodigoMoneda() {
		return codigoMoneda;
	}
	
	public void setCodigoMoneda(String codigoMoneda) {
		this.codigoMoneda = codigoMoneda;
	}
	
	public String getFormato() {
		return formato;
	}
	
	public void setFormato(String formato) {
		this.formato = formato;
	}
	
	public String getCodigoDistribucion() {
		return codigoDistribucion;
	}
	
	public void setCodigoDistribucion(String codigoDistribucion) {
		this.codigoDistribucion = codigoDistribucion;
	}
	
	public int getNoUrgente() {
		return noUrgente;
	}
	
	public void setNoUrgente(int noUrgente) {
		this.noUrgente = noUrgente;
	}
	
	public String getCentroControlador() {
		return centroControlador;
	}
	
	public void setCentroControlador(String centroControlador) {
		this.centroControlador = centroControlador;
	}
	
	public String getOrigen() {
		return origen;
	}
	
	public void setOrigen(String origen) {
		this.origen = origen;
	}
	
	public String getCodigoValorFacial() {
		return codigoValorFacial;
	}
	
	public void setCodigoValorFacial(String codigoValorFacial) {
		this.codigoValorFacial = codigoValorFacial;
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

}
