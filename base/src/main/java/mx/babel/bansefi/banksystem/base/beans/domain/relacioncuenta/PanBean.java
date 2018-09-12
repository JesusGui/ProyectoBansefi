package mx.babel.bansefi.banksystem.base.beans.domain.relacioncuenta;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import mx.babel.arq.catalogo.beans.CatalogoBean;
import mx.babel.bansefi.banksystem.base.enums.EstadoListadosEnum;

public class PanBean implements Serializable{

	private static final long serialVersionUID = -2648954745512119288L;

	private String entidad;
	
	private String nombreCliente;
	
	private String numeroPan;
	
	private String panAnterior;
	
	private String tipoTarjeta;
	
	private String bin;
	
	private String condicionEconomica;
	
	private String productoTCB;
	
	private String motivoEntrega;
	
	private String debitoCredito;
	
	private TipoAltaEnum tipoAlta;
	
	private Boolean titular;
	
	private EstadoListadosEnum estadoListado;
	
	private String respaldo;
	
	private List<CatalogoBean> catalogoCondicionEconomica;

	private List<CatalogoBean> catalogoProductosTcb;

	/**
	 * @return Atributo entidad
	 */
	public String getEntidad() {
		return entidad;
	}

	/**
	 * @param entidad Atributo entidad a definir
	 */
	public void setEntidad(String entidad) {
		this.entidad = entidad;
	}

	/**
	 * @return Atributo nombreCliente
	 */
	public String getNombreCliente() {
		return nombreCliente;
	}

	/**
	 * @param nombreCliente Atributo nombreCliente a definir
	 */
	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	/**
	 * @return Atributo numeroPan
	 */
	public String getNumeroPan() {
		return numeroPan;
	}

	/**
	 * @param numeroPan Atributo numeroPan a definir
	 */
	public void setNumeroPan(String numeroPan) {
		this.numeroPan = numeroPan.replaceAll("-", "");
	}

	/**
	 * @return Atributo panAnterior
	 */
	public String getPanAnterior() {
		return panAnterior;
	}

	/**
	 * @param panAnterior Atributo panAnterior a definir
	 */
	public void setPanAnterior(String panAnterior) {
		this.panAnterior = panAnterior.replaceAll("-", "");
	}

	/**
	 * @return Atributo tipoTarjeta
	 */
	public String getTipoTarjeta() {
		return tipoTarjeta;
	}

	/**
	 * @param tipoTarjeta Atributo tipoTarjeta a definir
	 */
	public void setTipoTarjeta(String tipoTarjeta) {
		this.tipoTarjeta = tipoTarjeta;
	}

	/**
	 * @return Atributo bin
	 */
	public String getBin() {
		return bin;
	}

	/**
	 * @param bin Atributo bin a definir
	 */
	public void setBin(String bin) {
		this.bin = bin;
	}

	/**
	 * @return Atributo condicionEconomica
	 */
	public String getCondicionEconomica() {
		return condicionEconomica;
	}

	/**
	 * @param condicionEconomica Atributo condicionEconomica a definir
	 */
	public void setCondicionEconomica(String condicionEconomica) {
		this.condicionEconomica = condicionEconomica;
	}

	/**
	 * @return Atributo productoTCB
	 */
	public String getProductoTCB() {
		return productoTCB;
	}

	/**
	 * @param productoTCB Atributo productoTCB a definir
	 */
	public void setProductoTCB(String productoTCB) {
		this.productoTCB = productoTCB;
	}

	/**
	 * @return Atributo motivoEntrega
	 */
	public String getMotivoEntrega() {
		return motivoEntrega;
	}

	/**
	 * @param motivoEntrega Atributo motivoEntrega a definir
	 */
	public void setMotivoEntrega(String motivoEntrega) {
		this.motivoEntrega = motivoEntrega;
	}

	/**
	 * @return Atributo debitoCredito
	 */
	public String getDebitoCredito() {
		return debitoCredito;
	}

	/**
	 * @param debitoCredito Atributo debitoCredito a definir
	 */
	public void setDebitoCredito(String debitoCredito) {
		this.debitoCredito = debitoCredito;
	}

	/**
	 * @return Atributo tipoAlta
	 */
	public TipoAltaEnum getTipoAlta() {
		return tipoAlta;
	}

	/**
	 * @param tipoAlta Atributo tipoAlta a definir
	 */
	public void setTipoAlta(TipoAltaEnum tipoAlta) {
		this.tipoAlta = tipoAlta;
	}

	/**
	 * @return Atributo titular
	 */
	public Boolean getTitular() {
		return titular;
	}

	/**
	 * @param titular Atributo titular a definir
	 */
	public void setTitular(Boolean titular) {
		this.titular = titular;
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
	public void setEstadoListado(EstadoListadosEnum estadoListado) {
		this.estadoListado = estadoListado;
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
	public void setRespaldo(String respaldo) {
		this.respaldo = respaldo;
	}

	/**
	 * @return Atributo catalogoCondicionEconomica
	 */
	public List<CatalogoBean> getCatalogoCondicionEconomica() {
		if(catalogoCondicionEconomica == null){
			catalogoCondicionEconomica = new ArrayList<CatalogoBean>();
		}
		return catalogoCondicionEconomica;
	}

	/**
	 * @param catalogoCondicionEconomica Atributo catalogoCondicionEconomica a definir
	 */
	public void setCatalogoCondicionEconomica(
			List<CatalogoBean> catalogoCondicionEconomica) {
		this.catalogoCondicionEconomica = catalogoCondicionEconomica;
	}

	/**
	 * @return Atributo catalogoProductosTcb
	 */
	public List<CatalogoBean> getCatalogoProductosTcb() {
		if(catalogoProductosTcb == null){
			catalogoProductosTcb = new ArrayList<CatalogoBean>();
		}
		return catalogoProductosTcb;
	}

	/**
	 * @param catalogoProductosTcb Atributo catalogoProductosTcb a definir
	 */
	public void setCatalogoProductosTcb(List<CatalogoBean> catalogoProductosTcb) {
		this.catalogoProductosTcb = catalogoProductosTcb;
	}
	
	public String getTipoPan(){
		if(this.titular){
			return "TITULAR";
		}else{
			return "ADICIONAL";
		}
	}
	
}
