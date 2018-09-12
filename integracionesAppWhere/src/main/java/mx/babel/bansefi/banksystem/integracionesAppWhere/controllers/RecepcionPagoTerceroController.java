package mx.babel.bansefi.banksystem.integracionesAppWhere.controllers;

import mx.babel.bansefi.banksystem.base.enums.integracionAppWhere.NavegacionEnum;
import mx.babel.bansefi.banksystem.base.enums.integracionAppWhere.UrlModuloEnum;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import java.io.Serializable;

@ManagedBean(name = "recepPagoTerceroController")
public class RecepcionPagoTerceroController extends GeneralController implements Serializable {

	/*
	 * Variables de clase.
	 */
	private static final long serialVersionUID = -9182841585023316075L;

	/*
	 * Iniciador del contexto manejado en Faces para poder recuperarlo.
	 */
	@PostConstruct
	private void init() {
		super.recuperarContextoFaces();
	}

	/*
	 * Metodos para renderizar vistas.
	 */
	public String recepcionPago() { return NavegacionEnum.RECEPCIONPAGO.getRuta(); }
	public String anulacionPago() { return NavegacionEnum.ANULACIONPAGO.getRuta(); }
	public String reporteOperaciones() { return NavegacionEnum.REPORTEOPERACIONES.getRuta(); }
	public String altaEmisores() { return NavegacionEnum.ALTAEMISORES.getRuta(); }
	public String modificaEmisores() { return NavegacionEnum.MODIFICAEMISORES.getRuta(); }
	public String configEntidades() { return NavegacionEnum.CONFIGENTIDADES.getRuta(); }
	public String reporteEntidad() { return NavegacionEnum.REPORTEENTIDAD.getRuta(); }
	public String reporteDiario() { return NavegacionEnum.REPORTEDIARIO.getRuta(); }

	/*
	 * Metodos para obtener url.
	 */
	public String urlRecepcionPago() { return UrlModuloEnum.RECEPCIONPAGO.getUrl(); }
	public String urlAnulacionPago() { return UrlModuloEnum.ANULACIONPAGO.getUrl(); }
	public String urlReporteOperaciones() { return UrlModuloEnum.REPORTEOPERACIONES.getUrl(); }
	public String urlAltaEmisores() { return UrlModuloEnum.ALTAEMISORES.getUrl(); }
	public String urlModificaEmisores() { return UrlModuloEnum.MODIFICAEMISORES.getUrl(); }
	public String urlConfigEntidades() { return UrlModuloEnum.CONFIGENTIDADES.getUrl(); }
	public String urlReporteEntidad() { return UrlModuloEnum.REPORTEENTIDAD.getUrl(); }
	public String urlReporteDiario() { return UrlModuloEnum.REPORTEDIARIO.getUrl(); }

}
