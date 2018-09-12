package mx.babel.bansefi.banksystem.base.enums;

/**
 * Enum para manejar los tipos de cuentas/acuerdos
 * Con su codigo de linea y grupo
 * @author manuel.nieto
 *
 */
public enum CuentaEnum {
	CREDITO_REVOLVENTE("CREDITO REVOLVENTE", "01", "41", false),
	CREDITO_SIMPLE("CREDITO SIMPLE", "01", "71", false),
	VISTA("CUENTA VISTA", "03", "11", false),
	MEDIO_PAGO("CUENTA DE MEDIO DE PAGO", "05", "50", true),
	PLAZO("CUENTA DE PLAZO", "03", "51", true),
	CREDITO_NO_BANSEFI("CREDITO", "01", "50", false),
	INTERVENCION("CUENTA DE INTERVENCION", "03", "17", true),
	MUTUA_AJENA("CUENTA MUTUA AJENA", "03", "81", true),
	PAGO_SERVCIOS("PAGO DE SERVICIOS", "05", "11", true),
	TELMEX("TELMEX", "05", "13", true),
	REMESAS_INTERNACIONALES("REMESAS INTERNACIONALES","05","43",true ) ;
	
	private CuentaEnum(String nombre, String linea, String grupo, boolean razonCancelacion){
		this.nombre = nombre;
		this.linea = linea;
		this.grupo = grupo;
		this.razonCancelacion = razonCancelacion;
	}
	
	private String nombre;
	private String linea;
	private String grupo;
	private boolean razonCancelacion;
	
	public boolean isRazonCancelacion() {
		return razonCancelacion;
	}
	public void setRazonCancelacion(boolean razonCancelacion) {
		this.razonCancelacion = razonCancelacion;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getLinea() {
		return linea;
	}
	public void setLinea(String linea) {
		this.linea = linea;
	}
	public String getGrupo() {
		return grupo;
	}
	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}
	
	/**
	 * Funcion que de acuerdo a una linea y grupo devuelve el Enum correspondiente;
	 * @param linea
	 * @param grupo
	 * @return
	 */
	public static CuentaEnum getTipoCuenta(String linea, String grupo){
		for(CuentaEnum cuentaEnum: CuentaEnum.values()){
			if(cuentaEnum.getLinea().equals(linea) && cuentaEnum.getGrupo().equals(grupo)){
				return cuentaEnum;
			}		
		}
		return null;
	}
	
	
}
