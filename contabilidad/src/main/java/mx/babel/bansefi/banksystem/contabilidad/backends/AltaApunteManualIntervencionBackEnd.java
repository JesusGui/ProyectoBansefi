package mx.babel.bansefi.banksystem.contabilidad.backends;

import java.io.Serializable;
import java.util.Date;

import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.ApunteManualBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.utils.IntegerToDateConverter;
import mx.babel.bansefi.banksystem.contabilidad.webservices.altaapuntemanualintervencion.AltaApunteManualIntervencionServicio;
import mx.babel.bansefi.banksystem.contabilidad.webservices.altaapuntemanualintervencion.Ejecutar;
import mx.babel.bansefi.banksystem.contabilidad.webservices.altaapuntemanualintervencion.EjecutarResult;
import mx.babel.bansefi.banksystem.contabilidad.webservices.altaapuntemanualintervencion.ResponseBansefi;
import mx.babel.bansefi.banksystem.contabilidad.webservices.altaapuntemanualintervencion.Ejecutar.ITRAMINIAPNTEMANOTRN.TRAMINIAPNTEMANOEVT.AMAPNTEMANUALE;
import mx.babel.bansefi.banksystem.contabilidad.webservices.altaapuntemanualintervencion.Ejecutar.ITRAMINIAPNTEMANOTRN.TRAMINIAPNTEMANOEVT.CONINDFECHCRREV;
import mx.babel.bansefi.banksystem.contabilidad.wrappers.AltaApunteManualIntervencionWrapper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AltaApunteManualIntervencionBackEnd extends BackEndBean implements
		Serializable {

	private static final long serialVersionUID = -77007016225536541L;

	@Autowired
	private ServicioWebUtils servicioWebUtils;

	@Autowired
	private AltaApunteManualIntervencionWrapper altaApunteManualWrapper;

	private static final Logger LOGGER = LogManager
			.getLogger(AltaApunteManualIntervencionBackEnd.class);

	private static final String DATE_FORMAT = "yyyyMMdd";

	/**
	 * Función principal que descencadena todo el llamado de la TRN/WebService
	 * 
	 */
	public long ejecutarTRN(ApunteManualBean altaApunteBean) {
		Ejecutar.ITRAMINIAPNTEMANOTRN contexto = initPeticion(altaApunteBean);
		EjecutarResult respuesta = ejecutarWS(contexto);
		super.verificaRespuesta(respuesta);
		if (verificaResponseBansefi(respuesta)) {
			return respuestaAltaApunte(respuesta.getResponseBansefi(),
					altaApunteBean);
		}
		return 0;
	}

	/**
	 * Funcion que recibe la respuesta de la TRN y Wrappea a un objeto propio
	 * del sistema
	 * 
	 */
	private long respuestaAltaApunte(ResponseBansefi response,
			ApunteManualBean altaApunteBean) {
		if (verificaRespuestaCliente(response)) {
			// Se recuperan y wrappean los datos de la cuenta
			altaApunteBean.getCuentaContableBean().setNombreCuentaContable(
					response.getOTRAMINIAPNTEMANOTRN().getTRAMINIAPNTEMANOEVT()
							.getNOMCTACTBLE().trim());

			IntegerToDateConverter converter = new IntegerToDateConverter();
			Date fechaOperacion = converter.convertTo(response
					.getOTRAMINIAPNTEMANOTRN().getSTDTRNOPARMV()
					.getFECHAOPRCN(), null);
			Date horaOperacion = converter.convertTo(
					response.getOTRAMINIAPNTEMANOTRN().getSTDTRNOPARMV()
							.getHORAOPRCN(), null);
			
			altaApunteBean.setFechaOperacion(fechaOperacion);
			altaApunteBean.setHoraOperacion2(horaOperacion);
			
			return response.getOTRAMINIAPNTEMANOTRN().getTRAMINIAPNTEMANOEVT()
					.getAMAPNTEMANUALP().getNUMAPNTEMANUAL();
		}

		return 0;
	}

	/**
	 * Función que inicializa los parametros de entrada que necesita el servicio
	 * web para ser ejecutado
	 * 
	 * @param contadoresCentroBean
	 * @return Ejecutar.ITROBTECONTTRNI
	 */
	private Ejecutar.ITRAMINIAPNTEMANOTRN initPeticion(
			ApunteManualBean altaApunteBean) {
		Ejecutar.ITRAMINIAPNTEMANOTRN contexto = new Ejecutar.ITRAMINIAPNTEMANOTRN();
		Ejecutar.ITRAMINIAPNTEMANOTRN.STDTRNIPARMV contextoEntrada = new Ejecutar.ITRAMINIAPNTEMANOTRN.STDTRNIPARMV();
		Ejecutar.ITRAMINIAPNTEMANOTRN.TRAMINIAPNTEMANOEVT datosEntrada = new Ejecutar.ITRAMINIAPNTEMANOTRN.TRAMINIAPNTEMANOEVT();

		initialize(contexto);
		initialize(datosEntrada);

		datosEntrada = altaApunteManualWrapper
				.wrappAltaApunteManual(altaApunteBean);

		AMAPNTEMANUALE apunte = datosEntrada.getAMAPNTEMANUALE();

		apunte.setCODNRBEEN(super.getEntidad());
		apunte.setCODINTERNOUO1(super.getSucursal());
		apunte.setIDCTACTBLE(altaApunteBean.getCuentaContableBean()
				.getIdCuentaContable());
		apunte.setCODNUMRCOMONEDA("MXN");
		apunte.setFECHACTBLECON(String.valueOf(super.getFechaSistemaInteger()));

		apunte.setCONCPTAPNTE(altaApunteBean.getConcepto());
		apunte.setCONCPTAPNTELEN(altaApunteBean.getConcepto().length());
		apunte.setAUTAPNTE("N");
		apunte.setIMPMOVTO(altaApunteBean.getImporte());
		apunte.setSGNCTBLE(altaApunteBean.getTipoOperacion());
		apunte.setCTERCAJA(altaApunteBean.getDatosContrapartida());

		apunte.setCODINTERNOUO2(altaApunteBean.getOficinaDestino()
				.getClaveFila());
		apunte.setIDINTERNOTERMTN(super.getTerminal());

		CONINDFECHCRREV conindfechcrrev = new CONINDFECHCRREV();
		conindfechcrrev.setSTDSMALLINT(1);

		datosEntrada.setCONINDFECHCRREV(conindfechcrrev);

		// Valida si es el mismo centro
		if (altaApunteBean.getOficinaDestino().getClaveFila()
				.equals(super.getSucursal())) {
			datosEntrada.setINDCENTRORELAC("N");
		} else {
			datosEntrada.setINDCENTRORELAC("S");
		}
		
		datosEntrada.setTIPOOPRCN(altaApunteBean.getClaveOperacion().getClaveFila());

		contextoEntrada.setIDINTERNOTERMTN(super.getTerminal());
		contextoEntrada
				.setCODTX(CodTxConstants.COD_TX_T1_AM_INI_APNTE_MANO_TRN);
		contextoEntrada.setCODTXDI("");
		contextoEntrada.setNUMSEC(0);
		contextoEntrada.setIDEMPLAUT("");

		contexto.setTRAMINIAPNTEMANOEVT(datosEntrada);
		contexto.setSTDTRNIPARMV(contextoEntrada);
		initialize(contexto);

		return contexto;

	}

	/**
	 * Función para invocar al servicio web y obtener su respuesta.
	 * 
	 * @param contexto
	 *            Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	private EjecutarResult ejecutarWS(Ejecutar.ITRAMINIAPNTEMANOTRN contexto) {
		EjecutarResult respuesta = null;
		try {
			respuesta = (EjecutarResult) this.servicioWebUtils.ejecutarWS(
					AltaApunteManualIntervencionServicio.class, contexto);
		} catch (NoControlableException e) {
			LOGGER.error("error", e);
			throw new NoControlableException(
					"Error al invocar servicio web de alta de "
							+ "apunte manual.", e);
		}
		return respuesta;
	}

	/**
	 * Función que valida que la respuesta del servidor no este vacía.
	 * 
	 * @param respuesta
	 *            Objeto respuesta del servicio web
	 * @return <code>true</code> si la respuesta no esta vacía.
	 */
	private Boolean verificaResponseBansefi(EjecutarResult respuesta) {
		Boolean noNulo = false;
		if (respuesta != null && respuesta.getResponseBansefi() != null) {
			noNulo = true;
		}
		return noNulo;
	}

	/**
	 * Función que valida que la respuesta Bansefi contenga un objeto con los
	 * datos de la consulta.
	 * 
	 * @param response
	 *            Respuesta Bansefi con datos de la consulta
	 * @return <code>true</code> si la respuesta Bansefi contiene elemento no
	 *         nulos
	 */
	private Boolean verificaRespuestaCliente(ResponseBansefi response) {
		Boolean noNulo = false;
		if (response != null
				&& response.getOTRAMINIAPNTEMANOTRN() != null
				&& response.getOTRAMINIAPNTEMANOTRN().getTRAMINIAPNTEMANOEVT() != null
				&& response.getOTRAMINIAPNTEMANOTRN().getTRAMINIAPNTEMANOEVT()
						.getNOMCTACTBLE() != null) {
			noNulo = true;
		}
		return noNulo;
	}

}
