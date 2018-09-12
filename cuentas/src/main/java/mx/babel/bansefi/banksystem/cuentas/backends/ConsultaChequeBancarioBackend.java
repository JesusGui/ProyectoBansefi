package mx.babel.bansefi.banksystem.cuentas.backends;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.ChequeBancarioBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultachequebancario.Ejecutar.ITRCONSULTACHEQUE2TRN;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultachequebancario.EjecutarResult;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultachequebancario.ConsultaChequeBancarioServicio;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultachequebancario.ResponseBansefi.OTRCONSULTACHEQUE2TRN.TRCONSULTACHEQUE2EVT;
import mx.babel.bansefi.banksystem.cuentas.wrappers.ConsultaApunteWrapper;

/**
 * Backend para la TRN de consulta datos de origen de un cheque bancario CJ
 * (TR_CONSULTA_CHEQUE_2_TRN).
 * 
 * @author omar.marquez
 */
@Component
public class ConsultaChequeBancarioBackend extends BackEndBean {

	private static final long serialVersionUID = -8295627349870476870L;

	@Autowired
	ServicioWebUtils servicioWebUtils;

	@Autowired
	ConsultaApunteWrapper consultaApunteWrapper;

	/**
	 * Constructor.
	 */
	public ConsultaChequeBancarioBackend() {
		super();
	}

	/**
	 * Método que ejecuta la TRN de consulta de datos de origen de un cheque
	 * bancario CJ.
	 * 
	 * @param numSecCj
	 * @return chequeBancarioBean
	 * @throws NullPointerException
	 * @throws ControlableException
	 * @throws NoControlableException
	 */
	public ChequeBancarioBean ejecutarTRN(Integer numSecCj)
			throws NullPointerException, ControlableException,
			NoControlableException {
		ITRCONSULTACHEQUE2TRN itrconsultacheque2trn = initPeticion(numSecCj);

		EjecutarResult resultado = (EjecutarResult) servicioWebUtils
				.ejecutarWS(ConsultaChequeBancarioServicio.class,
						itrconsultacheque2trn);

		super.verificaResponseBansefi(resultado.getResponseBansefi());

		TRCONSULTACHEQUE2EVT elemento = resultado.getResponseBansefi()
				.getOTRCONSULTACHEQUE2TRN().getTRCONSULTACHEQUE2EVT();

		return consultaApunteWrapper
				.consultaOrigenChequeBancarioWrapper(elemento);
	}

	/**
	 * Método privado que prepara los datos que serán enviados a la TRN.
	 * 
	 * @param numSecCj
	 * @return parametros de entrada encapsulados en un objeto
	 *         ITRCONSULTACHEQUE2TRN
	 */
	private ITRCONSULTACHEQUE2TRN initPeticion(Integer numSecCj) {
		ITRCONSULTACHEQUE2TRN itrconsultacheque2trn = new ITRCONSULTACHEQUE2TRN();

		super.initialize(itrconsultacheque2trn);

		itrconsultacheque2trn.getSTDTRNIPARMV().setIDINTERNOTERMTN(
				super.getTerminal());
		itrconsultacheque2trn.getSTDTRNIPARMV().setCODTX(
				CodTxConstants.COD_TX_TR_CONSULTA_CHEQUE_2_TRN);

		itrconsultacheque2trn.getTRCONSULTACHEQUEEVTY().getCJCHQPAGARECOMPP()
				.setCODNRBEEN(super.getEntidad());
		itrconsultacheque2trn.getTRCONSULTACHEQUEEVTY().getCJCHQPAGARECOMPP()
				.setNUMSECCJ(numSecCj);

		return itrconsultacheque2trn;
	}

}