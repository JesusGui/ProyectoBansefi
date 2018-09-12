package mx.babel.bansefi.banksystem.personas.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

import mx.babel.arq.catalogo.beans.CatalogoBean;
import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.storage.annotations.StoreStep;
import mx.babel.arq.storage.beans.StorageMgrBean;
import mx.babel.bansefi.banksystem.base.backends.ModificacionPerfilTransaccionalPersonaBackEnd;
import mx.babel.bansefi.banksystem.base.beans.domain.ClientePersonaFisicaBean;
import mx.babel.bansefi.banksystem.base.beans.domain.ReferenciaPersonalBean;
import mx.babel.bansefi.banksystem.base.constants.ConstantesFuncionales;
import mx.babel.bansefi.banksystem.base.enums.CatalogoEnum;
import mx.babel.bansefi.banksystem.base.enums.EstadoListadosEnum;
import mx.babel.bansefi.banksystem.base.enums.NavegacionEnum;
import mx.babel.bansefi.banksystem.base.enums.ParametrosFlashEnum;
import mx.babel.bansefi.banksystem.base.utils.BeanBackUpUtils;
import mx.babel.bansefi.banksystem.base.utils.CatalogoUtils;
import mx.babel.bansefi.banksystem.base.utils.DomicilioUtils;
import mx.babel.bansefi.banksystem.personas.backend.AltaReferenciasPersonalesRiesgoBackEnd;
import mx.babel.bansefi.banksystem.personas.backend.BajaReferenciasPersonalesRiesgoBackEnd;
import mx.babel.bansefi.banksystem.personas.backend.ModificacionReferenciasPersonalesRiesgoBackEnd;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Controlador para el Alta, baja y modificacion de datos de riesgo de clientes.
 * @author javier.martinnino
 *
 */
@ManagedBean(name = "altaClienteRiesgoController")
@ViewScoped
@Component
@Scope("view")
public class AltaClienteRiesgoController extends StorageMgrBean implements Serializable {

	private static final long serialVersionUID = -312687270398509276L;
	
	private static final Logger LOGGER = LogManager.getLogger(AltaClienteRiesgoController.class.getName());
	
	@Autowired
	ModificacionPerfilTransaccionalPersonaBackEnd modificacionPerfilTransaccionalPersonaBackend;
	
	@Autowired
	AltaReferenciasPersonalesRiesgoBackEnd altaReferenciasPersonalesRiesgoBackEnd;
	
	@Autowired
	ModificacionReferenciasPersonalesRiesgoBackEnd modificacionReferenciasPersonalesRiesgoBackEnd;
	
	@Autowired
	BajaReferenciasPersonalesRiesgoBackEnd bajaReferenciasPersonalesRiesgoBackEnd;
	
	@Autowired
	DomicilioUtils domicilioUtils;
	
	@Autowired
	CatalogoUtils catalogoUtils;
	
	@Autowired
    BeanBackUpUtils backUpUtils;
	
	/**
	 * Cliente a dar de alta en el sistema .
	 */
	private ClientePersonaFisicaBean cliente;
	
	/**
	 * Variable para saber si estamos en la modificacion de cliente
	 */
	private Boolean modificacionCliente;
	
	/**
	 * Variable para saber si se recupero informacion para renderizar en la vista.
	 */
	private boolean elementosVisibles;
	
	@PostConstruct
	void init() {
		super.restoreflash();
		if (obtieneFlash().get(ParametrosFlashEnum.CLIENTE_PF_BEAN.getParamFlash()) != null) {
			this.cliente = (ClientePersonaFisicaBean) obtieneFlash().get(ParametrosFlashEnum.CLIENTE_PF_BEAN.getParamFlash());
			
			if (obtieneFlash().get(ParametrosFlashEnum.MODIFICACION_CLIENTE.getParamFlash()) != null) {
				this.modificacionCliente = (Boolean) obtieneFlash().get(ParametrosFlashEnum.MODIFICACION_CLIENTE.getParamFlash());				
			}
			if (this.cliente.getDatosRiesgo().getReferenciasPersonales() !=null && !this.cliente.getDatosRiesgo().getReferenciasPersonales().isEmpty()
					&& this.cliente.getDatosRiesgo().getReferenciasPersonales().get(0).getRespaldo() == null
					&& this.cliente.getDatosRiesgo().getReferenciasPersonales().get(0).getEstado() == EstadoListadosEnum.CONSULTADO){
				this.backUpUtils.respaldaArray((ArrayList<ReferenciaPersonalBean>) this.cliente.getDatosRiesgo().getReferenciasPersonales());
			}
			this.elementosVisibles = true;
		} else{
			LOGGER.debug("Objeto ClientePersonaFisicaBean no encontrado: Procedemos a mostrar mensaje de cliente de riesgo no disponible");	
			FacesContext.getCurrentInstance() .addMessage( "messages", new FacesMessage(FacesMessage.SEVERITY_WARN,
									"¡Atención!","No se pudo recuperar el cliente, no es posible dar de alta o modificar una persona de riesgo en "
											+ "este momento."));
		}				
	}
	
	/**
	 * @return Metodo para el complete de los autocomplete de plazas 
	 *         
	 */
	public List<String> consultarDescripcionesPlazas(String query){
		List<String> descripciones = new ArrayList<String>();
		try{
			List<CatalogoBean> resultados = this.catalogoUtils.getCatalogo(CatalogoEnum.TP_PLAZA_BANCARIA);
		    for (int i=0;i<resultados.size() && descripciones.size()<ConstantesFuncionales.MAX_RESULTS_AUTOCOMPLETE;i++){
		    	if (StringUtils.containsIgnoreCase(resultados.get(i).getDescripcionL(), query.toUpperCase())){
		    		descripciones.add(resultados.get(i).getDescripcionL());
		    	}	    		
		    }
		}catch(NoControlableException nce){
			LOGGER.debug("Error al consultar el catalogo de plazas bancarias ",nce);	
			return descripciones;
		}catch(ControlableException ce){
			LOGGER.debug("Error al consultar el catalogo de plazas bancarias ",ce);
			return descripciones;
		}
	    
	    return descripciones;	    
	}
	
	/**
	 * @return Metodo utilizado para limpiar los datos de domicilio
	 *         
	 */
	public void limpiarDatosDomicilio(){
		if (this.cliente.getDatosRiesgo().getDenominacionEmpresa() ==null || ("").equals(this.cliente.getDatosRiesgo().getDenominacionEmpresa())){
			this.cliente.getDatosRiesgo().setCalle(null);
			this.cliente.getDatosRiesgo().setNumExterior(null);
			this.cliente.getDatosRiesgo().setNumInterior(null);
			this.cliente.getDatosRiesgo().setMunicipio(null);
			this.cliente.getDatosRiesgo().setColonia(null);
			this.cliente.getDatosRiesgo().setEstado(null);
			this.cliente.getDatosRiesgo().setCodigoPostal(null);
		}
	}
	
	/**
	 * Método para obtener el tipo de documento de la id externa del cliente
	 */
	public String obtenerDescripcionTpDoc(){
		try{
			return this.catalogoUtils.getCatalogoBean(CatalogoEnum.TP_ID_EXT_PERS, this.cliente.getTipoIdentificacion()).getDescripcionC();			
		}catch(ControlableException | NoControlableException nce){
			LOGGER.debug("No se pudo recuperar la descripcion para el valor del tipo de indentificacion externa: "+this.cliente.getTipoIdentificacion(),nce);
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
			LOGGER.debug("No se pudo recuperar la descripcion para el valor del tipo de indentificacion externa: "+this.cliente.getTipoIdentificacion(),nce);
			return "";
		}
	}
	
	/**
     * Metodo que se encarga de crear una nueva referencia personal a la vista
     */
    public void nuevaReferencia() {
        ReferenciaPersonalBean nuevaReferencia = new ReferenciaPersonalBean();
        nuevaReferencia.setEstado(EstadoListadosEnum.NUEVO);
        this.cliente.getDatosRiesgo().getReferenciasPersonales().add(nuevaReferencia);

    }
	
    /**
     * Se encarga de eliminar una referencia de la lista
     * En caso de ser una referencia NUEVA la elimina
     * En otro caso lo marca para ser eliminada al guardar
     * 
     * @param referencia
     */
    public void eliminaReferencia(ReferenciaPersonalBean referencia) {
        
        if(referencia.getEstado() == EstadoListadosEnum.NUEVO){
            this.cliente.getDatosRiesgo().getReferenciasPersonales().remove(referencia);
        }else{
            referencia.setEstado(EstadoListadosEnum.ELIMINADO);
        }
    }
    
    /**
     * Se encarga de editar una referencia de la lista
     * 
     * @param documento
     */
    public void editaReferencia(ReferenciaPersonalBean referencia) {
        referencia.setEstado(EstadoListadosEnum.MODIFICADO);
    }
    
    /**
     * Método que se encarga de restaurar una referencia eliminada o modificada
     * 
     * @param referencia
     */
    public void restauraReferencia(ReferenciaPersonalBean referencia) {
        backUpUtils.recuperaBean(referencia);
    }
	
    /**
     * Método utilizado para guardar las modificaciones en las referencias personales de clientes de riesgo
     * 
     * @param referencia
     */
    public void guardarReferenciasPersonales(List<ReferenciaPersonalBean> referenciasPersonales) {
        // Se incluya la logica de actualizacion de referencias personales
    	for (int i=0;i<referenciasPersonales.size();i++){
        	if (referenciasPersonales.get(i).getEstado().equals(EstadoListadosEnum.NUEVO)){
        		this.altaReferenciasPersonalesRiesgoBackEnd.ejecutarTRN(this.cliente.getIdInterna(), referenciasPersonales.get(i));
        	}else if (referenciasPersonales.get(i).getEstado().equals(EstadoListadosEnum.MODIFICADO)){
        		this.modificacionReferenciasPersonalesRiesgoBackEnd.ejecutarTRN(this.cliente.getIdInterna(), referenciasPersonales.get(i));
        	}else if (referenciasPersonales.get(i).getEstado().equals(EstadoListadosEnum.ELIMINADO)){
        		this.bajaReferenciasPersonalesRiesgoBackEnd.ejecutarTRN(this.cliente.getIdInterna(), referenciasPersonales.get(i));
        	}
        }
    }
    
    /**
	 * @return Metodo utilizado para crear/Modificar datos de riesgo del cliente
	 * 	       
	 */
	public String crearModificarDatosRiesgo() {
		
		String retorno = "";
		
		LOGGER.debug("Accedemos al metodo de alta/modificacion de datos de riesgo");		
		try{
			// Se modifican los datos de riesgo de clientes
			this.modificacionPerfilTransaccionalPersonaBackend.ejecutarTRN(this.cliente);
			
			// Se modifican las referencias personales
			this.guardarReferenciasPersonales(this.cliente.getDatosRiesgo().getReferenciasPersonales());
			
			if (this.modificacionCliente !=null && this.modificacionCliente) {
				RequestContext.getCurrentInstance().execute("PF('dlgModCorrecto').show()");
			}else{
				RequestContext.getCurrentInstance().execute("PF('dlgAltaCorrecto').show()");
			}
			
			LOGGER.debug("Salimos de la alta/modificacion del cliente con exito");
			
		}catch (ControlableException ce){
			LOGGER.debug("Error al intentar realizar la alta/modificacion de datos de riesgo",ce);
			FacesContext.getCurrentInstance().addMessage("messages",new FacesMessage(FacesMessage.SEVERITY_WARN,
					"¡Atención!","Hubo un error al intentar guardar los datos en el sistema."));
		}
				
		return retorno;
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
	 * @return Metodo utilizado para la redireccion tras guardar los datos
	 *         
	 */
	public String altaCorrecta() {		
		obtieneFlash().put(ParametrosFlashEnum.ID_INTERNA.getParamFlash(),this.cliente.getIdInterna());
		obtieneFlash().put(ParametrosFlashEnum.TIPO_CLIENTE.getParamFlash(),this.cliente.getTipo());
		return NavegacionEnum.DOCUMENTOS.getRuta();
	}
	
	/**
	 * @return Metodo utilizado para recuperar una instancia de Flash
	 */
	public Flash obtieneFlash() {
		return FacesContext.getCurrentInstance().getExternalContext().getFlash();
	}
	
	/**
	 * @return Metodo utilizado para cancelar el alta/modificacion de riesgo de Clientes
	 *         
	 */
	public String cancelarAltaModificacion() {
		obtieneFlash().put(ParametrosFlashEnum.ID_INTERNA.getParamFlash(),this.cliente.getIdInterna());
		obtieneFlash().put(ParametrosFlashEnum.TIPO_CLIENTE.getParamFlash(),this.cliente.getTipo());
		return NavegacionEnum.FICHA_CLIENTE.getRuta();
	}
	
	/**
	 * @return Metodo utilizado para redireccionar al paso 1 del alta/modificacion de informacion de riesgo de Clientes
	 */
	@StoreStep
	public String irAPaso1() {
		obtieneFlash().put(ParametrosFlashEnum.CLIENTE_PF_BEAN.getParamFlash(), this.cliente);
		obtieneFlash().put(ParametrosFlashEnum.MODIFICACION_CLIENTE.getParamFlash(), this.modificacionCliente);
		return NavegacionEnum.ALTA_CLIENTE_RIESGO1.getRuta();
	}

	/**
	 * @return Metodo utilizado para redireccionar al paso 2 del alta/modificacion de informacion de riesgo de Clientes
	 */
	@StoreStep
	public String irAPaso2() {
		obtieneFlash().put(ParametrosFlashEnum.CLIENTE_PF_BEAN.getParamFlash(), this.cliente);
		obtieneFlash().put(ParametrosFlashEnum.MODIFICACION_CLIENTE.getParamFlash(), this.modificacionCliente);
		return NavegacionEnum.ALTA_CLIENTE_RIESGO2.getRuta();
	}
	
	/**
	 * @return beanList
	 */
	@Override
	protected Map<String, Object> getBeanList() {
		Map<String, Object> beanList = new HashMap<String, Object>();
		beanList.put(ClientePersonaFisicaBean.class.getName(), this.cliente);
		beanList.put(Boolean.class.getName(), this.modificacionCliente);
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
	
	public CatalogoUtils getCatalogoUtils() {
		return catalogoUtils;
	}

	public void setCatalogoUtils(CatalogoUtils catalogoUtils) {
		this.catalogoUtils = catalogoUtils;
	}

	public ClientePersonaFisicaBean getCliente() {
		return cliente;
	}

	public void setCliente(ClientePersonaFisicaBean cliente) {
		this.cliente = cliente;
	}

	public Boolean getModificacionCliente() {
		return modificacionCliente;
	}

	public void setModificacionCliente(Boolean modificacionCliente) {
		this.modificacionCliente = modificacionCliente;
	}

	public boolean isElementosVisibles() {
		return elementosVisibles;
	}

	public void setElementosVisibles(boolean elementosVisibles) {
		this.elementosVisibles = elementosVisibles;
	}

}
