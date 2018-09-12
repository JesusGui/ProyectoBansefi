package mx.babel.bansefi.banksystem.personas.backend;

import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.personas.webservices.bajaaccionistasfuncionarios.BajaRelacionClienteAccionistaFuncionarioServicio;
import mx.babel.bansefi.banksystem.personas.webservices.bajaaccionistasfuncionarios.Ejecutar.IPEBAJAORGACCFUNTRN;
import mx.babel.bansefi.banksystem.personas.webservices.bajaaccionistasfuncionarios.EjecutarResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Backend para la TRN de baja de una relación tipo cliente funcionario.
 * 
 * @author omar.marquez
 * 
 */
@Component
public class BajaRelacionClienteFuncionarioBackEnd extends BackEndBean {

	private static final long serialVersionUID = -3902805347428810564L;

	@Autowired
	ServicioWebUtils servicioWebUtils;

	/**
	 * Método que ejecuta la TRN de baja de una relación tipo
	 * cliente-accionista.
	 * 
	 * @param idInternoPersona
	 * @param numSecFuncionario
	 */
	public void ejecutarTRN(Integer idInternoPersona, Integer numSecFuncionario){
		IPEBAJAORGACCFUNTRN ipebajaorgaccfuntrn = new IPEBAJAORGACCFUNTRN();

		super.initialize(ipebajaorgaccfuntrn);

		ipebajaorgaccfuntrn.getPEBAJAORGACCFUNEVTY().getPEPERSRLACCFUNP().setCODNRBEEN(super.getEntidad());
		ipebajaorgaccfuntrn.getPEBAJAORGACCFUNEVTY().getPEPERSRLACCFUNP().setIDINTERNOPE(idInternoPersona);
		ipebajaorgaccfuntrn.getPEBAJAORGACCFUNEVTY().getPEPERSRLACCFUNP().setNUMSEC(numSecFuncionario);

		ipebajaorgaccfuntrn.getSTDTRNIPARMV().setIDINTERNOTERMTN(super.getTerminal());
		ipebajaorgaccfuntrn.getSTDTRNIPARMV().setCODTX(CodTxConstants.COD_TX_PE_MODI_ORG_PERF_TRAN_TRN);

		EjecutarResult resultado = (EjecutarResult) servicioWebUtils
				.ejecutarWS(BajaRelacionClienteAccionistaFuncionarioServicio.class,
						ipebajaorgaccfuntrn);

		super.verificaRespuesta(resultado);
	}

}