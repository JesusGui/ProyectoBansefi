package mx.babel.bansefi.banksystem.transacciones.beans;


import java.math.BigDecimal;

import mx.babel.bansefi.banksystem.base.backends.ConsultaGenerica;

/**
 * Retiro Request.
 * @author alejandro.perez
 *
 */

public class RetiroReq extends ConsultaGenerica {

    private static final long serialVersionUID = 1L;
    
    private String cuenta;

    private BigDecimal importe;

    private String fecvalor;

    private String codope;

    private String cveidof;
    
    private String ife;

    private String concepto;

	/**
	 * Constructor.
	 */
	public RetiroReq() {
		super();
	}

	/**
	 * Método retiro.
	 * @param cuenta cuenta
	 * @param importe importe
	 * @param fecvalor fecvalor
	 * @param codope codope
	 * @param cveidof cveidof
	 * @param ife ife
	 * @param concepto concepto
	 */
	public RetiroReq(String cuenta, BigDecimal importe, String fecvalor,
			String codope, String cveidof, String ife, String concepto) {
		super();
		this.cuenta = cuenta;
		this.importe = importe;
		this.fecvalor = fecvalor;
		this.codope = codope;
		this.cveidof = cveidof;
		this.ife = ife;
		this.concepto = concepto;
	}

	/**
	 *  Getter & Setters.
	 * @return the cuenta
	 */
	public String getCuenta() {
		return cuenta;
	}

	/**
	 * Getter & Setters.
	 * @param bigInteger the cuenta to set
	 */
	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}

	/**
	 * Getter & Setters.
	 * @return the importe
	 */
	public BigDecimal getImporte() {
		return importe;
	}

	/**
	 * Getter & Setters.
	 * @param importe the importe to set
	 */
	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}

	/**
	 * Getter & Setters.
	 * @return the fecvalor
	 */
	public String getFecvalor() {
		return fecvalor;
	}

	/**
	 * Getter & Setters.
	 * @param fecvalor the fecvalor to set
	 */
	public void setFecvalor(String fecvalor) {
		this.fecvalor = fecvalor;
	}

	/**
	 * Getter & Setters.
	 * @return the codope
	 */
	public String getCodope() {
		return codope;
	}

	/**
	 * Getter & Setters.
	 * @param codope the codope to set
	 */
	public void setCodope(String codope) {
		this.codope = codope;
	}

	/**
	 * Getter & Setters.
	 * @return the cveidof
	 */
	public String getCveidof() {
		return cveidof;
	}

	/**
	 * Getter & Setters.
	 * @param cveidof the cveidof to set
	 */
	public void setCveidof(String cveidof) {
		this.cveidof = cveidof;
	}

	/**
	 * Getter & Setters.
	 * @return the ife
	 */
	public String getIfe() {
		return ife;
	}

	/**
	 * Getter & Setters.
	 * @param ife the ife to set
	 */
	public void setIfe(String ife) {
		this.ife = ife;
	}

	/**
	 * Getter & Setters.
	 * @return the concepto
	 */
	public String getConcepto() {
		return concepto;
	}

	/**
	 * Getter & Setters.
	 * @param concepto the concepto to set
	 */
	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}
    
}
