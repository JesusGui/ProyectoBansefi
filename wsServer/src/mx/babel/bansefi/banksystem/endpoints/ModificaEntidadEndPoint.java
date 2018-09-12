package mx.babel.bansefi.banksystem.endpoints;

import mx.babel.bansefi.banksystem.response.modificaentidad.ArrayOfResponseBansefi;
import mx.babel.bansefi.banksystem.response.modificaentidad.EjecutarResponse;
import mx.babel.bansefi.banksystem.response.modificaentidad.EjecutarResult;
import mx.babel.bansefi.banksystem.response.modificaentidad.ResponseBansefi;
import mx.babel.bansefi.banksystem.utils.MarshallObjetoUtils;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.w3c.dom.Element;

@Endpoint
public class ModificaEntidadEndPoint {

    
    @PayloadRoot(localPart = "Ejecutar", namespace = "http://BansefiNSS/WebServicesNSS/TR_MODI_ENTIDAD_TRN")
    @ResponsePayload
    public Element ejecutarConsulta(@RequestPayload Element element) {

        EjecutarResponse response = new EjecutarResponse();
        EjecutarResult result = new EjecutarResult();
        
        ArrayOfResponseBansefi arrayOfResponseBansefi = generarResultados();
        
        result.setResponseBansefi(arrayOfResponseBansefi);
        
        response.setEjecutarResult(result);
        
        System.out.println("About to return...");
        return MarshallObjetoUtils.marshallObjeto(mx.babel.bansefi.banksystem.response.modificaentidad.EjecutarResponse.class ,response);

    }
    
    private ArrayOfResponseBansefi generarResultados(){
        
        ArrayOfResponseBansefi arrayOfResponseBansefi = new ArrayOfResponseBansefi();
        ResponseBansefi responseBansefi = new ResponseBansefi();
        
        ResponseBansefi.TRMODIENTIDADTRNO trmodientidadtrno = new  ResponseBansefi.TRMODIENTIDADTRNO();
        
        trmodientidadtrno.setRTRNCD(1);
        
        responseBansefi.setTRMODIENTIDADTRNO(trmodientidadtrno);
        
        arrayOfResponseBansefi.getResponseBansefi().add(responseBansefi);
        
        return arrayOfResponseBansefi;
        
    }
    
}
