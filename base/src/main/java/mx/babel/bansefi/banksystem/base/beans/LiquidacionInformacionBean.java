package mx.babel.bansefi.banksystem.base.beans;

import java.math.BigDecimal;

/**
 * Clase que define los atributos de un LiquidacionInformacionBean.
 * 
 * @author omar.marquez
 */
public class LiquidacionInformacionBean {

	private String codInfAdicHL;
	private BigDecimal deposito;
	private String tipoInformacion;
	private String valor;

	public String getCodInfAdicHL() {
		return codInfAdicHL;
	}

	public void setCodInfAdicHL(String codInfAdicHL) {
		this.codInfAdicHL = codInfAdicHL;
	}

	public BigDecimal getDeposito() {
		return deposito;
	}

	public void setDeposito(BigDecimal deposito) {
		this.deposito = deposito;
	}

	public String getTipoInformacion() {
		return tipoInformacion;
	}

	public void setTipoInformacion(String tipoInformacion) {
		this.tipoInformacion = tipoInformacion;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

}