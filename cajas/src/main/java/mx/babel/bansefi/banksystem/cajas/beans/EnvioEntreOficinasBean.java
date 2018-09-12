package mx.babel.bansefi.banksystem.cajas.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import mx.babel.bansefi.banksystem.base.beans.parrilla.ParrillaBean;
import mx.babel.bansefi.banksystem.cajas.enums.EstadoPeticionEnum;

public class EnvioEntreOficinasBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1860205652558948235L;

	private ParrillaBean parrilla;

	private String entidad;
	private String centroOrigen;
	private String centroDestino;
	private String centroControlador;
	private String codigoMoneda;
	private String codigoDistribucion;
	private String estatusC;
	private String estatusL;
	private String idEmpleado;
	private String observaciones;
	private String tituloBoton;
	private String tituloResumen;

	private Date fechaContableEnvio;

	private Boolean editable = true;
	private Boolean filtro = true;
	private Boolean precinto = true;
	private Boolean importeDisabled = true;
	private Boolean unidadesDisabled = true;
	private Boolean precintoDisabled = true;
	private Boolean filtroDisabled = true;
	private Boolean botonLimpiarVisible = false;
	private Boolean botonGuardarVisible = false;
	private Boolean botonConfirmarvisible = false;
	private Boolean botonSuprimirVisible = false;
	private Boolean observacionDisabled = true;
	
	private EstadoPeticionEnum estadoPeticionEnum;

	private Integer tipoFechaEnvio;
	private Integer fechaContableEnvioInteger;
	private Integer indicadorUgente;
	private Integer fechaAbastecimiento;
	private Integer fechaProceso;

	private BigDecimal totalAutorizado;
	private BigDecimal totalPedido;
	private BigDecimal totalRecibido;
	private BigDecimal totalCertificado;
	private BigDecimal totalAEnviar;

	public ParrillaBean getParrilla() {
		return parrilla;
	}

	public void setParrilla(ParrillaBean parrilla) {
		this.parrilla = parrilla;
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

	public String getCentroDestino() {
		return centroDestino;
	}

	public void setCentroDestino(String centroDestino) {
		this.centroDestino = centroDestino;
	}

	public String getCentroControlador() {
		return centroControlador;
	}

	public void setCentroControlador(String centroControlador) {
		this.centroControlador = centroControlador;
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

	public String getTituloBoton() {
		return tituloBoton;
	}

	public void setTituloBoton(String tituloBoton) {
		this.tituloBoton = tituloBoton;
	}

	public String getTituloResumen() {
		return tituloResumen;
	}

	public void setTituloResumen(String tituloResumen) {
		this.tituloResumen = tituloResumen;
	}

	public Date getFechaContableEnvio() {
		return fechaContableEnvio;
	}

	public void setFechaContableEnvio(Date fechaContableEnvio) {
		this.fechaContableEnvio = fechaContableEnvio;
	}

	public Boolean getEditable() {
		return editable;
	}

	public void setEditable(Boolean editable) {
		this.editable = editable;
	}

	public Boolean getFiltro() {
		return filtro;
	}

	public void setFiltro(Boolean filtro) {
		this.filtro = filtro;
	}

	public Boolean getPrecinto() {
		return precinto;
	}

	public void setPrecinto(Boolean precinto) {
		this.precinto = precinto;
	}

	public Boolean getImporteDisabled() {
		return importeDisabled;
	}

	public void setImporteDisabled(Boolean importeDisabled) {
		this.importeDisabled = importeDisabled;
	}

	public Boolean getUnidadesDisabled() {
		return unidadesDisabled;
	}

	public void setUnidadesDisabled(Boolean unidadesDisabled) {
		this.unidadesDisabled = unidadesDisabled;
	}

	public Boolean getPrecintoDisabled() {
		return precintoDisabled;
	}

	public void setPrecintoDisabled(Boolean precintoDisabled) {
		this.precintoDisabled = precintoDisabled;
	}

	public Boolean getFiltroDisabled() {
		return filtroDisabled;
	}

	public void setFiltroDisabled(Boolean filtroDisabled) {
		this.filtroDisabled = filtroDisabled;
	}

	public Boolean getBotonLimpiarVisible() {
		return botonLimpiarVisible;
	}

	public void setBotonLimpiarVisible(Boolean botonLimpiarVisible) {
		this.botonLimpiarVisible = botonLimpiarVisible;
	}

	public Boolean getBotonGuardarVisible() {
		return botonGuardarVisible;
	}

	public void setBotonGuardarVisible(Boolean botonGuardarVisible) {
		this.botonGuardarVisible = botonGuardarVisible;
	}

	public Boolean getBotonConfirmarvisible() {
		return botonConfirmarvisible;
	}

	public void setBotonConfirmarvisible(Boolean botonConfirmarvisible) {
		this.botonConfirmarvisible = botonConfirmarvisible;
	}

	public Boolean getBotonSuprimirVisible() {
		return botonSuprimirVisible;
	}

	public void setBotonSuprimirVisible(Boolean botonSuprimirVisible) {
		this.botonSuprimirVisible = botonSuprimirVisible;
	}

	public Boolean getObservacionDisabled() {
		return observacionDisabled;
	}

	public void setObservacionDisabled(Boolean observacionDisabled) {
		this.observacionDisabled = observacionDisabled;
	}

	public EstadoPeticionEnum getEstadoPeticionEnum() {
		return estadoPeticionEnum;
	}

	public void setEstadoPeticionEnum(EstadoPeticionEnum estadoPeticionEnum) {
		this.estadoPeticionEnum = estadoPeticionEnum;
	}

	public Integer getTipoFechaEnvio() {
		return tipoFechaEnvio;
	}

	public void setTipoFechaEnvio(Integer tipoFechaEnvio) {
		this.tipoFechaEnvio = tipoFechaEnvio;
	}

	public Integer getFechaContableEnvioInteger() {
		return fechaContableEnvioInteger;
	}

	public void setFechaContableEnvioInteger(Integer fechaContableEnvioInteger) {
		this.fechaContableEnvioInteger = fechaContableEnvioInteger;
	}

	public Integer getIndicadorUgente() {
		return indicadorUgente;
	}

	public void setIndicadorUgente(Integer indicadorUgente) {
		this.indicadorUgente = indicadorUgente;
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

	public BigDecimal getTotalAEnviar() {
		return totalAEnviar;
	}

	public void setTotalAEnviar(BigDecimal totalAEnviar) {
		this.totalAEnviar = totalAEnviar;
	}
}