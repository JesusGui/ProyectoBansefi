package mx.babel.bansefi.banksystem.endpoints;

import mx.babel.bansefi.banksystem.response.altarelacioncuentacuenta.ResponseBansefi;
import mx.babel.bansefi.banksystem.response.altarelacioncuentacuenta.ArrayOfResponseBansefi;
import mx.babel.bansefi.banksystem.response.altarelacioncuentacuenta.EjecutarResponse;
import mx.babel.bansefi.banksystem.response.altarelacioncuentacuenta.EjecutarResult;
import mx.babel.bansefi.banksystem.utils.MarshallObjetoUtils;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.w3c.dom.Element;

@Endpoint
public class AltaRelacionCuentaCuentaEndPoint {

	@PayloadRoot(localPart = "Ejecutar", namespace = "http://BansefiNSS/WebServicesNSS/TR_ALTA_RX_PANT_TRN")
	@ResponsePayload
	public Element ejecutarAltaRelacion(@RequestPayload Element element){
		EjecutarResponse response = new EjecutarResponse();
		EjecutarResult result = new EjecutarResult();
		
		ArrayOfResponseBansefi arrayOfResponseBansefi = generarResultados();
		
		result.setResponseBansefi(arrayOfResponseBansefi);
		
		response.setEjecutarResult(result);
		
		System.out.println("About to return...");
		return MarshallObjetoUtils.marshallObjeto(mx.babel.bansefi.banksystem.response.altarelacionpersonacuenta.EjecutarResponse.class, response);
	}
	
	public ArrayOfResponseBansefi generarResultados(){
		ResponseBansefi responseBansefi = new ResponseBansefi();
		ResponseBansefi.TRALTARXPANTTRNO alta = new ResponseBansefi.TRALTARXPANTTRNO();
		ResponseBansefi.TRALTARXPANTTRNO.STDTRNMSJPARMV resultado = new ResponseBansefi.TRALTARXPANTTRNO.STDTRNMSJPARMV();
		ResponseBansefi.TRALTARXPANTTRNO.STDTRNOPARMV fecha= new ResponseBansefi.TRALTARXPANTTRNO.STDTRNOPARMV();
		ResponseBansefi.TRALTARXPANTTRNO.TRALTARXPANTEVTZ datos = new ResponseBansefi.TRALTARXPANTTRNO.TRALTARXPANTEVTZ();
		ResponseBansefi.TRALTARXPANTTRNO.TRALTARXPANTEVTZ.RXACRLACE relacion = new ResponseBansefi.TRALTARXPANTTRNO.TRALTARXPANTEVTZ.RXACRLACE();
		ArrayOfResponseBansefi arrayOfResponseBansefi = new ArrayOfResponseBansefi();
		
		relacion.setCODRLACAC("Alta");;
		datos.setRXACRLACE(relacion);
		
		resultado.setTEXTCODE(0);
		resultado.setTEXTARG1("Ejecucion correcta");
				
		alta.setRTRNCD(1);
		alta.setSTDTRNOPARMV(fecha);
		alta.setTRALTARXPANTEVTZ(datos);
		
		responseBansefi.setTRALTARXPANTTRNO(alta);
		arrayOfResponseBansefi.getResponseBansefi().add(responseBansefi);
				
		return arrayOfResponseBansefi;
	}
}
