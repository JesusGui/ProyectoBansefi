package mx.babel.bansefi.banksystem.endpoints;


import java.util.ArrayList;
import java.util.List;

import mx.babel.bansefi.banksystem.response.consultalistaentidades.ArrayOfResponseBansefi;
import mx.babel.bansefi.banksystem.response.consultalistaentidades.EjecutarResponse;
import mx.babel.bansefi.banksystem.response.consultalistaentidades.EjecutarResult;
import mx.babel.bansefi.banksystem.response.consultalistaentidades.ResponseBansefi;
import mx.babel.bansefi.banksystem.response.consultalistaentidades.ResponseBansefi.TRLISTAENTIDADES2TRNO.TRLISTAENTIDADESEVTZ;
import mx.babel.bansefi.banksystem.utils.MarshallObjetoUtils;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.w3c.dom.Element;

@Endpoint
public class ConsultaListaEntidadesEndPoint {

    @PayloadRoot(localPart = "Ejecutar", namespace = "http://BansefiNSS/WebServicesNSS/TR_LISTA_ENTIDADES_2_TRN")
    @ResponsePayload
    public Element ejecutarConsulta(@RequestPayload Element element){
        
        EjecutarResponse response = new EjecutarResponse();
        EjecutarResult result = new EjecutarResult();
        
        ArrayOfResponseBansefi arrayOfResponseBansefi = generarResultados();
        
        result.setResponseBansefi(arrayOfResponseBansefi);
        
        response.setEjecutarResult(result);
        
        System.out.println("About to return...");
        return MarshallObjetoUtils.marshallObjeto(mx.babel.bansefi.banksystem.response.consultalistaentidades.EjecutarResponse.class ,response);
    }
    
    private ArrayOfResponseBansefi generarResultados(){
        
        
        List<ResponseBansefi.TRLISTAENTIDADES2TRNO.TRLISTAENTIDADESEVTZ> trlistaentidadesevtz = new ArrayList<ResponseBansefi.TRLISTAENTIDADES2TRNO.TRLISTAENTIDADESEVTZ>();
        ArrayOfResponseBansefi arrayOfResponseBansefi = new ArrayOfResponseBansefi();
        ResponseBansefi responseBansefi = new ResponseBansefi();
        
        ResponseBansefi.TRLISTAENTIDADES2TRNO trno = new ResponseBansefi.TRLISTAENTIDADES2TRNO();
        
        ResponseBansefi.TRLISTAENTIDADES2TRNO.STDTRNMSJPARMV msj = new ResponseBansefi.TRLISTAENTIDADES2TRNO.STDTRNMSJPARMV();
        ResponseBansefi.TRLISTAENTIDADES2TRNO.STDTRNOPARMV params = new ResponseBansefi.TRLISTAENTIDADES2TRNO.STDTRNOPARMV();
        ResponseBansefi.TRLISTAENTIDADES2TRNO.TRLISTAENTIDADESEVTZ resultado1 = new ResponseBansefi.TRLISTAENTIDADES2TRNO.TRLISTAENTIDADESEVTZ();
        
        ResponseBansefi.TRLISTAENTIDADES2TRNO.TRLISTAENTIDADESEVTZ.CRENTIDADE entidad1 = new ResponseBansefi.TRLISTAENTIDADES2TRNO.TRLISTAENTIDADESEVTZ.CRENTIDADE(); 
        entidad1.setCODAMBTOORG("05");
        entidad1.setCODNRBEEN("0090");
        entidad1.setCODNRBEENPRES("0090");
        entidad1.setFECHAALTASISTEN(19500101);
        entidad1.setFECHAFINSABVEN(20900220);
        entidad1.setFECHAINISABVEN(20050101);
        entidad1.setIDINTERNOPE(0);
        entidad1.setIDOFICIALEN("00900001");
        entidad1.setNOMBCOORDRSIEN("DIRECTOR GENERAL");
        entidad1.setNOMBCORTOEN("SOFIPO T1");
        entidad1.setNOMBENTEN("SOFIPO T1");
        entidad1.setNOMBRESPNSBLEN("DIRECTOR GENERAL");
        
        
        ResponseBansefi.TRLISTAENTIDADES2TRNO.TRLISTAENTIDADESEVTZ.CRENTIDADE entidad2 = new ResponseBansefi.TRLISTAENTIDADES2TRNO.TRLISTAENTIDADESEVTZ.CRENTIDADE(); 
        entidad2.setCODAMBTOORG("05");
        entidad2.setCODNRBEEN("0091");
        entidad2.setCODNRBEENPRES("0090");
        entidad2.setFECHAALTASISTEN(19500101);
        entidad2.setFECHAFINSABVEN(20900220);
        entidad2.setFECHAINISABVEN(20050101);
        entidad2.setIDINTERNOPE(0);
        entidad2.setIDOFICIALEN("00900001");
        entidad2.setNOMBCOORDRSIEN("DIRECTOR GENERAL");
        entidad2.setNOMBCORTOEN("SOFIPO T1");
        entidad2.setNOMBENTEN("SOFIPO T1");
        entidad2.setNOMBRESPNSBLEN("DIRECTOR GENERAL");
        
        params.setFECHAOPRCN(0);
        params.setHORAOPRCN(0);
        
        msj.setTEXTCODE(1);
        
        resultado1.setCRENTIDADE(entidad1);
        
        
        TRLISTAENTIDADESEVTZ entidadEVTZ1 = new TRLISTAENTIDADESEVTZ();
        TRLISTAENTIDADESEVTZ entidadEVTZ2 = new TRLISTAENTIDADESEVTZ();
        entidadEVTZ1.setCRENTIDADE(entidad1);
        entidadEVTZ2.setCRENTIDADE(entidad2);
        trlistaentidadesevtz.add(entidadEVTZ1);
        trlistaentidadesevtz.add(entidadEVTZ2);
        
        trno.setSTDTRNOPARMV(params);
        trno.setRTRNCD(1);
        trno.getSTDTRNMSJPARMV().add(msj);
        trno.getTRLISTAENTIDADESEVTZ().add(entidadEVTZ1);
        trno.getTRLISTAENTIDADESEVTZ().add(entidadEVTZ2);
        
        responseBansefi.setTRLISTAENTIDADES2TRNO(trno);
        
        arrayOfResponseBansefi.getResponseBansefi().add(responseBansefi);
        
        return arrayOfResponseBansefi;
        
    }
}
