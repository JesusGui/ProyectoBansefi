package mx.babel.bansefi.banksystem.administracion.controllers;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.sesion.utils.ContextoUtils;
import mx.babel.bansefi.banksystem.base.backends.consultaempleados.ConsultaEmpleadoBackEnd;
import mx.babel.bansefi.banksystem.base.beans.domain.EmpleadoBean;
import mx.babel.bansefi.banksystem.base.enums.CatalogoEnum;
import mx.babel.bansefi.banksystem.base.enums.NavegacionEnum;
import mx.babel.bansefi.banksystem.base.enums.ParametrosFlashEnum;
import mx.babel.bansefi.banksystem.base.utils.CatalogoCentrosLoaderTask;
import mx.babel.bansefi.banksystem.base.utils.CatalogoUtils;
import mx.babel.bansefi.banksystem.base.utils.ManagedBeanStateRecover;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author javier.martinnino Clase utilizada para la ficha de Empleados
 */
@ManagedBean(name = "fichaEmpleadoController")
@ViewScoped
@Component
@Scope("view")
public class FichaEmpleadoController implements Serializable{
	
	private static final long serialVersionUID = -1586492146636462784L;

	private static final Logger LOGGER = LogManager.getLogger(FichaEmpleadoController.class.getName());
		
	@Autowired
	ContextoUtils contextoUtils;
	
	@Autowired
	CatalogoUtils catalogoUtils;	
	
	@Autowired
	CatalogoCentrosLoaderTask catalogoCentros;
	
	@Autowired
	ManagedBeanStateRecover managedBeanStateRecover;

	/**
	 * Variable utilizada para guardar la redireccion al flujo de entrada
	 * a la consulta de ficha de empleado.
	 */
	private NavegacionEnum destino;
	
	/**
	 * Variable utilizada para guardar el contenido del controller origen
	 * a la consulta de detalles de anotaciones.
	 */
	private Object destinoController;
	
	/**
	 * Backend para consultar los datos del empleado recibido.
	 */
	@Autowired
	private ConsultaEmpleadoBackEnd consultaEmpleadoBackEnd;
			
	/**
	 * Empleado consultado en el sistema.
	 */
	private EmpleadoBean empleado;
	
	/**
	 * Variable para saber si se recupero informacion para renderizar en la vista.
	 */
	private boolean elementosVisibles;
	
	@PostConstruct
	void init() {

		LOGGER.debug("Accedemos a la ficha de empleado");
		
		if (this.obtieneFlash().get(ParametrosFlashEnum.NAVEGACION_MANAGED_BEAN.getParamFlash()) != null) {
			if ((Boolean) this.obtieneFlash().get(ParametrosFlashEnum.NAVEGACION_MANAGED_BEAN.getParamFlash())) {
				LOGGER.debug("Accedemos al metodo de inicio init(), iniciando la navegacion tras volver de otro flujo");
				this.destino = this.managedBeanStateRecover.getDestino();
				this.destinoController = this.managedBeanStateRecover.getController();
				this.inicializaDatosFichaEmpleado();
			} else {
				LOGGER.debug("Accedemos al metodo de inicio init(), recuperando controller tras navegacion");
				this.managedBeanStateRecover.recuperaController(this);
			}
		} else {
			LOGGER.debug("Accedemos al metodo de inicio init() directamente sin acceso de navegacion");
			this.inicializaDatosFichaEmpleado();
		}
						
	}		
	
	/**
	 * @return Metodo utilizado para inicializar el controller
	 * 
	 */
	public void inicializaDatosFichaEmpleado() {
		LOGGER.debug("Accedemos al metodo de inicio init() de la ficha de empleado: ENTRADA");

		this.elementosVisibles = false;
		if (obtieneFlash().get(ParametrosFlashEnum.ID_EMPLEADO.getParamFlash()) != null) {
			
			LOGGER.debug("El idEmpleado encontrado, buscamos los datos del empleado");
			this.empleado = this.consultaEmpleadoBackEnd.ejecutarTRN((String)obtieneFlash().get(ParametrosFlashEnum.ID_EMPLEADO.getParamFlash()));
			this.elementosVisibles = true;	
		}else {
			LOGGER.debug("El idEmpleado no fue encontrado");
			FacesContext.getCurrentInstance() .addMessage( "messages", new FacesMessage(FacesMessage.SEVERITY_WARN,
					"¡Atención!","La información del empleado no se encuentra disponible en este momento."));
		}

		LOGGER.debug("Accedemos al metodo de inicio init() de la ficha de empleado: SALIDA");
	}
	
	/**
	 * @return Metodo utilizado para obtener la descripcion del perfil al que pertenece el empleado
	 * 
	 */
	public String obtenerDescPerfil() {
		try{
			return catalogoUtils.getCatalogoBean(CatalogoEnum.PERFILES_NSS, this.empleado.getPerfil()).getDescripcionL();
		}catch (ControlableException ce){
			LOGGER.debug("Error al intentar obtener la descripcion del perfil a partir del codigo: "+ this.empleado.getPerfil(),ce);
			return "";
		}
	}
	
	/**
	 * @return Metodo utilizado para obtener la descripcion del centro al que pertenece el empleado
	 * 
	 */
	public String obtenerDescCentro() {
		try{
			return catalogoCentros.getCatalogoDescripcion(contextoUtils.getEntidad(),this.empleado.getCentroPertenencia());
		}catch (ControlableException ce){
			LOGGER.debug("Error al intentar obtener la descripcion del centro a partir del codigo: "+ this.empleado.getCentroPertenencia(),ce);
			return "";
		}
	}
	
	/**
	 * @return Metodo utilizado para obtener la descripcion del cargo al que pertenece el empleado
	 * 
	 */
	public String obtenerDescCargo() {
		try{
			return catalogoUtils.getCatalogoBean(CatalogoEnum.TP_CARGO_EMPL, this.empleado.getCargo()).getDescripcionL();
		}catch (ControlableException ce){
			LOGGER.debug("Error al intentar obtener la descripcion del cargo a partir del codigo: "+ this.empleado.getCargo(),ce);
			return "";
		}
	}
	
	/**
	 * Método para obtener el tipo de documento de la id externa del empleado
	 */
	public String obtenerDescTpDoc(){
		try{
			return this.catalogoUtils.getCatalogoBean(CatalogoEnum.TP_ID_EXT_PERS, this.empleado.getDatos().getTipoIdentificacion()).getDescripcionC();			
		}catch(ControlableException ce){
			LOGGER.debug("Error al intentar obtener la descripcion del tipo de documento a partir del codigo: "+ this.empleado.getDatos().getTipoIdentificacion(),ce);
			return "";
		}
	}
	
	/**
	 * Función para obtener la dirección principal del empleado en el formato que se visualiza en la ficha de empleados.
	 * 
	 * @return La dirección del domicilio principal en el formato que se visualiza en la ficha de empleados.
	 */
	public String getDireccion() {
		if (this.empleado.getDatos().getDomicilios().isEmpty() || this.empleado.getDatos().getDomicilios().get(0) == null){
			return "";
		}
		return this.empleado.getDatos().getDomicilios().get(0).getDireccion();
	}
	
	/**
	 * Función para obtener el teléfono del empleado
	 * 
	 * @return El telefono asociado al domicilio principal del empleado.
	 */
	public String getTelefono() {
		if (this.empleado.getDatos().getDomicilios().isEmpty() || this.empleado.getDatos().getDomicilios().get(0) == null){
			return "";
		}
		return this.empleado.getDatos().getDomicilios().get(0).getTelefono();
	}
	
	/**
	 * @return Metodo utilizado para recuperar una instancia de Flash
	 */
	public Flash obtieneFlash() {
		return FacesContext.getCurrentInstance().getExternalContext().getFlash();
	}
	
	/**
	 * @return Metodo utilizado para realizar la accion del boton de volver, a la vista de la busqueda de empleados.
	 */
	public String volver() {
		String ruta = "";
		if (destino == null) {			
			ruta = NavegacionEnum.INICIO.getRuta();
		} else {
			ruta = destino.getRuta();
			managedBeanStateRecover.finNavegacion(destinoController);
		}
		return ruta;
	}
	
	/**
	 * @return Metodo utilizado para redireccionar a la modificacion de datos de empleados.         
	 */	
	public String modificarDatos() {
		obtieneFlash().put(ParametrosFlashEnum.EMPLEADO_BEAN.getParamFlash(), this.empleado);
		obtieneFlash().put(ParametrosFlashEnum.MODIFICACION_EMPLEADO.getParamFlash(), true);
		return NavegacionEnum.ALTA_EMPLEADO1.getRuta();
	}

	/**
	 * @return Metodo utilizado para redireccionar a la modificacion de atribuciones de empleados.         
	 */	
	public String modificarAtribuciones() {
		obtieneFlash().put(ParametrosFlashEnum.EMPLEADO_BEAN.getParamFlash(), this.empleado);
		obtieneFlash().put(ParametrosFlashEnum.MODIFICACION_EMPLEADO.getParamFlash(), true);
		return NavegacionEnum.ATRIBUCIONES_EMPLEADO.getRuta();
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
	 * @return elementosVisibles
	 */
	public boolean isElementosVisibles() {
		return elementosVisibles;
	}
	
	/**
	 * @param elementosVisibles
	 */
	public void setElementosVisibles(boolean elementosVisibles) {
		this.elementosVisibles = elementosVisibles;
	}
	
}
