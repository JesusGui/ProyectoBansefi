package mx.babel.bansefi.banksystem.base.webservices.relacioncliente.altapersona2;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 2.7.14.redhat-1
 * 2015-12-28T12:27:57.262-06:00
 * Generated source version: 2.7.14.redhat-1
 * 
 */
@WebService(targetNamespace = "http://BansefiNSS/WebServicesNSS/TR_PE_ALTA_RL_2_PE_DS_TRN", name = "SoapPort")
@XmlSeeAlso({ObjectFactory.class})
public interface SoapPort {

    @WebResult(name = "EjecutarResult", targetNamespace = "http://BansefiNSS/WebServicesNSS/TR_PE_ALTA_RL_2_PE_DS_TRN")
    @RequestWrapper(localName = "Ejecutar", targetNamespace = "http://BansefiNSS/WebServicesNSS/TR_PE_ALTA_RL_2_PE_DS_TRN", className = "mx.babel.bansefi.banksystem.base.webservices.relacioncliente.altapersona2.Ejecutar")
    @WebMethod(operationName = "Ejecutar", action = "http://BansefiNSS/WebServicesNSS/TR_PE_ALTA_RL_2_PE_DS_TRN/Ejecutar")
    @ResponseWrapper(localName = "EjecutarResponse", targetNamespace = "http://BansefiNSS/WebServicesNSS/TR_PE_ALTA_RL_2_PE_DS_TRN", className = "mx.babel.bansefi.banksystem.base.webservices.relacioncliente.altapersona2.EjecutarResponse")
    public mx.babel.bansefi.banksystem.base.webservices.relacioncliente.altapersona2.EjecutarResult ejecutar(
        @WebParam(name = "USERHEADER", targetNamespace = "http://BansefiNSS/WebServicesNSS/TR_PE_ALTA_RL_2_PE_DS_TRN")
        java.lang.String userheader,
        @WebParam(name = "PASSHEADER", targetNamespace = "http://BansefiNSS/WebServicesNSS/TR_PE_ALTA_RL_2_PE_DS_TRN")
        java.lang.String passheader,
        @WebParam(name = "IPHEADER", targetNamespace = "http://BansefiNSS/WebServicesNSS/TR_PE_ALTA_RL_2_PE_DS_TRN")
        java.lang.String ipheader,
        @WebParam(name = "ITR_PE_ALTA_RL_2_PE_DS_TR", targetNamespace = "http://BansefiNSS/WebServicesNSS/TR_PE_ALTA_RL_2_PE_DS_TRN")
        mx.babel.bansefi.banksystem.base.webservices.relacioncliente.altapersona2.Ejecutar.ITRPEALTARL2PEDSTR itrPEALTARL2PEDSTR
    );
}
