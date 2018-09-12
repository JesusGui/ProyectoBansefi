package mx.babel.bansefi.banksystem.base.beans.domain.relacioncuenta;

/**
 * @author mario.montesdeoca
 *
 */
public enum TipoRelacionPersonaCuenta {
	BENEFICIARIO("Beneficiario","06",1,1),
	
	AVAL("Aval","03",10,2),
	AVAL_NO_SOLIDARIO("Aval no solidario","04",11,2),
	HIPOTECANTE_NO_DEUDOR("Hipotecante no deudor","18",12,2),
	AVALISTA_SUBSIDIARIO("Avalista Subsidiario","19",13,2),
	
	TITULAR("Titular","01",30,3),
	AUTORIZADO("Autorizado","02",31,3),
	APODERADO("Apoderado","05",32,3),
	NOTARIO("Notario","07",33,3),
	CORREDOR_COMERCIO("Corredor de comercio","08",34,3),
	REPRESENTANTE_LEGAL("Representante legal","15",35,3),
	TUTOR("Tutor","16",36,3),
	PRESENTADOR("Presentador","22",37,3),
	USUFRACTARIO("Usufractario","24",38,3),
	ENTIDAD_DEPOSITARIA("Entidad depositaria","25",39,3),
	APODERADO_DEL_AVAL("Apoderado del Aval","28",40,3),
	APODERADO_DEL_AVAL_NO_SOLIDARIO("Apoderado del aval no solidario","29",41,3),
	APODERADO_DEL_APODERADO("Apoderado del apoderado","30",42,3),
	APODERADO_DEL_BENEFICIARIO("Apoderado del beneficiario","31",43,3),
	
	APODERADO_DEL_HIPOTECANTE("Apoderado hipotecante no deudor","34",42,3),
	APODERADO_DEL_AVALISTA_SUBSIDIARIO("Apoderado del avalista subsidiario","35",43,3);
	
	private String nombre;
	private String codigo;
	private Integer posicion;
	private Integer tipo;
	
	/**
	 * Constructor.
	 * @param nombre
	 * @param posicion 
	 */
	private TipoRelacionPersonaCuenta(String nombre, String codigo, Integer posicion, Integer tipo){
		this.nombre = nombre;
		this.codigo = codigo;
		this.posicion = posicion;
		this.tipo = tipo;
	}
	
	/**
	 * Este método devuelve el nombre de la relación.
	 * @return ruta
	 */
	public String getNombre() {
		return nombre;
	}
	
	/**
	 * Este método devuelve el nombre de la relación.
	 * @param ruta .
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return Atributo codigo
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo Atributo codigo a definir
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	/**
	 * @return Atributo posicion
	 */
	public Integer getPosicion() {
		return posicion;
	}

	/**
	 * @param posicion Atributo posicion a definir
	 */
	public void setPosicion(Integer posicion) {
		this.posicion = posicion;
	}
	
	/**
	 * @return Atributo tipo
	 */
	public Integer getTipo() {
		return tipo;
	}

	/**
	 * @param tipo Atributo tipo a definir
	 */
	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

	/**
	 * Método para obtener un tipo de relacion persona-cuenta por su código.
	 * @param codigo
	 * @return
	 */
	public static TipoRelacionPersonaCuenta codigoDe(String codigo) {
	    for (TipoRelacionPersonaCuenta e : TipoRelacionPersonaCuenta.values()) {
	        if (e.getCodigo().equals(codigo)) {
	            return e;
	        }
	    }
	    return null;
	}
}
