package mx.babel.bansefi.banksystem.cuentas.backends;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.domain.CuentaBean;
import mx.babel.bansefi.banksystem.base.beans.movimientos.MovimientoBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.constants.ConstantesFuncionales;
import mx.babel.bansefi.banksystem.base.utils.IntegerToDateConverter;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultadetalleapunte.ConsultaDetalleApunteServicio;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultadetalleapunte.Ejecutar.ITRAFCONSAPUNTETRNI;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultadetalleapunte.EjecutarResult;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultadetalleapunte.ResponseBansefi.OTRAFCONSAPUNTETRNO.TRAFCONSAPUNTEEVTZ;
import mx.babel.bansefi.banksystem.cuentas.wrappers.ConsultaApunteWrapper;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Backend para la TRN de consulta detallada de apuntes (TR_AF_CONS_APUNTE_TRN).
 * 
 * @author omar.marquez
 * 
 */
@Component
public class ConsultaDetalleApunteBackEnd extends BackEndBean {

	private static final long serialVersionUID = 6758116173369385413L;

	private IntegerToDateConverter integerToDateConverter;

	@Autowired
	ServicioWebUtils servicioWebUtils;

	@Autowired
	ConsultaApunteWrapper consultaApunteWrapper;
	
	@Autowired
	ConsultaGeneralApunteBackEnd consultaGeneralApunteBackEnd;

	/**
	 * Constructor.
	 */
	public ConsultaDetalleApunteBackEnd() {
		super();
		this.integerToDateConverter = new IntegerToDateConverter();
	}

	/**
	 * Método que ejecuta la TRN de consulta detallada de apuntes a partir de un
	 * MovimientoBean.
	 * 
	 * @param movimientoBean
	 * @throws NullPointerException
	 * @throws ControlableException
	 * @throws NoControlableException
	 */
	public void ejecutarTRN(MovimientoBean movimientoBean)
			throws NullPointerException, ControlableException,
			NoControlableException {
		ITRAFCONSAPUNTETRNI itrafconsapuntetrni = initPeticion(movimientoBean);

		EjecutarResult resultado = (EjecutarResult) servicioWebUtils
				.ejecutarWS(ConsultaDetalleApunteServicio.class,
						itrafconsapuntetrni);

		super.verificaRespuesta(resultado);

		TRAFCONSAPUNTEEVTZ salida = resultado.getResponseBansefi()
				.getOTRAFCONSAPUNTETRNO().getTRAFCONSAPUNTEEVTZ();

		establecerDetalle(salida, movimientoBean);
	}

	/**
	 * Método privado que prepara los datos que serán enviados a la TRN.
	 * 
	 * @param movimientoBean
	 * @return parametros de entrada encapsulados en un objeto
	 *         ITRAFCONSAPUNTETRNI
	 * @throws IndexOutOfBoundsException
	 * @throws NullPointerException
	 */
	private ITRAFCONSAPUNTETRNI initPeticion(MovimientoBean movimientoBean)
			throws IndexOutOfBoundsException, NullPointerException {
		ITRAFCONSAPUNTETRNI itrafconsapuntetrni = new ITRAFCONSAPUNTETRNI();

		super.initialize(itrafconsapuntetrni);

		itrafconsapuntetrni.setELEVATORPOSITION(0);
		itrafconsapuntetrni.setSCROLLABLEOCCURS(SCROLLABLE_OCCURS);

		itrafconsapuntetrni.getTRAFCONSAPUNTEEVTY().getAFAPNTEE()
				.setCODNRBEEN(super.getEntidad());
		itrafconsapuntetrni.getTRAFCONSAPUNTEEVTY().getAFAPNTEE()
				.setPRPDADCTA(ConstantesFuncionales.PRPDAD_CTA);
		itrafconsapuntetrni.getTRAFCONSAPUNTEEVTY().getAFAPNTEE()
				.setNUMSECAC(movimientoBean.getNumCuenta());
		itrafconsapuntetrni.getTRAFCONSAPUNTEEVTY().getAFAPNTEE()
				.setCODCTA(movimientoBean.getCodigoCuenta());
		itrafconsapuntetrni.getTRAFCONSAPUNTEEVTY().getAFAPNTEE()
				.setCODNUMRCOMONEDA(ConstantesFuncionales.COD_MONEDA);
		itrafconsapuntetrni.getTRAFCONSAPUNTEEVTY().getAFAPNTEE()
				.setNUMSEC(movimientoBean.getNumSec());

		// Obtenemos el signo del apunte.
		if (movimientoBean.getSigno() != null
				&& !movimientoBean.getSigno().trim().isEmpty()) {
			itrafconsapuntetrni.getTRAFCONSAPUNTEEVTY().getAFAPNTEE()
					.setSGN(movimientoBean.getSigno().substring(0, 1));
		}
		// Obtenemos la fecha contable.
		if (movimientoBean.getFechaContable() != null) {
			itrafconsapuntetrni
					.getTRAFCONSAPUNTEEVTY()
					.getAFAPNTEE()
					.setFECHACTBLE(
							integerToDateConverter.convertFrom(movimientoBean
									.getFechaContable()));
		}
		// Obtenemos la fecha valor.
		if (movimientoBean.getFechaValor() != null) {
			itrafconsapuntetrni
					.getTRAFCONSAPUNTEEVTY()
					.getAFAPNTEE()
					.setFECHAVALOR(
							integerToDateConverter.convertFrom(movimientoBean
									.getFechaValor()));
		}
		// Obtenemos los indicadores del apunte.
		itrafconsapuntetrni.getTRAFCONSAPUNTEEVTY().getAFAPNTEE()
				.setIND1(movimientoBean.getIndicador1());
		itrafconsapuntetrni.getTRAFCONSAPUNTEEVTY().getAFAPNTEE()
				.setIND2(movimientoBean.getIndicador2());
		itrafconsapuntetrni.getTRAFCONSAPUNTEEVTY().getAFAPNTEE()
				.setIND3(movimientoBean.getIndicador3());
		itrafconsapuntetrni.getTRAFCONSAPUNTEEVTY().getAFAPNTEE()
				.setIND4(movimientoBean.getIndicador4());
		itrafconsapuntetrni.getTRAFCONSAPUNTEEVTY().getAFAPNTEE()
				.setIND5(movimientoBean.getIndicador5());
		itrafconsapuntetrni.getTRAFCONSAPUNTEEVTY().getAFAPNTEE()
				.setIND6(movimientoBean.getIndicador6());
		itrafconsapuntetrni.getTRAFCONSAPUNTEEVTY().getAFAPNTEE()
				.setIND7(movimientoBean.getIndicador7());
		itrafconsapuntetrni.getTRAFCONSAPUNTEEVTY().getAFAPNTEE()
				.setIND8(movimientoBean.getIndicador8());
		itrafconsapuntetrni.getTRAFCONSAPUNTEEVTY().getAFAPNTEE()
				.setIND9(movimientoBean.getIndicador9());
		itrafconsapuntetrni.getTRAFCONSAPUNTEEVTY().getAFAPNTEE()
				.setIND10(movimientoBean.getIndicador10());
		itrafconsapuntetrni.getTRAFCONSAPUNTEEVTY().getAFAPNTEE()
				.setCODORGNAPNTE(movimientoBean.getCodigoOrigenApunte());
		itrafconsapuntetrni.getTRAFCONSAPUNTEEVTY().getAFAPNTEE()
				.setIDORGNAPNTE(movimientoBean.getIdOrigenApunte());
		itrafconsapuntetrni.getTRAFCONSAPUNTEEVTY().getAFAPNTEE()
				.setCODORIGEN(movimientoBean.getCodigoOrigen());
		itrafconsapuntetrni.getTRAFCONSAPUNTEEVTY().getAFAPNTEE()
				.setCODINTERNOUO(movimientoBean.getCentro());
		itrafconsapuntetrni.getTRAFCONSAPUNTEEVTY().getAFAPNTEE()
				.setCODLINEA(movimientoBean.getCodLinea());
		itrafconsapuntetrni.getTRAFCONSAPUNTEEVTY().getAFAPNTEE()
				.setIDGRPPD(movimientoBean.getIdGrupoProducto());

		itrafconsapuntetrni.getSTDTRNIPARMV().setIDINTERNOTERMTN(
				super.getTerminal());
		itrafconsapuntetrni.getSTDTRNIPARMV().setCODTX(
				CodTxConstants.COD_TX_TR_AF_CONS_APUNTE_TRN);

		return itrafconsapuntetrni;
	}

	/**
	 * Método privado encargado de mapear los atributos de la respuesta en un
	 * MovimientoBean.
	 * 
	 * @param salida
	 * @param movimientoBean
	 */
	private void establecerDetalle(TRAFCONSAPUNTEEVTZ salida,
			MovimientoBean movimientoBean) {
		if (salida != null && salida.getAFAPNTEE() != null) {
			consultaApunteWrapper.consultaDetalleApunte(salida, movimientoBean);
			// Obtenemos el signo del apunte.
			if ("D".equals(movimientoBean.getSigno())) {
				movimientoBean.setSigno("DEBE");
			}
			if ("H".equals(movimientoBean.getSigno())) {
				movimientoBean.setSigno("HABER");
			}
			if(StringUtils.isEmpty(movimientoBean.getCodigoOrigenApunte()) || 
					StringUtils.isEmpty(movimientoBean.getIdOrigenApunte())){
				CuentaBean cuenta = new CuentaBean();
				cuenta.setNumeroCuenta(salida.getAFAPNTEE().getNUMSECAC());
				cuenta.setCodLinea(salida.getAFAPNTEE().getCODLINEA());
				cuenta.setIdGrupoProducto(salida.getAFAPNTEE().getIDGRPPD());
				consultaGeneralApunteBackEnd.ejecutarTRN(movimientoBean, cuenta, 
						salida.getAFAPNTEE().getNUMSEC(), salida.getAFAPNTEE().getCODCTA());
			}
		}
	}

}