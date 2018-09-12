package mx.babel.bansefi.banksystem.personas.backend;

import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.personas.beans.OtroValorBean;
import mx.babel.bansefi.banksystem.personas.webservices.modificaotraidentificacionpersona.Ejecutar;
import mx.babel.bansefi.banksystem.personas.webservices.modificaotraidentificacionpersona.Ejecutar.ITRPEMODIFPEIDEXTTR.STDTRNIPARMV;
import mx.babel.bansefi.banksystem.personas.webservices.modificaotraidentificacionpersona.Ejecutar.ITRPEMODIFPEIDEXTTR.TRPEMODIFPEIDEXTEVT;
import mx.babel.bansefi.banksystem.personas.webservices.modificaotraidentificacionpersona.Ejecutar.ITRPEMODIFPEIDEXTTR.TRPEMODIFPEIDEXTEVT.PEIDEXTP;
import mx.babel.bansefi.banksystem.personas.webservices.modificaotraidentificacionpersona.EjecutarResult;
import mx.babel.bansefi.banksystem.personas.webservices.modificaotraidentificacionpersona.ModificaOtraIdentificacionPersonaServicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Clase BackEnd para la ejecución del servicio de Modificacion de Otra Identificacion de
 * Persona.
 * 
 * @author alejandro.pineda
 *
 */
@Component
public class ModificaOtraIdentificacionPersonaBackend extends BackEndBean{
	
	private static final long serialVersionUID = -1137733079850417047L;
	
	@Autowired
	private ServicioWebUtils servicioWebUtils;

	public void ejecutarTRN(final int idPersona, final OtroValorBean otroValorBean){
		
		final Ejecutar.ITRPEMODIFPEIDEXTTR trpealtapeidexttrni = initPeticion(idPersona, otroValorBean);
		final EjecutarResult respuesta = ejecutarWS(trpealtapeidexttrni);
        super.verificaRespuesta(respuesta);

	}


	private Ejecutar.ITRPEMODIFPEIDEXTTR initPeticion(final int idPersona, final OtroValorBean otroValorBean){
		final Ejecutar.ITRPEMODIFPEIDEXTTR itrpemodifpeidexttr = new Ejecutar.ITRPEMODIFPEIDEXTTR();

		super.initialize(itrpemodifpeidexttr);

		final TRPEMODIFPEIDEXTEVT trpemodifpeidextevt = itrpemodifpeidexttr.getTRPEMODIFPEIDEXTEVT();
		trpemodifpeidextevt.setIDEXTPE(otroValorBean.getValor());
		trpemodifpeidextevt.setINDPPALIDPE("N");
		
		final PEIDEXTP peidextp = itrpemodifpeidexttr.getTRPEMODIFPEIDEXTEVT().getPEIDEXTP();
		
		peidextp.setIDINTERNOPE(idPersona);
		peidextp.setCODIDEXTPERS(otroValorBean.getClave());
		peidextp.setCODNRBEEN(super.getEntidad());

        final STDTRNIPARMV stdtrniparmv = itrpemodifpeidexttr.getSTDTRNIPARMV();
        stdtrniparmv.setCODTX(CodTxConstants.COD_TX_TR_PE_MODIF_PE_ID_EXT_TRN);
        stdtrniparmv.setIDINTERNOTERMTN(this.getTerminal());

		return itrpemodifpeidexttr;
	}

	/**
	 * Función para invocar al servicio web y obtener su respuesta.
	 *
	 * @param contexto Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	private EjecutarResult ejecutarWS(final Ejecutar.ITRPEMODIFPEIDEXTTR contexto)
			throws NoControlableException{
		EjecutarResult respuesta = null;

		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(ModificaOtraIdentificacionPersonaServicio.class, contexto);
		}catch(final NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de modificación "
					+ "de otra identificacion de persona.", e);
		}
		return respuesta;
	}
	
}
