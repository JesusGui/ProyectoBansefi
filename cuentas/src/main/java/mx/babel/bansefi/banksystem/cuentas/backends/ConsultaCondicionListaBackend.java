package mx.babel.bansefi.banksystem.cuentas.backends;

import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.productossimples.condiciones.CondicionListaBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultacondicionlista.ConsultaCondicionListaServicio;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultacondicionlista.Ejecutar;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultacondicionlista.Ejecutar.ITRCONSVALKALSTTRNI.STDTRNIPARMV;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultacondicionlista.Ejecutar.ITRCONSVALKALSTTRNI.TRCONSVALKALSTEVTY.KACDACP;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultacondicionlista.EjecutarResult;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultacondicionlista.ResponseBansefi;
import mx.babel.bansefi.banksystem.cuentas.wrappers.ConsultaCondicionListaWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author joseluis.pena
 *
 */
@Component
public class ConsultaCondicionListaBackend extends BackEndBean {

	private static final long serialVersionUID = 2193790806092068134L;

	@Autowired
	ConsultaCondicionListaWrapper consultaCondicionListaWrapper;

	@Autowired
	ServicioWebUtils servicioWebUtils;

	public CondicionListaBean ejecutarTRN(final long numCuenta, final String idPds, final String idParmCd){
		
		final Ejecutar.ITRCONSVALKALSTTRNI trconsvalkalsttrni = initPeticion(numCuenta, idPds, idParmCd);

		final EjecutarResult respuesta = ejecutarWS(trconsvalkalsttrni);

        super.verificaRespuesta(respuesta);
        
		return getCondiciones(respuesta.getResponseBansefi());
	}

	private CondicionListaBean getCondiciones(final ResponseBansefi response){
		CondicionListaBean condicion = null;
		if (verificaRespuesta(response)) {
			condicion = consultaCondicionListaWrapper
					.wrappCondicionLista(response.getOTRCONSVALKALSTTRNO()
							.getTRCONSVALKALSTEVTZ());
		}
		return condicion;
	}

	/**
	 * Función que inicializa el objeto para realizar la petición al servicio
	 * web.
	 *
	 * @return Objeto a ser enviado al servicio web.
	 */
	private Ejecutar.ITRCONSVALKALSTTRNI initPeticion(final long numCuenta, final String idPds, final String idParmCd) {
		final Ejecutar.ITRCONSVALKALSTTRNI trconsvalkalsttrni = new Ejecutar.ITRCONSVALKALSTTRNI();

		super.initialize(trconsvalkalsttrni);

		final KACDACP kacdacp = trconsvalkalsttrni.getTRCONSVALKALSTEVTY()
				.getKACDACP();
		kacdacp.setCODNRBEEN(this.getEntidad());
		// TODO sustituir por el numero de acuerdo
		kacdacp.setNUMSECAC(numCuenta);
		// TODO sustituir por datos del acuerdo
		kacdacp.setIDPDS(idPds);
		// TODO sustituir por datos del acuerdo
		kacdacp.setIDPARMCD(idParmCd);

		final STDTRNIPARMV stdtrniparmv = trconsvalkalsttrni.getSTDTRNIPARMV();
		stdtrniparmv.setCODTX(CodTxConstants.COD_TX_TR_CONS_VAL_KA);
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
			final Ejecutar.ITRCONSVALKALSTTRNI contexto)
			throws NoControlableException {
		EjecutarResult respuesta = null;

		try {
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
					ConsultaCondicionListaServicio.class, contexto);
		} catch (final NoControlableException e) {
			throw new NoControlableException(
					"Error al invocar servicio web de consulta "
							+ "de condicion tipo Lista.", e);
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
				&& response.getOTRCONSVALKALSTTRNO() != null
				&& response.getOTRCONSVALKALSTTRNO().getSTDTRNMSJPARMV() != null
				&& !response.getOTRCONSVALKALSTTRNO().getSTDTRNMSJPARMV().isEmpty()
				&& response.getOTRCONSVALKALSTTRNO().getSTDTRNOPARMV() != null
				&& response.getOTRCONSVALKALSTTRNO().getTRCONSVALKALSTEVTZ() != null);
	}

}
