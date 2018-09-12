package mx.babel.bansefi.banksystem.base.webservices.consultacatalogo;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 2.7.7.redhat-1
 * 2015-05-15T17:33:45.975-05:00
 * Generated source version: 2.7.7.redhat-1
 * 
 */
@WebService(targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_WS + "CATALOGO", name = "SoapPort")
@XmlSeeAlso({ObjectFactory.class})
public interface SoapPort {

    @WebResult(name = "EjecutarResult", targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_WS + "CATALOGO")
    @RequestWrapper(localName = "Ejecutar", targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_WS + "CATALOGO", className = "mx.babel.bansefi.banksystem.base.webservices.consultacatalogo.Ejecutar")
    @WebMethod(operationName = "Ejecutar", action = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_WS + "CATALOGO/Ejecutar")
    @ResponseWrapper(localName = "EjecutarResponse", targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_WS + "CATALOGO", className = "mx.babel.bansefi.banksystem.base.webservices.consultacatalogo.EjecutarResponse")
    public mx.babel.bansefi.banksystem.base.webservices.consultacatalogo.EjecutarResult ejecutar(
        @WebParam(name = "USERHEADER", targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_WS + "CATALOGO")
        java.lang.String userheader,
        @WebParam(name = "PASSHEADER", targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_WS + "CATALOGO")
        java.lang.String passheader,
        @WebParam(name = "IPHEADER", targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_WS + "CATALOGO")
        java.lang.String ipheader,
        @WebParam(name = "CLAVE_DE_ACTIVACION1", targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_WS + "CATALOGO")
        java.lang.String claveDEACTIVACION1,
        @WebParam(name = "CLAVE_DE_FILA_NOMBRE", targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_WS + "CATALOGO")
        java.lang.String claveDEFILANOMBRE,
        @WebParam(name = "CLAVE_DE_PAGINACION1", targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_WS + "CATALOGO")
        java.lang.String claveDEPAGINACION1,
        @WebParam(name = "NUMERO_DE_REGISTROS1", targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_WS + "CATALOGO")
        java.lang.String numeroDEREGISTROS1
    );
}
