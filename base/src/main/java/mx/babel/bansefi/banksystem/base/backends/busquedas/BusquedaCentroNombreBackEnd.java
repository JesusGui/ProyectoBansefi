package mx.babel.bansefi.banksystem.base.backends.busquedas;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import mx.babel.arq.cache.annotation.Cache;
import mx.babel.arq.catalogo.beans.CatalogoBean;
import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ProveedorMensajeSpringUtils;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.busqueda.CentroBusquedaBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.webservices.listacentros.BusquedaNombreCentroServicio;
import mx.babel.bansefi.banksystem.base.webservices.listacentros.Ejecutar;
import mx.babel.bansefi.banksystem.base.webservices.listacentros.Ejecutar.ITRLISTNOMBCENTTRNI;
import mx.babel.bansefi.banksystem.base.webservices.listacentros.EjecutarResult;
import mx.babel.bansefi.banksystem.base.webservices.listacentros.ResponseBansefi.OTRLISTNOMBCENTTRNO.TRLISTNOMBCENTEVTZ.UOLAV2;
import mx.babel.bansefi.banksystem.base.wrappers.WrapperBusquedasUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * BackEnd para ejecutar el servicio de busqueda por nombre de centros.
 * 
 * @author alejandro.pineda
 * 
 */

@Component
public class BusquedaCentroNombreBackEnd extends BackEndBean implements
		Serializable {

	private static final long serialVersionUID = -534810166611736412L;

	@Autowired
	private WrapperBusquedasUtils wrapperBeanService;

	@Autowired
	ServicioWebUtils servicioWebUtils;

	public BusquedaCentroNombreBackEnd() {

	}

	/**
	 * Método que ejecuta el ws de búsqueda de nombre del centro.
	 * 
	 * @param obj
	 *            Objeto Bean que registra los datos.
	 * @return List Lista de resultados que obtiene el WS.
	 * @throws NoControlableException
	 *             Excepcion en caso de que el servicio muestre un error
	 *             inesperado.
	 * @throws ControlableException
	 *             Excepcion en caso de que el servicio muestre un error
	 *             esperado.
	 */
	public List<Object> ejecutarTRN(Object obj) throws NoControlableException,
			ControlableException {
		CentroBusquedaBean centro = (CentroBusquedaBean) obj;
		Ejecutar.ITRLISTNOMBCENTTRNI contextoEntrada = new Ejecutar.ITRLISTNOMBCENTTRNI();
		contextoEntrada.setELEVATORPOSITION(1);
		contextoEntrada.setSCROLLABLEOCCURS(SCROLLABLE_OCCURS);

		Ejecutar.ITRLISTNOMBCENTTRNI.UOLACB contextoVacio = new Ejecutar.ITRLISTNOMBCENTTRNI.UOLACB();
		contextoVacio.setCODINTERNOUO("");
		contextoVacio.setCODNRBEEN(super.getEntidad());
		contextoVacio.setNOMBCENTUO("");
		contextoVacio.setNOMBENTEN("");

		Ejecutar.ITRLISTNOMBCENTTRNI.STDTRNIPARMV contextoComun = new Ejecutar.ITRLISTNOMBCENTTRNI.STDTRNIPARMV();
		contextoComun.setIDINTERNOTERMTN(super.getTerminal());
		contextoComun.setIDEMPLAUT("");
		contextoComun.setNUMSEC(0);
		contextoComun.setCODTX("GCA15CON");
		contextoComun.setCODTXDI("");

		Ejecutar.ITRLISTNOMBCENTTRNI.TRLISTNOMBCENTEVTY contextoEntradaGeneral = new Ejecutar.ITRLISTNOMBCENTTRNI.TRLISTNOMBCENTEVTY();

		Ejecutar.ITRLISTNOMBCENTTRNI.TRLISTNOMBCENTEVTY.UOCENTROP contextoEntradaCamposVacios = new Ejecutar.ITRLISTNOMBCENTTRNI.TRLISTNOMBCENTEVTY.UOCENTROP();
		if (centro.getUltimoDatoConsultaAnterior() == null) {
			contextoEntradaCamposVacios.setCODINTERNOUO("");
		} else {
			contextoEntradaCamposVacios.setCODINTERNOUO(centro
					.getUltimoDatoConsultaAnterior().toString());
		}
		contextoEntradaCamposVacios.setCODNRBEEN(super.getEntidad());
		contextoEntradaGeneral.setUOCENTROP(contextoEntradaCamposVacios);

		Ejecutar.ITRLISTNOMBCENTTRNI.TRLISTNOMBCENTEVTY.UOLACB2 contextoEntradaCampos = new Ejecutar.ITRLISTNOMBCENTTRNI.TRLISTNOMBCENTEVTY.UOLACB2();
		contextoEntradaCampos.setCODNRBEEN(super.getEntidad());

		if (!("").equals(centro.getNombre())) {
			contextoEntradaCampos.setNOMBCENTUO("%" + centro.getNombre() + "%");
		}

		contextoEntradaGeneral.setUOLACB2(contextoEntradaCampos);
		contextoEntrada.setUOLACB(contextoVacio);
		contextoEntrada.setSTDTRNIPARMV(contextoComun);
		contextoEntrada.setTRLISTNOMBCENTEVTY(contextoEntradaGeneral);

		return this.obtenerRespuestaWebService(contextoEntrada, centro);
	}

	/**
	 * Método que ejecuta el ws de búsqueda de nombre del centro para las
	 * consultas de catalogo.
	 * 
	 * @param entidad
	 *            La entidad a consultar.
	 * @return List<CatalogoBean> Lista de resultados que obtiene el WS.
	 * @throws NoControlableException
	 *             Excepcion en caso de que el servicio muestre un error
	 *             inesperado.
	 */
	@Cache
	public List<CatalogoBean> ejecutarTRN(String entidad)
			throws NoControlableException {

		final List<CatalogoBean> result = new CopyOnWriteArrayList<CatalogoBean>();

		Ejecutar.ITRLISTNOMBCENTTRNI contextoEntrada = new Ejecutar.ITRLISTNOMBCENTTRNI();
		contextoEntrada.setELEVATORPOSITION(1);
		contextoEntrada.setSCROLLABLEOCCURS(SCROLLABLE_OCCURS);

		Ejecutar.ITRLISTNOMBCENTTRNI.STDTRNIPARMV contextoComun = new Ejecutar.ITRLISTNOMBCENTTRNI.STDTRNIPARMV();
		contextoComun.setIDINTERNOTERMTN(ProveedorMensajeSpringUtils
				.getValorConfiguracion("catalogo.terminal"));
		contextoComun.setIDEMPLAUT("");
		contextoComun.setNUMSEC(0);
		contextoComun.setCODTX(CodTxConstants.COD_TX_TR_LIST_NOMB_CENT_TRN);
		contextoComun.setCODTXDI("");

		Ejecutar.ITRLISTNOMBCENTTRNI.TRLISTNOMBCENTEVTY contextoEntradaGeneral = new Ejecutar.ITRLISTNOMBCENTTRNI.TRLISTNOMBCENTEVTY();

		Ejecutar.ITRLISTNOMBCENTTRNI.TRLISTNOMBCENTEVTY.UOCENTROP contextoEntradaCamposVacios = new Ejecutar.ITRLISTNOMBCENTTRNI.TRLISTNOMBCENTEVTY.UOCENTROP();
		contextoEntradaCamposVacios.setCODNRBEEN(entidad);
		contextoEntradaGeneral.setUOCENTROP(contextoEntradaCamposVacios);

		Ejecutar.ITRLISTNOMBCENTTRNI.TRLISTNOMBCENTEVTY.UOLACB2 contextoEntradaCampos = new Ejecutar.ITRLISTNOMBCENTTRNI.TRLISTNOMBCENTEVTY.UOLACB2();
		contextoEntradaCampos.setCODNRBEEN(entidad);

		contextoEntradaGeneral.setUOLACB2(contextoEntradaCampos);
		contextoEntrada.setSTDTRNIPARMV(contextoComun);
		contextoEntrada.setTRLISTNOMBCENTEVTY(contextoEntradaGeneral);

		EjecutarResult respuesta = (EjecutarResult) servicioWebUtils
				.ejecutarWS(BusquedaNombreCentroServicio.class, contextoEntrada);

		try {
			super.verificaRespuesta(respuesta);
			for (int i = 0; i < respuesta.getResponseBansefi()
					.getOTRLISTNOMBCENTTRNO().getNUMBEROFRECORDS(); i++) {
				CatalogoBean catalogo = new CatalogoBean();
				catalogo.setClaveFila(respuesta.getResponseBansefi()
						.getOTRLISTNOMBCENTTRNO().getTRLISTNOMBCENTEVTZ()
						.getUOLAV2().get(i).getCODINTERNOUO());
				catalogo.setDescripcionC((respuesta.getResponseBansefi()
						.getOTRLISTNOMBCENTTRNO().getTRLISTNOMBCENTEVTZ()
						.getUOLAV2().get(i).getNOMBCENTUO() != null ? respuesta
						.getResponseBansefi().getOTRLISTNOMBCENTTRNO()
						.getTRLISTNOMBCENTEVTZ().getUOLAV2().get(i)
						.getNOMBCENTUO().trim().toUpperCase() : ""));
				catalogo.setDescripcionL(respuesta.getResponseBansefi()
						.getOTRLISTNOMBCENTTRNO().getTRLISTNOMBCENTEVTZ()
						.getUOLAV2().get(i).getCODINTERNOUO()
						+ " - "
						+ (respuesta.getResponseBansefi()
								.getOTRLISTNOMBCENTTRNO()
								.getTRLISTNOMBCENTEVTZ().getUOLAV2().get(i)
								.getNOMBCENTUO() != null ? respuesta
								.getResponseBansefi().getOTRLISTNOMBCENTTRNO()
								.getTRLISTNOMBCENTEVTZ().getUOLAV2().get(i)
								.getNOMBCENTUO().trim().toUpperCase() : ""));
				result.add(catalogo);
			}
		} catch (ControlableException ce) {
			if (ce.getRtncod() != RETURN_COD_SIN_DATOS) {
				throw ce;
			} else {
				return result;
			}
		}

		boolean hayMasDatos = respuesta.getResponseBansefi()
				.getOTRLISTNOMBCENTTRNO().getMOREDATAIN() == 1;
		while (hayMasDatos) {
			contextoEntradaCamposVacios.setCODINTERNOUO(respuesta
					.getResponseBansefi()
					.getOTRLISTNOMBCENTTRNO()
					.getTRLISTNOMBCENTEVTZ()
					.getUOLAV2()
					.get(respuesta.getResponseBansefi()
							.getOTRLISTNOMBCENTTRNO().getNUMBEROFRECORDS() - 1)
					.getCODINTERNOUO());

			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
					BusquedaNombreCentroServicio.class, contextoEntrada);

			try {
				super.verificaRespuesta(respuesta);
				for (int i = 0; i < respuesta.getResponseBansefi()
						.getOTRLISTNOMBCENTTRNO().getNUMBEROFRECORDS(); i++) {
					CatalogoBean catalogo = new CatalogoBean();
					catalogo.setClaveFila(respuesta.getResponseBansefi()
							.getOTRLISTNOMBCENTTRNO().getTRLISTNOMBCENTEVTZ()
							.getUOLAV2().get(i).getCODINTERNOUO());
					catalogo.setDescripcionC((respuesta.getResponseBansefi()
							.getOTRLISTNOMBCENTTRNO().getTRLISTNOMBCENTEVTZ()
							.getUOLAV2().get(i).getNOMBCENTUO() != null ? respuesta
							.getResponseBansefi().getOTRLISTNOMBCENTTRNO()
							.getTRLISTNOMBCENTEVTZ().getUOLAV2().get(i)
							.getNOMBCENTUO().trim().toUpperCase()
							: ""));
					catalogo.setDescripcionL(respuesta.getResponseBansefi()
							.getOTRLISTNOMBCENTTRNO().getTRLISTNOMBCENTEVTZ()
							.getUOLAV2().get(i).getCODINTERNOUO()
							+ " - "
							+ (respuesta.getResponseBansefi()
									.getOTRLISTNOMBCENTTRNO()
									.getTRLISTNOMBCENTEVTZ().getUOLAV2().get(i)
									.getNOMBCENTUO() != null ? respuesta
									.getResponseBansefi()
									.getOTRLISTNOMBCENTTRNO()
									.getTRLISTNOMBCENTEVTZ().getUOLAV2().get(i)
									.getNOMBCENTUO().trim().toUpperCase() : ""));

					result.add(catalogo);
				}
			} catch (ControlableException ce) {
				if (ce.getRtncod() != RETURN_COD_SIN_DATOS) {
					throw ce;
				} else {
					return result;
				}
			}

		}
		return result;
	}

	/**
	 * Método que muestra el resultado del ws de búsqueda de nombre de centro
	 * cuando es ejecutado.
	 * 
	 * @param contextoEntrada
	 *            Contexto de entrada de todos los datos.
	 * @param centro
	 *            Bean de centro para almacenar paginación.
	 * @return List Lista que obtiene del WS
	 * @throws NoControlableException
	 *             Excepción con error que puede lanzar el servicio.
	 * @throws ControlableException
	 *             Excepcion en caso de que el servicio muestre un error
	 *             esperado.
	 */
	private List<Object> obtenerRespuestaWebService(
			ITRLISTNOMBCENTTRNI contextoEntrada, CentroBusquedaBean centro)
			throws NoControlableException, ControlableException {
		List<Object> resultados = new CopyOnWriteArrayList<>();

		EjecutarResult respuesta = null;
		respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
				BusquedaNombreCentroServicio.class, contextoEntrada);

		super.verificaRespuesta(respuesta);
		List<UOLAV2> resultTRN = respuesta.getResponseBansefi().getOTRLISTNOMBCENTTRNO()
				.getTRLISTNOMBCENTEVTZ().getUOLAV2();
		centro.setMasDatos(respuesta.getResponseBansefi().getOTRLISTNOMBCENTTRNO()
				.getMOREDATAIN() == 1);
		for (UOLAV2 resultado : resultTRN) {
			if (!("").equals(resultado.getCODINTERNOUO().trim())) {
				CentroBusquedaBean centroResultado = wrapperBeanService
						.wrappBeanBusquedaCentroNombre(resultado);
				if (("1").equals(centroResultado.getEstado())) {
					centroResultado.setEstado("ABIERTO");
				} else {
					centroResultado.setEstado("CERRADO");
				}
				resultados.add(centroResultado);
				if (respuesta.getResponseBansefi().getOTRLISTNOMBCENTTRNO().getMOREDATAIN() == 1) {
					centro.setUltimoDatoConsultaAnterior(resultado
							.getCODINTERNOUO().trim());
				}
			}
		}

		return resultados;
	}

}
