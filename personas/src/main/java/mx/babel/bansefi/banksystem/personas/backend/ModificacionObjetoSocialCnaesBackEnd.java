package mx.babel.bansefi.banksystem.personas.backend;

import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.personas.beans.CnaeBean;
import mx.babel.bansefi.banksystem.personas.webservices.modificacionobjetosocialcnae.Ejecutar;
import mx.babel.bansefi.banksystem.personas.webservices.modificacionobjetosocialcnae.Ejecutar.ITRPEMODICNAEOBJSOCT;
import mx.babel.bansefi.banksystem.personas.webservices.modificacionobjetosocialcnae.EjecutarResult;
import mx.babel.bansefi.banksystem.personas.webservices.modificacionobjetosocialcnae.ModificacionObjetoSocialCnaeServicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Clase BackEnd para el servicio de Modificación de Objeto Social.
 * @author alejandro.pineda
 *
 */
@Component
public class ModificacionObjetoSocialCnaesBackEnd extends BackEndBean {
	
	private static final long serialVersionUID = 4307988633328877684L;
	
	@Autowired
	ServicioWebUtils servicioWebUtils;
	
	/**
	 * Método para ejecutar la TRN de alta de CNAE's.
	 * 
	 * @param mercadoOrganizado
	 * @return int Código de retorno del servicio,
	 */
	public int ejecutarTRN(CnaeBean cnae){
		Ejecutar.ITRPEMODICNAEOBJSOCT contexto = new Ejecutar.ITRPEMODICNAEOBJSOCT();
		Ejecutar.ITRPEMODICNAEOBJSOCT.TRPEMODICNAEOBJSOCEV contextoEntradaCampos = new Ejecutar.ITRPEMODICNAEOBJSOCT.TRPEMODICNAEOBJSOCEV();
		Ejecutar.ITRPEMODICNAEOBJSOCT.TRPEMODICNAEOBJSOCEV.PEOBJSOCIALP contextoEntradaCamposUsuario = new Ejecutar.ITRPEMODICNAEOBJSOCT.TRPEMODICNAEOBJSOCEV.PEOBJSOCIALP();
		contextoEntradaCamposUsuario.setCODNRBEEN(super.getEntidad());
		contextoEntradaCamposUsuario.setIDINTERNOPE(cnae.getIdInterno());
		
		contextoEntradaCampos.setOBJSOCIALPELEN(cnae.getObjSocial().length());
		contextoEntradaCampos.setOBJSOCIALPE(cnae.getObjSocial());
		contextoEntradaCampos.setPEOBJSOCIALP(contextoEntradaCamposUsuario);

		Ejecutar.ITRPEMODICNAEOBJSOCT.STDTRNIPARMV contextoComun = new Ejecutar.ITRPEMODICNAEOBJSOCT.STDTRNIPARMV();
		contextoComun.setIDINTERNOTERMTN(super.getTerminal());
		contextoComun.setIDEMPLAUT("");
		contextoComun.setNUMSEC(0);
		contextoComun.setCODTX(CodTxConstants.COD_TX_TR_PE_MODI_CNAE_OBJSOC_TRN);
		contextoComun.setCODTXDI("");
		
		contexto.setSTDTRNIPARMV(contextoComun);
		contexto.setTRPEMODICNAEOBJSOCEV(contextoEntradaCampos);

		return this.obtenerRespuestaServicio(contexto);

	}

	/**
	 * Método para obtener codigo de respuesta del servicio de alta de CNAE's.
	 * 
	 * @param contexto
	 * @return int código retornado.
	 */
	public int obtenerRespuestaServicio(ITRPEMODICNAEOBJSOCT contexto){
		
		// Se ejecuta el WebService correspondiente
		EjecutarResult respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(ModificacionObjetoSocialCnaeServicio.class, contexto);

		super.verificaRespuesta(respuesta);

		return respuesta.getResponseBansefi().getOTRPEMODICNAEOBJSOCT().getRTRNCD();
	}
	
}
