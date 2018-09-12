package mx.babel.bansefi.banksystem.personas.backend;

import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.personas.webservices.bajaotraidentificacionpersona.BajaOtraIdentificacionPersonaServicio;
import mx.babel.bansefi.banksystem.personas.webservices.bajaotraidentificacionpersona.Ejecutar;
import mx.babel.bansefi.banksystem.personas.webservices.bajaotraidentificacionpersona.Ejecutar.ITRPEBAJAPEIDEXTTRN.STDTRNIPARMV;
import mx.babel.bansefi.banksystem.personas.webservices.bajaotraidentificacionpersona.Ejecutar.ITRPEBAJAPEIDEXTTRN.TRPEBAJAPEIDEXTEVT.PEIDEXTP;
import mx.babel.bansefi.banksystem.personas.webservices.bajaotraidentificacionpersona.EjecutarResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Clase BackEnd para la ejecución del servicio de Baja
 * Otra Identificacion de Persona.
 * 
 */
@Component
public class BajaOtraIdentificacionPersonaBackend extends BackEndBean{
	
	private static final long serialVersionUID = -2050609955858147668L;
	
	@Autowired
	ServicioWebUtils servicioWebUtils;

	public void ejecutarTRN(final int idPersona, final String codOtroValor){
		final Ejecutar.ITRPEBAJAPEIDEXTTRN trpebajapeidexttrni = initPeticion(idPersona, codOtroValor);
		final EjecutarResult respuesta = ejecutarWS(trpebajapeidexttrni);
        super.verificaRespuesta(respuesta);

	}


	private Ejecutar.ITRPEBAJAPEIDEXTTRN initPeticion(final int idPersona, final String codOtroValor){
		final Ejecutar.ITRPEBAJAPEIDEXTTRN trpebajapeidexttrni = new Ejecutar.ITRPEBAJAPEIDEXTTRN();

		super.initialize(trpebajapeidexttrni);

		final PEIDEXTP peidextp = trpebajapeidexttrni.getTRPEBAJAPEIDEXTEVT().getPEIDEXTP();
		peidextp.setCODNRBEEN(this.getEntidad());
		peidextp.setIDINTERNOPE(idPersona);
		peidextp.setCODIDEXTPERS(codOtroValor);

        final STDTRNIPARMV stdtrniparmv = trpebajapeidexttrni.getSTDTRNIPARMV();
        stdtrniparmv.setCODTX(CodTxConstants.COD_TX_TR_PE_BAJA_PE_ID_EXT_TRN);
        stdtrniparmv.setIDINTERNOTERMTN(this.getTerminal());

		return trpebajapeidexttrni;
	}

	/**
	 * Función para invocar al servicio web y obtener su respuesta.
	 *
	 * @param contexto Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	private EjecutarResult ejecutarWS(final Ejecutar.ITRPEBAJAPEIDEXTTRN contexto){
		EjecutarResult respuesta = null;

		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(BajaOtraIdentificacionPersonaServicio.class, contexto);
		}catch(final NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de baja "
					+ "de otra identificacion de persona.", e);
		}
		return respuesta;
	}
}
