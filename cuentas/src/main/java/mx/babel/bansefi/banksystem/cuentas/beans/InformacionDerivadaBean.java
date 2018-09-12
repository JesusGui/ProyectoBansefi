package mx.babel.bansefi.banksystem.cuentas.beans;

import java.io.Serializable;

public class InformacionDerivadaBean  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4443663304429064987L;
	private String informacionDerivada;
	private String valor;
	private String descripcionInformacion;
	private String tipo;
	
	/**
	 * Constructor
	 */
	public InformacionDerivadaBean(){
		super();
	}
	
	/**
	 * 
	 * @return informacionDerivada
	 */
	public String getInformacionDerivada() {
		return informacionDerivada;
	}
	
	/**
	 * Método que establece el tipo información derivada
	 * @param informacionDerivada 
	 */
	public void setInformacionDerivada(String informacionDerivada) {
		this.informacionDerivada = informacionDerivada;
	}
	
	/**
	 * 
	 * @return valor
	 */
	public String getValor() {
		return valor;
	}
	
	/**
	 * Método que establece el valor de la información derivada
	 * @param valor 
	 */
	public void setValor(String valor) {
		this.valor = valor;
	}

	/**
	 * 
	 * @return descripcionInformacion
	 */
	public String getDescripcionInformacion() {
		return descripcionInformacion;
	}

	/**
	 * Método que establece la descripción del tipo
	 * de información derivada
	 * @param descripcionInformacion
	 */
	public void setDescripcionInformacion(String descripcionInformacion) {
		this.descripcionInformacion = descripcionInformacion;
	}

	/**
	 * 
	 * @return tipo
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * Método que establece el tipo de dato al que 
	 * pertenece la informacion derivada de la cuenta
	 * @param tipo
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	

}
