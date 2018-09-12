package mx.babel.bansefi.banksystem.cuentas.webservices.consultaCentroAsociado;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

import mx.babel.arq.comun.constants.ServicioWebConstants;

/**
 * This class was generated by Apache CXF 2.7.7.redhat-1
 * 2015-06-19T13:42:23.328-05:00
 * Generated source version: 2.7.7.redhat-1
 * 
 */
@WebService(targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_CONSU_TODAS_OFI_AC_TRN", name = "SoapPort")
@XmlSeeAlso({ObjectFactory.class})
public interface SoapPort {

    @WebResult(name = "EjecutarResult", targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_CONSU_TODAS_OFI_AC_TRN")
    @RequestWrapper(localName = "Ejecutar", targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_CONSU_TODAS_OFI_AC_TRN", className = "bansefinss.webservicesnss.tr_consu_todas_ofi_ac_trn.Ejecutar")
    @WebMethod(operationName = "Ejecutar", action = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_CONSU_TODAS_OFI_AC_TRN/Ejecutar")
    @ResponseWrapper(localName = "EjecutarResponse", targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_CONSU_TODAS_OFI_AC_TRN", className = "bansefinss.webservicesnss.tr_consu_todas_ofi_ac_trn.EjecutarResponse")
    public mx.babel.bansefi.banksystem.cuentas.webservices.consultaCentroAsociado.EjecutarResult ejecutar(
        @WebParam(name = "USERHEADER", targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_CONSU_TODAS_OFI_AC_TRN")
        java.lang.String userheader,
        @WebParam(name = "PASSHEADER", targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_CONSU_TODAS_OFI_AC_TRN")
        java.lang.String passheader,
        @WebParam(name = "IPHEADER", targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_CONSU_TODAS_OFI_AC_TRN")
        java.lang.String ipheader,
        @WebParam(name = "ITR_CONSU_TODAS_OFI_AC_TR", targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_CONSU_TODAS_OFI_AC_TRN")
        mx.babel.bansefi.banksystem.cuentas.webservices.consultaCentroAsociado.Ejecutar.ITRCONSUTODASOFIACTR itrCONSUTODASOFIACTR
    );
}
