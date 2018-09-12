package mx.babel.bansefi.banksystem.contabilidad.backends;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.contabilidad.webservices.cierrecontable.CierreContableServicio;
import mx.babel.bansefi.banksystem.contabilidad.webservices.cierrecontable.EjecutarResult;
import mx.babel.bansefi.banksystem.contabilidad.webservices.cierrecontable.Ejecutar.ITRCIERREOFICINATRNI;

/**
 * Backend que ejecuta la TRN para realizar el cierre contable de oficina.
 * 
 * @author omar.marquez
 * 
 */
@Component
public class CierreContableBackend extends BackEndBean {

	private static final long serialVersionUID = -8434085844712204562L;

	@Autowired
	ServicioWebUtils servicioWebUtils;

	/**
	 * Constructor.
	 */
	public CierreContableBackend() {
		super();
	}

	/**
	 * Método que ejecuta la TRN de cierre contable de oficina.
	 * 
	 * @return código de retorno
	 * @throws NullPointerException
	 * @throws ControlableException
	 * @throws NoControlableException
	 */
	public int ejecutarTRN() throws NullPointerException, ControlableException,
			NoControlableException {
		ITRCIERREOFICINATRNI itrcierreoficinatrni = initPeticion();

		EjecutarResult resultado = (EjecutarResult) servicioWebUtils
				.ejecutarWS(CierreContableServicio.class, itrcierreoficinatrni);

		super.verificaRespuesta(resultado);

		return resultado.getResponseBansefi().getOTRCIERREOFICINATRNO()
				.getRTRNCD();
	}

	/**
	 * Método privado que prepara los datos que serán enviados a la TRN.
	 * 
	 * @return parametros de entrada encapsulados en un objeto
	 *         ITRCIERREOFICINATRNI
	 * @throws NullPointerException
	 */
	private ITRCIERREOFICINATRNI initPeticion() throws NullPointerException {
		ITRCIERREOFICINATRNI itrcierreoficinatrni = new ITRCIERREOFICINATRNI();

		super.initialize(itrcierreoficinatrni);

		itrcierreoficinatrni.getSTDTRNIPARMV().setIDINTERNOTERMTN(
				super.getTerminal());
		itrcierreoficinatrni.getSTDTRNIPARMV().setCODTX(
				CodTxConstants.COD_TX_TR_CIERRE_OFICINA_TRN);

		itrcierreoficinatrni.getTRCIERREOFICINAEVTY().setCODNRBEEN(
				super.getEntidad());
		itrcierreoficinatrni.getTRCIERREOFICINAEVTY().setCODINTERNOUO(
				super.getSucursal());

		return itrcierreoficinatrni;
	}

}