package mx.babel.bansefi.banksystem.base.wrappers;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.sesion.contexto.beans.NotificacionBean;
import mx.babel.arq.sesion.utils.ContextoUtils;
import mx.babel.bansefi.banksystem.base.beans.busqueda.NotificacionBusquedaBean;
import mx.babel.bansefi.banksystem.base.constants.ConstantesFuncionales;
import mx.babel.bansefi.banksystem.base.utils.CatalogoCentrosLoaderTask;
import mx.babel.bansefi.banksystem.base.webservices.mensajes.ResponseBansefi;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.joda.time.DateTimeComparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Clase encargada de realizar los mapeos entre el servicio de notificaciones y
 * el NotificacionBean.
 * 
 * @author omar.marquez
 * 
 */
@Component
public class NotificacionWrapper implements Serializable {

	private static final long serialVersionUID = 6376852818506142420L;

	@Autowired
	DozerBeanMapper dozerBeanMapper;

	@Autowired
	ContextoUtils contextoUtils;

	@Autowired
	CatalogoCentrosLoaderTask catalogoCentrosLoaderTask;

	/**
	 * Método encargado de mapear los atributos de un objeto tipo
	 * NotificacionBean a otro tipo ResponseBansefi.
	 * 
	 * @param notificacionBean
	 * @return responseBansefi
	 */
	public ResponseBansefi consultaNotificacionWrapper(
			NotificacionBean notificacionBean) {
		Mapper mapper = dozerBeanMapper;
		return mapper.map(notificacionBean, ResponseBansefi.class,
				"mapeoNotificacion");
	}

	/**
	 * Método encargado de mapear los atributos de la respuesta en un
	 * NotificacionBean.
	 * 
	 * @param responseBansefi
	 * @return notificacionBean
	 */
	public NotificacionBean consultaNotificacionWrapper(
			ResponseBansefi responseBansefi) {
		Mapper mapper = dozerBeanMapper;
		return mapper.map(responseBansefi, NotificacionBean.class,
				"mapeoNotificacion");
	}

	/**
	 * Método que a partir de la lista de resultados (List<NotificacionBean>)
	 * establece los valores en una lista de objetos de búsqueda
	 * (List<NotificacionBusquedaBean>).
	 * 
	 * @param objBusqueda
	 * @param notificaciones
	 * @return notificacionesBusqueda
	 */
	public List<NotificacionBusquedaBean> wrappBusquedaNotificacionesBean(
			Object objSearchParam, List<NotificacionBean> items) {
		List<NotificacionBusquedaBean> notificaciones = new ArrayList<NotificacionBusquedaBean>();
		if (objSearchParam != null
				&& objSearchParam instanceof NotificacionBusquedaBean) {
			for (NotificacionBean item : items) {
				NotificacionBusquedaBean notificacionBusquedaBean = new NotificacionBusquedaBean();
				notificacionBusquedaBean.setIndUrgencia(item.getIndUrgencia());
				notificacionBusquedaBean.setFechaVigenteDesde(item
						.getFechaVigenteDesde());
				notificacionBusquedaBean.setFechaVigenteHasta(item
						.getFechaVigenteHasta());

				// Verificamos la fecha hasta para obtener el estado.
				if (compararFechaConSistema(item.getFechaVigenteHasta())) {
					notificacionBusquedaBean.setIndEstado("SI");
				} else {
					notificacionBusquedaBean.setIndEstado("NO");
				}

				// Obtenemos el periodo de validez.
				DateFormat formatter = new SimpleDateFormat(
						ConstantesFuncionales.GENERAL_DATE_FORMATTER);
				String periodoValidez = formatter.format(item
						.getFechaVigenteDesde())
						+ " - "
						+ formatter.format(item.getFechaVigenteHasta());
				notificacionBusquedaBean.setPeriodoValidez(periodoValidez);

				notificacionBusquedaBean.setTexto(item.getTexto());
				notificacionBusquedaBean.setClave(item.getClave());

				// Obtenemos la descripción del centro con base a su ID.
				notificacionBusquedaBean.setCentro(obtenerDescCentro(
						item.getCentro()).trim());

				notificaciones.add(notificacionBusquedaBean);
			}
		}
		filtrarListaNotificaciones(objSearchParam, notificaciones);
		return notificaciones;
	}

	/**
	 * Método privado que aplica los filtros necesarios para la búsqueda.
	 * 
	 * @param objSearchParam
	 * @param listToBeFiltered
	 */
	private void filtrarListaNotificaciones(Object objSearchParam,
			List<NotificacionBusquedaBean> listToBeFiltered) {
		// Filtro 1: Sólo notificaciones urgentes.
		for (Iterator<NotificacionBusquedaBean> iter = listToBeFiltered
				.iterator(); iter.hasNext();) {
			NotificacionBusquedaBean notificacion = iter.next();
			if (((NotificacionBusquedaBean) objSearchParam)
					.isFiltroNotificacionUrgente()
					&& "NO".equals(notificacion.getIndUrgencia())) {
				iter.remove();
			}
		}
		// Filtro 2: Sólo notificaciones activas.
		for (Iterator<NotificacionBusquedaBean> iter = listToBeFiltered
				.iterator(); iter.hasNext();) {
			NotificacionBusquedaBean notificacion = iter.next();
			if (((NotificacionBusquedaBean) objSearchParam)
					.isFiltroNotificacionActiva()
					&& "NO".equals(notificacion.getIndEstado())) {
				iter.remove();
			}
		}
		// Filtro 3: Sólo notificaciones dentro del periodo de validez.
		for (Iterator<NotificacionBusquedaBean> iter = listToBeFiltered
				.iterator(); iter.hasNext();) {
			NotificacionBusquedaBean notificacion = iter.next();
			boolean indicador1 = compararFechas(
					((NotificacionBusquedaBean) objSearchParam)
							.getFiltroFechaHasta(),
					((NotificacionBusquedaBean) objSearchParam)
							.getFiltroFechaDesde(), notificacion
							.getFechaVigenteDesde());
			boolean indicador2 = compararFechas(
					((NotificacionBusquedaBean) objSearchParam)
							.getFiltroFechaHasta(),
					((NotificacionBusquedaBean) objSearchParam)
							.getFiltroFechaDesde(), notificacion
							.getFechaVigenteHasta());
			if (!(indicador1 && indicador2)) {
				iter.remove();
			}
		}
	}

	/**
	 * Método privado que devuelve 'true' sí la fecha recibida como parámetro
	 * (fecha) se encuentra dentro del período comprendido por fechaMin y
	 * fechaMax.
	 * 
	 * @param fechaMax
	 * @param fechaMin
	 * @param fecha
	 * @return indicador booleano
	 */
	private boolean compararFechas(Date fechaMax, Date fechaMin, Date fecha) {
		return fecha.compareTo(fechaMax) <= 0 && fecha.compareTo(fechaMin) >= 0;
	}

	/**
	 * Método privado que devuelve 'true' sí la fecha recibida como parámetro es
	 * mayor o igual a la fecha actual.
	 * 
	 * @param fecha
	 * @return indicador booleano
	 */
	private boolean compararFechaConSistema(Date fecha) {
		return DateTimeComparator.getDateOnlyInstance().compare(fecha,
				Calendar.getInstance().getTime()) >= 0;
	}

	/**
	 * Método que devuelve la descripción asociada al ID de un centro.
	 * 
	 * @param idCentro
	 * @return descripción larga
	 */
	private String obtenerDescCentro(final String idCentro) {
		try {
			return catalogoCentrosLoaderTask.getCatalogoBean(
					contextoUtils.getEntidad(), idCentro).getDescripcionL();
		} catch (ControlableException | NullPointerException e) {
			return new String();
		}
	}

}