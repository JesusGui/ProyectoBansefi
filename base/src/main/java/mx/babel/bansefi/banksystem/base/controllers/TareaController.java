package mx.babel.bansefi.banksystem.base.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

import mx.babel.arq.comun.utils.HashUtils;
import mx.babel.arq.storage.beans.StorageMgrBean;
import mx.babel.bansefi.banksystem.base.beans.TareaPendienteBean;
import mx.babel.bansefi.banksystem.base.enums.NavegacionEnum;
import mx.babel.bansefi.banksystem.base.enums.ParametrosFlashEnum;

import org.primefaces.context.RequestContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Controlador encargado de presentar las tareas que los usuarios vayan
 * guardando como pendientes.
 *
 * @author omar.marquez
 */
@ManagedBean
@ViewScoped
@Component
@Scope("view")
public class TareaController implements Serializable {

	private static final long serialVersionUID = -9076601253992762853L;

	private List<TareaPendienteBean> tareasPendientes;
	private TareaPendienteBean tareaSeleccionada;
	private String datosARestaurar;

	/**
	 * Constructor.
	 */
	public TareaController() {
		super();
		this.tareasPendientes = new ArrayList<>();
	}

	@SuppressWarnings("unchecked")
	@PostConstruct
	void init() {
		if (this.obtenerFlash().get(
				ParametrosFlashEnum.LISTADO_TAREAS.getParamFlash()) != null) {
			this.tareasPendientes = (List<TareaPendienteBean>) this
					.obtenerFlash().get(
							ParametrosFlashEnum.LISTADO_TAREAS.getParamFlash());
		}
	}

	// INICIA DECLARACIÓN DE GETTERS Y SETTERS

	/**
	 * Método que devuelve la lista de tareas pendientes.
	 *
	 * @return lista de tareas pendientes
	 */
	public List<TareaPendienteBean> getTareasPendientes() {
		return tareasPendientes;
	}

	/**
	 * Método que establece el contenido de la lista de tareas pendientes.
	 *
	 * @param tareasPendientes
	 */
	public void setTareasPendientes(final List<TareaPendienteBean> tareasPendientes) {
		this.tareasPendientes = tareasPendientes;
	}

	/**
	 * Método que devuelve la tarea seleccionada.
	 *
	 * @return tarea seleccionada
	 */
	public TareaPendienteBean getTareaSeleccionada() {
		return tareaSeleccionada;
	}

	/**
	 * Método que establece la tarea seleccionada.
	 *
	 * @param tareaSeleccionada
	 */
	public void setTareaSeleccionada(final TareaPendienteBean tareaSeleccionada) {
		this.tareaSeleccionada = tareaSeleccionada;
	}

	/**
	 * Método que obtiene el contenido del campo 'datosOcultos' que se encuentra
	 * en la vista.
	 *
	 * @return null o el contenido del campo 'datosOcultos'
	 */
	public String getDatosARestaurar() {
		return null;
	}

	/**
	 * Método que establece el contenido del campo 'datosOcultos' que se
	 * encuentra en la vista.
	 *
	 * @param datosARestaurar
	 */
	public void setDatosARestaurar(final String datosARestaurar) {
		this.datosARestaurar = datosARestaurar;
	}

	// INICIA DECLARACIÓN DE MÉTODOS PRINCIPALES

	/**
	 * Método privado que obtiene el objeto Flash proveniente del contexto
	 * externo.
	 *
	 * @return Flash
	 */
	private Flash obtenerFlash() {
		return FacesContext.getCurrentInstance().getExternalContext()
				.getFlash();
	}

	/**
	 * Método que obtiene las tareas mapeadas en el 'storage.js'.
	 *
	 * @return ""
	 */
	public String getTareas() {
		return "";
	}

	/**
	 * Método que almacena las tareas en la Flash de la aplicación para que
	 * posteriormente éstas sean cargadas en la tabla de tareas pendientes.
	 *
	 * @param tareas
	 */
	public void setTareas(final String tareas) {
		if (null != tareas && tareas.length() > 0) {
			final List<TareaPendienteBean> listadoTareas = new ArrayList<>();
			final String[] splittedList = tareas.split("\\|");
			String[] splittedIndex = null;
			for (final String keys : splittedList) {
				splittedIndex = keys.split("\\+");
				listadoTareas.add(new TareaPendienteBean(keys,
						splittedIndex[2], splittedIndex[1]));
			}
			Collections.sort(listadoTareas,
					new Comparator<TareaPendienteBean>() {
						@Override
						public int compare(final TareaPendienteBean tarea1,
								final TareaPendienteBean tarea2) {
							return tarea2.getHora().compareTo(tarea1.getHora());
						}
					});
			this.tareasPendientes = listadoTareas;
			obtenerFlash().put(
					ParametrosFlashEnum.LISTADO_TAREAS.getParamFlash(),
					listadoTareas);
		}
	}

	/**
	 * Método que restaura la tarea pendiente justo en el paso donde el usuario
	 * se quedó.
	 *
	 * @return tarea a restaurar
	 */
	public String restaurarFlujoOperativo(final String clave) {
		final String[] splittedIndex = clave.split("\\+");
		final byte[] restoredBytes = HashUtils.decodeB64(splittedIndex[0]);

		final String keyData = new String(restoredBytes);
		final String[] indexData = keyData.split("\\+");
		final FacesContext context = FacesContext.getCurrentInstance();

		final String beanName = "#" + "{"
				+ indexData[0].substring(0, 1).toLowerCase()
				+ indexData[0].substring(1) + "}";
		final StorageMgrBean bean = context.getApplication()
				.evaluateExpressionGet(context, beanName, StorageMgrBean.class);
		bean.restoreContext(datosARestaurar);
		if("null".equalsIgnoreCase(indexData[1])){
		    bean.setLastAction(context.getApplication()
                    .evaluateExpressionGet(context, beanName.replace("}", ".inicio()}"), String.class));
		}else{
            bean.setLastAction(indexData[1]);
		}
		RequestContext.getCurrentInstance().execute(
				"actualizarNumeroDeTareas()");
		return bean.getLastAction();
	}

	/**
	 * Método que redirige al usuario a la página de inicio que contiene el
	 * dashboard.
	 *
	 * @return ruta del recurso a mostrar
	 */
	public String volver() {
		return NavegacionEnum.INICIO.getRuta();
	}

	/**
	 * Método que establece la tarea a eliminar y muestra un mensaje para
	 * confirmar la acción.
	 */
	public void eliminarTarea(final TareaPendienteBean tarea) {
		this.tareaSeleccionada = tarea;
	}

	/**
	 * Método que elimina la tarea seleccionada tanto de la lista como del local
	 * storage.
	 */
	public String confirmarEliminacion() {
		tareasPendientes.remove(tareaSeleccionada);
		RequestContext.getCurrentInstance().execute(
				"storage.removeData('" + tareaSeleccionada.getClave() + "')");
		RequestContext.getCurrentInstance().execute(
				"actualizarNumeroDeTareas()");
		
		return NavegacionEnum.TAREAS_PENDIENTES.getRuta();
	}

}