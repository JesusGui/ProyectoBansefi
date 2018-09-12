package mx.babel.bansefi.banksystem.cuentas.backends;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.cuentas.beans.DatosCondicionTarifaBean;
import mx.babel.bansefi.banksystem.cuentas.beans.TarifaBean;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultadatoscondiciontarifa.ConsultaDatosCondicionTarifaServicio;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultadatoscondiciontarifa.Ejecutar;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultadatoscondiciontarifa.Ejecutar.ITRKPCONSCONDICIONTRN.STDTRNIPARMV;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultadatoscondiciontarifa.Ejecutar.ITRKPCONSCONDICIONTRN.TRKPCONSCONDICIONEVT.KPCODCLAVEV;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultadatoscondiciontarifa.Ejecutar.ITRKPCONSCONDICIONTRN.TRKPCONSCONDICIONEVT.TVTRFAPDVP;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultadatoscondiciontarifa.EjecutarResult;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultadatoscondiciontarifa.ResponseBansefi;
import mx.babel.bansefi.banksystem.cuentas.wrappers.ConsultaDatosCondicionTarifaWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author joseluis.pena
 *
 */
@Component
public class ConsultaDatosCondicionTarifaBackend extends BackEndBean {

	private static final long serialVersionUID = 6564921993348101257L;

	@Autowired
	ConsultaDatosCondicionTarifaWrapper consultaDatosCondicionTarifaWrapper;

	@Autowired
	ServicioWebUtils servicioWebUtils;

	public DatosCondicionTarifaBean ejecutarTRN(final String idPds, final String idParmcd,
			final String idCcps, final TarifaBean tarifa) {

		final Ejecutar.ITRKPCONSCONDICIONTRN request = initPeticion(idPds,
				idParmcd, idCcps, tarifa);

		final EjecutarResult respuesta = ejecutarWS(request);

		try {
			super.verificaRespuesta(respuesta);
		} catch (final ControlableException ce) {
			if (ce.getRtncod() != RETURN_COD_SIN_DATOS) {
				throw ce;
			} else {
				return null;
			}
		}

		return getRespuesta(respuesta.getResponseBansefi());
	}

	private DatosCondicionTarifaBean getRespuesta(final ResponseBansefi response) {
	    DatosCondicionTarifaBean respuesta = null;
		if (verificaRespuesta(response)) {
			respuesta = consultaDatosCondicionTarifaWrapper
					.wrappDatosCondicionTarifa(response
							.getOTRKPCONSCONDICIONTRN()
							.getTRKPCONSCONDICIONEVT().getKPCDPDE());
			obtenerNombreProducto(response, respuesta);
		}
		return respuesta;
	}

	/**
	 * Método privado que obtiene el nombre del producto simple. Este método fue
	 * creado con la finalidad de mostrar la descripción del ID_PDS en la vista:
	 * consultaDetAmpComisionLiquidacion.xhtml exclusivamente.
	 *
	 * @author omar.marquez
	 * @param response
	 * @param respuesta
	 */
	private void obtenerNombreProducto(final ResponseBansefi response,
			final DatosCondicionTarifaBean respuesta) {
		if (response.getOTRKPCONSCONDICIONTRN().getTRKPCONSCONDICIONEVT()
				.getTSCCPSE() != null
				&& response.getOTRKPCONSCONDICIONTRN()
						.getTRKPCONSCONDICIONEVT().getTSCCPSE().getNOMBCCPS() != null) {
			respuesta.setNomProductoSimple(response.getOTRKPCONSCONDICIONTRN()
					.getTRKPCONSCONDICIONEVT().getTSCCPSE().getNOMBCCPS()
					.trim());
		}
	}

	/**
	 * Función que inicializa el objeto para realizar la petición al servicio
	 * web.
	 *
	 * @return Objeto a ser enviado al servicio web.
	 */
	private Ejecutar.ITRKPCONSCONDICIONTRN initPeticion(final String idPds,
			final String idParmcd, final String idCcps, final TarifaBean tarifa) {
		final Ejecutar.ITRKPCONSCONDICIONTRN peticion = new Ejecutar.ITRKPCONSCONDICIONTRN();

		super.initialize(peticion);
		final KPCODCLAVEV kpcodclavev = peticion.getTRKPCONSCONDICIONEVT()
				.getKPCODCLAVEV();
		kpcodclavev.setCODNRBEEN(this.getEntidad());
		kpcodclavev.setIDPDS(idPds);
		kpcodclavev.setIDCCPS(idCcps);
		kpcodclavev.setIDPARMCD(idParmcd);
		// TODO fecha a huevo
		peticion.getTRKPCONSCONDICIONEVT().getKPFECHAV()
				.setFECHAENT(this.getFechaSistemaInteger());

		final TVTRFAPDVP tvtrfapdvp = peticion.getTRKPCONSCONDICIONEVT()
				.getTVTRFAPDVP();
		tvtrfapdvp.setCODNRBEEN(this.getEntidad());
		tvtrfapdvp.setCODLINEA(tarifa.getLinea());
		tvtrfapdvp.setIDGRPPD(tarifa.getGrupo());
		tvtrfapdvp.setIDPDV(tarifa.getProducto());
		tvtrfapdvp.setIDTRFAPDV(tarifa.getId());

		final STDTRNIPARMV stdtrniparmv = peticion.getSTDTRNIPARMV();
		// TODO poner el real
		stdtrniparmv.setCODTX(CodTxConstants.COD_TX_TR_KP_CONS_CONDICION_TRN);
		stdtrniparmv.setIDINTERNOTERMTN(this.getTerminal());
		return peticion;
	}

	/**
	 * Función para invocar al servicio web y obtener su respuesta.
	 *
	 * @param contexto
	 *            Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	private EjecutarResult ejecutarWS(
			final Ejecutar.ITRKPCONSCONDICIONTRN contexto) {
		EjecutarResult respuesta = null;

		try {
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
					ConsultaDatosCondicionTarifaServicio.class, contexto);
		} catch (final NoControlableException e) {
			throw new NoControlableException(
					"Error al invocar servicio web de consulta "
							+ "de datos de condicion de tarifa.", e);
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
				&& response.getOTRKPCONSCONDICIONTRN() != null
				&& response.getOTRKPCONSCONDICIONTRN()
						.getTRKPCONSCONDICIONEVT() != null
				&& response.getOTRKPCONSCONDICIONTRN()
						.getTRKPCONSCONDICIONEVT().getKPCDPDE() != null) {
			noNulo = true;
		}
		return noNulo;
	}

}