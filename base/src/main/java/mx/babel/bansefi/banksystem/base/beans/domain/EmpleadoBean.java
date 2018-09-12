package mx.babel.bansefi.banksystem.base.beans.domain;

import java.io.Serializable;
import java.util.List;

/**
 * Clase para definir el modelo de Empleados.
 * @author javier.martinnino
 * 
 */
public class EmpleadoBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String numEmpleado;
	
	private String centroPertenencia;
	
	private String cargo;
		
	private String perfil;
	
	private String perfilTCB;
		
	private ClientePersonaFisicaBean datos;
	
	private AtribucionBean atribucionFija;
	
	private List<AtribucionBean> atribucionesTemporales;

	/**
	 * @return el numEmpleado
	 */
	public String getNumEmpleado() {
		return numEmpleado;
	}

	/**
	 * @param numEmpleado el numEmpleado a definir
	 */
	public void setNumEmpleado(String numEmpleado) {
		this.numEmpleado = numEmpleado;
	}

	/**
	 * @return el centroPertenencia
	 */
	public String getCentroPertenencia() {
		return centroPertenencia;
	}

	/**
	 * @param centroPertenencia el centroPertenencia a definir
	 */
	public void setCentroPertenencia(String centroPertenencia) {
		this.centroPertenencia = centroPertenencia;
	}

	/**
	 * @return el cargo
	 */
	public String getCargo() {
		return cargo;
	}

	/**
	 * @param cargo el cargo a definir
	 */
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	
	/**
	 * @return the perfil
	 */
	public String getPerfil() {
		return perfil;
	}

	/**
	 * @param perfil the perfil to set
	 */
	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}
	
	public String getPerfilTCB() {
		return perfilTCB;
	}

	public void setPerfilTCB(String perfilTCB) {
		this.perfilTCB = perfilTCB;
	}

	/**
	 * @return the datos
	 */
	public ClientePersonaFisicaBean getDatos() {
		return datos;
	}

	/**
	 * @param datos the datos to set
	 */
	public void setDatos(ClientePersonaFisicaBean datos) {
		this.datos = datos;
	}

	/**
	 * @return the atribucionFija
	 */
	public AtribucionBean getAtribucionFija() {
		return atribucionFija;
	}

	/**
	 * @param atribucionFija the atribucionFija to set
	 */
	public void setAtribucionFija(AtribucionBean atribucionFija) {
		this.atribucionFija = atribucionFija;
	}

	/**
	 * @return the atribucionesTemporales
	 */
	public List<AtribucionBean> getAtribucionesTemporales() {
		return atribucionesTemporales;
	}

	/**
	 * @param atribucionesTemporales the atribucionesTemporales to set
	 */
	public void setAtribucionesTemporales(List<AtribucionBean> atribucionesTemporales) {
		this.atribucionesTemporales = atribucionesTemporales;
	}

}
