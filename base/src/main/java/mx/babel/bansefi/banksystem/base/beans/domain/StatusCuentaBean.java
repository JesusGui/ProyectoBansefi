package mx.babel.bansefi.banksystem.base.beans.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Clase que define los atributos que tendrá el status de cuenta.
 * 
 * @author javier.martinnino
 * 
 */

public class StatusCuentaBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private BigDecimal capital;
	private BigDecimal interes;
	private BigDecimal interesDemora;
	private BigDecimal ivaDemora;
	private BigDecimal ivaInteresReal;
	private BigDecimal interesComision;
	private BigDecimal ivaDeuda;
	private BigDecimal total;	
	private Date fecha;
	
	public BigDecimal getCapital() {
		return capital;
	}
	public void setCapital(BigDecimal capital) {
		this.capital = capital;
	}
	public BigDecimal getInteres() {
		return interes;
	}
	public void setInteres(BigDecimal interes) {
		this.interes = interes;
	}
	public BigDecimal getInteresDemora() {
		return interesDemora;
	}
	public void setInteresDemora(BigDecimal interesDemora) {
		this.interesDemora = interesDemora;
	}
	public BigDecimal getIvaDemora() {
		return ivaDemora;
	}
	public void setIvaDemora(BigDecimal ivaDemora) {
		this.ivaDemora = ivaDemora;
	}
	public BigDecimal getIvaInteresReal() {
		return ivaInteresReal;
	}
	public void setIvaInteresReal(BigDecimal ivaInteresReal) {
		this.ivaInteresReal = ivaInteresReal;
	}
	public BigDecimal getInteresComision() {
		return interesComision;
	}
	public void setInteresComision(BigDecimal interesComision) {
		this.interesComision = interesComision;
	}
	public BigDecimal getIvaDeuda() {
		return ivaDeuda;
	}
	public void setIvaDeuda(BigDecimal ivaDeuda) {
		this.ivaDeuda = ivaDeuda;
	}
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

}