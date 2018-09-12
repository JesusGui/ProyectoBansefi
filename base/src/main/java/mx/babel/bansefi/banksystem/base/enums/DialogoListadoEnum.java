package mx.babel.bansefi.banksystem.base.enums;

/**
 * Enum con detalles las vistas para los mensajes de guardado en listados.
 * @author mario.montesdeoca
 *
 */
public enum DialogoListadoEnum {
	SIN_CAMBIOS("info"),
	ALTA("exito"),
	MODIFICACION("exito"),
	ELIMINAR("exito"),
	ERROR("error"),
	ALERTA("warn"),
	CONFIRMA_ELIMINAR("");

	private String icono;
	private String mensaje;
	private Boolean mostrar;
	private String detalles;
	
	private DialogoListadoEnum(final String icono){
		this.icono = icono;
	}

	/**
	 * @return Atributo icono
	 */
	public String getIcono() {
		return icono;
	}

	/**
	 * @param icono Atributo icono a definir
	 */
	public void setIcono(String icono) {
		this.icono = icono;
	}

	/**
	 * @return Atributo mensaje
	 */
	public String getMensaje() {
		return mensaje;
	}

	/**
	 * @param mensaje Atributo mensaje a definir
	 */
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	/**
	 * @return Atributo mostrar
	 */
	public Boolean getMostrar() {
		return mostrar;
	}

	/**
	 * @param mostrar Atributo mostrar a definir
	 */
	public void setMostrar(Boolean mostrar) {
		this.mostrar = mostrar;
	}

	/**
	 * @return Atributo detalles
	 */
	public String getDetalles() {
		return detalles;
	}

	/**
	 * @param detalles Atributo detalles a definir
	 */
	public void setDetalles(String detalles) {
		this.detalles = detalles;
	}
	
}
