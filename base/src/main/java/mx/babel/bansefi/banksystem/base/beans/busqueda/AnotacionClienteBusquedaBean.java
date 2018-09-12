package mx.babel.bansefi.banksystem.base.beans.busqueda;

import java.io.Serializable;

import mx.babel.arq.comun.annotations.ArregloBusquedasPreviasAnnotation;
import mx.babel.arq.comun.annotations.BusquedaPreviaAnnotation;
import mx.babel.arq.comun.annotations.CampoBusquedaAnnotation;
import mx.babel.arq.comun.annotations.CampoResultadoAnnotation;
import mx.babel.arq.comun.annotations.NavegaAnnotation;

/**
 * Clase que define el Bean para la búsqueda de Anotaciones.
 *
 * @author javier.martinnino
 *
 */
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
						  backend = "busquedaPersonaBackEnd", 
						  tipoServicio = "WS",
						  objetoBusqueda="mx.babel.bansefi.banksystem.base.beans.busqueda.PersonasClienteBusquedaBean",
						  parametros={"idInterna:"},
						  construyeBean = true, opcionSegundaBusqueda=1, paramSegundaBusqueda="idInterna"),
@BusquedaPreviaAnnotation(opcionBusqueda = 3, 
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
@NavegaAnnotation(campoEnum = "DETALLE_ANOTACIONES")
public class AnotacionClienteBusquedaBean implements Serializable{

	private static final long serialVersionUID = -3108857125883532558L;
	
	// Entrada
	@CampoBusquedaAnnotation(requerido = true, desplegar = 0, regex = "/^[a-zA-ZÑñ ]*$/", longitud = 30)
	private String nombre;
	@CampoBusquedaAnnotation(requerido = true, desplegar = 0, regex = "/^[a-zA-ZÑñ ]*$/", longitud = 30)
	private String apellidoPaterno;
	@CampoBusquedaAnnotation(requerido = false, desplegar = 0, regex = "/^[a-zA-ZÑñ ]*$/", longitud = 30)
	private String apellidoMaterno;
	@CampoBusquedaAnnotation(requerido = true, desplegar = 1, regex = "/^[0-9a-zA-Z]*$/", longitud = 14)
	private String noIdentificador;
	
	@CampoBusquedaAnnotation(requerido = true, desplegar = 4, regex = "/^[0-9a-zA-ZÑñ ]*$/", longitud = 30)
	private String razonSocial;
	
	@CampoBusquedaAnnotation(requerido = true, desplegar = 3, regex = "/^[0-9a-zA-Z]*$/", longitud = 14)
	private String actaConstitutiva;
	
	// Entrada/Salida
	@CampoBusquedaAnnotation(requerido = true, desplegar = 2, regex = "/^[0-9]*$/", longitud = 9)
	@CampoResultadoAnnotation(desplegar= -1, parametro = true)
	private int idInterna;
			
	// Salida
	@CampoResultadoAnnotation(desplegar= -1, parametro = true, key = true)
	private long numero;
	
	@CampoResultadoAnnotation(desplegar = -1, parametro = true)
	private String tipo;
	
	@CampoResultadoAnnotation(desplegar = -1, parametro = true)
	private String subcodigo;
	
	@CampoResultadoAnnotation(posicion = 1, tituloColumna="Prioridad")
	private String prioridad;
	
	@CampoResultadoAnnotation(posicion = 2, tituloColumna="Descripción breve")
	private String descripcionCorta;
	
	@CampoResultadoAnnotation(posicion = 3, tituloColumna="Subcódigo")
	private String subCodigoDescripcion;
	
	@CampoResultadoAnnotation(posicion = 4, tituloColumna="F.de activación")
	private String fechaInicio;
	
	@CampoResultadoAnnotation(posicion = 5, tituloColumna="F.de cierre")
	private String fechaCierre;
	
	
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
	
	public String getNoIdentificador() {
		return noIdentificador;
	}

	public void setNoIdentificador(String noIdentificador) {
		this.noIdentificador = noIdentificador;
	}
	
	public long getNumero() {
		return numero;
	}
	
	public void setNumero(final long numero) {
		this.numero = numero;
	}
	
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public String getSubcodigo() {
		return subcodigo;
	}

	public void setSubcodigo(String subcodigo) {
		this.subcodigo = subcodigo;
	}
	
	public String getPrioridad() {
		return prioridad;
	}

	public void setPrioridad(String prioridad) {
		this.prioridad = prioridad;
	}
	
	public String getDescripcionCorta() {
		return descripcionCorta;
	}
	
	public void setDescripcionCorta(final String descripcionCorta) {
		this.descripcionCorta = descripcionCorta;
	}

	public String getSubCodigoDescripcion() {
		return subCodigoDescripcion;
	}

	public void setSubCodigoDescripcion(String subCodigoDescripcion) {
		this.subCodigoDescripcion = subCodigoDescripcion;
	}

	public String getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public String getFechaCierre() {
		return fechaCierre;
	}

	public void setFechaCierre(String fechaCierre) {
		this.fechaCierre = fechaCierre;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public String getActaConstitutiva() {
		return actaConstitutiva;
	}

	public void setActaConstitutiva(String actaConstitutiva) {
		this.actaConstitutiva = actaConstitutiva;
	}

	public void setIdInterna(int idInterna) {
		this.idInterna = idInterna;
	}	
	
}