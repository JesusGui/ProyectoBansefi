package mx.babel.bansefi.banksystem.endpoints;

import mx.babel.bansefi.banksystem.response.listacentros.ArrayOfResponseBansefi;
import mx.babel.bansefi.banksystem.response.listacentros.Ejecutar;
import mx.babel.bansefi.banksystem.response.listacentros.ResponseBansefi;
import mx.babel.bansefi.banksystem.utils.MarshallObjetoUtils;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.w3c.dom.Element;

@Endpoint
public class BusquedaNombreCentroEndPoint {
	
	@PayloadRoot(localPart = "Ejecutar", namespace = "http://BansefiNSS/WebServicesNSS/TR_LIST_NOMB_CENT_TRN")
	@ResponsePayload
	public Element ejecutarBusquedaNombreCentro(@RequestPayload Element element){
		Ejecutar objEjecutar = (Ejecutar) MarshallObjetoUtils.unMarshallObjeto(mx.babel.bansefi.banksystem.response.listacentros.Ejecutar.class, element);
		mx.babel.bansefi.banksystem.response.listacentros.EjecutarResponse response = new mx.babel.bansefi.banksystem.response.listacentros.EjecutarResponse();
		mx.babel.bansefi.banksystem.response.listacentros.EjecutarResult result = new mx.babel.bansefi.banksystem.response.listacentros.EjecutarResult();
		
		String [] arTipoResultado = {"OK", "ERROR", "NO_DATOS"};
		int indice = (int)Math.round(Math.random() * 2);
		String tipoResultado = arTipoResultado[0];
		
		ArrayOfResponseBansefi arrayOfResponseBansefi = generarResultados(tipoResultado);
		
		result.setResponseBansefi(arrayOfResponseBansefi);
		
		response.setEjecutarResult(result);
		
		System.out.println("About to return...");
		return MarshallObjetoUtils.marshallObjeto(mx.babel.bansefi.banksystem.response.listacentros.EjecutarResponse.class, response);
	}
	
	public ArrayOfResponseBansefi generarResultados(String tipoResultado){
		ResponseBansefi responseBansefi = new ResponseBansefi();
		ResponseBansefi.TRLISTNOMBCENTTRNO contextoSalida = new ResponseBansefi.TRLISTNOMBCENTTRNO();
		
		ResponseBansefi.TRLISTNOMBCENTTRNO.STDTRNMSJPARMV mensajes = new ResponseBansefi.TRLISTNOMBCENTTRNO.STDTRNMSJPARMV();
		ArrayOfResponseBansefi arrayOfResponseBansefi = new ArrayOfResponseBansefi();
		
		//int codigo = (int) Math.round(Math.random() * 21);
		
		int codigo = 1;
		
		mensajes.setTEXTCODE(codigo);
		mensajes.setTEXTARG1(tipoResultado);
		
		ResponseBansefi.TRLISTNOMBCENTTRNO.TRLISTNOMBCENTEVTZ camposSalida = new ResponseBansefi.TRLISTNOMBCENTTRNO.TRLISTNOMBCENTEVTZ();
		
		if (codigo != 1 && !tipoResultado.equals("OK")) {
			ResponseBansefi.TRLISTNOMBCENTTRNO.TRLISTNOMBCENTEVTZ.UOLAV2 campoSalidaCampos = new ResponseBansefi.TRLISTNOMBCENTTRNO.TRLISTNOMBCENTEVTZ.UOLAV2(); 
			campoSalidaCampos.setNOMBCENTUO(" ");
			campoSalidaCampos.setCODINTERNOUO(" ");
			campoSalidaCampos.setFECHAALTAUO(0);
			campoSalidaCampos.setCODECVCENT(" ");
		} else {
			contextoSalida.setNUMBEROFRECORDS(20);
			contextoSalida.setMOREDATAIN(10);
			for (int i = 0; i < 10; i++) {
				ResponseBansefi.TRLISTNOMBCENTTRNO.TRLISTNOMBCENTEVTZ.UOLAV2 campoSalidaCampos = new ResponseBansefi.TRLISTNOMBCENTTRNO.TRLISTNOMBCENTEVTZ.UOLAV2(); 
				campoSalidaCampos.setNOMBCENTUO("Nombre de la empresa " + i);
				campoSalidaCampos.setCODINTERNOUO("I0" + i);
				campoSalidaCampos.setFECHAALTAUO(20150504 + i);
				campoSalidaCampos.setCODECVCENT("E0" + i);
				camposSalida.getUOLAV2().add(campoSalidaCampos);
			}
		}
		
		contextoSalida.getSTDTRNMSJPARMV().add(mensajes);
		contextoSalida.setTRLISTNOMBCENTEVTZ(camposSalida);
		responseBansefi.setTRLISTNOMBCENTTRNO(contextoSalida);
		
		arrayOfResponseBansefi.getResponseBansefi().add(responseBansefi);
		
		return arrayOfResponseBansefi;
	}
}
