package mx.babel.bansefi.banksystem.webservices.retiro;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 2.7.7.redhat-1
 * 2015-07-16T19:00:12.463-05:00
 * Generated source version: 2.7.7.redhat-1
 * 
 */
@WebService(targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_WS + "VTRETIRO", name = "SoapPort")
@XmlSeeAlso({ObjectFactory.class})
public interface SoapPort {

    @WebResult(name = "EjecutarResult", targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_WS + "VTRETIRO")
    @RequestWrapper(localName = "Ejecutar", targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_WS + "VTRETIRO", className = "mx.babel.bansefi.banksystem.webservices.retiro.Ejecutar")
    @WebMethod(operationName = "Ejecutar", action = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_WS + "VTRETIRO/Ejecutar")
    @ResponseWrapper(localName = "EjecutarResponse", targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_WS + "VTRETIRO", className = "mx.babel.bansefi.banksystem.webservices.retiro.EjecutarResponse")
    public mx.babel.bansefi.banksystem.webservices.retiro.EjecutarResult ejecutar(
        @WebParam(name = "USERHEADER", targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_WS + "VTRETIRO")
        java.lang.String userheader,
        @WebParam(name = "PASSHEADER", targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_WS + "VTRETIRO")
        java.lang.String passheader,
        @WebParam(name = "IPHEADER", targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_WS + "VTRETIRO")
        java.lang.String ipheader,
        @WebParam(name = "ENTIDAD", targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_WS + "VTRETIRO")
        java.lang.String entidad,
        @WebParam(name = "ACUERDO", targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_WS + "VTRETIRO")
        java.math.BigInteger acuerdo,
        @WebParam(name = "IMPORTE", targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_WS + "VTRETIRO")
        java.math.BigDecimal importe,
        @WebParam(name = "FECVALOR", targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_WS + "VTRETIRO")
        java.lang.String fecvalor,
        @WebParam(name = "CODOPE", targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_WS + "VTRETIRO")
        java.lang.String codope,
        @WebParam(name = "CVEIDOF", targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_WS + "VTRETIRO")
        java.lang.String cveidof,
        @WebParam(name = "IFE", targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_WS + "VTRETIRO")
        java.lang.String ife,
        @WebParam(name = "CONCEPTO", targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_WS + "VTRETIRO")
        java.lang.String concepto
    );
}
