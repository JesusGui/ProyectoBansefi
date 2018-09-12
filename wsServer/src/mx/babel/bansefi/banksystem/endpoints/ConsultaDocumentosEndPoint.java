package mx.babel.bansefi.banksystem.endpoints;

import mx.babel.bansefi.banksystem.response.consultadocumentos.ArrayOfResponseBansefi;
import mx.babel.bansefi.banksystem.response.consultadocumentos.ResponseBansefi;
import mx.babel.bansefi.banksystem.utils.MarshallObjetoUtils;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.w3c.dom.Element;

@Endpoint
public class ConsultaDocumentosEndPoint {
	
	private static final String [] TIPO_RESULTADO = {"OK", "ERROR", "ERROR_COMUNICACION"};
	
	private static final String [] CODIGO_COMPROBANTE_DOMICILIO = {"V#","VA","VC","D4","D5","V1"};
	
	private static final String [] CODIGO_CEDULA_CONOCIMIENTO = {"E1","E2"};
	
	private static final String [] CODIGO_COMPROBANTE_IDENTIFICACION = {"VK","VL","VT","VZ","1#","1B",
		"1I","1J","1K","1L","10","11","13","14","15","16","18","2#","2A","20","23","D6"};
	
	private String tipoResultado;
	
	@PayloadRoot(localPart = "Ejecutar", namespace = "http://BansefiNSS/WebServicesNSS/TR_DC_LS_CNS_TRN")
	@ResponsePayload
	public Element ejecutarConsulta(@RequestPayload Element element){
		
		mx.babel.bansefi.banksystem.response.consultadocumentos.EjecutarResponse response = new mx.babel.bansefi.banksystem.response.consultadocumentos.EjecutarResponse();
		mx.babel.bansefi.banksystem.response.consultadocumentos.EjecutarResult result = new mx.babel.bansefi.banksystem.response.consultadocumentos.EjecutarResult();
		
		ArrayOfResponseBansefi arrayOfResponseBansefi = generarResultados();
		
		result.setResponseBansefi(arrayOfResponseBansefi);
		
		response.setEjecutarResult(result);
		
		System.out.println("About to return...");
		return MarshallObjetoUtils.marshallObjeto(mx.babel.bansefi.banksystem.response.consultadocumentos.EjecutarResponse.class ,response);
	}
	
	public ArrayOfResponseBansefi generarResultados(){
		setTipoResultado();
		ResponseBansefi responseBansefi = new ResponseBansefi();
		
		ResponseBansefi.TRDCLSCNSTRNO trno = new ResponseBansefi.TRDCLSCNSTRNO();
		
		ResponseBansefi.TRDCLSCNSTRNO.STDTRNMSJPARMV msj = new ResponseBansefi.TRDCLSCNSTRNO.STDTRNMSJPARMV();
		ResponseBansefi.TRDCLSCNSTRNO.STDTRNOPARMV params = new ResponseBansefi.TRDCLSCNSTRNO.STDTRNOPARMV();
		ResponseBansefi.TRDCLSCNSTRNO.TRDCLSCNSEVTZ resultado = new ResponseBansefi.TRDCLSCNSTRNO.TRDCLSCNSEVTZ();  
		
		setResponse(msj, resultado);
		
		trno.setSTDTRNOPARMV(params);
		trno.setTRDCLSCNSEVTZ(resultado);
		trno.getSTDTRNMSJPARMV().add(msj);
		
		responseBansefi.setTRDCLSCNSTRNO(trno);
		
		ArrayOfResponseBansefi arrayOfResponseBansefi = new ArrayOfResponseBansefi();
		arrayOfResponseBansefi.getResponseBansefi().add(responseBansefi);
		return arrayOfResponseBansefi;
	}
	
	private void setTipoResultado(){
		int indice = (int) Math.round(Math.random() * 10);
		if(indice < 9){
			indice = 0;
		}else if(indice >=9 && indice < 10 ){
			indice = 1;
		}else{
			indice = 2;
		}
		tipoResultado = TIPO_RESULTADO[0];
	}
	
	private void setResponse(ResponseBansefi.TRDCLSCNSTRNO.STDTRNMSJPARMV mensajes,
			ResponseBansefi.TRDCLSCNSTRNO.TRDCLSCNSEVTZ documentos){
		if("OK".equals(tipoResultado)){
			mensajes.setTEXTCODE(1);
			mensajes.setTEXTARG1(tipoResultado);	
			generaDocumentos(documentos);
		}else if("ERROR".equals(tipoResultado)){
			mensajes.setTEXTCODE(0);
			mensajes.setTEXTARG1(tipoResultado);
		}else if("ERROR_COMUNICACION".equals(tipoResultado)){
			mensajes.setTEXTCODE(3);
			mensajes.setTEXTARG1(tipoResultado);
		}
	}
	
	private void generaDocumentos(ResponseBansefi.TRDCLSCNSTRNO.TRDCLSCNSEVTZ documentos){
		for (int i = 0; i < 3; i++) {
			int indice = (int) Math.round(Math.random() * 10);
			if(indice < 9){
				ResponseBansefi.TRDCLSCNSTRNO.TRDCLSCNSEVTZ.TRDCLSCNSEVTV documento = new ResponseBansefi.TRDCLSCNSTRNO.TRDCLSCNSEVTZ.TRDCLSCNSEVTV();
				String codigoDocumento = "";
				if(i==0){
					indice = (int) Math.round(Math.random() * 5);
					codigoDocumento = CODIGO_COMPROBANTE_DOMICILIO[indice];
				}else if(i==1){
					indice = (int) Math.round(Math.random() * 1);
					codigoDocumento = CODIGO_CEDULA_CONOCIMIENTO[indice];
				}else if(i==2){
					indice = (int) Math.round(Math.random() * 21);
					codigoDocumento = CODIGO_COMPROBANTE_IDENTIFICACION[indice];
				}
				
				documento.setCODDOC(codigoDocumento);
				documento.setDESCRDOC("Descirpción del documento");
				
				documentos.getTRDCLSCNSEVTV().add(documento);
			}
		}
		
	}
}
 