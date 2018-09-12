package mx.babel.bansefi.banksystem.endpoints;

import java.math.BigDecimal;

import mx.babel.bansefi.banksystem.response.consultaretenciones.ArrayOfResponseBansefi;
import mx.babel.bansefi.banksystem.response.consultaretenciones.EjecutarResponse;
import mx.babel.bansefi.banksystem.response.consultaretenciones.EjecutarResult;
import mx.babel.bansefi.banksystem.response.consultaretenciones.ResponseBansefi;
import mx.babel.bansefi.banksystem.utils.MarshallObjetoUtils;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.w3c.dom.Element;

@Endpoint
public class ConsultaRetencionesEndPoint {

	@PayloadRoot(localPart = "Ejecutar", namespace = "http://BansefiNSS/WebServicesNSS/TR_CONSULTA_GLOBAL_RT_TRN")
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
						mx.babel.bansefi.banksystem.response.consultaretenciones.EjecutarResponse.class,
						response);
	}

	public ArrayOfResponseBansefi generarResultados() {
		ResponseBansefi responseBansefi = new ResponseBansefi();
		ResponseBansefi.TRCONSULTAGLOBALRTTRNO paginacion = new ResponseBansefi.TRCONSULTAGLOBALRTTRNO();
		ResponseBansefi.TRCONSULTAGLOBALRTTRNO.TRCONSULTAGLOBALRTEVTZ listaCamposSalida = new ResponseBansefi.TRCONSULTAGLOBALRTTRNO.TRCONSULTAGLOBALRTEVTZ();
		ResponseBansefi.TRCONSULTAGLOBALRTTRNO.STDTRNMSJPARMV mensajes = new ResponseBansefi.TRCONSULTAGLOBALRTTRNO.STDTRNMSJPARMV();
		ArrayOfResponseBansefi arrayOfResponseBansefi = new ArrayOfResponseBansefi();

		mensajes.setTEXTCODE(1);
		mensajes.setTEXTARG1("OK");
		
		paginacion.setNUMBEROFRECORDS(20);
		paginacion.setMOREDATAIN(10);
		
		int numMovimientos = (int) Math.round(Math.random() * 5);
		for (int i = 0; i < numMovimientos; i++) {
			ResponseBansefi.TRCONSULTAGLOBALRTTRNO.TRCONSULTAGLOBALRTEVTZ.TRAFCONSEVTV campoSalida = new ResponseBansefi.TRCONSULTAGLOBALRTTRNO.TRCONSULTAGLOBALRTEVTZ.TRAFCONSEVTV();
			ResponseBansefi.TRCONSULTAGLOBALRTTRNO.TRCONSULTAGLOBALRTEVTZ.TRAFCONSEVTV.AFAPNTEE retencion = new ResponseBansefi.TRCONSULTAGLOBALRTTRNO.TRCONSULTAGLOBALRTEVTZ.TRAFCONSEVTV.AFAPNTEE();
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
		paginacion.setTRCONSULTAGLOBALRTEVTZ(listaCamposSalida);
		responseBansefi.setTRCONSULTAGLOBALRTTRNO(paginacion);

		arrayOfResponseBansefi.getResponseBansefi().add(responseBansefi);

		return arrayOfResponseBansefi;
	}
}
