package mx.babel.bansefi.banksystem.base.beans.busqueda;

import java.io.Serializable;

import mx.babel.arq.comun.annotations.CampoBusquedaAnnotation;
import mx.babel.arq.comun.annotations.CampoResultadoAnnotation;
import mx.babel.arq.comun.annotations.NavegaAnnotation;
import mx.babel.arq.comun.beans.PaginacionBean;

/**
 * Clase que define el Bean para la búsqueda de Empleados.
 *
 * @author javier.martinnino
 *
 */
@NavegaAnnotation(campoEnum = "FICHA_EMPLEADO")
public class EmpleadoBusquedaBean extends PaginacionBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3330101294772642686L;

	// Entrada/Salida
	@CampoBusquedaAnnotation(requerido = true, desplegar = 0, regex = "/^[a-zA-Z0-9ñÑ\\- ]*$/", longitud = 8, tituloCampo="Id. Empleado")
	@CampoResultadoAnnotation(posicion = 1, parametro = true, key = true, tituloColumna="Id. Empleado")
	private String idEmpleado;
		
	@CampoBusquedaAnnotation(requerido = true, desplegar = 1, regex = "/^[0-9]*$/", longitud = 8, tituloCampo="Id. Interna")	
	@CampoResultadoAnnotation(desplegar = -1, parametro = true)
	private int idInterna;

	@CampoBusquedaAnnotation(requerido = true, desplegar = 2, regex = "/^[0-9]*$/", longitud = 4, tituloCampo="Código Centro")	
	@CampoResultadoAnnotation(posicion = 3, tituloColumna="Código Centro")
	private String codigoCentro;

	// Salida
	@CampoResultadoAnnotation(posicion = 2, parametro = true, tituloColumna="Nombre")
	private String nombre;

	@CampoResultadoAnnotation(posicion = 4, tituloColumna="Perfil")
	private String perfil;

	private int elevatorPosition;
	private int scrollableOccurs;


	public EmpleadoBusquedaBean() {		
	}

	public String getIdEmpleado() {
		return idEmpleado;
	}
	
	public void setIdEmpleado(final String idEmpleado) {
		this.idEmpleado = idEmpleado;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(final String nombre) {
		this.nombre = nombre;
	}

	public int getIdInterna() {
		return idInterna;
	}

	public void setIdInterna(final int idInterna) {
		this.idInterna = idInterna;
	}

	public int getElevatorPosition() {
		return elevatorPosition;
	}

	public void setElevatorPosition(final int elevatorPosition) {
		this.elevatorPosition = elevatorPosition;
	}

	public int getScrollableOccurs() {
		return scrollableOccurs;
	}

	public void setScrollableOccurs(final int scrollableOccurs) {
		this.scrollableOccurs = scrollableOccurs;
	}

	public String getCodigoCentro() {
		return codigoCentro;
	}

	public void setCodigoCentro(final String codigoCentro) {
		this.codigoCentro = codigoCentro;
	}

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(final String perfil) {
		this.perfil = perfil;
	}
	
}