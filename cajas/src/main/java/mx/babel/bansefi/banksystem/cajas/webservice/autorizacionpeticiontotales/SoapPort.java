package mx.babel.bansefi.banksystem.cajas.webservice.autorizacionpeticiontotales;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 2.7.14.redhat-1
 * 2015-10-29T18:06:25.645-06:00
 * Generated source version: 2.7.14.redhat-1
 * 
 */
@WebService(targetNamespace = "http://BansefiNSS/WebServicesNSS/TR_AUT_TOT_CONF_SM_TRN", name = "SoapPort")
@XmlSeeAlso({ObjectFactory.class})
public interface SoapPort {

    @WebResult(name = "EjecutarResult", targetNamespace = "http://BansefiNSS/WebServicesNSS/TR_AUT_TOT_CONF_SM_TRN")
    @RequestWrapper(localName = "Ejecutar", targetNamespace = "http://BansefiNSS/WebServicesNSS/TR_AUT_TOT_CONF_SM_TRN", className = "mx.babel.bansefi.banksystem.cajas.webservice.autorizacionPeticionTotales.Ejecutar")
    @WebMethod(operationName = "Ejecutar", action = "http://BansefiNSS/WebServicesNSS/TR_AUT_TOT_CONF_SM_TRN/Ejecutar")
    @ResponseWrapper(localName = "EjecutarResponse", targetNamespace = "http://BansefiNSS/WebServicesNSS/TR_AUT_TOT_CONF_SM_TRN", className = "mx.babel.bansefi.banksystem.cajas.webservice.autorizacionPeticionTotales.EjecutarResponse")
    public mx.babel.bansefi.banksystem.cajas.webservice.autorizacionpeticiontotales.EjecutarResult ejecutar(
        @WebParam(name = "USERHEADER", targetNamespace = "http://BansefiNSS/WebServicesNSS/TR_AUT_TOT_CONF_SM_TRN")
        java.lang.String userheader,
        @WebParam(name = "PASSHEADER", targetNamespace = "http://BansefiNSS/WebServicesNSS/TR_AUT_TOT_CONF_SM_TRN")
        java.lang.String passheader,
        @WebParam(name = "IPHEADER", targetNamespace = "http://BansefiNSS/WebServicesNSS/TR_AUT_TOT_CONF_SM_TRN")
        java.lang.String ipheader,
        @WebParam(name = "ITR_AUT_TOT_CONF_SM_TRN_I", targetNamespace = "http://BansefiNSS/WebServicesNSS/TR_AUT_TOT_CONF_SM_TRN")
        mx.babel.bansefi.banksystem.cajas.webservice.autorizacionpeticiontotales.Ejecutar.ITRAUTTOTCONFSMTRNI itrAUTTOTCONFSMTRNI
    );
}
