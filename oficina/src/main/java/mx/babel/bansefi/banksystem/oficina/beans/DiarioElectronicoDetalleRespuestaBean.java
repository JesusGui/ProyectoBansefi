package mx.babel.bansefi.banksystem.oficina.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

public class DiarioElectronicoDetalleRespuestaBean implements Serializable{

	private static final long serialVersionUID = 6212619794458050437L;

	//Campos genericos
	private String entidad;
	private String centro;
	private String plaza;
	private String terminal;
	private String usuario;
	
	private Date fechaContable;
	private Date fechaValor;
	private Date horaTecleo;
	private String debeHaber;
	private String tipoOperacion;
	private String respuesta;
	
	private Date fechaOperacion;
	private String digito;
	private String noSecuencia;
	private String idTransaccion;
	private String estatus;
	
	private String titular;
	private String noCuenta;
	private String tipoIdentificacion;
	private String noIdentificacion;
	
	private String movimiento;
	private String codigoOperacion;
	private String concepto;
	private BigDecimal importe;
	
	//Resto de campos
	private Map<String, String> camposEntradas;
	private Map<String, String> camposSalidas;
	//Unifica los dos mapas anteriores
	private Map<String, String> unificacion;
	
	public String getEntidad() {
		return entidad;
	}
	public void setEntidad(String entidad) {
		this.entidad = entidad;
	}		
	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}
	public Map<String, String> getCamposEntradas() {
		return camposEntradas;
	}
	public void setCamposEntradas(Map<String, String> camposEntradas) {
		this.camposEntradas = camposEntradas;
	}
	public Map<String, String> getCamposSalidas() {
		return camposSalidas;
	}
	public void setCamposSalidas(Map<String, String> camposSalidas) {
		this.camposSalidas = camposSalidas;
	}
	public String getCentro() {
		return centro;
	}
	public void setCentro(String centro) {
		this.centro = centro;
	}
	public String getPlaza() {
		return plaza;
	}
	public void setPlaza(String plaza) {
		this.plaza = plaza;
	}
	public String getTerminal() {
		return terminal;
	}
	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public Date getFechaContable() {
		return fechaContable;
	}
	public void setFechaContable(Date fechaContable) {
		this.fechaContable = fechaContable;
	}
	public Date getFechaValor() {
		return fechaValor;
	}
	public void setFechaValor(Date fechaValor) {
		this.fechaValor = fechaValor;
	}
	public Date getHoraTecleo() {
		return horaTecleo;
	}
	public void setHoraTecleo(Date horaTecleo) {
		this.horaTecleo = horaTecleo;
	}
	public String getDebeHaber() {
		return debeHaber;
	}
	public void setDebeHaber(String debeHaber) {
		this.debeHaber = debeHaber;
	}
	public String getTipoOperacion() {
		return tipoOperacion;
	}
	public void setTipoOperacion(String tipoOperacion) {
		this.tipoOperacion = tipoOperacion;
	}
	public Date getFechaOperacion() {
		return fechaOperacion;
	}
	public void setFechaOperacion(Date fechaOperacion) {
		this.fechaOperacion = fechaOperacion;
	}
	public String getDigito() {
		return digito;
	}
	public void setDigito(String digito) {
		this.digito = digito;
	}
	public String getNoSecuencia() {
		return noSecuencia;
	}
	public void setNoSecuencia(String noSecuencia) {
		this.noSecuencia = noSecuencia;
	}
	public String getIdTransaccion() {
		return idTransaccion;
	}
	public void setIdTransaccion(String idTransaccion) {
		this.idTransaccion = idTransaccion;
	}
	public String getEstatus() {
		return estatus;
	}
	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}
	public String getTitular() {
		return titular;
	}
	public void setTitular(String titular) {
		this.titular = titular;
	}
	public String getNoCuenta() {
		return noCuenta;
	}
	public void setNoCuenta(String noCuenta) {
		this.noCuenta = noCuenta;
	}
	public String getTipoIdentificacion() {
		return tipoIdentificacion;
	}
	public void setTipoIdentificacion(String tipoIdentificacion) {
		this.tipoIdentificacion = tipoIdentificacion;
	}
	public String getNoIdentificacion() {
		return noIdentificacion;
	}
	public void setNoIdentificacion(String noIdentificacion) {
		this.noIdentificacion = noIdentificacion;
	}
	public String getMovimiento() {
		return movimiento;
	}
	public void setMovimiento(String movimiento) {
		this.movimiento = movimiento;
	}
	public String getCodigoOperacion() {
		return codigoOperacion;
	}
	public void setCodigoOperacion(String codigoOperacion) {
		this.codigoOperacion = codigoOperacion;
	}
	public String getConcepto() {
		return concepto;
	}
	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}
	public Map<String, String> getUnificacion() {
		return unificacion;
	}
	public void setUnificacion(Map<String, String> unificacion) {
		this.unificacion = unificacion;
	}
	public BigDecimal getImporte() {
		return importe;
	}
	public String getRespuesta() {
		return respuesta;
	}
	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
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
	
}
