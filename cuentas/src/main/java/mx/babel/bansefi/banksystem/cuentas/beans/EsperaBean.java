package mx.babel.bansefi.banksystem.cuentas.beans;

import java.io.Serializable;
import java.util.Date;

public class EsperaBean implements Serializable{

	private static final long serialVersionUID = -7490060039528138717L;
	private String codTx;
	private int horaOperacion;
	private int fechaOperacion;
	private String terminal;
	private int secuencia;
	private String respuestaSincrona;
	
	//Respuesta
	private int fsIntervalo;
	private int fsIntentos;
	private String fsEstado;
	
	public String getCodTx() {
		return codTx;
	}
	public void setCodTx(String codTx) {
		this.codTx = codTx;
	}
	
	public int getHoraOperacion() {
		return horaOperacion;
	}
	public void setHoraOperacion(int horaOperacion) {
		this.horaOperacion = horaOperacion;
	}
	public int getFechaOperacion() {
		return fechaOperacion;
	}
	public void setFechaOperacion(int fechaOperacion) {
		this.fechaOperacion = fechaOperacion;
	}
	public String getTerminal() {
		return terminal;
	}
	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}
	public int getSecuencia() {
		return secuencia;
	}
	public void setSecuencia(int secuencia) {
		this.secuencia = secuencia;
	}
	public String getRespuestaSincrona() {
		return respuestaSincrona;
	}
	public void setRespuestaSincrona(String respuestaSincrona) {
		this.respuestaSincrona = respuestaSincrona;
	}
	public int getFsIntervalo() {
		return fsIntervalo;
	}
	public void setFsIntervalo(int fsIntervalo) {
		this.fsIntervalo = fsIntervalo;
	}
	public int getFsIntentos() {
		return fsIntentos;
	}
	public void setFsIntentos(int fsIntentos) {
		this.fsIntentos = fsIntentos;
	}
	public String getFsEstado() {
		return fsEstado;
	}
	public void setFsEstado(String fsEstado) {
		this.fsEstado = fsEstado;
	}
	
	
}
