package mx.babel.bansefi.banksystem.endpoints;

import mx.babel.bansefi.banksystem.response.consultacatalogo.ArrayOfResponseBansefi;
import mx.babel.bansefi.banksystem.response.consultacatalogo.EjecutarResponse;
import mx.babel.bansefi.banksystem.response.consultacatalogo.EjecutarResult;
import mx.babel.bansefi.banksystem.response.consultacatalogo.ResponseBansefi;
import mx.babel.bansefi.banksystem.response.consultacatalogo.Ejecutar;
import mx.babel.bansefi.banksystem.utils.MarshallObjetoUtils;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.w3c.dom.Element;

@Endpoint
public class ConsultaCatalogoEndPoint {

	@PayloadRoot(localPart = "Ejecutar", namespace = "http://10.200.14.110:3095/WebServices/SNTESN00")
	@ResponsePayload
	public Element ejecutarConsultaCatalogo(@RequestPayload Element element){
		Ejecutar objEjecutar = (Ejecutar) MarshallObjetoUtils.unMarshallObjeto(Ejecutar.class, element);
		
		EjecutarResponse response = new EjecutarResponse();
		EjecutarResult result = new EjecutarResult();
		
		result.setESTATUS("1");
		ArrayOfResponseBansefi arrayOfResponseBansefi = generarResultados();
		result.setResponseBansefi(arrayOfResponseBansefi);
		
		response.setEjecutarResult(result);
		 
	    System.out.println("About to return...");
	    return MarshallObjetoUtils.marshallObjeto(EjecutarResponse.class, response);
    }

	private ArrayOfResponseBansefi generarResultados() {
		ArrayOfResponseBansefi array = new ArrayOfResponseBansefi();
		for(int i=0; i<1000;i++){
			ResponseBansefi response = new ResponseBansefi();
			String number = String.format("%04d", i+1);
			response.setCLAVE(number);
			response.setCLAVEPAG(number);
			response.setDESCRIPCION(number+"Never knows best");
			response.setTIPO(number);
			array.getResponseBansefi().add(response);
		}
		return array;
	}
}
