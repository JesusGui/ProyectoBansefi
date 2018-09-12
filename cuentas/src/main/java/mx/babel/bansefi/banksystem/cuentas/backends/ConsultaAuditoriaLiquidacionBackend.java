package mx.babel.bansefi.banksystem.cuentas.backends;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.AuditoriaBean;
import mx.babel.bansefi.banksystem.base.beans.LiquidacionBean;
import mx.babel.bansefi.banksystem.base.beans.ParametrosBusquedaApunteBean;
import mx.babel.bansefi.banksystem.base.beans.domain.CuentaBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.constants.ConstantesFuncionales;
import mx.babel.bansefi.banksystem.base.utils.IntegerToDateConverter;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultaauditorialiquidacion.ConsultaAuditoriaLiquidacionServicio;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultaauditorialiquidacion.Ejecutar;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultaauditorialiquidacion.Ejecutar.ITRTXCONSAUDITTRNI;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultaauditorialiquidacion.EjecutarResult;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultaauditorialiquidacion.ResponseBansefi;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultaauditorialiquidacion.ResponseBansefi.OTRTXCONSAUDITTRNO.TRTXCONSAUDITEVTZ.AFAUDITE;
import mx.babel.bansefi.banksystem.cuentas.wrappers.ConsultaApunteWrapper;
import mx.babel.bansefi.banksystem.cuentas.wrappers.ConsultaLiquidacionesWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Backend para la TRN de consulta de datos de auditoría (TR_TX_CONS_AUDIT_TRN).
 * 
 * @author gerard.chavez
 * 
 */
@Component
public class ConsultaAuditoriaLiquidacionBackend extends BackEndBean {

	private static final long serialVersionUID = -1889326327396284806L;

	private static final String HL_STDCHAR02 = "HL";
	private static final String AF_STDCHAR02 = "AF";

	@Autowired
	ServicioWebUtils servicioWebUtils;

	@Autowired
	ConsultaLiquidacionesWrapper consultaLiquidacionesWrapper;

	@Autowired
	ConsultaApunteWrapper consultaApunteWrapper;

	/**
	 * Constructor.
	 */
	public ConsultaAuditoriaLiquidacionBackend() {
		super();
	}

	/**
	 * ########################################################################
	 * ##################### INICIAN MÉTODOS DEL FLUJO HL #####################
	 * ########################################################################
	 */

	public AuditoriaBean ejecutarTRN(CuentaBean cuentaBean,
			LiquidacionBean liquidacionBean) {
		Ejecutar.ITRTXCONSAUDITTRNI contexto = initPeticionHL(cuentaBean,
				liquidacionBean);

		EjecutarResult respuesta = (EjecutarResult) servicioWebUtils
				.ejecutarWS(ConsultaAuditoriaLiquidacionServicio.class,
						contexto);

		super.verificaRespuesta(respuesta);

		return getAuditoria(respuesta);
	}

	/**
	 * Función que inicializa el objeto para realizar la petición al servicio
	 * web.
	 * 
	 * @return Objeto a ser enviado al servicio web.
	 */
	private Ejecutar.ITRTXCONSAUDITTRNI initPeticionHL(CuentaBean cuentaBean,
			LiquidacionBean liquidacionBean) {
		Ejecutar.ITRTXCONSAUDITTRNI contexto = new Ejecutar.ITRTXCONSAUDITTRNI();
		Ejecutar.ITRTXCONSAUDITTRNI.STDTRNIPARMV contextoEntrada = new Ejecutar.ITRTXCONSAUDITTRNI.STDTRNIPARMV();
		Ejecutar.ITRTXCONSAUDITTRNI.TRTXCONSAUDITEVTY cuerpoContexto = new Ejecutar.ITRTXCONSAUDITTRNI.TRTXCONSAUDITEVTY();

		contextoEntrada.setCODTX(CodTxConstants.COD_TX_TR_TX_CONS_AUDIT_TRN);
		contextoEntrada.setIDINTERNOTERMTN(getTerminal());

		Ejecutar.ITRTXCONSAUDITTRNI.TRTXCONSAUDITEVTY.TIPOCLAVEV tipoclaveev = new Ejecutar.ITRTXCONSAUDITTRNI.TRTXCONSAUDITEVTY.TIPOCLAVEV();
		tipoclaveev.setSTDCHAR02(HL_STDCHAR02);

		cuerpoContexto.setTIPOCLAVEV(tipoclaveev);

		Ejecutar.ITRTXCONSAUDITTRNI.TRTXCONSAUDITEVTY.HLAUDITP hlauditp = new Ejecutar.ITRTXCONSAUDITTRNI.TRTXCONSAUDITEVTY.HLAUDITP();
		hlauditp.setCODNRBEEN(getEntidad());
		hlauditp.setNUMSECAC(cuentaBean.getNumeroCuenta());
		IntegerToDateConverter converter = new IntegerToDateConverter();
		hlauditp.setFECHALIQ(converter.convertFrom(liquidacionBean
				.getFechaLiquidacion()));
		hlauditp.setNUMSEC(liquidacionBean.getNumsec());
		cuerpoContexto.setHLAUDITP(hlauditp);

		contexto.setSTDTRNIPARMV(contextoEntrada);
		contexto.setSCROLLABLEOCCURS(SCROLLABLE_OCCURS);
		contexto.setELEVATORPOSITION(1);
		contexto.setTRTXCONSAUDITEVTY(cuerpoContexto);

		super.initialize(contexto);
		return contexto;
	}

	/**
	 * Función encargada de obtener la info de auditoria de una liquidacion a
	 * partir de la respuesta del servicio.
	 * 
	 * @param respuesta
	 *            El objeto de reultado enviado por el servicio web.
	 * @return
	 */
	private AuditoriaBean getAuditoria(EjecutarResult respuesta) {
		AuditoriaBean resultado = new AuditoriaBean();
		for (ResponseBansefi.OTRTXCONSAUDITTRNO.TRTXCONSAUDITEVTZ.HLAUDITE auditoriaLiquidacion : respuesta
				.getResponseBansefi().getOTRTXCONSAUDITTRNO()
				.getTRTXCONSAUDITEVTZ().getHLAUDITE()) {
			if (!"".equals(auditoriaLiquidacion.getCODNRBEEN().trim())) {
				resultado = consultaLiquidacionesWrapper
						.wrappDetalleAuditoriaLiq(auditoriaLiquidacion);
				break;
			}
		}
		return resultado;
	}

	/**
	 * ########################################################################
	 * ##################### INICIAN MÉTODOS DEL FLUJO AF #####################
	 * ########################################################################
	 */

	/**
	 * Método que ejecuta la TRN de consulta de auditoría de un apunte.
	 * 
	 * @param parametrosBean
	 * @return parametrosBean con atributos de auditoría
	 * @throws IndexOutOfBoundsException
	 * @throws NullPointerException
	 * @throws ControlableException
	 * @throws NoControlableException
	 */
	public ParametrosBusquedaApunteBean ejecutarTRN(
			ParametrosBusquedaApunteBean parametrosBean)
			throws IndexOutOfBoundsException, NullPointerException,
			ControlableException, NoControlableException {
		ITRTXCONSAUDITTRNI itrtxconsaudittrni = initPeticion(parametrosBean);

		EjecutarResult respuesta = (EjecutarResult) servicioWebUtils
				.ejecutarWS(ConsultaAuditoriaLiquidacionServicio.class,
						itrtxconsaudittrni);

		super.verificaRespuesta(respuesta);

		AFAUDITE elemento = respuesta.getResponseBansefi()
				.getOTRTXCONSAUDITTRNO().getTRTXCONSAUDITEVTZ().getAFAUDITE()
				.get(0);

		return getAuditoria(parametrosBean, elemento);
	}

	/**
	 * Método privado que prepara los datos que serán enviados a la TRN.
	 * 
	 * @param parametrosBean
	 * @return parametros de entrada encapsulados en un objeto
	 *         ITRTXCONSAUDITTRNI
	 * @throws NullPointerException
	 */
	private ITRTXCONSAUDITTRNI initPeticion(
			ParametrosBusquedaApunteBean parametrosBean)
			throws NullPointerException {
		ITRTXCONSAUDITTRNI itrtxconsaudittrni = new ITRTXCONSAUDITTRNI();

		super.initialize(itrtxconsaudittrni);

		itrtxconsaudittrni.setELEVATORPOSITION(0);
		itrtxconsaudittrni.setSCROLLABLEOCCURS(SCROLLABLE_OCCURS);

		itrtxconsaudittrni.getTRTXCONSAUDITEVTY().getTIPOCLAVEV()
				.setSTDCHAR02(AF_STDCHAR02);

		itrtxconsaudittrni.getTRTXCONSAUDITEVTY().getAFAUDITP()
				.setCODNRBEEN(super.getEntidad());
		itrtxconsaudittrni.getTRTXCONSAUDITEVTY().getAFAUDITP()
				.setNUMSECAC(parametrosBean.getCuentaBean().getNumeroCuenta());
		itrtxconsaudittrni.getTRTXCONSAUDITEVTY().getAFAUDITP()
				.setCODPRPDADCTA(ConstantesFuncionales.PRPDAD_CTA);
		itrtxconsaudittrni.getTRTXCONSAUDITEVTY().getAFAUDITP()
				.setCODNUMRCOMONEDA(ConstantesFuncionales.COD_MONEDA);
		itrtxconsaudittrni.getTRTXCONSAUDITEVTY().getAFAUDITP()
				.setCODCTA(parametrosBean.getFiltroTipoCuenta());
		itrtxconsaudittrni.getTRTXCONSAUDITEVTY().getAFAUDITP()
				.setNUMSEC(parametrosBean.getApunteSeleccionado().getNumSec());

		itrtxconsaudittrni.getSTDTRNIPARMV().setIDINTERNOTERMTN(
				super.getTerminal());
		itrtxconsaudittrni.getSTDTRNIPARMV().setCODTX(
				CodTxConstants.COD_TX_TR_TX_CONS_AUDIT_TRN);

		return itrtxconsaudittrni;
	}

	/**
	 * Método privado que devuelve los datos de la consulta de auditoría.
	 * 
	 * @param parametrosBean
	 * @param elemento
	 * @return parametrosBean con atributos de auditoría
	 */
	private ParametrosBusquedaApunteBean getAuditoria(
			ParametrosBusquedaApunteBean parametrosBean, AFAUDITE elemento) {
		if (elemento != null && elemento.getNUMSEC() != 0) {
			AuditoriaBean auditoriaBean = consultaApunteWrapper
					.consultaAuditoriaApunteWrapper(elemento);
			parametrosBean.getApunteSeleccionado().setAuditoriaBean(
					auditoriaBean);
		}
		return parametrosBean;
	}

}