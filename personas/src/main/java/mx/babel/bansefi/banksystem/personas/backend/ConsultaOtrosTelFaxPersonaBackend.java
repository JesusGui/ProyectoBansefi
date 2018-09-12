package mx.babel.bansefi.banksystem.personas.backend;

import java.util.List;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.personas.beans.OtroValorBean;
import mx.babel.bansefi.banksystem.personas.webservices.consultaotrostelfaxpersona.ConsultaOtrosTelFaxPersonaServicio;
import mx.babel.bansefi.banksystem.personas.webservices.consultaotrostelfaxpersona.Ejecutar;
import mx.babel.bansefi.banksystem.personas.webservices.consultaotrostelfaxpersona.Ejecutar.ITRDRCONSDRELCTRTRN;
import mx.babel.bansefi.banksystem.personas.webservices.consultaotrostelfaxpersona.Ejecutar.ITRDRCONSDRELCTRTRN.STDTRNIPARMV;
import mx.babel.bansefi.banksystem.personas.webservices.consultaotrostelfaxpersona.Ejecutar.ITRDRCONSDRELCTRTRN.TRDRCONSDRELCTREVTY.PEPERSRLDIRP;
import mx.babel.bansefi.banksystem.personas.webservices.consultaotrostelfaxpersona.EjecutarResult;
import mx.babel.bansefi.banksystem.personas.webservices.consultaotrostelfaxpersona.ResponseBansefi;
import mx.babel.bansefi.banksystem.personas.wrappers.ConsultaOtrosTelFaxPersonaWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Clase BackEnd para la ejecución del servicio de Consulta de 
 * Otros Telefonos/Fax de Personas.
 * 
 */
@Component
public class ConsultaOtrosTelFaxPersonaBackend extends BackEndBean{
	
	private static final long serialVersionUID = 1539596621330545982L;

	@Autowired
	ServicioWebUtils servicioWebUtils;
	
    @Autowired
    ConsultaOtrosTelFaxPersonaWrapper consultaOtrosTelFaxPersonaWrapper;

	public List<OtroValorBean> ejecutarTRN(final int idPersona)
			throws ControlableException, NoControlableException{
		final ITRDRCONSDRELCTRTRN trdrconsdrelctrtrni = initPeticion(idPersona);

		final EjecutarResult respuesta = ejecutarWS(trdrconsdrelctrtrni);

        super.verificaRespuesta(respuesta);
		return getRespuesta(respuesta.getResponseBansefi());

	}

	private List<OtroValorBean> getRespuesta(final ResponseBansefi response){
	    List<OtroValorBean> respuesta = null;
		if(verificaRespuesta(response)){
		    respuesta = consultaOtrosTelFaxPersonaWrapper
		            .wrappOtrosTelFax(response.getOTRDRCONSDRELCTRTRN().getTRDRCONSDRELCTREVTZ().getTRDRCONSDRELCTREVTV());
		}
		return respuesta;
	}

	private ITRDRCONSDRELCTRTRN initPeticion(final int idPersona){
		final Ejecutar.ITRDRCONSDRELCTRTRN trdrconsdrelctrtrni = new Ejecutar.ITRDRCONSDRELCTRTRN();

		super.initialize(trdrconsdrelctrtrni);

		final PEPERSRLDIRP pepersrldirp = trdrconsdrelctrtrni.getTRDRCONSDRELCTREVTY().getPEPERSRLDIRP();
		pepersrldirp.setCODNRBEEN(this.getEntidad());
		//TODO sustituir por el numero de acuerdo
		pepersrldirp.setIDINTERNOPE(idPersona);

        final STDTRNIPARMV stdtrniparmv = trdrconsdrelctrtrni.getSTDTRNIPARMV();
        stdtrniparmv.setCODTX(CodTxConstants.COD_TX_TR_DR_CONS_DR_ELCTR_TRN);
        stdtrniparmv.setIDINTERNOTERMTN(this.getTerminal());

        trdrconsdrelctrtrni.setELEVATORPOSITION(0);
        trdrconsdrelctrtrni.setSCROLLABLEOCCURS(50);
		return trdrconsdrelctrtrni;
	}

	/**
	 * Función para invocar al servicio web y obtener su respuesta.
	 *
	 * @param contexto Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	private EjecutarResult ejecutarWS(final Ejecutar.ITRDRCONSDRELCTRTRN contexto)
			throws NoControlableException{
		EjecutarResult respuesta = null;

		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(ConsultaOtrosTelFaxPersonaServicio.class, contexto);
		}catch(final NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de consulta "
					+ "de otros telefonos/faxes de persona.", e);
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
        if(response != null && response.getOTRDRCONSDRELCTRTRN() != null &&
                response.getOTRDRCONSDRELCTRTRN().getTRDRCONSDRELCTREVTZ()!= null &&
                response.getOTRDRCONSDRELCTRTRN().getTRDRCONSDRELCTREVTZ().getTRDRCONSDRELCTREVTV() != null &&
                !response.getOTRDRCONSDRELCTRTRN().getTRDRCONSDRELCTREVTZ().getTRDRCONSDRELCTREVTV().isEmpty()){
            noNulo = true;
        }
        return noNulo;
    }
}
