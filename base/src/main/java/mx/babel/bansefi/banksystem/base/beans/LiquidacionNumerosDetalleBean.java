package mx.babel.bansefi.banksystem.base.beans;

import java.math.BigDecimal;
import java.util.Date;

public class LiquidacionNumerosDetalleBean {

	private Date fechaValor;
	private BigDecimal importe;
	private BigDecimal saldo;
	private Integer dias;
	private BigDecimal numAcreedores;
	private BigDecimal numDeudores;
	private BigDecimal numDeudoresAut;
	private BigDecimal numExcedidos;
	private BigDecimal limites;

	public Date getFechaValor() {
		return fechaValor;
	}

	public void setFechaValor(Date fechaValor) {
		this.fechaValor = fechaValor;
	}

	public BigDecimal getImporte() {
		return importe;
	}

	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}

	public BigDecimal getSaldo() {
		return saldo;
	}

	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}

	public Integer getDias() {
		return dias;
	}

	public void setDias(Integer dias) {
		this.dias = dias;
	}

	public BigDecimal getNumAcreedores() {
		return numAcreedores;
	}

	public void setNumAcreedores(BigDecimal numAcreedores) {
		this.numAcreedores = numAcreedores;
	}

	public BigDecimal getNumDeudores() {
		return numDeudores;
	}

	public void setNumDeudores(BigDecimal numDeudores) {
		this.numDeudores = numDeudores;
	}

	public BigDecimal getNumDeudoresAut() {
		return numDeudoresAut;
	}

	public void setNumDeudoresAut(BigDecimal numDeudoresAut) {
		this.numDeudoresAut = numDeudoresAut;
	}

	public BigDecimal getNumExcedidos() {
		return numExcedidos;
	}

	public void setNumExcedidos(BigDecimal numExcedidos) {
		this.numExcedidos = numExcedidos;
	}

	public BigDecimal getLimites() {
		return limites;
	}

	public void setLimites(BigDecimal limites) {
		this.limites = limites;
	}

}