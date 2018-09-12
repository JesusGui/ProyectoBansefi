package mx.babel.bansefi.banksystem.personas.backend;

import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.personas.webservices.bajareferenciabancaria.BajaRelacionClienteRefBancariaServicio;
import mx.babel.bansefi.banksystem.personas.webservices.bajareferenciabancaria.Ejecutar.IPEBAJAORGREFBANTRN;
import mx.babel.bansefi.banksystem.personas.webservices.bajareferenciabancaria.EjecutarResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Backend para la TRN de baja de una relación tipo cliente referencia bancaria.
 * 
 * @author omar.marquez
 * 
 */
@Component
public class BajaRelacionClienteRefBancariaBackEnd extends BackEndBean {

	private static final long serialVersionUID = 7866980788173572426L;

	@Autowired
	ServicioWebUtils servicioWebUtils;

	/**
	 * Constructor.
	 */
	public BajaRelacionClienteRefBancariaBackEnd() {
		super();
	}

	/**
	 * Método que ejecuta la TRN de baja de una relación tipo cliente-referencia
	 * bancaria.
	 * 
	 * @param idInternoPersona
	 * @param numSecRefBancaria
	 */
	public void ejecutarTRN(Integer idInternoPersona, Integer numSecRefBancaria){
		IPEBAJAORGREFBANTRN ipebajaorgrefbantrn = new IPEBAJAORGREFBANTRN();

		super.initialize(ipebajaorgrefbantrn);

		ipebajaorgrefbantrn.getPEBAJAORGREFBANEVTY().getPEPERSRLREFBANP().setCODNRBEEN(super.getEntidad());
		ipebajaorgrefbantrn.getPEBAJAORGREFBANEVTY().getPEPERSRLREFBANP().setIDINTERNOPE(idInternoPersona);
		ipebajaorgrefbantrn.getPEBAJAORGREFBANEVTY().getPEPERSRLREFBANP().setNUMSEC(numSecRefBancaria);

		ipebajaorgrefbantrn.getSTDTRNIPARMV().setIDINTERNOTERMTN(super.getTerminal());
		ipebajaorgrefbantrn.getSTDTRNIPARMV().setCODTX(CodTxConstants.COD_TX_PE_MODI_ORG_PERF_TRAN_TRN);

		EjecutarResult resultado = (EjecutarResult) servicioWebUtils
				.ejecutarWS(BajaRelacionClienteRefBancariaServicio.class,ipebajaorgrefbantrn);

		super.verificaRespuesta(resultado);
	}

}