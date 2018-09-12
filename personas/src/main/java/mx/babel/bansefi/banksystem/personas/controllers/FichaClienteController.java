package mx.babel.bansefi.banksystem.personas.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

import mx.babel.arq.catalogo.beans.CatalogoBean;
import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.sesion.utils.ContextoUtils;
import mx.babel.bansefi.banksystem.base.backends.ConsultaCuentasClienteBackEnd;
import mx.babel.bansefi.banksystem.base.backends.ConsultaDatosGestorBackEnd;
import mx.babel.bansefi.banksystem.base.backends.ConsultaPersonaFisicaBackEnd;
import mx.babel.bansefi.banksystem.base.backends.ConsultaPersonaMoralBackEnd;
import mx.babel.bansefi.banksystem.base.backends.anotaciones.ConsultaListaAnotacionesBackEnd;
import mx.babel.bansefi.banksystem.base.beans.domain.AnotacionBean;
import mx.babel.bansefi.banksystem.base.beans.domain.ClienteBean;
import mx.babel.bansefi.banksystem.base.beans.domain.ClienteGrupoBean;
import mx.babel.bansefi.banksystem.base.beans.domain.ClientePersonaFisicaBean;
import mx.babel.bansefi.banksystem.base.beans.domain.ClientePersonaMoralBean;
import mx.babel.bansefi.banksystem.base.beans.domain.CuentaBean;
import mx.babel.bansefi.banksystem.base.beans.domain.CuentaClienteBean;
import mx.babel.bansefi.banksystem.base.beans.domain.TipoCliente;
import mx.babel.bansefi.banksystem.base.constants.ConstantesFuncionales;
import mx.babel.bansefi.banksystem.base.enums.CatalogoEnum;
import mx.babel.bansefi.banksystem.base.enums.NavegacionEnum;
import mx.babel.bansefi.banksystem.base.enums.ParametrosFlashEnum;
import mx.babel.bansefi.banksystem.base.utils.AnotacionPrioridadComparator;
import mx.babel.bansefi.banksystem.base.utils.CatalogoCentrosLoaderTask;
import mx.babel.bansefi.banksystem.base.utils.CatalogoUtils;
import mx.babel.bansefi.banksystem.base.utils.ManagedBeanStateRecover;
import mx.babel.bansefi.banksystem.base.utils.PdfUtils;
import mx.babel.bansefi.banksystem.personas.backend.ConsultaGrupoBackEnd;
import mx.babel.bansefi.banksystem.personas.backend.ConsultaIdIntegranteBackEnd;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Controlador encargado de gestionar la vista de ficha de cliente.
 *
 * @author mario.montesdeoca
 *
 */
@ManagedBean(name="fichaClienteController")
@ViewScoped
@Component
@Scope("view")
public class FichaClienteController implements Serializable {

	@Autowired
	private ContextoUtils contextoUtils;

	@Autowired
    private PdfUtils pdfUtils;

	@Autowired
	CatalogoUtils catalogoUtils;
	
	@Autowired
	CatalogoCentrosLoaderTask catalogoCentrosLoaderTask;

	@Autowired
	ConsultaPersonaFisicaBackEnd clienteConsultaPersonaFisica;

	@Autowired
	ConsultaPersonaMoralBackEnd clienteConsultaPersonaMoral;

	@Autowired
	ConsultaGrupoBackEnd clienteConsultaGrupo;

	@Autowired
	ConsultaDatosGestorBackEnd consultaDatosGestorBackEnd;
	
	@Autowired
	ConsultaIdIntegranteBackEnd consultaIdIntegranteBackEnd;

	@Autowired
	ConsultaCuentasClienteBackEnd clienteConsultaCuentasCliente;

	@Autowired
	ConsultaListaAnotacionesBackEnd consultaListaAnotacionesBackEnd;

	@Autowired
	ManagedBeanStateRecover managedBeanStateRecover;

	/**
	 * Enum para definir a donde se redireccionará al seleccionar un objeto de
	 * la tabla de resultados.
	 */
	private NavegacionEnum destino;
	private Object destinoController;

	private static final long serialVersionUID = 7585235941248351517L;
	private static final int NUM_ANOTACIONES = 6;
	public static final String CUENTAS_ACTIVAS = "4";

	public String id;
	public TipoCliente tipoCliente;

	public ClienteBean cliente;
	public List<CuentaClienteBean> cuentas;
	public List<CuentaClienteBean> cuentasTotales;

	public String estadoCuenta = CUENTAS_ACTIVAS;
	private String codigoGrupo;
	private List<CatalogoBean> estadosCuenta;
	private Boolean mostrarAvisoRepresentante;
	private int anotacionesVisibles;

	private Boolean origenBusqueda = false;
	
	private Exception initException;

	/**
	 * Método que obtienen los parametros del id interna del cliente y
	 * el tipo del mismo para consultar el webservice adecuado.
	 */
	@PostConstruct
	public void initView(){
		if(this.obtieneFlash().get(
				ParametrosFlashEnum.ORIGEN_BUSQUEDA.getParamFlash()) != null){
			this.origenBusqueda = (Boolean) this.obtieneFlash().get(
					ParametrosFlashEnum.ORIGEN_BUSQUEDA.getParamFlash());
		}
		try{
			if(this.obtieneFlash().get(ParametrosFlashEnum.NAVEGACION_MANAGED_BEAN.getParamFlash())!=null){
				if ((Boolean) this.obtieneFlash().get(ParametrosFlashEnum.NAVEGACION_MANAGED_BEAN.getParamFlash())) {
					destino = managedBeanStateRecover.getDestino();
					destinoController = managedBeanStateRecover.getController();
					initializeData();
				}else{
					managedBeanStateRecover.recuperaController(this);
					initCuentas();
				}
			}else{
				initializeData();
			}
		}catch(final NoControlableException e){
			cliente = null;
			initException = e;
		}catch (final ControlableException e) {
			cliente = null;
			initException = e;
		}
	}

	/**
	 * Método para inicializar la información a desplegarse en la vista.
	 * @throws ControlableException
	 * @throws NoControlableException
	 */
	public void initializeData() throws ControlableException, NoControlableException{
		if (obtieneFlash().get(ParametrosFlashEnum.CLIENTE.getParamFlash()) != null) {
			this.cliente = (ClienteBean) obtieneFlash().get(ParametrosFlashEnum.CLIENTE.getParamFlash());
			this.id = "";
			if (this.cliente.getIdInterna() != null){
				this.id = this.cliente.getIdInterna().toString();
			}
			if(this.cliente.getTipoClienteEnum() == null){
				this.cliente.setTipoClienteEnum(this.cliente.getTipo());
			}
			this.tipoCliente = this.cliente.getTipo();
			this.establecerAnotacionesVisibles();

			if (obtieneFlash().get(ParametrosFlashEnum.CUENTAS_FICHA.getParamFlash())!=null){
				this.cuentas = (List<CuentaClienteBean>)obtieneFlash().get(ParametrosFlashEnum.CUENTAS_FICHA.getParamFlash());
				this.cuentasTotales = (List<CuentaClienteBean>)obtieneFlash().get(ParametrosFlashEnum.CUENTAS_FICHA2.getParamFlash());
			} else {
				initCuentas();
			}

		} else {
			initParams();
			initCliente();
		}
	}

	/**
	 * Métodó encargado de recibir los parametros del contexto con los detalles del cliente
	 * a ser consultado.
	 *
	 */
	public void initParams(){
		if(obtieneFlash().get(ParametrosFlashEnum.ID_INTERNA.getParamFlash()) != null){
			id = obtieneFlash().get(ParametrosFlashEnum.ID_INTERNA.getParamFlash()).toString();
			tipoCliente = (TipoCliente) obtieneFlash().get(ParametrosFlashEnum.TIPO_CLIENTE.getParamFlash());
		}
		if(TipoCliente.GRUPO.equals(tipoCliente)){
			codigoGrupo = (String) obtieneFlash().get(ParametrosFlashEnum.COD_TIPO_GRUPO.getParamFlash());
		}
	}

	/**
	 * Método encargado de consultar los datos del cliente
	 */
	private void initCliente() throws NoControlableException, ControlableException{
		if(TipoCliente.PERSONA_MORAL.equals(tipoCliente)){
			try{
				cliente = clienteConsultaPersonaMoral.ejecutarTRN(Integer.parseInt(id));
			}catch(final NumberFormatException nfe){
				throw new ControlableException("No se puede consultar el cliente.",nfe.getCause());
			}
		}else if(TipoCliente.GRUPO.equals(tipoCliente)){
			cliente = clienteConsultaGrupo.ejecutarTRN(id, codigoGrupo);
		}else{
			try{
				cliente = clienteConsultaPersonaFisica.ejecutarTRN(Integer.parseInt(id));
				if(cliente != null){
					if(((ClientePersonaFisicaBean) cliente).getIsGestor()){
						((ClientePersonaFisicaBean) cliente).setDatosGestor(consultaDatosGestorBackEnd.ejecutarTRN(cliente.getIdInterna()));
					}
					
					//TODO: Pendiente ver por qué se hace esta consulta de integrante
//					try{
//						((ClientePersonaFisicaBean) cliente).setIdIntegrante(
//								consultaIdIntegranteBackEnd.ejecutarTRN(cliente.getIdInterna(), cliente.getNumIdentificacion()));
//					}catch(ControlableException e){
//						
//					}
					tipoCliente = cliente.getTipo();
				}
			}catch(final NumberFormatException nfe){
				throw new ControlableException("No se puede consultar el cliente.",nfe.getCause());
			}
		}
		if(cliente != null && cliente.getFusionado() != null && cliente.getFusionado()){
			tipoCliente = TipoCliente.FUSIONADO;
		}
		if(cliente != null && !TipoCliente.FUSIONADO.equals(tipoCliente)){
			cliente.setTipoCliente(tipoCliente.getTipo());
			initCuentas();
			initAnotaciones();
			this.establecerAnotacionesVisibles();
		}
	}

	/**
	 * Método para consultar las anotaciones de un cliente
	 */
	private void initAnotaciones(){
		if(!TipoCliente.GRUPO.equals(tipoCliente)){
			cliente.setAnotaciones(consultaListaAnotacionesBackEnd.ejecutarTRN(cliente.getIdInterna(),true));
			Collections.sort(cliente.getAnotaciones(), new AnotacionPrioridadComparator());
		}
	}
	
	/**
	 * Método para obtener el tipo de documento de la id externa del cliente informando la identificacion
	 */
	private String obtenerDescripcionTpDoc(String identificacion){
		try{
			return this.catalogoUtils.getCatalogoBean(CatalogoEnum.TP_ID_EXT_PERS, identificacion).getDescripcionC();
		}catch(NullPointerException | NoControlableException | ControlableException nce){
			return "";
		}
	}

	/**
	 * Método para obtener el tipo de un grupo.
	 * @param codigo Código del tipo de grupo
	 * @return Descripción del tipo de grupo.
	 */
	public String obtenerTipoGrupo(final String codigo){
		try{
			return this.catalogoUtils.getCatalogoBean(CatalogoEnum.TP_GRP, codigo).getDescripcionC();
		}catch(NullPointerException | NoControlableException | ControlableException nce){
			return codigo;
		}
	}
	/**
	 * Método para incializar la lista de cuentas asociadas al cliente
	 */
	private void initCuentas(){
		if(cliente != null){
			estadosCuenta = catalogoUtils.getCatalogo(CatalogoEnum.TP_ECV_AC);
			if(TipoCliente.GRUPO.getTipo().equals(cliente.getTipoCliente())){
				setCuentasTotales(clienteConsultaCuentasCliente.ejecutarTRN(0));
			}else{
				setCuentasTotales(clienteConsultaCuentasCliente.ejecutarTRN(Integer.parseInt(id)));
			}
			this.actualizaCuentas();
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
	 * Función encargada de verificar si el cliente debe tener cliente
	 * y, de ser así, si lo tiene.
	 *
	 * @return <code>true</code> en caso de que el cliente
	 * 		   tenga un representante.
	 */
	public Boolean hasRepresentante(){
		if(!TipoCliente.GESTOR.equals(tipoCliente) && !TipoCliente.CLIENTE_GESTOR.equals(tipoCliente)){
			if(cliente != null && 
					(TipoCliente.MENOR_EDAD_DISCAPAZ.equals(cliente.getTipo()) 
							|| TipoCliente.PERSONA_MORAL.equals(cliente.getTipo()))){
				if(cliente.getRepresentante() == null || cliente.getRepresentante().getIdInterna() == null){
					setMostrarAvisoRepresentante(true);
					return false;
				}else{
					setMostrarAvisoRepresentante(false);
					return true;
				}
			}
		}
		setMostrarAvisoRepresentante(false);
		return false;
	}

	/**
	 * Función para verificar si el cliente es un grupo.
	 *
	 * @return <code>true</code> en caso de que el cliente
	 * 		   sea un grupo.
	 */
	public String claseTipoPersona(final ClienteBean cliente){
		if(cliente != null && (TipoCliente.PERSONA_FISICA.equals(cliente.getTipo()) || TipoCliente.MENOR_EDAD_DISCAPAZ.equals(cliente.getTipo())
				|| TipoCliente.CLIENTE_GESTOR.equals(cliente.getTipo()) || TipoCliente.GESTOR.equals(cliente.getTipo()))){
			return "nombre-altacuenta";
		}
		if(cliente != null && TipoCliente.PERSONA_MORAL.equals(cliente.getTipo())){
			return "nombre-personamoral";
		}
		if(cliente != null && TipoCliente.GRUPO.equals(cliente.getTipo())){
			return "nombre-grupo";
		}
		return "";
	}


	public Boolean muestraTipo(final TipoCliente tipo){
		if(TipoCliente.MENOR_EDAD_DISCAPAZ.equals(tipo) || TipoCliente.GRUPO.equals(tipo) ){
			return true;
		}
		return false;
	}

	/**
	 * Función para obtener el teléfono del cliente
	 *
	 * @return El telefono asociado al domicilio principalo.
	 */
	public String getTelefono(final ClienteBean cliente) {
		if(domiciliosVacio(cliente)){
			return "";
		}
		return cliente.getDomicilios().get(0).getTelefono();
	}

	/**
	 * Función que obtiene la identificación de un cliente.
	 *
	 * @return Detalles de la identificaón del cliente
	 */
	public String getIdentificacion(final ClienteBean cliente){
		String labelIdentificacion = "";
		if(cliente != null){
			if(TipoCliente.GRUPO.equals(cliente.getTipo())){
				labelIdentificacion = "("+cliente.getNumIdentificacion()+")";
			}else{
				labelIdentificacion = "("+StringUtils.defaultString(obtenerDescripcionTpDoc(cliente.getTipoIdentificacion()))
					+":"+cliente.getNumIdentificacion()+")";
			}
		}
		return labelIdentificacion;
	}

	/**
	 * Función encargada de definir el texto para la fecha de nacimiento,
	 * constituón o alta dependiendo el tipo de cliente desplegado.
	 *
	 * @param cliente Objeto cliente del cual se despliegan sus datos
	 * @return Texto correspondiente a la fecha de nacimiento/constitución/alta del cliente
	 */
	public String getLabelFecha(final ClienteBean cliente){
		String labelFecha = "";
		if(cliente != null){
			if(TipoCliente.PERSONA_MORAL.equals(cliente.getTipo())){
				labelFecha = "FECHA DE CONSTITUCIÓN:";
			}else if(TipoCliente.GRUPO.equals(cliente.getTipo())){
				labelFecha = "FECHA DE ALTA:";
			}else{
				labelFecha = "FECHA DE NACIMIENTO:";
			}
		}
		return labelFecha;
	}

	public String getEstadoCuenta(final String estado){
		for (final CatalogoBean catalogo : estadosCuenta) {
			if(catalogo.getClaveFila().equals(estado)){
				return catalogo.getDescripcionC();
			}
		}
		return "";
	}

	/**
	 * Método para crear un archivo pdf con los datos desplegados en pantalla.
	 *
	 */
	public void printReport(){
		final Map<String, Object> params = new HashMap<String, Object>();
		String nombreOficina;
		String plazaBancaria;
		try{
			nombreOficina = catalogoCentrosLoaderTask.getCatalogoBean(contextoUtils.getEntidad(), contextoUtils.getSucursal()).getDescripcionL();
		} catch(Exception e){
			nombreOficina = "";
		}
		try{
			plazaBancaria = catalogoUtils.getCatalogoBean(CatalogoEnum.TP_PLAZA_BANCARIA, contextoUtils.getPlazaBancaria()).getDescripcionL();
		}catch(Exception e){
			plazaBancaria = "";
		}
		params.put("OFICINA", nombreOficina);
		params.put("PLAZA", plazaBancaria);
		params.put("DOMICILIO_CLIENTE",getDireccion(this.cliente).toUpperCase());
		params.put("DOMICILIO_REPRESENTANTE",getDireccion(this.cliente.getRepresentante()).toUpperCase());
		params.put("TELEFONO", getTelefono(cliente));
		params.put("TELEFONO_REPRESENTANTE", getTelefono(cliente.getRepresentante()));
		params.put("TIPO", cliente.getTipo().getTipo());
		params.put("LISTA_CUENTAS", cuentasEnEstado(CUENTAS_ACTIVAS));
		Map<String, String> codigosIdentificacion = new HashMap<String, String>();
		
		if(TipoCliente.GRUPO.equals(cliente.getTipo())){
			params.put("INTEGRANTES_GRUPO", ((ClienteGrupoBean)cliente).getIntegrantes());
			for (ClienteBean integrante : ((ClienteGrupoBean)cliente).getIntegrantes()) {
				codigosIdentificacion.put(this.catalogoUtils.getCatalogoBean(CatalogoEnum.TP_ID_EXT_PERS, integrante.getTipoIdentificacion()).getClaveFila()
						, this.catalogoUtils.getCatalogoBean(CatalogoEnum.TP_ID_EXT_PERS, integrante.getTipoIdentificacion()).getDescripcionC());
			}
		}else{
			codigosIdentificacion.put(this.catalogoUtils.getCatalogoBean(CatalogoEnum.TP_ID_EXT_PERS, cliente.getTipoIdentificacion()).getClaveFila()
					, this.catalogoUtils.getCatalogoBean(CatalogoEnum.TP_ID_EXT_PERS, cliente.getTipoIdentificacion()).getDescripcionC());
			if( cliente.getRepresentante() != null && cliente.getRepresentante().getIdInterna() != null){
				codigosIdentificacion.put(this.catalogoUtils.getCatalogoBean(CatalogoEnum.TP_ID_EXT_PERS, cliente.getRepresentante().getTipoIdentificacion()).getClaveFila()
					, this.catalogoUtils.getCatalogoBean(CatalogoEnum.TP_ID_EXT_PERS,  cliente.getRepresentante().getTipoIdentificacion()).getDescripcionC());
			}
		}
		if(TipoCliente.GESTOR.equals(cliente.getTipo()) || TipoCliente.CLIENTE_GESTOR.equals(cliente.getTipo())){
			params.put("GESTOR_NOMBRE", ((ClientePersonaFisicaBean)cliente).getDatosGestor().getNombreGestor());
			params.put("GESTOR_TIPO", ((ClientePersonaFisicaBean)cliente).getDatosGestor().getTipoGestor());
			params.put("GESTOR_ALTA", ((ClientePersonaFisicaBean)cliente).getDatosGestor().getFechaAlta());
			params.put("GESTOR_ESTADO", ((ClientePersonaFisicaBean)cliente).getDatosGestor().getEstado());
		}
		params.put("CODIGOS_IDENTIFICACION", codigosIdentificacion);
		
		final Map<String, String> subReportes = new HashMap<String, String>();
		subReportes.put("FichaClienteCuentas.jrxml", "SUBREPORT_CUENTAS");
		subReportes.put("FichaClienteIntegrantes.jrxml", "SUBREPORT_CLIENTES");

		final Map<String, String> images = new HashMap<String, String>();
        images.put("logo_top.png", "LOGO");

		final List<ClienteBean> listaBeans = new ArrayList<ClienteBean>();
		listaBeans.add(cliente);
		pdfUtils.generaPdf("FichaCliente.jrxml", params, images, subReportes, "fichaCliente", listaBeans);
	}

	/**
	 * Método que redirige al usuario a la búsqueda genérica.
	 *
	 * @return ruta del recurso a mostrar
	 */
	public String regresar(){
		String ruta = "";
		if (this.destino == null) {
			ruta = NavegacionEnum.BUSQUEDA.getRuta();
		} else {
			ruta = destino.getRuta();
			managedBeanStateRecover.finNavegacion(destinoController);
		}
		return ruta;
	}
	
	public String volverInicio(){
		return NavegacionEnum.INICIO.getRuta();
	}

	/**
	 * Función paraobtener la dirección principal del cliente en un formato específico.
	 *
	 * @return La dirección del domicilio principal en un formato específico.
	 */
	public String getDireccion(final ClienteBean cliente) {
		if(domiciliosVacio(cliente)){
			return "";
		}
		return cliente.getDomicilios().get(0).getDireccion();
	}

	/**
	 * Función que verifica si un cliente tiene por lo menos un domicilio con datos
	 *
	 * @param cliente el cliente con la lista de domicilios
	 * @return <code>true</code> si tiene por lo menos un domicilio con información
	 */
	private Boolean domiciliosVacio(final ClienteBean cliente){
		if (cliente == null || cliente.getDomicilios().isEmpty() || cliente.getDomicilios().get(0) == null){
			return true;
		}
		return false;
	}

	public void actualizaCuentas(){
		setCuentas(cuentasEnEstado(estadoCuenta));
	}

	public List<CuentaClienteBean> cuentasEnEstado(final String estado){
		final List<CuentaClienteBean> cuentasEnEstado = new ArrayList<CuentaClienteBean>();
		if(!(estado == null || "".equals(estado))){
			for (final CuentaClienteBean cuenta : getCuentasTotales()) {
				if(estado.equals(cuenta.getCuenta().getEstado())){
					cuentasEnEstado.add(cuenta);
				}
			}
		}else{
			cuentasEnEstado.addAll(getCuentasTotales());
		}
		return cuentasEnEstado;
	}

	/**
	 * Método utilizado para comprobar si procede visualiza el fieldset de personas relacionadas
	 *
	 */
	public void establecerAnotacionesVisibles() {
		int numAnotacionesVisibles = 0;
		if (this.cliente.getAnotaciones() != null){
			if (this.cliente.getAnotaciones().size() > NUM_ANOTACIONES){
				numAnotacionesVisibles = FichaClienteController.NUM_ANOTACIONES;
			}else{
				numAnotacionesVisibles = this.cliente.getAnotaciones().size();
			}
		}
		this.anotacionesVisibles = numAnotacionesVisibles;
	}

	/**
	 * Método utilizado para mostrar todas las anotaciones.
	 *
	 */
	public void mostrarTodasAnotaciones() {
		this.setAnotacionesVisibles(this.cliente.getAnotaciones().size());
	}

	/**
	 * Función para obtener la descripcion del tipo de la anotacion.
	 *
	 * @return la descripcion del tipo de la anotacion
	 */
	public String getTipoAnotacion(final String clave) {
		try {
			return catalogoUtils.getCatalogoBean(CatalogoEnum.TIPO_ANOTACION,clave).getDescripcionL();
		} catch (final ControlableException ce) {
			return "";
		}
	}

	/**
	 * @return String - La descripcion del subcodigo de la anotacion buscada
	 *         
	 */
	public String obtenerSubCodigoAnotacion(String clave) {
		try{
			return catalogoUtils.getCatalogoBean(CatalogoEnum.TP_ANTCN,clave).getDescripcionL();
		}catch (ControlableException ce){
			return "";
		}		
	}
	
	public String getTipoDescripcion(ClienteBean cliente){
		if(cliente.getTipoClienteEnum() != null){
			if(cliente.getTipoClienteEnum().equals(TipoCliente.MENOR_EDAD_DISCAPAZ)){
				if(ConstantesFuncionales.isMenorEdad(cliente.getFechaNacimiento())){
					return "Menor de edad";
				}else{
					return "Incapaz";
				}
			}
			return cliente.getTipoClienteEnum().getTipo();
		}
		return "";
	}
	
	/**
	 * Función para avanzar a la ventana de modificacion de datos personales.
	 *
	 * @return La URL a la que redireccionará
	 */

	public String modificarDatos() {
		managedBeanStateRecover.enviaController(this, NavegacionEnum.FICHA_CLIENTE);
		if (this.tipoCliente.equals(TipoCliente.PERSONA_MORAL)) {
			obtieneFlash().put(ParametrosFlashEnum.CLIENTE_PM_BEAN.getParamFlash(), this.cliente);
			obtieneFlash().put(ParametrosFlashEnum.MODIFICACION_CLIENTE.getParamFlash(), true);
			return NavegacionEnum.PERSONA_MORAL_GENERAL.getRuta();
		} else {
			obtieneFlash().put(ParametrosFlashEnum.CLIENTE_PF_BEAN.getParamFlash(), this.cliente);
			obtieneFlash().put(ParametrosFlashEnum.MODIFICACION_CLIENTE.getParamFlash(), true);
			return NavegacionEnum.ALTA_CLIENTE1.getRuta();
		}

	}

	/**
	 * Función para avanzar a la ventana de modificacion de datos personales.
	 *
	 * @return La URL a la que redireccionará
	 */
	public String modificarCliente(final ClienteBean clienteBean){
		managedBeanStateRecover.enviaController(this, NavegacionEnum.FICHA_CLIENTE);
		if (clienteBean instanceof ClientePersonaMoralBean) {
			obtieneFlash().put(ParametrosFlashEnum.CLIENTE_PM_BEAN.getParamFlash(), clienteBean);
			obtieneFlash().put(ParametrosFlashEnum.MODIFICACION_CLIENTE.getParamFlash(), true);
			return NavegacionEnum.PERSONA_MORAL_GENERAL.getRuta();
		} else {
			obtieneFlash().put(ParametrosFlashEnum.CLIENTE_PF_BEAN.getParamFlash(), clienteBean);
			obtieneFlash().put(ParametrosFlashEnum.MODIFICACION_CLIENTE.getParamFlash(), true);
			return NavegacionEnum.ALTA_CLIENTE1.getRuta();
		}
	}

	/**
	 * Función para avanzar a la ventana de actividad empresarial.
	 *
	 * @return La URL a la que redireccionará
	 */
	public String actividadEmpresarial(){
		obtieneFlash().put(ParametrosFlashEnum.ID_EMPLEADO.getParamFlash(), cliente.getIdInterna());
		obtieneFlash().put(ParametrosFlashEnum.NOMBRE_CLIENTE.getParamFlash(), cliente.getNombreCompleto());
		obtieneFlash().put(ParametrosFlashEnum.CLIENTE.getParamFlash(), this.cliente);
		managedBeanStateRecover.enviaController(this, NavegacionEnum.FICHA_CLIENTE);
		return NavegacionEnum.ACTIVIDAD_EMPRESARIAL.getRuta();
	}

	public String otrosDatos(){
		obtieneFlash().put(ParametrosFlashEnum.CLIENTE.getParamFlash(), this.cliente);
		managedBeanStateRecover.enviaController(this, NavegacionEnum.FICHA_CLIENTE);
		return NavegacionEnum.OTROS_DATOS.getRuta();
	}

	/**
	 * Función para avanzar a la ventana de listado de balances (en personas morales).
	 * @return La URL a la que redireccionará
	 */
	public String listaBalances() {
		obtieneFlash().put(ParametrosFlashEnum.CLIENTE.getParamFlash(), this.cliente);
		managedBeanStateRecover.enviaController(this, NavegacionEnum.FICHA_CLIENTE);
		return NavegacionEnum.PERSONA_BALANCE_LISTA.getRuta();
	}

	/**
	 * Función para avanzar a la ventana de consulta posición (en grupos).
	 * @return La URL a la que redireccionará
	 */
	public String consultaPosicion() {
		obtieneFlash().put(ParametrosFlashEnum.CLIENTE.getParamFlash(), this.cliente);
		obtieneFlash().put(ParametrosFlashEnum.CUENTAS_FICHA.getParamFlash(), this.cuentas);
		obtieneFlash().put(ParametrosFlashEnum.CUENTAS_FICHA2.getParamFlash(), this.cuentasTotales);
		managedBeanStateRecover.enviaController(this, NavegacionEnum.FICHA_CLIENTE);
		return NavegacionEnum.CONSULTA_POSICION.getRuta();
	}

	/**
	 * Función para avanzar a la ventana de datos económicos.
	 * @return La URL a la que redireccionará
	 */
	public String datosEconomicos(){
		obtieneFlash().put(ParametrosFlashEnum.ID_EMPLEADO.getParamFlash(), cliente.getIdInterna());
		obtieneFlash().put(ParametrosFlashEnum.NOMBRE_CLIENTE.getParamFlash(), cliente.getNombreCompleto());
		obtieneFlash().put(ParametrosFlashEnum.CLIENTE.getParamFlash(), this.cliente);
		managedBeanStateRecover.enviaController(this, NavegacionEnum.FICHA_CLIENTE);
		return NavegacionEnum.DATOS_ECONOMICOS.getRuta();
	}

	/**
	 * Función para avanzar a la ventana de domicilio persona
	 *
	 * @return La URL a la que redireccionará
	 */
	public String domicilioPersona(){
		obtieneFlash().put(ParametrosFlashEnum.CLIENTE.getParamFlash(), this.cliente);
		obtieneFlash().put(ParametrosFlashEnum.NOMBRE_CLIENTE.getParamFlash(), cliente.getNombreCompleto());
		obtieneFlash().put(ParametrosFlashEnum.TIPO_CLIENTE.getParamFlash(), this.tipoCliente);
		managedBeanStateRecover.enviaController(this, NavegacionEnum.FICHA_CLIENTE);
		return NavegacionEnum.DOMICILIO_PERSONA.getRuta();
	}

	/**
	 * Función para avanzar a la ventana de relaciones del cliente.
	 * @return La URL a la que redireccionará
	 */
	public String relacionesCliente(){
		obtieneFlash().put(ParametrosFlashEnum.CLIENTE.getParamFlash(), this.cliente);
		managedBeanStateRecover.enviaController(this, NavegacionEnum.FICHA_CLIENTE);
		return NavegacionEnum.RELACIONES_CLIENTE.getRuta();
	}

	/**
	 * Método para navegar al alta de cuenta.
	 * @return ruta del alta de cuenta.
	 */
	public String altaCuenta(){
		obtieneFlash().put(ParametrosFlashEnum.CLIENTE.getParamFlash(), this.cliente);
        managedBeanStateRecover.enviaController(this, NavegacionEnum.FICHA_CLIENTE);
        this.obtieneFlash().put(ParametrosFlashEnum.SUBFLUJO_ALTA_CUENTA.getParamFlash(),true);
		return NavegacionEnum.ALTA_CUENTA1.getRuta();
	}

	/**
	 * Método para navegar a la vista de relaciones de personas.
	 * @return ruta de la vista de relaciones de personas
	 */
	public String altaRepresentanteLegal(){
		obtieneFlash().put(ParametrosFlashEnum.CLIENTE.getParamFlash(), this.cliente);
		obtieneFlash().put(ParametrosFlashEnum.IND_ALTA_REPRESENTANTE.getParamFlash(), "true");
		managedBeanStateRecover.enviaController(this, NavegacionEnum.FICHA_CLIENTE);
		return NavegacionEnum.RELACIONES_CLIENTE.getRuta();
	}


	/**
	 * Método para enviar una anotación a la vista del detalle de anotación.
	 * @param anotacionBean bean con detalles de la anotación.
	 * @return ruta a vista de anotaciones.
	 */
	public String detalleAnotacion(final AnotacionBean anotacionBean){
		obtieneFlash().put(ParametrosFlashEnum.ANOTACION_BEAN.getParamFlash(), anotacionBean);
		managedBeanStateRecover.enviaController(this, NavegacionEnum.FICHA_CLIENTE);
		return NavegacionEnum.DETALLE_ANOTACIONES.getRuta();
	}

	/**
	 * Método para navegar a la vista de ficha de cuentsa
	 * @return ruta a vista de ficha de cuenta
	 */
	public void fichaCuenta(final SelectEvent event){
		final CuentaClienteBean cuenta = (CuentaClienteBean) event.getObject();
		final CuentaBean cuentaBean = new CuentaBean();
		cuentaBean.setNumeroCuenta(cuenta.getCuenta().getNumeroCuenta());
		obtieneFlash().put(ParametrosFlashEnum.CUENTA_BEAN.getParamFlash(), cuentaBean);
		managedBeanStateRecover.enviaController(this, NavegacionEnum.FICHA_CLIENTE);
		final ConfigurableNavigationHandler configurableNavigationHandler = (ConfigurableNavigationHandler) FacesContext
				.getCurrentInstance().getApplication().getNavigationHandler();

		configurableNavigationHandler.performNavigation(NavegacionEnum.FICHA_CUENTA.getRuta());
	}

	/**
	 * Método para navegar a la vista de documentos de la persona
	 * @return ruta a vista de documentos
	 */
	public String consultaDocumentosPersona(){
        obtieneFlash().put(ParametrosFlashEnum.CLIENTE.getParamFlash(), this.cliente);
        obtieneFlash().put(ParametrosFlashEnum.NOMBRE_CLIENTE.getParamFlash(), cliente.getNombreCompleto());
        obtieneFlash().put(ParametrosFlashEnum.TIPO_CLIENTE.getParamFlash(), this.tipoCliente);
        managedBeanStateRecover.enviaController(this, NavegacionEnum.FICHA_CLIENTE);
        return NavegacionEnum.DOCUMENTOS.getRuta();
    }
	
	/**
     * Método para navegar a la vista de bienes de la persona
     * @return ruta a vista de documentos
     */
    public String consultBienesPersona(){
        obtieneFlash().put(ParametrosFlashEnum.CLIENTE.getParamFlash(), this.cliente);
        obtieneFlash().put(ParametrosFlashEnum.NOMBRE_CLIENTE.getParamFlash(), cliente.getNombreCompleto());
        obtieneFlash().put(ParametrosFlashEnum.TIPO_CLIENTE.getParamFlash(), this.tipoCliente);
        managedBeanStateRecover.enviaController(this, NavegacionEnum.FICHA_CLIENTE);
        return NavegacionEnum.BIENES.getRuta();
    }

	/**
	 * Método encargado de verificar el estado de alertas para el cliente consultado.
	 */
	public void verificarAlertas(){
		if(cliente == null && initException != null){
			if(initException instanceof NoControlableException){
				throw (NoControlableException)initException;
			}else{
				throw (ControlableException)initException;
			}
		}else{
			FacesContext.getCurrentInstance().addMessage("messages",new FacesMessage(FacesMessage.SEVERITY_WARN,
					"¡Atención!","La información del cliente no se encuentra disponible por el momento."));
		}
	}

	/**
	 * Metodo encargado de direccionar a vista de modificar datos de persona version appwhere.
	 *
	 * @return vista modificar datos de persona
	 */
	public String irModificacionDatos() {
		obtieneFlash().put(ParametrosFlashEnum.CLIENTE.getParamFlash(), this.cliente);
		obtieneFlash().put(ParametrosFlashEnum.NAVEGACION_MANAGED_BEAN.getParamFlash(), true);
		if (this.cliente.getTipoClienteEnum().getCodPe().equals(TipoCliente.PERSONA_FISICA.getCodPe()))
			return mx.babel.bansefi.banksystem.base.enums.integracionAppWhere.NavegacionEnum.PERSONASMODIFICARDATOS.getRuta();
		else
			return mx.babel.bansefi.banksystem.base.enums.integracionAppWhere.NavegacionEnum.PERSONASMODIFICARDATOSMORALES.getRuta();
	}

	/**
	 * Metodo encargado de direccionar a vista de relacionar personas version appwhere.
	 *
	 * @return vista relacionar personas
	 */
	public String irRelacionarPersonas() {
		obtieneFlash().put(ParametrosFlashEnum.CLIENTE.getParamFlash(), this.cliente);
		obtieneFlash().put(ParametrosFlashEnum.NAVEGACION_MANAGED_BEAN.getParamFlash(), true);
		if (this.cliente.getTipoClienteEnum().getCodPe().equals(TipoCliente.PERSONA_FISICA.getCodPe()))
			return mx.babel.bansefi.banksystem.base.enums.integracionAppWhere.NavegacionEnum.PERSONASRELACIONARPERSONAS.getRuta();
		else
			return mx.babel.bansefi.banksystem.base.enums.integracionAppWhere.NavegacionEnum.PERSONASRELACIONARPERSONASMORALES.getRuta();
	}

	/**
	 * Metodo encargado de direccionar a vista de agregar documentos version appwhere.
	 *
	 * @return vista agregar documentos
	 */
	public String irAgregarDocumentos() {
		obtieneFlash().put(ParametrosFlashEnum.CLIENTE.getParamFlash(), this.cliente);
		obtieneFlash().put(ParametrosFlashEnum.NAVEGACION_MANAGED_BEAN.getParamFlash(), true);
		if (this.cliente.getTipoClienteEnum().getCodPe().equals(TipoCliente.PERSONA_FISICA.getCodPe()))
			return mx.babel.bansefi.banksystem.base.enums.integracionAppWhere.NavegacionEnum.PERSONASDOCUMENTOS.getRuta();
		else
			return mx.babel.bansefi.banksystem.base.enums.integracionAppWhere.NavegacionEnum.PERSONASDOCUMENTOSMORALES.getRuta();
	}

	/**
	 * Metodo encargado de direccionar a vista de actividad empresarial version appwhere.
	 *
	 * @return vista actividad empresarial
	 */
	public String irActividadEmpresarial() {
		obtieneFlash().put(ParametrosFlashEnum.CLIENTE.getParamFlash(), this.cliente);
		obtieneFlash().put(ParametrosFlashEnum.NAVEGACION_MANAGED_BEAN.getParamFlash(), true);
		return mx.babel.bansefi.banksystem.base.enums.integracionAppWhere.NavegacionEnum.PERSONASACTIVIDADEMPRESARIAL.getRuta();
	}

	/**
	 * Metodo encargado de direccionar a vista de otros datos version appwhere.
	 *
	 * @return vista otros datos
	 */
	public String irOtrosDatos() {
		obtieneFlash().put(ParametrosFlashEnum.CLIENTE.getParamFlash(), this.cliente);
		obtieneFlash().put(ParametrosFlashEnum.NAVEGACION_MANAGED_BEAN.getParamFlash(), true);
		return mx.babel.bansefi.banksystem.base.enums.integracionAppWhere.NavegacionEnum.PERSONASOTROSDATOS.getRuta();
	}

	/**
	 * Metodo encargado de direccionar a vista de anadir domicilios version appwhere.
	 *
	 * @return vista anadir domicilios
	 */
	public String irAnadirDomicilios() {
		obtieneFlash().put(ParametrosFlashEnum.CLIENTE.getParamFlash(), this.cliente);
		obtieneFlash().put(ParametrosFlashEnum.NAVEGACION_MANAGED_BEAN.getParamFlash(), true);
		return mx.babel.bansefi.banksystem.base.enums.integracionAppWhere.NavegacionEnum.PERSONASANADIRDOMICILIOS.getRuta();
	}

	/**
	 * Metodo encargado de direccionar a vista de datos economicos version appwhere.
	 *
	 * @return vista datos economicos
	 */
	public String irDatosEconomicos() {
		obtieneFlash().put(ParametrosFlashEnum.CLIENTE.getParamFlash(), this.cliente);
		obtieneFlash().put(ParametrosFlashEnum.NAVEGACION_MANAGED_BEAN.getParamFlash(), true);
		return mx.babel.bansefi.banksystem.base.enums.integracionAppWhere.NavegacionEnum.PERSONASDATOSECONOMICOS.getRuta();
	}

	/**
	 * Metodo encargado de direccionar a vista de bienes version appwhere.
	 *
	 * @return vista de ampliar bienes
	 */
	public String irAmpliarBienes() {
		obtieneFlash().put(ParametrosFlashEnum.CLIENTE.getParamFlash(), this.cliente);
		obtieneFlash().put(ParametrosFlashEnum.NAVEGACION_MANAGED_BEAN.getParamFlash(), true);
		return mx.babel.bansefi.banksystem.base.enums.integracionAppWhere.NavegacionEnum.PERSONASAMPLIARBIENES.getRuta();
	}

	/**
	 * @return Atributo destino
	 */
	public NavegacionEnum getDestino() {
		return destino;
	}

	/**
	 * @param destino Atributo destino a definir
	 */
	public void setDestino(final NavegacionEnum destino) {
		this.destino = destino;
	}

	/**
	 * @return Atributo destinoController
	 */
	public Object getDestinoController() {
		return destinoController;
	}

	/**
	 * @param destinoController Atributo destinoController a definir
	 */
	public void setDestinoController(final Object destinoController) {
		this.destinoController = destinoController;
	}

	/**
	 * @return El cliente de la ficha
	 */
	public ClienteBean getCliente() {
		return cliente;
	}

	/**
	 * @param cliente Atributo cliente a definir
	 */
	public void setCliente(final ClienteBean cliente) {
		this.cliente = cliente;
	}

	/**
	 * @return Atributo cuentas
	 */
	public List<CuentaClienteBean> getCuentas() {
		return cuentas;
	}

	/**
	 * @param cuentas Atributo cuentas a definir
	 */
	public void setCuentas(final List<CuentaClienteBean> cuentas) {
		this.cuentas = cuentas;
	}

	/**
	 * @return Atributo cuentasTotales
	 */
	public List<CuentaClienteBean> getCuentasTotales() {
		return cuentasTotales;
	}

	/**
	 * @param cuentasTotales Atributo cuentasTotales a definir
	 */
	public void setCuentasTotales(final List<CuentaClienteBean> cuentasTotales) {
		this.cuentasTotales = cuentasTotales;
	}

	/**
	 * @return Atributo estadoCuenta
	 */
	public String getEstadoCuenta() {
		return estadoCuenta;
	}

	/**
	 * @param estadoCuenta Atributo estadoCuenta a definir
	 */
	public void setEstadoCuenta(final String estadoCuenta) {
		this.estadoCuenta = estadoCuenta;
	}

	/**
	 * @return Atributo idInterna
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id Atributo idInterna a definir
	 */
	public void setId(final String id) {
		this.id = id;
	}

	/**
	 * @return Atributo tipoCliente
	 */
	public TipoCliente getTipoCliente() {
		return tipoCliente;
	}

	/**
	 * @param tipoCliente Atributo tipoCliente a definir
	 */
	public void setTipoCliente(final TipoCliente tipoCliente) {
		this.tipoCliente = tipoCliente;
	}

	/**
	 * @return Atributo codigoGrupo
	 */
	public String getCodigoGrupo() {
		return codigoGrupo;
	}

	/**
	 * @param codigoGrupo Atributo codigoGrupo a definir
	 */
	public void setCodigoGrupo(final String codigoGrupo) {
		this.codigoGrupo = codigoGrupo;
	}

	/**
	 * @return Atributo initException
	 */
	public Exception getInitException() {
		return initException;
	}

	/**
	 * @param initException Atributo initException a definir
	 */
	public void setInitException(final Exception initException) {
		this.initException = initException;
	}

	/**
	 * @return Atributo estadosCuenta
	 */
	public List<CatalogoBean> getEstadosCuenta() {
		return estadosCuenta;
	}

	/**
	 * @param estadosCuenta Atributo estadosCuenta a definir
	 */
	public void setEstadosCuenta(final List<CatalogoBean> estadosCuenta) {
		this.estadosCuenta = estadosCuenta;
	}

	/**
	 * @return Atributo mostrarAvisoRepresentante
	 */
	public Boolean getMostrarAvisoRepresentante() {
		return mostrarAvisoRepresentante;
	}

	/**
	 * @param mostrarAvisoRepresentante Atributo mostrarAvisoRepresentante a definir
	 */
	public void setMostrarAvisoRepresentante(final Boolean mostrarAvisoRepresentante) {
		this.mostrarAvisoRepresentante = mostrarAvisoRepresentante;
	}

	/**
	 * @return Atributo anotacionesVisibles
	 */
	public int getAnotacionesVisibles() {
		return anotacionesVisibles;
	}

	/**
	 * @param anotacionesVisibles Atributo anotacionesVisibles a definir
	 */
	public void setAnotacionesVisibles(final int anotacionesVisibles) {
		this.anotacionesVisibles = anotacionesVisibles;
	}

	/**
	 * @return Atributo origenBusqueda
	 */
	public Boolean getOrigenBusqueda() {
		return origenBusqueda;
	}

	/**
	 * @param origenBusqueda Atributo origenBusqueda a definir
	 */
	public void setOrigenBusqueda(Boolean origenBusqueda) {
		this.origenBusqueda = origenBusqueda;
	}

}