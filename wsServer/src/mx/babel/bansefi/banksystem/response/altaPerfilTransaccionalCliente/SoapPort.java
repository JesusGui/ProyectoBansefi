package mx.babel.bansefi.banksystem.response.altaPerfilTransaccionalCliente;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 2.7.7.redhat-1
 * 2015-03-27T18:53:18.301-06:00
 * Generated source version: 2.7.7.redhat-1
 * 
 */
@WebService(targetNamespace = "http://BansefiNSS/WebServicesNSS/PE_ALTA_INDV_PERF_TRAN_TRN", name = "SoapPort")
@XmlSeeAlso({ObjectFactory.class})
public interface SoapPort {

    @WebResult(name = "EjecutarResult", targetNamespace = "http://BansefiNSS/WebServicesNSS/PE_ALTA_INDV_PERF_TRAN_TRN")
    @RequestWrapper(localName = "Ejecutar", targetNamespace = "http://BansefiNSS/WebServicesNSS/PE_ALTA_INDV_PERF_TRAN_TRN", className = "bansefinss.webservicesnss.pe_alta_indv_perf_tran_trn.Ejecutar")
    @WebMethod(operationName = "Ejecutar", action = "http://10.200.14.110:3300/WebServicesNSS/PE_ALTA_INDV_PERF_TRAN_TRN/Ejecutar")
    @ResponseWrapper(localName = "EjecutarResponse", targetNamespace = "http://BansefiNSS/WebServicesNSS/PE_ALTA_INDV_PERF_TRAN_TRN", className = "bansefinss.webservicesnss.pe_alta_indv_perf_tran_trn.EjecutarResponse")
    public mx.babel.bansefi.banksystem.response.altaPerfilTransaccionalCliente.EjecutarResult ejecutar(
        @WebParam(name = "PE_ALTA_INDV_PERF_TRAN_TRN_I", targetNamespace = "http://BansefiNSS/WebServicesNSS/PE_ALTA_INDV_PERF_TRAN_TRN")
        mx.babel.bansefi.banksystem.response.altaPerfilTransaccionalCliente.Ejecutar.PEALTAINDVPERFTRANTRNI peALTAINDVPERFTRANTRNI
    );
}
