package mx.babel.bansefi.banksystem.administracion.controllers;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

import mx.babel.arq.sesion.utils.ContextoUtils;
import mx.babel.bansefi.banksystem.administracion.backends.atribuciones.AltaAtribucionesBackEnd;
import mx.babel.bansefi.banksystem.administracion.backends.atribuciones.BajaAtribucionesBackEnd;
import mx.babel.bansefi.banksystem.administracion.backends.atribuciones.ModificacionAtribucionesBackEnd;
import mx.babel.bansefi.banksystem.base.backends.atribuciones.ConsultaAtribucionesBackEnd;
import mx.babel.bansefi.banksystem.base.beans.domain.AtribucionBean;
import mx.babel.bansefi.banksystem.base.beans.domain.EmpleadoBean;
import mx.babel.bansefi.banksystem.base.constants.ConstantesFuncionales;
import mx.babel.bansefi.banksystem.base.enums.EstadoListadosEnum;
import mx.babel.bansefi.banksystem.base.enums.NavegacionEnum;
import mx.babel.bansefi.banksystem.base.enums.ParametrosFlashEnum;
import mx.babel.bansefi.banksystem.base.utils.BeanBackUpUtils;
import mx.babel.bansefi.banksystem.base.utils.ManagedBeanStateRecover;

import org.apache.commons.lang3.SerializationUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author javier.martinnino Clase utilizada para el manejo de atribuciones de Empleados
 */
@ManagedBean(name = "atribucionesController")
@ViewScoped
@Component
@Scope("view")
public class AtribucionesController implements Serializable{
	
	private static final long serialVersionUID = 5847419563064741280L;

	private static final Logger LOGGER = LogManager.getLogger(AtribucionesController.class.getName());

	@Autowired
	ContextoUtils contextoUtils;
	
	@Autowired
    ManagedBeanStateRecover managedBeanStateRecover;
	
	@Autowired
    BeanBackUpUtils backUpUtils;
	
	@Autowired
	ConsultaAtribucionesBackEnd consultaAtribucionesBackEnd;
	
	@Autowired
	AltaAtribucionesBackEnd altaAtribucionesBackEnd;
	
	@Autowired
	ModificacionAtribucionesBackEnd modificacionAtribucionesBackEnd;
	
	@Autowired
	BajaAtribucionesBackEnd bajaAtribucionesBackEnd;
	/**
	 * Variable utilizada para guardar la redireccion al flujo de entrada a la
	 * modificacion de atribuciones.
	 */
	private NavegacionEnum destino;

	/**
	 * Variable utilizada para guardar el contenido del controller origen a la
	 * modificacion de atribuciones.
	 */
	private Object destinoController;
	
	/**
	 *  empleado a consultar/modificar/crear atribuciones.
	 */
	private EmpleadoBean empleado;

	/**
	 * Variable para saber si estamos en modificacion o alta de atribuciones.
	 */
	private Boolean modificacionEmpleado;
	
	/**
	 * Variable para manejar el tab activo en cada momento.
	 */
	private int activeIndex;
	
	/**
	 * Variable para manejar la fecha de inicio a incluir como nueva fecha de atribucion temporal.
	 */
	private Date fechaInicio;
	
	/**
	 * Variable para manejar la fecha de fin a incluir como nueva fecha de atribucion temporal.
	 */
	private Date fechaFin;
	
	/**
	 * Variable para manejar el respaldo de la atribucion fija consultada.
	 */
	private AtribucionBean respaldoAtribucionFija;
	
	@PostConstruct
	void init() {
		
		if(this.obtieneFlash().get(ParametrosFlashEnum.NAVEGACION_MANAGED_BEAN.getParamFlash())!=null){
			if ((Boolean) this.obtieneFlash().get(ParametrosFlashEnum.NAVEGACION_MANAGED_BEAN.getParamFlash())) {
				destino = managedBeanStateRecover.getDestino();
				destinoController = managedBeanStateRecover.getController();
				this.inicializaAtribuciones();
			}else{
				managedBeanStateRecover.recuperaController(this);				
			}
		}else{
			this.inicializaAtribuciones();
		}			
		
	}
	
	/**
	 * @return Metodo utilizado para inicializar el controller
	 * 
	 */
	public void inicializaAtribuciones() {
		LOGGER.debug("Accedemos al metodo de inicio init() del alta/modificacion de atribuciones de empleados: ENTRADA");
		
		this.activeIndex=0;
		
		// Recuperamos el bean de empleado y si estamos en alta o modificacion
		if (obtieneFlash().get(ParametrosFlashEnum.EMPLEADO_BEAN.getParamFlash()) != null) {
			this.empleado = (EmpleadoBean) obtieneFlash().get(ParametrosFlashEnum.EMPLEADO_BEAN.getParamFlash());
			if (obtieneFlash().get(ParametrosFlashEnum.MODIFICACION_EMPLEADO.getParamFlash()) != null) {
				this.modificacionEmpleado = (Boolean) obtieneFlash().get(ParametrosFlashEnum.MODIFICACION_EMPLEADO.getParamFlash());
				if (this.modificacionEmpleado){
					if (this.empleado.getAtribucionFija() == null){	
						this.empleado.setAtribucionFija(new AtribucionBean());
					}
					if (this.empleado.getAtribucionesTemporales() == null){
						this.empleado.setAtribucionesTemporales(new ArrayList<AtribucionBean>());
					}
				}
			}
			
			if (this.empleado.getAtribucionesTemporales() !=null && !this.empleado.getAtribucionesTemporales().isEmpty()
					&& this.empleado.getAtribucionesTemporales().get(0).getRespaldo() == null
					&& this.empleado.getAtribucionesTemporales().get(0).getEstado() == EstadoListadosEnum.CONSULTADO){
				this.backUpUtils.respaldaArray((ArrayList<AtribucionBean>) this.empleado.getAtribucionesTemporales());				
			}
			
			respaldoAtribucionFija = SerializationUtils.clone(this.getEmpleado().getAtribucionFija());
			
		} 
		
		LOGGER.debug("Salimos del metodo de inicio init() del alta/modificacion de atribuciones de empleados: SALIDA");
	}
	
	/**
	 * Método que a partir de la fecha actual adiciona 1 día y devuelve la fecha
	 * fin mínima para una atribucion.
	 * 
	 * @return fechaActual 
	 */
	public String obtenerFechaFinMinima(Date fechaInicio) {
		DateFormat formatter = new SimpleDateFormat(ConstantesFuncionales.GENERAL_DATE_FORMATTER);
		if (fechaInicio != null) {
			return formatter.format(fechaInicio);
		} else {
			return formatter.format(Calendar.getInstance().getTime());
		}
	}
	
	/**
	 * @return Metodo utilizado para incluir una nueva atribucion temporal 
	 * 
	 */
	public void anadirAtribucionTemporal() {
		LOGGER.debug("Accedemos al metodo de alta de nueva atribucion temporal de empleados: ENTRADA");
		
		// Comprobamos la fecha de inicio de la atribucion temporal con respecto a las existentes para saber si 
		// podemos incluirla en el listado
		boolean encontrado = false;
		for (int i=0;i<this.empleado.getAtribucionesTemporales().size() && !encontrado;i++){
			if (this.fechaInicio.before(this.empleado.getAtribucionesTemporales().get(i).getFechaInicio()) && 
					this.fechaFin.after(this.empleado.getAtribucionesTemporales().get(i).getFechaInicio())){
				encontrado = true;
			}else if (this.fechaFin.after(this.empleado.getAtribucionesTemporales().get(i).getFechaFin()) && 
					this.fechaInicio.before(this.empleado.getAtribucionesTemporales().get(i).getFechaFin())){
				encontrado = true;
			}else if (this.fechaInicio.after(this.empleado.getAtribucionesTemporales().get(i).getFechaInicio()) &&
					this.fechaFin.before(this.empleado.getAtribucionesTemporales().get(i).getFechaFin())){
				encontrado = true;
			}else if (this.fechaInicio.equals(this.empleado.getAtribucionesTemporales().get(i).getFechaInicio()) &&
					this.fechaFin.equals(this.empleado.getAtribucionesTemporales().get(i).getFechaFin())){
				encontrado = true;
			}else if(this.fechaFin.equals(this.empleado.getAtribucionesTemporales().get(0).getFechaInicio()) ||
					this.fechaInicio.equals(this.empleado.getAtribucionesTemporales().get(0).getFechaFin())){
				encontrado = true;
			}
		}
		
		// Si la fecha de inicio introducida es superior a todas las de fin que ya existian entre el listado de atribuciones nos disponemos a 
		// insertarla en el listado en caso contrario informamos al usuario
		
		if (!encontrado){
			AtribucionBean atrTemp = new AtribucionBean();
			atrTemp.setFechaInicio(this.getFechaInicio());
			atrTemp.setFechaFin(this.getFechaFin());
			atrTemp.setEstado(EstadoListadosEnum.NUEVO);
			this.empleado.getAtribucionesTemporales().add(atrTemp);
			
		}else{
			RequestContext.getCurrentInstance().execute("PF('dlgFechaIncorrecta').show()");
			LOGGER.debug("Mostramos el dialogo de fechas introducidas no validas");			
		}
		
		
		LOGGER.debug("Salimos del metodo de alta de nueva atribucion temporal de empleados: SALIDA");
	}
	
	
    /**
     * Se encarga de eliminar una atribucion temporal de la lista
     * En caso de ser una atribucion NUEVA la elimina
     * En otro caso lo marca para ser eliminada al guardar
     * 
     * @param atribucion
     */
    public void eliminaAtribucion(AtribucionBean atribucion) {
        
        if(atribucion.getEstado() == EstadoListadosEnum.NUEVO){
            this.empleado.getAtribucionesTemporales().remove(atribucion);
        }else{
            atribucion.setEstado(EstadoListadosEnum.ELIMINADO);
        }
    }
    
    /**
     * Se encarga de editar una atribucion de la lista
     * 
     * @param atribucion
     */
    public void editaAtribucion(AtribucionBean atribucion) {
        atribucion.setEstado(EstadoListadosEnum.MODIFICADO);
    }
    
    /**
     * Método que se encarga de restaurar una atribucion eliminada o modificada
     * 
     * @param atribucion
     */
    public void restauraAtribucion(AtribucionBean atribucion) {
        backUpUtils.recuperaBean(atribucion);
    }
	
    /**
	 * @return Metodo utilizado para actualizar los campos del formulario
	 * al cambiar de tab
	 */
	public void actualizarAtribuciones(int index) {
		LOGGER.debug("Actualizamos el tab del formulario");
		this.activeIndex = index;
	}
    
	/**
	 * @return Metodo utilizado para guardar los cambios en las atribuciones de empleados
	 */
	public void guardar() {
		
		// Validamos si hay cambios en atribuciones fijas o temporales	
		if (this.getEmpleado().getAtribucionFija().equals(this.respaldoAtribucionFija)){
			// Si no ha habido cambios muestro dialogo de no cambios
			boolean modificacion = false;
			for (int i=0;i<this.empleado.getAtribucionesTemporales().size() && !modificacion;i++){
				if (!this.empleado.getAtribucionesTemporales().get(i).getEstado().equals(EstadoListadosEnum.CONSULTADO)){
					modificacion = true;
				}
			}
			if (!modificacion){
				RequestContext.getCurrentInstance().execute("PF('dlgNoCambios').show()");
				return;
			}
		} 
		if (this.modificacionEmpleado !=null && this.modificacionEmpleado){
			LOGGER.debug("Accedemos al metodo de modificacion de atribuciones de empleado");		
			
			// Comprobamos si la atribucion fija ya existia, en caso de ser nueva la damos de alta con las 
			// fechas indicadas de inicio y fin en el sistema
			if (this.empleado.getAtribucionFija().getFechaInicio() == null){
				Calendar atrCal = Calendar.getInstance();
				atrCal.set(9999, 11, 31, 0, 0);  
				this.empleado.getAtribucionFija().setFechaInicio(atrCal.getTime());
				this.empleado.getAtribucionFija().setFechaFin(atrCal.getTime());
				
				this.altaAtribucionesBackEnd.ejecutarTRN(this.empleado.getNumEmpleado(), this.empleado.getAtribucionFija());
			}else{
				this.modificacionAtribucionesBackEnd.ejecutarTRN(this.empleado.getNumEmpleado(), this.empleado.getAtribucionFija());
			}
			
			
			// Se crean/modifican las atribuciones temporales
			for (int i=0;i<this.empleado.getAtribucionesTemporales().size();i++){
				if (this.empleado.getAtribucionesTemporales().get(i).getEstado().equals(EstadoListadosEnum.NUEVO)){
	        		this.altaAtribucionesBackEnd.ejecutarTRN(this.empleado.getNumEmpleado(), this.empleado.getAtribucionesTemporales().get(i));
	        	}else if (this.empleado.getAtribucionesTemporales().get(i).getEstado().equals(EstadoListadosEnum.MODIFICADO)){
	        		this.modificacionAtribucionesBackEnd.ejecutarTRN(this.empleado.getNumEmpleado(), this.empleado.getAtribucionesTemporales().get(i));
	        	}else if (this.empleado.getAtribucionesTemporales().get(i).getEstado().equals(EstadoListadosEnum.ELIMINADO)){
	        		this.bajaAtribucionesBackEnd.ejecutarTRN(this.empleado.getNumEmpleado(), this.empleado.getAtribucionesTemporales().get(i));
	        	}
			}
			
			RequestContext.getCurrentInstance().execute("PF('dlgGuardadoCorrecto').show()");
			LOGGER.debug("Salimos de la modificacion de atribuciones de empleado con exito");
			
			
		}else{
			LOGGER.debug("Accedemos al metodo de atribuciones de empleado");
			
			// Se crea la atribucion fija
			this.altaAtribucionesBackEnd.ejecutarTRN(this.empleado.getNumEmpleado(), this.empleado.getAtribucionFija());
			
			// Se crean las atribuciones temporales
			for (int i=0;i<this.empleado.getAtribucionesTemporales().size();i++){
				if (this.empleado.getAtribucionesTemporales().get(i).equals(EstadoListadosEnum.NUEVO)){
					this.altaAtribucionesBackEnd.ejecutarTRN(this.empleado.getNumEmpleado(), this.empleado.getAtribucionesTemporales().get(i));
	        	}					
			}
			
			RequestContext.getCurrentInstance().execute("PF('dlgGuardadoCorrecto').show()");
			LOGGER.debug("Salimos del alta de atribuciones de empleado con exito");
		}
		
		this.setEmpleado(this.consultaAtribucionesBackEnd.ejecutarTRN(empleado));
		if (this.empleado.getAtribucionesTemporales() !=null && !this.empleado.getAtribucionesTemporales().isEmpty()
				&& this.empleado.getAtribucionesTemporales().get(0).getRespaldo() == null
				&& this.empleado.getAtribucionesTemporales().get(0).getEstado() == EstadoListadosEnum.CONSULTADO){
			this.backUpUtils.respaldaArray((ArrayList<AtribucionBean>) this.empleado.getAtribucionesTemporales());				
		}
		
		respaldoAtribucionFija = SerializationUtils.clone(this.empleado.getAtribucionFija());		
		this.setFechaInicio(null);
		this.setFechaFin(null);
	}
	
	/**
	 * @return Metodo utilizado para recuperar una instancia de Flash
	 */
	public Flash obtieneFlash() {
		return FacesContext.getCurrentInstance().getExternalContext().getFlash();
	}
	
	
	/**
	 * @return Metodo utilizado para volver al flujo anterior
	 */
	public String terminar() {
		
		obtieneFlash().put(ParametrosFlashEnum.ID_EMPLEADO.getParamFlash(), this.empleado.getNumEmpleado());
		return NavegacionEnum.FICHA_EMPLEADO.getRuta();			
	
	}
	
	/**
	 * @return Metodo utilizado para volver al flujo anterior
	 */
	public String volver() {
		String ruta = "";
		if (this.destino == null) {
			obtieneFlash().put(ParametrosFlashEnum.ID_EMPLEADO.getParamFlash(), this.empleado.getNumEmpleado());
			ruta = NavegacionEnum.FICHA_EMPLEADO.getRuta();			
		} else {
			ruta = destino.getRuta();
			managedBeanStateRecover.finNavegacion(destinoController);
		}
		return ruta;
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
	 * @return the empleado
	 */
	public EmpleadoBean getEmpleado() {
		return empleado;
	}

	/**
	 * @param empleado the empleado to set
	 */
	public void setEmpleado(EmpleadoBean empleado) {
		this.empleado = empleado;
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
	 * @return the activeIndex
	 */
	public int getActiveIndex() {
		return activeIndex;
	}

	/**
	 * @param activeIndex the activeIndex to set
	 */
	public void setActiveIndex(int activeIndex) {
		this.activeIndex = activeIndex;
	}

	/**
	 * @return the fechaInicio
	 */
	public Date getFechaInicio() {
		return fechaInicio;
	}

	/**
	 * @param fechaInicio the fechaInicio to set
	 */
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	/**
	 * @return the fechaFin
	 */
	public Date getFechaFin() {
		return fechaFin;
	}

	/**
	 * @param fechaFin the fechaFin to set
	 */
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	/**
	 * @return the respaldoAtribucionFija
	 */
	public AtribucionBean getRespaldoAtribucionFija() {
		return respaldoAtribucionFija;
	}

	/**
	 * @param respaldoAtribucionFija the respaldoAtribucionFija to set
	 */
	public void setRespaldoAtribucionFija(AtribucionBean respaldoAtribucionFija) {
		this.respaldoAtribucionFija = respaldoAtribucionFija;
	}
	
}
