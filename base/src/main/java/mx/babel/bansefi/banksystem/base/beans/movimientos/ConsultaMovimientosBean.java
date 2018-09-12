package mx.babel.bansefi.banksystem.base.beans.movimientos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mx.babel.arq.comun.beans.PaginacionBean;
import mx.babel.bansefi.banksystem.base.beans.domain.SaldoBean;
import mx.babel.bansefi.banksystem.base.utils.DetalleCuentaUtils;

/**
 * Bean con datos para consulta de movimientos
 * 
 * @author mario.montesdeoca
 *
 */
public class ConsultaMovimientosBean {
	
	private Date fechaDesde;
	
	private Date fechaHasta;

	private List<MovimientoBean> retenciones;
	
	private List<MovimientoBean> autorizaciones;
	
	private List<MovimientoBean> bloqueos;
	
	private List<MovimientoBean> movimientos;
	
	private DetalleCuentaUtils detalleCuentaUtils;
	
	private SaldoBean saldoCuenta;
	
	private boolean aplicarComision;
	
	private boolean tieneRetenciones;

	private boolean tieneAutorizaciones;
	
	private boolean tieneBloqueos;
	
	private boolean tieneMovimientos;
	
	private PaginacionBean paginacionMovimientos;
	
	private boolean errorRetenciones;
	
	private boolean errorBloqueos;
	
	private boolean errorMovimientos;

	/**
	 * @return Atributo fechaDesde
	 */
	public Date getFechaDesde() {
		return fechaDesde;
	}

	/**
	 * @param fechaDesde Atributo fechaDesde a definir
	 */
	public void setFechaDesde(Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	/**
	 * @return Atributo fechaHasta
	 */
	public Date getFechaHasta() {
		return fechaHasta;
	}

	/**
	 * @param fechaHasta Atributo fechaHasta a definir
	 */
	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

	/**
	 * @return Atributo retenciones
	 */
	public List<MovimientoBean> getRetenciones() {
		if(retenciones == null){
			retenciones = new ArrayList<MovimientoBean>();
		}
		return retenciones;
	}

	/**
	 * @param retenciones Atributo retenciones a definir
	 */
	public void setRetenciones(List<MovimientoBean> retenciones) {
		this.retenciones = retenciones;
	}

	/**
	 * @return Atributo autorizaciones
	 */
	public List<MovimientoBean> getAutorizaciones() {
		if(autorizaciones == null){
			autorizaciones = new ArrayList<MovimientoBean>();
		}
		return autorizaciones;
	}

	/**
	 * @param autorizaciones Atributo autorizaciones a definir
	 */
	public void setAutorizaciones(List<MovimientoBean> autorizaciones) {
		this.autorizaciones = autorizaciones;
	}

	/**
	 * @return Atributo bloqueos
	 */
	public List<MovimientoBean> getBloqueos() {
		if(bloqueos == null){
			bloqueos = new ArrayList<MovimientoBean>();
		}
		return bloqueos;
	}

	/**
	 * @param bloqueos Atributo bloqueos a definir
	 */
	public void setBloqueos(List<MovimientoBean> bloqueos) {
		this.bloqueos = bloqueos;
	}

	/**
	 * @return Atributo movimientos
	 */
	public List<MovimientoBean> getMovimientos() {
		if(movimientos == null){
			movimientos = new ArrayList<MovimientoBean>();
		}
		return movimientos;
	}

	/**
	 * @param movimientos Atributo movimientos a definir
	 */
	public void setMovimientos(List<MovimientoBean> movimientos) {
		this.movimientos = movimientos;
	}
	
	public void setMasMovimientos(List<MovimientoBean> movimientos){
		this.movimientos.addAll(movimientos);
	}

	/**
	 * @return Atributo detalleCuentaUtils
	 */
	public DetalleCuentaUtils getDetalleCuentaUtils() {
		return detalleCuentaUtils;
	}

	/**
	 * @param detalleCuentaUtils Atributo detalleCuentaUtils a definir
	 */
	public void setDetalleCuentaUtils(DetalleCuentaUtils detalleCuentaUtils) {
		this.detalleCuentaUtils = detalleCuentaUtils;
	}

	/**
	 * @return Atributo saldoCuenta
	 */
	public SaldoBean getSaldoCuenta() {
		return saldoCuenta;
	}

	/**
	 * @param saldoCuenta Atributo saldoCuenta a definir
	 */
	public void setSaldoCuenta(SaldoBean saldoCuenta) {
		this.saldoCuenta = saldoCuenta;
	}

	/**
	 * @return Atributo aplicarComision
	 */
	public boolean isAplicarComision() {
		return aplicarComision;
	}

	/**
	 * @param aplicarComision Atributo aplicarComision a definir
	 */
	public void setAplicarComision(boolean aplicarComision) {
		this.aplicarComision = aplicarComision;
	}

	/**
	 * @return Atributo tieneRetenciones
	 */
	public boolean getTieneRetenciones() {
		if(retenciones.isEmpty()){
			tieneRetenciones = false;
		}else{
			tieneRetenciones = true;
		}
		return tieneRetenciones;
	}

	/**
	 * @param tieneRetenciones Atributo tieneRetenciones a definir
	 */
	public void setTieneRetenciones(boolean tieneRetenciones) {
		this.tieneRetenciones = tieneRetenciones;
	}

	/**
	 * @return Atributo tieneAutorizaciones
	 */
	public boolean getTieneAutorizaciones() {
		if(autorizaciones.isEmpty()){
			tieneAutorizaciones = false;
		}else{
			tieneAutorizaciones = true;
		}
		return tieneAutorizaciones;
	}

	/**
	 * @param tieneAutorizaciones Atributo tieneAutorizaciones a definir
	 */
	public void setTieneAutorizaciones(boolean tieneAutorizaciones) {
		this.tieneAutorizaciones = tieneAutorizaciones;
	}

	/**
	 * @return Atributo tieneBloqueos
	 */
	public boolean getTieneBloqueos() {
		if(bloqueos.isEmpty()){
			tieneBloqueos = false;
		}else{
			tieneBloqueos = true;
		}
		return tieneBloqueos;
	}

	/**
	 * @param tieneBloqueos Atributo tieneBloqueos a definir
	 */
	public void setTieneBloqueos(boolean tieneBloqueos) {
		this.tieneBloqueos = tieneBloqueos;
	}
	
	/**
	 * 
	 * @return valor que indica si la cuenta tiene movimientos
	 */
	public boolean getTieneMovimientos(){
		if(movimientos.isEmpty()){
			tieneMovimientos = false;
		}else{
			tieneMovimientos = true;
		}
		return tieneMovimientos;
	}
	
	/**
	 * 
	 * @param tieneMovimientos valor a asignar T / F si la cuenta
	 * tiene movimientos o no
	 */
	public void setTieneMovimientos(boolean tieneMovimientos){
		this.tieneMovimientos = tieneMovimientos;
	}

	/**
	 * 
	 * @return Atributo paginacionMovimientos
	 */
	public PaginacionBean getPaginacionMovimientos() {
		if(this.paginacionMovimientos == null){
			paginacionMovimientos = new PaginacionBean();
			paginacionMovimientos.setUltimoDatoConsultaAnterior(0);
		}
		return paginacionMovimientos;
	}

	/**
	 * @param paginacionMovimientos Atributo paginacionMovimientos
	 * a definir.
	 */
	public void setPaginacionMovimientos(PaginacionBean paginacionMovimientos) {
		this.paginacionMovimientos = paginacionMovimientos;
	}

	/**
	 * @return valor que indica si hubo error al otener 
	 * la retenciones de la cuenta
	 */
	public boolean isErrorRetenciones() {
		return errorRetenciones;
	}

	/**
	 * @param errorRetenciones Atributo errorRetenciones
	 * a definir
	 */
	public void setErrorRetenciones(boolean errorRetenciones) {
		this.errorRetenciones = errorRetenciones;
	}

	/**
	 * @return Valor que indica si hubo error al obtener los
	 * bloqueos de la cuenta
	 */
	public boolean isErrorBloqueos() {
		return errorBloqueos;
	}

	/**
	 * @param errorBloqueos Atributo errorBloqueos
	 * a definir
	 */
	public void setErrorBloqueos(boolean errorBloqueos) {
		this.errorBloqueos = errorBloqueos;
	}

	/**
	 * @return valor que indica si hubo error al 
	 * obtener los  movimientos de la cueta
	 */
	public boolean isErrorMovimientos() {
		return errorMovimientos;
	}

	/**
	 * @param errorMovimientos Atributo errorMovimientos
	 * a  definir
	 */
	public void setErrorMovimientos(boolean errorMovimientos) {
		this.errorMovimientos = errorMovimientos;
	}
	
}
