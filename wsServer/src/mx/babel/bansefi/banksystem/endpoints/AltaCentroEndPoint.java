package mx.babel.bansefi.banksystem.endpoints;

import mx.babel.bansefi.banksystem.response.altacentro.ArrayOfResponseBansefi;
import mx.babel.bansefi.banksystem.response.altacentro.Ejecutar;
import mx.babel.bansefi.banksystem.response.altacentro.ResponseBansefi;
import mx.babel.bansefi.banksystem.utils.MarshallObjetoUtils;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.w3c.dom.Element;

@Endpoint
public class AltaCentroEndPoint {
	@PayloadRoot(localPart = "Ejecutar", namespace = "http://BansefiNSS/WebServicesNSS/TR_ALTA_CENTRO_TRN")
	@ResponsePayload
	public Element ejecutarAltaCentro(@RequestPayload Element element){
		Ejecutar objEjecutar = (Ejecutar) MarshallObjetoUtils.unMarshallObjeto(mx.babel.bansefi.banksystem.response.altacentro.Ejecutar.class, element);
		
		mx.babel.bansefi.banksystem.response.altacentro.EjecutarResponse response = new mx.babel.bansefi.banksystem.response.altacentro.EjecutarResponse();
		mx.babel.bansefi.banksystem.response.altacentro.EjecutarResult result = new mx.babel.bansefi.banksystem.response.altacentro.EjecutarResult();
		
		String [] arTipoResultado = {"OK", "ERROR", "NO_DATOS"};
		int indice = (int)Math.round(Math.random() * 2);
		String tipoResultado = arTipoResultado[0];
		
		ArrayOfResponseBansefi arrayOfResponseBansefi = generarResultados(tipoResultado);
		
		result.setResponseBansefi(arrayOfResponseBansefi);
		
		response.setEjecutarResult(result);
		
		System.out.println("About to return...");
		return MarshallObjetoUtils.marshallObjeto(mx.babel.bansefi.banksystem.response.altacentro.EjecutarResponse.class, response);
	}
	
	public ArrayOfResponseBansefi generarResultados(String tipoResultado){
		ResponseBansefi responseBansefi = new ResponseBansefi();
		ResponseBansefi.TRALTACENTROTRNO salida = new ResponseBansefi.TRALTACENTROTRNO();
		ResponseBansefi.TRALTACENTROTRNO.STDTRNMSJPARMV mensajes = new ResponseBansefi.TRALTACENTROTRNO.STDTRNMSJPARMV();
		ArrayOfResponseBansefi arrayOfResponseBansefi = new ArrayOfResponseBansefi();
		
		//int codigo = (int) Math.round(Math.random() * 21);
		
		int codigo = 1;
		
		mensajes.setTEXTCODE(codigo);
		mensajes.setTEXTARG1(tipoResultado);
		
		salida.setRTRNCD(codigo);
		
		salida.getSTDTRNMSJPARMV().add(mensajes);
		responseBansefi.setTRALTACENTROTRNO(salida);
		
		arrayOfResponseBansefi.getResponseBansefi().add(responseBansefi);
		
		return arrayOfResponseBansefi;
	}
}
