package mx.babel.bansefi.banksystem.response.consultatitular;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 2.7.7.redhat-1
 * 2015-04-06T10:16:14.781-05:00
 * Generated source version: 2.7.7.redhat-1
 * 
 */
@WebService(targetNamespace = "http://10.200.14.110:3095/WebServices/CONSTITU", name = "SoapPort")
@XmlSeeAlso({ObjectFactory.class})
public interface SoapPort {

    @WebResult(name = "EjecutarResult", targetNamespace = "http://10.200.14.110:3095/WebServices/CONSTITU")
    @RequestWrapper(localName = "Ejecutar", targetNamespace = "http://10.200.14.110:3095/WebServices/CONSTITU", className = "mx.babel.bansefi.banksystem.transacciones.webservices.consultatitular.Ejecutar")
    @WebMethod(operationName = "Ejecutar", action = "http://10.200.14.110:3095/WebServices/CONSTITU/Ejecutar")
    @ResponseWrapper(localName = "EjecutarResponse", targetNamespace = "http://10.200.14.110:3095/WebServices/CONSTITU", className = "mx.babel.bansefi.banksystem.transacciones.webservices.consultatitular.EjecutarResponse")
    public mx.babel.bansefi.banksystem.response.consultatitular.EjecutarResult ejecutar(
        @WebParam(name = "USERHEADER", targetNamespace = "http://10.200.14.110:3095/WebServices/CONSTITU")
        java.lang.String userheader,
        @WebParam(name = "PASSHEADER", targetNamespace = "http://10.200.14.110:3095/WebServices/CONSTITU")
        java.lang.String passheader,
        @WebParam(name = "IPHEADER", targetNamespace = "http://10.200.14.110:3095/WebServices/CONSTITU")
        java.lang.String ipheader,
        @WebParam(name = "ENTIDAD", targetNamespace = "http://10.200.14.110:3095/WebServices/CONSTITU")
        java.lang.String entidad,
        @WebParam(name = "ACUERDO", targetNamespace = "http://10.200.14.110:3095/WebServices/CONSTITU")
        java.math.BigInteger acuerdo
    );
}
