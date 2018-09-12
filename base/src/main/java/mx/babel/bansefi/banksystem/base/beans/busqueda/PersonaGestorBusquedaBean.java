package mx.babel.bansefi.banksystem.base.beans.busqueda;

import java.io.Serializable;

import mx.babel.arq.comun.annotations.CampoBusquedaAnnotation;
import mx.babel.arq.comun.annotations.CampoResultadoAnnotation;
import mx.babel.arq.comun.annotations.NavegaAnnotation;
import mx.babel.arq.comun.beans.PaginacionBean;

/**
 * Bean para la búsqueda de Gestores.
 * @author alejandro.pineda
 *
 */
@NavegaAnnotation(campoEnum = "FICHA_CLIENTE")
public class PersonaGestorBusquedaBean extends PaginacionBean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3333065879301196625L;
	// Entrada
//		@CampoBusquedaAnnotation(requerido = true, regex = "/^[a-zA-ZÑñ ]*$/", longitud = 30)
		private String nombre;
//		@CampoBusquedaAnnotation(requerido = true, regex = "/^[a-zA-ZÑñ ]*$/", longitud = 30)
		private String apellidoPaterno;
//		@CampoBusquedaAnnotation(requerido = false, regex = "/^[a-zA-ZÑñ ]*$/", longitud = 30)
		private String apellidoMaterno;
//		@CampoBusquedaAnnotation(requerido = true, regex = "/^[0-9a-zA-Z]*$/", longitud = 14)
		private String noIdentificador;
		
		// Entrada/Salida
		@CampoBusquedaAnnotation(requerido = true, desplegar = 0, regex = "/^[0-9]*$/", longitud = 9)
		@CampoResultadoAnnotation(posicion = 5, parametro = true, key = true, resultadoIntermedio=true)
		private int idInterna;

		// Salida
		@CampoResultadoAnnotation(posicion = 1, resultadoIntermedio=true)
		private String nombreCompleto;
		@CampoResultadoAnnotation(posicion = 3, resultadoIntermedio=true)
		private String fechaNacimiento;
		@CampoResultadoAnnotation(posicion = 2)
		private String identificacion;
		@CampoResultadoAnnotation(posicion = 4)
		private String domicilio;
		
		public String getNombre() {
			return nombre;
		}
		
		public void setNombre(String nombre) {
			this.nombre = nombre;
		}
		
		public String getApellidoPaterno() {
			return apellidoPaterno;
		}
		
		public void setApellidoPaterno(String apellidoPaterno) {
			this.apellidoPaterno = apellidoPaterno;
		}
		
		public String getApellidoMaterno() {
			return apellidoMaterno;
		}
		
		public void setApellidoMaterno(String apellidoMaterno) {
			this.apellidoMaterno = apellidoMaterno;
		}
		
		public String getNoIdentificador() {
			return noIdentificador;
		}
		
		public void setNoIdentificador(String noIdentificador) {
			this.noIdentificador = noIdentificador;
		}
		
		public int getIdInterna() {
			return idInterna;
		}
		
		public void setIdInterna(int idInterna) {
			this.idInterna = idInterna;
		}
		public String getNombreCompleto() {
			return nombreCompleto;
		}
		
		public void setNombreCompleto(String nombreCompleto) {
			this.nombreCompleto = nombreCompleto;
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
		
		public void setIdentificacion(String identificacion) {
			this.identificacion = identificacion;
		}
		
		public String getDomicilio() {
			return domicilio;
		}
		
		public void setDomicilio(String domicilio) {
			this.domicilio = domicilio;
		}		
}
