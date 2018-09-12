package mx.babel.bansefi.banksystem.base.beans;

import java.math.BigDecimal;

public class LiquidacionSaldoBean {
	private String codCta;
	private String codSaldo;
	private String indSaldo;
	private BigDecimal importeSaldo;
	private String descSaldo;
	
	
	public String getCodCta() {
		return codCta;
	}

	public void setCodCta(String codCta) {
		this.codCta = codCta;
	}

	public String getCodSaldo() {
		return codSaldo;
	}

	public void setCodSaldo(String codSaldo) {
		this.codSaldo = codSaldo;
	}

	public String getIndSaldo() {
		return indSaldo;
	}

	public void setIndSaldo(String indSaldo) {
		this.indSaldo = indSaldo;
	}

	public BigDecimal getImporteSaldo() {
		return importeSaldo;
	}

	public void setImporteSaldo(BigDecimal importeSaldo) {
		this.importeSaldo = importeSaldo;
	}

	public String getDescSaldo() {
		return descSaldo;
	}

	public void setDescSaldo(String descSaldo) {
		this.descSaldo = descSaldo;
	}

}