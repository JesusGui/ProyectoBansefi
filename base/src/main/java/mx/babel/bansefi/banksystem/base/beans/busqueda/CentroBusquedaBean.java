package mx.babel.bansefi.banksystem.base.beans.busqueda;

import java.io.Serializable;

import mx.babel.arq.comun.annotations.CampoBusquedaAnnotation;
import mx.babel.arq.comun.annotations.CampoResultadoAnnotation;
import mx.babel.arq.comun.annotations.NavegaAnnotation;
import mx.babel.arq.comun.beans.PaginacionBean;

/**
 * Clase que define el Bean para la búsqueda del Centro.
 * 
 * @author alejandro.pineda
 * 
 */
@NavegaAnnotation(campoEnum = "ALTA_CENTRO")
public class CentroBusquedaBean extends PaginacionBean implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 5175143927846074590L;
	
	@CampoBusquedaAnnotation(requerido = true, desplegar = 0, regex = "/^[0-9]*$/", longitud = 4, tituloCampo="Código Centro")
	@CampoResultadoAnnotation(posicion = 1, parametro = true, key = true, tituloColumna="Código Centro")
	private String codigoCentro;
	@CampoResultadoAnnotation(posicion = 2)
	@CampoBusquedaAnnotation(requerido = true, desplegar = 1, regex = "/^[0-9a-zA-ZÑñ\\- ]*$/", longitud = 30)
	private String nombre;

	@CampoResultadoAnnotation(posicion = 3)
	private String fechaAlta;
	@CampoResultadoAnnotation(posicion = 4)
	private String estado;

	public CentroBusquedaBean() {

	}

	public String getCodigoCentro() {
		return codigoCentro;
	}

	public void setCodigoCentro(String codigoCentro) {
		this.codigoCentro = codigoCentro;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(String fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

}
