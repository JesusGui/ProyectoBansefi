package mx.babel.bansefi.banksystem.response.detallecentro;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 2.7.7.redhat-1
 * 2015-04-17T13:15:51.718-05:00
 * Generated source version: 2.7.7.redhat-1
 * 
 */
@WebService(targetNamespace = "http://BansefiNSS/WebServicesNSS/TR_CONS_CENTRO_TRN", name = "SoapPort")
@XmlSeeAlso({ObjectFactory.class})
public interface SoapPort {

    @WebResult(name = "EjecutarResult", targetNamespace = "http://BansefiNSS/WebServicesNSS/TR_CONS_CENTRO_TRN")
    @RequestWrapper(localName = "Ejecutar", targetNamespace = "http://BansefiNSS/WebServicesNSS/TR_CONS_CENTRO_TRN", className = "bansefinss.webservicesnss.tr_cons_centro_trn.Ejecutar")
    @WebMethod(operationName = "Ejecutar", action = "http://10.200.14.110:3300/WebServicesNSS/TR_CONS_CENTRO_TRN/Ejecutar")
    @ResponseWrapper(localName = "EjecutarResponse", targetNamespace = "http://BansefiNSS/WebServicesNSS/TR_CONS_CENTRO_TRN", className = "bansefinss.webservicesnss.tr_cons_centro_trn.EjecutarResponse")
    public mx.babel.bansefi.banksystem.response.detallecentro.EjecutarResult ejecutar(
        @WebParam(name = "TR_CONS_CENTRO_TRN_I", targetNamespace = "http://BansefiNSS/WebServicesNSS/TR_CONS_CENTRO_TRN")
        mx.babel.bansefi.banksystem.response.detallecentro.Ejecutar.TRCONSCENTROTRNI trCONSCENTROTRNI
    );
}
