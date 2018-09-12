package mx.babel.bansefi.banksystem.oficina.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Bean con detalles de los traspasos entre puestos
 * @author mario.montesdeoca
 *
 */
public class TraspasoBean implements Serializable{

	private static final long serialVersionUID = 1L;

	private Date fecha;
	private String hora;
	private String origen;
	private String destino;
	private String usuarioOrigen;
	private String usuarioDestino;
	private BigDecimal importe;
	/**
	 * @return Atributo fecha
	 */
	public Date getFecha() {
		return fecha;
	}
	/**
	 * @param fecha Atributo fecha a definir
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	/**
	 * @return Atributo hora
	 */
	public String getHora() {
		return hora;
	}
	/**
	 * @param hora Atributo hora a definir
	 */
	public void setHora(String hora) {
		this.hora = hora;
	}
	/**
	 * @return Atributo origen
	 */
	public String getOrigen() {
		return origen;
	}
	/**
	 * @param origen Atributo origen a definir
	 */
	public void setOrigen(String origen) {
		this.origen = origen;
	}
	/**
	 * @return Atributo destino
	 */
	public String getDestino() {
		return destino;
	}
	/**
	 * @param destino Atributo destino a definir
	 */
	public void setDestino(String destino) {
		this.destino = destino;
	}
	/**
	 * @return Atributo usuarioOrigen
	 */
	public String getUsuarioOrigen() {
		return usuarioOrigen;
	}
	/**
	 * @param usuarioOrigen Atributo usuarioOrigen a definir
	 */
	public void setUsuarioOrigen(String usuarioOrigen) {
		this.usuarioOrigen = usuarioOrigen;
	}
	/**
	 * @return Atributo usuarioDestino
	 */
	public String getUsuarioDestino() {
		return usuarioDestino;
	}
	/**
	 * @param usuarioDestino Atributo usuarioDestino a definir
	 */
	public void setUsuarioDestino(String usuarioDestino) {
		this.usuarioDestino = usuarioDestino;
	}
	/**
	 * @return Atributo importe
	 */
	public BigDecimal getImporte() {
		return importe;
	}
	/**
	 * @param importe Atributo importe a definir
	 */
	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}
	
	public String getHoraFormateada(){
		String resultado = this.hora;
		while(resultado.length() < 6){
			resultado = "0"+ resultado;
		}
		return resultado.substring(0,2) + ":" + resultado.substring(2,4)+ ":" + resultado.substring(4);
	}
	
}
