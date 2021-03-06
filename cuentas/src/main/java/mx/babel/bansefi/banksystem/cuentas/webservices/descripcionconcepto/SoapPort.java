package mx.babel.bansefi.banksystem.cuentas.webservices.descripcionconcepto;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 2.7.14.redhat-1
 * 2015-12-29T10:00:56.338-06:00
 * Generated source version: 2.7.14.redhat-1
 * 
 */
@WebService(targetNamespace = "http://BansefiNSS/WebServicesNSS/HL_DESCR_CONCEP_TRN", name = "SoapPort")
@XmlSeeAlso({ObjectFactory.class})
public interface SoapPort {

    @WebResult(name = "EjecutarResult", targetNamespace = "http://BansefiNSS/WebServicesNSS/HL_DESCR_CONCEP_TRN")
    @RequestWrapper(localName = "Ejecutar", targetNamespace = "http://BansefiNSS/WebServicesNSS/HL_DESCR_CONCEP_TRN", className = "mx.babel.bansefi.banksystem.cuentas.webservices.descripcionconcepto.Ejecutar")
    @WebMethod(operationName = "Ejecutar", action = "http://BansefiNSS/WebServicesNSS/HL_DESCR_CONCEP_TRN/Ejecutar")
    @ResponseWrapper(localName = "EjecutarResponse", targetNamespace = "http://BansefiNSS/WebServicesNSS/HL_DESCR_CONCEP_TRN", className = "mx.babel.bansefi.banksystem.cuentas.webservices.descripcionconcepto.EjecutarResponse")
    public mx.babel.bansefi.banksystem.cuentas.webservices.descripcionconcepto.EjecutarResult ejecutar(
        @WebParam(name = "USERHEADER", targetNamespace = "http://BansefiNSS/WebServicesNSS/HL_DESCR_CONCEP_TRN")
        java.lang.String userheader,
        @WebParam(name = "PASSHEADER", targetNamespace = "http://BansefiNSS/WebServicesNSS/HL_DESCR_CONCEP_TRN")
        java.lang.String passheader,
        @WebParam(name = "IPHEADER", targetNamespace = "http://BansefiNSS/WebServicesNSS/HL_DESCR_CONCEP_TRN")
        java.lang.String ipheader,
        @WebParam(name = "IHL_DESCR_CONCEP_TRN_I", targetNamespace = "http://BansefiNSS/WebServicesNSS/HL_DESCR_CONCEP_TRN")
        mx.babel.bansefi.banksystem.cuentas.webservices.descripcionconcepto.Ejecutar.IHLDESCRCONCEPTRNI ihlDESCRCONCEPTRNI
    );
}
