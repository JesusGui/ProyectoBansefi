package mx.babel.bansefi.banksystem.endpoints;

import mx.babel.bansefi.banksystem.response.consultarIntervenientes.ArrayOfResponseBansefi;
import mx.babel.bansefi.banksystem.response.consultarIntervenientes.EjecutarResponse;
import mx.babel.bansefi.banksystem.response.consultarIntervenientes.EjecutarResult;
import mx.babel.bansefi.banksystem.response.consultarIntervenientes.ResponseBansefi;
import mx.babel.bansefi.banksystem.utils.MarshallObjetoUtils;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.w3c.dom.Element;


@Endpoint
public class ConsultaIntervenientesCuentaEndPoint {

	@PayloadRoot(localPart = "Ejecutar", namespace = "http://BansefiNSS/WebServicesNSS/TR_CONSULTA_RP_PANT_TRN")
	@ResponsePayload
	public Element ejecutarConsulta(@RequestPayload Element element){
		
		EjecutarResponse response = new EjecutarResponse();
		EjecutarResult result = new EjecutarResult();
		ArrayOfResponseBansefi arrayOfResponseBansefi = generarResultados();
		
		result.setResponseBansefi(arrayOfResponseBansefi);
		
		response.setEjecutarResult(result);
		
		return  MarshallObjetoUtils.marshallObjeto(EjecutarResponse.class, response);
	}

	private ArrayOfResponseBansefi generarResultados() {
		ResponseBansefi responseBansefi = new ResponseBansefi();
		ResponseBansefi.TRCONSULTARPPANTTRNO ttrno = new ResponseBansefi.TRCONSULTARPPANTTRNO();
		ResponseBansefi.TRCONSULTARPPANTTRNO.TRCONSULTARPPANTEVTZ rppantevtz = 
				new ResponseBansefi.TRCONSULTARPPANTTRNO.TRCONSULTARPPANTEVTZ();
		ResponseBansefi.TRCONSULTARPPANTTRNO.TRCONSULTARPPANTEVTZ.RPPERSV rppersv =
				new ResponseBansefi.TRCONSULTARPPANTTRNO.TRCONSULTARPPANTEVTZ.RPPERSV();
		
		rppersv.setCODIDEXTPERS("00001");
		rppersv.setIDEXTPE("001");
		
		for(int i = 0; i<0;i++){
			ResponseBansefi.TRCONSULTARPPANTTRNO.TRCONSULTARPPANTEVTZ.RPPERSRLACE relPersona =
					new ResponseBansefi.TRCONSULTARPPANTTRNO.TRCONSULTARPPANTEVTZ.RPPERSRLACE();
			relPersona.setIDINTERNOPE(9265511);
			relPersona.setCODRLPERSAC("BENEFICIARIO");
			relPersona.setNUMRLORDEN(i);
			relPersona.setFECHAALTA(20140505);
			rppantevtz.getRPPERSRLACE().add(relPersona);
		}
		
		rppantevtz.getRPPERSV().add(rppersv);
		ttrno.setTRCONSULTARPPANTEVTZ(rppantevtz);
		ttrno.setRTRNCD(1);
		responseBansefi.setTRCONSULTARPPANTTRNO(ttrno);
		
		ArrayOfResponseBansefi arrayBansefi = new ArrayOfResponseBansefi();
		arrayBansefi.getResponseBansefi().add(responseBansefi);
		return arrayBansefi;
	}
}
