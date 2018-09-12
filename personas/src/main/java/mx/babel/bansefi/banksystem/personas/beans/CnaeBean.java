package mx.babel.bansefi.banksystem.personas.beans;

/**
 * Clase Bean para CNAE.
 * @author alejandro.pineda
 *
 */
public class CnaeBean {
	
	private String entidad;
	
	private int idInterno;
	
	private String codCnae;
	
	private String descripcion;
	
	private String objSocial;
	
	public CnaeBean(){
		
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

	public String getCodCnae() {
		return codCnae;
	}

	public void setCodCnae(String codCnae) {
		this.codCnae = codCnae;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getObjSocial() {
		return objSocial;
	}

	public void setObjSocial(String objSocial) {
		this.objSocial = objSocial;
	}
	
	
	
}
