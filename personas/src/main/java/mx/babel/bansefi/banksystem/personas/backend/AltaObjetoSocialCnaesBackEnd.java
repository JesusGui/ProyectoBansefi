package mx.babel.bansefi.banksystem.personas.backend;

import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.personas.beans.CnaeBean;
import mx.babel.bansefi.banksystem.personas.webservices.altaobjetosocialcnae.AltaObjetoSocialCnaeServicio;
import mx.babel.bansefi.banksystem.personas.webservices.altaobjetosocialcnae.Ejecutar;
import mx.babel.bansefi.banksystem.personas.webservices.altaobjetosocialcnae.EjecutarResult;
import mx.babel.bansefi.banksystem.personas.wrappers.ActividadEmpresarialWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Clase BackEnd para ejecutar el servicio de alta de Mercados Organizados.
 * 
 * @author alejandro.pineda
 * 
 */
@Component
public class AltaObjetoSocialCnaesBackEnd extends BackEndBean {
	
	private static final long serialVersionUID = 3538831426526407774L;

	@Autowired
	ServicioWebUtils servicioWebUtils;
	
	@Autowired
	private ActividadEmpresarialWrapper wrapperActividad;

	/**
	 * Método para ejecutar la TRN de alta de CNAE's.
	 * 
	 * @param mercadoOrganizado
	 * @return int Código de retorno del servicio
	 */
	public int ejecutarTRN(CnaeBean cnae){
		
		Ejecutar.ITRPEAMPLICNAEOBJSOC contextoEntrada = new Ejecutar.ITRPEAMPLICNAEOBJSOC();
		Ejecutar.ITRPEAMPLICNAEOBJSOC.TRPEAMPLICNAEOBJSOCE contextoEntradaCampos = null;
		cnae.setEntidad(super.getEntidad());
		contextoEntradaCampos = wrapperActividad.wrapperAltaObjetoSocialCnae(cnae);

		Ejecutar.ITRPEAMPLICNAEOBJSOC.STDTRNIPARMV contextoComun = new Ejecutar.ITRPEAMPLICNAEOBJSOC.STDTRNIPARMV();
		contextoComun.setIDINTERNOTERMTN(super.getTerminal());
		contextoComun.setIDEMPLAUT("");
		contextoComun.setNUMSEC(0);
		contextoComun.setCODTX(CodTxConstants.COD_TX_TR_PE_AMPLI_CNAE_OBJSOC_TRN);
		contextoComun.setCODTXDI("");
				
		contextoEntrada.setSTDTRNIPARMV(contextoComun);
		contextoEntrada.setTRPEAMPLICNAEOBJSOCE(contextoEntradaCampos);
		
		// Se ejecuta el WebService correspondiente
		EjecutarResult respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(AltaObjetoSocialCnaeServicio.class, contextoEntrada);
		
		super.verificaRespuesta(respuesta);
		
		return respuesta.getResponseBansefi().getOTRPEAMPLICNAEOBJSOC().getRTRNCD();

	}

}
