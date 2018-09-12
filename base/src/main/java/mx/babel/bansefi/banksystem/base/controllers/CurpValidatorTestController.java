package mx.babel.bansefi.banksystem.base.controllers;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@ManagedBean(name="curpValidatorTestController")
@ViewScoped
@Component
@Scope("view")
public class CurpValidatorTestController {

	private String curp;
	
	public String guardar(){
		return "pruebaCurp.xhtml";
	}

	public String getCurp() {
		return curp;
	}

	public void setCurp(String curp) {
		this.curp = curp;
	}
	
	
}
