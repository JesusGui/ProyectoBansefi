package mx.babel.bansefi.banksystem.integracionesAppWhere.controllers;

import mx.babel.bansefi.banksystem.base.enums.integracionAppWhere.NavegacionEnum;
import mx.babel.bansefi.banksystem.base.enums.integracionAppWhere.UrlModuloEnum;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import java.io.Serializable;

@ManagedBean
public class CreditosController extends GeneralController implements Serializable {

	private static final long serialVersionUID = 8356412382903713308L;
	/*
	 * Iniciador del contexto manejado en Faces para poder recuperarlo.
	 */

	@PostConstruct
	private void init() {
		super.recuperarContextoFaces();
	}

	/*
	 * Metodos para renderizar vistas
	 */
	public String cuadroLimite() { return NavegacionEnum.CUADROLIMITE.getRuta(); }
	public String anulaCobro() { return NavegacionEnum.ANULACOBRO.getRuta(); }
	public String anulaFactura() { return NavegacionEnum.ANULAFACTURA.getRuta(); }
	public String cobroTotal() { return NavegacionEnum.COBROTOTAL.getRuta(); }

	public String urlCuadroLimite() {
		return UrlModuloEnum.CUADROLIMITE.getUrl();
	}
	public String urlAnulaCobro() {
		return UrlModuloEnum.ANULACOBRO.getUrl();
	}
	public String urlAnulaFactura() {
		return UrlModuloEnum.ANULAFACTURA.getUrl();
	}
	public String urlCobroTotal() {
		return UrlModuloEnum.COBROTOTAL.getUrl();
	}

}
