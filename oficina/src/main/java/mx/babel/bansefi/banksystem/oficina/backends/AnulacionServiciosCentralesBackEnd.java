package mx.babel.bansefi.banksystem.oficina.backends;

import java.io.Serializable;

import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.oficina.beans.AnulacionServiciosCentralesRespuestaBean;
import mx.babel.bansefi.banksystem.oficina.beans.DiarioElectronicoDetalleRespuestaBean;
import mx.babel.bansefi.banksystem.oficina.webservices.anulacionservicioscentrales.AnulacionServiciosCentralesServicio;
import mx.babel.bansefi.banksystem.oficina.webservices.anulacionservicioscentrales.Ejecutar;
import mx.babel.bansefi.banksystem.oficina.webservices.anulacionservicioscentrales.EjecutarResult;
import mx.babel.bansefi.banksystem.oficina.wrappers.AnulacionServicioCentralWrapper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AnulacionServiciosCentralesBackEnd extends BackEndBean implements
		Serializable {

	private static final long serialVersionUID = -734175047785889596L;
	private static final Logger LOGGER = LogManager
			.getLogger(AnulacionServiciosCentralesBackEnd.class);

	@Autowired
	ServicioWebUtils servicioWebUtils;

	@Autowired
	AnulacionServicioCentralWrapper anulacionServicioCentralWrapper;

	/**
	 * Funcion principal que descencadena la ejecución de la TRN
	 * 
	 * @param contadores
	 *            Bean generico para Contadores de Centro y Puesto
	 * @return ContadoresCentroBean
	 */
	public AnulacionServiciosCentralesRespuestaBean ejecutarWS(
			DiarioElectronicoDetalleRespuestaBean diarioElectronicoBean) throws InstantiationException{
		Ejecutar contexto = initPeticion(diarioElectronicoBean);
		EjecutarResult respuesta = ejecutarWS(contexto);
		super.verificaRespuestaWS(respuesta);
		if (verificaResponseBansefi(respuesta)) {
			return anulacionServicioCentral(respuesta);
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
	private AnulacionServiciosCentralesRespuestaBean anulacionServicioCentral(EjecutarResult response) {
		AnulacionServiciosCentralesRespuestaBean respuesta = null;
		// Se recuperan y wrappean los datos de la cuenta
		respuesta = anulacionServicioCentralWrapper.wrappRespuesta(response.getResponseBansefi());

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
			DiarioElectronicoDetalleRespuestaBean diarioElectronicoBean) {
		Ejecutar consulta = new Ejecutar();

		initialize(consulta);

		// Datos anulación
		consulta = anulacionServicioCentralWrapper
				.wrappEjecutarAnulacion(diarioElectronicoBean);

		consulta.setCENTRO(super.getSucursal());
		consulta.setENTIDAD(super.getEntidad());

		return consulta;

	}

	/**
	 * Función para invocar al servicio web y obtener su respuesta.
	 * 
	 * @param contexto
	 *            Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	private EjecutarResult ejecutarWS(Ejecutar contexto) {
		EjecutarResult respuesta = null;
		try {
			respuesta = (EjecutarResult) this.servicioWebUtils.ejecutarWS(
					AnulacionServiciosCentralesServicio.class,
					contexto.getENTIDAD(), contexto.getCENTRO(),
					contexto.getACUERDO(), contexto.getIMPORTE(),
					contexto.getMOVIMIENTO(), contexto.getSECDE(),
					contexto.getTAREA());
		} catch (NoControlableException e) {
			LOGGER.error("error", e);
			throw new NoControlableException(
					"Error al invocar servicio web de consulta de "
							+ "contadores de puesto host.", e);
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
