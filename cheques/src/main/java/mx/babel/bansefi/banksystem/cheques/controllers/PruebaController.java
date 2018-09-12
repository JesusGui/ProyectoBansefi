package mx.babel.bansefi.banksystem.cheques.controllers;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

import mx.babel.bansefi.banksystem.base.enums.NavegacionEnum;
import mx.babel.bansefi.banksystem.cheques.beans.PruebaBean;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@ManagedBean
@Component
@Scope("view")
@ViewScoped
public class PruebaController {
	
	private PruebaBean pruebaBean;
	
	@PostConstruct
	public void init() {
		pruebaBean = new PruebaBean();
	}

	public String irInicio() {
		return NavegacionEnum.INICIO.getRuta();
	}
	
	public void limpiar() {
		pruebaBean.setCadena("");
	}

	public PruebaBean getPruebaBean() {
		return pruebaBean;
	}

	public void setPruebaBean(PruebaBean pruebaBean) {
		this.pruebaBean = pruebaBean;
	}
	
}
