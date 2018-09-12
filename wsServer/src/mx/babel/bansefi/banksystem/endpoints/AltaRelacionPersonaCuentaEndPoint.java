package mx.babel.bansefi.banksystem.endpoints;

import mx.babel.bansefi.banksystem.response.altarelacionpersonacuenta.ResponseBansefi;
import mx.babel.bansefi.banksystem.response.altarelacionpersonacuenta.ArrayOfResponseBansefi;
import mx.babel.bansefi.banksystem.response.altarelacionpersonacuenta.EjecutarResponse;
import mx.babel.bansefi.banksystem.response.altarelacionpersonacuenta.EjecutarResult;
import mx.babel.bansefi.banksystem.utils.MarshallObjetoUtils;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.w3c.dom.Element;

@Endpoint
public class AltaRelacionPersonaCuentaEndPoint {

	@PayloadRoot(localPart = "Ejecutar", namespace = "http://BansefiNSS/WebServicesNSS/TR_ALTA_RP_PANT_TRN")
	@ResponsePayload
	public Element ejecutarAltaRelacion(@RequestPayload Element element){
		EjecutarResponse response = new mx.babel.bansefi.banksystem.response.altarelacionpersonacuenta.EjecutarResponse();
		EjecutarResult result = new mx.babel.bansefi.banksystem.response.altarelacionpersonacuenta.EjecutarResult();
		
		ArrayOfResponseBansefi arrayOfResponseBansefi = generarResultados();
		
		result.setResponseBansefi(arrayOfResponseBansefi);
		
		response.setEjecutarResult(result);
		
		System.out.println("About to return...");
		return MarshallObjetoUtils.marshallObjeto(mx.babel.bansefi.banksystem.response.altarelacionpersonacuenta.EjecutarResponse.class, response);
	}
	
	public ArrayOfResponseBansefi generarResultados(){
		ResponseBansefi responseBansefi = new ResponseBansefi();
		ResponseBansefi.TRALTARPPANTTRNO alta = new ResponseBansefi.TRALTARPPANTTRNO();
		ResponseBansefi.TRALTARPPANTTRNO.STDTRNMSJPARMV resultado = new ResponseBansefi.TRALTARPPANTTRNO.STDTRNMSJPARMV();
		ResponseBansefi.TRALTARPPANTTRNO.STDTRNOPARMV fecha= new ResponseBansefi.TRALTARPPANTTRNO.STDTRNOPARMV();
		ResponseBansefi.TRALTARPPANTTRNO.TRALTARPPANTEVTZ datos = new ResponseBansefi.TRALTARPPANTTRNO.TRALTARPPANTEVTZ();
		ResponseBansefi.TRALTARPPANTTRNO.TRALTARPPANTEVTZ.RPPERSRLACE persona = new ResponseBansefi.TRALTARPPANTTRNO.TRALTARPPANTEVTZ.RPPERSRLACE();
		ArrayOfResponseBansefi arrayOfResponseBansefi = new ArrayOfResponseBansefi();
		
		persona.setCODECVPERSAC("Alta");;
		datos.setRPPERSRLACE(persona);
		
		resultado.setTEXTCODE(0);
		resultado.setTEXTARG1("Ejecucion correcta");
				
		alta.setRTRNCD(0);
		alta.setSTDTRNOPARMV(fecha);
		alta.setTRALTARPPANTEVTZ(datos);
		
		responseBansefi.setTRALTARPPANTTRNO(alta);
		arrayOfResponseBansefi.getResponseBansefi().add(responseBansefi);
				
		return arrayOfResponseBansefi;
	}
}
