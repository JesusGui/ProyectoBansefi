package mx.babel.bansefi.banksystem.base.backends;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.beans.DepositoIPFBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.webservices.consultadatosipf.ConsultaDatosIPFServicio;
import mx.babel.bansefi.banksystem.base.webservices.consultadatosipf.Ejecutar;
import mx.babel.bansefi.banksystem.base.webservices.consultadatosipf.Ejecutar.ITRDATOSRENIMPSCNPAG.STDTRNIPARMV;
import mx.babel.bansefi.banksystem.base.webservices.consultadatosipf.Ejecutar.ITRDATOSRENIMPSCNPAG.TRDATOSRENIMPSCNPAGE.IPIMPSCNP;
import mx.babel.bansefi.banksystem.base.webservices.consultadatosipf.EjecutarResult;
import mx.babel.bansefi.banksystem.base.webservices.consultadatosipf.ResponseBansefi;
import mx.babel.bansefi.banksystem.base.wrappers.ConsultaDatosIPFWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author joseluis.pena
 *
 */
@Component
public class ConsultaDatosIPFBackend extends BackEndBean{

    /**
     *
     */
    private static final long serialVersionUID = 5096423337662981106L;

    @Autowired
    ConsultaDatosIPFWrapper consultaDatosIPFWrapper;

    @Autowired
    ServicioWebUtils servicioWebUtils;

	public DepositoIPFBean ejecutarTRN(final Long numCuenta, final Integer numSubAc){
		final Ejecutar.ITRDATOSRENIMPSCNPAG request = initPeticion(numCuenta, numSubAc);

		final EjecutarResult respuesta = ejecutarWS(request);
		
		try{
            super.verificaRespuesta(respuesta);
        }catch (final ControlableException ce){
            if (ce.getRtncod() != RETURN_COD_SIN_DATOS){
                throw ce;
            }else{
                return null;
            }
        }
		
		return getResponse(respuesta.getResponseBansefi());
	}


	private DepositoIPFBean getResponse(final ResponseBansefi response){
	    DepositoIPFBean respuesta = null;
		
		if(verificaRespuesta(response)){
		    respuesta = consultaDatosIPFWrapper.wrappDepositoIPF(response.getOTRDATOSRENIMPSCNPAG().getTRDATOSRENIMPSCNPAGE());
		}
		return respuesta;
	}

	/**
	 * Función que inicializa el objeto para realizar la petición al servicio web.
	 * @param ultimoTramo
	 *
	 * @return Objeto a ser enviado al servicio web.
	 */
	private Ejecutar.ITRDATOSRENIMPSCNPAG initPeticion(final Long numCuenta, final Integer numSubAc){
		final Ejecutar.ITRDATOSRENIMPSCNPAG peticion = new Ejecutar.ITRDATOSRENIMPSCNPAG();

		super.initialize(peticion);
		peticion.setSCROLLABLEOCCURS(50);

		final IPIMPSCNP ipimpscnp = peticion.getTRDATOSRENIMPSCNPAGE().getIPIMPSCNP();
		ipimpscnp.setCODNRBEEN(this.getEntidad());
		ipimpscnp.setNUMSECAC(numCuenta);
        ipimpscnp.setNUMSUBAC(numSubAc);

        final STDTRNIPARMV stdtrniparmv = peticion.getSTDTRNIPARMV();
        stdtrniparmv.setCODTX(CodTxConstants.COD_TX_TR_DATOS_REN_IMPSCN_PAG_TRN);
        stdtrniparmv.setIDINTERNOTERMTN(this.getTerminal());
		return peticion;
	}

	/**
	 * Función para invocar al servicio web y obtener su respuesta.
	 *
	 * @param contexto Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	private EjecutarResult ejecutarWS(final Ejecutar.ITRDATOSRENIMPSCNPAG contexto)
			throws NoControlableException{
		EjecutarResult respuesta = null;

		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
			        ConsultaDatosIPFServicio.class, contexto);
		}catch(final NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de la consulta "
					+ "de datos de la IPF.", e);
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
		if(response != null && response.getOTRDATOSRENIMPSCNPAG() != null &&
			response.getOTRDATOSRENIMPSCNPAG().getTRDATOSRENIMPSCNPAGE()!= null ){
			noNulo = true;
		}
		return noNulo;
	}

}
