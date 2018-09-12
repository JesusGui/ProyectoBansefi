package mx.babel.bansefi.banksystem.cuentas.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

import mx.babel.arq.catalogo.beans.CatalogoBean;
import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ProveedorMensajeSpringUtils;
import mx.babel.arq.sesion.utils.ContextoUtils;
import mx.babel.arq.storage.annotations.StoreStep;
import mx.babel.arq.storage.beans.StorageMgrBean;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.backends.ConsultaDocumentosBackEnd;
import mx.babel.bansefi.banksystem.base.backends.ConsultaRepresentanteLegalBackEnd;
import mx.babel.bansefi.banksystem.base.backends.busquedas.BusquedaIdExternaBackEnd;
import mx.babel.bansefi.banksystem.base.backends.busquedas.BusquedaIdInternaBackEnd;
import mx.babel.bansefi.banksystem.base.backends.busquedas.BusquedaPersonaMoralBackEnd;
import mx.babel.bansefi.banksystem.base.beans.domain.ClienteBean;
import mx.babel.bansefi.banksystem.base.beans.domain.ClientePersonaFisicaBean;
import mx.babel.bansefi.banksystem.base.beans.domain.CuentaBean;
import mx.babel.bansefi.banksystem.base.beans.domain.DocumentoBean;
import mx.babel.bansefi.banksystem.base.beans.domain.TipoCliente;
import mx.babel.bansefi.banksystem.base.beans.productossimples.ProductoSimpleBean;
import mx.babel.bansefi.banksystem.base.constants.ConstantesFuncionales;
import mx.babel.bansefi.banksystem.base.enums.BusquedaEnum;
import mx.babel.bansefi.banksystem.base.enums.CatalogoEnum;
import mx.babel.bansefi.banksystem.base.enums.NavegacionEnum;
import mx.babel.bansefi.banksystem.base.enums.ParametrosFlashEnum;
import mx.babel.bansefi.banksystem.base.utils.CatalogoUtils;
import mx.babel.bansefi.banksystem.base.utils.ManagedBeanStateRecover;
import mx.babel.bansefi.banksystem.cuentas.backends.AltaCuentaBackend;
import mx.babel.bansefi.banksystem.cuentas.backends.ConsultaDetalleGrpPrdVendBackend;
import mx.babel.bansefi.banksystem.cuentas.backends.ConsultaGrpPrdVendBackend;
import mx.babel.bansefi.banksystem.cuentas.backends.ConsultaNivelAcuerdoBackend;
import mx.babel.bansefi.banksystem.cuentas.backends.ConsultaNivelClienteBackend;
import mx.babel.bansefi.banksystem.cuentas.backends.ConsultaNombreCondicionesBackend;
import mx.babel.bansefi.banksystem.cuentas.backends.ConsultaProductosSimplesBackend;
import mx.babel.bansefi.banksystem.cuentas.backends.ConsultaProductosSimplesPorCuentaBackend;
import mx.babel.bansefi.banksystem.cuentas.beans.ProductoBean;
import mx.babel.bansefi.banksystem.cuentas.beans.TarifaBean;
import mx.babel.bansefi.banksystem.cuentas.beans.TarifaRelacionBean;
import mx.babel.bansefi.banksystem.cuentas.utils.TarifasUtils;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.context.RequestContext;
import org.primefaces.event.DragDropEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@ManagedBean(name = "altaCuentaController")
@Component
@Scope("view")
public class AltaCuentaController extends StorageMgrBean implements Serializable{

	private static final long serialVersionUID = -2303751709668796838L;
	private static final Logger LOGGER = LogManager.getLogger(AltaCuentaController.class.getName());
	

    //Id para meter en flash el Mapa de tarifas
    private static final String MAPA_TARIFAS = "mapa_tarifas";
    private static final String LISTA_CREDPREST_TARIFAS = "credPrestTarifas";
    private static final String LISTA_TARIFAS_FRECUENTES = "tarifasFrecuentes";
    private static final String LISTA_OTROS_TARIFAS = "otrosTarifas";
    private static final String LISTA_PLAZO_TARIFAS =  "plazoTarifas";
    private static final String LISTA_VISTA_TARIFAS = "vistaTarifas";
    private static final String LISTA_MEDIOS_PAGO_TARIFAS = "mediosPagoTarifas";
    private static final String CUSTOM_FILTER = "customFilter";

	/**
	 * Referencias Comunes
	 */
	private ClienteBean cliente;
	private CuentaBean cuentaBean;
	private String idFiltrado;

	//Bean que indica la tarifa seleccionada en el paso 1
	//Viaja al paso 2 y al paso 3
	private TarifaBean tarifaSeleccionada;
	//Mapa de tarifas sirve para enviar las tarifas al paso 2 y que
	//al volver al paso 1 no haya que consultar Host de nuevo
	private Map<String, List<TarifaBean>> mapaTarifas;

	/**
	 * Referencias Paso 1
	 */
	private List<TarifaBean> vistaTarifas;
	private List<TarifaBean> plazoTarifas;
	private List<TarifaBean> credPrestTarifas;
	private List<TarifaBean> otrosTarifas;
    private List<TarifaBean> mediosPagoTarifas;
	private List<TarifaBean> tarifasFrecuentes;

	private int columnaSize;
	private String inputFiltro;

	private Boolean clienteSinApoderado = false;

	@Autowired
    ConsultaGrpPrdVendBackend consultaGrpPrdVendBackend;

	@Autowired
    ConsultaDetalleGrpPrdVendBackend consultaDetalleGrpPrdVendBackend;
	/**
	 * Referencias Paso 2
	 */
	//Da de alta la cuenta para pasar al paso 3
    @Autowired
    AltaCuentaBackend altaCuentaBackend;
    //Carga los productos simples relacionados con la tarifa
    //seleccionada en el paso 1
	@Autowired
    ConsultaProductosSimplesBackend consultaProductosSimplesBackend;

    //Consulta los productos simples y los ids de las condiciones de la
    //cuenta contratada en el paso 2
    @Autowired
    ConsultaProductosSimplesPorCuentaBackend consultaProductosSimplesPorCuentaBackend;

    //Consulta los nombres de las condiciones a partir del producto Simple
    @Autowired
    ConsultaNombreCondicionesBackend consultaNombreCondicionesBackend;

	@Autowired
	BusquedaIdInternaBackEnd busquedaIdInternaBackEnd;

    @Autowired
    BusquedaIdExternaBackEnd busquedaIdExternaBackEnd;

    @Autowired
    BusquedaPersonaMoralBackEnd busquedaPersonaMoralBackEnd;

	@Autowired
	ConsultaNivelClienteBackend consultaNivelClienteBackend;

	@Autowired
	ConsultaNivelAcuerdoBackend consultaNivelAcuerdoBackend;

	@Autowired
	ConsultaDocumentosBackEnd consultaDocumentosBackEnd;

	@Autowired
	ConsultaRepresentanteLegalBackEnd clienteConsultaRepresentanteLegal;

	@Autowired
    ManagedBeanStateRecover managedBeanStateRecover;

    @Autowired
    ContextoUtils contexto;

    @Autowired
    TarifasUtils tarifasUtils;

    /**
     * Enum para definir a donde se redireccionará al seleccionar un objeto de
     * la tabla de resultados.
     */
    private NavegacionEnum destino;
    private Object destinoController;

    /**
	 * Variable para saber si el alta es un subflujo
	 */
	private Boolean subflujo;

    private boolean modificacionCuenta;
    private boolean doCustomFilter;

    @Autowired
    CatalogoUtils catalogoUtils;
    private String messageError;
    //Variable que indica si al final del flujo debemos nivelar la cuenta y el usuario
    //true cuando la cuenta es nivel 2 y el usuario tiene documentos
    private Boolean nivelarCuenta;


///// Logica Paso 1

	@SuppressWarnings("unchecked")
	public void iniciaPaso1() {
	    super.restoreflash();

	    final Flash flash = obtieneFlash();
	    doCustomFilter = false;
	    if(obtieneFlash().get(ParametrosFlashEnum.SUBFLUJO_ALTA_CUENTA.getParamFlash()) != null){
			this.subflujo = (Boolean) obtieneFlash().get(ParametrosFlashEnum.SUBFLUJO_ALTA_CUENTA.getParamFlash());
			this.destino = managedBeanStateRecover.getDestino();
		    this.destinoController = managedBeanStateRecover.getController();
		}else{
			this.subflujo = false;
			this.getFlujoOrigenInFlash();
		}
        if (null!= flash.get(ParametrosFlashEnum.CLIENTE.getParamFlash())) {
            cliente = (ClienteBean)flash.get(ParametrosFlashEnum.CLIENTE.getParamFlash());
            verificaApoderado(this.cliente);
        }else{
            cliente = new ClienteBean();
            if(null != flash.get(ParametrosFlashEnum.ID_INTERNA.getParamFlash())){
                this.cliente =  busquedaIdInternaBackEnd.ejecutarTRN(
                        (int)flash.get(ParametrosFlashEnum.ID_INTERNA.getParamFlash()));
                verificaApoderado(this.cliente);
            }
        }

	    if (null!=flash.get(AltaCuentaController.MAPA_TARIFAS)) {
	        this.getCustomFilterFromFlash();
	        this.mapaTarifas = (Map<String, List<TarifaBean>>) flash.get(
                    AltaCuentaController.MAPA_TARIFAS);
	        this.credPrestTarifas = mapaTarifas.get(AltaCuentaController.LISTA_CREDPREST_TARIFAS);
	        this.tarifasFrecuentes = mapaTarifas.get(AltaCuentaController.LISTA_TARIFAS_FRECUENTES);
	        this.otrosTarifas = mapaTarifas.get(AltaCuentaController.LISTA_OTROS_TARIFAS);
	        this.plazoTarifas = mapaTarifas.get(AltaCuentaController.LISTA_PLAZO_TARIFAS);
            this.mediosPagoTarifas = mapaTarifas.get(AltaCuentaController.LISTA_MEDIOS_PAGO_TARIFAS);
	        this.vistaTarifas = mapaTarifas.get(AltaCuentaController.LISTA_VISTA_TARIFAS);

	    }else{
	        TarifaRelacionBean tarifaRelacionada = null;

	        if(null!=flash.get(ParametrosFlashEnum.TARIFA_RELACIONADA.getParamFlash())){
	            tarifaRelacionada = (TarifaRelacionBean)flash.get(ParametrosFlashEnum.TARIFA_RELACIONADA.getParamFlash());
	            doCustomFilter = true;
	        }

    	    List<TarifaBean> listaTarifas = new ArrayList<TarifaBean>();

    	    for(final CatalogoBean linea:catalogoUtils.getCatalogo(CatalogoEnum.TP_LINEA_ASES)) {
    	        final List<TarifaBean> tempList = consultaGrpPrdVendBackend.ejecutarTRN(
    	                new TarifaBean(linea.getClaveFila()), contexto.getEntidad());
    	        if(tempList != null && !tempList.isEmpty()){
    	            listaTarifas.addAll(tempList);
    	        }
            }
    	    if(doCustomFilter && tarifaRelacionada != null
    	            && tarifaRelacionada.getProductos() != null && !tarifaRelacionada.getProductos().isEmpty()){
    	        final List<ProductoBean> tarifasPermitidas = tarifaRelacionada.getProductos();
    	        final List<TarifaBean> tarifasFiltradas = new ArrayList<TarifaBean>();
    	        for(final TarifaBean tarifa : listaTarifas){
    	            final ProductoBean prodBean = new ProductoBean();
    	            prodBean.setLinea(tarifa.getLinea());
                    prodBean.setGrupo(tarifa.getGrupo());
    	            if(tarifasPermitidas.contains(prodBean)){
    	                tarifasFiltradas.add(tarifa);
    	            }
    	        }
    	        listaTarifas = tarifasFiltradas;
    	    }
    	    this.vistaTarifas = TarifasUtils.filtrarVista(listaTarifas);
    	    this.plazoTarifas = TarifasUtils.filtrarPlazo(listaTarifas);
    	    this.credPrestTarifas = TarifasUtils.filtrarPrestamoCredito(listaTarifas);
    	    this.otrosTarifas = TarifasUtils.filtrarOtrosProductos(listaTarifas);
            this.mediosPagoTarifas = TarifasUtils.filtrarMediosPagoProductos(listaTarifas);
    	    this.tarifasFrecuentes = TarifasUtils.cargarCuentasFrecuentes(listaTarifas, this.getRutaFicheroTarifasFrecuentes());

    	    this.mapaTarifas = new HashMap<String, List<TarifaBean>>(5);
            mapaTarifas.put(AltaCuentaController.LISTA_CREDPREST_TARIFAS, this.credPrestTarifas);
            mapaTarifas.put(AltaCuentaController.LISTA_OTROS_TARIFAS, this.otrosTarifas);
            mapaTarifas.put(AltaCuentaController.LISTA_PLAZO_TARIFAS, this.plazoTarifas);
            mapaTarifas.put(AltaCuentaController.LISTA_VISTA_TARIFAS, this.vistaTarifas);
            mapaTarifas.put(AltaCuentaController.LISTA_MEDIOS_PAGO_TARIFAS, this.mediosPagoTarifas);
	    }

	    final Integer browserWidth = (Integer)contexto.getConfigData(ConstantesFuncionales.BROWSER_WIDTH);
        if(null == browserWidth || browserWidth<1500){
            this.columnaSize = 5;
        }else{
            this.columnaSize = 6;
        }
	}

	/**
	 *
	 */
    public void dropInEvent(final DragDropEvent ddEvent) {
        final List<TarifaBean> sourceList = this.getSourceList(ddEvent.getDragId());
        if(null!=sourceList){
            final int sourcePosition = this.getTarifasPosition(ddEvent.getDragId());
            final TarifaBean sourcetarifa = sourceList.get(sourcePosition);
            if(!this.tarifasFrecuentes.contains(sourcetarifa)){
                this.tarifasFrecuentes.add(sourcetarifa);
            }
        }
    }

    /**
     *
     */
    public void dropAtTheEndEvent() {
        final Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        final String dragId = params.get("atTheEnd_dragId");
        final List<TarifaBean> sourceList = this.getSourceList(dragId);
        if(null!=sourceList){
            final int sourcePosition = this.getTarifasPosition(dragId);
            final TarifaBean sourcetarifa = sourceList.get(sourcePosition);
            if(dragId.endsWith("dropArea")){
                this.tarifasFrecuentes.remove(sourcePosition);
                this.tarifasFrecuentes.add(sourcetarifa);
            }else if(!this.tarifasFrecuentes.contains(sourcetarifa)){
                this.tarifasFrecuentes.add(sourcetarifa);
            }
        }
    }

    private int getTarifasPosition(final String elementId) {
        final String[] splittedElementId = elementId.split(":");
        try{
            return Integer.parseInt(splittedElementId[splittedElementId.length-2]);
        } catch(final NumberFormatException ex){
            throw new NoControlableException("Ha ocurrido un error: Indice "+elementId+" para el ordenamiento de las tarifas frecuentes no es un número.", ex);
        } catch (final IndexOutOfBoundsException ex) {
            throw new NoControlableException("Ha ocurrido un error: Indice "+elementId+" para el ordenamiento de las tarifas frecuentes no encontrado.", ex);
        }
    }

    private List<TarifaBean> getSourceList(final String dragId) {
        List<TarifaBean> result = null;
        final String[] dragIds = dragId.split(":");
        switch (dragIds[dragIds.length-1]) {
          case "pnlVistaTarifas":
              result = this.getVistaTarifas();
            break;
          case "pnlPlazoTarifas":
              result = this.getPlazoTarifas();
                break;
          case "pnlCredPrestTarifas":
              result = this.getCredPrestTarifas();
                    break;
          case "pnlOtrosTarifas":
              result = this.getOtrosTarifas();
                        break;
          case "pnlMediosPagoTarifas":
              result = this.getMediosPagoTarifas();
                        break;
          case "dropArea":
              result = this.getTarifasFrecuentes();
                        break;
          default:
              throw new NoControlableException("Ha ocurrido un error: ", this.getClass().getName() +": lista "+dragIds[dragIds.length-1]+" origen de tarifas no encontrada");
        }
        return result;
    }

    /**
     *
     */
    public void dropInOrderedEvent() {

        final Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        final String dragId = params.get("inOrdered_dragId");

        if(dragId.endsWith("dropArea")){
            this.reorderEvent(dragId);
        }else{
            final int sourcePosition = this.getTarifasPosition(dragId);
            final List<TarifaBean> sourceList = this.getSourceList(dragId);
            if(null!=sourceList){
                final TarifaBean sourcetarifa = sourceList.get(sourcePosition);

                if(this.tarifasFrecuentes.isEmpty()){
                    this.tarifasFrecuentes.add(sourcetarifa);
                }else if(!this.tarifasFrecuentes.contains(sourcetarifa)){
                    String selfId = "";
                    if(params.containsKey(dragId + "_selfId")){
                        selfId = params.get(dragId + "_selfId");
                    }
                    final int index =  this.getTarifasPosition(selfId);
                    this.tarifasFrecuentes.add(index, sourcetarifa);
                }
            }
        }
    }

    private void reorderEvent(final String dragId) {
        if(!this.tarifasFrecuentes.isEmpty()&& this.tarifasFrecuentes.size()>1){
            final Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
            String selfId = "";
            if(params.containsKey(dragId + "_selfId")){
                selfId = params.get(dragId + "_selfId");
            }
            final int targetIndex =  this.getTarifasPosition(selfId);
            final int sourceIndex =  this.getTarifasPosition(dragId);
            final TarifaBean target = this.tarifasFrecuentes.get(targetIndex);
            final TarifaBean source = this.tarifasFrecuentes.get(sourceIndex);
            this.tarifasFrecuentes.remove(source);
            if(sourceIndex > targetIndex){
                this.tarifasFrecuentes.add(this.tarifasFrecuentes.indexOf(target), source);
            }else{

                this.tarifasFrecuentes.add(this.tarifasFrecuentes.indexOf(target)+1, source);
            }
        }
    }

    /**
     *
     */
    public void dropOutEvent() {
        final Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        final String dragId = params.get("dropOut_dragId");
        if(dragId.endsWith("dropArea") ){
            final int sourceIndex =  this.getTarifasPosition(dragId);
            this.tarifasFrecuentes.remove(sourceIndex);

        }
    }
    /**
     *
     */
    public void seleccionarTarifa() {
        final Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        final String clickTarget = params.get("clickTarifa_target");
        final int clickPosition = this.getTarifasPosition(clickTarget);
        final List<TarifaBean> clickList = this.getSourceList(clickTarget);
        if(null!=clickList){
            this.tarifaSeleccionada = clickList.get(clickPosition);
        }
        if(StringUtils.isNotBlank(this.idFiltrado)&&
                this.cliente!=null&&StringUtils.isBlank(this.cliente.getNombre())){
            addMessage(FacesMessage.SEVERITY_FATAL,
                    "¡Atención!", "La identificación introducida no existe");
            this.idFiltrado = "";
        }

        if(StringUtils.isBlank(this.idFiltrado)||StringUtils.isEmpty(this.idFiltrado)){
            addMessage(FacesMessage.SEVERITY_FATAL,
                    "¡Atención!", "Debe Asociar la Cuenta a una Persona Física o Moral");
        }
        
    }

    private String getRutaFicheroTarifasFrecuentes() {
        return ProveedorMensajeSpringUtils.getValorConfiguracion("ruta.configuracion")+"0166"+
                ProveedorMensajeSpringUtils.getValorConfiguracion("ruta.configuracion.cuentasfrecuentes")+
                contexto.getEntidad()+contexto.getTerminal()+contexto.getId()+".config";
    }


    /**
     * @return Metodo utilizado para acceder al paso 2 del alta de
     *         cuentas
     */
    @StoreStep
    public String irAPaso2() {
        if(this.cliente==null || StringUtils.isBlank(this.cliente.getNombre())){
            return null;
        }
//        if(! this.doCustomFilter){
//            TarifasUtils.guardarCuentasFrecuentes(this.tarifasFrecuentes, this.getRutaFicheroTarifasFrecuentes());
//        }
        if(this.esNivelCuentaClienteDiferente(this.cliente, this.tarifaSeleccionada)){
            addMessage(FacesMessage.SEVERITY_FATAL,
                    "¡Atención!", "Operación errónea por EXPEDIENTE INCOMPLETO "
                    + StringUtils.trimToEmpty(this.tarifaSeleccionada.getNombre()) + " "
                    + StringUtils.trimToEmpty(this.tarifaSeleccionada.getDescripcion()));
            return null;
        }

        this.setClienteBeanInFlash();
        this.setTarifaSeleccionadaInFlash();
        this.setFlujoOrigenInFlash();
        this.setCustomFilterInFlash();
        this.mapaTarifas.put(AltaCuentaController.LISTA_TARIFAS_FRECUENTES, this.tarifasFrecuentes);
        obtieneFlash().put(AltaCuentaController.MAPA_TARIFAS, this.mapaTarifas);
        obtieneFlash().put(ParametrosFlashEnum.NIVELAR_CUENTA.getParamFlash(), this.nivelarCuenta);
        String vista = null;
        if (tarifaSeleccionada.getLinea().equals("03") && tarifaSeleccionada.getGrupo().equals("51") )
            vista = mx.babel.bansefi.banksystem.base.enums.integracionAppWhere.NavegacionEnum.ALTACUENTA.getRuta();
        else
            vista = NavegacionEnum.ALTA_CUENTA2.getRuta();
        return vista;
    }


    private void setCustomFilterInFlash() {
            final Flash flash = obtieneFlash();
            flash.put(AltaCuentaController.CUSTOM_FILTER, doCustomFilter);
    }

    private void getCustomFilterFromFlash() {
        final Flash flash = obtieneFlash();
        if(null != flash.get(AltaCuentaController.CUSTOM_FILTER)){
            doCustomFilter = (boolean)flash.get(AltaCuentaController.CUSTOM_FILTER);
        }
    }

    private boolean esNivelCuentaClienteDiferente(final ClienteBean cliente,
            final TarifaBean tarifa) {
        boolean result = false;
        final String nivelClienteString = this.consultaNivelClienteBackend.ejecutarTRN(cliente.getIdInterna());
        Integer nivelCliente = null;
        if(nivelClienteString == null || Integer.parseInt(nivelClienteString) == 2){
        	nivelCliente = consultaNivelDocumentos(cliente);
        }else{
            nivelCliente = Integer.parseInt(nivelClienteString);
        }
        String nivelAcuerdo = this.consultaNivelAcuerdoBackend.ejecutarWS(tarifa);
        if(StringUtils.isNotBlank(nivelAcuerdo)){
            nivelAcuerdo = nivelAcuerdo.toUpperCase().replace("NIVEL ", "");
            if(Integer.parseInt(nivelAcuerdo) == 2 && nivelCliente.intValue() == 4 ){
                nivelarCuenta= true;
            }
            if(nivelCliente.intValue() < Integer.parseInt(nivelAcuerdo)){
        		result = true;
            }
        }
        return result;
    }

    private Integer consultaNivelDocumentos(final ClienteBean cliente){
    	Integer nivel = 2;
    	try{
    		consultaDocumentosBackEnd.ejecutarTRN(cliente);
    	}catch(final ControlableException e){
    		return nivel;
    	}
    	Boolean tieneCedula = false;
    	Boolean tieneIdentificacion = false;
    	Boolean tieneComprobante = false;
    	for (final DocumentoBean documento : cliente.getDocumentos()) {
			if(ConstantesFuncionales.getCodigosComprobanteDomicilio().contains(documento.getTipoDocumento())){
				tieneComprobante = true;
			}
			if(ConstantesFuncionales.getCodigosCedulaConocimiento().contains(documento.getTipoDocumento())){
				tieneCedula = true;
			}
			if(ConstantesFuncionales.getCodigosComprobanteIdentificacion().contains(documento.getTipoDocumento())){
				tieneIdentificacion = true;
			}
		}
    	if(tieneIdentificacion && tieneCedula && tieneComprobante){
    		nivel = 4;
    	}
    	return nivel;
    }

    public int getColumnaSize(){
        return this.columnaSize;
    }

    public void filtraTarifa(){
        this.vistaTarifas = this.hacerFiltrado(AltaCuentaController.LISTA_VISTA_TARIFAS);
        this.credPrestTarifas = this.hacerFiltrado(AltaCuentaController.LISTA_CREDPREST_TARIFAS);
        this.otrosTarifas = this.hacerFiltrado(AltaCuentaController.LISTA_OTROS_TARIFAS);
        this.plazoTarifas = this.hacerFiltrado(AltaCuentaController.LISTA_PLAZO_TARIFAS);
        this.mediosPagoTarifas = this.hacerFiltrado(AltaCuentaController.LISTA_MEDIOS_PAGO_TARIFAS);
    }

    private List<TarifaBean> hacerFiltrado(final String nombreMapa) {
        if(StringUtils.isNotBlank(this.inputFiltro)){
            final List<TarifaBean> nuevaLista = new ArrayList<TarifaBean>();
            final List<TarifaBean> originalLista = this.mapaTarifas.get(nombreMapa);
            for(final TarifaBean bean : originalLista){
                final String beanId = bean.getLinea()+" "+bean.getGrupo()+" "+bean.getProducto()+" "+bean.getId();
                if(beanId.contains(this.inputFiltro.trim()) || bean.getNombre().contains(this.inputFiltro.trim()) ){
                    nuevaLista.add(bean);
                }
            }
            return nuevaLista;
        }
        return this.mapaTarifas.get(nombreMapa);
    }

///// Fin logica Paso 1
///// Logica Paso 2
	@SuppressWarnings("unchecked")
	public void iniciaPaso2() {
	  //TODO Descomentar el codigo de la pagina xhtml 2
        //o eliminarlo si no hay que mostrar las condiciones
        super.restoreflash();
        Date fechaMostrarPDS = Calendar.getInstance().getTime();
        final Flash flash = obtieneFlash();
        if(obtieneFlash().get(ParametrosFlashEnum.SUBFLUJO_ALTA_CUENTA.getParamFlash()) != null){
			this.subflujo = (Boolean) obtieneFlash().get(ParametrosFlashEnum.SUBFLUJO_ALTA_CUENTA.getParamFlash());
			this.destino = managedBeanStateRecover.getDestino();
		    this.destinoController = managedBeanStateRecover.getController();
		}else{
			this.subflujo = false;
			this.getFlujoOrigenInFlash();
		}
        if(null!= flash.get(ParametrosFlashEnum.MODIFICACION_CUENTA.getParamFlash())
                && null !=flash.get(ParametrosFlashEnum.CUENTA_BEAN.getParamFlash())){
            this.modificacionCuenta = true;
            this.cuentaBean = (CuentaBean)flash.get(ParametrosFlashEnum.CUENTA_BEAN.getParamFlash());
            fechaMostrarPDS = this.cuentaBean.getFechaEstado();
            this.cliente = tarifasUtils.getClienteFromCuenta(this.cuentaBean);
            this.tarifaSeleccionada = new TarifaBean();
            this.tarifaSeleccionada.setLinea(this.cuentaBean.getCodLinea());
            this.tarifaSeleccionada.setGrupo(this.cuentaBean.getIdGrupoProducto());
            this.tarifaSeleccionada.setProducto(this.cuentaBean.getIdProducto());
            this.tarifaSeleccionada.setId(this.cuentaBean.getIdTarifaProducto());
            this.tarifaSeleccionada = consultaDetalleGrpPrdVendBackend.ejecutarTRN(this.tarifaSeleccionada);
        }else{
            if (null!= flash.get(ParametrosFlashEnum.CLIENTE.getParamFlash())) {
                cliente = (ClienteBean)flash.get(ParametrosFlashEnum.CLIENTE.getParamFlash());
                verificaApoderado(cliente);
            }else{
                throw new NoControlableException("Ha ocurrido un error:",
                        "Cliente seleccionado no disponible");
            }
            this.loadTarifaSeleccionadaFromFlash();
            if (null!= flash.get(AltaCuentaController.MAPA_TARIFAS)) {
                mapaTarifas =  (Map<String, List<TarifaBean>>) flash.get(
                        AltaCuentaController.MAPA_TARIFAS);
            }

        }
        this.tarifaSeleccionada.setProductosSimples(this.getProductosSimples(tarifaSeleccionada, fechaMostrarPDS));
        this.getCustomFilterFromFlash();
        if(null!= flash.get(ParametrosFlashEnum.NIVELAR_CUENTA.getParamFlash())){
            this.nivelarCuenta = (Boolean)obtieneFlash().get(ParametrosFlashEnum.NIVELAR_CUENTA.getParamFlash());
        }
	}

    private List<ProductoSimpleBean> getProductosSimples(final TarifaBean tarifaBean, final Date fechaAMostrar) {
        //TODO sustituir el long por el numero de acuerdo recien contratado cuando toque
    	// Listado de productos simples
        final List<ProductoSimpleBean> resultado = consultaProductosSimplesBackend.ejecutarTRN(tarifaBean);
        if(resultado != null){
            for(final ProductoSimpleBean pdsBean : resultado){
                pdsBean.setFechaInicio(fechaAMostrar);
            }
        }
    	return resultado;
    }

	/**
     * @return Metodo utilizado para acceder al paso 1 del alta de
     *         cuentas por primera vez
     */
    @StoreStep
    public String irAPaso1() {
        this.setClienteBeanInFlash();
        this.setCustomFilterInFlash();
        obtieneFlash().put(AltaCuentaController.MAPA_TARIFAS, this.mapaTarifas);
        this.setFlujoOrigenInFlash();
        return NavegacionEnum.ALTA_CUENTA1.getRuta();
    }


    @StoreStep
    public String irAPaso3() {
        //TODO contratar cuenta cuando funcione
        //TODO consultar aqui los productos simples en lugar de en inicioPaso2
        //porque ahi deberia haber otra trn
        try{
            final List<ProductoSimpleBean> completedPdsList = this.getProductosSimplesPorCuenta();
            this.cuentaBean = this.altaCuentaBackend.ejecutarTRN(this.cliente, tarifaSeleccionada);
            this.tarifaSeleccionada.setProductosSimples(completedPdsList);
        }catch(final ControlableException ex){
                this.messageError = ex.getMensajeDetalle();
                return null;
        }
        this.setClienteBeanInFlash();
        this.setCuentaBeanInFlash();
        this.setTarifaSeleccionadaInFlash();
        this.setFlujoOrigenInFlash();
        obtieneFlash().put(ParametrosFlashEnum.NIVELAR_CUENTA.getParamFlash(), this.nivelarCuenta);
        return NavegacionEnum.ALTA_CUENTA3.getRuta();
    }

    private List<ProductoSimpleBean> getProductosSimplesPorCuenta() {
        //TODO cambiar el numero de cuenta por el correcto
          final List<ProductoSimpleBean> resultado = this.consultaProductosSimplesPorCuentaBackend
                  .ejecutarTRN(null, this.tarifaSeleccionada);
          return tarifasUtils.fillPdsCondDescriptions(resultado, this.tarifaSeleccionada);
  }

///// Fin logica Paso 2



    private void loadTarifaSeleccionadaFromFlash(){
        if (null!= obtieneFlash().get(ParametrosFlashEnum.TARIFA_SELECCIONADA.getParamFlash())) {
            this.tarifaSeleccionada = (TarifaBean) obtieneFlash()
                    .get(ParametrosFlashEnum.TARIFA_SELECCIONADA.getParamFlash());
        }else{
            throw new NoControlableException("Ha ocurrido un error:",
                "La tarifa seleccionada no disponible");
        }
    }

    private void setClienteBeanInFlash(){
        obtieneFlash().put(ParametrosFlashEnum.CLIENTE.getParamFlash(), cliente);
    }

    private void setCuentaBeanInFlash(){
        obtieneFlash().put(ParametrosFlashEnum.CUENTA_BEAN.getParamFlash(), this.cuentaBean);
    }

    private void setTarifaSeleccionadaInFlash(){
        obtieneFlash().put(ParametrosFlashEnum.TARIFA_SELECCIONADA.getParamFlash(), this.tarifaSeleccionada);
    }

    private void setFlujoOrigenInFlash() {
        if(null != destino && null != destinoController){
            final Flash flash = obtieneFlash();
            flash.put(ParametrosFlashEnum.DESTINO.getParamFlash(), destino);
            flash.put(ParametrosFlashEnum.DESTINO_CONTROLLER.getParamFlash(), destinoController);
            flash.put(ParametrosFlashEnum.NAVEGACION_BUSQUEDA.getParamFlash(), destino);
            flash.put(ParametrosFlashEnum.CONTROLLER_DESTINO.getParamFlash(), destinoController);
        }
        parametrosSubFlujo();
    }

    private void getFlujoOrigenInFlash() {
        final Flash flash = obtieneFlash();
        if(flash.get(ParametrosFlashEnum.NAVEGACION_MANAGED_BEAN.getParamFlash())!=null){
            destino = managedBeanStateRecover.getDestino();
            destinoController = managedBeanStateRecover.getController();
        }else{
            if(flash.get(ParametrosFlashEnum.DESTINO.getParamFlash())!=null){
                destino = (NavegacionEnum)flash.get(ParametrosFlashEnum.DESTINO.getParamFlash());
            }
            if(flash.get(ParametrosFlashEnum.DESTINO_CONTROLLER.getParamFlash())!=null){
                destinoController = flash.get(ParametrosFlashEnum.DESTINO_CONTROLLER.getParamFlash());
            }
        }
    }

    private void parametrosSubFlujo(){
    	this.obtieneFlash().put(ParametrosFlashEnum.SUBFLUJO_ALTA_CUENTA.getParamFlash(),subflujo);
		if(subflujo){
			this.obtieneFlash().put(ParametrosFlashEnum.NAVEGACION_MANAGED_BEAN.getParamFlash(),true);
		}
	}
    /**
     * @return the messageError
     */
    public String getMessageError() {
        return messageError;
    }

    /**
     * @param messageError the messageError to set
     */
    public void setMessageError(final String messageError) {
        this.messageError = messageError;
    }

    /**
     * @return Metodo utilizado para recuperar una instancia de Flash
     */
    public Flash obtieneFlash() {
        return FacesContext.getCurrentInstance().getExternalContext()
                .getFlash();
    }

	/**
	 * @return the vistaTarifas
	 */
	public List<TarifaBean> getVistaTarifas() {
		return vistaTarifas;
	}

	/**
	 * @param vistaTarifas the vistaTarifas to set
	 */
	public void setVistaTarifas(final List<TarifaBean> vistaTarifas) {
		this.vistaTarifas = vistaTarifas;
	}

	/**
	 * @return the plazoTarifas
	 */
	public List<TarifaBean> getPlazoTarifas() {
		return plazoTarifas;
	}

	/**
	 * @param plazoTarifas the plazoTarifas to set
	 */
	public void setPlazoTarifas(final List<TarifaBean> plazoTarifas) {
		this.plazoTarifas = plazoTarifas;
	}

	/**
	 * @return the credPrestTarifas
	 */
	public List<TarifaBean> getCredPrestTarifas() {
		return credPrestTarifas;
	}

	/**
	 * @param credPrestTarifas the credPrestTarifas to set
	 */
	public void setCredPrestTarifas(final List<TarifaBean> credPrestTarifas) {
		this.credPrestTarifas = credPrestTarifas;
	}

	/**
	 * @return the otrosTarifas
	 */
	public List<TarifaBean> getOtrosTarifas() {
		return otrosTarifas;
	}

	/**
	 * @param otrosTarifas the otrosTarifas to set
	 */
	public void setOtrosTarifas(final List<TarifaBean> otrosTarifas) {
		this.otrosTarifas = otrosTarifas;
	}

	/**
	 * @return the tarifasFrecuentes
	 */
	public List<TarifaBean> getTarifasFrecuentes() {
		return tarifasFrecuentes;
	}

	/**
	 * @param tarifasFrecuentes the tarifasFrecuentes to set
	 */
	public void setTarifasFrecuentes(final List<TarifaBean> tarifasFrecuentes) {
		this.tarifasFrecuentes = tarifasFrecuentes;
	}



	/**
     * @return the mediosPagoTarifas
     */
    public List<TarifaBean> getMediosPagoTarifas() {
        return mediosPagoTarifas;
    }

    /**
     * @param mediosPagoTarifas the mediosPagoTarifas to set
     */
    public void setMediosPagoTarifas(final List<TarifaBean> mediosPagoTarifas) {
        this.mediosPagoTarifas = mediosPagoTarifas;
    }

    @Override
	protected Map<String, Object> getBeanList() {
	    final Map<String, Object> beanList = new HashMap<String, Object>();
        beanList.put(ClienteBean.class.getName(), cliente);
        beanList.put(CuentaBean.class.getName(), cuentaBean);
        beanList.put(TarifaBean.class.getName(), tarifaSeleccionada);
        beanList.put(ParametrosFlashEnum.NIVELAR_CUENTA.getParamFlash(), nivelarCuenta);
        if(!this.doCustomFilter){
            beanList.put(ParametrosFlashEnum.DESTINO.getParamFlash(), destino);
            beanList.put(ParametrosFlashEnum.DESTINO_CONTROLLER.getParamFlash(), destinoController);
        }
		return beanList;
	}

	@Override
	protected void setBeanList(final Map<String, Object> beanList) {
	    if (null != beanList) {
            final Flash flash = obtieneFlash();
            final ClienteBean clienteB = (ClienteBean) beanList.get(ClienteBean.class.getName());
            if(null != clienteB){
                flash.put(ParametrosFlashEnum.CLIENTE.getParamFlash(), clienteB);
            }
            final CuentaBean cuentaB = (CuentaBean) beanList.get(CuentaBean.class.getName());
            if(null != cuentaB){
                flash.put(ParametrosFlashEnum.CUENTA_BEAN.getParamFlash(), cuentaB);
            }
            final TarifaBean tarifaB = (TarifaBean) beanList.get(TarifaBean.class.getName());
            if(null != tarifaB){
                flash.put(ParametrosFlashEnum.TARIFA_SELECCIONADA.getParamFlash(), tarifaB);
            }
            final NavegacionEnum origen = (NavegacionEnum) beanList.get(ParametrosFlashEnum.DESTINO.getParamFlash());
            if(null != origen){
                flash.put(ParametrosFlashEnum.DESTINO.getParamFlash(), origen);
            }
            final Object origenController = beanList.get(ParametrosFlashEnum.DESTINO_CONTROLLER.getParamFlash());
            if(null != origenController){
                flash.put(ParametrosFlashEnum.DESTINO_CONTROLLER.getParamFlash(), origenController);
            }
            final Boolean nivelarAcc = (Boolean)beanList.get(ParametrosFlashEnum.NIVELAR_CUENTA.getParamFlash());
            if(null != nivelarAcc){
                flash.put(ParametrosFlashEnum.NIVELAR_CUENTA.getParamFlash(), nivelarAcc);
            }
        }
	}

	@Override
	protected String getNombreFlujo() {
		return "Alta de Cuenta: " + this.tarifaSeleccionada.getNombre() + " para "+this.cliente.getNombreCompleto();
	}

	/**
	 * @return Metodo utilizado para acceder al paso 1 del alta de
	 *         cuentas por primera vez
	 */
	@StoreStep
	public String inicio() {
		return NavegacionEnum.ALTA_CUENTA1.getRuta();
	}

    public String irAHome() {
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
     * @return the cliente
     */
    public ClienteBean getCliente() {
        return cliente;
    }

    /**
     * @param cliente the cliente to set
     */
    public void setCliente(final ClienteBean cliente) {
        this.cliente = cliente;
    }

    /**
	 * @return the tarifaSeleccionada
	 */
	public TarifaBean getTarifaSeleccionada() {
		return tarifaSeleccionada;
	}

	/**
	 * @param tarifaSeleccionada the tarifaSeleccionada to set
	 */
	public void setTarifaSeleccionada(final TarifaBean tarifaSeleccionada) {
		this.tarifaSeleccionada = tarifaSeleccionada;
	}


    /**
     * @return the cuentaBean
     */
    public CuentaBean getCuentaBean() {
        return cuentaBean;
    }


    /**
     * @param cuentaBean the cuentaBean to set
     */
    public void setCuentaBean(final CuentaBean cuentaBean) {
        this.cuentaBean = cuentaBean;
    }

    /**
     * @return the inputFiltro
     */
    public String getInputFiltro() {
        return "";
    }

    /**
     * @param inputFiltro the inputFiltro to set
     */
    public void setInputFiltro(final String inputFiltro) {
        this.inputFiltro = inputFiltro;
    }

    public String irABuscadorFisico(){
        //TODO Probar cuando funcione la busqueda de cuentas
        if(! this.doCustomFilter){
            TarifasUtils.guardarCuentasFrecuentes(this.tarifasFrecuentes, this.getRutaFicheroTarifasFrecuentes());
        }
        obtieneFlash().put(ParametrosFlashEnum.TIPO_BUSQUEDA.getParamFlash(),BusquedaEnum.PERSONA_CLIENTE.getBusquedaValor());
        managedBeanStateRecover.enviaController(this, NavegacionEnum.ALTA_CUENTA1);
        return NavegacionEnum.BUSQUEDA.getRuta();
    }

    public String irABuscadorMoral(){
        //TODO Probar cuando funcione la busqueda de cuentas
        if(! this.doCustomFilter){
            TarifasUtils.guardarCuentasFrecuentes(this.tarifasFrecuentes, this.getRutaFicheroTarifasFrecuentes());
        }
        obtieneFlash().put(ParametrosFlashEnum.TIPO_BUSQUEDA.getParamFlash(),BusquedaEnum.PERSONA_MORAL.getBusquedaValor());
        managedBeanStateRecover.enviaController(this, NavegacionEnum.ALTA_CUENTA1);
        return NavegacionEnum.BUSQUEDA.getRuta();
    }

    public void consultaIdExterna(){
        if( this.cliente== null || this.cliente.getNumIdentificacion()== null
                || !StringUtils.equals(this.idFiltrado, this.cliente.getIdInterna().toString())){
            ClienteBean clienteBean = null;
            if(StringUtils.isNotBlank(this.idFiltrado)){
                List<ClientePersonaFisicaBean> clientes = new ArrayList<>();
                try{
                    clientes = this.busquedaIdExternaBackEnd
                            .ejecutarTRN(this.idFiltrado);
                }catch (final ControlableException ce){
                    if (ce.getRtncod() != BackEndBean.RETURN_COD_SIN_DATOS){
                        throw ce;
                    }
                }

                if(clientes.size() > 1){
                    this.cliente = new ClienteBean();
                    RequestContext.getCurrentInstance().execute("PF('dlgBusqueda').show()");
                }else{

                    if (!clientes.isEmpty()) {
                        clienteBean = busquedaIdInternaBackEnd.ejecutarTRN(clientes.get(0).getIdInterna().intValue());
                        verificaApoderado(this.cliente);
                    } else{
                        this.cliente = new ClienteBean();
                        addMessage(FacesMessage.SEVERITY_FATAL,
                                "¡Atención!", "La identificación introducida no existe");
                    }

                }
            }
            if (clienteBean != null && clienteBean.getIdInterna() != null
                    && StringUtils.isNotBlank(clienteBean.getNombre())) {
                this.cliente = clienteBean;
                verificaApoderado(this.cliente);
            }
        }
    }

    public void verificaApoderado(final ClienteBean cliente){
        if(cliente != null && cliente.getTipoClienteEnum() != null &&
                cliente.getTipoClienteEnum().equals(TipoCliente.PERSONA_MORAL)){
            final Integer idRepresentante = clienteConsultaRepresentanteLegal.
	    			ejecutarTRN(cliente.getIdInterna(),
					TipoCliente.PERSONA_MORAL);
	    	if(idRepresentante == null){
	    		clienteSinApoderado = true;
	    	}
    	}
    }

    public String irRelacionPersonas(){
    	obtieneFlash().put(ParametrosFlashEnum.CLIENTE.getParamFlash(), this.cliente);
		obtieneFlash().put(ParametrosFlashEnum.IND_ALTA_REPRESENTANTE.getParamFlash(), "true");
		managedBeanStateRecover.enviaController(this, NavegacionEnum.ALTA_CUENTA1);
		return NavegacionEnum.RELACIONES_CLIENTE.getRuta();
    }

    /**
     * Función para adicionar alertas globales en la vista
     *
     * @param severity Severidad de la alerta.
     * @param title Titulo de la alerta.
     * @param message Mensaje desplegado en la alerta.
     */
    private void addMessage(final FacesMessage.Severity severity, final String title, final String message){
        final FacesMessage facesMessage =  new FacesMessage(severity,title, message);
        FacesContext.getCurrentInstance().addMessage("noTitular", facesMessage);
    }

    public boolean isOrigenDashBoard(){
        return this.destino==null;
    }

    /**
     * @return the idFiltrado
     */
    public String getIdFiltrado() {
        if(this.cliente==null || this.cliente.getIdInterna()==null){
            return this.idFiltrado;
        }
        return this.cliente.getNumIdentificacion().toString();
    }

    /**
     * @param idFiltrado the idFiltrado to set
     */
    public void setIdFiltrado(final String idFiltrado) {
        this.idFiltrado = idFiltrado;
    }

    /**
     * @return the modificacionCuenta
     */
    public boolean isModificacionCuenta() {
        return modificacionCuenta;
    }

    /**
     * @param modificacionCuenta the modificacionCuenta to set
     */
    public void setModificacionCuenta(final boolean modificacionCuenta) {
        this.modificacionCuenta = modificacionCuenta;
    }

    /**
     * @return not doCustomFilter
     */
    public boolean isDragAndDropEnabled() {
        return !doCustomFilter;
    }

	/**
	 * @return Atributo clienteSinApoderado
	 */
	public Boolean getClienteSinApoderado() {
		return clienteSinApoderado;
	}

	/**
	 * @param clienteSinApoderado Atributo clienteSinApoderado a definir
	 */
	public void setClienteSinApoderado(final Boolean clienteSinApoderado) {
		this.clienteSinApoderado = clienteSinApoderado;
	}

    /**
     * @return the nivelarCuenta
     */
    public Boolean getNivelarCuenta() {
        return nivelarCuenta;
    }

    /**
     * @param nivelarCuenta the nivelarCuenta to set
     */
    public void setNivelarCuenta(final Boolean nivelarCuenta) {
        this.nivelarCuenta = nivelarCuenta;
    }



}
