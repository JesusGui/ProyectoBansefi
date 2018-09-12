package mx.babel.bansefi.banksystem.endpoints;

import java.math.BigDecimal;

import mx.babel.bansefi.banksystem.response.consultasaldo.EjecutarResponse;
import mx.babel.bansefi.banksystem.response.consultasaldo.EjecutarResult;
import mx.babel.bansefi.banksystem.response.consultasaldo.ResponseBansefi;
//import mx.babel.bansefi.banksystem.response.consultasaldo.EjecutarResponse;
//import mx.babel.bansefi.banksystem.response.consultasaldo.EjecutarResult;
//import mx.babel.bansefi.banksystem.response.consultasaldo.ResponseBansefi;
import mx.babel.bansefi.banksystem.utils.MarshallObjetoUtils;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.w3c.dom.Element;

@Endpoint
public class ConsultaSaldoEndPoint {


    @PayloadRoot(localPart = "Ejecutar", namespace = "http://10.200.14.110:3095/WebServices/VTCONSAL")
    @ResponsePayload
    public Element ejecutarConsulta(@RequestPayload Element element){
        EjecutarResponse response = null;
        int resultadoRandom = 0;
        switch (resultadoRandom){
        case 0:
            response = this.generarResponseOk();
            break;
        case 1:
            response = this.generarResponseAcuerdoIncorrecto();
            break;
        case 2:
            response = this.generarResponseUserHeaderIncorrecto();
            break;
        case 3:
            response = this.generarResponsePassIncorrecto();
            break;
        case 4:
            response = this.generarResponseIpIncorrecta();
            break;
        }
        
        return MarshallObjetoUtils.marshallObjeto(EjecutarResponse.class ,response);
    }
    
    
    
    public EjecutarResponse generarResponseOk(){
        
        EjecutarResponse response = new EjecutarResponse();
        EjecutarResult result = new EjecutarResult();
        
        ResponseBansefi responseBansefi = new ResponseBansefi();
        
        responseBansefi.setCENTRO("Centro");
        responseBansefi.setDC("DC");
        responseBansefi.setDISPONIBLE(new BigDecimal("10000000.00"));
        responseBansefi.setMONEDA("MXN");
        responseBansefi.setSALDOCONTABLE(new BigDecimal("10001000.00"));
        responseBansefi.setTITULAR("Francisco Ferrer");
        responseBansefi.setTOTALAUTORIZADO(new BigDecimal("1000.00"));
        responseBansefi.setTOTALRETENIDO(new BigDecimal("0.00"));
        
        result.setResponseBansefi(responseBansefi);
        result.setESTATUS("0");
        result.setTRANID("CONSTITU");
        result.setCODIGO("CHEA001");
        result.setNUMTASK("0213120000569");
        result.setMENSAJE("PROCESO EXITOSO");
        
        response.setEjecutarResult(result);
        response.setEjecutarResult(result);
        
        return response;
    }
    
    public EjecutarResponse generarResponseAcuerdoIncorrecto(){
        
        EjecutarResponse response = new EjecutarResponse();
        EjecutarResult result = new EjecutarResult();
        
        ResponseBansefi responseBansefi = new ResponseBansefi();
        
        responseBansefi.setCENTRO("");
        responseBansefi.setDC("");
        responseBansefi.setDISPONIBLE(new BigDecimal("0.00"));
        responseBansefi.setMONEDA("");
        responseBansefi.setSALDOCONTABLE(new BigDecimal("0.00"));
        responseBansefi.setTITULAR("");
        responseBansefi.setTOTALAUTORIZADO(new BigDecimal("0.00"));
        responseBansefi.setTOTALRETENIDO(new BigDecimal("0.00"));
       
        result.setResponseBansefi(responseBansefi);
        result.setESTATUS("1");
        result.setTRANID("CONSTITU");
        result.setCODIGO("CHEE016");
        result.setNUMTASK("0216370001440");
        result.setMENSAJE("ACUERDO NO EXISTE");
        
        response.setEjecutarResult(result);
        
        return response;
    }
    
    public EjecutarResponse generarResponseUserHeaderIncorrecto(){
        
        EjecutarResponse response = new EjecutarResponse();
        EjecutarResult result = new EjecutarResult();
        
        ResponseBansefi responseBansefi = new ResponseBansefi();
        
        responseBansefi.setCENTRO("");
        responseBansefi.setDC("");
        responseBansefi.setDISPONIBLE(new BigDecimal("0.00"));
        responseBansefi.setMONEDA("");
        responseBansefi.setSALDOCONTABLE(new BigDecimal("0.00"));
        responseBansefi.setTITULAR("");
        responseBansefi.setTOTALAUTORIZADO(new BigDecimal("0.00"));
        responseBansefi.setTOTALRETENIDO(new BigDecimal("0.00"));        
        
        result.setResponseBansefi(responseBansefi);
        result.setESTATUS("1");
        result.setTRANID("ARQ1");
        result.setCODIGO("QGE0180");
        result.setNUMTASK("0116530006250");
        result.setMENSAJE("USUER CONEX-HEADER INCORRECTO");
        response.setEjecutarResult(result);
        
        return response;
    }
    
    public EjecutarResponse generarResponsePassIncorrecto(){
        
        EjecutarResponse response = new EjecutarResponse();
        EjecutarResult result = new EjecutarResult();
        
        ResponseBansefi responseBansefi = new ResponseBansefi();
        
        responseBansefi.setCENTRO("");
        responseBansefi.setDC("");
        responseBansefi.setDISPONIBLE(new BigDecimal("0.00"));
        responseBansefi.setMONEDA("");
        responseBansefi.setSALDOCONTABLE(new BigDecimal("0.00"));
        responseBansefi.setTITULAR("");
        responseBansefi.setTOTALAUTORIZADO(new BigDecimal("0.00"));
        responseBansefi.setTOTALRETENIDO(new BigDecimal("0.00"));        

        result.setResponseBansefi(responseBansefi);
        result.setESTATUS("1");
        result.setTRANID("ARQ1");
        result.setCODIGO("QGE0180");
        result.setNUMTASK("0116530006250");
        result.setMENSAJE("USUARIO-PASSWORD INCORRECTOS");
        
        response.setEjecutarResult(result);
        
        return response;
    }
    
    public EjecutarResponse generarResponseIpIncorrecta(){
        
        EjecutarResponse response = new EjecutarResponse();
        EjecutarResult result = new EjecutarResult();
        
        ResponseBansefi responseBansefi = new ResponseBansefi();
        
        responseBansefi.setCENTRO("");
        responseBansefi.setDC("");
        responseBansefi.setDISPONIBLE(new BigDecimal("0.00"));
        responseBansefi.setMONEDA("");
        responseBansefi.setSALDOCONTABLE(new BigDecimal("0.00"));
        responseBansefi.setTITULAR("");
        responseBansefi.setTOTALAUTORIZADO(new BigDecimal("0.00"));
        responseBansefi.setTOTALRETENIDO(new BigDecimal("0.00"));
        
        result.setResponseBansefi(responseBansefi);
        result.setESTATUS("1");
        result.setTRANID("ARQ1");
        result.setCODIGO("QGE0180");
        result.setNUMTASK("0116530006250");
        result.setMENSAJE("IP HEADER-LOGON INCORRECTA");
        
        response.setEjecutarResult(result);
        
        return response;
    }
    
}

