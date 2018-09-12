package mx.babel.bansefi.banksystem.base.backends;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import mx.babel.arq.comun.beans.PaginacionBean;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.beans.CuentaContableBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.constants.ConstantesFuncionales;
import mx.babel.bansefi.banksystem.base.webservices.consultacuentascontables.ConsultaCuentasContablesServicio;
import mx.babel.bansefi.banksystem.base.webservices.consultacuentascontables.Ejecutar;
import mx.babel.bansefi.banksystem.base.webservices.consultacuentascontables.Ejecutar.ITRCCAMLSTTRNI.TRCCAMLSTEVTY.CCCTACTBLEP;
import mx.babel.bansefi.banksystem.base.webservices.consultacuentascontables.EjecutarResult;
import mx.babel.bansefi.banksystem.base.webservices.consultacuentascontables.ResponseBansefi;
import mx.babel.bansefi.banksystem.base.webservices.consultacuentascontables.ResponseBansefi.OTRCCAMLSTTRNO.TRCCAMLSTEVTZ;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConsultaCuentasContablesBackEnd extends BackEndBean implements
		Serializable {

	private static final long serialVersionUID = -1963391200432048339L;

	@Autowired
	ServicioWebUtils servicioWebUtils;

	private static final Logger LOGGER = LogManager
			.getLogger(ConsultaCuentasContablesBackEnd.class);

	/**
	 * Función principal que descencadena todo el llamado de la TRN/WebService
	 * 
	 */
	public List<CuentaContableBean> ejecutarTRN() {
		List<CuentaContableBean> cuentas = new ArrayList<CuentaContableBean>();

		PaginacionBean paginacion = new PaginacionBean();
		paginacion.setMasDatos(true);

		while (paginacion.getMasDatos()) {
			Ejecutar.ITRCCAMLSTTRNI contexto = initPeticion(paginacion);
			EjecutarResult respuesta = ejecutarWS(contexto);
			super.verificaRespuesta(respuesta);
			if (verificaResponseBansefi(respuesta)) {
				respuestaConsultaCuentasOperativas(
						respuesta.getResponseBansefi(), cuentas);

				if (respuesta.getResponseBansefi().getOTRCCAMLSTTRNO()
						.getMOREDATAIN() == 1) {
					paginacion.setUltimoDatoConsultaAnterior(cuentas.get(
							cuentas.size() - 1).getIdCuentaContable());
				} else {
					paginacion.setMasDatos(false);
				}
			} else {
				paginacion.setMasDatos(false);
			}
		}

		return cuentas;
	}

	/**
	 * Funcion que recibe la respuesta de la TRN y Wrappea a un objeto propio
	 * del sistema
	 * 
	 */
	private void respuestaConsultaCuentasOperativas(ResponseBansefi response,
			List<CuentaContableBean> cuentas) {
		if (verificaRespuestaCliente(response)) {
			// Se recuperan y wrappean los datos de la cuenta
			for (TRCCAMLSTEVTZ objeto : response.getOTRCCAMLSTTRNO()
					.getTRCCAMLSTEVTZ()) {
				CuentaContableBean cuenta = new CuentaContableBean();
				cuenta.setCodigoCuentaContable(objeto.getCODCTACTBLE().trim());
				cuenta.setIdCuentaContable(objeto.getIDCTACTBLE().trim());
				cuenta.setNombreCuentaContable(objeto.getNOMCTACTBLE().trim());
				cuenta.setSaldo(objeto.getIMPACSDOPNTAL());
				cuentas.add(cuenta);
			}
		}
	}

	/**
	 * Función que inicializa los parametros de entrada que necesita el servicio
	 * web para ser ejecutado
	 * 
	 * @param contadoresCentroBean
	 * @return Ejecutar.ITROBTECONTTRNI
	 */
	private Ejecutar.ITRCCAMLSTTRNI initPeticion(PaginacionBean paginacionBean) {
		Ejecutar.ITRCCAMLSTTRNI contexto = new Ejecutar.ITRCCAMLSTTRNI();
		Ejecutar.ITRCCAMLSTTRNI.STDTRNIPARMV contextoEntrada = new Ejecutar.ITRCCAMLSTTRNI.STDTRNIPARMV();
		Ejecutar.ITRCCAMLSTTRNI.TRCCAMLSTEVTY datosEntrada = new Ejecutar.ITRCCAMLSTTRNI.TRCCAMLSTEVTY();

		initialize(contexto);
		initialize(datosEntrada);

		contexto.setSCROLLABLEOCCURS(50);

		CCCTACTBLEP ccctactblep = datosEntrada.getCCCTACTBLEP();
		ccctactblep.setCODNRBEEN(super.getEntidad());
		ccctactblep.setCODINTERNOUO(super.getSucursal());
		ccctactblep.setCODNUMRCOMONEDA("MXN");

		if (paginacionBean != null
				&& !StringUtils.isBlank((String) paginacionBean
						.getUltimoDatoConsultaAnterior())) {
			ccctactblep.setIDCTACTBLE((String) paginacionBean
					.getUltimoDatoConsultaAnterior());
		}

		datosEntrada.getAMCODCTACTBLEV().setCODCTACTBLE(
				ConstantesFuncionales.CODIGO_CUENTA_CONTABLE);

		datosEntrada.getCONCCCTACTBLEV().setIDCTACTBLE("A");

		contextoEntrada.setIDINTERNOTERMTN(super.getTerminal());
		contextoEntrada.setCODTX(CodTxConstants.COD_TX_TR_CC_AM_LST_TRN);

		contexto.setTRCCAMLSTEVTY(datosEntrada);
		contexto.setSTDTRNIPARMV(contextoEntrada);

		return contexto;

	}

	/**
	 * Función para invocar al servicio web y obtener su respuesta.
	 * 
	 * @param contexto
	 *            Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	private EjecutarResult ejecutarWS(Ejecutar.ITRCCAMLSTTRNI contexto) {
		EjecutarResult respuesta = null;
		try {
			respuesta = (EjecutarResult) this.servicioWebUtils.ejecutarWS(
					ConsultaCuentasContablesServicio.class, contexto);
		} catch (NoControlableException e) {
			LOGGER.error("error", e);
			throw new NoControlableException(
					"Error al invocar servicio web de alta de "
							+ "apunte manual.", e);
		}
		return respuesta;
	}

	/**
	 * Función que valida que la respuesta del servidor no este vacía.
	 * 
	 * @param respuesta
	 *            Objeto respuesta del servicio web
	 * @return <code>true</code> si la respuesta no esta vacía.
	 */
	private Boolean verificaResponseBansefi(EjecutarResult respuesta) {
		Boolean noNulo = false;
		if (respuesta != null && respuesta.getResponseBansefi() != null) {
			noNulo = true;
		}
		return noNulo;
	}

	/**
	 * Función que valida que la respuesta Bansefi contenga un objeto con los
	 * datos de la consulta.
	 * 
	 * @param response
	 *            Respuesta Bansefi con datos de la consulta
	 * @return <code>true</code> si la respuesta Bansefi contiene elemento no
	 *         nulos
	 */
	private Boolean verificaRespuestaCliente(ResponseBansefi response) {
		Boolean noNulo = false;
		if (response != null && response.getOTRCCAMLSTTRNO() != null
				&& response.getOTRCCAMLSTTRNO().getTRCCAMLSTEVTZ() != null
				&& response.getOTRCCAMLSTTRNO().getTRCCAMLSTEVTZ() != null) {
			noNulo = true;
		}
		return noNulo;
	}
}
