package mx.babel.bansefi.banksystem.endpoints;

import java.math.BigDecimal;
import java.math.BigInteger;

import mx.babel.bansefi.banksystem.response.deposito.EjecutarResponse;
import mx.babel.bansefi.banksystem.response.deposito.EjecutarResult;
import mx.babel.bansefi.banksystem.response.deposito.ResponseBansefi;
import mx.babel.bansefi.banksystem.utils.MarshallObjetoUtils;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.w3c.dom.Element;

@Endpoint
public class DepositoEndPoint {

    @PayloadRoot(localPart = "Ejecutar", namespace = "http://10.200.14.110:3095/WebServices/DISRECLI")
    @ResponsePayload
    public Element ejecutarConsulta(@RequestPayload Element element){
    
        return MarshallObjetoUtils.marshallObjeto(EjecutarResponse.class ,generarResponseOk());
        
    }
    
public EjecutarResponse generarResponseOk(){
        
        EjecutarResponse response = new EjecutarResponse();
        EjecutarResult result = new EjecutarResult();
        
        ResponseBansefi responseBansefi = new ResponseBansefi();
        
        responseBansefi.setACUERDO(new BigInteger("1234567898"));
        responseBansefi.setCENTRO("123");
        responseBansefi.setDIGITO("1");
        responseBansefi.setESTATUS("0");
        responseBansefi.setFECHAOPERACION("01/01/2015");
        responseBansefi.setHORAOPERACION("00:00:00");
        responseBansefi.setIDTASK("1234");
        responseBansefi.setIMPORTE(new BigDecimal("123214"));
        responseBansefi.setMOVIMIENTO("123123");
        responseBansefi.setPLAZA("456");
        responseBansefi.setSECUENCIA(new BigInteger("1"));
        responseBansefi.setTERMINAL("123");
        responseBansefi.setTITULAR("Luis Gonzalez Castellano");
        
        result.setESTATUS("0");
        result.setTRANID("CONSTITU");
        result.setCODIGO("CHEA001");
        result.setNUMTASK("0213120000569");
        result.setMENSAJE("PROCESO EXITOSO");
        result.setResponseBansefi(responseBansefi);
        response.setEjecutarResult(result);
        
        response.setEjecutarResult(result);
        
        return response;
    }
    
    
}
