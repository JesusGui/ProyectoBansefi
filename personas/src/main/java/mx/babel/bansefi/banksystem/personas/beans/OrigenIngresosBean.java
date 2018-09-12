package mx.babel.bansefi.banksystem.personas.beans;

/**
 * Bean de Origen de Ingresos para la ventana de Datos Económicos.
 * @author alejandro.pineda
 *
 */
public class OrigenIngresosBean {

	private int idInterno;
	
	private String entidad;
	
	private String codOrigenIngresos;
	
	public OrigenIngresosBean(){
		
	}

	public int getIdInterno() {
		return idInterno;
	}

	public void setIdInterno(int idInterno) {
		this.idInterno = idInterno;
	}

	public String getEntidad() {
		return entidad;
	}

	public void setEntidad(String entidad) {
		this.entidad = entidad;
	}

	public String getCodOrigenIngresos() {
		return codOrigenIngresos;
	}

	public void setCodOrigenIngresos(String codOrigenIngresos) {
		this.codOrigenIngresos = codOrigenIngresos;
	}
	
}
