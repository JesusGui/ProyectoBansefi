package mx.babel.bansefi.banksystem.cajas.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import mx.babel.arq.catalogo.beans.CatalogoBean;
import mx.babel.bansefi.banksystem.base.beans.parrilla.ExistenciaDenominacionBean;
import mx.babel.bansefi.banksystem.base.beans.parrilla.ParrillaBean;

public class RecepcionEntreOficinasBean implements Serializable {

	private static final long serialVersionUID = 2207734785744346895L;

	private List<ExistenciaDenominacionBean> listaDenominaciones;

	private ParrillaBean parrilla;

	private Date fechaEnvio;
	private Date fechaActual = new Date();

	private String entidad;
	private String centroOrigen;
	private String codigoMoneda;
	private String codigoDistribucion;
	private String centroControlador;
	private String estatusC;
	private String estatusL;
	private String idEmpleado;
	private String observaciones;
	private String tituloDetalle;

	private Integer fechaEnvioInteger;
	private Integer indicadorUrgente;
	private Integer fechaAbastecimiento;
	private Integer fechaProceso;

	private BigDecimal totalAutorizado;
	private BigDecimal totalEnviado;
	private BigDecimal totalRecibido;
	private BigDecimal totalRecibidoAbsoluto;
	private BigDecimal totalCertificado;
	private BigDecimal diferencias;

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

	public Date getFechaEnvio() {
		return fechaEnvio;
	}

	public void setFechaEnvio(Date fechaEnvio) {
		this.fechaEnvio = fechaEnvio;
	}

	public Date getFechaActual() {
		return fechaActual;
	}

	public void setFechaActual(Date fechaActual) {
		this.fechaActual = fechaActual;
	}

	public String getEntidad() {
		return entidad;
	}

	public void setEntidad(String entidad) {
		this.entidad = entidad;
	}

	public String getCentroOrigen() {
		return centroOrigen;
	}

	public void setCentroOrigen(String centroOrigen) {
		this.centroOrigen = centroOrigen;
	}

	public String getCodigoMoneda() {
		return codigoMoneda;
	}

	public void setCodigoMoneda(String codigoMoneda) {
		this.codigoMoneda = codigoMoneda;
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

	public String getEstatusC() {
		return estatusC;
	}

	public void setEstatusC(String estatusC) {
		this.estatusC = estatusC;
	}

	public String getEstatusL() {
		return estatusL;
	}

	public void setEstatusL(String estatusL) {
		this.estatusL = estatusL;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public String getTituloDetalle() {
		return tituloDetalle;
	}

	public void setTituloDetalle(String tituloDetalle) {
		this.tituloDetalle = tituloDetalle;
	}

	public Integer getFechaEnvioInteger() {
		return fechaEnvioInteger;
	}

	public void setFechaEnvioInteger(Integer fechaEnvioInteger) {
		this.fechaEnvioInteger = fechaEnvioInteger;
	}

	public Integer getIndicadorUrgente() {
		return indicadorUrgente;
	}

	public void setIndicadorUrgente(Integer indicadorUrgente) {
		this.indicadorUrgente = indicadorUrgente;
	}

	public Integer getFechaAbastecimiento() {
		return fechaAbastecimiento;
	}

	public void setFechaAbastecimiento(Integer fechaAbastecimiento) {
		this.fechaAbastecimiento = fechaAbastecimiento;
	}

	public Integer getFechaProceso() {
		return fechaProceso;
	}

	public void setFechaProceso(Integer fechaProceso) {
		this.fechaProceso = fechaProceso;
	}

	public BigDecimal getTotalAutorizado() {
		return totalAutorizado;
	}

	public void setTotalAutorizado(BigDecimal totalAutorizado) {
		this.totalAutorizado = totalAutorizado;
	}

	public BigDecimal getTotalEnviado() {
		return totalEnviado;
	}

	public void setTotalEnviado(BigDecimal totalEnviado) {
		this.totalEnviado = totalEnviado;
	}

	public BigDecimal getTotalRecibidoAbsoluto() {
		return totalRecibidoAbsoluto;
	}

	public void setTotalRecibidoAbsoluto(BigDecimal totalRecibidoAbsoluto) {
		this.totalRecibidoAbsoluto = totalRecibidoAbsoluto;
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

	public String getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(String idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	public BigDecimal getDiferencias() {
		return diferencias;
	}

	public void setDiferencias(BigDecimal diferencias) {
		this.diferencias = diferencias;
	}

}
