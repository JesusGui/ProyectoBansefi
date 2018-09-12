package mx.babel.bansefi.banksystem.personas.controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

import mx.babel.arq.catalogo.beans.CatalogoBean;
import mx.babel.arq.catalogo.beans.CatalogoGeoBean;
import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.sesion.contexto.services.IBSContextoService;
import mx.babel.arq.sesion.utils.ContextoUtils;
import mx.babel.arq.storage.annotations.StoreStep;
import mx.babel.arq.storage.beans.StorageMgrBean;
import mx.babel.bansefi.banksystem.base.backends.consultarelacioncargopublico.ConsultaRelacionCargoPublicoBackEnd;
import mx.babel.bansefi.banksystem.base.backends.validaidexterna.ValidaIdExternaBackEnd;
import mx.babel.bansefi.banksystem.base.beans.busqueda.PersonasClienteBusquedaBean;
import mx.babel.bansefi.banksystem.base.beans.domain.ClientePersonaFisicaBean;
import mx.babel.bansefi.banksystem.base.beans.domain.DomicilioTipoBean;
import mx.babel.bansefi.banksystem.base.beans.domain.ReferenciaPersonalBean;
import mx.babel.bansefi.banksystem.base.beans.domain.RiesgoClientePersonaFisicaBean;
import mx.babel.bansefi.banksystem.base.beans.domain.TransaccionalidadBean;
import mx.babel.bansefi.banksystem.base.beans.domain.UsoCuentaBean;
import mx.babel.bansefi.banksystem.base.constants.ConstantesFuncionales;
import mx.babel.bansefi.banksystem.base.controllers.DomicilioController;
import mx.babel.bansefi.banksystem.base.enums.BusquedaEnum;
import mx.babel.bansefi.banksystem.base.enums.CatalogoEnum;
import mx.babel.bansefi.banksystem.base.enums.DialogoListadoEnum;
import mx.babel.bansefi.banksystem.base.enums.NavegacionEnum;
import mx.babel.bansefi.banksystem.base.enums.ParametrosFlashEnum;
import mx.babel.bansefi.banksystem.base.utils.CatalogoCentrosLoaderTask;
import mx.babel.bansefi.banksystem.base.utils.CatalogoPaisesUtils;
import mx.babel.bansefi.banksystem.base.utils.CatalogoUtils;
import mx.babel.bansefi.banksystem.base.utils.DomicilioUtils;
import mx.babel.bansefi.banksystem.base.utils.ManagedBeanStateRecover;
import mx.babel.bansefi.banksystem.personas.backend.AltaPersonaBackEnd;
import mx.babel.bansefi.banksystem.personas.backend.ConsultaObjetoSocialCnaesBackEnd;
import mx.babel.bansefi.banksystem.personas.backend.ModificacionPersonaFisicaBackEnd;
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
 * @author javier.martinnino Clase utilizada para el alta de Clientes
 */
@ManagedBean(name = "altaClienteController")
@Component
@Scope("view")
public class AltaClienteController extends StorageMgrBean {
	
	private static final Logger LOGGER = LogManager.getLogger(AltaClienteController.class.getName());
	
	@Autowired
	IBSContextoService bsContextoService;

	@Autowired
	ContextoUtils contextoUtils;
	
	@Autowired
	DomicilioUtils domicilioUtils;
	
	@Autowired
	private ConsultaRelacionCargoPublicoBackEnd consultaRelacionCargoPublicoBackEnd;
	
	@Autowired
	private AltaPersonaBackEnd altaPersonaBackend;
	
	@Autowired
	private ModificacionPersonaFisicaBackEnd modificarPersonaBackend;
	
	@Autowired
	private ValidaIdExternaBackEnd validaIdExternaBackend;
	
	@Autowired
	CatalogoUtils catalogoUtils;
	
	@Autowired
	CatalogoPaisesUtils catalogoPaisesUtils;
	
	@Autowired
	CatalogoCentrosLoaderTask catalogoCentros;
	
	@Autowired
	ManagedBeanStateRecover managedBeanStateRecover;
	
	@Autowired
	private ConsultaObjetoSocialCnaesBackEnd consultaObjetoSocialCnaesBackEnd;

	/**
	 * Variable utilizada para guardar la redireccion al flujo de entrada a la
	 * modificacion de clientes.
	 */
	private NavegacionEnum destino;

	/**
	 * Variable utilizada para guardar el contenido del controller origen a la
	 * modificacion de clientes.
	 */
	private Object destinoController;
	
	/**
	 * Cliente a dar de alta en el sistema .
	 */
	private ClientePersonaFisicaBean cliente;
	
	/**
	 * Cliente consultado a ser modificado, se utiliza para mantener los valores de la consulta
	 * y poder observar las modificaciones para los indicadores de entrada al servicio de modificacion.
	 */
	private ClientePersonaFisicaBean clienteConsultado;
	
	/**
	 * Variable para mostrar las posibles transaccionalidades.
	 */
	private List<String> selectTransaccionalidad;
	
	/**
	 * Variable para mostrar los posibles uso de cuentas.
	 */
	private List<String> selectUsoCuenta;
	
	/**
	 * Variable para mostrar las posibles operaciones a seleccionar.
	 */
	private List<String> selectOperaciones;
	
	/**
	 * Variable para mostrar los posibles tipos de identificacion a seleccionar.
	 */
	private List<CatalogoBean> selectIdentificaciones;
	
	/**
	 * Variable para mostrar los posibles comprobantes de domicilio a seleccionar.
	 */
	private List<CatalogoBean> selectComprobanteDomicilio;
	
	/**
	 * Variable para saber si estamos en la modificacion de cliente
	 */
	private Boolean modificacionCliente;
	
	
	/**
	 * Variable para saber si el alta es un subflujo
	 */
	private Boolean subflujo;
	
	/**
	 * Variable para recuperar el catalogBean de CNAE elegido en la modificacion de cliente
	 */
	private CatalogoBean cnaeSelected;
	
	/**
	 * Variable para recuperar el catalogBean de pais de Nacionalidad elegido en la modificacion de cliente
	 */
	private CatalogoBean paisNacionalidadSelected;
	
	/**
	 * Variable para recuperar el catalogBean de pais de Residencia elegido en la modificacion de cliente
	 */
	private CatalogoBean paisResidenciaSelected;
	
	/**
	 * Variable para recuperar el catalogBean de pais de Nacimiento elegido en la modificacion de cliente
	 */
	private CatalogoBean paisNacimientoSelected;
	
	/**
	 * Variable para recuperar el catalogBean de OFICINA elegida en la modificacion de cliente
	 */
	private CatalogoBean oficinaSelected;
	
	private DialogoListadoEnum mensajeEliminados;
	
	private DialogoListadoEnum mensajeRedireccion;
	
	private List<CnaeBean> listaCnaes;
	
	
	
	@Autowired
	private DomicilioController domicilioController;
	
	//Documentos no principales (no permitidos para el alta de persona moral)
	private static final String[] DOCUMENTOS_NO_PRINCIPALES = {"53", "56", "57", "59", "60", "61", "63", "66"};
	
	@PostConstruct
	void init() {
		super.restoreflash();
		refreshEnums();
		if(this.obtieneFlash().get(ParametrosFlashEnum.NAVEGACION_MANAGED_BEAN.getParamFlash())!=null){
			if ((Boolean) this.obtieneFlash().get(ParametrosFlashEnum.NAVEGACION_MANAGED_BEAN.getParamFlash())) {
				destino = managedBeanStateRecover.getDestino();
				destinoController = managedBeanStateRecover.getController();
				this.inicializaAltaModificacionCliente();
			}else{
				managedBeanStateRecover.recuperaController(this);
			}
		}else{
			this.inicializaAltaModificacionCliente();
		}				
		
	}			
	
	/**
	 * @return Metodo utilizado para inicializar el controller
	 * 
	 */
	public void inicializaAltaModificacionCliente() {
		LOGGER.debug("Accedemos al metodo de inicio init() del alta/modificacion de clientes: ENTRADA");
		
		// Se inicializan la transaccionalidad, operaciones y usos de Cuentas a mostrar		
		
		this.selectTransaccionalidad = new ArrayList<>();
		this.selectTransaccionalidad.add(0, "Honorarios");
		this.selectTransaccionalidad.add(1, "Arrendamiento");
		this.selectTransaccionalidad.add(2, "Premios, rifas o sorteos");
		this.selectTransaccionalidad.add(3, "Sueldos");
		this.selectTransaccionalidad.add(4, "Préstamos");
		this.selectTransaccionalidad.add(5, "Donativos");
		this.selectTransaccionalidad.add(6, "Inversiones o ahorros");
		this.selectTransaccionalidad.add(7, "Herencia");
		this.selectTransaccionalidad.add(8, "Actividad Comercial");
		this.selectTransaccionalidad.add(9, "Comisiones");
		this.selectTransaccionalidad.add(10, "Beca o ayuda institucional");
		this.selectTransaccionalidad.add(11, "Recursos de terceros");
		this.selectTransaccionalidad.add(12, "Venta de bienes");
		this.selectTransaccionalidad.add(13, "Pensión o jubilación");
		
		this.selectOperaciones = new ArrayList<>();
		this.selectOperaciones.add(0, "Depósitos");
		this.selectOperaciones.add(1, "Cobro de giros");
		this.selectOperaciones.add(2, "Retiros");
		this.selectOperaciones.add(3, "Transferencias");
		
		this.selectUsoCuenta = new ArrayList<>();
		this.selectUsoCuenta.add(0, "Admón. de ingresos y gastos");
		this.selectUsoCuenta.add(1, "Concentración/dispersión de fondos");
		this.selectUsoCuenta.add(2, "Pagos a proveedores y comisionistas");
		this.selectUsoCuenta.add(3, "Pago de servicios");
		this.selectUsoCuenta.add(4, "Cuenta puente inversión");
		this.selectUsoCuenta.add(5, "Pago de créditos");
		this.selectUsoCuenta.add(6, "Inversión");
		this.selectUsoCuenta.add(7, "Fideicomisos");
		this.selectUsoCuenta.add(8, "Depósitos de Nómina");
		
		// Inicializacion del combo de identificaciones
		this.setSelectIdentificaciones(obtenerIdentificaciones());
		
		// Inicializacion del combo de comprobante de Domicilios
		this.setSelectComprobanteDomicilio(obtenerComprobantesDomicilios());
		
		//Comprobamos si el alta ha sido invocada como un sublujo para implementar
		//lógica de navegaciòn alternativa
		if(obtieneFlash().get(ParametrosFlashEnum.SUBFLUJO_ALTA_CLIENTE.getParamFlash()) != null){
			this.subflujo = (Boolean) obtieneFlash().get(ParametrosFlashEnum.SUBFLUJO_ALTA_CLIENTE.getParamFlash());
		}else{
			this.subflujo = false;
		}
		
		// Comprobamos si ya tenemos el bean de la persona con datos y si se trata del flujo
		// de alta o de modificacion para renderizar el flujo correcto y recuperar los datos
		// en el flujo
		if (obtieneFlash().get(ParametrosFlashEnum.CLIENTE_PF_BEAN.getParamFlash()) != null) {
			this.cliente = (ClientePersonaFisicaBean) obtieneFlash().get(
					ParametrosFlashEnum.CLIENTE_PF_BEAN.getParamFlash());
			domicilioController.setDomicilioBean(this.cliente.getDomicilios().get(0));
			LOGGER.debug("Entrando al controller AltaClienteController, inicializa variables.");
			if (obtieneFlash().get(ParametrosFlashEnum.CLIENTE_PF_BEAN_CONSULTADO.getParamFlash()) != null) {
				this.clienteConsultado = (ClientePersonaFisicaBean) obtieneFlash().get(ParametrosFlashEnum.CLIENTE_PF_BEAN_CONSULTADO.getParamFlash());
			}
			if (obtieneFlash().get(ParametrosFlashEnum.MODIFICACION_CLIENTE.getParamFlash()) != null) {
				this.modificacionCliente = (Boolean) obtieneFlash().get(ParametrosFlashEnum.MODIFICACION_CLIENTE.getParamFlash());
				if (this.clienteConsultado == null){
					this.clienteConsultado = SerializationUtils.clone(this.cliente);
				}
				this.listaCnaes = this.consultaObjetoSocialCnae();
			}
			
			if(this.clienteConsultado!=null){
				domicilioController.setDomicilioBean(this.clienteConsultado.getDomicilios().get(0));
			}
			
		} else {
		
			UsoCuentaBean usoCuenta = new UsoCuentaBean();
			TransaccionalidadBean transaccionalidad = new TransaccionalidadBean();
			
			LOGGER.debug("Entrando al controller del AltaClienteController, inicializa variables.");
			domicilioController.setDomicilioBean(new DomicilioTipoBean());
			List<DomicilioTipoBean> domicilios = new ArrayList<>();
			domicilios.add(domicilioController.getDomicilioBean());
			
			List<String> operaciones = new ArrayList<>();			
			List<String> usos = new ArrayList<>();			
			usoCuenta.setOperaciones(operaciones);
			usoCuenta.setUsos(usos);
			
			List<String> trans = new ArrayList<>();			
			transaccionalidad.setTransaccionalidad(trans);
			
			RiesgoClientePersonaFisicaBean datosRiesgo = new RiesgoClientePersonaFisicaBean();			
			List<ReferenciaPersonalBean> referencias = new ArrayList<>();
			datosRiesgo.setReferenciasPersonales(referencias);
			
			this.cliente = new ClientePersonaFisicaBean();
			this.cliente.setDomicilios(domicilios);
			this.cliente.setUsoCuenta(usoCuenta);
			this.cliente.setTransaccionalidad(transaccionalidad);
			this.cliente.setDatosRiesgo(datosRiesgo);
			this.cliente.setEstado(new CatalogoGeoBean());
			
			this.cliente.setPaisNacionalidad(ConstantesFuncionales.COD_PAIS_MEXICO);
			this.cliente.setPaisResidencia(ConstantesFuncionales.COD_PAIS_MEXICO);
			
			this.cnaeSelected = new CatalogoBean();
			this.paisNacimientoSelected = new CatalogoBean();
			this.oficinaSelected = new CatalogoBean();
		}	
		LOGGER.debug("Accedemos al metodo de inicio init() del alta/modificacion de clientes: SALIDA");
	}
	
	/**
	 * @return Metodo utilizado para la carga en el componente de domicilio
	 *         
	 */
//	public void cargaDatos(){
//		this.cliente.getDomicilios().set(0,domicilioUtils.cargaDatos(this.cliente.getDomicilios().get(0)));
//	}
	
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
	
	public void manejarComponenteDomicilio() {
		if( domicilioController.getDomicilioBean().getCodigoPostalCatGeo()==null && domicilioController.getDomicilioBean().getMunicipioCatGeo()==null ){
			domicilioController.limpiarCamposDomicilio();
		}else{
			domicilioController.cargaDatosDomicilio();
		}
		this.cliente.getDomicilios().set(0, domicilioController.getDomicilioBean() );
	}
	
	public List<CatalogoGeoBean> getCodigosPostales(String query){
		if ( StringUtils.isBlank(query) ){
			domicilioController.getDomicilioBean().setCodigoPostalCatGeo(null);
			if(domicilioController.getDomicilioBean().getMunicipioCatGeo()==null){
				domicilioController.limpiarCamposDomicilio();
			}
			return null;
		}
		
		return domicilioController.getCatalogoCodigosPostales(query);
	}
	
	public  List<CatalogoGeoBean> getMunicipios(String query){
		if ( StringUtils.isBlank(query) ){
			domicilioController.getDomicilioBean().setMunicipioCatGeo(null);
			if(domicilioController.getDomicilioBean().getCodigoPostalCatGeo()==null){
				domicilioController.limpiarCamposDomicilio();
			}
			
			return null;
		}
		
		return domicilioController.getCatalogoMunicipios(query);
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
		if(this.cliente.getIdInterna() != null){
			return this.consultaObjetoSocialCnaesBackEnd
					.ejecutarTRN(this.cliente.getIdInterna());
		}else{
			List<CnaeBean> listaCnaes = new ArrayList<>();
			return listaCnaes;
		}
		
	}
	
	/**
	 * @return Metodo utilizado para la recarga del campo estados en localidades no codificadas         
	 */
	public void actualizaEstado(){
		this.cliente.setEstado(new CatalogoGeoBean());		
	}
	
	/**
	 * @return Metodo utilizado para la carga de datos de nacimiento en funcion del municipio/localidad seleccionado
	 *         
	 */
	public void actualizaDatosNacimiento(){
		if (this.cliente.getMunicipioCatGeo() ==null || (this.cliente.getMunicipioCatGeo() !=null && ((this.cliente.getMunicipioCatGeo().getCodigoPostal() !=null 
				&& !("").equals(this.cliente.getMunicipioCatGeo().getCodigoPostal())) || this.cliente.getMunicipioCatGeo().getNombreMunicipio() == null 
				||("").equals(this.cliente.getMunicipioCatGeo().getNombreMunicipio())))){			
			this.cliente.setEstado(new CatalogoGeoBean());
			this.setPaisNacimientoSelected(null);
			this.cliente.setPais(null);
		}
	}
	
	/**
	 * @return Metodo utilizado para actualizar el valor del campo de oficina en relacion al check
	 * 
	 */
	public void actualizarOficina() {
		
		if (this.cliente.getCorrespondenciaOficina() == null || !this.cliente.getCorrespondenciaOficina()) {
			this.setOficinaSelected(null);
			this.cliente.setNumOficina(null);
		}
	}
	
	/**
	 * @return Metodo utilizado para actualizar el valor del campo de oficina en relacion al check
	 * 
	 */
	public void actualizarRegimenEconomico() {
		
		if (!ConstantesFuncionales.TP_EST_CIVIL_INDV_CASADO.equals(this.cliente.getEstadoCivil())) {
			this.getCliente().setRegimenEconomico(null);
		}
	}
	
	/**
	 * @return Metodo utilizado para actualizar el valor del campo Otros en uso de cuenta y transaccionalidad
	 *         
	 */
	public void actualizaDatosCedula(){
		if (!this.cliente.getTransaccionalidad().getTransaccionalidad().contains("Otros")){
			this.cliente.getTransaccionalidad().setProvieneOtros(null);
		}
		if (!this.cliente.getUsoCuenta().getUsos().contains("Otros")){
			this.cliente.getUsoCuenta().setOtrosUsos(null);
		}
	}
	
	/**
	 * Método para obtener el tipo de documento de la id externa del cliente
	 */
	public String obtenerDescripcionTpDoc(){
		try{
			return this.catalogoUtils.getCatalogoBean(CatalogoEnum.TP_ID_EXT_PERS, this.cliente.getTipoIdentificacion()).getDescripcionC();			
		}catch(ControlableException | NoControlableException nce){
			LOGGER.debug("Error al intentar obtener la descripcion del tipo de documento a partir del codigo: "+ this.cliente.getTipoIdentificacion(), nce);
			return "";
		}
	}
	
	/**
	 * Método para obtener el tipo de tratamiento del cliente
	 */
	public String obtenerDescripcionTratamiento(){
		try{
			return this.catalogoUtils.getCatalogoBean(CatalogoEnum.TP_TRATMTO_PERS, this.cliente.getTratamiento()).getDescripcionC();			
		}catch(ControlableException | NoControlableException nce){
			LOGGER.debug("No se pudo recuperar la descripcion para el valor del tipo de tratamiento: "+this.cliente.getTipoIdentificacion(),nce);
			return "";
		}
	}
	
	/**
	 * @return List<CatalogoBean> utilizado para cargar el combo de identificaciones
	 * 	filtrando el catalogo correspondiente
	 *         
	 */
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
				LOGGER.debug("Encontrado un registro con valor no numerico: "+resultadoBusqueda.get(i).getClaveFila()+ "no lo tratamos",nfe);
			}
		}
		LOGGER.debug("Obtenemos los valores de combo de identificaciones: SALIDA");
		return resultado;
	}
	
	/**
	 * @return List<CatalogoBean> utilizado para cargar el combo de seleccion de comprobantes de domicilio
	 * 	filtrando el catalogo correspondiente
	 *         
	 */
	private List<CatalogoBean> obtenerComprobantesDomicilios() {
		LOGGER.debug("Obtenemos los valores de combo de comprobante de domicilio: ENTRADA");
		List<CatalogoBean> resultado = new ArrayList<>();
		
		List<CatalogoBean> resultadoBusqueda = catalogoUtils.getCatalogo(CatalogoEnum.TP_DOC);
		List<String> codComprobantes = ConstantesFuncionales.getCodigosComprobanteDomicilio();
		for (int i=0;i<resultadoBusqueda.size();i++){
			if (codComprobantes.contains((resultadoBusqueda.get(i)).getClaveFila())){
				resultado.add(resultadoBusqueda.get(i));
			}
		}
		LOGGER.debug("Obtenemos los valores de combo de comprobante de domicilio: SALIDA");
		return resultado;
	}
	
	/**
	 * @return Metodo utilizado para crear/Modificar el cliente
	 * 	       
	 */
	public String crearModificarCliente() {
		LOGGER.debug("Accedemos al metodo de alta/modificacion de cliente");
		int suma = Integer.parseInt(this.cliente.getUsoCuenta().getManejoRecursosPropios())
				+ Integer.parseInt(this.cliente.getUsoCuenta().getManejoRecursosTerceros());
		if(suma != 100){
			RequestContext.getCurrentInstance().execute("PF('dlgPorcentajes').show()");
			return null;
		}else{
			if (this.modificacionCliente !=null && this.modificacionCliente){				
				this.modificarPersonaBackend.ejecutarTRN(this.cliente,this.clienteConsultado);			
			}else{
				cliente.setIdInterna(altaPersonaBackend.ejecutarTRN(cliente));	
			}
			
			// Se consulta si el cliente es de altoRiesgo para ver la redireccion de salida
			this.cliente.setEsClienteRiesgo(this.consultaRelacionCargoPublicoBackEnd.ejecutarTRN(this.cliente.getIdInterna(), true));
			
			// Si el alta es correcta vamos a la vista de documentos del cliente				
			obtieneFlash().put(ParametrosFlashEnum.CLIENTE_PF_BEAN.getParamFlash(), this.cliente);
			
			if (!this.cliente.getEsClienteRiesgo()) {
				if (this.modificacionCliente !=null && this.modificacionCliente) {
					RequestContext.getCurrentInstance().execute("PF('dlgFinalizacionMod').show()");
				}else{
					RequestContext.getCurrentInstance().execute("PF('dlgFinalizacionAlta').show()");
				}
				
				return null;
			} else {
				if (this.modificacionCliente !=null && this.modificacionCliente) {
					RequestContext.getCurrentInstance().execute("PF('dlgRiesgoMod').show()");
				}else{
					RequestContext.getCurrentInstance().execute("PF('dlgRiesgoAlta').show()");
				}			
				return null;
			}
		}
		
	}
	
	/**
	 * @return Metodo utilizado para ir a Documentos de cliente
	 *         
	 */
	public String irDocumentos() {
						
		obtieneFlash().put(ParametrosFlashEnum.CLIENTE_PF_BEAN.getParamFlash(), this.cliente);
		
		if (this.modificacionCliente !=null && this.modificacionCliente){
			obtieneFlash().put(ParametrosFlashEnum.MODIFICACION_CLIENTE.getParamFlash(), true);
		}else{
			obtieneFlash().put(ParametrosFlashEnum.MODIFICACION_CLIENTE.getParamFlash(), false);
		}		
		
		if(subflujo){
			parametrosSubFlujo();
		}
		
		return NavegacionEnum.DOCUMENTOS.getRuta();					
	}
	
	/**
	 * @return Metodo utilizado para ir a la ficha de cliente
	 *         
	 */
	public String irFichaCliente() {						
		obtieneFlash().put(ParametrosFlashEnum.ID_INTERNA.getParamFlash(),this.cliente.getIdInterna());
        obtieneFlash().put(ParametrosFlashEnum.TIPO_CLIENTE.getParamFlash(),this.cliente.getTipo());
        return NavegacionEnum.FICHA_CLIENTE.getRuta();				
	}
	
	/**
	 * @return Metodo utilizado para ir a Documentos de cliente
	 *         
	 */
	@StoreStep
	public String continuarRiesgo() {
			
		obtieneFlash().put(ParametrosFlashEnum.CLIENTE_PF_BEAN.getParamFlash(), this.cliente);
		
		if (this.modificacionCliente !=null && this.modificacionCliente){
			obtieneFlash().put(ParametrosFlashEnum.MODIFICACION_CLIENTE.getParamFlash(), true);
		}else{
			obtieneFlash().put(ParametrosFlashEnum.MODIFICACION_CLIENTE.getParamFlash(), false);
		}		
		
		if(subflujo){
			parametrosSubFlujo();
		}			
		
		return NavegacionEnum.ALTA_CLIENTE_RIESGO1.getRuta();
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
		if (this.modificacionCliente == null || !this.modificacionCliente){
				try {
					Integer idInternaDuplicado = new Integer(0);
					if(!"".equals(cliente.getNombre()) || !"".equals(cliente.getApePaterno()) || !"".equals(cliente.getApeMaterno())){
						try{
							idInternaDuplicado = this.validaIdExternaBackend.ejecutarTRN(cliente.getNombre(), cliente.getApePaterno(), cliente.getApeMaterno(),
									cliente.getTipoIdentificacion(), cliente.getNumIdentificacion());
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
								idInternaDuplicado = this.validaIdExternaBackend.ejecutarTRN(null, null, null,
										cliente.getTipoIdentificacion(), cliente.getNumIdentificacion());
							}catch (ControlableException ce){
								if(ce.getRtncod() == 10){
									this.mensajeEliminados = DialogoListadoEnum.ALERTA;
									this.mensajeEliminados
											.setMensaje(ce.getMensajeUsuario() + " " + ce.getMensajeDetalle());
									this.mensajeEliminados.setMostrar(true);
								}
							}
						}
					} else {
						try{
							idInternaDuplicado = this.validaIdExternaBackend.ejecutarTRN(null, null, null,
									cliente.getTipoIdentificacion(), cliente.getNumIdentificacion());
						}catch (ControlableException ce){
							if(ce.getRtncod() == 10){
								this.mensajeEliminados = DialogoListadoEnum.ALERTA;
								this.mensajeEliminados
										.setMensaje(ce.getMensajeUsuario() + " " + ce.getMensajeDetalle());
								this.mensajeEliminados.setMostrar(true);
							}
						}
					}
//					if (idInternaDuplicado !=null && idInternaDuplicado !=0){
//						RequestContext.getCurrentInstance().execute("PF('dlgAltaClienteExistente').show()");
//					}
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
		PersonasClienteBusquedaBean personaFisica = new PersonasClienteBusquedaBean();
		personaFisica.setNoIdentificador(cliente.getNumIdentificacion());
		this.obtieneFlash().put(ParametrosFlashEnum.NAVEGACION_MANAGED_BEAN.getParamFlash(), true);
		this.obtieneFlash().put(ParametrosFlashEnum.BEAN_DATOS_BUSQUEDA.getParamFlash(), personaFisica);
		this.obtieneFlash().put(ParametrosFlashEnum.TIPO_BUSQUEDA.getParamFlash(), BusquedaEnum.PERSONA_CLIENTE.getBusquedaValor());
		this.obtieneFlash().put(ParametrosFlashEnum.ACCION_BUSQUEDA.getParamFlash(), false);
		return NavegacionEnum.BUSQUEDA.getRuta();
	}
		
	
	/**
	 * @return Metodo utilizado para cancelar el alta de Clientes
	 *         
	 */
	public String cancelarAltaModificacion() {
		String ruta = "";
		if (this.destino == null) {
			if (this.modificacionCliente !=null && this.modificacionCliente){
				obtieneFlash().put(ParametrosFlashEnum.ID_INTERNA.getParamFlash(),this.cliente.getIdInterna());
				obtieneFlash().put(ParametrosFlashEnum.TIPO_CLIENTE.getParamFlash(),this.cliente.getTipo());
				ruta = NavegacionEnum.FICHA_CLIENTE.getRuta();				
			}else{
				ruta = NavegacionEnum.INICIO.getRuta();
			}
		} else {
			if (this.modificacionCliente !=null && this.modificacionCliente){
				obtieneFlash().put(ParametrosFlashEnum.ID_INTERNA.getParamFlash(),this.cliente.getIdInterna());
				obtieneFlash().put(ParametrosFlashEnum.TIPO_CLIENTE.getParamFlash(),this.cliente.getTipo());
				ruta = NavegacionEnum.FICHA_CLIENTE.getRuta();				
			}else{
                ruta = destino.getRuta();
                managedBeanStateRecover.finNavegacion(destinoController);
			}
		}
		return ruta;
	}
	
	/**
	 * @return Metodo utilizado para acceder al paso 1 del alta de
	 *         Clientes por primera vez
	 */
	@StoreStep
	public String inicio() {
		return NavegacionEnum.ALTA_CLIENTE1.getRuta();
	}
	
	/**
	 * @return Metodo utilizado para redireccionar al paso 1 del alta de
	 *         Clientes
	 */
	@StoreStep
	public String irAPaso1() {
		obtieneFlash().put(ParametrosFlashEnum.CLIENTE_PF_BEAN.getParamFlash(), this.cliente);
		obtieneFlash().put(ParametrosFlashEnum.MODIFICACION_CLIENTE.getParamFlash(), this.modificacionCliente);
		obtieneFlash().put(ParametrosFlashEnum.CLIENTE_PF_BEAN_CONSULTADO.getParamFlash(), this.clienteConsultado);
		parametrosSubFlujo();
		return NavegacionEnum.ALTA_CLIENTE1.getRuta();
	}
	
	/**
	 * @return Metodo utilizado para redireccionar al paso 2 del alta de Clientes desde el paso 1 mostrando el mensaje al usuario del
	 * 		   cliente con un solo apellido
	 */
	@StoreStep
	public String irAPaso2DesdePaso1() {
		if (this.cliente.getApeMaterno() !=null && !("").equals(this.cliente.getApeMaterno().trim())){
			return this.irAPaso2();
		}else{
			RequestContext.getCurrentInstance().execute("PF('dlgIrPaso2').show()");
			return null;
		}		
	}	
	
	/**
	 * @return Metodo utilizado para redireccionar al paso 2 del alta de
	 *         Clientes
	 */
	@StoreStep
	public String irAPaso2() {
		this.cliente.getDomicilios().set(0, domicilioController.getDomicilioBean() );
		obtieneFlash().put(ParametrosFlashEnum.CLIENTE_PF_BEAN.getParamFlash(), this.cliente);
		obtieneFlash().put(ParametrosFlashEnum.MODIFICACION_CLIENTE.getParamFlash(), this.modificacionCliente);
		obtieneFlash().put(ParametrosFlashEnum.CLIENTE_PF_BEAN_CONSULTADO.getParamFlash(), this.clienteConsultado);
		parametrosSubFlujo();
		if(this.validaIdentificacion()){
			return NavegacionEnum.ALTA_CLIENTE2.getRuta();
		} else {
			RequestContext.getCurrentInstance().execute("PF('dlgDocumentoNoPrincipal').show()");
			return null;
		}
		
	}
	
	/**
	 * Funcion que valida si se selecciono una identificacion
	 * no principal
	 * @return
	 */
	public boolean validaIdentificacion(){
		for(String identificacion: DOCUMENTOS_NO_PRINCIPALES){
			if(identificacion.equals(this.cliente.getTipoIdentificacion().trim())){
				return false;
			}
		}
		
		return true;
	}
	
	private boolean existeCnae(){
		boolean existeCnae = false;
		for(CnaeBean cnae : this.listaCnaes){
			if(this.cliente.getCnae().equals(cnae.getCodCnae())){
				existeCnae = true;
			}
		}
		return existeCnae;
	}

	/**
	 * @return Metodo utilizado para redireccionar al paso 3 del alta de
	 *         Clientes
	 */
	@StoreStep
	public String irAPaso3() {
		if(this.modificacionCliente != null){
			if(this.modificacionCliente && this.existeCnae() && !this.clienteConsultado.getCnae().equals(this.cliente.getCnae())){
				this.mensajeEliminados = DialogoListadoEnum.ALERTA;
				this.mensajeEliminados
						.setMensaje("El CNAE se encuentra duplicado");
				this.mensajeEliminados.setMostrar(true);
				return null;
			}
		}
		obtieneFlash().put(ParametrosFlashEnum.CLIENTE_PF_BEAN.getParamFlash(), this.cliente);
		obtieneFlash().put(ParametrosFlashEnum.MODIFICACION_CLIENTE.getParamFlash(), this.modificacionCliente);
		obtieneFlash().put(ParametrosFlashEnum.CLIENTE_PF_BEAN_CONSULTADO.getParamFlash(), this.clienteConsultado);
		parametrosSubFlujo();
		return NavegacionEnum.ALTA_CLIENTE3.getRuta();
	}
	
	private void parametrosSubFlujo(){
		this.obtieneFlash().put(ParametrosFlashEnum.CONTROLLER_DESTINO.getParamFlash(), destinoController);
		this.obtieneFlash().put(ParametrosFlashEnum.NAVEGACION_BUSQUEDA.getParamFlash(),destino);
		if(subflujo){
			this.obtieneFlash().put(ParametrosFlashEnum.NAVEGACION_MANAGED_BEAN.getParamFlash(),true);
			this.obtieneFlash().put(ParametrosFlashEnum.SUBFLUJO_ALTA_CLIENTE.getParamFlash(),subflujo);
		}
	}
	
	/**
	 * @return Metodo utilizado para recuperar una instancia de Flash
	 */
	public Flash obtieneFlash() {
		return FacesContext.getCurrentInstance().getExternalContext()
				.getFlash();
	}
	
	/**
	 * @return beanList
	 */
	@Override
	protected Map<String, Object> getBeanList() {
		Map<String, Object> beanList = new HashMap<String, Object>();
		beanList.put(ClientePersonaFisicaBean.class.getName(), this.cliente);
		beanList.put(Boolean.class.getName(), this.modificacionCliente);
		beanList.put(ParametrosFlashEnum.CLIENTE_PF_BEAN_CONSULTADO.getParamFlash(), this.clienteConsultado);
		return beanList;
	}

	/**
	 * @param beanList
	 */
	@Override
	protected void setBeanList(Map<String, Object> beanList) {
		if (null != beanList) {
			this.cliente = (ClientePersonaFisicaBean) beanList.get(ClientePersonaFisicaBean.class.getName());
			obtieneFlash().put(ParametrosFlashEnum.CLIENTE_PF_BEAN.getParamFlash(), this.cliente);
			this.modificacionCliente = (Boolean) beanList.get(Boolean.class.getName());
			obtieneFlash().put(ParametrosFlashEnum.MODIFICACION_CLIENTE.getParamFlash(), this.modificacionCliente);
			this.clienteConsultado =(ClientePersonaFisicaBean) beanList.get(ParametrosFlashEnum.CLIENTE_PF_BEAN_CONSULTADO.getParamFlash());
			obtieneFlash().put(ParametrosFlashEnum.CLIENTE_PF_BEAN_CONSULTADO.getParamFlash(), this.clienteConsultado);
		}

	}

	@Override
	protected String getNombreFlujo() {
		if (this.modificacionCliente !=null && this.modificacionCliente){
			return "Modificacion de cliente "+this.obtenerDescripcionTratamiento()+" "+this.cliente.getNombre()+" "+this.cliente.getApePaterno()+" "+this.cliente.getApeMaterno()
					+" ("+this.obtenerDescripcionTpDoc()+" "+this.cliente.getNumIdentificacion()+")";
		}else{
			return "Alta de cliente: "+this.obtenerDescripcionTratamiento()+" "+this.cliente.getNombre()+" "+this.cliente.getApePaterno()+" "+this.cliente.getApeMaterno()
					+" ("+this.obtenerDescripcionTpDoc()+" "+this.cliente.getNumIdentificacion()+")";
		}
	}
	

	/**
	 * Método que recupera la ruta de destino.
	 * 
	 */
	public NavegacionEnum getDestino() {
		return destino;
	}

	/**
	 * Método que guarda la ruta de destino.
	 * 
	 * @param destino
	 */
	public void setDestino(NavegacionEnum destino) {
		this.destino = destino;
	}

	/**
	 * Método que recupera el controller de destino.
	 * 
	 */
	public Object getDestinoController() {
		return destinoController;
	}

	/**
	 * Método que guarda el controller de destino.
	 * 
	 * @param destinoController
	 */
	public void setDestinoController(Object destinoController) {
		this.destinoController = destinoController;
	}
	/**
	 * @return cliente
	 */
	public ClientePersonaFisicaBean getCliente() {
		return cliente;
	}

	/**
	 * @param cliente
	 */
	public void setCliente(ClientePersonaFisicaBean cliente) {
		this.cliente = cliente;
	}
	
	/**
	 * @return clienteConsultado
	 */
	public ClientePersonaFisicaBean getClienteConsultado() {
		return clienteConsultado;
	}
	
	/**
	 * @param clienteConsultado
	 */
	public void setClienteConsultado(ClientePersonaFisicaBean clienteConsultado) {
		this.clienteConsultado = clienteConsultado;
	}

	/**
	 * @return selectTransaccionalidad
	 */
	public List<String> getSelectTransaccionalidad() {
		return selectTransaccionalidad;
	}

	/**
	 * @param selectedTransaccionalidad
	 */
	public void setSelectTransaccionalidad(List<String> selectTransaccionalidad) {
		this.selectTransaccionalidad = selectTransaccionalidad;
	}
	
	/**
	 * @return selectedOperaciones
	 */
	public List<String> getSelectOperaciones() {
		return selectOperaciones;
	}

	/**
	 * @param selectedOperaciones
	 */
	public void setSelectOperaciones(List<String> selectOperaciones) {
		this.selectOperaciones = selectOperaciones;
	}
	
	/**
	 * @return the selectIdentificaciones
	 */
	public List<CatalogoBean> getSelectIdentificaciones() {
		return selectIdentificaciones;
	}

	/**
	 * @param selectIdentificaciones the selectIdentificaciones to set
	 */
	public void setSelectIdentificaciones(List<CatalogoBean> selectIdentificaciones) {
		this.selectIdentificaciones = selectIdentificaciones;
	}

	/**
	 * @return selectedUsoCuenta
	 */
	public List<String> getSelectUsoCuenta() {
		return selectUsoCuenta;
	}

	/**
	 * @param selectedUsoCuenta
	 */
	public void setSelectUsoCuenta(List<String> selectUsoCuenta) {
		this.selectUsoCuenta = selectUsoCuenta;
	}

	/**
	 * @return selectComprobanteDomicilio
	 */
	public List<CatalogoBean> getSelectComprobanteDomicilio() {
		return selectComprobanteDomicilio;
	}

	/**
	 * @param selectComprobanteDomicilio
	 */
	public void setSelectComprobanteDomicilio(
			List<CatalogoBean> selectComprobanteDomicilio) {
		this.selectComprobanteDomicilio = selectComprobanteDomicilio;
	}
	
	/**
	 * @return modificacionCliente
	 */
	public Boolean getModificacionCliente() {
		return modificacionCliente;
	}

	/**
	 * @param modificacionCliente
	 */
	public void setModificacionCliente(Boolean modificacionCliente) {
		this.modificacionCliente = modificacionCliente;
	}
	
	
	
	/**
	 * @return Atributo subflujo
	 */
	public Boolean getSubflujo() {
		return subflujo;
	}

	/**
	 * @param subflujo Atributo subflujo a definir
	 */
	public void setSubflujo(Boolean subflujo) {
		this.subflujo = subflujo;
	}

	/**
	 * @return cnaeSelected
	 */
	public CatalogoBean getCnaeSelected() {
		if (this.cliente.getCnae() != null && !("").equals(this.cliente.getCnae())){
			cnaeSelected = catalogoUtils.getCatalogoBean(CatalogoEnum.TP_CNAE_PERS, this.cliente.getCnae());
		}
		return cnaeSelected;
	}

	/**
	 * @param cnaeSelected
	 */
	public void setCnaeSelected(CatalogoBean cnaeSelected) {
		this.cnaeSelected = cnaeSelected;
		if (this.cnaeSelected != null){
			this.cliente.setCnae(this.cnaeSelected.getClaveFila());
		}
	}

	/**
	 * @return the paisNacionalidadSelected
	 */
	public CatalogoBean getPaisNacionalidadSelected() {
		if (this.cliente.getPaisNacionalidad() != null && !("").equals(this.cliente.getPaisNacionalidad())){
			paisNacionalidadSelected = catalogoPaisesUtils.getCatalogoBean(this.cliente.getPaisNacionalidad());
		}
		return paisNacionalidadSelected;
	}

	/**
	 * @param paisNacionalidadSelected
	 */
	public void setPaisNacionalidadSelected(CatalogoBean paisNacionalidadSelected) {
		this.paisNacionalidadSelected = paisNacionalidadSelected;
		if (this.paisNacionalidadSelected != null){
			this.cliente.setPaisNacionalidad(this.paisNacionalidadSelected.getClaveFila());
		}
	}

	/**
	 * @return the paisResidenciaSelected
	 */
	public CatalogoBean getPaisResidenciaSelected() {
		if (this.cliente.getPaisResidencia() != null && !("").equals(this.cliente.getPaisResidencia())){
			paisResidenciaSelected = catalogoPaisesUtils.getCatalogoBean(this.cliente.getPaisResidencia());
		}
		return paisResidenciaSelected;
	}

	/**
	 * @param paisResidenciaSelected the paisResidenciaSelected to set
	 */
	public void setPaisResidenciaSelected(CatalogoBean paisResidenciaSelected) {
		this.paisResidenciaSelected = paisResidenciaSelected;
		if (this.paisNacionalidadSelected != null){
			this.cliente.setPaisResidencia(this.paisResidenciaSelected.getClaveFila());
		}
	}
	
	/**
	 * @return the paisNacimientoSelected
	 */
	public CatalogoBean getPaisNacimientoSelected() {
		if (this.cliente.getPais() != null && !("").equals(this.cliente.getPais())){
			paisNacimientoSelected = catalogoPaisesUtils.getCatalogoBean(this.cliente.getPais());
		}
		return paisNacimientoSelected;
	}
	
	/**
	 * @param paisNacimientoSelected the paisNacimientoSelected to set
	 */
	public void setPaisNacimientoSelected(CatalogoBean paisNacimientoSelected) {
		this.paisNacimientoSelected = paisNacimientoSelected;
		if (this.paisNacimientoSelected != null){
			this.cliente.setPais(this.paisNacimientoSelected.getClaveFila());
		}
	}

	/**
	 * @return the oficinaSelected
	 */
	public CatalogoBean getOficinaSelected() {
		if (this.cliente.getNumOficina() != null && !("").equals(this.cliente.getNumOficina())){
			oficinaSelected = catalogoCentros.getCatalogoBean(contextoUtils.getEntidad(),this.cliente.getNumOficina());
		}
		return oficinaSelected;
	}

	/**
	 * @param oficinaSelected the oficinaSelected to set
	 */
	public void setOficinaSelected(CatalogoBean oficinaSelected) {
		this.oficinaSelected = oficinaSelected;
		if (this.oficinaSelected != null){
			this.cliente.setNumOficina(this.oficinaSelected.getClaveFila());
		}
	}
	
	public DomicilioController getDomicilioController() {
		return domicilioController;
	}

	public void setDomicilioController(DomicilioController domicilioController) {
		this.domicilioController = domicilioController;
	}
	
	public DialogoListadoEnum getMensajeEliminados() {
		return mensajeEliminados;
	}

	public void setMensajeEliminados(DialogoListadoEnum mensajeEliminados) {
		this.mensajeEliminados = mensajeEliminados;
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