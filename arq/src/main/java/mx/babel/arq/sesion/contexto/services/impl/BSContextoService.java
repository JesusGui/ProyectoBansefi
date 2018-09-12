package mx.babel.arq.sesion.contexto.services.impl;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import mx.babel.arq.comun.utils.ProveedorMensajeSpringUtils;
import mx.babel.arq.sesion.contexto.beans.BSContexto;
import mx.babel.arq.sesion.contexto.beans.MenuBean;
import mx.babel.arq.sesion.contexto.beans.NotificacionBean;
import mx.babel.arq.sesion.contexto.beans.SubmenuBean;
import mx.babel.arq.sesion.contexto.beans.UsuarioBean;
import mx.babel.arq.sesion.contexto.services.IBSContextoService;

import org.apache.logging.log4j.ThreadContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * Clase encargada de gestionar el acceso al contexto.
 * 
 * @author joseluis.pena
 * 
 */
public class BSContextoService implements IBSContextoService {

	public static int numSesiones;

	/**
	 * Constructor.
	 */
	public BSContextoService() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mx.babel.arq.contexto.servicios.IBSContextoServicio#getNumSessions()
	 */
	@Override
	public int getNumSessions() {
		return numSesiones;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mx.babel.arq.contexto.servicios.IBSContextoServicio#getContexto()
	 */
	@Override
	public BSContexto getContexto() {
		try {
			return (BSContexto) getSession().getAttribute("bsctxt");
		} catch (Exception e) {
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * mx.babel.arq.contexto.servicios.IBSContextoServicio#construirContexto
	 * (mx.babel.arq.contexto.beans.UsuarioBean, java.util.List, java.util.List,
	 * java.util.Map, java.util.List)
	 */
	@Override
	public BSContexto construirContexto(UsuarioBean usuario,
			List<MenuBean> operPermitidas, List<SubmenuBean> operFrecuentes,
			Map<String, Object> configuracion,
			List<NotificacionBean> notificaciones, List<String> opBusquedas) {
		BSContexto bsContexto = new BSContexto();
		bsContexto.setUsuario(usuario);
		bsContexto.setOperPermitidas(operPermitidas);
		bsContexto.setOperFrecuentes(operFrecuentes);
		bsContexto.setConfiguracion(configuracion);
		bsContexto.setNotificaciones(notificaciones);
		bsContexto.setOpBusquedas(opBusquedas);
		bsContexto.setBanksystemVersion(ProveedorMensajeSpringUtils.getValorConfiguracion("banksystem.version"));
		getSession().setAttribute("bsctxt", bsContexto);
		ThreadContext.put("loginId", "["+bsContexto.getUsuario().getDireccionIp().replace(".","")+bsContexto.getUsuario().getId()+"]");
		
		return bsContexto;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * mx.babel.arq.contexto.servicios.IBSContextoServicio#destruirContexto()
	 */
	@Override
	public void destruirContexto() {
		getSession().removeAttribute("bsctxt");
		getSession().invalidate();
	}

	private HttpSession getSession() {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder
				.currentRequestAttributes();
		return attr.getRequest().getSession(false);
	}

}