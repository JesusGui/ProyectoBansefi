package mx.babel.bansefi.banksystem.oficina.beans;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Bean con detalles de cuadres de puestos
 * @author mario.montesdeoca
 *
 */
public class CuadrePuestoBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String terminal;
	private BigDecimal totalArqueo;
	private BigDecimal saldoCaja;
	private BigDecimal diferencia;
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
	 * @return Atributo totalArqueo
	 */
	public BigDecimal getTotalArqueo() {
		return totalArqueo;
	}
	/**
	 * @param totalArqueo Atributo totalArqueo a definir
	 */
	public void setTotalArqueo(BigDecimal totalArqueo) {
		this.totalArqueo = totalArqueo;
	}
	/**
	 * @return Atributo saldoCaja
	 */
	public BigDecimal getSaldoCaja() {
		return saldoCaja;
	}
	/**
	 * @param saldoCaja Atributo saldoCaja a definir
	 */
	public void setSaldoCaja(BigDecimal saldoCaja) {
		this.saldoCaja = saldoCaja;
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
}
