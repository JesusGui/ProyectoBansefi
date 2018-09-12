package mx.babel.bansefi.banksystem.cuentas.backends;

import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.domain.CuentaBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.cuentas.beans.ConsultaAnulacionCuentaBean;
import mx.babel.bansefi.banksystem.cuentas.webservices.anularconstitucionacuerdo.AnularConstitucionAcuerdoServicio;
import mx.babel.bansefi.banksystem.cuentas.webservices.anularconstitucionacuerdo.Ejecutar;
import mx.babel.bansefi.banksystem.cuentas.webservices.anularconstitucionacuerdo.Ejecutar.ITRCONSUANULARACTRNI.STDTRNIPARMV;
import mx.babel.bansefi.banksystem.cuentas.webservices.anularconstitucionacuerdo.Ejecutar.ITRCONSUANULARACTRNI.TRCONSUANULARACEVTY.ACACP;
import mx.babel.bansefi.banksystem.cuentas.webservices.anularconstitucionacuerdo.EjecutarResult;
import mx.babel.bansefi.banksystem.cuentas.webservices.anularconstitucionacuerdo.ResponseBansefi;
import mx.babel.bansefi.banksystem.cuentas.wrappers.ConsultaAnulacionCuentaWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author joseluis.pena
 * 
 */
@Component
public class AnularConstitucionAcuerdoBackend extends BackEndBean {

	private static final long serialVersionUID = 6564921993348101257L;

	@Autowired
	ServicioWebUtils servicioWebUtils;

	@Autowired
	ConsultaAnulacionCuentaWrapper consultaAnulacionCuentaWrapper;
	
	public Boolean ejecutarTRN(CuentaBean cuentaBean, Boolean porSolicitar){
		final Ejecutar.ITRCONSUANULARACTRNI request = initPeticion(cuentaBean, porSolicitar);

		final EjecutarResult respuesta = ejecutarWS(request);
		
		super.verificaRespuesta(respuesta);
		
		return respuesta.getResponseBansefi().getOTRCONSUANULARACTRNO().getRTRNCD() == 1;
	}

	public ConsultaAnulacionCuentaBean ejecutarTRN(CuentaBean cuentaBean){
		final Ejecutar.ITRCONSUANULARACTRNI request = initPeticion(cuentaBean, false);

		final EjecutarResult respuesta = ejecutarWS(request);
		
		super.verificaRespuesta(respuesta);
		
		return consultaAnulacionCuenta(respuesta.getResponseBansefi());
	}

	/**
	 * Funcion que recibe la respuesta de la TRN y Wrappea a un objeto propio
	 * del sistema
	 * 
	 * @param response
	 * @return ContadoresCentroBean
	 */
	private ConsultaAnulacionCuentaBean consultaAnulacionCuenta(ResponseBansefi response){
		ConsultaAnulacionCuentaBean consultaAnulacionCuentaBean = null;		
		if (verificaRespuestaCliente(response)) {
			// Se recuperan y wrappean los datos de la cuenta
			consultaAnulacionCuentaBean = consultaAnulacionCuentaWrapper
					.wrapperConsultaAnulacionCuenta(response
							.getOTRCONSUANULARACTRNO().getTRCONSUANULARACEVTZ());
		}

		return consultaAnulacionCuentaBean;
	}

	/**
	 * Función que inicializa el objeto para realizar la petición al servicio
	 * web.
	 * 
	 * @return Objeto a ser enviado al servicio web.
	 */
	private Ejecutar.ITRCONSUANULARACTRNI initPeticion(CuentaBean cuentaBean, Boolean porSolicitar) {
		final Ejecutar.ITRCONSUANULARACTRNI peticion = new Ejecutar.ITRCONSUANULARACTRNI();

		super.initialize(peticion);

		final ACACP acacp = peticion.getTRCONSUANULARACEVTY().getACACP();
		acacp.setCODNRBEEN(this.getEntidad());
		acacp.setNUMSECAC(cuentaBean.getNumeroCuenta());
		if(!porSolicitar){
			acacp.setCODCENTUO(cuentaBean.getCentro());
		}

		peticion.getTRCONSUANULARACEVTY().getCTXV()
				.setCODTX(CodTxConstants.COD_TX_TR_CONSTI_TOTAL_AC_TRN);

		final STDTRNIPARMV stdtrniparmv = peticion.getSTDTRNIPARMV();
		stdtrniparmv.setCODTX(CodTxConstants.COD_TX_TR_CONSU_ANULAR_AC_TRN);
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
			final Ejecutar.ITRCONSUANULARACTRNI contexto)
			throws NoControlableException {
		EjecutarResult respuesta = null;
		try {
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
					AnularConstitucionAcuerdoServicio.class, contexto);
		} catch (final NoControlableException e) {
			throw new NoControlableException(
					"Error al invocar servicio web de anulacion "
							+ " de constitucion de acuerdo.", e);
		}
		return respuesta;
	}

	/**
	 * Función que valida que la respuesta Bansefi contenga un objeto con los
	 * datos de la consulta.
	 * 
	 * @param response
	 *            Respuesta Bansefi con datos de la consulta
	 * @return <code>true</code> si la respuesta Bansefi contiene elemento no
	 *         nulos
	 */
	private Boolean verificaRespuestaCliente(ResponseBansefi response) {
		Boolean noNulo = false;
		if (response != null
				&& response.getOTRCONSUANULARACTRNO() != null
				&& response.getOTRCONSUANULARACTRNO().getTRCONSUANULARACEVTZ() != null
				&& response.getOTRCONSUANULARACTRNO().getTRCONSUANULARACEVTZ()
						.getACVAFAPUNTESV() != null) {
			noNulo = true;
		}
		return noNulo;
	}

}
