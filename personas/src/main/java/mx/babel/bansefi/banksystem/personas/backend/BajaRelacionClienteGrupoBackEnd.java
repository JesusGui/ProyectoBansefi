package mx.babel.bansefi.banksystem.personas.backend;

import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.personas.webservices.relacioncliente.bajagrupo.BajaRelacionClienteGrupoServicio;
import mx.babel.bansefi.banksystem.personas.webservices.relacioncliente.bajagrupo.Ejecutar.ITRGRBAJARLPETRNI;
import mx.babel.bansefi.banksystem.personas.webservices.relacioncliente.bajagrupo.EjecutarResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Backend para la TRN de baja de una relación tipo cliente grupo.
 * 
 * @author omar.marquez
 * 
 */
@Component
public class BajaRelacionClienteGrupoBackEnd extends BackEndBean {

	private static final long serialVersionUID = 4915852891173548677L;

	@Autowired
	ServicioWebUtils servicioWebUtils;

	/**
	 * Método que ejecuta la TRN de baja de una relación cliente-grupo.
	 * 
	 * @param idInternoPersona
	 * @param idExternaGrupo
	 * @param codigoTipoGrupo
	 * @param motivoBaja
	 */
	public void ejecutarTRN(Integer idInternoPersona, String idExternaGrupo, String codigoTipoGrupo, String motivoBaja) {

		ITRGRBAJARLPETRNI itrgrbajarlpetrni = new ITRGRBAJARLPETRNI();

		super.initialize(itrgrbajarlpetrni);

		itrgrbajarlpetrni.getTRGRBAJARLPEEVTY().getGRGRPRLPERSP().setCODNRBEEN(super.getEntidad());
		itrgrbajarlpetrni.getTRGRBAJARLPEEVTY().getGRGRPRLPERSP().setCODGRP(codigoTipoGrupo);
		itrgrbajarlpetrni.getTRGRBAJARLPEEVTY().getGRGRPRLPERSP().setIDEXTGR(idExternaGrupo);
		itrgrbajarlpetrni.getTRGRBAJARLPEEVTY().getGRGRPRLPERSP().setIDINTERNOPE(idInternoPersona);

		itrgrbajarlpetrni.getTRGRBAJARLPEEVTY().setRZNBAJAPERLGR(motivoBaja);

		itrgrbajarlpetrni.getSTDTRNIPARMV().setIDINTERNOTERMTN(super.getTerminal());
		itrgrbajarlpetrni.getSTDTRNIPARMV().setCODTX(CodTxConstants.COD_TX_TR_GR_BAJA_RL_PE_TRN);

		EjecutarResult resultado = (EjecutarResult) servicioWebUtils
				.ejecutarWS(BajaRelacionClienteGrupoServicio.class,itrgrbajarlpetrni);

		super.verificaRespuesta(resultado);
	}

}