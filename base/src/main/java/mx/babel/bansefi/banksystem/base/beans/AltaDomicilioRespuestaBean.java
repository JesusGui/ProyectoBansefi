package mx.babel.bansefi.banksystem.base.beans;

/**
 * Class simple para la respuesta a la alta de domicilios
 * @author manuel.nieto
 *
 */
public class AltaDomicilioRespuestaBean {
	
	private String numeroDireccion;
	private String codigoEntidad;
	public String getNumeroDireccion() {
		return numeroDireccion;
	}
	public void setNumeroDireccion(String numeroDireccion) {
		this.numeroDireccion = numeroDireccion;
	}
	public String getCodigoEntidad() {
		return codigoEntidad;
	}
	public void setCodigoEntidad(String codigoEntidad) {
		this.codigoEntidad = codigoEntidad;
	}
}
