package mx.babel.arq.comun.controller;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


@ManagedBean(name = "calculadoraController")
@ViewScoped
public class CalculadoraController  implements Serializable {

	private static final long serialVersionUID = -4246884307356741848L;
	
	private String valorSalida = "0";
	private String acumulado = "";
	private String acumuladoCortado = "";
	private boolean operacionElegida = false;
	private String operadorClicado = "";
	double valorAnterior = 0;
	private boolean decimales = false;
	private boolean mostrarIcono = false;
	boolean finalized = false;
	double valorMemoria = 0.0;
	
	
	private static final String CALCULADORA = "calculadora/calculadora?faces-redirect=true";
	
	/**
	 * Devuelve el enlace de a la ventana de calculadora.
	 * @return - Valor de la url relativa a la calculadora
	 */
	public String getCalculadora() {
    	return CALCULADORA;
    }

	/**
	 * Función que limpia todos los valores y flags al valor por defecto.
	 */	     
	public void clearAction() {
		this.valorSalida = "0";
		this.acumulado = "";
		this.acumuladoCortado = "";
	    this.valorAnterior = 0;
	    this.operacionElegida = false;
	    this.operadorClicado = "";
	    this.decimales = false;
	    this.finalized = false;
	    this.valorMemoria = 0.0;
	    this.mostrarIcono = false;
	}
	    
	/**
	 * Función que guarda el tipo de operación que ha elegido el usuario.
	 * Si ya se hubiera introducido una operación, la realiza
	 * @param operador - Valor del operador clicado
	 */
	public void operadorClicado(String operador) {
		if (("Ra").equals(operador)) {
			this.operadorClicado = operador;
			this.acumulado = this.acumulado + "sqtr(" + this.valorSalida + ")";
			if(this.acumulado.length() > 15){
				this.acumulado = this.acumulado.substring(this.acumulado.length() - 15, this.acumulado.length());
				this.mostrarIcono = true;
			}
			this.acumuladoCortado = this.acumulado;
			this.igualOperadorClicado(false);
			
		} else {
			if (("").equals(this.valorSalida)) {
				this.valorSalida = "0";
			}
			this.acumulado = this.acumulado + this.valorSalida + operador;
			if(this.acumulado.length() > 15){
				this.acumulado = this.acumulado.substring(this.acumulado.length() - 15, this.acumulado.length());
				this.mostrarIcono = true;
			}
			this.acumuladoCortado = this.acumulado;
			if (!this.operacionElegida) {    	
		    	this.operacionElegida = true;
		    	this.finalized = true;
		    } else {
		    	this.igualOperadorClicado(false);
		    }
		    this.decimales = false;
		    this.operadorClicado = operador;
		}
	    
	}

	/**
	 * Según el operador seleccionado, realiza la operación correspondiente e iguala el calor del 
	 * input al resultado.
	 */
	public void igualOperadorClicado( boolean botonIgual) {
		
	   double valorActual = Double.parseDouble(this.valorSalida);
	   double respuesta = 0;
	   
	   if (("+").equals(this.operadorClicado)) {
	        respuesta = valorActual + this.valorAnterior;
	   } else if (("-").equals(this.operadorClicado)) {
	        respuesta = this.valorAnterior - valorActual;
	   } else if (("*").equals(this.operadorClicado)) {
	        respuesta = this.valorAnterior * valorActual;
	   } else if (("/").equals(this.operadorClicado)) {
	        respuesta = this.valorAnterior / valorActual;
	   } else if (("Ra").equals(this.operadorClicado)) {
		   respuesta = Math.sqrt(valorActual);
		   this.operacionElegida = false;
	   } else if(("%").equals(this.operadorClicado)) {
		   respuesta = (this.valorAnterior * 100) / valorActual;
	   }
	   
	   
	   this.operadorClicado = "";
	   this.valorSalida = "" + respuesta;
	   this.decimales = false;
	   this.finalized = true;
	   if(botonIgual) {
		   this.acumulado="";
		   this.acumuladoCortado="";
		   this.operacionElegida = false;
		   this.mostrarIcono = false;
	   }
	}

	/**
	 * Función que acumula en la variable @valorSalida el numero seleccionado. 
	 * @param valor - Valor del numero introducido
	 */
	public void numeroClicado(String valor) {

		if(this.finalized) {
			this.valorAnterior = Double.parseDouble(this.valorSalida);
			this.valorSalida = "0";
			this.finalized = false;
		}
		
		if("0".equals(this.valorSalida)){
			this.valorSalida = "";
		}
		
		this.valorSalida = appendNumber(valor, this.valorSalida);
	    
	}
	
	/**
	 * Comprueba si se ha introducido un décimal antes y en caso contrario lo introduce. 
	 * 
	 */
	public void decimalClicado() {
		if (!this.decimales) {
	    	this.decimales = true;
		    this.valorSalida = appendNumber(".", this.valorSalida);	    	
	    }
	}
	
	/**
	 * 	Función que borra el último carácter introducido.
	 */
	public void borrarCaracter() {
		if (this.valorSalida.endsWith(".")) {
			this.decimales = false;
		}
		if (!("").equals(this.valorSalida)) {
			this.valorSalida = this.valorSalida.substring(0, this.valorSalida.length() - 1);
			if("".equals(this.valorSalida)){
				this.valorSalida = "0";
			}
		}
	}
	
	
	/**
	 * Funciones de uso de la memoria de la calculadora
	 */
	public void guardarMemoria() {
		this.valorMemoria = Double.parseDouble(this.valorSalida);
		this.finalized = true;
	}
	
	public void sumarMemoria() {
		this.valorMemoria = this.valorMemoria + Double.parseDouble(this.valorSalida);
	}
	
	public void restarMemoria() {
		this.valorMemoria = this.valorMemoria - Double.parseDouble(this.valorSalida);
	}
	
	public void mostrarMemoria() {
		this.valorSalida = String.valueOf(this.valorMemoria);
	}
	
	public void limpiarMemoria() {
		this.valorMemoria = 0.0;
	}
	    
	/**
	 * Función que concatena el número introducido con el valor que teníamos guardado
	 * @param number - Valor del nuevo número
	 * @param outPut - Valor acumulado	
	 * @return
	 */
	private String appendNumber(String number, String outPut) {
		String trim = outPut.trim();
	    StringBuilder builder = new StringBuilder(trim);
	    builder.append(number);
	    return builder.toString();
	}

	/*
	 GETTER & SETTER
	*/
	public String getvalorSalida() {
	   return this.valorSalida;
	}

	public void setvalorSalida(String valorSalida) {
	   this.valorSalida = valorSalida;
	}

	public String getAcumulado() {
		return acumulado;
	}

	public void setAcumulado(String acumulado) {
		this.acumulado = acumulado;
	}

	public String getAcumuladoCortado() {
		return acumuladoCortado;
	}

	public void setAcumuladoCortado(String acumuladoCortado) {
		this.acumuladoCortado = acumuladoCortado;
	}

	public boolean isMostrarIcono() {
		return mostrarIcono;
	}

	public void setMostrarIcono(boolean mostrarIcono) {
		this.mostrarIcono = mostrarIcono;
	}
	

}
