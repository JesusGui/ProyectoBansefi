package mx.babel.bansefi.banksystem.base.utils;


import java.io.Serializable;

/**
 * Clase que define el modelo de la Columna en las Tablas.
 * @author alejandro.pineda
 *
 */
public class ModeloColumnaUtils implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String propiedad;
	private String header;
	private Class<?> tipo;
	private int posicion;
	
	public ModeloColumnaUtils(){}

	public String getPropiedad() {
		return propiedad;
	}

	public void setPropiedad(String propiedad) {
		this.propiedad = propiedad;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public Class<?> getTipo() {
		return tipo;
	}

	public void setTipo(Class<?> tipo) {
		this.tipo = tipo;
	}

	public int getPosicion() {
		return posicion;
	}

	public void setPosicion(int posicion) {
		this.posicion = posicion;
	}
	
	
}
