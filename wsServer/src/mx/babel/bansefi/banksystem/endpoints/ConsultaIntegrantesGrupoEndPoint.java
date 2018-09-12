package mx.babel.bansefi.banksystem.endpoints;

import mx.babel.bansefi.banksystem.response.consultaintegrantesgrupo.ArrayOfResponseBansefi;
import mx.babel.bansefi.banksystem.response.consultaintegrantesgrupo.ResponseBansefi;
import mx.babel.bansefi.banksystem.utils.MarshallObjetoUtils;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.w3c.dom.Element;

@Endpoint
public class ConsultaIntegrantesGrupoEndPoint {
	
	private static final String [] TIPO_RESULTADO = {"OK", "ERROR", "ERROR_COMUNICACION"};
	
	private String tipoResultado;
	
	@PayloadRoot(localPart = "Ejecutar", namespace = "http://BansefiNSS/WebServicesNSS/TR_PE_CONSULTA_POSICION_TRN")
	@ResponsePayload
	public Element ejecutarConsulta(@RequestPayload Element element){
		
		mx.babel.bansefi.banksystem.response.consultaintegrantesgrupo.EjecutarResponse response = new mx.babel.bansefi.banksystem.response.consultaintegrantesgrupo.EjecutarResponse();
		mx.babel.bansefi.banksystem.response.consultaintegrantesgrupo.EjecutarResult result = new mx.babel.bansefi.banksystem.response.consultaintegrantesgrupo.EjecutarResult();
		
		ArrayOfResponseBansefi arrayOfResponseBansefi = generarResultados();
		
		result.setResponseBansefi(arrayOfResponseBansefi);
		
		response.setEjecutarResult(result);
		
		System.out.println("About to return...");
		return MarshallObjetoUtils.marshallObjeto(mx.babel.bansefi.banksystem.response.consultaintegrantesgrupo.EjecutarResponse.class ,response);
	}
	
	private ArrayOfResponseBansefi generarResultados(){
		setTipoResultado();
		ResponseBansefi responseBansefi = new ResponseBansefi();
		
		ResponseBansefi.TRPECONSULTAPOSICIONTRNO trno = new ResponseBansefi.TRPECONSULTAPOSICIONTRNO();
		
		ResponseBansefi.TRPECONSULTAPOSICIONTRNO.STDTRNMSJPARMV msj = new ResponseBansefi.TRPECONSULTAPOSICIONTRNO.STDTRNMSJPARMV();
		ResponseBansefi.TRPECONSULTAPOSICIONTRNO.STDTRNOPARMV params = new ResponseBansefi.TRPECONSULTAPOSICIONTRNO.STDTRNOPARMV();
		ResponseBansefi.TRPECONSULTAPOSICIONTRNO.TRPECONSULTAPOSICIONEVTZ resultado = new ResponseBansefi.TRPECONSULTAPOSICIONTRNO.TRPECONSULTAPOSICIONEVTZ();  
		
		setPECONSINDVOBJTRDV(resultado);
		params.setFECHAOPRCN(0);
		params.setHORAOPRCN(0);
		setSTDTRNMSJPARMV(msj);
		trno.setSTDTRNOPARMV(params);
		trno.setTRPECONSULTAPOSICIONEVTZ(resultado);
		trno.getSTDTRNMSJPARMV().add(msj);
		
		responseBansefi.setTRPECONSULTAPOSICIONTRNO(trno);
		
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
	
	private void setSTDTRNMSJPARMV(ResponseBansefi.TRPECONSULTAPOSICIONTRNO.STDTRNMSJPARMV mensajes){
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
	
	private void setPECONSINDVOBJTRDV(ResponseBansefi.TRPECONSULTAPOSICIONTRNO.TRPECONSULTAPOSICIONEVTZ resultado){
		ResponseBansefi.TRPECONSULTAPOSICIONTRNO.TRPECONSULTAPOSICIONEVTZ.PERPERSONASLST lista = new ResponseBansefi.TRPECONSULTAPOSICIONTRNO.TRPECONSULTAPOSICIONEVTZ.PERPERSONASLST();

		if("OK".equals(tipoResultado)){
			int numIntegrantes = (int) Math.round(Math.random() * 5);
			for(int i = 0; i< numIntegrantes; i++){
				ResponseBansefi.TRPECONSULTAPOSICIONTRNO.TRPECONSULTAPOSICIONEVTZ.PERPERSONASLST.PERPERSONASV persona = 
						new ResponseBansefi.TRPECONSULTAPOSICIONTRNO.TRPECONSULTAPOSICIONEVTZ.PERPERSONASLST.PERPERSONASV();
				persona.setIDINTERNOPE(123);
				lista.getPERPERSONASV().add(persona);
			}
			
			resultado.setPERPERSONASLST(lista);
		}
	}
}
 