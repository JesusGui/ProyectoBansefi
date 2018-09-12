package mx.babel.bansefi.banksystem.base.enums;

import java.io.Serializable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Clase enum para trabajar los tipos de domicilio desde el catalogo
 * correspondiente
 * 
 * @author manuel.nieto
 * 
 */
public enum TipoDomicilioEnum implements Serializable {
	DOMICILIO_HABITUAL("HABITUAL"), 
	DOMICILIO_FISCAL("FISCAL"), 
	DOMICILIO_SOCIAL("SOCIAL"), 
	DOMICILIO_POSTAL("POSTAL"), 
	DOMICILIO_DELBIEN("DEL BIEN"), 
	DOMICILIO_ACUERDO("ACUERDO"), 
	DIRECCION_ELECTRONICO("ELECTRONICO"), 
	DIRECCION_REGISTRAL("REGISTRAL");

	private String tipoDomicilio;
	private String clave;

	private static final Logger LOGGER = LogManager.getLogger(TipoDomicilioEnum.class);
	
	/**
	 * Constructor del Enum de Tipo de Domicilio
	 * 
	 * @param clave
	 */
	private TipoDomicilioEnum(final String tipoDomicilio) {
		this.tipoDomicilio = tipoDomicilio;
	}
	
	/**
	 * Metodo que obtiene el nombre del tipo de domicilio
	 * 
	 * @return
	 */
	public String getTipoDomicilio() {
		return tipoDomicilio;
	}

	/**
	 * Metodo que asigna el tipo de domicilio
	 * 
	 * @param tipoDomicilio
	 */
	public void setTipoDomicilio(String tipoDomicilio) {
		this.tipoDomicilio = tipoDomicilio;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}	

}
