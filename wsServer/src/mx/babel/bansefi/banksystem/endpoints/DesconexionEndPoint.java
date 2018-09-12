package mx.babel.bansefi.banksystem.endpoints;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import mx.babel.bansefi.banksystem.response.desconexion.ArrayOfResponseBansefi;
import mx.babel.bansefi.banksystem.response.desconexion.ResponseBansefi;
import mx.babel.bansefi.banksystem.response.desconexion.ResponseBansefi.TRDESCONEXIONIRISTRNO;
import mx.babel.bansefi.banksystem.utils.MarshallObjetoUtils;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.w3c.dom.Element;

@Endpoint
public class DesconexionEndPoint {
	@PayloadRoot(localPart = "Ejecutar", namespace = "http://BansefiNSS/WebServicesNSS/TR_DESCONEXION_IRIS_TRN")
	@ResponsePayload
	public Element ejecutarDesconexion(@RequestPayload Element element) {
		mx.babel.bansefi.banksystem.response.desconexion.EjecutarResponse response = new mx.babel.bansefi.banksystem.response.desconexion.EjecutarResponse();
		mx.babel.bansefi.banksystem.response.desconexion.EjecutarResult result = new mx.babel.bansefi.banksystem.response.desconexion.EjecutarResult();

		ArrayOfResponseBansefi arrayOfResponseBansefi = simularResultados();

		result.setResponseBansefi(arrayOfResponseBansefi);

		response.setEjecutarResult(result);

		return MarshallObjetoUtils
				.marshallObjeto(
						mx.babel.bansefi.banksystem.response.desconexion.EjecutarResponse.class,
						response);
	}

	private ArrayOfResponseBansefi simularResultados() {
		ArrayOfResponseBansefi arrayOfResponseBansefi = new ArrayOfResponseBansefi();

		TRDESCONEXIONIRISTRNO.TRDESCONEXIONIRISEVTZ subElemento1 = new TRDESCONEXIONIRISTRNO.TRDESCONEXIONIRISEVTZ();
		TRDESCONEXIONIRISTRNO.STDTRNMSJPARMV subElemento2 = new TRDESCONEXIONIRISTRNO.STDTRNMSJPARMV();
		TRDESCONEXIONIRISTRNO.STDTRNOPARMV subElemento3 = new TRDESCONEXIONIRISTRNO.STDTRNOPARMV();
		TRDESCONEXIONIRISTRNO elemento = new TRDESCONEXIONIRISTRNO();

		Map<Integer, String> mapa = obtenerMapaDescripciones();
		
		// Descomentar la línea de abajo para probar los casos alternos
		// Integer codigo = (int) Math.round(Math.random() * 21);

		Integer codigo = new Integer(1); // Sólo probar el camino feliz
		
		String descripcion = mapa.get(codigo);

		if (codigo.equals(1)) {
			subElemento1.setDUMMY("OK");
			subElemento2.setTEXTCODE(codigo);
			subElemento2.setTEXTARG1(descripcion);
		} else {
			subElemento1.setDUMMY("NOK");
			subElemento2.setTEXTCODE(codigo);
			subElemento2.setTEXTARG1(descripcion);
		}

		subElemento3.setFECHAOPRCN(Calendar.YEAR + Calendar.MONTH
				+ Calendar.DAY_OF_MONTH);
		subElemento3.setHORAOPRCN(Calendar.HOUR_OF_DAY);

		elemento.setRTRNCD(subElemento2.getTEXTCODE());
		elemento.setTRDESCONEXIONIRISEVTZ(subElemento1);
		elemento.setSTDTRNOPARMV(subElemento3);

		ResponseBansefi resp = new ResponseBansefi();
		resp.setTRDESCONEXIONIRISTRNO(elemento);

		arrayOfResponseBansefi.getResponseBansefi().add(resp);

		return arrayOfResponseBansefi;
	}

	private Map<Integer, String> obtenerMapaDescripciones() {
		Map<Integer, String> mapa = new HashMap<Integer, String>();
		mapa.put(0, "ERROR");
		mapa.put(1, "EXITO");
		mapa.put(2, "CAMPOS_INVALIDOS");
		mapa.put(3, "ERROR_COMUNIC");
		mapa.put(4, "ERROR_BD");
		mapa.put(5, "ABANDONAR_ACTN");
		mapa.put(6, "SALIR_ACTN");
		mapa.put(7, "DATOS_NO_ENCONT");
		mapa.put(8, "ERROR_TP");
		mapa.put(9, "CNTL_NUM_NO_IGUAL");
		mapa.put(10, "REGISTROS_MULTIPL");
		mapa.put(11, "ERROR_PROGRAMADOR");
		mapa.put(12, "ERROR_VALIDACION");
		mapa.put(13, "FIN_FICHERO");
		mapa.put(14, "INICIO_ACTN");
		mapa.put(15, "ANTERIOR_ACTN");
		mapa.put(16, "CERRAR_ACTN");
		mapa.put(17, "AUTORIZ_REQUERIDA");
		mapa.put(18, "REGISTRO_YA_EXISTE");
		mapa.put(19, "VOLVER_ACTN");
		mapa.put(20, "BIEN_ACTN");
		mapa.put(21, "FICHERO_LLENO");
		return mapa;
	}

}