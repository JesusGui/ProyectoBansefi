package mx.babel.bansefi.banksystem.cajas.beans;

import java.math.BigDecimal;

public class CambioDenominacionBean {

	private BigDecimal importeACambiar;
	
	private String origenDe;
	
	private String origenA;
	
	private String valorFacialDe;
	
	private String valorFacialA;

	public BigDecimal getImporteACambiar() {
		return importeACambiar;
	}

	public void setImporteACambiar(BigDecimal importeACambiar) {
		this.importeACambiar = importeACambiar;
	}

	public String getOrigenDe() {
		return origenDe;
	}

	public void setOrigenDe(String origenDe) {
		this.origenDe = origenDe;
	}

	public String getOrigenA() {
		return origenA;
	}

	public void setOrigenA(String origenA) {
		this.origenA = origenA;
	}

	public String getValorFacialDe() {
		return valorFacialDe;
	}

	public void setValorFacialDe(String valorFacialDe) {
		this.valorFacialDe = valorFacialDe;
	}

	public String getValorFacialA() {
		return valorFacialA;
	}

	public void setValorFacialA(String valorFacialA) {
		this.valorFacialA = valorFacialA;
	}
}
