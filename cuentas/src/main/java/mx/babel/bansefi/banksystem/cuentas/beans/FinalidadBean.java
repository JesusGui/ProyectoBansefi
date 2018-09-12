package mx.babel.bansefi.banksystem.cuentas.beans;

import java.io.Serializable;

public class FinalidadBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 714126058569967361L;
	private String codigoFinalidad;
	private String descCodigoFinalidad;
		
	public FinalidadBean(){
		super();
	}
	
	/**
	 * Getters & Setters
	 * @return codigoFinalidad
	 */
	public String getCodigoFinalidad(){
		return codigoFinalidad;
	}
	
	/**
	 * Getters & Setters
	 * @param codigoFinalidad
	 */
	public void setCodigoFinalidad(String codigoFinalidad){
		this.codigoFinalidad = codigoFinalidad;
	}
	
	/**
	 * Getters & Setters
	 * @return descCodigoFinalidad
	 */
	public String getDescCodigoFinalidad(){
		return descCodigoFinalidad;
	}
	
	/**
	 * Getters & Setters.
	 * @param descCodigoFinalidad
	 */
	public void setDescCodigoFinalidad(String descCodigoFinalidad){
		this.descCodigoFinalidad = descCodigoFinalidad;
	}
	


}
