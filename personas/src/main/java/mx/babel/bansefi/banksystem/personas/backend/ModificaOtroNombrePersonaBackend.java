package mx.babel.bansefi.banksystem.personas.backend;

import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.personas.beans.OtroValorBean;
import mx.babel.bansefi.banksystem.personas.webservices.modificaotronombrepersona.Ejecutar;
import mx.babel.bansefi.banksystem.personas.webservices.modificaotronombrepersona.Ejecutar.ITRPEMODIFPEOTRONOMB.STDTRNIPARMV;
import mx.babel.bansefi.banksystem.personas.webservices.modificaotronombrepersona.Ejecutar.ITRPEMODIFPEOTRONOMB.TRPEMODIFPEOTRONOMB.PEOTRONOMBP;
import mx.babel.bansefi.banksystem.personas.webservices.modificaotronombrepersona.EjecutarResult;
import mx.babel.bansefi.banksystem.personas.webservices.modificaotronombrepersona.ModificaOtroNombrePersonaServicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Clase BackEnd para la ejecución del servicio de Modificacion
 * de Otro Nombre de Persona.
 * 
 */
@Component
public class ModificaOtroNombrePersonaBackend extends BackEndBean{
	
	private static final long serialVersionUID = 1888851345200926709L;
	
	@Autowired
	private ServicioWebUtils servicioWebUtils;
	
	public void ejecutarTRN(final int idPersona, final OtroValorBean otroValorBean){
		final Ejecutar.ITRPEMODIFPEOTRONOMB trpemodifpeotronombtrni = initPeticion(idPersona, otroValorBean);
		final EjecutarResult respuesta = ejecutarWS(trpemodifpeotronombtrni);
        super.verificaRespuesta(respuesta);
        
	}

	private Ejecutar.ITRPEMODIFPEOTRONOMB initPeticion(final int idPersona, final OtroValorBean otroValorBean){
		final Ejecutar.ITRPEMODIFPEOTRONOMB trpemodifpeotronombtrni = new Ejecutar.ITRPEMODIFPEOTRONOMB();

		super.initialize(trpemodifpeotronombtrni);
		trpemodifpeotronombtrni.getTRPEMODIFPEOTRONOMB().setVALOTRSNOMBPE(otroValorBean.getValor());
		final PEOTRONOMBP peotronombp = trpemodifpeotronombtrni.getTRPEMODIFPEOTRONOMB().getPEOTRONOMBP();
		peotronombp.setCODNRBEEN(this.getEntidad());
		peotronombp.setIDINTERNOPE(idPersona);

		peotronombp.setCODNOMBPERS(otroValorBean.getClave());
        peotronombp.setVALOTRSNOMBPEC(otroValorBean.getValorAnterior());

        final STDTRNIPARMV stdtrniparmv = trpemodifpeotronombtrni.getSTDTRNIPARMV();
        stdtrniparmv.setCODTX(CodTxConstants.COD_TX_TR_PE_MODIF_PE_OTRO_NOMB_TRN);
        stdtrniparmv.setIDINTERNOTERMTN(this.getTerminal());
        stdtrniparmv.setNUMSEC(21494);

		return trpemodifpeotronombtrni;
	}

	/**
	 * Función para invocar al servicio web y obtener su respuesta.
	 *
	 * @param contexto Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	private EjecutarResult ejecutarWS(final Ejecutar.ITRPEMODIFPEOTRONOMB contexto){
		EjecutarResult respuesta = null;

		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(ModificaOtroNombrePersonaServicio.class, contexto);
		}catch(final NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de modificacion "
					+ "de otros nombres de persona.", e);
		}
		return respuesta;
	}

}
