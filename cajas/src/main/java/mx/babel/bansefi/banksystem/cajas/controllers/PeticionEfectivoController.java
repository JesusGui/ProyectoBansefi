package mx.babel.bansefi.banksystem.cajas.controllers;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

import org.joda.time.DateTime;
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
import mx.babel.bansefi.banksystem.cajas.backend.ConsultaHoraLimiteBackend;
import mx.babel.bansefi.banksystem.cajas.backend.ConsultaParillaBilletesBackend;
import mx.babel.bansefi.banksystem.cajas.backend.ConsultaPeticionEfectivoBackEnd;
import mx.babel.bansefi.banksystem.cajas.backend.ConsultaPeticionPorFechasBackEnd;
import mx.babel.bansefi.banksystem.cajas.backend.ModificaPeticionEfectivoBackEnd;
import mx.babel.bansefi.banksystem.cajas.backend.SuprimePeticionEfectivoBackEnd;
import mx.babel.bansefi.banksystem.cajas.beans.ComboUrgentesBean;
import mx.babel.bansefi.banksystem.cajas.beans.PeticionEfectivoBean;
import mx.babel.bansefi.banksystem.cajas.beans.ParrillaBean;
import mx.babel.bansefi.banksystem.cajas.beans.HoraBean;
import mx.babel.bansefi.banksystem.cajas.beans.CajaUtilsBean;
import mx.babel.bansefi.banksystem.cajas.beans.PeticionUrgenteBean;
import mx.babel.bansefi.banksystem.cajas.backend.AltaPeticionEfectivoBackEnd;
//import mx.babel.bansefi.banksystem.oficina.beans.ConsultaTraspasoBean;

/**
 * Controlador de vistas para flujo de peticion de efectivo 
 * @author jose.saldana
 *
 */
@ManagedBean(name="peticionEfectivoController")
@Component
@Scope("view")
public class PeticionEfectivoController extends StorageMgrBean{
	
	@Autowired
	private ContextoUtils contextoUtils;
	
	@Autowired
    private PdfUtils pdfUtils;
	
	@Autowired
	CatalogoUtils catalogoUtils;
	
	@Autowired
	ConsultaParillaBilletesBackend consultaParillaBilletesBackend;
	
	@Autowired
	ConsultaHoraLimiteBackend consultaHoraLimiteBackend; 
	
	@Autowired
	ConsultaPeticionEfectivoBackEnd consultaPeticionEfectivoBackEnd;
	
	@Autowired
	CajaUtilsBean cajasUtilsBean;
	
	@Autowired
	AltaPeticionEfectivoBackEnd altaPeticionEfectivoBackEnd;
	
	@Autowired
	ModificaPeticionEfectivoBackEnd modificaPeticionEfectivoBackEnd;
	
	@Autowired
	SuprimePeticionEfectivoBackEnd suprimePeticionEfectivoBackEnd;
	
	@Autowired
	CatalogoCentrosLoaderTask catalogoCentrosLoaderTask;
	
	@Autowired
	ConsultaPeticionPorFechasBackEnd  consultaPeticionPorFechasBackEnd;
	
	private Boolean muestraDialogoLimpiar;
	private Boolean muestraDialogoCancelar;
	private Boolean muestraDialogoGuardar;
	private Boolean muestraDialogoModificar;
	private Boolean muestraDialogoSuprimir;
	private Boolean muestraDialogoSuprimirExitoso;
	private Boolean muestraComboUrgente=false;;
	

	public Boolean getMuestraComboUrgente() {
		return muestraComboUrgente;
	}

	public void setMuestraComboUrgente(Boolean muestraComboUrgente) {
		this.muestraComboUrgente = muestraComboUrgente;
	}

	public Boolean getMuestraDialogoSuprimirExitoso() {
		return muestraDialogoSuprimirExitoso;
	}

	public void setMuestraDialogoSuprimirExitoso(
			Boolean muestraDialogoSuprimirExitoso) {
		this.muestraDialogoSuprimirExitoso = muestraDialogoSuprimirExitoso;
	}

	public Boolean getMuestraDialogoSuprimir() {
		return muestraDialogoSuprimir;
	}

	public void setMuestraDialogoSuprimir(Boolean muestraDialogoSuprimir) {
		this.muestraDialogoSuprimir = muestraDialogoSuprimir;
	}

	public Boolean getMuestraDialogoModificar() {
		return muestraDialogoModificar;
	}

	public void setMuestraDialogoModificar(Boolean muestraDialogoModificar) {
		this.muestraDialogoModificar = muestraDialogoModificar;
	}



	public Boolean getMuestraDialogoGuardar() {
		return muestraDialogoGuardar;
	}

	public void setMuestraDialogoGuardar(Boolean muestraDialogoGuardar) {
		this.muestraDialogoGuardar = muestraDialogoGuardar;
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

	public PeticionEfectivoBean getPeticionEfectivoBean() {
		return peticionEfectivoBean;
	}

	public void setPeticionEfectivoBean(PeticionEfectivoBean peticionEfectivoBean) {
		this.peticionEfectivoBean = peticionEfectivoBean;
	}



	private PeticionEfectivoBean peticionEfectivoBean;
	

	
	/**
	 * Clase para incializar bean de arqueo de puesto
	 */
	@PostConstruct
	public void init(){
		super.restoreflash();
		if (this.obtieneFlash().get(ParametrosFlashEnum.PETICION_EFECTIVO_BEAN.getParamFlash()) != null) {
			this.peticionEfectivoBean = (PeticionEfectivoBean) this.obtieneFlash().get(ParametrosFlashEnum.PETICION_EFECTIVO_BEAN.getParamFlash());
		}else{
			this.peticionEfectivoBean = new PeticionEfectivoBean();
			//this.peticionEfectivoBean.setPuesto(contextoUtils.getTerminal().substring(6));
		}
		
		if(this.peticionEfectivoBean.getHoraLimite()==null)
		{
			this.peticionEfectivoBean.setHoraLimite(new HoraBean());
			inicializaHoraLimite();
			
		}
		
		if(this.peticionEfectivoBean.getMayorHoraLimite()==null)
		{
			this.peticionEfectivoBean.setMayorHoraLimite(validaHoraLimite());
		}
		
		if(this.peticionEfectivoBean.getComboUrgenteBean() ==null)
		{
			this.peticionEfectivoBean.setComboUrgenteBean(new ComboUrgentesBean());
			this.peticionEfectivoBean.getComboUrgenteBean().setListaIndicadoresUrgentes(new ArrayList<PeticionUrgenteBean>());
		}
		if(this.peticionEfectivoBean.getParillaBean() ==null)
		{
			
			this.peticionEfectivoBean.setParillaBean(new ParrillaBean());
			this.peticionEfectivoBean.getParillaBean().setFiltro(0);
			iniciaListaDenominaciones(true);
			actualizaFechaAbastecimiento();
			
			consultaPeticionEfectivo();
			
		}
		
		
		
		/*if (this.obtieneFlash().get(ParametrosFlashEnum.CONSULTA_TRASPASO.getParamFlash()) != null) {
			this.consultaTraspasoBean = (ConsultaTraspasoBean) this.obtieneFlash().get(ParametrosFlashEnum.CONSULTA_TRASPASO.getParamFlash());
		}*/
	}
	
	/**
	 * MÃ¨todo para navegar a el resultado de arqueo
	 * @return ruta de resultado de arqueo
	 */
	@StoreStep
	public String inicio(){
		this.obtieneFlash().put(ParametrosFlashEnum.PETICION_EFECTIVO_BEAN.getParamFlash(), this.peticionEfectivoBean);
		return NavegacionEnum.PETICION_EFECTIVO.getRuta();
	}
	
	/**
	 * MÃ¨todo para navegaciÃ²n a inicio
	 * @return ruta de inicio
	 */
	@StoreStep
	public String rutaInicio(){
		return NavegacionEnum.INICIO.getRuta();
	}
	
	
	/**
	 * MÃ¨todo para navegar a el resumen de peticion
	 * @return ruta de resumen de peticion
	 */
	public String rutaDetalle(){
		this.obtieneFlash().put(ParametrosFlashEnum.PETICION_EFECTIVO_BEAN.getParamFlash(), this.peticionEfectivoBean);
		return NavegacionEnum.DETALLE_PETICION_EFECTIVO.getRuta();
	}
	
	public void inicializaHoraLimite()
	{
		consultaHoraLimiteBackend.ejecutarTRN(this.peticionEfectivoBean.getHoraLimite());
		
	}
	
	/**
	 * MÃ¨todo para consultar una peticion de efectivo
	 * @return ruta de inicio
	 */
	public void consultaPeticionEfectivo()
	{
		Boolean  result =	consultaPeticion();
		if(result ==false )
		{
			if(this.peticionEfectivoBean.getMayorHoraLimite()==false)
			{
			this.peticionEfectivoBean.setGuardar(true);
		    this.peticionEfectivoBean.setMuestraBotonModificar(false);
		    this.peticionEfectivoBean.setMuestraBotonSuprimir(false);
			}
			else
			{
				this.peticionEfectivoBean.setGuardar(false);
			    this.peticionEfectivoBean.setMuestraBotonModificar(false);
			    this.peticionEfectivoBean.setMuestraBotonSuprimir(false);
			}
		}
		else
		{
			this.peticionEfectivoBean.setGuardar(false);
			actualizaTotalPeticion();
			
			if(this.peticionEfectivoBean.getEstatus().equals("S") && this.peticionEfectivoBean.getMayorHoraLimite()==false)
			{
				this.peticionEfectivoBean.setMuestraBotonModificar(true);
				this.peticionEfectivoBean.setMuestraBotonSuprimir(true);
			}
			else
			{this.peticionEfectivoBean.setMuestraBotonModificar(false);
			 this.peticionEfectivoBean.setMuestraBotonSuprimir(false);
			}
			
			final CatalogoBean catalogo = catalogoUtils.getCatalogoBean(CatalogoEnum.ESTATUS_PETICION, this.peticionEfectivoBean.getEstatus());
			
			if(catalogo != null){
				this.peticionEfectivoBean.setEstatusDescripcion(catalogo.getDescripcionL());
			}else{
				this.peticionEfectivoBean.setEstatusDescripcion(" ");
			}
			
		}
	
	}
	
	
	public Boolean consultaPeticion()
	{
		int fecha;
		if(this.peticionEfectivoBean.getTipoPeticion()==1)
		{
			fecha=cajasUtilsBean.getFechaContableSiguienteInteger();
			this.peticionEfectivoBean.setIndicadorUrgencia(0);
		}
		else
		{
			fecha=cajasUtilsBean.getFechaSistemaInteger();
		}
		
		return consultaPeticionEfectivoBackEnd.ejecutarTRN(this.peticionEfectivoBean.getParillaBean().getListaDenominaciones(), fecha, this.peticionEfectivoBean);

		
	}
	
	public void consultaListaPeticionesUrgentes()
	{
		
		
	}
	
	
	/**
	 * MÃ©todo para inicializar la lista de denominaciones de la parrilla.
	 */
	public void iniciaListaDenominaciones(Boolean consultaParrilla){
		this.peticionEfectivoBean.getParillaBean().setListaDenominaciones(new ArrayList<ExistenciaDenominacionBean>());
		
		consultaParillaBilletesBackend.ejecutarTRN2(this.peticionEfectivoBean.getParillaBean().getListaDenominaciones());
		iniciaUnidades();
		/*this.peticionEfectivoBean.setListaDenominaciones(new ArrayList<ExistenciaDenominacionBean>());
		List<String> valores = ConstantesFuncionales.getValorDenominaciones();
		for (String valor : valores) {
			String denominacion = valor.split(":")[0];
			Boolean moneda = valor.split(":")[1].equals("m");
			ExistenciaDenominacionBean existenciaDenominacion = new ExistenciaDenominacionBean();
			existenciaDenominacion.setMoneda(moneda);
			existenciaDenominacion.setValor(new BigDecimal(denominacion));
			existenciaDenominacion.setFormato("E");
			existenciaDenominacion.setOrigen("V");
			this.peticionEfectivoBean.getListaDenominaciones().add(existenciaDenominacion);
		}
		if(consultaParrilla){
			//consultaParillaPuestoBackEnd.ejecutarTRN(this.arqueoPuestoBean.getListaDenominaciones());
		}
		iniciaUnidades();
		Collections.sort(this.peticionEfectivoBean.getListaDenominaciones(),new DenominacionValorComparator());*/
	}
	
	
	private void iniciaUnidades(){
		for (ExistenciaDenominacionBean existenciaDenominacionBean : this.peticionEfectivoBean.getParillaBean().getListaDenominaciones()) {
			actualizaUnidades(existenciaDenominacionBean);
		}
	}
	
	/**
	 * MÃ©todo que actualiza las unidades de una denominaciÃ³n en funciÃ³n al importe ingresado.
	 * @param denominacion DEnomincaciÃ³n a evaluar
	 */
	public void actualizaUnidades(ExistenciaDenominacionBean denominacion){
		if(denominacion.getValor() != null && denominacion.getImportePedido() != null){
			Long unidades = denominacion.getImportePedido().divide(denominacion.getValor()).longValue();
			denominacion.setUnidades(unidades);
		}
		actualizaTotalPeticion();
	}
	
	/**
	 * MÃ©todo que actualiza el total de la peticion  en relaciÃ³n a los valores ingresados en la parrilla de denominaciones.
	 */
	public void actualizaTotalPeticion(){
		BigDecimal total = new BigDecimal(0);
		for (ExistenciaDenominacionBean denominacion : this.peticionEfectivoBean.getParillaBean().getListaDenominaciones()) {
			if(denominacion.getImportePedido() != null){
				total = total.add(denominacion.getImportePedido());
			}
		}
		this.peticionEfectivoBean.setTotalPeticion(total);
		
		if(total.equals(BigDecimal.ZERO) || total.compareTo(new BigDecimal(0.00))==0 )
		{this.peticionEfectivoBean.setGuardarDisabled(true);}
		else
		{this.peticionEfectivoBean.setGuardarDisabled(false);}
		
		if(total.equals(BigDecimal.ZERO) || total.compareTo(new BigDecimal(0.00))==0 )
		{this.peticionEfectivoBean.setModificarDisabled(true);}
		else
		{this.peticionEfectivoBean.setModificarDisabled(false);}
	}

	/**
	 * MÃ©todo que la hora actual contra la hora limite  regresa false si es menor y true si es mayor.
	 */
	@SuppressWarnings("deprecation")
	public Boolean validaHoraLimite()
	{
		
		DateTime now = DateTime.now();
		int hora=now.getHourOfDay();
		int minuto= now.getMinuteOfHour();
		
		Date  dHoraLimite= this.peticionEfectivoBean.getHoraLimite().getHoraLimitePeticon();
		int horaLimite = dHoraLimite.getHours();
		int minutosLimite= dHoraLimite.getMinutes();
		
		if(hora >horaLimite)
		{return true; }
		else if(hora == horaLimite)
		{
			if(minuto < minutosLimite)
			{
				return false;
			}
			else
			{
				return true;
			}
		}
		else
		{
			return false;
		}
			
		
		

	}
	
	/**
	 * MÃ©todo que actualiza el importe de una denominaciÃ³n en funciÃ³n a las unidades ingresadas.
	 * @param denominacion DenomincaciÃ³n a evaluar
	 */
	public void actualizaImporte(ExistenciaDenominacionBean denominacion){
		if(denominacion.getValor() != null && denominacion.getUnidades() != null){
			BigDecimal importe = denominacion.getValor().multiply(new BigDecimal(denominacion.getUnidades()));
			denominacion.setImportePedido(importe);
		}
		actualizaTotalPeticion();
	}
	
	/**
	 * MÃ©todo que calcula la longitud mÃ¡xima de unidades para tener una longitud de importe de 12 digitos con 2 decimales.
	 * @param denominacion DenominaciÃ³n a evaluar
	 * @return Longitud mÃ¡xima de digitos
	 */
	public Integer cantidadMaxima(ExistenciaDenominacionBean denominacion){
		if(denominacion.getValor() != null){
			BigDecimal maxLength = new BigDecimal("100000000000.00");
			BigInteger maxResult = maxLength.divide(denominacion.getValor(),0,RoundingMode.UP).toBigInteger();
			return maxResult.toString().length();
		}
		return null;
	}
	/**
	 * MÃ©todo utilizado para limpiar los valores de unidades e importes en la parrilla de denominaciones.
	 */
	public void limpiar(){
		limpiaParrilla();
		this.peticionEfectivoBean.setObservacion("");
		setMuestraDialogoLimpiar(false);
	}
	
	public String limpiaBean()
	{
		setMuestraDialogoSuprimirExitoso(false);
		this.peticionEfectivoBean=null;
		 return inicio();
				
	}
	
	public void nuevaPeticionUrgente()
	{
		limpiaParrilla();
		this.peticionEfectivoBean.setObservacion("");
		this.peticionEfectivoBean.setIndicadorUrgencia(0);
		this.peticionEfectivoBean.setMuestraBotonModificar(false);
		 this.peticionEfectivoBean.setMuestraBotonSuprimir(false);
		 if(this.peticionEfectivoBean.getMayorHoraLimite()==false)
		 {
		 this.peticionEfectivoBean.setGuardar(true);
		 }
		 else
		 {
			 this.peticionEfectivoBean.setGuardar(false);
		 }
	}
	
	public void loadPeticionUrgente()
	{
		if(this.peticionEfectivoBean.getIndicadorUrgencia() != 0){
			consultaPeticionEfectivo();
		}
	}
	
	public void limpiaParrilla(){
		this.peticionEfectivoBean.getParillaBean().setFiltro(0);
		for (ExistenciaDenominacionBean denominacion : this.peticionEfectivoBean.getParillaBean().getListaDenominaciones()) {
			denominacion.setImportePedido(null);
			denominacion.setUnidades(null);
		}
		actualizaTotalPeticion();
	}
	
	/**
	 * MÃ©todo utilizado para guardar la peticion de efectivo de  la parrilla de denominaciones.
	 */
	public String guardar()
	{
		if(this.peticionEfectivoBean.getTipoPeticion()==1)
		{
		
		altaPeticionEfectivoBackEnd.ejecutarTRN(this.peticionEfectivoBean, cajasUtilsBean.getFechaContableSiguienteInteger());
		}
		else
		{
			altaPeticionEfectivoBackEnd.ejecutarTRN(this.peticionEfectivoBean, cajasUtilsBean.getFechaSistemaInteger());
		}
		this.peticionEfectivoBean.getParillaBean().setFiltro(5);
		
		if(this.peticionEfectivoBean.getEstatusDescripcion() == null)
		{
			this.peticionEfectivoBean.setEstatusDescripcion("SOLICITADO");
		}
		else if(this.peticionEfectivoBean.getEstatusDescripcion().trim().length()==0)
		{
			this.peticionEfectivoBean.setEstatusDescripcion("SOLICITADO");
		}
		
		
		return rutaDetalle();
	}
	
	/**
	 * MÃ©todo utilizado para modificar la peticion de efectivo de  la parrilla de denominaciones.
	 */
	public String modificar()
	{
		if(this.peticionEfectivoBean.getTipoPeticion()==1)
		{
		
		modificaPeticionEfectivoBackEnd.ejecutarTRN(this.peticionEfectivoBean, cajasUtilsBean.getFechaContableSiguienteInteger());
		}
		else
		{
			modificaPeticionEfectivoBackEnd.ejecutarTRN(this.peticionEfectivoBean, cajasUtilsBean.getFechaSistemaInteger());
		}
		this.peticionEfectivoBean.getParillaBean().setFiltro(5);
		return rutaDetalle();
	}
	/**
	 * MÃ©todo utilizado para suprimir la peticion de efectivo de  la parrilla de denominaciones.
	 */
	public void suprimir()
	{
		if(this.peticionEfectivoBean.getTipoPeticion()==1)
		{
		
			suprimePeticionEfectivoBackEnd.ejecutarTRN(this.peticionEfectivoBean, cajasUtilsBean.getFechaContableSiguienteInteger(),cajasUtilsBean.getFechaSistemaInteger());
		}
		else
		{
			suprimePeticionEfectivoBackEnd.ejecutarTRN(this.peticionEfectivoBean, cajasUtilsBean.getFechaSistemaInteger(),cajasUtilsBean.getFechaSistemaInteger());
		}
		
		setMuestraDialogoSuprimirExitoso(true);
	}
	
	/**
	 * 
	 */
	public void cambiaTipoPeticion()
	{
		this.peticionEfectivoBean.setMuestraBotonModificar(false);
		this.peticionEfectivoBean.setMuestraBotonSuprimir(false);
		this.peticionEfectivoBean.setGuardar(true);
		this.peticionEfectivoBean.setObservacion("");
		actualizaFechaAbastecimiento();
		limpiar();
		this.peticionEfectivoBean.setEstatus("");
		this.peticionEfectivoBean.setEstatusDescripcion("");
		
		if(this.peticionEfectivoBean.getTipoPeticion()==1)
		{
			setMuestraComboUrgente(false);
			consultaPeticionEfectivo();
		}
		else
		{
			limpiar();
			consultaPeticionesUrgentes();
			 if(this.peticionEfectivoBean.getMayorHoraLimite()==false)
			 {
			 this.peticionEfectivoBean.setGuardar(true);
			 }
			 else
			 {
				 this.peticionEfectivoBean.setGuardar(false);
			 }
		}
		
	}
	
	public void consultaPeticionesUrgentes()
	{
//		this.peticionEfectivoBean.getComboUrgenteBean().setListaIndicadoresUrgentes(null);
		this.peticionEfectivoBean.getComboUrgenteBean().setListaIndicadoresUrgentes(new ArrayList<PeticionUrgenteBean>());

		consultaPeticionPorFechasBackEnd.ejecutarTRN(this.peticionEfectivoBean, cajasUtilsBean.getFechaSistemaInteger());
		
		if(this.peticionEfectivoBean.getComboUrgenteBean().getListaIndicadoresUrgentes() != null){
		
			if(this.peticionEfectivoBean.getComboUrgenteBean().getListaIndicadoresUrgentes().size() >0)
			{
			setMuestraComboUrgente(true);
			}
			else
			{
				setMuestraComboUrgente(false);
			}
		}
		else
		{
			setMuestraComboUrgente(false);
		}
		
	}
	
	
	/**
	 * MÃ©todo utilizado para inicializar la fecha de abastecimiento.
	 */
	public void actualizaFechaAbastecimiento()
	{
		if(this.peticionEfectivoBean.getTipoPeticion()==1)
		{
		this.peticionEfectivoBean.setFechaAbastecimiento(cajasUtilsBean.getFechaContableSiguiente());	
		}
		else
		{
			this.peticionEfectivoBean.setFechaAbastecimiento(cajasUtilsBean.getFechaSistema());	
		}
	}
	
	/**
	 * MÃ©todo para crear un archivo pdf con los datos desplegados en pantalla.
	 *
	 */
	public void printReport(){
		
		this.peticionEfectivoBean.setListaDenominaciones(this.peticionEfectivoBean.getParillaBean().getListaDenominaciones());
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
		params.put("NUM_URGENTE", this.peticionEfectivoBean.getIndicadorUrgencia());
		
		final Map<String, String> subReportes = new HashMap<String, String>();
		subReportes.put("DesglosePeticion.jrxml", "SUBREPORT_DENOMINACION");

		final Map<String, String> images = new HashMap<String, String>();
        images.put("Logo_bsfi_bn.png", "LOGO");

		final List<PeticionEfectivoBean> listaBeans = new ArrayList<PeticionEfectivoBean>();
		listaBeans.add(this.peticionEfectivoBean);
		pdfUtils.generaPdf("PeticionEfectivo.jrxml", params, images, subReportes, "PeticionEfectivo", listaBeans);
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
		beanList.put(PeticionEfectivoBean.class.getName(), this.peticionEfectivoBean);
		return beanList;
	}

	@Override
	protected void setBeanList(Map<String, Object> beanList) {
		if (null != beanList) {
			this.peticionEfectivoBean =  (PeticionEfectivoBean) beanList.get(PeticionEfectivoBean.class.getName());
			obtieneFlash().put(ParametrosFlashEnum.PETICION_EFECTIVO_BEAN.getParamFlash(), this.peticionEfectivoBean);
		}
		
	}

	@Override
	protected String getNombreFlujo() {
		return "Peticion de Efectivo: " +  contextoUtils.getTerminal();
	}

}
