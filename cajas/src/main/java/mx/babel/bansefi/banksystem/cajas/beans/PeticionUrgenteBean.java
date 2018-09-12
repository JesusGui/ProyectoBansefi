package mx.babel.bansefi.banksystem.cajas.beans;

import java.io.Serializable;

public class PeticionUrgenteBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2831324298302837759L;
	
	private int indicadorUrgente;
	private int intFechasolicitud;
	private String descripcionL;
	private String codigoDistribucion;
	private String codigo;
	
	public String getDescripcionL() {
		return descripcionL;
	}
	public void setDescripcionL(String descripcionL) {
		this.descripcionL = descripcionL;
	}
	public int getIndicadorUrgente() {
		return indicadorUrgente;
	}
	public void setIndicadorUrgente(int indicadorUrgente) {
		this.indicadorUrgente = indicadorUrgente;
	}
	public int getIntFechasolicitud() {
		return intFechasolicitud;
	}
	public void setIntFechasolicitud(int intFechasolicitud) {
		this.intFechasolicitud = intFechasolicitud;
	}
	public String getCodigoDistribucion() {
		return codigoDistribucion;
	}
	public void setCodigoDistribucion(String codigoDistribucion) {
		this.codigoDistribucion = codigoDistribucion;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

}
