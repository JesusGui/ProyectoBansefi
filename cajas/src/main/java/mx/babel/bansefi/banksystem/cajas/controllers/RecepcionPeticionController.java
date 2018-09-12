package mx.babel.bansefi.banksystem.cajas.controllers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import mx.babel.arq.catalogo.beans.CatalogoBean;
import mx.babel.arq.sesion.utils.ContextoUtils;
import mx.babel.arq.storage.annotations.StoreStep;
import mx.babel.arq.storage.beans.StorageMgrBean;
import mx.babel.bansefi.banksystem.base.beans.parrilla.ExistenciaDenominacionBean;
import mx.babel.bansefi.banksystem.base.enums.CatalogoEnum;
import mx.babel.bansefi.banksystem.base.enums.NavegacionEnum;
import mx.babel.bansefi.banksystem.base.enums.ParametrosFlashEnum;
import mx.babel.bansefi.banksystem.base.utils.CatalogoCentrosLoaderTask;
import mx.babel.bansefi.banksystem.base.utils.CatalogoUtils;
import mx.babel.bansefi.banksystem.base.utils.PdfUtils;
import mx.babel.bansefi.banksystem.cajas.backend.ConsultaParillaBilletesBackend;
import mx.babel.bansefi.banksystem.cajas.backend.ConsultaPeticionEfectivoBackEnd;
import mx.babel.bansefi.banksystem.cajas.backend.RecepcionEfectivoBackEnd;
import mx.babel.bansefi.banksystem.cajas.beans.CajaUtilsBean;
import mx.babel.bansefi.banksystem.cajas.beans.ParrillaBean;
import mx.babel.bansefi.banksystem.cajas.beans.PeticionEfectivoBean;
import mx.babel.bansefi.banksystem.cajas.beans.RecepcionEfectivoBean;



/**
 * Controlador de vistas para flujo de rececpion de efectivo 
 * @author jose.saldana
 *
 */
@ManagedBean(name="recepcionPeticionController")
@Component
@Scope("view")
public class RecepcionPeticionController extends StorageMgrBean {

	@Autowired
	private ContextoUtils contextoUtils;
	@Autowired
	ConsultaPeticionEfectivoBackEnd consultaPeticionEfectivoBackEnd;
	
	@Autowired
	CajaUtilsBean cajasUtilsBean;
	
	@Autowired
	ConsultaParillaBilletesBackend consultaParillaBilletesBackend;
	
	@Autowired
	CatalogoUtils catalogoUtils;
	
	@Autowired
	RecepcionEfectivoBackEnd recepcionEfectivoBackEnd;
	@Autowired
	CatalogoCentrosLoaderTask catalogoCentrosLoaderTask;
	@Autowired
    private PdfUtils pdfUtils;

	
	public RecepcionEfectivoBean getRecepcionEfectivoBean() {
		return recepcionEfectivoBean;
	}

	public void setRecepcionEfectivoBean(RecepcionEfectivoBean recepcionEfectivoBean) {
		this.recepcionEfectivoBean = recepcionEfectivoBean;
	}

	private RecepcionEfectivoBean recepcionEfectivoBean;

    private Boolean consultaIncorrecta=false; 
    private Boolean estatusIncorrecto=false;
    private Boolean mostrarParrila= false;
	private Boolean muestraDialogoCancelar;
	private Boolean muestraDialogoLimpiar;
	private Boolean muestraDialogoGuardar;
	private Boolean guardarDisabled= true;
	
	
	public Boolean getGuardarDisabled() {
		return guardarDisabled;
	}

	public void setGuardarDisabled(Boolean guardarDisabled) {
		this.guardarDisabled = guardarDisabled;
	}

	public Boolean getMuestraDialogoGuardar() {
		return muestraDialogoGuardar;
	}

	public void setMuestraDialogoGuardar(Boolean muestraDialogoGuardar) {
		this.muestraDialogoGuardar = muestraDialogoGuardar;
	}

	private Boolean muestraBotonGuardar;
	
	

	public Boolean getMuestraBotonGuardar() {
		return muestraBotonGuardar;
	}

	public void setMuestraBotonGuardar(Boolean muestraBotonGuardar) {
		this.muestraBotonGuardar = muestraBotonGuardar;
	}

	public Boolean getMuestraDialogoLimpiar() {
		return muestraDialogoLimpiar;
	}

	public void setMuestraDialogoLimpiar(Boolean muestraDialogoLimpiar) {
		this.muestraDialogoLimpiar = muestraDialogoLimpiar;
	}

	public Boolean getMuestraDialogoCancelar() {
		return muestraDialogoCancelar;
	}

	public void setMuestraDialogoCancelar(Boolean muestraDialogoCancelar) {
		this.muestraDialogoCancelar = muestraDialogoCancelar;
	}

	public Boolean getMostrarParrila() {
		return mostrarParrila;
	}

	public void setMostrarParrila(Boolean mostrarParrila) {
		this.mostrarParrila = mostrarParrila;
	}

	public Boolean getEstatusIncorrecto() {
		return estatusIncorrecto;
	}

	public void setEstatusIncorrecto(Boolean estatusIncorrecto) {
		this.estatusIncorrecto = estatusIncorrecto;
	}

	public Boolean getConsultaIncorrecta() {
		return consultaIncorrecta;
	}

	public void setConsultaIncorrecta(Boolean consultaIncorrecta) {
		this.consultaIncorrecta = consultaIncorrecta;
	}

	/**
	 * Clase para incializar bean de recepcion de efectivo
	 */
	@PostConstruct
	public void init(){
		
		super.restoreflash();
		if (this.obtieneFlash().get(ParametrosFlashEnum.RECEPCION_EFECTIVO_BEAN.getParamFlash()) != null) {
			this.recepcionEfectivoBean = (RecepcionEfectivoBean) this.obtieneFlash().get(ParametrosFlashEnum.RECEPCION_EFECTIVO_BEAN.getParamFlash());
		}else{
			this.recepcionEfectivoBean = new RecepcionEfectivoBean();
			
		}
		if(this.recepcionEfectivoBean.getParillaBean() ==null)
		{
			
			this.recepcionEfectivoBean.setParillaBean(new ParrillaBean());
			this.recepcionEfectivoBean.setFechaAbastecimiento(cajasUtilsBean.getFechaSistema());
			iniciaListaDenominaciones();
		}
		
	}
	
	/**
	 * Mètodo para navegar a recepcion de efectivo
	 * @return ruta de recepcion de efectivo
	 */
	@StoreStep
	public String inicio(){
		this.obtieneFlash().put(ParametrosFlashEnum.RECEPCION_EFECTIVO_BEAN.getParamFlash(), this.recepcionEfectivoBean);
		return NavegacionEnum.RECEPCION_EFECTIVO.getRuta();
	}
	
	/**
	 * Mètodo para navegaciòn a inicio
	 * @return ruta de inicio
	 */
	@StoreStep
	public String rutaInicio(){
		return NavegacionEnum.INICIO.getRuta();
	}
	
	/**
	 * Mètodo para navegar a el resumen de peticion
	 * @return ruta de resumen de peticion
	 */
	public String rutaDetalle(){
		this.obtieneFlash().put(ParametrosFlashEnum.RECEPCION_EFECTIVO_BEAN.getParamFlash(), this.recepcionEfectivoBean);
		return NavegacionEnum.DETALLE_RECEPCION_EFECTIVO.getRuta();
	}
	
	
	/**
     * Se encarga de obtener el flash.
     * 
     * @return Flash con los datos de la pagina
     */
    public Flash obtieneFlash() {
        return FacesContext.getCurrentInstance().getExternalContext().getFlash();
    }
	@Override
	protected Map<String, Object> getBeanList() {
		Map<String, Object> beanList = new HashMap<String, Object>();
		beanList.put(RecepcionEfectivoBean.class.getName(), this.recepcionEfectivoBean);
		return beanList;
	}

	@Override
	protected void setBeanList(Map<String, Object> beanList) {
		if (null != beanList) {
			this.recepcionEfectivoBean =  (RecepcionEfectivoBean) beanList.get(RecepcionEfectivoBean.class.getName());
			obtieneFlash().put(ParametrosFlashEnum.RECEPCION_EFECTIVO_BEAN.getParamFlash(), this.recepcionEfectivoBean);
		}
		
	}

	@Override
	protected String getNombreFlujo() {
		return "Recepcion de Efectivo: " +  contextoUtils.getTerminal();
	}
	
	public void consultaRecepcion()
	{
		Boolean resultado;
		resultado=consultaRecepcionEfectivo();
		if(resultado==false)
		{
			setConsultaIncorrecta(true);
			RequestContext.getCurrentInstance().execute("PF('dlgAvisoConsultaIncorrecta').show();");
			setMostrarParrila(false);
		}
		else
		{
			setConsultaIncorrecta(false);
			if(this.recepcionEfectivoBean.getEstatus().equals("S")||this.recepcionEfectivoBean.getEstatus().equals("A")||this.recepcionEfectivoBean.getEstatus().equals("C"))
			{
				setEstatusIncorrecto(true);
				RequestContext.getCurrentInstance().execute("PF('dlgAvisoEstatusIncorrecta').show();");
				setMostrarParrila(false);
			}
			else
			{
				setEstatusIncorrecto(false);
				setMostrarParrila(true);
				final CatalogoBean catalogo = catalogoUtils.getCatalogoBean(CatalogoEnum.ESTATUS_PETICION, this.recepcionEfectivoBean.getEstatus());
				
				if(catalogo != null){
					this.recepcionEfectivoBean.setEstatusDescripcion(catalogo.getDescripcionL());
				}else{
					this.recepcionEfectivoBean.setEstatusDescripcion(" ");
				}
				
				if(this.recepcionEfectivoBean.getEstatus().equals("F"))
				{
					setMuestraBotonGuardar(true);
					
						}
				else
				{
					setMuestraBotonGuardar(false);
					
				}
				
				actualizaTotalRecepcion();
				
			}
			
				
		}
	}
	
	public Boolean consultaRecepcionEfectivo()
	{
		int fecha;
		Boolean resultado;
		fecha=cajasUtilsBean.getFechaSistemaInteger();
		resultado=consultaPeticionEfectivoBackEnd.ejecutarTRN(fecha, this.recepcionEfectivoBean);
		
	 return resultado;
	}
	
	
	/**
	 * Método para inicializar la lista de denominaciones de la parrilla.
	 */
	public void iniciaListaDenominaciones(){
		this.recepcionEfectivoBean.getParillaBean().setListaDenominaciones(new ArrayList<ExistenciaDenominacionBean>());
		
		consultaParillaBilletesBackend.ejecutarTRN2(this.recepcionEfectivoBean.getParillaBean().getListaDenominaciones());
		iniciaUnidades();
	
	}
	
	
	
	
	private void iniciaUnidades(){
		for (ExistenciaDenominacionBean existenciaDenominacionBean : this.recepcionEfectivoBean.getParillaBean().getListaDenominaciones()) {
			actualizaUnidades(existenciaDenominacionBean);
		}
	}
	
	/**
	 * Método que actualiza las unidades de una denominación en función al importe ingresado.
	 * @param denominacion DEnomincación a evaluar
	 */
	public void actualizaUnidades(ExistenciaDenominacionBean denominacion){
		if(denominacion.getValor() != null && denominacion.getImporteRecibido() != null){
			Long unidades = denominacion.getImporteRecibido().divide(denominacion.getValor()).longValue();
			denominacion.setUnidades(unidades);
		}
		actualizaTotalRecepcion();
	}
	
	
	/**
	 * Método que actualiza el total de lo recibido  en relación a los valores ingresados en la parrilla de denominaciones.
	 */
	public void actualizaTotalRecepcion(){
		BigDecimal total = new BigDecimal(0);
		for (ExistenciaDenominacionBean denominacion : this.recepcionEfectivoBean.getParillaBean().getListaDenominaciones()) {
			if(denominacion.getImporteRecibido() != null){
				total = total.add(denominacion.getImporteRecibido());
			}
		}
		this.recepcionEfectivoBean.setTotalRecibido(total);
		
		if(total.equals(BigDecimal.ZERO) || total.compareTo(new BigDecimal(0.00))==0 )
		{setGuardarDisabled(true);}
		else
		{setGuardarDisabled(false);}
		
		actualizaDiferencia();
	}

	/**
	 * Método que actualiza la diferencia entre lo recibido y lo autorizado.
	 */
	public void actualizaDiferencia()
	{
		BigDecimal total = new BigDecimal(0);
		
		
		if(this.recepcionEfectivoBean.getTotalRecibido()!= null && this.recepcionEfectivoBean.getTotalAutorizado()!=null )
		{
		total = total.add(this.recepcionEfectivoBean.getTotalRecibido());
		total= total.subtract(this.recepcionEfectivoBean.getTotalAutorizado());
		this.recepcionEfectivoBean.setDiferencia(total);
		}
		else
		{
			this.recepcionEfectivoBean.setDiferencia(null);
		}
		
	}
	
	/**
	 * Método que actualiza el importe de una denominación en función a las unidades ingresadas.
	 * @param denominacion Denomincación a evaluar
	 */
	public void actualizaImporte(ExistenciaDenominacionBean denominacion){
		if(denominacion.getValor() != null && denominacion.getUnidades() != null){
			BigDecimal importe = denominacion.getValor().multiply(new BigDecimal(denominacion.getUnidades()));
			denominacion.setImporteRecibido(importe);
		}
		actualizaTotalRecepcion();
	}
	public void limpiaConsulta()
	{
		setMostrarParrila(false);
		this.recepcionEfectivoBean.setIndicadorUrgencia(0);
		
		
	}
	public void limpiaParrilla(){
		this.recepcionEfectivoBean.getParillaBean().setFiltro(0);
		for (ExistenciaDenominacionBean denominacion : this.recepcionEfectivoBean.getParillaBean().getListaDenominaciones()) {
			denominacion.setImporteRecibido(null);
			denominacion.setUnidades(null);
		}
		actualizaTotalRecepcion();
	}
	
	/**
	 * Método utilizado para guardar la peticion de efectivo de  la parrilla de denominaciones.
	 */
	public String guardar()
	{
		recepcionEfectivoBackEnd.ejecutarTRN(this.recepcionEfectivoBean,  cajasUtilsBean.getFechaSistemaInteger());
		this.recepcionEfectivoBean.getParillaBean().setFiltro(5);
		return rutaDetalle();
	}
	
	/**
	 * Método para crear un archivo pdf con los datos desplegados en pantalla.
	 *
	 */
	public void printReport(){
		
		this.recepcionEfectivoBean.setListaDenominaciones(this.recepcionEfectivoBean.getParillaBean().getListaDenominaciones());
		final Map<String, Object> params = new HashMap<String, Object>();
		String nombreOficina;
		String plazaBancaria;
		try{
			nombreOficina = catalogoCentrosLoaderTask.getCatalogoBean(contextoUtils.getEntidad(), contextoUtils.getSucursal()).getDescripcionL();
		} catch(Exception e){
			nombreOficina = "";
		}
		try{
			plazaBancaria = catalogoUtils.getCatalogoBean(CatalogoEnum.TP_PLAZA_BANCARIA, contextoUtils.getPlazaBancaria()).getDescripcionL();
		}catch(Exception e){
			plazaBancaria = "";
		}
		
		params.put("FECHA_CONTABLE", contextoUtils.getFechaContableActual());
		
		
		params.put("OFICINA", nombreOficina);
		params.put("PLAZA", plazaBancaria);
		
		params.put("TERMINAL", contextoUtils.getTerminal());
		params.put("USUARIO", contextoUtils.getId());
		params.put("indicadorUrgencia", this.recepcionEfectivoBean.getIndicadorUrgencia());
		
		final Map<String, String> subReportes = new HashMap<String, String>();
		subReportes.put("DesgloseRecepcionEfectivo.jrxml", "SUBREPORT_DENOMINACION");

		final Map<String, String> images = new HashMap<String, String>();
        images.put("Logo_bsfi_bn.png", "LOGO");

		final List<RecepcionEfectivoBean> listaBeans = new ArrayList<RecepcionEfectivoBean>();
		listaBeans.add(this.recepcionEfectivoBean);
		pdfUtils.generaPdf("RecepcionEfectivo.jrxml", params, images, subReportes, "RecepcionEfectivo", listaBeans);
	}
	
	

}
