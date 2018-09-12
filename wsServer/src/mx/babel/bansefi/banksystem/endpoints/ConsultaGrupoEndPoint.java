package mx.babel.bansefi.banksystem.endpoints;

import mx.babel.bansefi.banksystem.response.consultagrupo.ArrayOfResponseBansefi;
import mx.babel.bansefi.banksystem.response.consultagrupo.ResponseBansefi;
import mx.babel.bansefi.banksystem.utils.MarshallObjetoUtils;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.w3c.dom.Element;

@Endpoint
public class ConsultaGrupoEndPoint {
	
	private static final String [] TIPO_RESULTADO = {"OK", "ERROR", "ERROR_COMUNICACION"};
	
	private String tipoResultado;
	
	@PayloadRoot(localPart = "Ejecutar", namespace = "http://BansefiNSS/WebServicesNSS/TR_GR_CONS_TRN")
	@ResponsePayload
	public Element ejecutarConsulta(@RequestPayload Element element){
		
		mx.babel.bansefi.banksystem.response.consultagrupo.EjecutarResponse response = new mx.babel.bansefi.banksystem.response.consultagrupo.EjecutarResponse();
		mx.babel.bansefi.banksystem.response.consultagrupo.EjecutarResult result = new mx.babel.bansefi.banksystem.response.consultagrupo.EjecutarResult();
		
		ArrayOfResponseBansefi arrayOfResponseBansefi = generarResultados();
		
		result.setResponseBansefi(arrayOfResponseBansefi);
		
		response.setEjecutarResult(result);
		
		System.out.println("About to return...");
		return MarshallObjetoUtils.marshallObjeto(mx.babel.bansefi.banksystem.response.consultagrupo.EjecutarResponse.class ,response);
	}
	
	public ArrayOfResponseBansefi generarResultados(){
		setTipoResultado();
		ResponseBansefi responseBansefi = new ResponseBansefi();
		
		ResponseBansefi.TRGRCONSTRNO trno = new ResponseBansefi.TRGRCONSTRNO();
		
		ResponseBansefi.TRGRCONSTRNO.STDTRNMSJPARMV mensajes = new ResponseBansefi.TRGRCONSTRNO.STDTRNMSJPARMV();
		ResponseBansefi.TRGRCONSTRNO.STDTRNOPARMV params = new ResponseBansefi.TRGRCONSTRNO.STDTRNOPARMV ();
		ResponseBansefi.TRGRCONSTRNO.TRGRCONSEVTZ grupo = new ResponseBansefi.TRGRCONSTRNO.TRGRCONSEVTZ();  
		
		params.setFECHAOPRCN(0);
		params.setHORAOPRCN(0);
		trno.setSTDTRNOPARMV(params);
		
		setSTDTRNMSJPARMV(mensajes);
		trno.getSTDTRNMSJPARMV().add(mensajes);
		
		setPECONSINDVOBJTRDV(grupo);
		trno.setTRGRCONSEVTZ(grupo);
		
		responseBansefi.setTRGRCONSTRNO(trno);
		
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
	
	private void setSTDTRNMSJPARMV(ResponseBansefi.TRGRCONSTRNO.STDTRNMSJPARMV mensajes){
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
	
	private void setPECONSINDVOBJTRDV(ResponseBansefi.TRGRCONSTRNO.TRGRCONSEVTZ grupo){
		if("OK".equals(tipoResultado)){
			grupo.setNOMBGR("Grupo Nuevo Leon");
			grupo.setFECHAALTAGR(20122012);
			grupo.setIDEXTGR("12341234");
		}
	}
}
 