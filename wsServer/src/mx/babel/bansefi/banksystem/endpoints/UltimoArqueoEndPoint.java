/**
 * 
 */
package mx.babel.bansefi.banksystem.endpoints;

import java.util.ArrayList;
import java.util.List;

import mx.babel.bansefi.banksystem.response.ultimo_arqueo.ArrayOfResponseBansefi;
import mx.babel.bansefi.banksystem.response.ultimo_arqueo.ResponseBansefi;
import mx.babel.bansefi.banksystem.utils.MarshallObjetoUtils;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.w3c.dom.Element;

/**
 * Clase para el EndPoint de arquedo
 * @author eliot.vasquez
 * 
 */
@Endpoint
public class UltimoArqueoEndPoint {
    @PayloadRoot(localPart = "Ejecutar", namespace = "http://BansefiNSS/WebServicesNSS/TR_EXIST_EX_LST_TRN")
    @ResponsePayload
    public Element ejecutarUltimoArqueo(@RequestPayload Element element) {
//        Ejecutar objEjecutar = (Ejecutar) MarshallObjetoUtils.unMarshallObjeto(mx.babel.bansefi.banksystem.response.ultimo_arqueo.Ejecutar.class, element);
        
        mx.babel.bansefi.banksystem.response.ultimo_arqueo.EjecutarResponse response = new mx.babel.bansefi.banksystem.response.ultimo_arqueo.EjecutarResponse();
        mx.babel.bansefi.banksystem.response.ultimo_arqueo.EjecutarResult result = new mx.babel.bansefi.banksystem.response.ultimo_arqueo.EjecutarResult();

        ArrayOfResponseBansefi arResponseBansefi = new ArrayOfResponseBansefi();
        ResponseBansefi responseBansefi = new ResponseBansefi();
        ResponseBansefi.TREXISTEXLSTTRNO trexistexlsttrno = new ResponseBansefi.TREXISTEXLSTTRNO();
        trexistexlsttrno.setRTRNCD(1);

        ResponseBansefi.TREXISTEXLSTTRNO.TREXISTEXLSTEVTZ trexistexlstevtz = new ResponseBansefi.TREXISTEXLSTTRNO.TREXISTEXLSTEVTZ();
        List<ResponseBansefi.TREXISTEXLSTTRNO.STDTRNMSJPARMV> stdtrnmsjparmv = new ArrayList<ResponseBansefi.TREXISTEXLSTTRNO.STDTRNMSJPARMV>();
        ResponseBansefi.TREXISTEXLSTTRNO.STDTRNMSJPARMV obj = new ResponseBansefi.TREXISTEXLSTTRNO.STDTRNMSJPARMV();
        stdtrnmsjparmv.add(obj);
        ResponseBansefi.TREXISTEXLSTTRNO.STDTRNOPARMV stdtrnoparmv = new ResponseBansefi.TREXISTEXLSTTRNO.STDTRNOPARMV();
        stdtrnoparmv.setFECHAOPRCN(20150101);
        stdtrnoparmv.setHORAOPRCN(1230);

        ResponseBansefi.TREXISTEXLSTTRNO.TREXISTEXLSTEVTZ.EXEXISTMONEDAE exexistmonedae = new ResponseBansefi.TREXISTEXLSTTRNO.TREXISTEXLSTEVTZ.EXEXISTMONEDAE();
        exexistmonedae.setCODNRBEEN("");
        exexistmonedae.setCODINTERNOUO("");
        exexistmonedae.setFECHAEX(20150101);
        exexistmonedae.setCODSITEX("");
        exexistmonedae.setCODECVEX("");
        exexistmonedae.setINDOFCNAEX("");
        exexistmonedae.setSDOINICIAL(0);
        exexistmonedae.setSDOFINAL(5000);
        exexistmonedae.setTOTEFCT(5000);
        exexistmonedae.setCAJACOBROSON(3000);
        exexistmonedae.setCAJAPAGOSON(3000);
        exexistmonedae.setCAJACOBROSOFF(0);
        exexistmonedae.setCAJAPAGOSOFF(0);
        exexistmonedae.setINTEHABERON(2000);
        exexistmonedae.setINTEDEBEON(2000);
        exexistmonedae.setINTEHABEROFF(0);
        exexistmonedae.setINTEDEBEOFF(0);
        exexistmonedae.setCODNUMRCOMONEDA("");
        
        trexistexlstevtz.getEXEXISTMONEDAE().add(exexistmonedae);
        trexistexlsttrno.setSTDTRNOPARMV(stdtrnoparmv);
        trexistexlsttrno.setTREXISTEXLSTEVTZ(trexistexlstevtz);
        responseBansefi.setTREXISTEXLSTTRNO(trexistexlsttrno);
        
        arResponseBansefi.getResponseBansefi().add(responseBansefi);
        result.setResponseBansefi(arResponseBansefi);
        response.setEjecutarResult(result);
        
        System.out.println("About to return...");
        return MarshallObjetoUtils.marshallObjeto(mx.babel.bansefi.banksystem.response.ultimo_arqueo.EjecutarResponse.class, response);
    }
}
