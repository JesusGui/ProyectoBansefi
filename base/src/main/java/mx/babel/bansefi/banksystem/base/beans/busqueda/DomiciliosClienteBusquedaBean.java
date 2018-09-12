package mx.babel.bansefi.banksystem.base.beans.busqueda;

import java.io.Serializable;

import mx.babel.arq.comun.annotations.ArregloBusquedasPreviasAnnotation;
import mx.babel.arq.comun.annotations.BusquedaPreviaAnnotation;
import mx.babel.arq.comun.annotations.CampoBusquedaAnnotation;
import mx.babel.arq.comun.annotations.CampoResultadoAnnotation;
import mx.babel.arq.comun.annotations.NavegaAnnotation;

@ArregloBusquedasPreviasAnnotation({
@BusquedaPreviaAnnotation(opcionBusqueda = 0, 
						  backend = "busquedaPersonaFisicaBackEnd", 
						  tipoServicio = "WS",
						  objetoBusqueda="mx.babel.bansefi.banksystem.base.beans.busqueda.PersonasClienteBusquedaBean",
						  parametros={"nombre:","apellidoPaterno:","apellidoMaterno:"},
						  construyeBean = true, opcionSegundaBusqueda=1, paramSegundaBusqueda="idInterna"),
@BusquedaPreviaAnnotation(opcionBusqueda = 1, 
						  backend = "busquedaPersonaFisicaBackEnd", 
						  tipoServicio = "WS",
						  objetoBusqueda="mx.babel.bansefi.banksystem.base.beans.busqueda.PersonasClienteBusquedaBean",
						  parametros={"noIdentificador"},
						  construyeBean = true, opcionSegundaBusqueda=1, paramSegundaBusqueda="idInterna"),
@BusquedaPreviaAnnotation(opcionBusqueda = 2, 
						  backend = "busquedaPersonaMoralBackEnd", 
						  tipoServicio = "WS",
						  objetoBusqueda="mx.babel.bansefi.banksystem.base.beans.busqueda.PersonaMoralBusquedaBean",
						  parametros={"actaConstitutiva"},
						  construyeBean = true, opcionSegundaBusqueda=1, paramSegundaBusqueda="idInterna"),
@BusquedaPreviaAnnotation(opcionBusqueda = 4, 
						backend = "busquedaPersonaMoralBackEnd", 
						tipoServicio = "WS",
						objetoBusqueda="mx.babel.bansefi.banksystem.base.beans.busqueda.PersonaMoralBusquedaBean",
						parametros={"razonSocial"},
						construyeBean = true, opcionSegundaBusqueda=1, paramSegundaBusqueda="idInterna")
})
@NavegaAnnotation(campoEnum = "DOMICILIO_PERSONA")
public class DomiciliosClienteBusquedaBean implements Serializable{
	
	private static final long serialVersionUID = -2831531291327857091L;
	
	// Entrada
	@CampoBusquedaAnnotation(requerido = true, desplegar = 0, regex = "/^[a-zA-ZáéíóúÁÉÍÓÚ ]*$/", longitud = 30)
	private String nombre;
	@CampoBusquedaAnnotation(requerido = true, desplegar = 0, regex = "/^[a-zA-ZáéíóúÁÉÍÓÚ ]*$/", longitud = 30)
	private String apellidoPaterno;
	@CampoBusquedaAnnotation(requerido = false, desplegar = 0, regex = "/^[a-zA-ZáéíóúÁÉÍÓÚ ]*$/", longitud = 30)
	private String apellidoMaterno;
	@CampoBusquedaAnnotation(requerido = true, desplegar = 1, regex = "/^[0-9a-zA-Z]*$/", longitud = 14)
	private String noIdentificador;
	
	@CampoBusquedaAnnotation(requerido = true, desplegar = 4, regex = "/^[a-zA-ZÑñ ]*$/", longitud = 30)
	private String razonSocial;
	
	@CampoBusquedaAnnotation(requerido = true, desplegar = 2, regex = "/^[0-9a-zA-Z]*$/", longitud = 14)
	private String actaConstitutiva;
	
	@CampoBusquedaAnnotation(requerido = true, desplegar = 3, regex = "/^[0-9]*$/", longitud = 9)
	@CampoResultadoAnnotation(desplegar = -1,parametro = true)
	private int idInterna;
				
	// Salida
	@CampoResultadoAnnotation(posicion = 1, parametro = true, key = true)
	private String tipo;
	
	@CampoResultadoAnnotation(posicion = 2, parametro = true, key = true)
	private String calle;
		
	@CampoResultadoAnnotation(posicion = 3, parametro = true, key = true)
	private String localidad;
	
	@CampoResultadoAnnotation(desplegar = -1, parametro = true)
	private String numeroDireccion;

	/**
	 * @return Atributo nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre Atributo nombre a definir
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return Atributo apellidoPaterno
	 */
	public String getApellidoPaterno() {
		return apellidoPaterno;
	}

	/**
	 * @param apellidoPaterno Atributo apellidoPaterno a definir
	 */
	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}

	/**
	 * @return Atributo apellidoMaterno
	 */
	public String getApellidoMaterno() {
		return apellidoMaterno;
	}

	/**
	 * @param apellidoMaterno Atributo apellidoMaterno a definir
	 */
	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}

	/**
	 * @return Atributo noIdentificador
	 */
	public String getNoIdentificador() {
		return noIdentificador;
	}

	/**
	 * @param noIdentificador Atributo noIdentificador a definir
	 */
	public void setNoIdentificador(String noIdentificador) {
		this.noIdentificador = noIdentificador;
	}

	/**
	 * @return Atributo razonSocial
	 */
	public String getRazonSocial() {
		return razonSocial;
	}

	/**
	 * @param razonSocial Atributo razonSocial a definir
	 */
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	/**
	 * @return Atributo actaConstitutiva
	 */
	public String getActaConstitutiva() {
		return actaConstitutiva;
	}

	/**
	 * @param actaConstitutiva Atributo actaConstitutiva a definir
	 */
	public void setActaConstitutiva(String actaConstitutiva) {
		this.actaConstitutiva = actaConstitutiva;
	}

	/**
	 * @return Atributo idInterna
	 */
	public int getIdInterna() {
		return idInterna;
	}

	/**
	 * @param idInterna Atributo idInterna a definir
	 */
	public void setIdInterna(int idInterna) {
		this.idInterna = idInterna;
	}

	/**
	 * @return Atributo tipo
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * @param tipo Atributo tipo a definir
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/**
	 * @return Atributo calle
	 */
	public String getCalle() {
		return calle;
	}

	/**
	 * @param calle Atributo calle a definir
	 */
	public void setCalle(String calle) {
		this.calle = calle;
	}

	/**
	 * @return Atributo localidad
	 */
	public String getLocalidad() {
		return localidad;
	}

	/**
	 * @param localidad Atributo localidad a definir
	 */
	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	/**
	 * @return Atributo numeroDireccion
	 */
	public String getNumeroDireccion() {
		return numeroDireccion;
	}

	/**
	 * @param numeroDireccion Atributo numeroDireccion a definir
	 */
	public void setNumeroDireccion(String numeroDireccion) {
		this.numeroDireccion = numeroDireccion;
	}

	
}
