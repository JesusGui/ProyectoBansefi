package mx.babel.bansefi.banksystem.endpoints;

import mx.babel.bansefi.banksystem.response.altaCliente.ArrayOfResponseBansefi;
import mx.babel.bansefi.banksystem.response.altaCliente.Ejecutar;
import mx.babel.bansefi.banksystem.response.altaCliente.ResponseBansefi;
import mx.babel.bansefi.banksystem.utils.MarshallObjetoUtils;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.w3c.dom.Element;

@Endpoint
public class AltaClienteEndPoint {
	
	@PayloadRoot(localPart = "Ejecutar", namespace = "http://BansefiNSS/WebServicesNSS/TR_PE_ALTA_INDV_TRN")
	@ResponsePayload
	public Element ejecutarBusquedaNombre(@RequestPayload Element element){
		Ejecutar objEjecutar = (Ejecutar) MarshallObjetoUtils.unMarshallObjeto(mx.babel.bansefi.banksystem.response.altaCliente.Ejecutar.class, element);
		
		mx.babel.bansefi.banksystem.response.altaCliente.EjecutarResponse response = new mx.babel.bansefi.banksystem.response.altaCliente.EjecutarResponse();
		mx.babel.bansefi.banksystem.response.altaCliente.EjecutarResult result = new mx.babel.bansefi.banksystem.response.altaCliente.EjecutarResult();
		
		ArrayOfResponseBansefi arrayOfResponseBansefi = generarResultados();
		
		result.setResponseBansefi(arrayOfResponseBansefi);
		
		response.setEjecutarResult(result);
		
		System.out.println("About to return...");
		return MarshallObjetoUtils.marshallObjeto(mx.babel.bansefi.banksystem.response.altaCliente.EjecutarResponse.class, response);
	}
	
	public ArrayOfResponseBansefi generarResultados(){
		ResponseBansefi responseBansefi = new ResponseBansefi();
		ResponseBansefi.TRPEALTAINDVTRNO alta = new ResponseBansefi.TRPEALTAINDVTRNO();
		ResponseBansefi.TRPEALTAINDVTRNO.STDTRNMSJPARMV  resultado = new ResponseBansefi.TRPEALTAINDVTRNO.STDTRNMSJPARMV();
		ResponseBansefi.TRPEALTAINDVTRNO.STDTRNOPARMV fecha = new ResponseBansefi.TRPEALTAINDVTRNO.STDTRNOPARMV();
		ResponseBansefi.TRPEALTAINDVTRNO.TRPEALTAINDVEVTZ valores = new ResponseBansefi.TRPEALTAINDVTRNO.TRPEALTAINDVEVTZ();
		ResponseBansefi.TRPEALTAINDVTRNO.TRPEALTAINDVEVTZ.PEPERSP pepersp = new ResponseBansefi.TRPEALTAINDVTRNO.TRPEALTAINDVEVTZ.PEPERSP();
		ArrayOfResponseBansefi arrayOfResponseBansefi = new ArrayOfResponseBansefi();
	
		resultado.setTEXTCODE(0);
		resultado.setTEXTARG1("Ejecucion correcta");
		
		pepersp.setIDINTERNOPE(19);	
		valores.setPEPERSP(pepersp);
		
		alta.setRTRNCD(1);
		alta.setSTDTRNOPARMV(fecha);
		alta.setTRPEALTAINDVEVTZ(valores);
		
		responseBansefi.setTRPEALTAINDVTRNO(alta);
		arrayOfResponseBansefi.getResponseBansefi().add(responseBansefi);
		
		return arrayOfResponseBansefi;
	}
}
