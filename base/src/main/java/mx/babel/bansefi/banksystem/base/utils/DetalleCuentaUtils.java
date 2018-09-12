package mx.babel.bansefi.banksystem.base.utils;

import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

import mx.babel.arq.catalogo.beans.CatalogoBean;
import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.sesion.utils.ContextoUtils;
import mx.babel.bansefi.banksystem.base.backends.ConsultaRelacionPersonaCuentaBackEnd;
import mx.babel.bansefi.banksystem.base.backends.anotaciones.ConsultaListaAnotacionesBackEnd;
import mx.babel.bansefi.banksystem.base.backends.consultatitular.ConsultaTitularBackend;
import mx.babel.bansefi.banksystem.base.backends.consultatitular.ConsultaTitularRes;
import mx.babel.bansefi.banksystem.base.beans.domain.AnotacionBean;
import mx.babel.bansefi.banksystem.base.beans.domain.relacioncuenta.RelacionadoBean;
import mx.babel.bansefi.banksystem.base.constants.ConstantesFuncionales;

/**
 * Bean para obtener los detalles de una cuenta y utilizarlos en el composite
 * de detalle de cuentas.
 * 
 * @author mario.montesdeoca
 *
 */
public class DetalleCuentaUtils {

    
	
	ContextoUtils contextoUtils;
	ConsultaTitularBackend consultaTitularBackend;
	ConsultaRelacionPersonaCuentaBackEnd consultaRelacionPersonaCuentaBackEnd;
	ConsultaListaAnotacionesBackEnd consultaAnotacionescuentaBackEnd;
	
	private List<CatalogoBean> codigosIdentificacion;
	private boolean muestraTitular;
	private boolean muestraTitularError;
	private boolean muestraDialogo;
	private String nombreTitular;
    private Long numeroCuenta;
    private String idExterno;
    private String codigoDocumento;
    private List<AnotacionBean> anotaciones;
    
    /**
     * Constructor de DetalleCuentaUtils
     * @param contextoUtils Contexto de la sesión
     * @param consultaTitular interfaz de consulta de titular
     */
	public DetalleCuentaUtils(ContextoUtils contextoUtils, 
			ConsultaTitularBackend consultaTitularBackend, 
			ConsultaRelacionPersonaCuentaBackEnd consultaRelacionPersonaCuentaBackEnd,
			ConsultaListaAnotacionesBackEnd consultaAnotacionescuentaBackEnd,
			List<CatalogoBean> codigosIdentificacion) {
		super();
		this.consultaTitularBackend = consultaTitularBackend;
		this.contextoUtils = contextoUtils;
		this.consultaRelacionPersonaCuentaBackEnd = consultaRelacionPersonaCuentaBackEnd;
		this.consultaAnotacionescuentaBackEnd = consultaAnotacionescuentaBackEnd;
		this.setCodigosIdentificacion(codigosIdentificacion);
	}
	
	public void busqueda(Long numeroCuenta, String nombreTitular){
		this.muestraTitular = true;
		this.numeroCuenta = numeroCuenta;
		this.nombreTitular = nombreTitular;
	}
	
	
	/**
	 * @return Atributo codigosIdentificacion
	 */
	public List<CatalogoBean> getCodigosIdentificacion() {
		return codigosIdentificacion;
	}

	/**
	 * @param codigosIdentificacion Atributo codigosIdentificacion a definir
	 */
	public void setCodigosIdentificacion(List<CatalogoBean> codigosIdentificacion) {
		this.codigosIdentificacion = codigosIdentificacion;
	}

	/**
	 * @return Atributo muestraTitular
	 */
	public boolean getMuestraTitular() {
		return muestraTitular;
	}

	/**
	 * @param muestraTitular Atributo muestraTitular a definir
	 */
	public void setMuestraTitular(boolean muestraTitular) {
		this.muestraTitular = muestraTitular;
	}
	
	 /**
	 * @return Atributo nombreTitular
	 */
	public String getNombreTitular() {
		return nombreTitular;
	}

	/**
	 * @param nombreTitular Atributo nombreTitular a definir
	 */
	public void setNombreTitular(String nombreTitular) {
		this.nombreTitular = nombreTitular;
	}

	/**
	 * @return Atributo numeroCuenta
	 */
	public Long getNumeroCuenta() {
		return numeroCuenta;
	}

	/**
	 * @param numeroCuenta Atributo numeroCuenta a definir
	 */
	public void setNumeroCuenta(Long numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	/**
	 * @return Atributo muestraDialogo
	 */
	public boolean isMuestraDialogo() {
		return muestraDialogo;
	}

	/**
	 * @param muestraDialogo Atributo muestraDialogo a definir
	 */
	public void setMuestraDialogo(boolean muestraDialogo) {
		this.muestraDialogo = muestraDialogo;
	}

	/**
	 * @return Atributo idExterno
	 */
	public String getIdExterno() {
		return idExterno;
	}

	/**
	 * @param idExterno Atributo idExterno a definir
	 */
	public void setIdExterno(String idExterno) {
		this.idExterno = idExterno;
	}

	/**
	 * @return Atributo codigoDocumento
	 */
	public String getCodigoDocumento() {
		return codigoDocumento;
	}

	/**
	 * @param codigoDocumento Atributo codigoDocumento a definir
	 */
	public void setCodigoDocumento(String codigoDocumento) {
		this.codigoDocumento = codigoDocumento;
	}
	
	/**
	 * 
	 * @return Atributo muestraTitularError
	 */
	public boolean getMuestraTitularError(){
		return muestraTitularError;
	}
	
	/**
	 * 
	 * @param muestraTitularError Atributo muestraTitularError a definir
	 */
	public void setMuestraTitularError(boolean muestraTitularError){
		this.muestraTitularError = muestraTitularError;
	}

	/**
	 * Método que obtiene el titular de la cuenta  y lo envía al composite detalle cuenta.
	 * @throws ControlableException
	 * @throws NoControlableException
	 */
	public void buscaTitular() throws ControlableException, NoControlableException{
		this.setMuestraTitular(false);
		this.setMuestraTitularError(false);
		ConsultaTitularRes respuesta= this.consultaTitularBackend.ejecutarWS(getNumeroCuenta().toString());
		if (respuesta != null && "0".equals(respuesta.getEstatus())) {
			this.muestraTitular = true;
			setNombreTitular(respuesta.getNombre());
			this.setAnotaciones(consultaAnotacionescuentaBackEnd.ejecutarTRN(getNumeroCuenta(),true));
		}else {
			this.muestraTitularError = true;
			this.muestraTitular = false;
        	
		}
	}
	
	/**
	 * Método que consulta los documentos asociados a una cuenta y verifica si el ingresado por el
	 * usuario se encuentra en dicha lista.
	 * 
	 * @return <code>true</code> en caso de que el documento ingresado forme parte de los documentos asociados.
	 * @throws ControlableException
	 * @throws NoControlableException
	 */
	public boolean verificarDocumento() throws ControlableException, NoControlableException{
		List<RelacionadoBean> relacionado = null; 
		Boolean existeRelacion = false;
		this.muestraDialogo = true;
		try{
			relacionado = consultaRelacionPersonaCuentaBackEnd.ejecutarTRN(this.numeroCuenta, false);
		}catch (NumberFormatException nfe){
			throw new ControlableException("No se puede realizar la consulta", "El formato de alguno de los parámetros es erroneo");
		}
		
		for (RelacionadoBean relacionadoBean : relacionado) {
			if (relacionadoBean.getPersona().getNumIdentificacion().equals(this.idExterno.toUpperCase()) && 
					relacionadoBean.getPersona().getTipoIdentificacion().trim().equals(this.codigoDocumento.trim())){
				existeRelacion = true;
			}
		}
		
		if(existeRelacion){
			this.muestraDialogo=false;
			return true;
		} else {
			return false;
		}
		
	}

	public List<AnotacionBean> getAnotaciones() {
		return anotaciones;
	}

	public void setAnotaciones(List<AnotacionBean> anotaciones) {
		this.anotaciones = anotaciones;
	}
	
	
	
	/**
	 * Se encarga de obtener el flash.
	 * 
	 * @return Flash con los datos de la pagina
	 */
	public Flash obtieneFlash() {
		return FacesContext.getCurrentInstance().getExternalContext()
				.getFlash();
	}
	
	public void ejecutarAnotaciones(Long cuenta){
		this.setAnotaciones(consultaAnotacionescuentaBackEnd.ejecutarTRN(cuenta, true));
		
	}
	
	


}
