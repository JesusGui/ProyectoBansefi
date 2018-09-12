package mx.babel.bansefi.banksystem.oficina.beans;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Bean para generar la lista de registros que se incluirán en las tablas
 * de Contadores On/Off/Mov
 * @author manuel.nieto
 *
 */
public class ContadoresCentroRegistrosDetalleBean implements Serializable{
	
	private static final long serialVersionUID = 4874792205179237077L;
	private String terminal;
	private String puesto;
	private BigDecimal cobros;
	private BigDecimal pagos;
	private BigDecimal debe;
	private BigDecimal haber;

	public ContadoresCentroRegistrosDetalleBean() {
	}

	public ContadoresCentroRegistrosDetalleBean(String terminal, String puesto, BigDecimal cobros) {
		this.terminal = terminal;
		this.puesto = puesto;
		this.cobros = cobros;
	}

	public String getTerminal() {
		return terminal;
	}

	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}

	public String getPuesto() {
		return puesto;
	}

	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}

	/**
	 * Funcion que obtiene el valor de la variable cobros
	 * @return BigDecimal
	 */
	public BigDecimal getCobros() {
		return cobros;
	}
	
	/**
	 * Funcion que asigna el valor de la variable cobros
	 * @param cobros
	 */
	public void setCobros(BigDecimal cobros) {
		this.cobros = cobros;
	}
	
	/**
	 * Funcion que obtiene el valor de la variable pagos
	 * @return BigDecimal
	 */
	public BigDecimal getPagos() {
		return pagos;
	}
	
	/**
	 * Funcion que asigna el valor de la variable pagos
	 * @param pagos
	 */
	public void setPagos(BigDecimal pagos) {
		this.pagos = pagos;
	}
	
	/**
	 * Funcion que obtiene el valor de la variable debe
	 * @return BigDecimal
	 */
	public BigDecimal getDebe() {
		return debe;
	}
	
	/**
	 * Funcion que asigna el valor de la variable debe
	 * @param debe
	 */
	public void setDebe(BigDecimal debe) {
		this.debe = debe;
	}
	
	/**
	 * Funcion que obtiene el valor de la variable haber
	 * @return BigDecimal
	 */
	public BigDecimal getHaber() {
		return haber;
	}
	
	/**
	 * Funcion que asigna el valor de la variable haber
	 * @param haber
	 */
	public void setHaber(BigDecimal haber) {
		this.haber = haber;
	}	
	
}
