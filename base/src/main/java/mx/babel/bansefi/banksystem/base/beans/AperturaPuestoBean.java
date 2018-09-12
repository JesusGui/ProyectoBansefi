package mx.babel.bansefi.banksystem.base.beans;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Clase que define los atributos de la apertura del puesto.
 * 
 * @author omar.marquez
 */
public class AperturaPuestoBean {

	private Date fechaContable;
	private BigDecimal importeInicialAnt;
	private BigDecimal importeInicialNuevo;
	private BigDecimal saldoContable;
	private BigDecimal importeTotal;
	private String indPuestoPrincipal;
	private boolean eliminarTraspasos;

	public Date getFechaContable() {
		return fechaContable;
	}

	public void setFechaContable(Date fechaContable) {
		this.fechaContable = fechaContable;
	}

	public BigDecimal getImporteInicialAnt() {
		return importeInicialAnt;
	}

	public void setImporteInicialAnt(BigDecimal importeInicialAnt) {
		this.importeInicialAnt = importeInicialAnt;
	}

	public BigDecimal getImporteInicialNuevo() {
		return importeInicialNuevo;
	}

	public void setImporteInicialNuevo(BigDecimal importeInicialNuevo) {
		this.importeInicialNuevo = importeInicialNuevo;
	}

	public BigDecimal getSaldoContable() {
		return saldoContable;
	}

	public void setSaldoContable(BigDecimal saldoContable) {
		this.saldoContable = saldoContable;
	}

	public BigDecimal getImporteTotal() {
		return importeTotal;
	}

	public void setImporteTotal(BigDecimal importeTotal) {
		this.importeTotal = importeTotal;
	}

	public String getIndPuestoPrincipal() {
		return indPuestoPrincipal;
	}

	public void setIndPuestoPrincipal(String indPuestoPrincipal) {
		this.indPuestoPrincipal = indPuestoPrincipal;
	}

	public boolean isEliminarTraspasos() {
		return eliminarTraspasos;
	}

	public void setEliminarTraspasos(boolean eliminarTraspasos) {
		this.eliminarTraspasos = eliminarTraspasos;
	}

}