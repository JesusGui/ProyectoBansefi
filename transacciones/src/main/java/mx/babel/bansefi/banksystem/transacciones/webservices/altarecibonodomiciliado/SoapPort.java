package mx.babel.bansefi.banksystem.transacciones.webservices.altarecibonodomiciliado;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 2.7.7.redhat-1
 * 2015-08-19T15:59:32.766-05:00
 * Generated source version: 2.7.7.redhat-1
 * 
 */
@WebService(targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_WS + "RCNDPAGO", name = "SoapPort")
@XmlSeeAlso({ObjectFactory.class})
public interface SoapPort {

    @WebResult(name = "EjecutarResult", targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_WS + "RCNDPAGO")
    @RequestWrapper(localName = "Ejecutar", targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_WS + "RCNDPAGO", className = "mx.babel.bansefi.banksystem.transacciones.webservices.altarecibonodomiciliado.Ejecutar")
    @WebMethod(operationName = "Ejecutar", action = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_WS + "RCNDPAGO/Ejecutar")
    @ResponseWrapper(localName = "EjecutarResponse", targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_WS + "RCNDPAGO", className = "mx.babel.bansefi.banksystem.transacciones.webservices.altarecibonodomiciliado.EjecutarResponse")
    public mx.babel.bansefi.banksystem.transacciones.webservices.altarecibonodomiciliado.EjecutarResult ejecutar(
        @WebParam(name = "USERHEADER", targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_WS + "RCNDPAGO")
        java.lang.String userheader,
        @WebParam(name = "PASSHEADER", targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_WS + "RCNDPAGO")
        java.lang.String passheader,
        @WebParam(name = "IPHEADER", targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_WS + "RCNDPAGO")
        java.lang.String ipheader,
        @WebParam(name = "ENTIDAD", targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_WS + "RCNDPAGO")
        java.lang.String entidad,
        @WebParam(name = "ACUERDO", targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_WS + "RCNDPAGO")
        java.math.BigInteger acuerdo,
        @WebParam(name = "CVESERV", targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_WS + "RCNDPAGO")
        java.lang.String cveserv,
        @WebParam(name = "NUMTELRCBO", targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_WS + "RCNDPAGO")
        java.lang.String numtelrcbo,
        @WebParam(name = "IMPORTE", targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_WS + "RCNDPAGO")
        java.math.BigDecimal importe,
        @WebParam(name = "TIPOPGO", targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_WS + "RCNDPAGO")
        java.lang.String tipopgo,
        @WebParam(name = "DOMCTA", targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_WS + "RCNDPAGO")
        java.math.BigInteger domcta
    );
}
