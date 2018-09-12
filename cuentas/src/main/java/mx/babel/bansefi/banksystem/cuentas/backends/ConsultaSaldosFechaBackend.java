package mx.babel.bansefi.banksystem.cuentas.backends;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.domain.CuentaBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.constants.ConstantesFuncionales;
import mx.babel.bansefi.banksystem.base.utils.IntegerToDateConverter;
import mx.babel.bansefi.banksystem.base.utils.StringToDateConverter;
import mx.babel.bansefi.banksystem.cuentas.beans.SaldoCuentaBean;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultasaldosfecha.ConsultaSaldosFechaServicio;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultasaldosfecha.Ejecutar.ITRSDOBTESDOFECHATRN;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultasaldosfecha.EjecutarResult;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultasaldosfecha.ResponseBansefi.OTRSDOBTESDOFECHATRN.TRSDOBTESDOFECHAEVT.FECHASALDOV;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultasaldosfecha.ResponseBansefi.OTRSDOBTESDOFECHATRN.TRSDOBTESDOFECHAEVT.SDDERCTACV;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Backend para la TRN de consulta de saldos por fecha.
 * 
 * @author omar.marquez
 */
@Component
public class ConsultaSaldosFechaBackend extends BackEndBean {

	private static final long serialVersionUID = -101630322054496331L;

	// Constante para no paginar sí es D1 - FIN DIA.
	private static final String TIPO_SALDO_SIN_PAGINACION = "D1";
	
	private static final Integer FECHA_DESDE_VACIA = 11111111;

	// Constante para las ocurrencias cuando es D1 - FIN DIA.
	private static final int D1_SCROLLABLE_OCCURS = 366;

	private IntegerToDateConverter integerToDateConverter;
	private StringToDateConverter stringToDateConverter;

	@Autowired
	ServicioWebUtils servicioWebUtils;

	/**
	 * Constructor.
	 */
	public ConsultaSaldosFechaBackend() {
		super();
		this.integerToDateConverter = new IntegerToDateConverter();
		this.stringToDateConverter = new StringToDateConverter();
	}

	/**
	 * Método que ejecuta la TRN de consulta de saldos por fecha a partir de un
	 * CuentaBean, SaldoCuentaBean y unos filtros de búsqueda.
	 * 
	 * @param cuentaBean
	 * @param saldoCuentaBean
	 * @param filtroRazonTipoSaldo
	 * @param filtroFechaDesde
	 * @param filtroFechaHasta
	 * @param fechaUltimoSaldo
	 * @return lista de saldos
	 */
	public List<SaldoCuentaBean> ejecutarTRN(CuentaBean cuentaBean,
			SaldoCuentaBean saldoCuentaBean, String filtroRazonTipoSaldo,
			Date filtroFechaDesde, Date filtroFechaHasta, Date fechaUltimoSaldo) {
		ITRSDOBTESDOFECHATRN itrsdobtesdofechatrn = initPeticion(cuentaBean,
				saldoCuentaBean, filtroRazonTipoSaldo, filtroFechaDesde,
				filtroFechaHasta, fechaUltimoSaldo);

		EjecutarResult resultado = (EjecutarResult) servicioWebUtils
				.ejecutarWS(ConsultaSaldosFechaServicio.class,
						itrsdobtesdofechatrn);

		try {
			super.verificaRespuesta(resultado);
		} catch (ControlableException e) {
			if (e.getRtncod() == RETURN_COD_SIN_DATOS) {
				return new ArrayList<SaldoCuentaBean>();
			} else {
				throw e;
			}
		}

		return obtenerListaSaldos(resultado);
	}

	/**
	 * Método privado que prepara los datos que serán enviados a la TRN.
	 * 
	 * @param cuentaBean
	 * @param saldoCuentaBean
	 * @param filtroRazonTipoSaldo
	 * @param filtroFechaDesde
	 * @param filtroFechaHasta
	 * @param fechaUltimoSaldo
	 * @return parametros de entrada encapsulados en un objeto
	 *         ITRSDOBTESDOFECHATRN
	 */
	private ITRSDOBTESDOFECHATRN initPeticion(CuentaBean cuentaBean,
			SaldoCuentaBean saldoCuentaBean, String filtroRazonTipoSaldo,
			Date filtroFechaDesde, Date filtroFechaHasta, Date fechaUltimoSaldo) {
		ITRSDOBTESDOFECHATRN itrsdobtesdofechatrn = new ITRSDOBTESDOFECHATRN();

		super.initialize(itrsdobtesdofechatrn);

		itrsdobtesdofechatrn.setELEVATORPOSITION(0);

		if (filtroRazonTipoSaldo != null
				&& filtroRazonTipoSaldo
						.equals(ConsultaSaldosFechaBackend.TIPO_SALDO_SIN_PAGINACION)) {
			// Sí el tipo de saldo es D1 - FIN DIA, no paginar.
			itrsdobtesdofechatrn
					.setSCROLLABLEOCCURS(ConsultaSaldosFechaBackend.D1_SCROLLABLE_OCCURS);
		} else {
			// En caso contrario, paginar de 50 en 50.
			itrsdobtesdofechatrn.setSCROLLABLEOCCURS(SCROLLABLE_OCCURS);
		}

		itrsdobtesdofechatrn.getTRSDOBTESDOFECHAEVT().getSDSDOFECHAE()
				.setCODNRBEEN(super.getEntidad());
		itrsdobtesdofechatrn.getTRSDOBTESDOFECHAEVT().getSDSDOFECHAE()
				.setPRPDADCTA(ConstantesFuncionales.PRPDAD_CTA);
		itrsdobtesdofechatrn.getTRSDOBTESDOFECHAEVT().getSDSDOFECHAE()
				.setNUMSECAC(cuentaBean.getNumeroCuenta());
		itrsdobtesdofechatrn.getTRSDOBTESDOFECHAEVT().getSDSDOFECHAE()
				.setCODNUMRCOMONEDA(ConstantesFuncionales.COD_MONEDA);
		itrsdobtesdofechatrn.getTRSDOBTESDOFECHAEVT().getSDSDOFECHAE()
				.setCODCTA(saldoCuentaBean.getCodigoCuenta());
		itrsdobtesdofechatrn.getTRSDOBTESDOFECHAEVT().getSDSDOFECHAE()
				.setCODSDO(saldoCuentaBean.getCodigoSaldo());
		itrsdobtesdofechatrn.getTRSDOBTESDOFECHAEVT().getSDSDOFECHAE()
				.setCODRZNSDO(filtroRazonTipoSaldo);

		// Verificamos sí la fecha del saldo viene informada.
		if (fechaUltimoSaldo != null) {
			itrsdobtesdofechatrn
					.getTRSDOBTESDOFECHAEVT()
					.getSDSDOFECHAE()
					.setFECHASDO(
							integerToDateConverter
									.convertFrom(fechaUltimoSaldo));
		}

		itrsdobtesdofechatrn.getTRSDOBTESDOFECHAEVT().getSDSDOFECHAE()
				.setCODLINEA(cuentaBean.getCodLinea());
		itrsdobtesdofechatrn.getTRSDOBTESDOFECHAEVT().getSDSDOFECHAE()
				.setIDGRPPD(cuentaBean.getIdGrupoProducto());
		itrsdobtesdofechatrn.getTRSDOBTESDOFECHAEVT().getSDSDOFECHAE()
				.setIMPSDO(saldoCuentaBean.getSaldo());
		itrsdobtesdofechatrn.getTRSDOBTESDOFECHAEVT().getSDSDOFECHAE()
				.setCNTRD(saldoCuentaBean.getDebe());
		itrsdobtesdofechatrn.getTRSDOBTESDOFECHAEVT().getSDSDOFECHAE()
				.setCNTRH(saldoCuentaBean.getHaber());

		if (filtroFechaDesde == null) {
			itrsdobtesdofechatrn
			.getTRSDOBTESDOFECHAEVT()
			.getSTDINTERVALOV()
			.setFECHADESDE(FECHA_DESDE_VACIA);
		}else{
			itrsdobtesdofechatrn
				.getTRSDOBTESDOFECHAEVT()
				.getSTDINTERVALOV()
				.setFECHADESDE(
						integerToDateConverter.convertFrom(filtroFechaDesde));
		}

		if (filtroFechaHasta == null) {
			filtroFechaHasta = stringToDateConverter
					.convertTo(ConstantesFuncionales.MAX_FECHA_FIN);
		}

		itrsdobtesdofechatrn
				.getTRSDOBTESDOFECHAEVT()
				.getSTDINTERVALOV()
				.setFECHAHASTA(
						integerToDateConverter.convertFrom(filtroFechaHasta));

		itrsdobtesdofechatrn.getSTDTRNIPARMV().setIDINTERNOTERMTN(
				super.getTerminal());
		itrsdobtesdofechatrn.getSTDTRNIPARMV().setCODTX(
				CodTxConstants.COD_TR_SD_OBTE_SDO_FECHA_TRN);

		return itrsdobtesdofechatrn;
	}

	/**
	 * Método privado que devuelve una lista de saldos a partir del resultado de
	 * la consulta.
	 * 
	 * @param resultado
	 * @return lista de saldos
	 * @throws NullPointerException
	 */
	private List<SaldoCuentaBean> obtenerListaSaldos(EjecutarResult resultado)
			throws NullPointerException {
		List<SaldoCuentaBean> saldos = new ArrayList<>();
		final int numRecords = resultado.getResponseBansefi().getOTRSDOBTESDOFECHATRN().getNUMBEROFRECORDS();
		List<FECHASALDOV> elementos = resultado.getResponseBansefi().getOTRSDOBTESDOFECHATRN()
				.getTRSDOBTESDOFECHAEVT().getFECHASALDOV();
		// Iteramos la lista 1.
		for (int i = 0; i < numRecords; i++){
			if (elementos.get(i) != null && elementos.get(i).getIMPSDO() != null
					&& elementos.get(i).getFECHASDO() != 0) {
				SaldoCuentaBean saldoCuentaBean = crearSaldoCuentaBean(elementos.get(i));
				saldos.add(saldoCuentaBean);
			}
		}
		List<SDDERCTACV> elementos2 = resultado.getResponseBansefi().getOTRSDOBTESDOFECHATRN()
				.getTRSDOBTESDOFECHAEVT().getSDDERCTACV();
		// Iteramos la lista 2.
		for (int i = 0; i < numRecords; i++){
			if (elementos2.get(i) != null && elementos2.get(i).getIMPSDO() != null
					&& elementos2.get(i).getFECHASDO() != 0) {
				SaldoCuentaBean saldoCuentaBean = crearSaldoCuentaBean(elementos2.get(i));
				saldos.add(saldoCuentaBean);
			}
		}
		// Al último elemento, le agregamos el indicador de más datos.
		if (saldos.size() > 0) {
			saldos.get(saldos.size() - 1).setMasDatos(
					resultado.getResponseBansefi().getOTRSDOBTESDOFECHATRN()
							.getMOREDATAIN() == 1);
		}
		return saldos;
	}

	/**
	 * Método privado que devuelve un objeto tipo SaldoCuentaBean a partir de un
	 * elemento FECHASALDOV.
	 * 
	 * @param elemento
	 * @return saldoCuentaBean
	 * @throws NullPointerException
	 */
	private SaldoCuentaBean crearSaldoCuentaBean(FECHASALDOV elemento)
			throws NullPointerException {
		SaldoCuentaBean saldoCuentaBean = new SaldoCuentaBean();
		saldoCuentaBean.setUltimoMovimiento(integerToDateConverter
				.convertTo(elemento.getFECHASDO()));
		saldoCuentaBean.setSaldo(elemento.getIMPSDO());
		saldoCuentaBean.setDebe(elemento.getCNTRD());
		saldoCuentaBean.setHaber(elemento.getCNTRH());
		saldoCuentaBean.setUltimoDatoConsultaAnterior(integerToDateConverter
				.convertTo(elemento.getFECHASDO()));
		return saldoCuentaBean;
	}

	/**
	 * Método privado que devuelve un objeto tipo SaldoCuentaBean a partir de un
	 * elemento SDDERCTACV.
	 * 
	 * @param elemento
	 * @return saldoCuentaBean
	 * @throws NullPointerException
	 */
	private SaldoCuentaBean crearSaldoCuentaBean(SDDERCTACV elemento)
			throws NullPointerException {
		SaldoCuentaBean saldoCuentaBean = new SaldoCuentaBean();
		saldoCuentaBean.setUltimoMovimiento(integerToDateConverter
				.convertTo(elemento.getFECHASDO()));
		saldoCuentaBean.setSaldo(elemento.getIMPSDO());
		saldoCuentaBean.setDebe(elemento.getCNTRD());
		saldoCuentaBean.setHaber(elemento.getCNTRH());
		saldoCuentaBean.setUltimoDatoConsultaAnterior(integerToDateConverter
				.convertTo(elemento.getFECHASDO()));
		return saldoCuentaBean;
	}

}