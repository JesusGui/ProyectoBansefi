package mx.babel.bansefi.banksystem.base.beans.parrilla;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Bean con valores y cantidades de denominaciones.
 * @author mario.montesdeoca
 *
 */
public class ExistenciaDenominacionBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private Boolean moneda;
	private BigDecimal valor;
	private String valorFacial;
	private String formato;
	private String origen;
	
	private String precinto;
	private Long unidades;
	/*
	 * Importe a modificar
	 */
	private BigDecimal importeModificable;
	/*
	 * Importes informativos
	 */
	private BigDecimal importeSolicitado;
	private BigDecimal importeEnviado;
	private BigDecimal importePedido;
	private BigDecimal importeRecibido;
	private BigDecimal importeAutorizado;
	private BigDecimal importeAEnviar;
	private BigDecimal importeNuevo;
	private BigDecimal existencias;
	private BigDecimal diferencia;
	/**
	 * @return Atributo moneda
	 */
	public Boolean getMoneda() {
		return moneda;
	}
	/**
	 * @param moneda Atributo moneda a definir
	 */
	public void setMoneda(Boolean moneda) {
		this.moneda = moneda;
	}
	/**
	 * @return Atributo valorFacial
	 */
	public String getValorFacial() {
		return valorFacial;
	}
	/**
	 * @param valorFacial Atributo valorFacial a definir
	 */
	public void setValorFacial(String valorFacial) {
		this.valorFacial = valorFacial;
	}
	/**
	 * @return Atributo formato
	 */
	public String getFormato() {
		return formato;
	}
	/**
	 * @param formato Atributo formato a definir
	 */
	public void setFormato(String formato) {
		this.formato = formato;
	}
	/**
	 * @return Atributo origen
	 */
	public String getOrigen() {
		return origen;
	}
	/**
	 * @param origen Atributo origen a definir
	 */
	public void setOrigen(String origen) {
		this.origen = origen;
	}
	/**
	 * @return Atributo precinto
	 */
	public String getPrecinto() {
		return precinto;
	}
	/**
	 * @param precinto Atributo precinto a definir
	 */
	public void setPrecinto(String precinto) {
		this.precinto = precinto;
	}
	/**
	 * @return Atributo unidades
	 */
	public Long getUnidades() {
		return unidades;
	}
	/**
	 * @param unidades Atributo unidades a definir
	 */
	public void setUnidades(Long unidades) {
		this.unidades = unidades;
	}
	/**
	 * @return Atributo valor
	 */
	public BigDecimal getValor() {
		return valor;
	}
	/**
	 * @param valor Atributo valor a definir
	 */
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	/**
	 * @return Atributo importePedido
	 */
	public BigDecimal getImportePedido() {
		return importePedido;
	}
	/**
	 * @param importePedido Atributo importePedido a definir
	 */
	public void setImportePedido(BigDecimal importePedido) {
		this.importePedido = importePedido;
	}
	/**
	 * @return Atributo importeRecibido
	 */
	public BigDecimal getImporteRecibido() {
		return importeRecibido;
	}
	/**
	 * @param importeRecibido Atributo importeRecibido a definir
	 */
	public void setImporteRecibido(BigDecimal importeRecibido) {
		this.importeRecibido = importeRecibido;
	}
	/**
	 * @return Atributo importeModificable
	 */
	public BigDecimal getImporteModificable() {
		return importeModificable;
	}
	/**
	 * @param importeModificable Atributo importeModificable a definir
	 */
	public void setImporteModificable(BigDecimal importeModificable) {
		this.importeModificable = importeModificable;
	}
	
	/**
	 * @return Atributo importeAutorizado
	 */
	public BigDecimal getImporteAutorizado() {
		return importeAutorizado;
	}
	/**
	 * @param importeAutorizado Atributo importeAutorizado a definir
	 */
	public void setImporteAutorizado(BigDecimal importeAutorizado) {
		this.importeAutorizado = importeAutorizado;
	}
	/**
	 * @return Atributo diferencia
	 */
	public BigDecimal getDiferencia() {
		return diferencia;
	}
	/**
	 * @param diferencia Atributo diferencia a definir
	 */
	public void setDiferencia(BigDecimal diferencia) {
		this.diferencia = diferencia;
	}
	/**
	 * @return Atributo importeSolicitado
	 */
	public BigDecimal getImporteSolicitado() {
		return importeSolicitado;
	}
	/**
	 * @param importeSolicitado Atributo importeSolicitado a definir
	 */
	public void setImporteSolicitado(BigDecimal importeSolicitado) {
		this.importeSolicitado = importeSolicitado;
	}
	/**
	 * @return Atributo importeEnviado
	 */
	public BigDecimal getImporteEnviado() {
		return importeEnviado;
	}
	/**
	 * @param importeEnviado Atributo importeEnviado a definir
	 */
	public void setImporteEnviado(BigDecimal importeEnviado) {
		this.importeEnviado = importeEnviado;
	}
	/**
	 * @return Atributo importeAEnviar
	 */
	public BigDecimal getImporteAEnviar() {
		return importeAEnviar;
	}
	/**
	 * @param importeAEnviar Atributo importeAEnviar a definir
	 */
	public void setImporteAEnviar(BigDecimal importeAEnviar) {
		this.importeAEnviar = importeAEnviar;
	}
	/**
	 * @return Atributo importeNuevo
	 */
	public BigDecimal getImporteNuevo() {
		return importeNuevo;
	}
	/**
	 * @param importeNuevo Atributo importeNuevo a definir
	 */
	public void setImporteNuevo(BigDecimal importeNuevo) {
		this.importeNuevo = importeNuevo;
	}
	/**
	 * @return Atributo existencias
	 */
	public BigDecimal getExistencias() {
		return existencias;
	}
	/**
	 * @param existencias Atributo existencias a definir
	 */
	public void setExistencias(BigDecimal existencias) {
		this.existencias = existencias;
	}
	/**
	 * MÃ¨todo que calcula el importe resultado del valor 
	 * de la denominaciÃ²n y las unidades registradas.
	 * @return Importe de la denominaciÃ²n.
	 */
	public BigDecimal getImporte(){
		return valor.multiply(new BigDecimal(unidades));
	}
}
