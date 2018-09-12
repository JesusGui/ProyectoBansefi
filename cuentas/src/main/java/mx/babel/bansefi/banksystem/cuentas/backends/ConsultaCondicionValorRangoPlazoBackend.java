package mx.babel.bansefi.banksystem.cuentas.backends;

import java.util.Date;

import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.productossimples.condiciones.CondicionValorRangoBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.utils.IntegerToDateConverter;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultacondicionvalorrangoplazo.ConsultaCondicionValorRangoPlazoServicio;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultacondicionvalorrangoplazo.Ejecutar;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultacondicionvalorrangoplazo.Ejecutar.ITRCONSVALKSVRGTRNI.STDTRNIPARMV;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultacondicionvalorrangoplazo.Ejecutar.ITRCONSVALKSVRGTRNI.TRCONSVALKSVRGEVTY.KSCDSBP;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultacondicionvalorrangoplazo.EjecutarResult;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultacondicionvalorrangoplazo.ResponseBansefi;
import mx.babel.bansefi.banksystem.cuentas.wrappers.ConsultaCondicionValorRangoWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author joseluis.pena
 *
 */
@Component
public class ConsultaCondicionValorRangoPlazoBackend extends BackEndBean{

	private static final long serialVersionUID = -538461368543916162L;

	@Autowired
    ConsultaCondicionValorRangoWrapper consultaCondicionValorRangoWrapper;

    @Autowired
    ServicioWebUtils servicioWebUtils;

	public CondicionValorRangoBean ejecutarTRN(final long numCuenta, final int numSubAc, final String idPds, final CondicionValorRangoBean condicion){

		final Ejecutar.ITRCONSVALKSVRGTRNI trconsvalksvrgtrni = initPeticion(numCuenta, numSubAc, idPds, condicion.getClave(), condicion.getFechaInicioValidez());

		final EjecutarResult respuesta = ejecutarWS(trconsvalksvrgtrni);

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
	private CondicionValorRangoBean getCondiciones(final ResponseBansefi response){
		CondicionValorRangoBean condicion = null;
		if(verificaRespuesta(response)){
		    condicion = consultaCondicionValorRangoWrapper
		            .wrappCondicionValorRangoPlazo(response.getOTRCONSVALKSVRGTRNO().getTRCONSVALKSVRGEVTZ());
		}
		return condicion;
	}

	/**
	 * Función que inicializa el objeto para realizar la petición al servicio web.
	 *
	 * @return Objeto a ser enviado al servicio web.
	 */
	private Ejecutar.ITRCONSVALKSVRGTRNI initPeticion(final long numCuenta, final int numSubAc, final String idPds,
	        final String idParmCd, final Date fechaInicio){
		final Ejecutar.ITRCONSVALKSVRGTRNI trconsvalksvrgtrni = new Ejecutar.ITRCONSVALKSVRGTRNI();

		super.initialize(trconsvalksvrgtrni);
        final IntegerToDateConverter itdConverter = new IntegerToDateConverter();

		final KSCDSBP kscdacp = trconsvalksvrgtrni.getTRCONSVALKSVRGEVTY().getKSCDSBP();
		kscdacp.setCODNRBEEN(this.getEntidad());

		kscdacp.setNUMSECAC(numCuenta);

		kscdacp.setNUMSUBAC(numSubAc);

		kscdacp.setIDPDS(idPds);

        kscdacp.setIDPARMCD(idParmCd);

        kscdacp.setFECHAINICVAL(itdConverter.convertFrom(fechaInicio));

        final STDTRNIPARMV stdtrniparmv = trconsvalksvrgtrni.getSTDTRNIPARMV();
        stdtrniparmv.setCODTX(CodTxConstants.COD_TX_TR_CONS_VAL_KS);
        stdtrniparmv.setNUMSEC(0);

        stdtrniparmv.setIDINTERNOTERMTN(this.getTerminal());
		return trconsvalksvrgtrni;
	}

	/**
	 * Función para invocar al servicio web y obtener su respuesta.
	 *
	 * @param contexto Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	private EjecutarResult ejecutarWS(final Ejecutar.ITRCONSVALKSVRGTRNI contexto)
			throws NoControlableException{
		EjecutarResult respuesta = null;

		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
					ConsultaCondicionValorRangoPlazoServicio.class, contexto);
		}catch(final NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de consulta "
					+ "de condicion tipo Valor-Rango Plazo.", e);
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
		if(response != null && response.getOTRCONSVALKSVRGTRNO() != null &&
				response.getOTRCONSVALKSVRGTRNO().getTRCONSVALKSVRGEVTZ() != null &&
				response.getOTRCONSVALKSVRGTRNO().getTRCONSVALKSVRGEVTZ().getKSCOMUNV() != null &&
				response.getOTRCONSVALKSVRGTRNO().getTRCONSVALKSVRGEVTZ().getKSESTRCTVRNGV()!= null){
			noNulo = true;
		}
		return noNulo;
	}

}
