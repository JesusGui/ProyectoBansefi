package mx.babel.bansefi.banksystem.cuentas.controllers;

import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.model.SelectItem;

import mx.babel.arq.catalogo.beans.CatalogoBean;
import mx.babel.arq.comun.beans.PaginacionBean;
import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.sesion.utils.ContextoUtils;
import mx.babel.bansefi.banksystem.base.beans.domain.CuentaBean;
import mx.babel.bansefi.banksystem.base.beans.movimientos.MovimientoBean;
import mx.babel.bansefi.banksystem.base.constants.ConstantesFuncionales;
import mx.babel.bansefi.banksystem.base.enums.CatalogoEnum;
import mx.babel.bansefi.banksystem.base.enums.NavegacionEnum;
import mx.babel.bansefi.banksystem.base.enums.ParametrosFlashEnum;
import mx.babel.bansefi.banksystem.base.utils.CatalogoUtils;
import mx.babel.bansefi.banksystem.base.utils.ManagedBeanStateRecover;
import mx.babel.bansefi.banksystem.base.utils.SelectItemStringValueComparator;
import mx.babel.bansefi.banksystem.cuentas.backends.ConsultaGeneralSaldosBackend;
import mx.babel.bansefi.banksystem.cuentas.backends.ConsultaSaldosFechaBackend;
import mx.babel.bansefi.banksystem.cuentas.beans.SaldoCuentaBean;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@ManagedBean
@ViewScoped
@Component
@Scope("view")
public class SaldosCuentaController implements Serializable {

	private static final long serialVersionUID = 6379643907123478990L;
	private static final Logger LOGGER = LogManager
			.getLogger(SaldosCuentaController.class);
	// Constante para no paginar sí es D1 - FIN DIA.
	private static final String TIPO_SALDO_SIN_PAGINACION = "D1";

	private CuentaBean cuentaBean;
	private List<SaldoCuentaBean> listaSaldos;
	private List<SaldoCuentaBean> listaSaldosFecha;
	private SaldoCuentaBean saldoSeleccionado;
	private CatalogoBean catalogoBean;
	private Map<String, String> mapaNaturalezas;
	private List<SelectItem> naturalezas;
	private Date fechaDesdeMinima;
	private boolean panelResultadosDisponible;

	// Variables para el flujo y retorno de datos.
	private NavegacionEnum navegacionEnumOrigen;
	private Object controladorOrigen;

	// Variables para el control de los filtros de la búsqueda por fecha.
	private String naturalezaSeleccionada;
	private String tipoSaldoSeleccionado;
	private Date filtroFechaDesde;
	private Date filtroFechaHasta;

	// Variables para controlar la paginación.
	private boolean masDatos;
	private int pagina;

	@Autowired
	ContextoUtils contextoUtils;

	@Autowired
	CatalogoUtils catalogoUtils;

	@Autowired
	ManagedBeanStateRecover managedBeanStateRecover;

	@Autowired
	ConsultaGeneralSaldosBackend consultaGeneralSaldosBackend;

	@Autowired
	ConsultaSaldosFechaBackend consultaSaldosFechaBackend;

	/**
	 * Constructor.
	 */
	public SaldosCuentaController() {
		super();
		this.cuentaBean = null;
		this.listaSaldos = null;
		this.listaSaldosFecha = null;
		this.catalogoBean = new CatalogoBean();
		this.mapaNaturalezas = null;
		this.naturalezas = new ArrayList<>();
		this.fechaDesdeMinima = this.obtenerFechaDesdeMinima();
		this.panelResultadosDisponible = false;
		this.naturalezaSeleccionada = null;
		this.tipoSaldoSeleccionado = null;
		this.filtroFechaDesde = null;
		this.filtroFechaHasta = null;
		this.masDatos = false;
		this.pagina = 0;
	}

	@PostConstruct
	public void init() {
		if (this.obtenerFlash().get(
				ParametrosFlashEnum.NAVEGACION_MANAGED_BEAN.getParamFlash()) != null) {
			if ((Boolean) this.obtenerFlash()
					.get(ParametrosFlashEnum.NAVEGACION_MANAGED_BEAN
							.getParamFlash())) {
				this.navegacionEnumOrigen = this.managedBeanStateRecover
						.getDestino();
				this.controladorOrigen = this.managedBeanStateRecover
						.getController();
				this.initData();
			} else {
				this.managedBeanStateRecover.recuperaController(this);
				this.consultarSaldos();
			}
		} else {
			this.initData();
		}
	}

	// INICIA DECLARACIÓN DE GETTERS Y SETTERS.

	/**
	 * Método que devuelve un objeto tipo CuentaBean.
	 * 
	 * @return cuentaBean
	 */
	public CuentaBean getCuentaBean() {
		return cuentaBean;
	}

	/**
	 * Método que establece un objeto tipo CuentaBean.
	 * 
	 * @param cuentaBean
	 */
	public void setCuentaBean(CuentaBean cuentaBean) {
		this.cuentaBean = cuentaBean;
	}

	/**
	 * Método que devuelve una lista de saldos.
	 * 
	 * @return listaSaldos
	 */
	public List<SaldoCuentaBean> getListaSaldos() {
		return listaSaldos;
	}

	/**
	 * Método que establece una lista de saldos.
	 * 
	 * @param listaSaldos
	 */
	public void setListaSaldos(List<SaldoCuentaBean> listaSaldos) {
		this.listaSaldos = listaSaldos;
	}

	/**
	 * Método que devuelve una lista de saldos a partir de la búsqueda por
	 * fecha.
	 * 
	 * @return listaSaldosFecha
	 */
	public List<SaldoCuentaBean> getListaSaldosFecha() {
		return listaSaldosFecha;
	}

	/**
	 * Método que establece una lista de saldos a partir de la búsqueda por
	 * fecha.
	 * 
	 * @param listaSaldosFecha
	 */
	public void setListaSaldosFecha(List<SaldoCuentaBean> listaSaldosFecha) {
		this.listaSaldosFecha = listaSaldosFecha;
	}

	public SaldoCuentaBean getSaldoSeleccionado() {
		return saldoSeleccionado;
	}

	public void setSaldoSeleccionado(SaldoCuentaBean saldoSeleccionado) {
		this.saldoSeleccionado = saldoSeleccionado;
	}

	/**
	 * Método que devuelve un objeto tipo CatalogoBean.
	 * 
	 * @return catalogoBean
	 */
	public CatalogoBean getCatalogoBean() {
		return catalogoBean;
	}

	/**
	 * Método que establece un objeto tipo CatalogoBean.
	 * 
	 * @param catalogoBean
	 */
	public void setCatalogoBean(CatalogoBean catalogoBean) {
		this.catalogoBean = catalogoBean;
	}

	/**
	 * Método que devuelve un mapa con las diferentes naturalezas que puede
	 * tener una cuenta.
	 * 
	 * @return mapaNaturalezas
	 */
	public Map<String, String> getMapaNaturalezas() {
		return mapaNaturalezas;
	}

	/**
	 * Método que establece un mapa con las diferentes naturalezas que puede
	 * tener una cuenta.
	 * 
	 * @param mapaNaturalezas
	 */
	public void setMapaNaturalezas(Map<String, String> mapaNaturalezas) {
		this.mapaNaturalezas = mapaNaturalezas;
	}

	/**
	 * Método que devuelve una lista de elementos para cargar la lista
	 * desplegable de naturalezas que se encuentra en saldosFecha.xhtml
	 * 
	 * @return lista de naturalezas
	 */
	public List<SelectItem> getNaturalezas() {
		return naturalezas;
	}

	/**
	 * Método que establece una lista de elementos para cargar la lista
	 * desplegable de naturalezas.
	 * 
	 * @param naturalezas
	 */
	public void setNaturalezas(List<SelectItem> naturalezas) {
		this.naturalezas = naturalezas;
	}

	/**
	 * Método que devuelve la fecha desde mínima permitida por la aplicación.
	 * 
	 * @return fechaDesdeMinima
	 */
	public Date getFechaDesdeMinima() {
		return fechaDesdeMinima;
	}

	/**
	 * Método que establece la fecha desde mínima permitida por la aplicación.
	 * 
	 * @param fechaDesdeMinima
	 */
	public void setFechaDesdeMinima(Date fechaDesdeMinima) {
		this.fechaDesdeMinima = fechaDesdeMinima;
	}

	/**
	 * Método que devuelve un indicador para determinar sí se debe mostrar el
	 * panel de resultados o no.
	 * 
	 * @return indicador booleano
	 */
	public boolean isPanelResultadosDisponible() {
		return panelResultadosDisponible;
	}

	/**
	 * Método que establece el valor del indicador para mostrar u ocultar el
	 * panel de resultados.
	 * 
	 * @param panelResultadosDisponible
	 */
	public void setPanelResultadosDisponible(boolean panelResultadosDisponible) {
		this.panelResultadosDisponible = panelResultadosDisponible;
	}

	/**
	 * Método que devuelve un NavegacionEnum para volver al origen.
	 * 
	 * @return navegacionEnumOrigen
	 */
	public NavegacionEnum getNavegacionEnumOrigen() {
		return navegacionEnumOrigen;
	}

	/**
	 * Método que establece un NavegacionEnum para volver al origen.
	 * 
	 * @param navegacionEnumOrigen
	 */
	public void setNavegacionEnumOrigen(NavegacionEnum navegacionEnumOrigen) {
		this.navegacionEnumOrigen = navegacionEnumOrigen;
	}

	/**
	 * Método que devuelve un objeto con el controlador de origen.
	 * 
	 * @return controladorOrigen
	 */
	public Object getControladorOrigen() {
		return controladorOrigen;
	}

	/**
	 * Método que establece un objeto con el controlador de origen.
	 * 
	 * @param controladorOrigen
	 */
	public void setControladorOrigen(Object controladorOrigen) {
		this.controladorOrigen = controladorOrigen;
	}

	/**
	 * Método que devuelve la clave de la naturaleza seleccionada por el
	 * usuario.
	 * 
	 * @return naturalezaSeleccionada
	 */
	public String getNaturalezaSeleccionada() {
		return naturalezaSeleccionada;
	}

	/**
	 * Método que establece la clave de la naturaleza seleccionada por el
	 * usuario.
	 * 
	 * @param naturalezaSeleccionada
	 */
	public void setNaturalezaSeleccionada(String naturalezaSeleccionada) {
		this.naturalezaSeleccionada = naturalezaSeleccionada;
	}

	/**
	 * Método que devuelve la clave del tipo de saldo seleccionado por el
	 * usuario.
	 * 
	 * @return tipoSaldoSeleccionado
	 */
	public String getTipoSaldoSeleccionado() {
		return tipoSaldoSeleccionado;
	}

	/**
	 * Método que establece la clave del tipo de saldo seleccionado por el
	 * usuario.
	 * 
	 * @param tipoSaldoSeleccionado
	 */
	public void setTipoSaldoSeleccionado(String tipoSaldoSeleccionado) {
		this.tipoSaldoSeleccionado = tipoSaldoSeleccionado;
	}

	/**
	 * Método que devuelve la fecha de inicio para la búsqueda de saldos por
	 * fecha.
	 * 
	 * @return filtroFechaDesde
	 */
	public Date getFiltroFechaDesde() {
		return filtroFechaDesde;
	}

	/**
	 * Método que establece la fecha de inicio para la búsqueda de saldos por
	 * fecha.
	 * 
	 * @param filtroFechaDesde
	 */
	public void setFiltroFechaDesde(Date filtroFechaDesde) {
		this.filtroFechaDesde = filtroFechaDesde;
	}

	/**
	 * Método que devuelve la fecha de término para la búsqueda de saldos por
	 * fecha.
	 * 
	 * @return filtroFechaHasta
	 */
	public Date getFiltroFechaHasta() {
		return filtroFechaHasta;
	}

	/**
	 * Método que establece la fecha de término para la búsqueda de saldos por
	 * fecha.
	 * 
	 * @param filtroFechaHasta
	 */
	public void setFiltroFechaHasta(Date filtroFechaHasta) {
		this.filtroFechaHasta = filtroFechaHasta;
	}

	/**
	 * Método que devuelve un indicador booleano para determinar sí existen más
	 * datos.
	 * 
	 * @return indicador booleano.
	 */
	public boolean isMasDatos() {
		return masDatos;
	}

	/**
	 * Método que establece el valor del indicador para determinar sí existen
	 * más datos.
	 * 
	 * @param masDatos
	 */
	public void setMasDatos(boolean masDatos) {
		this.masDatos = masDatos;
	}

	/**
	 * Método que devuelve el número de elementos a mostrar por página.
	 * 
	 * @return pagina
	 */
	public int getPagina() {
		return pagina;
	}

	/**
	 * Método que establece el número de elementos a mostrar por página.
	 * 
	 * @param pagina
	 */
	public void setPagina(int pagina) {
		this.pagina = pagina;
	}

	// INICIA DECLARACIÓN DE MÉTODOS PRINCIPALES.

	/**
	 * Método privado que inicializa la carga de datos.
	 */
	private void initData() {
		this.loadParametresFromFlash();
		if (contextoUtils != null
				&& contextoUtils.getFechaContableActual() != null) {
			this.filtroFechaDesde = contextoUtils.getFechaContableActual();
			this.filtroFechaHasta = contextoUtils.getFechaContableActual();
		}
		if (this.cuentaBean != null && this.mapaNaturalezas != null
				&& this.listaSaldos != null) {
			// Usuario pretende navegar a la consulta de saldos por fecha.
			LOGGER.debug("Usuario pretende navegar a la consulta de saldos por fecha.");
			this.construirListaNaturalezas();
		} else if (this.cuentaBean != null && this.mapaNaturalezas == null
				&& this.listaSaldos == null) {
			// Usuario pretende navegar a la consulta general de saldos.
			LOGGER.debug("Usuario pretende navegar a la consulta general de saldos.");
			this.consultarSaldos();
		} else {
			throw new NoControlableException(
					"Error al intentar recuperar la información de la cuenta / saldo.",
					this.getClass().getName()
							+ ": Se requieren más datos para efectuar la consulta.");
		}
	}

	/**
	 * Método privado que carga los parámetros almacenados en la Flash.
	 */
	@SuppressWarnings("unchecked")
	private void loadParametresFromFlash() {
		if (obtenerFlash().get(ParametrosFlashEnum.CUENTA_BEAN.getParamFlash()) != null) {
			this.cuentaBean = (CuentaBean) this.obtenerFlash().get(
					ParametrosFlashEnum.CUENTA_BEAN.getParamFlash());
		}
		if (obtenerFlash().get(
				ParametrosFlashEnum.MAPA_NATURALEZAS_CUENTA.getParamFlash()) != null) {
			this.mapaNaturalezas = (HashMap<String, String>) this
					.obtenerFlash().get(
							ParametrosFlashEnum.MAPA_NATURALEZAS_CUENTA
									.getParamFlash());
		}
		if (obtenerFlash()
				.get(ParametrosFlashEnum.LISTA_SALDOS.getParamFlash()) != null) {
			this.listaSaldos = (ArrayList<SaldoCuentaBean>) this.obtenerFlash()
					.get(ParametrosFlashEnum.LISTA_SALDOS.getParamFlash());
		}
	}

	/**
	 * Método privado que a partir del mapa de naturalezas construye una lista
	 * de forma dinámica.
	 */
	private void construirListaNaturalezas() {
		final Iterator<Entry<String, String>> iterador = mapaNaturalezas
				.entrySet().iterator();
		Entry<String, String> entry = null;
		while (iterador.hasNext()) {
			entry = iterador.next();
			naturalezas.add(new SelectItem(entry.getKey(), entry.getValue()));
		}
		Collections.sort(naturalezas, new SelectItemStringValueComparator());
	}

	/**
	 * Método privado que ejecuta la TRN de consulta general de saldos.
	 */
	private void consultarSaldos() {
		try {
			listaSaldos = new ArrayList<SaldoCuentaBean>();
			listaSaldos.addAll(consultaGeneralSaldosBackend
					.ejecutarTRN(cuentaBean));
			construirMapa();
		} catch (ControlableException e) {
			// TODO Implementar tratamiento.
			// LOGGER.error("Error al ejecutar la TRN de consulta general de saldos."
			// + this.getClass().getName() + ":" + e.getMessage());
		} catch (NullPointerException | NoControlableException e) {
			throw new NoControlableException(
					"Error al ejecutar la TRN de consulta general de saldos.",
					this.getClass().getName() + ":" + e.getMessage());
		}
	}

	/**
	 * Método privado que construye un mapa a partir de la consulta general de
	 * saldos. Este mapa es utilizado para construir la lista de naturalezas.
	 */
	private void construirMapa() {
		mapaNaturalezas = new HashMap<String, String>();
		for (SaldoCuentaBean beanDatos : listaSaldos) {
			String key = beanDatos.getCodigoCuenta().concat(
					beanDatos.getCodigoSaldo());
			String value = obtenerDescNaturaleza(beanDatos.getCodigoCuenta(),
					beanDatos.getCodigoSaldo());
			if (key != null && !key.isEmpty() && value != null
					&& !value.isEmpty()) {
				mapaNaturalezas.put(key, value);
			}
		}
	}

	/**
	 * Método que devuelve la descripción asociada al código de naturaleza de la
	 * cuenta.
	 * 
	 * @param codigoCuenta
	 * @param codigoSaldo
	 * @return descripción larga de la naturaleza del saldo
	 */
	public String obtenerDescNaturaleza(String codigoCuenta, String codigoSaldo) {
		// Buscamos la descripción de la naturaleza a través del codigoCuenta,
		// codigoSaldo, codigoLinea y idGrupoProducto
		try {
			return catalogoUtils.getCatalogoDesc(CatalogoEnum.TP_NOMB_SDO,
					codigoCuenta + codigoSaldo + cuentaBean.getCodLinea()
							+ cuentaBean.getIdGrupoProducto());
		} catch (ControlableException | NullPointerException e) {
			LOGGER.error("Error al obtener la descripción de la naturaleza utilizando la claveFila: "
					+ codigoCuenta
					+ codigoSaldo
					+ cuentaBean.getCodLinea()
					+ cuentaBean.getIdGrupoProducto());
		}
		// Si no se encuentra la descripción, buscamos por codigoCuenta,
		// codigoSaldo y "****".
		try {
			return catalogoUtils.getCatalogoDesc(CatalogoEnum.TP_NOMB_SDO,
					codigoCuenta + codigoSaldo + "****");
		} catch (ControlableException | NullPointerException e) {
			LOGGER.error("Error al obtener la descripción de la naturaleza utilizando la claveFila: "
					+ codigoCuenta + codigoSaldo + "****");
		}
		return new String();
	}

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
	 * Método privado que permite obtener la fecha mínima permitida por la
	 * aplicación (01/01/1950).
	 * 
	 * @return fecha desde mínima
	 */
	private Date obtenerFechaDesdeMinima() {
		try {
			return DateUtils.parseDate(ConstantesFuncionales.MIN_FECHA_INICIO,
					"dd/MM/yyyy");
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * Método que devuelve la descripción asociada a la clave del tipo de saldo.
	 * 
	 * @param claveFila
	 * @return descripción larga del tipo de saldo
	 */
	public String obtenerDescTipoSaldo(String claveFila) {
		try {
			catalogoBean = catalogoUtils.getCatalogoBean(
					CatalogoEnum.TP_RZN_SDO, claveFila);
			return catalogoBean.getDescripcionL();
		} catch (ControlableException | NullPointerException e) {
			return new String();
		}
	}

	/**
	 * Método que devuelve una lista con los elementos del catálogo TP_RZN_SDO
	 * (tipos de saldos por fecha).
	 * 
	 * @return lista elementos ordenados alfabéticamente
	 */
	public List<SelectItem> obtenerValoresTipoSaldo() {
		List<SelectItem> items = new ArrayList<>();
		if (naturalezaSeleccionada != null && !naturalezaSeleccionada.isEmpty()) {
			List<CatalogoBean> elementos = catalogoUtils
					.filtraCatalogoOrdenado(CatalogoEnum.TP_RZN_SDO,
							naturalezaSeleccionada);
			for (CatalogoBean elemento : elementos) {
				items.add(new SelectItem(elemento.getClaveFila(), elemento
						.getDescripcionL()));
			}
		}
		return items;
	}

	/**
	 * Método que redirige al usuario a la ficha de la cuenta o en su defecto al
	 * inicio.
	 * 
	 * @return ruta del recurso a mostrar
	 */
	public String volverAFichaCuenta() {
		managedBeanStateRecover.finNavegacion(controladorOrigen);
		if (navegacionEnumOrigen != null) {
			return navegacionEnumOrigen.getRuta();
		} else {
			return NavegacionEnum.INICIO.getRuta();
		}
	}

	/**
	 * Método que redirige al usuario a la consulta general de saldos.
	 * 
	 * @return ruta del recurso a mostrar
	 */
	public String volverASaldos() {
		obtenerFlash().put(ParametrosFlashEnum.CUENTA_BEAN.getParamFlash(),
				this.cuentaBean);
		managedBeanStateRecover.finNavegacion(controladorOrigen);
		return NavegacionEnum.SALDOS_CUENTA.getRuta();
	}

	/**
	 * Método que redirige al usuario a la consulta de saldos por fecha.
	 * 
	 * @return ruta del recurso a mostrar
	 */
	public String irAConsultaSaldosPorFecha() {
		obtenerFlash().put(ParametrosFlashEnum.CUENTA_BEAN.getParamFlash(),
				this.cuentaBean);
		obtenerFlash().put(
				ParametrosFlashEnum.MAPA_NATURALEZAS_CUENTA.getParamFlash(),
				this.mapaNaturalezas);
		obtenerFlash().put(ParametrosFlashEnum.LISTA_SALDOS.getParamFlash(),
				this.listaSaldos);
		managedBeanStateRecover.enviaController(this, null);
		return NavegacionEnum.SALDOS_FECHA.getRuta();
	}

	/**
	 * Método que redirige al usuario a la consulta de saldos por naturaleza de
	 * la cuenta.
	 * 
	 * @return ruta del recurso a mostrar
	 */
	// public String irABusquedaApuntePorNaturaleza() {
	// obtenerFlash().put(ParametrosFlashEnum.CUENTA_BEAN.getParamFlash(),
	// this.cuentaBean);
	// obtenerFlash().put(
	// ParametrosFlashEnum.MAPA_NATURALEZAS_CUENTA.getParamFlash(),
	// this.mapaNaturalezas);
	//
	// obtenerFlash().put(ParametrosFlashEnum.LISTA_SALDOS.getParamFlash(),
	// this.listaSaldos);
	//
	// managedBeanStateRecover.enviaController(this,
	// NavegacionEnum.SALDOS_CUENTA);
	// return NavegacionEnum.BUSQUEDA_APUNTE2.getRuta();
	// }
	public String irBusquedaApunte1() {
		this.obtenerFlash().put(
				ParametrosFlashEnum.CUENTA_BEAN.getParamFlash(),
				this.cuentaBean);
		this.obtenerFlash().put(
				ParametrosFlashEnum.LISTA_SALDOS.getParamFlash(),
				this.listaSaldos);

		managedBeanStateRecover.enviaController(this,
				NavegacionEnum.SALDOS_CUENTA);
		return NavegacionEnum.BUSQUEDA_APUNTE.getRuta();
	}

	public void onRowSelect(SelectEvent event) {
		saldoSeleccionado = new SaldoCuentaBean();
		saldoSeleccionado = (SaldoCuentaBean) event.getObject();

		this.obtenerFlash().put(
				ParametrosFlashEnum.CUENTA_BEAN.getParamFlash(),
				this.cuentaBean);
		this.obtenerFlash().put(
				ParametrosFlashEnum.MAPA_NATURALEZAS_CUENTA.getParamFlash(),
				this.mapaNaturalezas);

		this.obtenerFlash().put(
				ParametrosFlashEnum.LISTA_SALDOS.getParamFlash(),
				this.listaSaldos);
		this.obtenerFlash().put("saldoSeleccionado", saldoSeleccionado);

		managedBeanStateRecover.enviaController(this,
				NavegacionEnum.SALDOS_CUENTA);

		ConfigurableNavigationHandler navigationHandler = (ConfigurableNavigationHandler) FacesContext
				.getCurrentInstance().getApplication().getNavigationHandler();

		navigationHandler.performNavigation(NavegacionEnum.BUSQUEDA_APUNTE2
				.getRuta());
	}

	/**
	 * Método que ejecuta la TRN de consulta de saldos por fecha.
	 */
	public void buscar() {
		listaSaldosFecha = new ArrayList<>();
		ejecutarConsultaSaldos(null);
		panelResultadosDisponible = true;
		pagina = 10;
	}

	/**
	 * Método privado que recorre la lista de saldos y a la primera coincidencia
	 * devuelve un objeto tipo SaldoCuentaBean para ejecutar la TRN de consulta
	 * de saldos por fecha.
	 * 
	 * @return saldoCuentaBean
	 */
	private SaldoCuentaBean obtenerSaldoCuentaBean() {
		SaldoCuentaBean saldoCuentaBean = null;
		for (int i = 0; i < listaSaldos.size(); i++) {
			String codigoCuenta = listaSaldos.get(i).getCodigoCuenta();
			String codigoSaldo = listaSaldos.get(i).getCodigoSaldo();
			String naturaleza = codigoCuenta.concat(codigoSaldo);
			if (naturaleza.equals(naturalezaSeleccionada)) {
				saldoCuentaBean = listaSaldos.get(i);
				break;
			}
		}
		return saldoCuentaBean;
	}

	/**
	 * Método que ejecuta la TRN de consulta de saldos por fecha y muestra el
	 * resto de los resultados de 10 en 10.
	 */
	public void verMasDatos() {
		// Sí el tipo de saldo es D1 - FIN DIA, no paginar.
		if (tipoSaldoSeleccionado
				.equals(SaldosCuentaController.TIPO_SALDO_SIN_PAGINACION)) {
			if (pagina < listaSaldosFecha.size()) {
				pagina += 10;
			} else {
				masDatos = false;
			}
		} else {
			// Cualquier otro tipo de saldo, aplica paginación.
			if (pagina % PaginacionBean.LONGITUD_PAGINA == 0) {
				if (masDatos) {
					Date fechaUltimoSaldo = (Date) listaSaldosFecha.get(
							listaSaldosFecha.size() - 1)
							.getUltimoDatoConsultaAnterior();
					ejecutarConsultaSaldos(fechaUltimoSaldo);
					pagina += 10;
				}
			} else {
				if (pagina < listaSaldosFecha.size()) {
					pagina += 10;
				}
			}
		}
	}

	/**
	 * Método privado que ejecuta el llamado al backend de Consulta de Saldos
	 * por fecha.
	 * 
	 * @param fechaUltimoSaldo
	 */
	private void ejecutarConsultaSaldos(Date fechaUltimoSaldo) {
		try {
			SaldoCuentaBean saldoCuentaBean = obtenerSaldoCuentaBean();
			listaSaldosFecha.addAll(consultaSaldosFechaBackend.ejecutarTRN(
					cuentaBean, saldoCuentaBean, tipoSaldoSeleccionado,
					filtroFechaDesde, filtroFechaHasta, fechaUltimoSaldo));
			if (listaSaldosFecha.size() > 0) {
				masDatos = listaSaldosFecha.get(listaSaldosFecha.size() - 1)
						.getMasDatos();
			}
		} catch (NullPointerException | NoControlableException e) {
			throw new NoControlableException(
					"Error al ejecutar la TRN de consulta de saldos por fecha.",
					this.getClass().getName() + ":" + e.getMessage());
		}
	}

}