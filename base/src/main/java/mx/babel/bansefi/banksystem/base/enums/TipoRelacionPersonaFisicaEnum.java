package mx.babel.bansefi.banksystem.base.enums;

/**
 * Clase que enlista las relaciones que puede tener una persona física.
 * 
 * @author omar.marquez
 * 
 */
public enum TipoRelacionPersonaFisicaEnum {
	
	PERSONA(1, "Persona"),
	GRUPO(2, "Grupo");
	// TODO Descomentar está linea cuando funcionen las TRN's de gestores.
	// GESTOR(3, "Gestor");

	private int posicion;
	private String nombre;
	
	/**
	 * Constructor.
	 */
	private TipoRelacionPersonaFisicaEnum(int posicion, String nombre) {
		this.posicion = posicion;
		this.nombre = nombre;
	}

	/**
	 * Método que devuelve la posición del enumerado.
	 * 
	 * @return posicion
	 */
	public int getPosicion() {
		return posicion;
	}

	/**
	 * Método que establece la posición de un enumerado.
	 * 
	 * @param posicion
	 */
	public void setPosicion(int posicion) {
		this.posicion = posicion;
	}

	/**
	 * Método que devuelve el nombre del enumerado.
	 * 
	 * @return nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Método que establece el nombre del enumerado.
	 * 
	 * @param nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}