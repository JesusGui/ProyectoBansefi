package mx.babel.bansefi.banksystem.administracion.controllers;
        
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

import mx.babel.arq.catalogo.beans.CatalogoGeoBean;
import mx.babel.arq.sesion.utils.ContextoUtils;
import mx.babel.bansefi.banksystem.administracion.backends.ConsultaEntidadBackend;
import mx.babel.bansefi.banksystem.administracion.backends.ModificaEntidadBackend;
import mx.babel.bansefi.banksystem.administracion.beans.EntidadBean;
import mx.babel.bansefi.banksystem.base.backends.consultadomicilio.ConsultaDomicilioBackend;
import mx.babel.bansefi.banksystem.base.beans.domain.DomicilioTipoBean;
import mx.babel.bansefi.banksystem.base.constants.ConstantesFuncionales;
import mx.babel.bansefi.banksystem.base.controllers.DomicilioController;
import mx.babel.bansefi.banksystem.base.enums.NavegacionEnum;
import mx.babel.bansefi.banksystem.base.enums.ParametrosFlashEnum;
import mx.babel.bansefi.banksystem.base.utils.ManagedBeanStateRecover;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@ManagedBean(name = "entidadController")
@ViewScoped
@Component
@Scope("view")
public class EntidadController implements Serializable {

    private static final long serialVersionUID = -4541534030529908001L;
	
    private boolean datosGeneralesReadOnly;
    
    private boolean datosDireccionReadOnly;
    
    @Autowired
    private ContextoUtils contextoUtils;
    
    @Autowired
    private ConsultaDomicilioBackend consultaDomicilio;
    
    @Autowired
    private ConsultaEntidadBackend consultaEntidad;
    
    @Autowired
    private ModificaEntidadBackend modificaEntidad;
   
    @ManagedProperty(value = "entidad")
    private EntidadBean entidad;
    
    @Autowired
    ManagedBeanStateRecover managedBeanStateRecover;

    @Autowired
	private DomicilioController domicilioController;
	
    /**
     * Enum para definir a donde se redireccionará al seleccionar un objeto de
     * la tabla de resultados.
     */
    private NavegacionEnum destino;
    private Object destinoController;
    
    /**
     * Constructor.
     */
    public EntidadController() {
        super();
    }
    
    
    /**
     * @return Metodo utilizado para acceder al paso 1 del alta de entidades por
     *         primera vez.
     */
    public String inicio() {
        return NavegacionEnum.CONSULTA_ENTIDAD.getRuta();
    }
    
    /**
     * Se inicializan las variables de la vista.
     */
    @PostConstruct
    public void init() {
        if (this.obtieneFlash().get(ParametrosFlashEnum.NAVEGACION_MANAGED_BEAN.getParamFlash()) != null) {
            if ((Boolean) this.obtieneFlash().get(ParametrosFlashEnum.NAVEGACION_MANAGED_BEAN.getParamFlash())) {
                destino = managedBeanStateRecover.getDestino();
                destinoController = managedBeanStateRecover.getController();
            }
        }
        // Si el flash tiene valor lo recuperamos
        if (this.obtieneFlash().get(ParametrosFlashEnum.ENTIDAD.getParamFlash()) != null) {
            this.entidad = (EntidadBean) this.obtieneFlash().get(
                    ParametrosFlashEnum.ENTIDAD.getParamFlash());
            domicilioController.setDomicilioBean(this.entidad.getDomicilio());
        }
        //En este caso venimos de una busqueda
        else if(this.obtieneFlash().get(ParametrosFlashEnum.CODIGO_ENTIDAD.getParamFlash()) != null ){
            String codigoEntidad = (String)this.obtieneFlash().get(ParametrosFlashEnum.CODIGO_ENTIDAD.getParamFlash());
            this.entidad = this.consultaEntidad.ejectuarTRN(codigoEntidad);
            DomicilioTipoBean domicilioBean = this.consultaDomicilio.ejectuarTRN(null,this.entidad.getCodDomicilio(),this.entidad.getCodigo());
            entidad.setDomicilio(domicilioBean);
            entidad.setFechaAlta(new Date());
            domicilioController.setDomicilioBean(this.entidad.getDomicilio());
        }//Se crea una nueva entidad
        else{
            this.entidad = new EntidadBean();
			domicilioController.setDomicilioBean(new DomicilioTipoBean());
            entidad.setDomicilio(domicilioController.getDomicilioBean());
            entidad.setFechaAlta(new Date());
            
        }
        //Atributos que indican que campos puede editar el gestor
        this.datosGeneralesReadOnly=true;
        this.datosDireccionReadOnly=true;
        if(ConstantesFuncionales.ENTIDAD_ADAN.equals(contextoUtils.getEntidad())){
            this.datosGeneralesReadOnly=false;
            this.datosDireccionReadOnly=false;
        }else if(ConstantesFuncionales.CENTRO_CONTROLADOR.equals(contextoUtils.getSucursal())){
            this.datosDireccionReadOnly=false;
        }
    }
   
    public void modificarEntidad(){
        
        int numDir = this.modificaEntidad.ejectuarTRN(this.entidad);
        
        if(numDir>0){
            this.entidad.setCodDomicilio(numDir);
        }
        
        FacesContext context = FacesContext.getCurrentInstance();
        
        if(numDir>0){
            context.addMessage(null, new FacesMessage("Modificación realizada correctamente",  "Se ha realizado la modificación correctamente."));
        }else{
             context.addMessage(null, new FacesMessage("Error",  "Se ha producido un error en la modificación."));
        }
        
    }
    
    /**
     * Se encarga de obtener el flash.
     * 
     * @return Flash con los datos de la pagina
     */
    public Flash obtieneFlash() {
        return FacesContext.getCurrentInstance().getExternalContext()
                .getFlash();
    }
    
   
    public EntidadBean getEntidad() {
        return entidad;
    }

    public void setEntidad(EntidadBean entidad) {
        this.entidad = entidad;
    }

  
	public void manejarComponenteDomicilio() {
		if( domicilioController.getDomicilioBean().getCodigoPostalCatGeo()==null && domicilioController.getDomicilioBean().getMunicipioCatGeo()==null ){
			domicilioController.limpiarCamposDomicilio();
		}else{
			domicilioController.cargaDatosDomicilio();
		}
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
     * Método que regresa ruta de ventana de Inicio.
     * 
     * @return String Ruta de Inicio
     */
    public String redirigirInicio() {
        String rutaDestino = null;
        if (destino == null) {
            rutaDestino = NavegacionEnum.INICIO.getRuta();
        } else {
            rutaDestino = destino.getRuta();
            managedBeanStateRecover.finNavegacion(destinoController);
        }
        return rutaDestino;
    }


    public Boolean getDatosGeneralesReadOnly() {
        return datosGeneralesReadOnly;
    }


    public void setDatosGeneralesReadOnly(boolean datosGeneralesReadOnly) {
        this.datosGeneralesReadOnly = datosGeneralesReadOnly;
    }


    public Boolean getDatosDireccionReadOnly() {
        return datosDireccionReadOnly;
    }


    public void setDatosDireccionReadOnly(boolean datosDireccionReadOnly) {
        this.datosDireccionReadOnly = datosDireccionReadOnly;
    }
    
	public DomicilioController getDomicilioController() {
		return domicilioController;
	}

	public void setDomicilioController(DomicilioController domicilioController) {
		this.domicilioController = domicilioController;
	}

}
