package mx.babel.bansefi.banksystem.personas.controllers;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

import mx.babel.arq.catalogo.beans.CatalogoBean;
import mx.babel.arq.catalogo.beans.CatalogoGeoBean;
import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.sesion.utils.ContextoUtils;
import mx.babel.arq.storage.annotations.StoreStep;
import mx.babel.arq.storage.beans.StorageMgrBean;
import mx.babel.bansefi.banksystem.base.backends.ConsultaPerfilTransaccionalMoralBackEnd;
import mx.babel.bansefi.banksystem.base.backends.ModificacionPersonaMoralBackEnd;
import mx.babel.bansefi.banksystem.base.backends.validaidexterna.ValidaIdExternaBackEnd;
import mx.babel.bansefi.banksystem.base.beans.RelacionesClienteBean;
import mx.babel.bansefi.banksystem.base.beans.busqueda.PersonaMoralBusquedaBean;
import mx.babel.bansefi.banksystem.base.beans.busqueda.PersonasClienteBusquedaBean;
import mx.babel.bansefi.banksystem.base.beans.domain.ClientePersonaMoralBean;
import mx.babel.bansefi.banksystem.base.beans.domain.ConstitucionPersonaMoralBean;
import mx.babel.bansefi.banksystem.base.beans.domain.DomicilioTipoBean;
import mx.babel.bansefi.banksystem.base.beans.domain.DonativosPersonaMoralBean;
import mx.babel.bansefi.banksystem.base.beans.domain.GrupoFilialPersonaMoralBean;
import mx.babel.bansefi.banksystem.base.beans.domain.PersonaMoralAccionistaBean;
import mx.babel.bansefi.banksystem.base.beans.domain.TransaccionalidadBean;
import mx.babel.bansefi.banksystem.base.beans.domain.UsoCuentaBean;
import mx.babel.bansefi.banksystem.base.constants.ConstantesFuncionales;
import mx.babel.bansefi.banksystem.base.controllers.DomicilioController;
import mx.babel.bansefi.banksystem.base.enums.BusquedaEnum;
import mx.babel.bansefi.banksystem.base.enums.CatalogoEnum;
import mx.babel.bansefi.banksystem.base.enums.DialogoListadoEnum;
import mx.babel.bansefi.banksystem.base.enums.EstadoListadosEnum;
import mx.babel.bansefi.banksystem.base.enums.NavegacionEnum;
import mx.babel.bansefi.banksystem.base.enums.ParametrosFlashEnum;
import mx.babel.bansefi.banksystem.base.utils.CatalogoCentrosLoaderTask;
import mx.babel.bansefi.banksystem.base.utils.CatalogoPaisesUtils;
import mx.babel.bansefi.banksystem.base.utils.CatalogoUtils;
import mx.babel.bansefi.banksystem.base.utils.DomicilioUtils;
import mx.babel.bansefi.banksystem.base.utils.ManagedBeanStateRecover;
import mx.babel.bansefi.banksystem.personas.backend.AltaPerfilTransaccionalPersonaMoralBackEnd;
import mx.babel.bansefi.banksystem.personas.backend.AltaPersonaMoralBackEnd;
import mx.babel.bansefi.banksystem.personas.backend.ConsultaObjetoSocialCnaesBackEnd;
import mx.babel.bansefi.banksystem.personas.backend.ModificacionPerfilTransaccionalMoralBackEnd;
import mx.babel.bansefi.banksystem.personas.beans.CnaeBean;

import org.apache.commons.lang3.SerializationUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Persona Moral Controlador.
 * 
 * @author alejandro.perez
 * 
 */
@ManagedBean(name = "personaMoralController")
@ViewScoped
@Component
@Scope("view")
public class PersonaMoralController extends StorageMgrBean implements
		Serializable {

	private static final Logger LOGGER = LogManager
			.getLogger(PersonaMoralController.class.getName());

	private static final long serialVersionUID = 2108609323856895555L;

	@Autowired
	ContextoUtils contextoUtils;

	@Autowired
	DomicilioUtils domicilioUtils;

	@Autowired
	private DomicilioController domicilioController;

	@Autowired
	CatalogoPaisesUtils catalogoPaisesUtils;

	@Autowired
	CatalogoCentrosLoaderTask catalogoCentros;

	@Autowired
	CatalogoUtils catalogoUtils;

	@Autowired
	ManagedBeanStateRecover managedBeanStateRecover;

	/**
	 * BackEnds
	 */
	@Autowired
	private AltaPersonaMoralBackEnd altaPersonaMoralBackEnd;

	@Autowired
	private AltaPerfilTransaccionalPersonaMoralBackEnd altaPerfilTransaccionalMoralBackEnd;

	@Autowired
	private ConsultaPerfilTransaccionalMoralBackEnd consultaPerfilTransaccionalMoralBackEnd;

	@Autowired
	private ModificacionPersonaMoralBackEnd modificacionPersonaMoralBackEnd;

	@Autowired
	private ModificacionPerfilTransaccionalMoralBackEnd modificacionPerfilTransaccionalMoralBackEnd;
	
	@Autowired
	private ValidaIdExternaBackEnd validaIdExternaBackend;
	
	@Autowired
	private ConsultaObjetoSocialCnaesBackEnd consultaObjetoSocialCnaesBackEnd;

	/**
	 * Beans
	 */
	private ClientePersonaMoralBean personaMoralBean;

	private ClientePersonaMoralBean modificacionMoralBean;

	/**
	 * Catálogos
	 */
	private List<CatalogoBean> tiposIdentificacion;

	private List<CatalogoBean> comprobantesDomicilio;

	private CatalogoBean cnaeSelected;

	private CatalogoBean paisResidenciaSelected;

	private CatalogoBean centroSelected;
	
	private List<CnaeBean> listaCnaes;

	/**
	 * Variable para controlar que haya alguna transaccionalidad seleccionada.
	 */
	private List<String> selectedTransaccionalidad;

	/**
	 * Variable para controlar que haya algun uso de cuenta seleccionado.
	 */
	private List<String> selectedUsoCuenta;

	/**
	 * Variable para controlar que haya alguna operacion seleccionada.
	 */
	private List<String> selectedOperaciones;

	/**
	 * Variable para controlar que haya algun recurso seleccionado.
	 */
	private List<String> selectedRecursos;

	/**
	 * Enum para definir a donde se redireccionará al seleccionar un objeto de
	 * la tabla de resultados.
	 */
	private NavegacionEnum destino;
	private Object destinoController;

	private boolean dialogFinalizacion;
	private boolean dialogRiesgo;
	private boolean esModificacion;
	
	private DialogoListadoEnum dialogoGuardado;
	private DialogoListadoEnum mensajeRedireccion;

	@PostConstruct
	void init() {
		super.restoreflash();
		refreshEnums();
		if (this.obtieneFlash().get(
				ParametrosFlashEnum.NAVEGACION_MANAGED_BEAN.getParamFlash()) != null) {
			if ((Boolean) this.obtieneFlash()
					.get(ParametrosFlashEnum.NAVEGACION_MANAGED_BEAN
							.getParamFlash())) {
				destino = managedBeanStateRecover.getDestino();
				destinoController = managedBeanStateRecover.getController();
				this.inicializaAltaModificacionClienteMoral();
			} else {
				managedBeanStateRecover.recuperaController(this);
			}
		} else {
			this.inicializaAltaModificacionClienteMoral();
		}

	}

	/**
	 * Método de inicialización del bean de persona moral.
	 */
	private void inicializaAltaModificacionClienteMoral() {
		this.selectedOperaciones = new ArrayList<>();
		this.selectedOperaciones.add(0, "Depósitos");
		this.selectedOperaciones.add(1, "Cobro de giros");
		this.selectedOperaciones.add(2, "Retiros");
		this.selectedOperaciones.add(3, "Transferencias");

		this.selectedUsoCuenta = new ArrayList<>();
		this.selectedUsoCuenta.add(0, "Admón. de ingresos y gastos");
		this.selectedUsoCuenta.add(1, "Concentración/dispersión de fondos");
		this.selectedUsoCuenta.add(2, "Pagos a proveedores y comisionistas");
		this.selectedUsoCuenta.add(3, "Cuenta puente inversión");
		this.selectedUsoCuenta.add(4, "Pago de créditos");
		this.selectedUsoCuenta.add(5, "Inversión");
		this.selectedUsoCuenta.add(6, "Fideicomisos");
		this.selectedUsoCuenta.add(7, "Depósitos de Nómina");

		this.selectedTransaccionalidad = new ArrayList<>();
		this.selectedTransaccionalidad.add(0, "Ventas");
		this.selectedTransaccionalidad.add(1, "Comisiones");
		this.selectedTransaccionalidad.add(2, "Préstamos");
		this.selectedTransaccionalidad.add(3, "Obsequios y donativos");
		this.selectedTransaccionalidad.add(4, "Honorarios");
		this.selectedTransaccionalidad.add(5, "Inversiones");
		this.selectedTransaccionalidad.add(6, "Arrendamiento");

		this.selectedRecursos = new ArrayList<>();
		this.selectedRecursos.add(0, "Aportación de capital");
		this.selectedRecursos.add(1, "Beneficios");
		this.selectedRecursos.add(2, "Adjudicaciones judiciales");
		this.selectedRecursos.add(3, "Ingreso corriente");
		this.selectedRecursos.add(4, "Préstamos");
		this.selectedRecursos.add(5, "Venta de activo");
		this.selectedRecursos.add(6, "Recursos de terceros");

		this.setTiposIdentificacion(this.obtenerIdentificaciones());
		this.setComprobantesDomicilio(this.obtenerComprobantesDomicilios());

		if (obtieneFlash().get(
				ParametrosFlashEnum.CLIENTE_PM_BEAN.getParamFlash()) != null) {
			this.personaMoralBean = (ClientePersonaMoralBean) obtieneFlash()
					.get(ParametrosFlashEnum.CLIENTE_PM_BEAN.getParamFlash());
			this.domicilioController.setDomicilioBean(this.personaMoralBean
					.getDomicilios().get(0));
			if (obtieneFlash().get(
					ParametrosFlashEnum.CLIENTE_PM_BEAN_CONSULTADO
							.getParamFlash()) != null) {
				this.modificacionMoralBean = (ClientePersonaMoralBean) obtieneFlash()
						.get(ParametrosFlashEnum.CLIENTE_PM_BEAN_CONSULTADO
								.getParamFlash());
			}
			if (obtieneFlash().get(
					ParametrosFlashEnum.MODIFICACION_CLIENTE.getParamFlash()) != null) {
				this.esModificacion = (Boolean) obtieneFlash().get(
						ParametrosFlashEnum.MODIFICACION_CLIENTE
								.getParamFlash());
				if (this.modificacionMoralBean == null) {
					this.modificacionMoralBean = SerializationUtils
							.clone(this.personaMoralBean);
				}
				this.listaCnaes = this.consultaObjetoSocialCnae();
			}
			
			if (this.personaMoralBean != null) {
				this.domicilioController.setDomicilioBean(this.personaMoralBean
						.getDomicilios().get(0));
			}

		} else {

			List<DomicilioTipoBean> domicilios = new ArrayList<>();

			TransaccionalidadBean transaccionalidad = new TransaccionalidadBean();
			List<String> trans = new ArrayList<>();
			transaccionalidad.setTransaccionalidad(trans);

			UsoCuentaBean usoCuenta = new UsoCuentaBean();
			List<String> usos = new ArrayList<>();
			usoCuenta.setUsos(usos);

			ConstitucionPersonaMoralBean constitucionBean = new ConstitucionPersonaMoralBean();

			// Inicializamos los beans de datos de riesgo
			RelacionesClienteBean relacionesClienteRiesgo = new RelacionesClienteBean();
			DonativosPersonaMoralBean donativos = new DonativosPersonaMoralBean();
			GrupoFilialPersonaMoralBean grupoFilial = new GrupoFilialPersonaMoralBean();

			this.personaMoralBean = new ClientePersonaMoralBean();

			this.personaMoralBean.setDomicilios(domicilios);
			this.domicilioController.setDomicilioBean(new DomicilioTipoBean());
			this.personaMoralBean.getDomicilios().add(
					this.domicilioController.getDomicilioBean());

			this.personaMoralBean.setTransaccionalidad(transaccionalidad);
			this.personaMoralBean.setUsoCuenta(usoCuenta);
			this.personaMoralBean.setConstitucionBean(constitucionBean);

			this.personaMoralBean
					.setRelacionesClienteRiesgo(relacionesClienteRiesgo);
			this.personaMoralBean.setDonativosBean(donativos);
			this.personaMoralBean.setGrupoFilialBean(grupoFilial);

			this.personaMoralBean.setRecursos(new ArrayList<String>());

			// Se incluyen el minimo de dos accionistas para la persona moral de
			// riesgo
			PersonaMoralAccionistaBean accionista1 = new PersonaMoralAccionistaBean();
			accionista1.setEstado(EstadoListadosEnum.NUEVO);
			PersonaMoralAccionistaBean accionista2 = new PersonaMoralAccionistaBean();
			accionista2.setEstado(EstadoListadosEnum.NUEVO);

			this.personaMoralBean.getRelacionesClienteRiesgo().getAccionistas()
					.add(accionista1);
			this.personaMoralBean.getRelacionesClienteRiesgo().getAccionistas()
					.add(accionista2);

			this.cnaeSelected = new CatalogoBean();
			this.paisResidenciaSelected = new CatalogoBean();
			this.centroSelected = new CatalogoBean();

			this.personaMoralBean
					.setPais(ConstantesFuncionales.COD_PAIS_MEXICO);
			this.personaMoralBean.getConstitucionBean().setIdioma(
					ConstantesFuncionales.COD_IDIOMA_ESPAN);
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
	
	/**
	 * Método para ejecutar el servicio de alta de objeto social y cnae.
	 * 
	 * @return int Código de retorno del servicio.
	 * @throws ControlableException
	 *             Excepcion controlable que lance el servicio.
	 * @throws NoControlableException
	 *             Excepcion no controlable que lance el servicio.
	 */
	public List<CnaeBean> consultaObjetoSocialCnae()
			throws ControlableException, NoControlableException {
		if(this.personaMoralBean.getIdInterna() != null){
			return this.consultaObjetoSocialCnaesBackEnd
				.ejecutarTRN(this.personaMoralBean.getIdInterna());
		}else{
			List<CnaeBean> listaCnaes = new ArrayList<>();
			return listaCnaes;
		}
	}

	/**
	 * Método para obtener el tipo de documento de la id externa de la persona
	 * Moral
	 */
	public String obtenerDescripcionTpDoc() {
		try {
			return this.catalogoUtils.getCatalogoBean(
					CatalogoEnum.TP_ID_EXT_PERS,
					this.personaMoralBean.getTipoIdentificacion())
					.getDescripcionC();
		} catch (ControlableException | NoControlableException nce) {
			LOGGER.debug(
					"Error al intentar obtener la descripcion del tipo de documento a partir del codigo: "
							+ this.personaMoralBean.getTipoIdentificacion(),
					nce);
			return "";
		}
	}

	public void manejarComponenteDomicilio() {
		if (domicilioController.getDomicilioBean().getCodigoPostalCatGeo() == null
				&& domicilioController.getDomicilioBean().getMunicipioCatGeo() == null) {
			domicilioController.limpiarCamposDomicilio();
		} else {
			domicilioController.cargaDatosDomicilio();
		}
		this.personaMoralBean.getDomicilios().set(0,
				domicilioController.getDomicilioBean());
	}

	public List<CatalogoGeoBean> getCodigosPostales(String query) {
		if (StringUtils.isBlank(query)) {
			domicilioController.getDomicilioBean().setCodigoPostalCatGeo(null);
			if (domicilioController.getDomicilioBean().getMunicipioCatGeo() == null) {
				domicilioController.limpiarCamposDomicilio();
			}
			return null;
		}

		return domicilioController.getCatalogoCodigosPostales(query);
	}

	public List<CatalogoGeoBean> getMunicipios(String query) {
		if (StringUtils.isBlank(query)) {
			domicilioController.getDomicilioBean().setMunicipioCatGeo(null);
			if (domicilioController.getDomicilioBean().getCodigoPostalCatGeo() == null) {
				domicilioController.limpiarCamposDomicilio();
			}

			return null;
		}

		return domicilioController.getCatalogoMunicipios(query);
	}

	/**
	 * @return List<CatalogoBean> utilizado para cargar el combo de
	 *         identificaciones filtrando el catalogo correspondiente
	 * 
	 */
	private List<CatalogoBean> obtenerIdentificaciones() {
		LOGGER.debug("Obtenemos los valores de combo de identificaciones: ENTRADA");
		List<CatalogoBean> resultado = new ArrayList<>();

		List<CatalogoBean> resultadoBusqueda = catalogoUtils
				.getCatalogo(CatalogoEnum.TP_ID_EXT_PERS);

		for (int i = 0; i < resultadoBusqueda.size(); i++) {
			try {
				if (Integer.parseInt(resultadoBusqueda.get(i).getClaveFila()) < ConstantesFuncionales.COD_INICIO_IDENTFICACION_PERS_FISICAS) {
					resultado.add(resultadoBusqueda.get(i));
				}
			} catch (NumberFormatException nfe) {
				LOGGER.debug("Encontrado un registro con valor no numerico: "
						+ resultadoBusqueda.get(i).getClaveFila()
						+ "no lo tratamos", nfe);
			}
		}
		LOGGER.debug("Obtenemos los valores de combo de identificaciones: SALIDA");
		return resultado;
	}

	/**
	 * @return List<CatalogoBean> utilizado para cargar el combo de seleccion de
	 *         comprobantes de domicilio filtrando el catalogo correspondiente
	 * 
	 */
	private List<CatalogoBean> obtenerComprobantesDomicilios() {
		LOGGER.debug("Obtenemos los valores de combo de comprobante de domicilio: ENTRADA");
		List<CatalogoBean> resultado = new ArrayList<>();

		List<CatalogoBean> resultadoBusqueda = catalogoUtils
				.getCatalogo(CatalogoEnum.TP_DOC);
		List<String> codComprobantes = ConstantesFuncionales
				.getCodigosComprobanteDomicilio();
		for (int i = 0; i < resultadoBusqueda.size(); i++) {
			if (codComprobantes.contains((resultadoBusqueda.get(i))
					.getClaveFila())) {
				resultado.add(resultadoBusqueda.get(i));
			}
		}
		LOGGER.debug("Obtenemos los valores de combo de comprobante de domicilio: SALIDA");
		return resultado;
	}

	/**
	 * @return Metodo utilizado para actualizar el valor del campo de oficina en relacion al check
	 * 
	 */
	public void actualizarOficina() {
		
		if (this.personaMoralBean.getConstitucionBean().getCorrespondenciaOficina() == null || !this.personaMoralBean.getConstitucionBean().getCorrespondenciaOficina()) {
			this.setCentroSelected(null);
			this.personaMoralBean.setNumOficina(null);
		}
	}
	
	/**
	 * @return Metodo utilizado para actualizar el valor del campo Otros en uso
	 *         de cuenta y transaccionalidad
	 * 
	 */
	public void actualizaDatosCedula() {
		if (!this.personaMoralBean.getTransaccionalidad()
				.getTransaccionalidad().contains("Otros")) {
			this.personaMoralBean.getTransaccionalidad().setProvieneOtros(null);
		}
		if (!this.personaMoralBean.getRecursos().contains("Otros")) {
			this.personaMoralBean.setOtrosRecursos(null);
		}
		if (!this.personaMoralBean.getUsoCuenta().getUsos().contains("Otros")) {
			this.personaMoralBean.getUsoCuenta().setOtrosUsos(null);
		}
	}

	// /**
	// * @return Metodo utilizado para limpiar los datos registrales
	// *
	// */
	// public void limpiarDatosRegistrales(){
	// if
	// (StringUtils.isBlank(this.personaMoralBean.getConstitucionBean().getTipoRegistro())
	// && this.personaMoralBean.getConstitucionBean().getMunicipioRegistro()
	// !=null
	// &&
	// StringUtils.isBlank(this.personaMoralBean.getConstitucionBean().getMunicipioRegistro().getCodigoMunicipio())
	// ){
	// this.personaMoralBean.getConstitucionBean().setNumRegistro(null);
	// this.personaMoralBean.getConstitucionBean().setNumRegistral(null);
	// this.personaMoralBean.getConstitucionBean().setTomo(null);
	// this.personaMoralBean.getConstitucionBean().setLibro(null);
	// this.personaMoralBean.getConstitucionBean().setFolio(null);
	// this.personaMoralBean.getConstitucionBean().setNumInscripcion(null);
	// this.personaMoralBean.getConstitucionBean().setFinca(null);
	// this.personaMoralBean.getConstitucionBean().setFechaExpedicion(null);
	// }
	// }

	/**
	 * @return Metodo utilizado para cancelar el alta de Clientes guardando la
	 *         tarea
	 * 
	 */
	public String cancelarGuardarAlta() {
		return NavegacionEnum.INICIO.getRuta();
	}

	/**
	 * @return Metodo utilizado para cancelar el alta de Clientes
	 * 
	 */
	public String botonCancelar() {
		String ruta = "";
		if (this.destino == null) {
			if (this.esModificacion) {
				obtieneFlash().put(
						ParametrosFlashEnum.ID_INTERNA.getParamFlash(),
						this.personaMoralBean.getIdInterna());
				obtieneFlash().put(
						ParametrosFlashEnum.TIPO_CLIENTE.getParamFlash(),
						this.personaMoralBean.getTipo());
				ruta = NavegacionEnum.FICHA_CLIENTE.getRuta();
			} else {
				ruta = NavegacionEnum.INICIO.getRuta();
			}
		} else {
			if (this.esModificacion) {
				obtieneFlash().put(
						ParametrosFlashEnum.ID_INTERNA.getParamFlash(),
						this.personaMoralBean.getIdInterna());
				obtieneFlash().put(
						ParametrosFlashEnum.TIPO_CLIENTE.getParamFlash(),
						this.personaMoralBean.getTipo());
				ruta = NavegacionEnum.FICHA_CLIENTE.getRuta();
			}
		}
		return ruta;
	}

	/**
	 * @return Metodo utilizado para recuperar una instancia de Flash
	 */
	public Flash obtieneFlash() {
		return FacesContext.getCurrentInstance().getExternalContext()
				.getFlash();
	}

	@StoreStep
	public String inicio() {
		return NavegacionEnum.PERSONA_MORAL_GENERAL.getRuta();
	}

	/**
	 * Pantalla: Datos generales.
	 */

	@StoreStep
	public String irDatosGenerales() {
		obtieneFlash().put(ParametrosFlashEnum.CLIENTE_PM_BEAN.getParamFlash(),
				this.personaMoralBean);
		if (this.isEsModificacion()) {
			obtieneFlash().put(
					ParametrosFlashEnum.MODIFICACION_CLIENTE.getParamFlash(),
					this.esModificacion);
			obtieneFlash()
					.put(ParametrosFlashEnum.CLIENTE_PM_BEAN_CONSULTADO
							.getParamFlash(),
							this.modificacionMoralBean);
		}
		return NavegacionEnum.PERSONA_MORAL_GENERAL.getRuta();
	}
	
	private boolean existeCnae(){
		boolean existeCnae = false;
		for(CnaeBean cnae : this.listaCnaes){
			if(this.personaMoralBean.getCnae().equals(cnae.getCodCnae())){
				existeCnae = true;
			}
		}
		return existeCnae;
	}

	/**
	 * 
	 * Pantalla: Datos constitución.
	 * 
	 */

	@StoreStep
	public String irDatosConstitucion() {
		refreshEnums();
		if("01".equals(this.personaMoralBean.getTipoIdentificacion())){
			this.personaMoralBean.getDomicilios().set(0,
					domicilioController.getDomicilioBean());
			obtieneFlash().put(ParametrosFlashEnum.CLIENTE_PM_BEAN.getParamFlash(),
					this.personaMoralBean);
			if (this.isEsModificacion()) {
				if(this.existeCnae() && !this.modificacionMoralBean.getCnae().equals(this.personaMoralBean.getCnae())){
					this.dialogoGuardado = DialogoListadoEnum.ALERTA;
					this.dialogoGuardado
							.setMensaje("El CNAE se encuentra duplicado");
					this.dialogoGuardado.setMostrar(true);
					return null;
				}
				obtieneFlash().put(
						ParametrosFlashEnum.MODIFICACION_CLIENTE.getParamFlash(),
						this.esModificacion);
				obtieneFlash()
						.put(ParametrosFlashEnum.CLIENTE_PM_BEAN_CONSULTADO
								.getParamFlash(),
								this.modificacionMoralBean);
			}
			return NavegacionEnum.PERSONA_MORAL_CONTITUCION.getRuta();
		}else{
			this.dialogoGuardado = DialogoListadoEnum.ALERTA;
			this.dialogoGuardado
					.setMensaje("La identificación no es principal");
			this.dialogoGuardado.setMostrar(true);
			return null;
		}
	}

	/**
	 * 
	 * Pantalla: Cédula conocimiento
	 * 
	 */

	@StoreStep
	public String irCedulaConocimiento() {
		obtieneFlash().put(ParametrosFlashEnum.CLIENTE_PM_BEAN.getParamFlash(),
				this.personaMoralBean);
		if (this.isEsModificacion()) {
			obtieneFlash().put(
					ParametrosFlashEnum.MODIFICACION_CLIENTE.getParamFlash(),
					this.esModificacion);
			obtieneFlash()
					.put(ParametrosFlashEnum.CLIENTE_PM_BEAN_CONSULTADO
							.getParamFlash(),
							this.modificacionMoralBean);
		}
		return NavegacionEnum.PERSONA_MORAL_CONOCIMIENTO.getRuta();
	}

	@StoreStep
	public String irCedulaConocimientoDesdeConstitucion() {

		if (!StringUtils.isBlank(this.personaMoralBean.getConstitucionBean()
				.getTipoRegistro())
				&& (this.personaMoralBean.getConstitucionBean()
						.getMunicipioRegistro() == null || StringUtils
						.isBlank(this.personaMoralBean.getConstitucionBean()
								.getMunicipioRegistro().getCodigoMunicipio()))) {
			RequestContext.getCurrentInstance().execute(
					"PF('dlgRegistrales').show()");
			return null;
		} else if (StringUtils.isBlank(this.personaMoralBean
				.getConstitucionBean().getTipoRegistro())
				&& this.personaMoralBean.getConstitucionBean()
						.getMunicipioRegistro() != null
				&& !StringUtils.isBlank(this.personaMoralBean
						.getConstitucionBean().getMunicipioRegistro()
						.getCodigoMunicipio())) {
			RequestContext.getCurrentInstance().execute(
					"PF('dlgRegistrales').show()");
			return null;
		}

		obtieneFlash().put(ParametrosFlashEnum.CLIENTE_PM_BEAN.getParamFlash(),
				this.personaMoralBean);
		if (this.isEsModificacion()) {
			obtieneFlash().put(
					ParametrosFlashEnum.MODIFICACION_CLIENTE.getParamFlash(),
					this.esModificacion);
			obtieneFlash()
					.put(ParametrosFlashEnum.CLIENTE_PM_BEAN_CONSULTADO
							.getParamFlash(),
							this.modificacionMoralBean);
		}
		return NavegacionEnum.PERSONA_MORAL_CONOCIMIENTO.getRuta();
	}

	@StoreStep
	public String continuarRiesgo() {
		obtieneFlash().put(ParametrosFlashEnum.CLIENTE_PM_BEAN.getParamFlash(),
				this.personaMoralBean);
		if (this.isEsModificacion()) {
			obtieneFlash().put(
					ParametrosFlashEnum.MODIFICACION_CLIENTE.getParamFlash(),
					this.isEsModificacion());
		}
		return NavegacionEnum.PERSONA_MORAL_PERSONAS.getRuta();

	}

	public String irDocumentos() {
		obtieneFlash().put(ParametrosFlashEnum.CLIENTE_PM_BEAN.getParamFlash(),
				this.personaMoralBean);
		obtieneFlash().put(
				ParametrosFlashEnum.MODIFICACION_CLIENTE.getParamFlash(),
				this.esModificacion);
		return NavegacionEnum.DOCUMENTOS.getRuta();
	}

	/**
	 * @return Metodo utilizado para ir a la ficha de cliente
	 *         
	 */
	public String irFichaCliente() {						
		obtieneFlash().put(ParametrosFlashEnum.ID_INTERNA.getParamFlash(),this.personaMoralBean.getIdInterna());
        obtieneFlash().put(ParametrosFlashEnum.TIPO_CLIENTE.getParamFlash(),this.personaMoralBean.getTipo());
        return NavegacionEnum.FICHA_CLIENTE.getRuta();				
	}
	
	public String terminarFlujo() {
		int suma = Integer.parseInt(this.personaMoralBean.getUsoCuenta().getManejoRecursosPropios())
				+ Integer.parseInt(this.personaMoralBean.getUsoCuenta().getManejoRecursosTerceros());
		if(suma != 100){
			RequestContext.getCurrentInstance().execute("PF('dlgPorcentajes').show()");
			return null;
		}else{
			if (this.isEsModificacion()) {
				this.modificacionPersonaMoralBackEnd.ejecutarTRN(
						this.personaMoralBean, this.modificacionMoralBean);
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
				}
				this.modificacionPerfilTransaccionalMoralBackEnd
						.ejecutarTRN(this.personaMoralBean);
			} else {
				this.personaMoralBean.setIdInterna(this.altaPersonaMoralBackEnd
						.ejecutarTRN(this.personaMoralBean));
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
				}
				this.altaPerfilTransaccionalMoralBackEnd
						.ejecutarTRN(personaMoralBean);
			}

			this.personaMoralBean
					.setEsClienteRiesgo(this.consultaPerfilTransaccionalMoralBackEnd
							.ejecutarTRN(this.personaMoralBean.getIdInterna(), true));

			if (this.personaMoralBean.getEsClienteRiesgo()) {
				if (this.isEsModificacion()) {
					RequestContext.getCurrentInstance().execute(
							"PF('dlgRiesgoM').show()");
				} else {
					RequestContext.getCurrentInstance().execute(
							"PF('dlgRiesgoA').show()");
				}

			} else {
				if (this.isEsModificacion()) {
					RequestContext.getCurrentInstance().execute(
							"PF('dlgFinalizacionM').show()");
				} else {
					RequestContext.getCurrentInstance().execute(
							"PF('dlgFinalizacionA').show()");
				}
			}
			return null;
		}
	}

	/**
	 * Overrides del StorageMgrBean
	 */

	@Override
	protected Map<String, Object> getBeanList() {
		Map<String, Object> beanList = new HashMap<String, Object>();
		beanList.put(ClientePersonaMoralBean.class.getName(),
				this.personaMoralBean);
		beanList.put(Boolean.class.getName(), this.esModificacion);
		beanList.put(
				ParametrosFlashEnum.CLIENTE_PM_BEAN_CONSULTADO.getParamFlash(),
				this.modificacionMoralBean);
		return beanList;
	}

	@Override
	protected void setBeanList(Map<String, Object> beanList) {
		if (null != beanList) {
			this.personaMoralBean = (ClientePersonaMoralBean) beanList
					.get(ClientePersonaMoralBean.class.getName());
			obtieneFlash().put(
					ParametrosFlashEnum.CLIENTE_PM_BEAN.getParamFlash(),
					this.personaMoralBean);
			this.esModificacion = (Boolean) beanList.get(Boolean.class
					.getName());
			obtieneFlash().put(
					ParametrosFlashEnum.MODIFICACION_CLIENTE.getParamFlash(),
					this.esModificacion);
			this.modificacionMoralBean = (ClientePersonaMoralBean) beanList
					.get(ParametrosFlashEnum.CLIENTE_PM_BEAN_CONSULTADO
							.getParamFlash());
			obtieneFlash()
					.put(ParametrosFlashEnum.CLIENTE_PM_BEAN_CONSULTADO
							.getParamFlash(),
							this.modificacionMoralBean);
		}
	}

	@Override
	protected String getNombreFlujo() {
		if (this.esModificacion) {
			return "Modificacion de persona moral "
					+ this.personaMoralBean.getRazonSocial() + " ("
					+ this.obtenerDescripcionTpDoc() + " "
					+ this.personaMoralBean.getNumIdentificacion() + ")";
		} else {
			return "Alta de persona moral "
					+ this.personaMoralBean.getRazonSocial() + " ("
					+ this.obtenerDescripcionTpDoc() + " "
					+ this.personaMoralBean.getNumIdentificacion() + ")";
		}
	}
	
	/**
	 * @return Metodo utilizado para validar si el cliente existe en TRN
	 *         
	 */
	public void validarClienteExistente() {
		refreshEnums();
		LOGGER.debug("Accedemos al metodo que valida si el cliente existe en el sistema");
		boolean excepcion = false;
		// Solo realizamos la validacion en caso de encontrarnos en el flujo de alta no en modificacion
		if (!this.isEsModificacion()){
			try {
				Integer idInternaDuplicado = new Integer(0);
				if(!"".equals(this.personaMoralBean.getRazonSocial())){
					try{
						idInternaDuplicado = this.validaIdExternaBackend.ejecutarTRN(this.personaMoralBean.getRazonSocial(), 
								this.personaMoralBean.getTipoIdentificacion(), this.personaMoralBean.getNumIdentificacion());
					}catch (ControlableException ce){
						if(ce.getRtncod() == 10){
							this.mensajeRedireccion = DialogoListadoEnum.ALERTA;
							this.mensajeRedireccion
									.setMensaje(ce.getMensajeUsuario() + " " + ce.getMensajeDetalle() + ". Se redireccionará a la busqueda");
							this.mensajeRedireccion.setMostrar(true);
							excepcion = true;
						}
					}
					if(!excepcion){
						try{
							idInternaDuplicado = this.validaIdExternaBackend.ejecutarTRN(null, 
									this.personaMoralBean.getTipoIdentificacion(), this.personaMoralBean.getNumIdentificacion());
						}catch (ControlableException ce){
							if(ce.getRtncod() == 10){
								this.dialogoGuardado = DialogoListadoEnum.ALERTA;
								this.dialogoGuardado
										.setMensaje(ce.getMensajeUsuario() + " " + ce.getMensajeDetalle());
								this.dialogoGuardado.setMostrar(true);
							}
						}
					}
				} else {
					try{
						idInternaDuplicado = this.validaIdExternaBackend.ejecutarTRN(null, 
								this.personaMoralBean.getTipoIdentificacion(), this.personaMoralBean.getNumIdentificacion());
					}catch (ControlableException ce){
						if(ce.getRtncod() == 10){
							this.dialogoGuardado = DialogoListadoEnum.ALERTA;
							this.dialogoGuardado
									.setMensaje(ce.getMensajeUsuario() + " " + ce.getMensajeDetalle());
							this.dialogoGuardado.setMostrar(true);
						}
					}
				}
//				if (idInternaDuplicado !=null && idInternaDuplicado !=0){
//					RequestContext.getCurrentInstance().execute("PF('dlgAltaClienteExistente').show()");
//				}
			}catch (NoControlableException nce){
				LOGGER.debug("Error al intentar validar si el cliente ya existe en el sistema: "+nce);
			}
			
		}
		LOGGER.debug("Salimos del metodo que valida si el cliente existe en el sistema");
	}
	
	/**
	 * Redirecciona a la busqueda.
	 * @return Ruta de busqueda.
	 */
	public String redireccionarBusqueda(){
		PersonaMoralBusquedaBean personaMoral = new PersonaMoralBusquedaBean();
		personaMoral.setActaConstitutiva(this.personaMoralBean.getNumIdentificacion());
		this.obtieneFlash().put(ParametrosFlashEnum.NAVEGACION_MANAGED_BEAN.getParamFlash(), true);
		this.obtieneFlash().put(ParametrosFlashEnum.BEAN_DATOS_BUSQUEDA.getParamFlash(), personaMoral);
		this.obtieneFlash().put(ParametrosFlashEnum.TIPO_BUSQUEDA.getParamFlash(), BusquedaEnum.PERSONA_MORAL.getBusquedaValor());
		this.obtieneFlash().put(ParametrosFlashEnum.ACCION_BUSQUEDA.getParamFlash(), false);
		return NavegacionEnum.BUSQUEDA.getRuta();
	}

	/**
	 * Getters & Setters
	 */

	/**
	 * @return the selectedTransaccionalidad
	 */
	public List<String> getSelectedTransaccionalidad() {
		return selectedTransaccionalidad;
	}

	/**
	 * @param selectedTransaccionalidad
	 *            the selectedTransaccionalidad to set
	 */
	public void setSelectedTransaccionalidad(
			List<String> selectedTransaccionalidad) {
		this.selectedTransaccionalidad = selectedTransaccionalidad;
	}

	/**
	 * @return the selectedUsoCuenta
	 */
	public List<String> getSelectedUsoCuenta() {
		return selectedUsoCuenta;
	}

	/**
	 * @param selectedUsoCuenta
	 *            the selectedUsoCuenta to set
	 */
	public void setSelectedUsoCuenta(List<String> selectedUsoCuenta) {
		this.selectedUsoCuenta = selectedUsoCuenta;
	}

	/**
	 * @return the selectedOperaciones
	 */
	public List<String> getSelectedOperaciones() {
		return selectedOperaciones;
	}

	/**
	 * @param selectedOperaciones
	 *            the selectedOperaciones to set
	 */
	public void setSelectedOperaciones(List<String> selectedOperaciones) {
		this.selectedOperaciones = selectedOperaciones;
	}

	/**
	 * @return the tiposIdentificacion
	 */
	public List<CatalogoBean> getTiposIdentificacion() {
		return tiposIdentificacion;
	}

	/**
	 * @param tiposIdentificacion
	 *            the tiposIdentificacion to set
	 */
	public void setTiposIdentificacion(List<CatalogoBean> tiposIdentificacion) {
		this.tiposIdentificacion = tiposIdentificacion;
	}

	/**
	 * @return the dialogFinalizacion
	 */
	public boolean isDialogFinalizacion() {
		return dialogFinalizacion;
	}

	/**
	 * @param dialogFinalizacion
	 *            the dialogFinalizacion to set
	 */
	public void setDialogFinalizacion(boolean dialogFinalizacion) {
		this.dialogFinalizacion = dialogFinalizacion;
	}

	/**
	 * @return the comprobantesDomicilio
	 */
	public List<CatalogoBean> getComprobantesDomicilio() {
		return comprobantesDomicilio;
	}

	/**
	 * @param comprobantesDomicilio
	 *            the comprobantesDomicilio to set
	 */
	public void setComprobantesDomicilio(
			List<CatalogoBean> comprobantesDomicilio) {
		this.comprobantesDomicilio = comprobantesDomicilio;
	}

	public boolean isEsModificacion() {
		return esModificacion;
	}

	public void setEsModificacion(boolean esModificacion) {
		this.esModificacion = esModificacion;
	}

	public ClientePersonaMoralBean getModificacionMoralBean() {
		return modificacionMoralBean;
	}

	public void setModificacionMoralBean(
			ClientePersonaMoralBean modificacionMoralBean) {
		this.modificacionMoralBean = modificacionMoralBean;
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

	public boolean isDialogRiesgo() {
		return dialogRiesgo;
	}

	public void setDialogRiesgo(boolean dialogRiesgo) {
		this.dialogRiesgo = dialogRiesgo;
	}

	public List<String> getSelectedRecursos() {
		return selectedRecursos;
	}

	public void setSelectedRecursos(List<String> selectedRecursos) {
		this.selectedRecursos = selectedRecursos;
	}

	public ClientePersonaMoralBean getPersonaMoralBean() {
		return personaMoralBean;
	}

	public void setPersonaMoralBean(ClientePersonaMoralBean personaMoralBean) {
		this.personaMoralBean = personaMoralBean;
	}

	/**
	 * @return cnaeSelected
	 */
	public CatalogoBean getCnaeSelected() {
		if (this.personaMoralBean.getCnae() != null
				&& !("").equals(this.personaMoralBean.getCnae())) {
			cnaeSelected = catalogoUtils.getCatalogoBean(
					CatalogoEnum.TP_CNAE_PERS, this.personaMoralBean.getCnae());
		}
		return cnaeSelected;
	}

	/**
	 * @param cnaeSelected
	 */
	public void setCnaeSelected(CatalogoBean cnaeSelected) {
		this.cnaeSelected = cnaeSelected;
		if (this.cnaeSelected != null) {
			this.personaMoralBean.setCnae(this.cnaeSelected.getClaveFila());
		}
	}

	/**
	 * @return the paisNacionalidadSelected
	 */
	public CatalogoBean getPaisResidenciaSelected() {
		if (this.personaMoralBean.getPais() != null
				&& !("").equals(this.personaMoralBean.getPais())) {
			paisResidenciaSelected = catalogoPaisesUtils
					.getCatalogoBean(this.personaMoralBean.getPais());
		}
		return paisResidenciaSelected;
	}

	/**
	 * @param paisNacionalidadSelected
	 */
	public void setPaisResidenciaSelected(CatalogoBean paisResidenciaSelected) {
		this.paisResidenciaSelected = paisResidenciaSelected;
		if (this.paisResidenciaSelected != null) {
			this.personaMoralBean.setPais(this.paisResidenciaSelected
					.getClaveFila());
		}
	}

	/**
	 * @return the centro
	 */
	public CatalogoBean getCentroSelected() {
		if (this.personaMoralBean.getConstitucionBean().getNumOficina() != null
				&& !("").equals(this.personaMoralBean.getConstitucionBean()
						.getNumOficina())) {
			centroSelected = catalogoCentros.getCatalogoBean(contextoUtils
					.getEntidad(), this.personaMoralBean.getConstitucionBean()
					.getNumOficina());
		}
		return centroSelected;
	}

	/**
	 * @param centro
	 */
	public void setCentroSelected(CatalogoBean centroSelected) {
		this.centroSelected = centroSelected;
		if (this.centroSelected != null) {
			this.personaMoralBean.getConstitucionBean().setNumOficina(
					this.centroSelected.getClaveFila());
		}
	}

	public DomicilioController getDomicilioController() {
		return domicilioController;
	}

	public void setDomicilioController(DomicilioController domicilioController) {
		this.domicilioController = domicilioController;
	}
	
	public DialogoListadoEnum getDialogoGuardado() {
		return dialogoGuardado;
	}

	public void setDialogoGuardado(DialogoListadoEnum dialogoGuardado) {
		this.dialogoGuardado = dialogoGuardado;
	}

	public List<CnaeBean> getListaCnaes() {
		return listaCnaes;
	}

	public void setListaCnaes(List<CnaeBean> listaCnaes) {
		this.listaCnaes = listaCnaes;
	}

	public DialogoListadoEnum getMensajeRedireccion() {
		return mensajeRedireccion;
	}

	public void setMensajeRedireccion(DialogoListadoEnum mensajeRedireccion) {
		this.mensajeRedireccion = mensajeRedireccion;
	}
	
	public String getCurrentDate() {
		return new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime());
	}
	
}
