package mx.babel.bansefi.banksystem.endpoints;

import mx.babel.bansefi.banksystem.response.busquedacentro.ArrayOfResponseBansefi;
import mx.babel.bansefi.banksystem.response.busquedacentro.Ejecutar;
import mx.babel.bansefi.banksystem.response.busquedacentro.ResponseBansefi;
import mx.babel.bansefi.banksystem.utils.MarshallObjetoUtils;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.w3c.dom.Element;

@Endpoint
public class BusquedaCodigoCentroEndPoint {
	
	@PayloadRoot(localPart = "Ejecutar", namespace = "http://BansefiNSS/WebServicesNSS/TR_CON_MIN_CENTRO_2_TRN")
	@ResponsePayload
	public Element ejecutarBusquedaCodigoCentro(@RequestPayload Element element){
		Ejecutar objEjecutar = (Ejecutar) MarshallObjetoUtils.unMarshallObjeto(mx.babel.bansefi.banksystem.response.busquedacentro.Ejecutar.class, element);
		mx.babel.bansefi.banksystem.response.busquedacentro.EjecutarResponse response = new mx.babel.bansefi.banksystem.response.busquedacentro.EjecutarResponse();
		mx.babel.bansefi.banksystem.response.busquedacentro.EjecutarResult result = new mx.babel.bansefi.banksystem.response.busquedacentro.EjecutarResult();
		
		String [] arTipoResultado = {"OK", "ERROR", "NO_DATOS"};
		int indice = (int)Math.round(Math.random() * 2);
		String tipoResultado = arTipoResultado[0];
		
		ArrayOfResponseBansefi arrayOfResponseBansefi = generarResultados(tipoResultado);
		
		result.setResponseBansefi(arrayOfResponseBansefi);
		
		response.setEjecutarResult(result);
		
		System.out.println("About to return...");
		return MarshallObjetoUtils.marshallObjeto(mx.babel.bansefi.banksystem.response.busquedacentro.EjecutarResponse.class, response);
	}
	
	
	public ArrayOfResponseBansefi generarResultados(String tipoResultado){
		ResponseBansefi responseBansefi = new ResponseBansefi();
		ResponseBansefi.TRCONMINCENTRO2TRNO contextoSalida = new ResponseBansefi.TRCONMINCENTRO2TRNO();
		ResponseBansefi.TRCONMINCENTRO2TRNO.STDTRNMSJPARMV mensajes = new ResponseBansefi.TRCONMINCENTRO2TRNO.STDTRNMSJPARMV();
		ArrayOfResponseBansefi arrayOfResponseBansefi = new ArrayOfResponseBansefi();
		
		//int codigo = (int) Math.round(Math.random() * 21);
		
		int codigo = 1;
		
		mensajes.setTEXTCODE(codigo);
		mensajes.setTEXTARG1(tipoResultado);
		
		ResponseBansefi.TRCONMINCENTRO2TRNO.TRCONMINCENTROEVTZ campoSalida = new ResponseBansefi.TRCONMINCENTRO2TRNO.TRCONMINCENTROEVTZ();
		ResponseBansefi.TRCONMINCENTRO2TRNO.TRCONMINCENTROEVTZ.UOCENTROE campoSalidaCampos = new ResponseBansefi.TRCONMINCENTRO2TRNO.TRCONMINCENTROEVTZ.UOCENTROE(); 
		
		if (codigo != 1 && !tipoResultado.equals("OK")) {
			campoSalidaCampos.setNOMBCENTUO(" ");
			campoSalidaCampos.setCODINTERNOUO(" ");
			campoSalidaCampos.setFECHAALTAUO(0);
			campoSalidaCampos.setCODECVCENT(" ");
		} else {
			campoSalidaCampos.setNOMBCENTUO("Nombre del centro");
			campoSalidaCampos.setCODINTERNOUO("1234");
			campoSalidaCampos.setFECHAALTAUO(20150504);
			campoSalidaCampos.setCODECVCENT("0");
		}
		
		campoSalida.setUOCENTROE(campoSalidaCampos);
		contextoSalida.getSTDTRNMSJPARMV().add(mensajes);
		contextoSalida.setTRCONMINCENTROEVTZ(campoSalida);
		responseBansefi.setTRCONMINCENTRO2TRNO(contextoSalida);
		
		arrayOfResponseBansefi.getResponseBansefi().add(responseBansefi);
		
		return arrayOfResponseBansefi;
	}
	
}
