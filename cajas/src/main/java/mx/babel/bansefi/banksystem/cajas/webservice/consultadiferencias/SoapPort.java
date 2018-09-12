package mx.babel.bansefi.banksystem.cajas.webservice.consultadiferencias;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 2.7.14.redhat-1
 * 2016-02-24T14:08:27.747-06:00
 * Generated source version: 2.7.14.redhat-1
 * 
 */
@WebService(targetNamespace = "http://BansefiNSS/WebServicesNSS/TR_DIF_OF_SM_LST_TRN", name = "SoapPort")
@XmlSeeAlso({ObjectFactory.class})
public interface SoapPort {

    @WebResult(name = "EjecutarResult", targetNamespace = "http://BansefiNSS/WebServicesNSS/TR_DIF_OF_SM_LST_TRN")
    @RequestWrapper(localName = "Ejecutar", targetNamespace = "http://BansefiNSS/WebServicesNSS/TR_DIF_OF_SM_LST_TRN", className = "mx.babel.bansefi.banksystem.cajas.webservice.consultadiferencias.Ejecutar")
    @WebMethod(operationName = "Ejecutar", action = "http://BansefiNSS/WebServicesNSS/TR_DIF_OF_SM_LST_TRN/Ejecutar")
    @ResponseWrapper(localName = "EjecutarResponse", targetNamespace = "http://BansefiNSS/WebServicesNSS/TR_DIF_OF_SM_LST_TRN", className = "mx.babel.bansefi.banksystem.cajas.webservice.consultadiferencias.EjecutarResponse")
    public mx.babel.bansefi.banksystem.cajas.webservice.consultadiferencias.EjecutarResult ejecutar(
        @WebParam(name = "USERHEADER", targetNamespace = "http://BansefiNSS/WebServicesNSS/TR_DIF_OF_SM_LST_TRN")
        java.lang.String userheader,
        @WebParam(name = "PASSHEADER", targetNamespace = "http://BansefiNSS/WebServicesNSS/TR_DIF_OF_SM_LST_TRN")
        java.lang.String passheader,
        @WebParam(name = "IPHEADER", targetNamespace = "http://BansefiNSS/WebServicesNSS/TR_DIF_OF_SM_LST_TRN")
        java.lang.String ipheader,
        @WebParam(name = "ITR_DIF_OF_SM_LST_TRN_I", targetNamespace = "http://BansefiNSS/WebServicesNSS/TR_DIF_OF_SM_LST_TRN")
        mx.babel.bansefi.banksystem.cajas.webservice.consultadiferencias.Ejecutar.ITRDIFOFSMLSTTRNI itrDIFOFSMLSTTRNI
    );
}