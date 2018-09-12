package mx.babel.bansefi.banksystem.cuentas.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

import mx.babel.arq.comun.beans.PaginacionBean;
import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.sesion.utils.ContextoUtils;
import mx.babel.bansefi.banksystem.base.backends.ConsultaDomiciliosPersonaBackEnd;
import mx.babel.bansefi.banksystem.base.backends.ConsultaRelacionPersonaCuentaBackEnd;
import mx.babel.bansefi.banksystem.base.backends.consultadomicilio.ConsultaDomicilioBackend;
import mx.babel.bansefi.banksystem.base.beans.domain.CuentaBean;
import mx.babel.bansefi.banksystem.base.beans.domain.DomicilioTipoBean;
import mx.babel.bansefi.banksystem.base.beans.domain.relacioncuenta.RelacionadoBean;
import mx.babel.bansefi.banksystem.base.enums.CatalogoEnum;
import mx.babel.bansefi.banksystem.base.enums.DialogoListadoEnum;
import mx.babel.bansefi.banksystem.base.enums.EstadoListadosEnum;
import mx.babel.bansefi.banksystem.base.enums.EstadosCuentaEnum;
import mx.babel.bansefi.banksystem.base.enums.NavegacionEnum;
import mx.babel.bansefi.banksystem.base.enums.ParametrosFlashEnum;
import mx.babel.bansefi.banksystem.base.enums.TipoDomicilioEnum;
import mx.babel.bansefi.banksystem.base.utils.BeanBackUpUtils;
import mx.babel.bansefi.banksystem.base.utils.CatalogoCentrosLoaderTask;
import mx.babel.bansefi.banksystem.base.utils.CatalogoUtils;
import mx.babel.bansefi.banksystem.base.utils.DialogoListadoUtils;
import mx.babel.bansefi.banksystem.base.utils.DomicilioWrapper;
import mx.babel.bansefi.banksystem.base.utils.ManagedBeanStateRecover;
import mx.babel.bansefi.banksystem.cuentas.backends.AltaDomicilioCuentaBackEnd;
import mx.babel.bansefi.banksystem.cuentas.backends.ConsultaDomiciliosCuentaBackEnd;
import mx.babel.bansefi.banksystem.cuentas.backends.ModificaDomicilioCuentaBackEnd;
import mx.babel.bansefi.banksystem.cuentas.beans.DomicilioCuentaBean;

import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Controlador encargado de gestionar la vista de domicilios de cuenta.
 * 
 * @author mario.montesdeoca
 * 
 */
@ManagedBean(name = "domiciliosCuentaController")
@Component
@Scope("view")
public class DomiciliosCuentaController implements Serializable {

	private static final long serialVersionUID = 8730579341695949355L;
	
	@Autowired
	private CatalogoUtils catalogos;
	
	@Autowired
	ContextoUtils contextoUtils;

	@Autowired
	DomicilioWrapper domicilioWrapper;

	@Autowired
	ConsultaDomiciliosCuentaBackEnd consultaDomiciliosCuentaBackEnd;
	@Autowired
	ConsultaRelacionPersonaCuentaBackEnd consultaRelacionPersonaCuentaBackEnd;
	@Autowired
	ConsultaDomiciliosPersonaBackEnd consultaDomiciliosPersonaBackEnd;
	@Autowired
	ConsultaDomicilioBackend consultaDomicilioBackend;
	@Autowired
	AltaDomicilioCuentaBackEnd altaDomicilioCuentaBackEnd;
	@Autowired
	ModificaDomicilioCuentaBackEnd modificaDomicilioCuentaBackEnd;
	@Autowired
	ManagedBeanStateRecover managedBeanStateRecover;
	@Autowired
	private BeanBackUpUtils beanBackupUtils;
	@Autowired
	CatalogoCentrosLoaderTask catalogoCentros;

	private PaginacionBean paginacionDomiciliosPersona;
	private CuentaBean cuentaBean;
	private List<DomicilioCuentaBean> domiciliosCuenta;
	private List<DomicilioTipoBean> domiciliosRelacionado;
	private RelacionadoBean relacionadoSeleccionado;
	private DomicilioTipoBean domicilioSeleccionado;
	private Boolean muestraDialogoMuchosDomicilios;
	private Boolean muestraDialogoSinDomicilios;
	private Boolean mostrarPanelAnadir;
	private Boolean mostrarRelacionadosDomicilios;
	private Boolean muestraDialogoConfirmacion;
	private Boolean muestraDialogoResultado;
	private boolean datosAgregados;
	private boolean datosEliminados;
	private boolean datosGuardados;
	private int domiciliosEliminados;

	private DialogoListadoUtils dialogoUtils;

	private DialogoListadoEnum dialogoGuardado;

	private DialogoListadoEnum mensajeEliminados;

	/**
	 * Variable utilizada para guardar la redireccion al flujo de entrada a la
	 * consulta de detalles de anotaciones.
	 */
	private NavegacionEnum destino;

	/**
	 * Variable utilizada para guardar el contenido del controller origen a la
	 * consulta de detalles de anotaciones.
	 */
	private Object destinoController;

	/**
	 * @return Atributo cuentaBean
	 */
	public CuentaBean getCuentaBean() {
		return cuentaBean;
	}

	/**
	 * @param cuentaBean
	 *            Atributo cuentaBean a definir
	 */
	public void setCuentaBean(CuentaBean cuentaBean) {
		this.cuentaBean = cuentaBean;
	}

	/**
	 * @return Atributo domiciliosCuenta
	 */
	public List<DomicilioCuentaBean> getDomiciliosCuenta() {
		if (domiciliosCuenta == null) {
			domiciliosCuenta = new ArrayList<DomicilioCuentaBean>();
		}
		return domiciliosCuenta;
	}

	/**
	 * @param domiciliosCuenta
	 *            Atributo domiciliosCuenta a definir
	 */
	public void setDomiciliosCuenta(List<DomicilioCuentaBean> domiciliosCuenta) {
		this.domiciliosCuenta = domiciliosCuenta;
	}

	/**
	 * @return Atributo domiciliosRelacionado
	 */
	public List<DomicilioTipoBean> getDomiciliosRelacionado() {
		return domiciliosRelacionado;
	}

	/**
	 * @param domiciliosRelacionado
	 *            Atributo domiciliosRelacionado a definir
	 */
	public void setDomiciliosRelacionado(
			List<DomicilioTipoBean> domiciliosRelacionado) {
		this.domiciliosRelacionado = domiciliosRelacionado;
	}

	/**
	 * @return Atributo relacionadoSeleccionado
	 */
	public RelacionadoBean getRelacionadoSeleccionado() {
		return relacionadoSeleccionado;
	}

	/**
	 * @param relacionadoSeleccionado
	 *            Atributo relacionadoSeleccionado a definir
	 */
	public void setRelacionadoSeleccionado(
			RelacionadoBean relacionadoSeleccionado) {
		this.relacionadoSeleccionado = relacionadoSeleccionado;
	}

	/**
	 * @return Atributo domicilioSeleccionado
	 */
	public DomicilioTipoBean getDomicilioSeleccionado() {
		return domicilioSeleccionado;
	}

	/**
	 * @param domicilioSeleccionado
	 *            Atributo domicilioSeleccionado a definir
	 */
	public void setDomicilioSeleccionado(DomicilioTipoBean domicilioSeleccionado) {
		this.domicilioSeleccionado = domicilioSeleccionado;
	}

	/**
	 * @return Atributo muestraDialogoMuchosDomicilios
	 */
	public Boolean getMuestraDialogoMuchosDomicilios() {
		return muestraDialogoMuchosDomicilios;
	}

	/**
	 * @param muestraDialogoMuchosDomicilios
	 *            Atributo muestraDialogoMuchosDomicilios a definir
	 */
	public void setMuestraDialogoMuchosDomicilios(
			Boolean muestraDialogoMuchosDomicilios) {
		this.muestraDialogoMuchosDomicilios = muestraDialogoMuchosDomicilios;
	}

	/**
	 * @return Atributo muestraDialogoSinDomicilios
	 */
	public Boolean getMuestraDialogoSinDomicilios() {
		return muestraDialogoSinDomicilios;
	}

	/**
	 * @param muestraDialogoSinDomicilios
	 *            Atributo muestraDialogoSinDomicilios a definir
	 */
	public void setMuestraDialogoSinDomicilios(
			Boolean muestraDialogoSinDomicilios) {
		this.muestraDialogoSinDomicilios = muestraDialogoSinDomicilios;
	}

	/**
	 * @return Atributo mostrarPanelAnadir
	 */
	public Boolean getMostrarPanelAnadir() {
		return mostrarPanelAnadir;
	}

	/**
	 * @param mostrarPanelAnadir
	 *            Atributo mostrarPanelAnadir a definir
	 */
	public void setMostrarPanelAnadir(Boolean mostrarPanelAnadir) {
		this.mostrarPanelAnadir = mostrarPanelAnadir;
	}

	/**
	 * @return Atributo mostrarRelacionadosDomicilios
	 */
	public Boolean getMostrarRelacionadosDomicilios() {
		return mostrarRelacionadosDomicilios;
	}

	/**
	 * @param mostrarRelacionadosDomicilios
	 *            Atributo mostrarRelacionadosDomicilios a definir
	 */
	public void setMostrarRelacionadosDomicilios(
			Boolean mostrarRelacionadosDomicilios) {
		this.mostrarRelacionadosDomicilios = mostrarRelacionadosDomicilios;
	}

	/**
	 * @return Atributo muestraDialogoConfirmacion
	 */
	public Boolean getMuestraDialogoConfirmacion() {
		return muestraDialogoConfirmacion;
	}

	/**
	 * @param muestraDialogoConfirmacion
	 *            Atributo muestraDialogoConfirmacion a definir
	 */
	public void setMuestraDialogoConfirmacion(Boolean muestraDialogoConfirmacion) {
		this.muestraDialogoConfirmacion = muestraDialogoConfirmacion;
	}

	/**
	 * @return Atributo muestraDialogoResultado
	 */
	public Boolean getMuestraDialogoResultado() {
		return muestraDialogoResultado;
	}

	/**
	 * @param muestraDialogoResultado
	 *            Atributo muestraDialogoResultado a definir
	 */
	public void setMuestraDialogoResultado(Boolean muestraDialogoResultado) {
		this.muestraDialogoResultado = muestraDialogoResultado;
	}

	public DialogoListadoEnum getDialogoGuardado() {
		return dialogoGuardado;
	}

	public void setDialogoGuardado(DialogoListadoEnum dialogoGuardado) {
		this.dialogoGuardado = dialogoGuardado;
	}

	public DialogoListadoEnum getMensajeEliminados() {
		return mensajeEliminados;
	}

	public void setMensajeEliminados(DialogoListadoEnum mensajeEliminados) {
		this.mensajeEliminados = mensajeEliminados;
	}

	/**
	 * @return Atributo destino
	 */
	public NavegacionEnum getDestino() {
		return destino;
	}

	/**
	 * @param destino
	 *            Atributo destino a definir
	 */
	public void setDestino(NavegacionEnum destino) {
		this.destino = destino;
	}

	/**
	 * @return Atributo destinoController
	 */
	public Object getDestinoController() {
		return destinoController;
	}

	/**
	 * @param destinoController
	 *            Atributo destinoController a definir
	 */
	public void setDestinoController(Object destinoController) {
		this.destinoController = destinoController;
	}

	@PostConstruct
	public void init() {
		refreshEnums();
		if (this.obtieneFlash().get(
				ParametrosFlashEnum.NAVEGACION_MANAGED_BEAN.getParamFlash()) != null) {
			if ((Boolean) this.obtieneFlash()
					.get(ParametrosFlashEnum.NAVEGACION_MANAGED_BEAN
							.getParamFlash())) {
				destino = managedBeanStateRecover.getDestino();
				destinoController = managedBeanStateRecover.getController();
			}
		}

		if (this.obtieneFlash().get(
				ParametrosFlashEnum.CUENTA_BEAN.getParamFlash()) != null) {
			this.cuentaBean = (CuentaBean) this.obtieneFlash().get(
					ParametrosFlashEnum.CUENTA_BEAN.getParamFlash());
			this.domiciliosCuenta = consultaDomiciliosCuentaBackEnd
					.ejecutarTRN(cuentaBean.getNumeroCuenta());
			for (DomicilioCuentaBean domicilioCuentaBean : this.domiciliosCuenta) {
				domicilioCuentaBean
						.setEstadoLista(EstadoListadosEnum.CONSULTADO);
			}
			beanBackupUtils
					.respaldaArray((ArrayList<DomicilioCuentaBean>) this.domiciliosCuenta);
			this.datosAgregados = false;
			this.datosEliminados = false;
			this.datosGuardados = false;
			if (this.obtieneFlash().get("recarga") != null) {
				if ((Boolean) this.obtieneFlash().get("recarga")) {
					if ((Boolean) this.obtieneFlash().get("recarga")) {
						if ((Boolean) this.obtieneFlash().get("datosAgregados")
								&& !(Boolean) this.obtieneFlash().get(
										"datosEliminados")) {
							this.dialogoGuardado = DialogoListadoEnum.ALTA;
							this.dialogoGuardado
									.setMensaje("Los domicilios se dieron de alta correctamente.");
							this.dialogoGuardado.setMostrar(true);
						} else if (!(Boolean) this.obtieneFlash().get(
								"datosAgregados")
								&& (Boolean) this.obtieneFlash().get(
										"datosEliminados")) {
							this.dialogoGuardado = DialogoListadoEnum.ALTA;
							this.dialogoGuardado
									.setMensaje("Los domicilios se eliminaron correctamente.");
							this.dialogoGuardado.setMostrar(true);
						} else {
							this.dialogoGuardado = DialogoListadoEnum.ALTA;
							this.dialogoGuardado
									.setMensaje("Los cambios se guardaron correctamente.");
							this.dialogoGuardado.setMostrar(true);
						}
					}
				}
			}

		}
	}
	
	private void refreshEnums(){
		DialogoListadoEnum.CONFIRMA_ELIMINAR.setMostrar(false);
		DialogoListadoEnum.ALERTA.setMostrar(false);
		DialogoListadoEnum.ALTA.setMostrar(false);
		DialogoListadoEnum.ELIMINAR.setMostrar(false);
		DialogoListadoEnum.ERROR.setMostrar(false);
		DialogoListadoEnum.MODIFICACION.setMostrar(false);
		DialogoListadoEnum.SIN_CAMBIOS.setMostrar(false);
		DialogoListadoEnum.CONFIRMA_ELIMINAR.setMensaje("");
		DialogoListadoEnum.ALERTA.setMensaje("");
		DialogoListadoEnum.ALTA.setMensaje("");
		DialogoListadoEnum.ELIMINAR.setMensaje("");
		DialogoListadoEnum.ERROR.setMensaje("");
		DialogoListadoEnum.MODIFICACION.setMensaje("");
		DialogoListadoEnum.SIN_CAMBIOS.setMensaje("");
	}

	public void accionRestaurar(DomicilioCuentaBean domicilioCuentaBean) {
		if(domicilioCuentaBean != null){
			domicilioCuentaBean.setEstadoLista(EstadoListadosEnum.CONSULTADO);
			this.domiciliosEliminados--;
		}
	}

	public void accionEliminar(DomicilioCuentaBean domicilioCuentaBean) {
		if(domicilioCuentaBean != null){
			if (domicilioCuentaBean.getEstadoLista() == EstadoListadosEnum.NUEVO) {
				this.domiciliosCuenta.remove(domicilioCuentaBean);
			} else {
				domicilioCuentaBean.setEstadoLista(EstadoListadosEnum.ELIMINADO);
				this.domiciliosEliminados++;
			}
		}
	}

	public void editarDomicilio(DomicilioCuentaBean domicilioCuentaBean) {
		if(domicilioCuentaBean !=  null){
			domicilioCuentaBean.setEstadoLista(EstadoListadosEnum.MODIFICADO);
			masDatos(domicilioCuentaBean);
		}
	}

	public void editarDomicilioNuevo(DomicilioCuentaBean domicilioCuentaBean) {
		if(domicilioCuentaBean != null){
			domicilioCuentaBean.setModificarCentro(true);
		}
	}

	public void restaurarModificado(DomicilioCuentaBean domicilioCuentaBean) {
		if(domicilioCuentaBean != null){
			domicilioCuentaBean.setEstadoLista(EstadoListadosEnum.CONSULTADO);
			recuperarBeanDatos(domicilioCuentaBean);
		}
	}

	/**
	 * Método que permite recuperar los datos serializados en cada bean de datos
	 * dentro del listado.
	 * 
	 * @param objeto
	 */
	public void recuperarBeanDatos(Object objeto) {
		if (objeto != null) {
			beanBackupUtils.recuperaBean(objeto);
		}
	}

	public String guardar() throws NoControlableException {
		if (getDomiciliosCuenta().size() > 2) {
			setMuestraDialogoMuchosDomicilios(true);
			return null;
		} else if (getDomiciliosCuenta().size() == this.domiciliosEliminados) {
			setMuestraDialogoSinDomicilios(true);
			return null;
		} else if (this.domiciliosEliminados > 0) {
			this.mensajeEliminados = DialogoListadoEnum.ELIMINAR;
			this.mensajeEliminados.setMensaje(" " + domiciliosEliminados
					+ " domicilios");
			this.mensajeEliminados.setMostrar(true);
			return null;
		} else {
			return confirmaGuardado();
		}
	}

	public String confirmaGuardado() throws NoControlableException {
		boolean respuesta = false;
		try {
			for (DomicilioCuentaBean domicilioCuentaBean : getDomiciliosCuenta()) {
				if (domicilioCuentaBean.getEstadoLista().equals(
						EstadoListadosEnum.NUEVO)) {
					respuesta = altaDomicilios(respuesta, domicilioCuentaBean);
				}
				if (domicilioCuentaBean.getEstadoLista().equals(
						EstadoListadosEnum.ELIMINADO)) {
					respuesta = bajaDomicilios(respuesta, domicilioCuentaBean);
				}
				if (domicilioCuentaBean.getEstadoLista().equals(
						EstadoListadosEnum.MODIFICADO)) {
					respuesta = modificaDomicilios(respuesta,
							domicilioCuentaBean);
				}
			}
			if (respuesta) {
				return recargaPagina();
			} else {
				this.dialogoGuardado = DialogoListadoEnum.SIN_CAMBIOS;
				this.dialogoGuardado.setMensaje("No se han realizado cambios");
				this.dialogoGuardado.setMostrar(true);
			}
		} catch (ControlableException c) {
			throw c;
		}

		return null;
	}

	private boolean altaDomicilios(boolean respuesta,
			DomicilioCuentaBean domicilioCuentaBean) {
		respuesta = altaDomicilioCuentaBackEnd.ejecutarTRN(
				this.cuentaBean.getNumeroCuenta(), domicilioCuentaBean);
		this.datosAgregados = true;
		return respuesta;
	}

	private boolean bajaDomicilios(boolean respuesta,
			DomicilioCuentaBean domicilioCuentaBean) {
		domicilioCuentaBean.setFechaCierre(contextoUtils
				.getFechaContableActual());
		respuesta = modificaDomicilioCuentaBackEnd.ejecutarTRN(
				this.cuentaBean.getNumeroCuenta(), domicilioCuentaBean);
		this.datosEliminados = true;
		return respuesta;
	}

	private boolean modificaDomicilios(boolean respuesta,
			DomicilioCuentaBean domicilioCuentaBean) {
		domicilioCuentaBean.setFechaCierre(contextoUtils
				.getFechaContableActual());
		respuesta = modificaDomicilioCuentaBackEnd.ejecutarTRN(
				this.cuentaBean.getNumeroCuenta(), domicilioCuentaBean);
		respuesta = altaDomicilioCuentaBackEnd.ejecutarTRN(
				this.cuentaBean.getNumeroCuenta(), domicilioCuentaBean);
		this.datosGuardados = true;
		return respuesta;
	}

	private String recargaPagina() {
		obtieneFlash().put("datosEliminados", this.datosEliminados);
		obtieneFlash().put("datosAgregados", this.datosAgregados);
		obtieneFlash().put("recarga", true);
		this.obtieneFlash().put(
				ParametrosFlashEnum.CUENTA_BEAN.getParamFlash(),
				this.cuentaBean);
		this.obtieneFlash().put(
				ParametrosFlashEnum.CONTROLLER_DESTINO.getParamFlash(),
				destinoController);
		this.obtieneFlash().put(
				ParametrosFlashEnum.NAVEGACION_BUSQUEDA.getParamFlash(),
				destino);
		this.obtieneFlash().put(
				ParametrosFlashEnum.NAVEGACION_MANAGED_BEAN.getParamFlash(),
				true);
		return NavegacionEnum.DOMICILIOS_CUENTA.getRuta();
	}

	public void anadir() {
		if (domicilioSeleccionado != null) {
			DomicilioCuentaBean domicilioCuentaBean = new DomicilioCuentaBean();
			domicilioWrapper.wrappDomicilio(domicilioCuentaBean,
					domicilioSeleccionado);
			domicilioCuentaBean.setNumeroDireccion(domicilioSeleccionado
					.getNumeroDireccion());
			domicilioCuentaBean.setNombreLocalidad(domicilioSeleccionado.getCodigoPostalCatGeo().getNombreLocalidad());
			domicilioCuentaBean.setCatalogoCentro(catalogoCentros
					.getCatalogoBean(contextoUtils.getEntidad(),
							contextoUtils.getSucursal()));
			domicilioCuentaBean.setActivo(true);
			domicilioCuentaBean.setMasDatos(false);
			domicilioCuentaBean.setNuevo(true);
			domicilioCuentaBean.setEstadoLista(EstadoListadosEnum.NUEVO);
			domicilioCuentaBean.setDomicilio(domicilioSeleccionado
					.getNombreCalle());
			domicilioCuentaBean.setTipoDomicilio(domicilioSeleccionado
					.getGrouping());
			if (relacionadoSeleccionado != null && relacionadoSeleccionado.getPersona() != null) {
				domicilioCuentaBean.setNombreCliente(relacionadoSeleccionado
						.getPersona().getNombreCompleto());
				domicilioCuentaBean.setIdPersona(relacionadoSeleccionado
						.getPersona().getIdInterna());
			}
			domicilioCuentaBean.setFechaInicio(contextoUtils.getFechaContableActual());
			getDomiciliosCuenta().add(domicilioCuentaBean);
			setMostrarPanelAnadir(false);
		}
	}

	public Boolean verificaDomiciliosActivos() {
		int numDomicilios = 0;
		for (DomicilioCuentaBean domicilio : getDomiciliosCuenta()) {
			if (domicilio.getActivo()) {
				numDomicilios++;
			}
		}
		if (numDomicilios == 0) {
			setMuestraDialogoSinDomicilios(true);
			return false;
		}
		if (numDomicilios > 2) {
			setMuestraDialogoMuchosDomicilios(true);
			return false;
		}
		return true;
	}

	public void visibilidadRelacionados() {
		if (mostrarPanelAnadir == null) {
			setMostrarPanelAnadir(true);
			if (cuentaBean.getPersonasRelacionadas().isEmpty()) {
				this.cuentaBean
						.setPersonasRelacionadas(consultaRelacionPersonaCuentaBackEnd
								.ejecutarTRN(this.cuentaBean.getNumeroCuenta(),
										false));
			}
		} else {
			setMostrarPanelAnadir(!mostrarPanelAnadir);
		}
		setMostrarRelacionadosDomicilios(true);
	}

	public void buscaDomicilios(SelectEvent event) {
		setMostrarRelacionadosDomicilios(false);
		relacionadoSeleccionado = (RelacionadoBean) event.getObject();
		setDomicilioSeleccionado(null);
		setDomiciliosRelacionado(consultaDomiciliosPersonaBackEnd.ejecutarTRN(
				relacionadoSeleccionado.getPersona().getIdInterna(),
				paginacionDomiciliosPersona));
	}

	public void seleccionaDomicilio(SelectEvent event) {
		domicilioSeleccionado = (DomicilioTipoBean) event.getObject();
	}

	public String getTipo(List<TipoDomicilioEnum> tipos) {
		String tipoDomicilioString = "";
		for (TipoDomicilioEnum tipoDomicilio : tipos) {
			tipoDomicilioString = tipoDomicilioString
					+ tipoDomicilio.getTipoDomicilio() + ", ";
		}
		return tipoDomicilioString;
	}

	public void masDatos(DomicilioCuentaBean domicilioCuentaBean) {
		if(domicilioCuentaBean != null && domicilioCuentaBean.getMasDatos() != null){
			if (domicilioCuentaBean.getMasDatos()) {
				domicilioCuentaBean.setMasDatos(false);
			} else {
				domicilioCuentaBean.setMasDatos(true);
			}
			if (!domicilioCuentaBean.getConsultado()) {
				domicilioWrapper.wrappDomicilio(domicilioCuentaBean,
						consultaDomicilioBackend.ejectuarTRN(null,
								domicilioCuentaBean.getNumeroDireccion()));
				domicilioCuentaBean.setTipoCalle(catalogos.getCatalogoDesc(CatalogoEnum.TP_VIA, domicilioCuentaBean.getTipoCalle()));
				domicilioCuentaBean.setRegimenOcupacion(catalogos.getCatalogoDesc(CatalogoEnum.TP_OCUPACION_VIV, domicilioCuentaBean.getRegimenOcupacion()));
				domicilioCuentaBean.setConsultado(true);
			}
		}
	}

	public String volverFicha() {
		obtieneFlash().put(ParametrosFlashEnum.CUENTA_BEAN.getParamFlash(),
				this.cuentaBean);
		String rutaDestino = null;
		if (destino == null) {
			rutaDestino = NavegacionEnum.FICHA_CUENTA.getRuta();
		} else {
			rutaDestino = destino.getRuta();
			managedBeanStateRecover.finNavegacion(destinoController);
		}
		return rutaDestino;
	}

	/**
	 * Método privado que obtiene el objeto Flash proveniente del contexto
	 * externo.
	 * 
	 * @return Flash
	 */
	private Flash obtieneFlash() {
		return FacesContext.getCurrentInstance().getExternalContext()
				.getFlash();
	}
	
	/**
	 * Funcion que indica si la cuenta se encuentra
	 * en estado activo
	 * @return
	 */
	public boolean isCuentaActiva(){
		if(this.cuentaBean != null && this.cuentaBean.getEstadoEnum() != null){
			if(this.cuentaBean.getEstadoEnum().equals(EstadosCuentaEnum.CANCELADO)){
				return false;
			}
			
		}
		
		return true;
	}
}
