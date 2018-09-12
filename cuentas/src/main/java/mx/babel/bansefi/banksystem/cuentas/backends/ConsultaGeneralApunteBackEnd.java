package mx.babel.bansefi.banksystem.cuentas.backends;

import java.util.ArrayList;
import java.util.Date;
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
import mx.babel.bansefi.banksystem.base.utils.StringToDateConverter;
import mx.babel.bansefi.banksystem.cuentas.beans.ConceptoApunteBean;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultageneralapunte.ConsultaGeneralApunteServicio;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultageneralapunte.Ejecutar.ITRCONSGENAPNTETRNI;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultageneralapunte.EjecutarResult;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultageneralapunte.ResponseBansefi.OTRCONSGENAPNTETRNO.TRCONSGENAPNTEEVTZ;
import mx.babel.bansefi.banksystem.cuentas.wrappers.ConsultaApunteWrapper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Backend para la TRN de consulta general de apuntes (TR_CONS_GEN_APNTE_TRN).
 * 
 * @author omar.marquez
 */
@Component
public class ConsultaGeneralApunteBackEnd extends BackEndBean {

	private static final long serialVersionUID = 5775789960336954478L;

	private static final Logger LOGGER = LogManager
			.getLogger(ConsultaGeneralApunteBackEnd.class);

	// Constante para indicar que el contenido de un catálogo es vacío.
	private static final String IND_CONTENIDO_VACIO = "0";

	private IntegerToDateConverter integerToDateConverter;
	private StringToDateConverter stringToDateConverter;
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
	public ConsultaGeneralApunteBackEnd() {
		super();
		this.integerToDateConverter = new IntegerToDateConverter();
		this.stringToDateConverter = new StringToDateConverter();
		this.catalogo = new CatalogoBean();
	}

	/**
	 * Método que ejecuta la TRN de consulta general de apuntes a partir de un
	 * CuentaBean y unos filtros de búsqueda.
	 * 
	 * @param cuentaBean
	 * @param filtroTipoCuenta
	 * @param filtroFechaDesde
	 * @param filtroFechaHasta
	 * @param numSecUltimoApunteConsultado
	 * @return lista de apuntes
	 */
	public List<MovimientoBean> ejecutarTRN(CuentaBean cuentaBean,
			String filtroTipoCuenta, Date filtroFechaDesde,
			Date filtroFechaHasta, Integer numSecUltimoApunteConsultado) {
		ITRCONSGENAPNTETRNI itrconsgenapntetrni = initPeticion(cuentaBean,
				filtroTipoCuenta, filtroFechaDesde, filtroFechaHasta,
				numSecUltimoApunteConsultado, false);

		EjecutarResult resultado = (EjecutarResult) servicioWebUtils
				.ejecutarWS(ConsultaGeneralApunteServicio.class,
						itrconsgenapntetrni);

		super.verificaRespuesta(resultado);

		return obtenerListaApuntes(resultado, numSecUltimoApunteConsultado);
	}
	
	public void ejecutarTRN(MovimientoBean movimiento, CuentaBean cuenta,
		Integer numSec, String filtroTipoCuenta){
		ITRCONSGENAPNTETRNI itrconsgenapntetrni = initPeticion(cuenta,
				filtroTipoCuenta, null, null,
				numSec, true);

		EjecutarResult resultado = (EjecutarResult) servicioWebUtils
				.ejecutarWS(ConsultaGeneralApunteServicio.class,
						itrconsgenapntetrni);

		super.verificaRespuesta(resultado);

		obtenerDetalleApunte(resultado, movimiento);
	}

	/**
	 * Método privado que prepara los datos que serán enviados a la TRN.
	 * 
	 * @param cuentaBean
	 * @param filtroTipoCuenta
	 * @param filtroFechaDesde
	 * @param filtroFechaHasta
	 * @param numSecUltimoApunteConsultado
	 * @return parametros de entrada encapsulados en un objeto
	 *         IPECONSORGACCFUNTRN
	 */
	private ITRCONSGENAPNTETRNI initPeticion(CuentaBean cuentaBean,
			String filtroTipoCuenta, Date filtroFechaDesde,
			Date filtroFechaHasta, Integer numSecUltimoApunteConsultado, Boolean detalle) {
		ITRCONSGENAPNTETRNI itrconsgenapntetrni = new ITRCONSGENAPNTETRNI();

		super.initialize(itrconsgenapntetrni);

		itrconsgenapntetrni.setELEVATORPOSITION(0);
		if(detalle){
			itrconsgenapntetrni.setSCROLLABLEOCCURS(1);
		}else{
			itrconsgenapntetrni.setSCROLLABLEOCCURS(SCROLLABLE_OCCURS);
		}
		itrconsgenapntetrni.getTRCONSGENAPNTEEVTY().getIDFRCTCTAV()
				.setCODNRBEEN(super.getEntidad());
		itrconsgenapntetrni.getTRCONSGENAPNTEEVTY().getIDFRCTCTAV()
				.setNUMSECAC(cuentaBean.getNumeroCuenta());
		itrconsgenapntetrni.getTRCONSGENAPNTEEVTY().getIDFRCTCTAV()
				.setPRPDADCTA(ConstantesFuncionales.PRPDAD_CTA);
		itrconsgenapntetrni.getTRCONSGENAPNTEEVTY().getIDFRCTCTAV()
				.setCODCTA(filtroTipoCuenta);
		itrconsgenapntetrni.getTRCONSGENAPNTEEVTY().getIDFRCTCTAV()
				.setCODNUMRCOMONEDA(ConstantesFuncionales.COD_MONEDA);

		if (filtroFechaDesde == null) {
			filtroFechaDesde = stringToDateConverter
					.convertTo(ConstantesFuncionales.MIN_FECHA_INICIO);
		}

		itrconsgenapntetrni
				.getTRCONSGENAPNTEEVTY()
				.getSTDINTERVALOV()
				.setFECHADESDE(
						integerToDateConverter.convertFrom(filtroFechaDesde));

		if (filtroFechaHasta == null) {
			filtroFechaHasta = stringToDateConverter
					.convertTo(ConstantesFuncionales.MAX_FECHA_FIN);
		}

		itrconsgenapntetrni
				.getTRCONSGENAPNTEEVTY()
				.getSTDINTERVALOV()
				.setFECHAHASTA(
						integerToDateConverter.convertFrom(filtroFechaHasta));

		itrconsgenapntetrni.getTRCONSGENAPNTEEVTY().getLGGRPPDP()
				.setCODLINEA(cuentaBean.getCodLinea());
		itrconsgenapntetrni.getTRCONSGENAPNTEEVTY().getLGGRPPDP()
				.setIDGRPPD(cuentaBean.getIdGrupoProducto());

		itrconsgenapntetrni.getTRCONSGENAPNTEEVTY().getNUMSECV()
				.setNUMSEC(numSecUltimoApunteConsultado);

		itrconsgenapntetrni.getSTDTRNIPARMV().setIDINTERNOTERMTN(
				super.getTerminal());
		itrconsgenapntetrni.getSTDTRNIPARMV().setCODTX(
				CodTxConstants.COD_TX_TR_CONS_GEN_APNTE_TRN);

		return itrconsgenapntetrni;
	}
	
	private void obtenerDetalleApunte(EjecutarResult resultado, MovimientoBean movimiento){
		if(resultado.getResponseBansefi() != null &&
				resultado.getResponseBansefi().getOTRCONSGENAPNTETRNO() != null &&
				resultado.getResponseBansefi().getOTRCONSGENAPNTETRNO().getTRCONSGENAPNTEEVTZ()!= null){
			if(resultado.getResponseBansefi().getOTRCONSGENAPNTETRNO().getNUMBEROFRECORDS() > 0){
				TRCONSGENAPNTEEVTZ apunte = resultado.getResponseBansefi().getOTRCONSGENAPNTETRNO().getTRCONSGENAPNTEEVTZ().get(0);
				movimiento.setCodigoOrigenApunte(apunte.getAFAPNTEE().getCODORGNAPNTE());
				movimiento.setIdOrigenApunte(apunte.getAFAPNTEE().getIDORGNAPNTE());
			}
		}
	}

	/**
	 * Método privado que devuelve una lista de apuntes a partir del resultado
	 * de la consulta.
	 * 
	 * @param resultado
	 * @param numSecUltimoApunteConsultado
	 * @return lista de apuntes
	 * @throws NullPointerException
	 */
	private List<MovimientoBean> obtenerListaApuntes(EjecutarResult resultado,
			Integer numSecUltimoApunteConsultado) throws NullPointerException {
		List<MovimientoBean> apuntes = new ArrayList<>();
		for (TRCONSGENAPNTEEVTZ elemento : resultado.getResponseBansefi()
				.getOTRCONSGENAPNTETRNO().getTRCONSGENAPNTEEVTZ()) {
			if (elemento != null && elemento.getAFAPNTEE() != null
					&& elemento.getAFAPNTEE().getNUMSEC() != 0) {
				MovimientoBean apunte = new MovimientoBean();
				consultaApunteWrapper.consultaGeneralApunteWrapper(
						elemento.getAFAPNTEE(), apunte);
				// Obtenemos el concepto del apunte.
				if (apunte.getConcepto() == null) {
					obtenerConcepto(apunte);
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
						.getOTRCONSGENAPNTETRNO().getMOREDATAIN() == 1);
				// Condición para omitir el primer valor duplicado en la TRN.
				if (!(numSecUltimoApunteConsultado.intValue() == ((Integer) apunte
						.getUltimoDatoConsultaAnterior()).intValue())) {
					apuntes.add(apunte);
				}
			}
		}
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
					ConsultaGeneralApunteBackEnd.IND_CONTENIDO_VACIO)) {
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