package mx.babel.bansefi.banksystem.base.webservices.aperturapuesto;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 2.7.7.redhat-1
 * 2015-08-12T09:23:21.204-05:00
 * Generated source version: 2.7.7.redhat-1
 * 
 */
@WebService(targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_APERTURA_PUESTOS_TRN", name = "SoapPort")
@XmlSeeAlso({ObjectFactory.class})
public interface SoapPort {

    @WebResult(name = "EjecutarResult", targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_APERTURA_PUESTOS_TRN")
    @RequestWrapper(localName = "Ejecutar", targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_APERTURA_PUESTOS_TRN", className = "mx.babel.bansefi.banksystem.base.webservices.aperturapuesto.Ejecutar")
    @WebMethod(operationName = "Ejecutar", action = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_APERTURA_PUESTOS_TRN/Ejecutar")
    @ResponseWrapper(localName = "EjecutarResponse", targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_APERTURA_PUESTOS_TRN", className = "mx.babel.bansefi.banksystem.base.webservices.aperturapuesto.EjecutarResponse")
    public mx.babel.bansefi.banksystem.base.webservices.aperturapuesto.EjecutarResult ejecutar(
        @WebParam(name = "USERHEADER", targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_APERTURA_PUESTOS_TRN")
        java.lang.String userheader,
        @WebParam(name = "PASSHEADER", targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_APERTURA_PUESTOS_TRN")
        java.lang.String passheader,
        @WebParam(name = "IPHEADER", targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_APERTURA_PUESTOS_TRN")
        java.lang.String ipheader,
        @WebParam(name = "ITR_APERTURA_PUESTOS_TRN", targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_APERTURA_PUESTOS_TRN")
        mx.babel.bansefi.banksystem.base.webservices.aperturapuesto.Ejecutar.ITRAPERTURAPUESTOSTRN itrAPERTURAPUESTOSTRN
    );
}