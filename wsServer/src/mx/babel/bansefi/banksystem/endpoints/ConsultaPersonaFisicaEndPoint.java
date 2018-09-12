package mx.babel.bansefi.banksystem.endpoints;

import mx.babel.bansefi.banksystem.response.consultapersonafisica.ArrayOfResponseBansefi;
import mx.babel.bansefi.banksystem.response.consultapersonafisica.ResponseBansefi;
import mx.babel.bansefi.banksystem.utils.MarshallObjetoUtils;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.w3c.dom.Element;

@Endpoint
public class ConsultaPersonaFisicaEndPoint {
	
	private static final String [] TIPO_RESULTADO = {"OK", "ERROR", "ERROR_COMUNICACION"};
	
	private static final String [] CAPACIDAD_LEGAL = {"RESTRINGIDA","TOTAL"};
	
	private String tipoResultado;
	
	@PayloadRoot(localPart = "Ejecutar", namespace = "http://BansefiNSS/WebServicesNSS/TR_PE_CONS_INDV_TRN")
	@ResponsePayload
	public Element ejecutarConsulta(@RequestPayload Element element){
		
		mx.babel.bansefi.banksystem.response.consultapersonafisica.EjecutarResponse response = new mx.babel.bansefi.banksystem.response.consultapersonafisica.EjecutarResponse();
		mx.babel.bansefi.banksystem.response.consultapersonafisica.EjecutarResult result = new mx.babel.bansefi.banksystem.response.consultapersonafisica.EjecutarResult();
		
		ArrayOfResponseBansefi arrayOfResponseBansefi = generarResultados();
		
		result.setResponseBansefi(arrayOfResponseBansefi);
		
		response.setEjecutarResult(result);
		
		System.out.println("About to return...");
		return MarshallObjetoUtils.marshallObjeto(mx.babel.bansefi.banksystem.response.consultapersonafisica.EjecutarResponse.class ,response);
	}
	
	public ArrayOfResponseBansefi generarResultados(){
		setTipoResultado();
		ResponseBansefi responseBansefi = new ResponseBansefi();
		
		ResponseBansefi.TRPECONSINDVTRNO trno = new ResponseBansefi.TRPECONSINDVTRNO();

		ResponseBansefi.TRPECONSINDVTRNO.STDTRNMSJPARMV mensaje = new ResponseBansefi.TRPECONSINDVTRNO.STDTRNMSJPARMV();
		
		ResponseBansefi.TRPECONSINDVTRNO.STDANAVMSJV msj = new ResponseBansefi.TRPECONSINDVTRNO.STDANAVMSJV();
		ResponseBansefi.TRPECONSINDVTRNO.STDANAVMSJV.STDANAVMSJLS anotaciones = new ResponseBansefi.TRPECONSINDVTRNO.STDANAVMSJV.STDANAVMSJLS();
		ResponseBansefi.TRPECONSINDVTRNO.STDANAVMSJV.STDANAVMSJLS.DESCINDPRDADV descripcion = new ResponseBansefi.TRPECONSINDVTRNO.STDANAVMSJV.STDANAVMSJLS.DESCINDPRDADV();  
		
		ResponseBansefi.TRPECONSINDVTRNO.TRPECONSINDVEVTZ resultado = new ResponseBansefi.TRPECONSINDVTRNO.TRPECONSINDVEVTZ();
		
		descripcion.setSTDCHAR07("Anotaciones del cliente...");
		anotaciones.setDESCINDPRDADV(descripcion);
		
		msj.getSTDANAVMSJLS().add(anotaciones);
		trno.setSTDANAVMSJV(msj);
		
		setPECONSINDVOBJTRDV(resultado);
		trno.setTRPECONSINDVEVTZ(resultado);
		
		setSTDTRNMSJPARMV(mensaje);
		trno.getSTDTRNMSJPARMV().add(mensaje);
		
		responseBansefi.setTRPECONSINDVTRNO(trno);
		
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
	
	private String getCODCPCDADLGLIN(){
		int indice = (int) Math.round(Math.random() * 10);
		if(indice < 7){
			indice = 1;
		}else if(indice >= 7 ){
			indice = 0;
		}
		String capacidadLegal = CAPACIDAD_LEGAL[indice];
		return capacidadLegal;
	}
	
	private void setSTDTRNMSJPARMV(ResponseBansefi.TRPECONSINDVTRNO.STDTRNMSJPARMV mensajes){
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
	
	private void setPECONSINDVOBJTRDV(ResponseBansefi.TRPECONSINDVTRNO.TRPECONSINDVEVTZ resultado){
		ResponseBansefi.TRPECONSINDVTRNO.TRPECONSINDVEVTZ.PECONSINDVOBJTRDV cliente = new ResponseBansefi.TRPECONSINDVTRNO.TRPECONSINDVEVTZ.PECONSINDVOBJTRDV();

		if("OK".equals(tipoResultado)){
			cliente.setNOMBINNOMBPILA("Paco");
			cliente.setNOMBINAPE1IN("Lopez");
			cliente.setNOMBINAPE2IN("Perez");
			cliente.setIDINTERNOPE(9265511);
			cliente.setIDEXTPE("4545784411");
			cliente.setCODIDEXTPERS("IFE");
			cliente.setIDRFC("MOMH898989JK6");
			cliente.setFECNCTOCONSTPE(19810303);
			
			String capacidadLegal = getCODCPCDADLGLIN();
			cliente.setCODCPCDADLGLIN(capacidadLegal);
			
			cliente.setNUMDIRPRAL(0);
			
			resultado.setPECONSINDVOBJTRDV(cliente);
		}
	}
}
 