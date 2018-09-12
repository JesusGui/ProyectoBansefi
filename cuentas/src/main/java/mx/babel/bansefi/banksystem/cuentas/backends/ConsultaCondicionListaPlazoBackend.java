package mx.babel.bansefi.banksystem.cuentas.backends;

import java.util.Date;

import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.productossimples.condiciones.CondicionListaBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.utils.IntegerToDateConverter;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultacondicionlistaplazo.ConsultaCondicionListaPlazoServicio;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultacondicionlistaplazo.Ejecutar;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultacondicionlistaplazo.Ejecutar.ITRCONSVALKSLSTTRNI.STDTRNIPARMV;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultacondicionlistaplazo.Ejecutar.ITRCONSVALKSLSTTRNI.TRCONSVALKSLSTEVTY.KSCDSBP;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultacondicionlistaplazo.EjecutarResult;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultacondicionlistaplazo.ResponseBansefi;
import mx.babel.bansefi.banksystem.cuentas.wrappers.ConsultaCondicionListaWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author joseluis.pena
 *
 */
@Component
public class ConsultaCondicionListaPlazoBackend extends BackEndBean {

	private static final long serialVersionUID = 2193790806092068134L;

	@Autowired
	ConsultaCondicionListaWrapper consultaCondicionListaWrapper;

	@Autowired
	ServicioWebUtils servicioWebUtils;

	public CondicionListaBean ejecutarTRN(final long numCuenta, final int numSubAc, final String idPds, final CondicionListaBean condicion){

		final Ejecutar.ITRCONSVALKSLSTTRNI trconsvalkalsttrni = initPeticion(numCuenta, numSubAc,
		        idPds, condicion.getClave(), condicion.getFechaInicioValidez());

		final EjecutarResult respuesta = ejecutarWS(trconsvalkalsttrni);

		super.verificaRespuesta(respuesta);

		return getCondiciones(respuesta.getResponseBansefi());
	}

	private CondicionListaBean getCondiciones(final ResponseBansefi response){
		CondicionListaBean condicion = null;
		if (verificaRespuesta(response)) {
			condicion = consultaCondicionListaWrapper
					.wrappCondicionListaPlazo(response.getOTRCONSVALKSLSTTRNO()
							.getTRCONSVALKSLSTEVTZ());
		}
		return condicion;
	}

	/**
	 * Función que inicializa el objeto para realizar la petición al servicio
	 * web.
	 *
	 * @return Objeto a ser enviado al servicio web.
	 */
	private Ejecutar.ITRCONSVALKSLSTTRNI initPeticion(final long numCuenta, final int numSubAc, final String idPds,
	        final String idParmCd, final Date fechaInicio) {
		final Ejecutar.ITRCONSVALKSLSTTRNI trconsvalkalsttrni = new Ejecutar.ITRCONSVALKSLSTTRNI();

		super.initialize(trconsvalkalsttrni);
        final IntegerToDateConverter itdConverter = new IntegerToDateConverter();

		final KSCDSBP kscdsbp = trconsvalkalsttrni.getTRCONSVALKSLSTEVTY()
				.getKSCDSBP();
		kscdsbp.setCODNRBEEN(this.getEntidad());
		// TODO sustituir por el numero de acuerdo
		kscdsbp.setNUMSECAC(numCuenta);
        // TODO sustituir por el numero de acuerdo
        kscdsbp.setNUMSUBAC(numSubAc);
		// TODO sustituir por datos del acuerdo
		kscdsbp.setIDPDS(idPds);
		// TODO sustituir por datos del acuerdo
		kscdsbp.setIDPARMCD(idParmCd);

        kscdsbp.setFECHAINICVAL(itdConverter.convertFrom(fechaInicio));

		final STDTRNIPARMV stdtrniparmv = trconsvalkalsttrni.getSTDTRNIPARMV();
		stdtrniparmv.setCODTX(CodTxConstants.COD_TX_TR_CONS_VAL_KS);
		stdtrniparmv.setNUMSEC(0);

		stdtrniparmv.setIDINTERNOTERMTN(this.getTerminal());
		return trconsvalkalsttrni;
	}

	/**
	 * Función para invocar al servicio web y obtener su respuesta.
	 *
	 * @param contexto
	 *            Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	private EjecutarResult ejecutarWS(
			final Ejecutar.ITRCONSVALKSLSTTRNI contexto)
			throws NoControlableException {
		EjecutarResult respuesta = null;

		try {
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
					ConsultaCondicionListaPlazoServicio.class, contexto);
		} catch (final NoControlableException e) {
			throw new NoControlableException(
					"Error al invocar servicio web de consulta "
							+ "de condicion tipo Lista de plazo.", e);
		}
		return respuesta;
	}

	/**
	 * Función que valida que la respuesta Bansefi contenga un objeto con los
	 * datos del alta de realción.
	 *
	 * @param response
	 *            Respuesta Bansefi con datos del alta
	 * @return <code>true</code> si la respuesta Bansefi contiene los datos de
	 *         alta
	 */
	private Boolean verificaRespuesta(final ResponseBansefi response) {
		return (response != null
				&& response.getOTRCONSVALKSLSTTRNO() != null
				&& response.getOTRCONSVALKSLSTTRNO().getSTDTRNMSJPARMV() != null
				&& !response.getOTRCONSVALKSLSTTRNO().getSTDTRNMSJPARMV().isEmpty()
				&& response.getOTRCONSVALKSLSTTRNO().getSTDTRNOPARMV() != null
				&& response.getOTRCONSVALKSLSTTRNO().getTRCONSVALKSLSTEVTZ() != null);
	}

}
