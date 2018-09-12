package mx.babel.bansefi.banksystem.base.beans.busqueda;

import java.io.Serializable;

import mx.babel.arq.comun.annotations.CampoBusquedaAnnotation;
import mx.babel.arq.comun.annotations.CampoResultadoAnnotation;
import mx.babel.arq.comun.annotations.NavegaAnnotation;
import mx.babel.arq.comun.beans.PaginacionBean;

/**
 * Clase que define modelo de Persona Moral
 * @author alejandro.pineda
 *
 */
@NavegaAnnotation(campoEnum = "FICHA_CLIENTE")
public class PersonaMoralBusquedaBean extends PaginacionBean implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2543082411396245955L;

	// Entrada/Salida
	@CampoBusquedaAnnotation(requerido = true, desplegar = 0, regex = "/^[0-9a-zA-ZÑñ ]*$/", longitud = 30, tituloCampo="Razón Social")
	@CampoResultadoAnnotation(posicion = 1,  parametro = true, resultadoIntermedio=true)
	private String razonSocial;
	
	@CampoBusquedaAnnotation(requerido = true, desplegar = 1, regex = "/^[0-9a-zA-Z]*$/", longitud = 14)
	@CampoResultadoAnnotation(posicion = 2,  parametro = true, resultadoIntermedio=true)
	private String actaConstitutiva;
	
	@CampoResultadoAnnotation(posicion = 3)
	private String domicilio;
	
	@CampoBusquedaAnnotation(requerido = true, desplegar = 2, regex = "/^[0-9]*$/", longitud = 9)
	@CampoResultadoAnnotation(posicion = 4, parametro = true, key = true,  resultadoIntermedio=true)
	private int idInterna;
	

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String denominaLegal) {
		this.razonSocial = denominaLegal;
	}

	public String getActaConstitutiva() {
		return actaConstitutiva;
	}

	public void setActaConstitutiva(String actaConstitutiva) {
		this.actaConstitutiva = actaConstitutiva;
	}
	
	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public int getIdInterna() {
		return idInterna;
	}
	
	public void setIdInterna(int idInterna) {
		this.idInterna = idInterna;
	}

}
