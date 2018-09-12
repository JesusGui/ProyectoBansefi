package mx.babel.bansefi.banksystem.base.beans.domain;

import java.io.Serializable;
import java.util.List;

public class UsoCuentaBean implements Serializable{

	private static final long serialVersionUID = -7038826651285097815L;

	private String manejoRecursosPropios;
	
	private String manejoRecursosTerceros;
	
	private Boolean funcionarioPublico;
	
	private Boolean asociadoFuncionarioPublico;
	
	private List<String> operaciones;
		
	private List<String> usos;
	
	private String otrosUsos;
	
	private String funcionarioPublicoCargo;
	
	private String asociadoFuncionarioPublicoCargo;
	
	private String asociadoFuncionarioPublicoNombre;
	
	private String nivelRiesgo;
	
	/**
	 * @return Atributo manejoRecursosPropios
	 */
	public String getManejoRecursosPropios() {
		return manejoRecursosPropios;
	}

	/**
	 * @param manejoRecursosPropios Atributo manejoRecursosPropios a definir
	 */
	public void setManejoRecursosPropios(String manejoRecursosPropios) {
		this.manejoRecursosPropios = manejoRecursosPropios;
	}

	/**
	 * @return Atributo manejoRecursosTerceros
	 */
	public String getManejoRecursosTerceros() {
		return manejoRecursosTerceros;
	}

	/**
	 * @param manejoRecursosTerceros Atributo manejoRecursosTerceros a definir
	 */
	public void setManejoRecursosTerceros(String manejoRecursosTerceros) {
		this.manejoRecursosTerceros = manejoRecursosTerceros;

	}

	/**
	 * @return Atributo funcionarioPublico
	 */
	public Boolean getFuncionarioPublico() {
		return funcionarioPublico;
	}

	/**
	 * @param funcionarioPublico Atributo funcionarioPublico a definir
	 */
	public void setFuncionarioPublico(Boolean funcionarioPublico) {
		this.funcionarioPublico = funcionarioPublico;
	}

	/**
	 * @return Atributo asociadoFuncionarioPublico
	 */
	public Boolean getAsociadoFuncionarioPublico() {
		return asociadoFuncionarioPublico;
	}

	/**
	 * @param asociadoFuncionarioPublico Atributo asociadoFuncionarioPublico a definir
	 */
	public void setAsociadoFuncionarioPublico(Boolean asociadoFuncionarioPublico) {
		this.asociadoFuncionarioPublico = asociadoFuncionarioPublico;
	}

	/**
	 * @return Atributo otrosUsos
	 */
	public String getOtrosUsos() {
		return otrosUsos;
	}

	/**
	 * @param otrosUsos Atributo otrosUsos a definir
	 */
	public void setOtrosUsos(String otrosUsos) {
		this.otrosUsos = otrosUsos;
	}


	/**
	 * @return Atributo funcionarioPublicoCargo
	 */
	public String getFuncionarioPublicoCargo() {
		return funcionarioPublicoCargo;
	}

	/**
	 * @param funcionarioPublicoCargo Atributo funcionarioPublicoCargo a definir
	 */
	public void setFuncionarioPublicoCargo(String funcionarioPublicoCargo) {
		this.funcionarioPublicoCargo = funcionarioPublicoCargo;
	}

	/**
	 * @return Atributo asociadoFuncionarioPublico
	 */
	public String getAsociadoFuncionarioPublicoCargo() {
		return asociadoFuncionarioPublicoCargo;
	}

	/**
	 * @param asociadoFuncionarioPublicoCargo Atributo asociadoFuncionarioPublicoCargo a definir
	 */
	public void setAsociadoFuncionarioPublicoCargo(
			String asociadoFuncionarioPublicoCargo) {
		this.asociadoFuncionarioPublicoCargo = asociadoFuncionarioPublicoCargo;
	}

	/**
	 * @return the operaciones
	 */
	public List<String> getOperaciones() {
		return operaciones;
	}

	/**
	 * @param operaciones the operaciones to set
	 */
	public void setOperaciones(List<String> operaciones) {
		this.operaciones = operaciones;
	}

	/**
	 * @return the usos
	 */
	public List<String> getUsos() {
		return usos;
	}

	/**
	 * @param usos the usos to set
	 */
	public void setUsos(List<String> usos) {
		this.usos = usos;
	}

	/**
	 * @return the asociadoFuncionarioPublicoNombre
	 */
	public String getAsociadoFuncionarioPublicoNombre() {
		return asociadoFuncionarioPublicoNombre;
	}

	/**
	 * @param asociadoFuncionarioPublicoNombre the asociadoFuncionarioPublicoNombre to set
	 */
	public void setAsociadoFuncionarioPublicoNombre(
			String asociadoFuncionarioPublicoNombre) {
		this.asociadoFuncionarioPublicoNombre = asociadoFuncionarioPublicoNombre;
	}

	public String getNivelRiesgo() {
		return nivelRiesgo;
	}

	public void setNivelRiesgo(String nivelRiesgo) {
		this.nivelRiesgo = nivelRiesgo;
	}
}
