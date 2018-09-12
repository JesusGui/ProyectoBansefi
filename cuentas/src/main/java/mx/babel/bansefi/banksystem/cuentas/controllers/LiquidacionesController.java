package mx.babel.bansefi.banksystem.cuentas.controllers;

import java.io.Serializable;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.servlet.http.HttpServletRequest;

import mx.babel.arq.comun.beans.PaginacionBean;
import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.sesion.utils.ContextoUtils;
import mx.babel.bansefi.banksystem.base.backends.consultasaldo.ConsultaSaldoBackEnd;
import mx.babel.bansefi.banksystem.base.backends.consultatitular.ConsultaTitularBackend;
import mx.babel.bansefi.banksystem.base.beans.LiquidacionBean;
import mx.babel.bansefi.banksystem.base.beans.LiquidacionConceptoBean;
import mx.babel.bansefi.banksystem.base.beans.LiquidacionConceptoDemoraBean;
import mx.babel.bansefi.banksystem.base.beans.LiquidacionInformacionBean;
import mx.babel.bansefi.banksystem.base.beans.LiquidacionNumerosBean;
import mx.babel.bansefi.banksystem.base.beans.LiquidacionSaldoBean;
import mx.babel.bansefi.banksystem.base.beans.ParametrosBusquedaApunteBean;
import mx.babel.bansefi.banksystem.base.beans.domain.ClientePersonaFisicaBean;
import mx.babel.bansefi.banksystem.base.beans.domain.CuentaBean;
import mx.babel.bansefi.banksystem.base.beans.domain.relacioncuenta.RelacionadoBean;
import mx.babel.bansefi.banksystem.base.beans.domain.relacioncuenta.TipoRelacionPersonaCuenta;
import mx.babel.bansefi.banksystem.base.beans.movimientos.MovimientoBean;
import mx.babel.bansefi.banksystem.base.enums.CatalogoEnum;
import mx.babel.bansefi.banksystem.base.enums.NavegacionEnum;
import mx.babel.bansefi.banksystem.base.enums.ParametrosFlashEnum;
import mx.babel.bansefi.banksystem.base.utils.CatalogoCentrosLoaderTask;
import mx.babel.bansefi.banksystem.base.utils.CatalogoUtils;
import mx.babel.bansefi.banksystem.base.utils.ManagedBeanStateRecover;
import mx.babel.bansefi.banksystem.cuentas.backends.ConsultaAuditoriaInfoLiquidacionBackend;
import mx.babel.bansefi.banksystem.cuentas.backends.ConsultaAuditoriaLiquidacionBackend;
import mx.babel.bansefi.banksystem.cuentas.backends.ConsultaBasicaLiquidacionBackend;
import mx.babel.bansefi.banksystem.cuentas.backends.ConsultaComisionLiquidacionBackend;
import mx.babel.bansefi.banksystem.cuentas.backends.ConsultaConceptoApunteBackend;
import mx.babel.bansefi.banksystem.cuentas.backends.ConsultaDetalleApunteBackEnd;
import mx.babel.bansefi.banksystem.cuentas.backends.ConsultaInfoLiquidacionesBackend;
import mx.babel.bansefi.banksystem.cuentas.backends.ConsultaMasivaLiquidacionesBackend;
import mx.babel.bansefi.banksystem.cuentas.backends.ConsultaMovsLiquidacionBackend;
import mx.babel.bansefi.banksystem.cuentas.backends.ConsultaNumsLiquidacionBackend;
import mx.babel.bansefi.banksystem.cuentas.backends.ConsultaTramoLiquidacionBackend;
import mx.babel.bansefi.banksystem.cuentas.backends.DescConceptoLiquidacionTRDBackend;
import mx.babel.bansefi.banksystem.cuentas.backends.SimularLiquidacionesBackend;
import mx.babel.bansefi.banksystem.cuentas.backends.TablaIndicadoresApunteTRDBackend;
import mx.babel.bansefi.banksystem.cuentas.beans.ConceptoApunteBean;
import mx.babel.bansefi.banksystem.cuentas.wrappers.ConsultaLiquidacionesWrapper;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.component.tabview.Tab;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.TabChangeEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Controlador para el flujo de liquidaciones.
 * 
 * @author gerard.chavez
 */

@Component
@Scope("view")
@ManagedBean
@ViewScoped
public class LiquidacionesController implements Serializable {

	private static final long serialVersionUID = -5059094391770022922L;
	private static final Logger LOGGER = LogManager
			.getLogger(LiquidacionesController.class.getName());

	private static final String SIMULACION_LIQUIDACION = "simulacionLiquidacion";
	private static final String TIPO_TRAMO_L = "L";
	private static final String TIPO_TRAMO_R = "R";
	private static final String LIQUIDACION = "LIQUIDACION";
	private static final String COMPENSACION = "COMPENSACION";

	private static final String COD_CTA_07 = "07";
	private static final String COD_CTA_08 = "08";
	private static final String COD_CTA_09 = "09";
	private static final String COD_CTA_10 = "10";
	private static final String COD_CTA_13 = "13";
	private static final String COD_CTA_17 = "17";
	private static final String COD_CTA_21 = "21";

	private static final String COD_LINEA_03 = "03";

	private static final String ID_GPO_PROD_51 = "51";

	/**
	 * Variable utilizada para guardar la redireccion al flujo de entrada a la
	 * consulta de planificaciones.
	 */
	private NavegacionEnum destino;

	/**
	 * Variable utilizada para guardar el contenido del controller origen a la
	 * consulta de planificaciones.
	 */
	private Object destinoController;

	private LiquidacionBean liquidacionSeleccionada;
	private MovimientoBean movimientoSeleccionado;
	private LiquidacionConceptoBean conceptoSeleccionado;

	private CuentaBean cuentaBean;

	private boolean cargaNumeros;
	private boolean cargaNumerosDemora;
	private boolean cargaAuditoria;

	private boolean muestraTablaResultados;

	@Autowired
	ContextoUtils contextoUtils;

	@Autowired
	CatalogoCentrosLoaderTask catalogoCentros;

	@Autowired
	private CatalogoUtils catalogos;

	@Autowired
	ConsultaMasivaLiquidacionesBackend consultaMasivaLiquidacionesBackend;

	@Autowired
	ConsultaBasicaLiquidacionBackend consultaDetalleLiquidacionBackend;

	@Autowired
	ConsultaInfoLiquidacionesBackend consultaInfoLiquidacionesBackend;

	@Autowired
	ConsultaTramoLiquidacionBackend consultaTramoBackend;

	@Autowired
	ConsultaAuditoriaLiquidacionBackend consultaAuditoriaLiquidacionBackend;

	@Autowired
	ConsultaAuditoriaInfoLiquidacionBackend consultaAuditoriaInfoLiquidacionBackend;

	@Autowired
	SimularLiquidacionesBackend simularLiquidacionesBackend;

	@Autowired
	ConsultaMovsLiquidacionBackend consultaMovsLiquidacionBackend;

	@Autowired
	ConsultaNumsLiquidacionBackend consultaNumsLiquidacionBackend;

	@Autowired
	ConsultaSaldoBackEnd consultaSaldoBackend;

	@Autowired
	ConsultaTitularBackend consultaTitularBackend;

	@Autowired
	DescConceptoLiquidacionTRDBackend descConceptoLiquidacionBackend;

	@Autowired
	ManagedBeanStateRecover managedBeanStateRecover;

	@Autowired
	ConsultaComisionLiquidacionBackend consultaComisionLiquidacionBackend;

	@Autowired
	ConsultaConceptoApunteBackend consultaConceptoApunteBackend;

	@Autowired
	ConsultaDetalleApunteBackEnd consultaDetalleApunteBackEnd;

	@Autowired
	TablaIndicadoresApunteTRDBackend tablaIndicadoresApunteTRDBackend;

	@Autowired
	ConsultaLiquidacionesWrapper consultaLiquidacionesWrapper;

	public LiquidacionesController() {
		this.destinoController = "";
		this.liquidacionSeleccionada = new LiquidacionBean();
		this.conceptoSeleccionado = new LiquidacionConceptoBean();
		this.liquidacionSeleccionada
				.setListaConceptos(new ArrayList<LiquidacionConceptoBean>());
		this.liquidacionSeleccionada
				.setListaInformacion(new ArrayList<LiquidacionInformacionBean>());
		this.liquidacionSeleccionada
				.setListaMovimientos(new ArrayList<MovimientoBean>());
		this.liquidacionSeleccionada
				.setListaResultadosLiquidaciones(new ArrayList<LiquidacionBean>());
		this.movimientoSeleccionado = new MovimientoBean();
		this.cuentaBean = new CuentaBean();
		this.cargaNumeros = true;
		this.cargaNumerosDemora = true;
		this.cargaAuditoria = true;
		this.muestraTablaResultados = true;
	}

	/**
	 * Método de inicialización de pantallas para flujo de liquidaciones. Aquí
	 * se recuperan los diversos beans necesarios para cada flujo.
	 */
	@PostConstruct
	public void init() {
		// on postback flash is empty and exception is raised
		if (!FacesContext.getCurrentInstance().isPostback()) {
			if (this.obtenerFlash().get(SIMULACION_LIQUIDACION) != null) {
				this.obtenerFlash().put(SIMULACION_LIQUIDACION,
						(boolean) obtenerFlash().get(SIMULACION_LIQUIDACION));
			}
			if (this.obtenerFlash()
					.get(ParametrosFlashEnum.NAVEGACION_MANAGED_BEAN
							.getParamFlash()) != null) {
				if ((Boolean) this.obtenerFlash().get(
						ParametrosFlashEnum.NAVEGACION_MANAGED_BEAN
								.getParamFlash())) {
					LOGGER.debug("Accedemos al metodo de inicio init(), iniciando la navegacion tras volver de otro flujo");
					this.destino = this.managedBeanStateRecover.getDestino();
					this.destinoController = this.managedBeanStateRecover
							.getController();
					this.inicializaDatos();
					// se consulta saldo actual
					ClientePersonaFisicaBean persona = new ClientePersonaFisicaBean();
					for (RelacionadoBean relacionado : this.cuentaBean
							.getPersonasRelacionadas()) {
						if ((TipoRelacionPersonaCuenta.TITULAR
								.equals(relacionado.getTipo()))) {
							if (relacionado.getNumero() == 1) {
								persona = relacionado.getPersona();
							}
						}
					}
					if (persona != null) {
						this.cuentaBean.setSaldoBean(consultaSaldoBackend
								.consultaSaldo(new BigInteger(this.cuentaBean
										.getNumeroCuenta().toString()), persona
										.getTipoIdentificacion(), persona
										.getNumIdentificacion()));
					}
				} else {
					LOGGER.debug("Accedemos al metodo de inicio init(), recuperando controller tras navegacion");
					this.managedBeanStateRecover.recuperaController(this);
				}
			} else {
				this.inicializaDatos();
			}
		}
	}

	/**
	 * Método privado que obtiene los parámetros de la Flash.
	 */
	private void inicializaDatos() {
		if (this.obtenerFlash().get(
				ParametrosFlashEnum.CUENTA_BEAN.getParamFlash()) != null) {
			this.cuentaBean = (CuentaBean) this.obtenerFlash().get(
					ParametrosFlashEnum.CUENTA_BEAN.getParamFlash());
		} else {
			throw new NoControlableException(
					"Error al ingresar a la consulta de liquidaciones. No se tienen los datos necesarios.",
					"No se cuenta con el bean de cuenta necesario.");
		}
		if (this.obtenerFlash().get(
				ParametrosFlashEnum.LIQUIDACION_BEAN.getParamFlash()) != null) {
			this.liquidacionSeleccionada = (LiquidacionBean) this
					.obtenerFlash().get(
							ParametrosFlashEnum.LIQUIDACION_BEAN
									.getParamFlash());
			if (this.liquidacionSeleccionada.getListaResultadosLiquidaciones() != null) {
				if (this.liquidacionSeleccionada
						.getListaResultadosLiquidaciones().isEmpty()) {
					this.muestraTablaResultados = false;
				} else {
					this.muestraTablaResultados = true;
				}
			}
		} else {
			this.liquidacionSeleccionada = new LiquidacionBean();
			this.liquidacionSeleccionada
					.setListaConceptos(new ArrayList<LiquidacionConceptoBean>());
		}
		String arCadenas[] = new String[2];
		arCadenas = StringUtils.split(
				NavegacionEnum.DETALLE_LIQUIDACION.getRuta(), '?');
		if (this.obtenerUrlVistaDestino().contains(arCadenas[0])) {
			this.cargarSeccionNumerosLiquidacion();
		}
	}

	// INICIA DECLARACIÓN DE GETTERS Y SETTERS

	/**
	 * Método que devuelve un objeto tipo LiquidacionBean.
	 * 
	 * @return liquidacionSeleccionada
	 */
	public LiquidacionBean getLiquidacionSeleccionada() {
		return liquidacionSeleccionada;
	}

	/**
	 * Método que establece un objeto tipo LiquidacionBean.
	 * 
	 * @param liquidacionSeleccionada
	 */
	public void setLiquidacionSeleccionada(
			LiquidacionBean liquidacionSeleccionada) {
		this.liquidacionSeleccionada = liquidacionSeleccionada;
	}

	/**
	 * Método que devuelve un objeto tipo MovimientoBean.
	 * 
	 * @return movimientoSeleccionado
	 */
	public MovimientoBean getMovimientoSeleccionado() {
		return movimientoSeleccionado;
	}

	/**
	 * Método que establece un objeto tipo MovimientoBean.
	 * 
	 * @param movimientoSeleccionado
	 */
	public void setMovimientoSeleccionado(MovimientoBean movimientoSeleccionado) {
		this.movimientoSeleccionado = movimientoSeleccionado;
	}

	/**
	 * Método que devuelve un objeto tipo LiquidacionConceptoBean.
	 * 
	 * @return conceptoSeleccionado
	 */
	public LiquidacionConceptoBean getConceptoSeleccionado() {
		return conceptoSeleccionado;
	}

	/**
	 * Método que establece un objeto tipo LiquidacionConceptoBean.
	 * 
	 * @param conceptoSeleccionado
	 */
	public void setConceptoSeleccionado(
			LiquidacionConceptoBean conceptoSeleccionado) {
		this.conceptoSeleccionado = conceptoSeleccionado;
	}

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
	 * Método que devuelve un indicador booleano para determinar sí se debe
	 * mostrar la tabla de resultados o no.
	 * 
	 * @return indicador booleano
	 */
	public boolean isMuestraTablaResultados() {
		return muestraTablaResultados;
	}

	/**
	 * Método que establece el valor del indicador para mostrar la tabla de
	 * resultados.
	 * 
	 * @param muestraTablaResultados
	 */
	public void setMuestraTablaResultados(boolean muestraTablaResultados) {
		this.muestraTablaResultados = muestraTablaResultados;
	}

	/**
	 * Método que devuelve un NavegacionEnum para volver al destino.
	 * 
	 * @return destino
	 */
	public NavegacionEnum getDestino() {
		return destino;
	}

	/**
	 * Método que establece un NavegacionEnum para volver al destino.
	 * 
	 * @param destino
	 */
	public void setDestino(NavegacionEnum destino) {
		this.destino = destino;
	}

	/**
	 * Método que devuelve un objeto con el controlador destino.
	 * 
	 * @return destinoController
	 */
	public Object getDestinoController() {
		return destinoController;
	}

	/**
	 * Método que establece un objeto con el controlador destino.
	 * 
	 * @param destinoController
	 */
	public void setDestinoController(Object destinoController) {
		this.destinoController = destinoController;
	}

	/**
	 * Método que devuelve el valor de la constante de simulación de una
	 * liquidación.
	 * 
	 * @return SIMULACION_LIQUIDACION
	 */
	public static String getSimulacionLiquidacion() {
		return SIMULACION_LIQUIDACION;
	}

	// INICIA DECLARACIÓN DE MÉTODOS PRINCIPALES.

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
	 * Método privado que devuelve la URL de la vista destino.
	 * 
	 * @author omar.marquez
	 * @return urlVistaDestino
	 */
	private String obtenerUrlVistaDestino() {
		HttpServletRequest request = (HttpServletRequest) FacesContext
				.getCurrentInstance().getExternalContext().getRequest();
		return request.getRequestURL().toString();
	}

	/**
	 * Método para realizar la búsqueda de liquidaciones con base en los filtros
	 * introducidos por el usuario.
	 */
	public void consultaLiquidaciones(boolean masDatos) {
		try {
			if (!masDatos) {
				if (liquidacionSeleccionada.getListaResultadosLiquidaciones() != null) {
					liquidacionSeleccionada.getListaResultadosLiquidaciones()
							.clear();
				}
				liquidacionSeleccionada.setPagina(0);
				liquidacionSeleccionada.setPaginaVista(0);
				liquidacionSeleccionada
						.setListaResultadosLiquidaciones(consultaMasivaLiquidacionesBackend
								.ejecutarTRN(cuentaBean,
										liquidacionSeleccionada));
				liquidacionSeleccionada.setPaginaVista(1);
				muestraTablaResultados = true;
			} else {
				if (liquidacionSeleccionada.getPaginaVista() == 0) {
					// llamado inicial
					liquidacionSeleccionada
							.setListaResultadosLiquidaciones(consultaMasivaLiquidacionesBackend
									.ejecutarTRN(cuentaBean,
											liquidacionSeleccionada));
					liquidacionSeleccionada
							.setPaginaVista(liquidacionSeleccionada
									.getPaginaVista() + 1);
				} else {
					// validar tamaño de lista
					if ((liquidacionSeleccionada.getPaginaVista() * 10 % PaginacionBean.LONGITUD_PAGINA) == 0) {
						if (liquidacionSeleccionada
								.getListaResultadosLiquidaciones()
								.get(liquidacionSeleccionada
										.getListaResultadosLiquidaciones()
										.size() - 1).getMasDatos()) {
							liquidacionSeleccionada
									.setPagina(liquidacionSeleccionada
											.getPagina() + 1);
							liquidacionSeleccionada
									.getListaResultadosLiquidaciones()
									.addAll(consultaMasivaLiquidacionesBackend
											.ejecutarTRN(cuentaBean,
													liquidacionSeleccionada));
							liquidacionSeleccionada
									.setPaginaVista(liquidacionSeleccionada
											.getPaginaVista() + 1);
						}
					} else {
						if (liquidacionSeleccionada.getPaginaVista() * 10 < liquidacionSeleccionada
								.getListaResultadosLiquidaciones().size()) {
							liquidacionSeleccionada
									.setPaginaVista(liquidacionSeleccionada
											.getPaginaVista() + 1);
						}
					}
				}
				muestraTablaResultados = true;
			}
		} catch (ControlableException ce) {
			LOGGER.debug("Excepcion indicando no hay datos: " + ce.getMessage());
			if (ce.getRtncod() == 7) {
				liquidacionSeleccionada.setPaginaVista(0);
				muestraTablaResultados = false;
			}
		}

	}

	/**
	 * Método que se ejecuta al seleccionar una liquidación para ver su detalle
	 * 
	 * @param event
	 *            evento de selección de una fila
	 */
	public void seleccionaLiquidacion(SelectEvent event) {
		LiquidacionBean tempFiltros = null;

		if (liquidacionSeleccionada != null) {
			tempFiltros = liquidacionSeleccionada;
		}

		liquidacionSeleccionada = (LiquidacionBean) event.getObject();

		if (tempFiltros != null) {
			liquidacionSeleccionada.setTipoLiqFiltro(tempFiltros
					.getTipoLiqFiltro());
			liquidacionSeleccionada.setFechaInicioFiltro(tempFiltros
					.getFechaInicioFiltro());
			liquidacionSeleccionada.setFechaFinFiltro(tempFiltros
					.getFechaFinFiltro());
			liquidacionSeleccionada.setIncluirAnuladosFiltro(tempFiltros
					.isIncluirAnuladosFiltro());
			liquidacionSeleccionada.setListaResultadosLiquidaciones(tempFiltros
					.getListaResultadosLiquidaciones());
			liquidacionSeleccionada.setPagina(tempFiltros.getPagina());
			liquidacionSeleccionada
					.setPaginaVista(tempFiltros.getPaginaVista());
		}
		try {
			consultaDetalleLiquidacionBackend.ejecutarTRN(cuentaBean,
					liquidacionSeleccionada);

			descConceptoLiquidacionBackend.ejecutarTRN(liquidacionSeleccionada);

			List<LiquidacionInformacionBean> informacion = consultaInfoLiquidacionesBackend
					.ejecutarTRN(cuentaBean.getNumeroCuenta(),
							liquidacionSeleccionada
									.getFechaLiquidacion(),
							liquidacionSeleccionada.getNumsec());
			
			if(liquidacionSeleccionada.getListaInformacion() == null ||
					liquidacionSeleccionada.getListaInformacion().isEmpty()){
				liquidacionSeleccionada.setListaInformacion(informacion);
			}else{
				liquidacionSeleccionada.getListaInformacion().addAll(informacion);
			}
		} catch (ControlableException ce) {
			LOGGER.error("Error al consultar el detalle de la liquidación."
					+ this.getClass().getName() + ce.getMessage());
		}
		obtenerFlash().put(
				ParametrosFlashEnum.LIQUIDACION_BEAN.getParamFlash(),
				liquidacionSeleccionada);
		obtenerFlash().put(ParametrosFlashEnum.CUENTA_BEAN.getParamFlash(),
				cuentaBean);

		managedBeanStateRecover.enviaControllerMap(destinoController, destino);

		ConfigurableNavigationHandler configurableNavigationHandler = (ConfigurableNavigationHandler) FacesContext
				.getCurrentInstance().getApplication().getNavigationHandler();

		configurableNavigationHandler
				.performNavigation(NavegacionEnum.DETALLE_LIQUIDACION.getRuta());
	}

	/**
	 * Metodo para simular una liquidación.
	 * 
	 * @return ruta del recurso a mostrar
	 */
	public String simularLiquidaciones() {
		simularLiquidacionesBackend.ejecutarTRN(cuentaBean,
				liquidacionSeleccionada);
		descConceptoLiquidacionBackend.ejecutarTRN(liquidacionSeleccionada);

		obtenerFlash().put(
				ParametrosFlashEnum.LIQUIDACION_BEAN.getParamFlash(),
				liquidacionSeleccionada);
		obtenerFlash().put(ParametrosFlashEnum.CUENTA_BEAN.getParamFlash(),
				cuentaBean);

		managedBeanStateRecover.enviaControllerMap(destinoController, destino);
		return NavegacionEnum.DETALLE_SIMULAR_LIQUIDACION.getRuta();
	}

	/**
	 * Metodo que identifica el tab seleccionado y dispara la carga
	 * correspondiente
	 * 
	 * @param event
	 *            evento al seleccionar un tab del acordeon
	 */
	public void onTabChange(TabChangeEvent event) {
		Tab activeTab = event.getTab();
		switch (activeTab.getId()) {
		case "consultaMovimientosTab":
			consultaMovimientos();
			break;
		case "consultaNumerosTab":
			if (cargaNumeros) {
				consultaNumeros();
			}
			break;
		case "consultaNumerosDemoraTab":
			if (cargaNumerosDemora) {
				consultaNumerosDemora();
			}
			break;
		case "consultaAuditoriaTab":
			if (cargaAuditoria) {
				consultaAuditoria();
			}
			break;

		default:
			break;

		}
	}

	/**
	 * Método para consultar los apuntes (movimientos) de una liquidación.
	 */
	public void consultaMovimientos() {
		liquidacionSeleccionada
				.setListaMovimientos(consultaMovsLiquidacionBackend
						.ejecutarTRN(cuentaBean, liquidacionSeleccionada));
	}

	/**
	 * Método para consultar los números de una liquidación.
	 */
	public void consultaNumeros() {
		try {
			liquidacionSeleccionada
					.setNumerosBean(consultaNumsLiquidacionBackend.ejecutarTRN(
							cuentaBean, liquidacionSeleccionada, 0));
		} catch (ControlableException ce) {
			if (ce.getRtncod() == 7) {
				liquidacionSeleccionada
						.setNumerosBean(new LiquidacionNumerosBean());
			}
		}
		liquidacionSeleccionada.getNumerosBean().setCodOperacion(
				liquidacionSeleccionada.getTipoLiquidacion());
		liquidacionSeleccionada.getNumerosBean().setFechaPeriodoDesde(
				liquidacionSeleccionada.getPeriodoDesdeLiquidacion());
		liquidacionSeleccionada.getNumerosBean().setFechaPeriodoHasta(
				liquidacionSeleccionada.getPeriodoHastaLiquidacion());
		cargaNumeros = false;
	}

	/**
	 * Método para consulta los números de demora de una liquidación.
	 */
	public void consultaNumerosDemora() {
		try {
			liquidacionSeleccionada
					.setNumerosDemoraBean(consultaNumsLiquidacionBackend
							.ejecutarTRN(cuentaBean, liquidacionSeleccionada, 1));
		} catch (ControlableException ce) {
			if (ce.getRtncod() == 7) {
				liquidacionSeleccionada
						.setNumerosBean(new LiquidacionNumerosBean());
			}
		}
		if (liquidacionSeleccionada.getNumerosDemoraBean() != null) {
			liquidacionSeleccionada.getNumerosDemoraBean().setCodOperacion(
					liquidacionSeleccionada.getTipoLiquidacion());
			liquidacionSeleccionada.getNumerosDemoraBean()
					.setFechaPeriodoDesde(
							liquidacionSeleccionada
									.getPeriodoDesdeLiquidacion());
			liquidacionSeleccionada.getNumerosDemoraBean()
					.setFechaPeriodoHasta(
							liquidacionSeleccionada
									.getPeriodoHastaLiquidacion());
		}
		cargaNumerosDemora = false;
	}

	/**
	 * Método encargado de invocar a la TRN de consulta de auditoria para
	 * establecer y mostrar la info en pantalla.
	 */
	public void consultaAuditoria() {
		liquidacionSeleccionada
				.setAuditoriaBean(consultaAuditoriaLiquidacionBackend
						.ejecutarTRN(cuentaBean, liquidacionSeleccionada));
		consultaAuditoriaInfoLiquidacionBackend
				.ejecutarTRN(liquidacionSeleccionada.getAuditoriaBean());
		liquidacionSeleccionada.getAuditoriaBean().setCentroAc(
				cuentaBean.getCentro());
		// establecemos
		cargaAuditoria = false;
	}

	/**
	 * Método utilizado para seleccionar un tramo de una liquidación y con base
	 * en el número de resultados, mostrar el detalle del elemento seleccionado
	 * o bien un listado intermedio.
	 * 
	 * @param event
	 *            evento de seleccion en la tabla
	 */
	public void seleccionaConcepto(SelectEvent event) {
		String ruta = "";

		conceptoSeleccionado = (LiquidacionConceptoBean) event.getObject();

		liquidacionSeleccionada.setConceptoSeleccionado(conceptoSeleccionado);

		liquidacionSeleccionada.getConceptoSeleccionado()
				.setListaConceptosIntermedios(
						new ArrayList<LiquidacionConceptoBean>());

		// Sí el código de la cuenta es 10, 13, 17 o 21, entra a este bloque.
		if (COD_CTA_10.equals(liquidacionSeleccionada.getConceptoSeleccionado()
				.getCodCuenta())
				|| COD_CTA_13.equals(liquidacionSeleccionada
						.getConceptoSeleccionado().getCodCuenta())
				|| COD_CTA_17.equals(liquidacionSeleccionada
						.getConceptoSeleccionado().getCodCuenta())
				|| COD_CTA_21.equals(liquidacionSeleccionada
						.getConceptoSeleccionado().getCodCuenta())) {
			LOGGER.debug("Se ejecuta la TRN de consulta de comisiones de una liquidación.");
			ruta = this.consultaComisionLiquidacion();
		} else {
			// Sí el código de la cuenta es 07, 08, ó 09, entra a este bloque.
			if (COD_CTA_07.equals(liquidacionSeleccionada
					.getConceptoSeleccionado().getCodCuenta())
					|| COD_CTA_08.equals(liquidacionSeleccionada
							.getConceptoSeleccionado().getCodCuenta())
					|| COD_CTA_09.equals(liquidacionSeleccionada
							.getConceptoSeleccionado().getCodCuenta())) {
				// Sí el código de la cuenta es 09, entra a este bloque.
				if (COD_CTA_09.equals(liquidacionSeleccionada
						.getConceptoSeleccionado().getCodCuenta())) {
					LOGGER.debug("Se ejecuta lógica de TRD para mostrar los datos de demora de una liquidación.");
					ruta = this.consultaDemoraLiquidacion();
				} else {
					LOGGER.debug("Se ejecuta la TRN de consulta de tramo de una liquidación.");
					ruta = this.consultaTramoLiquidacion();
				}
			} else {
				RequestContext.getCurrentInstance().execute(
						"PF('dlgErrorGenerico').show()");
			}
		}

		Map<String, String> params = FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap();

		obtenerFlash().put(SIMULACION_LIQUIDACION,
				new Boolean(params.get(SIMULACION_LIQUIDACION)));
		obtenerFlash().put(
				ParametrosFlashEnum.LIQUIDACION_BEAN.getParamFlash(),
				liquidacionSeleccionada);
		obtenerFlash().put(ParametrosFlashEnum.CUENTA_BEAN.getParamFlash(),
				cuentaBean);

		managedBeanStateRecover.enviaControllerMap(destinoController, destino);

		ConfigurableNavigationHandler configurableNavigationHandler = (ConfigurableNavigationHandler) FacesContext
				.getCurrentInstance().getApplication().getNavigationHandler();

		configurableNavigationHandler.performNavigation(ruta);
	}

	/**
	 * Método privado que ejecuta la TRN para visualizar las comisiones y gastos
	 * de una liquidación.
	 * 
	 * @author omar.marquez
	 * @return ruta del recurso a mostrar
	 */
	private String consultaComisionLiquidacion() {
		try {
			liquidacionSeleccionada
					.setConceptoSeleccionado(consultaComisionLiquidacionBackend
							.ejecutarTRN(this.cuentaBean,
									this.liquidacionSeleccionada));
			liquidacionSeleccionada.getConceptoSeleccionado().setNomParametro(
					this.obtenerDescParametro());
			liquidacionSeleccionada.getConceptoSeleccionado()
					.setFormaAplicacion(
							this.obtenerDescCatalogoFormaAplicacion());
			return NavegacionEnum.DETALLE_AMP_COM_LIQUIDACION.getRuta();
		} catch (ControlableException e) {
			LOGGER.error("Error al ejecutar la TRN de consulta de comisiones de una liquidación. "
					+ this.getClass().getName() + ":" + e.getMessage());
			RequestContext.getCurrentInstance().execute(
					"PF('dlgErrorGenerico').show()");
			return new String();
		}
	}

	/**
	 * Método privado que compara el concepto seleccionado y establece los
	 * atributos necesarios para mostrar cualquiera de las vistas de tramo de
	 * una liquidación sin ejecutar TRN alguna.
	 * 
	 * @author omar.marquez
	 * @return ruta del recurso a mostrar
	 */
	private String consultaDemoraLiquidacion() {
		if (liquidacionSeleccionada.getListaConceptosDemora() != null
				&& liquidacionSeleccionada.getListaConceptosDemora().size() > 0) {
			if (liquidacionSeleccionada.getListaConceptosDemora().size() == 1) {
				consultaLiquidacionesWrapper.wrappConceptosLiquidacion(
						liquidacionSeleccionada.getListaConceptosDemora()
								.get(0), liquidacionSeleccionada
								.getConceptoSeleccionado());
				liquidacionSeleccionada.getConceptoSeleccionado().setDias(
						obtenerNumeroDias(liquidacionSeleccionada
								.getListaConceptosDemora().get(0)
								.getFechaHastaTramo(), liquidacionSeleccionada
								.getListaConceptosDemora().get(0)
								.getFechaDesdeTramo()));
				establecerTipoTramo(liquidacionSeleccionada
						.getConceptoSeleccionado());
				return NavegacionEnum.DETALLE_AMP_LIQUIDACION.getRuta();
			} else {
				List<LiquidacionConceptoBean> conceptos = new ArrayList<>();
				for (LiquidacionConceptoDemoraBean beanDatosDemora : liquidacionSeleccionada
						.getListaConceptosDemora()) {
					LiquidacionConceptoBean concepto = consultaLiquidacionesWrapper
							.wrappConceptosLiquidacion(beanDatosDemora);
					if (concepto != null) {
						concepto.setDias(obtenerNumeroDias(
								concepto.getFechaHastaTramo(),
								concepto.getFechaDesdeTramo()));
						establecerTipoTramo(concepto);
						conceptos.add(concepto);
					}
				}
				liquidacionSeleccionada.getConceptoSeleccionado()
						.setListaConceptosIntermedios(conceptos);
				return NavegacionEnum.DETALLE_AMP_INT_LIQUIDACION.getRuta();
			}
		} else {
			LOGGER.debug("No existe información de demora para la liquidación.");
			RequestContext.getCurrentInstance().execute(
					"PF('dlgErrorGenerico').show()");
			return new String();
		}
	}

	/**
	 * Método privado que establece sí se trata de una LIQUIDACIÓN (L) o una
	 * COMPENSACIÓN (R). Referirse a ConsultaTramoLiquidacionBackend para más
	 * información.
	 * 
	 * @param liquidacionConceptoBean
	 */
	private void establecerTipoTramo(
			LiquidacionConceptoBean liquidacionConceptoBean) {
		if (TIPO_TRAMO_L.equals(liquidacionConceptoBean.getTipoTramo())) {
			liquidacionConceptoBean.setTipoTramo(LIQUIDACION);
		} else if (TIPO_TRAMO_R.equals(liquidacionConceptoBean.getTipoTramo())) {
			liquidacionConceptoBean.setTipoTramo(COMPENSACION);
		}
	}

	/**
	 * Método privado que a partir de 2 fechas devuelve el número de días.
	 * 
	 * @param fechaHasta
	 * @param fechaDesde
	 * @return número de días
	 */
	private int obtenerNumeroDias(Date fechaHasta, Date fechaDesde) {
		Calendar calendar = Calendar.getInstance();
		if (fechaHasta != null && fechaDesde != null) {
			calendar.setTimeInMillis(fechaHasta.getTime()
					- fechaDesde.getTime());
			return calendar.get(Calendar.DAY_OF_YEAR);
		}
		return 0;
	}

	/**
	 * Método privado que ejecuta la TRN para visualizar el tramo de una
	 * liquidación.
	 * 
	 * @author omar.marquez
	 * @return ruta del recurso a mostrar
	 */
	private String consultaTramoLiquidacion() {
		try {
			liquidacionSeleccionada.getConceptoSeleccionado()
					.setListaConceptosIntermedios(
							consultaTramoBackend.ejecutarTRN(cuentaBean,
									liquidacionSeleccionada));
			if (liquidacionSeleccionada.getConceptoSeleccionado()
					.getListaConceptosIntermedios().size() == 0) {
				RequestContext.getCurrentInstance().execute(
						"PF('dlgErrorGenerico').show()");
			} else if (liquidacionSeleccionada.getConceptoSeleccionado()
					.getListaConceptosIntermedios().size() == 1) {
				LiquidacionConceptoBean concepto = liquidacionSeleccionada
						.getConceptoSeleccionado()
						.getListaConceptosIntermedios().get(0);
				concepto.setCodOrigen(conceptoSeleccionado.getCodOrigen());
				concepto.setCodCuenta(conceptoSeleccionado.getCodCuenta());
				concepto.setDescConcepto(conceptoSeleccionado.getDescConcepto());
				liquidacionSeleccionada.setConceptoSeleccionado(concepto);
				return NavegacionEnum.DETALLE_AMP_LIQUIDACION.getRuta();
			} else if (liquidacionSeleccionada.getConceptoSeleccionado()
					.getListaConceptosIntermedios().size() > 1) {
				return NavegacionEnum.DETALLE_AMP_INT_LIQUIDACION.getRuta();
			}
			return new String();
		} catch (ControlableException e) {
			LOGGER.error("Error al ejecutar la TRN de consulta de tramo de una liquidación. "
					+ this.getClass().getName() + ":" + e.getMessage());
			RequestContext.getCurrentInstance().execute(
					"PF('dlgErrorGenerico').show()");
			return new String();
		}
	}

	/**
	 * Método privado que ejecuta la TRN de consulta de conceptos de un apunte y
	 * a partir de la ID_PARMCD devuelve la descripción del parámetro contenido
	 * en la vista: consultaDetAmpComLiquidacion.xhtml.
	 * 
	 * @author omar.marquez
	 * @return descripción del parámetro
	 */
	private String obtenerDescParametro() {
		if (liquidacionSeleccionada != null
				&& liquidacionSeleccionada.getConceptoSeleccionado() != null
				&& liquidacionSeleccionada.getConceptoSeleccionado()
						.getIdparmcd() != null) {
			String id_parmcd = liquidacionSeleccionada
					.getConceptoSeleccionado().getIdparmcd();
			ConceptoApunteBean caBean = consultaConceptoApunteBackend
					.ejecutarTRN(id_parmcd);
			if (caBean.getNombre() != null) {
				return caBean.getNombre();
			}
		}
		return new String();
	}

	/**
	 * Método privado que a partir del indicador de forma de aplicación,
	 * devuelve su descripción.
	 * 
	 * @author omar.marquez
	 * @return descripción de la forma de aplicación de una liquidación
	 */
	private String obtenerDescCatalogoFormaAplicacion() {
		if (liquidacionSeleccionada != null
				&& liquidacionSeleccionada.getConceptoSeleccionado() != null
				&& liquidacionSeleccionada.getConceptoSeleccionado()
						.getIndpreciokt() != null) {
			return catalogos.getCatalogoDesc(CatalogoEnum.FORMA_APPCN,
					liquidacionSeleccionada.getConceptoSeleccionado()
							.getIndpreciokt());
		}
		return new String();
	}

	/**
	 * Método utilizado para seleccionar un tramo intermedio de una liquidación
	 * y mostrar el detalle del elemento seleccionado
	 * 
	 * @param event
	 *            evento de seleccion en la tabla
	 */
	public void seleccionaConceptoIntermedio(SelectEvent event) {

		if (obtenerFlash().get(SIMULACION_LIQUIDACION) != null) {
			obtenerFlash().put(SIMULACION_LIQUIDACION,
					(boolean) obtenerFlash().get(SIMULACION_LIQUIDACION));
		}

		LiquidacionConceptoBean conceptoSeleccionado = (LiquidacionConceptoBean) event
				.getObject();

		liquidacionSeleccionada.setConceptoSeleccionado(conceptoSeleccionado);

		obtenerFlash().put(
				ParametrosFlashEnum.LIQUIDACION_BEAN.getParamFlash(),
				liquidacionSeleccionada);
		obtenerFlash().put(ParametrosFlashEnum.CUENTA_BEAN.getParamFlash(),
				cuentaBean);

		managedBeanStateRecover.enviaControllerMap(destinoController, destino);

		ConfigurableNavigationHandler configurableNavigationHandler = (ConfigurableNavigationHandler) FacesContext
				.getCurrentInstance().getApplication().getNavigationHandler();

		configurableNavigationHandler
				.performNavigation(NavegacionEnum.DETALLE_AMP_LIQUIDACION
						.getRuta());
	}

	/**
	 * Método para ir al detalle de un apunte de una liquidación
	 * 
	 * @param event
	 */
	public void seleccionaApunte(SelectEvent event) {
		movimientoSeleccionado = (MovimientoBean) event.getObject();

		// 1 porque todo apunte de una liquidacion tiene el mismo origen.
		movimientoSeleccionado.setApunteLiquidacion(1);

		// HL porque se ingresa al detalle desde liquidaciones.
		movimientoSeleccionado.setCodigoOrigenApunte("HL");

		ejecutarConsultaDetalleApunte();
		obtenerListaIndicadores();
		establecerParametrosBusqueda();

		managedBeanStateRecover.enviaController(this,
				NavegacionEnum.DETALLE_LIQUIDACION);

		ConfigurableNavigationHandler configurableNavigationHandler = (ConfigurableNavigationHandler) FacesContext
				.getCurrentInstance().getApplication().getNavigationHandler();

		configurableNavigationHandler
				.performNavigation(NavegacionEnum.DETALLE_APUNTE.getRuta());
	}

	/**
	 * Método privado que ejecuta la TRN de consulta detallada de apuntes.
	 */
	private void ejecutarConsultaDetalleApunte() {
		try {
			consultaDetalleApunteBackEnd.ejecutarTRN(movimientoSeleccionado);
		} catch (ControlableException e) {
			LOGGER.error("Error al ejecutar la TRN de consulta detallada de apuntes."
					+ this.getClass().getName() + ":" + e.getMessage());
		} catch (NullPointerException | NoControlableException e) {
			throw new NoControlableException(
					"Error al ejecutar la TRN de consulta detallada de apuntes.",
					this.getClass().getName() + ":" + e.getMessage());
		}
	}

	/**
	 * Método privado que ejecuta la TRD de consulta de indicadores de un
	 * apunte. Este método permite construir la tabla de indicadores vs.
	 * situaciones del apunte.
	 */
	private void obtenerListaIndicadores() {
		try {
			tablaIndicadoresApunteTRDBackend
					.ejecutarTRN(movimientoSeleccionado);
		} catch (NullPointerException | ControlableException
				| NoControlableException e) {
			LOGGER.error("Error al ejecutar la TRD de consulta de indicadores de un apunte. "
					+ this.getClass().getName() + ":" + e.getMessage());
		}
	}

	/**
	 * Método privado que establece todos los parámetros de la busqueda
	 * (entradas y salidas) en la Flash para que la información se encuentre
	 * disponible al volver.
	 */
	private void establecerParametrosBusqueda() {
		ParametrosBusquedaApunteBean parametrosBean = new ParametrosBusquedaApunteBean();
		parametrosBean.setCuentaBean(cuentaBean);
		parametrosBean.setFiltroTipoCuenta(movimientoSeleccionado
				.getCodigoCuenta());
		parametrosBean.setApunteSeleccionado(movimientoSeleccionado);

		obtenerFlash().put(ParametrosFlashEnum.MOVIMIENTO_BEAN.getParamFlash(),
				movimientoSeleccionado);
		obtenerFlash().put(
				ParametrosFlashEnum.LIQUIDACION_BEAN.getParamFlash(),
				liquidacionSeleccionada);
		obtenerFlash()
				.put(ParametrosFlashEnum.PARAMETROS_BUSQUEDA_APUNTE_BEAN
						.getParamFlash(),
						parametrosBean);
	}

	/**
	 * Método para navegar a la pantalla de búsqueda inicial
	 * 
	 * @return String representando la pagina a navegar
	 */
	public String navegaConsultaMasiva() {
		liquidacionSeleccionada
				.setListaConceptos(new ArrayList<LiquidacionConceptoBean>());
		liquidacionSeleccionada
				.setListaInformacion(new ArrayList<LiquidacionInformacionBean>());
		liquidacionSeleccionada
				.setListaMovimientos(new ArrayList<MovimientoBean>());
		liquidacionSeleccionada
				.setListaSaldos(new ArrayList<LiquidacionSaldoBean>());
		liquidacionSeleccionada.setNumerosBean(new LiquidacionNumerosBean());
		liquidacionSeleccionada
				.setNumerosDemoraBean(new LiquidacionNumerosBean());
		obtenerFlash().put(
				ParametrosFlashEnum.LIQUIDACION_BEAN.getParamFlash(),
				liquidacionSeleccionada);
		obtenerFlash().put(ParametrosFlashEnum.CUENTA_BEAN.getParamFlash(),
				cuentaBean);
		managedBeanStateRecover.enviaControllerMap(destinoController, destino);
		return NavegacionEnum.CONSULTA_LIQUIDACIONES.getRuta();
	}

	/**
	 * Método para navegar a la pantalla de detalle intermedio de la liquidación
	 * 
	 * @return String representando la pagina a navegar
	 */
	public String navegaDetalleLiquidacionCondicional() {
		obtenerFlash().put(
				ParametrosFlashEnum.LIQUIDACION_BEAN.getParamFlash(),
				liquidacionSeleccionada);
		obtenerFlash().put(ParametrosFlashEnum.CUENTA_BEAN.getParamFlash(),
				cuentaBean);
		managedBeanStateRecover.enviaControllerMap(destinoController, destino);
		if (liquidacionSeleccionada.getConceptoSeleccionado()
				.getListaConceptosIntermedios() != null
				&& !liquidacionSeleccionada.getConceptoSeleccionado()
						.getListaConceptosIntermedios().isEmpty()
				&& liquidacionSeleccionada.getConceptoSeleccionado()
						.getListaConceptosIntermedios().size() != 1) {
			if (obtenerFlash().get(SIMULACION_LIQUIDACION) != null) {
				if ((boolean) obtenerFlash().get(SIMULACION_LIQUIDACION)) {
					obtenerFlash().put(SIMULACION_LIQUIDACION, true);
				}
			}
			return NavegacionEnum.DETALLE_AMP_INT_LIQUIDACION.getRuta();
		} else {
			if (obtenerFlash().get(SIMULACION_LIQUIDACION) != null) {
				if ((boolean) obtenerFlash().get(SIMULACION_LIQUIDACION)) {
					return NavegacionEnum.DETALLE_SIMULAR_LIQUIDACION.getRuta();
				} else {
					return NavegacionEnum.DETALLE_LIQUIDACION.getRuta();
				}
			} else {
				throw new NoControlableException("Error de navegación",
						"No se tiene el objeto flash para decidir la redirección.");
			}
		}
	}

	/**
	 * Método para navegar a la pantalla de detalle de la liquidación
	 * 
	 * @return String representando la pagina a navegar
	 */
	public String navegaDetalleLiquidacion() {
		obtenerFlash().put(
				ParametrosFlashEnum.LIQUIDACION_BEAN.getParamFlash(),
				liquidacionSeleccionada);
		obtenerFlash().put(ParametrosFlashEnum.CUENTA_BEAN.getParamFlash(),
				cuentaBean);
		managedBeanStateRecover.enviaControllerMap(destinoController, destino);
		if (obtenerFlash().get(SIMULACION_LIQUIDACION) != null) {
			if ((boolean) obtenerFlash().get(SIMULACION_LIQUIDACION)) {
				return NavegacionEnum.DETALLE_SIMULAR_LIQUIDACION.getRuta();
			} else {
				return NavegacionEnum.DETALLE_LIQUIDACION.getRuta();
			}
		} else {
			return NavegacionEnum.DETALLE_LIQUIDACION.getRuta();
		}
	}

	/**
	 * Método para consultar la descripción de una operación de liquidación
	 * 
	 * @param clave
	 *            id para consultar en el catálogo
	 * @return String con la descripción larga del catálogo
	 */
	public String obtenerDescCatalogoTipoLiq(String clave) {
		try {
			return catalogos.getCatalogoBean(CatalogoEnum.TP_OPER_LIQ, clave)
					.getDescripcionL();
		} catch (ControlableException | NullPointerException e) {
			LOGGER.debug(
					"Error al intentar obtener la descripcion del tipo de liquidacióm a partir del codigo: "
							+ clave, e);
			return "";
		}
	}

	/**
	 * Método para consultar la descripción de un código de bloqueo
	 * 
	 * @param clave
	 *            id para consultar en el catálogo
	 * @return String con la descripción larga del catálogo
	 */
	public String obtenerDescCatalogoTipoBloqueo(String clave) {
		try {
			return catalogos.getCatalogoBean(CatalogoEnum.TP_BLOQUEO, clave)
					.getDescripcionL();
		} catch (ControlableException | NullPointerException e) {
			LOGGER.debug(
					"Error al intentar obtener la descripcion del tipo de bloqueo a partir del codigo: "
							+ clave, e);
			return "";
		}
	}

	/**
	 * Método para consultar la descripción de una transacción de auditoria
	 * 
	 * @param clave
	 *            id para consultar en el catálogo
	 * @return String con la descripción larga del catálogo
	 */
	public String obtenerDescCatalogoCodTXAudit(String clave) {
		try {
			return catalogos.getCatalogoBean(CatalogoEnum.TP_TX, clave)
					.getDescripcionL();
		} catch (ControlableException | NullPointerException e) {
			LOGGER.debug(
					"Error al intentar obtener la descripcion de la transacción de auditoria a partir del codigo: "
							+ clave, e);
			return "";
		}
	}

	/**
	 * Método para consultar la descripción de la situación de una liquidación
	 * 
	 * @param clave
	 *            id para consultar en el catálogo
	 * @return String con la descripción larga del catálogo
	 */
	public String obtenerDescCatalogoSituacionLiq(String clave) {
		try {
			return catalogos.getCatalogoBean(
					CatalogoEnum.SITUACION_LIQUIDACION, clave)
					.getDescripcionL();
		} catch (ControlableException | NullPointerException e) {
			LOGGER.debug(
					"Error al intentar obtener la descripcion de la situación de la liquidación a partir del codigo: "
							+ clave, e);
			return "";
		}
	}

	/**
	 * Método para consultar la descripción de la situación de la auditoria una
	 * liquidación
	 * 
	 * @param clave
	 *            id para consultar en el catálogo
	 * @return String con la descripción larga del catálogo
	 */
	public String obtenerDescCatalogoSituacionAudit(String clave) {
		try {
			return catalogos.getCatalogoBean(CatalogoEnum.TP_MODIF_HL, clave)
					.getDescripcionL();
		} catch (ControlableException | NullPointerException e) {
			LOGGER.debug(
					"Error al intentar obtener la descripcion de la situación de la auditoria de liquidación a partir del codigo: "
							+ clave, e);
			return "";
		}
	}

	/**
	 * Función para obtener la descripcion del nombre del centro.
	 * 
	 * @return la descripcion del centro
	 */
	public String obtenerDescCatalogoCentro(String clave) {
		try {
			return catalogoCentros.getCatalogoBean(contextoUtils.getEntidad(),
					clave).getDescripcionL();
		} catch (ControlableException ce) {
			LOGGER.debug(
					"Error al intentar obtener la descripcion del centro a partir del codigo: "
							+ clave, ce);
			return "";
		}
	}

	/**
	 * Función para obtener la descripcion del nombre del centro.
	 * 
	 * @return la descripcion del centro
	 */
	public String obtenerDescCatalogoPorcentajeMovs(final String clave) {
		try {
			return catalogos.getCatalogoBean(CatalogoEnum.TP_HL_RL_AF, clave)
					.getDescripcionL();
		} catch (ControlableException | NullPointerException e) {
			LOGGER.debug(
					"Error al intentar obtener la descripcion del % abono del movimiento a partir del codigo: "
							+ clave, e);
			return "";
		}
	}

	/**
	 * Función para obtener la descripcion del nombre del centro.
	 * 
	 * @return la descripcion del centro
	 */
	public String obtenerDescCatalogoConceptoMovs(final String clave) {
		try {
			return catalogos.getCatalogoBean(CatalogoEnum.TP_CTA, clave)
					.getDescripcionL();
		} catch (ControlableException | NullPointerException e) {
			LOGGER.debug(
					"Error al intentar obtener la descripcion del saldo del movimiento a partir del codigo: "
							+ clave, e);
			return "";
		}
	}

	/**
	 * Función para obtener la descripcion del nombre del centro.
	 * 
	 * @return la descripcion del centro
	 */
	public String obtenerDescCatalogoSaldo(final LiquidacionSaldoBean saldo) {
		String clave = "";
		try {
			clave = saldo.getCodCta().concat(
					saldo.getCodSaldo().concat(saldo.getIndSaldo()));
			return catalogos.getCatalogoBean(
					CatalogoEnum.DESCRIPCION_SALDO_LIQUIDACION, clave)
					.getDescripcionL();
		} catch (ControlableException | NullPointerException e) {
			try {
				clave = clave.concat(liquidacionSeleccionada.getIndDevengo());
				return catalogos.getCatalogoBean(
						CatalogoEnum.DESCRIPCION_SALDO_LIQUIDACION, clave)
						.getDescripcionL();
			} catch (ControlableException | NullPointerException e1) {
				LOGGER.debug(
						"Error al intentar obtener la descripcion del concepto del movimiento a partir del codigo: "
								+ clave, e1);
				return "";
			}
		}
	}

	/**
	 * Función para obtener la descripcion del tipo de comunicado de la
	 * liquidacion.
	 * 
	 * @return la descripcion del centro
	 */
	public String obtenerDescCatalogoTipoComunicado(final String clave) {
		try {
			return catalogos.getCatalogoBean(
					CatalogoEnum.TIPO_COMUNICADO_LIQUIDACION, clave)
					.getDescripcionL();
		} catch (ControlableException | NullPointerException e) {
			LOGGER.debug(
					"Error al intentar obtener la descripcion del saldo del movimiento a partir del codigo: "
							+ clave, e);
			return "";
		}
	}

	/**
	 * Función para obtener la descripcion del tipo de la modalidad de la
	 * liquidacion.
	 * 
	 * @return la descripcion del centro
	 */
	public String obtenerDescCatalogoModalidad(final String clave) {
		try {
			return catalogos.getCatalogoBean(
					CatalogoEnum.MODALIDAD_LIQUIDACION, clave)
					.getDescripcionL();
		} catch (ControlableException | NullPointerException e) {
			LOGGER.debug(
					"Error al intentar obtener la descripcion del saldo del movimiento a partir del codigo: "
							+ clave, e);
			return "";
		}
	}

	/**
	 * Método que devuelve la descripción asociada al tipo de identificación del
	 * cliente.
	 * 
	 * @param clave
	 * @return la descripción del tipo de identificación
	 */
	public String obtenerDescCatalogoTipoId(final String clave) {
		try {
			return catalogos
					.getCatalogoBean(CatalogoEnum.TP_ID_EXT_PERS, clave)
					.getDescripcionL();
		} catch (ControlableException | NullPointerException e) {
			LOGGER.debug(
					"Error al intentar obtener la descripcion del tipo de identificación a partir del codigo: "
							+ clave, e);
			return "";
		}
	}

	/**
	 * @return Metodo utilizado para volver a la ficha de cuenta
	 */
	public String volver() {
		obtenerFlash().put(ParametrosFlashEnum.CUENTA_BEAN.getParamFlash(),
				this.cuentaBean);
		managedBeanStateRecover.finNavegacion(destinoController);
		return destino.getRuta();
	}

	/**
	 * Método privado que a partir de la liquidación seleccionada verifica sí se
	 * deben mostrar las secciones de números de liquidación y de demora.
	 * 
	 * @author omar.marquez
	 */
	private void cargarSeccionNumerosLiquidacion() {
		// Condición para consultar números de una liquidación.
		if (liquidacionSeleccionada.getCodLinea().equals(COD_LINEA_03)
				&& liquidacionSeleccionada.getIdGrpPd().equals(ID_GPO_PROD_51)) {
			liquidacionSeleccionada.setMostrarConsNumsLiquidacion(false);
		} else {
			liquidacionSeleccionada.setMostrarConsNumsLiquidacion(true);
		}
		// Condición para consultar números de demora.
		if (liquidacionSeleccionada.getListaConceptos() != null
				&& liquidacionSeleccionada.getListaConceptos().size() > 0) {
			for (LiquidacionConceptoBean concepto : liquidacionSeleccionada
					.getListaConceptos()) {
				if (concepto.getCodCuenta().equals(COD_CTA_09)) {
					liquidacionSeleccionada.setMostrarConsNumsDemora(true);
					break;
				} else {
					liquidacionSeleccionada.setMostrarConsNumsDemora(false);
				}
			}
		} else {
			liquidacionSeleccionada.setMostrarConsNumsDemora(false);
		}
	}
	
	public String getCurrentDate() {
		return new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime());
	}
	
}