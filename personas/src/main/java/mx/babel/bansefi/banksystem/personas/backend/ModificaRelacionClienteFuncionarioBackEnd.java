package mx.babel.bansefi.banksystem.personas.backend;

import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.domain.PersonaMoralFuncionarioBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.personas.webservices.modificacionaccionistafuncionario.Ejecutar.IPEMODIORGACCFUNTRN;
import mx.babel.bansefi.banksystem.personas.webservices.modificacionaccionistafuncionario.EjecutarResult;
import mx.babel.bansefi.banksystem.personas.webservices.modificacionaccionistafuncionario.ModificaRelacionClienteAccionistaFuncionarioServicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Backend para ejecutar la TRN de modificación de una relación cliente
 * funcionario.
 * 
 * @author omar.marquez
 * 
 */
@Component
public class ModificaRelacionClienteFuncionarioBackEnd extends BackEndBean {

	private static final long serialVersionUID = -2863509691735722564L;

	@Autowired
	ServicioWebUtils servicioWebUtils;

	/**
	 * Constructor.
	 */
	public ModificaRelacionClienteFuncionarioBackEnd() {
		super();
	}

	/**
	 * Método que ejecuta la TRN de modificación de una relación tipo
	 * cliente-funcionario.
	 * 
	 * @param idInternoPersona
	 * @param funcionario
	 */
	public void ejecutarTRN(Integer idInternoPersona,
			PersonaMoralFuncionarioBean funcionario){
		IPEMODIORGACCFUNTRN ipemodiorgaccfuntrn = initPeticion(
				idInternoPersona, funcionario);

		EjecutarResult resultado = (EjecutarResult) servicioWebUtils
				.ejecutarWS(ModificaRelacionClienteAccionistaFuncionarioServicio.class,
						ipemodiorgaccfuntrn);

		super.verificaRespuesta(resultado);
	}

	/**
	 * Método privado que prepara los datos que serán enviados a la TRN.
	 * 
	 * @param idInternoPersona
	 * @param funcionario
	 * @return parametros de entrada encapsulados en un objeto
	 *         IPEMODIORGACCFUNTRN
	 * @throws NullPointerException
	 */
	private IPEMODIORGACCFUNTRN initPeticion(Integer idInternoPersona,
			PersonaMoralFuncionarioBean funcionario)
			throws NullPointerException {
		IPEMODIORGACCFUNTRN ipemodiorgaccfuntrn = new IPEMODIORGACCFUNTRN();

		super.initialize(ipemodiorgaccfuntrn);

		ipemodiorgaccfuntrn.getPEMODIORGACCFUNEVTY().getPEPERSRLACCFUNP()
				.setCODNRBEEN(super.getEntidad());
		ipemodiorgaccfuntrn.getPEMODIORGACCFUNEVTY().getPEPERSRLACCFUNP()
				.setIDINTERNOPE(idInternoPersona);
		ipemodiorgaccfuntrn.getPEMODIORGACCFUNEVTY().getPEPERSRLACCFUNP()
				.setNUMSEC(funcionario.getIdInterno());

		ipemodiorgaccfuntrn.getPEMODIORGACCFUNEVTY().getPEPERSRLACCFUNE()
				.setCODNRBEEN(super.getEntidad());
		ipemodiorgaccfuntrn.getPEMODIORGACCFUNEVTY().getPEPERSRLACCFUNE()
				.setIDINTERNOPE(idInternoPersona);
		ipemodiorgaccfuntrn.getPEMODIORGACCFUNEVTY().getPEPERSRLACCFUNE()
				.setNUMSEC(funcionario.getIdInterno());
		ipemodiorgaccfuntrn.getPEMODIORGACCFUNEVTY().getPEPERSRLACCFUNE()
				.setNOMBINNOMBPILA(funcionario.getNombre());
		ipemodiorgaccfuntrn.getPEMODIORGACCFUNEVTY().getPEPERSRLACCFUNE()
				.setNOMBINAPE1IN(funcionario.getApellidoPaterno());
		ipemodiorgaccfuntrn.getPEMODIORGACCFUNEVTY().getPEPERSRLACCFUNE()
				.setNOMBINAPE2IN(funcionario.getApellidoMaterno());
		ipemodiorgaccfuntrn.getPEMODIORGACCFUNEVTY().getPEPERSRLACCFUNE()
				.setIDRFC(funcionario.getRfc());
		ipemodiorgaccfuntrn.getPEMODIORGACCFUNEVTY().getPEPERSRLACCFUNE()
				.setPUESTO(funcionario.getPuesto());

		ipemodiorgaccfuntrn.getSTDTRNIPARMV().setIDINTERNOTERMTN(
				super.getTerminal());
		ipemodiorgaccfuntrn.getSTDTRNIPARMV().setCODTX(
				CodTxConstants.COD_TX_PE_CONS_ORG_PERF_TRAN_TRN);

		return ipemodiorgaccfuntrn;
	}

}