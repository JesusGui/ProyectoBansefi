package mx.babel.bansefi.banksystem.cuentas.controllers;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.sesion.utils.ContextoUtils;
import mx.babel.arq.storage.annotations.StoreStep;
import mx.babel.arq.storage.beans.StorageMgrBean;
import mx.babel.bansefi.banksystem.base.beans.domain.CuentaBean;
import mx.babel.bansefi.banksystem.base.enums.BusquedaEnum;
import mx.babel.bansefi.banksystem.base.enums.CuentaEnum;
import mx.babel.bansefi.banksystem.base.enums.NavegacionEnum;
import mx.babel.bansefi.banksystem.base.enums.ParametrosFlashEnum;
import mx.babel.bansefi.banksystem.base.utils.CatalogoCentrosLoaderTask;
import mx.babel.bansefi.banksystem.base.utils.ManagedBeanStateRecover;





import mx.babel.bansefi.banksystem.base.utils.PdfUtils;
import mx.babel.bansefi.banksystem.cuentas.backends.ConsultaGastosExternosBackend;
import mx.babel.bansefi.banksystem.cuentas.backends.ConsultaTiposGastosBackend;
import mx.babel.bansefi.banksystem.cuentas.backends.EliminacionGastoExternoBackend;
import mx.babel.bansefi.banksystem.cuentas.backends.ImputarGastoExternoBackend;
import mx.babel.bansefi.banksystem.cuentas.backends.RegistroGastoExternoBackend;
import mx.babel.bansefi.banksystem.cuentas.beans.GastoBean;
import mx.babel.bansefi.banksystem.cuentas.beans.GastosExternosBean;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.lucene.queryparser.flexible.core.util.StringUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


/**
 * Controller utilizado para el registro de gastos externos de la cuenta
 * 
 * @author jose de jesus saldaña lopez
 * 
 */
@ManagedBean(name = "gastosExternosController")
@ViewScoped
@Component
@Scope("view")
public class GastosExternosController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8059030989237895300L;
	/**
	 * 
	 */
	
	private static final Logger LOGGER = LogManager
			.getLogger(GastosExternosController.class.getName());

	@Autowired
	ManagedBeanStateRecover managedBeanStateRecover;
	
	@Autowired
	ConsultaTiposGastosBackend consultaTiposGastosBackend;
	
	@Autowired
	 ConsultaGastosExternosBackend   consultaGastosExternosBackend;
	@Autowired
	RegistroGastoExternoBackend registroGastoExternoBackend;
	
	@Autowired
	EliminacionGastoExternoBackend eliminacionGastoExternoBackend;
	
	@Autowired
	ImputarGastoExternoBackend imputarGastoExternoBackend;
	@Autowired
	private ContextoUtils contextoUtils;
	
	@Autowired
	CatalogoCentrosLoaderTask catalogoCentrosLoaderTask;
	@Autowired
    private PdfUtils pdfUtils;
	/**
	 * Variable para almacenar un cuentaBean.
	 */
	private CuentaBean cuentaBean;
	
	/**
	 * Variable utilizada para guardar la redireccion al flujo de entrada a la
	 * consulta de cancelacion de cuenta
	 */
	private NavegacionEnum destino;
	

	/**
	 * Variable utilizada para guardar el contenido del controller origen a la
	 * consulta de cancelacion de cuenta
	 */
	private Object destinoController;
	
	private GastosExternosBean gastosExternosBean;
	
	private String idTipoGasto;
	private Boolean muestraDialogoRegistrar=false;
	private Boolean muestraRegistrarExitoso= false;
	private Boolean muestraDialogoSuprimir=false;
	private Boolean muestraSuprimirExitoso= false;
	private Boolean muestraControlesImputar = false;
	private Boolean muetraBotonRegistrar=true;
	
	private Boolean muestraDialogoRegistrarImputar=false;
	private Boolean muestraRegistrarImputarExitoso= false;
	
	private Boolean muestraMensajeTipo=false;
	
	

	public Boolean getMuestraMensajeTipo() {
		return muestraMensajeTipo;
	}

	public void setMuestraMensajeTipo(Boolean muestraMensajeTipo) {
		this.muestraMensajeTipo = muestraMensajeTipo;
	}

	public Boolean getMuestraDialogoRegistrarImputar() {
		return muestraDialogoRegistrarImputar;
	}

	public void setMuestraDialogoRegistrarImputar(
			Boolean muestraDialogoRegistrarImputar) {
		this.muestraDialogoRegistrarImputar = muestraDialogoRegistrarImputar;
	}

	public Boolean getMuestraRegistrarImputarExitoso() {
		return muestraRegistrarImputarExitoso;
	}

	public void setMuestraRegistrarImputarExitoso(
			Boolean muestraRegistrarImputarExitoso) {
		this.muestraRegistrarImputarExitoso = muestraRegistrarImputarExitoso;
	}

	public Boolean getMuetraBotonRegistrar() {
		return muetraBotonRegistrar;
	}

	public void setMuetraBotonRegistrar(Boolean muetraBotonRegistrar) {
		this.muetraBotonRegistrar = muetraBotonRegistrar;
	}

	public Boolean getMuestraControlesImputar() {
		return muestraControlesImputar;
	}

	public void setMuestraControlesImputar(Boolean muestraControlesImputar) {
		this.muestraControlesImputar = muestraControlesImputar;
	}

	public Boolean getMuestraDialogoSuprimir() {
		return muestraDialogoSuprimir;
	}

	public void setMuestraDialogoSuprimir(Boolean muestraDialogoSuprimir) {
		this.muestraDialogoSuprimir = muestraDialogoSuprimir;
	}

	public Boolean getMuestraSuprimirExitoso() {
		return muestraSuprimirExitoso;
	}

	public void setMuestraSuprimirExitoso(Boolean muestraSuprimirExitoso) {
		this.muestraSuprimirExitoso = muestraSuprimirExitoso;
	}

	
	
	
	public Boolean getMuestraRegistrarExitoso() {
		return muestraRegistrarExitoso;
	}

	public void setMuestraRegistrarExitoso(Boolean muestraRegistrarExitoso) {
		this.muestraRegistrarExitoso = muestraRegistrarExitoso;
	}

	public Boolean getMuestraDialogoRegistrar() {
		return muestraDialogoRegistrar;
	}

	public void setMuestraDialogoRegistrar(Boolean muestraDialogoRegistrar) {
		this.muestraDialogoRegistrar = muestraDialogoRegistrar;
	}

	public String getIdTipoGasto() {
		return idTipoGasto;
	}

	public void setIdTipoGasto(String idTipoGasto) {
		this.idTipoGasto = idTipoGasto;
	}

	public GastosExternosBean getGastosExternosBean() {
		return gastosExternosBean;
	}

	public void setGastosExternosBean(GastosExternosBean gastosExternosBean) {
		this.gastosExternosBean = gastosExternosBean;
	}

	public CuentaBean getCuentaBean() {
		return cuentaBean;
	}

	public void setCuentaBean(CuentaBean cuentaBean) {
		this.cuentaBean = cuentaBean;
	}

	@PostConstruct
	public void init() {
	
		if (this.obtieneFlash().get(
				ParametrosFlashEnum.NAVEGACION_MANAGED_BEAN.getParamFlash()) != null) {
			if ((Boolean) this.obtieneFlash()
					.get(ParametrosFlashEnum.NAVEGACION_MANAGED_BEAN
							.getParamFlash())) {
				LOGGER.debug("Accedemos al metodo de inicio init(), iniciando la navegacion tras volver de otro flujo");
				this.destino = this.managedBeanStateRecover.getDestino();
				this.destinoController = this.managedBeanStateRecover
						.getController();
				this.initializeData();
			} else {
				LOGGER.debug("Accedemos al metodo de inicio init(), recuperando controller tras navegacion");
				this.managedBeanStateRecover.recuperaController(this);
				if (this.obtieneFlash().get(
						ParametrosFlashEnum.NUMERO_CUENTA.getParamFlash()) != null) {
					Long numCuenta = (Long) this.obtieneFlash().get(
							ParametrosFlashEnum.NUMERO_CUENTA.getParamFlash());
					this.gastosExternosBean.setOtraCuenta(numCuenta);
				}
				
			}
		} else {
			LOGGER.debug("Accedemos al metodo de inicio init() directamente sin acceso de navegacion");
			this.initializeData();
		}
		
		
	}
	
	public void initializeData() {
		if (obtieneFlash().get(ParametrosFlashEnum.CUENTA_BEAN.getParamFlash()) != null) {
			this.cuentaBean = (CuentaBean) obtieneFlash().get(
					ParametrosFlashEnum.CUENTA_BEAN.getParamFlash());
			
			if (this.obtieneFlash().get(ParametrosFlashEnum.GASTOS_EXTERNOS_BEAN.getParamFlash()) != null) {
				this.gastosExternosBean = (GastosExternosBean) this.obtieneFlash().get(ParametrosFlashEnum.GASTOS_EXTERNOS_BEAN.getParamFlash());
			}else{
				this.gastosExternosBean = new GastosExternosBean();
			}
			
			cargaCombotipo();
			cargaParrilla();
			actualizarImportes();
			
		}
		else {
			throw new NoControlableException(
					"No se recibió la información de la cuenta ",
					"No se recibió la información de la cuenta ");
		}
	}
	
	
	public void cargaCombotipo()
	{
		consultaTiposGastosBackend.ejecutarTRN(this.cuentaBean.getCodLinea(), this.cuentaBean.getIdGrupoProducto() , this.gastosExternosBean);
		
		
		if(this.getGastosExternosBean().getlTipoGasto() == null )
		{this.setMuestraMensajeTipo(true);}
	}
	public void cargaParrilla()
	{
		consultaGastosExternosBackend.ejecutarTRN(this.cuentaBean.getNumeroCuenta(), this.gastosExternosBean);
	}
	
	/**
	 * @return Metodo utilizado para realizar la accion del boton de volver, a
	 *         la ficha de cuenta.
	 */
	public String volver() {
		String ruta = "";
		if (this.destino == null) {
			ruta = NavegacionEnum.BUSQUEDA.getRuta();
		} else {
			ruta = destino.getRuta();
			managedBeanStateRecover.finNavegacion(destinoController);
		}
		return ruta;
	}
	
	public String volverGastos() {
		String ruta = "";
		obtieneFlash().put(
				ParametrosFlashEnum.CUENTA_BEAN.getParamFlash(),
				this.cuentaBean);
		if (this.destino == null) {
			ruta = NavegacionEnum.BUSQUEDA.getRuta();
		} else {
			ruta = destino.getRuta();
			//managedBeanStateRecover.finNavegacion(destinoController);
		}
		return ruta;
	}
	
	public String irImpresion()
	{
		obtieneFlash().put(
				ParametrosFlashEnum.CUENTA_BEAN.getParamFlash(),
				this.getCuentaBean());
		
		obtieneFlash().put(ParametrosFlashEnum.GASTOS_EXTERNOS_BEAN.getParamFlash(),this.getGastosExternosBean());
		
		
		managedBeanStateRecover.enviaController(this,
				NavegacionEnum.GASTOS_EXTERNOS);
		
		
		return NavegacionEnum.DETALLE_GASTO_IMPUTACION.getRuta();
	}
	
	public void printReportTipoGasto()
	{
		
	}
	
	public void registrarGasto()
	{
		
		registroGastoExternoBackend.ejecutarTRN(this.cuentaBean.getNumeroCuenta(), this.gastosExternosBean.getImporte(), this.getIdTipoGasto(), this.cuentaBean.getCodLinea(), this.cuentaBean.getIdGrupoProducto() );
		cargaParrilla();
		
		this.gastosExternosBean.setImporte(null);
		actualizarImportes();
		setMuestraRegistrarExitoso(true);
		
	}
	
	public void registroImputar()
	{
		long acuerdo=0;
		String tipoImputacion;
		
		if(this.getGastosExternosBean().getDestinoImputacion().equals("CT"))
		{acuerdo=this.gastosExternosBean.getGastoSeleccionado().getCuentaAsociada();
		tipoImputacion="1";}
		else if(this.getGastosExternosBean().getDestinoImputacion().equals("IV"))
		{
			acuerdo=0;
			tipoImputacion="4";
		}
		else{
			acuerdo= this.gastosExternosBean.getOtraCuenta();
			tipoImputacion="1";
		}
			
		
		imputarGastoExternoBackend.ejecutarTRN(this.cuentaBean.getNumeroCuenta(), this.cuentaBean.getCodLinea(), this.cuentaBean.getIdGrupoProducto(), this.gastosExternosBean.getGastoSeleccionado().getImporte(), this.getGastosExternosBean(), acuerdo, tipoImputacion);
		cargaParrilla();
		actualizarImportes();
		setMuestraRegistrarImputarExitoso(true);
		this.setMuestraControlesImputar(false);
		this.setMuetraBotonRegistrar(true);
		
		
		
	}
	
	public void actualizarImportes()
	{
		this.gastosExternosBean.setImportePendiente(null);
		this.gastosExternosBean.setImporteTotal(null);
		
		BigDecimal pendiente =new BigDecimal(0);
		BigDecimal total= new BigDecimal(0);
		
		if(this.gastosExternosBean.getParrillaGastos() !=null)
		{
			
		  for(GastoBean item : this.gastosExternosBean.getParrillaGastos())
		  {
			  if(item.getEstatusGastos().equals("IMPAGADO/PENDIE"))
			  {
				  pendiente= pendiente.add(item.getImporte());
			  }
			  
			  total= total.add(item.getImporte());
				 
			  
		  }
		  
			
			
		}
		
		this.gastosExternosBean.setImportePendiente(pendiente);
		this.gastosExternosBean.setImporteTotal(total);
		
	}
	
	/*public void onRowSelect(SelectEvent event){
	
		GastoBean  gastoBean = new GastoBean();
		
		gastoBean = (GastoBean) event.getObject();
		
		this.gastosExternosBean.setGastoSeleccionado(gastoBean);
		
	}*/
	
	public void eliminarGasto()
	{
		eliminacionGastoExternoBackend.ejecutarTRN(this.cuentaBean.getNumeroCuenta(), this.gastosExternosBean.getGastoSeleccionado().getNumSecuencia());
		cargaParrilla();
		actualizarImportes();
		this.setMuestraSuprimirExitoso(true);
	}
	
	/**
	 * Funcion que determina si se puede hacer el cargo a una cuenta
	 * asociada/operativa
	 * 
	 * @return
	 */
	public boolean permiteCuentaAsociada() {
		if (this.cuentaBean.getCodLinea().equals("01") && this.cuentaBean.getIdGrupoProducto().equals("41")) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Método para buscar mediante el buscador el número de cuenta
	 * 
	 * @return
	 */
	public String buscarPersona() {
		obtieneFlash().put(ParametrosFlashEnum.TIPO_BUSQUEDA.getParamFlash(),
				BusquedaEnum.CUENTAS.getBusquedaValor());
		managedBeanStateRecover.enviaController(this,
				NavegacionEnum.GASTOS_EXTERNOS);
		return NavegacionEnum.BUSQUEDA.getRuta();

	}
	

	public void muestraImputar(Boolean opcion )
	{
		if(opcion)
		{
		this.setMuetraBotonRegistrar(false);
		this.setMuestraControlesImputar(true);
		this.gastosExternosBean.setDestinoImputacion("");
		
		
		
		this.setIdTipoGasto(this.gastosExternosBean.getGastoSeleccionado().getCodigoDescripcion());
		}
		else
		{
			this.setMuetraBotonRegistrar(true);
			this.setMuestraControlesImputar(false);
		}
		
	}
	public void muestraRegistrar()
	{
		RequestContext.getCurrentInstance().execute(
				"PF('dialogRegistrar').show()");
	}
	
	public void muestraRegistrarImputar()
	{
		long g= this.gastosExternosBean.getGastoSeleccionado().getCuentaAsociada();
		String s= this.gastosExternosBean.getDestinoImputacion();
		RequestContext.getCurrentInstance().execute(
				"PF('dialogRegistrarImputar').show()");
	}
	
	public void printImputar()
	{
		
		Map<String, Object> params = new HashMap<String, Object>();
		Map<String, String> images = new HashMap<String, String>();
        images.put("Logo_bsfi_bn.png", "LOGO");
      //  params.put("DESCRIPCIONGASTO", this.gastosExternosBean.getGastoSeleccionado().getDescripcionGasto());
		params.put("MONEDA", this.gastosExternosBean.getGastoSeleccionado().getMoneda());
		//params.put("IMPORTE", this.getGastosExternosBean().getGastoSeleccionado().getImporte());
        params.put("OFICINA", contextoUtils.getSucursal());
        params.put("FECHA_REPORTE", contextoUtils.getFechaContableActual());
        params.put("USUARIO", contextoUtils.getNombre());
        params.put("ENTIDAD", contextoUtils.getEntidad());
        params.put("ACUERDO", this.cuentaBean.getNumeroCuenta());
        params.put("DIGITO",this.gastosExternosBean.getGastoSeleccionado().getCodDig());
		params.put("PLAZA", this.gastosExternosBean.getGastoSeleccionado().getPlazaBancaria());
		params.put("CODGASTO", this.gastosExternosBean.getGastoSeleccionado().getCodigoDescripcion());
		
		List<GastoBean> listaGasto= new ArrayList<GastoBean>();
		GastoBean gasto= new GastoBean();
		
		
		gasto.setImporte(this.getGastosExternosBean().getGastoSeleccionado().getImporte());
		gasto.setDescripcionGasto(this.gastosExternosBean.getGastoSeleccionado().getDescripcionGasto());
		listaGasto.add(gasto);
		
		if(this.getGastosExternosBean().getDestinoImputacion().equals("CT") || this.getGastosExternosBean().getDestinoImputacion().equals("IV"))
		{
			params.put("CENTROASOCIADO", "");
			params.put("OTRACUENTA", "0000000000");
		}
		else{
			params.put("CENTROASOCIADO",contextoUtils.getSucursal());
			params.put("OTRACUENTA", this.gastosExternosBean.getOtraCuenta().toString());
			
		}
		
		System.out.println("*******antes del pdf*" + listaGasto.get(0).getImporte());
        
        pdfUtils.generaPdf("Imputacion.jrxml", params, images,null , "Imputacion", listaGasto);
        
        
		
	}
	public void imputarExitoso()
	{
		this.setMuestraRegistrarImputarExitoso(false);
		printImputar();
	}
	
	/**
	 * Método privado que obtiene el objeto Flash proveniente del contexto
	 * externo.
	 * 
	 * @return Flash
	 */
	private Flash obtieneFlash() {
		return FacesContext.getCurrentInstance().getExternalContext()
				.getFlash();
	}


}
