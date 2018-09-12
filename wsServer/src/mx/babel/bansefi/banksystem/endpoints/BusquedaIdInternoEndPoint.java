package mx.babel.bansefi.banksystem.endpoints;

import mx.babel.bansefi.banksystem.response.busquedaidinterna.ArrayOfResponseBansefi;
import mx.babel.bansefi.banksystem.response.busquedaidinterna.Ejecutar;
import mx.babel.bansefi.banksystem.response.busquedaidinterna.ResponseBansefi;
import mx.babel.bansefi.banksystem.utils.MarshallObjetoUtils;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.w3c.dom.Element;

@Endpoint
public class BusquedaIdInternoEndPoint {

	@PayloadRoot(localPart = "Ejecutar", namespace = "http://BansefiNSS/WebServicesNSS/TR_PE_CL_CB_DA_CNS_TRN")
	@ResponsePayload
	public Element ejecutarBusquedaIdInterno(@RequestPayload Element element){
		Ejecutar objEjecutar = (Ejecutar) MarshallObjetoUtils.unMarshallObjeto(mx.babel.bansefi.banksystem.response.busquedaidinterna.Ejecutar.class, element);
		
		mx.babel.bansefi.banksystem.response.busquedaidinterna.EjecutarResponse response = new mx.babel.bansefi.banksystem.response.busquedaidinterna.EjecutarResponse();
		mx.babel.bansefi.banksystem.response.busquedaidinterna.EjecutarResult result = new mx.babel.bansefi.banksystem.response.busquedaidinterna.EjecutarResult();
		
		String [] arTipoResultado = {"OK", "ERROR", "NO_DATOS"};
		int indice = (int)Math.round(Math.random() * 2);
		String tipoResultado = arTipoResultado[0];
		
		ArrayOfResponseBansefi arrayOfResponseBansefi = generarResultados(tipoResultado);
		
		result.setResponseBansefi(arrayOfResponseBansefi);
		
		response.setEjecutarResult(result);
		
		System.out.println("About to return...");
		return MarshallObjetoUtils.marshallObjeto(mx.babel.bansefi.banksystem.response.busquedaidinterna.EjecutarResponse.class, response);
	}
	
	public ArrayOfResponseBansefi generarResultados(String tipoResultado){
		ResponseBansefi responseBansefi = new ResponseBansefi();
		ResponseBansefi.TRPECLCBDACNSTRNO contextoSalida = new ResponseBansefi.TRPECLCBDACNSTRNO();
		ResponseBansefi.TRPECLCBDACNSTRNO.STDTRNMSJPARMV mensajes = new ResponseBansefi.TRPECLCBDACNSTRNO.STDTRNMSJPARMV();
		ArrayOfResponseBansefi arrayOfResponseBansefi = new ArrayOfResponseBansefi();
		
		//int codigo = (int) Math.round(Math.random() * 21);
		
		int codigo = 1;
		
		mensajes.setTEXTCODE(codigo);
		mensajes.setTEXTARG1(tipoResultado);
		
		ResponseBansefi.TRPECLCBDACNSTRNO.TRPECLCBDACNSEVTZ campoSalida = new ResponseBansefi.TRPECLCBDACNSTRNO.TRPECLCBDACNSEVTZ();
		
		if (codigo != 1 && !tipoResultado.equals("OK")) {
			campoSalida.setNOMB50(" ");
			campoSalida.setIDINTERNOPE(0);
		} else {
			campoSalida.setNOMB50("Nombre completo ");
			campoSalida.setIDINTERNOPE(12345);
		}
		
		contextoSalida.getSTDTRNMSJPARMV().add(mensajes);
		contextoSalida.setTRPECLCBDACNSEVTZ(campoSalida);;
		responseBansefi.setTRPECLCBDACNSTRNO(contextoSalida);
		
		arrayOfResponseBansefi.getResponseBansefi().add(responseBansefi);
		
		return arrayOfResponseBansefi;
	}
	
}
