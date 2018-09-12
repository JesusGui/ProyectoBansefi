package mx.babel.bansefi.banksystem.cuentas.backends;

import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.productossimples.condiciones.CondicionValorRangoBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultacondicionvalorrango.ConsultaCondicionValorRangoServicio;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultacondicionvalorrango.Ejecutar;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultacondicionvalorrango.Ejecutar.ITRCONSVALKAVRGTRNI.STDTRNIPARMV;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultacondicionvalorrango.Ejecutar.ITRCONSVALKAVRGTRNI.TRCONSVALKAVRGEVTY.KACDACP;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultacondicionvalorrango.EjecutarResult;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultacondicionvalorrango.ResponseBansefi;
import mx.babel.bansefi.banksystem.cuentas.wrappers.ConsultaCondicionValorRangoWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author joseluis.pena
 *
 */
@Component
public class ConsultaCondicionValorRangoBackend extends BackEndBean{

	private static final long serialVersionUID = -538461368543916162L;

	@Autowired
    ConsultaCondicionValorRangoWrapper consultaCondicionValorRangoWrapper;

    @Autowired
    ServicioWebUtils servicioWebUtils;

	public CondicionValorRangoBean ejecutarTRN(final long numCuenta, final String idPds, final String idParmCd){
		
		final Ejecutar.ITRCONSVALKAVRGTRNI trconsvalkavrgtrni = initPeticion(numCuenta, idPds, idParmCd);

		final EjecutarResult respuesta = ejecutarWS(trconsvalkavrgtrni);
		
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
		            .wrappCondicionValorRango(response.getOTRCONSVALKAVRGTRNO().getTRCONSVALKAVRGEVTZ());
		}
		
		if (condicion.getUnidades()==null) condicion.setUnidades("U");
		
		return condicion;
	}

	/**
	 * Función que inicializa el objeto para realizar la petición al servicio web.
	 *
	 * @return Objeto a ser enviado al servicio web.
	 */
	private Ejecutar.ITRCONSVALKAVRGTRNI initPeticion(final long numCuenta, final String idPds, final String idParmCd){
		final Ejecutar.ITRCONSVALKAVRGTRNI trconsvalkavrgtrni = new Ejecutar.ITRCONSVALKAVRGTRNI();

		super.initialize(trconsvalkavrgtrni);

		final KACDACP kacdacp = trconsvalkavrgtrni.getTRCONSVALKAVRGEVTY().getKACDACP();
		kacdacp.setCODNRBEEN(this.getEntidad());

		kacdacp.setNUMSECAC(numCuenta);

		kacdacp.setIDPDS(idPds);

        kacdacp.setIDPARMCD(idParmCd);

        final STDTRNIPARMV stdtrniparmv = trconsvalkavrgtrni.getSTDTRNIPARMV();
        stdtrniparmv.setCODTX(CodTxConstants.COD_TX_TR_CONS_VAL_KA);
        stdtrniparmv.setNUMSEC(0);

        stdtrniparmv.setIDINTERNOTERMTN(this.getTerminal());
		return trconsvalkavrgtrni;
	}

	/**
	 * Función para invocar al servicio web y obtener su respuesta.
	 *
	 * @param contexto Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	private EjecutarResult ejecutarWS(final Ejecutar.ITRCONSVALKAVRGTRNI contexto)
			throws NoControlableException{
		EjecutarResult respuesta = null;

		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
					ConsultaCondicionValorRangoServicio.class, contexto);
		}catch(final NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de consulta "
					+ "de condicion tipo Valor-Rango.", e);
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
		if(response != null && response.getOTRCONSVALKAVRGTRNO() != null &&
				response.getOTRCONSVALKAVRGTRNO().getTRCONSVALKAVRGEVTZ() != null &&
				response.getOTRCONSVALKAVRGTRNO().getTRCONSVALKAVRGEVTZ().getKACOMUNV() != null &&
				response.getOTRCONSVALKAVRGTRNO().getTRCONSVALKAVRGEVTZ().getKAESTRCTVRNGV()!= null){
			noNulo = true;
		}
		return noNulo;
	}

}
