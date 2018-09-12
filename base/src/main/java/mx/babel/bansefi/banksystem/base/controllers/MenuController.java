package mx.babel.bansefi.banksystem.base.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.el.ExpressionFactory;
import javax.el.MethodExpression;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.html.HtmlPanelGrid;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import mx.babel.arq.sesion.contexto.beans.MenuBean;
import mx.babel.arq.sesion.contexto.beans.SubmenuBean;
import mx.babel.arq.sesion.contexto.services.impl.BSContextoService;
import mx.babel.arq.sesion.utils.ContextoUtils;
import mx.babel.bansefi.banksystem.base.backends.login.LogoutBackEnd;
import mx.babel.bansefi.banksystem.base.enums.ImagenEnum;
import mx.babel.bansefi.banksystem.base.enums.NavegacionEnum;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.component.commandlink.CommandLink;
import org.primefaces.component.dashboard.Dashboard;
import org.primefaces.component.graphicimage.GraphicImage;
import org.primefaces.component.outputlabel.OutputLabel;
import org.primefaces.component.panel.Panel;
import org.primefaces.context.RequestContext;
import org.primefaces.model.DashboardModel;
import org.primefaces.model.DefaultDashboardColumn;
import org.primefaces.model.DefaultDashboardModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * Controlador que se encarga de generar el encabezado, el dashboard y el menú
 * de manera dinámica.
 * 
 * @author omar.marquez
 * 
 */
@ManagedBean
@ViewScoped
@Component
@Scope("view")
public class MenuController implements Serializable {

	private static final long serialVersionUID = 819347155642502438L;

	private Dashboard tablero;
	private List<MenuBean> menus;
	private int codigoRetorno;
	private String recursoXHTML;
	private String idVistaActual;
	private String urlVistaDestino;

	@Autowired
	ContextoUtils contextoUtils;

	@Autowired
	BSContextoService bsContextoService;

	@Autowired
	LogoutBackEnd logoutBackEnd;

	/**
	 * Constructor.
	 */
	public MenuController() {
		tablero = new Dashboard();
		menus = new ArrayList<>();
		codigoRetorno = -1;
		recursoXHTML = new String();
		idVistaActual = obtenerIdVistaActual();
		urlVistaDestino = obtenerUrlVistaDestino();
	}

	@PostConstruct
	public void init() {
		// Sí el usuario se autentica, aumentamos el time-out a 1h.
		final int sessionTimeOut = Integer.parseInt(FacesContext
				.getCurrentInstance().getExternalContext()
				.getInitParameter("sessionTimeOut"));
		((ServletRequestAttributes) RequestContextHolder
				.currentRequestAttributes()).getRequest().getSession()
				.setMaxInactiveInterval(sessionTimeOut * 60);
		if (verificarOperacionesPermitidas()) {
			crearMenu();
			String arCadenas[] = null;
			arCadenas = StringUtils.split(NavegacionEnum.INICIO.getRuta(), '?');
			if (verificarOperacionesFrecuentes()
					&& urlVistaDestino.contains(arCadenas[0])) {
				crearTablero();
			} else if (urlVistaDestino.contains(arCadenas[0])) {
				cargarSubmenuPredeterminado();
			}
		} else {
			cargarMenuPredeterminado();
			cargarSubmenuPredeterminado();
		}
	}

	/**
	 * Método que devuelve un objeto tipo Dashboard.
	 * 
	 * @return tablero con operativas frecuentes
	 */
	public Dashboard getTablero() {
		return tablero;
	}

	/**
	 * Método que establece un objeto tipo Dashboard.
	 * 
	 * @param tablero
	 */
	public void setTablero(Dashboard tablero) {
		this.tablero = tablero;
	}

	/**
	 * Método que devuelve una lista con los elementos del menú.
	 * 
	 * @return menus
	 */
	public List<MenuBean> getMenus() {
		return menus;
	}

	/**
	 * Método que establece una lista con los elementos del menú.
	 * 
	 * @param menus
	 */
	public void setMenus(List<MenuBean> menus) {
		this.menus = menus;
	}

	/**
	 * Método que obtiene el código de retorno devuelto por el servicio de
	 * desconexión de usuarios.
	 * 
	 * @return codigoRetorno
	 */
	public int getCodigoRetorno() {
		return codigoRetorno;
	}

	/**
	 * Método que establece el valor del código de retorno.
	 * 
	 * @param codigoRetorno
	 */
	public void setCodigoRetorno(int codigoRetorno) {
		this.codigoRetorno = codigoRetorno;
	}

	/**
	 * Método que devuelve el nombre de una vista o recurso XHTML.
	 * 
	 * @return recursoXHTML
	 */
	public String getRecursoXHTML() {
		return recursoXHTML;
	}

	/**
	 * Método que establece el nombre de una vista o recurso XHTML.
	 * 
	 * @param recursoXHTML
	 */
	public void setRecursoXHTML(String recursoXHTML) {
		this.recursoXHTML = recursoXHTML;
	}

	/**
	 * Método que genera un menú con las operativas permitidas que se encuentran
	 * cargadas en el contexto.
	 */
	public void crearMenu() {
		menus = contextoUtils.getOperPermitidas();
	}

	/**
	 * Método que genera un tablero dinámico con las operativas frecuentes.
	 */
	public void crearTablero() {
		DashboardModel modelo = new DefaultDashboardModel();

		List<SubmenuBean> operativasFrecuentes = contextoUtils
				.getOperFrecuentes();
		for (int i = 0; i < operativasFrecuentes.size(); i++) {
			SubmenuBean elemento = new SubmenuBean(operativasFrecuentes.get(i)
					.getCodigo(), operativasFrecuentes.get(i).getNombre(),
					operativasFrecuentes.get(i).getVinculoURL(),
					operativasFrecuentes.get(i).getImagenURL());

			GraphicImage imagen = new GraphicImage();
			imagen.setId("imagen" + i);
			imagen.setUrl(elemento.getImagenURL());

			OutputLabel etiqueta = new OutputLabel();
			etiqueta.setId("etiqueta" + i);
			etiqueta.setValue(elemento.getNombre());

			HtmlPanelGrid tabla = new HtmlPanelGrid();
			tabla.setId("cuadro" + i);
			tabla.getChildren().add(imagen);
			tabla.getChildren().add(etiqueta);

			CommandLink vinculo = new CommandLink();
			vinculo.setId("vinculo" + i);
			vinculo.setActionExpression(this.obtainActionExpression(elemento
					.getVinculoURL()));
			vinculo.getChildren().add(tabla);

			Panel panel = new Panel();
			panel.setId("panel" + i);
			panel.setWidgetVar("panel" + i);
			panel.getChildren().add(vinculo);

			DefaultDashboardColumn columna = new DefaultDashboardColumn();
			columna.addWidget(panel.getId());
			modelo.addColumn(columna);

			tablero.getChildren().add(panel);
		}
		tablero.setModel(modelo);
	}

	/**
	 * Método que ejecuta la transacción de cierre de sesión y sólo sí el
	 * resultado de la misma es 1 (OK) redirige al usuario al login.
	 * 
	 * @return ruta del recurso a mostrar
	 */
	public String cerrarSesion() {
		String vistaDestino = "#";
		if (logoutBackEnd.ejecutarWS() == 0) {
			// OK > Destruye el contexto y cierra la sesión Web.
			bsContextoService.destruirContexto();
			vistaDestino = NavegacionEnum.LOGIN.getRuta();
		} else {
			// NOK > Notifica al usuario que hubo un error.
			RequestContext.getCurrentInstance()
					.execute("PF('dlgError').show()");
		}
		return vistaDestino;
	}

	/**
	 * Método que recibe una expresión tipo #{controlador.inicio()} y lo
	 * convierte en una ruta de navegación válida.
	 * 
	 * @param actionExpression
	 * @return resource
	 */
	public String convertActionExpressionToString(String actionExpression) {
		String resource = new String();
		resource = (String) FacesContext
				.getCurrentInstance()
				.getApplication()
				.getExpressionFactory()
				.createValueExpression(
						FacesContext.getCurrentInstance().getELContext(),
						actionExpression, String.class)
				.getValue(FacesContext.getCurrentInstance().getELContext());
		return resource;
	}

	/**
	 * Método que actualiza la tabla de notificaciones cuando el componente
	 * 'notificaciones.xhtml' se encuentra activo. Este método devuelve un
	 * indicador booleano para determinar sí se debe actualizar la tabla de
	 * notificaciones o no.
	 * 
	 * @return indicador booleano
	 */
	public boolean actualizarTablaNotificaciones() {
		String arCadenas[] = null;
		arCadenas = StringUtils.split(NavegacionEnum.NOTIFICACIONES.getRuta(),
				'?');
		if (idVistaActual.contains(arCadenas[0])) {
			return true;
		}
		return false;
	}

	/**
	 * Método que redirige al usuario a la página de inicio que contiene el
	 * dashboard.
	 * 
	 * @return ruta del recurso a mostrar
	 */
	public String redirigirAInicio() {
		return NavegacionEnum.INICIO.getRuta();
	}

	/**
	 * Método que redirige al usuario a la página de tareas pendientes.
	 * 
	 * @return ruta del recurso a mostrar
	 */
	public String redirigirATareas() {
		return NavegacionEnum.TAREAS_PENDIENTES.getRuta();
	}

	/**
	 * Método que redirige al usuario a la página de notificaciones.
	 * 
	 * @return ruta del recurso a mostrar
	 */
	public String redirigirANotificaciones() {
		return NavegacionEnum.NOTIFICACIONES.getRuta();
	}

	/**
	 * Método que redirige al usuario a la página de cambio de contraseña 2.
	 * 
	 * @return ruta del recurso a mostrar
	 */
	public String redirigirACambioContrasena() {
		return NavegacionEnum.CAMBIAR_CONTRASENA_2.getRuta();
	}

	/**
	 * Método privado que devuelve el ID de la vista actual.
	 * 
	 * @return idVistaActual
	 */
	private String obtenerIdVistaActual() {
		return FacesContext.getCurrentInstance().getViewRoot().getViewId();
	}

	/**
	 * Método privado que devuelve la URL de la vista destino.
	 * 
	 * @return urlVistaDestino
	 */
	private String obtenerUrlVistaDestino() {
		HttpServletRequest request = (HttpServletRequest) FacesContext
				.getCurrentInstance().getExternalContext().getRequest();
		return request.getRequestURL().toString();
	}

	/**
	 * Método privado que se encarga de generar un elemento predeterminado para
	 * el menú.
	 */
	private void cargarMenuPredeterminado() {
		MenuBean menu = new MenuBean();
		menu.setCodigo("1");
		menu.setNombre("Búsqueda");
		menu.setVinculoURL("#{busquedaController.inicio()}");
		// menu.setImagenURL(ImagenEnum.PREDETERMINADA.getUrlImagen());
		menus.add(menu);
	}

	/**
	 * Método privado que se encarga de generar un elemento predeterminado para
	 * el dashboard.
	 */
	private void cargarSubmenuPredeterminado() {
		DashboardModel modelo = new DefaultDashboardModel();
		SubmenuBean submenu = new SubmenuBean("1", "Búsqueda",
				"#{busquedaController.inicio()}",
				ImagenEnum.PREDETERMINADA.getUrlImagen());
		GraphicImage imagen = new GraphicImage();
		imagen.setId("imagen0");
		imagen.setUrl(submenu.getImagenURL());
		OutputLabel etiqueta = new OutputLabel();
		etiqueta.setId("etiqueta0");
		etiqueta.setValue(submenu.getNombre());
		HtmlPanelGrid tabla = new HtmlPanelGrid();
		tabla.setId("cuadro0");
		tabla.getChildren().add(imagen);
		tabla.getChildren().add(etiqueta);
		CommandLink vinculo = new CommandLink();
		vinculo.setId("vinculo0");
		vinculo.setActionExpression(this.obtainActionExpression(submenu
				.getVinculoURL()));
		vinculo.getChildren().add(tabla);
		Panel panel = new Panel();
		panel.setId("panel0");
		panel.setWidgetVar("panel0");
		panel.getChildren().add(vinculo);
		DefaultDashboardColumn columna = new DefaultDashboardColumn();
		columna.addWidget(panel.getId());
		modelo.addColumn(columna);
		tablero.getChildren().add(panel);
	}

	/**
	 * Método privado que devuelve la URL de un recurso XHTML a través de la
	 * definición de un MethodExpression. Este método permite redireccionar a
	 * los usuarios a las diferentes vistas.
	 * 
	 * @author joseluis.pena
	 * @return methodExpression
	 */
	private MethodExpression obtainActionExpression(String resource) {
		FacesContext context = FacesContext.getCurrentInstance();
		ExpressionFactory factory = context.getApplication()
				.getExpressionFactory();
		MethodExpression methodExpression = null;
		if (resource != null && !resource.trim().isEmpty()) {
			methodExpression = factory.createMethodExpression(FacesContext
					.getCurrentInstance().getELContext(), resource, List.class,
					new Class[] { String.class });
		}
		return methodExpression;
	}

	/**
	 * Método privado que devuelve un indicador booleano para determinar si la
	 * lista de operaciones permitidas se encuentra cargada en el contexto.
	 * 
	 * @return indicador booleano
	 */
	private boolean verificarOperacionesPermitidas() {
		if (contextoUtils != null && contextoUtils.existeContexto()
				&& contextoUtils.getOperPermitidas() != null
				&& contextoUtils.getOperPermitidas().size() > 0) {
			return true;
		}
		return false;
	}

	/**
	 * Método privado que devuelve un indicador booleano para determinar si la
	 * lista de operaciones frecuentes se encuentra cargada en el contexto.
	 * 
	 * @return indicador booleano
	 */
	private boolean verificarOperacionesFrecuentes() {
		if (contextoUtils != null && contextoUtils.existeContexto()
				&& contextoUtils.getOperFrecuentes() != null
				&& contextoUtils.getOperFrecuentes().size() > 0) {
			return true;
		}
		return false;
	}

}