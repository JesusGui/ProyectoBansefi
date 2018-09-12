package mx.babel.bansefi.banksystem.personas.backend;

import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.domain.PersonaMoralRComercialBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.personas.webservices.modificacionreferenciacomercial.Ejecutar.IPEMODIORGREFCOMTRN;
import mx.babel.bansefi.banksystem.personas.webservices.modificacionreferenciacomercial.EjecutarResult;
import mx.babel.bansefi.banksystem.personas.webservices.modificacionreferenciacomercial.ModificaRelacionClienteRefComercialServicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Backend para ejecutar la TRN de modificación de una relación cliente
 * referencia comercial.
 * 
 * @author omar.marquez
 * 
 */
@Component
public class ModificaRelacionClienteRefComercialBackEnd extends BackEndBean {

	private static final long serialVersionUID = 5517359676701774635L;

	@Autowired
	ServicioWebUtils servicioWebUtils;

	/**
	 * Método que ejecuta la TRN de modificación de una relación tipo
	 * cliente-referencia comercial.
	 * 
	 * @param idInternoPersona
	 * @param refComercial
	 */
	public void ejecutarTRN(Integer idInternoPersona,
			PersonaMoralRComercialBean refComercial){
		IPEMODIORGREFCOMTRN ipemodiorgrefcomtrn = initPeticion(idInternoPersona, refComercial);

		EjecutarResult resultado = (EjecutarResult) servicioWebUtils
				.ejecutarWS(ModificaRelacionClienteRefComercialServicio.class,
						ipemodiorgrefcomtrn);

		super.verificaRespuesta(resultado);
	}

	/**
	 * Método privado que prepara los datos que serán enviados a la TRN.
	 * 
	 * @param idInternoPersona
	 * @param refComercial
	 * @return parametros de entrada encapsulados en un objeto
	 *         IPEMODIORGREFCOMTRN
	 */
	private IPEMODIORGREFCOMTRN initPeticion(Integer idInternoPersona,
			PersonaMoralRComercialBean refComercial){
		IPEMODIORGREFCOMTRN ipemodiorgrefcomtrn = new IPEMODIORGREFCOMTRN();

		super.initialize(ipemodiorgrefcomtrn);

		ipemodiorgrefcomtrn.getPEMODIORGREFCOMEVTY().getPEPERSRLREFCOMP()
				.setCODNRBEEN(super.getEntidad());
		ipemodiorgrefcomtrn.getPEMODIORGREFCOMEVTY().getPEPERSRLREFCOMP()
				.setIDINTERNOPE(idInternoPersona);
		ipemodiorgrefcomtrn.getPEMODIORGREFCOMEVTY().getPEPERSRLREFCOMP()
				.setNUMSEC(refComercial.getIdInterno());

		ipemodiorgrefcomtrn.getPEMODIORGREFCOMEVTY().getPEPERSRLREFCOME()
				.setCODNRBEEN(super.getEntidad());
		ipemodiorgrefcomtrn.getPEMODIORGREFCOMEVTY().getPEPERSRLREFCOME()
				.setIDINTERNOPE(idInternoPersona);
		ipemodiorgrefcomtrn.getPEMODIORGREFCOMEVTY().getPEPERSRLREFCOME()
				.setNUMSEC(refComercial.getIdInterno());
		ipemodiorgrefcomtrn.getPEMODIORGREFCOMEVTY().getPEPERSRLREFCOME()
				.setNOMB50(refComercial.getNombre());
		ipemodiorgrefcomtrn.getPEMODIORGREFCOMEVTY().getPEPERSRLREFCOME()
				.setGIRO(refComercial.getGiro());
		ipemodiorgrefcomtrn.getPEMODIORGREFCOMEVTY().getPEPERSRLREFCOME()
				.setDOMIC50(refComercial.getDomicilio());
		ipemodiorgrefcomtrn.getPEMODIORGREFCOMEVTY().getPEPERSRLREFCOME()
				.setTELEFONO(refComercial.getTelefono());
		ipemodiorgrefcomtrn.getPEMODIORGREFCOMEVTY().getPEPERSRLREFCOME()
				.setPERSRLTIT(refComercial.getRelacion());

		ipemodiorgrefcomtrn.getSTDTRNIPARMV().setIDINTERNOTERMTN(
				super.getTerminal());
		ipemodiorgrefcomtrn.getSTDTRNIPARMV().setCODTX(
				CodTxConstants.COD_TX_PE_CONS_ORG_PERF_TRAN_TRN);

		return ipemodiorgrefcomtrn;
	}

}