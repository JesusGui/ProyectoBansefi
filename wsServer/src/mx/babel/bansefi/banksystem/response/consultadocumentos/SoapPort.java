package mx.babel.bansefi.banksystem.response.consultadocumentos;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 2.7.7.redhat-1
 * 2015-04-06T15:43:50.151-05:00
 * Generated source version: 2.7.7.redhat-1
 * 
 */
@WebService(targetNamespace = "http://BansefiNSS/WebServicesNSS/TR_DC_LS_CNS_TRN", name = "SoapPort")
@XmlSeeAlso({ObjectFactory.class})
public interface SoapPort {

    @WebResult(name = "EjecutarResult", targetNamespace = "http://BansefiNSS/WebServicesNSS/TR_DC_LS_CNS_TRN")
    @RequestWrapper(localName = "Ejecutar", targetNamespace = "http://BansefiNSS/WebServicesNSS/TR_DC_LS_CNS_TRN", className = "mx.babel.bansefi.banksystem.personas.webservices.consultadocumentos.Ejecutar")
    @WebMethod(operationName = "Ejecutar", action = "http://10.200.14.110:3300/WebServicesNSS/TR_DC_LS_CNS_TRN/Ejecutar")
    @ResponseWrapper(localName = "EjecutarResponse", targetNamespace = "http://BansefiNSS/WebServicesNSS/TR_DC_LS_CNS_TRN", className = "mx.babel.bansefi.banksystem.personas.webservices.consultadocumentos.EjecutarResponse")
    public mx.babel.bansefi.banksystem.response.consultadocumentos.EjecutarResult ejecutar(
        @WebParam(name = "TR_DC_LS_CNS_TRN_I", targetNamespace = "http://BansefiNSS/WebServicesNSS/TR_DC_LS_CNS_TRN")
        mx.babel.bansefi.banksystem.response.consultadocumentos.Ejecutar.TRDCLSCNSTRNI trDCLSCNSTRNI
    );
}
