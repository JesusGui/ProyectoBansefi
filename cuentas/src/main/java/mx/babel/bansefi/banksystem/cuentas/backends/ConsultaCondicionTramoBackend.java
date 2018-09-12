package mx.babel.bansefi.banksystem.cuentas.backends;

import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.productossimples.condiciones.CondicionTramoBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultacondiciontramo.ConsultaCondicionTramoServicio;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultacondiciontramo.Ejecutar;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultacondiciontramo.Ejecutar.ITRCONSVALKAMTZTRNI.STDTRNIPARMV;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultacondiciontramo.Ejecutar.ITRCONSVALKAMTZTRNI.TRCONSVALKAMTZEVTY.KACDACP;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultacondiciontramo.EjecutarResult;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultacondiciontramo.ResponseBansefi;
import mx.babel.bansefi.banksystem.cuentas.wrappers.ConsultaCondicionTramoWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author joseluis.pena
 *
 */
@Component
public class ConsultaCondicionTramoBackend extends BackEndBean{

	private static final long serialVersionUID = -7439407145580929274L;

	@Autowired
    ConsultaCondicionTramoWrapper consultaCondicionTramoWrapper;

	@Autowired
	ServicioWebUtils servicioWebUtils;

	public CondicionTramoBean ejecutarTRN(final long numCuenta, final String idPds, final String idParmCd){
		
		final Ejecutar.ITRCONSVALKAMTZTRNI trconsvalkamtztrni = initPeticion(numCuenta,idPds,idParmCd);

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
		            .wrappCondicionTramo(response.getOTRCONSVALKAMTZTRNO().getTRCONSVALKAMTZEVTZ());
		}
		return condicion;
	}

	/**
	 * Función que inicializa el objeto para realizar la petición al servicio web.
	 *
	 * @return Objeto a ser enviado al servicio web.
	 */
	private Ejecutar.ITRCONSVALKAMTZTRNI initPeticion(final long numCuenta, final String idPds, final String idParmCd){
		final Ejecutar.ITRCONSVALKAMTZTRNI trconsvalkamtztrni = new Ejecutar.ITRCONSVALKAMTZTRNI();

		super.initialize(trconsvalkamtztrni);

		final KACDACP kacdacp = trconsvalkamtztrni.getTRCONSVALKAMTZEVTY().getKACDACP();
		kacdacp.setCODNRBEEN(this.getEntidad());

		kacdacp.setNUMSECAC(numCuenta);

		kacdacp.setIDPDS(idPds);

        kacdacp.setIDPARMCD(idParmCd);

        final STDTRNIPARMV stdtrniparmv = trconsvalkamtztrni.getSTDTRNIPARMV();
        stdtrniparmv.setCODTX(CodTxConstants.COD_TX_TR_CONS_VAL_KA);
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
	private EjecutarResult ejecutarWS(final Ejecutar.ITRCONSVALKAMTZTRNI contexto)
			throws NoControlableException{
		EjecutarResult respuesta = null;

		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(ConsultaCondicionTramoServicio.class, contexto);
		}catch(final NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de consulta "
					+ "de condicion tipo Tramo.", e);
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
		if(response != null && response.getOTRCONSVALKAMTZTRNO() != null &&
				response.getOTRCONSVALKAMTZTRNO().getTRCONSVALKAMTZEVTZ() != null &&
				response.getOTRCONSVALKAMTZTRNO().getTRCONSVALKAMTZEVTZ().getKACOMUNV() != null &&
				response.getOTRCONSVALKAMTZTRNO().getTRCONSVALKAMTZEVTZ().getKAMATRIZV() != null){
			noNulo = true;
		}
		return noNulo;
	}

}
