package mx.babel.bansefi.banksystem.oficina.beans;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mx.babel.arq.comun.beans.PaginacionBean;

/**
 * Bean para realizar la consulta de traspasos y saldos en un puesto
 * @author mario.montesdeoca
 *
 */
public class ConsultaTraspasoBean extends PaginacionBean{

	private static final long serialVersionUID = -4156364581541183626L;

	private String puesto;
	private Date desde;
	private Date hasta;
	private BigDecimal totalEnviado;
	private BigDecimal totalRecibido;
	private BigDecimal netoTraspaso;
	private BigDecimal saldoInicial;
	private List<TraspasoBean> traspasos;
	/**
	 * @return Atributo puesto
	 */
	public String getPuesto() {
		return puesto;
	}
	/**
	 * @param puesto Atributo puesto a definir
	 */
	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}
	/**
	 * @return Atributo desde
	 */
	public Date getDesde() {
		return desde;
	}
	/**
	 * @param desde Atributo desde a definir
	 */
	public void setDesde(Date desde) {
		this.desde = desde;
	}
	/**
	 * @return Atributo hasta
	 */
	public Date getHasta() {
		return hasta;
	}
	/**
	 * @param hasta Atributo hasta a definir
	 */
	public void setHasta(Date hasta) {
		this.hasta = hasta;
	}
	/**
	 * @return Atributo totalEnviado
	 */
	public BigDecimal getTotalEnviado() {
		return totalEnviado;
	}
	/**
	 * @param totalEnviado Atributo totalEnviado a definir
	 */
	public void setTotalEnviado(BigDecimal totalEnviado) {
		this.totalEnviado = totalEnviado;
	}
	/**
	 * @return Atributo totalRecibido
	 */
	public BigDecimal getTotalRecibido() {
		return totalRecibido;
	}
	/**
	 * @param totalRecibido Atributo totalRecibido a definir
	 */
	public void setTotalRecibido(BigDecimal totalRecibido) {
		this.totalRecibido = totalRecibido;
	}
	/**
	 * @return Atributo netoTraspaso
	 */
	public BigDecimal getNetoTraspaso() {
		return netoTraspaso;
	}
	/**
	 * @param netoTraspaso Atributo netoTraspaso a definir
	 */
	public void setNetoTraspaso(BigDecimal netoTraspaso) {
		this.netoTraspaso = netoTraspaso;
	}
	/**
	 * @return Atributo saldoInicial
	 */
	public BigDecimal getSaldoInicial() {
		return saldoInicial;
	}
	/**
	 * @param saldoInicial Atributo saldoInicial a definir
	 */
	public void setSaldoInicial(BigDecimal saldoInicial) {
		this.saldoInicial = saldoInicial;
	}
	/**
	 * @return Atributo traspasos
	 */
	public List<TraspasoBean> getTraspasos() {
		if(traspasos == null){
			traspasos = new ArrayList<TraspasoBean>();
		}
		return traspasos;
	}
	/**
	 * @param traspasos Atributo traspasos a definir
	 */
	public void setTraspasos(List<TraspasoBean> traspasos) {
		this.traspasos = traspasos;
	}
	
	/**
	 * Método para actualizar los totales de traspasos.
	 */
	public void actualizaTotales(){
		this.totalEnviado = new BigDecimal(0);
		this.totalRecibido = new BigDecimal(0);
		for (TraspasoBean traspaso : getTraspasos()) {
			if(this.puesto.equals(traspaso.getOrigen())){
				this.totalEnviado = this.totalEnviado.add(traspaso.getImporte());
			}else{
				this.totalRecibido = this.totalRecibido.add(traspaso.getImporte());
			}
		}
		if(this.netoTraspaso == null){
			this.netoTraspaso = new BigDecimal(0);
		}
		this.saldoInicial = this.netoTraspaso.add(this.totalRecibido.negate());
		this.saldoInicial = this.saldoInicial.add(this.totalEnviado);
	}

}
