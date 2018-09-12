package mx.babel.bansefi.banksystem.oficina.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * Bean que genera tola la información que se deplegará en el tabal de
 * Contadores On/Off/Mov Incluyendo todos los registros de la tabla y los
 * totales
 * 
 * @author manuel.nieto
 * 
 */
public class ContadoresCentroRegistrosBean implements Serializable {

	private static final long serialVersionUID = -458812907802087094L;
	private List<ContadoresCentroRegistrosDetalleBean> lista;
	private BigDecimal totalCobros;
	private BigDecimal totalPagos;
	private BigDecimal totalDebe;
	private BigDecimal totalHaber;

	/**
	 * Funcion que obtiene la lista de registros del
	 * 'ContadoresCentroRegistrosBean'
	 * 
	 * @return List<ContadoresCentroRegistrosDetalleBean>
	 */
	public List<ContadoresCentroRegistrosDetalleBean> getLista() {
		return lista;
	}

	/**
	 * Funcion que asigna la lista de registros del
	 * 'ContadoresCentroRegistroBean'
	 * 
	 * @param lista
	 */
	public void setLista(List<ContadoresCentroRegistrosDetalleBean> lista) {
		this.lista = lista;
	}

	/**
	 * Funcion que obtiene el valor de la variable de totalCobros
	 * 
	 * @return BigDecimal
	 */
	public BigDecimal getTotalCobros() {
		return totalCobros;
	}

	/**
	 * Funcion que asigna el valor de la variable totalCobros
	 * 
	 * @param totalCobros
	 */
	public void setTotalCobros(BigDecimal totalCobros) {
		this.totalCobros = totalCobros;
	}

	/**
	 * Funcion que obtiene el valor de la variable totalPagos
	 * 
	 * @return BigDecimal
	 */
	public BigDecimal getTotalPagos() {
		return totalPagos;
	}

	/**
	 * Funcion que asigna el valor de la variable totalPagos
	 * 
	 * @param totalPagos
	 */
	public void setTotalPagos(BigDecimal totalPagos) {
		this.totalPagos = totalPagos;
	}

	/**
	 * Funcion que obtiene el valor de la variable totalDebe
	 * 
	 * @return BigDecimal
	 */
	public BigDecimal getTotalDebe() {
		return totalDebe;
	}

	/**
	 * Funcion que asigna el valor de la variable totalDebe
	 * 
	 * @param totalDebe
	 */
	public void setTotalDebe(BigDecimal totalDebe) {
		this.totalDebe = totalDebe;
	}

	/**
	 * Funcion que obtiene el valor de la variable totalHaber
	 * 
	 * @return BigDecimal
	 */
	public BigDecimal getTotalHaber() {
		return totalHaber;
	}

	/**
	 * Funcion que asigna el valor de la variable totalHaber
	 * 
	 * @param totalHaber
	 */
	public void setTotalHaber(BigDecimal totalHaber) {
		this.totalHaber = totalHaber;
	}

	/**
	 * Operacion BigDecimal Método para realizar sumas y restas entre
	 * BigDecimals
	 * 
	 * @param value
	 *            Valor base
	 * @param augend
	 *            Valor a sumar o restarse
	 * @param suma
	 *            boolean para definir si es suma o resta
	 * @return BigDecimal con resultado de la operaciòn.
	 */
	public BigDecimal opBigDecimal(BigDecimal value, BigDecimal augend,
			Boolean suma) {
		if (value == null) {
			value = BigDecimal.ZERO;
		}

		if (augend == null) {
			augend = BigDecimal.ZERO;
		}
		if (suma) {
			return value.add(augend);
		} else {
			return value.add(augend.negate());
		}
	}

	/**
	 * Funcion que recorre la lista para generar los totales
	 */
	public void calculaTotales() {
		this.totalCobros = BigDecimal.ZERO;
		this.totalPagos = BigDecimal.ZERO;
		this.totalDebe = BigDecimal.ZERO;
		this.totalHaber = BigDecimal.ZERO;

		if (lista != null && !lista.isEmpty()) {
			for (ContadoresCentroRegistrosDetalleBean registro : lista) {
				this.totalCobros = opBigDecimal(this.totalCobros,
						registro.getCobros(), true);
				this.totalPagos = opBigDecimal(this.totalPagos,
						registro.getPagos(), true);
				this.totalDebe = opBigDecimal(this.totalDebe,
						registro.getDebe(), true);
				this.totalHaber = opBigDecimal(this.totalHaber,
						registro.getHaber(), true);
			}
		}
	}

}
