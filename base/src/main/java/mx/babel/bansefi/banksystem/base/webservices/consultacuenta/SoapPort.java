package mx.babel.bansefi.banksystem.base.webservices.consultacuenta;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 2.7.7.redhat-1
 * 2015-06-30T12:55:07.376-05:00
 * Generated source version: 2.7.7.redhat-1
 * 
 */
@WebService(targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_CONSULTA_ACUERDO_TRN", name = "SoapPort")
@XmlSeeAlso({ObjectFactory.class})
public interface SoapPort {

    @WebMethod(operationName = "Ejecutar", action = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_CONSULTA_ACUERDO_TRN/Ejecutar")
    @RequestWrapper(localName = "Ejecutar", targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_CONSULTA_ACUERDO_TRN", className = "bansefinss.webservicesnss.tr_consulta_acuerdo_trn.Ejecutar")
    @ResponseWrapper(localName = "EjecutarResponse", targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_CONSULTA_ACUERDO_TRN", className = "bansefinss.webservicesnss.tr_consulta_acuerdo_trn.EjecutarResponse")
    @WebResult(name = "EjecutarResult", targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_CONSULTA_ACUERDO_TRN")
    public mx.babel.bansefi.banksystem.base.webservices.consultacuenta.EjecutarResult ejecutar(
        @WebParam(name = "USERHEADER", targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_CONSULTA_ACUERDO_TRN")
        java.lang.String userheader,
        @WebParam(name = "PASSHEADER", targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_CONSULTA_ACUERDO_TRN")
        java.lang.String passheader,
        @WebParam(name = "IPHEADER", targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_CONSULTA_ACUERDO_TRN")
        java.lang.String ipheader,
        @WebParam(name = "ITR_CONSULTA_ACUERDO_TRN", targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_CONSULTA_ACUERDO_TRN")
        mx.babel.bansefi.banksystem.base.webservices.consultacuenta.Ejecutar.ITRCONSULTAACUERDOTRN itrCONSULTAACUERDOTRN
    );
}
