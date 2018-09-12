package mx.babel.bansefi.banksystem.cuentas.backends;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.AuditoriaBean;
import mx.babel.bansefi.banksystem.base.beans.ParametrosBusquedaApunteBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.utils.IntegerToDateConverter;
import mx.babel.bansefi.banksystem.cuentas.webservices.cargaauditoria.CargaAuditoriaServicio;
import mx.babel.bansefi.banksystem.cuentas.webservices.cargaauditoria.Ejecutar;
import mx.babel.bansefi.banksystem.cuentas.webservices.cargaauditoria.Ejecutar.ITRCARGARAUDITTRNI;
import mx.babel.bansefi.banksystem.cuentas.webservices.cargaauditoria.EjecutarResult;
import mx.babel.bansefi.banksystem.cuentas.webservices.cargaauditoria.ResponseBansefi;
import mx.babel.bansefi.banksystem.cuentas.wrappers.ConsultaLiquidacionesWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Backend para la TRN de consulta de datos adicionales de auditoría
 * (TR_CARGAR_AUDIT_TRN).
 */
@Component
public class ConsultaAuditoriaInfoLiquidacionBackend extends BackEndBean {

	private static final long serialVersionUID = 5450864921885955006L;

	@Autowired
	ServicioWebUtils servicioWebUtils;

	@Autowired
	ConsultaLiquidacionesWrapper consultaLiquidacionesWrapper;

	/**
	 * Constructor.
	 */
	public ConsultaAuditoriaInfoLiquidacionBackend() {
		super();
	}

	public void ejecutarTRN(AuditoriaBean auditoria) {
		Ejecutar.ITRCARGARAUDITTRNI contexto = initPeticion(auditoria);
		EjecutarResult respuesta = (EjecutarResult) servicioWebUtils
				.ejecutarWS(CargaAuditoriaServicio.class, contexto);

		super.verificaRespuesta(respuesta);

		getAuditoria(respuesta, auditoria);
	}

	/**
	 * Función que inicializa el objeto para realizar la petición al servicio
	 * web.
	 * 
	 * @return Objeto a ser enviado al servicio web.
	 */
	private Ejecutar.ITRCARGARAUDITTRNI initPeticion(AuditoriaBean auditoria) {
		Ejecutar.ITRCARGARAUDITTRNI contexto = new Ejecutar.ITRCARGARAUDITTRNI();
		Ejecutar.ITRCARGARAUDITTRNI.STDTRNIPARMV contextoEntrada = new Ejecutar.ITRCARGARAUDITTRNI.STDTRNIPARMV();
		Ejecutar.ITRCARGARAUDITTRNI.TRCARGARAUDITEVTY cuerpoContexto = new Ejecutar.ITRCARGARAUDITTRNI.TRCARGARAUDITEVTY();

		contextoEntrada.setCODTX(CodTxConstants.COD_TX_TR_CARGAR_AUDIT_TRN);
		contextoEntrada.setIDINTERNOTERMTN(getTerminal());
		contextoEntrada.setCODTXDI("");
		contextoEntrada.setIDEMPLAUT("");
		contextoEntrada.setNUMSEC(4209);

		Ejecutar.ITRCARGARAUDITTRNI.TRCARGARAUDITEVTY.EMPLEADOSAUDITV empleadosAudit = new Ejecutar.ITRCARGARAUDITTRNI.TRCARGARAUDITEVTY.EMPLEADOSAUDITV();
		empleadosAudit.setIDINTERNOEMPLEP(auditoria.getEmpleadoOrigen());
		empleadosAudit.setIDEMPLAUT(auditoria.getEmpleadoAutorizado());
		empleadosAudit.setCODNRBEEN(getEntidad());

		cuerpoContexto.setEMPLEADOSAUDITV(empleadosAudit);

		Ejecutar.ITRCARGARAUDITTRNI.TRCARGARAUDITEVTY.CARGARAUDITACV cargaAudit = new Ejecutar.ITRCARGARAUDITTRNI.TRCARGARAUDITEVTY.CARGARAUDITACV();
		cargaAudit.setCODNRBEEN(getEntidad());
		cuerpoContexto.setCARGARAUDITACV(cargaAudit);

		Ejecutar.ITRCARGARAUDITTRNI.TRCARGARAUDITEVTY.FECHACTBLEV fechaContable = new Ejecutar.ITRCARGARAUDITTRNI.TRCARGARAUDITEVTY.FECHACTBLEV();
		IntegerToDateConverter converter = new IntegerToDateConverter();
		fechaContable.setFECHACTBLE(converter.convertFrom(auditoria
				.getFechaContable()));

		contexto.setSTDTRNIPARMV(contextoEntrada);
		contexto.setTRCARGARAUDITEVTY(cuerpoContexto);
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
	private void getAuditoria(EjecutarResult respuesta,
			AuditoriaBean auditoriaBean) throws NoControlableException,
			ControlableException {
		if (verificaRespuesta(respuesta)) {
			ResponseBansefi.OTRCARGARAUDITTRNO.TRCARGARAUDITEVTZ auditoria = respuesta
					.getResponseBansefi().getOTRCARGARAUDITTRNO()
					.getTRCARGARAUDITEVTZ();
			consultaLiquidacionesWrapper.wrappDetalleInfoAuditoriaLiq(
					auditoria, auditoriaBean);
		}
	}

	/**
	 * Función que valida que la respuesta Bansefi contenga un objeto con los
	 * datos de la consulta del detalle de liquidacion
	 * 
	 * @param respuesta
	 *            Respuesta Bansefi con datos de la consulta
	 * @return <code>true</code> si la respuesta Bansefi contiene datos
	 */
	private Boolean verificaRespuesta(EjecutarResult respuesta) {
		Boolean noNulo = false;
		if (respuesta != null
				&& respuesta.getResponseBansefi().getOTRCARGARAUDITTRNO() != null
				&& respuesta.getResponseBansefi().getOTRCARGARAUDITTRNO()
						.getTRCARGARAUDITEVTZ() != null) {
			noNulo = true;
		}
		return noNulo;
	}

	/**
	 * Método privado que prepara los datos que serán enviados a la TRN.
	 * 
	 * @param parametrosBean
	 * @return parametrosBean con atributos de la carga de auditoría
	 */
	public ParametrosBusquedaApunteBean ejecutarTRN(
			ParametrosBusquedaApunteBean parametrosBean) {
		Ejecutar.ITRCARGARAUDITTRNI contexto = initPeticion(parametrosBean);

		EjecutarResult respuesta = (EjecutarResult) servicioWebUtils
				.ejecutarWS(CargaAuditoriaServicio.class, contexto);

		super.verificaRespuesta(respuesta);

		return getAuditoria(respuesta, parametrosBean);
	}

	/**
	 * Método privado que prepara los datos que serán enviados a la TRN.
	 * 
	 * @param parametrosBean
	 * @return parametros de entrada encapsulados en un objeto
	 *         ITRCARGARAUDITTRNI
	 * @throws NullPointerException
	 */
	private ITRCARGARAUDITTRNI initPeticion(
			ParametrosBusquedaApunteBean parametrosBean)
			throws NullPointerException {
		ITRCARGARAUDITTRNI itrcargaraudittrni = new ITRCARGARAUDITTRNI();

		super.initialize(itrcargaraudittrni);

		itrcargaraudittrni.getTRCARGARAUDITEVTY().getEMPLEADOSAUDITV()
				.setCODNRBEEN(super.getEntidad());

		if (parametrosBean.getApunteSeleccionado().getAuditoriaBean()
				.getEmpleadoOrigen() != null
				&& !parametrosBean.getApunteSeleccionado().getAuditoriaBean()
						.getEmpleadoOrigen().trim().isEmpty()) {
			itrcargaraudittrni
					.getTRCARGARAUDITEVTY()
					.getEMPLEADOSAUDITV()
					.setIDINTERNOEMPLEP(
							parametrosBean.getApunteSeleccionado()
									.getAuditoriaBean().getEmpleadoOrigen());
		}

		if (parametrosBean.getApunteSeleccionado().getAuditoriaBean()
				.getEmpleadoAutorizado() != null
				&& !parametrosBean.getApunteSeleccionado().getAuditoriaBean()
						.getEmpleadoAutorizado().trim().isEmpty()) {
			itrcargaraudittrni
					.getTRCARGARAUDITEVTY()
					.getEMPLEADOSAUDITV()
					.setIDEMPLAUT(
							parametrosBean.getApunteSeleccionado()
									.getAuditoriaBean().getEmpleadoAutorizado());
		}

		itrcargaraudittrni.getSTDTRNIPARMV().setIDINTERNOTERMTN(
				super.getTerminal());
		itrcargaraudittrni.getSTDTRNIPARMV().setCODTX(
				CodTxConstants.COD_TX_TR_CARGAR_AUDIT_TRN);

		return itrcargaraudittrni;
	}

	/**
	 * Método privado que a partir de los identificadores de usuario (origen y
	 * autorizador) devuelve sus nombres.
	 * 
	 * @param respuesta
	 * @param parametrosBean
	 * @return parametrosBean con nombres de usuario (origen y autorizador)
	 * @throws NullPointerException
	 */
	private ParametrosBusquedaApunteBean getAuditoria(EjecutarResult respuesta,
			ParametrosBusquedaApunteBean parametrosBean)
			throws NullPointerException {
		if (respuesta.getResponseBansefi().getOTRCARGARAUDITTRNO()
				.getTRCARGARAUDITEVTZ().getNOMBEMPLORIG().getNOMB50() != null
				&& !respuesta.getResponseBansefi().getOTRCARGARAUDITTRNO()
						.getTRCARGARAUDITEVTZ().getNOMBEMPLORIG().getNOMB50()
						.trim().isEmpty()) {
			parametrosBean
					.getApunteSeleccionado()
					.getAuditoriaBean()
					.setEmpleadoOrigenDesc(
							respuesta.getResponseBansefi()
									.getOTRCARGARAUDITTRNO()
									.getTRCARGARAUDITEVTZ().getNOMBEMPLORIG()
									.getNOMB50().trim());
		}
		if (respuesta.getResponseBansefi().getOTRCARGARAUDITTRNO()
				.getTRCARGARAUDITEVTZ().getNOMBEMPLAUT().getNOMB50() != null
				&& !respuesta.getResponseBansefi().getOTRCARGARAUDITTRNO()
						.getTRCARGARAUDITEVTZ().getNOMBEMPLAUT().getNOMB50()
						.trim().isEmpty()) {
			parametrosBean
					.getApunteSeleccionado()
					.getAuditoriaBean()
					.setEmpleadoAutorizadoDesc(
							respuesta.getResponseBansefi()
									.getOTRCARGARAUDITTRNO()
									.getTRCARGARAUDITEVTZ().getNOMBEMPLAUT()
									.getNOMB50().trim());
		}
		return parametrosBean;
	}

}