package mx.babel.bansefi.banksystem.administracion.webservices.bajacentrocontrolador;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 2.7.7.redhat-1
 * 2015-07-03T17:18:36.233-05:00
 * Generated source version: 2.7.7.redhat-1
 * 
 */
@WebService(targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_BAJA_CENT_CTRL_TRN", name = "SoapPort")
@XmlSeeAlso({ObjectFactory.class})
public interface SoapPort {

    @WebResult(name = "EjecutarResult", targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_BAJA_CENT_CTRL_TRN")
    @RequestWrapper(localName = "Ejecutar", targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_BAJA_CENT_CTRL_TRN", className = "bansefinss.webservicesnss.tr_baja_cent_ctrl_trn.Ejecutar")
    @WebMethod(operationName = "Ejecutar", action = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_BAJA_CENT_CTRL_TRN/Ejecutar")
    @ResponseWrapper(localName = "EjecutarResponse", targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_BAJA_CENT_CTRL_TRN", className = "bansefinss.webservicesnss.tr_baja_cent_ctrl_trn.EjecutarResponse")
    public mx.babel.bansefi.banksystem.administracion.webservices.bajacentrocontrolador.EjecutarResult ejecutar(
        @WebParam(name = "USERHEADER", targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_BAJA_CENT_CTRL_TRN")
        java.lang.String userheader,
        @WebParam(name = "PASSHEADER", targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_BAJA_CENT_CTRL_TRN")
        java.lang.String passheader,
        @WebParam(name = "IPHEADER", targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_BAJA_CENT_CTRL_TRN")
        java.lang.String ipheader,
        @WebParam(name = "ITR_BAJA_CENT_CTRL_TRN_I", targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_BAJA_CENT_CTRL_TRN")
        mx.babel.bansefi.banksystem.administracion.webservices.bajacentrocontrolador.Ejecutar.ITRBAJACENTCTRLTRNI itrBAJACENTCTRLTRNI
    );
}
