package mx.babel.arq.comun.constants;

import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ProveedorMensajeSpringUtils;

/**
 * Clase utilizada para guardar constantes asociadas a las rutas de servicios web
 * @author gerard.chavez
 *
 */
public final class ServicioWebConstants {

    public final static String SOAP_PORT_URL_TRN = "http://BansefiNSS/WebServicesNSS/";
//    public final static String SOAP_PORT_URL_LOGON = "http://10.200.14.125:3096/WebServices/";
//    public final static String SOAP_PORT_URL_WS = "http://10.200.14.125:3095/WebServices/";
    public final static String SOAP_PORT_URL_LOGON = "http://10.200.14.110:3096/WebServices/";
    public final static String SOAP_PORT_URL_WS = "http://10.200.14.110:3095/WebServices/";
//    public final static String SOAP_PORT_URL_LOGON = "http://10.200.14.80:3096/WebServices/";
//    public final static String SOAP_PORT_URL_WS = "http://10.200.14.80:3095/WebServices/";

    public final static String URL_WSDL = ProveedorMensajeSpringUtils.getValorConfiguracion("ambiente.trn.wsdl");
    public final static String URL_WSDL_WS = ProveedorMensajeSpringUtils.getValorConfiguracion("ambiente.ws.wsdl");

    public static String WSDL_LOGON = ProveedorMensajeSpringUtils.getValorConfiguracion("ambiente.logon.wsdl");
    public static String TARGETNAMESPACE_LOGON = ProveedorMensajeSpringUtils.getValorConfiguracion("ambiente.logon.targetnamespace");

    public static String TARGETNAMESPACE_TRN = ProveedorMensajeSpringUtils.getValorConfiguracion("ambiente.trn.targetnamespace");
    public static String TARGETNAMESPACE_WS = ProveedorMensajeSpringUtils.getValorConfiguracion("ambiente.ws.targetnamespace");

    public ServicioWebConstants(){
        throw new NoControlableException("Error al invocar contructor de clase de constantes ServicioWebConstants", "Error: tratar de construir una clase no permitida.");
    }
}
