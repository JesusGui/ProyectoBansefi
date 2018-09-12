package mx.babel.bansefi.banksystem.base.controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;

import mx.babel.arq.catalogo.beans.CatalogoBean;
import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.sesion.utils.ContextoUtils;
import mx.babel.bansefi.banksystem.base.backends.ConsultaCuentaBackend;
import mx.babel.bansefi.banksystem.base.backends.anotaciones.AltaAnotacionesBackEnd;
import mx.babel.bansefi.banksystem.base.backends.anotaciones.AltaRelacionAnotacionesBackEnd;
import mx.babel.bansefi.banksystem.base.backends.anotaciones.ConsultaAnotacionBackEnd;
import mx.babel.bansefi.banksystem.base.backends.anotaciones.ConsultaAnotacionesRelacionadasBackEnd;
import mx.babel.bansefi.banksystem.base.backends.anotaciones.ConsultaRespuestasAnotacionBackEnd;
import mx.babel.bansefi.banksystem.base.backends.anotaciones.ModificacionAnotacionesBackEnd;
import mx.babel.bansefi.banksystem.base.backends.busquedas.BusquedaCuentaBackEnd;
import mx.babel.bansefi.banksystem.base.backends.busquedas.BusquedaIdExternaBackEnd;
import mx.babel.bansefi.banksystem.base.backends.busquedas.BusquedaPersonaMoralBackEnd;
import mx.babel.bansefi.banksystem.base.beans.domain.AnotacionBean;
import mx.babel.bansefi.banksystem.base.beans.domain.ClientePersonaFisicaBean;
import mx.babel.bansefi.banksystem.base.beans.domain.CuentaBean;
import mx.babel.bansefi.banksystem.base.constants.ConstantesFuncionales;
import mx.babel.bansefi.banksystem.base.enums.BusquedaEnum;
import mx.babel.bansefi.banksystem.base.enums.CatalogoEnum;
import mx.babel.bansefi.banksystem.base.enums.NavegacionEnum;
import mx.babel.bansefi.banksystem.base.enums.ParametrosFlashEnum;
import mx.babel.bansefi.banksystem.base.utils.CatalogoUtils;
import mx.babel.bansefi.banksystem.base.utils.ManagedBeanStateRecover;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author javier.martinnino - Clase utilizada para manejar las anotaciones
 */
@ManagedBean(name = "anotaciones2Controller")
@ViewScoped
@Component
@Scope("view")
public class Anotaciones2Controller{
	
	private static final Logger LOGGER = LogManager.getLogger(Anotaciones2Controller.class.getName());
	
	private static final String BATCH_ID = "HPSBATCH";
	private static final String SEPARATOR = " - ";
	
	@Autowired
	ContextoUtils contextoUtils;	
	
	@Autowired
	BusquedaCuentaBackEnd busquedaCuentaBackEnd;
	
	@Autowired
	ConsultaCuentaBackend consultaCuentaBackend;
	
	@Autowired
	BusquedaIdExternaBackEnd busquedaIdExternaBackEnd;
	
	@Autowired
	BusquedaPersonaMoralBackEnd busquedaPersonaMoralBackEnd;
	
	@Autowired
	ConsultaAnotacionBackEnd consultaAnotacionBackEnd;
	
	@Autowired
	ConsultaAnotacionesRelacionadasBackEnd consultaAnotacionesRelacionadasBackEnd;
	
	@Autowired
	ConsultaRespuestasAnotacionBackEnd consultaRespuestasAnotacionBackEnd;
	
	@Autowired
	ModificacionAnotacionesBackEnd modificacionAnotacionesBackEnd;
	
	@Autowired
	AltaAnotacionesBackEnd altaAnotacionesBackEnd;
	
	@Autowired
	AltaRelacionAnotacionesBackEnd altaRelacionAnotacionesBackEnd;
	
	@Autowired
	CatalogoUtils catalogoUtils;
		
	@Autowired
	ManagedBeanStateRecover managedBeanStateRecover;

	/**
	 * Variable utilizada para guardar la redireccion al flujo de entrada
	 * a la consulta de detalles de anotaciones.
	 */
	private NavegacionEnum destino;
	
	/**
	 * Variable utilizada para guardar el contenido del controller origen
	 * a la consulta de detalles de anotaciones.
	 */
	private Object destinoController;
	/**
	 * Lista de anotaciones a dar de alta en el sistema .
	 */
	private List<AnotacionBean> anotaciones;
	
	/**
	 * Anotacion a consultar/contestar .
	 */
	private AnotacionBean anotacion;
	
	/**
	 * Tipo de anotacion seleccionada al pulsar el boton de añadir en el alta.
	 */
	private String tipo;
	
	/**
	 * Dirigido A seleccionada al pulsar el boton de añadir en el alta.
	 */
	private String dirigidoA;
	
	/**
	 * Variable para mostrar los posibles subcodigos para anotaciones informativas.
	 */
	private List<CatalogoBean> subCodigoInformativas;
	
	/**
	 * Variable para mostrar los posibles subcodigos para anotaciones de aviso.
	 */
	private List<CatalogoBean> subCodigoAvisos;
	
	/**
	 * Variable de control para almacenar la posicion del elemento de la lista en que hemos de 
	 * incluir el elemento buscado a traves del buscados cliente o cuenta.
	 */
	private int posicionPersonaCuenta;
	
	/**
	 * Variable para conocer si el usuario que dio de alta la operacion fue el del BATCH.
	 */
	private boolean origenAnotacionBatch;
	
	/**
	 * Variable para habilitar la modificacion de fecha de cierre en la consulta de anotaciones.
	 */
	private boolean accesoModificarFechaCierre;
	
	/**
	 * Variable para mostrar el numero de respuestas de anotacion ocultas.
	 */
	private int respuestasOcultas;
	
	/**
	 * Variable para mostrar el boton de respuesta a anotacion.
	 */
	private boolean activarBotonRespuestaAnotacion;
	
	/**
	 * Variable para mostrar el panel de respuesta a anotacion.
	 */
	private boolean activarRespuestaAnotacion;
	
	/**
	 * Anotacion utilizada para responder al hilo de anotaciones .
	 */
	private AnotacionBean anotacionRespuesta;
	
	private long numeroCuenta;
	/**
	 * Variable utilizada para guardar la redireccion al flujo de entrada a la
	 * consulta de cancelacion de cuenta
	 */

	
	@PostConstruct
	void init() {
					
		// Consulta de detalle de anotaciones desde ficha de Cliente y ficha de Cuenta
		if (obtieneFlash().get(ParametrosFlashEnum.ANOTACION_BEAN.getParamFlash()) != null) {
			
			// Consultamos el detalle de la anotacion recibida para poder pintar todos los datos en la vista
			this.anotacion = (AnotacionBean) obtieneFlash().get(ParametrosFlashEnum.ANOTACION_BEAN.getParamFlash());			
			this.anotacion = this.consultaAnotacionBackEnd.ejecutarTRN(this.anotacion);
			
			this.inicializaConsultaDetalleAnotacion();
		
		// Consulta de detalle de anotaciones desde busqueda
		}else if (obtieneFlash().get(ParametrosFlashEnum.NUMERO_ANOTACION.getParamFlash()) != null) {
				
			// Incializamos la anotacion con los datos que vienen de la busqueda			
			this.anotacion = new AnotacionBean();
			this.anotacion.setNumero((long)obtieneFlash().get(ParametrosFlashEnum.NUMERO_ANOTACION.getParamFlash()));		
			this.anotacion.setTipo((String)obtieneFlash().get(ParametrosFlashEnum.TIPO_ANOTACION.getParamFlash()));		
			this.anotacion.setSubcodigo((String)obtieneFlash().get(ParametrosFlashEnum.SUBCODIGO_ANOTACION.getParamFlash()));		
									
			this.anotacion = this.consultaAnotacionBackEnd.ejecutarTRN(this.anotacion);
			
			this.anotacion.setIdentificadores(new ArrayList<String>());
			
			if (obtieneFlash().get(ParametrosFlashEnum.NUMERO_CUENTA.getParamFlash()) != null && (Long)obtieneFlash().get(ParametrosFlashEnum.NUMERO_CUENTA.getParamFlash()) != 0){
				this.anotacion.getIdentificadores().add(Long.toString((Long)obtieneFlash().get(ParametrosFlashEnum.NUMERO_CUENTA.getParamFlash())));
				this.anotacion.setDirigidoA(ConstantesFuncionales.DEST_ANTCN.get(0));
			}else if (obtieneFlash().get(ParametrosFlashEnum.ID_INTERNA.getParamFlash()) != null && (Integer)obtieneFlash().get(ParametrosFlashEnum.ID_INTERNA.getParamFlash()) != 0){
				this.anotacion.getIdentificadores().add(Integer.toString((Integer)obtieneFlash().get(ParametrosFlashEnum.ID_INTERNA.getParamFlash())));
				this.anotacion.setDirigidoA(ConstantesFuncionales.DEST_ANTCN.get(1));
			}
			
			this.inicializaConsultaDetalleAnotacion();
				
			}
		else if (obtieneFlash().get(ParametrosFlashEnum.ID_INTERNA.getParamFlash()) != null) {
			this.managedBeanStateRecover.recuperaController(this);
			if (!this.anotaciones.get(this.posicionPersonaCuenta).getIdentificadores().contains((Integer.toString((Integer)obtieneFlash().get(ParametrosFlashEnum.ID_INTERNA.getParamFlash()))))){
				this.anotaciones.get(this.posicionPersonaCuenta).getIdentificadores().add((Integer.toString((Integer)obtieneFlash().get(ParametrosFlashEnum.ID_INTERNA.getParamFlash()))));
				if (obtieneFlash().get(ParametrosFlashEnum.NOMBRE_COMPLETO_PERSONA.getParamFlash()) != null){
					this.anotaciones.get(this.posicionPersonaCuenta).getIdentificadoresDescripciones().add((String)obtieneFlash().get(ParametrosFlashEnum.IDENTIFICACION.getParamFlash())
							+ Anotaciones2Controller.SEPARATOR + (String)obtieneFlash().get(ParametrosFlashEnum.NOMBRE_COMPLETO_PERSONA.getParamFlash()));
				}else if (obtieneFlash().get(ParametrosFlashEnum.RAZON_SOCIAL.getParamFlash()) != null){
					this.anotaciones.get(this.posicionPersonaCuenta).getIdentificadoresDescripciones().add((String)obtieneFlash().get(ParametrosFlashEnum.ACTA_CONSTITUTIVA.getParamFlash())
							+ Anotaciones2Controller.SEPARATOR + (String)obtieneFlash().get(ParametrosFlashEnum.RAZON_SOCIAL.getParamFlash()));
				}
				
			}else{
				RequestContext.getCurrentInstance().execute("PF('dlgDestDuplicado').show()");
			}
		}else{
			if (obtieneFlash().get(ParametrosFlashEnum.NUMERO_CUENTA.getParamFlash()) != null)
			{
				this.numeroCuenta = (long) obtieneFlash().get(
						ParametrosFlashEnum.NUMERO_CUENTA.getParamFlash());
			}
			this.destino = this.managedBeanStateRecover.getDestino();
			this.destinoController = this.managedBeanStateRecover
					.getController();
			// Inicializacion del combo de subcodigo para anotaciones informativas y avisos
			this.setSubCodigoInformativas(obtenerSubCodigosInformativas());
			this.setSubCodigoAvisos(obtenerSubCodigosAvisos()); 
						
			// Accedemos al alta de anotaciones	
			this.anotaciones = new ArrayList<>();
			
			this.managedBeanStateRecover.recuperaController(this);
		}
				
	}			
	
	/**
	 * @return Metodo utilizado para inicializar la vista de detalle de anotaciones
	 *         
	 */
	public void inicializaConsultaDetalleAnotacion() {
		this.destino = managedBeanStateRecover.getDestino();
		this.destinoController = managedBeanStateRecover.getController();
					
		// Consultamos los clientes/cuentas relacionados para incluirlos en la vista
		this.anotacion = this.consultaAnotacionesRelacionadasBackEnd.ejecutarTRN(this.anotacion);
								
		// Se inicializan los datos de la anotacion de respuesta por si el usuario decide responder al hilo de anotaciones
		this.anotacionRespuesta = this.inicializaAnotacionRespuesta();			
		
		// Comprobamos si el usuario que accede es el mismo que dio de alta la anotacion para permitir modificar 
		// la fecha de cierre de la anotacion ademas de comprobar que la anotacion esta activa y ademas si la anotacion
		// esta activa mostramos el boton de responder para permitir al usuario contestar al hilo de anotaciones
		if (this.anotacion.getIdEmpleado().equals(Anotaciones2Controller.BATCH_ID)){
			this.setOrigenAnotacionBatch(true);
		}
		
		// Comprobamos si el usuario que accede es el mismo que dio de alta la anotacion para permitir modificar 
		// la fecha de cierre de la anotacion ademas de comprobar que la anotacion esta activa y ademas si la anotacion
		// esta activa mostramos el boton de responder para permitir al usuario contestar al hilo de anotaciones
		if (ConstantesFuncionales.COD_ESTADO_ANOTACION_ACTIVA.equals(this.anotacion.getCodEstadoAnotacion())){
			setActivarBotonRespuestaAnotacion(true);	
			if (contextoUtils.getId().equals(this.anotacion.getIdEmpleado())){
				setAccesoModificarFechaCierre(true);
			}
		}
					
		// Buscamos todas las respuestas a la anotacion
		this.anotaciones = this.consultaRespuestasAnotacionBackEnd.ejecutarTRN(this.anotacion);
		
		if (this.anotaciones !=null){
			this.respuestasOcultas = this.anotaciones.size();
			int busquedaDetalle = 5;
			if (busquedaDetalle > this.respuestasOcultas){
				busquedaDetalle = this.respuestasOcultas;
				this.respuestasOcultas=0;
				for (int i=this.anotaciones.size()-1;i>=0;i--){
					this.anotaciones.set(i, this.consultaAnotacionBackEnd.ejecutarTRN(this.anotaciones.get(i)));
				}
			}else{
				this.respuestasOcultas = this.respuestasOcultas - busquedaDetalle;
				for (int i=this.anotaciones.size()-1;i>this.anotaciones.size()-1-busquedaDetalle;i--){
					this.anotaciones.set(i, this.consultaAnotacionBackEnd.ejecutarTRN(this.anotaciones.get(i)));
				}
			}			
		}				
	}
	
	/**
	 * @return String - La descripcion del tipo de la anotacion buscada
	 *         
	 */
	public String obtenerTipoAnotacionBuscada(String clave) {
		try{
			return catalogoUtils.getCatalogoBean(CatalogoEnum.TIPO_ANOTACION,clave).getDescripcionL();
		}catch (ControlableException ce){
			LOGGER.debug("Error al intentar obtener la descripcion del tipo de anotacion a partir del codigo: "+ clave,ce);
			return "";
		}		
	}
	
	/**
	 * @return String - La descripcion del subcodigo de la anotacion buscada
	 *         
	 */
	public String obtenerSubCodigoAnotacionBuscada(String clave) {
		try{
			return catalogoUtils.getCatalogoBean(CatalogoEnum.TP_ANTCN,clave).getDescripcionL();
		}catch (ControlableException ce){
			LOGGER.debug("Error al intentar obtener la descripcion del subcodigo de anotacion a partir del codigo: "+ clave,ce);
			return "";
		}		
	}
	
	/**
	 * @return String - La descripcion del area de la anotacion buscada
	 *         
	 */
	public String obtenerAreaAnotacionBuscada(String clave) {
		try{
			return catalogoUtils.getCatalogoBean(CatalogoEnum.TP_GRP_ANTCN,clave).getDescripcionL();
		}catch (ControlableException ce){
			LOGGER.debug("Error al intentar obtener la descripcion del area de anotacion a partir del codigo: "+ clave,ce);
			return "";
		}		
	}
		
	/**
	 * @return List<CatalogoBean> utilizado para cargar el combo de seleccion de subcodigos para anotaciones
	 * 	de tipo informativas filtrando el catalogo TP_ANTCN
	 *         
	 */
	private List<CatalogoBean> obtenerSubCodigosInformativas() {
		LOGGER.debug("Obtenemos los valores de combo de subcodigos para anotaciones informativas: ENTRADA");
		List<CatalogoBean> resultado = new ArrayList<>();
		
		List<CatalogoBean> resultadoBusqueda = catalogoUtils.getCatalogo(CatalogoEnum.TP_ANTCN);
		List<String> subCodInformativas = ConstantesFuncionales.getSubcodigosAnotacionesInformativas();
		for (int i=0;i<resultadoBusqueda.size();i++){
			if (subCodInformativas.contains(resultadoBusqueda.get(i).getClaveFila())){
				resultado.add(resultadoBusqueda.get(i));
			}
		}
		LOGGER.debug("Obtenemos los valores de combo de subcodigos para anotaciones informativas: SALIDA");
		return resultado;
	}
	
	/**
	 * @return List<CatalogoBean> utilizado para cargar el combo de seleccion de subcodigos para anotaciones
	 * 	de tipo avisos filtrando el catalogo TP_ANTCN
	 *         
	 */
	private List<CatalogoBean> obtenerSubCodigosAvisos() {
		LOGGER.debug("Obtenemos los valores de combo de subcodigos para anotaciones avisos: ENTRADA");
		List<CatalogoBean> resultado = new ArrayList<>();
		
		List<CatalogoBean> resultadoBusqueda = catalogoUtils.getCatalogo(CatalogoEnum.TP_ANTCN);
		Pattern patronAvisos = Pattern.compile(ConstantesFuncionales.SUBCODIGOS_ANOTACIONES_AVISOS);
		for (int i=0;i<resultadoBusqueda.size();i++){
			Matcher comparador = patronAvisos.matcher((CharSequence) resultadoBusqueda.get(i).getClaveFila());
			if (comparador.matches()){
				resultado.add(resultadoBusqueda.get(i));
			}
		}
		LOGGER.debug("Obtenemos los valores de combo de subcodigos para anotaciones avisos: SALIDA");
		return resultado;
	}
	
	/**
	 * Metodo utilizado para inicializar la anotacion de respuesta en el detalle de anotaciones
	 * con los datos
	 * 
	 * @return AnotacionBean
	 */
	public AnotacionBean inicializaAnotacionRespuesta() {
		LOGGER.debug("Se accede al metodo que inicializa la anotacion de respuesta: ENTRADA");
		AnotacionBean anotacionRes = new AnotacionBean();
		anotacionRes.setTipo(this.anotacion.getTipo());
		anotacionRes.setSubcodigo(this.anotacion.getSubcodigo());
		anotacionRes.setNumero(this.anotacion.getNumero());
		anotacionRes.setDirigidoA(this.anotacion.getDirigidoA());
		anotacionRes.setArea(this.anotacion.getArea());
		anotacionRes.setFechaCierre(this.anotacion.getFechaCierre());
		anotacionRes.setIdentificadores(this.anotacion.getIdentificadores());
		anotacionRes.setIdEmpleado(contextoUtils.getId());
		anotacionRes.setNombreEmpleado(contextoUtils.getNombre());				
		LOGGER.debug("Se accede al metodo que inicializa la anotacion de respuesta: SALIDA");
		return anotacionRes;
	}
	
	/**
	 * @return Metodo utilizado para mostrar el panel de respuesta a anotacion
	 * 
	 */
	public void mostrarPanelRespuestaAnotacion() {
		LOGGER.debug("Se accede al metodo que renderiza el panel de respuesta a anotacion: ENTRADA");
		this.activarRespuestaAnotacion=true;				
		LOGGER.debug("Se accede al metodo que renderiza el panel de respuesta a anotacion: SALIDA");
	}
	
	/**
	 * @return Metodo utilizado para ocultar el panel de respuesta a anotacion
	 * 
	 */
	public void ocultarPanelRespuestaAnotacion() {
		LOGGER.debug("Se accede al metodo que oculta el panel de respuesta a anotacion: ENTRADA");
		this.activarRespuestaAnotacion=false;				
		LOGGER.debug("Se accede al metodo que oculta el panel de respuesta a anotacion: SALIDA");
	}
	
	/**
	 * @return Metodo utilizado para mostrar el panel de respuesta
	 * 
	 */
	public void responderAnotacion() {
		LOGGER.debug("Se accede al metodo que añade una respuesta a la anotacion: ENTRADA");
		this.altaAnotacionesBackEnd.ejecutarTRN(this.anotacionRespuesta,true);
		// Se incluye la anotacion respondida en el hilo de respuestas con fecha de inicio igual a hoy.
		this.anotacionRespuesta.setFechaInicio(this.contextoUtils.getFechaContableActual());		
		if (this.anotaciones == null){
			this.anotaciones = new ArrayList<>();
		}
		this.anotaciones.add(this.anotacionRespuesta);
		// Se vuelve a inicializar el valor de la anotacion de respuesta y se vuelve a ocultar el dialogo 
		// de respuesta del boton
		this.anotacionRespuesta = this.inicializaAnotacionRespuesta();
		this.activarRespuestaAnotacion=false;
		LOGGER.debug("Se accede al metodo que añade una respuesta a la anotacion: SALIDA");
	}
	
	/**
	 * @return Metodo que despliega mas respuesta en el hilo de respuesta de anotaciones
	 *         
	 */
	public void verMasRespuestasAnotaciones() {
		LOGGER.debug("Añadimos mas anotaciones al hilo de respuestas: ENTRADA");
		if (this.anotaciones !=null){
			int busquedaDetalle = 5;
			if (busquedaDetalle > this.respuestasOcultas){
				busquedaDetalle = this.respuestasOcultas;				
				for (int i=this.respuestasOcultas-1;i>=0;i--){
					this.anotaciones.set(i, this.consultaAnotacionBackEnd.ejecutarTRN(this.anotaciones.get(i)));
				}
				this.respuestasOcultas=0;
			}else{				
				for (int i=this.respuestasOcultas-1;i>this.respuestasOcultas-1-busquedaDetalle;i--){
					this.anotaciones.set(i, this.consultaAnotacionBackEnd.ejecutarTRN(this.anotaciones.get(i)));
				}
				this.respuestasOcultas = this.respuestasOcultas - busquedaDetalle;
			}			
		}
		LOGGER.debug("Añadimos mas anotaciones al hilo de respuestas: SALIDA");
	}
	
	/**
	 * @return Metodo utilizado para añadir anotaciones en el alta
	 * 
	 */
	public void anadirAnotacion() {
		LOGGER.debug("Se incluye una nueva anotacion: ENTRADA");
		
		AnotacionBean anot = new AnotacionBean();
		List<String> identificadores = new ArrayList<>();
		List<String> identificadoresDescripciones = new ArrayList<>();
		anot.setIdentificadores(identificadores);
		anot.setIdentificadoresDescripciones(identificadoresDescripciones);
		anot.setTipo(this.tipo);
		anot.setDirigidoA(this.dirigidoA);
		anot.setRegistrada(false);
		anot.setIdentificadorBusqueda(Long.toString(this.numeroCuenta));
		getAnotaciones().add(anot);
		
		LOGGER.debug("Se incluye una nueva anotacion: SALIDA");
	}
	
	/**
	 * @return Metodo utilizado para eliminar anotaciones en el listado de alta
	 * 
	 */
	public void eliminaAnotacion(AnotacionBean anotacion) {
		LOGGER.debug("Se eliminar la anotacion seleccionada: ENTRADA");
		this.anotaciones.remove(anotacion);
		LOGGER.debug("Se eliminar la anotacion seleccionada: SALIDA");
	}
	
	/**
	 * @return Metodo utilizado para añadir identificadores para una determinada anotacion
	 * 
	 */
	public void anadirElemento(AnotacionBean anotacion,int posicion) {
		LOGGER.debug("Metodo anadirElemento: ENTRADA");		
		if (anotacion.getIdentificadorBusqueda() == null || ("").equals(anotacion.getIdentificadorBusqueda())){
			return;
		}
		else
		{
			anadirSiExisteElemento(anotacion,posicion);
		}
		/*else if(anotacion.getIdentificadores().contains(anotacion.getIdentificadorBusqueda())){
			return;
		}else{
			anadirSiExisteElemento(anotacion,posicion);
		}*/
		LOGGER.debug("Metodo anadirElemento: SALIDA");	
		return;
	}
	
	/**
	 * @return Metodo utilizado para añadir identificadores para una determinada anotacion
	 * 
	 */
	public void anadirSiExisteElemento(AnotacionBean anotacion,int posicion) {
		LOGGER.debug("Metodo anadirSiExisteElemento: ENTRADA");	
		try{
			
			if (anotacion.getDirigidoA().equals(ConstantesFuncionales.DEST_ANTCN.get(0))){				
				CuentaBean cuenta = this.busquedaCuentaBackEnd.ejecutarWS(Long.parseLong(anotacion.getIdentificadorBusqueda()));
				if (cuenta != null && cuenta.getNumeroCuenta() !=0){
					String identificador = anotacion.getIdentificadorBusqueda();
					if(!existeValor(anotacion, identificador)){
						LOGGER.debug("Se incluye el elemento a la lista");		
						anotacion.getIdentificadores().add(identificador);
						anotacion.getIdentificadoresDescripciones().add(anotacion.getIdentificadorBusqueda()+Anotaciones2Controller.SEPARATOR+
								this.consultaCuentaBackend.ejecutarTRN(Long.parseLong(anotacion.getIdentificadorBusqueda())).getTipoCuenta());

						anotacion.setIdentificadorBusqueda("");
					}else{
						RequestContext.getCurrentInstance().execute("PF('dlgDestDuplicado').show()");
					}			
				}else{
					RequestContext.getCurrentInstance().execute("PF('dlgDestNoEncontrado').show()");
				}
				
			}else if (anotacion.getDirigidoA().equals(ConstantesFuncionales.DEST_ANTCN.get(1))){				 
				final List<ClientePersonaFisicaBean> clientes = this.busquedaIdExternaBackEnd
						.ejecutarTRN(anotacion.getIdentificadorBusqueda());
				
				if(clientes.size() > 1){					
					this.posicionPersonaCuenta = posicion;
					RequestContext.getCurrentInstance().execute("PF('dlgBusqueda').show()");
				}else{
					if (!clientes.isEmpty()) {
						String identificador = clientes.get(0).getIdInterna().toString();
						if(!existeValor(anotacion, identificador)){
							anotacion.getIdentificadores().add(clientes.get(0).getIdInterna().toString());
							anotacion.getIdentificadoresDescripciones().add(anotacion.getIdentificadorBusqueda()+
									Anotaciones2Controller.SEPARATOR+clientes.get(0).getNombreCompleto());

							anotacion.setIdentificadorBusqueda("");
						}else{
							RequestContext.getCurrentInstance().execute("PF('dlgDestDuplicado').show()");
						}
						
					} else {
						RequestContext.getCurrentInstance().execute("PF('dlgDestNoEncontrado').show()");
					}
				}				
			}
			
		}catch (NumberFormatException nfe){
			LOGGER.debug("Error en la validacion del input del formulario de alta de anotaciones");
			RequestContext.getCurrentInstance().execute("PF('dlgDestNumerico').show()");			
		}				
		
		LOGGER.debug("Metodo anadirSiExisteElemento: SALIDA");
	}
	
	private boolean existeValor(AnotacionBean anotacion, String identificador){
		if(anotacion.getIdentificadores().contains(identificador)){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * @return Metodo utilizado para eliminar identificadores de una determinada anotacion
	 * 
	 */
	public void eliminaElemento(AnotacionBean anotacion, String identificador) {
		LOGGER.debug("Se elimina el identificador seleccionada de la anotacion seleccionada: ENTRADA");



		
		int posicion = anotacion.getIdentificadoresDescripciones().indexOf(identificador);
		if (posicion != -1){
			anotacion.getIdentificadores().remove(posicion);
			anotacion.getIdentificadoresDescripciones().remove(posicion);

		}
		
		
		LOGGER.debug("Se elimina el identificador seleccionada de la anotacion seleccionada: SALIDA");
	}
	
	/**
	 * @return Metodo utilizado para guardar anotaciones
	 * 
	 */
	public void guardarAnotaciones() {		
		boolean encontrada = false;
		if (anotaciones == null || anotaciones.isEmpty()){
			return;
		}
		
		for (int i=0;i<this.anotaciones.size() && !encontrada;i++){
			if(!(this.anotaciones.get(i).getIdentificadores().size()>0)){
				encontrada = true;
			}
		}
		if (encontrada){
			RequestContext.getCurrentInstance().execute("PF('dlgUnDestinatario').show()");
		}else{
			for (int i=0;i<this.anotaciones.size();i++){
				
				if(this.anotaciones.get(i).getRegistrada()==false)
				{
					LOGGER.debug("Damos de alta la anotacion con la primera cuenta/persona");
					this.anotaciones.get(i).setNumero(this.altaAnotacionesBackEnd.ejecutarTRN(this.anotaciones.get(i),false));
					this.anotaciones.get(i).setRegistrada(true);
					for (int j=1;j<this.anotaciones.get(i).getIdentificadores().size();j++){
						LOGGER.debug("Relacionamos a la anotacion creada el resto de cuentas/personas");
						this.altaRelacionAnotacionesBackEnd.ejecutarTRN(this.anotaciones.get(i), j);
					}
				}
			}
			RequestContext.getCurrentInstance().execute("PF('dlgAltaCorrecta').show()");
			LOGGER.debug("Salimos de la modificacion del cliente con exito");
		}
	}
	
	/**
	 * @return Metodo utilizado para modificar la fecha de cierre de una anotacion
	 * 
	 */
	public void modificarFechaCierreAnotacion(AnotacionBean anotacion) {		
		this.modificacionAnotacionesBackEnd.ejecutarTRN(anotacion);
		// Volvemos a refrescar el contenido de la anotacion de respuesta ya que cambio
		// la fecha de cierre de la anotacion
		this.anotacionRespuesta = this.inicializaAnotacionRespuesta();
		RequestContext.getCurrentInstance().execute("PF('dlgModiFechaCierre').show()");
	}
	
	/**
	 * @return Metodo utilizado para recuperar una instancia de Flash
	 */
	public Flash obtieneFlash() {
		return FacesContext.getCurrentInstance().getExternalContext().getFlash();
	}
	
	/**
	 * @return Metodo utilizado para la busqueda de Clientes
	 * 
	 */
	public String buscarCuentas(int posicion) {
		this.posicionPersonaCuenta = posicion;
		obtieneFlash().put(ParametrosFlashEnum.TIPO_BUSQUEDA.getParamFlash(),BusquedaEnum.CUENTAS.getBusquedaValor());
		managedBeanStateRecover.enviaController(this, NavegacionEnum.ALTA_ANOTACIONES);
		return NavegacionEnum.BUSQUEDA.getRuta();
	}
	
	/**
	 * @return Metodo utilizado para la busqueda de Clientes
	 * 
	 */
	public String buscarClientes(int posicion) {
		this.posicionPersonaCuenta = posicion;
		obtieneFlash().put(ParametrosFlashEnum.TIPO_BUSQUEDA.getParamFlash(),BusquedaEnum.PERSONA_CLIENTE.getBusquedaValor());
		managedBeanStateRecover.enviaController(this, NavegacionEnum.ALTA_ANOTACIONES);
		return NavegacionEnum.BUSQUEDA.getRuta();
	}
	
	/**
	 * @return Metodo utilizado para la busqueda de Clientes
	 * 
	 */
	public String buscarClientesMoral(int posicion) {
		this.posicionPersonaCuenta = posicion;
		obtieneFlash().put(ParametrosFlashEnum.TIPO_BUSQUEDA.getParamFlash(),BusquedaEnum.PERSONA_MORAL.getBusquedaValor());
		managedBeanStateRecover.enviaController(this, NavegacionEnum.ALTA_ANOTACIONES);
		return NavegacionEnum.BUSQUEDA.getRuta();
	}
	
	/**
	 * @return Metodo utilizado para cancelar el alta de Anotaciones
	 * 
	 */
	public String cancelarAltaAnotaciones() {
		LOGGER.debug("Se cancela el alta de anotaciones");
		String ruta = "";
		if (this.destino == null) {
			ruta = NavegacionEnum.BUSQUEDA.getRuta();
		} else {
			ruta = destino.getRuta();
			managedBeanStateRecover.finNavegacion(destinoController);
		}
		return ruta;
	}
	
	/**
	 * @return Metodo utilizado para volver desde la vista de detalle de anotaciones al
	 * flujo origen o al inicio en su defecto.
	 * 
	 */
	public String volver() {
		String ruta = "";
		if (destino == null) {			
			ruta = NavegacionEnum.INICIO.getRuta();
		} else {
			ruta = destino.getRuta();
			obtieneFlash().put(ParametrosFlashEnum.RETURN_AND_REFRESH.getParamFlash(), true);
			managedBeanStateRecover.finNavegacion(destinoController);
		}
		return ruta;	
	}
	
	/**
	 * @return Metodo utilizado para acceder al flujo de alta de anotaciones
	 */
	public String inicio() {
		LOGGER.debug("Se accede al flujo de alta de anotaciones");
		//return NavegacionEnum.ALTA_ANOTACIONES.getRuta();
		
		obtieneFlash().put(ParametrosFlashEnum.NUMERO_CUENTA.getParamFlash(),
				this.numeroCuenta);
		obtieneFlash().put(ParametrosFlashEnum.RETURN_AND_REFRESH.getParamFlash(), true);
		managedBeanStateRecover.finNavegacion(destinoController);
		
		
		return NavegacionEnum.ALTA_ANOTACIONES_GASTOS.getRuta();
	}
		
	/**
	 * @return Metodo utilizado para redireccionar a la consulta de anotaciones
	 */	
	public String detalleAnotacion() {
		return NavegacionEnum.DETALLE_ANOTACIONES.getRuta();
	}

	public List<AnotacionBean> getAnotaciones() {
		return anotaciones;
	}

	public void setAnotaciones(List<AnotacionBean> anotaciones) {
		this.anotaciones = anotaciones;
	}

	public AnotacionBean getAnotacion() {
		return anotacion;
	}

	public void setAnotacion(AnotacionBean anotacion) {
		this.anotacion = anotacion;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getDirigidoA() {
		return dirigidoA;
	}

	public void setDirigidoA(String dirigidoA) {
		this.dirigidoA = dirigidoA;
	}

	public List<CatalogoBean> getSubCodigoInformativas() {
		return subCodigoInformativas;
	}

	public void setSubCodigoInformativas(List<CatalogoBean> subCodigoInformativas) {
		this.subCodigoInformativas = subCodigoInformativas;
	}

	public List<CatalogoBean> getSubCodigoAvisos() {
		return subCodigoAvisos;
	}

	public void setSubCodigoAvisos(List<CatalogoBean> subCodigoAvisos) {
		this.subCodigoAvisos = subCodigoAvisos;
	}

	public int getPosicionPersonaCuenta() {
		return posicionPersonaCuenta;
	}

	public void setPosicionPersonaCuenta(int posicionPersonaCuenta) {
		this.posicionPersonaCuenta = posicionPersonaCuenta;
	}

	public boolean isOrigenAnotacionBatch() {
		return origenAnotacionBatch;
	}

	public void setOrigenAnotacionBatch(boolean origenAnotacionBatch) {
		this.origenAnotacionBatch = origenAnotacionBatch;
	}

	public boolean isAccesoModificarFechaCierre() {
		return accesoModificarFechaCierre;
	}

	public void setAccesoModificarFechaCierre(boolean accesoModificarFechaCierre) {
		this.accesoModificarFechaCierre = accesoModificarFechaCierre;
	}

	public int getRespuestasOcultas() {
		return respuestasOcultas;
	}

	public void setRespuestasOcultas(int respuestasOcultas) {
		this.respuestasOcultas = respuestasOcultas;
	}

	public boolean isActivarBotonRespuestaAnotacion() {
		return activarBotonRespuestaAnotacion;
	}

	public void setActivarBotonRespuestaAnotacion(
			boolean activarBotonRespuestaAnotacion) {
		this.activarBotonRespuestaAnotacion = activarBotonRespuestaAnotacion;
	}

	public boolean isActivarRespuestaAnotacion() {
		return activarRespuestaAnotacion;
	}

	public void setActivarRespuestaAnotacion(boolean activarRespuestaAnotacion) {
		this.activarRespuestaAnotacion = activarRespuestaAnotacion;
	}

	public AnotacionBean getAnotacionRespuesta() {
		return anotacionRespuesta;
	}

	public void setAnotacionRespuesta(AnotacionBean anotacionRespuesta) {
		this.anotacionRespuesta = anotacionRespuesta;
	}
	
	public String getCurrentDate() {
		return new SimpleDateFormat("dd/MM/yyyy").format(contextoUtils.getFechaContableActual());
	}
	
}