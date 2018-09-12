package mx.babel.bansefi.banksystem.personas.backend;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.personas.beans.CnaeBean;
import mx.babel.bansefi.banksystem.personas.webservices.bajaobjetosocialcnae.BajaObjetoSocialCnaeServicio;
import mx.babel.bansefi.banksystem.personas.webservices.bajaobjetosocialcnae.Ejecutar;
import mx.babel.bansefi.banksystem.personas.webservices.bajaobjetosocialcnae.EjecutarResult;
import mx.babel.bansefi.banksystem.personas.wrappers.ActividadEmpresarialWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Clase BackEnd para la ejecución del servicio de Baja
 * Objeto Social CNAE.
 * 
 */
@Component
public class BajaObjetoSocialCnaeBackEnd extends BackEndBean {
	
	private static final long serialVersionUID = -5666109993716562782L;

	@Autowired
	ServicioWebUtils servicioWebUtils;
	
	@Autowired
	private ActividadEmpresarialWrapper wrapperActividad;
	
	/**
	 * Método para ejecutar la TRN de baja de CNAE.
	 * 
	 * @param mercadoOrganizado
	 * @return int Código de retorno del servicio
	 */
	public int ejecutarTRN(CnaeBean cnae) throws NoControlableException,
			ControlableException {
		Ejecutar.ITRPEBAJACNAEOBJSOCT contexto = new Ejecutar.ITRPEBAJACNAEOBJSOCT();
		Ejecutar.ITRPEBAJACNAEOBJSOCT.TRPEBAJACNAEOBJSOCEV contextoEntrada = new Ejecutar.ITRPEBAJACNAEOBJSOCT.TRPEBAJACNAEOBJSOCEV();

		Ejecutar.ITRPEBAJACNAEOBJSOCT.TRPEBAJACNAEOBJSOCEV.PEOTROCNAEP contextoEntradaCampos = new Ejecutar.ITRPEBAJACNAEOBJSOCT.TRPEBAJACNAEOBJSOCEV.PEOTROCNAEP();
		cnae.setEntidad(super.getEntidad());
		contextoEntradaCampos = wrapperActividad.wrapperBajaObjetoSocialCnae(cnae);
		contextoEntrada.setPEOTROCNAEP(contextoEntradaCampos);

		Ejecutar.ITRPEBAJACNAEOBJSOCT.TRPEBAJACNAEOBJSOCEV.PEOTROCNAEV contextoCamposVacios = new Ejecutar.ITRPEBAJACNAEOBJSOCT.TRPEBAJACNAEOBJSOCEV.PEOTROCNAEV();
		contextoCamposVacios.setCODCNAEPERS("");
		contextoCamposVacios.setDESCRCNAEPERS("");
		contextoEntrada.setPEOTROCNAEV(contextoCamposVacios);

		Ejecutar.ITRPEBAJACNAEOBJSOCT.STDTRNIPARMV contextoComun = new Ejecutar.ITRPEBAJACNAEOBJSOCT.STDTRNIPARMV();
		contextoComun.setIDINTERNOTERMTN(super.getTerminal());
		contextoComun.setIDEMPLAUT("");
		contextoComun.setNUMSEC(0);
		contextoComun.setCODTX(CodTxConstants.COD_TX_TR_PE_BAJA_CNAE_OBJSOC_TRN);
		contextoComun.setCODTXDI("");
				
		contexto.setSTDTRNIPARMV(contextoComun);
		contexto.setTRPEBAJACNAEOBJSOCEV(contextoEntrada);

		EjecutarResult respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(BajaObjetoSocialCnaeServicio.class, contexto);
		
		super.verificaRespuesta(respuesta);

		return respuesta.getResponseBansefi().getOTRPEBAJACNAEOBJSOCT().getRTRNCD();

	}

}
