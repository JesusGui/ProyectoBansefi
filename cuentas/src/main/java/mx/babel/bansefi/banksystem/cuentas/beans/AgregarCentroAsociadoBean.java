package mx.babel.bansefi.banksystem.cuentas.beans;

import java.io.Serializable;

public class AgregarCentroAsociadoBean implements Serializable {

	/**
	 * serial para agregar centro asociado Bean
	 */
	private static final long serialVersionUID = 7295187716744773046L;

	private String centroAgregado;

	/**
	 * @return the centroAgregado
	 * @author Natalio Iván Mendoza Sánchez &lt;nmendoza@uanlmexico.mx&gt;
	 */
	public String getCentroAgregado() {
		return centroAgregado;
	}

	/**
	 * @param centroAgregado
	 *            the centroAgregado to set
	 * @author Natalio Iván Mendoza Sánchez &lt;nmendoza@uanlmexico.mx&gt;
	 */
	public void setCentroAgregado(String centroAgregado) {
		this.centroAgregado = centroAgregado;
	}

}
