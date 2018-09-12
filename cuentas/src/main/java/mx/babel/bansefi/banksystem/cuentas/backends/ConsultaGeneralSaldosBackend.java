package mx.babel.bansefi.banksystem.cuentas.backends;

import java.util.ArrayList;
import java.util.List;

import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.domain.CuentaBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.constants.ConstantesFuncionales;
import mx.babel.bansefi.banksystem.base.utils.IntegerToDateConverter;
import mx.babel.bansefi.banksystem.cuentas.beans.SaldoCuentaBean;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultageneralsaldos.ConsultaGeneralSaldosServicio;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultageneralsaldos.Ejecutar.ITRCONSSALDOSGENTRNI;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultageneralsaldos.EjecutarResult;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultageneralsaldos.ResponseBansefi.OTRCONSSALDOSGENTRNO.CTCTASDOSV;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Backend para la TRN de consulta general de saldos (TR_CONS_SALDOS_GEN_TRN).
 * 
 * @author omar.marquez
 * 
 */
@Component
public class ConsultaGeneralSaldosBackend extends BackEndBean {

	private static final long serialVersionUID = 5124269732424985472L;

	// Constante para no tener que informar el COD_CTA de la cuenta.
	private static final String LIQ_SOLO_UNA_CTA = "N";

	private IntegerToDateConverter integerToDateConverter;

	@Autowired
	ServicioWebUtils servicioWebUtils;

	/**
	 * Constructor.
	 */
	public ConsultaGeneralSaldosBackend() {
		super();
		this.integerToDateConverter = new IntegerToDateConverter();
	}

	/**
	 * Método que ejecuta la TRN de consulta general de saldos.
	 * 
	 * @param cuentaBean
	 * @return lista de saldos
	 */
	public List<SaldoCuentaBean> ejecutarTRN(CuentaBean cuentaBean) {
		ITRCONSSALDOSGENTRNI itrconssaldosgentrni = initPeticion(cuentaBean);

		EjecutarResult resultado = (EjecutarResult) servicioWebUtils
				.ejecutarWS(ConsultaGeneralSaldosServicio.class,
						itrconssaldosgentrni);

		super.verificaRespuesta(resultado);

		List<CTCTASDOSV> elementos = resultado.getResponseBansefi()
				.getOTRCONSSALDOSGENTRNO().getCTCTASDOSV();

		return obtenerListaSaldos(elementos);
	}

	/**
	 * Método privado que prepara los datos que serán enviados a la TRN.
	 * 
	 * @param cuentaBean
	 * @return parametros de entrada encapsulados en un objeto
	 *         ITRCONSSALDOSGENTRNI
	 * @throws NullPointerException
	 */
	private ITRCONSSALDOSGENTRNI initPeticion(CuentaBean cuentaBean)
			throws NullPointerException {
		ITRCONSSALDOSGENTRNI itrconssaldosgentrni = new ITRCONSSALDOSGENTRNI();

		super.initialize(itrconssaldosgentrni);

		itrconssaldosgentrni.setELEVATORPOSITION(0);
		itrconssaldosgentrni.setSCROLLABLEOCCURS(SCROLLABLE_OCCURS);

		itrconssaldosgentrni.getLIQSOLOUNACTA().setOPCION(
				ConsultaGeneralSaldosBackend.LIQ_SOLO_UNA_CTA);

		itrconssaldosgentrni.getSDSDOE().setCODNRBEEN(super.getEntidad());
		itrconssaldosgentrni.getSDSDOE().setPRPDADCTA(
				ConstantesFuncionales.PRPDAD_CTA);
		itrconssaldosgentrni.getSDSDOE().setNUMSECAC(
				cuentaBean.getNumeroCuenta());
		itrconssaldosgentrni.getSDSDOE().setCODNUMRCOMONEDA(
				ConstantesFuncionales.COD_MONEDA);

		itrconssaldosgentrni.getSTDTRNIPARMV().setIDINTERNOTERMTN(
				super.getTerminal());
		itrconssaldosgentrni.getSTDTRNIPARMV().setCODTX(
				CodTxConstants.COD_TR_CONS_SALDOS_GEN_TRN);

		return itrconssaldosgentrni;
	}

	/**
	 * Método privado que devuelve una lista de saldos a partir del resultado de
	 * la consulta.
	 * 
	 * @param elementos
	 * @return lista de saldos
	 * @throws NullPointerException
	 */
	private List<SaldoCuentaBean> obtenerListaSaldos(List<CTCTASDOSV> elementos)
			throws NullPointerException {
		List<SaldoCuentaBean> saldos = new ArrayList<>();
		/*for (CTCTASDOSV elemento : elementos) {
			if (elemento != null && elemento.getCODSDO() != null
					&& !elemento.getCODSDO().trim().isEmpty()
					&& elemento.getCODCTA() != null
					&& !elemento.getCODCTA().trim().isEmpty()) {
				SaldoCuentaBean saldoCuentaBean = crearSaldoCuentaBean(elemento);
				saldos.add(saldoCuentaBean);
			}
		}*/
		for (int i = 0; i < elementos.size(); i++) {
			if (elementos.get(i) != null && elementos.get(i).getCODSDO() != null
					&& !elementos.get(i).getCODSDO().trim().isEmpty()
					&& elementos.get(i).getCODCTA() != null
					&& !elementos.get(i).getCODCTA().trim().isEmpty()) {
				SaldoCuentaBean saldoCuentaBean = crearSaldoCuentaBean(elementos.get(i));
				saldoCuentaBean.setNumSec(i + 1);
				saldos.add(saldoCuentaBean);
			}
		}
		return saldos;
	}

	/**
	 * Método privado que devuelve un objeto tipo SaldoCuentaBean.
	 * 
	 * @param elemento
	 * @return saldoCuentaBean
	 * @throws NullPointerException
	 */
	private SaldoCuentaBean crearSaldoCuentaBean(CTCTASDOSV elemento)
			throws NullPointerException {
		SaldoCuentaBean saldoCuentaBean = new SaldoCuentaBean();
		saldoCuentaBean.setCodigoSaldo(elemento.getCODSDO().trim());
		saldoCuentaBean.setUltimoMovimiento(integerToDateConverter
				.convertTo(elemento.getFECHASDO()));
		saldoCuentaBean.setSaldo(elemento.getIMPSDO());
		saldoCuentaBean.setDebe(elemento.getCNTRD());
		saldoCuentaBean.setHaber(elemento.getCNTRH());
		saldoCuentaBean.setCodigoCuenta(elemento.getCODCTA().trim());
		return saldoCuentaBean;
	}

}