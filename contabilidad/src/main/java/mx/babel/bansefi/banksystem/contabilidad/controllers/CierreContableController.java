package mx.babel.bansefi.banksystem.contabilidad.controllers;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import mx.babel.arq.sesion.utils.ContextoUtils;
import mx.babel.bansefi.banksystem.base.enums.NavegacionEnum;
import mx.babel.bansefi.banksystem.contabilidad.backends.CierreContableBackend;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Controlador encargado del flujo de cierre contable de oficina.
 * 
 * @author omar.marquez
 */
@ManagedBean
@ViewScoped
@Component
@Scope("view")
public class CierreContableController implements Serializable {

	private static final long serialVersionUID = 7190826447747573245L;

	private String contrasena;
	private boolean contrasenaCorrecta;
	private boolean contrasenaVacia;

	@Autowired
	ContextoUtils contextoUtils;

	@Autowired
	CierreContableBackend cierreContableBackEnd;

	/**
	 * Constructor.
	 */
	public CierreContableController() {
		super();
	}

	@PostConstruct
	public void init() {
		this.contrasena = null;
		this.contrasenaCorrecta = false;
		this.contrasenaVacia = true;
	}
	
	/**
	 * Metodo que cargar la ruta de inicio de la ventana.
	 * @return
	 */
	public String inicio(){
		return NavegacionEnum.CIERRE_CONTABLE.getRuta();
	}

	/**
	 * Método que devuelve la contraseña del cajero principal.
	 * 
	 * @return contrasena
	 */
	public String getContrasena() {
		return contrasena;
	}

	/**
	 * Método que establece la contraseña del cajero principal que realizará el
	 * cierre de la oficina.
	 * 
	 * @param contrasena
	 */
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	/**
	 * Método que devuelve un indicador booleano para determinar sí la
	 * contraseña es correcta.
	 * 
	 * @return indicador booleano
	 */
	public boolean isContrasenaCorrecta() {
		return contrasenaCorrecta;
	}

	/**
	 * Método que establece el valor del indicador para determinar sí la
	 * contraseña es correcta.
	 * 
	 * @param contrasenaCorrecta
	 */
	public void setContrasenaCorrecta(boolean contrasenaCorrecta) {
		this.contrasenaCorrecta = contrasenaCorrecta;
	}

	public boolean isContrasenaVacia() {
		return contrasenaVacia;
	}

	public void setContrasenaVacia(boolean contrasenaVacia) {
		this.contrasenaVacia = contrasenaVacia;
	}

	/**
	 * Método que redirige al usuario a la página de inicio.
	 * 
	 * @return ruta del recurso a mostrar
	 */
	public String cancelarCierre() {
		return NavegacionEnum.INICIO.getRuta();
	}

	/**
	 * Método que verifica que la contraseña introducida sea correcta.
	 */
	public void verificarContrasena() {
		if (StringUtils.isNotBlank(contrasena)) {
			contrasenaVacia = false;
			if (contrasena.equals(contextoUtils.getPwd())) {
				contrasenaCorrecta = true;
				RequestContext.getCurrentInstance().execute(
						"PF('dlgConfirmarCierreContable').show()");
			} else {
				contrasenaCorrecta = false;
			}
		} else {
			contrasenaVacia = true;
		}
	}

	/**
	 * Método que ejecuta la TRN para realizar el cierre contable de oficina.
	 */
	public void realizarCierre() {
		// TODO Implementar llamado a backend.
		int respuesta = cierreContableBackEnd.ejecutarTRN();

		if (respuesta == 1) {
			RequestContext.getCurrentInstance().execute(
					"PF('dlgExitoCierreContable').show();");
		} else {
			RequestContext.getCurrentInstance().execute(
					"PF('dlgErrorCierreContable').show();");
		}

	}

}