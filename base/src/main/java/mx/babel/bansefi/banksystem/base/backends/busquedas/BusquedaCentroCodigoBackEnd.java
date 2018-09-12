package mx.babel.bansefi.banksystem.base.backends.busquedas;

import java.io.Serializable;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.busqueda.CentroBusquedaBean;
import mx.babel.bansefi.banksystem.base.webservices.busquedacodigocentro.BusquedaCodigoCentroServicio;
import mx.babel.bansefi.banksystem.base.webservices.busquedacodigocentro.Ejecutar;
import mx.babel.bansefi.banksystem.base.webservices.busquedacodigocentro.EjecutarResult;
import mx.babel.bansefi.banksystem.base.wrappers.WrapperBusquedasUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Backend para ejecutar los ws de búsqueda de centro.
 * 
 * @author alejandro.pineda
 * 
 */

@Component
public class BusquedaCentroCodigoBackEnd extends BackEndBean implements
		Serializable {

	private static final long serialVersionUID = -2794999688908209334L;

	@Autowired
	private WrapperBusquedasUtils wrapperBeanService;

	@Autowired
	ServicioWebUtils servicioWebUtils;

	public BusquedaCentroCodigoBackEnd() {
	}

	/**
	 * Método que ejecuta el ws de búsqueda de codigo del centro.
	 * 
	 * @param obj
	 *            Objeto Bean que registra los datos.
	 * @return CentroBusquedaBean navega a la ruta de Mantenimiento de centros.
	 * @throws NoControlableException
	 *             Excepcion en caso de que el servicio muestre un error
	 *             inesperado.
	 * @throws ControlableException
	 *             Excepcion en caso de que el servicio muestre un error
	 *             esperado.
	 */
	public CentroBusquedaBean ejecutarTRN(Object obj)
			throws NoControlableException {
		CentroBusquedaBean centro = (CentroBusquedaBean) obj;
		Ejecutar.ITRCONMINCENTRO2TRN contextoEntrada = new Ejecutar.ITRCONMINCENTRO2TRN();
		contextoEntrada.setELEVATORPOSITION(1);
		contextoEntrada.setSCROLLABLEOCCURS(SCROLLABLE_OCCURS);

		Ejecutar.ITRCONMINCENTRO2TRN.UOLACB uolacb = new Ejecutar.ITRCONMINCENTRO2TRN.UOLACB();
		uolacb.setCODINTERNOUO(centro.getCodigoCentro());
		uolacb.setCODNRBEEN(super.getEntidad());
		uolacb.setNOMBCENTUO("");
		uolacb.setNOMBENTEN("");
		contextoEntrada.setUOLACB(uolacb);

		Ejecutar.ITRCONMINCENTRO2TRN.STDTRNIPARMV contextoComun = new Ejecutar.ITRCONMINCENTRO2TRN.STDTRNIPARMV();
		contextoComun.setIDINTERNOTERMTN(super.getTerminal());
		contextoComun.setIDEMPLAUT("");
		contextoComun.setNUMSEC(0);
		contextoComun.setCODTX("GCA16CON");
		contextoComun.setCODTXDI("");

		Ejecutar.ITRCONMINCENTRO2TRN.TRCONMINCENTROEVTY contextoEntradaCampos = new Ejecutar.ITRCONMINCENTRO2TRN.TRCONMINCENTROEVTY();
		Ejecutar.ITRCONMINCENTRO2TRN.TRCONMINCENTROEVTY.UOCENTROP contextoEntradaCamposId = new Ejecutar.ITRCONMINCENTRO2TRN.TRCONMINCENTROEVTY.UOCENTROP();

		contextoEntradaCamposId.setCODNRBEEN(super.getEntidad());
		contextoEntradaCamposId.setCODINTERNOUO(centro.getCodigoCentro());
		contextoEntradaCampos.setUOCENTROP(contextoEntradaCamposId);

		contextoEntrada.setSTDTRNIPARMV(contextoComun);
		contextoEntrada.setTRCONMINCENTROEVTY(contextoEntradaCampos);

		return this.obtenerRespuestaWebService(contextoEntrada, centro);
	}

	/**
	 * Método que muestra el resultado del ws de búsqueda de código de centro
	 * cuando es ejecutado.
	 * 
	 * @param contextoEntrada
	 *            Contexto de entrada de todos los datos.
	 * @return CentroBusquedaBean Navega a la ruta de Mantenimiento de Centros.
	 * @throws NoControlableException
	 *             Excepción con error que puede lanzar el servicio.
	 * @throws ControlableException
	 *             Excepcion en caso de que el servicio muestre un error
	 *             esperado.
	 */
	private CentroBusquedaBean obtenerRespuestaWebService(
			Ejecutar.ITRCONMINCENTRO2TRN contextoEntrada, CentroBusquedaBean centro)
			throws NoControlableException, ControlableException {
		EjecutarResult respuesta = null;
		CentroBusquedaBean objeto = null;
		respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
				BusquedaCodigoCentroServicio.class, contextoEntrada);

		super.verificaRespuesta(respuesta);
		objeto = wrapperBeanService
				.wrappBeanBusquedaCentroCodigo(respuesta.getResponseBansefi()
						.getOTRCONMINCENTRO2TRN().getTRCONMINCENTROEVTZ()
						.getUOCENTROE());
		centro.setMasDatos(false);

		return objeto;
	}

}
