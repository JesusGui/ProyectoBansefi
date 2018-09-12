package mx.babel.bansefi.banksystem.base.controllers;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

import mx.babel.arq.catalogo.beans.CatalogoBean;
import mx.babel.bansefi.banksystem.base.backends.AltaBienIngresoGastoBackEnd;
import mx.babel.bansefi.banksystem.base.backends.AltaBienInmuebleBackEnd;
import mx.babel.bansefi.banksystem.base.backends.AltaBienMaterialesBackEnd;
import mx.babel.bansefi.banksystem.base.backends.AltaDatosAdicionalesBienBackend;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.backends.BajaBienIngresoGastoBackEnd;
import mx.babel.bansefi.banksystem.base.backends.BajaBienInmuebleBackEnd;
import mx.babel.bansefi.banksystem.base.backends.ConsultaBienesBackEnd;
import mx.babel.bansefi.banksystem.base.backends.ConsultaDatosAdicionalesBienBackend;
import mx.babel.bansefi.banksystem.base.backends.ConsultaDetalleBienIngresoGastoBackEnd;
import mx.babel.bansefi.banksystem.base.backends.ConsultaDetalleBienInmuebleBackEnd;
import mx.babel.bansefi.banksystem.base.backends.ConsultaListaCrctBackEnd;
import mx.babel.bansefi.banksystem.base.backends.ModificaBienIngresoGastoBackEnd;
import mx.babel.bansefi.banksystem.base.backends.ModificaBienInmuebleBackEnd;
import mx.babel.bansefi.banksystem.base.backends.ModificaBienMaterialesBackEnd;
import mx.babel.bansefi.banksystem.base.backends.ModificaDatosAdicionalesBienBackEnd;
import mx.babel.bansefi.banksystem.base.beans.domain.BienBean;
import mx.babel.bansefi.banksystem.base.beans.domain.BienBeanComparator;
import mx.babel.bansefi.banksystem.base.beans.domain.ClienteBean;
import mx.babel.bansefi.banksystem.base.beans.domain.DatosDeudaBienBean;
import mx.babel.bansefi.banksystem.base.beans.domain.DatosGeneralesBienBean;
import mx.babel.bansefi.banksystem.base.beans.domain.DatosSeguroBienBean;
import mx.babel.bansefi.banksystem.base.beans.domain.DatosTerrenoBienBean;
import mx.babel.bansefi.banksystem.base.beans.domain.DatosValuacionBienBean;
import mx.babel.bansefi.banksystem.base.beans.domain.DireccionRegistralBienBean;
import mx.babel.bansefi.banksystem.base.enums.CatalogoEnum;
import mx.babel.bansefi.banksystem.base.enums.DialogoListadoEnum;
import mx.babel.bansefi.banksystem.base.enums.EstadoListadosEnum;
import mx.babel.bansefi.banksystem.base.enums.NavegacionEnum;
import mx.babel.bansefi.banksystem.base.enums.ParametrosFlashEnum;
import mx.babel.bansefi.banksystem.base.utils.BeanBackUpUtils;
import mx.babel.bansefi.banksystem.base.utils.CatalogoUtils;
import mx.babel.bansefi.banksystem.base.utils.DialogoListadoUtils;
import mx.babel.bansefi.banksystem.base.utils.ManagedBeanStateRecover;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@ManagedBean(name = "bienesController")
@ViewScoped
@Component
@Scope("view")
public class BienesController implements Serializable {

    private static final long serialVersionUID = -4246884307356741848L;

    //Estados para el tipo de modificacion que se ha realizado, se utiliza para mostrar mensajes
    private static final Integer ALTA_BIENES = 1;
    private static final Integer BAJA_BIENES = 2;
    private static final Integer MODIFICACION_BIENES = 3;
    
    private static final int BIEN_INMUEBLE = 1;
    private static final String BIEN_INMUEBLE_STR = "1";
    private static final int BIEN_INGRESO_GASTO = 2;
    
    private static final int MANEJO_MAX_BIENES = 5;

    private static final Logger LOGGER = LogManager.getLogger(BienesController.class.getName());
    
    private Integer idInternaCliente;

    private String clase;

    private String tipo;
    
    private Integer posicionScroll;
    
    /**
     * Variable que se encarga de comprobar si se han producidos errores previos
     */
    private Boolean errorBienes;
    
    private List<CatalogoBean> tipoBienes;

    private List<CatalogoBean> tipoBienesParaMostrar;

    private List<BienBean> bienesLista;

    private boolean muestraDatosGenerales;

    private boolean muestraDatosTerreno;

    private boolean muestraDireccionRegistral;

    private boolean muestraDatosValuacion;

    private boolean muestraDatosSeguro;

    private boolean muestraDatosDeuda;

    private ClienteBean cliente;
    
    private Integer bienesEliminados;
    
    /**
     * Variable utilizada para guardar la redireccion al flujo de entrada a la
     * consulta de detalles de anotaciones.
     */
    private NavegacionEnum destino;

    /**
     * Variable utilizada para guardar el contenido del controller origen a la
     * consulta de detalles de anotaciones.
     */
    private Object destinoController;
   
    public static final String COSAS = "bienes";
    
    public static final String COSA = "bien";
    
    private DialogoListadoUtils dialogoUtils;

    private DialogoListadoEnum dialogoGuardado;
    
    private DialogoListadoEnum mensajeEliminados;
    
    @Autowired
    CatalogoUtils catalogoUtils;

    @Autowired
    AltaBienInmuebleBackEnd altaBienInmueble;

    @Autowired
    AltaBienIngresoGastoBackEnd altaBienIngreso;

    @Autowired
    AltaBienMaterialesBackEnd altaBienMaterial;
    
    @Autowired
    ConsultaBienesBackEnd consultaBienes;

    @Autowired
    ConsultaDetalleBienInmuebleBackEnd consultaDetalleBienInmueble;

    @Autowired
    ConsultaDetalleBienIngresoGastoBackEnd consultaDetalleBienIngresoGasto;

    @Autowired
    BajaBienInmuebleBackEnd bajaBienInmueble;

    @Autowired
    BajaBienIngresoGastoBackEnd bajaBienIngresoGasto;

    @Autowired
    ModificaBienInmuebleBackEnd modificaBienInmueble;

    @Autowired
    ModificaBienIngresoGastoBackEnd modificaBienIngresoGasto;

    @Autowired
    ManagedBeanStateRecover managedBeanStateRecover;
   
    @Autowired
    BeanBackUpUtils backUpUtils;
    
    @Autowired
    ConsultaListaCrctBackEnd consultaCrct;
    
    @Autowired
    ConsultaDatosAdicionalesBienBackend consultaDatosAdicionales;
    
    @Autowired
    ModificaBienMaterialesBackEnd modificaBienMateriales;
    
    @Autowired
    AltaDatosAdicionalesBienBackend altaDatosAdicionales;
    
    @Autowired
    ModificaDatosAdicionalesBienBackEnd modificaDatosAdicionales;
    
    public BienesController() {
        super();
    }

    @PostConstruct
    public void init() {
    	refreshEnums();
        //Obtenemos los parametros para volver a la ficha de cliente
        if (getParam(ParametrosFlashEnum.NAVEGACION_MANAGED_BEAN) != null) {
            if ((Boolean) getParam(ParametrosFlashEnum.NAVEGACION_MANAGED_BEAN)) {
                destino = managedBeanStateRecover.getDestino();
                destinoController = managedBeanStateRecover.getController();
                managedBeanStateRecover.enviaControllerMap(destinoController,
                        destino);

            }
        }
        
        //En este caso venimos de la ficha de cliente o en su defecto de la propia vista de bienes
        if (getParam(ParametrosFlashEnum.CLIENTE) != null) {
            this.cliente = (ClienteBean) getParam(ParametrosFlashEnum.CLIENTE);
           

            //Obtenemos el id interna del cliente
            this.idInternaCliente = cliente.getIdInterna();
            
            LOGGER.debug("this.idInternaPersona from CLIENTE-> " + this.idInternaCliente);
            
            //Consultamos la lista de bienes de Host
            this.bienesLista = this.consultaBienes.ejectuarTRN(this.idInternaCliente);
            //ordenamos la lista
            this.ordenaBienesLista();
            //Hacemos un respaldo de la lista de bienes
            backUpUtils.respaldaArray((ArrayList<BienBean>) this.bienesLista);
        }
        
     // Recuperamos el flash por si venimos de la propia ventana y se han
        // producido errores
        if (getParam(ParametrosFlashEnum.ERROR_BIENES) != null) {
            this.errorBienes = (Boolean) getParam(ParametrosFlashEnum.ERROR_BIENES);
            LOGGER.debug("this.errorBIENES-> " + this.errorBienes);
            if (this.errorBienes) {
                RequestContext.getCurrentInstance().execute(
                        "PF('dlgError').show()");
                
            }//Si no hay errores comprobamos si se han producido modificaciones previas 
            else if (getParam(ParametrosFlashEnum.MODIFICACION_BIENES) != null) {
                Integer modificacionBIENES = (Integer) getParam(ParametrosFlashEnum.MODIFICACION_BIENES);
                LOGGER.debug("modificacionBIENES-> " + modificacionBIENES);
                if(modificacionBIENES==BienesController.ALTA_BIENES){
                    RequestContext.getCurrentInstance().execute(
                            "PF('dlgAlta').show()");
                }else if(modificacionBIENES==BienesController.BAJA_BIENES){
                    RequestContext.getCurrentInstance().execute(
                            "PF('dlgEliminar').show()");
                }else if(modificacionBIENES==BienesController.MODIFICACION_BIENES){
                    RequestContext.getCurrentInstance().execute(
                            "PF('dlgModificacion').show()");
                }
            }
        }

        this.tipoBienes = this.catalogoUtils.getCatalogoOrdenado(CatalogoEnum.TP_BIEN);
        this.tipoBienesParaMostrar = new ArrayList<CatalogoBean>();
        this.clase = BienesController.BIEN_INMUEBLE_STR;
        this.tipo = this.tipoBienes.get(0).getClaveFila();
        
        this.dialogoUtils = new DialogoListadoUtils(0);
        
        this.determinaTiposBien();

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
     * @return Metodo utilizado para acceder al controller de bienes por primera
     *         vez.
     */
    public String inicio() {
        return NavegacionEnum.BIENES.getRuta();
    }

    /**
     * Metodo que se encarga de crear un nuevo bien y añadirlo a la lista
     */
    public void nuevoBien() {
        
    	if(validaCantidadBienes()){
	        BienBean bienNuevo = new BienBean();
	        bienNuevo.setIdInternoPersona(this.idInternaCliente);
	     
	        bienNuevo.setTipo(Integer.parseInt(this.tipo));
	        bienNuevo.setTipoCodigo(this.tipo);
	        // Obtenemos la descripcion del bien
	        if (StringUtils.isNotBlank(bienNuevo.getTipoCodigo())) {
	            CatalogoBean catalogo = catalogoUtils.getCatalogoBean(
	                    CatalogoEnum.TP_BIEN, bienNuevo.getTipoCodigo());
	
	            bienNuevo.setTipoDesc(catalogo.getDescripcionL());
	        }
	        bienNuevo.setEstado(EstadoListadosEnum.NUEVO);
	        bienNuevo.setClase(Integer.parseInt(this.clase));
	        DatosGeneralesBienBean datosGenerales = new DatosGeneralesBienBean();
	        DatosDeudaBienBean datosDeuda = new DatosDeudaBienBean();
	        DatosSeguroBienBean datosSeguro = new DatosSeguroBienBean();
	        DatosValuacionBienBean datosValuacion = new DatosValuacionBienBean();
	        DatosTerrenoBienBean datosTerreno = new DatosTerrenoBienBean();
	        DireccionRegistralBienBean direccionRegistral = new DireccionRegistralBienBean();
	        bienNuevo.setDatosDeuda(datosDeuda);
	        bienNuevo.setDatosGenerales(datosGenerales);
	        bienNuevo.setDatosSeguro(datosSeguro);
	        bienNuevo.setDatosTerreno(datosTerreno);
	        bienNuevo.setDatosValuacion(datosValuacion);
	        bienNuevo.setDireccionRegistral(direccionRegistral);
	        bienNuevo.tipoBien();
	        
	        this.bienesLista.add(bienNuevo);
	        // Efectuamos el desplazamiento vertical segÃºn el elemento insertado.
	        
//	        this.ordenaBienesLista();
	        int posicionScroll = this.bienesLista.indexOf(bienNuevo);
	        this.dialogoUtils.adicionaNuevo();
	        RequestContext.getCurrentInstance().execute(
	                "scrollTo('#bienesForm\\\\:bienesLista"+posicionScroll+"');");
    	}else{
    		RequestContext.getCurrentInstance().execute(
                    "PF('dlgNoMas').show()");
    	}
    }
    
    /**
     * Funcion que valida la maxima cantidad de bienes que se pueden agregar o editar 
     * @return
     */
    public boolean validaCantidadBienes(){
    	int nBienes= 0;
    	for(BienBean bien: this.bienesLista){
    		if(bien.getEstado().equals(EstadoListadosEnum.NUEVO)
    				|| bien.getEstado().equals(EstadoListadosEnum.MODIFICADO)){
    			nBienes++;
    		}
    	}
    	
    	if(nBienes < MANEJO_MAX_BIENES){
    		return true;
    	}else{
    		return false;
    	}
    }

    /**
     * Funcion que devuelve el array de bienes ordenados
     */
    public void ordenaBienesLista(){
        
        Collections.sort(this.bienesLista, new BienBeanComparator());
        
    }
    
    /**
     * Se encarga de mostrar los dialogs en caso de que se hayam producido cambios
     * 
     */
    public String volver() {
        Boolean cambios = false;
        

        for (BienBean bien : this.bienesLista) {
            if (bien.getEstado() == EstadoListadosEnum.ELIMINADO || bien.getEstado() == EstadoListadosEnum.MODIFICADO || bien.getEstado() == EstadoListadosEnum.NUEVO) {
                cambios = true;
            }
        }

        if (cambios) {
            RequestContext.getCurrentInstance().execute(
                    "PF('dlgVolver').show()");
            return null;
        } else {
            return this.confirmarVolver();
        }
    }
    
    public String confirmarVolver(){
        if(destinoController!=null){
            managedBeanStateRecover.finNavegacion(destinoController);
        }
            
        setParam(ParametrosFlashEnum.CLIENTE, this.cliente);
        return NavegacionEnum.FICHA_CLIENTE.getRuta();
    }
    
    /**
     * Se encarga de consultar el detalle de bien
     * 
     * @param bien
     */
    public void consultaDetalle(final BienBean bien) {

        if (bien.getClase() == BienesController.BIEN_INMUEBLE) {
            this.consultaDetalleBienInmueble.ejectuarTRN(bien);
            this.consultaCrct.ejecutarTRN(bien);
            this.consultaDatosAdicionales.ejecutarTRN(bien);
        } else {
            this.consultaDetalleBienIngresoGasto.ejectuarTRN(bien);
        }
        
    }

    /**
     * Se encarga de editar un bien de la lista
     * 
     * @param bien
     */
    public void editaBien(BienBean bien) {
    	if(validaCantidadBienes()){
	        this.consultaDetalle(bien);
	        bien.setEstado(EstadoListadosEnum.MODIFICADO);
	        this.dialogoUtils.adicionaEditado();   
    	}else{
    		RequestContext.getCurrentInstance().execute(
                    "PF('dlgNoMas').show()");
    	}
    }

    
    /**
     * Se encarga de procesar todos los cambios sobre los bienes
     */
    public String guardarBienes() {
        this.bienesEliminados = 0;
        Boolean eliminados = false;
        Boolean cambios = false;

        for (BienBean bien : this.bienesLista) {
            if(bien.getEstado() == EstadoListadosEnum.NUEVO) {
                cambios = true;
            }
            
            if (bien.getEstado() == EstadoListadosEnum.ELIMINADO) {
                this.bienesEliminados++;
                cambios = true;
                eliminados = true;

            } else if (bien.getEstado() == EstadoListadosEnum.MODIFICADO) {
                cambios = true;
            }
        }

        if (eliminados) {
            RequestContext.getCurrentInstance().execute(
                    "PF('dlgConfirmacionModificacion').show()");
            return null;
        } else if (cambios) {

            return this.confirmarGuardar();

        } else {
            RequestContext.getCurrentInstance().execute(
                    "PF('dlgNoCambios').show()");
            return null;
        }

    }
    
    /**
     * Se encarga de realizar las operaciones sobre los bienes
     */
    public String confirmarGuardar() {
        
        Integer tipoModificacionBienes = null;
        Boolean errorBienes = false;
        
        List<BienBean> eliminados = new ArrayList<BienBean>();
        
        for (BienBean bien : this.bienesLista) {
            if(bien.getEstado()==EstadoListadosEnum.NUEVO){
                if(bien.getClase() == BienesController.BIEN_INMUEBLE){
                    int returnCode1 = this.altaBienInmueble.ejectuarTRN(bien);
                    int returnCode2 = this.altaBienMaterial.ejectuarTRN(bien);
                    int returnCode3 = this.altaDatosAdicionales.ejectuarTRN(bien);
                    if (returnCode1 != BackEndBean.RETURN_COD_OK || returnCode2 != BackEndBean.RETURN_COD_OK || returnCode3 != BackEndBean.RETURN_COD_OK) {
                        errorBienes = true;
                    }else{
                    	bien.setEstado(EstadoListadosEnum.CONSULTADO);
                    }
                }else if(bien.getClase() == BienesController.BIEN_INGRESO_GASTO){
                    int returnCode1 = this.altaBienIngreso.ejectuarTRN(bien);
                    if (returnCode1 != BackEndBean.RETURN_COD_OK) {
                        errorBienes = true;
                    }else{
                    	bien.setEstado(EstadoListadosEnum.CONSULTADO);
                    }
                }
                tipoModificacionBienes = BienesController.ALTA_BIENES;
            }else if(bien.getEstado()==EstadoListadosEnum.ELIMINADO){
                if(bien.getClase() == BienesController.BIEN_INMUEBLE){
                    int returnCode = this.bajaBienInmueble.ejectuarTRN(bien);
                    if (returnCode != BackEndBean.RETURN_COD_OK) {
                        errorBienes = true;
                    }else{
                    	eliminados.add(bien);
                    }
                }else if(bien.getClase() == BienesController.BIEN_INGRESO_GASTO){
                    int returnCode = this.bajaBienIngresoGasto.ejectuarTRN(bien);
                    if (returnCode != BackEndBean.RETURN_COD_OK) {
                        errorBienes = true;
                    }else{
                    	eliminados.add(bien);
                    }
                }
                if(tipoModificacionBienes!=null){
                    tipoModificacionBienes=BienesController.MODIFICACION_BIENES;
                }else{
                    tipoModificacionBienes=BienesController.BAJA_BIENES;
                }
                
            }else if(bien.getEstado()==EstadoListadosEnum.MODIFICADO){
                if(bien.getClase() == BienesController.BIEN_INMUEBLE){
                    int returnCode1 = this.modificaBienInmueble.ejectuarTRN(bien);
                    int returnCode2 = this.modificaBienMateriales.ejectuarTRN(bien);
                    int returnCode3 = this.modificaDatosAdicionales.ejectuarTRN(bien);
                    if (returnCode1 != BackEndBean.RETURN_COD_OK || returnCode2 != BackEndBean.RETURN_COD_OK || returnCode3 != BackEndBean.RETURN_COD_OK) {
                        errorBienes = true;
                    }else{
                    	bien.setEstado(EstadoListadosEnum.CONSULTADO);
                    }
                }else if(bien.getClase() == BienesController.BIEN_INGRESO_GASTO){
                    int returnCode = this.modificaBienIngresoGasto.ejectuarTRN(bien);
                    if (returnCode != BackEndBean.RETURN_COD_OK) {
                        errorBienes = true;
                    }else{
                    	bien.setEstado(EstadoListadosEnum.CONSULTADO);
                    }
                }
                tipoModificacionBienes=BienesController.MODIFICACION_BIENES;
            }
        }
        //Metemos en la flash al cliente
        setParam(ParametrosFlashEnum.CLIENTE, this.cliente);
        setParam(ParametrosFlashEnum.CLIENTE, this.cliente);
        setParam(ParametrosFlashEnum.ERROR_BIENES, errorBienes);
        setParam(ParametrosFlashEnum.MODIFICACION_BIENES, tipoModificacionBienes);
        return NavegacionEnum.BIENES.getRuta();
        
    }

    public void determinaTiposBien() {
        this.tipoBienesParaMostrar = new ArrayList<CatalogoBean>();
        if (BienesController.BIEN_INMUEBLE_STR.equals(this.clase)) {
            for (CatalogoBean catalogoBean : this.tipoBienes) {
                if (Integer.parseInt(catalogoBean.getClaveFila()) >= 401
                        && Integer.parseInt(catalogoBean.getClaveFila()) <= 816) {
                    this.tipoBienesParaMostrar.add(catalogoBean);
                }
            }
        } else {
            for (CatalogoBean catalogoBean : this.tipoBienes) {
                if (Integer.parseInt(catalogoBean.getClaveFila()) >= 110
                        && Integer.parseInt(catalogoBean.getClaveFila()) <= 399) {
                    this.tipoBienesParaMostrar.add(catalogoBean);
                }
            }
        }
        
        if(!this.tipoBienesParaMostrar.isEmpty() && this.tipoBienesParaMostrar.get(0) != null)
        	this.tipo = this.tipoBienesParaMostrar.get(0).getClaveFila();

    }

    /**
     * @return Metodo utilizado para recuperar una instancia de Flash
     */
    public Flash obtieneFlash() {
    	try{
    		return FacesContext.getCurrentInstance().getExternalContext()
                .getFlash();
    	}catch(Exception e){
    		LOGGER.debug("Ocurrió un problema al recuperar la flash", e);
    	}
    	
    	return null;
    }
    
    /**
     * Funcion que valida si no hay error al recuperar la flash, y obtiene el parametro indicado
     * @param param
     * @return
     */
    public Object getParam(ParametrosFlashEnum param){
    	if(obtieneFlash() != null){
    		return obtieneFlash().get(param.getParamFlash());
    	}else{
    		return null;
    	}    	
    }
    
    /**
     * Funcion que asigna una variable en la flash validando
     * si esta disponible la flash
     * @param param
     * @param value
     * @return
     */
    public Object setParam(ParametrosFlashEnum param, Object value){
    	if(obtieneFlash() != null){
    		return obtieneFlash().put(param.getParamFlash(), value);
    	}else{
    		return null;
    	}    	
    }

    /**
     * Se encarga de eliminar un bien de la lista
     * En caso de ser un bien NUEVO lo elimina
     * En otro caso lo marca para ser eliminado al guardar
     * @param bien
     */
    public void eliminaBien(BienBean bien) {
        
        if(bien.getEstado() == EstadoListadosEnum.NUEVO){
            this.bienesLista.remove(bien);
            this.dialogoUtils.restaNuevo();
        }else{
            bien.setEstado(EstadoListadosEnum.ELIMINADO);
            this.dialogoUtils.adicionaEliminado();
        }
    }
 
    /**
     * Método que se encarga de restaurar un bien eliminado o modificado
     * 
     * @param idContenedor
     */
    public void restauraBien(BienBean bien) {
        backUpUtils.recuperaBean(bien);
    }

    public boolean isMuestraDatosGenerales() {
        return muestraDatosGenerales;
    }

    public void setMuestraDatosGenerales(boolean muestraDatosGenerales) {
        this.muestraDatosGenerales = muestraDatosGenerales;
    }

    public boolean isMuestraDatosTerreno() {
        return muestraDatosTerreno;
    }

    public void setMuestraDatosTerreno(boolean muestraDatosTerreno) {
        this.muestraDatosTerreno = muestraDatosTerreno;
    }

    public boolean isMuestraDireccionRegistral() {
        return muestraDireccionRegistral;
    }

    public void setMuestraDireccionRegistral(boolean muestraDireccionRegistral) {
        this.muestraDireccionRegistral = muestraDireccionRegistral;
    }

    public boolean isMuestraDatosValuacion() {
        return muestraDatosValuacion;
    }

    public void setMuestraDatosValuacion(boolean muestraDatosValuacion) {
        this.muestraDatosValuacion = muestraDatosValuacion;
    }

    public boolean isMuestraDatosSeguro() {
        return muestraDatosSeguro;
    }

    public void setMuestraDatosSeguro(boolean muestraDatosSeguro) {
        this.muestraDatosSeguro = muestraDatosSeguro;
    }

    public boolean isMuestraDatosDeuda() {
        return muestraDatosDeuda;
    }

    public void setMuestraDatosDeuda(boolean muestraDatosDeuda) {
        this.muestraDatosDeuda = muestraDatosDeuda;
    }

//    public List<BienBean> getNuevosBienes() {
////        return bienesNuevos;
//    }
//
//    public void setNuevosBienes(List<BienBean> nuevosBienes) {
////        this.bienesNuevos = nuevosBienes;
//    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public List<CatalogoBean> getTipoBienes() {
        return tipoBienes;
    }

    public void setTipoBienes(List<CatalogoBean> tipoBienes) {
        this.tipoBienes = tipoBienes;
    }

    public List<CatalogoBean> getTipoBienesParaMostrar() {
        return tipoBienesParaMostrar;
    }

    public void setTipoBienesParaMostrar(
            List<CatalogoBean> tipoBienesParaMostrar) {
        this.tipoBienesParaMostrar = tipoBienesParaMostrar;
    }

    public String getClase() {
        return clase;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }

    public Integer getIdInternaCliente() {
        return idInternaCliente;
    }

    public void setIdInternaCliente(Integer idInternaCliente) {
        this.idInternaCliente = idInternaCliente;
    }

    public List<BienBean> getBienesLista() {
        return bienesLista;
    }

    public void setBienesLista(List<BienBean> bienesLista) {
        this.bienesLista = bienesLista;
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

    public ClienteBean getCliente() {
        return cliente;
    }

    public void setCliente(ClienteBean cliente) {
        this.cliente = cliente;
    }

    public Integer getPosicionScroll() {
        return posicionScroll;
    }

    public void setPosicionScroll(Integer posicionScroll) {
        this.posicionScroll = posicionScroll;
    }

    public DialogoListadoUtils getDialogoUtils() {
        return dialogoUtils;
    }

    public void setDialogoUtils(DialogoListadoUtils dialogoUtils) {
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

    public Boolean getErrorBienes() {
        return errorBienes;
    }

    public void setErrorBienes(Boolean errorBienes) {
        this.errorBienes = errorBienes;
    }

    public Integer getBienesEliminados() {
        return bienesEliminados;
    }

    public void setBienesEliminados(Integer bienesEliminados) {
        this.bienesEliminados = bienesEliminados;
    }

	public static int getManejoMaxBienes() {
		return MANEJO_MAX_BIENES;
	}
	
	public String getCurrentDate() {
		return new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime());
	}
    
}
