package mx.babel.bansefi.banksystem.cuentas.enums;

/**
 * Enum con detalles las vistas para los mensajes de guardado en listados.
 * @author mario.montesdeoca
 *
 */
public enum DialogoRelacionesCuentaListadoEnum {
	SIN_CAMBIOS("info"),
	ALTA("exito"),
	MODIFICACION("exito"),
	ELIMINAR("exito"),
	ERROR("error"),
	ALERTA("warn"),
	CONFIRMA_ELIMINAR("");

	private String icono;
	
	private DialogoRelacionesCuentaListadoEnum(final String icono){
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

}
