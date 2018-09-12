package mx.babel.bansefi.banksystem.cuentas.backends;

import java.io.Serializable;
import java.util.Date;

import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.productossimples.condiciones.CondicionInteresBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.utils.IntegerToDateConverter;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultacondicioninteresplazo.ConsultaCondicionInteresPlazoServicio;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultacondicioninteresplazo.Ejecutar;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultacondicioninteresplazo.Ejecutar.ITRCONSVALKSINTTRNI.STDTRNIPARMV;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultacondicioninteresplazo.Ejecutar.ITRCONSVALKSINTTRNI.TRCONSVALKSINTEVTY.KSCDSBP;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultacondicioninteresplazo.EjecutarResult;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultacondicioninteresplazo.ResponseBansefi;
import mx.babel.bansefi.banksystem.cuentas.wrappers.ConsultaCondicionInteresWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/** Backend para la consulta de condiciones tipo interes.
 * @author joseluis.pena
 *
 */
@Component
public class ConsultaCondicionInteresPlazoBackend extends BackEndBean implements
		Serializable {

	private static final long serialVersionUID = -721747359313753325L;

	@Autowired
	private ConsultaCondicionInteresWrapper consultaCondicionInteresWrapper;

	@Autowired
	private ServicioWebUtils servicioWebUtils;

	public CondicionInteresBean ejecutarTRN(final long numCuenta, final int numSubAc,final String idPds, final CondicionInteresBean condicion){

		final Ejecutar.ITRCONSVALKSINTTRNI trconsvalkscmstrni = initPeticion(numCuenta, numSubAc,
                idPds, condicion.getClave(), condicion.getFechaInicioValidez());

		final EjecutarResult respuesta = ejecutarWS(trconsvalkscmstrni);

		super.verificaRespuesta(respuesta);

		return getCondiciones(respuesta.getResponseBansefi());
	}

	private CondicionInteresBean getCondiciones(final ResponseBansefi response){
		CondicionInteresBean condicion = null;
		if (verificaRespuesta(response)) {
			condicion = consultaCondicionInteresWrapper
					.wrappCondicionInteresPlazo(response.getOTRCONSVALKSINTTRNO()
							.getTRCONSVALKSINTEVTZ());
		}
		return condicion;
	}

	/**
	 * Función que inicializa el objeto para realizar la petición al servicio
	 * web.
	 *
	 * @return Objeto a ser enviado al servicio web.
	 */
	private Ejecutar.ITRCONSVALKSINTTRNI initPeticion(final long numCuenta, final int numSubAc, final String idPds,
            final String idParmCd, final Date fechaInicio){
		final Ejecutar.ITRCONSVALKSINTTRNI trconsvalksinttrni = new Ejecutar.ITRCONSVALKSINTTRNI();

		super.initialize(trconsvalksinttrni);
        final IntegerToDateConverter itdConverter = new IntegerToDateConverter();

		final KSCDSBP kscdsbp = trconsvalksinttrni.getTRCONSVALKSINTEVTY()
				.getKSCDSBP();
		kscdsbp.setCODNRBEEN(this.getEntidad());
		kscdsbp.setNUMSECAC(numCuenta);
		kscdsbp.setNUMSUBAC(numSubAc);
		kscdsbp.setIDPDS(idPds);
		kscdsbp.setIDPARMCD(idParmCd);

        kscdsbp.setFECHAINICVAL(itdConverter.convertFrom(fechaInicio));

		final STDTRNIPARMV stdtrniparmv = trconsvalksinttrni.getSTDTRNIPARMV();
		stdtrniparmv.setCODTX(CodTxConstants.COD_TX_TR_CONS_VAL_KS);
		stdtrniparmv.setNUMSEC(0);

		stdtrniparmv.setIDINTERNOTERMTN(this.getTerminal());
		return trconsvalksinttrni;
	}

	/**
	 * Función para invocar al servicio web y obtener su respuesta.
	 *
	 * @param contexto
	 *            Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	private EjecutarResult ejecutarWS(
			final Ejecutar.ITRCONSVALKSINTTRNI contexto)
			throws NoControlableException {
		EjecutarResult respuesta = null;

		try {
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
					ConsultaCondicionInteresPlazoServicio.class, contexto);
		} catch (final NoControlableException e) {
			throw new NoControlableException(
					"Error al invocar servicio web de consulta "
							+ " de condicion tipo Interes de plazo.", e);
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
		Boolean noNulo = false;
		if (response != null
				&& response.getOTRCONSVALKSINTTRNO() != null
				&& response.getOTRCONSVALKSINTTRNO().getTRCONSVALKSINTEVTZ() != null
				&& response.getOTRCONSVALKSINTTRNO().getTRCONSVALKSINTEVTZ()
						.getKSCOMUNV() != null
				&& response.getOTRCONSVALKSINTTRNO().getTRCONSVALKSINTEVTZ()
						.getKSESTRCTINT1V() != null
				&& (response.getOTRCONSVALKSINTTRNO().getTRCONSVALKSINTEVTZ()
						.getKSESTRCTINTFIJV() != null || response
						.getOTRCONSVALKSINTTRNO().getTRCONSVALKSINTEVTZ()
						.getKSESTRCTINTVARV() != null)) {
			noNulo = true;
		}
		return noNulo;
	}

}
