package mx.babel.bansefi.banksystem.personas.beans;

/**
 * Clase Bean para Mercados Organizados.
 * @author alejandro.pineda
 *
 */
public class MercadoOrganizadoBean {
	
	private String entidad;
	
	private int idInterno;
	
	private String codigoMercado;
	
	private int fechaIniCrt;
	
	public MercadoOrganizadoBean(){
		
	}

	public String getEntidad() {
		return entidad;
	}

	public void setEntidad(String entidad) {
		this.entidad = entidad;
	}

	public int getIdInterno() {
		return idInterno;
	}

	public void setIdInterno(int idInterno) {
		this.idInterno = idInterno;
	}

	public String getCodigoMercado() {
		return codigoMercado;
	}

	public void setCodigoMercado(String codigoMercado) {
		this.codigoMercado = codigoMercado;
	}

	public int getFechaIniCrt() {
		return fechaIniCrt;
	}

	public void setFechaIniCrt(int fechaIniCrt) {
		this.fechaIniCrt = fechaIniCrt;
	}
	
}
