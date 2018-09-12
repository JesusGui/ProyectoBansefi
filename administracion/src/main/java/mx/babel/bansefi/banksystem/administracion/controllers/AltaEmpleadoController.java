

package mx.babel.bansefi.banksystem.administracion.controllers;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.validator.ValidatorException;

import mx.babel.arq.catalogo.beans.CatalogoBean;
import mx.babel.arq.catalogo.beans.CatalogoGeoBean;
import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.sesion.utils.ContextoUtils;
import mx.babel.arq.storage.annotations.StoreStep;
import mx.babel.arq.storage.beans.StorageMgrBean;
import mx.babel.bansefi.banksystem.administracion.backends.mantenimientoempleado.AltaEmpleadoBackEnd;
import mx.babel.bansefi.banksystem.administracion.backends.mantenimientoempleado.ModificacionEmpleadoBackEnd;
import mx.babel.bansefi.banksystem.base.backends.ConsultaPersonaFisicaBackEnd;
import mx.babel.bansefi.banksystem.base.beans.domain.AtribucionBean;
import mx.babel.bansefi.banksystem.base.beans.domain.ClientePersonaFisicaBean;
import mx.babel.bansefi.banksystem.base.beans.domain.DomicilioTipoBean;
import mx.babel.bansefi.banksystem.base.beans.domain.EmpleadoBean;
import mx.babel.bansefi.banksystem.base.beans.domain.TransaccionalidadBean;
import mx.babel.bansefi.banksystem.base.beans.domain.UsoCuentaBean;
import mx.babel.bansefi.banksystem.base.constants.ConstantesFuncionales;
import mx.babel.bansefi.banksystem.base.controllers.DomicilioController;
import mx.babel.bansefi.banksystem.base.enums.BusquedaEnum;
import mx.babel.bansefi.banksystem.base.enums.CatalogoEnum;
import mx.babel.bansefi.banksystem.base.enums.NavegacionEnum;
import mx.babel.bansefi.banksystem.base.enums.ParametrosFlashEnum;
import mx.babel.bansefi.banksystem.base.utils.CatalogoCentrosLoaderTask;
import mx.babel.bansefi.banksystem.base.utils.CatalogoListaEmpleadosLoaderTask;
import mx.babel.bansefi.banksystem.base.utils.CatalogoPaisesUtils;
import mx.babel.bansefi.banksystem.base.utils.CatalogoUtils;
import mx.babel.bansefi.banksystem.base.utils.DomicilioUtils;
import mx.babel.bansefi.banksystem.base.utils.ManagedBeanStateRecover;

import org.apache.commons.lang3.SerializationUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SlideEndEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author javier.martinnino Clase utilizada para el alta de Empleados
 */
@ManagedBean(name = "altaEmpleadoController")
@ViewScoped
@Component
@Scope("view")
public class AltaEmpleadoController extends StorageMgrBean implements Serializable {
	
	private static final long serialVersionUID = 5847419563064741280L;

	private static final Logger LOGGER = LogManager.getLogger(AltaEmpleadoController.class.getName());
	
	private static final String NOENCONTRADO = "No se ha encontrado ningún cliente para el id interno proporcionado";
	
	
	/**
	 * Modificaciones NQC
	 */

	private static final String NOCORRESPONDE = "3440 Identificador no se corresponde con tipo de Persona";
	
	private static final String TCB03440 = "TCB03440";

	/**
	 * Modificaciones NQC
	 */
	
	@Autowired
	ContextoUtils contextoUtils;
	
	@Autowired
	DomicilioUtils domicilioUtils;
			
	@Autowired
	private ConsultaPersonaFisicaBackEnd clienteConsultaPersonaFisica;
	
	@Autowired
	private ModificacionEmpleadoBackEnd modificacionEmpleadoBackEnd;
	
	@Autowired
	private AltaEmpleadoBackEnd altaEmpleadoBackEnd;
	
	@Autowired
    ManagedBeanStateRecover managedBeanStateRecover;
	
	@Autowired
	CatalogoUtils catalogoUtils;
	
	@Autowired
	CatalogoCentrosLoaderTask catalogoCentros;
	
	@Autowired
	CatalogoListaEmpleadosLoaderTask catalogoEmpleados;
	
	@Autowired
	CatalogoPaisesUtils catalogoPaisesUtils;
	
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
	 * Empleado a dar de alta en el sistema .
	 */
	private EmpleadoBean empleado;
	
	/**
	 * Empleado consultado a ser modificado, se utiliza para mantener los valores de la consulta
	 * y poder observar las modificaciones para los indicadores de entrada al servicio de modificacion.
	 */
	private EmpleadoBean empleadoConsultado;
	
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
	 * Opcion de alta de empleado seleccionada.
	 */
	private Integer opcionAltaEmpleado;
	
	/**
	 * Variable para saber almacenar el valor del idexterna del empleado a buscar desde la vista de filtro de empleados.
	 */
	private Integer idInternaBusqueda;
	
	/**
	 * Nombre de cliente encontrado en el filtro de busqueda de Empleados a partir de clientes.
	 */
	private String nombreClienteEncontrado;
	
	/**
	 * Variable para saber si hay que mostrar el nombre del cliente.
	 */
	private Boolean mostrarCliente;
	
	/**
	 * Variable para saber si estamos en la modificacion de empleados
	 */
	private Boolean modificacionEmpleado;
	
	/**
	 * Variable para mostrar los posibles tipos de identificacion a seleccionar.
	 */
	private List<CatalogoBean> selectIdentificaciones;
	
	/**
	 * Variable para mostrar los posibles comprobantes de domicilio a seleccionar.
	 */
	private List<CatalogoBean> selectComprobanteDomicilio;
		
	/**
	 * Variable para recuperar el catalogBean de CNAE elegido en la modificacion de cliente
	 */
	private CatalogoBean cnaeSelected;
		
	/**
	 * Variable para recuperar el catalogBean de CENTRO elegido en la modificacion de cliente
	 */
	private CatalogoBean centroSelected;
	
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
	
	@Autowired
	private DomicilioController domicilioController;
	
	
	@PostConstruct
	void init() {
		super.restoreflash();
		
		if(this.obtieneFlash().get(ParametrosFlashEnum.NAVEGACION_MANAGED_BEAN.getParamFlash())!=null){
			if ((Boolean) this.obtieneFlash().get(ParametrosFlashEnum.NAVEGACION_MANAGED_BEAN.getParamFlash())) {
				destino = managedBeanStateRecover.getDestino();
				destinoController = managedBeanStateRecover.getController();
				this.inicializaAltaModificacionEmpleados();
			}else{
				managedBeanStateRecover.recuperaController(this);
				// Comprobacion si venimos de la busqueda para de la persona fisica.		
				if(this.obtieneFlash().get(ParametrosFlashEnum.ID_INTERNA.getParamFlash()) != null){
					managedBeanStateRecover.recuperaController(this);
					setIdInternaBusqueda((Integer) this.obtieneFlash().get(ParametrosFlashEnum.ID_INTERNA.getParamFlash()));
					this.buscarPorIdInterna();
					
				}
			}
		}else{
			this.inicializaAltaModificacionEmpleados();
		}			
		
	}
	
	/**
	 * @return Metodo utilizado para inicializar el controller
	 * 
	 */
	public void inicializaAltaModificacionEmpleados() {
		LOGGER.debug("Accedemos al metodo de inicio init() del alta/modificacion de empleados: ENTRADA");
		
		this.opcionAltaEmpleado = 0;

		this.selectTransaccionalidad = new ArrayList<>();
		this.selectTransaccionalidad.add(0, "Honorarios");
		this.selectTransaccionalidad.add(1, "Arrendamiento");
		this.selectTransaccionalidad.add(2, "Primas,rifas o sorteos");
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
		
		// Comprobacion si venimos de la busqueda para de la persona fisica.		
		if(this.obtieneFlash().get(ParametrosFlashEnum.ID_INTERNA.getParamFlash()) != null){
			managedBeanStateRecover.recuperaController(this);
			setIdInternaBusqueda((Integer) this.obtieneFlash().get(ParametrosFlashEnum.ID_INTERNA.getParamFlash()));
			this.buscarPorIdInterna();
			
		}
		
		// Recuperamos el bean de empleado y si estamos en alta o modificacion
		else if (obtieneFlash().get(ParametrosFlashEnum.EMPLEADO_BEAN.getParamFlash()) != null) {
			this.empleado = (EmpleadoBean) obtieneFlash().get(ParametrosFlashEnum.EMPLEADO_BEAN.getParamFlash());
			this.domicilioController.setDomicilioBean(this.empleado.getDatos().getDomicilios().get(0));
			if (obtieneFlash().get(ParametrosFlashEnum.EMPLEADO_BEAN_CONSULTADO.getParamFlash()) != null) {
				this.empleadoConsultado = (EmpleadoBean) obtieneFlash().get(ParametrosFlashEnum.EMPLEADO_BEAN_CONSULTADO.getParamFlash());
			}
			
			if (obtieneFlash().get(ParametrosFlashEnum.MODIFICACION_EMPLEADO.getParamFlash()) != null) {
				this.modificacionEmpleado = (Boolean) obtieneFlash().get(ParametrosFlashEnum.MODIFICACION_EMPLEADO.getParamFlash());
				if (this.empleadoConsultado == null){
					this.empleadoConsultado = SerializationUtils.clone(this.empleado);
				}
			}
			if(this.empleadoConsultado!=null){
				this.domicilioController.setDomicilioBean(this.empleadoConsultado.getDatos().getDomicilios().get(0));
			}
			
		// Accedemos al flujo de alta inicializando los objetos necesarios.
		} else {
			setNombreClienteEncontrado(AltaEmpleadoController.NOENCONTRADO);
						
			this.empleado = new EmpleadoBean();
			this.empleado.setDatos(inicializaDatosPersonaEmpleado());
			this.empleado.setAtribucionesTemporales(new ArrayList<AtribucionBean>());
						
			this.empleado.setAtribucionFija(new AtribucionBean());
			Calendar atrCal = Calendar.getInstance();
			atrCal.set(9999, 11, 31, 0, 0);  
			this.empleado.getAtribucionFija().setFechaInicio(atrCal.getTime());
			this.empleado.getAtribucionFija().setFechaFin(atrCal.getTime());
			
			this.cnaeSelected = new CatalogoBean();			
			this.paisNacimientoSelected = new CatalogoBean();
			this.oficinaSelected = new CatalogoBean();
			
		}
		
		LOGGER.debug("Accedemos al metodo de inicio init() del alta/modificacion de empleados: SALIDA");
	}
	
	/**
	 * @return Metodo utilizado para inicializar los datos de persona fisica
	 *         
	 */
	public ClientePersonaFisicaBean inicializaDatosPersonaEmpleado(){
		
		UsoCuentaBean usoCuenta = new UsoCuentaBean();
		TransaccionalidadBean transaccionalidad = new TransaccionalidadBean();

		List<DomicilioTipoBean> domicilios = new ArrayList<>();
		domicilioController.setDomicilioBean(new DomicilioTipoBean());

		domicilios.add(domicilioController.getDomicilioBean());

		List<String> operaciones = new ArrayList<>();
		List<String> usos = new ArrayList<>();

		usoCuenta.setOperaciones(operaciones);
		usoCuenta.setUsos(usos);

		List<String> trans = new ArrayList<>();
		transaccionalidad.setTransaccionalidad(trans);
		
		ClientePersonaFisicaBean datos = new ClientePersonaFisicaBean();
		datos.setDomicilios(domicilios);
		datos.setUsoCuenta(usoCuenta);
		datos.setTransaccionalidad(transaccionalidad);
		datos.setEstado(new CatalogoGeoBean());
		
		datos.setPaisNacionalidad(ConstantesFuncionales.COD_PAIS_MEXICO);
		datos.setPaisResidencia(ConstantesFuncionales.COD_PAIS_MEXICO);
		
		return datos;
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
			if (codComprobantes.contains(resultadoBusqueda.get(i).getClaveFila())){
				resultado.add(resultadoBusqueda.get(i));
			}
		}
		LOGGER.debug("Obtenemos los valores de combo de comprobante de domicilio: SALIDA");
		return resultado;
	}
	
	/**
	 * @return Metodo utilizado para la carga en el componente de domicilio
	 *         
	 */
	public void manejarComponenteDomicilio() {
		if( domicilioController.getDomicilioBean().getCodigoPostalCatGeo()==null && domicilioController.getDomicilioBean().getMunicipioCatGeo()==null ){
			domicilioController.limpiarCamposDomicilio();
		}else{
			domicilioController.cargaDatosDomicilio();
		}
		this.empleado.getDatos().getDomicilios().set(0, domicilioController.getDomicilioBean());
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
	 * @return Metodo utilizado para la recarga del campo estados en localidades no codificadas         
	 */
	public void actualizaEstado(){
		this.empleado.getDatos().setEstado(new CatalogoGeoBean());		
	}
	
	/**
	 * @return Metodo utilizado para la carga de datos de nacimiento en funcion del municipio/localidad seleccionado
	 *         
	 */
	public void actualizaDatosNacimiento(){
		if (this.empleado.getDatos().getMunicipioCatGeo() ==null || (this.empleado.getDatos().getMunicipioCatGeo() !=null && ((this.empleado.getDatos().getMunicipioCatGeo().getCodigoPostal() !=null 
				&& !("").equals(this.empleado.getDatos().getMunicipioCatGeo().getCodigoPostal())) || this.empleado.getDatos().getMunicipioCatGeo().getNombreMunicipio() == null 
				||("").equals(this.empleado.getDatos().getMunicipioCatGeo().getNombreMunicipio())))){
			this.empleado.getDatos().setEstado(new CatalogoGeoBean());
			this.setPaisNacimientoSelected(null);
			this.empleado.getDatos().setPais(null);
		}
	}
	
	/**
	 * @return Metodo utilizado para actualizar el valor del campo de oficina en relacion al check
	 * 
	 */
	public void actualizarOficina() {
		
		if (this.empleado.getDatos().getCorrespondenciaOficina() == null || !this.empleado.getDatos().getCorrespondenciaOficina()) {
			this.setOficinaSelected(null);
			this.empleado.getDatos().setNumOficina(null);
		}
	}
	
	/**
	 * @return Metodo utilizado para actualizar el valor del campo Otros en uso de cuenta y transaccionalidad
	 *         
	 */
	public void actualizaDatosCedula(){
		if (!this.empleado.getDatos().getTransaccionalidad().getTransaccionalidad().contains("Otros")){
			this.empleado.getDatos().getTransaccionalidad().setProvieneOtros(null);
		}
		if (!this.empleado.getDatos().getUsoCuenta().getUsos().contains("Otros")){
			this.empleado.getDatos().getUsoCuenta().setOtrosUsos(null);
		}
	}
	
	/**
	 * @return Metodo utilizado para crear/modificar empleados
	 * 
	 */
	
	public String crearModificarEmpleado() {
		String retorno = "";
		Boolean valido=true;
//RAV20160225 ini - validación en complementación de porcentaje.
		int suma = Integer.parseInt(this.empleado.getDatos().getUsoCuenta().getManejoRecursosPropios())
		+ Integer.parseInt(this.empleado.getDatos().getUsoCuenta().getManejoRecursosTerceros());
		if(suma != 100){
			valido=false;
			RequestContext.getCurrentInstance().execute("PF('dlgPorcentajes').show()");
		}	
//RAV20160225 ini - validación en complementación de porcentaje.		
		
		Boolean contiene= this.empleado.getDatos().getUsoCuenta().getUsos().contains("Otros");
		
		
		
		if(contiene== true)
		{
			if(this.empleado.getDatos().getUsoCuenta().getOtrosUsos().trim().length()==0)
			{
				
				valido=false;
				FacesContext.getCurrentInstance().addMessage(null, 
		                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Debe de especificar el uso otros de su cuenta","Debe de especificar el uso otros de su cuenta"));     

				
			}
		}
		
		contiene=this.empleado.getDatos().getTransaccionalidad().getTransaccionalidad().contains("Otros");
		
		if(contiene==true)
		{
		
			if(this.empleado.getDatos().getTransaccionalidad().getProvieneOtros().trim().length()==0)
			{
				valido=false;
				FacesContext.getCurrentInstance().addMessage(null, 
		                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Debe de especificar el proviene otros","Debe de especificar el proviene otros"));    
			}
		}
		
		RequestContext.getCurrentInstance().update("message");
		
		if(valido ==true)
		{
		
				if (this.modificacionEmpleado !=null && this.modificacionEmpleado){
					
					if(!this.empleado.getDatos().getEstadoCivil().equals(ConstantesFuncionales.TP_EST_CIVIL_INDV_CASADO))
					{
						this.empleado.getDatos().setRegimenEconomico(null);
					}
					
					LOGGER.debug("Accedemos al metodo de modificacion de empleado");		
					this.modificacionEmpleadoBackEnd.ejecutarTRN(this.empleado,this.empleadoConsultado);
					RequestContext.getCurrentInstance().execute("PF('dlgModificacionCorrecta').show()");
					LOGGER.debug("Salimos de la modificacion del empleado con exito");
					
				}else{
					LOGGER.debug("Accedemos al metodo de alta de empleado");
					this.altaEmpleadoBackEnd.ejecutarTRN(empleado);
					RequestContext.getCurrentInstance().execute("PF('dlgAltaCorrecta').show()");
					LOGGER.debug("Salimos del alta del empleado con exito");
				}	
			
		}
		return retorno;
	}
	
	/**
	 * @return Metodo utilizado para la busqueda por id externa de clientes
	 * 
	 */
	public void buscarPorIdInterna() {
		LOGGER.debug("Accedemos a la busqueda del cliente a traves de su id Interna: ENTRADA");
		setMostrarCliente(true);
		try{
			this.empleado.setDatos((ClientePersonaFisicaBean)this.clienteConsultaPersonaFisica.ejecutarTRN(getIdInternaBusqueda()));			
			if (this.empleado.getDatos() !=null && this.empleado.getDatos().getIdInterna() !=null && this.empleado.getDatos().getIdInterna() !=0){
				LOGGER.debug("Cliente Encontrado");
				setNombreClienteEncontrado(this.empleado.getDatos().getNombreCompleto());
				domicilioController.setDomicilioBean((DomicilioTipoBean)this.empleado.getDatos().getDomicilios().get(0));
			}else{
				   LOGGER.debug("Cliente No Encontrado");
				   setNombreClienteEncontrado(AltaEmpleadoController.NOENCONTRADO);
				   this.empleado.setDatos(inicializaDatosPersonaEmpleado());

				
			}

		}catch (ControlableException ce){			
	/**Modificaciones NQC */
			if (ce.getMensajeUsuario().equals(TCB03440)){				
				setNombreClienteEncontrado(AltaEmpleadoController.NOCORRESPONDE);								
			}else{
	/**Modificaciones NQC */			
			   LOGGER.debug("Error consultando la persona por idInterna",ce);
			   setNombreClienteEncontrado(AltaEmpleadoController.NOENCONTRADO);
			   this.empleado.getDatos().setIdInterna(null);
			}
		}
		
		LOGGER.debug("Accedemos a la busqueda del cliente a traves de su id Interna: SALIDA");
	}
	
	/**
	 * @return Metodo utilizado para la busqueda de Clientes
	 * 
	 */
	public String buscarClientes() {
		obtieneFlash().put(ParametrosFlashEnum.TIPO_BUSQUEDA.getParamFlash(),BusquedaEnum.PERSONA_CLIENTE.getBusquedaValor());
		managedBeanStateRecover.enviaController(this, NavegacionEnum.ALTA_EMPLEADO_FILTRO);
		return NavegacionEnum.BUSQUEDA.getRuta();
	}
	
	/**
	 * Método que es llamado al generar un evento en los radio botones y cambiar
	 * el valor de la opcion.
	 * 
	 * @param event
	 *            Del radio seleccionado en la ventana.
	 * @return String
	 */
	public String actualizarOpcion(SlideEndEvent event) {
		this.opcionAltaEmpleado = new Integer(event.getValue());
		return null;
	}
	
	/**
	 * @return Metodo utilizado para redireccionar al paso 1 del alta de
	 *         Clientes
	 */
	@StoreStep
	public String irAPaso1DesdeFiltro() {
		LOGGER.debug("Accedemos al primer paso del alta de empleado desde la ventana de filtro de empleados: ENTRADA");
		CatalogoBean idEmpleado = null;
		try{
			idEmpleado = catalogoEmpleados.getCatalogoBean(contextoUtils.getEntidad(), this.empleado.getNumEmpleado());
		}catch (ControlableException ce){
			LOGGER.debug("No se encontro el idEmpleado en el catalogo, por lo que podemos usar el idEmpleado incluido"
					+ "en el formulario",ce);
		}
		
		if(idEmpleado !=null){
			LOGGER.debug("El Id. Empleado introducido ya existe para esta entidad");
			RequestContext.getCurrentInstance().execute("PF('dlgEmpleadoExistente').show();");
			return null;
		}
		
		
		if (this.empleado.getDatos().getIdInterna() ==null || this.opcionAltaEmpleado==1){
			
			LOGGER.debug("Nuevo empleado a partir de persona no existente");			
			this.empleado.setDatos(inicializaDatosPersonaEmpleado());
		}		
		
		obtieneFlash().put(ParametrosFlashEnum.EMPLEADO_BEAN.getParamFlash(), this.empleado);
		LOGGER.debug("Accedemos al primer paso del alta de empleado desde la ventana de filtro de empleados: SALIDA");
		
		return NavegacionEnum.ALTA_EMPLEADO1.getRuta();
	}
	
	/**
	 * @return Metodo utilizado para cancelar el alta de Clientes
	 *         
	 */
	public String redireccionAltaModificacion() {		
		if (this.modificacionEmpleado !=null && this.modificacionEmpleado){
			obtieneFlash().put(ParametrosFlashEnum.ID_EMPLEADO.getParamFlash(),this.empleado.getNumEmpleado());
			return NavegacionEnum.FICHA_EMPLEADO.getRuta();
		}else{
			obtieneFlash().put(ParametrosFlashEnum.EMPLEADO_BEAN.getParamFlash(),this.empleado);
			obtieneFlash().put(ParametrosFlashEnum.MODIFICACION_EMPLEADO.getParamFlash(),this.modificacionEmpleado);
			return NavegacionEnum.ATRIBUCIONES_EMPLEADO.getRuta();
		}
	}
	
	/**
	 * @return Metodo utilizado para cancelar el alta de Empleados
	 * 
	 */
	public String cancelarAltaModificacion() {
		String ruta = "";
		if (this.destino == null) {
			if (this.modificacionEmpleado !=null && this.modificacionEmpleado){
				obtieneFlash().put(ParametrosFlashEnum.ID_EMPLEADO.getParamFlash(), this.empleado.getNumEmpleado());
				ruta = NavegacionEnum.FICHA_EMPLEADO.getRuta();				
			}else{
				ruta = NavegacionEnum.INICIO.getRuta();
			}
		} else {
			ruta = destino.getRuta();
			managedBeanStateRecover.finNavegacion(destinoController);
		}
		return ruta;
	}

	/**
	 * @return Metodo utilizado para acceder al paso 1 del alta de Clientes por
	 *         primera vez
	 */
	@StoreStep
	public String inicio() {
		return NavegacionEnum.ALTA_EMPLEADO_FILTRO.getRuta();
	}
	
	/**
	 * @return Metodo utilizado para redireccionar al paso 1 del alta de
	 *         Clientes
	 */
	public String irAPasoFiltro() {
		return NavegacionEnum.ALTA_EMPLEADO_FILTRO.getRuta();
	}
	
	
	/**
	 * @return Metodo utilizado para redireccionar al paso 1 del alta de
	 *         Clientes
	 */
	@StoreStep
	public String irAPaso1() {
		obtieneFlash().put(ParametrosFlashEnum.EMPLEADO_BEAN.getParamFlash(), this.empleado);
		obtieneFlash().put(ParametrosFlashEnum.MODIFICACION_EMPLEADO.getParamFlash(), this.modificacionEmpleado);
		obtieneFlash().put(ParametrosFlashEnum.EMPLEADO_BEAN_CONSULTADO.getParamFlash(), this.empleadoConsultado);
		obtieneFlash().put(ParametrosFlashEnum.DESTINO.getParamFlash(), this.destino);
		obtieneFlash().put(ParametrosFlashEnum.DESTINO_CONTROLLER.getParamFlash(), this.destinoController);
		return NavegacionEnum.ALTA_EMPLEADO1.getRuta();
	}

	/**
	 * @return Metodo utilizado para redireccionar al paso 2 del alta de
	 *         Clientes
	 */
	@StoreStep
	public String irAPaso2() {
		this.empleado.getDatos().getDomicilios().set(0, domicilioController.getDomicilioBean());
		obtieneFlash().put(ParametrosFlashEnum.EMPLEADO_BEAN.getParamFlash(), this.empleado);
		obtieneFlash().put(ParametrosFlashEnum.MODIFICACION_EMPLEADO.getParamFlash(), this.modificacionEmpleado);
		obtieneFlash().put(ParametrosFlashEnum.EMPLEADO_BEAN_CONSULTADO.getParamFlash(), this.empleadoConsultado);
		obtieneFlash().put(ParametrosFlashEnum.DESTINO.getParamFlash(), this.destino);
		obtieneFlash().put(ParametrosFlashEnum.DESTINO_CONTROLLER.getParamFlash(), this.destinoController);
		return NavegacionEnum.ALTA_EMPLEADO2.getRuta();
	}

	/**
	 * @return Metodo utilizado para redireccionar al paso 3 del alta de
	 *         Clientes
	 */
	@StoreStep
	public String irAPaso3() {
		obtieneFlash().put(ParametrosFlashEnum.EMPLEADO_BEAN.getParamFlash(), this.empleado);
		obtieneFlash().put(ParametrosFlashEnum.MODIFICACION_EMPLEADO.getParamFlash(), this.modificacionEmpleado);
		obtieneFlash().put(ParametrosFlashEnum.EMPLEADO_BEAN_CONSULTADO.getParamFlash(), this.empleadoConsultado);
		obtieneFlash().put(ParametrosFlashEnum.DESTINO.getParamFlash(), this.destino);
		obtieneFlash().put(ParametrosFlashEnum.DESTINO_CONTROLLER.getParamFlash(), this.destinoController);
		return NavegacionEnum.ALTA_EMPLEADO3.getRuta();
	}

	/**
	 * @return Metodo utilizado para recuperar una instancia de Flash
	 */
	public Flash obtieneFlash() {
		return FacesContext.getCurrentInstance().getExternalContext().getFlash();
	}

	/**
	 * @return beanList
	 */
	@Override
	protected Map<String, Object> getBeanList() {
		Map<String, Object> beanList = new HashMap<String, Object>();
		beanList.put(EmpleadoBean.class.getName(), empleado);
		beanList.put(Boolean.class.getName(), modificacionEmpleado);
		beanList.put(ParametrosFlashEnum.EMPLEADO_BEAN_CONSULTADO.getParamFlash(), this.empleadoConsultado);
		return beanList;
	}

	/**
	 * @param beanList
	 */
	@Override
	protected void setBeanList(Map<String, Object> beanList) {
		if (null != beanList) {
			this.empleado = (EmpleadoBean) beanList.get(EmpleadoBean.class.getName());
			this.modificacionEmpleado = (Boolean) beanList.get(Boolean.class.getName());
			this.empleadoConsultado = (EmpleadoBean) beanList.get(ParametrosFlashEnum.EMPLEADO_BEAN_CONSULTADO.getParamFlash());
			obtieneFlash().put(ParametrosFlashEnum.EMPLEADO_BEAN.getParamFlash(), this.empleado);
			obtieneFlash().put(ParametrosFlashEnum.MODIFICACION_EMPLEADO.getParamFlash(), this.modificacionEmpleado);
			obtieneFlash().put(ParametrosFlashEnum.EMPLEADO_BEAN_CONSULTADO.getParamFlash(), this.empleadoConsultado);
		}

	}

	@Override
	protected String getNombreFlujo() {
		if (this.modificacionEmpleado !=null && this.modificacionEmpleado){
			return "Modificacion de empleado: "+this.empleado.getDatos().getTratamiento()+" "+this.empleado.getDatos().getNombre()+" "+this.empleado.getDatos().getApePaterno()+" "+this.empleado.getDatos().getApeMaterno()
					+"(ID: "+this.empleado.getNumEmpleado()+")";
		}else{
			return "Alta de empleado: "+this.empleado.getDatos().getTratamiento()+" "+this.empleado.getDatos().getNombre()+" "+this.empleado.getDatos().getApePaterno()+" "+this.empleado.getDatos().getApeMaterno()
					+"(ID: "+this.empleado.getNumEmpleado()+")";
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
	 * @return empleado
	 */
	public EmpleadoBean getEmpleado() {
		return empleado;
	}

	/**
	 * @param empleado
	 */
	public void setEmpleado(EmpleadoBean empleado) {
		this.empleado = empleado;
	}

	/**
	 * @return empleadoConsultado
	 */
	public EmpleadoBean getEmpleadoConsultado() {
		return empleadoConsultado;
	}

	/**
	 * @param empleadoConsultado
	 */
	public void setEmpleadoConsultado(EmpleadoBean empleadoConsultado) {
		this.empleadoConsultado = empleadoConsultado;
	}

	/**
	 * @return opcionAltaEmpleado
	 */
	public Integer getOpcionAltaEmpleado() {
		return opcionAltaEmpleado;
	}

	/**
	 * @param opcionAltaEmpleado
	 */
	public void setOpcionAltaEmpleado(Integer opcionAltaEmpleado) {
		this.opcionAltaEmpleado = opcionAltaEmpleado;
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
	 * @return the selectComprobanteDomicilio
	 */
	public List<CatalogoBean> getSelectComprobanteDomicilio() {
		return selectComprobanteDomicilio;
	}

	/**
	 * @param selectComprobanteDomicilio the selectComprobanteDomicilio to set
	 */
	public void setSelectComprobanteDomicilio(
			List<CatalogoBean> selectComprobanteDomicilio) {
		this.selectComprobanteDomicilio = selectComprobanteDomicilio;
	}
	
	/**
	 * @return idInternaBusqueda
	 */
	public Integer getIdInternaBusqueda() {
		return idInternaBusqueda;
	}

	/**
	 * @param idExternaBusqueda
	 */
	public void setIdInternaBusqueda(Integer idInternaBusqueda) {
		this.idInternaBusqueda = idInternaBusqueda;
	}
	
	/**
	 * @return nombreClienteEncontrado
	 */
	public String getNombreClienteEncontrado() {
		return nombreClienteEncontrado;
	}

	/**
	 * @param nombreClienteEncontrado
	 */
	public void setNombreClienteEncontrado(String nombreClienteEncontrado) {
		this.nombreClienteEncontrado = nombreClienteEncontrado;
	}
	
	/**
	 * @return mostrarCliente
	 */
	public Boolean getMostrarCliente() {
		return mostrarCliente;
	}

	/**
	 * @param mostrarCliente
	 */
	public void setMostrarCliente(Boolean mostrarCliente) {
		this.mostrarCliente = mostrarCliente;
	}

	/**
	 * @return modificacionEmpleado
	 */
	public Boolean getModificacionEmpleado() {
		return modificacionEmpleado;
	}

	/**
	 * @param modificacionEmpleado
	 */
	public void setModificacionEmpleado(Boolean modificacionEmpleado) {
		this.modificacionEmpleado = modificacionEmpleado;
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
	 * @return cnaeSelected
	 */
	public CatalogoBean getCnaeSelected() {
		if (this.empleado.getDatos().getCnae() != null && !("").equals(this.empleado.getDatos().getCnae())){
			cnaeSelected = catalogoUtils.getCatalogoBean(CatalogoEnum.TP_CNAE_PERS, this.empleado.getDatos().getCnae());
		}
		return cnaeSelected;
	}

	/**
	 * @param cnaeSelected
	 */
	public void setCnaeSelected(CatalogoBean cnaeSelected) {
		this.cnaeSelected = cnaeSelected;
		if (this.cnaeSelected != null){
			this.empleado.getDatos().setCnae(this.cnaeSelected.getClaveFila());
		}
	}
	
	/**
	 * @return centroSelected
	 */
	public CatalogoBean getCentroSelected() {
		if (this.empleado.getCentroPertenencia() != null && !("").equals(this.empleado.getCentroPertenencia())){
			centroSelected = catalogoCentros.getCatalogoBean(contextoUtils.getEntidad(), this.empleado.getCentroPertenencia());
		}
		return centroSelected;
	}
	
	/**
	 * @param centroSelected
	 */
	public void setCentroSelected(CatalogoBean centroSelected) {
		this.centroSelected = centroSelected;
		if (this.centroSelected != null){
			this.empleado.setCentroPertenencia(this.centroSelected.getClaveFila());
		}
	}
	
	/**
	 * @return the paisNacionalidadSelected
	 */
	public CatalogoBean getPaisNacionalidadSelected() {
		if (this.empleado.getDatos().getPaisNacionalidad() != null && !("").equals(this.empleado.getDatos().getPaisNacionalidad())){
			paisNacionalidadSelected = catalogoPaisesUtils.getCatalogoBean(this.empleado.getDatos().getPaisNacionalidad());
		}
		return paisNacionalidadSelected;
	}

	/**
	 * @param paisNacionalidadSelected
	 */
	public void setPaisNacionalidadSelected(CatalogoBean paisNacionalidadSelected) {
		this.paisNacionalidadSelected = paisNacionalidadSelected;
		if (this.paisNacionalidadSelected != null){
			this.empleado.getDatos().setPaisNacionalidad(this.paisNacionalidadSelected.getClaveFila());
		}
	}

	/**
	 * @return the paisResidenciaSelected
	 */
	public CatalogoBean getPaisResidenciaSelected() {
		if (this.empleado.getDatos().getPaisResidencia() != null && !("").equals(this.empleado.getDatos().getPaisResidencia())){
			paisResidenciaSelected = catalogoPaisesUtils.getCatalogoBean(this.empleado.getDatos().getPaisResidencia());
		}
		return paisResidenciaSelected;
	}

	/**
	 * @param paisResidenciaSelected the paisResidenciaSelected to set
	 */
	public void setPaisResidenciaSelected(CatalogoBean paisResidenciaSelected) {
		this.paisResidenciaSelected = paisResidenciaSelected;
		if (this.paisNacionalidadSelected != null){
			this.empleado.getDatos().setPaisResidencia(this.paisResidenciaSelected.getClaveFila());
		}
	}

	public CatalogoBean getPaisNacimientoSelected() {
		if (this.empleado.getDatos().getPais() != null && !("").equals(this.empleado.getDatos().getPais())){
			paisNacimientoSelected = catalogoPaisesUtils.getCatalogoBean(this.empleado.getDatos().getPais());
		}
		return paisNacimientoSelected;
	}

	public void setPaisNacimientoSelected(CatalogoBean paisNacimientoSelected) {
		this.paisNacimientoSelected = paisNacimientoSelected;
		if (this.paisNacimientoSelected != null){
			this.empleado.getDatos().setPais(this.paisNacimientoSelected.getClaveFila());
		}
	}

	/**
	 * @return the oficinaSelected
	 */
	public CatalogoBean getOficinaSelected() {
		if (this.empleado.getDatos().getNumOficina() != null && !("").equals(this.empleado.getDatos().getNumOficina())){
			oficinaSelected = catalogoCentros.getCatalogoBean(contextoUtils.getEntidad(),this.empleado.getDatos().getNumOficina());
		}
		return oficinaSelected;
	}

	/**
	 * @param oficinaSelected the oficinaSelected to set
	 */
	public void setOficinaSelected(CatalogoBean oficinaSelected) {
		this.oficinaSelected = oficinaSelected;
		if (this.oficinaSelected != null){
			this.empleado.getDatos().setNumOficina(this.oficinaSelected.getClaveFila());
		}
	}
	
	public DomicilioController getDomicilioController() {
		return domicilioController;
	}

	public void setDomicilioController(DomicilioController domicilioController) {
		this.domicilioController = domicilioController;
	}
	
	public String getCurrentDate() {
		return new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime());
	}

}
