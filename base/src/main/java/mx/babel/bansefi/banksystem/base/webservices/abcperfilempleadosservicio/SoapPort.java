package mx.babel.bansefi.banksystem.base.webservices.abcperfilempleadosservicio;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 2.7.7.redhat-1
 * 2015-08-04T09:50:40.444-05:00
 * Generated source version: 2.7.7.redhat-1
 * 
 */
@WebService(targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_WS + "ALTUSROI", name = "SoapPort")
@XmlSeeAlso({ObjectFactory.class})
public interface SoapPort {

    @WebResult(name = "EjecutarResult", targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_WS + "ALTUSROI")
    @RequestWrapper(localName = "Ejecutar", targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_WS + "ALTUSROI", className = "_125._14._200._10._3095.webservices.altusroi.Ejecutar")
    @WebMethod(operationName = "Ejecutar", action = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_WS + "ALTUSROI/Ejecutar")
    @ResponseWrapper(localName = "EjecutarResponse", targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_WS + "ALTUSROI", className = "_125._14._200._10._3095.webservices.altusroi.EjecutarResponse")
    public mx.babel.bansefi.banksystem.base.webservices.abcperfilempleadosservicio.EjecutarResult ejecutar(
        @WebParam(name = "USERHEADER", targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_WS + "ALTUSROI")
        java.lang.String userheader,
        @WebParam(name = "PASSHEADER", targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_WS + "ALTUSROI")
        java.lang.String passheader,
        @WebParam(name = "IPHEADER", targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_WS + "ALTUSROI")
        java.lang.String ipheader,
        @WebParam(name = "ACCION", targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_WS + "ALTUSROI")
        java.lang.String accion,
        @WebParam(name = "USUARIO", targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_WS + "ALTUSROI")
        java.lang.String usuario,
        @WebParam(name = "ENTIDAD", targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_WS + "ALTUSROI")
        java.lang.String entidad,
        @WebParam(name = "PERFIL", targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_WS + "ALTUSROI")
        java.lang.String perfil,
        @WebParam(name = "TERMINAL", targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_WS + "ALTUSROI")
        java.lang.String terminal
    );
}