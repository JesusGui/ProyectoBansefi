package mx.babel.bansefi.banksystem.cuentas.backends;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mx.babel.arq.cache.annotation.Cache;
import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.constants.ConstantesFuncionales;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultanombrecondiciones.ConsultaNombreCondicionesServicio;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultanombrecondiciones.Ejecutar;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultanombrecondiciones.Ejecutar.ITRCONSGLOBALKPRLTS.STDTRNIPARMV;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultanombrecondiciones.Ejecutar.ITRCONSGLOBALKPRLTS.TRCONSGLOBALKPRLTSE.KPCDPDP;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultanombrecondiciones.EjecutarResult;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultanombrecondiciones.ResponseBansefi.OTRCONSGLOBALKPRLTS.TRCONSGLOBALKPRLTSE.TRCONSGLOBALKPRLTSE1;
import mx.babel.bansefi.banksystem.cuentas.wrappers.ConsultaDetalleTramoWrapper;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author joseluis.pena
 *
 */
@Component
public class ConsultaNombreCondicionesBackend extends BackEndBean{

	private static final long serialVersionUID = 6564921993348101257L;

	@Autowired
	ConsultaDetalleTramoWrapper consultaDetalleTramoWrapper;

    @Autowired
    ServicioWebUtils servicioWebUtils;

	@Cache
	public Map<String,String> ejecutarTRN(final String idPds, final String idCcps1){
	    List<TRCONSGLOBALKPRLTSE1> listaRespuesta = new ArrayList<>();
	    listaRespuesta = this.ejecutarPaginacion(idPds, idCcps1, listaRespuesta);
        return getResponse(listaRespuesta, idPds);
    }

	/**
     * Método que ejecuta la TRN
     * @param cuentaBean
     * @param lista
     * @return Lista de TODOS los productos simples de la cuenta
     */
    public List<TRCONSGLOBALKPRLTSE1> ejecutarPaginacion(final String idPds, final String idCcps1, final List<TRCONSGLOBALKPRLTSE1> lista){
        Ejecutar.ITRCONSGLOBALKPRLTS contexto = null;

        if(lista.isEmpty()){
            contexto = initPeticion(idPds, idCcps1, null);
        } else {
            contexto = initPeticion(idPds, idCcps1, lista.get(lista.size()-1).getKPDATOSCDPDV().get(0).getIDPARMCD().trim());
        }

        final EjecutarResult respuesta = ejecutarWS(contexto);

        try{
            super.verificaRespuesta(respuesta);
        }catch (final ControlableException ce){
            if (ce.getRtncod() != RETURN_COD_SIN_DATOS){
                throw ce;
            }else{
                return lista;
            }
        }
        //SE VERIFICA SI HAY MÁS DATOS QUE CONSULTAR
        if(respuesta.getResponseBansefi().getOTRCONSGLOBALKPRLTS().getMOREDATAIN() > 0){
            lista.addAll(respuesta.getResponseBansefi().getOTRCONSGLOBALKPRLTS().getTRCONSGLOBALKPRLTSE().getTRCONSGLOBALKPRLTSE());
            this.ejecutarPaginacion(idPds, idCcps1, lista);
        } else{
            lista.addAll(respuesta.getResponseBansefi().getOTRCONSGLOBALKPRLTS().getTRCONSGLOBALKPRLTSE().getTRCONSGLOBALKPRLTSE());
        }

        return lista;
    }

	private Map<String,String> getResponse(final List<TRCONSGLOBALKPRLTSE1> trconsglobalkprltse1, final String idPds){
	    final Map<String,String> resultado = new HashMap<>();
		if(null != trconsglobalkprltse1){
		    for(final TRCONSGLOBALKPRLTSE1 item: trconsglobalkprltse1){
		        if((item.getKPNOMBPARMCDV() != null && !item.getKPNOMBPARMCDV().isEmpty())
		           && (item.getKPDATOSCDPDV() != null && !item.getKPDATOSCDPDV().isEmpty())
		           && StringUtils.isNoneBlank(item.getKPNOMBPARMCDV().get(0).getNOMBPARMCD(),
		                   item.getKPDATOSCDPDV().get(0).getIDPARMCD())){
		            resultado.put(idPds+"-"+item.getKPDATOSCDPDV().get(0).getIDPARMCD(),
		                    item.getKPNOMBPARMCDV().get(0).getNOMBPARMCD().trim());
		        }
		    }
		}
		return resultado;
	}



	/**
	 * Función que inicializa el objeto para realizar la petición al servicio web.
	 * @param idCcps1
	 * @param idParmC
	 *
	 * @return Objeto a ser enviado al servicio web.
	 */
	private Ejecutar.ITRCONSGLOBALKPRLTS initPeticion(final String idPds, final String idCcps1, final String idParmC){
		final Ejecutar.ITRCONSGLOBALKPRLTS peticion = new Ejecutar.ITRCONSGLOBALKPRLTS();

		super.initialize(peticion);
		peticion.setSCROLLABLEOCCURS(50);

		final KPCDPDP kpcdpdp = peticion.getTRCONSGLOBALKPRLTSE().getKPCDPDP();
		kpcdpdp.setCODNRBEEN(this.getEntidad());
		kpcdpdp.setIDPDS(idPds);
		kpcdpdp.setIDCCPS(idCcps1);
        kpcdpdp.setIDPARMCD(idParmC);

		peticion.getTRCONSGLOBALKPRLTSE().setEVENTCD(ConstantesFuncionales.CTE_EVENTCD_2);

        final STDTRNIPARMV stdtrniparmv = peticion.getSTDTRNIPARMV();
        stdtrniparmv.setCODTX(CodTxConstants.COD_TX_TR_CONS_GLOBAL_KP_RL_TS_TRN);

        stdtrniparmv.setIDINTERNOTERMTN(this.getTerminal());
		return peticion;
	}

	/**
	 * Función para invocar al servicio web y obtener su respuesta.
	 *
	 * @param contexto Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	private EjecutarResult ejecutarWS(final Ejecutar.ITRCONSGLOBALKPRLTS contexto)
			throws NoControlableException{
		EjecutarResult respuesta = null;

		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
			        ConsultaNombreCondicionesServicio.class, contexto);
		}catch(final NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de consulta "
					+ "de nombre de condiciones.", e);
		}
		return respuesta;
	}
}
