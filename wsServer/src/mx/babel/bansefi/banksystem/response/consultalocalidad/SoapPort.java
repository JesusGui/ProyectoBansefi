package mx.babel.bansefi.banksystem.response.consultalocalidad;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 2.7.7.redhat-1
 * 2015-03-27T18:43:33.232-06:00
 * Generated source version: 2.7.7.redhat-1
 * 
 */
@WebService(targetNamespace = "http://BansefiNSS/WebServicesNSS/TR_AG_LOCALIDAD_CNS_TRN", name = "SoapPort")
@XmlSeeAlso({ObjectFactory.class})
public interface SoapPort {

    @WebResult(name = "EjecutarResult", targetNamespace = "http://BansefiNSS/WebServicesNSS/TR_AG_LOCALIDAD_CNS_TRN")
    @RequestWrapper(localName = "Ejecutar", targetNamespace = "http://BansefiNSS/WebServicesNSS/TR_AG_LOCALIDAD_CNS_TRN", className = "bansefinss.webservicesnss.tr_ag_localidad_cns_trn.Ejecutar")
    @WebMethod(operationName = "Ejecutar", action = "http://10.200.14.110:3300/WebServicesNSS/TR_AG_LOCALIDAD_CNS_TRN/Ejecutar")
    @ResponseWrapper(localName = "EjecutarResponse", targetNamespace = "http://BansefiNSS/WebServicesNSS/TR_AG_LOCALIDAD_CNS_TRN", className = "bansefinss.webservicesnss.tr_ag_localidad_cns_trn.EjecutarResponse")
    public mx.babel.bansefi.banksystem.response.consultalocalidad.EjecutarResult ejecutar(
        @WebParam(name = "TR_AG_LOCALIDAD_CNS_TRN_I", targetNamespace = "http://BansefiNSS/WebServicesNSS/TR_AG_LOCALIDAD_CNS_TRN")
        mx.babel.bansefi.banksystem.response.consultalocalidad.Ejecutar.TRAGLOCALIDADCNSTRNI trAGLOCALIDADCNSTRNI
    );
}
