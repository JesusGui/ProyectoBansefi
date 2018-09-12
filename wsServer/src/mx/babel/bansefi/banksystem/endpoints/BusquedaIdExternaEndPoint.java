package mx.babel.bansefi.banksystem.endpoints;

import mx.babel.bansefi.banksystem.response.busquedaidexterna.ArrayOfResponseBansefi;
import mx.babel.bansefi.banksystem.response.busquedaidexterna.Ejecutar;
import mx.babel.bansefi.banksystem.response.busquedaidexterna.ResponseBansefi;
import mx.babel.bansefi.banksystem.utils.MarshallObjetoUtils;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.w3c.dom.Element;

@Endpoint
public class BusquedaIdExternaEndPoint {
	@PayloadRoot(localPart = "Ejecutar", namespace = "http://BansefiNSS/WebServicesNSS/TR_PE_CB_ID_EXTERNA_CNS_TRN")
	@ResponsePayload
	public Element ejecutarBusquedaIdExterna(@RequestPayload Element element){
		Ejecutar objEjecutar = (Ejecutar) MarshallObjetoUtils.unMarshallObjeto(mx.babel.bansefi.banksystem.response.busquedaidexterna.Ejecutar.class, element);
		
		mx.babel.bansefi.banksystem.response.busquedaidexterna.EjecutarResponse response = new mx.babel.bansefi.banksystem.response.busquedaidexterna.EjecutarResponse();
		mx.babel.bansefi.banksystem.response.busquedaidexterna.EjecutarResult result = new mx.babel.bansefi.banksystem.response.busquedaidexterna.EjecutarResult();
		
		String [] arTipoResultado = {"OK", "ERROR", "NO_DATOS"};
		int indice = (int)Math.round(Math.random() * 2);
		String tipoResultado = arTipoResultado[0];
		
		ArrayOfResponseBansefi arrayOfResponseBansefi = generarResultados(tipoResultado);
		
		result.setResponseBansefi(arrayOfResponseBansefi);
		
		response.setEjecutarResult(result);
		
		System.out.println("About to return...");
		return MarshallObjetoUtils.marshallObjeto(mx.babel.bansefi.banksystem.response.busquedaidexterna.EjecutarResponse.class, response);
	}
	
	public ArrayOfResponseBansefi generarResultados(String tipoResultado){
		ResponseBansefi responseBansefi = new ResponseBansefi();
		ResponseBansefi.TRPECBIDEXTERNACNSTRNO paginacion = new ResponseBansefi.TRPECBIDEXTERNACNSTRNO();
		ResponseBansefi.TRPECBIDEXTERNACNSTRNO.TRPECBIDEXTERNACNSEVTZ listaCamposSalida = new ResponseBansefi.TRPECBIDEXTERNACNSTRNO.TRPECBIDEXTERNACNSEVTZ();
		ResponseBansefi.TRPECBIDEXTERNACNSTRNO.STDTRNMSJPARMV mensajes = new ResponseBansefi.TRPECBIDEXTERNACNSTRNO.STDTRNMSJPARMV();
		ArrayOfResponseBansefi arrayOfResponseBansefi = new ArrayOfResponseBansefi();
		
		//int codigo = (int) Math.round(Math.random() * 21);
		
		int codigo = 1;
		
		mensajes.setTEXTCODE(codigo);
		mensajes.setTEXTARG1(tipoResultado);
		
		if (codigo == 1 && tipoResultado.equals("OK")) {
			paginacion.setNUMBEROFRECORDS(20);
			paginacion.setMOREDATAIN(10);
			for (int i = 0; i < 10; i++) {
				ResponseBansefi.TRPECBIDEXTERNACNSTRNO.TRPECBIDEXTERNACNSEVTZ.TRPECBIDEXTERNACNSEVTV campoSalida = new ResponseBansefi.TRPECBIDEXTERNACNSTRNO.TRPECBIDEXTERNACNSEVTZ.TRPECBIDEXTERNACNSEVTV();
				campoSalida.setNOMB50("Nombre completo " + i);
				campoSalida.setIDINTERNOPE(i);
				campoSalida.setIDEXTPE("id externa " + i);
				campoSalida.setFECNCTOCONSTPE(i);
				listaCamposSalida.getTRPECBIDEXTERNACNSEVTV().add(campoSalida);
			}
		} else {
			paginacion.setNUMBEROFRECORDS(0);
			paginacion.setMOREDATAIN(0);
			ResponseBansefi.TRPECBIDEXTERNACNSTRNO.TRPECBIDEXTERNACNSEVTZ.TRPECBIDEXTERNACNSEVTV campoSalida = new ResponseBansefi.TRPECBIDEXTERNACNSTRNO.TRPECBIDEXTERNACNSEVTZ.TRPECBIDEXTERNACNSEVTV();
			campoSalida.setNOMB50(" ");
			campoSalida.setIDINTERNOPE(0);
			campoSalida.setIDEXTPE(" ");
			campoSalida.setFECNCTOCONSTPE(0);
			listaCamposSalida.getTRPECBIDEXTERNACNSEVTV().add(campoSalida);	
		}
		
		
		paginacion.getSTDTRNMSJPARMV().add(mensajes);
		paginacion.setTRPECBIDEXTERNACNSEVTZ(listaCamposSalida);
		responseBansefi.setTRPECBIDEXTERNACNSTRNO(paginacion);
		
		arrayOfResponseBansefi.getResponseBansefi().add(responseBansefi);
		
		return arrayOfResponseBansefi;
	}
}
