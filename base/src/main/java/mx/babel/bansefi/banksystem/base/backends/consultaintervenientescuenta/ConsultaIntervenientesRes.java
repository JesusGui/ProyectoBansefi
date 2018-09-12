package mx.babel.bansefi.banksystem.base.backends.consultaintervenientescuenta;

import java.io.Serializable;



public class ConsultaIntervenientesRes implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String numeroIdentificacion;
	private String tipoIdentificacion;

	public ConsultaIntervenientesRes() {
		super();
	}

	/**
	 * @param numeroIdentificacion
	 * @param tipoIdentificacion
	 */
	public ConsultaIntervenientesRes(String numeroIdentificacion,
			String tipoIdentificacion) {
		super();
		this.numeroIdentificacion = numeroIdentificacion;
		this.tipoIdentificacion = tipoIdentificacion;
	}

	/**
	 * @return the numeroIdentificacion
	 */
	public String getNumeroIdentificacion() {
		return numeroIdentificacion;
	}

	/**
	 * @param numeroIdentificacion the numeroIdentificacion to set
	 */
	public void setNumeroIdentificacion(String numeroIdentificacion) {
		this.numeroIdentificacion = numeroIdentificacion;
	}

	/**
	 * @return the tipoIdentificacion
	 */
	public String getTipoIdentificacion() {
		return tipoIdentificacion;
	}

	/**
	 * @param tipoIdentificacion the tipoIdentificacion to set
	 */
	public void setTipoIdentificacion(String tipoIdentificacion) {
		this.tipoIdentificacion = tipoIdentificacion;
	}
	
	
}
