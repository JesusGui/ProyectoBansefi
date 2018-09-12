
package mx.babel.bansefi.banksystem.cuentas.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.model.SelectItem;

import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.sesion.utils.ContextoUtils;
import mx.babel.arq.storage.annotations.StoreStep;
import mx.babel.arq.storage.beans.StorageMgrBean;
import mx.babel.bansefi.banksystem.base.backends.ConsultaCuentaBackend;
import mx.babel.bansefi.banksystem.base.beans.PreferenceItemBean;
import mx.babel.bansefi.banksystem.base.beans.domain.ClienteBean;
import mx.babel.bansefi.banksystem.base.beans.domain.CuentaBean;
import mx.babel.bansefi.banksystem.base.beans.productossimples.ComisionTarifaBean;
import mx.babel.bansefi.banksystem.base.beans.productossimples.CondicionBean;
import mx.babel.bansefi.banksystem.base.beans.productossimples.ProductoSimpleBean;
import mx.babel.bansefi.banksystem.base.beans.productossimples.condiciones.CondicionComisionBean;
import mx.babel.bansefi.banksystem.base.beans.productossimples.condiciones.CondicionInteresBean;
import mx.babel.bansefi.banksystem.base.beans.productossimples.condiciones.CondicionListaBean;
import mx.babel.bansefi.banksystem.base.beans.productossimples.condiciones.CondicionRangoBean;
import mx.babel.bansefi.banksystem.base.beans.productossimples.condiciones.CondicionTramoBean;
import mx.babel.bansefi.banksystem.base.beans.productossimples.condiciones.CondicionValorListaBean;
import mx.babel.bansefi.banksystem.base.beans.productossimples.condiciones.CondicionValorRangoBean;
import mx.babel.bansefi.banksystem.base.beans.productossimples.condiciones.tramos.CabeceraTramoBean;
import mx.babel.bansefi.banksystem.base.beans.productossimples.condiciones.tramos.SubTramoBean;
import mx.babel.bansefi.banksystem.base.constants.ConstantesFuncionales;
import mx.babel.bansefi.banksystem.base.enums.CatalogoEnum;
import mx.babel.bansefi.banksystem.base.enums.EstadosCuentaEnum;
import mx.babel.bansefi.banksystem.base.enums.NavegacionEnum;
import mx.babel.bansefi.banksystem.base.enums.ParametrosFlashEnum;
import mx.babel.bansefi.banksystem.base.utils.CatalogoUtils;
import mx.babel.bansefi.banksystem.base.utils.ManagedBeanStateRecover;
import mx.babel.bansefi.banksystem.cuentas.backends.ConsultaCatalogoValorListaBackend;
import mx.babel.bansefi.banksystem.cuentas.backends.ConsultaComisionTarifaBackend;
import mx.babel.bansefi.banksystem.cuentas.backends.ConsultaConceptoApunteBackend;
import mx.babel.bansefi.banksystem.cuentas.backends.ConsultaCondicionComisionBackend;
import mx.babel.bansefi.banksystem.cuentas.backends.ConsultaCondicionInteresBackend;
import mx.babel.bansefi.banksystem.cuentas.backends.ConsultaCondicionListaBackend;
import mx.babel.bansefi.banksystem.cuentas.backends.ConsultaCondicionRangoBackend;
import mx.babel.bansefi.banksystem.cuentas.backends.ConsultaCondicionTramoBackend;
import mx.babel.bansefi.banksystem.cuentas.backends.ConsultaCondicionValorListaBackend;
import mx.babel.bansefi.banksystem.cuentas.backends.ConsultaCondicionValorRangoBackend;
import mx.babel.bansefi.banksystem.cuentas.backends.ConsultaCondicionesCuentaBackend;
import mx.babel.bansefi.banksystem.cuentas.backends.ConsultaDatosCondicionTarifaBackend;
import mx.babel.bansefi.banksystem.cuentas.backends.ConsultaDetalleGrpPrdVendBackend;
import mx.babel.bansefi.banksystem.cuentas.backends.ConsultaDetalleTramoBackend;
import mx.babel.bansefi.banksystem.cuentas.backends.ConsultaInteresTarifaBackend;
import mx.babel.bansefi.banksystem.cuentas.backends.ConsultaListaBaseCalculoCondicionesBackend;
import mx.babel.bansefi.banksystem.cuentas.backends.ConsultaListaDominioCondicionesBackend;
import mx.babel.bansefi.banksystem.cuentas.backends.ConsultaProductosSimplesPorCuentaBackend;
import mx.babel.bansefi.banksystem.cuentas.backends.ConsultaRangoTarifaBackend;
import mx.babel.bansefi.banksystem.cuentas.backends.ModificaCondicionComisionBackend;
import mx.babel.bansefi.banksystem.cuentas.backends.ModificaCondicionInteresBackend;
import mx.babel.bansefi.banksystem.cuentas.backends.ModificaCondicionListaBackend;
import mx.babel.bansefi.banksystem.cuentas.backends.ModificaCondicionRangoBackend;
import mx.babel.bansefi.banksystem.cuentas.backends.ModificaCondicionValorListaBackend;
import mx.babel.bansefi.banksystem.cuentas.backends.ModificaCondicionValorRangoBackend;
import mx.babel.bansefi.banksystem.cuentas.beans.BaseCalculoCondicionesBean;
import mx.babel.bansefi.banksystem.cuentas.beans.ConceptoApunteBean;
import mx.babel.bansefi.banksystem.cuentas.beans.DatosCondicionTarifaBean;
import mx.babel.bansefi.banksystem.cuentas.beans.DominioCondicionBean;
import mx.babel.bansefi.banksystem.cuentas.beans.TarifaBean;
import mx.babel.bansefi.banksystem.cuentas.utils.TarifasUtils;

import org.apache.commons.lang3.SerializationUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.component.accordionpanel.AccordionPanel;
import org.primefaces.context.RequestContext;
import org.primefaces.event.TabChangeEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@ManagedBean(name = "modificacionCondicionesController")
@Component
@Scope("view")
public class ModificacionCondicionesController extends StorageMgrBean implements Serializable{

	private static final long serialVersionUID = -2303751709668796838L;
    private static final Logger LOGGER = LogManager.getLogger(ModificacionCondicionesController.class
            .getName());
    private static final String CONDICIONES_MAPA = "condicionesMapa";
    private static final String TARIFA_ORIGINAL = "tarifaOriginal";
	/**
	 * Referencias Comunes
	 */
	private ClienteBean cliente;
	private CuentaBean cuentaBean;
    private String flujoContinuar;
    private Integer estadoAlta = 0;
    private Boolean nivelarCuenta;

	//Bean que indica la tarifa seleccionada en el paso 1
	//Viaja al paso 2 y al paso 3
	private TarifaBean tarifaSeleccionada;
	private TarifaBean tarifaSeleccionadaOriginal;

    /**
     * Referencias Paso 3
     */
	//Mapa con las condiciones cargadas que permiten
	//comprobar si han sido modificadas en front
    private Map<String, CondicionBean> initialConditionMap;
	//Consultas para el detalle de condiciones que se muestran al
	//desplegar los tabs contenidos en los productos simples
	@Autowired
	ConsultaCondicionComisionBackend consultaCondicionComisionBackend;
    @Autowired
    ConsultaCondicionInteresBackend consultaCondicionInteresBackend;
    @Autowired
    ConsultaCondicionListaBackend consultaCondicionListaBackend;
    @Autowired
    ConsultaCondicionRangoBackend consultaCondicionRangoBackend;
    @Autowired
    ConsultaCondicionTramoBackend consultaCondicionTramoBackend;
    @Autowired
    ConsultaCondicionValorListaBackend consultaCondicionValorListaBackend;
    @Autowired
    ConsultaCondicionValorRangoBackend consultaCondicionValorRangoBackend;
    @Autowired
    ConsultaDetalleTramoBackend consultaDetalleTramoBackend;

    //Llamadas que modifican las condiciones que se han modificado
    //en el paso 3 antes de avanzar a otro paso
    @Autowired
    ModificaCondicionComisionBackend modificaCondicionComisionBackend;
    @Autowired
    ModificaCondicionInteresBackend modificaCondicionInteresBackend;
    @Autowired
    ModificaCondicionValorListaBackend modificaCondicionValorListaBackend;
    @Autowired
    ModificaCondicionValorRangoBackend modificaCondicionValorRangoBackend;
    @Autowired
    ModificaCondicionListaBackend modificaCondicionListaBackend;
    @Autowired
    ModificaCondicionRangoBackend modificaCondicionRangoBackend;

    //Consulta una lista de valores asociada a una condicion
    //indicando ids y cual es la opcion preferente
    @Autowired
    ConsultaCatalogoValorListaBackend consultaCatalogoValorListaBackend;
    //Consulta una lista de valores asociada a una condicion
    //para obtener descripciones
    @Autowired
    ConsultaListaDominioCondicionesBackend consultaListaDominioCondicionesBackend;
    //Consulta la descripcion para la base de calculo que debe mostrarse
    //en algunas condiciones
    @Autowired
    ConsultaListaBaseCalculoCondicionesBackend consultaListaBaseCalculoCondicionesBackend;


    @Autowired
    ConsultaDetalleGrpPrdVendBackend consultaDetalleGrpPrdVendBackend;



  //Consulta los productos simples y los ids de las condiciones de la
    //cuenta contratada en el paso 2
    @Autowired
    ConsultaProductosSimplesPorCuentaBackend consultaProductosSimplesPorCuentaBackend;


    @Autowired
    ConsultaRangoTarifaBackend consultaRangoTarifaBackend;
    @Autowired
    ConsultaComisionTarifaBackend consultaComisionTarifaBackend;
    @Autowired
    ConsultaInteresTarifaBackend consultaInteresTarifaBackend;
    @Autowired
    ConsultaDatosCondicionTarifaBackend consultaDatosCondicionTarifaBackend;

    @Autowired
    ContextoUtils contexto;

    @Autowired
    CatalogoUtils catalogoUtils;

    @Autowired
    TarifasUtils tarifasUtils;

    @Autowired
    ManagedBeanStateRecover managedBeanStateRecover;

    @Autowired
    ConsultaCondicionesCuentaBackend consultaCondicionesCuentaBackend;
    
    @Autowired
	ConsultaCuentaBackend consultaCuentaBackend;

    private boolean origenModificacion;
    private boolean modificacion;
    /**
     * Enum para definir a donde se redireccionará al seleccionar un objeto de
     * la tabla de resultados.
     */
    private NavegacionEnum destino;
    private Object destinoController;

    /**
   	 * Variable para saber si el alta es un subflujo
   	 */
   	private boolean subflujo;

   	/**
   	 * Variable para identificar un alta de medios de pago
   	 */
   	private Boolean altaSat = false;

   	/**
   	 * Variables para el filtrado de productos simples
   	 */
    private String idPdsFiltrado;
    private List <SelectItem> pdsList;


    @Autowired
    ConsultaConceptoApunteBackend consultaConceptoApunteBackend;

///// Logica Paso 3

    public void iniciaPaso3() {
        super.restoreflash();

        final Flash flash = obtieneFlash();

        if(obtieneFlash().get(ParametrosFlashEnum.SUBFLUJO_ALTA_CUENTA.getParamFlash()) != null){
			this.subflujo = (Boolean) obtieneFlash().get(ParametrosFlashEnum.SUBFLUJO_ALTA_CUENTA.getParamFlash());
			this.destino = managedBeanStateRecover.getDestino();
			this.destinoController = managedBeanStateRecover.getController();
		}else{
			this.subflujo = false;
			if(flash.get(ParametrosFlashEnum.NAVEGACION_MANAGED_BEAN.getParamFlash())!=null){
	            destino = managedBeanStateRecover.getDestino();
	            destinoController = managedBeanStateRecover.getController();
	        }
		}
        //TODO ver que hacer con este cuentaBean que solo trae el numero de cuenta
        //cuando venimos del alta de cuenta
        this.cuentaBean = ((CuentaBean)obtieneFlash().get(ParametrosFlashEnum.CUENTA_BEAN.getParamFlash()));
        if(null!= flash.get(ParametrosFlashEnum.MODIFICACION_CUENTA.getParamFlash())){
            this.origenModificacion = true;
            this.modificacion = (boolean)flash.get(ParametrosFlashEnum.MODIFICACION_CUENTA.getParamFlash());
            this.cliente = tarifasUtils.getClienteFromCuenta(this.cuentaBean);
            this.tarifaSeleccionada = new TarifaBean();
            this.tarifaSeleccionada.setLinea(this.cuentaBean.getCodLinea());
            this.tarifaSeleccionada.setGrupo(this.cuentaBean.getIdGrupoProducto());
            this.tarifaSeleccionada.setProducto(this.cuentaBean.getIdProducto());
            this.tarifaSeleccionada.setId(this.cuentaBean.getIdTarifaProducto());
            this.tarifaSeleccionada = consultaDetalleGrpPrdVendBackend.ejecutarTRN(this.tarifaSeleccionada);
            this.tarifaSeleccionada.setProductosSimples(this.getProductosSimplesPorCuenta());
        }else{
            this.modificacion = true;
            this.loadClienteBeanFromFlash();
            this.loadTarifaSeleccionadaFromFlash();
        }
        this.altaSat = ConstantesFuncionales.isMediosPago(this.tarifaSeleccionada.getLinea(), this.tarifaSeleccionada.getGrupo());
        if(flash.get(ParametrosFlashEnum.NAVEGACION_ALTA_CUENTA.getParamFlash()) != null){
        	estadoAlta = (Integer) flash.get(ParametrosFlashEnum.NAVEGACION_ALTA_CUENTA.getParamFlash());
        }
        if(this.altaSat && estadoAlta == 0){
    		estadoAlta = 1;
    	}
        if(flash.get(ModificacionCondicionesController.CONDICIONES_MAPA) != null){
            initialConditionMap = (Map<String, CondicionBean>)flash.get(ModificacionCondicionesController.CONDICIONES_MAPA);
        }else{
            initialConditionMap = new HashMap<String, CondicionBean>();
        }
        if(flash.get(ModificacionCondicionesController.TARIFA_ORIGINAL) != null){
            this.tarifaSeleccionadaOriginal = (TarifaBean)flash.get(ModificacionCondicionesController.TARIFA_ORIGINAL);
        }else{
            this.tarifaSeleccionadaOriginal = SerializationUtils.clone(this.tarifaSeleccionada);
        }
        this.pdsList = this.buildPdsList();
        if(null!= flash.get(ParametrosFlashEnum.NIVELAR_CUENTA.getParamFlash())){
            this.nivelarCuenta = (Boolean)obtieneFlash().get(ParametrosFlashEnum.NIVELAR_CUENTA.getParamFlash());
        }
    }

    private List<SelectItem> buildPdsList() {
        this.idPdsFiltrado = "TODOS";
        final List<SelectItem> result = new ArrayList<>();
        result.add(new SelectItem(idPdsFiltrado));
        if(this.tarifaSeleccionada.getProductosSimples() != null){
            for(final ProductoSimpleBean psBean: this.tarifaSeleccionada.getProductosSimples()){
                result.add(new SelectItem(psBean.getId(), psBean.getDescripcion()));
            }
        }
        return result;
    }

    private List<ProductoSimpleBean> getProductosSimplesPorCuenta() {
      //TODO cambiar el numero de cuenta por el correcto
        final List<ProductoSimpleBean> resultado = consultaCondicionesCuentaBackend.EjecutarTRN(this.cuentaBean);
        return tarifasUtils.fillPdsCondDescriptions(resultado, this.tarifaSeleccionada);
    }

    private void modificaCondicion(final Long numCuenta, final String idPds, final CondicionBean condicion) {
        if(condicion instanceof CondicionComisionBean){
            this.modificaCondicionComisionBackend.ejecutarTRN(numCuenta,idPds,(CondicionComisionBean)condicion);
        }else if(condicion instanceof CondicionInteresBean){
            this.modificaCondicionInteresBackend.ejecutarTRN(numCuenta,idPds,(CondicionInteresBean)condicion);
        }else if(condicion instanceof CondicionValorListaBean){
            this.modificaCondicionValorListaBackend.ejecutarTRN(numCuenta,idPds,(CondicionValorListaBean)condicion);
        }else if(condicion instanceof CondicionValorRangoBean){
            this.modificaCondicionValorRangoBackend.ejecutarTRN(numCuenta,idPds,(CondicionValorRangoBean)condicion);
        }else if(condicion instanceof CondicionListaBean){
            this.modificaCondicionListaBackend.ejecutarTRN(numCuenta,idPds,(CondicionListaBean)condicion);
        }else if(condicion instanceof CondicionRangoBean){
            this.modificaCondicionRangoBackend.ejecutarTRN(numCuenta,idPds,(CondicionRangoBean)condicion);
        }
    }


    public void cargarCondicion(final TabChangeEvent event){

        if(! (event.getSource() instanceof AccordionPanel) || ! (event.getData() instanceof CondicionBean)){
            throw new NoControlableException("Ha ocurrido un error al cargar las condiciones",
                    this.getClass().getName()+".cargarCondicion(): El evento cargado no pertenece a un AccordionPanel");
        }
        final String pdsId = ((AccordionPanel)event.getSource()).getWidgetVar().substring(5);
        final String condicionId = ((CondicionBean)event.getData()).getClave();


        final List<CondicionBean> condicionList = this.getConditionOfSimpleProduct(pdsId);
        if(!condicionList.isEmpty()){
            for(int i = 0; i<condicionList.size();i++){
                if(StringUtils.equals(condicionList.get(i).getClave(), condicionId)){
                    if(!condicionList.get(i).isLoaded()){
                        final CondicionBean condicionCargada = this.loadCondition(pdsId, condicionList.get(i));
                        condicionList.set(i,condicionCargada);
                        initialConditionMap.put(pdsId+"-"+condicionId, SerializationUtils.clone(condicionCargada));
                    }
                    break;
                }
            }
        }
    }

    /**
     * @param pdsId
     * @return
     */
    private List<CondicionBean> getConditionOfSimpleProduct(final String pdsId) {
        final List<ProductoSimpleBean> pdsList = this.tarifaSeleccionada.getProductosSimples();
        List <CondicionBean> condicionList = null;
        for(final ProductoSimpleBean producto : pdsList){
            if(StringUtils.equals(producto.getId(), pdsId)){
                condicionList = producto.getCondiciones();
                break;
            }
        }
        if(condicionList==null){
            throw new NoControlableException("Producto Simple no encontrado",
                    this.getClass().getName()+": "+pdsId+" No encontrado en el listado de productos simples");
        }
        return condicionList;
    }

    private CondicionBean loadCondition(final String pdsId, final CondicionBean condicion) {
        CondicionBean resultado = null;
        if(condicion instanceof CondicionComisionBean){
            resultado = this.getCondicionComision(pdsId, condicion.getClave(), condicion.getIdCcps());
        }else if(condicion instanceof CondicionInteresBean){
            resultado = getCondicionInteres(pdsId, condicion.getClave(), condicion.getIdCcps());

        }else if(condicion instanceof CondicionListaBean){
        //Carga Done
            resultado = getCondicionLista(pdsId, condicion);
        }else if(condicion instanceof CondicionRangoBean){
        //Carga Done
            resultado = consultaCondicionRangoBackend.ejecutarTRN(this.cuentaBean.getNumeroCuenta(),
                    pdsId, condicion.getClave());
        }else if(condicion instanceof CondicionTramoBean){
            resultado = loadCondicionTramoBean(pdsId, condicion);
        }else if(condicion instanceof CondicionValorListaBean){
        //Carga Done
            resultado = getCondicionValorLista(pdsId, condicion);
        }else if(condicion instanceof CondicionValorRangoBean){
        //Carga Done
            resultado = this.getCondicionValorRango(pdsId, condicion.getClave(), condicion.getIdCcps());
        }else {
            throw new NoControlableException("Ha ocurrido un error:",
                    "Tipo de condicion desconocida.");
        }
        if(resultado == null){
            return condicion;
        }
        resultado.setLoaded(true);
        resultado.setClave(condicion.getClave());
        resultado.setDescripcion(condicion.getDescripcion());
        resultado.setEstado(condicion.getEstado());
        resultado.setFechaInicioValidez(condicion.getFechaInicioValidez());
        return resultado;
    }

    /**
     * @param pdsId
     * @param condicion
     * @return
     */
    private CondicionBean getCondicionLista(final String pdsId, final CondicionBean condicion) {
        final CondicionBean resultado = consultaCondicionListaBackend.ejecutarTRN(this.cuentaBean.getNumeroCuenta(),
                pdsId, condicion.getClave());
        final List<PreferenceItemBean> listItems = new ArrayList<PreferenceItemBean>();
        final List<DominioCondicionBean> listaDominionCondiciones =
                consultaListaDominioCondicionesBackend.ejecutarTRN(condicion.getClave());
        final List<PreferenceItemBean> itemsToBeShown = consultaCatalogoValorListaBackend.ejecutarTRN(pdsId, condicion.getIdCcps(), condicion.getClave());
        if(null!=listaDominionCondiciones && null != itemsToBeShown){
            for(final PreferenceItemBean itemTBS : itemsToBeShown){
                for(final DominioCondicionBean dcb:listaDominionCondiciones){
                    if(StringUtils.equals(itemTBS.getId(), dcb.getCodDomParmCd())){
                        final PreferenceItemBean ssi = new PreferenceItemBean();
                        ssi.setId(dcb.getCodDomParmCd());
                        ssi.setDesc(dcb.getDescDomParmCd());
                        listItems.add(ssi);
                    }
                }
            }
        }

        ((CondicionListaBean)resultado).setListaNoSeleccionados(listItems);
        return resultado;
    }

    /**
     * @param pdsId
     * @param condicion
     * @return
     */
    private CondicionBean getCondicionValorLista(final String pdsId, final CondicionBean condicion) {
        final CondicionBean resultado = consultaCondicionValorListaBackend.ejecutarTRN(this.cuentaBean.getNumeroCuenta(),
                pdsId, condicion.getClave());

        final List<PreferenceItemBean> listItems = new ArrayList<PreferenceItemBean>();
        final List<DominioCondicionBean> listaDominionCondiciones =
                consultaListaDominioCondicionesBackend.ejecutarTRN(condicion.getClave());
        final List<PreferenceItemBean> itemsToBeShown = consultaCatalogoValorListaBackend.ejecutarTRN(pdsId, condicion.getIdCcps(), condicion.getClave());
        if(null!=listaDominionCondiciones && null != itemsToBeShown){
            for(final PreferenceItemBean itemTBS : itemsToBeShown){
                for(final DominioCondicionBean dcb:listaDominionCondiciones){
                    if(StringUtils.equals(itemTBS.getId(), dcb.getCodDomParmCd())){
                        final PreferenceItemBean ssi = new PreferenceItemBean();
                        ssi.setId(dcb.getCodDomParmCd());
                        ssi.setDesc(dcb.getDescDomParmCd());
                        if(itemTBS.isPreferente()){
                            ssi.setPreferente(true);
                        }
                        listItems.add(ssi);
                    }
                }
            }
        }
        ((CondicionValorListaBean)resultado).setItems(listItems);
        return resultado;
    }

    /**
     * @param pdsId
     * @param condicion
     * @return
     */
    private CondicionInteresBean getCondicionInteres(final String pdsId, final String idParmcd, final String idCcps) {
        final CondicionInteresBean resultado = consultaCondicionInteresBackend.ejecutarTRN(this.cuentaBean.getNumeroCuenta(),
                pdsId, idParmcd);
        resultado.setBaseCalculoDesc(this.getBaseCalculoDesc(pdsId, idParmcd,
                resultado.getEstructuraIdPds(),
                resultado.getEstructuraParmCd()));
        if(resultado.getTipo().equalsIgnoreCase("F")){
            final DatosCondicionTarifaBean datosCondicionTarifa = consultaDatosCondicionTarifaBackend.ejecutarTRN(pdsId,
                    idParmcd, idCcps, this.tarifaSeleccionada);
            final CondicionInteresBean interesTarifa = consultaInteresTarifaBackend.ejecutarTRN(datosCondicionTarifa);
            resultado.setIntFijoIncremento(interesTarifa.getIntFijoIncremento());
            resultado.setIntFijoPreferente(interesTarifa.getIntFijoPreferente());
            resultado.setIntFijoMax(interesTarifa.getIntFijoMax());
            resultado.setIntFijoMin(interesTarifa.getIntFijoMin());
        }
        return resultado;
    }

    /**
     * @param pdsId
     * @param condicion
     * @return
     */
    private CondicionComisionBean getCondicionComision(final String pdsId, final String idParmcd, final String idCcps) {
        final CondicionComisionBean resultado = consultaCondicionComisionBackend.ejecutarTRN(this.cuentaBean.getNumeroCuenta(),
                pdsId, idParmcd);
        if(!(resultado.getTipo().equalsIgnoreCase("F"))){
            resultado.
               setBaseCalculoComVarDesc(this.getBaseCalculoDesc(pdsId, idParmcd,
                       resultado.getEstructuraIdPds(),resultado.getEstructuraParmCd()));
        }else{
            final DatosCondicionTarifaBean datosCondicionTarifa = consultaDatosCondicionTarifaBackend.ejecutarTRN(pdsId,
                    idParmcd, idCcps, this.tarifaSeleccionada);
            final ComisionTarifaBean comisionTarifa = consultaComisionTarifaBackend.ejecutarTRN(datosCondicionTarifa);
            resultado.setImpFijoMin(comisionTarifa.getImpFijoMin());
            resultado.setImpFijoMax(comisionTarifa.getImpFijoMax());
            resultado.setImpFijoPreferente(comisionTarifa.getImpFijoPreferente());
            resultado.setImpFijoIncremento(comisionTarifa.getImpFijoIncremento());
        }
        return resultado;
    }

    /**
     * @param pdsId
     * @param condicion
     * @return
     */
    private CondicionValorRangoBean getCondicionValorRango(final String pdsId, final String idParmcd, final String idCcps) {
        final CondicionValorRangoBean resultado = consultaCondicionValorRangoBackend.ejecutarTRN(this.cuentaBean.getNumeroCuenta(),
                pdsId, idParmcd);
        final DatosCondicionTarifaBean datosCondicion = consultaDatosCondicionTarifaBackend.ejecutarTRN(pdsId,
                idParmcd, idCcps, this.tarifaSeleccionada);

        final CondicionRangoBean condicionRangoBean = consultaRangoTarifaBackend.ejecutarTRN(datosCondicion);
        resultado.setMinimo(condicionRangoBean.getMinimo());
        resultado.setMaximo(condicionRangoBean.getMaximo());
        resultado.setIncremento(condicionRangoBean.getIncremento());
        resultado.setPreferente(condicionRangoBean.getPreferente());

        final ConceptoApunteBean caBean = consultaConceptoApunteBackend.ejecutarTRN(idParmcd);
        if(StringUtils.equalsIgnoreCase(ConstantesFuncionales.CONCEPTO_APUNTE_MXN, caBean.getUnidades())){
            resultado.setAlternateUnits(true);
        }
        return resultado;
    }


    /**
     * @param pdsId
     * @param condicion
     * @return
     */
    private CondicionTramoBean loadCondicionTramoBean(final String pdsId,
            final CondicionBean condicion) {
        final CondicionTramoBean resultado = consultaCondicionTramoBackend.ejecutarTRN(this.cuentaBean.getNumeroCuenta(),
                pdsId, condicion.getClave());
        resultado.setBaseCalculoDesc(this.getBaseCalculoDesc(pdsId, condicion.getClave(),
                resultado.getIdPds(),
                resultado.getIdParamcd()));

        final CondicionTramoBean tempTramo = consultaDetalleTramoBackend
                .ejecutarTRN(this.cuentaBean.getNumeroCuenta(), pdsId, condicion.getClave(),
                        resultado.getIdTrameado(), condicion.getFechaInicioValidez(), null);
        resultado.setMasDatos(tempTramo.isMasDatos());
        resultado.setUltimoDatoConsultaAnterior(tempTramo.getSubtramoList().get(tempTramo.getSubtramoList().size()-1).getNumTramo());
        resultado.setSubtramoList(tempTramo.getSubtramoList());
        resultado.setCabeceraList(tempTramo.getCabeceraList());
        if(null!= resultado.getCabeceraList()){
            for(final CabeceraTramoBean cabecera : resultado.getCabeceraList()){
                cabecera.setDescripcion(this.getBaseCalculoDesc(pdsId, condicion.getClave(),
                        cabecera.getIdPds(),
                        cabecera.getIdParamCd()));
                if(null== cabecera.getUdMedidas()){
                    this.loadCondicionTramoColumnas(cabecera.getIdParamCd(), cabecera.getPosCol(), resultado.getSubtramoList());
                }else if(!cabecera.getUdMedidas().equalsIgnoreCase("U")){
                        cabecera.setDescripcion(cabecera.getDescripcion()
                           + " ("+catalogoUtils.getCatalogoDesc(CatalogoEnum.TP_UM, cabecera.getUdMedidas())+")");
                }
            }

        }
        if(null!=resultado.getSubtramoList()){
            final List<String> listIdParmCd = new ArrayList<>();
            for(final SubTramoBean stBean : resultado.getSubtramoList()){
                if(null!= stBean.getCondicionBean() && (stBean.getCondicionBean() instanceof CondicionValorListaBean
                        || stBean.getCondicionBean() instanceof CondicionListaBean)){
                    listIdParmCd.add(stBean.getCondicionBean().getClave());
                }
            }
            if(!listIdParmCd.isEmpty()){
                final List<DominioCondicionBean> lista = new ArrayList<>();
                for(final String idParmCd:listIdParmCd){
                    lista.addAll(consultaListaDominioCondicionesBackend.ejecutarTRN(idParmCd));
                }
                if(!lista.isEmpty()){
                    for(final SubTramoBean stBean : resultado.getSubtramoList()){
                        for(final DominioCondicionBean dcBean : lista){
                            if(StringUtils.isNoneBlank(stBean.getValue(), dcBean.getCodDomParmCd())
                                    && StringUtils.equals(stBean.getValue(), dcBean.getCodDomParmCd())){
                                stBean.setValue(dcBean.getDescDomParmCd());
                                if(stBean.getCondicionBean() instanceof CondicionValorListaBean){
                                    final PreferenceItemBean piBean = new PreferenceItemBean();
                                    piBean.setId(dcBean.getCodDomParmCd());
                                    piBean.setDesc(dcBean.getDescDomParmCd());
                                    final List<PreferenceItemBean> listaPIBean = new ArrayList<>(1);
                                    listaPIBean.add(piBean);
                                    ((CondicionValorListaBean)stBean.getCondicionBean()).setItems(listaPIBean);
                                }
                                break;
                            }
                        }
                        if(stBean.getCondicionBean() instanceof CondicionListaBean){
                            if(null!=((CondicionListaBean)stBean.getCondicionBean()).getLista()){
                                for(final PreferenceItemBean piBean:((CondicionListaBean)stBean.getCondicionBean()).getLista()){
                                    for(final DominioCondicionBean dcBean : lista){
                                        if(StringUtils.isNoneBlank(piBean.getId(), dcBean.getCodDomParmCd())
                                                && StringUtils.equals(piBean.getId(), dcBean.getCodDomParmCd())){
                                            piBean.setDesc(dcBean.getDescDomParmCd());
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return resultado;
    }

    private void loadCondicionTramoColumnas(final String parmCd, final Integer posCol,
            final List<SubTramoBean> subtramoList) {
        final List<DominioCondicionBean> lista = consultaListaDominioCondicionesBackend.ejecutarTRN(parmCd);
        if(null!= subtramoList && lista != null){
            for(final SubTramoBean stBean : subtramoList){
                final String codParmCdEstr = stBean.getLimiteTramo().getCodDomParmCd(posCol);
                for(final DominioCondicionBean dcBean : lista){
                    if(StringUtils.isNoneBlank(codParmCdEstr, dcBean.getCodDomParmCd())
                            && StringUtils.equals(codParmCdEstr, dcBean.getCodDomParmCd())){
                        stBean.getLimiteTramo().setDescDomParmCd(posCol, dcBean.getDescDomParmCd());
                        break;
                    }
                }
            }
        }

    }

    private String getBaseCalculoDesc(final String idPds, final String idParmcd, final String estructuraIdPds, final String estructuraIdParmcd) {
        final List<BaseCalculoCondicionesBean> respuesta = this.consultaListaBaseCalculoCondicionesBackend.ejecutarTRN(idPds, idParmcd);
        for(final BaseCalculoCondicionesBean datos : respuesta){
            if(StringUtils.isNoneBlank(datos.getIdPds(), datos.getParmCd(), datos.getNomParmCd())
               && StringUtils.equals(datos.getIdPds(), estructuraIdPds)
               && StringUtils.equals(datos.getParmCd(), estructuraIdParmcd)){
                return datos.getNomParmCd();
            }
        }
        return null;
    }


    public void guardar() {
        //TODO a donde vamos al pulsar guardar
        this.persistirCondicionesModificadas();
        RequestContext.getCurrentInstance().execute(
                "PF('dlgSuccess').show()");

        //TODO faltan verificaciones de si son necesarias las relaciones
    }


    @StoreStep
    public String irARelacionarPersona() {
        this.flujoContinuar = NavegacionEnum.RELACIONES_CUENTA_PERSONAS.getRuta();
        if(this.hayCondicionesModificadas()){
            RequestContext.getCurrentInstance().execute(
                    "PF('dlgNoGuardar').show()");
            return null;
        }
        return this.irAFlujo();
    }

    @StoreStep
    public String irARelacionarAcuerdo() {
        this.flujoContinuar = NavegacionEnum.RELACIONES_CUENTA_CUENTAS.getRuta();
        if(this.hayCondicionesModificadas()){
            RequestContext.getCurrentInstance().execute(
                    "PF('dlgNoGuardar').show()");
            return null;
        }
        return this.irAFlujo();
    }

    @StoreStep
    public String irAEmisionDocumentos() {
        this.flujoContinuar = NavegacionEnum.EMITIR_DOCUMENTOS.getRuta();
        if(this.hayCondicionesModificadas()){
            RequestContext.getCurrentInstance().execute(
                    "PF('dlgNoGuardar').show()");
            return null;
        }
        return this.irAFlujo();
    }

    @StoreStep
    public String irRelacionPan() {
        this.flujoContinuar = NavegacionEnum.RELACIONES_CUENTA_PAN.getRuta();
        if(this.hayCondicionesModificadas()){
            RequestContext.getCurrentInstance().execute(
                    "PF('dlgNoGuardar').show()");
            return null;
        }
        return this.irAFlujo();
    }

    @StoreStep
    public String irAFlujo(){
        if(!this.origenModificacion){
            this.setClienteBeanInFlash();
            this.setCuentaBeanInFlash();
            this.setTarifaSeleccionadaInFlash();
            this.setNavegacionAltaCuentaInFlash();
            this.parametrosSubFlujo();
            obtieneFlash().put(ParametrosFlashEnum.NIVELAR_CUENTA.getParamFlash(), this.nivelarCuenta);
        }
        if(subflujo){
        	return this.flujoContinuar;
        }
        return resolverDirHome(this.flujoContinuar);
    }

    private boolean hayCondicionesModificadas(){
        if(null!=this.initialConditionMap
            &&!this.initialConditionMap.isEmpty()){
            for(final ProductoSimpleBean bean : this.tarifaSeleccionada.getProductosSimples()){
                if(null != bean && null != bean.getCondiciones()){
                    for(final CondicionBean condicion : bean.getCondiciones()){
                        final String key = bean.getId()+"-"+condicion.getClave();
                        if(this.initialConditionMap.containsKey(key)
                           && !condicion.equals(this.initialConditionMap.get(key))){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    private void persistirCondicionesModificadas() {
        if(null!=this.initialConditionMap
                && !this.initialConditionMap.isEmpty()){
            for(final ProductoSimpleBean bean : this.tarifaSeleccionada.getProductosSimples()){
                if(null != bean && null != bean.getCondiciones()){
                    for(final CondicionBean condicion : bean.getCondiciones()){
                        final String key = bean.getId()+"-"+condicion.getClave();
                        if(this.initialConditionMap.containsKey(key)
                           && !condicion.equals(this.initialConditionMap.get(key))){
                            this.modificaCondicion(this.getCuentaBean().getNumeroCuenta(), bean.getId(), condicion);
                            //Actualizamos el mapa de condiciones iniciales
                            this.initialConditionMap.put(key, SerializationUtils.clone(condicion));
                        }
                    }
                }
            }
        }
    }

///// Fin logica Paso 3

    private void loadClienteBeanFromFlash(){
        final Flash flash = obtieneFlash();
        if (flash.containsKey(ParametrosFlashEnum.CLIENTE.getParamFlash())) {
            cliente = (ClienteBean)flash.get(ParametrosFlashEnum.CLIENTE.getParamFlash());

        }else{
            throw new NoControlableException("Ha ocurrido un error:",
                    "Cliente no disponible");
        }
    }

    private void loadTarifaSeleccionadaFromFlash(){
        if (obtieneFlash().containsKey(ParametrosFlashEnum.TARIFA_SELECCIONADA.getParamFlash())) {
            this.tarifaSeleccionada = (TarifaBean) obtieneFlash()
                    .get(ParametrosFlashEnum.TARIFA_SELECCIONADA.getParamFlash());
        }else{
            throw new NoControlableException("Ha ocurrido un error:",
                "Tarifa seleccionada no disponible");
        }
    }

    private void setClienteBeanInFlash(){
        obtieneFlash().put(ParametrosFlashEnum.CLIENTE.getParamFlash(), cliente);
    }

    private void setCuentaBeanInFlash(){
        obtieneFlash().put(ParametrosFlashEnum.CUENTA_BEAN.getParamFlash(), this.cuentaBean);
    }

    private void setTarifaSeleccionadaInFlash(){
        obtieneFlash().put(ParametrosFlashEnum.TARIFA_SELECCIONADA.getParamFlash(), this.tarifaSeleccionadaOriginal);
    }

    private void setNavegacionAltaCuentaInFlash(){
    	obtieneFlash().put(ParametrosFlashEnum.NAVEGACION_ALTA_CUENTA.getParamFlash(), this.estadoAlta);
    }

    private void parametrosSubFlujo(){
    	this.obtieneFlash().put(ParametrosFlashEnum.CONTROLLER_DESTINO.getParamFlash(), destinoController);
		this.obtieneFlash().put(ParametrosFlashEnum.NAVEGACION_BUSQUEDA.getParamFlash(),destino);
		this.obtieneFlash().put(ParametrosFlashEnum.SUBFLUJO_ALTA_CUENTA.getParamFlash(),subflujo);
		if(subflujo){
			this.obtieneFlash().put(ParametrosFlashEnum.NAVEGACION_MANAGED_BEAN.getParamFlash(),true);
		}
	}
    /**
     * @return Metodo utilizado para recuperar una instancia de Flash
     */
    public Flash obtieneFlash() {
        return FacesContext.getCurrentInstance().getExternalContext()
                .getFlash();
    }

	@Override
	protected Map<String, Object> getBeanList() {
	    final Map<String, Object> beanList = new HashMap<String, Object>();
        beanList.put(ClienteBean.class.getName(), cliente);
        beanList.put(CuentaBean.class.getName(), cuentaBean);
        beanList.put(TarifaBean.class.getName(), tarifaSeleccionada);
        beanList.put(ModificacionCondicionesController.CONDICIONES_MAPA, initialConditionMap);
        beanList.put(ModificacionCondicionesController.TARIFA_ORIGINAL, tarifaSeleccionadaOriginal);
        beanList.put(ParametrosFlashEnum.NIVELAR_CUENTA.getParamFlash(), nivelarCuenta);
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
//            	verificaEstadoCuenta(cuentaB);
                flash.put(ParametrosFlashEnum.CUENTA_BEAN.getParamFlash(), cuentaB);
            }
            final TarifaBean tarifaB = (TarifaBean) beanList.get(TarifaBean.class.getName());
            if(null != tarifaB){
                flash.put(ParametrosFlashEnum.TARIFA_SELECCIONADA.getParamFlash(), tarifaB);
            }
            final Boolean nivelarAcc = (Boolean)beanList.get(ParametrosFlashEnum.NIVELAR_CUENTA.getParamFlash());
            if(null != nivelarAcc){
                flash.put(ParametrosFlashEnum.NIVELAR_CUENTA.getParamFlash(), nivelarAcc);
            }
            final Map<String, CondicionBean> conditionMap =
                    (Map<String, CondicionBean>)beanList.get(ModificacionCondicionesController.CONDICIONES_MAPA);
            if(conditionMap != null){
                flash.put(ModificacionCondicionesController.CONDICIONES_MAPA, conditionMap);
            }
            final TarifaBean tarifaOriginal = (TarifaBean)beanList.get(ModificacionCondicionesController.TARIFA_ORIGINAL);
            if(tarifaOriginal != null){
                flash.put(ModificacionCondicionesController.TARIFA_ORIGINAL, tarifaOriginal);
            }
        }
	}

	@Override
	protected String getNombreFlujo() {
	    return "Alta de Cuenta: " + this.tarifaSeleccionada.getNombre() + " para "+this.cliente.getNombreCompleto();
	}
	
	public void verificaEstadoCuenta(CuentaBean cuentaB){
		CuentaBean cuenta = consultaCuentaBackend.ejecutarTRN(cuentaB.getNumeroCuenta());
		if(!EstadosCuentaEnum.SOLICITADO.getEstado().equals(cuenta.getEstado())){
			cuentaB.setEstado(cuenta.getEstado());
			obtieneFlash().put(ParametrosFlashEnum.MODIFICACION_CUENTA.getParamFlash(), false);
		}
	}

    public String irAHome() {
        this.flujoContinuar = irAHomeDirecto();
        if(this.hayCondicionesModificadas()){
            RequestContext.getCurrentInstance().execute(
                    "PF('dlgNoGuardar').show()");
            return null;
        }
        return this.irAFlujo();
    }

    public String irAHomeDirecto() {
        return resolverDirHome(NavegacionEnum.INICIO.getRuta());
    }

    private String resolverDirHome(final String redireccionOriginal){
        String ruta = "";
        if (destino == null) {
            ruta = redireccionOriginal;
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
	 * @return Atributo estadoAlta
	 */
	public Integer getEstadoAlta() {
		return estadoAlta;
	}



	/**
	 * @param estadoAlta Atributo estadoAlta a definir
	 */
	public void setEstadoAlta(final Integer estadoAlta) {
		this.estadoAlta = estadoAlta;
	}



	public void masDatosTramos(final String pdsId, final String paramCd){
        final List<CondicionBean> condicionList = this.getConditionOfSimpleProduct(pdsId);
        if(!condicionList.isEmpty()){
            for(int i = 0; i<condicionList.size();i++){
                if(StringUtils.equals(condicionList.get(i).getClave(), paramCd)){
                    final CondicionTramoBean condicionAPaginar = (CondicionTramoBean)condicionList.get(i);
                    if(condicionAPaginar.cargarMasDatos()){
                        final CondicionTramoBean condicionCargada = consultaDetalleTramoBackend
                                .ejecutarTRN(this.cuentaBean.getNumeroCuenta(), pdsId, condicionAPaginar.getClave(),
                                        condicionAPaginar.getIdTrameado(), condicionAPaginar.getFechaInicioValidez(),
                                        condicionAPaginar.getUltimoDatoConsultaAnterior());
                        condicionAPaginar.setMasDatos(condicionCargada.isMasDatos());
                        condicionAPaginar.setUltimoDatoConsultaAnterior(condicionCargada.getSubtramoList()
                                .get(condicionCargada.getSubtramoList().size()-1).getNumTramo());

                        condicionAPaginar.getSubtramoList().addAll(condicionCargada.getSubtramoList()
                                .subList(1, condicionCargada.getSubtramoList().size()));
                        if(null!= condicionAPaginar.getCabeceraList()){
                            for(final CabeceraTramoBean cabecera : condicionAPaginar.getCabeceraList()){
                                if(null== cabecera.getUdMedidas()){
                                    this.loadCondicionTramoColumnas(cabecera.getIdParamCd(),
                                            cabecera.getPosCol(), condicionAPaginar.getSubtramoList());
                                }
                            }
                        }
                        condicionList.set(i,condicionAPaginar);
                        initialConditionMap.put(pdsId+"-"+paramCd, SerializationUtils.clone(condicionAPaginar));
                    }else{
                        ((CondicionTramoBean)initialConditionMap.get(pdsId+"-"+paramCd)).avanzarIndice();
                    }
                    condicionAPaginar.avanzarIndice();
                    break;
                }
            }
        }
    }

    /**
     * @return the tarifaSeleccionadaOriginal
     */
    public TarifaBean getTarifaSeleccionadaOriginal() {
        return tarifaSeleccionadaOriginal;
    }

    /**
     * @param tarifaSeleccionadaOriginal the tarifaSeleccionadaOriginal to set
     */
    public void setTarifaSeleccionadaOriginal(final TarifaBean tarifaSeleccionadaOriginal) {
        this.tarifaSeleccionadaOriginal = tarifaSeleccionadaOriginal;
    }

    /**
     * @return the origenModificacion
     */
    public boolean isOrigenModificacion() {
        return origenModificacion;
    }

    /**
     * @param origenModificacion the origenModificacion to set
     */
    public void setOrigenModificacion(final boolean origenModificacion) {
        this.origenModificacion = origenModificacion;
    }




    /**
     * @return the modificacion
     */
    public boolean isModificacion() {
        return modificacion;
    }




    /**
     * @param modificacion the modificacion to set
     */
    public void setModificacion(final boolean modificacion) {
        this.modificacion = modificacion;
    }



	/**
	 * @return Atributo altaSat
	 */
	public Boolean getAltaSat() {
		return altaSat;
	}



	/**
	 * @param altaSat Atributo altaSat a definir
	 */
	public void setAltaSat(final Boolean altaSat) {
		this.altaSat = altaSat;
	}

    /**
     * @return the idPdsFiltrado
     */
    public String getIdPdsFiltrado() {
        return idPdsFiltrado;
    }

    /**
     * @param idPdsFiltrado the idPdsFiltrado to set
     */
    public void setIdPdsFiltrado(final String idPdsFiltrado) {
        this.idPdsFiltrado = idPdsFiltrado;
    }

    /**
     * @param pdsList the pdsList to set
     */
    public void setPdsList(final List<SelectItem> pdsList) {
        this.pdsList = pdsList;
    }

    /**
     * @return the pdsList
     */
    public List<SelectItem> getPdsList() {
        return pdsList;
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
