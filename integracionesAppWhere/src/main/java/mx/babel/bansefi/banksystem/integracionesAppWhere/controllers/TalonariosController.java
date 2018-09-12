package mx.babel.bansefi.banksystem.integracionesAppWhere.controllers;

import mx.babel.bansefi.banksystem.base.enums.integracionAppWhere.NavegacionEnum;
import mx.babel.bansefi.banksystem.base.enums.integracionAppWhere.UrlModuloEnum;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import java.io.Serializable;

@ManagedBean
public class TalonariosController extends GeneralController implements Serializable {

	/*
	 * Variables de clase.
	 */
	private static final long serialVersionUID = -2731219895748632943L;

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
	public String cheques() { return NavegacionEnum.CHEQUES.getRuta(); }
	public String chequera() { return NavegacionEnum.CHEQUERA.getRuta(); }
	public String entregaChequera() { return NavegacionEnum.ENTREGACHEQUERA.getRuta(); }
	public String chequeraBanamex() { return NavegacionEnum.CHEQUERABANAMEX.getRuta(); }

	public String urlCheques() {
		return UrlModuloEnum.CHEQUES.getUrl();
	}
	public String urlChequera() {
		return UrlModuloEnum.CHEQUERA.getUrl();
	}
	public String urlEntregaChequera() {
		return UrlModuloEnum.ENTREGACHEQUERA.getUrl();
	}
	public String urlChequeraBanamex() {
		return UrlModuloEnum.CHEQUERABANAMEX.getUrl();
	}
	
}
