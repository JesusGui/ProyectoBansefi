package mx.babel.bansefi.banksystem.base.beans.domain.relacioncuenta;

public enum TipoAltaEnum {

	NUEVA("Nueva"),
	RENOVACION("Renovación"),
	ROBO_EXTRAVIO("Robo / Extravío"),
	REPOSICION("Reposición");
	
	private String nombre;
	
	/**
	 * Constructor.
	 * @param nombre
	 */
	private TipoAltaEnum(String nombre){
		this.nombre = nombre;
	}

	/**
	 * @return Atributo nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre Atributo nombre a definir
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
