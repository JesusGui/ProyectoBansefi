package mx.babel.bansefi.banksystem.endpoints;

import mx.babel.bansefi.banksystem.response.consultacuentascliente.ArrayOfResponseBansefi;
import mx.babel.bansefi.banksystem.response.consultacuentascliente.ResponseBansefi;
import mx.babel.bansefi.banksystem.utils.MarshallObjetoUtils;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.w3c.dom.Element;

@Endpoint
public class ConsultaCuentasClienteEndPoint {
	
	private static final String [] TIPO_RESULTADO = {"OK", "ERROR", "ERROR_COMUNICACION"};
	
	private String tipoResultado;
	
	@PayloadRoot(localPart = "Ejecutar", namespace = "http://BansefiNSS/WebServicesNSS/TR_CONSULTA_RP_PANT_5_TRN")
	@ResponsePayload
	public Element ejecutarConsulta(@RequestPayload Element element){
		
		mx.babel.bansefi.banksystem.response.consultacuentascliente.EjecutarResponse response = new mx.babel.bansefi.banksystem.response.consultacuentascliente.EjecutarResponse();
		mx.babel.bansefi.banksystem.response.consultacuentascliente.EjecutarResult result = new mx.babel.bansefi.banksystem.response.consultacuentascliente.EjecutarResult();
		
		ArrayOfResponseBansefi arrayOfResponseBansefi = generarResultados();
		
		result.setResponseBansefi(arrayOfResponseBansefi);
		
		response.setEjecutarResult(result);
		
		System.out.println("About to return...");
		return MarshallObjetoUtils.marshallObjeto(mx.babel.bansefi.banksystem.response.consultacuentascliente.EjecutarResponse.class ,response);
	}
	
	public ArrayOfResponseBansefi generarResultados(){
		setTipoResultado();
		ResponseBansefi responseBansefi = new ResponseBansefi();
		
		ResponseBansefi.TRCONSULTARPPANT5TRNO trno = new ResponseBansefi.TRCONSULTARPPANT5TRNO();
		
		ResponseBansefi.TRCONSULTARPPANT5TRNO.STDTRNMSJPARMV msj = new ResponseBansefi.TRCONSULTARPPANT5TRNO.STDTRNMSJPARMV();
		ResponseBansefi.TRCONSULTARPPANT5TRNO.STDTRNOPARMV params = new ResponseBansefi.TRCONSULTARPPANT5TRNO.STDTRNOPARMV();
		ResponseBansefi.TRCONSULTARPPANT5TRNO.TRCONSULTARPPANT5EVTZ resultado = new ResponseBansefi.TRCONSULTARPPANT5TRNO.TRCONSULTARPPANT5EVTZ();  
		
		params.setFECHAOPRCN(0);
		params.setHORAOPRCN(0);

		setSTDTRNMSJPARMV(msj);
		setPECONSINDVOBJTRDV(resultado);
		trno.setSTDTRNOPARMV(params);
		trno.setTRCONSULTARPPANT5EVTZ(resultado);
		trno.getSTDTRNMSJPARMV().add(msj);
		
		responseBansefi.setTRCONSULTARPPANT5TRNO(trno);
		
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
	
	private void setSTDTRNMSJPARMV(ResponseBansefi.TRCONSULTARPPANT5TRNO.STDTRNMSJPARMV mensajes){
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
	
	private void setPECONSINDVOBJTRDV(ResponseBansefi.TRCONSULTARPPANT5TRNO.TRCONSULTARPPANT5EVTZ resultado){
		if("OK".equals(tipoResultado)){
			int numIntegrantes = (int) Math.round(Math.random() * 5);
			for(int i = 0; i< numIntegrantes; i++){
				ResponseBansefi.TRCONSULTARPPANT5TRNO.TRCONSULTARPPANT5EVTZ.RPPERSRLACE  cuenta = 
						new ResponseBansefi.TRCONSULTARPPANT5TRNO.TRCONSULTARPPANT5EVTZ.RPPERSRLACE();
				cuenta.setNUMSECAC(69225969L);
				cuenta.setCODNRBEEN("Cuenta Ahorro");
				cuenta.setCODRLPERSAC("Titular");
				cuenta.setCODECVPERSAC("Activo");
				
				resultado.getRPPERSRLACE().add(cuenta);
			}
		}
	}
}
 