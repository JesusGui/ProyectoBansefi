package mx.babel.bansefi.banksystem.base.backends.login;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ProveedorMensajeSpringUtils;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.arq.sesion.contexto.beans.MenuBean;
import mx.babel.arq.sesion.contexto.beans.SubmenuBean;
import mx.babel.arq.sesion.contexto.beans.UsuarioBean;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.constants.ConstantesFuncionales;
import mx.babel.bansefi.banksystem.base.enums.BusquedaEnum;
import mx.babel.bansefi.banksystem.base.utils.ProveedorImagenesUtils;
import mx.babel.bansefi.banksystem.base.webservices.login.EjecutarResult;
import mx.babel.bansefi.banksystem.base.webservices.login.LoginServicio;
import mx.babel.bansefi.banksystem.base.webservices.login.ResponseBansefi;
import mx.babel.bansefi.banksystem.base.wrappers.LoginWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Backend para el servicio de inicio de sesión.
 * 
 * @author omar.marquez
 * 
 */
@Component
public class LoginBackEnd extends BackEndBean {

	private static final long serialVersionUID = 9130680404376780045L;

	private String direccionIp;

	@Autowired
	LoginWrapper loginWrapper;

	@Autowired
	ServicioWebUtils servicioWebUtils;

	/**
	 * Constructor.
	 */
	public LoginBackEnd() {
		super();
		direccionIp = null;
	}

	/**
	 * Método que invoca el servicio de conexión de usuarios.
	 * 
	 * @param usuario
	 * @param contrasena
	 * @param nuevaContrasena
	 * @param forzarInicioSesion
	 * @return resultado
	 */
	public EjecutarResult ejecutarWS(String usuario, String contrasena,
			String nuevaContrasena, String forzarInicioSesion)
			throws NullPointerException, ControlableException,
			NoControlableException {

		direccionIp = this.getIp();
		
		EjecutarResult resultado = null;
		if (nuevaContrasena != null && !nuevaContrasena.trim().isEmpty()) {
			resultado = (EjecutarResult) servicioWebUtils.ejecutarWS(
					LoginServicio.class, usuario, contrasena, nuevaContrasena,
					direccionIp, forzarInicioSesion);
		} else {
			resultado = (EjecutarResult) servicioWebUtils.ejecutarWS(
					LoginServicio.class, usuario, contrasena, null,
					direccionIp, forzarInicioSesion);
		}
		return resultado;
	}

	/**
	 * Implementación del método ejecutarWS exclusivo para iniciar sesión y
	 * forzar el inicio cuando exista una sesión abierta. Este método fue
	 * sobrecargado únicamente para distinguir entre una operación de cambio de
	 * contraseña y un inicio de sesión.
	 * 
	 * @param usuario
	 * @param contrasena
	 * @param forzarInicioSesion
	 * @return resultado
	 */
	public EjecutarResult ejecutarWS(String usuario, String contrasena,
			String forzarInicioSesion) {
		return ejecutarWS(usuario, contrasena, null, forzarInicioSesion);
	}

	/**
	 * Implementación del método ejecutarWS exclusivo para iniciar sesión y
	 * forzar el inicio cuando exista una sesión abierta. Este método fue
	 * sobrecargado únicamente para distinguir entre una operación de cambio de
	 * contraseña y un inicio de sesión.
	 * 
	 * @param usuario
	 * @param contrasena
	 * @param forzarInicioSesion
	 * @return resultado
	 */
	public EjecutarResult ejecutarWSCatalogo(String usuario, String contrasena,
			String ip, String forzarInicioSesion) {

		EjecutarResult resultado = (EjecutarResult) servicioWebUtils
				.ejecutarWS(LoginServicio.class, usuario, contrasena, null, ip,
						forzarInicioSesion);

		return resultado;
	}

	/**
	 * Método que devuelve un UsuarioBean que encapsula los datos que serán
	 * almacenados en el contexto.
	 * 
	 * @param resultado
	 * @return usuarioBean
	 */
	public UsuarioBean obtenerUsuarioBean(EjecutarResult resultado) {
		UsuarioBean usuarioBean = null;
		if (verificarResultado(resultado)
				&& resultado.getResponseBansefi().getResponseBansefi().size() > 0) {
			usuarioBean = new UsuarioBean();
			usuarioBean = loginWrapper.wrappLogin(resultado
					.getResponseBansefi().getResponseBansefi().get(0));
			usuarioBean.setPuesto(usuarioBean.getPuesto());
			usuarioBean.setDireccionIp(direccionIp);
		}
		return usuarioBean;
	}

	/**
	 * Método que devuelve una lista de operativas filtrada (sólo aplicaciones
	 * con código 1 > NSS).
	 * 
	 * @param resultado
	 * @return lista de operativas filtrada
	 */
	public List<String> obtenerOpcionesBusquedas(EjecutarResult resultado) {
		List<String> claves = new ArrayList<>();
		List<String> opcionesBusquedas = new ArrayList<>();
		if (verificarResultado(resultado)
				&& resultado.getResponseBansefi().getResponseBansefi().size() > 0) {
			List<ResponseBansefi> listOfResponseBansefi = resultado
					.getResponseBansefi().getResponseBansefi();
			for (ResponseBansefi responseBansefi : listOfResponseBansefi) {
				// Filtro 1: Operativas con código de aplicación 1
				// correspondientes al menú de búsquedas
				if (responseBansefi != null
						&& responseBansefi.getAPLICACION() != null
						&& !responseBansefi.getAPLICACION().trim().isEmpty()
						&& "1".equals(responseBansefi.getAPLICACION().trim())
						&& ConstantesFuncionales.COD_MENU_BUSQUEDA
								.equals(responseBansefi.getMENU().trim())
						&& responseBansefi.getSUBMENU() != null
						&& !responseBansefi.getSUBMENU().trim().isEmpty()) {
					String codBusqueda = responseBansefi.getSUBMENU().trim();
					if (!claves.contains(codBusqueda)) {
						claves.add(codBusqueda);
					}
				}

			}
		}
		for (int i = 0; i < claves.size(); i++) {
			for (int j = 0; j < BusquedaEnum.values().length; j++) {
				if (BusquedaEnum.values()[j].getBusquedaClave().equals(
						claves.get(i))) {
					opcionesBusquedas.add(BusquedaEnum.values()[j]
							.getBusquedaValor());
				}
			}
		}
		return opcionesBusquedas;
	}

	/**
	 * Método que devuelve una lista de operativas filtrada (sólo aplicaciones
	 * con código 1 > NSS).
	 * 
	 * @param resultado
	 * @return lista de operativas filtrada
	 */
	public List<MenuBean> obtenerOperativasPermitidas(EjecutarResult resultado) {
		Map<String, MenuBean> mapa = new HashMap<>();
		List<MenuBean> operativasPermitidas = null;
		if (verificarResultado(resultado)
				&& resultado.getResponseBansefi().getResponseBansefi().size() > 0) {
			List<ResponseBansefi> listOfResponseBansefi = resultado
					.getResponseBansefi().getResponseBansefi();
			for (ResponseBansefi responseBansefi : listOfResponseBansefi) {
				// Filtro 1: Operativas con código de aplicación 1 (NSS).
				if (responseBansefi != null
						&& responseBansefi.getAPLICACION() != null
						&& !responseBansefi.getAPLICACION().trim().isEmpty()
						&& "1".equals(responseBansefi.getAPLICACION().trim())) {
					MenuBean menu = crearMenuBean(responseBansefi);
					if (menu != null && !mapa.containsKey(menu.getCodigo())) {
						mapa.put(menu.getCodigo(), menu);
					}
					if (responseBansefi.getSUBMENU() != null
							&& !responseBansefi.getSUBMENU().trim().isEmpty()
							&& responseBansefi.getVINCULASUBMENU() != null
							&& !responseBansefi.getVINCULASUBMENU().trim()
									.isEmpty()) {
						SubmenuBean submenu = crearSubmenuBean(responseBansefi);
						if (submenu != null
								&& !ConstantesFuncionales.COD_MENU_BUSQUEDA
										.equals(submenu.getIdMenu())) {
							mapa.get(menu.getCodigo()).getSubmenus()
									.add(submenu);
						} else if (submenu != null
								&& ConstantesFuncionales.COD_MENU_BUSQUEDA
										.equals(submenu.getIdMenu())
								&& mapa.get(menu.getCodigo()).getSubmenus()
										.isEmpty()) {
							submenu.setNombre(ConstantesFuncionales.COD_MENU_BUSQUEDA_TEXTO);
							mapa.get(menu.getCodigo()).getSubmenus()
									.add(submenu);
						}
					}
				}
			}
			operativasPermitidas = new ArrayList<>(mapa.values());
			// Ordenamos los elementos de manera ascendente por código de menú.
			Collections.sort(operativasPermitidas, new Comparator<MenuBean>() {
				public int compare(MenuBean obj1, MenuBean obj2) {
					Integer key1 = Integer.parseInt(obj1.getCodigo());
					Integer key2 = Integer.parseInt(obj2.getCodigo());
					return key1.compareTo(key2);
				}
			});
		}
		return operativasPermitidas;
	}

	/**
	 * Método que devuelve una lista de operativas frecuentes (sólo submenus
	 * cuyo indicador de frecuencia sea verdadero).
	 * 
	 * @return lista de operativas frecuentes
	 */
	public List<SubmenuBean> obtenerOperativasFrecuentes(
			List<MenuBean> operativasPermitidas) {
		List<SubmenuBean> operativasFrecuentes = null;
		if (operativasPermitidas != null && operativasPermitidas.size() > 0) {
			operativasFrecuentes = new ArrayList<>();
			for (MenuBean menu : operativasPermitidas) {
				if (menu != null && menu.getSubmenus() != null
						&& menu.getSubmenus().size() > 0) {
					for (SubmenuBean submenu : menu.getSubmenus()) {
						if (submenu != null && submenu.getVinculoURL() != null
								&& !submenu.getVinculoURL().trim().isEmpty()
								&& submenu.isOperativaFrecuente()) {
							operativasFrecuentes.add(submenu);
						}
					}
				}
			}
		}
		return operativasFrecuentes;
	}

	/**
	 * Método privado que verifica que el resultado obtenido no contenga valores
	 * nulos.
	 * 
	 * @param resultado
	 * @return indicador booleano
	 */
	private boolean verificarResultado(EjecutarResult resultado) {
		if (resultado != null && resultado.getResponseBansefi() != null
				&& resultado.getResponseBansefi().getResponseBansefi() != null) {
			return true;
		}
		return false;
	}

	/**
	 * Método privado que devuelve un MenuBean.
	 * 
	 * @param responseBansefi
	 * @return menu
	 */
	private MenuBean crearMenuBean(ResponseBansefi responseBansefi) {
		MenuBean menu = null;
		if (responseBansefi != null) {
			menu = new MenuBean();
			menu.setCodigo(responseBansefi.getMENU().trim());
			menu.setNombre(responseBansefi.getNOMBREMENU().trim());
			menu.setVinculoURL(responseBansefi.getVINCULAMENU().trim());
		}
		return menu;
	}

	/**
	 * Método privado que devuelve un SubMenuBean.
	 * 
	 * @param responseBansefi
	 * @return submenu
	 */
	private SubmenuBean crearSubmenuBean(ResponseBansefi responseBansefi) {
		SubmenuBean submenu = null;
		if (responseBansefi != null) {
			submenu = new SubmenuBean();
			submenu.setIdMenu(responseBansefi.getMENU().trim());
			submenu.setCodigo(responseBansefi.getSUBMENU().trim());
			submenu.setNombre(responseBansefi.getNOMBRESUBMENU().trim());
			submenu.setVinculoURL(responseBansefi.getVINCULASUBMENU().trim());
			submenu.setImagenURL(ProveedorImagenesUtils
					.obtenerUrlImagen(Integer.parseInt(responseBansefi
							.getSUBMENU().trim())));
			boolean isOperativaFrecuente = false;
			if (responseBansefi.getFRECUENTE().intValue() == 1) {
				isOperativaFrecuente = true;
			}
			submenu.setOperativaFrecuente(isOperativaFrecuente);
		}
		return submenu;
	}


}