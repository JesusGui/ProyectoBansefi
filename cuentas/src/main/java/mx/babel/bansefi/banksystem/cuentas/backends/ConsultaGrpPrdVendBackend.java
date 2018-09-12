package mx.babel.bansefi.banksystem.cuentas.backends;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import mx.babel.arq.cache.annotation.Cache;
import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.EstadoEnum;
import mx.babel.arq.comun.utils.ProveedorMensajeSpringUtils;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.webservices.consultagruposproductosvendibles.ConsultaGrpPrdVendServicio;
import mx.babel.bansefi.banksystem.base.webservices.consultagruposproductosvendibles.Ejecutar;
import mx.babel.bansefi.banksystem.base.webservices.consultagruposproductosvendibles.EjecutarResult;
import mx.babel.bansefi.banksystem.cuentas.beans.TarifaBean;
import mx.babel.bansefi.banksystem.cuentas.wrappers.ConsultaGrpPrdVendWrapper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Backend para la consulta de grupos y productos vendibles
 *
 * @author
 *
 */

@Component
public class ConsultaGrpPrdVendBackend extends BackEndBean {

	private static final long serialVersionUID = 5699073293324807417L;
	private static final Logger LOGGER = LogManager
			.getLogger(ConsultaGrpPrdVendBackend.class.getName());

	private Integer codigoRetorno;

	@Autowired
	ConsultaGrpPrdVendWrapper consultaGrpPrdVendWrapper;

	@Autowired
	ConsultaDetalleGrpPrdVendBackend consultaDetalleGrpPrdVendBackend;

	@Autowired
	ServicioWebUtils servicioWebUtils;

	/**
	 * Constructor.
	 */
	public ConsultaGrpPrdVendBackend() {
		codigoRetorno = new Integer(-1);
	}

	/**
	 * Método que obtiene el código de retorno devuelto por el servicio de
	 * consulta de cuentas.
	 *
	 * @return codigoRetorno
	 */
	public Integer getCodigoRetorno() {
		return codigoRetorno;
	}

	/**
	 * Método que establece el código de retorno.
	 *
	 * @param codigoRetorno
	 */
	public void setCodigoRetorno(final Integer codigoRetorno) {
		this.codigoRetorno = codigoRetorno;
	}

	/**
	 * Método que ejecuta la transacción de consulta de cuentas a partir de un
	 * objeto CuentaBean.
	 *
	 * @param cuentaBean
	 * @return cuentaBean con valores de atributos recuperados
	 */
	@Cache(ignoreEmpty=true)
	public List<TarifaBean> ejecutarTRN(final TarifaBean tarifaBean, final String entidad)
			throws ControlableException, NoControlableException {
		List<TarifaBean> result = new CopyOnWriteArrayList<TarifaBean>();

		final Ejecutar.ITRCONSLISTAPDVTRNI trconslistapdvtrni = new Ejecutar.ITRCONSLISTAPDVTRNI();
		trconslistapdvtrni.setELEVATORPOSITION(1);
		trconslistapdvtrni.setSCROLLABLEOCCURS(BackEndBean.SCROLLABLE_OCCURS);

		final Ejecutar.ITRCONSLISTAPDVTRNI.TRCONSLISTAPDVEVTY trconslistapdvevty = new Ejecutar.ITRCONSLISTAPDVTRNI.TRCONSLISTAPDVEVTY();
		final Ejecutar.ITRCONSLISTAPDVTRNI.TRCONSLISTAPDVEVTY.PVPDVP pvpdvp = new Ejecutar.ITRCONSLISTAPDVTRNI.TRCONSLISTAPDVEVTY.PVPDVP();

		pvpdvp.setCODNRBEEN(entidad);
		pvpdvp.setCODLINEA(tarifaBean.getLinea());
		pvpdvp.setIDGRPPD(tarifaBean.getGrupo());
		trconslistapdvevty.setPVPDVP(pvpdvp);
		trconslistapdvtrni.setTRCONSLISTAPDVEVTY(trconslistapdvevty);

		final Ejecutar.ITRCONSLISTAPDVTRNI.STDTRNIPARMV stdtrniparmv = new Ejecutar.ITRCONSLISTAPDVTRNI.STDTRNIPARMV();

		stdtrniparmv.setIDINTERNOTERMTN(super.getTerminal());
		stdtrniparmv.setCODTX(CodTxConstants.COD_TX_TR_CONS_LISTA_PDV_TRN);
		trconslistapdvtrni.setSTDTRNIPARMV(stdtrniparmv);

		final Ejecutar.ITRCONSLISTAPDVTRNI.TRCONSLISTAPDVEVTY.CTLCODECVV ctlcodecvv = new Ejecutar.ITRCONSLISTAPDVTRNI.TRCONSLISTAPDVEVTY.CTLCODECVV();
		this.initialize(ctlcodecvv);
		this.initialize(trconslistapdvtrni);

		for (int i = 0; i < 7; i++) {
			trconslistapdvtrni.getTRCONSLISTAPDVEVTY().getCTLCODECVV()
					.add(ctlcodecvv);
		}

		EjecutarResult resultado = null;
		resultado = (EjecutarResult) servicioWebUtils.ejecutarWS(
				ConsultaGrpPrdVendServicio.class, trconslistapdvtrni);
		if (verificarResultado(resultado)) {
			codigoRetorno = resultado.getResponseBansefi().getOTRCONSLISTAPDVTRNO().getRTRNCD();
			if (codigoRetorno.equals(BackEndBean.RETURN_COD_OK)) {
				if (tarifaBean.getGrupo() == null
						|| tarifaBean.getGrupo().isEmpty()) {
					final List<TarifaBean> lstTarifaBean = consultaGrpPrdVendWrapper
							.wrappGrpPrdVend(resultado.getResponseBansefi()
									.getOTRCONSLISTAPDVTRNO()
									.getTRCONSLISTAPDVEVTZ().getLGLISTAV(),
									tarifaBean.getLinea());
					List<TarifaBean> tempList = null;
					for (final TarifaBean groupTarifa : lstTarifaBean) {
						tempList = this.ejecutarTRN(groupTarifa, entidad);
						if (tempList != null && !tempList.isEmpty()) {
							result.addAll(tempList);
						}
					}

				} else {
					result = consultaGrpPrdVendWrapper.wrappGrpPrdVend(
							resultado.getResponseBansefi().getOTRCONSLISTAPDVTRNO()
									.getTRCONSLISTAPDVEVTZ().getPVLISTAV(),
							tarifaBean.getLinea(), tarifaBean.getGrupo());

					result = this.consultaDetalle(result, entidad);
				}
			} else if (codigoRetorno.equals(7)) {
				LOGGER.debug("Datos no encontrados");
			} else {
				EstadoEnum.mensajesError("trn", codigoRetorno);
			}
		}

		return result;
	}

	private List<TarifaBean> consultaDetalle(final List<TarifaBean> listaTarifas, final String entidad) {
		final List<TarifaBean> resultado = new CopyOnWriteArrayList<TarifaBean>();
		for (final TarifaBean tarifaBean : listaTarifas) {
			final List<TarifaBean> detalle = consultaDetalleGrpPrdVendBackend
					.ejecutarTRN(tarifaBean, entidad);
			if (null != detalle) {
				resultado.addAll(detalle);
			}
		}
		return resultado;
	}

	/**
	 * Método privado que verifica que el resultado obtenido no contenga valores
	 * nulos.
	 *
	 * @param resultado
	 * @return indicador booleano para determinar que el resultado no es nulo
	 */
	private boolean verificarResultado(final EjecutarResult resultado) {
		try {
			if (resultado != null
					&& resultado.getResponseBansefi() != null
					&& resultado.getResponseBansefi().getOTRCONSLISTAPDVTRNO() != null
					&& resultado.getResponseBansefi().getOTRCONSLISTAPDVTRNO()
							.getTRCONSLISTAPDVEVTZ() != null) {
				return true;
			}
		} catch (final NullPointerException e) {
			throw new NoControlableException(
					"Error al invocar el servicio de consulta de grupos y productos vendibles.",
					this.getClass().getName()
							+ ". La respuesta contiene valores nulos.");
		}
		return false;
	}
}
