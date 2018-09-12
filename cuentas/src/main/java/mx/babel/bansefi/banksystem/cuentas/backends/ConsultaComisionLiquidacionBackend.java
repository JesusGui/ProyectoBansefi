package mx.babel.bansefi.banksystem.cuentas.backends;

import java.math.BigDecimal;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.LiquidacionBean;
import mx.babel.bansefi.banksystem.base.beans.LiquidacionConceptoBean;
import mx.babel.bansefi.banksystem.base.beans.domain.CuentaBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.constants.ConstantesFuncionales;
import mx.babel.bansefi.banksystem.base.utils.IntegerToDateConverter;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultacomisionliquidacion.ConsultaComisionLiquidacionServicio;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultacomisionliquidacion.Ejecutar.ITRCONSKTTRNI;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultacomisionliquidacion.Ejecutar.ITRCONSKTTRNI.TRCONSKTEVTY.KTCLAVEV.HLCLAVEAFV;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultacomisionliquidacion.EjecutarResult;
import mx.babel.bansefi.banksystem.cuentas.wrappers.ConsultaLiquidacionesWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Backend para la TRN de consulta de comisiones y gastos de una liquidación.
 * 
 * @author omar.marquez
 */
@Component
public class ConsultaComisionLiquidacionBackend extends BackEndBean {

	private static final long serialVersionUID = 1989996537775301022L;

	private IntegerToDateConverter integerToDateConverter;

	@Autowired
	ServicioWebUtils servicioWebUtils;

	@Autowired
	ConsultaLiquidacionesWrapper consultaLiquidacionesWrapper;

	/**
	 * Constructor.
	 */
	public ConsultaComisionLiquidacionBackend() {
		super();
		this.integerToDateConverter = new IntegerToDateConverter();
	}

	/**
	 * Método que ejecuta la TRN de consulta de comisiones y gastos de una
	 * liquidación.
	 * 
	 * @param cuentaBean
	 * @param liquidacionBean
	 * @return liquidacionConceptoBean con datos de comisión
	 * @throws IndexOutOfBoundsException
	 * @throws NullPointerException
	 * @throws ControlableException
	 * @throws NoControlableException
	 */
	public LiquidacionConceptoBean ejecutarTRN(CuentaBean cuentaBean,
			LiquidacionBean liquidacionBean) throws IndexOutOfBoundsException,
			NullPointerException, ControlableException, NoControlableException {
		ITRCONSKTTRNI itrconskttrni = initPeticion(cuentaBean, liquidacionBean);

		EjecutarResult resultado = (EjecutarResult) servicioWebUtils
				.ejecutarWS(ConsultaComisionLiquidacionServicio.class,
						itrconskttrni);

		super.verificaRespuesta(resultado);

		// Antes de wrappear los datos, obtenemos el concepto para no perderlo.
		String concepto = liquidacionBean.getConceptoSeleccionado()
				.getDescConcepto();

		LiquidacionConceptoBean liquidacionConceptoBean = consultaLiquidacionesWrapper
				.wrappComisionLiq(resultado.getResponseBansefi()
						.getOTRCONSKTTRNO().getTRCONSKTEVTZ().getKTDATOSLST()
						.getKTDATOSV().get(0));

		// Establecemos la descripción del concepto original.
		liquidacionConceptoBean.setDescConcepto(concepto);

		return liquidacionConceptoBean;
	}

	/**
	 * Método privado que prepara los datos que serán enviados a la TRN.
	 * 
	 * @param cuentaBean
	 * @param liquidacionBean
	 * @return parametros de entrada encapsulados en un objeto ITRCONSKTTRNI
	 * @throws NullPointerException
	 */
	private ITRCONSKTTRNI initPeticion(CuentaBean cuentaBean,
			LiquidacionBean liquidacionBean) throws NullPointerException {
		ITRCONSKTTRNI itrconskttrni = new ITRCONSKTTRNI();

		super.initialize(itrconskttrni);

		itrconskttrni.setELEVATORPOSITION(0);
		itrconskttrni.setSCROLLABLEOCCURS(SCROLLABLE_OCCURS);

		itrconskttrni.getTRCONSKTEVTY().getKTCLAVEV()
				.setCODNRBEEN(super.getEntidad());
		itrconskttrni.getTRCONSKTEVTY().getKTCLAVEV()
				.setNUMSECAC(cuentaBean.getNumeroCuenta());
		itrconskttrni
				.getTRCONSKTEVTY()
				.getKTCLAVEV()
				.setFECHALIQ(
						integerToDateConverter.convertFrom(liquidacionBean
								.getFechaLiquidacion()));
		itrconskttrni
				.getTRCONSKTEVTY()
				.getKTCLAVEV()
				.setCODCTA(
						liquidacionBean.getConceptoSeleccionado()
								.getCodCuenta());
		itrconskttrni.getTRCONSKTEVTY().getKTCLAVEV()
				.setCODNUMRCOMONEDA(ConstantesFuncionales.COD_MONEDA);
		itrconskttrni.getTRCONSKTEVTY().getKTCLAVEV().getIMPFACTURADOV()
				.setIMPSDO(BigDecimal.ZERO);
		itrconskttrni.getTRCONSKTEVTY().getKTCLAVEV().getHLCLAVEAFV()
				.add(new HLCLAVEAFV());

		if (liquidacionBean.getConceptoSeleccionado().getNumSecComision() != 0) {
			itrconskttrni
					.getTRCONSKTEVTY()
					.getKTCLAVEV()
					.getHLCLAVEAFV()
					.get(0)
					.setNUMSEC(
							liquidacionBean.getConceptoSeleccionado()
									.getNumSecComision());
		} else {
			// itrconskttrni.getTRCONSKTEVTY().getKTCLAVEV().getHLCLAVEAFV()
			// .get(0).setNUMSEC(1);
			itrconskttrni.getTRCONSKTEVTY().getKTCLAVEV().getHLCLAVEAFV()
					.get(0).setNUMSEC(liquidacionBean.getNumsec());
		}

		itrconskttrni.getTRCONSKTEVTY().getKTCLAVEV().getHLCLAVEAFV().get(0)
				.setPRPDADCTA(ConstantesFuncionales.PRPDAD_CTA);

		if (liquidacionBean.getConceptoSeleccionado().getNumSubAcComision() != 0) {
			itrconskttrni
					.getTRCONSKTEVTY()
					.getKTCLAVEV()
					.getHLCLAVEAFV()
					.get(0)
					.setNUMSUBAC(
							liquidacionBean.getConceptoSeleccionado()
									.getNumSubAcComision());
		} else {
			itrconskttrni.getTRCONSKTEVTY().getKTCLAVEV().getHLCLAVEAFV()
					.get(0).setNUMSUBAC(0);
		}

		itrconskttrni.getSTDTRNIPARMV().setIDINTERNOTERMTN(super.getTerminal());
		itrconskttrni.getSTDTRNIPARMV().setCODTX(
				CodTxConstants.COD_TX_TR_CONS_KT_TRN);

		return itrconskttrni;
	}
}