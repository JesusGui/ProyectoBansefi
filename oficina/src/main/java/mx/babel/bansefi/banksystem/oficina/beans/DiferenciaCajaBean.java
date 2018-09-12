package mx.babel.bansefi.banksystem.oficina.beans;

import java.io.Serializable;

public class DiferenciaCajaBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8313298386402384250L;
	
	private String fecha;
	private String destino;
	private String saldoContabilidad;
	private String arqueo;
	private String diferencias;
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getDestino() {
		return destino;
	}
	public void setDestino(String destino) {
		this.destino = destino;
	}
	public String getSaldoContabilidad() {
		return saldoContabilidad;
	}
	public void setSaldoContabilidad(String saldoContabilidad) {
		this.saldoContabilidad = saldoContabilidad;
	}
	public String getArqueo() {
		return arqueo;
	}
	public void setArqueo(String arqueo) {
		this.arqueo = arqueo;
	}
	public String getDiferencias() {
		return diferencias;
	}
	public void setDiferencias(String diferencias) {
		this.diferencias = diferencias;
	}
	
	
	
}
