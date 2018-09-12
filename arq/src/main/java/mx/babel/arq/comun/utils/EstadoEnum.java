package mx.babel.arq.comun.utils;

import java.util.Map;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;

/**Enumerado que engloba las respuesta de los webservice.
 * @author joseluis.pena
 *
 */
public enum EstadoEnum {
	ERROR("0"), OK("1"), NO_DATA("2");

	private String status;

	private EstadoEnum(final String status) {
		this.status = status;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}


	/** Revisa si el resultado a sido OK y
	 * la llamada devuelve un 0 como estado.
	 * @param result del webservice
	 * @return if result == "0"
	 */
	public static boolean isOK(final String result) {
		return result != null && EstadoEnum.OK.getStatus().equals(result);
	}

	/** Revisa si el resultado a sido Error y
	 * la llamada devuelve un 1 como estado.
	 * @param result del webservice
	 * @return if result == "1"
	 */
	public static boolean isError(final String result) {
		return result != null && EstadoEnum.ERROR.getStatus().equals(result);
	}

	/** Revisa si el resultado a sido OK pero no ha devuelto datos y
	 * la llamada devuelve un 2 como estado.
	 * @param result del webservice
	 * @return if result == "2"
	 */
	public static boolean isNoData(final String result) {
		return result != null && EstadoEnum.NO_DATA.getStatus().equals(result);
	}

	/**
	 * Método para regresar el mensaje a mostrar al usuario en caso de error.
	 *
	 * @param tipo
	 *            Tipo del error, si es de WS o TRN.
	 * @param cod
	 *            Código del error.
	 * @return String Mensaje de error.
	 */
	public static String mensajesError(final String tipo, final int cod) throws NoControlableException, ControlableException {
		final String mensajeUsuario = ProveedorMensajeSpringUtils.getValoresMensajesError("error."
				+ tipo + "." + cod + ".mensaje.usuario");
		final String mensajeDetalle = ProveedorMensajeSpringUtils.getValoresMensajesError("error."
				+ tipo + "." + cod + ".mensaje.detalle");
		switch (cod) {
		case 0:
			throw new ControlableException(mensajeUsuario, mensajeDetalle);
		case 2:
			throw new ControlableException(mensajeUsuario, mensajeDetalle);
		case 3:
			throw new ControlableException(mensajeUsuario, mensajeDetalle);
		case 4:
			throw new NoControlableException(mensajeUsuario, mensajeDetalle);
		case 7:

			break;
		case 8:

			break;
		case 9:

			break;
		case 10:

			break;
		case 11:
			throw new NoControlableException(mensajeUsuario, mensajeDetalle);
		case 12:
			throw new ControlableException(mensajeUsuario, mensajeDetalle);
		case 13:
			throw new NoControlableException(mensajeUsuario, mensajeDetalle);
		case 14:

			break;
		case 15:

			break;
		case 16:

			break;
		case 17:
			throw new ControlableException(mensajeUsuario, mensajeDetalle);
		case 18:
			throw new ControlableException(mensajeUsuario, mensajeDetalle);
		case 19:

			break;
		case 20:

			break;
		case 21:
			throw new NoControlableException(mensajeUsuario, mensajeDetalle);
		default:
			return "";
		}
		return "";
	}
		
	/**
     * Método para regresar el mensaje a mostrar al usuario en caso de error.
     *
     * @param tipo
     *            Tipo del error, si es de WS o TRN.
     * @param cod
     *            Código del error.
     * @return String Mensaje de error.
     */
    public static String mensajesError(final String tipo, final int cod, final Map<Integer, String> errorDetailMap) throws NoControlableException, ControlableException {
        final String mensajeUsuario = ProveedorMensajeSpringUtils.getValoresMensajesError("error."
                + tipo + "." + cod + ".mensaje.usuario");
        final StringBuilder mensajeDetalle = new StringBuilder();
        if(errorDetailMap!= null){
            for(final Map.Entry<Integer, String> entry: errorDetailMap.entrySet()){
                if(entry.getKey()!= 0){
                    try{
                        mensajeDetalle.append(EstadoEnum.mensajesDetalleError(entry.getKey(), entry.getValue())+";");
                    }catch( final NoControlableException ex){
                        mensajeDetalle.append("Error "+entry.getKey()+": Mensaje Inexistente");
                    }
                }
            }
        }
        
        return EstadoEnum.mensajesError(cod, mensajeUsuario, mensajeDetalle.toString());        
    }
    
    /**
     * Método para regresar el mensaje a mostrar al usuario en caso de error.
     *
     * @param cod Código del error.
     * @param mensajeUsuario Mensaje del usuario.
     * @param mensajeDetalle Detalle del mensaje.
     *             
     * @return String Mensaje de error.
     */
    public static String mensajesError(final int cod, final String mensajeUsuario, final String mensajeDetalle) throws NoControlableException, ControlableException {
       
        switch (cod) {
        case 0:
            throw new ControlableException(mensajeUsuario, mensajeDetalle,cod);
        case 2:
            throw new ControlableException(mensajeUsuario, mensajeDetalle,cod);
        case 3:
            throw new ControlableException(mensajeUsuario, mensajeDetalle,cod);
        case 4:
            throw new NoControlableException(mensajeUsuario, mensajeDetalle,cod);
        case 7:
            throw new ControlableException(mensajeUsuario, mensajeDetalle,cod);
        case 8:

            break;
        case 9:

            break;
        case 10:
        	throw new ControlableException(mensajeUsuario, mensajeDetalle,cod);
        case 11:
            throw new NoControlableException(mensajeUsuario, mensajeDetalle,cod);
        case 12:
            throw new ControlableException(mensajeUsuario, mensajeDetalle,cod);
        case 13:
            throw new NoControlableException(mensajeUsuario, mensajeDetalle,cod);
        case 14:

            break;
        case 15:

            break;
        case 16:

            break;
        case 17:
            throw new ControlableException(mensajeUsuario, mensajeDetalle,cod);
        case 18:
            throw new ControlableException(mensajeUsuario, mensajeDetalle,cod);
        case 19:

            break;
        case 20:

            break;
        case 21:
            throw new NoControlableException(mensajeUsuario, mensajeDetalle,cod);
        default:
            return "";
        }
        return "";
    }
    
	/**
     * Método para regresar el detalle del mensaje a mostrar al usuario en caso de error.
     *
     * @param textcod
     *            Código del error.
     * @param textargs
     *            Texto extra para sustituir dentro del mensaje de error
     * @return String Mensaje de error.
     */
    public static String mensajesDetalleError(final int textcod, final String textargs) {
        if(textargs == null){
            return ProveedorMensajeSpringUtils
                    .getValoresMensajesError("detalle.error.trn."+textcod);
        }
        Object[] array = null;
        if(textargs.contains("#")){
            array = textargs.split("");
        }else{
            array = new Object[]{textargs};
        }
        return ProveedorMensajeSpringUtils
                .getValoresMensajesError("detalle.error.trn."+textcod, array);
    }

}
