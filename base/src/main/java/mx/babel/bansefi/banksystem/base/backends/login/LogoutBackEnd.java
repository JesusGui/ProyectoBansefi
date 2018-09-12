package mx.babel.bansefi.banksystem.base.backends.login;

import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.webservices.logout.EjecutarResult;
import mx.babel.bansefi.banksystem.base.webservices.logout.LogoutServicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Backend para el WS de desconexión de usuarios.
 * 
 * @author omar.marquez
 * 
 */
@Component
public class LogoutBackEnd extends BackEndBean {

	private static final long serialVersionUID = -1715432109281573838L;

	@Autowired
	ServicioWebUtils servicioWebUtils;

	/**
	 * Constructor.
	 */
	public LogoutBackEnd() {
		super();
	}

	/**
	 * Método que invoca al servicio de desconexión de usuarios.
	 * 
	 * @return código con el resultado de la operación (0 = OK, -1 ó 1 = NOK)
	 */
	public int ejecutarWS() {
		try {
			EjecutarResult resultado = (EjecutarResult) servicioWebUtils
					.ejecutarWS(LogoutServicio.class, super.getUsuarioId(),
							super.getEntidad(), super.getSucursal(),
							super.getIp());
			return Integer.parseInt(resultado.getESTATUS().trim());
		} catch (NumberFormatException | NoControlableException e) {
			return -1;
		}
	}
	
	/**
	 * Método que invoca al servicio de desconexión de usuarios desde el flujo de error
	 * 
	 * @return código con el resultado de la operación (0 = OK, -1 ó 1 = NOK)
	 */
	public int ejecutarWS(String usuarioId, String entidad, String sucursal, String ip) {
		try {
			servicioWebUtils = new ServicioWebUtils();
			EjecutarResult resultado = (EjecutarResult) servicioWebUtils
					.ejecutarWS(LogoutServicio.class, usuarioId,
							entidad, sucursal, ip);
			return Integer.parseInt(resultado.getESTATUS().trim());
		} catch (NumberFormatException | NoControlableException e) {
			return -1;
		}
	}

}