package mx.babel.bansefi.banksystem.endpoints;

import mx.babel.bansefi.banksystem.response.consultabloqueos.ArrayOfResponseBansefi;
import mx.babel.bansefi.banksystem.response.consultabloqueos.EjecutarResponse;
import mx.babel.bansefi.banksystem.response.consultabloqueos.EjecutarResult;
import mx.babel.bansefi.banksystem.response.consultabloqueos.ResponseBansefi;
import mx.babel.bansefi.banksystem.utils.MarshallObjetoUtils;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.w3c.dom.Element;

@Endpoint
public class ConsultaBloqueosEndPoint {
	
	@PayloadRoot(localPart = "Ejecutar", namespace = "http://BansefiNSS/WebServicesNSS/TR_PETICION_CONSULTA_BP_TRN")
    @ResponsePayload									
    public Element ejecutarConsulta(@RequestPayload Element element){
		EjecutarResponse response = new EjecutarResponse();
		EjecutarResult result = new EjecutarResult();
		
		ArrayOfResponseBansefi arrayOfResponseBansefi = generarResultados();
		
		result.setResponseBansefi(arrayOfResponseBansefi);
		
		response.setEjecutarResult(result);
		
		System.out.println("About to return...");
		return MarshallObjetoUtils.marshallObjeto(mx.babel.bansefi.banksystem.response.consultabloqueos.EjecutarResponse.class ,response);
	}
	
	public ArrayOfResponseBansefi generarResultados(){
		ResponseBansefi responseBansefi = new ResponseBansefi();
		
		ResponseBansefi.TRPETICIONCONSULTABPTRNO trno = new ResponseBansefi.TRPETICIONCONSULTABPTRNO();
		
		ResponseBansefi.TRPETICIONCONSULTABPTRNO.TRPETICIONCONSULTABPEVTZ contexto = new ResponseBansefi.TRPETICIONCONSULTABPTRNO.TRPETICIONCONSULTABPEVTZ();

		trno.setRTRNCD(0);
		
		int numMovimientos = (int) Math.round(Math.random() * 5);
		for(int i = 0; i< numMovimientos; i++){
			ResponseBansefi.TRPETICIONCONSULTABPTRNO.TRPETICIONCONSULTABPEVTZ.BPLISTABLOQUEOV bloqueo = 
					new ResponseBansefi.TRPETICIONCONSULTABPTRNO.TRPETICIONCONSULTABPEVTZ.BPLISTABLOQUEOV();
			
			bloqueo.setFECHAINICECV(20111212);
			bloqueo.setFECHAPLANIF(20111212);
			bloqueo.setIDINTERNOEMPLEP("DAPARA66");
			
			ResponseBansefi.TRPETICIONCONSULTABPTRNO.TRPETICIONCONSULTABPEVTZ.BPLISTABLOQUEOV.BPBLOQUEOPRTCNE detalles =
					new ResponseBansefi.TRPETICIONCONSULTABPTRNO.TRPETICIONCONSULTABPEVTZ.BPLISTABLOQUEOV.BPBLOQUEOPRTCNE();
			
			detalles.setCODBLOQUEO("Manual");
			detalles.setMOTIVOBLQPRTCN("Lorem Ipsum Dolor...");
			detalles.setCODNRBEEN("0166");
			detalles.setCODCENTUO("0166");
			
			bloqueo.setBPBLOQUEOPRTCNE(detalles);
			
			contexto.getBPLISTABLOQUEOV().add(bloqueo);
		}
		
		trno.setTRPETICIONCONSULTABPEVTZ(contexto);
		
		
		responseBansefi.setTRPETICIONCONSULTABPTRNO(trno);
		
		ArrayOfResponseBansefi arrayOfResponseBansefi = new ArrayOfResponseBansefi();
		arrayOfResponseBansefi.getResponseBansefi().add(responseBansefi);
		return arrayOfResponseBansefi;
	}

}
