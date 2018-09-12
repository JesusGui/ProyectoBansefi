package mx.babel.bansefi.banksystem.endpoints;

import mx.babel.bansefi.banksystem.response.busquedagrupos.ArrayOfResponseBansefi;
import mx.babel.bansefi.banksystem.response.busquedagrupos.Ejecutar;
import mx.babel.bansefi.banksystem.response.busquedagrupos.ResponseBansefi;
import mx.babel.bansefi.banksystem.utils.MarshallObjetoUtils;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.w3c.dom.Element;

@Endpoint
public class BusquedasGrupoEndPoint {

	@PayloadRoot(localPart = "Ejecutar", namespace = "http://BansefiNSS/WebServicesNSS/TR_GR_LST_TRN")
	@ResponsePayload
	public Element ejecutarBusquedasGrupo(@RequestPayload Element element) {
		Ejecutar objEjecutar = (Ejecutar) MarshallObjetoUtils
				.unMarshallObjeto(
						mx.babel.bansefi.banksystem.response.busquedagrupos.Ejecutar.class,
						element);
		
		mx.babel.bansefi.banksystem.response.busquedagrupos.EjecutarResponse response = new mx.babel.bansefi.banksystem.response.busquedagrupos.EjecutarResponse();
		mx.babel.bansefi.banksystem.response.busquedagrupos.EjecutarResult result = new mx.babel.bansefi.banksystem.response.busquedagrupos.EjecutarResult();

		String[] arTipoResultado = { "OK", "ERROR", "NO_DATOS" };
		int indice = 0;
		String tipoResultado = arTipoResultado[indice];
		
		ArrayOfResponseBansefi arrayOfResponseBansefi = null;
		
		if(objEjecutar.getTRGRLSTTRNI().getTRGRLSTEVTY().getGRLACB() != null){
			arrayOfResponseBansefi = generarResultadosNombres(tipoResultado);
		}else if(objEjecutar.getTRGRLSTTRNI().getTRGRLSTEVTY().getGRGRPP() != null){
			arrayOfResponseBansefi = generarResultadoId(tipoResultado);
		}
		
		

		result.setResponseBansefi(arrayOfResponseBansefi);

		response.setEjecutarResult(result);

		System.out.println("About to return...");
		return MarshallObjetoUtils
				.marshallObjeto(
						mx.babel.bansefi.banksystem.response.busquedagrupos.EjecutarResponse.class,
						response);
	}

	private ArrayOfResponseBansefi generarResultadoId(String tipoResultado) {
		ResponseBansefi responseBansefi = new ResponseBansefi();
		ResponseBansefi.TRGRLSTTRNO paginacion = new ResponseBansefi.TRGRLSTTRNO();
		ResponseBansefi.TRGRLSTTRNO.STDTRNMSJPARMV mensajes = new ResponseBansefi.TRGRLSTTRNO.STDTRNMSJPARMV();
		ResponseBansefi.TRGRLSTTRNO.TRGRLSTEVTZ listaCampoSalida = new ResponseBansefi.TRGRLSTTRNO.TRGRLSTEVTZ();
		
		ArrayOfResponseBansefi arrayOfResponseBansefi = new ArrayOfResponseBansefi();
		
		paginacion.setNUMBEROFRECORDS(1);
		paginacion.setMOREDATAIN(0);
		
		if(tipoResultado.equals("OK")){
			mensajes.setTEXTCODE(0);
			mensajes.setTEXTARG1(tipoResultado);
		}else if(tipoResultado.equals("ERROR")){
			mensajes.setTEXTCODE(1);
			mensajes.setTEXTARG1(tipoResultado);
		}else if(tipoResultado.equals("NO_DATOS")){
			mensajes.setTEXTCODE(2);
			mensajes.setTEXTARG1(tipoResultado);
		}
		
		for (int i = 0; i < 1; i++) {
			ResponseBansefi.TRGRLSTTRNO.TRGRLSTEVTZ.TRGRLSTEVTV campoSalida = new ResponseBansefi.TRGRLSTTRNO.TRGRLSTEVTZ.TRGRLSTEVTV();
			campoSalida.setNOMBGR("Nombre grupo" + i);
			campoSalida.setIDEXTGR("EAGN760419LC8");
			listaCampoSalida.getTRGRLSTEVTV().add(campoSalida);
		}
		
		paginacion.getSTDTRNMSJPARMV().add(mensajes);
		paginacion.setTRGRLSTEVTZ(listaCampoSalida);
		responseBansefi.setTRGRLSTTRNO(paginacion);
		
		arrayOfResponseBansefi.getResponseBansefi().add(responseBansefi);
		
		return arrayOfResponseBansefi;
	}

	private ArrayOfResponseBansefi generarResultadosNombres(String tipoResultado) {
		ResponseBansefi responseBansefi = new ResponseBansefi();
		ResponseBansefi.TRGRLSTTRNO paginacion = new ResponseBansefi.TRGRLSTTRNO();
		ResponseBansefi.TRGRLSTTRNO.STDTRNMSJPARMV mensajes = new ResponseBansefi.TRGRLSTTRNO.STDTRNMSJPARMV();
		ResponseBansefi.TRGRLSTTRNO.TRGRLSTEVTZ listaCampoSalida = new ResponseBansefi.TRGRLSTTRNO.TRGRLSTEVTZ();
		
		ArrayOfResponseBansefi arrayOfResponseBansefi = new ArrayOfResponseBansefi();
		
		paginacion.setNUMBEROFRECORDS(20);
		paginacion.setMOREDATAIN(10);
		
		if(tipoResultado.equals("OK")){
			mensajes.setTEXTCODE(0);
			mensajes.setTEXTARG1(tipoResultado);
		}else if(tipoResultado.equals("ERROR")){
			mensajes.setTEXTCODE(1);
			mensajes.setTEXTARG1(tipoResultado);
		}else if(tipoResultado.equals("NO_DATOS")){
			mensajes.setTEXTCODE(2);
			mensajes.setTEXTARG1(tipoResultado);
		}
		
		for (int i = 0; i < 10; i++) {
			ResponseBansefi.TRGRLSTTRNO.TRGRLSTEVTZ.TRGRLSTEVTV campoSalida = new ResponseBansefi.TRGRLSTTRNO.TRGRLSTEVTZ.TRGRLSTEVTV();
			campoSalida.setNOMBGR("Nombre grupo" + i);
			campoSalida.setIDEXTGR("EAGN760419LC8");
			campoSalida.setFECHAALTAGR(i);
			campoSalida.setFECHAINICECV(i);
			listaCampoSalida.getTRGRLSTEVTV().add(campoSalida);
		}
		
		paginacion.getSTDTRNMSJPARMV().add(mensajes);
		paginacion.setTRGRLSTEVTZ(listaCampoSalida);
		responseBansefi.setTRGRLSTTRNO(paginacion);
		
		arrayOfResponseBansefi.getResponseBansefi().add(responseBansefi);
		
		return arrayOfResponseBansefi;
	}

}
