package mx.babel.bansefi.banksystem.base.beans;

import java.io.Serializable;
import java.util.Date;

/**
 * @author gerardo.pucheta
 */
public class AuditoriaBean implements Serializable {

	private static final long serialVersionUID = 6651469948753421468L;

	private Date fechaOperacion;
	private String fechaOperacionStr;
	private String horaOperacion;
	private String situacion;
	private String idSituacion;
	private String transaccion;
	private String transaccionDesc;
	private String terminal;
	private String entidad;
	private String entidadDesc;
	private String empleadoOrigen;
	private String empleadoOrigenDesc;
	private String centroOperativo;
	private String centroOperativoDesc;
	private String empleadoAutorizado;
	private String empleadoAutorizadoDesc;
	private String centroAc;
	private String centroAcDesc;
	private Date fechaContable;
	private String fechaContableStr;
	private Date fechaInicioEstadoCuenta;
	private String fechaInicioEstadoCuentaStr;
	private Date fechaFinEstadoCuenta;
	private String fechaFinEstadoCuentaStr;
	private String codigoEstadoAcuerdo;
	private String codigoEstadoAcuerdoStr;
	private String codigoRazonEstadoAcuerdo;
	private String cuenta;

	public Date getFechaOperacion() {
		return fechaOperacion;
	}

	public void setFechaOperacion(Date fechaOperacion) {
		this.fechaOperacion = fechaOperacion;
	}

	public String getHoraOperacion() {
		return horaOperacion;
	}

	public void setHoraOperacion(String horaOperacion) {
		this.horaOperacion = horaOperacion;
	}

	public String getSituacion() {
		return situacion;
	}

	public void setSituacion(String situacion) {
		this.situacion = situacion;
	}

	public String getIdSituacion() {
		return idSituacion;
	}

	public void setIdSituacion(String idSituacion) {
		this.idSituacion = idSituacion;
	}

	public String getTransaccion() {
		return transaccion;
	}

	public void setTransaccion(String transaccion) {
		this.transaccion = transaccion;
	}

	public String getTransaccionDesc() {
		return transaccionDesc;
	}

	public void setTransaccionDesc(String transaccionDesc) {
		this.transaccionDesc = transaccionDesc;
	}

	public String getTerminal() {
		return terminal;
	}

	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}

	public String getEntidad() {
		return entidad;
	}

	public void setEntidad(String entidad) {
		this.entidad = entidad;
	}

	public String getEntidadDesc() {
		return entidadDesc;
	}

	public void setEntidadDesc(String entidadDesc) {
		this.entidadDesc = entidadDesc;
	}

	public String getEmpleadoOrigen() {
		return empleadoOrigen;
	}

	public void setEmpleadoOrigen(String empleadoOrigen) {
		this.empleadoOrigen = empleadoOrigen;
	}

	public String getEmpleadoOrigenDesc() {
		return empleadoOrigenDesc;
	}

	public void setEmpleadoOrigenDesc(String empleadoOrigenDesc) {
		this.empleadoOrigenDesc = empleadoOrigenDesc;
	}

	public String getCentroOperativo() {
		return centroOperativo;
	}

	public void setCentroOperativo(String centroOperativo) {
		this.centroOperativo = centroOperativo;
	}

	public String getCentroOperativoDesc() {
		return centroOperativoDesc;
	}

	public void setCentroOperativoDesc(String centroOperativoDesc) {
		this.centroOperativoDesc = centroOperativoDesc;
	}

	public String getEmpleadoAutorizado() {
		return empleadoAutorizado;
	}

	public void setEmpleadoAutorizado(String empleadoAutorizado) {
		this.empleadoAutorizado = empleadoAutorizado;
	}

	public String getEmpleadoAutorizadoDesc() {
		return empleadoAutorizadoDesc;
	}

	public void setEmpleadoAutorizadoDesc(String empleadoAutorizadoDesc) {
		this.empleadoAutorizadoDesc = empleadoAutorizadoDesc;
	}

	public String getCentroAc() {
		return centroAc;
	}

	public void setCentroAc(String centroAc) {
		this.centroAc = centroAc;
	}

	public String getCentroAcDesc() {
		return centroAcDesc;
	}

	public void setCentroAcDesc(String centroAcDesc) {
		this.centroAcDesc = centroAcDesc;
	}

	public Date getFechaContable() {
		return fechaContable;
	}

	public void setFechaContable(Date fechaContable) {
		this.fechaContable = fechaContable;
	}

	public String getFechaOperacionStr() {
		return fechaOperacionStr;
	}

	public void setFechaOperacionStr(String fechaOperacionStr) {
		this.fechaOperacionStr = fechaOperacionStr;
	}

	public String getFechaContableStr() {
		return fechaContableStr;
	}

	public void setFechaContableStr(String fechaContableStr) {
		this.fechaContableStr = fechaContableStr;
	}

	public Date getFechaInicioEstadoCuenta() {
		return fechaInicioEstadoCuenta;
	}

	public void setFechaInicioEstadoCuenta(Date fechaInicioEstadoCuenta) {
		this.fechaInicioEstadoCuenta = fechaInicioEstadoCuenta;
	}

	public String getFechaInicioEstadoCuentaStr() {
		return fechaInicioEstadoCuentaStr;
	}

	public void setFechaInicioEstadoCuentaStr(String fechaInicioEstadoCuentaStr) {
		this.fechaInicioEstadoCuentaStr = fechaInicioEstadoCuentaStr;
	}

	public Date getFechaFinEstadoCuenta() {
		return fechaFinEstadoCuenta;
	}

	public void setFechaFinEstadoCuenta(Date fechaFinEstadoCuenta) {
		this.fechaFinEstadoCuenta = fechaFinEstadoCuenta;
	}

	public String getFechaFinEstadoCuentaStr() {
		return fechaFinEstadoCuentaStr;
	}

	public void setFechaFinEstadoCuentaStr(String fechaFinEstadoCuentaStr) {
		this.fechaFinEstadoCuentaStr = fechaFinEstadoCuentaStr;
	}

	public String getCodigoEstadoAcuerdo() {
		return codigoEstadoAcuerdo;
	}

	public void setCodigoEstadoAcuerdo(String codigoEstadoAcuerdo) {
		this.codigoEstadoAcuerdo = codigoEstadoAcuerdo;
	}

	public String getCodigoRazonEstadoAcuerdo() {
		return codigoRazonEstadoAcuerdo;
	}

	public void setCodigoRazonEstadoAcuerdo(String codigoRazonEstadoAcuerdo) {
		this.codigoRazonEstadoAcuerdo = codigoRazonEstadoAcuerdo;
	}

	public String getCuenta() {
		return cuenta;
	}

	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}

	public String getCodigoEstadoAcuerdoStr() {
		return codigoEstadoAcuerdoStr;
	}

	public void setCodigoEstadoAcuerdoStr(String codigoEstadoAcuerdoStr) {
		this.codigoEstadoAcuerdoStr = codigoEstadoAcuerdoStr;
	}

	@Override
	public String toString() {
		return "AuditoriaBean [fechaOperacion=" + fechaOperacion
				+ ", fechaOperacionStr=" + fechaOperacionStr
				+ ", horaOperacion=" + horaOperacion + ", situacion="
				+ situacion + ", idSituacion=" + idSituacion + ", transaccion="
				+ transaccion + ", transaccionDesc=" + transaccionDesc
				+ ", terminal=" + terminal + ", entidad=" + entidad
				+ ", entidadDesc=" + entidadDesc + ", empleadoOrigen="
				+ empleadoOrigen + ", empleadoOrigenDesc=" + empleadoOrigenDesc
				+ ", centroOperativo=" + centroOperativo
				+ ", centroOperativoDesc=" + centroOperativoDesc
				+ ", empleadoAutorizado=" + empleadoAutorizado
				+ ", empleadoAutorizadoDesc=" + empleadoAutorizadoDesc
				+ ", centroAc=" + centroAc + ", centroAcDesc=" + centroAcDesc
				+ ", fechaContable=" + fechaContable + ", fechaContableStr="
				+ fechaContableStr + ", fechaInicioEstadoCuenta="
				+ fechaInicioEstadoCuenta + ", fechaInicioEstadoCuentaStr="
				+ fechaInicioEstadoCuentaStr + ", fechaFinEstadoCuenta="
				+ fechaFinEstadoCuenta + ", fechaFinEstadoCuentaStr="
				+ fechaFinEstadoCuentaStr + ", codigoEstadoAcuerdo="
				+ codigoEstadoAcuerdo + ", codigoEstadoAcuerdoStr="
				+ codigoEstadoAcuerdoStr + ", codigoRazonEstadoAcuerdo="
				+ codigoRazonEstadoAcuerdo + ", cuenta=" + cuenta + "]";
	}

}