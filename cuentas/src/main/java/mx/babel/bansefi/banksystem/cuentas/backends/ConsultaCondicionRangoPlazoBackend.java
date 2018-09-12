package mx.babel.bansefi.banksystem.cuentas.backends;

import java.util.Date;

import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.productossimples.condiciones.CondicionRangoBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.utils.IntegerToDateConverter;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultacondicionrangoplazo.ConsultaCondicionRangoPlazoServicio;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultacondicionrangoplazo.Ejecutar;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultacondicionrangoplazo.Ejecutar.ITRCONSVALKSRNGTRNI.STDTRNIPARMV;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultacondicionrangoplazo.Ejecutar.ITRCONSVALKSRNGTRNI.TRCONSVALKSRNGEVTY.KSCDSBP;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultacondicionrangoplazo.EjecutarResult;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultacondicionrangoplazo.ResponseBansefi;
import mx.babel.bansefi.banksystem.cuentas.wrappers.ConsultaCondicionRangoWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author joseluis.pena
 *
 */
@Component
public class ConsultaCondicionRangoPlazoBackend extends BackEndBean{

	private static final long serialVersionUID = 475639838627890503L;

	@Autowired
    ConsultaCondicionRangoWrapper consultaCondicionRangoWrapper;

	@Autowired
	ServicioWebUtils servicioWebUtils;

	public CondicionRangoBean ejecutarTRN(final long numCuenta, final int numSubAc,final String idPds, final CondicionRangoBean condicion){

		final Ejecutar.ITRCONSVALKSRNGTRNI trconsvalksrngtrni = initPeticion(numCuenta, numSubAc,
		        idPds, condicion.getClave(), condicion.getFechaInicioValidez());

		final EjecutarResult respuesta = ejecutarWS(trconsvalksrngtrni);

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
		            .wrappCondicionRangoPlazo(response.getOTRCONSVALKSRNGTRNO().getTRCONSVALKSRNGEVTZ());
		}
		return condicion;
	}

	/**
	 * Función que inicializa el objeto para realizar la petición al servicio web.
	 *
	 * @return Objeto a ser enviado al servicio web.
	 */
	private Ejecutar.ITRCONSVALKSRNGTRNI initPeticion(final long numCuenta, final int numSubAc, final String idPds,
	        final String idParmCd, final Date fechaInicio){
		final Ejecutar.ITRCONSVALKSRNGTRNI trconsvalkarngtrni = new Ejecutar.ITRCONSVALKSRNGTRNI();

		super.initialize(trconsvalkarngtrni);
        final IntegerToDateConverter itdConverter = new IntegerToDateConverter();

		final KSCDSBP kscdsbp = trconsvalkarngtrni.getTRCONSVALKSRNGEVTY().getKSCDSBP();
		kscdsbp.setCODNRBEEN(this.getEntidad());
		//TODO sustituir por el numero de acuerdo
		kscdsbp.setNUMSECAC(numCuenta);
        //TODO sustituir por el numero de acuerdo
        kscdsbp.setNUMSUBAC(numSubAc);
        //TODO sustituir por datos del acuerdo
		kscdsbp.setIDPDS(idPds);
        //TODO sustituir por datos del acuerdo
		kscdsbp.setIDPARMCD(idParmCd);

		kscdsbp.setFECHAINICVAL(itdConverter.convertFrom(fechaInicio));

        final STDTRNIPARMV stdtrniparmv = trconsvalkarngtrni.getSTDTRNIPARMV();
        stdtrniparmv.setCODTX(CodTxConstants.COD_TX_TR_CONS_VAL_KS);
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
	private EjecutarResult ejecutarWS(final Ejecutar.ITRCONSVALKSRNGTRNI contexto)
			throws NoControlableException{
		EjecutarResult respuesta = null;

		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
					ConsultaCondicionRangoPlazoServicio.class, contexto);
		}catch(final NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de consulta "
					+ "de condicion tipo Rango Plazo.", e);
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
		if(response != null && response.getOTRCONSVALKSRNGTRNO() != null &&
				response.getOTRCONSVALKSRNGTRNO().getTRCONSVALKSRNGEVTZ() != null &&
				response.getOTRCONSVALKSRNGTRNO().getTRCONSVALKSRNGEVTZ().getKSCOMUNV() != null &&
				response.getOTRCONSVALKSRNGTRNO().getTRCONSVALKSRNGEVTZ().getKSESTRCTRNGV() != null){
			noNulo = true;
		}
		return noNulo;
	}

}
