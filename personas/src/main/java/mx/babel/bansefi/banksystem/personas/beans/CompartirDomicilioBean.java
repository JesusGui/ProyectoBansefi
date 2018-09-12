package mx.babel.bansefi.banksystem.personas.beans;

import java.io.Serializable;
import java.util.List;

import mx.babel.bansefi.banksystem.base.enums.TipoDomicilioEnum;

/**
 * Bean para la ejecucion de la TRn de Compartir domicilio de personas
 * @author manuel.nieto
 *
 */
public class CompartirDomicilioBean implements Serializable{

	private static final long serialVersionUID = 6539260249806111622L;
	private String idInternoPersona;
	private String idInternoPersona2;
	private String idInternoBien;
	private String numeroDireccion;
	private String codigoDireccion;
	private String indicaMasDirecciones;
	private String valorDireccionElectronica;
	private int longitudValorDireccionElectronica;
	private List<TipoDomicilioEnum> tipoDomicilio;
	
	public String getIdInternoPersona() {
		return idInternoPersona;
	}
	public void setIdInternoPersona(String idInternoPersona) {
		this.idInternoPersona = idInternoPersona;
	}
	public String getIdInternoPersona2() {
		return idInternoPersona2;
	}
	public void setIdInternoPersona2(String idInternoPersona2) {
		this.idInternoPersona2 = idInternoPersona2;
	}
	public String getIdInternoBien() {
		return idInternoBien;
	}
	public void setIdInternoBien(String idInternoBien) {
		this.idInternoBien = idInternoBien;
	}
	public String getNumeroDireccion() {
		return numeroDireccion;
	}
	public void setNumeroDireccion(String numeroDireccion) {
		this.numeroDireccion = numeroDireccion;
	}
	public String getCodigoDireccion() {
		return codigoDireccion;
	}
	public void setCodigoDireccion(String codigoDireccion) {
		this.codigoDireccion = codigoDireccion;
	}
	public String getIndicaMasDirecciones() {
		return indicaMasDirecciones;
	}
	public void setIndicaMasDirecciones(String indicaMasDirecciones) {
		this.indicaMasDirecciones = indicaMasDirecciones;
	}
	public String getValorDireccionElectronica() {
		return valorDireccionElectronica;
	}
	public void setValorDireccionElectronica(String valorDireccionElectronica) {
		this.valorDireccionElectronica = valorDireccionElectronica;
	}
	public int getLongitudValorDireccionElectronica() {
		return longitudValorDireccionElectronica;
	}
	public void setLongitudValorDireccionElectronica(
			int longitudValorDireccionElectronica) {
		this.longitudValorDireccionElectronica = longitudValorDireccionElectronica;
	}
	public List<TipoDomicilioEnum> getTipoDomicilio() {
		return tipoDomicilio;
	}
	public void setTipoDomicilio(List<TipoDomicilioEnum> tipoDomicilio) {
		this.tipoDomicilio = tipoDomicilio;
	}	
}
