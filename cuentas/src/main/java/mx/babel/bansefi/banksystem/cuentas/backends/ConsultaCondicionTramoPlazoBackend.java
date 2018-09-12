package mx.babel.bansefi.banksystem.cuentas.backends;

import java.util.Date;

import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.productossimples.condiciones.CondicionTramoBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.utils.IntegerToDateConverter;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultacondiciontramoplazo.ConsultaCondicionTramoPlazoServicio;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultacondiciontramoplazo.Ejecutar;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultacondiciontramoplazo.Ejecutar.ITRCONSVALKSMTZTRNI.STDTRNIPARMV;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultacondiciontramoplazo.Ejecutar.ITRCONSVALKSMTZTRNI.TRCONSVALKSMTZEVTY.KSCDSBP;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultacondiciontramoplazo.EjecutarResult;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultacondiciontramoplazo.ResponseBansefi;
import mx.babel.bansefi.banksystem.cuentas.wrappers.ConsultaCondicionTramoWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author joseluis.pena
 *
 */
@Component
public class ConsultaCondicionTramoPlazoBackend extends BackEndBean{

	private static final long serialVersionUID = -7439407145580929274L;

	@Autowired
    ConsultaCondicionTramoWrapper consultaCondicionTramoWrapper;

	@Autowired
	ServicioWebUtils servicioWebUtils;

	public CondicionTramoBean ejecutarTRN(final long numCuenta, final int numSubAc,final String idPds, final CondicionTramoBean condicion){

		final Ejecutar.ITRCONSVALKSMTZTRNI trconsvalkamtztrni = initPeticion(numCuenta, numSubAc,
		        idPds, condicion.getClave(), condicion.getFechaInicioValidez());

		final EjecutarResult respuesta = ejecutarWS(trconsvalkamtztrni);

		super.verificaRespuesta(respuesta);

		return getCondiciones(respuesta.getResponseBansefi());
	}

	/**
	 * Función encargada de obtener el cliente a partir de la respuesta del servicio web
	 *
	 * @param idInterno El id interno de la persona moral
	 * @param response El objeto de reultado enviado por el servicio web.
	 * @return
	 */
	private CondicionTramoBean getCondiciones(final ResponseBansefi response){
		CondicionTramoBean condicion = null;
		if(verificaRespuesta(response)){
		    condicion = consultaCondicionTramoWrapper
		            .wrappCondicionTramoPlazo(response.getOTRCONSVALKSMTZTRNO().getTRCONSVALKSMTZEVTZ());
		}
		return condicion;
	}

	/**
	 * Función que inicializa el objeto para realizar la petición al servicio web.
	 *
	 * @return Objeto a ser enviado al servicio web.
	 */
	private Ejecutar.ITRCONSVALKSMTZTRNI initPeticion(final long numCuenta, final int numSubAc, final String idPds,
	        final String idParmCd, final Date fechaInicio){
		final Ejecutar.ITRCONSVALKSMTZTRNI trconsvalkamtztrni = new Ejecutar.ITRCONSVALKSMTZTRNI();

		super.initialize(trconsvalkamtztrni);
        final IntegerToDateConverter itdConverter = new IntegerToDateConverter();

		final KSCDSBP kscdsbp = trconsvalkamtztrni.getTRCONSVALKSMTZEVTY().getKSCDSBP();
		kscdsbp.setCODNRBEEN(this.getEntidad());

		kscdsbp.setNUMSECAC(numCuenta);

        kscdsbp.setNUMSUBAC(numSubAc);

		kscdsbp.setIDPDS(idPds);

		kscdsbp.setIDPARMCD(idParmCd);

        kscdsbp.setFECHAINICVAL(itdConverter.convertFrom(fechaInicio));

        final STDTRNIPARMV stdtrniparmv = trconsvalkamtztrni.getSTDTRNIPARMV();
        stdtrniparmv.setCODTX(CodTxConstants.COD_TX_TR_CONS_VAL_KS);
        stdtrniparmv.setNUMSEC(0);

        stdtrniparmv.setIDINTERNOTERMTN(this.getTerminal());
		return trconsvalkamtztrni;
	}

	/**
	 * Función para invocar al servicio web y obtener su respuesta.
	 *
	 * @param contexto Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	private EjecutarResult ejecutarWS(final Ejecutar.ITRCONSVALKSMTZTRNI contexto)
			throws NoControlableException{
		EjecutarResult respuesta = null;

		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(ConsultaCondicionTramoPlazoServicio.class, contexto);
		}catch(final NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de consulta "
					+ "de condicion tipo Tramo Plazo.", e);
		}
		return respuesta;
	}

	/**
	 * Función que valida que la respuesta Bansefi contenga un objeto con los datos
	 * del alta de realción.
	 *
	 * @param response Respuesta Bansefi con datos del alta
	 * @return <code>true</code> si la respuesta Bansefi contiene los datos de alta
	 */
	private Boolean verificaRespuesta(final ResponseBansefi response){
		Boolean noNulo = false;
		if(response != null && response.getOTRCONSVALKSMTZTRNO() != null &&
				response.getOTRCONSVALKSMTZTRNO().getTRCONSVALKSMTZEVTZ() != null &&
				response.getOTRCONSVALKSMTZTRNO().getTRCONSVALKSMTZEVTZ().getKSCOMUNV() != null &&
				response.getOTRCONSVALKSMTZTRNO().getTRCONSVALKSMTZEVTZ().getKSMATRIZV() != null){
			noNulo = true;
		}
		return noNulo;
	}

}
