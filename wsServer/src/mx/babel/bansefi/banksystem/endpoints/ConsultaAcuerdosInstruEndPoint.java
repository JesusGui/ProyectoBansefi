package mx.babel.bansefi.banksystem.endpoints;

import mx.babel.bansefi.banksystem.response.consultaacuerdosinst.ArrayOfResponseBansefi;
import mx.babel.bansefi.banksystem.response.consultaacuerdosinst.Ejecutar;
import mx.babel.bansefi.banksystem.response.consultaacuerdosinst.ResponseBansefi;
import mx.babel.bansefi.banksystem.utils.MarshallObjetoUtils;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.w3c.dom.Element;

@Endpoint
public class ConsultaAcuerdosInstruEndPoint {
	@PayloadRoot(localPart = "Ejecutar", namespace = "http://BansefiNSS/WebServicesNSS/TR_CONS_AC_INST_OFIC_TRN")
	@ResponsePayload
	public Element ejecutarConsultaAcuerdos(@RequestPayload Element element) {
		Ejecutar objEjecutar = (Ejecutar) MarshallObjetoUtils
				.unMarshallObjeto(
						mx.babel.bansefi.banksystem.response.consultaacuerdosinst.Ejecutar.class,
						element);

		mx.babel.bansefi.banksystem.response.consultaacuerdosinst.EjecutarResponse response = new mx.babel.bansefi.banksystem.response.consultaacuerdosinst.EjecutarResponse();
		mx.babel.bansefi.banksystem.response.consultaacuerdosinst.EjecutarResult result = new mx.babel.bansefi.banksystem.response.consultaacuerdosinst.EjecutarResult();

		String[] arTipoResultado = { "OK", "ERROR", "NO_DATOS" };
		int indice = (int) Math.round(Math.random() * 2);
		String tipoResultado = arTipoResultado[0];

		ArrayOfResponseBansefi arrayOfResponseBansefi = generarResultados(tipoResultado);

		result.setResponseBansefi(arrayOfResponseBansefi);

		response.setEjecutarResult(result);

		System.out.println("About to return...");
		return MarshallObjetoUtils
				.marshallObjeto(
						mx.babel.bansefi.banksystem.response.consultaacuerdosinst.EjecutarResponse.class,
						response);
	}

	public ArrayOfResponseBansefi generarResultados(String tipoResultado) {
		ResponseBansefi responseBansefi = new ResponseBansefi();
		ResponseBansefi.TRCONSACINSTOFICTRNO contextoSalida = new ResponseBansefi.TRCONSACINSTOFICTRNO();
		ResponseBansefi.TRCONSACINSTOFICTRNO.TRCONSACINSTOFICEVTZ contextoListaCamposSalida = new ResponseBansefi.TRCONSACINSTOFICTRNO.TRCONSACINSTOFICEVTZ();
		ResponseBansefi.TRCONSACINSTOFICTRNO.STDTRNMSJPARMV mensajes = new ResponseBansefi.TRCONSACINSTOFICTRNO.STDTRNMSJPARMV();
		ArrayOfResponseBansefi arrayOfResponseBansefi = new ArrayOfResponseBansefi();

		// int codigo = (int) Math.round(Math.random() * 21);

		int codigo = 1;

		mensajes.setTEXTCODE(codigo);
		mensajes.setTEXTARG1(tipoResultado);

		if (codigo == 1 && tipoResultado.equals("OK")) {
			ResponseBansefi.TRCONSACINSTOFICTRNO.TRCONSACINSTOFICEVTZ.UOACINSTLAV listaCamposSalida = new ResponseBansefi.TRCONSACINSTOFICTRNO.TRCONSACINSTOFICEVTZ.UOACINSTLAV();
			
			ResponseBansefi.TRCONSACINSTOFICTRNO.TRCONSACINSTOFICEVTZ.UOACINSTLAV.UOACINSTLGV camposSalida1 = new ResponseBansefi.TRCONSACINSTOFICTRNO.TRCONSACINSTOFICEVTZ.UOACINSTLAV.UOACINSTLGV();
			camposSalida1.setCODLINEA("09");
			camposSalida1.setIDGRPPD("11");
			camposSalida1.setNUMSECAC(0);
			listaCamposSalida.getUOACINSTLGV().add(camposSalida1);
			
			ResponseBansefi.TRCONSACINSTOFICTRNO.TRCONSACINSTOFICEVTZ.UOACINSTLAV.UOACINSTLGV camposSalida2 = new ResponseBansefi.TRCONSACINSTOFICTRNO.TRCONSACINSTOFICEVTZ.UOACINSTLAV.UOACINSTLGV();
			camposSalida2.setCODLINEA("09");
			camposSalida2.setIDGRPPD("14");
			camposSalida2.setNUMSECAC(0);
			listaCamposSalida.getUOACINSTLGV().add(camposSalida2);
			
			
			ResponseBansefi.TRCONSACINSTOFICTRNO.TRCONSACINSTOFICEVTZ.UOACINSTLAV.UOACINSTLGV camposSalida3 = new ResponseBansefi.TRCONSACINSTOFICTRNO.TRCONSACINSTOFICEVTZ.UOACINSTLAV.UOACINSTLGV();
			camposSalida3.setCODLINEA("03");
			camposSalida3.setIDGRPPD("17");
			camposSalida3.setNUMSECAC(0);
			listaCamposSalida.getUOACINSTLGV().add(camposSalida3);
			
			contextoListaCamposSalida.getUOACINSTLAV().add(listaCamposSalida);
		} else {
			ResponseBansefi.TRCONSACINSTOFICTRNO.TRCONSACINSTOFICEVTZ.UOACINSTLAV listaCamposSalida = new ResponseBansefi.TRCONSACINSTOFICTRNO.TRCONSACINSTOFICEVTZ.UOACINSTLAV();
			ResponseBansefi.TRCONSACINSTOFICTRNO.TRCONSACINSTOFICEVTZ.UOACINSTLAV.UOACINSTLGV camposSalida = new ResponseBansefi.TRCONSACINSTOFICTRNO.TRCONSACINSTOFICEVTZ.UOACINSTLAV.UOACINSTLGV();
			camposSalida.setCODLINEA(" ");
			camposSalida.setIDGRPPD(" ");
			camposSalida.setNUMSECAC(0);
			listaCamposSalida.getUOACINSTLGV().add(camposSalida);
			contextoListaCamposSalida.getUOACINSTLAV().add(listaCamposSalida);
		}

		contextoSalida.getSTDTRNMSJPARMV().add(mensajes);
		contextoSalida.setTRCONSACINSTOFICEVTZ(contextoListaCamposSalida);
		responseBansefi.setTRCONSACINSTOFICTRNO(contextoSalida);

		arrayOfResponseBansefi.getResponseBansefi().add(responseBansefi);

		return arrayOfResponseBansefi;
	}
}
