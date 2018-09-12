package mx.babel.bansefi.banksystem.personas.controllers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

import mx.babel.arq.catalogo.beans.CatalogoBean;
import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.bansefi.banksystem.base.beans.domain.ClienteBean;
import mx.babel.bansefi.banksystem.base.beans.domain.TipoCliente;
import mx.babel.bansefi.banksystem.base.constants.ConstantesFuncionales;
import mx.babel.bansefi.banksystem.base.enums.CatalogoEnum;
import mx.babel.bansefi.banksystem.base.enums.DialogoListadoEnum;
import mx.babel.bansefi.banksystem.base.enums.EstadoListadosEnum;
import mx.babel.bansefi.banksystem.base.enums.NavegacionEnum;
import mx.babel.bansefi.banksystem.base.enums.ParametrosFlashEnum;
import mx.babel.bansefi.banksystem.base.utils.BeanBackUpUtils;
import mx.babel.bansefi.banksystem.base.utils.CatalogoUtils;
import mx.babel.bansefi.banksystem.personas.utils.DialogoPersonasListadoUtils;
import mx.babel.bansefi.banksystem.base.utils.ManagedBeanStateRecover;
import mx.babel.bansefi.banksystem.personas.backend.AltaOtraIdentificacionPersonaBackend;
import mx.babel.bansefi.banksystem.personas.backend.AltaOtroNombrePersonaBackend;
import mx.babel.bansefi.banksystem.personas.backend.AltaOtroTelFaxPersonaBackend;
import mx.babel.bansefi.banksystem.personas.backend.BajaOtraIdentificacionPersonaBackend;
import mx.babel.bansefi.banksystem.personas.backend.BajaOtroNombrePersonaBackend;
import mx.babel.bansefi.banksystem.personas.backend.BajaOtroTelFaxPersonaBackend;
import mx.babel.bansefi.banksystem.personas.backend.ConsultaOtrasIdentificacionesPersonaBackend;
import mx.babel.bansefi.banksystem.personas.backend.ConsultaOtrosNombresPersonaBackend;
import mx.babel.bansefi.banksystem.personas.backend.ConsultaOtrosTelFaxPersonaBackend;
import mx.babel.bansefi.banksystem.personas.backend.ModificaOtraIdentificacionPersonaBackend;
import mx.babel.bansefi.banksystem.personas.backend.ModificaOtroNombrePersonaBackend;
import mx.babel.bansefi.banksystem.personas.backend.ModificaOtroTelFaxPersonaBackend;
import mx.babel.bansefi.banksystem.personas.beans.OrigenIngresosBean;
import mx.babel.bansefi.banksystem.personas.beans.OtroValorBean;
import mx.babel.bansefi.banksystem.personas.enums.BeanCrudEnum;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Controller encargado de las operaciones sobre otros datos de personas.
 * 
 */
@ManagedBean(name = "otrosDatosController")
@Component
@Scope("view")
public class OtrosDatosController {

	private static final String ID_CLIENTE = "cliente";

	/**
	 * Enumerado utilizado para discernir entre los diferentes otros datos.
	 * 
	 */
	private enum TipoOtros {
		NOMBRE, IDS, CONTACTOS
	};
	
	private static final Logger LOGGER = LogManager.getLogger(OtrosDatosController.class.getName());

	private ClienteBean cliente;
	private CatalogoBean tipoNombre;
	private CatalogoBean tipoId;
	private CatalogoBean tipoContacto;
	private List<CatalogoBean> listaNombres;
	private List<CatalogoBean> listaIndentificaciones;
	private List<OtroValorBean> otrosNombresLista;
	private List<OtroValorBean> otrosIdsLista;
	private List<OtroValorBean> otrosContactosLista;
	private boolean datosAgregados;
	private boolean datosGuardados;
	private boolean datosEliminados;
	private int valoresBorrados;
	private int totalNombres;
	private int totalIds;
	private int totalContactos;
	private int totalModif;
	private String tipoLista;
	private String claveTipoLista;
	private String descripcionTipoLista;
	private DialogoPersonasListadoUtils dialogoUtils;
	private DialogoListadoEnum dialogoGuardado;
	private DialogoListadoEnum mensajeEliminados;

	/**
	 * Enum para definir a donde se redireccionará al seleccionar un objeto de
	 * la tabla de resultados.
	 */
	private NavegacionEnum destino;
	private Object destinoController;

	@Autowired
	private CatalogoUtils catalogoUtils;
	@Autowired
	private ManagedBeanStateRecover managedBeanStateRecover;
	@Autowired
	private BeanBackUpUtils beanBackupUtils;

	// Backends de consulta
	@Autowired
	private ConsultaOtrasIdentificacionesPersonaBackend consultaOtrasIdentificacionesPersonaBackend;
	@Autowired
	private ConsultaOtrosNombresPersonaBackend consultaOtrosNombresPersonaBackend;
	@Autowired
	private ConsultaOtrosTelFaxPersonaBackend consultaOtrosTelFaxPersonaBackend;

	// Backends de alta
	@Autowired
	private AltaOtraIdentificacionPersonaBackend altaOtraIdentificacionPersonaBackend;
	@Autowired
	private AltaOtroNombrePersonaBackend altaOtroNombrePersonaBackend;
	@Autowired
	private AltaOtroTelFaxPersonaBackend altaOtroTelFaxPersonaBackend;

	// Backends de baja
	@Autowired
	private BajaOtraIdentificacionPersonaBackend bajaOtraIdentificacionPersonaBackend;
	@Autowired
	private BajaOtroNombrePersonaBackend bajaOtroNombrePersonaBackend;
	@Autowired
	private BajaOtroTelFaxPersonaBackend bajaOtroTelFaxPersonaBackend;

	// Backends de modificacion
	@Autowired
	private ModificaOtroNombrePersonaBackend modificaciaOtroNombrePersonaBackend;
	@Autowired
	private ModificaOtraIdentificacionPersonaBackend modificaOtraIdentificacionPersonaBackend;
	@Autowired
	private ModificaOtroTelFaxPersonaBackend modificaOtroTelFaxPersonaBackend;

	/**
	 * Se inicializan las variables de la vista.
	 */
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
				initializeData();
			} else {
				managedBeanStateRecover.recuperaController(this);
			}
		} else {
			initializeData();
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
	
	private void initializeData() {
		this.cliente = (ClienteBean) obtieneFlash().get(
				OtrosDatosController.ID_CLIENTE);
		if (this.cliente == null) {
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "Otros Datos",
					"Se ha producido un error al obtener datos de la ficha cliente.");
			RequestContext.getCurrentInstance().showMessageInDialog(message);
		} else {
			final int idCliente = this.cliente.getIdInterna();
			this.setListaIndentificaciones(obtenerIdentificaciones());
			this.setListaNombres(obtenerNombres());
			if (StringUtils.equals(TipoCliente.PERSONA_MORAL.getTipo(),
					cliente.getTipoCliente())) {
				this.setTipoLista("N");
				try {
					this.otrosNombresLista = this.consultaOtrosNombresPersonaBackend
							.ejecutarTRN(idCliente);
					for (OtroValorBean valorBean : this.otrosNombresLista) {
						valorBean.setEstado(EstadoListadosEnum.CONSULTADO);
					}
					this.totalNombres = this.otrosNombresLista.size();
				} catch (final ControlableException ex) {
					if (ex.getRtncod() != 7) {
						throw ex;
					}
					this.otrosNombresLista = new ArrayList<OtroValorBean>();
					this.totalNombres = 0;
				}

				this.fillCatalogDescription(this.otrosNombresLista,
						TipoOtros.NOMBRE);

				beanBackupUtils
						.respaldaArray((ArrayList<OtroValorBean>) this.otrosNombresLista);
			} else {
					this.setTipoLista("I");
					try {
						this.otrosIdsLista = this.consultaOtrasIdentificacionesPersonaBackend
								.ejecutarTRN(idCliente);
						for (OtroValorBean valorBean : this.otrosIdsLista) {
							valorBean.setEstado(EstadoListadosEnum.CONSULTADO);
						}
						this.totalIds = this.otrosIdsLista.size();
					} catch (final ControlableException ex) {
						if (ex.getRtncod() != 7) {
							throw ex;
						}
						this.otrosIdsLista = new ArrayList<OtroValorBean>();
						this.totalIds = 0;
					}

					this.fillCatalogDescription(this.otrosIdsLista, TipoOtros.IDS);
					beanBackupUtils
							.respaldaArray((ArrayList<OtroValorBean>) this.otrosIdsLista);
			}
			
			try {
				this.otrosContactosLista = this.consultaOtrosTelFaxPersonaBackend
						.ejecutarTRN(idCliente);
				for (OtroValorBean valorBean : this.otrosContactosLista) {
					valorBean.setEstado(EstadoListadosEnum.CONSULTADO);
				}
				this.totalContactos = this.otrosContactosLista.size();
			} catch (final ControlableException ex) {
				if (ex.getRtncod() != 7) {
					throw ex;
				}
				this.otrosContactosLista = new ArrayList<OtroValorBean>();
				this.totalContactos = 0;
			}

			this.fillCatalogDescriptionContactos();
			beanBackupUtils
					.respaldaArray((ArrayList<OtroValorBean>) this.otrosContactosLista);

			this.datosAgregados = false;
			this.datosEliminados = false;
			this.datosGuardados = false;
		}

		if (this.obtieneFlash().get("recarga") != null) {
			if ((Boolean) this.obtieneFlash().get("recarga")) {
				if ((Boolean) this.obtieneFlash().get("recarga")) {
					if ((Boolean) this.obtieneFlash().get("datosAgregados")
							&& !(Boolean) this.obtieneFlash().get(
									"datosEliminados")
							&& !(Boolean) this.obtieneFlash().get(
									"datosGuardados")) {
						this.dialogoGuardado = DialogoListadoEnum.ALTA;
						this.dialogoGuardado
								.setMensaje("Los registros se dieron de alta correctamente.");
						this.dialogoGuardado.setMostrar(true);
					} else if (!(Boolean) this.obtieneFlash().get(
							"datosAgregados")
							&& (Boolean) this.obtieneFlash().get(
									"datosEliminados")
							&& !(Boolean) this.obtieneFlash().get(
									"datosGuardados")) {
						this.dialogoGuardado = DialogoListadoEnum.ALTA;
						this.dialogoGuardado
								.setMensaje("Los registros se eliminaron correctamente.");
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

	private void fillCatalogDescriptionContactos() {
		final List<CatalogoBean> catalogoLista = this.getCatalogoDirecciones();
		for (final OtroValorBean otroValorBean : this.otrosContactosLista) {
			for (final CatalogoBean catalogoBean : catalogoLista) {
				if (StringUtils.equalsIgnoreCase(otroValorBean.getClave(),
						catalogoBean.getClaveFila())) {
					otroValorBean
							.setDescripcion(catalogoBean.getDescripcionL());
					otroValorBean.setPintarEdicion(true);
				}
			
			}

			
			if("".equals(otroValorBean.getDescripcion()) || otroValorBean.getDescripcion() == null	){
				otroValorBean.setDescripcion("TELEFONO DOMICILIO");
				otroValorBean.setPintarEdicion(false);
			}
			
			// 2015 12 11
			if ("07".equals(otroValorBean.getClave()) && "TELEFONO_DOMICILIO".equals(otroValorBean.getDescripcion()) ){
				otroValorBean.setDescripcion("TELEFONO DOMICILIO");
				otroValorBean.setPintarEdicion(false);
			}			
			
		}
	}

	private void fillCatalogDescription(
			final List<OtroValorBean> otroValorLista, final TipoOtros tipoOtros) {
		if (CollectionUtils.isNotEmpty(otroValorLista)) {
			for (final OtroValorBean otroValorBean : otroValorLista) {
				if (StringUtils.isNotBlank(otroValorBean.getClave())) {
					otroValorBean.setDescripcion(this.getDescription(tipoOtros,
							otroValorBean.getClave()));
				}
			}
		}

	}
	
	private List<CatalogoBean> obtenerIdentificaciones() {
		LOGGER.debug("Obtenemos los valores de combo de identificaciones: ENTRADA");
		List<CatalogoBean> resultado = new ArrayList<>();
		
		List<CatalogoBean> resultadoBusqueda = catalogoUtils.getCatalogo(CatalogoEnum.TP_ID_EXT_PERS);
		
		for (int i=0;i<resultadoBusqueda.size();i++){
			try{
				if (Integer.parseInt(resultadoBusqueda.get(i).getClaveFila()) >= ConstantesFuncionales.COD_INICIO_IDENTFICACION_PERS_FISICAS){
					resultado.add(resultadoBusqueda.get(i));
				}
			}catch(NumberFormatException nfe){
				LOGGER.debug("Encontrado un registro con valor no numerico: "+resultadoBusqueda.get(i).getClaveFila()+ " no lo tratamos",nfe);
			}
		}
		LOGGER.debug("Obtenemos los valores de combo de identificaciones: SALIDA");
		return resultado;
	}
	
	
	private List<CatalogoBean> obtenerNombres() {
		LOGGER.debug("Obtenemos los valores de combo de identificaciones: ENTRADA");
		List<CatalogoBean> resultado = new ArrayList<>();
		
		List<CatalogoBean> resultadoBusqueda = catalogoUtils.getCatalogo(CatalogoEnum.TP_NOMB_PERS);
		
		for (int i=0;i<resultadoBusqueda.size();i++){
			try{
				if (Integer.parseInt(resultadoBusqueda.get(i).getClaveFila()) >= ConstantesFuncionales.COD_INICIO_NOMBRES_PERS_MORALES){
					resultado.add(resultadoBusqueda.get(i));
				}
			}catch(NumberFormatException nfe){
				LOGGER.debug("Encontrado un registro con valor no numerico: "+resultadoBusqueda.get(i).getClaveFila()+ " no lo tratamos",nfe);
			}
		}
		LOGGER.debug("Obtenemos los valores de combo de identificaciones: SALIDA");
		return resultado;
	}

	private String getDescription(final TipoOtros tipoOtros, final String clave) {
		CatalogoEnum catalogo = null;
		switch (tipoOtros) {
		case NOMBRE:
			catalogo = CatalogoEnum.TP_NOMB_PERS;
			break;
		case IDS:
			catalogo = CatalogoEnum.TP_ID_EXT_PERS;
			break;
		case CONTACTOS:
			catalogo = null;
			break;
		default:
			throw new NoControlableException(
					"Ha ocurrido un error al cargar Otros Datos",
					"Catalogo para el tipo de lista  " + tipoOtros.name()
							+ " no encontrado.");
		}
		if (catalogo != null) {
			return this.catalogoUtils.getCatalogoBean(catalogo, clave)
					.getDescripcionL();
		} else {
			return "";
		}
	}

	public void addOtroNombre(OtroValorBean otroValor) {
		this.otrosNombresLista.add(otroValor);

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

	/**
	 * Método que devuelve a su estado original un accionista modificado.
	 * 
	 * @param actividad
	 */
	public void restaurarModificado(OtroValorBean otroValorBean) {
		otroValorBean.setEstado(EstadoListadosEnum.CONSULTADO);
		recuperarBeanDatos(otroValorBean);
		this.totalModif--;
	}

	public void editarFila(final OtroValorBean otroValorBean) {
		otroValorBean.setValorAnterior(otroValorBean.getValor());
		otroValorBean.setEstado(EstadoListadosEnum.MODIFICADO);
		this.totalModif++;
	}

	public void removeOtroValorExistente(final OtroValorBean otroValorBean) {
		otroValorBean.deleteValue();
		otroValorBean.setEstado(EstadoListadosEnum.ELIMINADO);
		this.valoresBorrados++;
	}

	public void removeOtroNombre(final OtroValorBean otroValorBean) {
		this.otrosNombresLista.remove(otroValorBean);
	}

	public void restoreValueExistente(final OtroValorBean otroValorBean) {
		otroValorBean.restoreValue();
		otroValorBean.setEstado(EstadoListadosEnum.CONSULTADO);
		this.valoresBorrados--;
	}

	public void addOtroId(OtroValorBean otroValor) {
		this.otrosIdsLista.add(otroValor);
	}

	public void removeOtroId(final OtroValorBean otroValorBean) {
		this.otrosIdsLista.remove(otroValorBean);

	}

	public void addOtroContacto(OtroValorBean otroValor) {
		this.otrosContactosLista.add(otroValor);
		this.fillCatalogDescriptionContactos();
	}

	public void removeOtroContacto(final OtroValorBean otroValorBean) {
		this.otrosContactosLista.remove(otroValorBean);
	}

	public void addObjetoNuevo() {
		OtroValorBean otroValor = new OtroValorBean();
		otroValor.setOperacion(BeanCrudEnum.ALTA);
		otroValor.setEstado(EstadoListadosEnum.NUEVO);
		otroValor.setClave(this.claveTipoLista);
		switch (this.tipoLista) {
		case "N":
			otroValor.setDescripcion(this.getDescription(TipoOtros.NOMBRE,
					otroValor.getClave()));
			this.addOtroNombre(otroValor);
			break;
		case "I":
			otroValor.setDescripcion(this.getDescription(TipoOtros.IDS,
					otroValor.getClave()));
			boolean existe= false;
 			//16_12_2015 jjsl validacion para eviatr elementos duplicados 
			for( OtroValorBean item : this.otrosIdsLista)
			{
				if(otroValor.getClave().equals(item.getClave()))
				{ existe=true;
				 break;
				}
				
			}
				
				
			
			if(existe==false)
			{
			this.addOtroId(otroValor);
			}
			else
			{
				RequestContext.getCurrentInstance().execute("PF('dlgDestDuplicado').show()");
				break;
			}
			
			
			break;
		case "T":
			this.addOtroContacto(otroValor);
			break;
		default:
			throw new NoControlableException(
					"Ha ocurrido un error al añadir Otros Datos",
					"Estado de los datos sin operacion: "
							+ otroValor.getOperacion());
		}
	}

	public String recargarPagina() {
		obtieneFlash().put(OtrosDatosController.ID_CLIENTE, this.cliente);
		this.obtieneFlash().put(
				ParametrosFlashEnum.CONTROLLER_DESTINO.getParamFlash(),
				destinoController);
		this.obtieneFlash().put(
				ParametrosFlashEnum.NAVEGACION_BUSQUEDA.getParamFlash(),
				destino);
		this.obtieneFlash().put(
				ParametrosFlashEnum.NAVEGACION_MANAGED_BEAN.getParamFlash(),
				true);
		obtieneFlash().put("datosAgregados", this.datosAgregados);
		obtieneFlash().put("datosGuardados", this.datosGuardados);
		obtieneFlash().put("datosEliminados", this.datosEliminados);
		obtieneFlash().put("recarga", true);

		return NavegacionEnum.OTROS_DATOS.getRuta();
	}

	public String irAHome() {
		String ruta = "";
		if (destino == null) {
			ruta = NavegacionEnum.INICIO.getRuta();
		} else {
			ruta = destino.getRuta();
			managedBeanStateRecover.finNavegacion(destinoController);
		}
		return ruta;
	}

	public Flash obtieneFlash() {
		return FacesContext.getCurrentInstance().getExternalContext()
				.getFlash();
	}
	
	/**
	 * Valida si muestra el dialogo de cancelar.
	 */
	public String validarAccionCancelar() {
		boolean cambiosVentana = false;
		if (StringUtils.equals(TipoCliente.PERSONA_MORAL.getTipo(),
				cliente.getTipoCliente())) {
			if(this.otrosNombresLista.size() > this.totalNombres){
				cambiosVentana = true;
			}
		}else{
			if(this.otrosIdsLista.size() > this.totalIds){
				cambiosVentana = true;
			}
		}
		if (this.valoresBorrados > 0 || this.otrosContactosLista.size() > this.totalContactos 
				|| cambiosVentana || this.totalModif > 0) {
			RequestContext.getCurrentInstance().execute("PF('dlgCancelar').show();");
			return "";
		} else {
			return irAHome();	
		}
	}

	public String validarGuardar() {
		if (this.valoresBorrados > 0) {
			this.mensajeEliminados = DialogoListadoEnum.ELIMINAR;
			this.mensajeEliminados.setMensaje(" " + valoresBorrados
					+ " registros");
			this.mensajeEliminados.setMostrar(true);
			return null;
		} else {
			return guardar();
		}
	}

	public String guardar() throws NoControlableException {
		try {
			this.fillCatalogDescriptionContactos();
			this.verificaCambios(this.otrosIdsLista, TipoOtros.IDS);
			this.verificaCambios(this.otrosNombresLista, TipoOtros.NOMBRE);
			this.verificaCambios(this.otrosContactosLista, TipoOtros.CONTACTOS);
			if (!this.datosAgregados && !this.datosEliminados
					&& !this.datosGuardados) {
				this.dialogoGuardado = DialogoListadoEnum.SIN_CAMBIOS;
				this.dialogoGuardado.setMensaje("No se han realizado cambios");
				this.dialogoGuardado.setMostrar(true);
			} else {
				return recargarPagina();
			}
		} catch (ControlableException c) {
			this.dialogoGuardado = DialogoListadoEnum.SIN_CAMBIOS;
			this.dialogoGuardado
					.setMensaje(c.getMensajeUsuario() + " "
							+ c.getMensajeDetalle());
			this.dialogoGuardado.setMostrar(true);
		}
		return null;
	}

	private void verificaCambios(final List<OtroValorBean> listaOtrosValores,
			final TipoOtros tipoOtros) throws ControlableException,
			NoControlableException {
		if (listaOtrosValores != null) {
			for (final OtroValorBean otroValorBean : listaOtrosValores) {
				switch (otroValorBean.getOperacion()) {
				case ALTA:
				case ALTA_MODIFICACION:
					this.altaNuevosOtros(otroValorBean, tipoOtros);
					this.datosAgregados = true;
					break;
				case MODIFICACION:
					this.modificacionOtros(otroValorBean, tipoOtros);
					break;
				case BAJA:
				case MODIFICACION_BAJA:
					this.bajaOtros(otroValorBean, tipoOtros);
					this.datosEliminados = true;
					break;
				case ALTA_BAJA:
				case ALTA_MODIFICACION_BAJA:
				case SIN_CAMBIOS:
					break;
				default:
					throw new NoControlableException(
							"Ha ocurrido un error al dar de guardar Otros Datos",
							"Estado de los datos sin operacion: "
									+ otroValorBean.getOperacion()
									+ " no encontrada para los datos ["
									+ otroValorBean.getDescripcion() + "-"
									+ otroValorBean.getValor() + "]");
				}

			}
		}

	}

	private void bajaOtros(final OtroValorBean otroValorBean,
			final TipoOtros tipoOtros) throws ControlableException,
			NoControlableException {
		final int idCliente = this.cliente.getIdInterna();
		switch (tipoOtros) {
		case NOMBRE:
			this.bajaOtroNombrePersonaBackend.ejecutarTRN(idCliente,
					otroValorBean);
			break;
		case IDS:
			this.bajaOtraIdentificacionPersonaBackend.ejecutarTRN(idCliente,
					otroValorBean.getClave());
			break;
		case CONTACTOS:
			this.bajaOtroTelFaxPersonaBackend.ejecutarTRN(idCliente,
					otroValorBean);
			break;
		default:
			throw new NoControlableException(
					"Ha ocurrido un error al dar de alta Otros Datos",
					"Operacion de alta " + tipoOtros.name() + " no encontrada");
		}
	}

	private void modificacionOtros(final OtroValorBean otroValorBean,
			final TipoOtros tipoOtros) throws ControlableException,
			NoControlableException {
		final int idCliente = this.cliente.getIdInterna();
		switch (tipoOtros) {
		case NOMBRE:
			if (EstadoListadosEnum.MODIFICADO.equals(otroValorBean.getEstado())) {
				this.modificaciaOtroNombrePersonaBackend.ejecutarTRN(idCliente,
						otroValorBean);
				this.datosGuardados = true;
			}
			break;
		case IDS:
			if (EstadoListadosEnum.MODIFICADO.equals(otroValorBean.getEstado())) {
				this.modificaOtraIdentificacionPersonaBackend.ejecutarTRN(
						idCliente, otroValorBean);
				this.datosGuardados = true;
			}
			break;
		case CONTACTOS:
			if (EstadoListadosEnum.MODIFICADO.equals(otroValorBean.getEstado())) {
				this.modificaOtroTelFaxPersonaBackend.ejecutarTRN(idCliente,
						otroValorBean);
				this.datosGuardados = true;
			}
			break;
		default:
			throw new NoControlableException(
					"Ha ocurrido un error al dar de modificar Otros Datos",
					"Operacion de modificacion " + tipoOtros.name()
							+ " no encontrada");
		}

	}

	private void altaNuevosOtros(final OtroValorBean otroValorBean,
			final TipoOtros tipoOtros) throws NoControlableException,
			ControlableException {
		final int idCliente = this.cliente.getIdInterna();
		switch (tipoOtros) {
		case NOMBRE:
			this.altaOtroNombrePersonaBackend.ejecutarTRN(idCliente,
					otroValorBean);
			break;
		case IDS:
			this.altaOtraIdentificacionPersonaBackend.ejecutarTRN(idCliente,
					otroValorBean);
			break;
		case CONTACTOS:
			this.altaOtroTelFaxPersonaBackend.ejecutarTRN(idCliente,
					otroValorBean);
			break;
		default:
			throw new NoControlableException(
					"Ha ocurrido un error al dar de alta Otros Datos",
					"Operacion de alta " + tipoOtros.name() + " no encontrada");
		}
	}

	public List<CatalogoBean> getCatalogoDirecciones() {
		//return ConstantesFuncionales.CATALOGO_DIRECCIONES;
		//2015 12 11
    	final List<CatalogoBean> catalogo = catalogoUtils.getCatalogo(CatalogoEnum.TEL_DIR_ELECTRONICAS);

    	return catalogo;
	}
	

	public ClienteBean getCliente() {
		return cliente;
	}

	public void setCliente(final ClienteBean cliente) {
		this.cliente = cliente;
	}

	public CatalogoBean getTipoNombre() {
		return tipoNombre;
	}

	public void setTipoNombre(final CatalogoBean tipoNombre) {
		this.tipoNombre = tipoNombre;
	}

	public CatalogoBean getTipoId() {
		return tipoId;
	}
	
	public void setTipoId(final CatalogoBean tipoId) {
		this.tipoId = tipoId;
	}

	public CatalogoBean getTipoContacto() {
		return tipoContacto;
	}
	
	public void setTipoContacto(final CatalogoBean tipoContacto) {
		this.tipoContacto = tipoContacto;
	}
	
	public List<CatalogoBean> getListaNombres() {
		return listaNombres;
	}

	public void setListaNombres(List<CatalogoBean> listaNombres) {
		this.listaNombres = listaNombres;
	}

	public List<CatalogoBean> getListaIndentificaciones() {
		return listaIndentificaciones;
	}

	public void setListaIndentificaciones(List<CatalogoBean> listaIndentificaciones) {
		this.listaIndentificaciones = listaIndentificaciones;
	}

	public List<OtroValorBean> getOtrosNombresLista() {
		return otrosNombresLista;
	}

	public void setOtrosNombresLista(final List<OtroValorBean> otrosNombresLista) {
		this.otrosNombresLista = otrosNombresLista;
	}

	public List<OtroValorBean> getOtrosIdsLista() {
		return otrosIdsLista;
	}

	public void setOtrosIdsLista(final List<OtroValorBean> otrosIdsLista) {
		this.otrosIdsLista = otrosIdsLista;
	}

	public List<OtroValorBean> getOtrosContactosLista() {
		return otrosContactosLista;
	}

	public void setOtrosContactosLista(
			final List<OtroValorBean> otrosContactosLista) {
		this.otrosContactosLista = otrosContactosLista;
	}

	public int getValoresBorrados() {
		return valoresBorrados;
	}

	public void setValoresBorrados(int valoresBorrados) {
		this.valoresBorrados = valoresBorrados;
	}

	public String getTipoLista() {
		return tipoLista;
	}

	public void setTipoLista(String tipoLista) {
		this.tipoLista = tipoLista;
	}

	public String getClaveTipoLista() {
		return claveTipoLista;
	}

	public void setClaveTipoLista(String claveTipoLista) {
		this.claveTipoLista = claveTipoLista;
	}

	public String getDescripcionTipoLista() {
		return descripcionTipoLista;
	}

	public void setDescripcionTipoLista(String descripcionTipoLista) {
		this.descripcionTipoLista = descripcionTipoLista;
	}

	public DialogoPersonasListadoUtils getDialogoUtils() {
		return dialogoUtils;
	}

	public void setDialogoUtils(DialogoPersonasListadoUtils dialogoUtils) {
		this.dialogoUtils = dialogoUtils;
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

}
