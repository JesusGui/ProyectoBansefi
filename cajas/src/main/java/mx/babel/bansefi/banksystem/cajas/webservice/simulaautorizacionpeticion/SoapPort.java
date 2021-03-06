package mx.babel.bansefi.banksystem.cajas.webservice.simulaautorizacionpeticion;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 2.7.14.redhat-1
 * 2015-11-12T14:27:34.604-06:00
 * Generated source version: 2.7.14.redhat-1
 * 
 */
@WebService(targetNamespace = "http://BansefiNSS/WebServicesNSS/TR_AUT_SMLCN_PETCN_SM_TRN", name = "SoapPort")
@XmlSeeAlso({ObjectFactory.class})
public interface SoapPort {

    @WebResult(name = "EjecutarResult", targetNamespace = "http://BansefiNSS/WebServicesNSS/TR_AUT_SMLCN_PETCN_SM_TRN")
    @RequestWrapper(localName = "Ejecutar", targetNamespace = "http://BansefiNSS/WebServicesNSS/TR_AUT_SMLCN_PETCN_SM_TRN", className = "mx.babel.bansefi.banksystem.cajas.webservice.simulaautorizacionpeticion.Ejecutar")
    @WebMethod(operationName = "Ejecutar", action = "http://BansefiNSS/WebServicesNSS/TR_AUT_SMLCN_PETCN_SM_TRN/Ejecutar")
    @ResponseWrapper(localName = "EjecutarResponse", targetNamespace = "http://BansefiNSS/WebServicesNSS/TR_AUT_SMLCN_PETCN_SM_TRN", className = "mx.babel.bansefi.banksystem.cajas.webservice.simulaautorizacionpeticion.EjecutarResponse")
    public mx.babel.bansefi.banksystem.cajas.webservice.simulaautorizacionpeticion.EjecutarResult ejecutar(
        @WebParam(name = "USERHEADER", targetNamespace = "http://BansefiNSS/WebServicesNSS/TR_AUT_SMLCN_PETCN_SM_TRN")
        java.lang.String userheader,
        @WebParam(name = "PASSHEADER", targetNamespace = "http://BansefiNSS/WebServicesNSS/TR_AUT_SMLCN_PETCN_SM_TRN")
        java.lang.String passheader,
        @WebParam(name = "IPHEADER", targetNamespace = "http://BansefiNSS/WebServicesNSS/TR_AUT_SMLCN_PETCN_SM_TRN")
        java.lang.String ipheader,
        @WebParam(name = "ITR_AUT_SMLCN_PETCN_SM_TR", targetNamespace = "http://BansefiNSS/WebServicesNSS/TR_AUT_SMLCN_PETCN_SM_TRN")
        mx.babel.bansefi.banksystem.cajas.webservice.simulaautorizacionpeticion.Ejecutar.ITRAUTSMLCNPETCNSMTR itrAUTSMLCNPETCNSMTR
    );
}
