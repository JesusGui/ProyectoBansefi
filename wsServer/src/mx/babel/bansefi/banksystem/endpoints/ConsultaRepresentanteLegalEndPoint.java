package mx.babel.bansefi.banksystem.endpoints;

import mx.babel.bansefi.banksystem.response.consultarepresentantelegal.ArrayOfResponseBansefi;
import mx.babel.bansefi.banksystem.response.consultarepresentantelegal.ResponseBansefi;
import mx.babel.bansefi.banksystem.utils.MarshallObjetoUtils;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.w3c.dom.Element;

@Endpoint
public class ConsultaRepresentanteLegalEndPoint {
	
	private static final String [] TIPO_RESULTADO = {"OK", "ERROR", "ERROR_COMUNICACION"};
	
	private String tipoResultado;
	
	@PayloadRoot(localPart = "Ejecutar", namespace = "http://BansefiNSS/WebServicesNSS/TR_PE_RL_PE_LS_CNS_TRN")
	@ResponsePayload
	public Element ejecutarConsulta(@RequestPayload Element element){
		
		mx.babel.bansefi.banksystem.response.consultarepresentantelegal.EjecutarResponse response = new mx.babel.bansefi.banksystem.response.consultarepresentantelegal.EjecutarResponse();
		mx.babel.bansefi.banksystem.response.consultarepresentantelegal.EjecutarResult result = new mx.babel.bansefi.banksystem.response.consultarepresentantelegal.EjecutarResult();
		
		ArrayOfResponseBansefi arrayOfResponseBansefi = generarResultados();
		
		result.setResponseBansefi(arrayOfResponseBansefi);
		
		response.setEjecutarResult(result);
		
		System.out.println("About to return...");
		return MarshallObjetoUtils.marshallObjeto(mx.babel.bansefi.banksystem.response.consultarepresentantelegal.EjecutarResponse.class ,response);
	}
	
	public ArrayOfResponseBansefi generarResultados(){
		setTipoResultado();
		ResponseBansefi responseBansefi = new ResponseBansefi();
		
		ResponseBansefi.TRPERLPELSCNSTRNO trno = new ResponseBansefi.TRPERLPELSCNSTRNO();
		
		ResponseBansefi.TRPERLPELSCNSTRNO.STDTRNMSJPARMV mensajes = new ResponseBansefi.TRPERLPELSCNSTRNO.STDTRNMSJPARMV();
		ResponseBansefi.TRPERLPELSCNSTRNO.STDTRNOPARMV params = new ResponseBansefi.TRPERLPELSCNSTRNO.STDTRNOPARMV();
		ResponseBansefi.TRPERLPELSCNSTRNO.TRPERLPELSCNSEVTZ resultado = new ResponseBansefi.TRPERLPELSCNSTRNO.TRPERLPELSCNSEVTZ();  
		
		setResponse(mensajes, resultado);
				
		params.setFECHAOPRCN(0);
		params.setHORAOPRCN(0);

		trno.setSTDTRNOPARMV(params);
		trno.setTRPERLPELSCNSEVTZ(resultado);
		trno.getSTDTRNMSJPARMV().add(mensajes);
		
		responseBansefi.setTRPERLPELSCNSTRNO(trno);
		
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
	
	private void setResponse(ResponseBansefi.TRPERLPELSCNSTRNO.STDTRNMSJPARMV mensajes,
			ResponseBansefi.TRPERLPELSCNSTRNO.TRPERLPELSCNSEVTZ personas){
		if("OK".equals(tipoResultado)){
			mensajes.setTEXTCODE(1);
			mensajes.setTEXTARG1(tipoResultado);			
			int indice = (int) Math.round(Math.random() * 10);
			if(indice < 8){
				ResponseBansefi.TRPERLPELSCNSTRNO.TRPERLPELSCNSEVTZ.PEPERSRLPERSP persona = new ResponseBansefi.TRPERLPELSCNSTRNO.TRPERLPELSCNSEVTZ.PEPERSRLPERSP();
				persona.setIDINTERNOPE1(1234);
				persona.setIDINTERNOPE2(12345);
				personas.getPEPERSRLPERSP().add(persona);
			}
		}else if("ERROR".equals(tipoResultado)){
			mensajes.setTEXTCODE(0);
			mensajes.setTEXTARG1(tipoResultado);
		}else if("ERROR_COMUNICACION".equals(tipoResultado)){
			mensajes.setTEXTCODE(3);
			mensajes.setTEXTARG1(tipoResultado);
		}
	}
}
 