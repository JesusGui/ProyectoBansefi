package mx.babel.bansefi.banksystem.oficina.backends;

import java.io.Serializable;

import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.oficina.beans.DiarioElectronicoDetalleRespuestaBean;
import mx.babel.bansefi.banksystem.oficina.beans.DiarioElectronicoResultadoBean;
import mx.babel.bansefi.banksystem.oficina.converters.DiarioElectronicoConverter;
import mx.babel.bansefi.banksystem.oficina.webservices.consultadetallediarioelectronico.ConsultaDetalleDiarioElectronicoServicio;
import mx.babel.bansefi.banksystem.oficina.webservices.consultadetallediarioelectronico.Ejecutar;
import mx.babel.bansefi.banksystem.oficina.webservices.consultadetallediarioelectronico.EjecutarResult;
import mx.babel.bansefi.banksystem.oficina.wrappers.DiarioElectronicoDetalleWrapper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Backend que consulta un detalle de una operacion en el diario electrónico
 * 
 * @author manuel.nieto
 * 
 */
@Component
public class ConsultaDetalleDiarioElectronicoBackEnd extends BackEndBean implements Serializable {

	private static final long serialVersionUID = -1305426364398230000L;
	private static final Logger LOGGER = LogManager
			.getLogger(ConsultaDetalleDiarioElectronicoBackEnd.class);

	@Autowired
	ServicioWebUtils servicioWebUtils;

	@Autowired
	DiarioElectronicoDetalleWrapper diarioElectronicoWrapper;

	@Autowired
	DiarioElectronicoConverter diarioElectronicoConverter;

	/**
	 * Funcion principal que descencadena la ejecución de la TRN
	 * 
	 * @param contadores
	 *            Bean generico para Contadores de Centro y Puesto
	 * @return ContadoresCentroBean
	 */
	public DiarioElectronicoDetalleRespuestaBean ejecutarWS(DiarioElectronicoResultadoBean diarioElectronicoBean){
		Ejecutar contexto = initPeticion(diarioElectronicoBean);
		EjecutarResult respuesta = ejecutarWS(contexto);
//		super.verificaRespuestaWS(respuesta);
		if (verificaResponseBansefi(respuesta)) {
			return consultaDetalleDiarioElectronico(respuesta,
					diarioElectronicoBean);
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
	private DiarioElectronicoDetalleRespuestaBean consultaDetalleDiarioElectronico(
			EjecutarResult response,DiarioElectronicoResultadoBean diarioElectronicoBean){
		DiarioElectronicoDetalleRespuestaBean respuesta = null;
		DiarioElectronicoDetalleRespuestaBean preFinal = null;
		
		// Se recuperan y wrappean los datos de la TRN
		respuesta = diarioElectronicoWrapper
				.wrappRespuestaDetalleDiarioElectronico(response
						.getResponseBansefi());
		
		//Se depositan algunos datos iniciales del bean anterior en el bean 
		preFinal = diarioElectronicoWrapper
				.wrappTraspasoDatosBean(diarioElectronicoBean);
		
		//Se unifican los datos de ambos beans
		diarioElectronicoWrapper.wrappTraspasoDatosDetalleBean(
				preFinal, respuesta);

		//Se da tratamiento a los datos
		respuesta = diarioElectronicoConverter.convertResponse(respuesta);

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
			DiarioElectronicoResultadoBean diarioElectronicoBean) {
		Ejecutar consulta = new Ejecutar();

		initialize(consulta);

		consulta = diarioElectronicoWrapper
				.wrappConsultaDetalleDiarioElectronico(diarioElectronicoBean);

		// Datos base
		// consulta.setUSERHEADER(super.getUsuarioId());
		// consulta.setPASSHEADER(super.getPassword());
		// consulta.setIPHEADER(super.getIp());

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
					ConsultaDetalleDiarioElectronicoServicio.class,
					contexto.getICONDIA02I());
		} catch (NoControlableException e) {
			LOGGER.error("error", e);
			throw new NoControlableException(
					"Error al invocar servicio web de consulta detalle de "
							+ "diario electronico.", e);
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
