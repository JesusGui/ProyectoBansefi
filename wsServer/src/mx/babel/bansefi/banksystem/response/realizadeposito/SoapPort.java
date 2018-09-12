package mx.babel.bansefi.banksystem.response.realizadeposito;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 2.7.7.redhat-1
 * 2015-04-29T11:43:09.588-05:00
 * Generated source version: 2.7.7.redhat-1
 * 
 */
@WebService(targetNamespace = "http://10.200.14.110:3095/WebServices/VTDEPOSI", name = "SoapPort")
@XmlSeeAlso({ObjectFactory.class})
public interface SoapPort {

    @WebResult(name = "EjecutarResult", targetNamespace = "http://10.200.14.110:3095/WebServices/VTDEPOSI")
    @RequestWrapper(localName = "Ejecutar", targetNamespace = "http://10.200.14.110:3095/WebServices/VTDEPOSI", className = "_110._14._200._10._3095.webservices.vtdeposi.Ejecutar")
    @WebMethod(operationName = "Ejecutar", action = "http://10.200.14.110:3095/WebServices/VTDEPOSI/Ejecutar")
    @ResponseWrapper(localName = "EjecutarResponse", targetNamespace = "http://10.200.14.110:3095/WebServices/VTDEPOSI", className = "_110._14._200._10._3095.webservices.vtdeposi.EjecutarResponse")
    public mx.babel.bansefi.banksystem.response.realizadeposito.EjecutarResult ejecutar(
        @WebParam(name = "USERHEADER", targetNamespace = "http://10.200.14.110:3095/WebServices/VTDEPOSI")
        java.lang.String userheader,
        @WebParam(name = "PASSHEADER", targetNamespace = "http://10.200.14.110:3095/WebServices/VTDEPOSI")
        java.lang.String passheader,
        @WebParam(name = "IPHEADER", targetNamespace = "http://10.200.14.110:3095/WebServices/VTDEPOSI")
        java.lang.String ipheader,
        @WebParam(name = "ENTIDAD", targetNamespace = "http://10.200.14.110:3095/WebServices/VTDEPOSI")
        java.lang.String entidad,
        @WebParam(name = "ACUERDO", targetNamespace = "http://10.200.14.110:3095/WebServices/VTDEPOSI")
        java.math.BigInteger acuerdo,
        @WebParam(name = "IMPORTE", targetNamespace = "http://10.200.14.110:3095/WebServices/VTDEPOSI")
        java.math.BigDecimal importe,
        @WebParam(name = "FECHAVALOR", targetNamespace = "http://10.200.14.110:3095/WebServices/VTDEPOSI")
        java.lang.String fechavalor,
        @WebParam(name = "CODOPERACION", targetNamespace = "http://10.200.14.110:3095/WebServices/VTDEPOSI")
        java.lang.String codoperacion,
        @WebParam(name = "CONCEPTO", targetNamespace = "http://10.200.14.110:3095/WebServices/VTDEPOSI")
        java.lang.String concepto,
        @WebParam(name = "IDREFE", targetNamespace = "http://10.200.14.110:3095/WebServices/VTDEPOSI")
        java.lang.String idrefe,
        @WebParam(name = "REFERENCIA", targetNamespace = "http://10.200.14.110:3095/WebServices/VTDEPOSI")
        java.lang.String referencia
    );
}
