package mx.babel.bansefi.banksystem.cuentas.backends;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.LiquidacionBean;
import mx.babel.bansefi.banksystem.base.beans.LiquidacionConceptoBean;
import mx.babel.bansefi.banksystem.base.beans.LiquidacionConceptoDemoraBean;
import mx.babel.bansefi.banksystem.base.beans.LiquidacionInformacionBean;
import mx.babel.bansefi.banksystem.base.beans.LiquidacionSaldoBean;
import mx.babel.bansefi.banksystem.base.beans.ParametrosBusquedaApunteBean;
import mx.babel.bansefi.banksystem.base.beans.domain.CuentaBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.constants.ConstantesFuncionales;
import mx.babel.bansefi.banksystem.base.utils.IntegerToDateConverter;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultadetalleliquidacion.ConsultaBasicaLiquidacionServicio;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultadetalleliquidacion.Ejecutar;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultadetalleliquidacion.Ejecutar.ITRLIQCONSBASICATRNI;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultadetalleliquidacion.EjecutarResult;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultadetalleliquidacion.ResponseBansefi;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultadetalleliquidacion.ResponseBansefi.OTRLIQCONSBASICATRNO.TRLIQCONSBASICAEVTZ.HLHCOLIQE;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultadetalleliquidacion.ResponseBansefi.OTRLIQCONSBASICATRNO.TRLIQCONSBASICAEVTZ.HLSDOLST.HLSDOEV;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultadetalleliquidacion.ResponseBansefi.OTRLIQCONSBASICATRNO.TRLIQCONSBASICAEVTZ.LIQAPUNTEBASLST.LIQAPUNTEBASV;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultadetalleliquidacion.ResponseBansefi.OTRLIQCONSBASICATRNO.TRLIQCONSBASICAEVTZ.LIQCONSDEMORAV.HLTRAMOV;
import mx.babel.bansefi.banksystem.cuentas.wrappers.ConsultaApunteWrapper;
import mx.babel.bansefi.banksystem.cuentas.wrappers.ConsultaLiquidacionesWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Backend para la TRN de consulta básica de una liquidación
 * (TR_LIQ_CONS_BASICA_TRN).
 */
@Component
public class ConsultaBasicaLiquidacionBackend extends BackEndBean {

	private static final long serialVersionUID = -2998996784778732048L;

	private IntegerToDateConverter integerToDateConverter;

	@Autowired
	ServicioWebUtils servicioWebUtils;

	@Autowired
	ConsultaApunteWrapper consultaApunteWrapper;

	@Autowired
	ConsultaLiquidacionesWrapper consultaLiquidacionesWrapper;

	/**
	 * Constructor.
	 */
	public ConsultaBasicaLiquidacionBackend() {
		super();
		this.integerToDateConverter = new IntegerToDateConverter();
	}

	/**
	 * Método que ejecuta la TRN de consulta básica de una liquidación.
	 * 
	 * @param cuentaBean
	 * @param liquidacionBean
	 * @throws NullPointerException
	 * @throws ControlableException
	 * @throws NoControlableException
	 */
	public void ejecutarTRN(CuentaBean cuentaBean,
			LiquidacionBean liquidacionBean) throws NullPointerException,
			ControlableException, NoControlableException {
		Ejecutar.ITRLIQCONSBASICATRNI contexto = initPeticion(cuentaBean,
				liquidacionBean);

		EjecutarResult respuesta = (EjecutarResult) servicioWebUtils
				.ejecutarWS(ConsultaBasicaLiquidacionServicio.class, contexto);

		super.verificaRespuesta(respuesta);

		obtenerDetalleLiquidacion(respuesta, liquidacionBean);
	}

	/**
	 * Método privado que prepara los datos que serán enviados a la TRN.
	 * 
	 * @param cuentaBean
	 * @param liquidacionBean
	 * @return parametros de entrada encapsulados en un objeto
	 *         ITRLIQCONSBASICATRNI
	 * @throws NullPointerException
	 */
	private ITRLIQCONSBASICATRNI initPeticion(CuentaBean cuentaBean,
			LiquidacionBean liquidacionBean) throws NullPointerException {
		ITRLIQCONSBASICATRNI itrliqconsbasicatrni = new ITRLIQCONSBASICATRNI();

		super.initialize(itrliqconsbasicatrni);

		itrliqconsbasicatrni.setELEVATORPOSITION(0);
		itrliqconsbasicatrni.setSCROLLABLEOCCURS(SCROLLABLE_OCCURS);

		itrliqconsbasicatrni.getTRLIQCONSBASICAEVTY().getHLCLAVEV()
				.setCODNRBEEN(super.getEntidad());
		itrliqconsbasicatrni.getTRLIQCONSBASICAEVTY().getHLCLAVEV()
				.setNUMSECAC(cuentaBean.getNumeroCuenta());
		itrliqconsbasicatrni
				.getTRLIQCONSBASICAEVTY()
				.getHLCLAVEV()
				.setFECHALIQ(
						integerToDateConverter.convertFrom(liquidacionBean
								.getFechaLiquidacion()));
		itrliqconsbasicatrni.getTRLIQCONSBASICAEVTY().getHLCLAVEV()
				.setNUMSEC(liquidacionBean.getNumsec());

		itrliqconsbasicatrni.getSTDTRNIPARMV().setIDINTERNOTERMTN(
				super.getTerminal());
		itrliqconsbasicatrni.getSTDTRNIPARMV().setCODTX(
				CodTxConstants.COD_TX_TR_LIQ_CONS_BASICA_TRN);

		return itrliqconsbasicatrni;
	}

	/**
	 * Método privado encargado de mapear los datos a partir de la respuesta del
	 * servicio.
	 * 
	 * @param respuesta
	 * @param liquidacionBean
	 * @throws NullPointerException
	 * @throws ControlableException
	 * @throws NoControlableException
	 */
	private void obtenerDetalleLiquidacion(EjecutarResult respuesta,
			LiquidacionBean liquidacionBean) throws NullPointerException,
			ControlableException, NoControlableException {
		if (verificaRespuesta(respuesta)) {
			ResponseBansefi.OTRLIQCONSBASICATRNO.TRLIQCONSBASICAEVTZ detalleLiquidacion = respuesta
					.getResponseBansefi().getOTRLIQCONSBASICATRNO()
					.getTRLIQCONSBASICAEVTZ();
			consultaLiquidacionesWrapper.wrappDetalleLiquidacion(
					detalleLiquidacion, liquidacionBean);
			// Mapear saldos
			if (detalleLiquidacion.getHLSDOLST().getHLSDOEV() != null
					&& detalleLiquidacion.getHLSDOLST().getHLSDOEV().size() != 0) {
				List<LiquidacionSaldoBean> saldos = new ArrayList<>();
				for (HLSDOEV saldoRes : detalleLiquidacion.getHLSDOLST()
						.getHLSDOEV()) {
					if (saldoRes.getCODCTA() != null
							&& !"".equals(saldoRes.getCODCTA().trim())) {
						LiquidacionSaldoBean saldo = new LiquidacionSaldoBean();
						saldo.setCodCta(saldoRes.getCODCTA());
						saldo.setCodSaldo(saldoRes.getCODSDO());
						saldo.setIndSaldo(saldoRes.getINDSALDO());
						saldo.setImporteSaldo(saldoRes.getIMPSDOHL());
						saldos.add(saldo);
					}
				}
				liquidacionBean.setListaSaldos(saldos);
			}
			// Mapear conceptos
			if (detalleLiquidacion.getLIQAPUNTEBASLST().getLIQAPUNTEBASV() != null
					&& detalleLiquidacion.getLIQAPUNTEBASLST()
							.getLIQAPUNTEBASV().size() != 0) {
				List<LiquidacionConceptoBean> conceptos = new ArrayList<>();
				int i = 0;
				for (LIQAPUNTEBASV conceptoRes : detalleLiquidacion
						.getLIQAPUNTEBASLST().getLIQAPUNTEBASV()) {
					if (conceptoRes.getCODCTA() != null
							&& !conceptoRes.getCODCTA().trim().isEmpty()) {
						LiquidacionConceptoBean concepto = new LiquidacionConceptoBean();
						concepto.setCodCuenta(conceptoRes.getCODCTA());
						if (conceptoRes.getIMPFACTURADOV() != null
								&& conceptoRes.getIMPFACTURADOV().size() != 0) {
							concepto.setImporteFacturado(conceptoRes
									.getIMPFACTURADOV().get(0).getIMPSDO());
						}
						if (conceptoRes.getIMPPENDIENTEV() != null
								&& conceptoRes.getIMPPENDIENTEV().size() != 0) {
							concepto.setImportePendiente(conceptoRes
									.getIMPPENDIENTEV().get(0).getIMPSDO());
						}
						concepto.setCodOrigen(conceptoRes.getCODORIGEN());
						concepto.setRowKey(i);
						establecerIndicadores(concepto, conceptoRes);
						if (conceptoRes.getCODORIGEN() != null
								&& !conceptoRes.getCODORIGEN().trim().isEmpty()) {
							concepto.setCodOrigen(conceptoRes.getCODORIGEN()
									.trim());
						}
						if (concepto.getCodCuenta() != null
								&& !concepto.getCodCuenta().trim().isEmpty()
								&& conceptoRes.getCODCTA() != null
								&& !conceptoRes.getCODCTA().trim().isEmpty()
								&& concepto.getCodCuenta().equals(
										conceptoRes.getCODCTA())) {
							if (conceptoRes.getHLCLAVEAFV() != null
									&& !conceptoRes.getHLCLAVEAFV().isEmpty()
									&& conceptoRes.getHLCLAVEAFV().size() > 0
									&& conceptoRes.getHLCLAVEAFV().get(0) != null
									&& conceptoRes.getHLCLAVEAFV().get(0)
											.getNUMSEC() != 0) {
								concepto.setNumSecComision(conceptoRes
										.getHLCLAVEAFV().get(0).getNUMSEC());
								concepto.setNumSubAcComision(conceptoRes
										.getHLCLAVEAFV().get(0).getNUMSUBAC());
							}
						}
						conceptos.add(concepto);
						i++;
					}
				}
				// Mapear atributos correspondientes a intereses de demora.
				List<LiquidacionConceptoDemoraBean> conceptosDemora = new ArrayList<>();
				for (HLTRAMOV datosDemora : detalleLiquidacion
						.getLIQCONSDEMORAV().getHLTRAMOV()) {
					if (datosDemora != null && datosDemora.getIDPDS() != null
							&& !datosDemora.getIDPDS().trim().isEmpty()
							&& datosDemora.getIDPARMCD() != null
							&& !datosDemora.getIDPARMCD().trim().isEmpty()) {
						LiquidacionConceptoDemoraBean liquidacionDemoraBean = new LiquidacionConceptoDemoraBean();
						consultaLiquidacionesWrapper.wrappLiquidacionDemora(
								datosDemora, liquidacionDemoraBean);
						conceptosDemora.add(liquidacionDemoraBean);
					}
				}
				liquidacionBean.setListaConceptosDemora(conceptosDemora);
				liquidacionBean.setListaConceptos(conceptos);
			}
			if(!detalleLiquidacion.getHLINFADICLST().getHLINFADICV().isEmpty() &&
					detalleLiquidacion.getHLINFADICLST().getNUMELEMS() > 0){
				List<LiquidacionInformacionBean> infoAdic = new ArrayList<LiquidacionInformacionBean>();
				for(int i= 0; i < detalleLiquidacion.getHLINFADICLST().getNUMELEMS(); i++){
					LiquidacionInformacionBean liq = new LiquidacionInformacionBean();
					liq.setValor(ConstantesFuncionales.getListaInformacionAdicLiquidacion().get(
							detalleLiquidacion.getHLINFADICLST().getHLINFADICV().get(i).getIND1()));
					liq.setCodInfAdicHL(ConstantesFuncionales.CLAVE_LIQUIDACION_INF_ADIC);
					liq.setDeposito(null);
					liq.setTipoInformacion(ConstantesFuncionales.LISTA_INFO_ADIC_LIQUIDACION);
					infoAdic.add(liq);
				}
				liquidacionBean.setListaInformacion(infoAdic);
			}
		}
	}

	/**
	 * Método privado encargado de establecer los indicadores que hayan sido
	 * devueltos por la transacción.
	 * 
	 * @param resultado
	 * @param elemento
	 * @throws NullPointerException
	 */
	private void establecerIndicadores(
			LiquidacionConceptoBean liquidacionConceptoBean,
			LIQAPUNTEBASV elemento) throws NullPointerException {
		liquidacionConceptoBean.setIndicadores(new ArrayList<String>());
		if (elemento.getIND1() == null) {
			liquidacionConceptoBean.getIndicadores().add("");
		} else {
			liquidacionConceptoBean.getIndicadores().add(
					elemento.getIND1().trim());
		}
		if (elemento.getIND2() == null) {
			liquidacionConceptoBean.getIndicadores().add("");
		} else {
			liquidacionConceptoBean.getIndicadores().add(
					elemento.getIND2().trim());
		}
		if (elemento.getIND3() == null) {
			liquidacionConceptoBean.getIndicadores().add("");
		} else {
			liquidacionConceptoBean.getIndicadores().add(
					elemento.getIND3().trim());
		}
		if (elemento.getIND4() == null) {
			liquidacionConceptoBean.getIndicadores().add("");
		} else {
			liquidacionConceptoBean.getIndicadores().add(
					elemento.getIND4().trim());
		}
		if (elemento.getIND5() == null) {
			liquidacionConceptoBean.getIndicadores().add("");
		} else {
			liquidacionConceptoBean.getIndicadores().add(
					elemento.getIND5().trim());
		}
		if (elemento.getIND6() == null) {
			liquidacionConceptoBean.getIndicadores().add("");
		} else {
			liquidacionConceptoBean.getIndicadores().add(
					elemento.getIND6().trim());
		}
		if (elemento.getIND7() == null) {
			liquidacionConceptoBean.getIndicadores().add("");
		} else {
			liquidacionConceptoBean.getIndicadores().add(
					elemento.getIND7().trim());
		}
		if (elemento.getIND8() == null) {
			liquidacionConceptoBean.getIndicadores().add("");
		} else {
			liquidacionConceptoBean.getIndicadores().add(
					elemento.getIND8().trim());
		}
		if (elemento.getIND9() == null) {
			liquidacionConceptoBean.getIndicadores().add("");
		} else {
			liquidacionConceptoBean.getIndicadores().add(
					elemento.getIND9().trim());
		}
		if (elemento.getIND10() == null) {
			liquidacionConceptoBean.getIndicadores().add("");
		} else {
			liquidacionConceptoBean.getIndicadores().add(
					elemento.getIND10().trim());
		}
	}

	/**
	 * Método que valida que la respuesta contenga un objeto con los datos de la
	 * consulta del detalle de liquidación.
	 * 
	 * @param respuesta
	 * @return indicador booleano
	 */
	private Boolean verificaRespuesta(EjecutarResult respuesta) {
		Boolean noNulo = false;
		if (respuesta != null
				&& respuesta.getResponseBansefi().getOTRLIQCONSBASICATRNO() != null
				&& respuesta.getResponseBansefi().getOTRLIQCONSBASICATRNO()
						.getTRLIQCONSBASICAEVTZ() != null) {
			noNulo = true;
		}
		return noNulo;
	}

	/**
	 * Método que ejecuta la TRN de consulta origen de apuntes a partir de un
	 * ParametrosBusquedaApunteBean.
	 * 
	 * @param parametrosBean
	 * @return parametrosBean con atributos de origen
	 * @throws NullPointerException
	 * @throws ControlableException
	 * @throws NoControlableException
	 */
	public ParametrosBusquedaApunteBean ejecutarTRN(
			ParametrosBusquedaApunteBean parametrosBean)
			throws NullPointerException, ControlableException,
			NoControlableException {
		ITRLIQCONSBASICATRNI itrliqconsbasicatrni = initPeticion(parametrosBean);

		EjecutarResult respuesta = (EjecutarResult) servicioWebUtils
				.ejecutarWS(ConsultaBasicaLiquidacionServicio.class,
						itrliqconsbasicatrni);

		super.verificaRespuesta(respuesta);

		HLHCOLIQE elemento = respuesta.getResponseBansefi()
				.getOTRLIQCONSBASICATRNO().getTRLIQCONSBASICAEVTZ()
				.getHLHCOLIQE();

		return getLiquidacion(parametrosBean, elemento);
	}

	/**
	 * Método privado que prepara los datos que serán enviados a la TRN.
	 * 
	 * @param parametrosBean
	 * @return parametros de entrada encapsulados en un objeto
	 *         ITRLIQCONSBASICATRNI
	 * @throws NullPointerException
	 */
	private ITRLIQCONSBASICATRNI initPeticion(
			ParametrosBusquedaApunteBean parametrosBean)
			throws NullPointerException {
		ITRLIQCONSBASICATRNI itrliqconsbasicatrni = new ITRLIQCONSBASICATRNI();

		super.initialize(itrliqconsbasicatrni);

		itrliqconsbasicatrni.setELEVATORPOSITION(0);
		itrliqconsbasicatrni.setSCROLLABLEOCCURS(SCROLLABLE_OCCURS);

		itrliqconsbasicatrni.getTRLIQCONSBASICAEVTY().getHLCLAVEV()
				.setCODNRBEEN(super.getEntidad());
		itrliqconsbasicatrni.getTRLIQCONSBASICAEVTY().getHLCLAVEV()
				.setNUMSECAC(parametrosBean.getCuentaBean().getNumeroCuenta());

		if (parametrosBean.getApunteSeleccionado().getFechaValor() != null) {
			itrliqconsbasicatrni
					.getTRLIQCONSBASICAEVTY()
					.getHLCLAVEV()
					.setFECHALIQ(
							integerToDateConverter.convertFrom(parametrosBean
									.getApunteSeleccionado().getFechaValor()));
		}

		itrliqconsbasicatrni.getTRLIQCONSBASICAEVTY().getHLCLAVEV()
				.setNUMSEC(parametrosBean.getApunteSeleccionado().getNumSec());

		itrliqconsbasicatrni.getSTDTRNIPARMV().setIDINTERNOTERMTN(
				super.getTerminal());
		itrliqconsbasicatrni.getSTDTRNIPARMV().setCODTX(
				CodTxConstants.COD_TX_TR_LIQ_CONS_BASICA_TRN);

		return itrliqconsbasicatrni;
	}

	/**
	 * Método privado que establece los atributos de origen del apunte.
	 * 
	 * @param parametrosBean
	 * @param elemento
	 * @return parametrosBean con atributos de origen
	 * @throws NullPointerException
	 */
	private ParametrosBusquedaApunteBean getLiquidacion(
			ParametrosBusquedaApunteBean parametrosBean, HLHCOLIQE elemento)
			throws NullPointerException {
		if (elemento != null && elemento.getNUMSECAC() != 0) {
			LiquidacionBean liquidacionBean = consultaApunteWrapper
					.consultaOrigenApunteWrapper(elemento);
			parametrosBean.getApunteSeleccionado().setLiquidacionBean(
					liquidacionBean);
		}
		return parametrosBean;
	}

}