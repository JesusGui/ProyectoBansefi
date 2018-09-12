package mx.babel.bansefi.banksystem.personas.backend;

import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.personas.webservices.relacioncliente.altagrupo.AltaRelacionClienteGrupoServicio;
import mx.babel.bansefi.banksystem.personas.webservices.relacioncliente.altagrupo.Ejecutar.ITRGRALTARLPETRNI;
import mx.babel.bansefi.banksystem.personas.webservices.relacioncliente.altagrupo.EjecutarResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Backend para la TRN de alta de una relación tipo cliente grupo.
 * 
 * @author omar.marquez
 * 
 */
@Component
public class AltaRelacionClienteGrupoBackEnd extends BackEndBean {

	private static final long serialVersionUID = -11514509930261635L;

	@Autowired
	ServicioWebUtils servicioWebUtils;

	/**
	 * Método que ejecuta la TRN de alta de una relación tipo cliente-grupo.
	 * 
	 * @param idInternaPersona
	 * @param idExternaGrupo
	 * @param codigoGrupo
	 */
	public void ejecutarTRN(Integer idInternaPersona, String idExternaGrupo,
			String codigoGrupo){
		ITRGRALTARLPETRNI itrgraltarlpetrni = initPeticion(idInternaPersona,
				idExternaGrupo, codigoGrupo);

		EjecutarResult resultado = (EjecutarResult) servicioWebUtils
				.ejecutarWS(AltaRelacionClienteGrupoServicio.class,
						itrgraltarlpetrni);

		super.verificaRespuesta(resultado);
	}

	/**
	 * Método privado que prepara los datos que serán enviados a la TRN.
	 * 
	 * @param idInternaPersona
	 * @param idExternaGrupo
	 * @param codigoGrupo
	 * @return parametros de entrada encapsulados en un objeto ITRGRALTARLPETRNI
	 */
	private ITRGRALTARLPETRNI initPeticion(Integer idInternaPersona,
			String idExternaGrupo, String codigoGrupo){
		ITRGRALTARLPETRNI itrgraltarlpetrni = new ITRGRALTARLPETRNI();

		super.initialize(itrgraltarlpetrni);

		itrgraltarlpetrni.getTRGRALTARLPEEVTY()
				.setCODNRBEEN(super.getEntidad());
		itrgraltarlpetrni.getTRGRALTARLPEEVTY().setCODGRP(codigoGrupo);
		itrgraltarlpetrni.getTRGRALTARLPEEVTY().setIDEXTGR(idExternaGrupo);
		itrgraltarlpetrni.getTRGRALTARLPEEVTY()
				.setIDINTERNOPE(idInternaPersona);

		itrgraltarlpetrni.getSTDTRNIPARMV().setIDINTERNOTERMTN(
				super.getTerminal());
		itrgraltarlpetrni.getSTDTRNIPARMV().setCODTX(
				CodTxConstants.COD_TX_TR_GR_ALTA_RL_PE_TRN);
		
		return itrgraltarlpetrni;
	}

}