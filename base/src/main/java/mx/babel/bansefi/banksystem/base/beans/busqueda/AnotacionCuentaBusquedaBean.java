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
						  backend = "busquedaCuentaBackEnd", 
						  tipoServicio = "WS",
						  objetoBusqueda="mx.babel.bansefi.banksystem.base.beans.busqueda.CuentaBusquedaBean",
						  parametros={"numeroCuenta"},
						  construyeBean = true, opcionSegundaBusqueda=0, paramSegundaBusqueda="numeroCuenta"),
@BusquedaPreviaAnnotation(opcionBusqueda = 1, 
						  backend = "busquedaCuentaBackEnd", 
						  tipoServicio = "WS",
						  objetoBusqueda="mx.babel.bansefi.banksystem.base.beans.busqueda.CuentaBusquedaBean",
						  parametros={"numeroCuenta"},
						  construyeBean = true, opcionSegundaBusqueda=0, paramSegundaBusqueda="numeroCuenta")						  
})
@NavegaAnnotation(campoEnum = "DETALLE_ANOTACIONES")
public class AnotacionCuentaBusquedaBean implements Serializable {
	
	private static final long serialVersionUID = -1634378018063898267L;
	
	// Entrada/Salida
	@CampoBusquedaAnnotation(requerido = true, desplegar = 0, regex = "/^[0-9]*$/", longitud = 10)
	@CampoResultadoAnnotation(desplegar= -1, parametro = true)
	private long numeroCuenta;
	@CampoBusquedaAnnotation(requerido = true, desplegar = 1, regex = "/^[0-9]*$/", longitud = 16)
	private String numPan;
		
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
		
	public Long getNumeroCuenta() {
		return numeroCuenta;
	}
	
	public void setNumeroCuenta(Long numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}
	
	public String getNumPan() {
		return numPan;
	}
	
	public void setNumPan(String noPan) {
		this.numPan = noPan;
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

}