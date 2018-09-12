package mx.babel.bansefi.banksystem.personas.backend;

import java.util.List;

import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.personas.beans.OtroValorBean;
import mx.babel.bansefi.banksystem.personas.webservices.consultaotrosnombrespersona.ConsultaOtrosNombresPersonaServicio;
import mx.babel.bansefi.banksystem.personas.webservices.consultaotrosnombrespersona.Ejecutar;
import mx.babel.bansefi.banksystem.personas.webservices.consultaotrosnombrespersona.Ejecutar.ITRPECONSPEOTRONOMB;
import mx.babel.bansefi.banksystem.personas.webservices.consultaotrosnombrespersona.Ejecutar.ITRPECONSPEOTRONOMB.STDTRNIPARMV;
import mx.babel.bansefi.banksystem.personas.webservices.consultaotrosnombrespersona.Ejecutar.ITRPECONSPEOTRONOMB.TRPECONSPEOTRONOMBE.PEOTRONOMBP;
import mx.babel.bansefi.banksystem.personas.webservices.consultaotrosnombrespersona.EjecutarResult;
import mx.babel.bansefi.banksystem.personas.webservices.consultaotrosnombrespersona.ResponseBansefi;
import mx.babel.bansefi.banksystem.personas.wrappers.ConsultaOtrosNombresPersonaWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Clase BackEnd para la ejecución del servicio de Consulta
 * de Otros Nombres de Persona.
 * 
 */
@Component
public class ConsultaOtrosNombresPersonaBackend extends BackEndBean{

	private static final long serialVersionUID = -5077942272316521159L;
	
	@Autowired
	ServicioWebUtils servicioWebUtils;
	
	@Autowired
    ConsultaOtrosNombresPersonaWrapper consultaOtrosNombresPersonaWrapper;

	public List<OtroValorBean> ejecutarTRN(final int idPersona){
		final Ejecutar.ITRPECONSPEOTRONOMB trpeconspeotronombtrni = initPeticion(idPersona);

		final EjecutarResult respuesta = ejecutarWS(trpeconspeotronombtrni);

        super.verificaRespuesta(respuesta);
		return getRespuesta(respuesta.getResponseBansefi());

	}

	private List<OtroValorBean> getRespuesta(final ResponseBansefi response){
	    List<OtroValorBean> respuesta = null;
		if(verificaRespuesta(response)){
		    respuesta = consultaOtrosNombresPersonaWrapper
		            .wrappOtrosNombres(response.getOTRPECONSPEOTRONOMB().getTRPECONSPEOTRONOMBE().getTRPECONSPEOTRONOMBE());
		}
		return respuesta;
	}

	private ITRPECONSPEOTRONOMB initPeticion(final int idPersona){
		final Ejecutar.ITRPECONSPEOTRONOMB trpeconspeotronombtrni = new Ejecutar.ITRPECONSPEOTRONOMB();

		super.initialize(trpeconspeotronombtrni);

		final PEOTRONOMBP peotronombp = trpeconspeotronombtrni.getTRPECONSPEOTRONOMBE().getPEOTRONOMBP();
		peotronombp.setCODNRBEEN(this.getEntidad());
		//TODO sustituir por el numero de acuerdo
		peotronombp.setIDINTERNOPE(idPersona);

        final STDTRNIPARMV stdtrniparmv = trpeconspeotronombtrni.getSTDTRNIPARMV();
        stdtrniparmv.setCODTX(CodTxConstants.COD_TX_TR_PE_CONS_PE_OTRO_NOMB_TRN);
        stdtrniparmv.setIDINTERNOTERMTN(this.getTerminal());
        stdtrniparmv.setNUMSEC(21488);

        trpeconspeotronombtrni.setELEVATORPOSITION(0);
        trpeconspeotronombtrni.setSCROLLABLEOCCURS(50);
		return trpeconspeotronombtrni;
	}

	/**
	 * Función para invocar al servicio web y obtener su respuesta.
	 *
	 * @param contexto Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	private EjecutarResult ejecutarWS(final Ejecutar.ITRPECONSPEOTRONOMB contexto)
			throws NoControlableException{
		EjecutarResult respuesta = null;

		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(ConsultaOtrosNombresPersonaServicio.class, contexto);

		}catch(final NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de consulta "
					+ "de otros nombres de persona.", e);
		}
		return respuesta;
	}

	/**
     * Función que valida que la respuesta Bansefi contenga un objeto con los datos
     *
     * @param response Respuesta Bansefi con datos
     * @return <code>true</code> si la respuesta Bansefi contiene los datos
     */
    private Boolean verificaRespuesta(final ResponseBansefi response){
        Boolean noNulo = false;
        if(response != null && response.getOTRPECONSPEOTRONOMB() != null &&
                response.getOTRPECONSPEOTRONOMB().getTRPECONSPEOTRONOMBE()!= null &&
                response.getOTRPECONSPEOTRONOMB().getTRPECONSPEOTRONOMBE().getTRPECONSPEOTRONOMBE() != null &&
                !response.getOTRPECONSPEOTRONOMB().getTRPECONSPEOTRONOMBE().getTRPECONSPEOTRONOMBE().isEmpty()){
            noNulo = true;
        }
        return noNulo;
    }
}
