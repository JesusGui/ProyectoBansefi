package mx.babel.bansefi.banksystem.base.enums;

/**
 * Enum utilizado para la parametrizacion de las opciones de busqueda por
 * usuario.
 * 
 * @author javier.martinnino
 * 
 */

public enum BusquedaEnum {

	PERSONA_CLIENTE("10","PERSONAS: FISICAS"),
	PERSONA_GRUPO("10","PERSONAS: GRUPOS"),
	PERSONA_MORAL("10","PERSONAS: MORALES"),
	PERSONA_GESTOR("10","PERSONAS: GESTORES"),
	CUENTAS("11","CUENTAS"),
	CENTROS("12","CENTROS"),
	ENTIDAD("13","ENTIDADES"),
	EMPLEADOS("14","EMPLEADOS"),
	ANOTACIONES_CLIENTES("21","ANOTACIONES: CLIENTES"),
	ANOTACIONES_CUENTAS("21","ANOTACIONES: CUENTAS"),
	DOMICILIOS_CLIENTE("22","DOMICILIOS: CLIENTES"),
	NOTIFICACIONES("27","NOTIFICACIONES"),
	SERVICIOS_RECIBOS("10", "SERVICIOS: RECIBOS NO DOMICILIADOS"),
	CONTABILIDAD_APUNTES("10", "CONTABILIDAD: APUNTES MANUALES");
	//TODO: cambiar el codigo de recibos por uno nuevo: 28

	private String busquedaClave;
	private String busquedaValor;

	/**
	 * Constructor.
	 * 
	 * @param paramFlash
	 * 
	 */
	private BusquedaEnum(final String busquedaClave, final String busquedaValor) {
		this.busquedaClave = busquedaClave;
		this.busquedaValor = busquedaValor;
	}

	/**
	 * Este método devuelve el parámetro de opcion de busqueda a consultar.
	 * 
	 * @return busquedaClave
	 */
	public String getBusquedaClave() {
		return busquedaClave;
	}

	/**
	 * Este método recibe el parámetro de opcion busqueda a guardar.
	 * 
	 * @param busquedaClave
	 *
	 */
	public void setBusquedaClave(final String busquedaClave) {
		this.busquedaClave = busquedaClave;
	}

	/**
	 * Este método devuelve el parámetro de opcion de busqueda a consultar.
	 * 
	 * @return busquedaValor
	 */
	public String getBusquedaValor() {
		return busquedaValor;
	}

	/**
	 * Este método recibe el parámetro de opcion busqueda a guardar.
	 * 
	 * @param busquedaValor
	 *
	 */
	public void setBusquedaValor(final String busquedaValor) {
		this.busquedaValor = busquedaValor;
	}

}