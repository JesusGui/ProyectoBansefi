package mx.babel.bansefi.banksystem.oficina.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

/**
 * Bean para almacenar la informacion detalle de una operacion en el diario
 * electronico.
 * 
 * @author manuel.nieto, alejando.perez
 * 
 */
public class DiarioElectronicoResultadoBean implements Serializable {

	private static final long serialVersionUID = 3560802684874569004L;

	private String id;

	private Date fechaContable;

	private Date fechaValor;

	private String puesto;

	private String cuenta;

	private BigDecimal importe;

	private String numSecuencia;

	private String debeHaber;

	private String cajaIntervencion;

	private String clob;

	private String subclob;

	private String respuesta;

	private String usuario;

	private String terminal;

	private String entidad;

	private String autorizado;

	private String centro;

	private String plaza;

	private String centroApertura;

	private String modo;

	private String tipoOperacion;

	private String situacion;

	private int digito;

	private Date fechaTecleo;

	private String horaInicio;

	private String horaFin;

	private Date fechaOperacion;

	private String numeroRespuesta;

	private BigDecimal saldo;

	private String moneda;

	private String claveAnulacion;

	private Date horaTecleo;

	private String titular;

	private String codigo;

	private String estado;

	private Map<String, String> informacionVariable;

	/**
	 * Anulacion
	 */

	private Date fechaAnulacion;

	private String usuarioAnulacion;

	private String terminalAnulacion;

	private String numeroSecuenciaAnulacion;

	private String servicioAnulacion;

	/**
	 * Transmision
	 */

	private Date fechaTransmision;

	private String usuarioTransmision;

	private String terminalTransmision;

	private String numeroSecuenciaOffTransmision;

	/**
	 * @return the fechaContable
	 */
	public Date getFechaContable() {
		return fechaContable;
	}

	/**
	 * @param fechaContable
	 *            the fechaContable to set
	 */
	public void setFechaContable(Date fechaContable) {
		this.fechaContable = fechaContable;
	}

	/**
	 * @return the fechaValor
	 */
	public Date getFechaValor() {
		return fechaValor;
	}

	/**
	 * @param fechaValor
	 *            the fechaValor to set
	 */
	public void setFechaValor(Date fechaValor) {
		this.fechaValor = fechaValor;
	}

	/**
	 * @return the puesto
	 */
	public String getPuesto() {
		return puesto;
	}

	/**
	 * @param puesto
	 *            the puesto to set
	 */
	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}

	/**
	 * @return the cuenta
	 */
	public String getCuenta() {
		return cuenta;
	}

	/**
	 * @param cuenta
	 *            the cuenta to set
	 */
	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}

	/**
	 * @return the importe
	 */
	public BigDecimal getImporte() {
		return importe;
	}

	/**
	 * @param importe
	 *            the importe to set
	 */
	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}

	/**
	 * @return the numSecuencia
	 */
	public String getNumSecuencia() {
		return numSecuencia;
	}

	/**
	 * @param numSecuencia
	 *            the numSecuencia to set
	 */
	public void setNumSecuencia(String numSecuencia) {
		this.numSecuencia = numSecuencia;
	}

	/**
	 * @return the debeHaber
	 */
	public String getDebeHaber() {
		return debeHaber;
	}
	
	/**
	 * Funcion que devuelve el tipo de operación, en string completo
	 * @return
	 */
	public String getDebeHaberToString(){
		if(!StringUtils.isBlank(getDebeHaber())){
			if("D".equals(getDebeHaber())){
				return "DEBE";
			}else if("H".equals(getDebeHaber())){
				return "HABER";
			}else{
				return "NO IDENTIFICADO";
			}
		}else{
			return null;
		}
		
	}

	/**
	 * @param debeHaber
	 *            the debeHaber to set
	 */
	public void setDebeHaber(String debeHaber) {
		this.debeHaber = debeHaber;
	}

	/**
	 * @return the cajaIntervencion
	 */
	public String getCajaIntervencion() {
		return cajaIntervencion;
	}

	/**
	 * @param cajaIntervencion
	 *            the cajaIntervencion to set
	 */
	public void setCajaIntervencion(String cajaIntervencion) {
		this.cajaIntervencion = cajaIntervencion;
	}

	/**
	 * @return the clob
	 */
	public String getClob() {
		return clob;
	}

	/**
	 * @param clob
	 *            the clob to set
	 */
	public void setClob(String clob) {
		this.clob = clob;
	}

	/**
	 * @return the subclob
	 */
	public String getSubclob() {
		return subclob;
	}

	/**
	 * @param subclob
	 *            the subclob to set
	 */
	public void setSubclob(String subclob) {
		this.subclob = subclob;
	}

	/**
	 * @return the respuesta
	 */
	public String getRespuesta() {
		return respuesta;
	}
	
	/**
	 * Funcion que interpreta el tipo de respuseta y devuelve el texto
	 * completo correspondiente
	 * @return
	 */
	public String getRespuestaToString(){
		if(!StringUtils.isBlank(getRespuesta())){
			if("0".equals(getRespuesta())){
				return "BIEN";
			}else if("1".equals(getRespuesta())){
				return "MAL";
			}else{
				return "SIN RESPUESTA";
			}
		}else{
			return "SIN RESPUESTA";
		}
	}

	/**
	 * @param respuesta
	 *            the respuesta to set
	 */
	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}

	/**
	 * @return the usuario
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario
	 *            the usuario to set
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	/**
	 * @return the terminal
	 */
	public String getTerminal() {
		return terminal;
	}

	/**
	 * @param terminal
	 *            the terminal to set
	 */
	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}

	/**
	 * @return the autorizado
	 */
	public String getAutorizado() {
		return autorizado;
	}

	/**
	 * @param autorizado
	 *            the autorizado to set
	 */
	public void setAutorizado(String autorizado) {
		this.autorizado = autorizado;
	}

	/**
	 * @return the centro
	 */
	public String getCentro() {
		return centro;
	}

	/**
	 * @param centro
	 *            the centro to set
	 */
	public void setCentro(String centro) {
		this.centro = centro;
	}

	/**
	 * @return the centroApertura
	 */
	public String getCentroApertura() {
		return centroApertura;
	}

	/**
	 * @param centroApertura
	 *            the centroApertura to set
	 */
	public void setCentroApertura(String centroApertura) {
		this.centroApertura = centroApertura;
	}

	/**
	 * @return the modo
	 */
	public String getModo() {
		return modo;
	}

	/**
	 * @param modo
	 *            the modo to set
	 */
	public void setModo(String modo) {
		this.modo = modo;
	}

	/**
	 * @return the tipoOperacion
	 */
	public String getTipoOperacion() {
		return tipoOperacion;
	}

	/**
	 * @param tipoOperacion
	 *            the tipoOperacion to set
	 */
	public void setTipoOperacion(String tipoOperacion) {
		this.tipoOperacion = tipoOperacion;
	}

	/**
	 * @return the situacion
	 */
	public String getSituacion() {
		return situacion;
	}

	/**
	 * @param situacion
	 *            the situacion to set
	 */
	public void setSituacion(String situacion) {
		this.situacion = situacion;
	}

	/**
	 * @return the fechaTecleo
	 */
	public Date getFechaTecleo() {
		return fechaTecleo;
	}

	/**
	 * @param fechaTecleo
	 *            the fechaTecleo to set
	 */
	public void setFechaTecleo(Date fechaTecleo) {
		this.fechaTecleo = fechaTecleo;
	}

	/**
	 * @return the horaInicio
	 */
	public String getHoraInicio() {
		return horaInicio;
	}

	/**
	 * @param horaInicio
	 *            the horaInicio to set
	 */
	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}

	/**
	 * @return the horaFin
	 */
	public String getHoraFin() {
		return horaFin;
	}

	/**
	 * @param horaFin
	 *            the horaFin to set
	 */
	public void setHoraFin(String horaFin) {
		this.horaFin = horaFin;
	}

	/**
	 * @return the fechaOperacion
	 */
	public Date getFechaOperacion() {
		return fechaOperacion;
	}

	/**
	 * @param fechaOperacion
	 *            the fechaOperacion to set
	 */
	public void setFechaOperacion(Date fechaOperacion) {
		this.fechaOperacion = fechaOperacion;
	}

	/**
	 * @return the numeroRespuesta
	 */
	public String getNumeroRespuesta() {
		return numeroRespuesta;
	}

	/**
	 * @param numeroRespuesta
	 *            the numeroRespuesta to set
	 */
	public void setNumeroRespuesta(String numeroRespuesta) {
		this.numeroRespuesta = numeroRespuesta;
	}

	/**
	 * @return the saldo
	 */
	public BigDecimal getSaldo() {
		return saldo;
	}

	/**
	 * @param saldo
	 *            the saldo to set
	 */
	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}

	/**
	 * @return the moneda
	 */
	public String getMoneda() {
		return moneda;
	}

	/**
	 * @param moneda
	 *            the moneda to set
	 */
	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	/**
	 * @return the claveAnulacion
	 */
	public String getClaveAnulacion() {
		return claveAnulacion;
	}

	/**
	 * @param claveAnulacion
	 *            the claveAnulacion to set
	 */
	public void setClaveAnulacion(String claveAnulacion) {
		this.claveAnulacion = claveAnulacion;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getHoraTecleo() {
		return horaTecleo;
	}

	public void setHoraTecleo(Date horaTecleo) {
		this.horaTecleo = horaTecleo;
	}

	public Map<String, String> getInformacionVariable() {
		return informacionVariable;
	}

	public void setInformacionVariable(Map<String, String> informacionVariable) {
		this.informacionVariable = informacionVariable;
	}

	public Date getFechaAnulacion() {
		return fechaAnulacion;
	}

	public void setFechaAnulacion(Date fechaAnulacion) {
		this.fechaAnulacion = fechaAnulacion;
	}

	public String getUsuarioAnulacion() {
		return usuarioAnulacion;
	}

	public void setUsuarioAnulacion(String usuarioAnulacion) {
		this.usuarioAnulacion = usuarioAnulacion;
	}

	public String getTerminalAnulacion() {
		return terminalAnulacion;
	}

	public void setTerminalAnulacion(String terminalAnulacion) {
		this.terminalAnulacion = terminalAnulacion;
	}

	public String getNumeroSecuenciaAnulacion() {
		return numeroSecuenciaAnulacion;
	}

	public void setNumeroSecuenciaAnulacion(String numeroSecuenciaAnulacion) {
		this.numeroSecuenciaAnulacion = numeroSecuenciaAnulacion;
	}

	public Date getFechaTransmision() {
		return fechaTransmision;
	}

	public void setFechaTransmision(Date fechaTransmision) {
		this.fechaTransmision = fechaTransmision;
	}

	public String getUsuarioTransmision() {
		return usuarioTransmision;
	}

	public void setUsuarioTransmision(String usuarioTransmision) {
		this.usuarioTransmision = usuarioTransmision;
	}

	public String getTerminalTransmision() {
		return terminalTransmision;
	}

	public void setTerminalTransmision(String terminalTransmision) {
		this.terminalTransmision = terminalTransmision;
	}

	public String getNumeroSecuenciaOffTransmision() {
		return numeroSecuenciaOffTransmision;
	}

	public void setNumeroSecuenciaOffTransmision(
			String numeroSecuenciaOffTransmision) {
		this.numeroSecuenciaOffTransmision = numeroSecuenciaOffTransmision;
	}

	public String getEntidad() {
		return entidad;
	}

	public void setEntidad(String entidad) {
		this.entidad = entidad;
	}

	public String getPlaza() {
		return plaza;
	}

	public void setPlaza(String plaza) {
		this.plaza = plaza;
	}

	public int getDigito() {
		return digito;
	}

	public void setDigito(int digito) {
		this.digito = digito;
	}

	public String getTitular() {
		return titular;
	}

	public void setTitular(String titular) {
		this.titular = titular;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getServicioAnulacion() {
		return servicioAnulacion;
	}

	public void setServicioAnulacion(String servicioAnulacion) {
		this.servicioAnulacion = servicioAnulacion;
	}

}
