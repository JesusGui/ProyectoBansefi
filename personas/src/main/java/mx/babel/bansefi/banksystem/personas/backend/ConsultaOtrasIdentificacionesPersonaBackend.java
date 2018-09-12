package mx.babel.bansefi.banksystem.personas.backend;

import java.util.ArrayList;
import java.util.List;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.personas.beans.OtroValorBean;
import mx.babel.bansefi.banksystem.personas.webservices.consultaotrasidentificacionespersona.ConsultaOtrasIdentificacionesPersonaServicio;
import mx.babel.bansefi.banksystem.personas.webservices.consultaotrasidentificacionespersona.Ejecutar;
import mx.babel.bansefi.banksystem.personas.webservices.consultaotrasidentificacionespersona.Ejecutar.ITRPECONSPEIDEXTTRN;
import mx.babel.bansefi.banksystem.personas.webservices.consultaotrasidentificacionespersona.Ejecutar.ITRPECONSPEIDEXTTRN.STDTRNIPARMV;
import mx.babel.bansefi.banksystem.personas.webservices.consultaotrasidentificacionespersona.Ejecutar.ITRPECONSPEIDEXTTRN.TRPECONSPEIDEXTEVT.PEIDEXTP;
import mx.babel.bansefi.banksystem.personas.webservices.consultaotrasidentificacionespersona.EjecutarResult;
import mx.babel.bansefi.banksystem.personas.webservices.consultaotrasidentificacionespersona.ResponseBansefi;
import mx.babel.bansefi.banksystem.personas.wrappers.ConsultaOtrasIdentificacionesPersonaWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Clase BackEnd para la ejecución del servicio de Consulta de 
 * Otras Identificaciones de Persona.
 * 
 */
@Component
public class ConsultaOtrasIdentificacionesPersonaBackend extends BackEndBean{
	
	private static final long serialVersionUID = 4040399742082567179L;

	@Autowired
	ServicioWebUtils servicioWebUtils;
	
    @Autowired
    ConsultaOtrasIdentificacionesPersonaWrapper consultaOtrasIdentificacionesPersonaWrapper;

	public List<OtroValorBean> ejecutarTRN(final int idPersona){
		final ITRPECONSPEIDEXTTRN trpeconspeidexttrni = initPeticion(idPersona);

		final EjecutarResult respuesta = ejecutarWS(trpeconspeidexttrni);

		try{
			super.verificaRespuesta(respuesta);
		}catch (ControlableException ce){
			if (ce.getRtncod() != RETURN_COD_SIN_DATOS){
				throw ce;
			}else{
				return new ArrayList<OtroValorBean>();
			}
		}
		
		List<OtroValorBean> respuestaSalida = null;
		if(verificaRespuesta(respuesta.getResponseBansefi())){
			respuestaSalida = consultaOtrasIdentificacionesPersonaWrapper
		            .wrappOtrasIdentificaciones(respuesta.getResponseBansefi().getOTRPECONSPEIDEXTTRN().getTRPECONSPEIDEXTEVT().getTRPECONSPEIDEXTEVT());
		}
		return respuestaSalida;

	}

	private ITRPECONSPEIDEXTTRN initPeticion(final int idPersona){
		final Ejecutar.ITRPECONSPEIDEXTTRN trpeconspeidexttrni = new Ejecutar.ITRPECONSPEIDEXTTRN();

		super.initialize(trpeconspeidexttrni);

		final PEIDEXTP peidextp = trpeconspeidexttrni.getTRPECONSPEIDEXTEVT().getPEIDEXTP();
		peidextp.setCODNRBEEN(this.getEntidad());
		//TODO sustituir por el numero de acuerdo
		peidextp.setIDINTERNOPE(idPersona);

        final STDTRNIPARMV stdtrniparmv = trpeconspeidexttrni.getSTDTRNIPARMV();
        stdtrniparmv.setCODTX(CodTxConstants.COD_TX_TR_PE_CONS_PE_ID_EXT_TRN);
        stdtrniparmv.setIDINTERNOTERMTN(this.getTerminal());

        trpeconspeidexttrni.setELEVATORPOSITION(0);
        trpeconspeidexttrni.setSCROLLABLEOCCURS(50);
		return trpeconspeidexttrni;
	}

	/**
	 * Función para invocar al servicio web y obtener su respuesta.
	 *
	 * @param contexto Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	private EjecutarResult ejecutarWS(final Ejecutar.ITRPECONSPEIDEXTTRN contexto){
		EjecutarResult respuesta = null;

		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(ConsultaOtrasIdentificacionesPersonaServicio.class, contexto);

		}catch(final NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de consulta "
					+ "de otras identificaciones de persona.", e);
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
        if(response != null && response.getOTRPECONSPEIDEXTTRN() != null &&
                response.getOTRPECONSPEIDEXTTRN().getTRPECONSPEIDEXTEVT()!= null &&
                response.getOTRPECONSPEIDEXTTRN().getTRPECONSPEIDEXTEVT().getTRPECONSPEIDEXTEVT() != null &&
                !response.getOTRPECONSPEIDEXTTRN().getTRPECONSPEIDEXTEVT().getTRPECONSPEIDEXTEVT().isEmpty()){
            noNulo = true;
        }
        return noNulo;
    }
}
