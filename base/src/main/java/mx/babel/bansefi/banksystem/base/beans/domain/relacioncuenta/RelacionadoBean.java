package mx.babel.bansefi.banksystem.base.beans.domain.relacioncuenta;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import mx.babel.bansefi.banksystem.base.beans.domain.ClientePersonaFisicaBean;
import mx.babel.bansefi.banksystem.base.enums.EstadoListadosEnum;

public class RelacionadoBean implements Serializable{
	/**
     *
     */
    private static final long serialVersionUID = -1474128413157189320L;
    private TipoRelacionPersonaCuenta tipo;
	private ClientePersonaFisicaBean persona;
	private BigDecimal porcentaje;
	private Integer numero;
	private BigDecimal garantia;
	private String moneda;
	private Date fechaAlta;
	private Date fechaInicio;
	private Date fechaInactivo;
	private String razonCambio;
	private String estado;
	private String representante;
	private EstadoListadosEnum estadoListado;
	private String respaldo;
	private String codPe;
	/**
	 * @return Atributo tipo
	 */
	public TipoRelacionPersonaCuenta getTipo() {
		return tipo;
	}
	/**
	 * @param tipo Atributo tipo a definir
	 */
	public void setTipo(final TipoRelacionPersonaCuenta tipo) {
		this.tipo = tipo;
	}
	/**
	 * @return Atributo persona
	 */
	public ClientePersonaFisicaBean getPersona() {
		return persona;
	}
	/**
	 * @param persona Atributo persona a definir
	 */
	public void setPersona(final ClientePersonaFisicaBean persona) {
		this.persona = persona;
	}
	/**
	 * @return Atributo porcentaje
	 */
	public BigDecimal getPorcentaje() {
		return porcentaje;
	}
	/**
	 * @param porcentaje Atributo porcentaje a definir
	 */
	public void setPorcentaje(final BigDecimal porcentaje) {
		this.porcentaje = porcentaje;
	}
	/**
	 * @return Atributo numero
	 */
	public Integer getNumero() {
		return numero;
	}
	/**
	 * @param numero Atributo numero a definir
	 */
	public void setNumero(final Integer numero) {
		this.numero = numero;
	}
	/**
	 * @return Atributo garantia
	 */
	public BigDecimal getGarantia() {
		return garantia;
	}
	/**
	 * @param garantia Atributo garantia a definir
	 */
	public void setGarantia(final BigDecimal garantia) {
		this.garantia = garantia;
	}
	/**
	 * @return Atributo moneda
	 */
	public String getMoneda() {
		return moneda;
	}
	/**
	 * @param moneda Atributo moneda a definir
	 */
	public void setMoneda(final String moneda) {
		this.moneda = moneda;
	}

	/**
	 * @return Atributo fechaAlta
	 */
	public Date getFechaAlta() {
		return fechaAlta;
	}
	/**
	 * @param fechaAlta Atributo fechaAlta a definir
	 */
	public void setFechaAlta(final Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}
	/**
	 * @return Atributo fechaIncio
	 */
	public Date getFechaInicio() {
		return fechaInicio;
	}
	/**
	 * @param fechaIncio Atributo fechaIncio a definir
	 */
	public void setFechaInicio(final Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	/**
	 * @return Atributo activo
	 */
	public String getEstado() {
		return estado;
	}
	/**
	 * @param activo Atributo activo a definir
	 */
	public void setEstado(final String estado) {
		this.estado = estado;
	}
	/**
	 * @return Atributo representante
	 */
	public String getRepresentante() {
		return representante;
	}
	/**
	 * @param representante Atributo representante a definir
	 */
	public void setRepresentante(final String representante) {
		this.representante = representante;
	}
	/**
	 * @return Atributo estadoListado
	 */
	public EstadoListadosEnum getEstadoListado() {
		return estadoListado;
	}
	/**
	 * @param estadoListado Atributo estadoListado a definir
	 */
	public void setEstadoListado(final EstadoListadosEnum estadoListado) {
		this.estadoListado = estadoListado;
	}
	/**
	 * @return Atributo fechaInactivo
	 */
	public Date getFechaInactivo() {
		return fechaInactivo;
	}
	/**
	 * @param fechaInactivo Atributo fechaInactivo a definir
	 */
	public void setFechaInactivo(final Date fechaInactivo) {
		this.fechaInactivo = fechaInactivo;
	}
	/**
	 * @return Atributo razonCambio
	 */
	public String getRazonCambio() {
		return razonCambio;
	}
	/**
	 * @param razonCambio Atributo razonCambio a definir
	 */
	public void setRazonCambio(final String razonCambio) {
		this.razonCambio = razonCambio;
	}
	/**
	 * @return Atributo respaldo
	 */
	public String getRespaldo() {
		return respaldo;
	}
	/**
	 * @param respaldo Atributo respaldo a definir
	 */
	public void setRespaldo(final String respaldo) {
		this.respaldo = respaldo;
	}

	/**
	 * @return Atributo codPe
	 */
	public String getCodPe() {
		return codPe;
	}
	/**
	 * @param codPe Atributo codPe a definir
	 */
	public void setCodPe(String codPe) {
		this.codPe = codPe;
	}
	@Override
	public String toString() {
		return "RelacionadoBean [tipo=" + tipo + ", persona=" + persona
				+ ", porcentaje=" + porcentaje + ", numero=" + numero
				+ ", garantia=" + garantia + ", moneda=" + moneda
				+ ", fechaAlta=" + fechaAlta + ", fechaInactivo="
				+ fechaInactivo + ", razonCambio=" + razonCambio
				+ ", estadoListado=" + estadoListado + ", respaldo=" + respaldo + "]";
	}

}


