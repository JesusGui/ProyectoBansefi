package mx.babel.bansefi.banksystem.cuentas.webservices.consultanumsliquidacion;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 2.7.7.redhat-1
 * 2015-07-23T11:38:36.856-05:00
 * Generated source version: 2.7.7.redhat-1
 * 
 */
@WebService(targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_LIQ_CONSULTAR_NUMEROS_TRN", name = "SoapPort")
@XmlSeeAlso({ObjectFactory.class})
public interface SoapPort {

    @WebResult(name = "EjecutarResult", targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_LIQ_CONSULTAR_NUMEROS_TRN")
    @RequestWrapper(localName = "Ejecutar", targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_LIQ_CONSULTAR_NUMEROS_TRN", className = "mx.babel.bansefi.banksystem.cuentas.webservices.consultanumsliquidacion.Ejecutar")
    @WebMethod(operationName = "Ejecutar", action = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_LIQ_CONSULTAR_NUMEROS_TRN/Ejecutar")
    @ResponseWrapper(localName = "EjecutarResponse", targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_LIQ_CONSULTAR_NUMEROS_TRN", className = "mx.babel.bansefi.banksystem.cuentas.webservices.consultanumsliquidacion.EjecutarResponse")
    public mx.babel.bansefi.banksystem.cuentas.webservices.consultanumsliquidacion.EjecutarResult ejecutar(
        @WebParam(name = "USERHEADER", targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_LIQ_CONSULTAR_NUMEROS_TRN")
        java.lang.String userheader,
        @WebParam(name = "PASSHEADER", targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_LIQ_CONSULTAR_NUMEROS_TRN")
        java.lang.String passheader,
        @WebParam(name = "IPHEADER", targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_LIQ_CONSULTAR_NUMEROS_TRN")
        java.lang.String ipheader,
        @WebParam(name = "ITR_LIQ_CONSULTAR_NUMEROS", targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_LIQ_CONSULTAR_NUMEROS_TRN")
        mx.babel.bansefi.banksystem.cuentas.webservices.consultanumsliquidacion.Ejecutar.ITRLIQCONSULTARNUMEROS itrLIQCONSULTARNUMEROS
    );
}
