package mx.babel.arq.comun.exceptions;

/**
 * Excepcion de error en la invocacion a los webservices.
 * @author eliot.vasquez
 *
 */
public class NoControlableException extends RuntimeException{
	/**
	 * Id version..
	 */
	private static final long serialVersionUID = 7350672700696484590L;

	/**
	 * Mensaje para el usuario.
	 */
	private final String mensajeUsuario;
	/**
	 * Detalle de la excepcion.
	 */	
	private final String mensajeDetalle;
	/**
	 * Código de error para manejo de excepciones
	 */
	private final int rtncod;
	/**
	 * Constructor.
	 */
	public NoControlableException(){
		super();
		this.mensajeUsuario = "";
		this.mensajeDetalle = "";
		this.rtncod = -1;
	}
	
	
	
	/**
	 * Constructor.
	 * @param mensajeUsuario mensaje para el usuario
	 * @param mensajeDetalle mensaje de detalle para debug
	 */
	public NoControlableException(String mensajeUsuario, String mensajeDetalle) {
		super(mensajeUsuario + " " + mensajeDetalle, new Throwable(mensajeDetalle));
		this.mensajeUsuario = mensajeUsuario;
		this.mensajeDetalle = mensajeDetalle;
		this.rtncod = -1;
	}
	/**
	 * Contructor.
	 * @param mensajeUsuario
	 * @param mensajeDetalle
	 * @param rtnCod
	 */
	public NoControlableException(String mensajeUsuario, String mensajeDetalle, int rtncod) {
		super(mensajeUsuario + " " + mensajeDetalle, new Throwable(mensajeDetalle));
		this.mensajeUsuario = mensajeUsuario;
		this.mensajeDetalle = mensajeDetalle;
		this.rtncod = rtncod;
	}
	/**
	 * Constructor.
	 * @param mensajeUsuario
	 * @param causa
	 */
	public NoControlableException(String mensajeUsuario, Throwable causa) {
        super(mensajeUsuario,causa);
        this.mensajeUsuario = mensajeUsuario;
        if(causa != null && !"".equals(causa.getMessage())){
            this.mensajeDetalle = causa.getMessage();
        }else{
            this.mensajeDetalle = "No se ha podido establecer un detalle del origen del error";
        }
        this.rtncod = -1;
    }
	


	/**
	 * retorna mensaje usuario.
	 * @return mensajeUsuario
	 */
	public String getMensajeUsuario() {
		return mensajeUsuario;
	}
	/**
	 * Retorna mensaje usuario.
	 * @return mensajeDetalle
	 */
	public String getMensajeDetalle() {
		return mensajeDetalle;
	}
	/**
	 * Retorna codigo de retorno.
	 * @return
	 */
	public int getRtncod() {
		return rtncod;
	}
}
