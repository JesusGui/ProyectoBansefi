package mx.babel.bansefi.banksystem.response.consultapersonamoral;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 2.7.7.redhat-1
 * 2015-03-27T17:32:40.919-06:00
 * Generated source version: 2.7.7.redhat-1
 * 
 */
@WebService(targetNamespace = "http://BansefiNSS/WebServicesNSS/TR_PE_CONS_ORG_TRN", name = "SoapPort")
@XmlSeeAlso({ObjectFactory.class})
public interface SoapPort {

    @WebResult(name = "EjecutarResult", targetNamespace = "http://BansefiNSS/WebServicesNSS/TR_PE_CONS_ORG_TRN")
    @RequestWrapper(localName = "Ejecutar", targetNamespace = "http://BansefiNSS/WebServicesNSS/TR_PE_CONS_ORG_TRN", className = "mx.babel.bansefi.banksystem.personas.webservices.consultapersonamoral.Ejecutar")
    @WebMethod(operationName = "Ejecutar", action = "http://10.200.14.110:3300/WebServicesNSS/TR_PE_CONS_ORG_TRN/Ejecutar")
    @ResponseWrapper(localName = "EjecutarResponse", targetNamespace = "http://BansefiNSS/WebServicesNSS/TR_PE_CONS_ORG_TRN", className = "mx.babel.bansefi.banksystem.personas.webservices.consultapersonamoral.EjecutarResponse")
    public mx.babel.bansefi.banksystem.response.consultapersonamoral.EjecutarResult ejecutar(
        @WebParam(name = "TR_PE_CONS_ORG_TRN_I", targetNamespace = "http://BansefiNSS/WebServicesNSS/TR_PE_CONS_ORG_TRN")
        mx.babel.bansefi.banksystem.response.consultapersonamoral.Ejecutar.TRPECONSORGTRNI trPECONSORGTRNI
    );
}
