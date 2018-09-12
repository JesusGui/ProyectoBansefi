package mx.babel.bansefi.banksystem.administracion.beans.centro;

import java.io.Serializable;

import mx.babel.bansefi.banksystem.base.beans.domain.DomicilioTipoBean;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Bean para la ventana de Alta y Modificación del centro.
 * @author alejandro.pineda
 *
 */
public class AltaCentroBean implements Serializable {
	
	private static final long serialVersionUID = -9213173965752723093L;
	
	private String centro;
	private String numero;
	private String nombre;
	private String modalidad;
	
	private String plazaBancaria;
	private String localidad1;
	
	
	private String desgCtble;
	private boolean controlador;
	private String aplicacion;
	private int codDomicilio;
	private int idInterno;
    private DomicilioTipoBean domicilio;
    
    
	public AltaCentroBean(){
		
	}
	
	public String getCentro() {
		return centro;
	}

	public void setCentro(String centro) {
		this.centro = centro;
	}

	public String getNumero() {
		return numero;
	}
	
	public void setNumero(String numero) {
		this.numero = numero;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getModalidad() {
		return modalidad;
	}
	
	public void setModalidad(String modalidad) {
		this.modalidad = modalidad;
	}
	
	public String getPlazaBancaria() {
		return plazaBancaria;
	}
	
	public void setPlazaBancaria(String plazaBancaria) {
		this.plazaBancaria = plazaBancaria;
	}
	
	public String getLocalidad1() {
		return localidad1;
	}
	
	public void setLocalidad1(String localidad1) {
		this.localidad1 = localidad1;
	}
	
	public String getDesgCtble() {
		return desgCtble;
	}
	
	public void setDesgCtble(String desgCtble) {
		this.desgCtble = desgCtble;
	}
	
	public void setControlador(boolean controlador) {
		this.controlador = controlador;
	}
	
	public boolean isControlador() {
		return controlador;
	}

	public String getAplicacion() {
		return aplicacion;
	}

	public void setAplicacion(String aplicacion) {
		this.aplicacion = aplicacion;
	}
	
	public DomicilioTipoBean getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(DomicilioTipoBean domicilio) {
        this.domicilio = domicilio;
    }

    public int getCodDomicilio() {
        return codDomicilio;
    }

    public void setCodDomicilio(int codDomicilio) {
        this.codDomicilio = codDomicilio;
    }
    
    public int getIdInterno() {
		return idInterno;
	}

	public void setIdInterno(int idInterno) {
		this.idInterno = idInterno;
	}

	@Override
    public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj,
				new String[] { "serialVersionUID" });
	}
    
    @Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this,
				new String[] { "serialVersionUID" });
	}
}
