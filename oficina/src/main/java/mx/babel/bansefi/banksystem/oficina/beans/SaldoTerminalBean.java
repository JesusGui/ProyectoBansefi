package mx.babel.bansefi.banksystem.oficina.beans;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Bean con los detalles de saldos netos en un puesto
 * @author mario.montesdeoca
 *
 */
public class SaldoTerminalBean implements Serializable{

	private static final long serialVersionUID = 1L;

	private String terminal;
	private String puesto;
	private BigDecimal saldoNeto;
	/**
	 * @return Atributo terminal
	 */
	public String getTerminal() {
		return terminal;
	}
	/**
	 * @param terminal Atributo terminal a definir
	 */
	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}
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
	 * @return Atributo saldoNeto
	 */
	public BigDecimal getSaldoNeto() {
		return saldoNeto;
	}
	/**
	 * @param saldoNeto Atributo saldoNeto a definir
	 */
	public void setSaldoNeto(BigDecimal saldoNeto) {
		this.saldoNeto = saldoNeto;
	}
}
