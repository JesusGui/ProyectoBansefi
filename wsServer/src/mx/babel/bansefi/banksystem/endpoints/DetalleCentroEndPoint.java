package mx.babel.bansefi.banksystem.endpoints;

import mx.babel.bansefi.banksystem.response.detallecentro.ArrayOfResponseBansefi;
import mx.babel.bansefi.banksystem.response.detallecentro.Ejecutar;
import mx.babel.bansefi.banksystem.response.detallecentro.ResponseBansefi;
import mx.babel.bansefi.banksystem.utils.MarshallObjetoUtils;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.w3c.dom.Element;

@Endpoint
public class DetalleCentroEndPoint {
	@PayloadRoot(localPart = "Ejecutar", namespace = "http://BansefiNSS/WebServicesNSS/TR_CONS_CENTRO_TRN")
	@ResponsePayload
	public Element ejecutarConsultaAcuerdos(@RequestPayload Element element) {
		Ejecutar objEjecutar = (Ejecutar) MarshallObjetoUtils
				.unMarshallObjeto(
						mx.babel.bansefi.banksystem.response.detallecentro.Ejecutar.class,
						element);

		mx.babel.bansefi.banksystem.response.detallecentro.EjecutarResponse response = new mx.babel.bansefi.banksystem.response.detallecentro.EjecutarResponse();
		mx.babel.bansefi.banksystem.response.detallecentro.EjecutarResult result = new mx.babel.bansefi.banksystem.response.detallecentro.EjecutarResult();

		String[] arTipoResultado = { "OK", "ERROR", "NO_DATOS" };
		int indice = (int) Math.round(Math.random() * 2);
		String tipoResultado = arTipoResultado[0];

		ArrayOfResponseBansefi arrayOfResponseBansefi = generarResultados(tipoResultado);

		result.setResponseBansefi(arrayOfResponseBansefi);

		response.setEjecutarResult(result);

		System.out.println("About to return...");
		return MarshallObjetoUtils
				.marshallObjeto(
						mx.babel.bansefi.banksystem.response.detallecentro.EjecutarResponse.class,
						response);
	}

	public ArrayOfResponseBansefi generarResultados(String tipoResultado) {
		ResponseBansefi responseBansefi = new ResponseBansefi();
		ResponseBansefi.TRCONSCENTROTRNO contextoSalida = new ResponseBansefi.TRCONSCENTROTRNO();
		ResponseBansefi.TRCONSCENTROTRNO.TRCONSCENTROEVTZ contextoCampoSalida = new ResponseBansefi.TRCONSCENTROTRNO.TRCONSCENTROEVTZ();
		ResponseBansefi.TRCONSCENTROTRNO.STDTRNMSJPARMV mensajes = new ResponseBansefi.TRCONSCENTROTRNO.STDTRNMSJPARMV();
		ArrayOfResponseBansefi arrayOfResponseBansefi = new ArrayOfResponseBansefi();

		// int codigo = (int) Math.round(Math.random() * 21);

		int codigo = 1;

		mensajes.setTEXTCODE(codigo);
		mensajes.setTEXTARG1(tipoResultado);

		if (codigo == 1 && tipoResultado.equals("OK")) {
			ResponseBansefi.TRCONSCENTROTRNO.TRCONSCENTROEVTZ.UOCENTROE campoSalida = new ResponseBansefi.TRCONSCENTROTRNO.TRCONSCENTROEVTZ.UOCENTROE();
			campoSalida.setCODINTERNOUO("1234");
			campoSalida.setNOMBCENTUO("Ejemplo de detalle de centro");
			campoSalida.setCODDESIGCTBL("1");
			campoSalida.setCODMDLDADCENT("M1");
			campoSalida.setCODPLZBANCARIA("P01");
			campoSalida.setINDCENTCTRLUO("S");

			contextoCampoSalida.setUOCENTROE(campoSalida);
		} else {
			ResponseBansefi.TRCONSCENTROTRNO.TRCONSCENTROEVTZ.UOCENTROE campoSalida = new ResponseBansefi.TRCONSCENTROTRNO.TRCONSCENTROEVTZ.UOCENTROE();
			campoSalida.setCODINTERNOUO("");
			campoSalida.setNOMBCENTUO("");
			campoSalida.setCODDESIGCTBL("");
			campoSalida.setCODMDLDADCENT("");
			campoSalida.setCODPLZBANCARIA("");
			campoSalida.setINDCENTCTRLUO("");

			contextoCampoSalida.setUOCENTROE(campoSalida);
		}

		contextoSalida.getSTDTRNMSJPARMV().add(mensajes);
		contextoSalida.setTRCONSCENTROEVTZ(contextoCampoSalida);
		responseBansefi.setTRCONSCENTROTRNO(contextoSalida);

		arrayOfResponseBansefi.getResponseBansefi().add(responseBansefi);

		return arrayOfResponseBansefi;
	}
}
