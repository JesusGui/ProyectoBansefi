package mx.babel.bansefi.banksystem.response.guardadocumento;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 2.7.7.redhat-1
 * 2015-04-27T09:53:26.704-05:00
 * Generated source version: 2.7.7.redhat-1
 * 
 */
@WebService(targetNamespace = "http://BansefiNSS/WebServicesNSS/TR_GENERA_IDCTE_TRN", name = "SoapPort")
@XmlSeeAlso({ObjectFactory.class})
public interface SoapPort {

    @WebResult(name = "EjecutarResult", targetNamespace = "http://BansefiNSS/WebServicesNSS/TR_GENERA_IDCTE_TRN")
    @RequestWrapper(localName = "Ejecutar", targetNamespace = "http://BansefiNSS/WebServicesNSS/TR_GENERA_IDCTE_TRN", className = "mx.babel.bansefi.banksystem.base.webservices.guardadocumento.Ejecutar")
    @WebMethod(operationName = "Ejecutar", action = "http://10.200.14.110:3300/WebServicesNSS/TR_GENERA_IDCTE_TRN/Ejecutar")
    @ResponseWrapper(localName = "EjecutarResponse", targetNamespace = "http://BansefiNSS/WebServicesNSS/TR_GENERA_IDCTE_TRN", className = "mx.babel.bansefi.banksystem.base.webservices.guardadocumento.EjecutarResponse")
    public mx.babel.bansefi.banksystem.response.guardadocumento.EjecutarResult ejecutar(
        @WebParam(name = "TR_GENERA_IDCTE_TRN_I", targetNamespace = "http://BansefiNSS/WebServicesNSS/TR_GENERA_IDCTE_TRN")
        mx.babel.bansefi.banksystem.response.guardadocumento.Ejecutar.TRGENERAIDCTETRNI trGENERAIDCTETRNI
    );
}
