package mx.babel.bansefi.banksystem.personas.webservices.altareferenciaspersonalesriesgo;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 2.7.7.redhat-1
 * 2015-07-22T17:42:36.895-05:00
 * Generated source version: 2.7.7.redhat-1
 * 
 */
@WebService(targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "PE_ALTA_INDV_REF_PER_TRN", name = "SoapPort")
@XmlSeeAlso({ObjectFactory.class})
public interface SoapPort {

    @WebResult(name = "EjecutarResult", targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "PE_ALTA_INDV_REF_PER_TRN")
    @RequestWrapper(localName = "Ejecutar", targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "PE_ALTA_INDV_REF_PER_TRN", className = "bansefinss.webservicesnss.pe_alta_indv_ref_per_trn.Ejecutar")
    @WebMethod(operationName = "Ejecutar", action = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "PE_ALTA_INDV_REF_PER_TRN/Ejecutar")
    @ResponseWrapper(localName = "EjecutarResponse", targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "PE_ALTA_INDV_REF_PER_TRN", className = "bansefinss.webservicesnss.pe_alta_indv_ref_per_trn.EjecutarResponse")
    public mx.babel.bansefi.banksystem.personas.webservices.altareferenciaspersonalesriesgo.EjecutarResult ejecutar(
        @WebParam(name = "USERHEADER", targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "PE_ALTA_INDV_REF_PER_TRN")
        java.lang.String userheader,
        @WebParam(name = "PASSHEADER", targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "PE_ALTA_INDV_REF_PER_TRN")
        java.lang.String passheader,
        @WebParam(name = "IPHEADER", targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "PE_ALTA_INDV_REF_PER_TRN")
        java.lang.String ipheader,
        @WebParam(name = "IPE_ALTA_INDV_REF_PER_TRN", targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "PE_ALTA_INDV_REF_PER_TRN")
        mx.babel.bansefi.banksystem.personas.webservices.altareferenciaspersonalesriesgo.Ejecutar.IPEALTAINDVREFPERTRN ipeALTAINDVREFPERTRN
    );
}
