package mx.babel.bansefi.banksystem.cuentas.controllers;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.backends.ConsultaCatalogeBackend;
import mx.babel.bansefi.banksystem.base.backends.consultasaldo.ConsultaSaldoBackEnd;
import mx.babel.bansefi.banksystem.base.beans.ApunteChequeBean;
import mx.babel.bansefi.banksystem.base.beans.AuditoriaBean;
import mx.babel.bansefi.banksystem.base.beans.ChequeBancarioBean;
import mx.babel.bansefi.banksystem.base.beans.DatosAmpliadosBean;
import mx.babel.bansefi.banksystem.base.beans.LiquidacionBean;
import mx.babel.bansefi.banksystem.base.beans.ParametrosBusquedaApunteBean;
import mx.babel.bansefi.banksystem.base.beans.domain.CuentaBean;
import mx.babel.bansefi.banksystem.base.beans.domain.SaldoBean;
import mx.babel.bansefi.banksystem.base.beans.movimientos.MovimientoBean;
import mx.babel.bansefi.banksystem.base.constants.ConstantesFuncionales;
import mx.babel.bansefi.banksystem.base.enums.CatalogoEnum;
import mx.babel.bansefi.banksystem.base.enums.NavegacionEnum;
import mx.babel.bansefi.banksystem.base.enums.ParametrosFlashEnum;
import mx.babel.bansefi.banksystem.base.utils.CatalogoCentrosLoaderTask;
import mx.babel.bansefi.banksystem.base.utils.CatalogoUtils;
import mx.babel.bansefi.banksystem.base.utils.IntegerToDateConverter;
import mx.babel.bansefi.banksystem.base.utils.ManagedBeanStateRecover;
import mx.babel.bansefi.banksystem.base.utils.SelectItemStringValueComparator;
import mx.babel.bansefi.banksystem.base.utils.StringToDateConverter;
import mx.babel.bansefi.banksystem.cuentas.backends.ConsultaAmpliadaApunteBackEnd;
import mx.babel.bansefi.banksystem.cuentas.backends.ConsultaApuntePorChequeBackEnd;
import mx.babel.bansefi.banksystem.cuentas.backends.ConsultaApuntePorNaturalezaBackend;
import mx.babel.bansefi.banksystem.cuentas.backends.ConsultaAuditoriaInfoLiquidacionBackend;
import mx.babel.bansefi.banksystem.cuentas.backends.ConsultaAuditoriaLiquidacionBackend;
import mx.babel.bansefi.banksystem.cuentas.backends.ConsultaBasicaLiquidacionBackend;
import mx.babel.bansefi.banksystem.cuentas.backends.ConsultaChequeBancarioBackend;
import mx.babel.bansefi.banksystem.cuentas.backends.ConsultaConceptoApunteBackend;
import mx.babel.bansefi.banksystem.cuentas.backends.ConsultaDetalleApunteBackEnd;
import mx.babel.bansefi.banksystem.cuentas.backends.ConsultaGeneralApunteBackEnd;
import mx.babel.bansefi.banksystem.cuentas.backends.ConsultaTraspasoTFBackEnd;
import mx.babel.bansefi.banksystem.cuentas.backends.DescConceptoLiquidacionTRDBackend;
import mx.babel.bansefi.banksystem.cuentas.backends.TablaIndicadoresApunteTRDBackend;
import mx.babel.bansefi.banksystem.cuentas.beans.ConceptoApunteBean;
import mx.babel.bansefi.banksystem.cuentas.beans.SaldoCuentaBean;
import mx.babel.bansefi.banksystem.cuentas.beans.TraspasoTFBean;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.DateTimeComparator;
import org.primefaces.component.tabview.Tab;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.TabChangeEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Controlador encargado de la búsqueda y detalle de movimientos / apuntes.
 * 
 * @author omar.marquez
 * 
 */
@ManagedBean
@ViewScoped
@Component
@Scope("view")
public class ApunteController implements Serializable {

	private static final long serialVersionUID = 7208142909159215189L;
	private static final Logger LOGGER = LogManager
			.getLogger(ApunteController.class);

	// Constante para indicar que el contenido de un catálogo es vacío.
	private static final String IND_CONTENIDO_VACIO = "0";

	// Constante que representa la longitud de la naturaleza de una cuenta.
	private static final int LONGITUD_NATURALEZA = 4;

	// Constante para indicar que el origen del detalle es una liquidación.
	private static final int ORIGEN_LIQUIDACION = 1;

	// Constantes para determinar el origen del apunte.
	private static final String HL = "HL"; // Liquidación
	private static final String AC = "AC"; // Acuerdo
	private static final String AO = "AO"; // Aportación
	private static final String ER = "ER"; // Exp Reclam
	private static final String CP = "CP"; // Consulta de cheques
	private static final String TF = "TF"; // Traspaso
	private static final String CJ = "CJ"; // Mantenimiento de Cheque Bancario

	// Constante para indicar que no hay más información que mostrar.
	private static final int NO_HAY_MAS_DATOS = 7;

	private ParametrosBusquedaApunteBean parametrosBean;
	private List<MovimientoBean> apuntes;
	private MovimientoBean apunteSeleccionado;
	private CuentaBean cuentaBean;
	private String filtroNaturaleza;
	private String filtroTipoCuenta;
	private Date filtroFechaDesde;
	private Date filtroFechaHasta;
	private boolean panelResultadosDisponible;
	private CatalogoBean catalogoBean;
	private Date fechaDesdeMinima;
	private Map<String, String> mapaNaturalezas;
	private List<SelectItem> naturalezas;
	private List<SaldoCuentaBean> listaSaldos;
	private LiquidacionBean liquidacionSeleccionada;
	private ChequeBancarioBean chequeBancarioBean;
	private ApunteChequeBean apunteChequeBean;
	private SaldoCuentaBean saldoCuentaBean;

	// Variables para controlar la paginación.
	private boolean masDatos;
	private int pagina;

	// Variables para el flujo y retorno de datos.
	private NavegacionEnum navegacionEnumOrigen;
	private Object controladorOrigen;

	// Variables para controlar la ejecución de TRN's de detalle.
	private boolean indConsultaAuditoria;
	private boolean indConsultaAmpliada;
	private boolean indConsultaOrigen;

	// Variables para controlar el código de retorno 7 (no existen datos).
	private int returnCodDetalle;
	private int returnCodAuditoria;
	private int returnCodAmpliada;
	private int returnCodOrigen;

	// Indicador para determinar sí se debe ocultar la fecha de presentación.
	private boolean fechaPresentacionOculta;

	// Consultas origen
	private TraspasoTFBean traspasoTFBean;

	@Autowired
	ContextoUtils contextoUtils;

	@Autowired
	CatalogoUtils catalogoUtils;

	@Autowired
	CatalogoCentrosLoaderTask catalogoCentrosLoaderTask;

	@Autowired
	ManagedBeanStateRecover managedBeanStateRecover;

	@Autowired
	ConsultaGeneralApunteBackEnd consultaGeneralApunteBackEnd;

	@Autowired
	ConsultaApuntePorNaturalezaBackend consultaApuntePorNaturalezaBackend;

	@Autowired
	ConsultaDetalleApunteBackEnd consultaDetalleApunteBackEnd;

	@Autowired
	TablaIndicadoresApunteTRDBackend tablaIndicadoresApunteTRDBackend;

	@Autowired
	ConsultaAuditoriaLiquidacionBackend consultaAuditoriaLiquidacionBackend;

	@Autowired
	ConsultaAuditoriaInfoLiquidacionBackend consultaAuditoriaInfoLiquidacionBackend;

	@Autowired
	ConsultaAmpliadaApunteBackEnd consultaAmpliadaApunteBackEnd;

	@Autowired
	ConsultaBasicaLiquidacionBackend consultaBasicaLiquidacionBackend;


	@Autowired
	DescConceptoLiquidacionTRDBackend descConceptoLiquidacionBackend;

	@Autowired
	ConsultaConceptoApunteBackend consultaConceptoApunteBackend;

	@Autowired
	ConsultaApuntePorChequeBackEnd consultaApuntePorChequeBackEnd;

	@Autowired
	ConsultaTraspasoTFBackEnd consultaTraspasoTFBackEnd;

	@Autowired
	ConsultaChequeBancarioBackend consultaChequeBancarioBackend;


	@Autowired
	ConsultaCatalogeBackend catalogeBackend;

	@Autowired
	ConsultaSaldoBackEnd consultaSaldoBackEnd;

	/**
	 * Constructor.
	 */
	public ApunteController() {
		this.parametrosBean = null;
		this.apuntes = new ArrayList<>();
		this.apunteSeleccionado = null;
		this.cuentaBean = null;
		this.filtroNaturaleza = null;
		this.filtroTipoCuenta = null;
		this.filtroFechaDesde = null;
		this.filtroFechaHasta = null;
		this.panelResultadosDisponible = false;
		this.catalogoBean = new CatalogoBean();
		this.fechaDesdeMinima = this.obtenerFechaDesdeMinima();
		this.mapaNaturalezas = null;
		this.naturalezas = new ArrayList<>();
		this.chequeBancarioBean = new ChequeBancarioBean();
		this.masDatos = false;
		this.pagina = 0;
		this.indConsultaAuditoria = false;
		this.indConsultaAmpliada = false;
		this.indConsultaOrigen = false;
		this.returnCodDetalle = -1;
		this.returnCodAuditoria = -1;
		this.returnCodAmpliada = -1;
		this.returnCodOrigen = -1;
		this.saldoCuentaBean = new SaldoCuentaBean();
	}

	@PostConstruct
	public void init() {
		if (!FacesContext.getCurrentInstance().isPostback()) {
			if (this.obtenerFlash()
					.get(ParametrosFlashEnum.NAVEGACION_MANAGED_BEAN
							.getParamFlash()) != null) {
				if ((Boolean) this.obtenerFlash().get(
						ParametrosFlashEnum.NAVEGACION_MANAGED_BEAN
								.getParamFlash())) {
					this.navegacionEnumOrigen = this.managedBeanStateRecover
							.getDestino();
					this.controladorOrigen = this.managedBeanStateRecover
							.getController();
					this.initData();
				} else {
					this.managedBeanStateRecover.recuperaController(this);
				}
			} else {
				this.initData();
			}
		}
	}

	// INICIA DECLARACIÓN DE GETTERS Y SETTERS.

	/**
	 * Método que devuelve un objeto tipo ParametrosBusquedaApunteBean.
	 * 
	 * @return parametrosBean
	 */
	public ParametrosBusquedaApunteBean getParametrosBean() {
		return parametrosBean;
	}

	/**
	 * Método que establece un objeto tipo ParametrosBusquedaApunteBean.
	 * 
	 * @param parametrosBean
	 */
	public void setParametrosBean(ParametrosBusquedaApunteBean parametrosBean) {
		this.parametrosBean = parametrosBean;
	}

	/**
	 * Método que devuelve una lista de apuntes / movimientos.
	 * 
	 * @return apuntes
	 */
	public List<MovimientoBean> getApuntes() {
		return apuntes;
	}

	/**
	 * Método que establece una lista de apuntes / movimientos.
	 * 
	 * @param apuntes
	 */
	public void setApuntes(List<MovimientoBean> apuntes) {
		this.apuntes = apuntes;
	}

	/**
	 * Método que devuelve un objeto tipo MovimientoBean de acuerdo a la
	 * selección del usuario.
	 * 
	 * @return apunteSeleccionado
	 */
	public MovimientoBean getApunteSeleccionado() {
		return apunteSeleccionado;
	}

	/**
	 * Método que establece un objeto tipo MovimientoBean de acuerdo a la
	 * selección del usuario.
	 * 
	 * @param apunteSeleccionado
	 */
	public void setApunteSeleccionado(MovimientoBean apunteSeleccionado) {
		this.apunteSeleccionado = apunteSeleccionado;
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
	 * Método que devuelve la clave de la naturaleza de la cuenta.
	 * 
	 * @return filtroNaturaleza
	 */
	public String getFiltroNaturaleza() {
		return filtroNaturaleza;
	}

	/**
	 * Método que establece la clave de la naturaleza de la cuenta.
	 * 
	 * @param filtroNaturaleza
	 */
	public void setFiltroNaturaleza(String filtroNaturaleza) {
		this.filtroNaturaleza = filtroNaturaleza;
	}

	/**
	 * Método que devuelve la clave con el tipo de cuenta seleccionada
	 * (cboTipoCuenta referenciado en la vista).
	 * 
	 * @return filtroTipoCuenta
	 */
	public String getFiltroTipoCuenta() {
		return filtroTipoCuenta;
	}

	/**
	 * Método que establece la clave con el tipo de cuenta seleccionada.
	 * 
	 * @param filtroTipoCuenta
	 */
	public void setFiltroTipoCuenta(String filtroTipoCuenta) {
		this.filtroTipoCuenta = filtroTipoCuenta;
	}

	/**
	 * Método que devuelve la fecha de inicio para la búsqueda
	 * (calFiltroFechaDesde referenciado en la vista).
	 * 
	 * @return filtroFechaDesde
	 */
	public Date getFiltroFechaDesde() {
		return filtroFechaDesde;
	}

	/**
	 * Método que establece la fecha de inicio para la búsqueda.
	 * 
	 * @param filtroFechaDesde
	 */
	public void setFiltroFechaDesde(Date filtroFechaDesde) {
		this.filtroFechaDesde = filtroFechaDesde;
	}

	/**
	 * Método que devuelve la fecha de término para la búsqueda
	 * (calFiltroFechaHasta referenciado en la vista).
	 * 
	 * @return filtroFechaHasta
	 */
	public Date getFiltroFechaHasta() {
		return filtroFechaHasta;
	}

	/**
	 * Método que establece la fecha de término para la búsqueda.
	 * 
	 * @param filtroFechaHasta
	 */
	public void setFiltroFechaHasta(Date filtroFechaHasta) {
		this.filtroFechaHasta = filtroFechaHasta;
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
	 * Método que devuelve un objeto tipo ChequeBancarioBean.
	 * 
	 * @return chequeBancarioBean
	 */
	public ChequeBancarioBean getChequeBancarioBean() {
		return chequeBancarioBean;
	}

	/**
	 * Método que establece un objeto tipo ChequeBancarioBean.
	 * 
	 * @param chequeBancarioBean
	 */
	public void setChequeBancarioBean(ChequeBancarioBean chequeBancarioBean) {
		this.chequeBancarioBean = chequeBancarioBean;
	}

	/**
	 * @return Atributo apunteChequeBean
	 */
	public ApunteChequeBean getApunteChequeBean() {
		return apunteChequeBean;
	}

	/**
	 * @param apunteChequeBean
	 *            Atributo apunteChequeBean a definir
	 */
	public void setApunteChequeBean(ApunteChequeBean apunteChequeBean) {
		this.apunteChequeBean = apunteChequeBean;
	}

	/**
	 * @return Atributo saldoCuentaBean
	 */
	public SaldoCuentaBean getSaldoCuentaBean() {
		return saldoCuentaBean;
	}

	/**
	 * @param saldoCuentaBean
	 *            Atributo saldoCuentaBean a definir
	 */
	public void setSaldoCuentaBean(SaldoCuentaBean saldoCuentaBean) {
		this.saldoCuentaBean = saldoCuentaBean;
	}

	/**
	 * Método que devuelve una lista de elementos para cargar la lista
	 * desplegable de naturalezas que se encuentra en busquedaApunte2.xhtml
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

	/**
	 * Método que devuelve un indicador booleano para determinar sí la TRN de
	 * consulta auditoría de apuntes fue ejecutada. Este método devuelve true sí
	 * ya se ejecutó la TRN y false en cualquier otro caso.
	 * 
	 * @return indicador booleano (indConsultaAuditoria)
	 */
	public boolean isIndConsultaAuditoria() {
		return indConsultaAuditoria;
	}

	/**
	 * Método que establece el valor del indicador para determinar sí una TRN ha
	 * sido ejecutada o no.
	 * 
	 * @param indConsultaAuditoria
	 */
	public void setIndConsultaAuditoria(boolean indConsultaAuditoria) {
		this.indConsultaAuditoria = indConsultaAuditoria;
	}

	/**
	 * Método que devuelve un indicador booleano para determinar sí la TRN de
	 * consulta ampliada de apuntes fue ejecutada. Este método devuelve true sí
	 * ya se ejecutó la TRN y false en cualquier otro caso.
	 * 
	 * @return indicador booleano (indConsultaAmpliada)
	 */
	public boolean isIndConsultaAmpliada() {
		return indConsultaAmpliada;
	}

	/**
	 * Método que establece el valor del indicador para determinar sí una TRN ha
	 * sido ejecutada o no.
	 * 
	 * @param indConsultaAmpliada
	 */
	public void setIndConsultaAmpliada(boolean indConsultaAmpliada) {
		this.indConsultaAmpliada = indConsultaAmpliada;
	}

	/**
	 * Método que devuelve un indicador booleano para determinar sí la TRN de
	 * consulta de originación de apuntes fue ejecutada. Este método devuelve
	 * true sí ya se ejecutó la TRN y false en cualquier otro caso.
	 * 
	 * @return indicador booleano (indConsultaOrigen)
	 */
	public boolean isIndConsultaOrigen() {
		return indConsultaOrigen;
	}

	/**
	 * Método que devuelve el código de retorno cuando ocurre una
	 * ControlableException.
	 * 
	 * @return código de retorno consulta detalle apunte
	 */
	public int getReturnCodDetalle() {
		return returnCodDetalle;
	}

	/**
	 * Método que establece el código de retorno cuando ocurre una
	 * ControlableException.
	 * 
	 * @param returnCodDetalle
	 */
	public void setReturnCodDetalle(int returnCodDetalle) {
		this.returnCodDetalle = returnCodDetalle;
	}

	/**
	 * Método que devuelve el código de retorno cuando ocurre una
	 * ControlableException.
	 * 
	 * @return código de retorno consulta auditoría de apunte
	 */
	public int getReturnCodAuditoria() {
		return returnCodAuditoria;
	}

	/**
	 * Método que establece el código de retorno cuando ocurre una
	 * ControlableException.
	 * 
	 * @param returnCodAuditoria
	 */
	public void setReturnCodAuditoria(int returnCodAuditoria) {
		this.returnCodAuditoria = returnCodAuditoria;
	}

	/**
	 * Método que devuelve el código de retorno cuando ocurre una
	 * ControlableException.
	 * 
	 * @return código de retorno consulta ampliada de apunte
	 */
	public int getReturnCodAmpliada() {
		return returnCodAmpliada;
	}

	/**
	 * Método que establece el código de retorno cuando ocurre una
	 * ControlableException.
	 * 
	 * @param returnCodAmpliada
	 */
	public void setReturnCodAmpliada(int returnCodAmpliada) {
		this.returnCodAmpliada = returnCodAmpliada;
	}

	/**
	 * Método que devuelve el código de retorno cuando ocurre una
	 * ControlableException.
	 * 
	 * @return código de retorno consulta origen apunte
	 */
	public int getReturnCodOrigen() {
		return returnCodOrigen;
	}

	/**
	 * Método que establece el código de retorno cuando ocurre una
	 * ControlableException.
	 * 
	 * @param returnCodOrigen
	 */
	public void setReturnCodOrigen(int returnCodOrigen) {
		this.returnCodOrigen = returnCodOrigen;
	}

	/**
	 * Método que establece el valor del indicador para determinar sí una TRN ha
	 * sido ejecutada o no.
	 * 
	 * @param indConsultaOrigen
	 */
	public void setIndConsultaOrigen(boolean indConsultaOrigen) {
		this.indConsultaOrigen = indConsultaOrigen;
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
	 * Método que devuelve un indicador booleano para determinar sí se debe
	 * mostrar o no la fecha de presentación de un cheque bancario (CJ).
	 * 
	 * @return indicador booleano
	 */
	public boolean isFechaPresentacionOculta() {
		return fechaPresentacionOculta;
	}

	/**
	 * Método que establece un indicador booleano para determinar sí se debe
	 * mostrar o no la fecha de presentación de un cheque bancario (CJ).
	 * 
	 * @param fechaPresentacionOculta
	 */
	public void setFechaPresentacionOculta(boolean fechaPresentacionOculta) {
		this.fechaPresentacionOculta = fechaPresentacionOculta;
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

	public TraspasoTFBean getTraspasoTFBean() {
		return traspasoTFBean;
	}

	public void setTraspasoTFBean(TraspasoTFBean traspasoTFBean) {
		this.traspasoTFBean = traspasoTFBean;
	}

	/**
	 * Método privado que inicializa la carga de datos.
	 */
	private void initData() {
		this.loadParametresFromFlash();
		if (this.cuentaBean == null && this.parametrosBean != null) {
			LOGGER.debug("Usuario pretende navegar al detalle del apunte.");
			this.obtenerParametrosBusqueda();
		} else if (this.cuentaBean != null && this.mapaNaturalezas != null) {
			LOGGER.debug("Usuario pretende navegar a la búsqueda de apuntes por naturaleza.");
			this.construirListaNaturalezas();
		} else if (this.cuentaBean != null && this.parametrosBean == null) {
			LOGGER.debug("Usuario pretende navegar a la búsqueda de apuntes por tipo de cuenta.");
		} else {
			throw new NoControlableException(
					"Error al intentar recuperar la información de la cuenta / apunte.",
					this.getClass().getName()
							+ ": Se requieren más datos para efectuar la consulta.");
		}
	}

	/**
	 * Método privado que carga los parámetros almacenados en la Flash.
	 */
	@SuppressWarnings("unchecked")
	private void loadParametresFromFlash() {
		if (this.obtenerFlash().get(
				ParametrosFlashEnum.CUENTA_BEAN.getParamFlash()) != null) {
			this.cuentaBean = (CuentaBean) this.obtenerFlash().get(
					ParametrosFlashEnum.CUENTA_BEAN.getParamFlash());
		}
		if (this.obtenerFlash().get(
				ParametrosFlashEnum.PARAMETROS_BUSQUEDA_APUNTE_BEAN
						.getParamFlash()) != null) {
			this.parametrosBean = (ParametrosBusquedaApunteBean) this
					.obtenerFlash().get(
							ParametrosFlashEnum.PARAMETROS_BUSQUEDA_APUNTE_BEAN
									.getParamFlash());
		}
		if (this.obtenerFlash().get(
				ParametrosFlashEnum.MAPA_NATURALEZAS_CUENTA.getParamFlash()) != null) {
			this.mapaNaturalezas = (HashMap<String, String>) this
					.obtenerFlash().get(
							ParametrosFlashEnum.MAPA_NATURALEZAS_CUENTA
									.getParamFlash());
		}
		if (this.obtenerFlash().get(
				ParametrosFlashEnum.LISTA_SALDOS.getParamFlash()) != null) {
			this.listaSaldos = (List<SaldoCuentaBean>) this.obtenerFlash().get(
					ParametrosFlashEnum.LISTA_SALDOS.getParamFlash());
		}
		if (this.obtenerFlash().get(
				ParametrosFlashEnum.LIQUIDACION_BEAN.getParamFlash()) != null) {
			this.liquidacionSeleccionada = (LiquidacionBean) this
					.obtenerFlash().get(
							ParametrosFlashEnum.LIQUIDACION_BEAN
									.getParamFlash());
		}
		if (this.obtenerFlash().get("saldoSeleccionado") != null) {
			this.saldoCuentaBean = (SaldoCuentaBean) this.obtenerFlash().get(
					"saldoSeleccionado");
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
		this.filtroNaturaleza = this.saldoCuentaBean.getCodigoCuenta() + ""
				+ this.saldoCuentaBean.getCodigoSaldo();
		this.buscarPorNaturaleza();
	}

	/**
	 * Método privado que obtiene todos los parámetros de la búsqueda (entradas
	 * y salidas).
	 */
	private void obtenerParametrosBusqueda() {
		this.cuentaBean = new CuentaBean();
		this.cuentaBean = this.parametrosBean.getCuentaBean();
		this.filtroNaturaleza = this.parametrosBean.getFiltroNaturaleza();
		this.filtroTipoCuenta = this.parametrosBean.getFiltroTipoCuenta();
		this.filtroFechaDesde = this.parametrosBean.getFiltroFechaDesde();
		this.filtroFechaHasta = this.parametrosBean.getFiltroFechaHasta();
		this.apuntes = this.parametrosBean.getApuntes();
		this.apunteSeleccionado = this.parametrosBean.getApunteSeleccionado();
		this.masDatos = this.parametrosBean.isMasDatos();
		this.pagina = this.parametrosBean.getPagina();
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
	 * Método que devuelve la descripción asociada a la naturaleza de la cuenta.
	 * 
	 * @return descripción larga naturaleza de la cuenta
	 */
	public String obtenerDescNaturaleza() {
		try {
			catalogoBean = catalogoUtils.getCatalogoBean(CatalogoEnum.TP_SDO,
					filtroNaturaleza);
			return catalogoBean.getDescripcionL();
		} catch (ControlableException | NullPointerException e) {
			return new String();
		}
	}

	/**
	 * Método que devuelve la descripción asociada al tipo de cuenta.
	 * 
	 * @return descripción larga tipo de cuenta
	 */
	public String obtenerDescTipoCuenta() {
		try {
			if (parametrosBean != null
					&& parametrosBean.getApunteSeleccionado() != null
					&& parametrosBean.getApunteSeleccionado()
							.getApunteLiquidacion() == ORIGEN_LIQUIDACION) {
				catalogoBean = catalogoUtils.getCatalogoBean(
						CatalogoEnum.TP_CTA,
						apunteSeleccionado.getCodigoCuenta());
			} else {
				catalogoBean = catalogoUtils.getCatalogoBean(
						CatalogoEnum.TP_CTA, filtroTipoCuenta);
			}
			return catalogoBean.getDescripcionL();
		} catch (ControlableException | NullPointerException e) {
			return new String();
		}
	}

	/**
	 * Método que devuelve la descripción asociada al tipo de identificación del
	 * cliente.
	 * 
	 * @return descripción larga tipo de identificación
	 */
	public String obtenerDescTipoId() {
		try {
			catalogoBean = catalogoUtils.getCatalogoBean(
					CatalogoEnum.TP_ID_EXT_PERS, parametrosBean.getCuentaBean()
							.getTipoIdentificacionTitular());
			return catalogoBean.getDescripcionL();
		} catch (ControlableException | NullPointerException e) {
			return new String();
		}
	}

	/**
	 * Método que devuelve la descripción asociada al tipo de operación. Este
	 * método primero consulta el catálogo TP_CTA (GAE 00032) y posteriormente
	 * el catálogo que haya sido recuperado y en el último de los casos, ejecuta
	 * la TRN para obtener el concepto del apunte.
	 * 
	 * @return descripción larga tipo de operación
	 */
	public String obtenerDescTipoOperacion() {
		try {
			catalogoBean = catalogoUtils.getCatalogoBean(CatalogoEnum.TP_CTA,
					parametrosBean.getApunteSeleccionado().getCodigoCuenta());
			if (!catalogoBean.getContenido().equals(
					ApunteController.IND_CONTENIDO_VACIO)) {
				CatalogoEnum catalogoEnum = catalogoUtils
						.getCatalogoEnum(catalogoBean.getContenido());
				catalogoBean = catalogoUtils.getCatalogoBean(catalogoEnum,
						parametrosBean.getApunteSeleccionado()
								.getCodigoOrigen());
				return catalogoBean.getDescripcionL();
			} else {
				return obtenerDescDeConsulta(parametrosBean
						.getApunteSeleccionado());
			}
		} catch (ControlableException | NullPointerException e) {
			LOGGER.error("Error al obtener la descripción de la operación. "
					+ this.getClass().getName() + ":" + e.getMessage());
			return new String();
		}
	}

	/**
	 * Método privado que ejecuta la TRN de consulta a la PK
	 * (TR_NOTIF_CONS_PK_TRN). Este método se ejecuta cuando el catálogo no haya
	 * devuelto datos del apunte.
	 * 
	 * @param apunte
	 */
	private String obtenerDescDeConsulta(MovimientoBean apunte) {
		try {
			String claveOperacion = apunte.getCodigoOrigen().substring(3, 6);
			ConceptoApunteBean caBean = consultaConceptoApunteBackend
					.ejecutarTRN(claveOperacion);
			if (caBean.getNombre() != null) {
				return caBean.getNombre();
			}
			return new String();
		} catch (IndexOutOfBoundsException | NullPointerException
				| ControlableException | NoControlableException e) {
			LOGGER.error("Error al obtener el concepto del apunte desde TRN de consulta. "
					+ this.getClass().getName() + ":" + e.getMessage());
			return new String();
		}
	}

	/**
	 * Método que devuelve la descripción asociada al código de transacción.
	 * 
	 * @return descripción larga de acuerdo al código de transacción
	 */
	public String obtenerDescCodTransaccion() {
		try {
			catalogoBean = catalogoUtils.getCatalogoBean(CatalogoEnum.TP_TX,
					parametrosBean.getApunteSeleccionado().getAuditoriaBean()
							.getTransaccion());
			return catalogoBean.getDescripcionL();
		} catch (ControlableException | NullPointerException e) {
			return new String();
		}
	}

	public String obtenerDescCodTransaccion(String codInf) {
		try {
			catalogoBean = catalogoUtils.getCatalogoBean(CatalogoEnum.TP_TX,codInf);
			return catalogoBean.getDescripcionL();
		} catch (ControlableException | NullPointerException e) {
			return new String();
		}
	}

	/**
	 * Método que devuelve la descripción asociada al ID de un centro.
	 * 
	 * @param idCentro
	 * @return descripción larga
	 */
	public String obtenerDescCentro(final String idCentro) {
		try {
			return catalogoCentrosLoaderTask.getCatalogoBean(
					contextoUtils.getEntidad(), idCentro).getDescripcionL();
		} catch (ControlableException | NullPointerException e) {
			return new String();
		}
	}

	/**
	 * Método que devuelve la descripción asociada al tipo de liquidación.
	 * 
	 * @return descripción larga tipo de liquidación
	 */
	public String obtenerDescTipoLiquidacion() {
		try {
			catalogoBean = catalogoUtils.getCatalogoBean(
					CatalogoEnum.TP_OPER_LIQ, parametrosBean
							.getApunteSeleccionado().getLiquidacionBean()
							.getTipoLiquidacion());
			return catalogoBean.getDescripcionL();
		} catch (ControlableException | NullPointerException e) {
			return new String();
		}
	}

	/**
	 * Método que devuelve la descripción asociada a la situación del apunte.
	 * 
	 * @return descripción larga tipo de cuenta
	 */
	public String obtenerDescSituacionLiquidacion() {
		try {
			catalogoBean = catalogoUtils.getCatalogoBean(
					CatalogoEnum.SITUACION_LIQUIDACION, parametrosBean
							.getApunteSeleccionado().getLiquidacionBean()
							.getSituacion());
			return catalogoBean.getDescripcionL();
		} catch (ControlableException | NullPointerException e) {
			return new String();
		}
	}

	/**
	 * Método que devuelve la descripción asociada a la info adicional del
	 * detalle del apunte.
	 * 
	 * @return descripción larga tipo de cuenta
	 */
	public String obtenerInfoTranTipoPlanif(String value) {
		try {
			catalogoBean = catalogoUtils.getCatalogoBean(
					CatalogoEnum.ORIGEN_LIQUIDACION, value);


			return catalogoBean.getDescripcionL();
		} catch (ControlableException | NullPointerException e) {
			return new String();
		}
	}

	/**
	 * Método que devuelve la descripción asociada a la info adicional del
	 * detalle del apunte.
	 * 
	 * @return descripción larga tipo de cuenta
	 */
	public String obtenerDescInfoAdic(String value) {
		try {
			catalogoBean = catalogoUtils.getCatalogoBean(
					CatalogoEnum.TP_AF_INF_DRVD, value);
			return catalogoBean.getDescripcionL();
		} catch (ControlableException | NullPointerException e) {
			return new String();
		}
	}

	/**
	 * Método que devuelve la descripción asociada a la info adicional del
	 * detalle del apunte.
	 * 
	 * @return descripción larga tipo de cuenta
	 */
	public String obtenerDescOrigenDetalle(String value) {
		try {
			catalogoBean = catalogoUtils.getCatalogoBean(
					CatalogoEnum.ORIGEN_LIQUIDACION, value);
			return catalogoBean.getDescripcionL();
		} catch (ControlableException | NullPointerException e) {
			return new String();
		}
	}

	/**
	 * Método que devuelve la descripción asociada a la situación del apunte /
	 * liquidación.
	 * 
	 * @param claveFila
	 * @return descripción larga con la situación del apunte / liquidación
	 */
	public String obtenerDescCatalogoSituacionAudit(String claveFila) {
		try {
			catalogoBean = catalogoUtils.getCatalogoBean(
					CatalogoEnum.TP_MODIF_HL, claveFila);
			return catalogoBean.getDescripcionL();
		} catch (ControlableException | NullPointerException e) {
			return new String();
		}
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
	 * Método que redirige al usuario a la vista que haya invocado al flujo
	 * (detalle liquidación, búsqueda de apuntes por naturaleza o búsqueda de
	 * apuntes por tipo de cuenta).
	 * 
	 * @return ruta del recurso a mostrar
	 */
	public String volverAFlujoOrigen() {
		managedBeanStateRecover.finNavegacion(controladorOrigen);
		return navegacionEnumOrigen.getRuta();
	}

	/**
	 * Método que ejecuta la TRN de consulta general de apuntes y muestra los
	 * primeros 10 resultados.
	 */
	public void buscar() {
		apuntes = new ArrayList<>();
		ejecutarBusquedaApuntes(new Integer(0));
		panelResultadosDisponible = true;
		apunteSeleccionado = null;
		pagina = 10;
	}

	/**
	 * Método que ejecuta la TRN de consulta de apuntes por naturaleza de la
	 * cuenta y muestra los primeros 10 resultados.
	 */
	public void buscarPorNaturaleza() {

		apuntes = new ArrayList<>();
		ejecutarBusquedaApuntesPorNaturaleza(new Integer(0));
		panelResultadosDisponible = true;
		apunteSeleccionado = null;
		pagina = 10;
	}

	/**
	 * Método que ejecuta la TRN de consulta general de apuntes y muestra el
	 * resto de los resultados de 10 en 10.
	 */
	public void verMasDatos() {
		apunteSeleccionado = null;
		if (pagina % PaginacionBean.LONGITUD_PAGINA == 0) {
			if (masDatos) {
				ejecutarBusquedaApuntes((Integer) apuntes.get(
						apuntes.size() - 1).getUltimoDatoConsultaAnterior());
				pagina += 10;
			}
		} else {
			if (pagina < apuntes.size()) {
				pagina += 10;
			}
		}
	}

	/**
	 * Método que ejecuta la TRN de consulta de apuntes por naturaleza de la
	 * cuenta y muestra el resto de los resultados de 10 en 10.
	 */
	public void verMasDatosPorNaturaleza() {
	/** RAV 2016 03 29 INI Ajustar el indicador masDatos cuando se presenta el limite de pagina igual al de datos.*/
		int datos, maximo;
	/** RAV 2016 03 29 FIN Ajustar el indicador masDatos cuando se presenta el limite de pagina igual al de datos.*/

		apunteSeleccionado = null;
		if (pagina % PaginacionBean.LONGITUD_PAGINA == 0) {
			if (masDatos) {
				ejecutarBusquedaApuntesPorNaturaleza((Integer) apuntes.get(
						apuntes.size() - 1).getUltimoDatoConsultaAnterior());
				pagina += 10;
	/** RAV 2016 03 29 INI Ajustar el indicador masDatos cuando se presenta el limite de pagina igual al de datos.*/
				datos = (int) apuntes.size() / 50;
				maximo = datos * 50;
				if (apuntes.size() == maximo)
					{
					if (apuntes.get(maximo - 1).getNumSec()== 1)
						{
						this.masDatos = false;
						}
					}
	/** RAV 2016 03 29 FIN Ajustar el indicador masDatos cuando se presenta el limite de pagina igual al de datos.*/
			}
		} else {
			if (pagina < apuntes.size()) {
				pagina += 10;
			}
	/** RAV 2016 03 29 INI Ajustar el indicador masDatos cuando se presenta el limite de pagina igual al de datos.*/
			if (apuntes.size() == 50)
			{
			if (apuntes.get(49).getNumSec()== 1)
				{
				this.masDatos = false;
				}
			}
	/** RAV 2016 03 29 FIN Ajustar el indicador masDatos cuando se presenta el limite de pagina igual al de datos.*/

		}
	}

	/**
	 * Método privado que ejecuta el llamado al backend de Consulta General de
	 * Apuntes.
	 * 
	 * @param numSecUltimoApunteConsultado
	 */
	private void ejecutarBusquedaApuntes(Integer numSecUltimoApunteConsultado) {
		try {
			apuntes.addAll(consultaGeneralApunteBackEnd.ejecutarTRN(cuentaBean,
					filtroTipoCuenta, filtroFechaDesde, filtroFechaHasta,
					numSecUltimoApunteConsultado));
			if (!apuntes.isEmpty()) {
				masDatos = apuntes.get(apuntes.size() - 1).getMasDatos();
			}
		} catch (ControlableException e) {
			apuntes = new ArrayList<>();
		} catch (NullPointerException | NoControlableException e) {
			throw new NoControlableException(
					"Error al ejecutar la TRN de consulta general de apuntes (por tipo de cuenta).",
					this.getClass().getName() + ":" + e.getMessage());
		}
	}

	/**
	 * Método privado que ejecuta el llamado al backend de Consulta Apuntes por
	 * Naturaleza de la Cuenta.
	 * 
	 * @param numSecUltimoApunteConsultado
	 */
	private void ejecutarBusquedaApuntesPorNaturaleza(
			Integer numSecUltimoApunteConsultado) {
		BigDecimal saldo = null;
		MovimientoBean apunte = null;

		if (numSecUltimoApunteConsultado == 0) {
			saldo = this.ObtenerSaldo();
		} else {
			apunte = apuntes.get(apuntes.size() - 1);
		}

		try {
			apuntes.addAll(consultaApuntePorNaturalezaBackend.ejecutarTRN(
					cuentaBean, filtroNaturaleza, numSecUltimoApunteConsultado,
					saldo, apunte));
			masDatos = apuntes.get(apuntes.size() - 1).getMasDatos();
		} catch (ControlableException e) {
			apuntes = new ArrayList<>();
		} catch (IndexOutOfBoundsException | NullPointerException
				| NoControlableException e) {
			throw new NoControlableException(
					"Error al ejecutar la TRN de consulta general de apuntes (por naturaleza de la cuenta).",
					this.getClass().getName() + ":" + e.getMessage());
		}
	}

	/*
	 * private BigDecimal ObtenerSaldo(String filtro) { String codCuenta =

	 * filtro.substring(0, 2); String codSaldo = filtro.substring(2, 4);
	 * 

	 * for (SaldoCuentaBean elemento : this.listaSaldos) { if
	 * (codCuenta.equals(elemento.getCodigoCuenta().trim()) &&
	 * codSaldo.equals(elemento.getCodigoSaldo().trim())) { return
	 * elemento.getSaldo(); } }
	 * 



	 * return new BigDecimal(0);
	 * 
	 * final SaldoBean saldo = consultaSaldoBackEnd.consultaSaldo(new
	 * BigInteger(cuentaBean.getNumeroCuenta().toString()),
	 * cuentaBean.getTipoIdentificacionTitular(),
	 * cuentaBean.getNumIdentificacionTitular()); return
	 * saldo.getSaldoContable(); }
	 */
	private BigDecimal ObtenerSaldo() throws ControlableException,
			NoControlableException {
		final SaldoBean saldo = consultaSaldoBackEnd.consultaSaldo(
				new BigInteger(cuentaBean.getNumeroCuenta().toString()),
				cuentaBean.getTipoIdentificacionTitular().trim(),
				cuentaBean.getNumIdentificacionTitular().trim());
		return saldo.getSaldoContable();
	}

	/**
	 * Método que a partir de la selección del apunte, redirige al usuario al
	 * detalle del apunte.
	 * 
	 * @param event
	 */
	public void onRowSelect(SelectEvent event) {
		apunteSeleccionado = new MovimientoBean();

		apunteSeleccionado = (MovimientoBean) event.getObject();

		ejecutarConsultaDetalleApunte();
		obtenerListaIndicadores();
		establecerParametrosBusqueda();

		if (this.filtroNaturaleza != null) {
			managedBeanStateRecover.enviaController(this,
					NavegacionEnum.BUSQUEDA_APUNTE2);
		} else {
			managedBeanStateRecover.enviaController(this,
					NavegacionEnum.BUSQUEDA_APUNTE);
		}

		ConfigurableNavigationHandler navigationHandler = (ConfigurableNavigationHandler) FacesContext
				.getCurrentInstance().getApplication().getNavigationHandler();

		navigationHandler.performNavigation(NavegacionEnum.DETALLE_APUNTE
				.getRuta());
	}

	/**
	 * Método privado que ejecuta la TRN de consulta detallada de apuntes.
	 */
	private void ejecutarConsultaDetalleApunte() {
		try {
			consultaDetalleApunteBackEnd.ejecutarTRN(apunteSeleccionado);
		} catch (ControlableException e) {
			returnCodDetalle = e.getRtncod();
		} catch (NullPointerException | NoControlableException e) {
			throw new NoControlableException(
					"Error al ejecutar la TRN de consulta detallada de apuntes.",
					this.getClass().getName() + ":" + e.getMessage());
		}


		if (AC.equals(apunteSeleccionado.getCodigoOrigenApunte())
				&& apunteSeleccionado.getIdOrigenApunte() == null
				|| apunteSeleccionado.getIdOrigenApunte().trim().length() == 0) {

			apunteSeleccionado.setIdOrigenApunte(apunteSeleccionado
					.getNumCuenta().toString());


		}
	}

	/**
	 * Método privado que ejecuta la TRD de consulta de indicadores de un
	 * apunte. Este método permite construir la tabla de indicadores vs.
	 * situaciones del apunte.
	 */
	private void obtenerListaIndicadores() {
		try {
			tablaIndicadoresApunteTRDBackend.ejecutarTRN(apunteSeleccionado);
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
		this.parametrosBean = new ParametrosBusquedaApunteBean();
		this.parametrosBean.setCuentaBean(this.cuentaBean);
		this.parametrosBean.setFiltroNaturaleza(this.filtroNaturaleza);
		// Sí la búsqueda es por naturaleza, obtenemos el código de la cuenta.
		if (this.filtroNaturaleza != null
				&& !this.filtroNaturaleza.trim().isEmpty()
				&& this.filtroNaturaleza.trim().length() == ApunteController.LONGITUD_NATURALEZA) {
			this.parametrosBean.setFiltroTipoCuenta(this.filtroNaturaleza
					.substring(0, 2));
		} else {
			// Significa que la búsqueda es por tipo de cuenta.
			this.parametrosBean.setFiltroTipoCuenta(this.filtroTipoCuenta);
		}
		this.parametrosBean.setFiltroFechaDesde(this.filtroFechaDesde);
		this.parametrosBean.setFiltroFechaHasta(this.filtroFechaHasta);
		this.parametrosBean.setApuntes(this.apuntes);
		this.parametrosBean.setApunteSeleccionado(this.apunteSeleccionado);
		this.parametrosBean.setMasDatos(this.masDatos);
		this.parametrosBean.setPagina(this.pagina);

		// Limpiamos lista de detalle ampliado
		this.parametrosBean.getApunteSeleccionado()
				.setDetalleTipoTranPlanifApunte(
						new ArrayList<DatosAmpliadosBean>());
		this.parametrosBean.getApunteSeleccionado()
				.setDetalleInfoAdicionalApunte(
						new ArrayList<DatosAmpliadosBean>());
		this.obtenerFlash()
				.put(ParametrosFlashEnum.PARAMETROS_BUSQUEDA_APUNTE_BEAN
						.getParamFlash(),
						this.parametrosBean);
	}

	/**
	 * Método que a partir del clic del usuario, ejecuta el llamado a las TRN's
	 * de consulta.
	 * 
	 * @param event
	 */
	public void onTabChange(TabChangeEvent event) {
		Tab activeTab = event.getTab();
		switch (activeTab.getId()) {
		case "tabConsultaAuditoria":
			if (!indConsultaAuditoria) {
				ejecutarConsultaAuditoriaApunte();
			}
			break;
		case "tabConsultaAmpliada":
			if (!indConsultaAmpliada) {
				ejecutarConsultaAmpliadaApunte();
			}
			break;
		case "tabConsultaOrigen":
			if (!indConsultaOrigen) {
				ejecutarConsultaOrigenApunte();
			}
			break;
		}
	}

	/**
	 * Método invocado desde la vista 'consultaDetalleApunte.xhtml' con la
	 * finalidad de obtener cada columna de datos necesaria para construir la
	 * tabla de indicadores.
	 * 
	 * @param rowIndexVar
	 * @param columnIndex
	 * @return valor celda a mostrar
	 */
	public String obtenerColumnaTabla(int rowIndexVar, int columnIndex) {
		String[] tokens = parametrosBean.getApunteSeleccionado()
				.getIndicadores().get(rowIndexVar).split("\\+");
		if (tokens != null && tokens.length > columnIndex) {
			return tokens[columnIndex];
		}
		return new String();
	}

	/**
	 * Método privado que ejecuta la TRN de consulta auditoría de apuntes
	 * (TR_TX_CONS_AUDIT_TRN) y la TRN de carga de auditoría
	 * (TR_CARGAR_AUDIT_TRN).
	 */
	private void ejecutarConsultaAuditoriaApunte() {
		try {
			if (parametrosBean.getApunteSeleccionado().getApunteLiquidacion() == ORIGEN_LIQUIDACION
					&& liquidacionSeleccionada != null) {
				AuditoriaBean auditoriaBean = consultaAuditoriaLiquidacionBackend
						.ejecutarTRN(parametrosBean.getCuentaBean(),
								liquidacionSeleccionada);
				parametrosBean.getApunteSeleccionado().setAuditoriaBean(
						auditoriaBean);
				parametrosBean = consultaAuditoriaInfoLiquidacionBackend
						.ejecutarTRN(parametrosBean);
			} else {
				parametrosBean = consultaAuditoriaLiquidacionBackend
						.ejecutarTRN(parametrosBean);
				parametrosBean = consultaAuditoriaInfoLiquidacionBackend
						.ejecutarTRN(parametrosBean);
			}
			indConsultaAuditoria = true;
		} catch (ControlableException e) {
			returnCodAuditoria = e.getRtncod();
		} catch (IndexOutOfBoundsException | NullPointerException
				| NoControlableException e) {
			throw new NoControlableException(
					"Error al ejecutar la TRN de consulta auditoría de apuntes.",
					this.getClass().getName() + ":" + e.getMessage());
		}
	}

	/**
	 * Método privado que ejecuta la TRN de consulta ampliada de apuntes.
	 */
	private void ejecutarConsultaAmpliadaApunte() {
		try {
			parametrosBean = consultaAmpliadaApunteBackEnd
					.ejecutarTRN(parametrosBean);
			indConsultaAmpliada = true;
		} catch (ControlableException e) {
			returnCodAmpliada = e.getRtncod();
		} catch (NullPointerException | NoControlableException e) {
			throw new NoControlableException(
					"Error al ejecutar la TRN de consulta ampliada de apuntes.",
					e.getMessage());
		}
	}

	/**
	 * Método privado que ejecuta la TRN de consulta originación de apuntes. Sí
	 * la liquidación se encuentra informada, este método simplemente carga los
	 * datos provenientes del flujo de liquidaciones.
	 */
	private void ejecutarConsultaOrigenApunte() {
		try {
			if (parametrosBean.getApunteSeleccionado().getApunteLiquidacion() == ORIGEN_LIQUIDACION
					&& liquidacionSeleccionada != null) {
				extraerDatosOrigenLiquidacion();
			} else {
				if (parametrosBean.getApunteSeleccionado()
						.getCodigoOrigenApunte() != null
						&& parametrosBean.getApunteSeleccionado()
								.getIdOrigenApunte() != null) {
					if (parametrosBean.getApunteSeleccionado()
							.getCodigoOrigenApunte().equals(HL)) {
						extraerDatosOrigenCuentaAsociada();
					} else if (parametrosBean.getApunteSeleccionado()
							.getCodigoOrigenApunte().equals(CP)) {
						apunteChequeBean = consultaApuntePorChequeBackEnd
								.ejecutarTRN(parametrosBean
										.getApunteSeleccionado());
					} else if (parametrosBean.getApunteSeleccionado()
							.getCodigoOrigenApunte().equals(TF)) {
						LOGGER.debug("El código de origen del apunte es TF. Se efectúa la consulta de origen de un traspaso.");
						ejecutarConsultaOrigenTraspaso();
					} else if (parametrosBean.getApunteSeleccionado()
							.getCodigoOrigenApunte().equals(CJ)) {
						LOGGER.debug("El código de origen del apunte es CJ. Se efectúa la consulta de origen de un cheque bancario.");
						ejecutarConsultaOrigenChequeBancario();
					} else if (parametrosBean.getApunteSeleccionado()
							.getCodigoOrigenApunte().equals(AC)) {
						LOGGER.debug("El código de origen del apunte es AC. No existe información que mostrar.");
						returnCodOrigen = NO_HAY_MAS_DATOS;
					} else if (parametrosBean.getApunteSeleccionado()
							.getCodigoOrigenApunte().equals(AO)) {
						LOGGER.debug("El código de oriden del apunte es AO. No existe información que mostrar.");
						returnCodOrigen = NO_HAY_MAS_DATOS;
					} else if (parametrosBean.getApunteSeleccionado()
							.getCodigoOrigenApunte().equals(ER)) {
						LOGGER.debug("El código de oriden del apunte es ER. No existe información que mostrar.");
						returnCodOrigen = NO_HAY_MAS_DATOS;
					} else {
						LOGGER.debug("El código de oriden del apunte es "
								+ parametrosBean.getApunteSeleccionado()
										.getCodigoOrigenApunte()
								+ ". No existe información que mostrar.");
						returnCodOrigen = NO_HAY_MAS_DATOS;
					}
				} else {
					returnCodOrigen = NO_HAY_MAS_DATOS;
				}
			}
			indConsultaOrigen = true;
		} catch (NullPointerException | NoControlableException e) {
			throw new NoControlableException(
					"Error al ejecutar la TRN de consulta origen de apuntes.",
					this.getClass().getName() + ":" + e.getMessage());
		}
	}

	/**
	 * Funcion que obtiene la consulta de traspaso TF
	 * 
	 * @param numeroCuenta
	 * @return
	 */
	public TraspasoTFBean consultaTraspasoTF(long numeroCuenta) {
		return consultaTraspasoTFBackEnd.ejecutarTRN(numeroCuenta);
	}

	/**
	 * Método privado que obtiene los datos de origen de la liquidación cuando
	 * el usuario haya accedido al detalle de apuntes desde el flujo de
	 * liquidaciones.
	 */
	private void extraerDatosOrigenLiquidacion() {
		parametrosBean
				.getApunteSeleccionado()
				.getLiquidacionBean()
				.setPeriodoDesdeLiquidacion(
						liquidacionSeleccionada.getPeriodoDesdeLiquidacion());
		parametrosBean
				.getApunteSeleccionado()
				.getLiquidacionBean()
				.setPeriodoHastaLiquidacion(
						liquidacionSeleccionada.getPeriodoHastaLiquidacion());
		parametrosBean.getApunteSeleccionado().getLiquidacionBean()
				.setNetoOperacion(liquidacionSeleccionada.getNetoOperacion());
		parametrosBean
				.getApunteSeleccionado()
				.getLiquidacionBean()
				.setImportePendiente(
						liquidacionSeleccionada.getImportePendiente());
		parametrosBean
				.getApunteSeleccionado()
				.getLiquidacionBean()
				.setTipoLiquidacion(
						liquidacionSeleccionada.getTipoLiquidacion());
		parametrosBean.getApunteSeleccionado().getLiquidacionBean()
				.setSituacion(liquidacionSeleccionada.getSituacion());
	}

	/**
	 * Método privado que obtiene los datos de origen de una cuenta asociada a
	 * la cuenta eje.
	 */
	private void extraerDatosOrigenCuentaAsociada() {
		StringToDateConverter converter = new StringToDateConverter();
		String dataRecord[] = parametrosBean.getApunteSeleccionado()
				.getIdOrigenApunte().split("-");
		if (dataRecord != null && dataRecord.length > 0) {
			CuentaBean cuentaBeanAsociada = new CuentaBean();
			LiquidacionBean liquidacionBean = new LiquidacionBean();
			cuentaBeanAsociada.setNumeroCuenta(Long.parseLong(dataRecord[1]
					.replaceAll("[^\\d.]", "")));
			liquidacionBean.setFechaLiquidacion(converter
					.convertTo(dataRecord[2]));
			liquidacionBean.setNumsec(Integer.parseInt(dataRecord[3]
					.replaceAll("[^\\d.]", "")));
			try {
				consultaBasicaLiquidacionBackend.ejecutarTRN(
						cuentaBeanAsociada, liquidacionBean);


			} catch (ControlableException e) {
				if (e.getRtncod() == BackEndBean.RETURN_COD_SIN_DATOS) {
					try {
						consultaBasicaLiquidacionBackend.ejecutarTRN(
								this.parametrosBean.getCuentaBean(),

								liquidacionBean);
					} catch (ControlableException ce) {
						if (e.getRtncod() == BackEndBean.RETURN_COD_SIN_DATOS) {
							throw e;
						}
					}
				}
			}
			descConceptoLiquidacionBackend.ejecutarTRN(liquidacionBean);
			parametrosBean.getApunteSeleccionado().setLiquidacionBean(
					liquidacionBean);
		} else {
			LOGGER.error("Error al extraer los datos del id de origen del apunte.");
			returnCodOrigen = NO_HAY_MAS_DATOS;
		}
	}

	/**
	 * Método privado que ejecuta la TRN de consulta de origen de un cheque
	 * bancario (TR_CONSULTA_CHEQUE_2_TRN).
	 */
	private void ejecutarConsultaOrigenChequeBancario() {
		if (!parametrosBean.getApunteSeleccionado().getIdOrigenApunte().trim()
				.isEmpty()
				&& parametrosBean.getApunteSeleccionado().getIdOrigenApunte()
						.trim().length() >= 10) {
			String idOrigenApunte = parametrosBean.getApunteSeleccionado()
					.getIdOrigenApunte().trim();
			int numSecCj = Integer.parseInt(idOrigenApunte
					.substring(idOrigenApunte.length() - 10));
			chequeBancarioBean = consultaChequeBancarioBackend
					.ejecutarTRN(numSecCj);
			if (chequeBancarioBean != null
					&& chequeBancarioBean.getFechaPresentacion() != null) {
				String claveFila = chequeBancarioBean.getTipoCheque();
				chequeBancarioBean
						.setTipoCheque(obtenerDescTipoCheque(claveFila));
				IntegerToDateConverter converter = new IntegerToDateConverter();
				Date fechaMinTCB = converter
						.convertTo(ConstantesFuncionales.MIN_FECHA_INICIO_INT_TCB);
				if (DateTimeComparator.getDateOnlyInstance().compare(
						chequeBancarioBean.getFechaPresentacion(), fechaMinTCB) == 0) {
					fechaPresentacionOculta = true;
				}
			}
		} else {
			LOGGER.error("Error al extraer los datos del id de origen del apunte.");
			returnCodOrigen = NO_HAY_MAS_DATOS;
		}
	}

	/**
	 * Método que obtiene la descripción del tipo de cheque bancario CJ.
	 * 
	 * @param claveFila
	 * @return descripción larga del tipo de cheque bancario
	 */
	public String obtenerDescTipoCheque(String claveFila) {
		try {
			catalogoBean = catalogoUtils.getCatalogoBean(
					CatalogoEnum.TP_OP_CHEQUE, claveFila);
			return catalogoBean.getDescripcionL();
		} catch (NullPointerException | ControlableException e) {
			return new String();
		}
	}

	/**
	 * Método que obtiene la descripción de cada una de las cláusulas que se
	 * pueden presentan en la vista.
	 * 
	 * @param claveFila
	 * @return descripción larga de cada cláusula
	 */
	public String obtenerDescClausula(String claveFila) {
		try {
			catalogoBean = catalogoUtils.getCatalogoBean(
					CatalogoEnum.CLAUSULAS, claveFila);
			return catalogoBean.getDescripcionL();
		} catch (NullPointerException | ControlableException e) {
			return new String();
		}
	}

	/**
	 * Método privado que ejecuta la TRN de consulta de traspasos
	 * (TR_PETCN_OBTE_TRFRCIA_TRN).
	 */
	private void ejecutarConsultaOrigenTraspaso() {
		if (!parametrosBean.getApunteSeleccionado().getIdOrigenApunte().trim()
				.isEmpty()
				&& parametrosBean.getApunteSeleccionado().getIdOrigenApunte()
						.trim().length() >= 10) {
			String idOrigenApunte = parametrosBean.getApunteSeleccionado()
					.getIdOrigenApunte().trim();
			long numSecTf = Long.parseLong(idOrigenApunte
					.substring(idOrigenApunte.length() - 10));
			traspasoTFBean = consultaTraspasoTFBackEnd.ejecutarTRN(numSecTf);
			LOGGER.error("Fin consulta traspaso");
		} else {
			LOGGER.error("Error al extraer los datos del id de origen del apunte.");
			returnCodOrigen = NO_HAY_MAS_DATOS;
		}
	}

	/**
	 * Mètodo para obtener el tipo de talonario para apunte con origen de
	 * cheques
	 */
	public String obtenerTipoTalonario(ApunteChequeBean apunteChequeBean) {
		try {
			String nombreCatalogo = apunteChequeBean.getCodcjchqpg()
					+ apunteChequeBean.getCodidcheq();
			return this.catalogoUtils.getCatalogoBean(CatalogoEnum.TP_CP_ENT,
					nombreCatalogo).getDescripcionL();
		} catch (NullPointerException | NoControlableException nce) {
			return "";
		}
	}

	/**
	 * Método para obtener el estado del cheques
	 */
	public String obtenerEstadoCheque(String estado) {
		try {
			return this.catalogoUtils.getCatalogoBean(CatalogoEnum.ECV_CHEQUE,
					estado).getDescripcionL();
		} catch (NullPointerException | NoControlableException
				| ControlableException nce) {
			return "";
		}
	}

	/**
	 * Método para obtener el estado del cheques
	 */
	public String obtenerSituacionPago(ApunteChequeBean apunteCheque) {
		String situacion;
		try {
			situacion = this.catalogoUtils.getCatalogoBean(
					CatalogoEnum.SIT_PAGO, apunteCheque.getSituacionPago())
					.getDescripcionL();
		} catch (NullPointerException | NoControlableException
				| ControlableException nce) {
			situacion = "";
		}
		return situacion;
	}

	/**
	 * Método para obtener la disposición de un cheque
	 * 
	 * @param disposicion
	 *            valor clave disposición
	 * @return descripción de disposición
	 */
	public String obtenerDisposicion(String disposicion) {
		try {
			return this.catalogoUtils.getCatalogoBean(CatalogoEnum.DISPOSICION,
					disposicion).getDescripcionL();
		} catch (NullPointerException | NoControlableException
				| ControlableException nce) {
			return "OTRO";
		}
	}

	/**
	 * Método que consulta el cataloge para obtener el tipo de cuentas en base a
	 * el codigo de linea de la cuenta y el id grupo de producto.
	 * 
	 * @return lista con los tipos de cuenta
	 * 
	 */

	public List<CatalogoBean> consultaTipoCuentaCataloge() {
		List<CatalogoBean> listaTipoCuentaCataloge = new ArrayList<CatalogoBean>();
		List<CatalogoBean> listaTipoCuentaCatalogo = new ArrayList<CatalogoBean>();
		List<CatalogoBean> listaFinal = new ArrayList<CatalogoBean>();
		listaTipoCuentaCataloge = catalogeBackend.ejecutarTRN(
				CatalogoEnum.TP_CTA,
				(cuentaBean.getCodLinea() + cuentaBean.getIdGrupoProducto()));
		listaTipoCuentaCatalogo = catalogoUtils
				.getCatalogo(CatalogoEnum.TP_CTA);

		for (CatalogoBean dataCataloge : listaTipoCuentaCataloge) {
			for (CatalogoBean dataCatalogo : listaTipoCuentaCatalogo) {
				if (dataCataloge.getClaveFila().trim()
						.equals(dataCatalogo.getClaveFila().trim())) {
					CatalogoBean catalogoBean = new CatalogoBean();
					catalogoBean.setClaveFila(dataCataloge.getClaveFila()
							.trim());
					catalogoBean.setContenido(dataCatalogo.getContenido()
							.trim());
					catalogoBean.setDescripcionC(dataCatalogo.getDescripcionC()
							.trim());
					catalogoBean.setDescripcionL(dataCatalogo.getDescripcionL()
							.trim());


					listaFinal.add(catalogoBean);


					break;
				}
			}
		}
		return listaFinal;
	}

}