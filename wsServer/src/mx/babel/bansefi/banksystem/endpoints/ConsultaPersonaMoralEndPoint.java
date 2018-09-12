package mx.babel.bansefi.banksystem.endpoints;

import mx.babel.bansefi.banksystem.response.consultapersonamoral.ArrayOfResponseBansefi;
import mx.babel.bansefi.banksystem.response.consultapersonamoral.ResponseBansefi;
import mx.babel.bansefi.banksystem.utils.MarshallObjetoUtils;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.w3c.dom.Element;

@Endpoint
public class ConsultaPersonaMoralEndPoint {
	
	private static final String [] TIPO_RESULTADO = {"OK", "ERROR", "ERROR_COMUNICACION"};
	
	private String tipoResultado;
	
	@PayloadRoot(localPart = "Ejecutar", namespace = "http://BansefiNSS/WebServicesNSS/TR_PE_CONS_ORG_TRN")
	@ResponsePayload
	public Element ejecutarConsulta(@RequestPayload Element element){
		
		mx.babel.bansefi.banksystem.response.consultapersonamoral.EjecutarResponse response = new mx.babel.bansefi.banksystem.response.consultapersonamoral.EjecutarResponse();
		mx.babel.bansefi.banksystem.response.consultapersonamoral.EjecutarResult result = new mx.babel.bansefi.banksystem.response.consultapersonamoral.EjecutarResult();
		
		ArrayOfResponseBansefi arrayOfResponseBansefi = generarResultados();
		
		result.setResponseBansefi(arrayOfResponseBansefi);
		
		response.setEjecutarResult(result);
		
		System.out.println("About to return...");
		return MarshallObjetoUtils.marshallObjeto(mx.babel.bansefi.banksystem.response.consultapersonamoral.EjecutarResponse.class ,response);
	}
	
	public ArrayOfResponseBansefi generarResultados(){
		setTipoResultado();
		ResponseBansefi responseBansefi = new ResponseBansefi();
		
		ResponseBansefi.TRPECONSORGTRNO trno = new ResponseBansefi.TRPECONSORGTRNO();
		
		ResponseBansefi.TRPECONSORGTRNO.STDANAVMSJV msj = new ResponseBansefi.TRPECONSORGTRNO.STDANAVMSJV();
		ResponseBansefi.TRPECONSORGTRNO.STDANAVMSJV.STDANAVMSJLS anotaciones = new ResponseBansefi.TRPECONSORGTRNO.STDANAVMSJV.STDANAVMSJLS();
		ResponseBansefi.TRPECONSORGTRNO.STDANAVMSJV.STDANAVMSJLS.DESCINDPRDADV descripcion = new ResponseBansefi.TRPECONSORGTRNO.STDANAVMSJV.STDANAVMSJLS.DESCINDPRDADV();  
		
		ResponseBansefi.TRPECONSORGTRNO.STDTRNMSJPARMV mensaje = new ResponseBansefi.TRPECONSORGTRNO.STDTRNMSJPARMV();
		ResponseBansefi.TRPECONSORGTRNO.TRPECONSORGEVTZ contexto = new ResponseBansefi.TRPECONSORGTRNO.TRPECONSORGEVTZ();

		descripcion.setSTDCHAR07("Lorem ipsum...");
		anotaciones.setDESCINDPRDADV(descripcion);
		msj.getSTDANAVMSJLS().add(anotaciones);
		trno.setSTDANAVMSJV(msj);
		
		setSTDTRNMSJPARMV(mensaje);
		trno.getSTDTRNMSJPARMV().add(mensaje);
		
		setPECONSINDVOBJTRDV(contexto);		
		trno.setTRPECONSORGEVTZ(contexto);
		
		responseBansefi.setTRPECONSORGTRNO(trno);
		
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
	
	private void setSTDTRNMSJPARMV(ResponseBansefi.TRPECONSORGTRNO.STDTRNMSJPARMV mensajes){
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
	
	private void setPECONSINDVOBJTRDV(ResponseBansefi.TRPECONSORGTRNO.TRPECONSORGEVTZ resultado){
		ResponseBansefi.TRPECONSORGTRNO.TRPECONSORGEVTZ.PECONSORGOBJTRDV cliente = 
				new ResponseBansefi.TRPECONSORGTRNO.TRPECONSORGEVTZ.PECONSORGOBJTRDV();

		if("OK".equals(tipoResultado)){
			cliente.setDENOMLEGALOR("Babel SA");
			cliente.setIDINTERNOPE(1234);
			cliente.setIDEXTPE("4545784411");
			cliente.setCODIDEXTPERS("ACTA CONSTITUTIVA");
			cliente.setIDRFC("MOMH898989JK6");
			cliente.setFECNCTOCONSTPE(10101985);
			cliente.setNUMDIRPRAL(0);
			
			resultado.setPECONSORGOBJTRDV(cliente);
		}
	}
}
 