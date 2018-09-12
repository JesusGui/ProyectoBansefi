package mx.babel.bansefi.banksystem.cuentas.backends;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.LiquidacionInformacionBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.utils.IntegerToDateConverter;
import mx.babel.bansefi.banksystem.base.utils.StringToBigDecimalConverter;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultainfoliquidacion.ConsultaInfoLiquidacionServicio;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultainfoliquidacion.Ejecutar.ITRCONSHLCDAPLICTRN;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultainfoliquidacion.EjecutarResult;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultainfoliquidacion.ResponseBansefi;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultainfoliquidacion.ResponseBansefi.OTRCONSHLCDAPLICTRN.TRCONSHLCDAPLICEVTZ;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Backend para la consulta de informacion de liquidaciones.
 * 
 * @author javier.martinnino
 * 
 */
@Component
public class ConsultaInfoLiquidacionesBackend extends BackEndBean {

	private static final long serialVersionUID = -4397098606099961316L;

	@Autowired
	ServicioWebUtils servicioWebUtils;

	@Autowired
	ConsultaInfoLiqAdicionalTRDBackend consultaInfoLiqAdicionalTRDBackend;

	/**
	 * Método que ejecuta la transacción de consulta de información de
	 * liquidaciones.
	 * 
	 * @param numeroCuenta
	 * @param fechaLiquidacion
	 * @param numSec
	 * @return lista de objetos tipo LiquidacionInformacionBean
	 */
	public List<LiquidacionInformacionBean> ejecutarTRN(long numeroCuenta,
			Date fechaLiquidacion, Integer numSec) {
		ITRCONSHLCDAPLICTRN contexto = initPeticion(numeroCuenta,
				fechaLiquidacion, numSec);

		EjecutarResult respuesta = ejecutarWS(contexto);

		super.verificaRespuesta(respuesta);

		return consultaInfoLiquidacion(respuesta.getResponseBansefi(),
				numeroCuenta, fechaLiquidacion, numSec);
	}

	/**
	 * Método privado que devuelve una lista de objetos tipo
	 * LiquidacionInformacionBean.
	 * 
	 * @param response
	 * @param numeroCuenta
	 * @return lista de objetos tipo LiquidacionInformacionBean
	 * @throws NoControlableException
	 * @throws ControlableException
	 */
	private List<LiquidacionInformacionBean> consultaInfoLiquidacion(
			ResponseBansefi response, long numeroCuenta, Date fechaLiquidacion,
			Integer numSec) throws NoControlableException, ControlableException {
		List<LiquidacionInformacionBean> resultado = null;

		if (verificaRespuestaCliente(response)) {
			resultado = new ArrayList<LiquidacionInformacionBean>();
			boolean hayRegistros = true;
			TRCONSHLCDAPLICEVTZ listaInfoLiquidaciones = response
					.getOTRCONSHLCDAPLICTRN().getTRCONSHLCDAPLICEVTZ();
			for (int i = 0; i < listaInfoLiquidaciones.getHLCDAPLICE().size()
					&& hayRegistros; i++) {
				if (("").equals(listaInfoLiquidaciones.getHLCDAPLICE().get(i)
						.getCODNRBEEN().trim())) {
					hayRegistros = false;
				} else {
					LiquidacionInformacionBean infoLiquidacion = new LiquidacionInformacionBean();
					infoLiquidacion.setDeposito(listaInfoLiquidaciones
							.getLIQIMPINFADICHLV().get(i).getSTDDEC15Y2());
					consultaInfoLiqAdicionalTRDBackend.ejecutarTRN(
							infoLiquidacion, numeroCuenta, fechaLiquidacion,
							numSec,
							listaInfoLiquidaciones.getHLCDAPLICE().get(i)
									.getCODINFADICHL().trim(),
							listaInfoLiquidaciones.getHLCDAPLICE().get(i)
									.getVALORINFADICHL().trim());
					if (listaInfoLiquidaciones.getHLCDAPLICE().get(i)
							.getCODINFADICHL().startsWith("E")) {
						StringToBigDecimalConverter converter = new StringToBigDecimalConverter();
						String deposito = infoLiquidacion.getValor();
						String valor = converter.convertFrom(infoLiquidacion
								.getDeposito());
						infoLiquidacion.setDeposito(converter
								.convertTo(deposito));
						infoLiquidacion.setValor(valor);
					}
					resultado.add(infoLiquidacion);
				}
			}
		}
		return resultado;
	}

	/**
	 * Función para inicializar los atributos del objeto de peticíon al servicio
	 * web.
	 * 
	 * @param numeroCuenta
	 *            el numero de cuenta a consultar
	 * @param fechaLiquidacion
	 *            la fecha de liquidacion a consultar
	 * @return Objeto de petición al web service
	 */
	private ITRCONSHLCDAPLICTRN initPeticion(long numeroCuenta,
			Date fechaLiquidacion, Integer numSec) {
		ITRCONSHLCDAPLICTRN contexto = new ITRCONSHLCDAPLICTRN();
		ITRCONSHLCDAPLICTRN.STDTRNIPARMV contextoEntrada = new ITRCONSHLCDAPLICTRN.STDTRNIPARMV();
		ITRCONSHLCDAPLICTRN.TRCONSHLCDAPLICEVTY consultaLiquidaciones = new ITRCONSHLCDAPLICTRN.TRCONSHLCDAPLICEVTY();
		ITRCONSHLCDAPLICTRN.TRCONSHLCDAPLICEVTY.HLCDAPLICE consultaLiquidacionesDatos = new ITRCONSHLCDAPLICTRN.TRCONSHLCDAPLICEVTY.HLCDAPLICE();

		contextoEntrada.setCODTX(CodTxConstants.COD_TX_TR_CONS_HL_CD_APLIC_TRN);
		contextoEntrada.setCODTXDI("");
		contextoEntrada.setIDEMPLAUT("");
		contextoEntrada.setIDINTERNOTERMTN(super.getTerminal());

		consultaLiquidacionesDatos.setCODNRBEEN(super.getEntidad());
		consultaLiquidacionesDatos.setNUMSECAC(numeroCuenta);
		consultaLiquidacionesDatos.setNUMSEC(numSec);
		IntegerToDateConverter converter = new IntegerToDateConverter();
		consultaLiquidacionesDatos.setFECHALIQ(converter
				.convertFrom(fechaLiquidacion));

		consultaLiquidaciones.setHLCDAPLICE(consultaLiquidacionesDatos);

		contexto.setTRCONSHLCDAPLICEVTY(consultaLiquidaciones);
		contexto.setSTDTRNIPARMV(contextoEntrada);
		contexto.setELEVATORPOSITION(1);
		contexto.setSCROLLABLEOCCURS(SCROLLABLE_OCCURS);

		super.initialize(contexto);

		return contexto;
	}

	/**
	 * Método privado que invoca al servicio web y obtiene su respuesta.
	 * 
	 * @param contexto
	 * @return respuesta
	 */
	private EjecutarResult ejecutarWS(ITRCONSHLCDAPLICTRN contexto) {
		EjecutarResult respuesta = null;
		try {
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
					ConsultaInfoLiquidacionServicio.class, contexto);
		} catch (NoControlableException e) {
			throw new NoControlableException(
					"Error al invocar servicio web de consulta de "
							+ "informacion de liquidaciones.", e);
		}
		return respuesta;
	}

	/**
	 * Método privado que devuelve un indicador booleano para determinar sí la
	 * respuesta contiene datos.
	 * 
	 * @param response
	 * @return indicador booleano
	 */
	private Boolean verificaRespuestaCliente(ResponseBansefi response) {
		if (response != null
				&& response.getOTRCONSHLCDAPLICTRN() != null
				&& response.getOTRCONSHLCDAPLICTRN().getTRCONSHLCDAPLICEVTZ() != null
				&& response.getOTRCONSHLCDAPLICTRN().getTRCONSHLCDAPLICEVTZ()
						.getHLCDAPLICE() != null
				&& response.getOTRCONSHLCDAPLICTRN().getTRCONSHLCDAPLICEVTZ()
						.getLIQIMPINFADICHLV() != null) {
			return true;
		}
		return false;
	}

}