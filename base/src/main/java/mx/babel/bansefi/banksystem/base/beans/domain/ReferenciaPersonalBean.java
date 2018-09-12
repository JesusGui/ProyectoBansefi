package mx.babel.bansefi.banksystem.base.beans.domain;

import java.io.Serializable;

import mx.babel.bansefi.banksystem.base.enums.EstadoListadosEnum;

/**
 * Clase para definir el modelo de datos de una referencia personal de persona de riesgo.
 * @author javier.martinnino
 * 
 */
public class ReferenciaPersonalBean implements Serializable{

	private static final long serialVersionUID = -3640088528743963575L;

	private Integer numeroSecuencia;
	
	private String nombre;
	
	private String apellidoPaterno;
	
	private String apellidoMaterno;
	
	private String telefono;
	
	private String domicilio;
	
	private String relacionParentesco;
	
	private EstadoListadosEnum estado;
    
    private String respaldo;
	
	/**
	 * @return the numeroSecuencia
	 */
	public Integer getNumeroSecuencia() {
		return numeroSecuencia;
	}

	/**
	 * @param numeroSecuencia the numeroSecuencia to set
	 */
	public void setNumeroSecuencia(Integer numeroSecuencia) {
		this.numeroSecuencia = numeroSecuencia;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the apellidoPaterno
	 */
	public String getApellidoPaterno() {
		return apellidoPaterno;
	}

	/**
	 * @param apellidoPaterno the apellidoPaterno to set
	 */
	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}

	/**
	 * @return the apellidoMaterno
	 */
	public String getApellidoMaterno() {
		return apellidoMaterno;
	}

	/**
	 * @param apellidoMaterno the apellidoMaterno to set
	 */
	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}

	/**
	 * @return the telefono
	 */
	public String getTelefono() {
		return telefono;
	}

	/**
	 * @param telefono the telefono to set
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	/**
	 * @return the domicilio
	 */
	public String getDomicilio() {
		return domicilio;
	}

	/**
	 * @param domicilio the domicilio to set
	 */
	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	/**
	 * @return the relacionParentesco
	 */
	public String getRelacionParentesco() {
		return relacionParentesco;
	}

	/**
	 * @param relacionParentesco the relacionParentesco to set
	 */
	public void setRelacionParentesco(String relacionParentesco) {
		this.relacionParentesco = relacionParentesco;
	}
	
	/**
	 * @return the estado
	 */
	public EstadoListadosEnum getEstado() {
        return estado;
    }
	
	/**
	 * @param estado the estado to set
	 */
    public void setEstado(EstadoListadosEnum estado) {
        this.estado = estado;
    }
	
    /**
	 * @return the respaldo
	 */
    public String getRespaldo() {
        return respaldo;
    }

    /**
	 * @param respaldo the respaldo to set
	 */
    public void setRespaldo(String respaldo) {
        this.respaldo = respaldo;
    }
}
