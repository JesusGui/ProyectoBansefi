package mx.babel.bansefi.banksystem.response.altarelacioncuentacuenta;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 2.7.7.redhat-1
 * 2015-05-11T18:34:37.166-05:00
 * Generated source version: 2.7.7.redhat-1
 * 
 */
@WebService(targetNamespace = "http://BansefiNSS/WebServicesNSS/TR_ALTA_RX_PANT_TRN", name = "SoapPort")
@XmlSeeAlso({ObjectFactory.class})
public interface SoapPort {

    @WebResult(name = "EjecutarResult", targetNamespace = "http://BansefiNSS/WebServicesNSS/TR_ALTA_RX_PANT_TRN")
    @RequestWrapper(localName = "Ejecutar", targetNamespace = "http://BansefiNSS/WebServicesNSS/TR_ALTA_RX_PANT_TRN", className = "mx.babel.bansefi.banksystem.cuentas.webservices.altarelacioncuentacuenta.Ejecutar")
    @WebMethod(operationName = "Ejecutar", action = "http://10.200.14.110:3300/WebServicesNSS/TR_ALTA_RX_PANT_TRN/Ejecutar")
    @ResponseWrapper(localName = "EjecutarResponse", targetNamespace = "http://BansefiNSS/WebServicesNSS/TR_ALTA_RX_PANT_TRN", className = "mx.babel.bansefi.banksystem.cuentas.webservices.altarelacioncuentacuenta.EjecutarResponse")
    public mx.babel.bansefi.banksystem.response.altarelacioncuentacuenta.EjecutarResult ejecutar(
        @WebParam(name = "TR_ALTA_RX_PANT_TRN_I", targetNamespace = "http://BansefiNSS/WebServicesNSS/TR_ALTA_RX_PANT_TRN")
        mx.babel.bansefi.banksystem.response.altarelacioncuentacuenta.Ejecutar.TRALTARXPANTTRNI trALTARXPANTTRNI
    );
}