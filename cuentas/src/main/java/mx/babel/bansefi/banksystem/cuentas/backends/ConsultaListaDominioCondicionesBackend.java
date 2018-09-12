package mx.babel.bansefi.banksystem.cuentas.backends;

import java.util.ArrayList;
import java.util.List;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.cuentas.beans.DominioCondicionBean;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultalistadominiocondiciones.ConsultaListaDominioCondicionesServicio;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultalistadominiocondiciones.Ejecutar;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultalistadominiocondiciones.Ejecutar.ITRLISTADOMPKTRNI.STDTRNIPARMV;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultalistadominiocondiciones.EjecutarResult;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultalistadominiocondiciones.ResponseBansefi;
import mx.babel.bansefi.banksystem.cuentas.wrappers.ConsultaListaDominioCondicionesWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author joseluis.pena
 *
 */
@Component
public class ConsultaListaDominioCondicionesBackend extends BackEndBean{

	private static final long serialVersionUID = 6564921993348101257L;

	@Autowired
	ConsultaListaDominioCondicionesWrapper consultaListaDominioCondicionesWrapper;

    @Autowired
    ServicioWebUtils servicioWebUtils;

	public List<DominioCondicionBean> ejecutarTRN(final String idParmCd){
		final Ejecutar.ITRLISTADOMPKTRNI request = initPeticion(idParmCd);

		final EjecutarResult respuesta = ejecutarWS(request);

		try{
            super.verificaRespuesta(respuesta);
        }catch (final ControlableException ce){
            if (ce.getRtncod() != RETURN_COD_SIN_DATOS){
                throw ce;
            }else{
                return new ArrayList<DominioCondicionBean>();
            }
        }
		
		return getCatalogo(respuesta.getResponseBansefi());
	}

	private List<DominioCondicionBean> getCatalogo(final ResponseBansefi response){
	    List<DominioCondicionBean> respuesta = new ArrayList<>();
		
		if(verificaRespuesta(response)){
		    respuesta = consultaListaDominioCondicionesWrapper.wrappCatalogoLista(
		            response.getOTRLISTADOMPKTRNO().getTRLISTADOMPKEVTZ().getPKDOMINIOV());
		}
		return respuesta;
	}

	/**
	 * Función que inicializa el objeto para realizar la petición al servicio web.
	 *
	 * @return Objeto a ser enviado al servicio web.
	 */
	private Ejecutar.ITRLISTADOMPKTRNI initPeticion(final String idParmCd){
		final Ejecutar.ITRLISTADOMPKTRNI peticion = new Ejecutar.ITRLISTADOMPKTRNI();

		super.initialize(peticion);
		peticion.setSCROLLABLEOCCURS(50);
		peticion.getTRLISTADOMPKEVTY().getPKDOMPARMCDP().setIDPARMCD(idParmCd);

        final STDTRNIPARMV stdtrniparmv = peticion.getSTDTRNIPARMV();
        stdtrniparmv.setCODTX(CodTxConstants.COD_TX_TR_LISTA_DOM_PK_TRN);

        stdtrniparmv.setIDINTERNOTERMTN(this.getTerminal());
		return peticion;
	}

	/**
	 * Función para invocar al servicio web y obtener su respuesta.
	 *
	 * @param contexto Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	private EjecutarResult ejecutarWS(final Ejecutar.ITRLISTADOMPKTRNI contexto)
			throws NoControlableException{
		EjecutarResult respuesta = null;

		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
					ConsultaListaDominioCondicionesServicio.class, contexto);
		}catch(final NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de consulta "
					+ "de catalogo Lista.", e);
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
		if(response != null && response.getOTRLISTADOMPKTRNO() != null &&
			response.getOTRLISTADOMPKTRNO().getTRLISTADOMPKEVTZ()!= null &&
			response.getOTRLISTADOMPKTRNO().getTRLISTADOMPKEVTZ().getPKDOMINIOV() != null &&
			!response.getOTRLISTADOMPKTRNO().getTRLISTADOMPKEVTZ().getPKDOMINIOV().isEmpty()){
			noNulo = true;
		}
		return noNulo;
	}

}
