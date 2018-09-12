package mx.babel.bansefi.banksystem.transacciones.beans;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import mx.babel.bansefi.banksystem.base.beans.domain.AnotacionBean;

/**
 * Bean de Retiro-
 * @author alejandro.perez
 *
 */

@ManagedBean
@ViewScoped
public class RetiroBean {

	private String operacionRetiro;
	private String identificacionClienteRetiro;
	private String numeroIdentificacionClienteRetiro;
	private Date fechaValorRetiro;
	private String usuarioRetiro;
	private Long cuentaRetiro;
	private BigDecimal importeRetiro;
	private String conceptoRetiro;
	private boolean mostrarUnidadesMillon;
	private boolean imprimirSaldoRetiro;
	private boolean cuentaPuenteRetiro;
	private Integer unidadesMillon;
	private boolean intervenientes;
	private String nivelCuenta;
	private List<AnotacionBean> anotaciones;
	
	/**
	 * Super Constructor con todos los campos.
	 * @param operacionRetiro - operacionRetiro
	 * @param identificacionClienteRetiro - identificacionClienteRetiro
	 * @param numeroIdentificacionClienteRetiro - numeroIdentificacionClienteRetiro
	 * @param fechaValorRetiro - fechaValorRetiro
	 * @param cuentaRetiro - cuentaRetiro
	 * @param importeRetiro - importeRetiro
	 * @param conceptoRetiro - conceptoRetiro
	 * @param mostrarUnidadesMillon - mostrarUnidadesMillon
	 * @param imprimirSaldoRetiro - imprimirSaldoRetiro
	 * @param cuentaPuenteRetiro - cuentaPuenteRetiro
	 * @param unidadesMillon - unidadesMillon
	 */
	public RetiroBean(String operacionRetiro,
			String identificacionClienteRetiro,
			String numeroIdentificacionClienteRetiro, Date fechaValorRetiro,
			Long cuentaRetiro, BigDecimal importeRetiro, 
			String conceptoRetiro,
			boolean mostrarUnidadesMillon, boolean imprimirSaldoRetiro,
			boolean cuentaPuenteRetiro, Integer unidadesMillon) {
		super();
		this.operacionRetiro = operacionRetiro;
		this.identificacionClienteRetiro = identificacionClienteRetiro;
		this.numeroIdentificacionClienteRetiro = 
				numeroIdentificacionClienteRetiro;
		this.fechaValorRetiro = fechaValorRetiro;
		this.cuentaRetiro = cuentaRetiro;
		this.importeRetiro = importeRetiro;
		this.conceptoRetiro = conceptoRetiro;
		this.mostrarUnidadesMillon = mostrarUnidadesMillon;
		this.imprimirSaldoRetiro = imprimirSaldoRetiro;
		this.cuentaPuenteRetiro = cuentaPuenteRetiro;
		this.unidadesMillon = unidadesMillon;
	}


	/**
	 * Constructor.
	 */
	public RetiroBean() {
		super();
	}


	/**
	 * Getters & Setters.
	 * @return the operacionRetiro
	 */
	public String getOperacionRetiro() {
		return operacionRetiro;
	}


	/**
	 * Getters & Setters.
	 * @param operacionRetiro the operacionRetiro to set
	 */
	public void setOperacionRetiro(String operacionRetiro) {
		this.operacionRetiro = operacionRetiro;
	}


	/**
	 * Getters & Setters.
	 * @return the identificacionClienteRetiro
	 */
	public String getIdentificacionClienteRetiro() {
		return identificacionClienteRetiro;
	}


	/**
	 * Getters & Setters.
	 * @param identificacionClienteRetiro the identificacionClienteRetiro to set
	 */
	public void setIdentificacionClienteRetiro(String identificacionClienteRetiro) {
		this.identificacionClienteRetiro = identificacionClienteRetiro;
	}


	/**
	 * Getters & Setters.
	 * @return the numeroIdentificacionClienteRetiro
	 */
	public String getNumeroIdentificacionClienteRetiro() {
		return numeroIdentificacionClienteRetiro;
	}


	/**
	 * Getters & Setters.
	 * @param numeroIdentificacionClienteRetiro 
	 * the numeroIdentificacionClienteRetiro to set
	 */
	public void setNumeroIdentificacionClienteRetiro(
			String numeroIdentificacionClienteRetiro) {
		this.numeroIdentificacionClienteRetiro = 
				numeroIdentificacionClienteRetiro;
	}


	/**
	 * Getters & Setters.
	 * @return the fechaValorRetiro
	 */
	public Date getFechaValorRetiro() {
		return fechaValorRetiro;
	}


	/**
	 * Getters & Setters.
	 * @param fechaValorRetiro the fechaValorRetiro to set
	 */
	public void setFechaValorRetiro(Date fechaValorRetiro) {
		this.fechaValorRetiro = fechaValorRetiro;
	}


	/**
	 * Getters & Setters.
	 * @return the usuarioRetiro
	 */
	public String getUsuarioRetiro() {
		return usuarioRetiro;
	}


	/**
	 * Getters & Setters.
	 * @param usuarioRetiro the usuarioRetiro to set
	 */
	public void setUsuarioRetiro(String usuarioRetiro) {
		this.usuarioRetiro = usuarioRetiro;
	}


	/**
	 * Getters & Setters.
	 * @return the cuentaRetiro
	 */
	public Long getCuentaRetiro() {
		return cuentaRetiro;
	}


	/**
	 * Getters & Setters.
	 * @param long1 the cuentaRetiro to set
	 */
	public void setCuentaRetiro(Long cuentaRetiro) {
		this.cuentaRetiro = cuentaRetiro;
	}


	/**
	 * Getters & Setters.
	 * @return the importeRetiro
	 */
	public BigDecimal getImporteRetiro() {
		return importeRetiro;
	}


	/**
	 * Getters & Setters.
	 * @param importeRetiro the importeRetiro to set
	 */
	public void setImporteRetiro(BigDecimal importeRetiro) {
		this.importeRetiro = importeRetiro;
	}


	/**
	 * Getters & Setters.
	 * @return the conceptoRetiro
	 */
	public String getConceptoRetiro() {
		return conceptoRetiro;
	}


	/**
	 * Getters & Setters.
	 * @param conceptoRetiro the conceptoRetiro to set
	 */
	public void setConceptoRetiro(String conceptoRetiro) {
		this.conceptoRetiro = conceptoRetiro;
	}


	/**
	 * Getters & Setters.
	 * @return the mostrarUnidadesMillon
	 */
	public boolean isMostrarUnidadesMillon() {
		return mostrarUnidadesMillon;
	}


	/**
	 * Getters & Setters.
	 * @param mostrarUnidadesMillon the mostrarUnidadesMillon to set
	 */
	public void setMostrarUnidadesMillon(boolean mostrarUnidadesMillon) {
		this.mostrarUnidadesMillon = mostrarUnidadesMillon;
	}


	/**
	 * Getters & Setters.
	 * @return the imprimirSaldoRetiro
	 */
	public boolean isImprimirSaldoRetiro() {
		return imprimirSaldoRetiro;
	}


	/**
	 * Getters & Setters.
	 * @param imprimirSaldoRetiro the imprimirSaldoRetiro to set
	 */
	public void setImprimirSaldoRetiro(boolean imprimirSaldoRetiro) {
		this.imprimirSaldoRetiro = imprimirSaldoRetiro;
	}


	/**
	 * Getters & Setters.
	 * @return the cuentaPuenteRetiro
	 */
	public boolean isCuentaPuenteRetiro() {
		return cuentaPuenteRetiro;
	}


	/**
	 * Getters & Setters.
	 * @param cuentaPuenteRetiro the cuentaPuenteRetiro to set
	 */
	public void setCuentaPuenteRetiro(boolean cuentaPuenteRetiro) {
		this.cuentaPuenteRetiro = cuentaPuenteRetiro;
	}


	/**
	 * Getters & Setters.
	 * @return the unidadesMillon
	 */
	public Integer getUnidadesMillon() {
		return unidadesMillon;
	}


	/**
	 * Getters & Setters.
	 * @param unidadesMillon the unidadesMillon to set
	 */
	public void setUnidadesMillon(Integer unidadesMillon) {
		this.unidadesMillon = unidadesMillon;
	}


	/**
	 * Getters & Setters.
	 * @return the intervenientes
	 */
	public boolean isIntervenientes() {
		return intervenientes;
	}


	/**
	 * Getters & Setters.
	 * @param intervenientes the intervenientes to set
	 */
	public void setIntervenientes(boolean intervenientes) {
		this.intervenientes = intervenientes;
	}
	
	/**
	 * Getters & Setters.
	 * @return nivelCuenta
	 */
	public String getnivelCuenta(){
		return nivelCuenta;
	}
	
	/**
	 * Getters & Setters.
	 * @param nivelCuenta
	 */
	public void setNivelCuenta(String nivelCuenta){
		this.nivelCuenta = nivelCuenta;
	}
	
	/**
	 * Getters & Setters
	 * @return anotaciones
	 */
	public List<AnotacionBean> getAnotaciones(){
		if(anotaciones == null){
			anotaciones = new ArrayList<AnotacionBean>();
		}
		return anotaciones;
	}
	
	/**
	 * Getters & Setters
	 * @param anotaciones
	 */
	public void setAnotaciones(List<AnotacionBean> anotaciones){
		this.anotaciones = anotaciones;
	}


	
}
