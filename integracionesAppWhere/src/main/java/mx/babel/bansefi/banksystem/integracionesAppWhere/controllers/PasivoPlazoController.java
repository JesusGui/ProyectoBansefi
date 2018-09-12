package mx.babel.bansefi.banksystem.integracionesAppWhere.controllers;

import mx.babel.bansefi.banksystem.base.enums.integracionAppWhere.NavegacionEnum;
import mx.babel.bansefi.banksystem.base.enums.integracionAppWhere.UrlModuloEnum;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import java.io.Serializable;

@ManagedBean
public class PasivoPlazoController extends GeneralController implements Serializable {

	/*
	 * Variables de clase.
	 */
	private static final long serialVersionUID = 3103563701540579874L;

	/*
	 * Iniciador del contexto manejado en Faces para poder recuperarlo.
	 */
	@PostConstruct
	private void init() {
		super.recuperarContextoFaces();
	}

	/*
	 * Metodos para renderizar vistas de Monitoreo de Fraudes.
	 */
	public String AltaDepPF() { return NavegacionEnum.DEPOSITOPLAZOFIJO.getRuta(); }
	public String DepPlazoFijo() { return NavegacionEnum.CONSULTADEPOSITOPLAZOFIJO.getRuta(); }
	public String ConsDepPlFCM() { return NavegacionEnum.ANULAPLAZOFIJO.getRuta(); }
	public String ConsPlazoFijo() { return NavegacionEnum.CONSULTAMARCO.getRuta(); }
	public String ConsultMovim() { return NavegacionEnum.CONSULTAMOVIMIENTOSPLAZOFIJO.getRuta(); }

	/*
	 * Metodos para obtener url de los modulos de Monitoreo de Fraudes.
	 */
	public String urlDepositoPlazoFijo() {
		return UrlModuloEnum.DEPOSITOPLAZOFIJO.getUrl();
	}
	public String urlCltaDepositoPlazoFijo() {
		return UrlModuloEnum.CONSULTADEPOSITOPLAZOFIJO.getUrl();
	}
	public String urlAnulaPlazoFijo() {
		return UrlModuloEnum.ANULAPLAZOFIJO.getUrl();
	}
	public String urlConsultaMarco() {
		return UrlModuloEnum.CONSULTAMARCO.getUrl();
	}
	public String urlConsultaMovimientos() { return UrlModuloEnum.CONSULTAMOVIMIENTOSPLAZOFIJO.getUrl(); }
	
}
