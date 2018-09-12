package mx.babel.bansefi.banksystem.endpoints;

import mx.babel.bansefi.banksystem.response.consultadomicilio.ArrayOfResponseBansefi;
import mx.babel.bansefi.banksystem.response.consultadomicilio.ResponseBansefi;
import mx.babel.bansefi.banksystem.utils.MarshallObjetoUtils;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.w3c.dom.Element;

@Endpoint
public class ConsultaDomicilioEndPoint {
	
	private static final String [] TIPO_RESULTADO = {"OK", "ERROR", "ERROR_COMUNICACION"};
	
	private String tipoResultado;
	
	@PayloadRoot(localPart = "Ejecutar", namespace = "http://BansefiNSS/WebServicesNSS/TR_CONS_DOMIC_PPAL_TRN")
	@ResponsePayload
	public Element ejecutarConsulta(@RequestPayload Element element){
		
		mx.babel.bansefi.banksystem.response.consultadomicilio.EjecutarResponse response = new mx.babel.bansefi.banksystem.response.consultadomicilio.EjecutarResponse();
		mx.babel.bansefi.banksystem.response.consultadomicilio.EjecutarResult result = new mx.babel.bansefi.banksystem.response.consultadomicilio.EjecutarResult();
		
		ArrayOfResponseBansefi arrayOfResponseBansefi = generarResultados();
		
		result.setResponseBansefi(arrayOfResponseBansefi);
		
		response.setEjecutarResult(result);
		
		System.out.println("About to return...");
		return MarshallObjetoUtils.marshallObjeto(mx.babel.bansefi.banksystem.response.consultadomicilio.EjecutarResponse.class ,response);
	}
	
	private ArrayOfResponseBansefi generarResultados(){
		setTipoResultado();
		ResponseBansefi responseBansefi = new ResponseBansefi();
		
		ResponseBansefi.TRCONSDOMICPPALTRNO trno = new ResponseBansefi.TRCONSDOMICPPALTRNO();
		
		ResponseBansefi.TRCONSDOMICPPALTRNO.STDTRNMSJPARMV params = new ResponseBansefi.TRCONSDOMICPPALTRNO.STDTRNMSJPARMV();
		ResponseBansefi.TRCONSDOMICPPALTRNO.TRCONSDOMICPPALEVTZ contexto = new ResponseBansefi.TRCONSDOMICPPALTRNO.TRCONSDOMICPPALEVTZ();  
		
		setSTDTRNMSJPARMV(params);
		trno.getSTDTRNMSJPARMV().add(params);
		
		setPECONSINDVOBJTRDV(contexto);
		trno.setTRCONSDOMICPPALEVTZ(contexto);
		
		responseBansefi.setTRCONSDOMICPPALTRNO(trno);
		
		ArrayOfResponseBansefi arrayOfResponseBansefi = new ArrayOfResponseBansefi();
		arrayOfResponseBansefi.getResponseBansefi().add(responseBansefi);
		return arrayOfResponseBansefi;
	}
	
	private void setTipoResultado(){
		int indice = (int) Math.round(Math.random() * 10);
		if(indice < 8){
			indice = 0;
		}else if(indice >= 8 && indice <= 9 ){
			indice = 1;
		}else if(indice >9){
			indice = 2;
		}
		tipoResultado = TIPO_RESULTADO[0];
	}
	
	private void setSTDTRNMSJPARMV(ResponseBansefi.TRCONSDOMICPPALTRNO.STDTRNMSJPARMV mensajes){
		if("OK".equals(tipoResultado)){
			mensajes.setTEXTCODE(1);
			mensajes.setTEXTARG1(tipoResultado);
		}else if("ERROR".equals(tipoResultado)){
			mensajes.setTEXTCODE(0);
			mensajes.setTEXTARG1(tipoResultado);
		}else if("ERROR_COMUNICACION".equals(tipoResultado)){
			mensajes.setTEXTCODE(3);
			mensajes.setTEXTARG1(tipoResultado);
		}
	}
	
	private void setPECONSINDVOBJTRDV(ResponseBansefi.TRCONSDOMICPPALTRNO.TRCONSDOMICPPALEVTZ resultado){
		ResponseBansefi.TRCONSDOMICPPALTRNO.TRCONSDOMICPPALEVTZ.PERSTDDOMICPPALSRVV domicilio = new ResponseBansefi.TRCONSDOMICPPALTRNO.TRCONSDOMICPPALEVTZ.PERSTDDOMICPPALSRVV();  
		
		if("OK".equals(tipoResultado)){
			domicilio.setCODPOSTALAG("15004");
			domicilio.setDOMIC50("Nuevo León 254");
			domicilio.setNOMB50("Condesa");
			domicilio.setNOMBLOCALIDADAG("Ciudad de México");
			domicilio.setNOMBPROVINCIAAG("Distrito Federal");
			domicilio.setNUMTLFNODOMIC("55463546");
			domicilio.setPREFTLFNODOMIC("55");
			domicilio.setNOMBPAISAG("México");
			
			resultado.setPERSTDDOMICPPALSRVV(domicilio);
		}
	}
}
 