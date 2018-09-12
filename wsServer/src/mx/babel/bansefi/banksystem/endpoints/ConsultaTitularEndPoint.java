package mx.babel.bansefi.banksystem.endpoints;



import java.math.BigInteger;

import mx.babel.bansefi.banksystem.response.consultatitular.ArrayOfResponseBansefi;
import mx.babel.bansefi.banksystem.response.consultatitular.EjecutarResponse;
import mx.babel.bansefi.banksystem.response.consultatitular.EjecutarResult;
import mx.babel.bansefi.banksystem.response.consultatitular.ResponseBansefi;
import mx.babel.bansefi.banksystem.response.consultatitular.Ejecutar;
import mx.babel.bansefi.banksystem.utils.MarshallObjetoUtils;

import org.aspectj.weaver.NewConstructorTypeMunger;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.w3c.dom.Element;

@Endpoint
public class ConsultaTitularEndPoint {

    @PayloadRoot(localPart = "Ejecutar", namespace = "http://10.200.14.110:3095/WebServices/CONSTITU")
    @ResponsePayload
    public Element ejecutarConsulta(@RequestPayload Element element){
    	Ejecutar objEjecutar = (Ejecutar) MarshallObjetoUtils.unMarshallObjeto(
				mx.babel.bansefi.banksystem.response.consultatitular.Ejecutar.class,
				element);
        EjecutarResponse response = null;
        int resultadoRandom = 0;
        switch (resultadoRandom){
        case 0:
            response = this.generarResponseOk(objEjecutar.getACUERDO());
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
    
    
    
    public EjecutarResponse generarResponseOk(BigInteger acuerdo){
        
        EjecutarResponse response = new EjecutarResponse();
        EjecutarResult result = new EjecutarResult();
        
        ArrayOfResponseBansefi arrayOfResponseBansefi = new ArrayOfResponseBansefi();
        
        ResponseBansefi responseBansefi = new ResponseBansefi();
        
        if(!acuerdo.equals(new BigInteger("2222222222"))){
	        responseBansefi.setNOMBRE("MARIA PEREZ ADRIAN");
	        responseBansefi.setIDRFC("PEAM6909124H3");
	        responseBansefi.setIDINTERNOPE(new BigInteger("0002215406"));
	        responseBansefi.setIDCURP("");
	        result.setResponseBansefi(arrayOfResponseBansefi);
	        result.setESTATUS("0");
	        result.setTRANID("CONSTITU");
	        result.setCODIGO("CHEA001");
	        result.setNUMTASK("0213120000569");
	        result.setMENSAJE("PROCESO EXITOSO");
        }else{
        	responseBansefi.setNOMBRE("");
	        responseBansefi.setIDRFC("");
	        responseBansefi.setIDINTERNOPE(null);
	        responseBansefi.setIDCURP("");
	        result.setResponseBansefi(arrayOfResponseBansefi);
	        result.setESTATUS("1");
	        result.setTRANID("CONSTITU");
	        result.setCODIGO("CHEA001");
	        result.setNUMTASK("0213120000569");
	        result.setMENSAJE("NO EXISTE");
        }
        response.setEjecutarResult(result);
        
        
        
        arrayOfResponseBansefi.getResponseBansefi().add(responseBansefi);
        
        response.setEjecutarResult(result);
        
        return response;
    }
    
    public EjecutarResponse generarResponseAcuerdoIncorrecto(){
        
        EjecutarResponse response = new EjecutarResponse();
        EjecutarResult result = new EjecutarResult();
        
        ResponseBansefi responseBansefi = new ResponseBansefi();
        
        responseBansefi.setNOMBRE("");
        responseBansefi.setIDRFC("");
        responseBansefi.setIDINTERNOPE(new BigInteger("0000000000"));
        responseBansefi.setIDCURP("");
        
        ArrayOfResponseBansefi arrayOfResponseBansefi = new ArrayOfResponseBansefi();
        arrayOfResponseBansefi.getResponseBansefi().add(responseBansefi);
        
        result.setResponseBansefi(arrayOfResponseBansefi);
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
        
        responseBansefi.setNOMBRE("");
        responseBansefi.setIDRFC("");
        responseBansefi.setIDINTERNOPE(new BigInteger("0"));
        responseBansefi.setIDCURP("");
        
        ArrayOfResponseBansefi arrayOfResponseBansefi = new ArrayOfResponseBansefi();
        arrayOfResponseBansefi.getResponseBansefi().add(responseBansefi);
        
        result.setResponseBansefi(arrayOfResponseBansefi);
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
        
        responseBansefi.setNOMBRE("");
        responseBansefi.setIDRFC("");
        responseBansefi.setIDINTERNOPE(new BigInteger("0"));
        responseBansefi.setIDCURP("");
        
        ArrayOfResponseBansefi arrayOfResponseBansefi = new ArrayOfResponseBansefi();
        arrayOfResponseBansefi.getResponseBansefi().add(responseBansefi);
        
        result.setResponseBansefi(arrayOfResponseBansefi);
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
        
        responseBansefi.setNOMBRE("");
        responseBansefi.setIDRFC("");
        responseBansefi.setIDINTERNOPE(new BigInteger("0"));
        responseBansefi.setIDCURP("");
        
        ArrayOfResponseBansefi arrayOfResponseBansefi = new ArrayOfResponseBansefi();
        arrayOfResponseBansefi.getResponseBansefi().add(responseBansefi);
        
        result.setResponseBansefi(arrayOfResponseBansefi);
        result.setESTATUS("1");
        result.setTRANID("ARQ1");
        result.setCODIGO("QGE0180");
        result.setNUMTASK("0116530006250");
        result.setMENSAJE("IP HEADER-LOGON INCORRECTA");
        
        response.setEjecutarResult(result);
        
        return response;
    }
    
}
