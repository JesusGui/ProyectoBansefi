package mx.babel.bansefi.banksystem.endpoints;

import mx.babel.bansefi.banksystem.response.guardadocumento.ArrayOfResponseBansefi;
import mx.babel.bansefi.banksystem.response.guardadocumento.EjecutarResponse;
import mx.babel.bansefi.banksystem.response.guardadocumento.EjecutarResult;
import mx.babel.bansefi.banksystem.response.guardadocumento.ResponseBansefi;
import mx.babel.bansefi.banksystem.utils.MarshallObjetoUtils;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.w3c.dom.Element;

@Endpoint
public class GuardaDocumentoEndPoint {

	@PayloadRoot(localPart = "Ejecutar", namespace = "http://BansefiNSS/WebServicesNSS/TR_GENERA_IDCTE_TRN")
	@ResponsePayload
	public Element ejecutar(@RequestPayload Element element) {

		EjecutarResponse response = new EjecutarResponse();
		EjecutarResult result = new EjecutarResult();

				
		ArrayOfResponseBansefi arrayOfResponseBansefi = generarResultados();

		result.setResponseBansefi(arrayOfResponseBansefi);

		response.setEjecutarResult(result);

		System.out.println("About to return...");
		return MarshallObjetoUtils
				.marshallObjeto(
						mx.babel.bansefi.banksystem.response.guardadocumento.EjecutarResponse.class,
						response);
	}

	public ArrayOfResponseBansefi generarResultados() {
		ResponseBansefi responseBansefi = new ResponseBansefi();
		ResponseBansefi.TRGENERAIDCTETRNO trno = new ResponseBansefi.TRGENERAIDCTETRNO();
		ResponseBansefi.TRGENERAIDCTETRNO.TRGENERAIDCTEEVTZ salida = new ResponseBansefi.TRGENERAIDCTETRNO.TRGENERAIDCTEEVTZ();
		ResponseBansefi.TRGENERAIDCTETRNO.STDTRNMSJPARMV mensajes = new ResponseBansefi.TRGENERAIDCTETRNO.STDTRNMSJPARMV();
		ArrayOfResponseBansefi arrayOfResponseBansefi = new ArrayOfResponseBansefi();

		mensajes.setTEXTCODE(1);
		mensajes.setTEXTARG1("OK");
		
		ResponseBansefi.TRGENERAIDCTETRNO.TRGENERAIDCTEEVTZ.IDIFEV identificacion = new ResponseBansefi.TRGENERAIDCTETRNO.TRGENERAIDCTEEVTZ.IDIFEV();
		identificacion.setCODIDEXTPERS("52");
		identificacion.setIDEXTPE("0166888888");
		
		salida.setIDIFEV(identificacion);

		trno.setTRGENERAIDCTEEVTZ(salida);
		responseBansefi.setTRGENERAIDCTETRNO(trno);

		arrayOfResponseBansefi.getResponseBansefi().add(responseBansefi);

		return arrayOfResponseBansefi;
	}
}
