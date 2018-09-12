package mx.babel.bansefi.banksystem.personas.backend;

import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.personas.webservices.bajareferenciacomercial.BajaRelacionClienteRefComercialServicio;
import mx.babel.bansefi.banksystem.personas.webservices.bajareferenciacomercial.Ejecutar.IPEBAJAORGREFCOMTRN;
import mx.babel.bansefi.banksystem.personas.webservices.bajareferenciacomercial.EjecutarResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Backend para la TRN de baja de una relación tipo cliente referencia
 * comercial.
 * 
 * @author omar.marquez
 * 
 */
@Component
public class BajaRelacionClienteRefComercialBackEnd extends BackEndBean {

	private static final long serialVersionUID = -6298814592382672271L;

	@Autowired
	ServicioWebUtils servicioWebUtils;

	/**
	 * Método que ejecuta la TRN de baja de una relación tipo cliente-referencia
	 * comercial.
	 * 
	 * @param idInternoPersona
	 * @param numSecRefComercial
	 */
	public void ejecutarTRN(Integer idInternoPersona, Integer numSecRefComercial){
		IPEBAJAORGREFCOMTRN ipebajaorgrefcomtrn = new IPEBAJAORGREFCOMTRN();

		super.initialize(ipebajaorgrefcomtrn);

		ipebajaorgrefcomtrn.getPEBAJAORGREFCOMEVTY().getPEPERSRLREFCOMP().setCODNRBEEN(super.getEntidad());
		ipebajaorgrefcomtrn.getPEBAJAORGREFCOMEVTY().getPEPERSRLREFCOMP().setIDINTERNOPE(idInternoPersona);
		ipebajaorgrefcomtrn.getPEBAJAORGREFCOMEVTY().getPEPERSRLREFCOMP().setNUMSEC(numSecRefComercial);

		ipebajaorgrefcomtrn.getSTDTRNIPARMV().setIDINTERNOTERMTN(super.getTerminal());
		ipebajaorgrefcomtrn.getSTDTRNIPARMV().setCODTX(CodTxConstants.COD_TX_PE_MODI_ORG_PERF_TRAN_TRN);

		EjecutarResult resultado = (EjecutarResult) servicioWebUtils
				.ejecutarWS(BajaRelacionClienteRefComercialServicio.class,ipebajaorgrefcomtrn);

		super.verificaRespuesta(resultado);
	}

}