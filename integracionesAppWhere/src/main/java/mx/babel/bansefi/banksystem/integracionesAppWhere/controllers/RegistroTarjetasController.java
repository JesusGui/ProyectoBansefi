package mx.babel.bansefi.banksystem.integracionesAppWhere.controllers;

import mx.babel.bansefi.banksystem.base.enums.integracionAppWhere.NavegacionEnum;
import mx.babel.bansefi.banksystem.base.enums.integracionAppWhere.UrlModuloEnum;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import java.io.Serializable;

@ManagedBean
public class RegistroTarjetasController extends GeneralController implements Serializable {

	/*
	 * Variables de clase.
	 */
	private static final long serialVersionUID = 4624620171790127769L;

	/*
	 * Iniciador del contexto manejado en Faces para poder recuperarlo.
	 */
	@PostConstruct
	private void init() {
		super.recuperarContextoFaces();
	}

	/*
	 * Metodo para renderizar vistas.
	 */
	public String registrarTarjetas() { return NavegacionEnum.REGISTRARTARJETAS.getRuta(); }
	public String salidaOperativo() { return NavegacionEnum.SALIDAOPERATIVO.getRuta(); }
	public String formalizarTarjetas() { return NavegacionEnum.FORMALIZARTARJETAS.getRuta(); }
	public String NoEntregadasPorOpe() { return NavegacionEnum.TARJETASNOENTREGADASPOROPERATIVO.getRuta(); }
	public String reporteDetallado() { return NavegacionEnum.REPORTEDETALLADO.getRuta(); }

	/*
	 * Metodo para obtener url.
	 */
	public String urlRegistrarTarjetas() {
		return UrlModuloEnum.REGISTRARTARJETAS.getUrl();
	}
	public String urlSalidaOperativo() {
		return UrlModuloEnum.SALIDAOPERATIVO.getUrl();
	}
	public String urlFormalizarTarjetas() {
		return UrlModuloEnum.FORMALIZARTARJETAS.getUrl();
	}
	public String urlTarjetasNoEntregadasPorOperativo() { return UrlModuloEnum.TARJETASNOENTREGADASPOROPERATIVO.getUrl(); }
	public String urlReporteDetallado() {
		return UrlModuloEnum.REPORTEDETALLADO.getUrl();
	}
	
}
