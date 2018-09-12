package mx.babel.bansefi.banksystem.integracionesAppWhere.controllers;

import mx.babel.bansefi.banksystem.base.enums.integracionAppWhere.NavegacionEnum;
import mx.babel.bansefi.banksystem.base.enums.integracionAppWhere.UrlModuloEnum;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import java.io.Serializable;

@ManagedBean(name = "remNacController")
public class RemesasNacionalesController extends GeneralController implements Serializable {

	/*
	 * Variables de clase.
	 */
	private static final long serialVersionUID = 2942367561315180712L;

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
	public String originar() { return NavegacionEnum.REMNACORIGINAR.getRuta(); }
	public String liquidar() { return NavegacionEnum.REMNACLIQUIDAR.getRuta(); }
	public String reembolsos() { return NavegacionEnum.REMNACREEMBOLSOS.getRuta(); }
	public String consultaOperaciones() {
		return NavegacionEnum.REMNACCONSULTAOPERACIONES.getRuta();
	}
	public String configuracionEntidad(){
		return NavegacionEnum.REMNACCONFIGURACIONENTIDAD.getRuta();
	}
	public String configuracionComisiones() { return NavegacionEnum.REMNACCONFIGURACIONCOMISIONES.getRuta(); }
	public String configuracionLimites() { return NavegacionEnum.REMNACCONFIGURACIONLIMITES.getRuta(); }
	public String aclaraciones() { return NavegacionEnum.REMNACACLARACIONES.getRuta(); }
	public String reporteOperaciones() {
		return NavegacionEnum.REMNACREPORTEOPERACIONES.getRuta();
	}
	public String reportePolizas() { return NavegacionEnum.REMNACREPORTEPOLIZAS.getRuta(); }
	public String reporteConciliacion() { return NavegacionEnum.REMNACREPORTECONCILIACION.getRuta(); }
	public String reporteComisiones(){
		return NavegacionEnum.REMNACREPORTECOMISIONES.getRuta();
	}

	/*
	 * Metodos para obtener url.
	 */
	public String urlOriginar() { return UrlModuloEnum.REMNACORIGINAR.getUrl(); }
	public String urlLiquidar() { return UrlModuloEnum.REMNACLIQUIDAR.getUrl(); }
	public String urlReembolsos() { return UrlModuloEnum.REMNACREEMBOLSOS.getUrl(); }
	public String urlConsultaOperaciones(){
		return UrlModuloEnum.REMNACCONSULTAOPERACIONES.getUrl();
	}
	public String urlConfiguracionEntidad(){
		return UrlModuloEnum.REMNACCONFIGURACIONENTIDAD.getUrl();
	}
	public String urlConfiguracionComisiones() { return UrlModuloEnum.REMNACCONFIGURACIONCOMISIONES.getUrl(); }
	public String urlConfiguracionLimites() { return UrlModuloEnum.REMNACCONFIGURACIONLIMITES.getUrl(); }
	public String urlAclaraciones() { return UrlModuloEnum.REMNACACLARACIONES.getUrl(); }
	public String urlReporteOperaciones(){
		return UrlModuloEnum.REMNACREPORTEOPERACIONES.getUrl();
	}
	public String urlReportePolizas() { return UrlModuloEnum.REMNACREPORTEPOLIZAS.getUrl(); }
	public String urlReporteConciliacion() { return UrlModuloEnum.REMNACREPORTECONCILIACION.getUrl(); }
	public String urlReporteComisiones(){
		return UrlModuloEnum.REMNACREPORTECOMISIONES.getUrl();
	}
}
