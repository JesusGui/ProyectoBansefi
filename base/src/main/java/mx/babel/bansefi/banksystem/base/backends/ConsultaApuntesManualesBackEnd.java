package mx.babel.bansefi.banksystem.base.backends;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.beans.busqueda.ApunteManualBusquedaBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.utils.IntegerToDateConverter;
import mx.babel.bansefi.banksystem.base.webservices.consultaapuntesmanuales.ConsultaApuntesManualesServicio;
import mx.babel.bansefi.banksystem.base.webservices.consultaapuntesmanuales.Ejecutar;
import mx.babel.bansefi.banksystem.base.webservices.consultaapuntesmanuales.Ejecutar.ITRAMCONSAPNTELSTTRN.TRAMCONSAPNTELSTEVT.AMAPNTEMANUALP;
import mx.babel.bansefi.banksystem.base.webservices.consultaapuntesmanuales.EjecutarResult;
import mx.babel.bansefi.banksystem.base.webservices.consultaapuntesmanuales.ResponseBansefi;
import mx.babel.bansefi.banksystem.base.wrappers.ConsultaApuntesManualesWrapper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConsultaApuntesManualesBackEnd extends BackEndBean implements
		Serializable {

	private static final long serialVersionUID = -934376331852800365L;

	@Autowired
	private ServicioWebUtils servicioWebUtils;

	private static final Logger LOGGER = LogManager
			.getLogger(ConsultaApuntesManualesBackEnd.class);

	@Autowired
	ConsultaApuntesManualesWrapper consultaApuntesManualesWrapper;

	/**
	 * Función principal que descencadena todo el llamado de la TRN/WebService
	 * 
	 */
	public List<ApunteManualBusquedaBean> ejecutarTRN(String idCuentaContable,
			Date fechaInicial, Date fechaFinal) {
		Ejecutar.ITRAMCONSAPNTELSTTRN contexto = initPeticion(idCuentaContable,
				fechaInicial, fechaFinal);
		EjecutarResult respuesta = ejecutarWS(contexto);
		super.verificaRespuesta(respuesta);
		if (verificaResponseBansefi(respuesta)) {
			return respuestaAltaApunte(respuesta.getResponseBansefi());
		}
		return null;
	}

	/**
	 * Funcion que recibe la respuesta de la TRN y Wrappea a un objeto propio
	 * del sistema
	 * 
	 */
	private List<ApunteManualBusquedaBean> respuestaAltaApunte(ResponseBansefi response) {
		if (verificaRespuestaCliente(response)) {
			List<ApunteManualBusquedaBean> listaApuntes = new ArrayList<ApunteManualBusquedaBean>();
			// Se recuperan y wrappean los datos de la cuenta
			int nRecords = response.getOTRAMCONSAPNTELSTTRN()
					.getNUMBEROFRECORDS();

			if (nRecords > 0) {
				for (ResponseBansefi.OTRAMCONSAPNTELSTTRN.TRAMCONSAPNTELSTEVT.AMAPNTEMANUALE apunte : response
						.getOTRAMCONSAPNTELSTTRN().getTRAMCONSAPNTELSTEVT()
						.getAMAPNTEMANUALE()) {
					ApunteManualBusquedaBean apunteManual = consultaApuntesManualesWrapper
							.wrappApunteManualBean(apunte);
					listaApuntes.add(apunteManual);
				}
			}
			return listaApuntes;
//			return apuntesSimul();
		}

		return null;
	}

	public List<ApunteManualBusquedaBean> apuntesSimul() {
		List<ApunteManualBusquedaBean> lista = new ArrayList<ApunteManualBusquedaBean>();
		for (int i = 0; i < 50; i++) {
			ApunteManualBusquedaBean apunte = new ApunteManualBusquedaBean();
			apunte.setFechaOperacion("23/05/1991");
			apunte.setFechaContable("07/08/2013");
			apunte.setNoApunte(12587943);
			apunte.setOrigen("0121");
			apunte.setDestino("0121");
			apunte.setImporte(new BigDecimal("539658"));
			apunte.setDebeHaber("D");
			apunte.setSituacion("NORMAL");
			apunte.setHora("12:25:41");
			apunte.setTerminal("12012115");
			apunte.setConcepto("GASTOS HOSPEDAJE");
			apunte.setContraPartida("CT");
			apunte.setCuenta("1970151");
			apunte.setClaveOperacion("019999");
			apunte.setNoSecuencia("5343843");
			apunte.setIdCuentaContable("A6041034");
			lista.add(apunte);
			
		}
		return lista;
	}

	/**
	 * Función que inicializa los parametros de entrada que necesita el servicio
	 * web para ser ejecutado
	 * 
	 * @param contadoresCentroBean
	 * @return Ejecutar.ITROBTECONTTRNI
	 */
	private Ejecutar.ITRAMCONSAPNTELSTTRN initPeticion(String idCuentaContable,
			Date fechaInicial, Date fechaFinal) {
		Ejecutar.ITRAMCONSAPNTELSTTRN contexto = new Ejecutar.ITRAMCONSAPNTELSTTRN();
		Ejecutar.ITRAMCONSAPNTELSTTRN.STDTRNIPARMV contextoEntrada = new Ejecutar.ITRAMCONSAPNTELSTTRN.STDTRNIPARMV();
		Ejecutar.ITRAMCONSAPNTELSTTRN.TRAMCONSAPNTELSTEVT datosEntrada = new Ejecutar.ITRAMCONSAPNTELSTTRN.TRAMCONSAPNTELSTEVT();

		initialize(contexto);
		initialize(datosEntrada);

		contexto.setSCROLLABLEOCCURS(50);
		AMAPNTEMANUALP apunte = datosEntrada.getAMAPNTEMANUALP();
		apunte.setCODNRBEEN(super.getEntidad());
		apunte.setCODINTERNOUO1(super.getSucursal());
		apunte.setIDCTACTBLE(idCuentaContable);

		apunte.setCODNUMRCOMONEDA("MXN");

		IntegerToDateConverter converter = new IntegerToDateConverter();
		datosEntrada.getFRFECHADV().setSTDFECHA(
				converter.convertFrom(fechaInicial, null));
		datosEntrada.getFRFECHAHV().setSTDFECHA(super.getFechaSistemaInteger());
		datosEntrada.getFECHAOPRCN1V().setFECHAOPRCN(
				converter.convertFrom(fechaFinal, null));
		datosEntrada.getCODINTERNOUOV().setCODINTERNOUO(super.getSucursal());

		contextoEntrada.setIDINTERNOTERMTN(super.getTerminal());
		contextoEntrada
				.setCODTX(CodTxConstants.COD_TX_TR_AM_CONS_APNTE_LST_TRN);
		contextoEntrada.setCODTXDI("");
		contextoEntrada.setNUMSEC(0);
		contextoEntrada.setIDEMPLAUT("");

		contexto.setTRAMCONSAPNTELSTEVT(datosEntrada);
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
	private EjecutarResult ejecutarWS(Ejecutar.ITRAMCONSAPNTELSTTRN contexto) {
		EjecutarResult respuesta = null;
		try {
			respuesta = (EjecutarResult) this.servicioWebUtils.ejecutarWS(
					ConsultaApuntesManualesServicio.class, contexto);
		} catch (NoControlableException e) {
			LOGGER.error("error", e);
			throw new NoControlableException(
					"Error al invocar servicio web de consulta de "
							+ "apuntes manuales.", e);
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
				&& response.getOTRAMCONSAPNTELSTTRN() != null
				&& response.getOTRAMCONSAPNTELSTTRN().getTRAMCONSAPNTELSTEVT() != null
				&& response.getOTRAMCONSAPNTELSTTRN().getTRAMCONSAPNTELSTEVT()
						.getAMAPNTEMANUALE() != null) {
			noNulo = true;
		}
		return noNulo;
	}
}
