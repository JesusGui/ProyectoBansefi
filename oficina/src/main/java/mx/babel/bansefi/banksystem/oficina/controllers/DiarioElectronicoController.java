package mx.babel.bansefi.banksystem.oficina.controllers;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.sesion.utils.ContextoUtils;
import mx.babel.bansefi.banksystem.base.beans.ApuntesBean;
import mx.babel.bansefi.banksystem.base.enums.BusquedaEnum;
import mx.babel.bansefi.banksystem.base.enums.NavegacionEnum;
import mx.babel.bansefi.banksystem.base.enums.ParametrosFlashEnum;
import mx.babel.bansefi.banksystem.base.utils.FechaUtils;
import mx.babel.bansefi.banksystem.base.utils.ManagedBeanStateRecover;
import mx.babel.bansefi.banksystem.oficina.backends.AnulacionServiciosCentralesBackEnd;
import mx.babel.bansefi.banksystem.oficina.backends.ConsultaDetalleDiarioElectronicoBackEnd;
import mx.babel.bansefi.banksystem.oficina.backends.ConsultaDiarioElectronicoBackEnd;
import mx.babel.bansefi.banksystem.oficina.beans.AnulacionServiciosCentralesRespuestaBean;
import mx.babel.bansefi.banksystem.oficina.beans.DiarioElectronicoBusquedaBean;
import mx.babel.bansefi.banksystem.oficina.beans.DiarioElectronicoDetalleRespuestaBean;
import mx.babel.bansefi.banksystem.oficina.beans.DiarioElectronicoResultadoBean;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@ManagedBean(name = "diarioElectronicoController")
@ViewScoped
@Component
@Scope("view")
public class DiarioElectronicoController implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<DiarioElectronicoResultadoBean> diarioBeanList = new ArrayList<DiarioElectronicoResultadoBean>();

	private DiarioElectronicoResultadoBean diarioBeanTable;

	private DiarioElectronicoDetalleRespuestaBean diarioElectronicoDetalleBean;

	private DiarioElectronicoResultadoBean selectedRow;

	@Autowired
	private ConsultaDiarioElectronicoBackEnd diarioElectronicoBackEnd;

	@Autowired
	private ConsultaDetalleDiarioElectronicoBackEnd consultaDetalleDiarioElectronicoBackEnd;

	@Autowired
	private AnulacionServiciosCentralesBackEnd anulacionServiciosCentralesBackEnd;

	@Autowired
	ManagedBeanStateRecover managedBeanStateRecover;

	@Autowired
	private ContextoUtils contextoUtils;

	/**
	 * Enum para definir a donde se redireccionarÃ¡ al seleccionar un objeto de
	 * la tabla de resultados.
	 */
	private NavegacionEnum destino;
	private Object destinoController;

	private DiarioElectronicoBusquedaBean diarioBusquedaBean;

	private ApuntesBean apuntesBean;

	private boolean busquedaApunte;

	/**
	 * Bandera que indica si no se utilizo ningun campo para filtrar la busqueda
	 */
	private boolean errorFiltros;

	/**
	 * PaginaciÃ³n
	 */
	private int tamanoPagina = 10;
	private int nPagina = 1;
	private List<DiarioElectronicoResultadoBean> diarioBeanListPagina = new ArrayList<DiarioElectronicoResultadoBean>();

	// @Autowired
	// WordUtils wordUtils;
	
	private static final Logger LOGGER = LogManager
			.getLogger(DiarioElectronicoController.class);
	
	@PostConstruct
	public void init() {
		if (this.obtieneFlash().get(
				ParametrosFlashEnum.NAVEGACION_MANAGED_BEAN.getParamFlash()) != null) {
			if ((Boolean) this.obtieneFlash()
					.get(ParametrosFlashEnum.NAVEGACION_MANAGED_BEAN
							.getParamFlash())) {
				destino = managedBeanStateRecover.getDestino();
				destinoController = managedBeanStateRecover.getController();
				initializeData();
			} else {
				// Regresa de un flujo
				managedBeanStateRecover.recuperaController(this);
				
				this.diarioBusquedaBean = (DiarioElectronicoBusquedaBean) this.obtieneFlash().get("almacenaBusqueda");

				if (this.obtieneFlash().get(
						ParametrosFlashEnum.NUMERO_CUENTA.getParamFlash()) != null) {
					Long numCuenta = (Long) this.obtieneFlash().get(
							ParametrosFlashEnum.NUMERO_CUENTA.getParamFlash());
					this.diarioBusquedaBean
							.setCuenta(String.valueOf(numCuenta));
				}
			}
		} else {
			initializeData();
		}
	}

	/**
	 * Funcion que inicializa el controlador de acuerdo a la informacion en la
	 * Flash
	 */
	public void initializeData() {
		// Validacion para ventana: diarioTabla.xhtml
		// Valida si tiene el bean de busqueda
		if (this.obtieneFlash()
				.get(ParametrosFlashEnum.DIARIO_ELECTRONICO_BUSQUEDA
						.getParamFlash()) != null) {
			// Almacena el bean para realizar la busqueda
			this.setDiarioBusquedaBean((DiarioElectronicoBusquedaBean) this
					.obtieneFlash().get(
							ParametrosFlashEnum.DIARIO_ELECTRONICO_BUSQUEDA
									.getParamFlash()));
			// Verifica que el bean de busqueda no sea nulo
			if (this.getDiarioBusquedaBean() != null) {
				try {
					// Ejecuta la busqueda de operaciones
					this.setDiarioBeanList(diarioElectronicoBackEnd
							.ejecutarWS(this.getDiarioBusquedaBean()));
					copiaDatos();
				} catch (Exception e) {
					LOGGER.debug("No hay respuesta del servicio web");
				}
			}

			/**
			 * Verifica si viene de hacer una anulaciÃ³n, para mostrar el mensaje
			 * de confirmaciÃ³n
			 */
			if (obtieneFlash().get("confirmAnulacion") != null) {
				if ((boolean) obtieneFlash().get("confirmAnulacion")) {
					RequestContext.getCurrentInstance().execute(
							"PF('dlgConfirmacionAnulacion').show()");
				} else {
					RequestContext.getCurrentInstance().execute(
							"PF('dlgErrorDetalle').show()");
				}
			}

		} else if (this.obtieneFlash().get(
				ParametrosFlashEnum.DIARIO_ELECTRONICO_TABLA.getParamFlash()) != null) {
			// Validacion para ventana: diarioDetalle.xhtml

			// Obtiene el bean seleccionado en la tabla
			this.setDiarioBeanTable((DiarioElectronicoResultadoBean) this
					.obtieneFlash().get(
							ParametrosFlashEnum.DIARIO_ELECTRONICO_TABLA
									.getParamFlash()));

			// El bean tiene informaciÃ³n basica que se mostraba en la tabla
			// Es necesario ejectuar otro servicio para completar la informaciÃ³n
			// detalle, con el mismo bean.
			try {
				this.setDiarioElectronicoDetalleBean(this.consultaDetalleDiarioElectronicoBackEnd
						.ejecutarWS(this.getDiarioBeanTable()));
			} catch (NoControlableException e) {
				LOGGER.debug(
						"Error al consultar el detalle del diario electronico",
						e);
				obtieneFlash().put("confirmAnulacion", false);
				cerrarDetalle2();
			}
			if (obtieneFlash().get("almacenaBusqueda") != null) {
				this.diarioBusquedaBean = (DiarioElectronicoBusquedaBean) obtieneFlash()
						.get("almacenaBusqueda");
			}
		} else {
			// Validacion para ventana: diarioBusqueda.xhtml
			
		
			this.diarioBusquedaBean = new DiarioElectronicoBusquedaBean();
	
			
			if (this.obtieneFlash().get(
					ParametrosFlashEnum.BUSQUEDA_APUNTE.getParamFlash()) != null) {
				this.setApuntesBean((ApuntesBean) this.obtieneFlash().get(
						ParametrosFlashEnum.BUSQUEDA_APUNTE.getParamFlash()));
				this.diarioBusquedaBean.setFechaValor(this.apuntesBean
						.getFechaValor());
				this.diarioBusquedaBean.setPuesto(this.apuntesBean.getPuesto());
				this.diarioBusquedaBean.setNumSecuencia(this.apuntesBean
						.getNumTransaccion());
				// this.setDestino(managedBeanStateRecover.getDestino());
				// this.setDestinoController(managedBeanStateRecover.getController());
				this.setBusquedaApunte(true);
			}

		}

	}
	
	/**
	 * Funcion que dependiendo del estado del registro, devuelve la clase del
	 * estilo correspondiente
	 * 
	 * @param tipo
	 * @return
	 */
	public String getEntryStyle(String tipo) {
		if ("0".equals(tipo)) {
			return "estado-bien";
		} else if ("1".equals(tipo)) {
			return "estado-mal";
		} else {
			return "estado-sinrespuesta";
		}
	}

	/**
	 * Funcion que indica si hay mas datos a mostrar
	 * 
	 * @return
	 */
	public void verMas() {
		this.nPagina++;
		copiaDatos();
	}

	/**
	 * Funcion que indica si aun hay mas datos a listar.
	 * 
	 * @return
	 */
	public boolean isMasDatos() {
		if (diarioBeanList != null && !diarioBeanList.isEmpty()
				&& diarioBeanListPagina.size() < diarioBeanList.size()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Funcion que copia datos a la nueva tabla a partir de la pagina actual
	 */
	public void copiaDatos() {
		if (diarioBeanList != null && !diarioBeanList.isEmpty()) {
			for (int i = (tamanoPagina * (nPagina - 1)); (i < (tamanoPagina * nPagina))
					&& (i < diarioBeanList.size()); i++) {
				diarioBeanListPagina.add(diarioBeanList.get(i));
			}
		}
	}

	/**
	 * @return Metodo utilizado para recuperar una instancia de Flash
	 */
	public Flash obtieneFlash() {
		return FacesContext.getCurrentInstance().getExternalContext()
				.getFlash();
	}

	public String inicio() {
		return NavegacionEnum.DIARIO_BUSQUEDA.getRuta();
	}

	public String cancelar() {
		return NavegacionEnum.INICIO.getRuta();
	}

	public void limpiar() {
		RequestContext.getCurrentInstance().reset("formDiario");
		this.errorFiltros = false;
		this.diarioBusquedaBean = new DiarioElectronicoBusquedaBean();	
	}

	/**
	 * Funcion que valida que al menos se haya elegido un filtro
	 * 
	 * @return
	 */
	public boolean validaFiltros() {
		if (this.diarioBusquedaBean != null) {
			if (this.diarioBusquedaBean.getContableDesde() == null
					&& this.diarioBusquedaBean.getContableHasta() == null
					&& this.diarioBusquedaBean.getTecleoDesde() == null
					&& this.diarioBusquedaBean.getTecleoHasta() == null
					&& (this.diarioBusquedaBean.getImporteDesde() == null || this.diarioBusquedaBean
							.getImporteDesde().equals(BigDecimal.ZERO))
					&& (this.diarioBusquedaBean.getImporteHasta() == null || this.diarioBusquedaBean
							.getImporteHasta().equals(BigDecimal.ZERO))
					&& StringUtils.isBlank(this.diarioBusquedaBean
							.getSignoContable())
					&& StringUtils.isBlank(this.diarioBusquedaBean
							.getTipoOperacion())
					&& StringUtils.isBlank(this.diarioBusquedaBean.getCuenta())
					&& StringUtils
							.isBlank(this.diarioBusquedaBean.getUsuario())) {
				return false;
			} else {
				return true;
			}
		}
		return false;
	}

	/**
	 * MÃ©todo para buscar mediante el buscador el nÃºmero de cuenta
	 * 
	 * @return
	 */
	public String buscarPersona() {
		obtieneFlash().put(ParametrosFlashEnum.TIPO_BUSQUEDA.getParamFlash(),
				BusquedaEnum.CUENTAS.getBusquedaValor());
		managedBeanStateRecover.enviaController(this,
				NavegacionEnum.DIARIO_BUSQUEDA);
		return NavegacionEnum.BUSQUEDA.getRuta();

	}

	/**
	 * Funcion que envia los datos de la busqueda a la ventana de listado de
	 * resultados
	 * 
	 * @return
	 */
	public String buscar() {
		if (validaFiltros()) {
			obtieneFlash()
					.put(ParametrosFlashEnum.DIARIO_ELECTRONICO_BUSQUEDA
							.getParamFlash(),
							this.diarioBusquedaBean);
			if (this.isBusquedaApunte()) {
				managedBeanStateRecover.enviaController(this.destinoController,
						this.destino);
				obtieneFlash().put(
						ParametrosFlashEnum.APUNTE_BEAN.getParamFlash(), true);
			} else {
				managedBeanStateRecover.enviaController(this,
						NavegacionEnum.DIARIO_BUSQUEDA);
			}
			return NavegacionEnum.DIARIO_TABLA.getRuta();
		} else {
			this.errorFiltros = true;
			this.diarioBusquedaBean.setImporteDesde(null);
			this.diarioBusquedaBean.setImporteHasta(null);
			RequestContext.getCurrentInstance().execute(
					"scrollTo('#formDiario\\\\:alerta')");
			return null;
		}
	}

	/**
	 * Evento que se ejecuta al seleccionar una fila del listado de resultados
	 * Muestra el detalle de la operacion seleccionada en una ventana nueva
	 * 
	 * @param event
	 * @throws IOException
	 */
	public void mostrarDetalle(SelectEvent event) throws IOException {
		Object fila = event.getObject();
		this.selectedRow = (DiarioElectronicoResultadoBean) fila;
	    this.obtieneFlash().put("almacenaBusqueda", this.diarioBusquedaBean);
  
		if (this.isBusquedaApunte()) {
			ApuntesBean apunteSeleccionado = this.crearApunte();
			this.obtieneFlash().put(ParametrosFlashEnum.APUNTE_BEAN.getParamFlash(),
					apunteSeleccionado);
			this.managedBeanStateRecover.finNavegacion(this.destinoController);
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect("../anulacionApuntes/busquedaAnulacion.xhtml");
		} else {
			this.obtieneFlash()
					.put(ParametrosFlashEnum.DIARIO_ELECTRONICO_TABLA
							.getParamFlash(),
							this.selectedRow);
			/*FacesContext.getCurrentInstance().getExternalContext()
					.redirect("diarioDetalle.xhtml");*/
			ConfigurableNavigationHandler navigationHandler = (ConfigurableNavigationHandler) FacesContext
					.getCurrentInstance().getApplication().getNavigationHandler();

			navigationHandler.performNavigation(NavegacionEnum.DIARIO_DETALLE.getRuta());
		}
	}

	/**
	 * Funcion que determina si es posible anular la operaciÃ³n. Esto de acuerdo
	 * a si la fecha de la operaciÃ³n corresponde la misma fecha del sistema
	 * Valida tambien el estatus de la operacion
	 * 
	 * @return boolean
	 */
	public boolean permiteAnulacion() {
		if (diarioBeanTable != null) {
			// Valida el estatus de la transaccion
			// Si no es activo, no sigue validando y retorna falso
			if (!StringUtils.isBlank(diarioBeanTable.getCodigo())
					&& !"ACTIVO".equalsIgnoreCase(diarioBeanTable.getCodigo()
							.trim())) {
				return false;
			}

			if (contextoUtils != null) {
				String fechaContableActual = FechaUtils.formatFecha(
						contextoUtils.getFechaContableActual(), "dd-MM-yyyy");
				String fechaOperacion = FechaUtils.formatFecha(
						diarioBeanTable.getFechaContable(), "dd-MM-yyyy");
				if (fechaContableActual.equals(fechaOperacion)) {
					return true;
				}
			}
		}

		return false;
	}

	/**
	 * Metodo que ejecuta el WebService para anular la operacion
	 * 
	 * @return
	 */
	public String anularOperacion() {
		if (diarioBeanTable != null) {
			try {
				AnulacionServiciosCentralesRespuestaBean respuesta = anulacionServiciosCentralesBackEnd
						.ejecutarWS(diarioElectronicoDetalleBean);

				if (respuesta != null) {
					obtieneFlash().put("confirmAnulacion", true);
					return cerrarDetalle("resultados");
				} else {
					return null;
				}
			} catch (NoControlableException | InstantiationException e) {
				LOGGER.debug("Error en anulacion de servicios centrales", e);
				// FacesMessage message = new FacesMessage(
				// FacesMessage.SEVERITY_ERROR,
				// "AnulaciÃ³n de Servicios Centrales",
				// "Lo sentimos, no fue posible anular el servicio.");
				// RequestContext.getCurrentInstance()
				// .showMessageInDialog(message);

				// Cerrar dialogo de anulacion
				RequestContext.getCurrentInstance().execute(
						"PF('dlgConfirmacionAnulacion').hide()");

				// Mostrar error
				RequestContext.getCurrentInstance().execute(
						"PF('dlgErrorAnulacion').show()");

				return null;
			}
		} else {
			return null;
		}
	}

	public String volver() {
		
 		managedBeanStateRecover.finNavegacion(destinoController);
		if (destino != null) {
			this.obtieneFlash().put("almacenaBusqueda", this.diarioBusquedaBean);
			return destino.getRuta();
		} else {
			
//			this.setDiarioBusquedaBean((DiarioElectronicoBusquedaBean) this
//					.obtieneFlash().get(
//							ParametrosFlashEnum.DIARIO_ELECTRONICO_BUSQUEDA
//									.getParamFlash()));
			
			this.obtieneFlash().put("almacenaBusqueda", this.diarioBusquedaBean);
				
			return NavegacionEnum.DIARIO_BUSQUEDA.getRuta();
			
		}
	}

	/**
	 * Funcion para volver cuando hay un error en la consulta detalle
	 */
	public void volver2() {
		try {
			managedBeanStateRecover.finNavegacion(destinoController);
			if (destino != null) {
				FacesContext.getCurrentInstance().getExternalContext()
						.redirect(destino.getRuta());
			} else {
				FacesContext.getCurrentInstance().getExternalContext()
						.redirect("diarioTabla.xhtml");
			}
		} catch (IOException e) {
			LOGGER.debug("Error al redireccionar la vista");
		}
	}

	/**
	 * Funcion que se ejecuta al pulsar el boton de cerrar en la ventana detalle
	 * de la operacion
	 * 
	 * @return String ruta
	 */
	public String cerrarDetalle(String destino) {
		if ("resultados".equals(destino)) {
			obtieneFlash()
					.put(ParametrosFlashEnum.DIARIO_ELECTRONICO_BUSQUEDA
							.getParamFlash(),
							this.getDiarioBusquedaBean());
			return NavegacionEnum.DIARIO_TABLA.getRuta();
		} else {
			return NavegacionEnum.DIARIO_BUSQUEDA.getRuta();
		}
	}

	/**
	 * Funcion para cerrar detalle cuando hubo un error en la consulta del
	 * detalle
	 */
	public void cerrarDetalle2() {
		obtieneFlash()
				.put(ParametrosFlashEnum.DIARIO_ELECTRONICO_BUSQUEDA
						.getParamFlash(),
						this.getDiarioBusquedaBean());
		try {
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect(NavegacionEnum.DIARIO_TABLA.getRuta());
		} catch (IOException e) {
			LOGGER.debug("Error al redireccionar la vista");
		}
	}

	/**
	 * Funcion que calcula el total de haber en la lista de operaciones
	 * 
	 * @return
	 */
	public BigDecimal calculaTotalDeberHaber(String tipo) {
		BigDecimal total = BigDecimal.ZERO;
		if (diarioBeanList != null && !StringUtils.isBlank(tipo)) {
			tipo = tipo.toUpperCase().trim();
			for (DiarioElectronicoResultadoBean item : diarioBeanList) {
				String tipoDH = item.getDebeHaber().toUpperCase().trim();
				if (tipo.equals(tipoDH)) {
					total = opBigDecimal(total, item.getImporte(), true);
				}
			}
		}

		return total;
	}

	/**
	 * Operacion BigDecimal MÃ¨todo para realizar sumas y restas entre
	 * BigDecimals
	 * 
	 * @param value
	 *            Valor base
	 * @param augend
	 *            Valor a sumar o restarse
	 * @param suma
	 *            boolean para definir si es suma o resta
	 * @return BigDecimal con resultado de la operaciÃ²n.
	 */
	public BigDecimal opBigDecimal(BigDecimal value, BigDecimal augend,
			Boolean suma) {
		if (value == null) {
			value = BigDecimal.ZERO;
		}

		if (augend == null) {
			augend = BigDecimal.ZERO;
		}
		if (suma) {
			return value.add(augend);
		} else {
			return value.add(augend.negate());
		}
	}

	/**
	 * Asigna los atributos necesarios del apunte.
	 * 
	 * @return
	 */
	private ApuntesBean crearApunte() {
		ApuntesBean apunte = new ApuntesBean();
		apunte.setCentro(this.selectedRow.getCentro());
		apunte.setFechaValor(this.selectedRow.getFechaValor());
		apunte.setCodigoOperacion(this.selectedRow.getTipoOperacion());
		apunte.setFechaOperacion(this.selectedRow.getFechaOperacion());
		apunte.setFechaValor(this.selectedRow.getFechaValor());
		apunte.setHoraOperacion(this.selectedRow.getHoraFin());
		apunte.setImporte(this.selectedRow.getImporte());
		apunte.setNumCuenta(Long.parseLong(this.selectedRow.getCuenta()));
		apunte.setNumTransaccion(this.selectedRow.getNumSecuencia());
		apunte.setPuesto(this.selectedRow.getPuesto());
		apunte.setTerminal(this.selectedRow.getTerminal());
		apunte.setTipoApunte(this.selectedRow.getModo());
		apunte.setTipoOperacion(this.selectedRow.getTipoOperacion());
		apunte.setTitular(this.selectedRow.getUsuario());
		return apunte;
	}

	// public void exportarExcel() throws ControlableException,
	// NoControlableException, IOException {
	// this.wordUtils.generaExcel("prueba", this.diarioBeanList);
	// }

	public DiarioElectronicoBusquedaBean getDiarioBusquedaBean() {
		return diarioBusquedaBean;
	}

	public void setDiarioBusquedaBean(DiarioElectronicoBusquedaBean diarioBean) {
		this.diarioBusquedaBean = diarioBean;
	}

	/**
	 * @return the diarioBeanList
	 */
	public List<DiarioElectronicoResultadoBean> getDiarioBeanList() {
		return diarioBeanList;
	}

	/**
	 * @param diarioBeanList
	 *            the diarioBeanList to set
	 */
	public void setDiarioBeanList(
			List<DiarioElectronicoResultadoBean> diarioBeanList) {
		this.diarioBeanList = diarioBeanList;
	}

	/**
	 * @return the diarioBeanTable
	 */
	public DiarioElectronicoResultadoBean getDiarioBeanTable() {
		return diarioBeanTable;
	}

	/**
	 * @param diarioBeanTable
	 *            the diarioBeanTable to set
	 */
	public void setDiarioBeanTable(
			DiarioElectronicoResultadoBean diarioBeanTable) {
		this.diarioBeanTable = diarioBeanTable;
	}

	public ApuntesBean getApuntesBean() {
		return apuntesBean;
	}

	public void setApuntesBean(ApuntesBean apuntesBean) {
		this.apuntesBean = apuntesBean;
	}

	public NavegacionEnum getDestino() {
		return destino;
	}

	public void setDestino(NavegacionEnum destino) {
		this.destino = destino;
	}

	public Object getDestinoController() {
		return destinoController;
	}

	public void setDestinoController(Object destinoController) {
		this.destinoController = destinoController;
	}

	public boolean isBusquedaApunte() {
		return busquedaApunte;
	}

	public void setBusquedaApunte(boolean busquedaApunte) {
		this.busquedaApunte = busquedaApunte;
	}

	public boolean isErrorFiltros() {
		return errorFiltros;
	}

	public void setErrorFiltros(boolean errorFiltros) {
		this.errorFiltros = errorFiltros;
	}

	public DiarioElectronicoResultadoBean getSelectedRow() {
		return selectedRow;
	}

	public void setSelectedRow(DiarioElectronicoResultadoBean selectedRow) {
		this.selectedRow = selectedRow;
	}

	public DiarioElectronicoDetalleRespuestaBean getDiarioElectronicoDetalleBean() {
		return diarioElectronicoDetalleBean;
	}

	public void setDiarioElectronicoDetalleBean(
			DiarioElectronicoDetalleRespuestaBean diarioElectronicoDetalleBean) {
		this.diarioElectronicoDetalleBean = diarioElectronicoDetalleBean;
	}

	public ContextoUtils getContextoUtils() {
		return contextoUtils;
	}

	public void setContextoUtils(ContextoUtils contextoUtils) {
		this.contextoUtils = contextoUtils;
	}

	public List<DiarioElectronicoResultadoBean> getDiarioBeanListPagina() {
		return diarioBeanListPagina;
	}

	public void setDiarioBeanListPagina(
			List<DiarioElectronicoResultadoBean> diarioBeanListPagina) {
		this.diarioBeanListPagina = diarioBeanListPagina;
	}

	public int getTamanoPagina() {
		return tamanoPagina;
	}

	public void setTamanoPagina(int tamanoPagina) {
		this.tamanoPagina = tamanoPagina;
	}

	public int getnPagina() {
		return nPagina;
	}

	public void setnPagina(int nPagina) {
		this.nPagina = nPagina;
	}

}
