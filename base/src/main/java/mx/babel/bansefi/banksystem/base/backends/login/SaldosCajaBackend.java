package mx.babel.bansefi.banksystem.base.backends.login;

import java.math.BigDecimal;
import java.util.Calendar;

import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.AperturaPuestoBean;
import mx.babel.bansefi.banksystem.base.utils.IntegerToDateConverter;
import mx.babel.bansefi.banksystem.base.webservices.saldoscaja.Ejecutar.ITRACTUALIZARSALDOSTRN;
import mx.babel.bansefi.banksystem.base.webservices.saldoscaja.EjecutarResult;
import mx.babel.bansefi.banksystem.base.webservices.saldoscaja.SaldosCajaServicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Backend para la TRN de actualización de saldos de caja.
 * 
 * @author omar.marquez
 */
@Component
public class SaldosCajaBackend extends BackEndBean {

	private static final long serialVersionUID = 2764685617900861637L;

	private static final String TIPO_PUESTO = "C";
	private static final String IND_APERTURA = "1";
	private static final String IND_SI = "S";
	private static final String IND_NO = "N";
	private static final String SW_BACKUP = "S";
	private static final String TIPO_CENTRO = "F";

	private IntegerToDateConverter integerToDateConverter;

	@Autowired
	ServicioWebUtils servicioWebUtils;

	/**
	 * Constructor.
	 */
	public SaldosCajaBackend() {
		super();
		this.integerToDateConverter = new IntegerToDateConverter();
	}

	/**
	 * Método que ejecuta la TRN de actualización de saldos de caja.
	 * 
	 * @param aperturaPuestoBean
	 * @return código de retorno de la operación
	 */
	public int ejecutarTRN(AperturaPuestoBean aperturaPuestoBean) {
		ITRACTUALIZARSALDOSTRN itractualizarsaldostrn = initPeticion(aperturaPuestoBean);

		EjecutarResult resultado = (EjecutarResult) servicioWebUtils
				.ejecutarWS(SaldosCajaServicio.class, itractualizarsaldostrn);

		super.verificaRespuesta(resultado);

		return resultado.getResponseBansefi().getOTRACTUALIZARSALDOSTRN()
				.getRTRNCD();
	}

	/**
	 * Método privado que prepara los datos que serán enviados a la TRN.
	 * 
	 * @param aperturaPuestoBean
	 * @return parametros de entrada encapsulados en un objeto
	 *         ITRACTUALIZARSALDOSTRN
	 * @throws NullPointerException
	 */
	private ITRACTUALIZARSALDOSTRN initPeticion(
			AperturaPuestoBean aperturaPuestoBean) throws NullPointerException {
		ITRACTUALIZARSALDOSTRN itractualizarsaldostrn = new ITRACTUALIZARSALDOSTRN();

		super.initialize(itractualizarsaldostrn);

		itractualizarsaldostrn.getSTDAPPLPARMV().setCODNRBEEN(
				super.getEntidad());
		itractualizarsaldostrn.getSTDAPPLPARMV().setCODNRBEENFSC(
				super.getEntidad());
		itractualizarsaldostrn.getSTDAPPLPARMV().setCODINTERNOUO(
				super.getSucursal());
		itractualizarsaldostrn.getSTDAPPLPARMV().setCODINTERNOUOFSC(
				super.getSucursal());
		itractualizarsaldostrn.getSTDAPPLPARMV().setCODCSBOF(
				super.getSucursal());
		itractualizarsaldostrn.getSTDAPPLPARMV().setIDINTERNOTERMTN(
				super.getTerminal());
		itractualizarsaldostrn.getSTDAPPLPARMV().setIDINTERNOEMPLEP(
				super.getUsuarioId());
		itractualizarsaldostrn.getSTDAPPLPARMV().setFECHACTBLE(
				integerToDateConverter.convertFrom(aperturaPuestoBean
						.getFechaContable()));

		// Verificamos el indicador para eliminar traspasos.
		if (aperturaPuestoBean.isEliminarTraspasos()) {
			itractualizarsaldostrn.getSALDOSBORRADOSV().setSTDCHAR01("S");
		} else {
			itractualizarsaldostrn.getSALDOSBORRADOSV().setSTDCHAR01("N");
		}

		itractualizarsaldostrn.getPCSALDOST().setCODNRBEENFSC(
				super.getEntidad());
		itractualizarsaldostrn.getPCSALDOST().setCODINTERNOUOFSC(
				super.getSucursal());
		itractualizarsaldostrn.getPCSALDOST().setFECHAPC(
				integerToDateConverter.convertFrom(Calendar.getInstance()
						.getTime()));
		itractualizarsaldostrn.getPCSALDOST().setIDINTERNOTERMTN(
				super.getTerminal());
		itractualizarsaldostrn.getPCSALDOST().setNUMPUESTO(super.getPuesto());
		itractualizarsaldostrn.getPCSALDOST().setIDINTERNOEMPLEP(
				super.getUsuarioId());
		itractualizarsaldostrn.getPCSALDOST().setFECHACTBLE(
				integerToDateConverter.convertFrom(aperturaPuestoBean
						.getFechaContable()));
		itractualizarsaldostrn.getPCSALDOST().setTOTALARQUEOHOST(
				aperturaPuestoBean.getImporteInicialAnt());
		itractualizarsaldostrn.getPCSALDOST().setTOTALARQUEOPC(
				aperturaPuestoBean.getImporteInicialNuevo());
		itractualizarsaldostrn.getPCSALDOST().setSALDOCONTABLE(
				aperturaPuestoBean.getSaldoContable());
		itractualizarsaldostrn.getPCSALDOST().setIMPMOVOFFDEBE(BigDecimal.ZERO);
		itractualizarsaldostrn.getPCSALDOST()
				.setIMPMOVOFFHABER(BigDecimal.ZERO);
		itractualizarsaldostrn.getPCSALDOST()
				.setIMPMOVOFFDPOST(BigDecimal.ZERO);
		itractualizarsaldostrn.getPCSALDOST()
				.setIMPMOVOFFHPOST(BigDecimal.ZERO);
		itractualizarsaldostrn.getPCSALDOST().setIMPINI(
				aperturaPuestoBean.getImporteInicialAnt());
		itractualizarsaldostrn.getPCSALDOST().setSWCNTBORRADOS(
				SaldosCajaBackend.IND_NO);
		itractualizarsaldostrn.getPCSALDOST().setSWSALDOSBORRADOS(
				SaldosCajaBackend.IND_NO);
		itractualizarsaldostrn.getPCSALDOST().setSWRESULTADO(
				SaldosCajaBackend.IND_SI);

		itractualizarsaldostrn.getPCTERMINALTANTV().getPCTERMINALT()
				.setCODNRBEENFSC(super.getEntidad());
		itractualizarsaldostrn.getPCTERMINALTANTV().getPCTERMINALT()
				.setCODINTERNOUOFSC(super.getSucursal());
		itractualizarsaldostrn.getPCTERMINALTANTV().getPCTERMINALT()
				.setIDINTERNOTERMTN(super.getTerminal());
		itractualizarsaldostrn.getPCTERMINALTANTV().getPCTERMINALT()
				.setIDINTERNOEMPLEP(super.getUsuarioId());
		itractualizarsaldostrn.getPCTERMINALTANTV().getPCTERMINALT()
				.setCODNRBEEN(super.getEntidad());
		itractualizarsaldostrn.getPCTERMINALTANTV().getPCTERMINALT()
				.setCODINTERNOUO(super.getSucursal());
		itractualizarsaldostrn.getPCTERMINALTANTV().getPCTERMINALT()
				.setINDAPERTURATN(SaldosCajaBackend.IND_APERTURA);
		itractualizarsaldostrn.getPCTERMINALTANTV().getPCTERMINALT()
				.setNUMPUESTO(super.getPuesto());
		itractualizarsaldostrn.getPCTERMINALTANTV().getPCTERMINALT()
				.setCODCSBOF(super.getSucursal());
		itractualizarsaldostrn.getPCTERMINALTANTV().getPCTERMINALT()
				.setTIPOPUESTO(SaldosCajaBackend.TIPO_PUESTO);
		itractualizarsaldostrn.getPCTERMINALTANTV().getPCTERMINALT()
				.setPUESTOPRINCIPAL(aperturaPuestoBean.getIndPuestoPrincipal());

		itractualizarsaldostrn.getPCTERMINALTACTV().getPCTERMINALT()
				.setCODNRBEENFSC(super.getEntidad());
		itractualizarsaldostrn.getPCTERMINALTACTV().getPCTERMINALT()
				.setCODINTERNOUOFSC(super.getSucursal());
		itractualizarsaldostrn.getPCTERMINALTACTV().getPCTERMINALT()
				.setIDINTERNOTERMTN(super.getTerminal());
		itractualizarsaldostrn.getPCTERMINALTACTV().getPCTERMINALT()
				.setIDINTERNOEMPLEP(super.getUsuarioId());
		itractualizarsaldostrn.getPCTERMINALTACTV().getPCTERMINALT()
				.setIMPINI(aperturaPuestoBean.getImporteInicialNuevo());
		itractualizarsaldostrn.getPCTERMINALTACTV().getPCTERMINALT()
				.setCODNRBEEN(super.getEntidad());
		itractualizarsaldostrn.getPCTERMINALTACTV().getPCTERMINALT()
				.setCODINTERNOUO(super.getSucursal());
		itractualizarsaldostrn.getPCTERMINALTACTV().getPCTERMINALT()
				.setINDAPERTURATN(SaldosCajaBackend.IND_APERTURA);
		itractualizarsaldostrn.getPCTERMINALTACTV().getPCTERMINALT()
				.setNUMPUESTO(super.getPuesto());
		itractualizarsaldostrn.getPCTERMINALTACTV().getPCTERMINALT()
				.setCODCSBOF(super.getSucursal());
		itractualizarsaldostrn.getPCTERMINALTACTV().getPCTERMINALT()
				.setTIPOPUESTO(SaldosCajaBackend.TIPO_PUESTO);
		itractualizarsaldostrn.getPCTERMINALTACTV().getPCTERMINALT()
				.setPUESTOPRINCIPAL(aperturaPuestoBean.getIndPuestoPrincipal());

		itractualizarsaldostrn.getPCCENTROTACTV().getPCCENTROT()
				.setCODNRBEEN(super.getEntidad());
		itractualizarsaldostrn.getPCCENTROTACTV().getPCCENTROT()
				.setCODINTERNOUO(super.getSucursal());
		itractualizarsaldostrn
				.getPCCENTROTACTV()
				.getPCCENTROT()
				.setFECHACTBLE(
						integerToDateConverter.convertFrom(aperturaPuestoBean
								.getFechaContable()));
		itractualizarsaldostrn.getPCCENTROTACTV().getPCCENTROT()
				.setIMPINI(aperturaPuestoBean.getImporteInicialNuevo());
		itractualizarsaldostrn.getPCCENTROTACTV().getPCCENTROT()
				.setSWBACKUP(SaldosCajaBackend.SW_BACKUP);
		itractualizarsaldostrn.getPCCENTROTACTV().getPCCENTROT()
				.setTIPOCENTRO(SaldosCajaBackend.TIPO_CENTRO);
		itractualizarsaldostrn.getPCCENTROTACTV().getPCCENTROT()
				.setCODCSBOF(super.getSucursal());
		/**
		 * Se omiten FECHA_ULT_BAK, FECHA_PRX_BAK, NUM_DIAS_BAK_DI,
		 * NUM_DIAS_BORR_DI, NUM_TLFNO_DOMIC, NOMB_CENT_UO.
		 */
		return itractualizarsaldostrn;
	}

}