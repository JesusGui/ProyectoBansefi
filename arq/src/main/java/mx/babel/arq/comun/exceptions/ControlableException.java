package mx.babel.arq.comun.exceptions;

/**
 * Ecepcion controlada en la invocacion de los web services.
 * @author gerard.chavez
 *
 */
public class ControlableException extends RuntimeException{

	/**
	 * id.
	 */
	private static final long serialVersionUID = 7650803773003331951L;

	/**
	 * Mensaje para el usuario.
	 */
	private final String mensajeUsuario;
	/**
	 * Detalle de la excepcion.
	 */
	/**
	 * Mensaje de detalle para posible debug
	 */
	private final String mensajeDetalle;
	/**
	 * Código de error para manejo de excepciones
	 */
	private final int rtncod;
	/**
	 * Contructor.
	 */
	public ControlableException(){
		super();
		this.mensajeUsuario = "";
		this.mensajeDetalle = "";
		this.rtncod = -1;
	}
	
	/**
	 * Contructor.
	 * @param mensajeUsuario
	 * @param mensajeDetalle
	 */
	public ControlableException(String mensajeUsuario, String mensajeDetalle) {
		super(mensajeUsuario, new Throwable(mensajeDetalle));
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
	public ControlableException(String mensajeUsuario, String mensajeDetalle, int rtncod) {
		super(mensajeUsuario, new Throwable(mensajeDetalle));
		this.mensajeUsuario = mensajeUsuario;
		this.mensajeDetalle = mensajeDetalle;
		this.rtncod = rtncod;
	}
	
	public ControlableException(String mensajeUsuario, Throwable causa) {
        super(causa);
        this.mensajeUsuario = mensajeUsuario;
        if(causa != null && "".equals(causa.getMessage())){
            this.mensajeDetalle = causa.getMessage();
        }else{
            this.mensajeDetalle = "No se ha podido establecer un detalle del origen del error";
        }
        this.rtncod = -1;
    }



	/**
	 * retorna mensaje usuario.
	 * @return
	 */
	public String getMensajeUsuario() {
		return mensajeUsuario;
	}
	/**
	 * Retorna mensaje usuario.
	 * @return
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
