package mx.babel.bansefi.banksystem.endpoints;

import java.util.Random;

import mx.babel.bansefi.banksystem.response.consultacuenta.ArrayOfResponseBansefi;
import mx.babel.bansefi.banksystem.response.consultacuenta.Ejecutar;
import mx.babel.bansefi.banksystem.response.consultacuenta.ResponseBansefi;
import mx.babel.bansefi.banksystem.utils.MarshallObjetoUtils;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.w3c.dom.Element;

@Endpoint
public class ConsultaCuentaEndPoint {
	@PayloadRoot(localPart = "Ejecutar", namespace = "http://BansefiNSS/WebServicesNSS/TR_CONSULTA_ACUERDO_TRN")
	@ResponsePayload
	public Element ejecutarConsulta(@RequestPayload Element element) {
		Ejecutar objEjecutar = (Ejecutar) MarshallObjetoUtils
				.unMarshallObjeto(
						mx.babel.bansefi.banksystem.response.consultacuenta.Ejecutar.class,
						element);

		mx.babel.bansefi.banksystem.response.consultacuenta.EjecutarResponse response = new mx.babel.bansefi.banksystem.response.consultacuenta.EjecutarResponse();
		mx.babel.bansefi.banksystem.response.consultacuenta.EjecutarResult result = new mx.babel.bansefi.banksystem.response.consultacuenta.EjecutarResult();

		String entidad = objEjecutar.getTRCONSULTAACUERDOTRNI()
				.getTRCONSULTAACUERDOEVTY().getACACP().getCODNRBEEN();
		String sucursal = objEjecutar.getTRCONSULTAACUERDOTRNI()
				.getTRCONSULTAACUERDOEVTY().getACACP().getCODCENTUO();
		String cuenta = String.valueOf(objEjecutar.getTRCONSULTAACUERDOTRNI()
				.getTRCONSULTAACUERDOEVTY().getACACP().getNUMSECAC());

		ArrayOfResponseBansefi arrayOfResponseBansefi = new ArrayOfResponseBansefi();

		if (entidad != null && sucursal != null && cuenta !=null) {
			arrayOfResponseBansefi = simularResultados(cuenta);
		}

		result.setResponseBansefi(arrayOfResponseBansefi);

		response.setEjecutarResult(result);

		return MarshallObjetoUtils
				.marshallObjeto(
						mx.babel.bansefi.banksystem.response.consultacuenta.EjecutarResponse.class,
						response);
	}

	private ArrayOfResponseBansefi simularResultados(String cuenta) {
		ArrayOfResponseBansefi arrayOfResponseBansefi = new ArrayOfResponseBansefi();
		ResponseBansefi responseBansefi = new ResponseBansefi();

		ResponseBansefi.TRCONSULTAACUERDOTRNO trconsultaacuerdotrno = new ResponseBansefi.TRCONSULTAACUERDOTRNO();
		ResponseBansefi.TRCONSULTAACUERDOTRNO.TRCONSULTAACUERDOEVTZ trconsultaacuerdoevtz = new ResponseBansefi.TRCONSULTAACUERDOTRNO.TRCONSULTAACUERDOEVTZ();
		ResponseBansefi.TRCONSULTAACUERDOTRNO.TRCONSULTAACUERDOEVTZ.ACACE acace = new ResponseBansefi.TRCONSULTAACUERDOTRNO.TRCONSULTAACUERDOEVTZ.ACACE();
		ResponseBansefi.TRCONSULTAACUERDOTRNO.TRCONSULTAACUERDOEVTZ.DESCRPDVV descrpdvv = new ResponseBansefi.TRCONSULTAACUERDOTRNO.TRCONSULTAACUERDOEVTZ.DESCRPDVV();
		ResponseBansefi.TRCONSULTAACUERDOTRNO.TRCONSULTAACUERDOEVTZ.PERSONAACV personaacv = new ResponseBansefi.TRCONSULTAACUERDOTRNO.TRCONSULTAACUERDOEVTZ.PERSONAACV();

		Random rnd = new Random();
		
		// Descomentar la línea de abajo para probar los casos alternos
		// Integer codigo = (int) Math.round(rnd.nextDouble() * 21);

		Integer codigo = new Integer(1); // Sólo probar el camino feliz

		if (codigo.equals(1)) {
			// Número de cuenta (mismo que la entrada)
			acace.setNUMSECAC(Long.parseLong(cuenta));
			
			// Aleatorio del 0 al 9 para el dígito verificador
			acace.setCODDIGCRUO(String.valueOf((int) Math.round(rnd
					.nextDouble() * 9)));

			// Aleatorio para la clave de la moneda
			int indiceClaveMoneda = (int) Math.round(rnd.nextDouble() * 2);
			String arMonedas[] = { "MXP", "USD", "EUR" };
			String moneda = arMonedas[indiceClaveMoneda];
			acace.setCODNUMRCOMONEDA(moneda);

			// Aleatorio del 0 al 9999 para la plaza bancaria
			acace.setCODPLZBANCARIA(String.format("%04d",
					(int) Math.round(rnd.nextDouble() * 9999)));

			// Aleatorio para el tipo de cuenta
			int indiceTipoCuenta = (int) Math.round(rnd.nextDouble() * 3);
			String arTipoCuentas[] = { "Cheques", "Ahorro", "Inversión", "Otra" };
			String tipoCuenta = arTipoCuentas[indiceTipoCuenta];
			descrpdvv.setNOMBPDV(tipoCuenta);

			// Aleatorio para el nombre del titular
			int indiceNombreTitular = (int) Math.round(Math.random() * 3);
			String arNombreTitulares[] = { "Aquiles Serdán", "Carmen Serdan",
					"Manuela Saenz", "Carlota Armero", "Luis Echeverria" };
			String nombreTitular = arNombreTitulares[indiceNombreTitular];
			personaacv.setNOMB50(nombreTitular);

			trconsultaacuerdoevtz.setACACE(acace);
			trconsultaacuerdoevtz.setDESCRPDVV(descrpdvv);
			trconsultaacuerdoevtz.setPERSONAACV(personaacv);

			trconsultaacuerdotrno.setRTRNCD(codigo);
			trconsultaacuerdotrno.setMOREDATAIN(0);
			trconsultaacuerdotrno.setNUMBEROFRECORDS(1);
			trconsultaacuerdotrno
					.setTRCONSULTAACUERDOEVTZ(trconsultaacuerdoevtz);
		} else {
			trconsultaacuerdotrno.setRTRNCD(codigo);
			trconsultaacuerdotrno.setMOREDATAIN(0);
			trconsultaacuerdotrno.setNUMBEROFRECORDS(0);
			trconsultaacuerdotrno.setTRCONSULTAACUERDOEVTZ(null);
		}

		responseBansefi.setTRCONSULTAACUERDOTRNO(trconsultaacuerdotrno);

		arrayOfResponseBansefi.getResponseBansefi().add(responseBansefi);

		return arrayOfResponseBansefi;
	}

}