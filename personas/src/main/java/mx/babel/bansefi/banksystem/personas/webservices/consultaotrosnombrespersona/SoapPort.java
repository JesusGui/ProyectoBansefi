package mx.babel.bansefi.banksystem.personas.webservices.consultaotrosnombrespersona;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 2.7.7.redhat-1
 * 2015-06-15T18:26:33.494-05:00
 * Generated source version: 2.7.7.redhat-1
 * 
 */
@WebService(targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_PE_CONS_PE_OTRO_NOMB_TRN", name = "SoapPort")
@XmlSeeAlso({ObjectFactory.class})
public interface SoapPort {

    @WebResult(name = "EjecutarResult", targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_PE_CONS_PE_OTRO_NOMB_TRN")
    @RequestWrapper(localName = "Ejecutar", targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_PE_CONS_PE_OTRO_NOMB_TRN", className = "bansefinss.webservicesnss.tr_pe_cons_pe_otro_nomb_trn.Ejecutar")
    @WebMethod(operationName = "Ejecutar", action = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_PE_CONS_PE_OTRO_NOMB_TRN/Ejecutar")
    @ResponseWrapper(localName = "EjecutarResponse", targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_PE_CONS_PE_OTRO_NOMB_TRN", className = "bansefinss.webservicesnss.tr_pe_cons_pe_otro_nomb_trn.EjecutarResponse")
    public mx.babel.bansefi.banksystem.personas.webservices.consultaotrosnombrespersona.EjecutarResult ejecutar(
        @WebParam(name = "USERHEADER", targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_PE_CONS_PE_OTRO_NOMB_TRN")
        java.lang.String userheader,
        @WebParam(name = "PASSHEADER", targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_PE_CONS_PE_OTRO_NOMB_TRN")
        java.lang.String passheader,
        @WebParam(name = "IPHEADER", targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_PE_CONS_PE_OTRO_NOMB_TRN")
        java.lang.String ipheader,
        @WebParam(name = "ITR_PE_CONS_PE_OTRO_NOMB", targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_PE_CONS_PE_OTRO_NOMB_TRN")
        mx.babel.bansefi.banksystem.personas.webservices.consultaotrosnombrespersona.Ejecutar.ITRPECONSPEOTRONOMB itrPECONSPEOTRONOMB
    );
}
