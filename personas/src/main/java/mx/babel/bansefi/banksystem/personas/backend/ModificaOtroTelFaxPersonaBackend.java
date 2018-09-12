package mx.babel.bansefi.banksystem.personas.backend;

import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.constants.ConstantesFuncionales;
import mx.babel.bansefi.banksystem.personas.beans.OtroValorBean;
import mx.babel.bansefi.banksystem.personas.webservices.modificaotrotelfaxpersona.Ejecutar;
import mx.babel.bansefi.banksystem.personas.webservices.modificaotrotelfaxpersona.Ejecutar.ITRDRMODIFDRELCTRTRN;
import mx.babel.bansefi.banksystem.personas.webservices.modificaotrotelfaxpersona.Ejecutar.ITRDRMODIFDRELCTRTRN.STDTRNIPARMV;
import mx.babel.bansefi.banksystem.personas.webservices.modificaotrotelfaxpersona.Ejecutar.ITRDRMODIFDRELCTRTRN.TRDRMODIFDRELCTREVT;
import mx.babel.bansefi.banksystem.personas.webservices.modificaotrotelfaxpersona.Ejecutar.ITRDRMODIFDRELCTRTRN.TRDRMODIFDRELCTREVT.DRELCTRP;
import mx.babel.bansefi.banksystem.personas.webservices.modificaotrotelfaxpersona.EjecutarResult;
import mx.babel.bansefi.banksystem.personas.webservices.modificaotrotelfaxpersona.ModificaOtroTelFaxPersonaServicio;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Clase BackEnd para la ejecución del servicio de Modificación de Otro Telefono/Fax de
 * Persona.
 * 
 */
@Component
public class ModificaOtroTelFaxPersonaBackend extends BackEndBean{

	private static final long serialVersionUID = 267710889264544337L;
	
	@Autowired
	private ServicioWebUtils servicioWebUtils;
	
	public void ejecutarTRN(final int idPersona, final OtroValorBean otroValorBean){
		final ITRDRMODIFDRELCTRTRN itrdrmodifdrelctrtrn = initPeticion(idPersona, otroValorBean);
		final EjecutarResult respuesta = ejecutarWS(itrdrmodifdrelctrtrn);
        super.verificaRespuesta(respuesta);
	}

	private ITRDRMODIFDRELCTRTRN initPeticion(final int idPersona, final OtroValorBean otroValorBean){
		final Ejecutar.ITRDRMODIFDRELCTRTRN itrdrmodifdrelctrtrn = new Ejecutar.ITRDRMODIFDRELCTRTRN();

		super.initialize(itrdrmodifdrelctrtrn);

		final TRDRMODIFDRELCTREVT trdrmodifdrelctrevt = itrdrmodifdrelctrtrn.getTRDRMODIFDRELCTREVT();
		trdrmodifdrelctrevt.setIDINTERNOPE(idPersona);
	    trdrmodifdrelctrevt.setCODDIRELCTR(otroValorBean.getClave());
		if(StringUtils.equalsIgnoreCase(ConstantesFuncionales.DIR_INTERNET.getClaveFila(), otroValorBean.getClave())){
		    trdrmodifdrelctrevt.setTXTELCTRDRAMPL(otroValorBean.getValor());
            trdrmodifdrelctrevt.setVALORELCTRDR(otroValorBean.getDescripcion());
            trdrmodifdrelctrevt.setVALORELCTRDRLEN(otroValorBean.getDescripcion().length());
		}else{
    		trdrmodifdrelctrevt.setTXTELCTRDR(otroValorBean.getValor());
            trdrmodifdrelctrevt.setVALORELCTRDR(otroValorBean.getNumero());
            trdrmodifdrelctrevt.setVALORELCTRDRLEN(otroValorBean.getNumero().length());
		}
		
		trdrmodifdrelctrevt.setCODPERSRLDIR("07");
        trdrmodifdrelctrevt.setCODDIR("2");
        
        final DRELCTRP drelctrp = itrdrmodifdrelctrtrn.getTRDRMODIFDRELCTREVT().getDRELCTRP();
        drelctrp.setCODNRBEEN(super.getEntidad());
        drelctrp.setNUMDIR(otroValorBean.getIdInterno());

        final STDTRNIPARMV stdtrniparmv = itrdrmodifdrelctrtrn.getSTDTRNIPARMV();
        stdtrniparmv.setCODTX(CodTxConstants.COD_TX_TR_DR_MODIF_DR_ELCTR_TRN);
        stdtrniparmv.setIDINTERNOTERMTN(this.getTerminal());

		return itrdrmodifdrelctrtrn;
	}

	/**
	 * Función para invocar al servicio web y obtener su respuesta.
	 *
	 * @param contexto Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	private EjecutarResult ejecutarWS(final Ejecutar.ITRDRMODIFDRELCTRTRN contexto){
		EjecutarResult respuesta = null;

		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(ModificaOtroTelFaxPersonaServicio.class, contexto);
		}catch(final NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de modificación "
					+ "de otro telefono/fax de persona.", e);
		}
		return respuesta;
	}

}
