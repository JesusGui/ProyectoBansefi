package mx.babel.bansefi.banksystem.cuentas.backends;

import java.util.ArrayList;
import java.util.List;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.cuentas.beans.BaseCalculoCondicionesBean;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultalistabasecalculocondiciones.ConsultaListaBaseCalculoCondicionesServicio;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultalistabasecalculocondiciones.Ejecutar;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultalistabasecalculocondiciones.Ejecutar.ITRCONSLISTAPSPKPSPK.STDTRNIPARMV;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultalistabasecalculocondiciones.Ejecutar.ITRCONSLISTAPSPKPSPK.TRCONSLISTAPSPKPSPKE.PSPKPSRLPKPSP;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultalistabasecalculocondiciones.EjecutarResult;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultalistabasecalculocondiciones.ResponseBansefi;
import mx.babel.bansefi.banksystem.cuentas.wrappers.ConsultaListaBaseCalculoCondicionesWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author joseluis.pena
 *
 */
@Component
public class ConsultaListaBaseCalculoCondicionesBackend extends BackEndBean{

	private static final long serialVersionUID = 6564921993348101257L;

	@Autowired
	ConsultaListaBaseCalculoCondicionesWrapper consultaListaBaseCalculoCondicionesWrapper;

    @Autowired
    ServicioWebUtils servicioWebUtils;

	public List<BaseCalculoCondicionesBean> ejecutarTRN(final String idPds, final String idParmcd){
	    final Ejecutar.ITRCONSLISTAPSPKPSPK request = initPeticion(idPds, idParmcd);

		final EjecutarResult respuesta = ejecutarWS(request);
		
		try{
            super.verificaRespuesta(respuesta);
        }catch (final ControlableException ce){
            if (ce.getRtncod() != RETURN_COD_SIN_DATOS){
                throw ce;
            }else{
                return new ArrayList<BaseCalculoCondicionesBean>();
            }
        }
		
		return getRespuesta(idPds, idParmcd, respuesta.getResponseBansefi());
	}


	private List<BaseCalculoCondicionesBean> getRespuesta(final String idPds, final String idParmcd, final ResponseBansefi response){
	    List<BaseCalculoCondicionesBean> respuesta = null;		
		if(verificaRespuesta(response)){
		    respuesta = consultaListaBaseCalculoCondicionesWrapper.wrappListaBaseCalculoCondiciones(
		            response.getOTRCONSLISTAPSPKPSPK().getTRCONSLISTAPSPKPSPKE().getPSDATOSRLPKPSV());

		}
		return respuesta;
	}

	/**
	 * Función que inicializa el objeto para realizar la petición al servicio web.
	 *
	 * @return Objeto a ser enviado al servicio web.
	 */
	private Ejecutar.ITRCONSLISTAPSPKPSPK initPeticion(final String idPds, final String idParmcd){
		final Ejecutar.ITRCONSLISTAPSPKPSPK peticion = new Ejecutar.ITRCONSLISTAPSPKPSPK();

		super.initialize(peticion);
		
		peticion.setSCROLLABLEOCCURS(50);
		
		final PSPKPSRLPKPSP pspkpsrlpkpsp = peticion.getTRCONSLISTAPSPKPSPKE().getPSPKPSRLPKPSP();
		pspkpsrlpkpsp.setIDPDS1(idPds);
        pspkpsrlpkpsp.setIDPARMCD1(idParmcd);

        final STDTRNIPARMV stdtrniparmv = peticion.getSTDTRNIPARMV();
        //TODO poner el real
        stdtrniparmv.setCODTX(CodTxConstants.COD_TX_TR_CONS_LISTA_PSPK_PSPK_TRN);
        stdtrniparmv.setIDINTERNOTERMTN(this.getTerminal());
        
		return peticion;
	}

	/**
	 * Función para invocar al servicio web y obtener su respuesta.
	 *
	 * @param contexto Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	private EjecutarResult ejecutarWS(final Ejecutar.ITRCONSLISTAPSPKPSPK contexto)
			throws NoControlableException{
		EjecutarResult respuesta = null;

		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
					ConsultaListaBaseCalculoCondicionesServicio.class, contexto);
		}catch(final NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de consulta "
					+ "de lista de base de calculo de condiciones.", e);
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
		if(response != null && response.getOTRCONSLISTAPSPKPSPK() != null &&
			response.getOTRCONSLISTAPSPKPSPK().getTRCONSLISTAPSPKPSPKE()!= null &&
            response.getOTRCONSLISTAPSPKPSPK().getTRCONSLISTAPSPKPSPKE().getPSDATOSRLPKPSV()!= null &&
            !response.getOTRCONSLISTAPSPKPSPK().getTRCONSLISTAPSPKPSPKE().getPSDATOSRLPKPSV().isEmpty()){
			noNulo = true;
		}
		return noNulo;
	}

}
