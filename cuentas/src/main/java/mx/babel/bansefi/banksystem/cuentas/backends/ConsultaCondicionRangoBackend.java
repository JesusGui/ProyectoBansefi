package mx.babel.bansefi.banksystem.cuentas.backends;

import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.productossimples.condiciones.CondicionRangoBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultacondicionrango.ConsultaCondicionRangoServicio;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultacondicionrango.Ejecutar;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultacondicionrango.Ejecutar.ITRCONSVALKARNGTRNI.STDTRNIPARMV;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultacondicionrango.Ejecutar.ITRCONSVALKARNGTRNI.TRCONSVALKARNGEVTY.KACDACP;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultacondicionrango.EjecutarResult;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultacondicionrango.ResponseBansefi;
import mx.babel.bansefi.banksystem.cuentas.wrappers.ConsultaCondicionRangoWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author joseluis.pena
 *
 */
@Component
public class ConsultaCondicionRangoBackend extends BackEndBean{

	private static final long serialVersionUID = 475639838627890503L;

	@Autowired
    ConsultaCondicionRangoWrapper consultaCondicionRangoWrapper;

	@Autowired
	ServicioWebUtils servicioWebUtils;

	public CondicionRangoBean ejecutarTRN(final long numCuenta, final String idPds, final String idParmCd){
		
		final Ejecutar.ITRCONSVALKARNGTRNI trconsvalkarngtrni = initPeticion(numCuenta,idPds, idParmCd);

		final EjecutarResult respuesta = ejecutarWS(trconsvalkarngtrni);

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
	private CondicionRangoBean getCondiciones(final ResponseBansefi response){
		CondicionRangoBean condicion = null;
        
		if(verificaRespuesta(response)){
		    condicion = consultaCondicionRangoWrapper
		            .wrappCondicionRango(response.getOTRCONSVALKARNGTRNO().getTRCONSVALKARNGEVTZ());
		}
		return condicion;
	}

	/**
	 * Función que inicializa el objeto para realizar la petición al servicio web.
	 *
	 * @return Objeto a ser enviado al servicio web.
	 */
	private Ejecutar.ITRCONSVALKARNGTRNI initPeticion(final long numCuenta, final String idPds, final String idParmCd){
		final Ejecutar.ITRCONSVALKARNGTRNI trconsvalkarngtrni = new Ejecutar.ITRCONSVALKARNGTRNI();

		super.initialize(trconsvalkarngtrni);

		final KACDACP kacdacp = trconsvalkarngtrni.getTRCONSVALKARNGEVTY().getKACDACP();
		kacdacp.setCODNRBEEN(this.getEntidad());
		//TODO sustituir por el numero de acuerdo
		kacdacp.setNUMSECAC(numCuenta);
        //TODO sustituir por datos del acuerdo
		kacdacp.setIDPDS(idPds);
        //TODO sustituir por datos del acuerdo
        kacdacp.setIDPARMCD(idParmCd);

        final STDTRNIPARMV stdtrniparmv = trconsvalkarngtrni.getSTDTRNIPARMV();
        stdtrniparmv.setCODTX(CodTxConstants.COD_TX_TR_CONS_VAL_KA);
        stdtrniparmv.setNUMSEC(0);
        stdtrniparmv.setIDINTERNOTERMTN(this.getTerminal());
		return trconsvalkarngtrni;
	}

	/**
	 * Función para invocar al servicio web y obtener su respuesta.
	 *
	 * @param contexto Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	private EjecutarResult ejecutarWS(final Ejecutar.ITRCONSVALKARNGTRNI contexto)
			throws NoControlableException{
		EjecutarResult respuesta = null;

		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
					ConsultaCondicionRangoServicio.class, contexto);
		}catch(final NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de consulta "
					+ "de condicion tipo Rango.", e);
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
		if(response != null && response.getOTRCONSVALKARNGTRNO() != null &&
				response.getOTRCONSVALKARNGTRNO().getTRCONSVALKARNGEVTZ() != null &&
				response.getOTRCONSVALKARNGTRNO().getTRCONSVALKARNGEVTZ().getKACOMUNV() != null &&
				response.getOTRCONSVALKARNGTRNO().getTRCONSVALKARNGEVTZ().getKAESTRCTRNGV() != null){
			noNulo = true;
		}
		return noNulo;
	}

}
