package mx.babel.bansefi.banksystem.cuentas.backends;

import java.io.Serializable;

import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.productossimples.condiciones.CondicionInteresBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultacondicioninteres.ConsultaCondicionInteresServicio;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultacondicioninteres.Ejecutar;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultacondicioninteres.Ejecutar.ITRCONSVALKAINTTRNI.STDTRNIPARMV;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultacondicioninteres.Ejecutar.ITRCONSVALKAINTTRNI.TRCONSVALKAINTEVTY.KACDACP;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultacondicioninteres.EjecutarResult;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultacondicioninteres.ResponseBansefi;
import mx.babel.bansefi.banksystem.cuentas.wrappers.ConsultaCondicionInteresWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/** Backend para la consulta de condiciones tipo interes.
 * @author joseluis.pena
 *
 */
@Component
public class ConsultaCondicionInteresBackend extends BackEndBean implements
		Serializable {

	private static final long serialVersionUID = -721747359313753325L;

	@Autowired
	private ConsultaCondicionInteresWrapper consultaCondicionInteresWrapper;

	@Autowired
	private ServicioWebUtils servicioWebUtils;

	public CondicionInteresBean ejecutarTRN(final long numCuenta, final String idPds, final String idParmCd){
		
		final Ejecutar.ITRCONSVALKAINTTRNI trconsvalkacmstrni = initPeticion(numCuenta, idPds, idParmCd);

		final EjecutarResult respuesta = ejecutarWS(trconsvalkacmstrni);

		super.verificaRespuesta(respuesta);
		
		return getCondiciones(respuesta.getResponseBansefi());
	}

	private CondicionInteresBean getCondiciones(final ResponseBansefi response){
        
		CondicionInteresBean condicion = null;
		if (verificaRespuesta(response)) {
			condicion = consultaCondicionInteresWrapper
					.wrappCondicionInteres(response.getOTRCONSVALKAINTTRNO()
							.getTRCONSVALKAINTEVTZ());
		}
		return condicion;
	}

	/**
	 * Función que inicializa el objeto para realizar la petición al servicio
	 * web.
	 *
	 * @return Objeto a ser enviado al servicio web.
	 */
	private Ejecutar.ITRCONSVALKAINTTRNI initPeticion(final long numCuenta, final String idPds, final String idParmCd) {
		final Ejecutar.ITRCONSVALKAINTTRNI trconsvalkainttrni = new Ejecutar.ITRCONSVALKAINTTRNI();

		super.initialize(trconsvalkainttrni);

		final KACDACP kacdacp = trconsvalkainttrni.getTRCONSVALKAINTEVTY()
				.getKACDACP();
		kacdacp.setCODNRBEEN(this.getEntidad());
		kacdacp.setNUMSECAC(numCuenta);
		kacdacp.setIDPDS(idPds);
		kacdacp.setIDPARMCD(idParmCd);

		final STDTRNIPARMV stdtrniparmv = trconsvalkainttrni.getSTDTRNIPARMV();
		stdtrniparmv.setCODTX(CodTxConstants.COD_TX_TR_CONS_VAL_KA);
		stdtrniparmv.setNUMSEC(0);

		stdtrniparmv.setIDINTERNOTERMTN(this.getTerminal());
		return trconsvalkainttrni;
	}

	/**
	 * Función para invocar al servicio web y obtener su respuesta.
	 *
	 * @param contexto
	 *            Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	private EjecutarResult ejecutarWS(
			final Ejecutar.ITRCONSVALKAINTTRNI contexto)
			throws NoControlableException {
		EjecutarResult respuesta = null;

		try {
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
					ConsultaCondicionInteresServicio.class, contexto);
		} catch (final NoControlableException e) {
			throw new NoControlableException(
					"Error al invocar servicio web de consulta "
							+ " de condicion tipo Interes.", e);
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
				&& response.getOTRCONSVALKAINTTRNO() != null
				&& response.getOTRCONSVALKAINTTRNO().getTRCONSVALKAINTEVTZ() != null
				&& response.getOTRCONSVALKAINTTRNO().getTRCONSVALKAINTEVTZ()
						.getKACOMUNV() != null
				&& response.getOTRCONSVALKAINTTRNO().getTRCONSVALKAINTEVTZ()
						.getKAESTRCTINT1V() != null
				&& (response.getOTRCONSVALKAINTTRNO().getTRCONSVALKAINTEVTZ()
						.getKAESTRCTINTFIJV() != null || response
						.getOTRCONSVALKAINTTRNO().getTRCONSVALKAINTEVTZ()
						.getKAESTRCTINTVARV() != null)) {
			noNulo = true;
		}
		return noNulo;
	}

}
