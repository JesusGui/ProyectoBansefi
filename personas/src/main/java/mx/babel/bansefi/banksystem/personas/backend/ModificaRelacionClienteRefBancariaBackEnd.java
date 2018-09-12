package mx.babel.bansefi.banksystem.personas.backend;

import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.domain.PersonaMoralRBancariaBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.personas.webservices.modificacionreferenciabancaria.Ejecutar.IPEMODIORGREFBANTRN;
import mx.babel.bansefi.banksystem.personas.webservices.modificacionreferenciabancaria.EjecutarResult;
import mx.babel.bansefi.banksystem.personas.webservices.modificacionreferenciabancaria.ModificaRelacionClienteRefBancariaServicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Backend para ejecutar la TRN de modificación de una relación cliente
 * referencia bancaria.
 * 
 * @author omar.marquez
 * 
 */
@Component
public class ModificaRelacionClienteRefBancariaBackEnd extends BackEndBean {

	private static final long serialVersionUID = -4457494258505244447L;

	@Autowired
	ServicioWebUtils servicioWebUtils;

	/**
	 * Método que ejecuta la TRN de modificación de una relación tipo
	 * cliente-referencia bancaria.
	 * 
	 * @param idInternoPersona
	 * @param refBancaria
	 */
	public void ejecutarTRN(Integer idInternoPersona,
			PersonaMoralRBancariaBean refBancaria){
		IPEMODIORGREFBANTRN ipemodiorgrefbantrn = initPeticion(
				idInternoPersona, refBancaria);

		EjecutarResult resultado = (EjecutarResult) servicioWebUtils
				.ejecutarWS(ModificaRelacionClienteRefBancariaServicio.class,
						ipemodiorgrefbantrn);

		super.verificaRespuesta(resultado);
	}

	/**
	 * Método privado que prepara los datos que serán enviados a la TRN.
	 * 
	 * @param idInternoPersona
	 * @param refBancaria
	 * @return parametros de entrada encapsulados en un objeto
	 *         IPEMODIORGREFBANTRN
	 */
	private IPEMODIORGREFBANTRN initPeticion(Integer idInternoPersona,
			PersonaMoralRBancariaBean refBancaria){
		IPEMODIORGREFBANTRN ipemodiorgrefbantrn = new IPEMODIORGREFBANTRN();

		super.initialize(ipemodiorgrefbantrn);

		ipemodiorgrefbantrn.getPEMODIORGREFBANEVTY().getPEPERSRLREFBANP()
				.setCODNRBEEN(super.getEntidad());
		ipemodiorgrefbantrn.getPEMODIORGREFBANEVTY().getPEPERSRLREFBANP()
				.setIDINTERNOPE(idInternoPersona);
		ipemodiorgrefbantrn.getPEMODIORGREFBANEVTY().getPEPERSRLREFBANP()
				.setNUMSEC(refBancaria.getIdInterno());

		ipemodiorgrefbantrn.getPEMODIORGREFBANEVTY().getPEPERSRLREFBANE()
				.setCODNRBEEN(super.getEntidad());
		ipemodiorgrefbantrn.getPEMODIORGREFBANEVTY().getPEPERSRLREFBANE()
				.setIDINTERNOPE(idInternoPersona);
		ipemodiorgrefbantrn.getPEMODIORGREFBANEVTY().getPEPERSRLREFBANE()
				.setNUMSEC(refBancaria.getIdInterno());
		ipemodiorgrefbantrn.getPEMODIORGREFBANEVTY().getPEPERSRLREFBANE()
				.setBANCO(refBancaria.getBanco());
		ipemodiorgrefbantrn.getPEMODIORGREFBANEVTY().getPEPERSRLREFBANE()
				.setNUMCTACHAR(refBancaria.getNumCuenta());
		ipemodiorgrefbantrn.getPEMODIORGREFBANEVTY().getPEPERSRLREFBANE()
				.setTIPOCUENTA(refBancaria.getTipoCuenta());

		ipemodiorgrefbantrn.getSTDTRNIPARMV().setIDINTERNOTERMTN(
				super.getTerminal());
		ipemodiorgrefbantrn.getSTDTRNIPARMV().setCODTX(
				CodTxConstants.COD_TX_PE_CONS_ORG_PERF_TRAN_TRN);

		return ipemodiorgrefbantrn;
	}

}