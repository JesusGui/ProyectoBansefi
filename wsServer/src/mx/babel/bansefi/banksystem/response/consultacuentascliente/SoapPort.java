package mx.babel.bansefi.banksystem.response.consultacuentascliente;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 2.7.7.redhat-1
 * 2015-04-10T08:45:56.783-05:00
 * Generated source version: 2.7.7.redhat-1
 * 
 */
@WebService(targetNamespace = "http://BansefiNSS/WebServicesNSS/TR_CONSULTA_RP_PANT_5_TRN", name = "SoapPort")
@XmlSeeAlso({ObjectFactory.class})
public interface SoapPort {

    @WebResult(name = "EjecutarResult", targetNamespace = "http://BansefiNSS/WebServicesNSS/TR_CONSULTA_RP_PANT_5_TRN")
    @RequestWrapper(localName = "Ejecutar", targetNamespace = "http://BansefiNSS/WebServicesNSS/TR_CONSULTA_RP_PANT_5_TRN", className = "mx.babel.bansefi.banksystem.personas.webservices.consultacuentascliente.Ejecutar")
    @WebMethod(operationName = "Ejecutar", action = "http://10.200.14.110:3300/WebServicesNSS/TR/Ejecutar")
    @ResponseWrapper(localName = "EjecutarResponse", targetNamespace = "http://BansefiNSS/WebServicesNSS/TR_CONSULTA_RP_PANT_5_TRN", className = "mx.babel.bansefi.banksystem.personas.webservices.consultacuentascliente.EjecutarResponse")
    public mx.babel.bansefi.banksystem.response.consultacuentascliente.EjecutarResult ejecutar(
        @WebParam(name = "TR_CONSULTA_RP_PANT_5_TRN_I", targetNamespace = "http://BansefiNSS/WebServicesNSS/TR_CONSULTA_RP_PANT_5_TRN")
        mx.babel.bansefi.banksystem.response.consultacuentascliente.Ejecutar.TRCONSULTARPPANT5TRNI trCONSULTARPPANT5TRNI
    );
}
