package mx.babel.bansefi.banksystem.endpoints;

import java.util.List;

import mx.babel.bansefi.banksystem.response.altaPerfilTransaccionalCliente.ArrayOfResponseBansefi;
import mx.babel.bansefi.banksystem.response.altaPerfilTransaccionalCliente.Ejecutar;
import mx.babel.bansefi.banksystem.response.altaPerfilTransaccionalCliente.ResponseBansefi;
import mx.babel.bansefi.banksystem.utils.MarshallObjetoUtils;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.w3c.dom.Element;

@Endpoint
public class AltaPerfilTransaccionalClienteEndPoint {
	
	@PayloadRoot(localPart = "Ejecutar", namespace = "http://BansefiNSS/WebServicesNSS/PE_ALTA_INDV_PERF_TRAN_TRN")
	@ResponsePayload
	public Element ejecutarBusquedaNombre(@RequestPayload Element element){
		Ejecutar objEjecutar = (Ejecutar) MarshallObjetoUtils.unMarshallObjeto(mx.babel.bansefi.banksystem.response.altaPerfilTransaccionalCliente.Ejecutar.class, element);
		
		mx.babel.bansefi.banksystem.response.altaPerfilTransaccionalCliente.EjecutarResponse response = new mx.babel.bansefi.banksystem.response.altaPerfilTransaccionalCliente.EjecutarResponse();
		mx.babel.bansefi.banksystem.response.altaPerfilTransaccionalCliente.EjecutarResult result = new mx.babel.bansefi.banksystem.response.altaPerfilTransaccionalCliente.EjecutarResult();
		
		ArrayOfResponseBansefi arrayOfResponseBansefi = generarResultados();
		
		result.setResponseBansefi(arrayOfResponseBansefi);
		
		response.setEjecutarResult(result);
		
		System.out.println("About to return...");
		return MarshallObjetoUtils.marshallObjeto(mx.babel.bansefi.banksystem.response.altaPerfilTransaccionalCliente.EjecutarResponse.class, response);
	}
	
	public ArrayOfResponseBansefi generarResultados(){
		ResponseBansefi responseBansefi = new ResponseBansefi();
		ResponseBansefi.PEALTAINDVPERFTRANTRNO alta = new ResponseBansefi.PEALTAINDVPERFTRANTRNO();
		ResponseBansefi.PEALTAINDVPERFTRANTRNO.STDTRNMSJPARMV resultado = new ResponseBansefi.PEALTAINDVPERFTRANTRNO.STDTRNMSJPARMV();
		ResponseBansefi.PEALTAINDVPERFTRANTRNO.STDTRNOPARMV fecha= new ResponseBansefi.PEALTAINDVPERFTRANTRNO.STDTRNOPARMV();
		ArrayOfResponseBansefi arrayOfResponseBansefi = new ArrayOfResponseBansefi();
		
		resultado.setTEXTCODE(0);
		resultado.setTEXTARG1("Ejecucion correcta");
				
		alta.setRTRNCD(1);
		alta.setSTDTRNOPARMV(fecha);
		
		responseBansefi.setPEALTAINDVPERFTRANTRNO(alta);
		arrayOfResponseBansefi.getResponseBansefi().add(responseBansefi);
				
		return arrayOfResponseBansefi;
	}
}
