package mx.babel.bansefi.banksystem.base.webservices.consultalocalidadcodificada;

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
 * 2015-03-27T18:42:12.565-06:00
 * Generated source version: 2.7.7.redhat-1
 * 
 */
@WebService(targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_AG_LOC_LA_CNS_TRN", name = "SoapPort")
@XmlSeeAlso({ObjectFactory.class})
public interface SoapPort {

    @WebResult(name = "EjecutarResult", targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_AG_LOC_LA_CNS_TRN")
    @RequestWrapper(localName = "Ejecutar", targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_AG_LOC_LA_CNS_TRN", className = "bansefinss.webservicesnss.tr_ag_loc_la_cns_trn.Ejecutar")
    @WebMethod(operationName = "Ejecutar", action = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_AG_LOC_LA_CNS_TRN/Ejecutar")
    @ResponseWrapper(localName = "EjecutarResponse", targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_AG_LOC_LA_CNS_TRN", className = "bansefinss.webservicesnss.tr_ag_loc_la_cns_trn.EjecutarResponse")
    public mx.babel.bansefi.banksystem.base.webservices.consultalocalidadcodificada.EjecutarResult ejecutar(
        @WebParam(name = "TR_AG_LOC_LA_CNS_TRN_I", targetNamespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_AG_LOC_LA_CNS_TRN")
        mx.babel.bansefi.banksystem.base.webservices.consultalocalidadcodificada.Ejecutar.TRAGLOCLACNSTRNI trAGLOCLACNSTRNI
    );
}
