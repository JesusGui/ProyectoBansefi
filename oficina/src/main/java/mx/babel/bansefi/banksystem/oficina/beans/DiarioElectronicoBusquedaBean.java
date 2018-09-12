package mx.babel.bansefi.banksystem.oficina.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class DiarioElectronicoBusquedaBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6764344318158978161L;
	
	private int id;

	private Date contableDesde;
	
	private Date contableHasta;
	
	private Date tecleoDesde;
	
	private Date tecleoHasta;
	
	private String horaTecleo;
	
	private boolean fechaValorBool;
	
	private Date fechaValor;
	
	private Date fechaAnulacion;
	
	private String tipoImporte;
	
	private BigDecimal importeDesde;
	
	private BigDecimal importeHasta;
	
	private String moneda;
	
	private String terminacionImporte;
	
	private String signoContable;
	
	private String transaccion;
	
	private String clob;
	
	private String numSecuencia;
	
	private String modo;
	
	private String tipoOperacion;
	
	private String respuesta;
	
	private String situacion;
	
	private String cuenta;
	
	private String puesto;
	
	private String terminal;
	
	private String centroApertura;
	
	private String usuario;
	
	private String usuarioAutorizador;
	
	private boolean incluirBool;
	
	private String usuarioAnulador;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the contableDesde
	 */
	public Date getContableDesde() {
		return contableDesde;
	}

	/**
	 * @param contableDesde the contableDesde to set
	 */
	public void setContableDesde(Date contableDesde) {
		this.contableDesde = contableDesde;
	}

	/**
	 * @return the contableHasta
	 */
	public Date getContableHasta() {
		return contableHasta;
	}

	/**
	 * @param contableHasta the contableHasta to set
	 */
	public void setContableHasta(Date contableHasta) {
		this.contableHasta = contableHasta;
	}

	/**
	 * @return the tecleoDesde
	 */
	public Date getTecleoDesde() {
		return tecleoDesde;
	}

	/**
	 * @param tecleoDesde the tecleoDesde to set
	 */
	public void setTecleoDesde(Date tecleoDesde) {
		this.tecleoDesde = tecleoDesde;
	}

	/**
	 * @return the tecleoHasta
	 */
	public Date getTecleoHasta() {
		return tecleoHasta;
	}

	/**
	 * @param tecleoHasta the tecleoHasta to set
	 */
	public void setTecleoHasta(Date tecleoHasta) {
		this.tecleoHasta = tecleoHasta;
	}

	/**
	 * @return the horaTecleo
	 */
	public String getHoraTecleo() {
		return horaTecleo;
	}

	/**
	 * @param horaTecleo the horaTecleo to set
	 */
	public void setHoraTecleo(String horaTecleo) {
		this.horaTecleo = horaTecleo;
	}

	/**
	 * @return the fechaValorBool
	 */
	public boolean isFechaValorBool() {
		return fechaValorBool;
	}

	/**
	 * @param fechaValorBool the fechaValorBool to set
	 */
	public void setFechaValorBool(boolean fechaValorBool) {
		this.fechaValorBool = fechaValorBool;
	}

	/**
	 * @return the fechaValor
	 */
	public Date getFechaValor() {
		return fechaValor;
	}

	/**
	 * @param fechaValor the fechaValor to set
	 */
	public void setFechaValor(Date fechaValor) {
		this.fechaValor = fechaValor;
	}

	/**
	 * @return the fechaAnulacion
	 */
	public Date getFechaAnulacion() {
		return fechaAnulacion;
	}

	/**
	 * @param fechaAnulacion the fechaAnulacion to set
	 */
	public void setFechaAnulacion(Date fechaAnulacion) {
		this.fechaAnulacion = fechaAnulacion;
	}

	/**
	 * @return the tipoImporte
	 */
	public String getTipoImporte() {
		return tipoImporte;
	}

	/**
	 * @param tipoImporte the tipoImporte to set
	 */
	public void setTipoImporte(String tipoImporte) {
		this.tipoImporte = tipoImporte;
	}

	/**
	 * @return the importeDesde
	 */
	public BigDecimal getImporteDesde() {
		return importeDesde;
	}

	/**
	 * @param importeDesde the importeDesde to set
	 */
	public void setImporteDesde(BigDecimal importeDesde) {
		this.importeDesde = importeDesde;
	}

	/**
	 * @return the importeHasta
	 */
	public BigDecimal getImporteHasta() {
		return importeHasta;
	}

	/**
	 * @param importeHasta the importeHasta to set
	 */
	public void setImporteHasta(BigDecimal importeHasta) {
		this.importeHasta = importeHasta;
	}

	/**
	 * @return the moneda
	 */
	public String getMoneda() {
		return moneda;
	}

	/**
	 * @param moneda the moneda to set
	 */
	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	/**
	 * @return the terminacionImporte
	 */
	public String getTerminacionImporte() {
		return terminacionImporte;
	}

	/**
	 * @param terminacionImporte the terminacionImporte to set
	 */
	public void setTerminacionImporte(String terminacionImporte) {
		this.terminacionImporte = terminacionImporte;
	}

	/**
	 * @return the signoContable
	 */
	public String getSignoContable() {
		return signoContable;
	}

	/**
	 * @param signoContable the signoContable to set
	 */
	public void setSignoContable(String signoContable) {
		this.signoContable = signoContable;
	}

	/**
	 * @return the transaccion
	 */
	public String getTransaccion() {
		return transaccion;
	}

	/**
	 * @param transaccion the transaccion to set
	 */
	public void setTransaccion(String transaccion) {
		this.transaccion = transaccion;
	}

	/**
	 * @return the clob
	 */
	public String getClob() {
		return clob;
	}

	/**
	 * @param clob the clob to set
	 */
	public void setClob(String clob) {
		this.clob = clob;
	}

	/**
	 * @return the numSecuencia
	 */
	public String getNumSecuencia() {
		return numSecuencia;
	}

	/**
	 * @param numSecuencia the numSecuencia to set
	 */
	public void setNumSecuencia(String numSecuencia) {
		this.numSecuencia = numSecuencia;
	}

	/**
	 * @return the modo
	 */
	public String getModo() {
		return modo;
	}

	/**
	 * @param modo the modo to set
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
	 * @param tipoOperacion the tipoOperacion to set
	 */
	public void setTipoOperacion(String tipoOperacion) {
		this.tipoOperacion = tipoOperacion;
	}

	/**
	 * @return the respuesta
	 */
	public String getRespuesta() {
		return respuesta;
	}

	/**
	 * @param respuesta the respuesta to set
	 */
	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}

	/**
	 * @return the situacion
	 */
	public String getSituacion() {
		return situacion;
	}

	/**
	 * @param situacion the situacion to set
	 */
	public void setSituacion(String situacion) {
		this.situacion = situacion;
	}

	/**
	 * @return the cuenta
	 */
	public String getCuenta() {
		return cuenta;
	}

	/**
	 * @param cuenta the cuenta to set
	 */
	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}

	/**
	 * @return the puesto
	 */
	public String getPuesto() {
		return puesto;
	}

	/**
	 * @param puesto the puesto to set
	 */
	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}

	/**
	 * @return the terminal
	 */
	public String getTerminal() {
		return terminal;
	}

	/**
	 * @param terminal the terminal to set
	 */
	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}

	/**
	 * @return the centroApertura
	 */
	public String getCentroApertura() {
		return centroApertura;
	}

	/**
	 * @param centroApertura the centroApertura to set
	 */
	public void setCentroApertura(String centroApertura) {
		this.centroApertura = centroApertura;
	}

	/**
	 * @return the usuario
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	/**
	 * @return the usuarioAutorizador
	 */
	public String getUsuarioAutorizador() {
		return usuarioAutorizador;
	}

	/**
	 * @param usuarioAutorizador the usuarioAutorizador to set
	 */
	public void setUsuarioAutorizador(String usuarioAutorizador) {
		this.usuarioAutorizador = usuarioAutorizador;
	}

	/**
	 * @return the incluirBool
	 */
	public boolean isIncluirBool() {
		return incluirBool;
	}

	/**
	 * @param incluirBool the incluirBool to set
	 */
	public void setIncluirBool(boolean incluirBool) {
		this.incluirBool = incluirBool;
	}

	/**
	 * @return the usuarioAnulador
	 */
	public String getUsuarioAnulador() {
		return usuarioAnulador;
	}

	/**
	 * @param usuarioAnulador the usuarioAnulador to set
	 */
	public void setUsuarioAnulador(String usuarioAnulador) {
		this.usuarioAnulador = usuarioAnulador;
	}

	
	
}
