package mx.babel.bansefi.banksystem.oficina.webservices.consultasaldos;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 2.7.7.redhat-1
 * 2015-07-21T09:14:30.084-05:00
 * Generated source version: 2.7.7.redhat-1
 * 
 */
@WebService(targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "UO_TERM_CNS_2M_LIST_TRN", name = "SoapPort")
@XmlSeeAlso({ObjectFactory.class})
public interface SoapPort {

    @WebResult(name = "EjecutarResult", targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "UO_TERM_CNS_2M_LIST_TRN")
    @RequestWrapper(localName = "Ejecutar", targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "UO_TERM_CNS_2M_LIST_TRN", className = "mx.babel.bansefi.banksystem.oficina.webservices.consultasaldos.Ejecutar")
    @WebMethod(operationName = "Ejecutar", action = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "UO_TERM_CNS_2M_LIST_TRN/Ejecutar")
    @ResponseWrapper(localName = "EjecutarResponse", targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "UO_TERM_CNS_2M_LIST_TRN", className = "mx.babel.bansefi.banksystem.oficina.webservices.consultasaldos.EjecutarResponse")
    public mx.babel.bansefi.banksystem.oficina.webservices.consultasaldos.EjecutarResult ejecutar(
        @WebParam(name = "USERHEADER", targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "UO_TERM_CNS_2M_LIST_TRN")
        java.lang.String userheader,
        @WebParam(name = "PASSHEADER", targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "UO_TERM_CNS_2M_LIST_TRN")
        java.lang.String passheader,
        @WebParam(name = "IPHEADER", targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "UO_TERM_CNS_2M_LIST_TRN")
        java.lang.String ipheader,
        @WebParam(name = "IUO_TERM_CNS_2M_LIST_TRN", targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "UO_TERM_CNS_2M_LIST_TRN")
        mx.babel.bansefi.banksystem.oficina.webservices.consultasaldos.Ejecutar.IUOTERMCNS2MLISTTRN iuoTERMCNS2MLISTTRN
    );
}