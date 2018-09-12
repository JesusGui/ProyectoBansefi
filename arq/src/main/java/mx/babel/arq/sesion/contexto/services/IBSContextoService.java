package mx.babel.arq.sesion.contexto.services;

import java.util.List;
import java.util.Map;

import mx.babel.arq.sesion.contexto.beans.BSContexto;
import mx.babel.arq.sesion.contexto.beans.NotificacionBean;
import mx.babel.arq.sesion.contexto.beans.MenuBean;
import mx.babel.arq.sesion.contexto.beans.SubmenuBean;
import mx.babel.arq.sesion.contexto.beans.UsuarioBean;

/**
 * Interfaz que define las operaciones de acceso al contexto.
 * 
 * @author joseluis.pena
 * 
 */
public interface IBSContextoService {

	/**
	 * Recupera el número de sesiones que hay actualmente abiertas en el
	 * sistema.
	 * 
	 * @return int numero de sesiones abiertas
	 */
	int getNumSessions();

	/**
	 * Recupera el contexto del usuario si este se ha autenticado en el sistema,
	 * en caso contrario devuelve null.
	 * 
	 * @return BSContexto contexto del usuario
	 */
	BSContexto getContexto();

	/**
	 * Genera un contexto de sesion para un usuario autenticado en el sistema.
	 * 
	 * @param usuario
	 *            Datos del usuario.
	 * @param operPermitidas
	 *            Listado de operativas permitidas para el usuario.
	 * @param operFrecuentes
	 *            Listado de las operativas mas utilizadas por el usuario.
	 * @param configuracion
	 *            Mapa con los datos de configuracion.
	 * @param notificaciones
	 *            Listado de notificaciones que podrá visualizar el usuario.
	 * @return BSContexto contexto del usuario
	 */
	BSContexto construirContexto(UsuarioBean usuario,
			List<MenuBean> operPermitidas, List<SubmenuBean> operFrecuentes,
			Map<String, Object> configuracion,
			List<NotificacionBean> notificaciones, List<String> opBusquedas);

	/**
	 * Elimina el contexto de la sesion, la sesion es invalidada despues de
	 * eliminar el contexto.
	 */
	void destruirContexto();
}