package mx.babel.bansefi.banksystem.cajas.controllers;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

import mx.babel.bansefi.banksystem.base.enums.ParametrosFlashEnum;
import mx.babel.bansefi.banksystem.base.utils.PdfUtils;
import mx.babel.bansefi.banksystem.cajas.beans.EnvioEntreOficinasBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@ManagedBean(name = "detalleEnvioEntreOficinas")
@ViewScoped
@Scope("view")
public class DetalleEnvioEntreOficinas {
	
	@Autowired
	private PdfUtils pdfUtils;
	
	private EnvioEntreOficinasBean envioEntreOficinasBean;

	public EnvioEntreOficinasBean getEnvioEntreOficinasBean() {
		return envioEntreOficinasBean;
	}

	public void setEnvioEntreOficinasBean(
			EnvioEntreOficinasBean envioEntreOficinasBean) {
		this.envioEntreOficinasBean = envioEntreOficinasBean;
	}
	
	/**
     * Se encarga de obtener el flash.
     * 
     * @return Flash con los datos de la pagina
     */
	public Flash obtieneFlash() {
		 return FacesContext.getCurrentInstance().getExternalContext().getFlash();
	}
	
	@PostConstruct
	public void init(){
		if(this.obtieneFlash().get(ParametrosFlashEnum.ENVIO_ENTRE_OFICINAS_BEAN.getParamFlash()) != null){
			this.envioEntreOficinasBean = (EnvioEntreOficinasBean) this.obtieneFlash().get(ParametrosFlashEnum.ENVIO_ENTRE_OFICINAS_BEAN.getParamFlash());
		}
	}
}
