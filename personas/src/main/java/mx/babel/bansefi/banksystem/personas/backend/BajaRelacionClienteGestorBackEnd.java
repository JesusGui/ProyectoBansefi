package mx.babel.bansefi.banksystem.personas.backend;

import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.personas.webservices.relacioncliente.bajagestor.BajaRelacionClienteGestorServicio;
import mx.babel.bansefi.banksystem.personas.webservices.relacioncliente.bajagestor.Ejecutar.ITRPEBAJARLGTTRNI;
import mx.babel.bansefi.banksystem.personas.webservices.relacioncliente.bajagestor.EjecutarResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Backend para la TRN de baja de una relación tipo cliente-gestor.
 * 
 * @author omar.marquez
 * 
 */
@Component
public class BajaRelacionClienteGestorBackEnd extends BackEndBean {

	private static final long serialVersionUID = 1978232017057653020L;

	@Autowired
	ServicioWebUtils servicioWebUtils;

	/**
	 * Método que ejecuta la TRN de baja de una relación cliente-gestor.
	 * 
	 * @param idInternoPersona
	 * @param idInternoGestor
	 * @param motivoBaja
	 */
	public void ejecutarTRN(Integer idInternoPersona, Integer idInternoGestor, String motivoBaja){
		ITRPEBAJARLGTTRNI itrpebajarlgttrni = new ITRPEBAJARLGTTRNI();

		super.initialize(itrpebajarlgttrni);

		itrpebajarlgttrni.getTRPEBAJARLGTEVTY().getPEPERSRLGESTORP().setCODNRBEEN(super.getEntidad());
		itrpebajarlgttrni.getTRPEBAJARLGTEVTY().getPEPERSRLGESTORP().setIDINTERNOPE(idInternoPersona);
		itrpebajarlgttrni.getTRPEBAJARLGTEVTY().getPEPERSRLGESTORP().setIDINTERNOPEGT(idInternoGestor);

		itrpebajarlgttrni.getTRPEBAJARLGTEVTY().setRZNECVGTRLPE(motivoBaja);

		itrpebajarlgttrni.getSTDTRNIPARMV().setIDINTERNOTERMTN(super.getTerminal());
		itrpebajarlgttrni.getSTDTRNIPARMV().setCODTX(CodTxConstants.COD_TR_PE_BAJA_RL_GT_TRN);

		EjecutarResult resultado = (EjecutarResult) servicioWebUtils
				.ejecutarWS(BajaRelacionClienteGestorServicio.class,itrpebajarlgttrni);

		super.verificaRespuesta(resultado);
	}

}