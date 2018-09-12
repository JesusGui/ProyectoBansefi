package mx.babel.bansefi.banksystem.administracion.beans.centro;

import java.io.Serializable;

/**
 * Bean para la ventana de Acuerdos Instrumentales.
 * 
 * @author alejandro.pineda
 * 
 */
public class AcuerdoInstrumentalBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8860426600759630886L;

	private Integer idApoderado;
	private String linea;
	private String grupo;
	private String nombre;
	private long cuenta;
	private String codigoCentro;
	private Integer idInternoCentro;
	private boolean alta;

	public AcuerdoInstrumentalBean() {

	}

	public Integer getIdApoderado() {
		return idApoderado;
	}

	public void setIdApoderado(Integer idApoderado) {
		if (idApoderado.intValue() == 0) {
			this.idApoderado = null;
		} else {
			this.idApoderado = idApoderado;
		}
	}

	public String getLinea() {
		return linea;
	}

	public void setLinea(String linea) {
		this.linea = linea;
	}

	public String getGrupo() {
		return grupo;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public long getCuenta() {
		return cuenta;
	}

	public void setCuenta(long cuenta) {
		this.cuenta = cuenta;
	}

	public String getCodigoCentro() {
		return codigoCentro;
	}

	public void setCodigoCentro(String codigoCentro) {
		this.codigoCentro = codigoCentro;
	}

	public Integer getIdInternoCentro() {
		return idInternoCentro;
	}

	public void setIdInternoCentro(Integer idInternoCentro) {
		this.idInternoCentro = idInternoCentro;
	}

	public boolean isAlta() {
		return alta;
	}

	public void setAlta(boolean alta) {
		this.alta = alta;
	}

}
