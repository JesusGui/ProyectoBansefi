package mx.babel.bansefi.banksystem.endpoints;

import mx.babel.bansefi.banksystem.response.consultamovimientos.ArrayOfResponseBansefi;
import mx.babel.bansefi.banksystem.response.consultamovimientos.EjecutarResponse;
import mx.babel.bansefi.banksystem.response.consultamovimientos.EjecutarResult;
import mx.babel.bansefi.banksystem.response.consultamovimientos.ResponseBansefi;
import mx.babel.bansefi.banksystem.utils.MarshallObjetoUtils;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.w3c.dom.Element;

@Endpoint
public class ConsultaMovimientosEndPoint {
	
	@PayloadRoot(localPart = "Ejecutar", namespace = "http://10.200.14.110:3095/WebServices/VTCONMOV")
    @ResponsePayload									
    public Element ejecutarConsulta(@RequestPayload Element element){
        EjecutarResponse response = null;
        response = this.generarResponseOk();
        
        return MarshallObjetoUtils.marshallObjeto(EjecutarResponse.class ,response);
    }
    
    public EjecutarResponse generarResponseOk(){
        
        EjecutarResponse response = new EjecutarResponse();
        EjecutarResult result = new EjecutarResult();
        ArrayOfResponseBansefi results = new ArrayOfResponseBansefi();
        
        int numMovimientos = (int) Math.round(Math.random() * 200);
		for(int i = 0; i< numMovimientos; i++){
	        ResponseBansefi responseBansefi = new ResponseBansefi();	        
	        responseBansefi.setCONCEPTO("Ingreso en caja 124352");
	        responseBansefi.setFECHAOPER("12/12/2014");
	        responseBansefi.setFECHAVALOR("12/12/2014");
	        responseBansefi.setOFTERMINAL("12018104");
	        responseBansefi.setIMPORTE("10000");
	        responseBansefi.setSALDO("10000");
	        results.getResponseBansefi().add(responseBansefi);
		}
        result.setResponseBansefi(results);
        result.setESTATUS("0");
        result.setTRANID("VTCONMOV");
        result.setCODIGO("CODIGO");
        result.setNUMTASK("1231231231");
        result.setMENSAJE("PROCESO EXITOSO");
        
        response.setEjecutarResult(result);
        response.setEjecutarResult(result);
        
        return response;
    }
}
