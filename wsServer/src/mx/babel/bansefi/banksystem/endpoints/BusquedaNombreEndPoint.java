package mx.babel.bansefi.banksystem.endpoints;

import java.util.List;

import mx.babel.bansefi.banksystem.response.busquedanombre.ArrayOfResponseBansefi;
import mx.babel.bansefi.banksystem.response.busquedanombre.Ejecutar;
import mx.babel.bansefi.banksystem.response.busquedanombre.ResponseBansefi;
import mx.babel.bansefi.banksystem.utils.MarshallObjetoUtils;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.w3c.dom.Element;

@Endpoint
public class BusquedaNombreEndPoint {

	@PayloadRoot(localPart = "Ejecutar", namespace = "http://BansefiNSS/WebServicesNSS/TR_PE_CB_NOMBRE_CNS_TRN")
	@ResponsePayload
	public Element ejecutarBusquedaNombre(@RequestPayload Element element) {
		Ejecutar objEjecutar = (Ejecutar) MarshallObjetoUtils
				.unMarshallObjeto(
						mx.babel.bansefi.banksystem.response.busquedanombre.Ejecutar.class,
						element);

		mx.babel.bansefi.banksystem.response.busquedanombre.EjecutarResponse response = new mx.babel.bansefi.banksystem.response.busquedanombre.EjecutarResponse();
		mx.babel.bansefi.banksystem.response.busquedanombre.EjecutarResult result = new mx.babel.bansefi.banksystem.response.busquedanombre.EjecutarResult();

		String[] arTipoResultado = { "OK", "ERROR", "NO_DATOS" };
		int indice = (int) Math.round(Math.random() * 2);
		
		String tipoResultado = arTipoResultado[0];

		ArrayOfResponseBansefi arrayOfResponseBansefi = generarResultados(tipoResultado);

		result.setResponseBansefi(arrayOfResponseBansefi);

		response.setEjecutarResult(result);

		System.out.println("About to return...");
		return MarshallObjetoUtils
				.marshallObjeto(
						mx.babel.bansefi.banksystem.response.busquedanombre.EjecutarResponse.class,
						response);
	}

	public ArrayOfResponseBansefi generarResultados(String tipoResultado) {
		ResponseBansefi responseBansefi = new ResponseBansefi();
		ResponseBansefi.TRPECBNOMBRECNSTRNO paginacion = new ResponseBansefi.TRPECBNOMBRECNSTRNO();
		ResponseBansefi.TRPECBNOMBRECNSTRNO.TRPECBNOMBRECNSEVTZ listaCamposSalida = new ResponseBansefi.TRPECBNOMBRECNSTRNO.TRPECBNOMBRECNSEVTZ();
		ResponseBansefi.TRPECBNOMBRECNSTRNO.STDTRNMSJPARMV mensajes = new ResponseBansefi.TRPECBNOMBRECNSTRNO.STDTRNMSJPARMV();
		ArrayOfResponseBansefi arrayOfResponseBansefi = new ArrayOfResponseBansefi();

//		int codigo = (int) Math.round(Math.random() * 21);
		int codigo = 1;
		mensajes.setTEXTCODE(1);
		mensajes.setTEXTARG1(tipoResultado);
		
		if(codigo == 1 && tipoResultado.equals("OK")){
			paginacion.setNUMBEROFRECORDS(20);
			paginacion.setMOREDATAIN(10);
			for (int i = 0; i < 10; i++) {
				ResponseBansefi.TRPECBNOMBRECNSTRNO.TRPECBNOMBRECNSEVTZ.TRPECBNOMBRECNSEVTV campoSalida = new ResponseBansefi.TRPECBNOMBRECNSTRNO.TRPECBNOMBRECNSEVTZ.TRPECBNOMBRECNSEVTV();
				campoSalida.setNOMB50("Nombre completo " + i);
				campoSalida.setIDINTERNOPE(i);
				campoSalida.setCODIDEXTPERS("cod " + i);
				campoSalida.setIDEXTPE("id externa " + i);
				campoSalida.setFECNCTOCONSTPE(i);
				campoSalida.setDENOMLEGALORC("Denominacion Legal " + i);
				listaCamposSalida.getTRPECBNOMBRECNSEVTV().add(campoSalida);
			}
		}else{
			paginacion.setNUMBEROFRECORDS(0);
			paginacion.setMOREDATAIN(0);
			ResponseBansefi.TRPECBNOMBRECNSTRNO.TRPECBNOMBRECNSEVTZ.TRPECBNOMBRECNSEVTV campoSalida = new ResponseBansefi.TRPECBNOMBRECNSTRNO.TRPECBNOMBRECNSEVTZ.TRPECBNOMBRECNSEVTV();
			campoSalida.setNOMB50(" ");
			campoSalida.setIDINTERNOPE(0);
			campoSalida.setCODIDEXTPERS(" ");
			campoSalida.setIDEXTPE(" ");
			campoSalida.setFECNCTOCONSTPE(0);
			campoSalida.setDENOMLEGALORC(" ");
			listaCamposSalida.getTRPECBNOMBRECNSEVTV().add(campoSalida);
		}

		paginacion.getSTDTRNMSJPARMV().add(mensajes);
		paginacion.setTRPECBNOMBRECNSEVTZ(listaCamposSalida);
		responseBansefi.setTRPECBNOMBRECNSTRNO(paginacion);

		arrayOfResponseBansefi.getResponseBansefi().add(responseBansefi);

		return arrayOfResponseBansefi;
	}
}
