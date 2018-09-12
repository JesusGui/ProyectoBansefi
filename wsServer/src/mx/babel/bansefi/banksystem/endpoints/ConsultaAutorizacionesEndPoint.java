package mx.babel.bansefi.banksystem.endpoints;

import java.math.BigDecimal;

import mx.babel.bansefi.banksystem.response.consultaautorizaciones.ArrayOfResponseBansefi;
import mx.babel.bansefi.banksystem.response.consultaautorizaciones.EjecutarResponse;
import mx.babel.bansefi.banksystem.response.consultaautorizaciones.EjecutarResult;
import mx.babel.bansefi.banksystem.response.consultaautorizaciones.ResponseBansefi;
import mx.babel.bansefi.banksystem.utils.MarshallObjetoUtils;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.w3c.dom.Element;

@Endpoint
public class ConsultaAutorizacionesEndPoint {

	@PayloadRoot(localPart = "Ejecutar", namespace = "http://BansefiNSS/WebServicesNSS/TR_CONSULTA_GLOBAL_AT_TRN")
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
						mx.babel.bansefi.banksystem.response.consultaautorizaciones.EjecutarResponse.class,
						response);
	}

	public ArrayOfResponseBansefi generarResultados() {
		ResponseBansefi responseBansefi = new ResponseBansefi();
		ResponseBansefi.TRCONSULTAGLOBALATTRNO paginacion = new ResponseBansefi.TRCONSULTAGLOBALATTRNO();
		ResponseBansefi.TRCONSULTAGLOBALATTRNO.TRCONSULTAGLOBALATEVTZ listaCamposSalida = new ResponseBansefi.TRCONSULTAGLOBALATTRNO.TRCONSULTAGLOBALATEVTZ();
		ResponseBansefi.TRCONSULTAGLOBALATTRNO.STDTRNMSJPARMV mensajes = new ResponseBansefi.TRCONSULTAGLOBALATTRNO.STDTRNMSJPARMV();
		ArrayOfResponseBansefi arrayOfResponseBansefi = new ArrayOfResponseBansefi();

		mensajes.setTEXTCODE(1);
		mensajes.setTEXTARG1("OK");
		
		paginacion.setNUMBEROFRECORDS(20);
		paginacion.setMOREDATAIN(10);
		
		int numMovimientos = (int) Math.round(Math.random() * 5);
		for (int i = 0; i < numMovimientos; i++) {
			ResponseBansefi.TRCONSULTAGLOBALATTRNO.TRCONSULTAGLOBALATEVTZ.TRAFCONSEVTV campoSalida = new ResponseBansefi.TRCONSULTAGLOBALATTRNO.TRCONSULTAGLOBALATEVTZ.TRAFCONSEVTV();
			ResponseBansefi.TRCONSULTAGLOBALATTRNO.TRCONSULTAGLOBALATEVTZ.TRAFCONSEVTV.AFAPNTEE retencion = new ResponseBansefi.TRCONSULTAGLOBALATTRNO.TRCONSULTAGLOBALATEVTZ.TRAFCONSEVTV.AFAPNTEE();
			//????
			retencion.setCONCPTAPNTE("Lorem ipsum dolor...");
			retencion.setCODORGNAPNTE("0166888888");
			retencion.setFECHACTBLE(19991212);
			retencion.setFECHAVALOR(20151212);
			retencion.setIMPAPNTE(new BigDecimal("2000.00"));
			retencion.setIDORGNAPNTE("DAPARA66");
			
			campoSalida.setAFAPNTEE(retencion);
			listaCamposSalida.getTRAFCONSEVTV().add(campoSalida);
		}

		paginacion.getSTDTRNMSJPARMV().add(mensajes);
		paginacion.setTRCONSULTAGLOBALATEVTZ(listaCamposSalida);
		responseBansefi.setTRCONSULTAGLOBALATTRNO(paginacion);

		arrayOfResponseBansefi.getResponseBansefi().add(responseBansefi);

		return arrayOfResponseBansefi;
	}
}
