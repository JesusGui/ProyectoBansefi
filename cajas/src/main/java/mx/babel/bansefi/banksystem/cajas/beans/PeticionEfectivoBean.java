package mx.babel.bansefi.banksystem.cajas.beans;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Date;

import mx.babel.bansefi.banksystem.base.beans.parrilla.ExistenciaDenominacionBean;

/**
 * Bean con detalles de la peticion de efectivo
 * @author jose.saldana
 *
 */
public class PeticionEfectivoBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private BigDecimal totalPeticion;
	private ParrillaBean parillaBean;
	private HoraBean horaLimite;
	private Boolean guardar=false;
	private String observacion;
	private int tipoPeticion=1;
	private int indicadorUrgencia=0;
	private Date fechaAbastecimiento;
	private Boolean guardarDisabled= true;
	private String estatus;
	private String estatusDescripcion;
	private Boolean muestraBotonModificar;
	private String cod_INTERNO_UO_1;
	private Boolean muestraBotonSuprimir;
	private Boolean mayorHoraLimite;
	private  ComboUrgentesBean comboUrgenteBean;
	private List<ExistenciaDenominacionBean> listaDenominaciones;
private Boolean modificarDisabled= true;
	
	
	
	public Boolean getModificarDisabled() {
		return modificarDisabled;
	}

	public void setModificarDisabled(Boolean modificarDisabled) {
		this.modificarDisabled = modificarDisabled;
	}
	
	
	
	
	
	

	public List<ExistenciaDenominacionBean> getListaDenominaciones() {
		return listaDenominaciones;
	}

	public void setListaDenominaciones(
			List<ExistenciaDenominacionBean> listaDenominaciones) {
		this.listaDenominaciones = listaDenominaciones;
	}

	public ComboUrgentesBean getComboUrgenteBean() {
		return comboUrgenteBean;
	}

	public void setComboUrgenteBean(ComboUrgentesBean comboUrgenteBean) {
		this.comboUrgenteBean = comboUrgenteBean;
	}

	public Boolean getMayorHoraLimite() {
		return mayorHoraLimite;
	}

	public void setMayorHoraLimite(Boolean mayorHoraLimite) {
		this.mayorHoraLimite = mayorHoraLimite;
	}

	public Boolean getMuestraBotonSuprimir() {
		return muestraBotonSuprimir;
	}

	public void setMuestraBotonSuprimir(Boolean muestraBotonSuprimir) {
		this.muestraBotonSuprimir = muestraBotonSuprimir;
	}

	public String getCod_INTERNO_UO_1() {
		return cod_INTERNO_UO_1;
	}

	public void setCod_INTERNO_UO_1(String cod_INTERNO_UO_1) {
		this.cod_INTERNO_UO_1 = cod_INTERNO_UO_1;
	}

	public Boolean getMuestraBotonModificar() {
		return muestraBotonModificar;
	}

	public void setMuestraBotonModificar(Boolean muestraBotonModificar) {
		this.muestraBotonModificar = muestraBotonModificar;
	}
	
	
	
	public String getEstatusDescripcion() {
		return estatusDescripcion;
	}
	public void setEstatusDescripcion(String estatusDescripcion) {
		this.estatusDescripcion = estatusDescripcion;
	}
	public String getEstatus() {
		return estatus;
	}
	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}
	public Boolean getGuardarDisabled() {
		return guardarDisabled;
	}
	public void setGuardarDisabled(Boolean guardarDisabled) {
		this.guardarDisabled = guardarDisabled;
	}
	public String getObservacion() {
		return observacion;
	}
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	public Boolean getGuardar() {
		return guardar;
	}
	public void setGuardar(Boolean guardar) {
		this.guardar = guardar;
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

	
	
	public int getTipoPeticion() {
		return tipoPeticion;
	}
	public void setTipoPeticion(int tipoPeticion) {
		this.tipoPeticion = tipoPeticion;
	}
	public HoraBean getHoraLimite() {
		return horaLimite;
	}
	public void setHoraLimite(HoraBean horaLimite) {
		this.horaLimite = horaLimite;
	}
	public ParrillaBean getParillaBean() {
		return parillaBean;
	}
	public void setParillaBean(ParrillaBean parillaBean) {
		this.parillaBean = parillaBean;
	}
	public BigDecimal getTotalPeticion() {
		return totalPeticion;
	}
	public void setTotalPeticion(BigDecimal totalPeticion) {
		this.totalPeticion = totalPeticion;
	}
	
	
	

}
