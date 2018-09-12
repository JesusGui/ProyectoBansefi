package mx.babel.bansefi.banksystem.cuentas.backends;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.LiquidacionBean;
import mx.babel.bansefi.banksystem.base.beans.LiquidacionConceptoBean;
import mx.babel.bansefi.banksystem.base.beans.domain.CuentaBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.utils.IntegerToDateConverter;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultatramoliquidacion.ConsultaTramoLiquidacionServicio;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultatramoliquidacion.Ejecutar.ITRCONSHLTRAMOTRNI;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultatramoliquidacion.EjecutarResult;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultatramoliquidacion.ResponseBansefi.OTRCONSHLTRAMOTRNO.TRCONSHLTRAMOEVTZ.HLTRAMOE;
import mx.babel.bansefi.banksystem.cuentas.wrappers.ConsultaApunteWrapper;
import mx.babel.bansefi.banksystem.cuentas.wrappers.ConsultaLiquidacionesWrapper;

import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Backend para la TRN de consulta de tramos de una liquidación
 * (TR_CONS_HL_TRAMO_TRN).
 */
@Component
public class ConsultaTramoLiquidacionBackend extends BackEndBean {

	private static final long serialVersionUID = 6217700410584130580L;

	private static final String TIPO_TRAMO_L = "L";
	private static final String TIPO_TRAMO_R = "R";

	private static final String LIQUIDACION = "LIQUIDACION";
	private static final String COMPENSACION = "COMPENSACION";

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
	public ConsultaTramoLiquidacionBackend() {
		super();
		this.integerToDateConverter = new IntegerToDateConverter();
	}

	/**
	 * Método que ejecuta la TRN de consulta de tramo de una liquidación.
	 * 
	 * @param cuentaBean
	 * @param liquidacionBean
	 * @return lista de conceptos
	 * @throws NullPointerException
	 * @throws ControlableException
	 * @throws NoControlableException
	 */
	public List<LiquidacionConceptoBean> ejecutarTRN(CuentaBean cuentaBean,
			LiquidacionBean liquidacionBean) throws NullPointerException,
			ControlableException, NoControlableException {
		ITRCONSHLTRAMOTRNI contexto = initPeticion(cuentaBean, liquidacionBean);

		EjecutarResult respuesta = (EjecutarResult) servicioWebUtils
				.ejecutarWS(ConsultaTramoLiquidacionServicio.class, contexto);

		super.verificaRespuesta(respuesta);

		return obtenerTramoLiquidacion(respuesta, liquidacionBean);
	}

	/**
	 * Método privado que prepara los datos que serán enviados a la TRN.
	 * 
	 * @param cuentaBean
	 * @param liquidacionBean
	 * @return parametros de entrada encapsulados en un objeto
	 *         ITRCONSHLTRAMOTRNI
	 * @throws NullPointerException
	 */
	private ITRCONSHLTRAMOTRNI initPeticion(CuentaBean cuentaBean,
			LiquidacionBean liquidacionBean) throws NullPointerException {
		ITRCONSHLTRAMOTRNI itrconshltramotrni = new ITRCONSHLTRAMOTRNI();

		super.initialize(itrconshltramotrni);

		itrconshltramotrni.setELEVATORPOSITION(0);
		itrconshltramotrni.setSCROLLABLEOCCURS(SCROLLABLE_OCCURS);

		itrconshltramotrni.getTRCONSHLTRAMOEVTY().getHLTRAMOP()
				.setCODNRBEEN(super.getEntidad());
		itrconshltramotrni.getTRCONSHLTRAMOEVTY().getHLTRAMOP()
				.setNUMSECAC(cuentaBean.getNumeroCuenta());
		itrconshltramotrni.getTRCONSHLTRAMOEVTY().getHLTRAMOP()
				.setNUMSEC(liquidacionBean.getNumsec());
		itrconshltramotrni
				.getTRCONSHLTRAMOEVTY()
				.getHLTRAMOP()
				.setFECHALIQ(
						integerToDateConverter.convertFrom(liquidacionBean
								.getFechaLiquidacion()));

		itrconshltramotrni.getSTDTRNIPARMV().setIDINTERNOTERMTN(
				super.getTerminal());
		itrconshltramotrni.getSTDTRNIPARMV().setCODTX(
				CodTxConstants.COD_TX_TR_CONS_HL_TRAMO_TRN);

		return itrconshltramotrni;
	}

	/**
	 * Método privado encargado de obtener una lista con los conceptos de tramo
	 * a partir de la respuesta del servicio.
	 * 
	 * @param respuesta
	 * @param liquidacionSeleccionada
	 * @return lista de conceptos de tramo de la liquidación
	 * @throws NullPointerException
	 */
	private List<LiquidacionConceptoBean> obtenerTramoLiquidacion(
			EjecutarResult respuesta, LiquidacionBean liquidacionBean)
			throws NullPointerException {
		List<LiquidacionConceptoBean> result = new ArrayList<>();
		if (verificaRespuesta(respuesta)) {
			for (HLTRAMOE tramoRes : respuesta.getResponseBansefi()
					.getOTRCONSHLTRAMOTRNO().getTRCONSHLTRAMOEVTZ()
					.getHLTRAMOE()) {
				if (tramoRes.getCODNRBEEN() != null
						&& !tramoRes.getCODNRBEEN().trim().isEmpty()
						&& tramoRes.getIMPTRAMO() != null
						&& tramoRes.getIMPTRAMO() != BigDecimal.ZERO) {
					LiquidacionConceptoBean concepto = new LiquidacionConceptoBean();
					concepto = consultaLiquidacionesWrapper
							.wrappTramoLiq(tramoRes);
					concepto.setDias(calculaNumDiasTramo(
							concepto.getFechaDesdeTramo(),
							concepto.getFechaHastaTramo()));
					if (!StringUtils.isBlank(concepto.getTipoTramo())) {
						if (concepto.getTipoTramo().equals(TIPO_TRAMO_L)) {
							concepto.setTipoTramo(LIQUIDACION);
						} else if (concepto.getTipoTramo().equals(TIPO_TRAMO_R)) {
							concepto.setTipoTramo(COMPENSACION);
						}
					}
					result.add(concepto);
				}
			}
		}
		return result;
	}

	/**
	 * Método privado encargado de validar que la respuesta contenga un objeto
	 * con datos.
	 * 
	 * @param respuesta
	 * @return indicador booleano
	 */
	private Boolean verificaRespuesta(EjecutarResult respuesta) {
		Boolean noNulo = false;
		if (respuesta != null
				&& respuesta.getResponseBansefi().getOTRCONSHLTRAMOTRNO() != null
				&& respuesta.getResponseBansefi().getOTRCONSHLTRAMOTRNO()
						.getTRCONSHLTRAMOEVTZ() != null) {
			noNulo = true;
		}
		return noNulo;
	}

	/**
	 * Método privado encargado de calcular el número de días de un tramo de
	 * liquidación.
	 * 
	 * @param inicio
	 * @param fin
	 * @return número de días de un tramo de liquidación
	 */
	private int calculaNumDiasTramo(Date inicio, Date fin) {
		DateTime dt1 = new DateTime(inicio);
		DateTime dt2 = new DateTime(fin);
		return Math.abs(Days.daysBetween(dt1, dt2).getDays());
	}

}