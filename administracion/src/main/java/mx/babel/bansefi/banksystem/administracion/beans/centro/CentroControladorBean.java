package mx.babel.bansefi.banksystem.administracion.beans.centro;

import java.io.Serializable;

/**
 * Bean utilizado para las aplicaciones de centros controladores.
 * 
 * @author alejandro.pineda
 * 
 */
public class CentroControladorBean implements Serializable {

	private static final long serialVersionUID = -9213173965752723093L;

	private String entidad;

	private String codigoCentro;

	private String codigoCentroControlador;

	public CentroControladorBean() {

	}

	public String getEntidad() {
		return entidad;
	}

	public void setEntidad(String entidad) {
		this.entidad = entidad;
	}

	public String getCodigoCentro() {
		return codigoCentro;
	}

	public void setCodigoCentro(String codigoCentro) {
		this.codigoCentro = codigoCentro;
	}

	public String getCodigoCentroControlador() {
		return codigoCentroControlador;
	}

	public void setCodigoCentroControlador(String codigoCentroControlador) {
		this.codigoCentroControlador = codigoCentroControlador;
	}

}
