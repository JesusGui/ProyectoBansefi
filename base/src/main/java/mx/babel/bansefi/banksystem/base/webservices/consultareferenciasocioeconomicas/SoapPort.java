package mx.babel.bansefi.banksystem.base.webservices.consultareferenciasocioeconomicas;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 2.7.7.redhat-1
 * 2015-06-16T11:44:02.362-05:00
 * Generated source version: 2.7.7.redhat-1
 * 
 */
@WebService(targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "PE_CONS_ORG_SOCIOEC_TRN", name = "SoapPort")
@XmlSeeAlso({ObjectFactory.class})
public interface SoapPort {

    @WebResult(name = "EjecutarResult", targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "PE_CONS_ORG_SOCIOEC_TRN")
    @RequestWrapper(localName = "Ejecutar", targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "PE_CONS_ORG_SOCIOEC_TRN", className = "mx.babel.bansefi.banksystem.personas.webservices.consultareferenciasocioeconomicas.Ejecutar")
    @WebMethod(operationName = "Ejecutar", action = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "PE_CONS_ORG_SOCIOEC_TRN/Ejecutar")
    @ResponseWrapper(localName = "EjecutarResponse", targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "PE_CONS_ORG_SOCIOEC_TRN", className = "mx.babel.bansefi.banksystem.personas.webservices.consultareferenciasocioeconomicas.EjecutarResponse")
    public mx.babel.bansefi.banksystem.base.webservices.consultareferenciasocioeconomicas.EjecutarResult ejecutar(
        @WebParam(name = "USERHEADER", targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "PE_CONS_ORG_SOCIOEC_TRN")
        java.lang.String userheader,
        @WebParam(name = "PASSHEADER", targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "PE_CONS_ORG_SOCIOEC_TRN")
        java.lang.String passheader,
        @WebParam(name = "IPHEADER", targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "PE_CONS_ORG_SOCIOEC_TRN")
        java.lang.String ipheader,
        @WebParam(name = "IPE_CONS_ORG_SOCIOEC_TRN", targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "PE_CONS_ORG_SOCIOEC_TRN")
        mx.babel.bansefi.banksystem.base.webservices.consultareferenciasocioeconomicas.Ejecutar.IPECONSORGSOCIOECTRN ipeCONSORGSOCIOECTRN
    );
}
