package mx.babel.bansefi.banksystem.cajas.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import mx.babel.bansefi.banksystem.base.beans.parrilla.ExistenciaDenominacionBean;

/**
 * Bean con detalles de la recepcion de efectivo
 * @author jose.saldana
 *
 */
public class RecepcionEfectivoBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9087882598824888784L;
	private Date fechaAbastecimiento;
	private ParrillaBean parillaBean;
	private int indicadorUrgencia=0;
	private String observacion;
	private String estatus;
	private String estatusDescripcion;
	public String getEstatusDescripcion() {
		return estatusDescripcion;
	}
	public void setEstatusDescripcion(String estatusDescripcion) {
		this.estatusDescripcion = estatusDescripcion;
	}
	private String cod_INTERNO_UO_1;
	private BigDecimal totalAutorizado;
	private BigDecimal totalRecibido;
	private BigDecimal diferencia;
	private List<ExistenciaDenominacionBean> listaDenominaciones;
	
	
	
	public List<ExistenciaDenominacionBean> getListaDenominaciones() {
		return listaDenominaciones;
	}
	public void setListaDenominaciones(
			List<ExistenciaDenominacionBean> listaDenominaciones) {
		this.listaDenominaciones = listaDenominaciones;
	}
	public BigDecimal getDiferencia() {
		return diferencia;
	}
	public void setDiferencia(BigDecimal diferencia) {
		this.diferencia = diferencia;
	}
	public BigDecimal getTotalAutorizado() {
		return totalAutorizado;
	}
	public void setTotalAutorizado(BigDecimal totalAutorizado) {
		this.totalAutorizado = totalAutorizado;
	}
	public BigDecimal getTotalRecibido() {
		return totalRecibido;
	}
	public void setTotalRecibido(BigDecimal totalRecibido) {
		this.totalRecibido = totalRecibido;
	}
	public String getObservacion() {
		return observacion;
	}
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	public String getEstatus() {
		return estatus;
	}
	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}
	public String getCod_INTERNO_UO_1() {
		return cod_INTERNO_UO_1;
	}
	public void setCod_INTERNO_UO_1(String cod_INTERNO_UO_1) {
		this.cod_INTERNO_UO_1 = cod_INTERNO_UO_1;
	}
	public int getIndicadorUrgencia() {
		return indicadorUrgencia;
	}
	public void setIndicadorUrgencia(int indicadorUrgencia) {
		this.indicadorUrgencia = indicadorUrgencia;
	}
	public Date getFechaAbastecimiento() {
		return fechaAbastecimiento;
	}
	public void setFechaAbastecimiento(Date fechaAbastecimiento) {
		this.fechaAbastecimiento = fechaAbastecimiento;
	}
	public ParrillaBean getParillaBean() {
		return parillaBean;
	}
	public void setParillaBean(ParrillaBean parillaBean) {
		this.parillaBean = parillaBean;
	}
	
	
}
