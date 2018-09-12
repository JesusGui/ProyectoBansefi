package mx.babel.bansefi.banksystem.cajas.controllers;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.sesion.utils.ContextoUtils;
import mx.babel.arq.storage.annotations.StoreStep;
import mx.babel.bansefi.banksystem.base.enums.NavegacionEnum;
import mx.babel.bansefi.banksystem.base.enums.ParametrosFlashEnum;
import mx.babel.bansefi.banksystem.base.utils.CatalogoUtils;
import mx.babel.bansefi.banksystem.cajas.backend.AutorizacionPeticionTotalesBackend;
import mx.babel.bansefi.banksystem.cajas.backend.ConsultaPeticionTotalesBackend;
import mx.babel.bansefi.banksystem.cajas.beans.AutorizacionPeticionTotalesBean;
import mx.babel.bansefi.banksystem.cajas.utils.FechaUtils;

import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@ManagedBean(name="autorizacionPeticionTotalesController")
@Component
@Scope("view")
public class AutorizacionPeticionTotalesController implements Serializable{

	private static final long serialVersionUID = -2962094806581601853L;
	
	@Autowired
	private ContextoUtils contextoUtils;
	@Autowired
	private CatalogoUtils catalogoUtils;
	@Autowired
	private ConsultaPeticionTotalesBackend peticionEfectivoTotBackend;
	@Autowired
	private AutorizacionPeticionTotalesBackend autorizacionPeticionEfectivoTotBackend;
	
	private Boolean mostrarTabla;
	
	private int fechaFormateada;
	
	private String mensajeError;
	
	private AutorizacionPeticionTotalesBean autorizacionPeticionTotalesBean;

	public AutorizacionPeticionTotalesBean getAutorizacionPeticionTotalesBean() {
		return autorizacionPeticionTotalesBean;
	}

	public void setAutorizacionPeticionTotalesBean(
			AutorizacionPeticionTotalesBean autorizacionPeticionTotalesBean) {
		this.autorizacionPeticionTotalesBean = autorizacionPeticionTotalesBean;
	}	

	public Boolean getMostrarTabla() {
		return mostrarTabla;
	}

	public void setMostrarTabla(Boolean mostrarTabla) {
		this.mostrarTabla = mostrarTabla;
	}

	public int getFechaFormateada() {
		return fechaFormateada;
	}

	public void setFechaFormateada(int fechaFormateada) {
		this.fechaFormateada = fechaFormateada;
	}
	
	public String getMensajeError() {
		return mensajeError;
	}

	public void setMensajeError(String mensajeError) {
		this.mensajeError = mensajeError;
	}

	@PostConstruct
	public void init(){
		this.autorizacionPeticionTotalesBean = new AutorizacionPeticionTotalesBean();
		this.autorizacionPeticionTotalesBean.setListaPeticiones(new ArrayList<AutorizacionPeticionTotalesBean>());
		this.autorizacionPeticionTotalesBean.setSaldoAnterior(new BigDecimal(0.00));
		this.autorizacionPeticionTotalesBean.setSaldoActual(new BigDecimal(0.00));
		this.autorizacionPeticionTotalesBean.setTotalPedido(new BigDecimal(0.00));
		this.autorizacionPeticionTotalesBean.setTotalAutorizado(new BigDecimal(0.00));
		this.mostrarTabla = false;
	}

	/**
	 * Método para navegar hacia los tipos de autorización
	 * @return ruta del tipo peticion de efectivo por totales
	 */
	@StoreStep
	public String inicio(){
		this.obtieneFlash().put(ParametrosFlashEnum.AUTORIZACION_PETICION_TOTALES_BEAN.getParamFlash(), this.autorizacionPeticionTotalesBean);
		return NavegacionEnum.AUTORIZACION_PETICION.getRuta();
	}
	

	/**
	 * Método que regresa a la pagina 
	 * 
	 * @return Página de inicio
	 */
	@StoreStep
	public String irInicio(){
		return NavegacionEnum.INICIO.getRuta();
	}
	
	/**
     * Se encarga de obtener el flash.
     * 
     * @return Flash con los datos de la página
     */
	public Flash obtieneFlash() {
	    return FacesContext.getCurrentInstance().getExternalContext().getFlash();
	}
	
	/**
	 * Método que se encarga de buscar las peticiones de efectivo en base a la fecha de abastecimiento
	 * 
	 */
	public void buscarPeticiones(){
		FechaUtils utileriaFecha = new FechaUtils();
		if(this.autorizacionPeticionTotalesBean.getFechaAbastecimiento() != null && !this.autorizacionPeticionTotalesBean.getFechaAbastecimiento().toString().equals("")){
			try{
				setFechaFormateada(utileriaFecha.formateoFecha(this.autorizacionPeticionTotalesBean.getFechaAbastecimiento()));
				if(utileriaFecha.validaFecha(getFechaFormateada())){
					peticionEfectivoTotBackend.ejecutarTRN(getFechaFormateada());
				}
				
				if(!obtenerLista().isEmpty() || obtenerLista().size() != 0){
					this.autorizacionPeticionTotalesBean.setListaPeticiones(obtenerLista());
					this.autorizacionPeticionTotalesBean.setSaldoAnterior(peticionEfectivoTotBackend.getSaldoAnterior());
					calculaTotalAutorizado();
					calculaSaldoActual();
					calculaTotalPedido();
					setMostrarTabla(true);
				}else{
					this.autorizacionPeticionTotalesBean.getListaPeticiones().clear();
					this.autorizacionPeticionTotalesBean.setTotalAutorizado(new BigDecimal(0.00));
					this.autorizacionPeticionTotalesBean.setTotalPedido(new BigDecimal(0.00));
					calculaTotalAutorizado();
					calculaSaldoActual();
					setMostrarTabla(true);
				}
			}catch (ControlableException ce){
				if(ce.getRtncod() == 7){
					setMensajeError("No existen peticiones para mostrar");
					RequestContext.getCurrentInstance().execute("PF('dlgMensajeErrorTotales').show();");
					RequestContext.getCurrentInstance().update("dlgMensajeErrorTotales");
				}else if(ce.getRtncod() != 1){
					setMensajeError(ce.getMensajeUsuario() + " - " + ce.getMensajeDetalle());
					RequestContext.getCurrentInstance().execute("PF('dlgMensajeErrorTotales').show();");
					RequestContext.getCurrentInstance().update("dlgMensajeErrorTotales");
				}
			}catch (ParseException pe){
				setMensajeError("Fecha no valida");
				RequestContext.getCurrentInstance().execute("PF('dlgMensajeErrorTotales').show();");
				RequestContext.getCurrentInstance().update("dlgMensajeErrorTotales");
			}
		}
	}
	
	/**
	 * Método que autoriza la peticiones de efectivo
	 * 
	 * */
	public void autorizarPeticion(){
		int respuesta = 0;
		try{
			if(!this.autorizacionPeticionTotalesBean.getListaPeticiones().isEmpty() && this.autorizacionPeticionTotalesBean.getListaPeticiones() != null
					&& this.autorizacionPeticionTotalesBean.getFechaAbastecimiento() != null){
				if(this.autorizacionPeticionTotalesBean.getTotalAutorizado().compareTo(BigDecimal.ZERO) > 0){
					respuesta = autorizacionPeticionEfectivoTotBackend.ejecutarTRN(this.autorizacionPeticionTotalesBean.getListaPeticiones(), fechaFormateada);
					if(respuesta == 1){
						RequestContext.getCurrentInstance().execute("PF('dlgOperacionCorrectaTotales').show();");
					}
				}else{
					setMensajeError("El importe total autorizado no puede ser cero.");
					RequestContext.getCurrentInstance().execute("PF('dlgMensajeErrorTotales').show()");
					RequestContext.getCurrentInstance().update("dlgMensajeErrorTotales");
				}
			}
		}catch (ControlableException ce){
			setMensajeError(ce.getMensajeDetalle());
			RequestContext.getCurrentInstance().execute("PF('dlgMensajeErrorTotales').show();");
			RequestContext.getCurrentInstance().update("dlgMensajeErrorTotales");
		}
	}
	
	public String irResumen(){
		this.obtieneFlash().put(ParametrosFlashEnum.AUTORIZACION_PETICION_TOTALES_BEAN.getParamFlash(), this.autorizacionPeticionTotalesBean);
		this.obtieneFlash().put(ParametrosFlashEnum.TOTAL_AUTORIZADO.getParamFlash(), this.autorizacionPeticionTotalesBean.getTotalAutorizado());
		return NavegacionEnum.RESUMEN_AUTORIZACION_PETICION_TOTALES.getRuta();
	}
	
	/**
	 * Método que modifica el importe autorizado de una petición y calcula de nuevo el importe autorizado total y el saldo actual.
	 * 
	 * @param AutorizacionPeticionTotalesBean
	 * 
	 */
	public void modificarImporteAutorizado(AutorizacionPeticionTotalesBean bean){
		for (int i = 0; i < this.autorizacionPeticionTotalesBean.getListaPeticiones().size(); i++) {
			if(bean.getCentro() == this.autorizacionPeticionTotalesBean.getListaPeticiones().get(i).getCentro()){
				this.autorizacionPeticionTotalesBean.getListaPeticiones().get(i).setImporteAutorizado(bean.getImporteAutorizado());
			}
		}
		calculaTotalAutorizado();
		calculaSaldoActual();
	}
	
	/**
	 * 
	 * Método que calcula el total de importe pedido
	 * 
	 * */
	public void calculaTotalPedido(){
		BigDecimal totalPedido = new BigDecimal("0");
		for(int i = 0; i < this.autorizacionPeticionTotalesBean.getListaPeticiones().size(); i++){
			totalPedido = totalPedido.add(this.autorizacionPeticionTotalesBean.getListaPeticiones().get(i).getImportePedido());
		}
		this.autorizacionPeticionTotalesBean.setTotalPedido(totalPedido);
	}
	
	/**
	 * 
	 * Método que calcula el total de importe autorizado
	 * 
	 * */
	public void calculaTotalAutorizado(){
		BigDecimal totalAutorizado = new BigDecimal("0");
		for(int i = 0; i < this.autorizacionPeticionTotalesBean.getListaPeticiones().size(); i++){
			totalAutorizado = totalAutorizado.add(this.autorizacionPeticionTotalesBean.getListaPeticiones().get(i).getImporteAutorizado());
		}
		this.autorizacionPeticionTotalesBean.setTotalAutorizado(totalAutorizado);
	}
	
	/**
	 * 
	 * Método que calcula el saldo actual del centro o sucursal
	 * 
	 * */
	public void calculaSaldoActual(){
		try{
			this.autorizacionPeticionTotalesBean.setSaldoActual(this.autorizacionPeticionTotalesBean.getSaldoAnterior().subtract(this.autorizacionPeticionTotalesBean.getTotalAutorizado()));
		} catch (ControlableException ce){
			throw ce;
		}
	}
	
	/**
	 * Método que obtiene la lista para el llenado de la tabla
	 * 
	 * @return lista de peticiones de efectivo
	 * 
	 * */
	public List<AutorizacionPeticionTotalesBean> obtenerLista(){
		return peticionEfectivoTotBackend.getListaPeticiones();
	}
	
	/**
	 * Método que limpia las cantidades calculadas y los importes autorizados
	 * 
	 */
	public void limpiarTabla(){
		for(AutorizacionPeticionTotalesBean autorizacion : this.autorizacionPeticionTotalesBean.getListaPeticiones()){
			autorizacion.setImporteAutorizado(new BigDecimal(0.00));
		}
		calculaTotalAutorizado();;
		calculaSaldoActual();	
	}
}
