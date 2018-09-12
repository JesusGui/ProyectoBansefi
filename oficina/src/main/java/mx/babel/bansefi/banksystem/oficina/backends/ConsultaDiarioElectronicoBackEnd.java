package mx.babel.bansefi.banksystem.oficina.backends;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.oficina.beans.DiarioElectronicoBusquedaBean;
import mx.babel.bansefi.banksystem.oficina.beans.DiarioElectronicoResultadoBean;
import mx.babel.bansefi.banksystem.oficina.webservices.consultadiarioelectronico.ConsultaDiarioElectronicoServicio;
import mx.babel.bansefi.banksystem.oficina.webservices.consultadiarioelectronico.Ejecutar;
import mx.babel.bansefi.banksystem.oficina.webservices.consultadiarioelectronico.EjecutarResult;
import mx.babel.bansefi.banksystem.oficina.webservices.consultadiarioelectronico.ResponseBansefi;
import mx.babel.bansefi.banksystem.oficina.wrappers.DiarioElectronicoWrapper;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Backend que consulta el diario electronico a partir de filtros y retorna
 * operaciones
 * 
 * @author manuel.nieto
 * 
 */
@Component
public class ConsultaDiarioElectronicoBackEnd extends BackEndBean implements
		Serializable {

	private static final long serialVersionUID = -3951459195554460665L;

	private static final Logger LOGGER = LogManager
			.getLogger(ConsultaDetalleDiarioElectronicoBackEnd.class);

	@Autowired
	ServicioWebUtils servicioWebUtils;

	@Autowired
	DiarioElectronicoWrapper diarioElectronicoWrapper;

	/**
	 * Funcion principal que descencadena la ejecución de la TRN
	 * 
	 * @param contadores
	 *            Bean generico para Contadores de Centro y Puesto
	 * @return ContadoresCentroBean
	 */
	public List<DiarioElectronicoResultadoBean> ejecutarWS(
			DiarioElectronicoBusquedaBean diarioElectronicoBusquedaBean)
			throws IllegalArgumentException {
		Ejecutar contexto = initPeticion(diarioElectronicoBusquedaBean);
		EjecutarResult respuesta = ejecutarWS(contexto);
		super.verificaRespuestaWS(respuesta);
		if (verificaResponseBansefi(respuesta)) {
			return consultaDetalleDiarioElectronico(respuesta);
		}
		return null;
	}

	/**
	 * Funcion que recibe la respuesta de la TRN para analizarla y wrappear la
	 * respuesta
	 * 
	 * @param response
	 *            respuesta de la transacción
	 * @return ContadoresCentroBean
	 */
	private List<DiarioElectronicoResultadoBean> consultaDetalleDiarioElectronico(
			EjecutarResult response) {
		List<DiarioElectronicoResultadoBean> respuesta = new ArrayList<DiarioElectronicoResultadoBean>();

		// Se recuperan y wrappean los datos de la cuenta
		// Se recorre el arreglo de la respuesta
		for (ResponseBansefi resp : response.getResponseBansefi()
				.getResponseBansefi()) {
			DiarioElectronicoResultadoBean item;
			item = diarioElectronicoWrapper
					.wrappRespuestaDiarioElectronico(resp);

			respuesta.add(item);
		}

		return respuesta;
	}

	/**
	 * Inicializa los datos de entrada que necesita el contexto de la TRN para
	 * realizar la petición
	 * 
	 * @param contadoresCentroBean
	 * @return Ejecutar.ITRTNCONTHOSTCNSTRN
	 */
	private Ejecutar initPeticion(
			DiarioElectronicoBusquedaBean diarioElectronicoBusquedaBean) {
		Ejecutar consulta = new Ejecutar();

		initialize(consulta);

		consulta = diarioElectronicoWrapper
				.wrappConsultaDiarioElectronico(diarioElectronicoBusquedaBean);

		if (consulta.getSIGNO() == null) {
			consulta.setSIGNO("A");
		}

		if (consulta.getIMPORTEHASTA() == null
				|| consulta.getIMPORTEHASTA().equals(BigDecimal.ZERO)) {
			consulta.setIMPORTEHASTA(new BigDecimal("999999999999999"));
		}

		if (StringUtils.isBlank(consulta.getHORAFIN())) {
			consulta.setHORAFIN("23.59.59");
		}
		return consulta;

	}

	/**
	 * Función para invocar al servicio web y obtener su respuesta.
	 * 
	 * @param contexto
	 *            Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	private EjecutarResult ejecutarWS(Ejecutar contexto)
			throws IllegalArgumentException {
		EjecutarResult respuesta = null;
		try {
			respuesta = (EjecutarResult) this.servicioWebUtils.ejecutarWS(
					ConsultaDiarioElectronicoServicio.class,
					contexto.getFECHADESDE(), contexto.getFECHAHASTA(),
					contexto.getIMPORTEDESDE(), contexto.getIMPORTEHASTA(),
					contexto.getIDTASK(), contexto.getENTIDAD(),
					contexto.getCENTRO(), contexto.getTERMIN(),
					contexto.getHORAINI(), contexto.getHORAFIN(),
					contexto.getSIGNO(), contexto.getTIPOOPERACION(),
					contexto.getACUERDO(), contexto.getUSUARIO());

		} catch (NoControlableException e) {
			LOGGER.error("error", e);
			throw new NoControlableException(
					"Error al invocar servicio web de consulta de "
							+ "contadores de puesto host.", e);
		} catch(IllegalArgumentException ex){
			LOGGER.error("error", ex);
			throw new IllegalArgumentException(
					"Error al invocar servicio web de consulta de "
							+ "contadores de puesto host.", ex);
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

}
