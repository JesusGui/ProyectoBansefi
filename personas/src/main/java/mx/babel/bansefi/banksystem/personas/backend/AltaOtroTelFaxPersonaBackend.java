package mx.babel.bansefi.banksystem.personas.backend;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.constants.ConstantesFuncionales;
import mx.babel.bansefi.banksystem.personas.beans.OtroValorBean;
import mx.babel.bansefi.banksystem.personas.webservices.altaotrotelfaxpersona.AltaOtroTelFaxPersonaServicio;
import mx.babel.bansefi.banksystem.personas.webservices.altaotrotelfaxpersona.Ejecutar;
import mx.babel.bansefi.banksystem.personas.webservices.altaotrotelfaxpersona.Ejecutar.ITRDRALTADRELCTRTRN;
import mx.babel.bansefi.banksystem.personas.webservices.altaotrotelfaxpersona.Ejecutar.ITRDRALTADRELCTRTRN.STDTRNIPARMV;
import mx.babel.bansefi.banksystem.personas.webservices.altaotrotelfaxpersona.Ejecutar.ITRDRALTADRELCTRTRN.TRDRALTADRELCTREVTY;
import mx.babel.bansefi.banksystem.personas.webservices.altaotrotelfaxpersona.EjecutarResult;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Clase BackEnd para la ejecución del servicio de Alta de Otro Telefono/Fax de
 * Persona.
 * 
 */
@Component
public class AltaOtroTelFaxPersonaBackend extends BackEndBean{

	private static final long serialVersionUID = -1944581857136057300L;
	
	@Autowired
	ServicioWebUtils servicioWebUtils;

	public void ejecutarTRN(final int idPersona, final OtroValorBean otroValorBean)
			throws ControlableException, NoControlableException{
		final ITRDRALTADRELCTRTRN trdraltadrelctrtrni = initPeticion(idPersona, otroValorBean);
		final EjecutarResult respuesta = ejecutarWS(trdraltadrelctrtrni);
        super.verificaRespuesta(respuesta);

	}


	private ITRDRALTADRELCTRTRN initPeticion(final int idPersona, final OtroValorBean otroValorBean){
		final Ejecutar.ITRDRALTADRELCTRTRN trdraltadrelctrtrni = new Ejecutar.ITRDRALTADRELCTRTRN();

		super.initialize(trdraltadrelctrtrni);

		final TRDRALTADRELCTREVTY trdraltadrelctrevty = trdraltadrelctrtrni.getTRDRALTADRELCTREVTY();
		trdraltadrelctrevty.setCODNRBEEN(this.getEntidad());
		trdraltadrelctrevty.setIDINTERNOPE(idPersona);
	    trdraltadrelctrevty.setCODDIRELCTR(otroValorBean.getClave());
		if(StringUtils.equalsIgnoreCase(ConstantesFuncionales.DIR_INTERNET.getClaveFila(), otroValorBean.getClave())){
		    trdraltadrelctrevty.setTXTELCTRDRAMPL(otroValorBean.getValor());
            trdraltadrelctrevty.setVALORELCTRDR(otroValorBean.getDescripcion());
            trdraltadrelctrevty.setVALORELCTRDRLEN(otroValorBean.getDescripcion().length());
		}else{
    		trdraltadrelctrevty.setTXTELCTRDR(otroValorBean.getValor());
            trdraltadrelctrevty.setVALORELCTRDR(otroValorBean.getNumero());
            trdraltadrelctrevty.setVALORELCTRDRLEN(otroValorBean.getNumero().length());
		}

		trdraltadrelctrevty.setCODPERSRLDIR("07");
        trdraltadrelctrevty.setCODDIR("2");
        trdraltadrelctrevty.setNUMARGEO(0);
        trdraltadrelctrevty.setNUMDIR(0);

        final STDTRNIPARMV stdtrniparmv = trdraltadrelctrtrni.getSTDTRNIPARMV();
        stdtrniparmv.setCODTX(CodTxConstants.COD_TX_TR_DR_ALTA_DR_ELCTR_TRN);
        stdtrniparmv.setIDINTERNOTERMTN(this.getTerminal());

		return trdraltadrelctrtrni;
	}

	/**
	 * Función para invocar al servicio web y obtener su respuesta.
	 *
	 * @param contexto Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	private EjecutarResult ejecutarWS(final Ejecutar.ITRDRALTADRELCTRTRN contexto)
			throws NoControlableException{
		EjecutarResult respuesta = null;

		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(AltaOtroTelFaxPersonaServicio.class, contexto);
		}catch(final NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de alta "
					+ "de otro telefono/fax de persona.", e);
		}
		return respuesta;
	}
}
