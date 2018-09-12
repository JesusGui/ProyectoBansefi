package mx.babel.bansefi.banksystem.base.beans;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class LiquidacionConceptoBean {
	// Atributos utilizados en la vista de detalle de liquidaciones.
	private int rowKey;
	private String codCuenta;
	private BigDecimal importeFacturado;
	private String descConcepto;
	private String signo;
	private BigDecimal importePendiente;
	private int numDeposito;
	private List<String> indicadores;
	private String codOrigen;

	// Atributos utilizados en las vistas: intermedia y detalle ampliado.
	private String tipoTramo;
	private String referencia;
	private int numTramo;
	private String computoDias;
	private BigDecimal baseFranquicia; // también se utiliza en la vista de
										// comisiones.
	private double porcentajeIntFranquicia;
	private int dias;
	private BigDecimal valorBase; // también se utiliza en la vista de
									// comisiones.
	private double porcentajeIntereses;
	private BigDecimal interesDevengado;
	private Date fechaDesdeTramo;
	private Date fechaHastaTramo;

	// Atributos utilizados en la vista: comisión de una liquidación.
	private String idpds;
	private String nomProdVend;
	private String idparmcd;
	private String nomParametro;
	private String indpreciokt;
	private String formaAplicacion;
	private Date fechaBase;
	private BigDecimal comision;
	private BigDecimal precio;

	// Atributo utilizado en la vista: conceptos intermedios de la liquidación.
	private List<LiquidacionConceptoBean> listaConceptosIntermedios;

	// Atributos adicionales para EVENT_CD
	private int numSecComision;
	private int numSubAcComision;

	public int getRowKey() {
		return rowKey;
	}

	public void setRowKey(int rowKey) {
		this.rowKey = rowKey;
	}

	public String getCodCuenta() {
		return codCuenta;
	}

	public void setCodCuenta(String codCuenta) {
		this.codCuenta = codCuenta;
	}

	public BigDecimal getImporteFacturado() {
		return importeFacturado;
	}

	public void setImporteFacturado(BigDecimal importeFacturado) {
		this.importeFacturado = importeFacturado;
	}

	public String getDescConcepto() {
		return descConcepto;
	}

	public void setDescConcepto(String descConcepto) {
		this.descConcepto = descConcepto;
	}

	public String getSigno() {
		return signo;
	}

	public void setSigno(String signo) {
		this.signo = signo;
	}

	public BigDecimal getImportePendiente() {
		return importePendiente;
	}

	public void setImportePendiente(BigDecimal importePendiente) {
		this.importePendiente = importePendiente;
	}

	public int getNumDeposito() {
		return numDeposito;
	}

	public void setNumDeposito(int numDeposito) {
		this.numDeposito = numDeposito;
	}

	public List<String> getIndicadores() {
		return indicadores;
	}

	public void setIndicadores(List<String> indicadores) {
		this.indicadores = indicadores;
	}

	public String getCodOrigen() {
		return codOrigen;
	}

	public void setCodOrigen(String codOrigen) {
		this.codOrigen = codOrigen;
	}

	public String getTipoTramo() {
		return tipoTramo;
	}

	public void setTipoTramo(String tipoTramo) {
		this.tipoTramo = tipoTramo;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public int getNumTramo() {
		return numTramo;
	}

	public void setNumTramo(int numTramo) {
		this.numTramo = numTramo;
	}

	public String getComputoDias() {
		return computoDias;
	}

	public void setComputoDias(String computoDias) {
		this.computoDias = computoDias;
	}

	public BigDecimal getBaseFranquicia() {
		return baseFranquicia;
	}

	public void setBaseFranquicia(BigDecimal baseFranquicia) {
		this.baseFranquicia = baseFranquicia;
	}

	public double getPorcentajeIntFranquicia() {
		return porcentajeIntFranquicia;
	}

	public void setPorcentajeIntFranquicia(double porcentajeIntFranquicia) {
		this.porcentajeIntFranquicia = porcentajeIntFranquicia;
	}

	public int getDias() {
		return dias;
	}

	public void setDias(int dias) {
		this.dias = dias;
	}

	public BigDecimal getValorBase() {
		return valorBase;
	}

	public void setValorBase(BigDecimal valorBase) {
		this.valorBase = valorBase;
	}

	public double getPorcentajeIntereses() {
		return porcentajeIntereses;
	}

	public void setPorcentajeIntereses(double porcentajeIntereses) {
		this.porcentajeIntereses = porcentajeIntereses;
	}

	public BigDecimal getInteresDevengado() {
		return interesDevengado;
	}

	public void setInteresDevengado(BigDecimal interesDevengado) {
		this.interesDevengado = interesDevengado;
	}

	public Date getFechaDesdeTramo() {
		return fechaDesdeTramo;
	}

	public void setFechaDesdeTramo(Date fechaDesdeTramo) {
		this.fechaDesdeTramo = fechaDesdeTramo;
	}

	public Date getFechaHastaTramo() {
		return fechaHastaTramo;
	}

	public void setFechaHastaTramo(Date fechaHastaTramo) {
		this.fechaHastaTramo = fechaHastaTramo;
	}

	public String getIdpds() {
		return idpds;
	}

	public void setIdpds(String idpds) {
		this.idpds = idpds;
	}

	public String getNomProdVend() {
		return nomProdVend;
	}

	public void setNomProdVend(String nomProdVend) {
		this.nomProdVend = nomProdVend;
	}

	public String getIdparmcd() {
		return idparmcd;
	}

	public void setIdparmcd(String idparmcd) {
		this.idparmcd = idparmcd;
	}

	public String getNomParametro() {
		return nomParametro;
	}

	public void setNomParametro(String nomParametro) {
		this.nomParametro = nomParametro;
	}

	public String getIndpreciokt() {
		return indpreciokt;
	}

	public void setIndpreciokt(String indpreciokt) {
		this.indpreciokt = indpreciokt;
	}

	public String getFormaAplicacion() {
		return formaAplicacion;
	}

	public void setFormaAplicacion(String formaAplicacion) {
		this.formaAplicacion = formaAplicacion;
	}

	public Date getFechaBase() {
		return fechaBase;
	}

	public void setFechaBase(Date fechaBase) {
		this.fechaBase = fechaBase;
	}

	public BigDecimal getComision() {
		return comision;
	}

	public void setComision(BigDecimal comision) {
		this.comision = comision;
	}

	public BigDecimal getPrecio() {
		return precio;
	}

	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}

	public List<LiquidacionConceptoBean> getListaConceptosIntermedios() {
		return listaConceptosIntermedios;
	}

	public void setListaConceptosIntermedios(
			List<LiquidacionConceptoBean> listaConceptosIntermedios) {
		this.listaConceptosIntermedios = listaConceptosIntermedios;
	}

	public int getNumSecComision() {
		return numSecComision;
	}

	public void setNumSecComision(int numSecComision) {
		this.numSecComision = numSecComision;
	}

	public int getNumSubAcComision() {
		return numSubAcComision;
	}

	public void setNumSubAcComision(int numSubAcComision) {
		this.numSubAcComision = numSubAcComision;
	}

}