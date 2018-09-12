package mx.babel.bansefi.banksystem.cuentas.controllers;



import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

import mx.babel.arq.catalogo.beans.CatalogoBean;
import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.bansefi.banksystem.base.backends.ConsultaCuentaBackend;
import mx.babel.bansefi.banksystem.base.beans.domain.CuentaBean;
import mx.babel.bansefi.banksystem.base.enums.CatalogoEnum;
import mx.babel.bansefi.banksystem.base.enums.EstadosCuentaEnum;
import mx.babel.bansefi.banksystem.base.enums.NavegacionEnum;
import mx.babel.bansefi.banksystem.base.enums.ParametrosFlashEnum;
import mx.babel.bansefi.banksystem.base.utils.CatalogoUtils;
import mx.babel.bansefi.banksystem.base.utils.ManagedBeanStateRecover;
import mx.babel.bansefi.banksystem.cuentas.backends.ConsultaClasificacionBackend;
import mx.babel.bansefi.banksystem.cuentas.backends.ConsultaFinalidadListaBackEnd;
import mx.babel.bansefi.banksystem.cuentas.backends.ModificaInformeClasificacionBackEnd;
//import mx.babel.bansefi.banksystem.cuentas.backends.ConsultaFinalidadListaBackEnd;
import mx.babel.bansefi.banksystem.cuentas.beans.ClasificacionBean;
import mx.babel.bansefi.banksystem.cuentas.beans.FinalidadBean;

import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
/**
 * 
 * @author miguel.rojas
 *
 */

@ManagedBean (name = "clasificacionCuentaController")
@ViewScoped
@Component
@Scope("view")
public class ClasificacionCuentaController implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5610772307580858058L;
	
	@Autowired
	ConsultaFinalidadListaBackEnd consultaListaFinalidadBackEnd;
	
	@Autowired
	ManagedBeanStateRecover managedBeanStateRecover;
	
	@Autowired
	ModificaInformeClasificacionBackEnd modificaInformeClasificacionBackEnd;
	
	@Autowired
	ConsultaCuentaBackend consultaCuentaBackend;
	
	@Autowired
	ConsultaClasificacionBackend consultaClasificacionBackEnd;
	
	@Autowired
	private CatalogoUtils catalogo;
	
	private Object destinoController; 
	
	
	private ClasificacionBean clasificacionBean = null;
	private List<FinalidadBean> finalidad;
	private CuentaBean cuentaBean;
	private CatalogoBean cnaeSelected;
	private boolean guardado;
	private String codigoFinalidad;
	private String cnae;
	
	public ClasificacionCuentaController(){
		super();
	}
	
	
	@PostConstruct
	public void init(){
		cnaeSelected = new CatalogoBean();
		clasificacionBean = new ClasificacionBean();
		finalidad = new ArrayList<FinalidadBean>();
		
		if(obtieneFlash().get(ParametrosFlashEnum.CUENTA_BEAN.getParamFlash()) != null){
			this.cuentaBean = (CuentaBean) this.obtieneFlash().get(ParametrosFlashEnum.CUENTA_BEAN.getParamFlash());
			this.destinoController = managedBeanStateRecover.getController();
			
			
		}else{
			cuentaBean = new CuentaBean();
			cuentaBean.setNumeroCuenta(69225969L);
			
		}
		clasificacionBean = consultaClasificacionBackEnd.ejecutarTRN(cuentaBean.getNumeroCuenta());
		cuentaBean = consultaCuentaBackend.ejecutarTRN(cuentaBean.getNumeroCuenta());
		finalidad = consultaListaFinalidadBackEnd.ejecutarTRN(cuentaBean.getCodLinea(), cuentaBean.getIdGrupoProducto(), cuentaBean.getIdProducto(), cuentaBean.getIdTarifaProducto());
		int tamaño = finalidad.size();
		Boolean contieneFinalidad = false;
		for(int i=0; i<tamaño; i++){
			CatalogoBean codigo = catalogo.getCatalogoBeanNull(CatalogoEnum.TP_FNDAD_AC, finalidad.get(i).getCodigoFinalidad());
			if(codigo == null){
				finalidad.get(i).setDescCodigoFinalidad("Falta Descripción");
			}else{
				finalidad.get(i).setDescCodigoFinalidad(codigo.getDescripcionL());
				if(codigo.getClaveFila().equals(clasificacionBean.getFinalidad())){
					contieneFinalidad = true;
				}
			}
		}
		if(!contieneFinalidad){
			CatalogoBean codigo = catalogo.getCatalogoBeanNull(CatalogoEnum.TP_FNDAD_AC, clasificacionBean.getFinalidad());
			if(codigo != null){
				FinalidadBean finalidadBean = new FinalidadBean();
				finalidadBean.setCodigoFinalidad(codigo.getClaveFila());
				finalidadBean.setDescCodigoFinalidad(codigo.getDescripcionL());
				finalidad.add(finalidadBean);
			}
		}
		if("1".equals(clasificacionBean.getResidente())){
			clasificacionBean.setResidente("SI");
		}else{
			clasificacionBean.setResidente("NO");
		}
		if("S".equals(clasificacionBean.getSocio())){
			clasificacionBean.setSocio("SI");
		}else{
			clasificacionBean.setSocio("NO");
		}
		
		codigoFinalidad = clasificacionBean.getFinalidad();
		cnae = clasificacionBean.getCnae();
		
				
	}
	
	/**
	 * 
	 * @return descripción de código Activo
	 */
	public String obtenerDescActivo(){
		try{
			return catalogo.getCatalogoBean(CatalogoEnum.COD_ACTIVO, clasificacionBean.getCodigoActivo()).getDescripcionL();
		}catch (ControlableException ce){
			return "";
		}
		
	}
	
	/**
	 * 
	 * @return descripción situación irregular
	 */
	public String obtenerDescSitIrreg(){
		try{
			return catalogo.getCatalogoBean(CatalogoEnum.TP_SIT_IRREG, clasificacionBean.getSituacionIrregular()).getDescripcionL();
		}catch (ControlableException ce){
			return "";
		}
	}
	
	/**
	 * 
	 * @return descripción situación de reclamacación
	 */
	public String obtenerDescSitReclam(){
		try{
			return catalogo.getCatalogoBean(CatalogoEnum.TP_SIT_RECLAM, clasificacionBean.getSituacionReclama()).getDescripcionL();
		}catch (ControlableException ce){
			return "";
		}
		
	}
	
	/**
	 * 
	 * @return descripción de garantía
	 */
	public String obtenerDescGrtia(){
		try{
			return catalogo.getCatalogoBean(CatalogoEnum.TP_GRTIA_AC,clasificacionBean.getCodigoGarantia()).getDescripcionL();
		}catch (ControlableException ce){
			return "";
		}
		
	}
	
	/**
	 * 
	 * @return descripción de garantía contable
	 */
	public String obtenerDescGtiaCtble(){
		try{
			return catalogo.getCatalogoBean(CatalogoEnum.COD_GTIA_CTBLE,clasificacionBean.getDescGarantiaContable()).getDescripcionL();
		}catch (ControlableException ce){
			return "";
		}
		
	}
	
	/**
	 * 
	 * @return descripción de la garantia Cirbe
	 */
	public String obtenerDescGtiaCirbe(){
		try{
			return catalogo.getCatalogoBean(CatalogoEnum.TP_GTIA_CIRBE, clasificacionBean.getGarantiaCirbe()).getDescripcionL();
		}catch (ControlableException ce){
			return "";
		}
	}
	
	/**
	 * 
	 * @return descripción de persona contable
	 */
	public String obtenerDescSitPersCtble(){
		try{
			return catalogo.getCatalogoBean(CatalogoEnum.TP_PERS_CTBLE, clasificacionBean.getPersContable()).getDescripcionL();
		}catch (ControlableException ce){
			return "";
		}
	}
	
	public CuentaBean getCuentaBean(){
		return cuentaBean;
	}
	
	public void setCuentaBean(CuentaBean cuentaBean){
		this.cuentaBean = cuentaBean;
	}
	
	/**
	 * Envía a la pantalla de inicio donde se encuentra el dashboard
	 * @return ruta de inicio del dashboard
	 */
	public String inicio(){
		return NavegacionEnum.INICIO.getRuta();
		
	}
	
	/**
	 * Regresa a la pantalla de ficha de cuenta con los valores de la cuenta de búsqueda
	 * @return ruta de ficha cuenta
	 */
	public String regresa(){
		managedBeanStateRecover.finNavegacion(destinoController);
		return NavegacionEnum.FICHA_CUENTA.getRuta();
	}
	
	public ClasificacionBean getClasificacionBean(){
		return clasificacionBean;
	}
	
	public void setClasificacionBean(ClasificacionBean clasificacionBean){
		this.clasificacionBean = clasificacionBean;
	}
	
	/**
	 * Getters & Setters.
	 * @return finalidad
	 */
	public List<FinalidadBean> getFinalidad(){
		if(finalidad == null){
			finalidad = new ArrayList<FinalidadBean>();
		}
		return finalidad;
	}
	
	/**
	 * Getters & Setters.
	 * @param finalidad
	 */
	public void setFinalidad(List<FinalidadBean> finalidad){
		this.finalidad = finalidad;
	}
	
	
	private Flash obtieneFlash(){
		return FacesContext.getCurrentInstance().getExternalContext().getFlash();
	}
	
	/**
	 * Getters & Setters
	 * @return cnaeSelected
	 */
	public CatalogoBean getCnaeSelected(){
		if(this.clasificacionBean.getCnae() != null && !("").equals(this.clasificacionBean.getCnae())){
			cnaeSelected = catalogo.getCatalogoBean(CatalogoEnum.TP_CNAE_PERS, this.clasificacionBean.getCnae());
		}
		return cnaeSelected;
	}
	
	/**
	 * Getters & Setters.
	 * @param cnaeSelected
	 */
	public void setCnaeSelected(CatalogoBean cnaeSelected){
		this.cnaeSelected = cnaeSelected;
		if(this.cnaeSelected != null){
			this.clasificacionBean.setCnae(this.cnaeSelected.getClaveFila());
		}else{
			this.clasificacionBean.setCnae(null);
		}
	}
	
	/**
	 * Método para validar que se haya guardado correctamente.
	 */
	public void guardar(){
		String cnaeClasificacion = clasificacionBean.getCnae();
		if(cnaeClasificacion == null){
			cnaeClasificacion = "";
		}
		if(!this.cnae.equals(cnaeClasificacion) || !this.codigoFinalidad.equals(clasificacionBean.getFinalidad())){
			modificaInformeClasificacionBackEnd.ejecutarTRN(this.cuentaBean.getNumeroCuenta(),this.clasificacionBean);
			this.setGuardado(true);
			this.cnae = cnaeClasificacion;
			this.codigoFinalidad = clasificacionBean.getFinalidad();
		}else{
			this.setGuardado(false);
			RequestContext.getCurrentInstance().execute("PF('dlgNoCambios').show();");
		}
	}
	
	/**
	 * Funcion que indica si la cuenta se encuentra
	 * en estado activo
	 * @return
	 */
	public boolean isCuentaActiva(){
		if(this.cuentaBean != null && this.cuentaBean.getEstadoEnum() != null){
			if(this.cuentaBean.getEstadoEnum().equals(EstadosCuentaEnum.CANCELADO)){
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * Getters & Setters.
	 * @return guardado
	 */
	public boolean isGuardado(){
		return guardado;
	}
	
	/**
	 * Getters & Setters.
	 * @param guardado
	 */
	public void setGuardado(boolean guardado){
		this.guardado = guardado;
	}
	
	public Object getDestinoController(){
		return destinoController;
	}
	
	public void setDestinoController(Object destinoController){
		this.destinoController = destinoController;
	}

}
