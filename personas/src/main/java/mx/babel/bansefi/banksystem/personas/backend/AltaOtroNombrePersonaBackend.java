package mx.babel.bansefi.banksystem.personas.backend;

import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.enums.EstadoListadosEnum;
import mx.babel.bansefi.banksystem.personas.beans.OtroValorBean;
import mx.babel.bansefi.banksystem.personas.enums.BeanCrudEnum;
import mx.babel.bansefi.banksystem.personas.webservices.altaotronombrepersona.AltaOtroNombrePersonaServicio;
import mx.babel.bansefi.banksystem.personas.webservices.altaotronombrepersona.Ejecutar;
import mx.babel.bansefi.banksystem.personas.webservices.altaotronombrepersona.Ejecutar.ITRPEALTAPEOTRONOMB;
import mx.babel.bansefi.banksystem.personas.webservices.altaotronombrepersona.Ejecutar.ITRPEALTAPEOTRONOMB.STDTRNIPARMV;
import mx.babel.bansefi.banksystem.personas.webservices.altaotronombrepersona.Ejecutar.ITRPEALTAPEOTRONOMB.TRPEALTAPEOTRONOMBE;
import mx.babel.bansefi.banksystem.personas.webservices.altaotronombrepersona.EjecutarResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Clase BackEnd para la ejecución del servicio de Alta de Otro Nombre de
 * Persona.
 * 
 */
@Component
public class AltaOtroNombrePersonaBackend extends BackEndBean{
	
	private static final long serialVersionUID = -5093982343169442855L;
	
	@Autowired
	ServicioWebUtils servicioWebUtils;

	public void ejecutarTRN(final int idPersona, final OtroValorBean otroValorBean){
		final ITRPEALTAPEOTRONOMB trpealtapeotronombtrni = initPeticion(idPersona, otroValorBean);

		final EjecutarResult respuesta = ejecutarWS(trpealtapeotronombtrni);

        super.verificaRespuesta(respuesta);
        otroValorBean.setOperacion(BeanCrudEnum.SIN_CAMBIOS);
        otroValorBean.setEstado(EstadoListadosEnum.CONSULTADO);
	}


	private ITRPEALTAPEOTRONOMB initPeticion(final int idPersona, final OtroValorBean otroValorBean){
		final Ejecutar.ITRPEALTAPEOTRONOMB trpealtapeotronombtrni = new Ejecutar.ITRPEALTAPEOTRONOMB();

		super.initialize(trpealtapeotronombtrni);

		final TRPEALTAPEOTRONOMBE trpealtapeotronombevty = trpealtapeotronombtrni.getTRPEALTAPEOTRONOMBE();
		trpealtapeotronombevty.setCODNRBEEN(this.getEntidad());
		trpealtapeotronombevty.setIDINTERNOPE(idPersona);
		trpealtapeotronombevty.setCODNOMBPERS(otroValorBean.getClave());
        trpealtapeotronombevty.setVALOTRSNOMBPE(otroValorBean.getValor());

        final STDTRNIPARMV stdtrniparmv = trpealtapeotronombtrni.getSTDTRNIPARMV();
        stdtrniparmv.setCODTX(CodTxConstants.COD_TX_TR_PE_ALTA_PE_OTRO_NOMB_TRN);
        stdtrniparmv.setIDINTERNOTERMTN(this.getTerminal());

		return trpealtapeotronombtrni;
	}

	/**
	 * Función para invocar al servicio web y obtener su respuesta.
	 *
	 * @param contexto Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	private EjecutarResult ejecutarWS(final Ejecutar.ITRPEALTAPEOTRONOMB contexto){
		EjecutarResult respuesta = null;

		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(AltaOtroNombrePersonaServicio.class, contexto);
		}catch(final NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de alta "
					+ "de otro nombre de persona.", e);
		}
		return respuesta;
	}
}
