package mx.babel.bansefi.banksystem.integracionesAppWhere.controllers;

import mx.babel.bansefi.banksystem.base.enums.integracionAppWhere.NavegacionEnum;
import mx.babel.bansefi.banksystem.base.enums.integracionAppWhere.UrlModuloEnum;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import java.io.Serializable;

@ManagedBean
public class TraspasoController extends GeneralController implements Serializable {

	private static final long serialVersionUID = 2108172159205718826L;

	/*
	 * Iniciador del contexto manejado en Faces para poder recuperarlo.
	 */
	@PostConstruct
	private void init() {
		super.recuperarContextoFaces();
	}

	/*
	 * Metodo para renderizar vista de reporte
	 */
	public String traspasoCuentas() { return NavegacionEnum.TRASPASOENTRECUENTAS.getRuta(); }

	public String urlTraspasoCuentas() {
		return UrlModuloEnum.TRASPASOENTRECUENTAS.getUrl();
	}
	
}
