package mx.babel.bansefi.banksystem.personas.backend;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.personas.beans.OtroValorBean;
import mx.babel.bansefi.banksystem.personas.webservices.altaotraidentificacionpersona.AltaOtraIdentificacionPersonaServicio;
import mx.babel.bansefi.banksystem.personas.webservices.altaotraidentificacionpersona.Ejecutar;
import mx.babel.bansefi.banksystem.personas.webservices.altaotraidentificacionpersona.Ejecutar.ITRPEALTAPEIDEXTTRN.STDTRNIPARMV;
import mx.babel.bansefi.banksystem.personas.webservices.altaotraidentificacionpersona.Ejecutar.ITRPEALTAPEIDEXTTRN.TRPEALTAPEIDEXTEVT;
import mx.babel.bansefi.banksystem.personas.webservices.altaotraidentificacionpersona.EjecutarResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Clase BackEnd para la ejecución del servicio de Alta de Otra Identificacion de
 * Persona.
 * 
 */
@Component
public class AltaOtraIdentificacionPersonaBackend extends BackEndBean{
	
	private static final long serialVersionUID = -8855490868933771434L;
	
	@Autowired
	ServicioWebUtils servicioWebUtils;

	public void ejecutarTRN(final int idPersona, final OtroValorBean otroValorBean)
			throws ControlableException, NoControlableException{
		
		final Ejecutar.ITRPEALTAPEIDEXTTRN trpealtapeidexttrni = initPeticion(idPersona, otroValorBean);
		final EjecutarResult respuesta = ejecutarWS(trpealtapeidexttrni);
        super.verificaRespuesta(respuesta);

	}


	private Ejecutar.ITRPEALTAPEIDEXTTRN initPeticion(final int idPersona, final OtroValorBean otroValorBean){
		final Ejecutar.ITRPEALTAPEIDEXTTRN trpealtapeidexttrni = new Ejecutar.ITRPEALTAPEIDEXTTRN();

		super.initialize(trpealtapeidexttrni);

		final TRPEALTAPEIDEXTEVT trpealtapeotronombevty = trpealtapeidexttrni.getTRPEALTAPEIDEXTEVT();
		trpealtapeotronombevty.setCODNRBEEN(this.getEntidad());
		//TODO sustituir por el numero de acuerdo
		trpealtapeotronombevty.setIDINTERNOPE(idPersona);
		trpealtapeotronombevty.setCODIDEXTPERS(otroValorBean.getClave());
        trpealtapeotronombevty.setIDEXTPE(otroValorBean.getValor());
        trpealtapeotronombevty.setINDPPALIDPE("N");

        final STDTRNIPARMV stdtrniparmv = trpealtapeidexttrni.getSTDTRNIPARMV();
        stdtrniparmv.setCODTX(CodTxConstants.COD_TX_TR_PE_ALTA_PE_ID_EXT_TRN);
        stdtrniparmv.setIDINTERNOTERMTN(this.getTerminal());

		return trpealtapeidexttrni;
	}

	/**
	 * Función para invocar al servicio web y obtener su respuesta.
	 *
	 * @param contexto Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	private EjecutarResult ejecutarWS(final Ejecutar.ITRPEALTAPEIDEXTTRN contexto)
			throws NoControlableException{
		EjecutarResult respuesta = null;

		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(AltaOtraIdentificacionPersonaServicio.class, contexto);
		}catch(final NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de alta "
					+ "de otra identificacion de persona.", e);
		}
		return respuesta;
	}
}
