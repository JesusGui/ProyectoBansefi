package mx.babel.bansefi.banksystem.oficina.controllers;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

import mx.babel.arq.sesion.utils.ContextoUtils;
import mx.babel.bansefi.banksystem.base.enums.NavegacionEnum;
import mx.babel.bansefi.banksystem.oficina.backends.ContadoresCentroBackEnd;

import mx.babel.bansefi.banksystem.oficina.beans.ContadoresCentroBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Fecha de Creación: 3 de Junio de 2015 Objetivo: Controlador encargado de
 * realizar la consulta de Contadores de Centro
 * 
 * @author manuel.nieto
 * 
 */
@ManagedBean(name = "contadoresCentroController")
@ViewScoped
@Component
@Scope("view")
public class ContadoresCentroController implements Serializable {

    private static final long serialVersionUID = 219078356151939864L;

    @Autowired
	private ContextoUtils contextoUtils;

	// Contadores Generales
	private ContadoresCentroBean contadoresCentroBean;
	
	//Backend
	@Autowired
	private ContadoresCentroBackEnd contadoresCentroBackend;

	private String numeroCentro;

	/**
	 * Funcion que regresa la ruta para acceder a la vista de este controlador
	 * 
	 * @return
	 */
	public String inicio() {
		return NavegacionEnum.CONTADORES_CENTRO.getRuta();
	}

	@PostConstruct
	public void init() {

		numeroCentro = contextoUtils.getTerminal().substring(2, 6);

		// Contadores de Centro Caja

		this.contadoresCentroBean = new ContadoresCentroBean();
		this.contadoresCentroBean.setCodigoCentro(contextoUtils.getTerminal()
				.substring(2, 6));
		
		/**
		 * Ejecucion de consultas TRN's
		 */
		consultaContadoresCentro();
		
	}

	/**
	 * Ejecuta la TRN para consultar los contadores de centro
	 */
	public void consultaContadoresCentro() {
		contadoresCentroBean = contadoresCentroBackend
				.ejecutarTRN(contadoresCentroBean);
	}	

	public String getNumeroCentro() {
		return numeroCentro;
	}

	public void setNumeroCentro(String numeroCentro) {
		this.numeroCentro = numeroCentro;
	}

	public ContadoresCentroBean getContadoresCentroBean() {
		return contadoresCentroBean;
	}

	public void setContadoresCentroBean(
			ContadoresCentroBean contadoresCentroBean) {
		this.contadoresCentroBean = contadoresCentroBean;
	}

	/**
	 * Mètodo para navegaciòn a inicio
	 * 
	 * @return ruta de inicio
	 */
	public String rutaInicio() {
		return NavegacionEnum.INICIO.getRuta();
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

	/**
	 * Función para adicionar alertas globales en la vista
	 * 
	 * @param severity
	 *            Severidad de la alerta.
	 * @param title
	 *            Titulo de la alerta.
	 * @param message
	 *            Mensaje desplegado en la alerta.
	 */
	private void addMessage(FacesMessage.Severity severity, String title,
			String message) {
		FacesMessage facesMessage = new FacesMessage(severity, title, message);
		FacesContext.getCurrentInstance().addMessage("contadoresCentro",
				facesMessage);
	}

}
