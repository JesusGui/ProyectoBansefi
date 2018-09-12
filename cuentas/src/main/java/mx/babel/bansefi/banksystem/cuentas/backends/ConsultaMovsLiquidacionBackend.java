package mx.babel.bansefi.banksystem.cuentas.backends;

import java.util.ArrayList;
import java.util.List;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.LiquidacionBean;
import mx.babel.bansefi.banksystem.base.beans.domain.CuentaBean;
import mx.babel.bansefi.banksystem.base.beans.movimientos.MovimientoBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.constants.ConstantesFuncionales;
import mx.babel.bansefi.banksystem.base.utils.IntegerToDateConverter;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultamovsliquidacion.ConsultaMovimientosLiquidacionServicio;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultamovsliquidacion.Ejecutar.ITRCONSHLRLAFTRNI;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultamovsliquidacion.EjecutarResult;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultamovsliquidacion.ResponseBansefi.OTRCONSHLRLAFTRNO.TRCONSHLRLAFEVTZ.HLHCOLIQRLAFELST;
import mx.babel.bansefi.banksystem.cuentas.wrappers.ConsultaLiquidacionesWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Backend para la TRN de consulta de movimientos de una liquidación
 * (TR_CONS_HL_RL_AF_TRN).
 */
@Component
public class ConsultaMovsLiquidacionBackend extends BackEndBean {

	private static final long serialVersionUID = -6982193526911193391L;

	// Constantes para lógica EVENT_CD.
	private static final String COD_LINEA_03 = "03";
	private static final String ID_GPO_PROD_51 = "51";

	private IntegerToDateConverter converter;

	@Autowired
	ServicioWebUtils servicioWebUtils;

	@Autowired
	ConsultaLiquidacionesWrapper consultaLiquidacionesWrapper;

	/**
	 * Constructor.
	 */
	public ConsultaMovsLiquidacionBackend() {
		super();
		this.converter = new IntegerToDateConverter();
	}

	/**
	 * Método que ejecuta la TRN de consulta de movimientos de una liquidación.
	 * 
	 * @param cuentaBean
	 * @param liquidacionBean
	 * @return lista de movimientos
	 * @throws NullPointerException
	 * @throws ControlableException
	 * @throws NoControlableException
	 */
	public List<MovimientoBean> ejecutarTRN(CuentaBean cuentaBean,
			LiquidacionBean liquidacionBean) throws NullPointerException,
			ControlableException, NoControlableException {
		ITRCONSHLRLAFTRNI contexto = initPeticion(cuentaBean, liquidacionBean);

		EjecutarResult respuesta = (EjecutarResult) servicioWebUtils
				.ejecutarWS(ConsultaMovimientosLiquidacionServicio.class,
						contexto);

		super.verificaRespuesta(respuesta);

		return obtenerMovimientos(respuesta);
	}

	/**
	 * Método privado que prepara los datos que serán enviados a la TRN.
	 * 
	 * @param cuentaBean
	 * @param liquidacionBean
	 * @return parametros de entrada encapsulados en un objeto ITRCONSHLRLAFTRNI
	 * @throws NullPointerException
	 */
	private ITRCONSHLRLAFTRNI initPeticion(CuentaBean cuentaBean,
			LiquidacionBean liquidacionBean) throws NullPointerException {
		ITRCONSHLRLAFTRNI itrconshlrlaftrni = new ITRCONSHLRLAFTRNI();

		super.initialize(itrconshlrlaftrni);

		itrconshlrlaftrni.setELEVATORPOSITION(0);
		itrconshlrlaftrni.setSCROLLABLEOCCURS(SCROLLABLE_OCCURS);

		// Verificamos sí se deben mostrar todos los movimientos o no.
		if (liquidacionBean.isIncluirMovsAnuladosFiltro()) {
			itrconshlrlaftrni.getTRCONSHLRLAFEVTY().getHLHCOLIQANULV()
					.setOPCION(ConstantesFuncionales.IND_SI);
		} else {
			itrconshlrlaftrni.getTRCONSHLRLAFEVTY().getHLHCOLIQANULV()
					.setOPCION(ConstantesFuncionales.IND_NO);
		}

		itrconshlrlaftrni.getTRCONSHLRLAFEVTY().getHLHCOLIQRLAFE()
				.setCODNRBEEN(super.getEntidad());
		itrconshlrlaftrni.getTRCONSHLRLAFEVTY().getHLHCOLIQRLAFE()
				.setNUMSECAC(cuentaBean.getNumeroCuenta());
		itrconshlrlaftrni
				.getTRCONSHLRLAFEVTY()
				.getHLHCOLIQRLAFE()
				.setFECHALIQ(
						converter.convertFrom(liquidacionBean
								.getFechaLiquidacion()));
		itrconshlrlaftrni.getTRCONSHLRLAFEVTY().getHLHCOLIQRLAFE()
				.setNUMSEC(liquidacionBean.getNumsec());

		// Sí tarifa es PLAZO, entra a este bloque.
		if (cuentaBean.getCodLinea().equals(COD_LINEA_03)
				&& cuentaBean.getIdGrupoProducto().equals(ID_GPO_PROD_51)) {
			itrconshlrlaftrni.getTRCONSHLRLAFEVTY().getHLTIPOREPOSV()
					.setEVENTCD(2);
		} else {
			itrconshlrlaftrni.getTRCONSHLRLAFEVTY().getHLTIPOREPOSV()
					.setEVENTCD(0);
		}

		itrconshlrlaftrni.getSTDTRNIPARMV().setIDINTERNOTERMTN(
				super.getTerminal());
		itrconshlrlaftrni.getSTDTRNIPARMV().setCODTX(
				CodTxConstants.COD_TX_TR_CONS_HL_RL_AF_TRN);

		return itrconshlrlaftrni;
	}

	/**
	 * Método privado que devuelve una lista de movimientos a partir de la
	 * respuesta del servicio.
	 * 
	 * @param respuesta
	 * @return lista de movimientos
	 * @throws NullPointerException
	 */
	private List<MovimientoBean> obtenerMovimientos(EjecutarResult respuesta)
			throws NullPointerException {
		List<MovimientoBean> movimientos = new ArrayList<MovimientoBean>();
		if (verificaRespuesta(respuesta)) {
			for (HLHCOLIQRLAFELST movimiento : respuesta.getResponseBansefi()
					.getOTRCONSHLRLAFTRNO().getTRCONSHLRLAFEVTZ()
					.getHLHCOLIQRLAFELST()) {
				if (!"".equals(movimiento.getCODHLRLAF().trim())) {
					MovimientoBean movimientoResult = new MovimientoBean();
					movimientoResult = consultaLiquidacionesWrapper
							.wrappMovimientoLiq(movimiento);
					// Obtenemos el signo del apunte.
					if ("D".equals(movimientoResult.getSigno())) {
						movimientoResult.setSigno("DEBE");
					}
					if ("H".equals(movimientoResult.getSigno())) {
						movimientoResult.setSigno("HABER");
					}
					if (respuesta.getResponseBansefi().getOTRCONSHLRLAFTRNO()
							.getMOREDATAIN() != 0) {
						movimientoResult.setMasDatos(true);
						movimientoResult
								.setUltimoDatoConsultaAnterior(movimiento
										.getNUMSEC());
					}
					obtenerListaIndicadores(movimientoResult, movimiento);
					movimientoResult.setNumeroDatos(respuesta
							.getResponseBansefi().getOTRCONSHLRLAFTRNO()
							.getNUMBEROFRECORDS());
					movimientos.add(movimientoResult);
				}
			}
		}
		return movimientos;
	}

	/**
	 * Método privado encargado de obtener todos los indicadores que hayan sido
	 * devueltos por la transacción.
	 * 
	 * @param resultado
	 * @param elemento
	 * @throws NullPointerException
	 */
	private void obtenerListaIndicadores(MovimientoBean resultado,
			HLHCOLIQRLAFELST elemento) throws NullPointerException {
		resultado.setIndicadores(new ArrayList<String>());
		if (elemento.getIND1() == null) {
			resultado.getIndicadores().add("");
		} else {
			resultado.getIndicadores().add(elemento.getIND1().trim());
		}
		if (elemento.getIND2() == null) {
			resultado.getIndicadores().add("");
		} else {
			resultado.getIndicadores().add(elemento.getIND2().trim());
		}
		if (elemento.getIND3() == null) {
			resultado.getIndicadores().add("");
		} else {
			resultado.getIndicadores().add(elemento.getIND3().trim());
		}
		if (elemento.getIND4() == null) {
			resultado.getIndicadores().add("");
		} else {
			resultado.getIndicadores().add(elemento.getIND4().trim());
		}
		if (elemento.getIND5() == null) {
			resultado.getIndicadores().add("");
		} else {
			resultado.getIndicadores().add(elemento.getIND5().trim());
		}
		if (elemento.getIND6() == null) {
			resultado.getIndicadores().add("");
		} else {
			resultado.getIndicadores().add(elemento.getIND6().trim());
		}
		if (elemento.getIND7() == null) {
			resultado.getIndicadores().add("");
		} else {
			resultado.getIndicadores().add(elemento.getIND7().trim());
		}
		if (elemento.getIND8() == null) {
			resultado.getIndicadores().add("");
		} else {
			resultado.getIndicadores().add(elemento.getIND8().trim());
		}
		if (elemento.getIND9() == null) {
			resultado.getIndicadores().add("");
		} else {
			resultado.getIndicadores().add(elemento.getIND9().trim());
		}
		if (elemento.getIND10() == null) {
			resultado.getIndicadores().add("");
		} else {
			resultado.getIndicadores().add(elemento.getIND10().trim());
		}
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
				&& respuesta.getResponseBansefi().getOTRCONSHLRLAFTRNO() != null
				&& respuesta.getResponseBansefi().getOTRCONSHLRLAFTRNO()
						.getTRCONSHLRLAFEVTZ() != null
				&& respuesta.getResponseBansefi().getOTRCONSHLRLAFTRNO()
						.getTRCONSHLRLAFEVTZ().getHLHCOLIQRLAFELST() != null
				&& !respuesta.getResponseBansefi().getOTRCONSHLRLAFTRNO()
						.getTRCONSHLRLAFEVTZ().getHLHCOLIQRLAFELST().isEmpty()) {
			noNulo = true;
		}
		return noNulo;
	}

}