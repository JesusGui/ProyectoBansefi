package mx.babel.bansefi.banksystem.base.webservices.busquedapersonasmorales;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 2.7.7.redhat-1
 * 2015-06-22T17:10:51.322-05:00
 * Generated source version: 2.7.7.redhat-1
 * 
 */
@WebService(targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_WS + "VTUNICNJ", name = "SoapPort")
@XmlSeeAlso({ObjectFactory.class})
public interface SoapPort {

    @WebResult(name = "EjecutarResult", targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_WS + "VTUNICNJ")
    @RequestWrapper(localName = "Ejecutar", targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_WS + "VTUNICNJ", className = "_125._14._200._10._3095.webservices.vtunicnj.Ejecutar")
    @WebMethod(operationName = "Ejecutar", action = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_WS + "VTUNICNJ/Ejecutar")
    @ResponseWrapper(localName = "EjecutarResponse", targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_WS + "VTUNICNJ", className = "_125._14._200._10._3095.webservices.vtunicnj.EjecutarResponse")
    public mx.babel.bansefi.banksystem.base.webservices.busquedapersonasmorales.EjecutarResult ejecutar(
        @WebParam(name = "USERHEADER", targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_WS + "VTUNICNJ")
        java.lang.String userheader,
        @WebParam(name = "PASSHEADER", targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_WS + "VTUNICNJ")
        java.lang.String passheader,
        @WebParam(name = "IPHEADER", targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_WS + "VTUNICNJ")
        java.lang.String ipheader,
        @WebParam(name = "ENTIDAD", targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_WS + "VTUNICNJ")
        java.lang.String entidad,
        @WebParam(name = "NUM_IFE", targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_WS + "VTUNICNJ")
        java.lang.String numIFE,
        @WebParam(name = "ID_INTERNO", targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_WS + "VTUNICNJ")
        java.math.BigInteger idINTERNO,
        @WebParam(name = "ID_EXTERNO", targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_WS + "VTUNICNJ")
        java.lang.String idEXTERNO,
        @WebParam(name = "DEN_LEGAL", targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_WS + "VTUNICNJ")
        java.lang.String denLEGAL
    );
}
