package mx.babel.bansefi.banksystem.base.beans.busqueda;

import java.io.Serializable;

import mx.babel.arq.comun.annotations.CampoBusquedaAnnotation;
import mx.babel.arq.comun.annotations.CampoResultadoAnnotation;
import mx.babel.arq.comun.annotations.NavegaAnnotation;

/**
 * Clase para definir el modelo de búsqueda de Personas Cliente y Gestor
 * que hereda de PersonaBean.
 * @author alejandro.pineda
 * 
 */
@NavegaAnnotation(campoEnum = "FICHA_CLIENTE")
public class PersonasClienteBusquedaBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -116410839677682010L;
	// Entrada
	@CampoBusquedaAnnotation(requerido = true, desplegar = 0, regex = "/^[a-zA-ZÑñ ]*$/", longitud = 30)
	private String nombre;
	@CampoBusquedaAnnotation(requerido = true, desplegar = 0, regex = "/^[a-zA-ZÑñ ]*$/", longitud = 30)
	private String apellidoPaterno;
	@CampoBusquedaAnnotation(requerido = false, desplegar = 0, regex = "/^[a-zA-ZÑñ ]*$/", longitud = 30)
	private String apellidoMaterno;
	@CampoBusquedaAnnotation(requerido = true, desplegar = 1, regex = "/^[0-9a-zA-Z]*$/", longitud = 14, tituloCampo="N° Identificador")
	private String noIdentificador;
	
	// Entrada/Salida
	@CampoBusquedaAnnotation(requerido = true, desplegar = 2, regex = "/^[0-9]*$/", longitud = 9)
	@CampoResultadoAnnotation(posicion = 5, parametro = true, key = true, resultadoIntermedio=true)
	private int idInterna;

	// Salida
	@CampoResultadoAnnotation(posicion = 1, parametro = true, resultadoIntermedio=true)
	private String nombreCompleto;
	@CampoResultadoAnnotation(posicion = 3, resultadoIntermedio=true)
	private String fechaNacimiento;
	@CampoResultadoAnnotation(posicion = 2, parametro = true,tituloColumna = "Identificación")
	private String identificacion;
	@CampoResultadoAnnotation(posicion = 4)
	private String domicilio;

	public PersonasClienteBusquedaBean() {
		
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidoMaterno() {
		return apellidoMaterno;
	}

	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}

	public String getApellidoPaterno() {
		return apellidoPaterno;
	}

	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}
	
	public Integer getIdInterna() {
		return idInterna;
	}
	
	public void setIdInterna(Integer idInterna) {
		this.idInterna = idInterna;
	}
	
	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public String getNoIdentificador() {
		return noIdentificador;
	}

	public void setNoIdentificador(String noIdentificador) {
		this.noIdentificador = noIdentificador;
	}

	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getIdentificacion() {
		return identificacion;
	}
	
	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}	
	
}
