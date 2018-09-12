package mx.babel.bansefi.banksystem.personas.backend;

import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.personas.beans.OtroValorBean;
import mx.babel.bansefi.banksystem.personas.webservices.bajaotronombrepersona.BajaOtroNombrePersonaServicio;
import mx.babel.bansefi.banksystem.personas.webservices.bajaotronombrepersona.Ejecutar;
import mx.babel.bansefi.banksystem.personas.webservices.bajaotronombrepersona.Ejecutar.ITRPEBAJAPEOTRONOMB.STDTRNIPARMV;
import mx.babel.bansefi.banksystem.personas.webservices.bajaotronombrepersona.Ejecutar.ITRPEBAJAPEOTRONOMB.TRPEBAJAPEOTRONOMBE.PEOTRONOMBP;
import mx.babel.bansefi.banksystem.personas.webservices.bajaotronombrepersona.EjecutarResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Clase BackEnd para la ejecución del servicio de Baja
 * de Otro Nombre de Persona.
 * 
 */
@Component
public class BajaOtroNombrePersonaBackend extends BackEndBean{
	
	private static final long serialVersionUID = -8437273318353006854L;
	
	@Autowired
	ServicioWebUtils servicioWebUtils;

	public void ejecutarTRN(final int idPersona, final OtroValorBean otroValorBean){
		
		final Ejecutar.ITRPEBAJAPEOTRONOMB trpebajapeotronombtrni = initPeticion(idPersona, otroValorBean);
		final EjecutarResult respuesta = ejecutarWS(trpebajapeotronombtrni);
        super.verificaRespuesta(respuesta);

	}

	private Ejecutar.ITRPEBAJAPEOTRONOMB initPeticion(final int idPersona, final OtroValorBean otroValorBean){
		final Ejecutar.ITRPEBAJAPEOTRONOMB bajaOtroNombre = new Ejecutar.ITRPEBAJAPEOTRONOMB();

		super.initialize(bajaOtroNombre);

		final PEOTRONOMBP peotronombp = bajaOtroNombre.getTRPEBAJAPEOTRONOMBE().getPEOTRONOMBP();		
		peotronombp.setCODNRBEEN(this.getEntidad());
		peotronombp.setIDINTERNOPE(idPersona);
		peotronombp.setCODNOMBPERS(otroValorBean.getClave());
		peotronombp.setVALOTRSNOMBPEC(otroValorBean.getValor());

        final STDTRNIPARMV stdtrniparmv = bajaOtroNombre.getSTDTRNIPARMV();
        stdtrniparmv.setCODTX(CodTxConstants.COD_TX_TR_PE_BAJA_PE_OTRO_NOMB_TRN);
        stdtrniparmv.setIDINTERNOTERMTN(this.getTerminal());

		return bajaOtroNombre;
	}

	/**
	 * Función para invocar al servicio web y obtener su respuesta.
	 *
	 * @param contexto Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	private EjecutarResult ejecutarWS(final Ejecutar.ITRPEBAJAPEOTRONOMB contexto){
		EjecutarResult respuesta = null;

		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(BajaOtroNombrePersonaServicio.class, contexto);
		}catch(final NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de baja "
					+ "de otro nombre de persona.", e);
		}
		return respuesta;
	}
}
