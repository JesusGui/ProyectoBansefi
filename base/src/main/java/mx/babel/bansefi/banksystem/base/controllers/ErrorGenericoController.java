package mx.babel.bansefi.banksystem.base.controllers;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import mx.babel.bansefi.banksystem.base.enums.NavegacionEnum;

import org.primefaces.context.RequestContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Clase controller para la vista de error
 * 
 * @author gerard.chavez
 * 
 */
@ManagedBean
@ViewScoped
@Component
@Scope("view")
public class ErrorGenericoController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8454621847772921948L;
	private String mensajeError;
	private String mensajeDetalleError;
	private String redireccion;
	private boolean mostrarAceptar;

	@PostConstruct
	void init() {
		if(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("msgException")!=null){
			//en caso de recibir un controller con datos es necesario mostrar el mensaje de error
			//con la información recibida
			ErrorGenericoController errorGenericoController = (ErrorGenericoController)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("msgException");
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("msgException");
			this.mensajeError = errorGenericoController.getMensajeError();
			this.mensajeDetalleError = errorGenericoController.getMensajeDetalleError();
			this.redireccion = errorGenericoController.getRedireccion();
			this.mostrarAceptar = errorGenericoController.isMostrarAceptar();
			RequestContext.getCurrentInstance().execute("PF('dlgExcepcion').show();");
		}else{
			this.mensajeError ="";
			this.mensajeDetalleError="";
			this.redireccion="";
			this.mostrarAceptar = false;
		}
	}

	public String navegaDestino() {
		if(this.getMensajeDetalleError().contains("Usuario no Firmado") 
				|| this.getMensajeDetalleError().contains("IP HEADER-LOGON INCORRECTA")){
			return NavegacionEnum.LOGIN.getRuta();
		}else {
			return this.redireccion;
		}
	}
	
	public String getMensajeError() {
		return mensajeError;
	}

	public void setMensajeError(String mensajeError) {
		this.mensajeError = mensajeError;
	}
	
	public String getMensajeDetalleError() {
		return mensajeDetalleError;
	}

	public void setMensajeDetalleError(String mensajeDetalleError) {
		this.mensajeDetalleError = mensajeDetalleError;
	}

	public String getRedireccion() {
		return redireccion;
	}

	public void setRedireccion(String redireccion) {
		this.redireccion = redireccion;
	}

	public boolean isMostrarAceptar() {
		return mostrarAceptar;
	}

	public void setMostrarAceptar(boolean mostrarAceptar) {
		this.mostrarAceptar = mostrarAceptar;
	}

}
