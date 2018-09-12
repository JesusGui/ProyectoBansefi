package mx.babel.bansefi.banksystem.cuentas.backends;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.LiquidacionBean;
import mx.babel.bansefi.banksystem.base.beans.domain.CuentaBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.constants.ConstantesFuncionales;
import mx.babel.bansefi.banksystem.base.utils.IntegerToDateConverter;
import mx.babel.bansefi.banksystem.base.utils.StringToDateConverter;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultaliquidaciones.ConsultaLiquidacionesMasivaSericio;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultaliquidaciones.Ejecutar.ITRLIQCONSMASIVAYZTR;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultaliquidaciones.EjecutarResult;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultaliquidaciones.ResponseBansefi.OTRLIQCONSMASIVAYZTR.TRLIQCONSMASIVAYZEVT.HLHCOLIQE;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultaliquidaciones.ResponseBansefi.OTRLIQCONSMASIVAYZTR.TRLIQCONSMASIVAYZEVT.HLLIQREGULARIZADOV;
import mx.babel.bansefi.banksystem.cuentas.wrappers.ConsultaLiquidacionesWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Backend para la TRN de consulta másiva de liquidaciones
 * (TR_LIQ_CONS_MASIVA_YZ_TRN).
 */
@Component
public class ConsultaMasivaLiquidacionesBackend extends BackEndBean {

	private static final long serialVersionUID = 5236347086249904475L;

	private static final String COD_CSB_OF = "001";
	private static final String HL_OPCION_V = "S";

	private IntegerToDateConverter integerToDateConverter;
	private StringToDateConverter stringToDateConverter;

	@Autowired
	ServicioWebUtils servicioWebUtils;

	@Autowired
	ConsultaLiquidacionesWrapper consultaLiquidacionesWrapper;

	/**
	 * Constructor.
	 */
	public ConsultaMasivaLiquidacionesBackend() {
		super();
		this.integerToDateConverter = new IntegerToDateConverter();
		this.stringToDateConverter = new StringToDateConverter();
	}

	/**
	 * Método que ejecuta la transacción de consulta masiva de liquidaciones.
	 * 
	 * @param cuentaBean
	 * @param liquidacionBusqueda
	 * @return lista de objetos tipo LiquidacionBean
	 * @throws NullPointerException
	 * @throws ControlableException
	 * @throws NoControlableException
	 */
	public List<LiquidacionBean> ejecutarTRN(CuentaBean cuentaBean,
			LiquidacionBean liquidacionBusqueda) throws NullPointerException,
			ControlableException, NoControlableException {
		List<LiquidacionBean> resultado = new ArrayList<>();

		ITRLIQCONSMASIVAYZTR contexto = initPeticion(cuentaBean,
				liquidacionBusqueda);

		EjecutarResult respuesta = (EjecutarResult) servicioWebUtils
				.ejecutarWS(ConsultaLiquidacionesMasivaSericio.class, contexto);

		super.verificaRespuesta(respuesta);

		resultado = getLiquidaciones(respuesta);

		return resultado;
	}

	/**
	 * Método privado que prepara los datos que serán enviados a la TRN.
	 * 
	 * @param cuentaBean
	 * @param liquidacionBean
	 * @return parametros de entrada encapsulados en un objeto
	 *         ITRLIQCONSMASIVAYZTR
	 */
	private ITRLIQCONSMASIVAYZTR initPeticion(CuentaBean cuentaBean,
			LiquidacionBean liquidacionBean) {
		ITRLIQCONSMASIVAYZTR itrliqconsmasivayztr = new ITRLIQCONSMASIVAYZTR();

		super.initialize(itrliqconsmasivayztr);

		itrliqconsmasivayztr.setELEVATORPOSITION(0);
		itrliqconsmasivayztr.setSCROLLABLEOCCURS(SCROLLABLE_OCCURS);

		itrliqconsmasivayztr.getTRLIQCONSMASIVAYZEVT().getACACE()
				.setCODNRBEEN(super.getEntidad());
		itrliqconsmasivayztr.getTRLIQCONSMASIVAYZEVT().getACACE()
				.setNUMSECAC(cuentaBean.getNumeroCuenta());
		itrliqconsmasivayztr.getTRLIQCONSMASIVAYZEVT().getACACE()
				.setCODDIGCRUO(cuentaBean.getDigitoVerificador());
		itrliqconsmasivayztr.getTRLIQCONSMASIVAYZEVT().getACACE()
				.setCODINTERNOUO(cuentaBean.getCentro());
		itrliqconsmasivayztr.getTRLIQCONSMASIVAYZEVT().getACACE()
				.setCODCSBOF(COD_CSB_OF);
		itrliqconsmasivayztr.getTRLIQCONSMASIVAYZEVT().getACACE()
				.setCODPLZBANCARIA(cuentaBean.getPlazaBancaria());

		itrliqconsmasivayztr.getTRLIQCONSMASIVAYZEVT().getHLHCOLIQE()
				.setCODNRBEEN(super.getEntidad());
		itrliqconsmasivayztr.getTRLIQCONSMASIVAYZEVT().getHLHCOLIQE()
				.setNUMSECAC(cuentaBean.getNumeroCuenta());
		itrliqconsmasivayztr.getTRLIQCONSMASIVAYZEVT().getHLHCOLIQE()
				.setNUMSEC(liquidacionBean.getPagina());

		// Verificamos sí la fecha desde se encuentra informada.
		if (liquidacionBean.getFechaInicioFiltro() == null) {
			itrliqconsmasivayztr
					.getTRLIQCONSMASIVAYZEVT()
					.getHLHCOLIQE()
					.setFECHADESDE(
							integerToDateConverter.convertFrom(stringToDateConverter
									.convertTo(ConstantesFuncionales.MIN_FECHA_INICIO)));
		} else {
			itrliqconsmasivayztr
					.getTRLIQCONSMASIVAYZEVT()
					.getHLHCOLIQE()
					.setFECHADESDE(
							integerToDateConverter.convertFrom(liquidacionBean
									.getFechaInicioFiltro()));
		}

		// Verificamos la fecha hasta y sí se está paginando.
		if (liquidacionBean.getPagina() == 0) {
			if (liquidacionBean.getFechaFinFiltro() == null) {
				itrliqconsmasivayztr
						.getTRLIQCONSMASIVAYZEVT()
						.getHLHCOLIQE()
						.setFECHAHASTA(
								integerToDateConverter.convertFrom(new Date()));
			} else {
				itrliqconsmasivayztr
						.getTRLIQCONSMASIVAYZEVT()
						.getHLHCOLIQE()
						.setFECHAHASTA(
								integerToDateConverter
										.convertFrom(liquidacionBean
												.getFechaFinFiltro()));
			}
		} else {
			if (liquidacionBean.getListaResultadosLiquidaciones().size() != 0) {
				itrliqconsmasivayztr
						.getTRLIQCONSMASIVAYZEVT()
						.getHLHCOLIQE()
						.setFECHAHASTA(
								((HLHCOLIQE) (liquidacionBean
										.getListaResultadosLiquidaciones()
										.get(liquidacionBean
												.getListaResultadosLiquidaciones()
												.size() - 1))
										.getUltimoDatoConsultaAnterior())
										.getFECHAHASTA());
			}
		}

		itrliqconsmasivayztr.getTRLIQCONSMASIVAYZEVT().getHLHCOLIQE()
				.setIMPPENDLIQ(BigDecimal.ZERO);
		itrliqconsmasivayztr.getTRLIQCONSMASIVAYZEVT().getHLHCOLIQE()
				.setIMPTOTALLIQ(BigDecimal.ZERO);
		itrliqconsmasivayztr.getTRLIQCONSMASIVAYZEVT().getHLHCOLIQE()
				.setCODOPERLIQ(liquidacionBean.getTipoLiqFiltro());

		// Verificamos nuevamente la fecha desde.
		if (liquidacionBean.getFechaInicioFiltro() == null) {
			itrliqconsmasivayztr
					.getTRLIQCONSMASIVAYZEVT()
					.getSTDFECHALIQINIV()
					.setSTDFECHA(
							integerToDateConverter.convertFrom(stringToDateConverter
									.convertTo(ConstantesFuncionales.MIN_FECHA_INICIO)));
		} else {
			itrliqconsmasivayztr
					.getTRLIQCONSMASIVAYZEVT()
					.getSTDFECHALIQINIV()
					.setSTDFECHA(
							integerToDateConverter.convertFrom(liquidacionBean
									.getFechaInicioFiltro()));
		}

		// Verificamos nuevamente la fecha hasta y la paginación.
		if (liquidacionBean.getPagina() == 0) {
			if (liquidacionBean.getFechaFinFiltro() == null) {
				itrliqconsmasivayztr
						.getTRLIQCONSMASIVAYZEVT()
						.getSTDFECHALIQFINV()
						.setSTDFECHA(
								integerToDateConverter.convertFrom(new Date()));
			} else {
				itrliqconsmasivayztr
						.getTRLIQCONSMASIVAYZEVT()
						.getSTDFECHALIQFINV()
						.setSTDFECHA(
								integerToDateConverter
										.convertFrom(liquidacionBean
												.getFechaFinFiltro()));
			}
		} else {
			if (liquidacionBean.getListaResultadosLiquidaciones().size() != 0) {
				itrliqconsmasivayztr
						.getTRLIQCONSMASIVAYZEVT()
						.getSTDFECHALIQFINV()
						.setSTDFECHA(
								((HLHCOLIQE) (liquidacionBean
										.getListaResultadosLiquidaciones()
										.get(liquidacionBean
												.getListaResultadosLiquidaciones()
												.size() - 1))
										.getUltimoDatoConsultaAnterior())
										.getFECHAHASTA());
			}
		}

		// Verificamos el valor del filtro para mostrar u ocultar anulados.
		if (liquidacionBean.isIncluirAnuladosFiltro()) {
			// Se envía "S" o cualquier caracter para mostrar todo.
			itrliqconsmasivayztr.getTRLIQCONSMASIVAYZEVT().getHLHCOLIQANULV()
					.setOPCION("S");
		} else {
			// Se envía "vacío" cuando se quieren omitir registros anulados.
			itrliqconsmasivayztr.getTRLIQCONSMASIVAYZEVT().getHLHCOLIQANULV()
					.setOPCION("");
		}

		itrliqconsmasivayztr.getTRLIQCONSMASIVAYZEVT().getHLINICIOREPOSV()
				.setSTDSMALLINT(0);
		itrliqconsmasivayztr.getTRLIQCONSMASIVAYZEVT().getHLNUMTOTALV()
				.setSTDSMALLINT(SCROLLABLE_OCCURS);

		itrliqconsmasivayztr.getTRLIQCONSMASIVAYZEVT().getHLOPCIONV()
				.setOPCION(HL_OPCION_V);

		itrliqconsmasivayztr.getSTDTRNIPARMV().setIDINTERNOTERMTN(
				super.getTerminal());
		itrliqconsmasivayztr.getSTDTRNIPARMV().setCODTX(
				CodTxConstants.COD_TX_TR_LIQ_CONS_MASIVA_YZ_TRN);

		return itrliqconsmasivayztr;
	}

	/**
	 * Método privado encargado de obtener una lista de liquidaciones a partir
	 * de la respuesta del servicio.
	 * 
	 * @param respuesta
	 * @return lista de objetos tipo LiquidacionBean
	 * @throws NullPointerException
	 * @throws ControlableException
	 * @throws NoControlableException
	 */
	private List<LiquidacionBean> getLiquidaciones(EjecutarResult respuesta)
			throws NullPointerException, ControlableException,
			NoControlableException {
		List<LiquidacionBean> liquidaciones = new ArrayList<LiquidacionBean>();
		if (verificaRespuesta(respuesta)) {
			for (HLHCOLIQE liquidacion : respuesta.getResponseBansefi()
					.getOTRLIQCONSMASIVAYZTR().getTRLIQCONSMASIVAYZEVT()
					.getHLHCOLIQE()) {
				if (liquidacion.getCODNRBEEN() != null
						&& !"".equals(liquidacion.getCODNRBEEN().trim())) {
					LiquidacionBean liquidacionResult = consultaLiquidacionesWrapper
							.wrappConsultaLiquidacion(liquidacion);
					liquidacionResult.setIdLiquidacion(liquidacionResult
							.toString() + Calendar.getInstance());

					if (respuesta.getResponseBansefi()
							.getOTRLIQCONSMASIVAYZTR().getMOREDATAIN() != 0) {
						liquidacionResult.setMasDatos(true);
						liquidacionResult
								.setUltimoDatoConsultaAnterior(liquidacion);
						liquidacionResult.setPagina(liquidacion.getNUMSEC());
					}
					liquidacionResult.setNumeroDatos(respuesta
							.getResponseBansefi().getOTRLIQCONSMASIVAYZTR()
							.getNUMBEROFRECORDS());
					liquidaciones.add(liquidacionResult);
				}
			}
			for (int i = 0; i < liquidaciones.size(); i++) {
				HLLIQREGULARIZADOV item = respuesta.getResponseBansefi()
						.getOTRLIQCONSMASIVAYZTR().getTRLIQCONSMASIVAYZEVT()
						.getHLLIQREGULARIZADOV().get(i);
				if (liquidaciones.get(i) != null && item.getOPCION() != null
						&& !item.getOPCION().trim().isEmpty()
						&& "S".equalsIgnoreCase(item.getOPCION().trim())) {
					liquidaciones.get(i).setAj("SI");
				}
			}
		}
		return liquidaciones;
	}

	/**
	 * Método que devuelve un indicador booleano para determinar sí la respuesta
	 * contiene datos.
	 * 
	 * @param respuesta
	 * @return indicador booleano
	 */
	private Boolean verificaRespuesta(EjecutarResult respuesta) {
		Boolean noNulo = false;
		if (respuesta != null
				&& respuesta.getResponseBansefi().getOTRLIQCONSMASIVAYZTR() != null
				&& respuesta.getResponseBansefi().getOTRLIQCONSMASIVAYZTR()
						.getTRLIQCONSMASIVAYZEVT() != null
				&& respuesta.getResponseBansefi().getOTRLIQCONSMASIVAYZTR()
						.getTRLIQCONSMASIVAYZEVT().getHLHCOLIQE() != null
				&& !respuesta.getResponseBansefi().getOTRLIQCONSMASIVAYZTR()
						.getTRLIQCONSMASIVAYZEVT().getHLHCOLIQE().isEmpty()) {
			noNulo = true;
		}
		return noNulo;
	}

}