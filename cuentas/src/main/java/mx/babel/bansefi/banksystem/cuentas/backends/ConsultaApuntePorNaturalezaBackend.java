package mx.babel.bansefi.banksystem.cuentas.backends;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import mx.babel.arq.catalogo.beans.CatalogoBean;
import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.domain.CuentaBean;
import mx.babel.bansefi.banksystem.base.beans.movimientos.MovimientoBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.constants.ConstantesFuncionales;
import mx.babel.bansefi.banksystem.base.enums.CatalogoEnum;
import mx.babel.bansefi.banksystem.base.utils.CatalogoUtils;
import mx.babel.bansefi.banksystem.base.utils.IntegerToDateConverter;
import mx.babel.bansefi.banksystem.cuentas.beans.ConceptoApunteBean;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultaapuntepornaturaleza.ConsultaApuntePorNaturalezaServicio;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultaapuntepornaturaleza.Ejecutar.ITRCONSCTAFTRNI;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultaapuntepornaturaleza.EjecutarResult;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultaapuntepornaturaleza.ResponseBansefi.OTRCONSCTAFTRNO.TRCONSCTAFEVTZ.AFAPUNTESDV;
import mx.babel.bansefi.banksystem.cuentas.wrappers.ConsultaApunteWrapper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Backend para la TRN de consulta de apuntes por naturaleza de la cuenta
 * (TR_CONS_CT_AF_TRN).
 * 
 * @author omar.marquez
 */
@Component
public class ConsultaApuntePorNaturalezaBackend extends BackEndBean {

	private static final long serialVersionUID = -2010565989286369927L;

	private static final Logger LOGGER = LogManager
			.getLogger(ConsultaApuntePorNaturalezaBackend.class);

	// Constante para indicar que el contenido de un catálogo es vacío.
	private static final String IND_CONTENIDO_VACIO = "0";

	private static final int LONGITUD_NATURALEZA = 4;
	private String codigoCuenta;
	private String codigoSaldo;
	private CatalogoBean catalogo;

	@Autowired
	ServicioWebUtils servicioWebUtils;

	@Autowired
	ConsultaApunteWrapper consultaApunteWrapper;

	@Autowired
	CatalogoUtils catalogoUtils;

	@Autowired
	ConsultaConceptoApunteBackend consultaConceptoApunteBackend;

	/**
	 * Constructor.
	 */
	public ConsultaApuntePorNaturalezaBackend() {
		super();
		this.codigoCuenta = new String();
	}

	/**
	 * Método que ejecuta la TRN de consulta de apuntes a partir de un
	 * CuentaBean y un filtro de búsqueda que contiene la naturaleza de la
	 * cuenta.
	 * 
	 * @param cuentaBean
	 * @param filtroNaturaleza
	 * @param numSecUltimoApunteConsultado
	 * @return lista de apuntes
	 */
	public List<MovimientoBean> ejecutarTRN(CuentaBean cuentaBean,
			String filtroNaturaleza, Integer numSecUltimoApunteConsultado,
			BigDecimal saldo, MovimientoBean apunte) {
		ITRCONSCTAFTRNI itrconsctaftrni = initPeticion(cuentaBean,
				filtroNaturaleza, numSecUltimoApunteConsultado, saldo, apunte);

		EjecutarResult resultado = (EjecutarResult) servicioWebUtils
				.ejecutarWS(ConsultaApuntePorNaturalezaServicio.class,
						itrconsctaftrni);

		super.verificaRespuesta(resultado);

		return obtenerListaApuntes(resultado, cuentaBean);
	}

	/**
	 * Método privado que prepara los datos que serán enviados a la TRN.
	 * 
	 * @param cuentaBean
	 * @param filtroNaturaleza
	 * @param numSecUltimoApunteConsultado
	 * @return parametros de entrada encapsulados en un objeto ITRCONSCTAFTRNI
	 * @throws IndexOutOfBoundsException
	 * @throws NullPointerException
	 */
	private ITRCONSCTAFTRNI initPeticion(CuentaBean cuentaBean,
			String filtroNaturaleza, Integer numSecUltimoApunteConsultado,
			BigDecimal saldo, MovimientoBean apunte) throws IndexOutOfBoundsException,
			NullPointerException {
		ITRCONSCTAFTRNI itrconsctaftrni = new ITRCONSCTAFTRNI();

		super.initialize(itrconsctaftrni);

		itrconsctaftrni.setELEVATORPOSITION(0);
		itrconsctaftrni.setSCROLLABLEOCCURS(SCROLLABLE_OCCURS);

		itrconsctaftrni.getTRCONSCTAFEVTY().getSDSDOE()
				.setCODNRBEEN(super.getEntidad());
		itrconsctaftrni.getTRCONSCTAFEVTY().getSDSDOE()
				.setPRPDADCTA(ConstantesFuncionales.PRPDAD_CTA);
		itrconsctaftrni.getTRCONSCTAFEVTY().getSDSDOE()
				.setNUMSECAC(cuentaBean.getNumeroCuenta());
		itrconsctaftrni.getTRCONSCTAFEVTY().getSDSDOE()
				.setCODNUMRCOMONEDA(ConstantesFuncionales.COD_MONEDA);

		// Obtenemos el código de la cuenta y el código de saldo.
		if (filtroNaturaleza != null
				&& !filtroNaturaleza.trim().isEmpty()
				&& filtroNaturaleza.trim().length() == ConsultaApuntePorNaturalezaBackend.LONGITUD_NATURALEZA) {
			this.codigoCuenta = filtroNaturaleza.substring(0, 2);
			this.codigoSaldo = filtroNaturaleza.substring(2, 4);
			itrconsctaftrni.getTRCONSCTAFEVTY().getSDSDOE()
					.setCODCTA(this.codigoCuenta);
			itrconsctaftrni.getTRCONSCTAFEVTY().getSDSDOE()
					.setCODSDO(this.codigoSaldo);
		}

		itrconsctaftrni.getTRCONSCTAFEVTY().getSDSDOE()
				.setCODLINEA(cuentaBean.getCodLinea());
		itrconsctaftrni.getTRCONSCTAFEVTY().getSDSDOE()
				.setIDGRPPD(cuentaBean.getIdGrupoProducto());

		itrconsctaftrni.getTRCONSCTAFEVTY().getNUMSECV()
				.setNUMSEC(numSecUltimoApunteConsultado);
		if(saldo != null){
			itrconsctaftrni.getTRCONSCTAFEVTY().getSDOINICARRASTREV()
				.setIMPSDO(saldo);
		}else{
			IntegerToDateConverter converter = new IntegerToDateConverter();
			itrconsctaftrni.getTRCONSCTAFEVTY().getSDOINICARRASTREV()
				.setIMPSDO(apunte.getSaldo());
			itrconsctaftrni.getTRCONSCTAFEVTY().getFECHACTBLEV()
				.setFECHACTBLE(converter.convertFrom(apunte.getFechaContable()));
		}
		itrconsctaftrni.getSTDTRNIPARMV().setIDINTERNOTERMTN(
				super.getTerminal());
		itrconsctaftrni.getSTDTRNIPARMV().setCODTX(
				CodTxConstants.COD_TX_TR_CONS_CT_AF_TRN);

		return itrconsctaftrni;
	}

	/**
	 * Método privado que devuelve una lista de apuntes a partir del resultado
	 * de la consulta.
	 * 
	 * @param resultado
	 * @param cuentaBean
	 * @return lista de apuntes
	 * @throws NullPointerException
	 */
	private List<MovimientoBean> obtenerListaApuntes(EjecutarResult resultado,
			CuentaBean cuentaBean) throws NullPointerException {
		BigDecimal saldoFinArrastre;
		List<MovimientoBean> apuntes = new ArrayList<>();
		for (AFAPUNTESDV elemento : resultado.getResponseBansefi()
				.getOTRCONSCTAFTRNO().getTRCONSCTAFEVTZ().getAFAPUNTESDV()) {
			if (elemento != null && elemento.getNUMSEC() != 0) {
				MovimientoBean apunte = new MovimientoBean();
				consultaApunteWrapper.consultaApuntePorNaturalezaWrapper(
						elemento, apunte);
				apunte.setNumCuenta(cuentaBean.getNumeroCuenta());
				apunte.setCodigoCuenta(this.codigoCuenta);
				// Obtenemos el concepto del apunte.
				if (apunte.getConcepto() == null) {
					obtenerConcepto(apunte);
				}
				// Asignación especial de signo.
				if (this.codigoCuenta.equals("06")
						&& this.codigoSaldo.equals("02")
						&& (elemento.getIND1().trim().equals("X") || elemento
								.getIND1().trim().equals("F"))) {
					apunte.setSigno("H");
				}
				// Obtenemos el signo del apunte.
				if ("D".equals(apunte.getSigno())) {
					apunte.setSigno("DEBE");
				}
				if ("H".equals(apunte.getSigno())) {
					apunte.setSigno("HABER");
				}
				apunte.setUltimoDatoConsultaAnterior(apunte.getNumSec());
				apunte.setMasDatos(resultado.getResponseBansefi()
						.getOTRCONSCTAFTRNO().getMOREDATAIN() == 1);
				apuntes.add(apunte);
			}
		}
		saldoFinArrastre = resultado.getResponseBansefi().getOTRCONSCTAFTRNO()
				.getTRCONSCTAFEVTZ().getSDOFINARRASTREV().getIMPSDO();
		apuntes.get(apuntes.size() - 1).setSaldoFinArrastre(saldoFinArrastre);
		return apuntes;
	}

	/**
	 * Método privado que aplica las siguientes reglas para obtener el concepto
	 * del apunte.
	 * 
	 * Siempre y cuando la TRN no haya devuelto el concepto del apunte:
	 * 
	 * 1) Consulta el catálogo TP_CTA (GAE00032) para obtener el atributo
	 * contenido y sí:
	 * 
	 * a) El atributo contenido es diferente a "0", toma el concepto del
	 * catálogo anidado.
	 * 
	 * b) El atributo contenido es igual a "0", ejecuta la consulta para obtener
	 * el concepto a través de la TR_NOTIF_CONS_PK_TRN.
	 * 
	 * @param apunte
	 */
	private void obtenerConcepto(MovimientoBean apunte) {
		if (apunte.getCodigoCuenta() != null) {
			obtenerConceptoDeCatalogo(apunte);
		}
	}

	/**
	 * Método privado que se ejecuta cuando la TRN no haya devuelto el concepto
	 * del apunte.
	 * 
	 * @param apunte
	 */
	private void obtenerConceptoDeCatalogo(MovimientoBean apunte) {
		try {
			catalogo = catalogoUtils.getCatalogoBean(CatalogoEnum.TP_CTA,
					apunte.getCodigoCuenta());
			if (!catalogo.getContenido().equals(
					ConsultaApuntePorNaturalezaBackend.IND_CONTENIDO_VACIO)) {
				CatalogoEnum catalogoEnum = catalogoUtils
						.getCatalogoEnum(catalogo.getContenido());
				catalogo = catalogoUtils.getCatalogoBean(catalogoEnum,
						apunte.getCodigoOrigen());
				apunte.setConcepto(catalogo.getDescripcionL().trim());
			} else {
				if (apunte.getCodigoOrigen() != null) {
					obtenerConceptoDeConsulta(apunte);
				}
			}
		} catch (NullPointerException | ControlableException e) {
			LOGGER.error("Error al obtener el concepto del apunte desde catálogo. "
					+ this.getClass().getName() + ":" + e.getMessage());
		}
	}

	/**
	 * Método privado que ejecuta la TRN de consulta a la PK
	 * (TR_NOTIF_CONS_PK_TRN). Este método se ejecuta cuando el catálogo no haya
	 * devuelto el concepto del apunte.
	 * 
	 * @param apunte
	 */
	private void obtenerConceptoDeConsulta(MovimientoBean apunte) {
		try {
			String claveConcepto = apunte.getCodigoOrigen().substring(3, 6);
			ConceptoApunteBean caBean = consultaConceptoApunteBackend
					.ejecutarTRN(claveConcepto);
			if (caBean.getNombre() != null) {
				apunte.setConcepto(caBean.getNombre());
			} else {
				apunte.setConcepto(new String());
			}
		} catch (IndexOutOfBoundsException | NullPointerException
				| ControlableException | NoControlableException e) {
			LOGGER.error("Error al obtener el concepto del apunte desde TRN de consulta. "
					+ this.getClass().getName() + ":" + e.getMessage());
		}
	}

}