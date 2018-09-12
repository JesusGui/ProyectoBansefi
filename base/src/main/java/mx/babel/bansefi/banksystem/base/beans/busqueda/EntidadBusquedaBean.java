package mx.babel.bansefi.banksystem.base.beans.busqueda;

import java.io.Serializable;

import mx.babel.arq.comun.annotations.CampoBusquedaAnnotation;
import mx.babel.arq.comun.annotations.CampoResultadoAnnotation;
import mx.babel.arq.comun.annotations.NavegaAnnotation;
import mx.babel.arq.comun.beans.PaginacionBean;

/**
 * Clase que define el Bean para la búsqueda de Entidad.
 * 
 * @author Luis.gonzalez
 * 
 */
@NavegaAnnotation(campoEnum = "CONSULTA_ENTIDAD")
public class EntidadBusquedaBean extends PaginacionBean implements Serializable{
 
    /**
	 * 
	 */
	private static final long serialVersionUID = -5788601597858106699L;

	@CampoBusquedaAnnotation(requerido = true, desplegar = 0, regex="/[\\d\\-\\.]/", longitud=4)
    @CampoResultadoAnnotation(posicion = 1, parametro = true, key = true)
    private String codigoEntidad;
    
    @CampoResultadoAnnotation(posicion = 2)
    @CampoBusquedaAnnotation(requerido = true, desplegar = 1, regex="/^[0-9a-zA-ZáéíóúÁÉÍÓÚ\\-\\.\\ñ\\Ñ ]*$/", longitud=36 )
    private String nombre;
    
    @CampoResultadoAnnotation(posicion = 3)
    private String fechaAlta;

    private int elevatorPosition;
    private int scrollableOccurs;

    public String getCodigoEntidad() {
        return codigoEntidad;
    }

    public void setCodigoEntidad(String codigoEntidad) {
        this.codigoEntidad = codigoEntidad;
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

    public int getElevatorPosition() {
        return elevatorPosition;
    }

    public void setElevatorPosition(int elevatorPosition) {
        this.elevatorPosition = elevatorPosition;
    }

    public int getScrollableOccurs() {
        return scrollableOccurs;
    }

    public void setScrollableOccurs(int scrollableOccurs) {
        this.scrollableOccurs = scrollableOccurs;
    }
    
    
}
