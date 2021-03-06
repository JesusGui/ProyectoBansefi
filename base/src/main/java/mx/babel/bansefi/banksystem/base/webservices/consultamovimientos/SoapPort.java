package mx.babel.bansefi.banksystem.base.webservices.consultamovimientos;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 2.7.7.redhat-1
 * 2015-04-28T10:53:52.103-05:00
 * Generated source version: 2.7.7.redhat-1
 * 
 */
@WebService(targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_WS + "VTCONMOV", name = "SoapPort")
@XmlSeeAlso({ObjectFactory.class})
public interface SoapPort {

    @WebResult(name = "EjecutarResult", targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_WS + "VTCONMOV")
    @RequestWrapper(localName = "Ejecutar", targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_WS + "VTCONMOV", className = "mx.babel.bansefi.banksystem.base.webservices.consultamovimientos.Ejecutar")
    @WebMethod(operationName = "Ejecutar", action = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_WS + "VTCONMOV/Ejecutar")
    @ResponseWrapper(localName = "EjecutarResponse", targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_WS + "VTCONMOV", className = "mx.babel.bansefi.banksystem.base.webservices.consultamovimientos.EjecutarResponse")
    public mx.babel.bansefi.banksystem.base.webservices.consultamovimientos.EjecutarResult ejecutar(
        @WebParam(name = "USERHEADER", targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_WS + "VTCONMOV")
        java.lang.String userheader,
        @WebParam(name = "PASSHEADER", targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_WS + "VTCONMOV")
        java.lang.String passheader,
        @WebParam(name = "IPHEADER", targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_WS + "VTCONMOV")
        java.lang.String ipheader,
        @WebParam(name = "ENTIDAD", targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_WS + "VTCONMOV")
        java.lang.String entidad,
        @WebParam(name = "ACUERDO", targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_WS + "VTCONMOV")
        java.math.BigInteger acuerdo,
        @WebParam(name = "FECHA_DESDE", targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_WS + "VTCONMOV")
        java.lang.String fechaDESDE,
        @WebParam(name = "FECHA_HASTA", targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_WS + "VTCONMOV")
        java.lang.String fechaHASTA,
        @WebParam(name = "NUM_SEC", targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_WS + "VTCONMOV")
        java.math.BigInteger numSEC,
        @WebParam(name = "CVEIDOF", targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_WS + "VTCONMOV")
        java.lang.String cveidof,
        @WebParam(name = "NUM_IFE", targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_WS + "VTCONMOV")
        java.lang.String numIFE
    );
}
