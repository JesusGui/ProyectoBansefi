package mx.babel.bansefi.banksystem.personas.backend;

import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.domain.PersonaMoralAccionistaBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.personas.webservices.modificacionaccionistafuncionario.Ejecutar.IPEMODIORGACCFUNTRN;
import mx.babel.bansefi.banksystem.personas.webservices.modificacionaccionistafuncionario.EjecutarResult;
import mx.babel.bansefi.banksystem.personas.webservices.modificacionaccionistafuncionario.ModificaRelacionClienteAccionistaFuncionarioServicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Backend para ejecutar la TRN de modificación de una relación cliente
 * accionista.
 * 
 * @author omar.marquez
 * 
 */
@Component
public class ModificaRelacionClienteAccionistaBackEnd extends BackEndBean {

	private static final long serialVersionUID = -2863509691735722564L;

	@Autowired
	ServicioWebUtils servicioWebUtils;

	/**
	 * Constructor.
	 */
	public ModificaRelacionClienteAccionistaBackEnd() {
		super();
	}

	/**
	 * Método que ejecuta la TRN de modificación de una relación tipo
	 * cliente-accionista.
	 * 
	 * @param idInternoPersona
	 * @param accionista
	 */
	public void ejecutarTRN(Integer idInternoPersona,
			PersonaMoralAccionistaBean accionista){
		IPEMODIORGACCFUNTRN ipemodiorgaccfuntrn = initPeticion(
				idInternoPersona, accionista);

		EjecutarResult resultado = (EjecutarResult) servicioWebUtils
				.ejecutarWS(ModificaRelacionClienteAccionistaFuncionarioServicio.class,
						ipemodiorgaccfuntrn);

		super.verificaRespuesta(resultado);
	}

	/**
	 * Método privado que prepara los datos que serán enviados a la TRN.
	 * 
	 * @param idInternoPersona
	 * @param accionista
	 * @return parametros de entrada encapsulados en un objeto
	 *         IPEMODIORGACCFUNTRN
	 */
	private IPEMODIORGACCFUNTRN initPeticion(Integer idInternoPersona,
			PersonaMoralAccionistaBean accionista){
		IPEMODIORGACCFUNTRN ipemodiorgaccfuntrn = new IPEMODIORGACCFUNTRN();

		super.initialize(ipemodiorgaccfuntrn);

		ipemodiorgaccfuntrn.getPEMODIORGACCFUNEVTY().getPEPERSRLACCFUNP()
				.setCODNRBEEN(super.getEntidad());
		ipemodiorgaccfuntrn.getPEMODIORGACCFUNEVTY().getPEPERSRLACCFUNP()
				.setIDINTERNOPE(idInternoPersona);
		ipemodiorgaccfuntrn.getPEMODIORGACCFUNEVTY().getPEPERSRLACCFUNP()
				.setNUMSEC(accionista.getIdInterno());

		ipemodiorgaccfuntrn.getPEMODIORGACCFUNEVTY().getPEPERSRLACCFUNE()
				.setCODNRBEEN(super.getEntidad());
		ipemodiorgaccfuntrn.getPEMODIORGACCFUNEVTY().getPEPERSRLACCFUNE()
				.setIDINTERNOPE(idInternoPersona);
		ipemodiorgaccfuntrn.getPEMODIORGACCFUNEVTY().getPEPERSRLACCFUNE()
				.setNUMSEC(accionista.getIdInterno());
		ipemodiorgaccfuntrn.getPEMODIORGACCFUNEVTY().getPEPERSRLACCFUNE()
				.setNOMBINNOMBPILA(accionista.getNombre());
		ipemodiorgaccfuntrn.getPEMODIORGACCFUNEVTY().getPEPERSRLACCFUNE()
				.setNOMBINAPE1IN(accionista.getApellidoPaterno());
		ipemodiorgaccfuntrn.getPEMODIORGACCFUNEVTY().getPEPERSRLACCFUNE()
				.setNOMBINAPE2IN(accionista.getApellidoMaterno());
		ipemodiorgaccfuntrn.getPEMODIORGACCFUNEVTY().getPEPERSRLACCFUNE()
				.setIDRFC(accionista.getRfc());
		ipemodiorgaccfuntrn.getPEMODIORGACCFUNEVTY().getPEPERSRLACCFUNE()
				.setPCTACCNRIO(accionista.getPorcentaje());

		ipemodiorgaccfuntrn.getSTDTRNIPARMV().setIDINTERNOTERMTN(
				super.getTerminal());
		ipemodiorgaccfuntrn.getSTDTRNIPARMV().setCODTX(
				CodTxConstants.COD_TX_PE_CONS_ORG_PERF_TRAN_TRN);

		return ipemodiorgaccfuntrn;
	}

}