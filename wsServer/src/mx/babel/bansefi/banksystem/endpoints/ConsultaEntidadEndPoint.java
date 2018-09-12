package mx.babel.bansefi.banksystem.endpoints;



import mx.babel.bansefi.banksystem.response.consultaentidad.ArrayOfResponseBansefi;
import mx.babel.bansefi.banksystem.response.consultaentidad.EjecutarResponse;
import mx.babel.bansefi.banksystem.response.consultaentidad.EjecutarResult;
import mx.babel.bansefi.banksystem.response.consultaentidad.ResponseBansefi;
import mx.babel.bansefi.banksystem.response.consultaentidad.ResponseBansefi.TRCONENTIDADTRNO;
import mx.babel.bansefi.banksystem.response.consultaentidad.ResponseBansefi.TRCONENTIDADTRNO.TRCONENTIDADEVTZ;
import mx.babel.bansefi.banksystem.response.consultaentidad.ResponseBansefi.TRCONENTIDADTRNO.TRCONENTIDADEVTZ.NUMDIRPRALV;
import mx.babel.bansefi.banksystem.response.consultaentidad.ResponseBansefi.TRCONENTIDADTRNO.TRCONENTIDADEVTZ.STDNRBEEN;
import mx.babel.bansefi.banksystem.response.consultaentidad.ResponseBansefi.TRCONENTIDADTRNO.TRCONENTIDADEVTZ.STDNRBEENPRES;
import mx.babel.bansefi.banksystem.utils.MarshallObjetoUtils;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.w3c.dom.Element;

@Endpoint
public class ConsultaEntidadEndPoint {

    @PayloadRoot(localPart = "Ejecutar", namespace = "http://BansefiNSS/WebServicesNSS/TR_CON_ENTIDAD_TRN")
    @ResponsePayload
    public Element ejecutarConsulta(@RequestPayload Element element) {

        EjecutarResponse response = new EjecutarResponse();
        EjecutarResult result = new EjecutarResult();
        
        ArrayOfResponseBansefi arrayOfResponseBansefi = generarResultados();
        
        result.setResponseBansefi(arrayOfResponseBansefi);
        
        response.setEjecutarResult(result);
        
        System.out.println("About to return...");
        return MarshallObjetoUtils.marshallObjeto(mx.babel.bansefi.banksystem.response.consultaentidad.EjecutarResponse.class ,response);

    }

    
    private ArrayOfResponseBansefi generarResultados(){
    
        ArrayOfResponseBansefi arrayOfResponseBansefi = new ArrayOfResponseBansefi();
        ResponseBansefi responseBansefi = new ResponseBansefi();
        
        TRCONENTIDADTRNO trconentidadtrno = new TRCONENTIDADTRNO();  
        TRCONENTIDADEVTZ trconentidadevtz = new TRCONENTIDADEVTZ();
        
        trconentidadevtz.setCODAMBTOORG("05");
        trconentidadevtz.setCODNRBEEN("0166");
        trconentidadevtz.setCODNRBEENPRES("0166");
        trconentidadevtz.setFECHAALTASISTEN(19500101);
        trconentidadevtz.setFECHAFINSABVEN(20051231);
        trconentidadevtz.setFECHAINISABVEN(20051231);
        trconentidadevtz.setIDINTERNOPE(1);
        trconentidadevtz.setIDOFICIALEN("01660001");
        trconentidadevtz.setNOMBCOORDRSIEN("BAN500901167");
        trconentidadevtz.setNOMBCORTOEN("BANSEFI");
        trconentidadevtz.setNOMBRESPNSBLEN("LUIS ANGEL CANSECO RODRIGUEZ");
        NUMDIRPRALV numdirpralv = new NUMDIRPRALV();
        numdirpralv.setNUMDIRPRAL(18647117);
        trconentidadevtz.setNUMDIRPRALV(numdirpralv);
        STDNRBEEN stdnrbeen = new STDNRBEEN();
        stdnrbeen.setNOMBENTEN("BANSEFI");
        trconentidadevtz.setSTDNRBEEN(stdnrbeen);
        STDNRBEENPRES stdnrbeenpres = new STDNRBEENPRES();
        stdnrbeenpres.setNOMBENTEN("BANSEFI");
        trconentidadevtz.setSTDNRBEENPRES(stdnrbeenpres);
        
        

        trconentidadtrno.setTRCONENTIDADEVTZ(trconentidadevtz);
        trconentidadtrno.setRTRNCD(1);
        
        responseBansefi.setTRCONENTIDADTRNO(trconentidadtrno);
        
        arrayOfResponseBansefi.getResponseBansefi().add(responseBansefi);
        
        return arrayOfResponseBansefi;
    }
    
}
