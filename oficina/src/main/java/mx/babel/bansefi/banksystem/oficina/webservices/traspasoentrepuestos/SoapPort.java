package mx.babel.bansefi.banksystem.oficina.webservices.traspasoentrepuestos;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 2.7.7.redhat-1
 * 2015-08-20T17:56:53.005-05:00
 * Generated source version: 2.7.7.redhat-1
 * 
 */
@WebService(targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_TRASPASO_ACTU_SALDOS_TRN", name = "SoapPort")
@XmlSeeAlso({ObjectFactory.class})
public interface SoapPort {

    @WebResult(name = "EjecutarResult", targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_TRASPASO_ACTU_SALDOS_TRN")
    @RequestWrapper(localName = "Ejecutar", targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_TRASPASO_ACTU_SALDOS_TRN", className = "mx.babel.bansefi.banksystem.oficina.webservices.traspasoentrepuestos.Ejecutar")
    @WebMethod(operationName = "Ejecutar", action = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_TRASPASO_ACTU_SALDOS_TRN/Ejecutar")
    @ResponseWrapper(localName = "EjecutarResponse", targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_TRASPASO_ACTU_SALDOS_TRN", className = "mx.babel.bansefi.banksystem.oficina.webservices.traspasoentrepuestos.EjecutarResponse")
    public mx.babel.bansefi.banksystem.oficina.webservices.traspasoentrepuestos.EjecutarResult ejecutar(
        @WebParam(name = "USERHEADER", targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_TRASPASO_ACTU_SALDOS_TRN")
        java.lang.String userheader,
        @WebParam(name = "PASSHEADER", targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_TRASPASO_ACTU_SALDOS_TRN")
        java.lang.String passheader,
        @WebParam(name = "IPHEADER", targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_TRASPASO_ACTU_SALDOS_TRN")
        java.lang.String ipheader,
        @WebParam(name = "ITR_TRASPASO_ACTU_SALDOS", targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_TRASPASO_ACTU_SALDOS_TRN")
        mx.babel.bansefi.banksystem.oficina.webservices.traspasoentrepuestos.Ejecutar.ITRTRASPASOACTUSALDOS itrTRASPASOACTUSALDOS
    );
}