package mx.babel.bansefi.banksystem.transacciones.webservices.validaemisora;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 2.7.7.redhat-1
 * 2015-08-13T18:50:24.082-05:00
 * Generated source version: 2.7.7.redhat-1
 * 
 */
@WebService(targetNamespace = "http://10.200.14.125:3095/WebServices/RCNDOMIC", name = "SoapPort")
@XmlSeeAlso({ObjectFactory.class})
public interface SoapPort {

    @WebResult(name = "EjecutarResult", targetNamespace = "http://10.200.14.125:3095/WebServices/RCNDOMIC")
    @RequestWrapper(localName = "Ejecutar", targetNamespace = "http://10.200.14.125:3095/WebServices/RCNDOMIC", className = "mx.babel.bansefi.banksystem.transacciones.webservices.validaemisora.Ejecutar")
    @WebMethod(operationName = "Ejecutar", action = "http://10.200.14.125:3095/WebServices/RCNDOMIC/Ejecutar")
    @ResponseWrapper(localName = "EjecutarResponse", targetNamespace = "http://10.200.14.125:3095/WebServices/RCNDOMIC", className = "mx.babel.bansefi.banksystem.transacciones.webservices.validaemisora.EjecutarResponse")
    public mx.babel.bansefi.banksystem.transacciones.webservices.validaemisora.EjecutarResult ejecutar(
        @WebParam(name = "USERHEADER", targetNamespace = "http://10.200.14.125:3095/WebServices/RCNDOMIC")
        java.lang.String userheader,
        @WebParam(name = "PASSHEADER", targetNamespace = "http://10.200.14.125:3095/WebServices/RCNDOMIC")
        java.lang.String passheader,
        @WebParam(name = "IPHEADER", targetNamespace = "http://10.200.14.125:3095/WebServices/RCNDOMIC")
        java.lang.String ipheader,
        @WebParam(name = "EMISORA", targetNamespace = "http://10.200.14.125:3095/WebServices/RCNDOMIC")
        java.lang.String emisora
    );
}
