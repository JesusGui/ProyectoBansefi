package mx.babel.bansefi.banksystem.cuentas.beans;

import java.math.BigDecimal;
import java.util.List;

import mx.babel.bansefi.banksystem.base.beans.movimientos.MovimientoBean;

/**
 * Define las propiedades de los apuntes de saldos
 * 
 * @author Aramis Oswaldo Gadea Gerónimo &lt;gadea@uanlmexico.mx&gt
 * @category Bean
 */
public class SaldoCuentaApuntesBean {
	private BigDecimal saldoInicialArrastre;
	private BigDecimal saldoFinalArrastre;
	private List<MovimientoBean> apuntes;

	/**
	 * @return the saldoInicialArrastre
	 * @author Aramis Oswaldo Gadea Gerónimo &lt;gadea@uanlmexico.mx&gt;
	 */
	public BigDecimal getSaldoInicialArrastre() {
		return saldoInicialArrastre;
	}

	/**
	 * @param saldoInicialArrastre
	 *            the saldoInicialArrastre to set
	 * @author Aramis Oswaldo Gadea Gerónimo &lt;gadea@uanlmexico.mx&gt;
	 */
	public void setSaldoInicialArrastre(BigDecimal saldoInicialArrastre) {
		this.saldoInicialArrastre = saldoInicialArrastre;
	}

	/**
	 * @return the saldoFinalArrastre
	 * @author Aramis Oswaldo Gadea Gerónimo &lt;gadea@uanlmexico.mx&gt;
	 */
	public BigDecimal getSaldoFinalArrastre() {
		return saldoFinalArrastre;
	}

	/**
	 * @param saldoFinalArrastre
	 *            the saldoFinalArrastre to set
	 * @author Aramis Oswaldo Gadea Gerónimo &lt;gadea@uanlmexico.mx&gt;
	 */
	public void setSaldoFinalArrastre(BigDecimal saldoFinalArrastre) {
		this.saldoFinalArrastre = saldoFinalArrastre;
	}

	/**
	 * @return the apuntes
	 * @author Aramis Oswaldo Gadea Gerónimo &lt;gadea@uanlmexico.mx&gt;
	 */
	public List<MovimientoBean> getApuntes() {
		return apuntes;
	}

	/**
	 * @param apuntes
	 *            the apuntes to set
	 * @author Aramis Oswaldo Gadea Gerónimo &lt;gadea@uanlmexico.mx&gt;
	 */
	public void setApuntes(List<MovimientoBean> apuntes) {
		this.apuntes = apuntes;
	}
}
