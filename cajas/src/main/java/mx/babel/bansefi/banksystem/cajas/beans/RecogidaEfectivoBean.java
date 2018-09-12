package mx.babel.bansefi.banksystem.cajas.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import mx.babel.bansefi.banksystem.base.beans.parrilla.ExistenciaDenominacionBean;

/**
 * Bean con detalles de la recogida de efectivo
 * 
 * @author aaron.lopez
 * 
 */
public class RecogidaEfectivoBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4279643206305677467L;

	private HoraBean horaBean;

	private ComboUrgentesBean comboUrgentesBean;

	private Date fechaRecogida;

	private List<ExistenciaDenominacionBean> listaDenominaciones;

	private String entidad;
	private String centro;
	private String codigoMoneda;
	private String codigoDistribucion;
	private String centroControlador;
	private String estatusC;
	private String estatusL;
	private String idEmpleado;
	private String observacion;
	private String tituloResumen;

	private int tipoPeticion = 1;
	private int indicadorUrgencia = 0;
	private int fechaSolicitud;
	private int fechaProceso;
	private int fechaAbastecimiento;

	private BigDecimal totalAutorizado;
	private BigDecimal totalPedido;
	private BigDecimal totalRecibido;
	private BigDecimal totalCertificado;
	private BigDecimal totalRecogida;

	public HoraBean getHoraBean() {
		return horaBean;
	}

	public void setHoraBean(HoraBean horaBean) {
		this.horaBean = horaBean;
	}

	public ComboUrgentesBean getComboUrgentesBean() {
		return comboUrgentesBean;
	}

	public void setComboUrgentesBean(ComboUrgentesBean comboUrgentesBean) {
		this.comboUrgentesBean = comboUrgentesBean;
	}

	public Date getFechaRecogida() {
		return fechaRecogida;
	}

	public void setFechaRecogida(Date fechaRecogida) {
		this.fechaRecogida = fechaRecogida;
	}

	public List<ExistenciaDenominacionBean> getListaDenominaciones() {
		return listaDenominaciones;
	}

	public void setListaDenominaciones(
			List<ExistenciaDenominacionBean> listaDenominaciones) {
		this.listaDenominaciones = listaDenominaciones;
	}

	public BigDecimal getTotalRecogida() {
		return totalRecogida;
	}

	public void setTotalRecogida(BigDecimal totalRecogida) {
		this.totalRecogida = totalRecogida;
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

	public String getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(String idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	
	public String getTituloResumen() {
		return tituloResumen;
	}

	public void setTituloResumen(String tituloResumen) {
		this.tituloResumen = tituloResumen;
	}

	public int getTipoPeticion() {
		return tipoPeticion;
	}

	public void setTipoPeticion(int tipoPeticion) {
		this.tipoPeticion = tipoPeticion;
	}

	public int getIndicadorUrgencia() {
		return indicadorUrgencia;
	}

	public void setIndicadorUrgencia(int indicadorUrgencia) {
		this.indicadorUrgencia = indicadorUrgencia;
	}

	public int getFechaSolicitud() {
		return fechaSolicitud;
	}

	public void setFechaSolicitud(int fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}

	public int getFechaProceso() {
		return fechaProceso;
	}

	public void setFechaProceso(int fechaProceso) {
		this.fechaProceso = fechaProceso;
	}

	public int getFechaAbastecimiento() {
		return fechaAbastecimiento;
	}

	public void setFechaAbastecimiento(int fechaAbastecimiento) {
		this.fechaAbastecimiento = fechaAbastecimiento;
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
