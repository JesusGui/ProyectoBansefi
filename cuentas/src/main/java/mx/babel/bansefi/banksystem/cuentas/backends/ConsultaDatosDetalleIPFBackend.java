package mx.babel.bansefi.banksystem.cuentas.backends;

import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.DatosDetalleIPFBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultadatosdetalleipf.ConsultaDatosDetalleIPFServicio;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultadatosdetalleipf.Ejecutar;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultadatosdetalleipf.Ejecutar.ITRCONSIMPSCNINDIVTRN.STDTRNIPARMV;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultadatosdetalleipf.Ejecutar.ITRCONSIMPSCNINDIVTRN.TRCONSIMPSCNINDIVEVT.IPIMPSCNP;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultadatosdetalleipf.EjecutarResult;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultadatosdetalleipf.ResponseBansefi;
import mx.babel.bansefi.banksystem.cuentas.wrappers.ConsultaDatosDetalleIPFWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/** Backend para la consulta de condiciones tipo interes.
 * @author joseluis.pena
 *
 */
@Component
public class ConsultaDatosDetalleIPFBackend extends BackEndBean {


	@Autowired
	private ConsultaDatosDetalleIPFWrapper consultaDatosDetalleIPFWrapper;

	@Autowired
	private ServicioWebUtils servicioWebUtils;

	public DatosDetalleIPFBean ejecutarTRN(final long numCuenta, final int numSubAc){

		final Ejecutar.ITRCONSIMPSCNINDIVTRN itrconsimpscnindivtrn = initPeticion(numCuenta, numSubAc);

		final EjecutarResult respuesta = ejecutarWS(itrconsimpscnindivtrn);

		super.verificaRespuesta(respuesta);

		return getDatos(respuesta.getResponseBansefi());
	}

	private DatosDetalleIPFBean getDatos(final ResponseBansefi response){
	    DatosDetalleIPFBean datos = null;
		if (verificaRespuesta(response)) {
		    datos = consultaDatosDetalleIPFWrapper
					.wrappDatosDetalleIPF(response.getOTRCONSIMPSCNINDIVTRN()
							.getTRCONSIMPSCNINDIVEVT().getTRCONSIMPSCNINDIVEVT());
		}
		return datos;
	}

	/**
	 * Función que inicializa el objeto para realizar la petición al servicio
	 * web.
	 *
	 * @return Objeto a ser enviado al servicio web.
	 */
	private Ejecutar.ITRCONSIMPSCNINDIVTRN initPeticion(final long numCuenta, final int numSubAc){
		final Ejecutar.ITRCONSIMPSCNINDIVTRN itrconsimpscnindivtrn = new Ejecutar.ITRCONSIMPSCNINDIVTRN();

		super.initialize(itrconsimpscnindivtrn);

		itrconsimpscnindivtrn.setSCROLLABLEOCCURS(50);

		final IPIMPSCNP ipimpscnp = itrconsimpscnindivtrn.getTRCONSIMPSCNINDIVEVT().getIPIMPSCNP();
		ipimpscnp.setCODNRBEEN(this.getEntidad());
		ipimpscnp.setNUMSECAC(numCuenta);
		ipimpscnp.setNUMSUBAC(numSubAc);


		final STDTRNIPARMV stdtrniparmv = itrconsimpscnindivtrn.getSTDTRNIPARMV();
		stdtrniparmv.setCODTX(CodTxConstants.COD_TR_CONS_IMPSCN_INDIV_TRN);

		stdtrniparmv.setIDINTERNOTERMTN(this.getTerminal());
		return itrconsimpscnindivtrn;
	}

	/**
	 * Función para invocar al servicio web y obtener su respuesta.
	 *
	 * @param contexto
	 *            Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	private EjecutarResult ejecutarWS(
			final Ejecutar.ITRCONSIMPSCNINDIVTRN contexto)
			throws NoControlableException {
		EjecutarResult respuesta = null;

		try {
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
			        ConsultaDatosDetalleIPFServicio.class, contexto);
		} catch (final NoControlableException e) {
			throw new NoControlableException(
					"Error al invocar servicio web de consulta "
							+ " de datos de detalle de plazo.", e);
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
				&& response.getOTRCONSIMPSCNINDIVTRN() != null
				&& response.getOTRCONSIMPSCNINDIVTRN().getTRCONSIMPSCNINDIVEVT() != null
				&& response.getOTRCONSIMPSCNINDIVTRN().getTRCONSIMPSCNINDIVEVT()
						.getTRCONSIMPSCNINDIVEVT() != null) {
			noNulo = true;
		}
		return noNulo;
	}

}
