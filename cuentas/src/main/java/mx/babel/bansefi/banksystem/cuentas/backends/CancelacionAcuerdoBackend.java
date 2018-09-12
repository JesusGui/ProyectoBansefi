package mx.babel.bansefi.banksystem.cuentas.backends;

import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.constants.ConstantesFuncionales;
import mx.babel.bansefi.banksystem.base.utils.IntegerToDateConverter;
import mx.babel.bansefi.banksystem.cuentas.beans.CancelacionCuentaBean;
import mx.babel.bansefi.banksystem.cuentas.beans.EsperaBean;
import mx.babel.bansefi.banksystem.cuentas.webservices.cancelacionacuerdo.CancelacionAcuerdoServicio;
import mx.babel.bansefi.banksystem.cuentas.webservices.cancelacionacuerdo.Ejecutar;
import mx.babel.bansefi.banksystem.cuentas.webservices.cancelacionacuerdo.Ejecutar.TRCANCETOTALACTRNI.STDTRNIPARMV;
import mx.babel.bansefi.banksystem.cuentas.webservices.cancelacionacuerdo.Ejecutar.TRCANCETOTALACTRNI.TRCANCETOTALACEVTY.ACACP;
import mx.babel.bansefi.banksystem.cuentas.webservices.cancelacionacuerdo.EjecutarResult;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author joseluis.pena
 * 
 */
@Component
public class CancelacionAcuerdoBackend extends BackEndBean {

	private static final long serialVersionUID = 6564921993348101257L;

	private static final Logger LOGGER = LogManager
			.getLogger(CancelacionAcuerdoBackend.class);
	
	@Autowired
	ServicioWebUtils servicioWebUtils;

	private static final int NUM_STD_TARGET_TERMINAL_V = 50;

	public void ejecutarTRN(final CancelacionCuentaBean cancelacionCuentaBean){
		final Ejecutar.TRCANCETOTALACTRNI request = initPeticion(cancelacionCuentaBean);

		final EjecutarResult respuesta = ejecutarWS(request);

		super.verificaRespuesta(respuesta);
		
		if(!StringUtils.isBlank(respuesta.getResponseBansefi().getTRCANCETOTALACTRNO().getRESPUESTASINCRONAV().getSTDCHAR01())){
			EsperaBean esperaBean = new EsperaBean();
			esperaBean.setFechaOperacion(respuesta.getResponseBansefi().getTRCANCETOTALACTRNO().getSTDTRNOPARMV().getFECHAOPRCN());
			esperaBean.setHoraOperacion(respuesta.getResponseBansefi().getTRCANCETOTALACTRNO().getSTDTRNOPARMV().getHORAOPRCN());
			esperaBean.setTerminal(this.getTerminal());
			esperaBean.setSecuencia(0);
			String respuestaSincrona = respuesta.getResponseBansefi().getTRCANCETOTALACTRNO().getRESPUESTASINCRONAV().getSTDCHAR01();
			esperaBean.setRespuestaSincrona(respuestaSincrona);
			cancelacionCuentaBean.setEsperaBean(esperaBean);
		}
	}

	/**
	 * Función que inicializa el objeto para realizar la petición al servicio
	 * web.
	 * 
	 * @return Objeto a ser enviado al servicio web.
	 */
	private Ejecutar.TRCANCETOTALACTRNI initPeticion(
			final CancelacionCuentaBean cancelacionCuentaBean) {
		final Ejecutar.TRCANCETOTALACTRNI peticion = new Ejecutar.TRCANCETOTALACTRNI();

		super.initialize(peticion);

		final ACACP acacp = peticion.getTRCANCETOTALACEVTY().getACACP();
		acacp.setCODNRBEEN(this.getEntidad());
		acacp.setNUMSECAC(cancelacionCuentaBean.getCuentaBean()
				.getNumeroCuenta());

		// OTRA CUENTA
		if (cancelacionCuentaBean.getDestinoImporte() != null
				&& cancelacionCuentaBean.getDestinoImporte().equals("null")
				&& cancelacionCuentaBean.getOtraCuenta() != null) {
			peticion.getTRCANCETOTALACEVTY().getRXACRLACP().setCODNRBEEN(this.getEntidad());
			peticion.getTRCANCETOTALACEVTY().getACVTIPOCOBROV()
					.setSTDCHAR02(" ");
			peticion.getTRCANCETOTALACEVTY().getRXACRLACP()
					.setNUMSECAC2(cancelacionCuentaBean.getOtraCuenta());
		} else {
			peticion.getTRCANCETOTALACEVTY().getACVTIPOCOBROV()
					.setSTDCHAR02(cancelacionCuentaBean.getDestinoImporte());
		}

		IntegerToDateConverter itd = new IntegerToDateConverter();
		// RAZON DE CANCELACION
		if (cancelacionCuentaBean.getRazonCancelacion() != null) {
			peticion.getTRCANCETOTALACEVTY().getACVFECHAVALORV()
					.setSTDFECHA(00000000);
			peticion.getTRCANCETOTALACEVTY().getACVTIPOCOBROV()
					.setSTDCHAR02(ConstantesFuncionales.CTE_CT);
			peticion.getTRCANCETOTALACEVTY()
					.getACRAZONCANCEV()
					.setCODRZNECVAC(cancelacionCuentaBean.getRazonCancelacion());
		} else {
			peticion.getTRCANCETOTALACEVTY().getACVFECHAVALORV()
					.setSTDFECHA(itd.convertFrom(this.getFechaSistema()));
		}

		// TODO constante
		peticion.getRESPUESTASINCRONAV().setSTDCHAR01(
				cancelacionCuentaBean.getRespuestaSincrona());
		// TODO constante
		peticion.getNROLLAMADAV().setSTDCHAR01(
				cancelacionCuentaBean.getnLlamada());

		final STDTRNIPARMV stdtrniparmv = peticion.getSTDTRNIPARMV();
		stdtrniparmv.setCODTX(CodTxConstants.COD_TX_TR_CANCE_TOTAL_AC_TRN);
		stdtrniparmv.setIDINTERNOTERMTN(this.getTerminal());

		// Creacion de ocurrencias necesarias para el correcto funcionamiento de
		// la TRN
		for (int i = 0; i < NUM_STD_TARGET_TERMINAL_V; i++) {
			Ejecutar.TRCANCETOTALACTRNI.STDAUTORIZAV.STDTARGETTERMINALV terminal = new Ejecutar.TRCANCETOTALACTRNI.STDAUTORIZAV.STDTARGETTERMINALV();
			super.initialize(terminal);
			peticion.getSTDAUTORIZAV().getSTDTARGETTERMINALV().add(terminal);
		}

		return peticion;
	}

	/**
	 * Función para invocar al servicio web y obtener su respuesta.
	 * 
	 * @param contexto
	 *            Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	private EjecutarResult ejecutarWS(final Ejecutar.TRCANCETOTALACTRNI contexto)
			throws NoControlableException {
		EjecutarResult respuesta = null;
		try {
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
					CancelacionAcuerdoServicio.class, contexto);
		} catch (final NoControlableException e) {
			LOGGER.debug("Error cancelacion cuenta NoControlable--", e);
			throw new NoControlableException(
					"Error al invocar servicio web de cancelacion "
							+ " de acuerdo.", e);
		}
		return respuesta;
	}
	

}
