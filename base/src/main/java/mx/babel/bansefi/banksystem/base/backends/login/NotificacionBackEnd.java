package mx.babel.bansefi.banksystem.base.backends.login;

import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.arq.sesion.contexto.beans.NotificacionBean;
import mx.babel.arq.sesion.contexto.beans.UsuarioBean;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.utils.PrioridadNotificacionConverter;
import mx.babel.bansefi.banksystem.base.webservices.mensajes.EjecutarResult;
import mx.babel.bansefi.banksystem.base.webservices.mensajes.NotificacionesServicio;
import mx.babel.bansefi.banksystem.base.webservices.mensajes.ResponseBansefi;
import mx.babel.bansefi.banksystem.base.wrappers.NotificacionWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Backend para el servicio de notificaciones (alta, modificación y consulta).
 * 
 * @author omar.marquez
 * 
 */
@Component
public class NotificacionBackEnd extends BackEndBean {

	private static final long serialVersionUID = -830011070922834560L;

	private PrioridadNotificacionConverter prioridadConverter;
	private DateFormat dateFormatter;

	@Autowired
	ServicioWebUtils servicioWebUtils;

	@Autowired
	NotificacionWrapper notificacionWrapper;

	/**
	 * Constructor.
	 */
	public NotificacionBackEnd() {
		super();
		this.dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
		this.prioridadConverter = new PrioridadNotificacionConverter();
	}

	/**
	 * Método que invoca al servicio de notificaciones a partir de una opción
	 * válida (A = Alta o M = Modificación).
	 * 
	 * @param opcion
	 * @param usuarioBean
	 * @param notificacionBean
	 * @throws NullPointerException
	 * @throws NoControlableException
	 */
	public void ejecutarWS(String opcion, UsuarioBean usuarioBean,
			NotificacionBean notificacionBean) throws NullPointerException,
			NoControlableException {
		EjecutarResult resultado = null;
		switch (opcion) {
		case "A":
			resultado = ejecutarAlta(notificacionBean);
			break;
		case "M":
			resultado = ejecutarModificacion(notificacionBean);
			break;
		default:
			throw new NoControlableException(
					"Error al invocar el servicio de notificaciones.", this
							.getClass().getName()
							+ ": Opción inválida, sólo se permite 'A' o 'M'.");
		}
		if (!verificarResultado(resultado)) {
			throw new NoControlableException(
					"Error al invocar el servicio de notificaciones.", this
							.getClass().getName()
							+ ": Estado "
							+ resultado.getESTATUS()
							+ " Código "
							+ resultado.getCODIGO()
							+ " Descripción "
							+ resultado.getMENSAJE());
		}
	}

	/**
	 * Método privado que verifica que el resultado obtenido no contenga valores
	 * nulos.
	 * 
	 * @param resultado
	 * @return indicador booleano
	 */
	private boolean verificarResultado(EjecutarResult resultado) {
		if (resultado != null && resultado.getESTATUS() != null
				&& resultado.getESTATUS().equals(ESTATUS_OK)) {
			return true;
		}
		return false;
	}

	/**
	 * Método que devuelve la lista de notificaciones que podrá visualizar
	 * cualquier usuario.
	 * 
	 * @param usuarioBean
	 * @return notificaciones usuario
	 * @throws NullPointerException
	 * @throws NoControlableException
	 */
	public List<NotificacionBean> obtenerListaNotificaciones(
			UsuarioBean usuarioBean) throws NullPointerException,
			NoControlableException {
		EjecutarResult resultado = ejecutarConsulta(usuarioBean);
		List<NotificacionBean> notificaciones = new ArrayList<>();
		if (verificarResultado(resultado)) {
			for (ResponseBansefi responseBansefi : resultado
					.getResponseBansefi().getResponseBansefi()) {
				NotificacionBean notificacion = notificacionWrapper
						.consultaNotificacionWrapper(responseBansefi);
				if (notificacion != null
						&& notificacion.getClave().intValue() != 0) {
					notificaciones.add(notificacion);
				}
			}
		}
		return notificaciones;
	}

	/**
	 * Método privado encargado de realizar la consulta de notificaciones.
	 * 
	 * @param usuarioBean
	 * @return resultado
	 * @throws NullPointerException
	 * @throws NoControlableException
	 */
	private EjecutarResult ejecutarConsulta(UsuarioBean usuarioBean)
			throws NullPointerException, NoControlableException {
		return (EjecutarResult) servicioWebUtils.ejecutarWS(
				NotificacionesServicio.class, usuarioBean.getId(),
				usuarioBean.getPwd(), usuarioBean.getDireccionIp(), "C",
				usuarioBean.getEntidad(), null, usuarioBean.getSucursal(),
				null, null, null, null, null, null, null);
	}

	/**
	 * Método privado encargado de realizar el alta de una notificación nueva.
	 * 
	 * @param notificacionBean
	 * @return resultado
	 * @throws NullPointerException
	 * @throws NoControlableException
	 */
	private EjecutarResult ejecutarAlta(NotificacionBean notificacionBean)
			throws NullPointerException, NoControlableException {
		// Convertimos los valores de la notificación.
		String fechaVigenteDesde = dateFormatter.format(notificacionBean
				.getFechaVigenteDesde());
		String fechaVigenteHasta = dateFormatter.format(notificacionBean
				.getFechaVigenteHasta());
		BigInteger prioridad = prioridadConverter.convertFrom(notificacionBean
				.getIndUrgencia());
		return (EjecutarResult) servicioWebUtils.ejecutarWS(
				NotificacionesServicio.class, super.getUsuarioId(),
				super.getPassword(), super.getIp(), "A", super.getEntidad(),
				null, notificacionBean.getCentro(), super.getUsuarioId(), null,
				super.getTerminal(), prioridad, notificacionBean.getTexto(),
				fechaVigenteDesde, fechaVigenteHasta);
	}

	/**
	 * Método privado encargado de realizar modificaciones a una notificación
	 * existente.
	 * 
	 * @param notificacionBean
	 * @return resultado
	 * @throws NullPointerException
	 * @throws NoControlableException
	 */
	private EjecutarResult ejecutarModificacion(
			NotificacionBean notificacionBean) throws NullPointerException,
			NoControlableException {
		// Convertimos los valores de la notificación.
		String fechaVigenteDesde = dateFormatter.format(notificacionBean
				.getFechaVigenteDesde());
		String fechaVigenteHasta = dateFormatter.format(notificacionBean
				.getFechaVigenteHasta());
		BigInteger prioridad = prioridadConverter.convertFrom(notificacionBean
				.getIndUrgencia());
		return (EjecutarResult) servicioWebUtils.ejecutarWS(
				NotificacionesServicio.class, super.getUsuarioId(),
				super.getPassword(), super.getIp(), "M", super.getEntidad(),
				null, notificacionBean.getCentro(), super.getUsuarioId(),
				notificacionBean.getClave(), super.getTerminal(), prioridad,
				notificacionBean.getTexto(), fechaVigenteDesde,
				fechaVigenteHasta);
	}

}