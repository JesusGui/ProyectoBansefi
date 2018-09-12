package mx.babel.bansefi.banksystem.cuentas.backends;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.LiquidacionBean;
import mx.babel.bansefi.banksystem.base.beans.LiquidacionNumerosBean;
import mx.babel.bansefi.banksystem.base.beans.LiquidacionNumerosDetalleBean;
import mx.babel.bansefi.banksystem.base.beans.LiquidacionSaldoBean;
import mx.babel.bansefi.banksystem.base.beans.domain.CuentaBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.constants.ConstantesFuncionales;
import mx.babel.bansefi.banksystem.base.utils.IntegerToDateConverter;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultanumsliquidacion.ConsultaNumsLiquidacionServicio;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultanumsliquidacion.Ejecutar.ITRLIQCONSULTARNUMEROS;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultanumsliquidacion.Ejecutar.ITRLIQCONSULTARNUMEROS.TRLIQCONSULTARNUMEROS.HLSDOLST.HLSDOEV;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultanumsliquidacion.EjecutarResult;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultanumsliquidacion.ResponseBansefi.OTRLIQCONSULTARNUMEROS.TRLIQCONSULTARNUMEROS;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultanumsliquidacion.ResponseBansefi.OTRLIQCONSULTARNUMEROS.TRLIQCONSULTARNUMEROS.LIQNUMEROSLST.LIQNUMEROSV;
import mx.babel.bansefi.banksystem.cuentas.wrappers.ConsultaLiquidacionesWrapper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Backend para la TRN de consulta de números de liquidación
 * (TR_LIQ_CONSULTAR_NUMEROS_TRN).
 */
@Component
public class ConsultaNumsLiquidacionBackend extends BackEndBean {

	private static final long serialVersionUID = 439220593020677474L;

	private static final Logger LOGGER = LogManager
			.getLogger(ConsultaNumsLiquidacionBackend.class);

	private static final String COD_LINEA_01 = "01";
	private static final String COD_LINEA_03 = "03";
	private static final String ID_GPO_PROD_11 = "11";
	private static final String ID_GPO_PROD_15 = "15";
	private static final String ID_GPO_PROD_18 = "18";
	private static final String ID_GPO_PROD_21 = "21";
	private static final String ID_GPO_PROD_25 = "25";
	private static final String ID_GPO_PROD_41 = "41";
	private static final String ID_GPO_PROD_71 = "71";

	private IntegerToDateConverter integerToDateConverter;

	@Autowired
	ServicioWebUtils servicioWebUtils;

	@Autowired
	ConsultaLiquidacionesWrapper consultaLiquidacionesWrapper;

	/**
	 * Constructor.
	 */
	public ConsultaNumsLiquidacionBackend() {
		super();
		this.integerToDateConverter = new IntegerToDateConverter();
	}

	/**
	 * Método que ejecuta la TRN de consulta de números de una liquidación.
	 * 
	 * @param cuentaBean
	 * @param liquidacionBean
	 * @param event_cd_consulta
	 *            (puede ser 0 ó 1, 0 indica que son números de liquidación y 1
	 *            que son números de demora)
	 * @return liquidacionNumerosBean
	 * @throws NullPointerException
	 * @throws ControlableException
	 * @throws NoControlableException
	 */
	public LiquidacionNumerosBean ejecutarTRN(CuentaBean cuentaBean,
			LiquidacionBean liquidacionBean, Integer event_cd_consulta)
			throws NullPointerException, ControlableException,
			NoControlableException {
		ITRLIQCONSULTARNUMEROS contexto = initPeticion(cuentaBean,
				liquidacionBean, event_cd_consulta);

		EjecutarResult respuesta = (EjecutarResult) servicioWebUtils
				.ejecutarWS(ConsultaNumsLiquidacionServicio.class, contexto);

		super.verificaRespuesta(respuesta);

		return getNumsLiquidacion(respuesta, liquidacionBean);
	}

	/**
	 * Método privado que prepara los datos que serán enviados a la TRN.
	 * 
	 * @param cuentaBean
	 * @param liquidacionBean
	 * @param event_cd_consulta
	 *            (puede ser 0 ó 1, 0 indica que son números de liquidación y 1
	 *            que son números de demora)
	 * @return parametros de entrada encapsulados en un objeto
	 *         ITRLIQCONSULTARNUMEROS
	 */
	private ITRLIQCONSULTARNUMEROS initPeticion(CuentaBean cuentaBean,
			LiquidacionBean liquidacionBean, Integer event_cd_consulta) {
		ITRLIQCONSULTARNUMEROS itrliqconsultarnumeros = new ITRLIQCONSULTARNUMEROS();

		super.initialize(itrliqconsultarnumeros);

		// Mapear los atributos correspondientes a la cuenta y a la liquidación.
		itrliqconsultarnumeros.getTRLIQCONSULTARNUMEROS().getHLHCOLIQE()
				.setCODNRBEEN(super.getEntidad());
		itrliqconsultarnumeros.getTRLIQCONSULTARNUMEROS().getHLHCOLIQE()
				.setNUMSECAC(cuentaBean.getNumeroCuenta());
		itrliqconsultarnumeros
				.getTRLIQCONSULTARNUMEROS()
				.getHLHCOLIQE()
				.setFECHALIQ(
						integerToDateConverter.convertFrom(liquidacionBean
								.getFechaLiquidacion()));
		itrliqconsultarnumeros.getTRLIQCONSULTARNUMEROS().getHLHCOLIQE()
				.setNUMSEC(liquidacionBean.getNumsec());
		itrliqconsultarnumeros
				.getTRLIQCONSULTARNUMEROS()
				.getHLHCOLIQE()
				.setFECHADESDE(
						integerToDateConverter.convertFrom(liquidacionBean
								.getPeriodoDesdeLiquidacion()));
		itrliqconsultarnumeros
				.getTRLIQCONSULTARNUMEROS()
				.getHLHCOLIQE()
				.setFECHAHASTA(
						integerToDateConverter.convertFrom(liquidacionBean
								.getPeriodoHastaLiquidacion()));
		itrliqconsultarnumeros.getTRLIQCONSULTARNUMEROS().getHLHCOLIQE()
				.setIMPPENDLIQ(liquidacionBean.getImportePendiente());
		itrliqconsultarnumeros.getTRLIQCONSULTARNUMEROS().getHLHCOLIQE()
				.setIMPTOTALLIQ(liquidacionBean.getNetoOperacion());
		itrliqconsultarnumeros.getTRLIQCONSULTARNUMEROS().getHLHCOLIQE()
				.setSITUACIONHL(liquidacionBean.getSituacion());

		// Verificamos la fecha del último movimiento realizado.
		if (liquidacionBean.getFechaUltimoMov() != null) {
			itrliqconsultarnumeros
					.getTRLIQCONSULTARNUMEROS()
					.getHLHCOLIQE()
					.setFECHAULTCOBRO(
							integerToDateConverter.convertFrom(liquidacionBean
									.getFechaUltimoMov()));
		}
		// Verificamos la fecha del último calculo realizado.
		if (liquidacionBean.getFechaUltimoCalc() != null) {
			itrliqconsultarnumeros
					.getTRLIQCONSULTARNUMEROS()
					.getHLHCOLIQE()
					.setFECULTCALCDMRA(
							integerToDateConverter.convertFrom(liquidacionBean
									.getFechaUltimoCalc()));
		}

		itrliqconsultarnumeros.getTRLIQCONSULTARNUMEROS().getHLHCOLIQE()
				.setSGN(liquidacionBean.getSigno());
		itrliqconsultarnumeros.getTRLIQCONSULTARNUMEROS().getHLHCOLIQE()
				.setINDCOBPAR(liquidacionBean.getIndCobroParcial());
		itrliqconsultarnumeros.getTRLIQCONSULTARNUMEROS().getHLHCOLIQE()
				.setINDDEVENGO(liquidacionBean.getIndDevengo());
		itrliqconsultarnumeros.getTRLIQCONSULTARNUMEROS().getHLHCOLIQE()
				.setINDCOMPDIAS(liquidacionBean.getIndCompDias());
		itrliqconsultarnumeros.getTRLIQCONSULTARNUMEROS().getHLHCOLIQE()
				.setINDBASECALC(liquidacionBean.getIndBaseCalc());
		itrliqconsultarnumeros.getTRLIQCONSULTARNUMEROS().getHLHCOLIQE()
				.setINDSITCOM(liquidacionBean.getIndSitComercial());
		itrliqconsultarnumeros.getTRLIQCONSULTARNUMEROS().getHLHCOLIQE()
				.setCODLINEA(cuentaBean.getCodLinea());
		itrliqconsultarnumeros.getTRLIQCONSULTARNUMEROS().getHLHCOLIQE()
				.setIDGRPPD(cuentaBean.getIdGrupoProducto());
		itrliqconsultarnumeros.getTRLIQCONSULTARNUMEROS().getHLHCOLIQE()
				.setCODOPERLIQ(liquidacionBean.getCodOperacionLiq());
		itrliqconsultarnumeros.getTRLIQCONSULTARNUMEROS().getHLHCOLIQE()
				.setCODNUMRCOMONEDA(ConstantesFuncionales.COD_MONEDA);

		// Mapear los N saldos encontrados.
		if (liquidacionBean.getListaSaldos() != null
				&& liquidacionBean.getListaSaldos().size() > 0) {
			mapearListaSaldos(liquidacionBean, itrliqconsultarnumeros);
		}

		// Verificamos el origen de la consulta (1 = demora, 0 = liquidación).
		if (event_cd_consulta != null && event_cd_consulta.intValue() == 1) {
			itrliqconsultarnumeros.getTRLIQCONSULTARNUMEROS().setEVENTCD(
					event_cd_consulta);
		} else {
			itrliqconsultarnumeros.getTRLIQCONSULTARNUMEROS().setEVENTCD(0);
		}

		itrliqconsultarnumeros.getSTDTRNIPARMV().setIDINTERNOTERMTN(
				super.getTerminal());
		itrliqconsultarnumeros.getSTDTRNIPARMV().setCODTX(
				CodTxConstants.COD_TX_TR_LIQ_CONSULTAR_NUMEROS_TRN);

		return itrliqconsultarnumeros;
	}

	/**
	 * Método privado que a partir de un objeto LiquidacionBean, obtiene los
	 * valores de los saldos y los establece en un objeto ITRLIQCONSULTARNUMEROS
	 * que contiene todos los elementos de entrada.
	 * 
	 * @param liquidacionBean
	 * @param itrliqconsultarnumeros
	 * @throws NullPointerException
	 */
	private void mapearListaSaldos(LiquidacionBean liquidacionBean,
			ITRLIQCONSULTARNUMEROS itrliqconsultarnumeros)
			throws NullPointerException {
		for (Iterator<LiquidacionSaldoBean> iterator = liquidacionBean
				.getListaSaldos().iterator(); iterator.hasNext();) {
			LiquidacionSaldoBean saldo = (LiquidacionSaldoBean) iterator.next();
			HLSDOEV hlsdoev = new HLSDOEV();
			hlsdoev.setCODCTA(saldo.getCodCta());
			hlsdoev.setCODSDO(saldo.getCodSaldo());
			hlsdoev.setIMPSDOHL(saldo.getImporteSaldo());
			hlsdoev.setINDSALDO(saldo.getIndSaldo());
			itrliqconsultarnumeros.getTRLIQCONSULTARNUMEROS().getHLSDOLST()
					.getHLSDOEV().add(hlsdoev);
		}
		itrliqconsultarnumeros.getTRLIQCONSULTARNUMEROS().getHLSDOLST()
				.setNUMELEMS(liquidacionBean.getListaSaldos().size());
	}

	/**
	 * Método privado encargado de devolver un objeto LiquidacionNumerosBean con
	 * los valores de la respuesta.
	 * 
	 * @param respuesta
	 * @param liquidacionBean
	 * @return liquidacionNumerosBean
	 * @throws NullPointerException
	 */
	private LiquidacionNumerosBean getNumsLiquidacion(EjecutarResult respuesta,
			LiquidacionBean liquidacionBean) throws NullPointerException {
		LiquidacionNumerosBean result = new LiquidacionNumerosBean();

		result.setFechaPeriodoDesde(liquidacionBean
				.getPeriodoDesdeLiquidacion());
		result.setFechaPeriodoHasta(liquidacionBean
				.getPeriodoHastaLiquidacion());
		result.setCodOperacion(liquidacionBean.getTipoLiquidacion());

		List<LiquidacionNumerosDetalleBean> numerosLiq = new ArrayList<>();
		if (verificaRespuesta(respuesta)) {
			TRLIQCONSULTARNUMEROS liquidacion = respuesta.getResponseBansefi()
					.getOTRLIQCONSULTARNUMEROS().getTRLIQCONSULTARNUMEROS();
			// 01 71 y 01 41 con 11/11/1111 no agregarlos
			for (Iterator<LIQNUMEROSV> iterator = liquidacion
					.getLIQNUMEROSLST().getLIQNUMEROSV().iterator(); iterator
					.hasNext();) {
				LIQNUMEROSV num = (LIQNUMEROSV) iterator.next();
				if (num.getFECHAVALOR() != 0) {
					LiquidacionNumerosDetalleBean numRes = new LiquidacionNumerosDetalleBean();
					numRes = consultaLiquidacionesWrapper.wrappNumsLiq(num);
					if (liquidacionBean.getCodLinea().equals(COD_LINEA_01)
							&& (liquidacionBean.getIdGrpPd().equals(
									ID_GPO_PROD_41) || liquidacionBean
									.getIdGrpPd().equals(ID_GPO_PROD_71))) {
						LOGGER.debug("Cuenta con naturaleza deudora (01-41 ó 01-71).");
						// Núm. Acreedores
						numRes.setNumAcreedores(num.getLIQNUMAV().get(0)
								.getSTDDEC182());
						// Núm. Deudores
						numRes.setNumDeudores(num.getLIQNUMDV().get(0)
								.getSTDDEC182());
						// Núm. Deudores Autorizados
						numRes.setNumDeudoresAut(BigDecimal.ZERO);
						// Núm. Excedidos
						numRes.setNumExcedidos(num.getLIQNUMEV().get(0)
								.getSTDDEC182());
						// Sumatoria Acreedores
						result.setSmAcreedores(result.getSmAcreedores().add(
								num.getLIQNUMAV().get(0).getSTDDEC182()));
						// Sumatoria Deudores
						result.setSmDeudores(result.getSmDeudores().add(
								num.getLIQNUMDV().get(0).getSTDDEC182()));
						// Sumatoria Excedidos
						result.setSmExcedidos(result.getSmExcedidos().add(
								num.getLIQNUMEV().get(0).getSTDDEC182()));
					} else {
						if (liquidacionBean.getCodLinea().equals(COD_LINEA_03)
								&& (liquidacionBean.getIdGrpPd().equals(
										ID_GPO_PROD_11)
										|| liquidacionBean.getIdGrpPd().equals(
												ID_GPO_PROD_15)
										|| liquidacionBean.getIdGrpPd().equals(
												ID_GPO_PROD_18)
										|| liquidacionBean.getIdGrpPd().equals(
												ID_GPO_PROD_21) || liquidacionBean
										.getIdGrpPd().equals(ID_GPO_PROD_25))) {
							LOGGER.debug("Cuenta con naturaleza acreedora (03-11 ó 03-15 ó 03-18 ó 03-21 ó 03-25).");
							// Núm. Acreedores
							numRes.setNumAcreedores(num.getLIQNUMAV().get(0)
									.getSTDDEC182());
							// Núm. Deudores
							numRes.setNumDeudores(BigDecimal.ZERO);
							// Núm. Deudores Autorizados
							numRes.setNumDeudoresAut(num.getLIQNUMDV().get(0)
									.getSTDDEC182());
							// Núm. Excedidos
							numRes.setNumExcedidos(num.getLIQNUMEV().get(0)
									.getSTDDEC182());
							// Sumatoria Acreedores
							result.setSmAcreedores(result.getSmAcreedores()
									.add(num.getLIQNUMAV().get(0)
											.getSTDDEC182()));
							// Sumatoria Deudores Autorizados
							result.setSmDeudoresAut(result.getSmDeudoresAut()
									.add(num.getLIQNUMDV().get(0)
											.getSTDDEC182()));
							// Sumatoria Excedidos
							result.setSmExcedidos(result.getSmExcedidos().add(
									num.getLIQNUMEV().get(0).getSTDDEC182()));
						} else {
							LOGGER.debug("Cuenta sin naturaleza filtrada, caso de excepción.");
							// Núm. Acreedores
							numRes.setNumAcreedores(BigDecimal.ZERO);
							// Núm. Deudores
							numRes.setNumDeudores(num.getLIQNUMDV().get(0)
									.getSTDDEC182());
							// Núm. Deudores Autorizados
							numRes.setNumDeudoresAut(BigDecimal.ZERO);
							// Núm. Excedidos
							numRes.setNumExcedidos(BigDecimal.ZERO);
							// Sumatoria Deudores
							result.setSmDeudores(result.getSmDeudores().add(
									num.getLIQNUMDV().get(0).getSTDDEC182()));
						}
					}
					result.setSmDias(result.getSmDias() + numRes.getDias());
					numerosLiq.add(numRes);
				}
			}
		}
		result.setRegistrosNumeros(numerosLiq);
		return result;
	}

	/**
	 * Método privado que devuelve un indicador booleano para determinar sí la
	 * respuesta contiene datos.
	 * 
	 * @param respuesta
	 * @return indicador booleano
	 */
	private boolean verificaRespuesta(EjecutarResult respuesta) {
		if (respuesta != null
				&& respuesta.getResponseBansefi().getOTRLIQCONSULTARNUMEROS() != null
				&& respuesta.getResponseBansefi().getOTRLIQCONSULTARNUMEROS()
						.getTRLIQCONSULTARNUMEROS() != null
				&& respuesta.getResponseBansefi().getOTRLIQCONSULTARNUMEROS()
						.getTRLIQCONSULTARNUMEROS().getLIQNUMEROSLST() != null
				&& !respuesta.getResponseBansefi().getOTRLIQCONSULTARNUMEROS()
						.getTRLIQCONSULTARNUMEROS().getLIQNUMEROSLST()
						.getLIQNUMEROSV().isEmpty()) {
			return true;
		}
		return false;
	}

}