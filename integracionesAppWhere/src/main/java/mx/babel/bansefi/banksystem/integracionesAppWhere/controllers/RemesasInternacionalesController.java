package mx.babel.bansefi.banksystem.integracionesAppWhere.controllers;

import mx.babel.bansefi.banksystem.base.enums.integracionAppWhere.NavegacionEnum;
import mx.babel.bansefi.banksystem.base.enums.integracionAppWhere.UrlModuloEnum;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import java.io.Serializable;

@ManagedBean(name = "remInterController")
public class RemesasInternacionalesController extends GeneralController implements Serializable {

	/*
	 * Variables de clase.
	 */
	private static final long serialVersionUID = -2230404741233824047L;


	/*
	 * Iniciador del contexto manejado en Faces para poder recuperarlo.
	 */
	@PostConstruct
	private void init() {
		super.recuperarContextoFaces();
	}

	/*
	 * Metodos para renderizar vistas de Remesas Internacionales.
	 */
	public String abonoCuenta() { return NavegacionEnum.REMINTABONOCUENTA.getRuta(); }
	public String aclaracion() { return NavegacionEnum.REMINTACLARACION.getRuta(); }
	public String configComisionEntidad() { return NavegacionEnum.REMINTCONFIGURACIONCOMISIONESENTIDAD.getRuta(); }
	public String configComisionRemesa() { return NavegacionEnum.REMINTCONFIGURACIONCOMISIONESREMESADORA.getRuta(); }
	public String consulComisionEntidad() { return NavegacionEnum.REMINTCONSULTACOMISIONESENTIDAD.getRuta(); }
	public String consulComisionRemesa() { return NavegacionEnum.REMINTCONSULTACOMISIONESREMESADORA.getRuta(); }
	public String confingLimites() { return NavegacionEnum.REMINTCONFIGURACIONLIMITES.getRuta(); }
	public String consulMovimientos() { return NavegacionEnum.REMINTCONSULTAMOVIMIENTOS.getRuta(); }
	public String consulMovEntidad() { return NavegacionEnum.REMINTCONSULTAMOVIMIENTOSENTIDAD.getRuta(); }
	public String consulMovRemesa() { return NavegacionEnum.REMINTCONSULTAMOVIMIENTOSREMESADORA.getRuta(); }
	public String altaEntidad() { return NavegacionEnum.REMINTALTAENTIDAD.getRuta(); }
	public String modificacionEntidad() { return NavegacionEnum.REMINTMODIFICACIONENTIDAD.getRuta(); }
	public String consulOperacionCentral() { return NavegacionEnum.REMINTCONSULTAOPERACIONCENTRAL.getRuta(); }
	public String pagoAutoCentral() { return NavegacionEnum.REMINTPAGOAUTORIZACIONCENTRAL.getRuta(); }
	public String pagoVentanilla() { return NavegacionEnum.REMINTPAGOVENTANILLA.getRuta(); }
	public String altaRemesadora() { return NavegacionEnum.REMINTALTAREMESADORA.getRuta(); }
	public String modificacionRemesadora() { return NavegacionEnum.REMINTMODIFICACIONREMESADORA.getRuta(); }
	public String liquidacionEntidades() { return NavegacionEnum.REMINTLIQUIDACIONENTIDADES.getRuta(); }
	public String reportesRegulatorios() { return NavegacionEnum.REMINTREPORTESREGULATORIOS.getRuta(); }
	public String reporteDiarioMov() { return NavegacionEnum.REMINTREPORTEDIARIOMOVIMIENTOS.getRuta(); }
	public String poliza() { return NavegacionEnum.REMINTPOLIZA.getRuta(); }

	//#{remInterController.urlConfigComisionEntidad()}
	/*
	 * Metodos para obtener url de Remesas Internacionales.
	 */
	public String urlAbonoCuenta() {
		return UrlModuloEnum.REMINTABONOCUENTA.getUrl();
	}
	public String urlAclaracion() {
		return UrlModuloEnum.REMINTACLARACION.getUrl();
	}
	public String urlConfigComisionEntidad() {
		return UrlModuloEnum.REMINTCONFIGURACIONCOMISIONESENTIDAD.getUrl();
	}
	public String urlConfigComisionRemesa() {
		return UrlModuloEnum.REMINTCONFIGURACIONCOMISIONESREMESADORA.getUrl();
	}
	public String urlConsulComisionEntidad() {
		return UrlModuloEnum.REMINTCONSULTACOMISIONESENTIDAD.getUrl();
	}
	public String urlConsulComisionRemesa() {
		return UrlModuloEnum.REMINTCONSULTACOMISIONESREMESADORA.getUrl();
	}
	public String urlConfigLimites() {
		return UrlModuloEnum.REMINTCONFIGURACIONLIMITES.getUrl();
	}
	public String urlConsulMovimientos() {
		return UrlModuloEnum.REMINTCONSULTAMOVIMIENTOS.getUrl();
	}
	public String urlConsulMovEntidad() {
		return UrlModuloEnum.REMINTCONSULTAMOVIMIENTOSENTIDAD.getUrl();
	}
	public String urlConsulMovRemesa() {
		return UrlModuloEnum.REMINTCONSULTAMOVIMIENTOSREMESADORA.getUrl();
	}
	public String urlAltaEntidad() {
		return UrlModuloEnum.REMINTALTAENTIDAD.getUrl();
	}
	public String urlModificacionEntidad() {
		return UrlModuloEnum.REMINTMODIFICACIONENTIDAD.getUrl();
	}
	public String urlConsulOperacionCentral() {
		return UrlModuloEnum.REMINTCONSULTAOPERACIONCENTRAL.getUrl();
	}
	public String urlPagoAutoCentral() {
		return UrlModuloEnum.REMINTPAGOAUTORIZACIONCENTRAL.getUrl();
	}
	public String urlPagoVentanilla() {
		return UrlModuloEnum.REMINTPAGOVENTANILLA.getUrl();
	}
	public String urlAltaRemesadora() {
		return UrlModuloEnum.REMINTALTAREMESADORA.getUrl();
	}
	public String urlModificacionRemesadora() {
		return UrlModuloEnum.REMINTMODIFICACIONREMESADORA.getUrl();
	}
	public String urlLiquidacionEntidades() {
		return UrlModuloEnum.REMINTLIQUIDACIONENTIDADES.getUrl();
	}
	public String urlReportesRegulatorios() {
		return UrlModuloEnum.REMINTREPORTESREGULATORIOS.getUrl();
	}
	public String urlReporteDiarioMov() {
		return UrlModuloEnum.REMINTREPORTEDIARIOMOVIMIENTOS.getUrl();
	}
	public String urlPoliza() {
		return UrlModuloEnum.REMINTPOLIZA.getUrl();
	}

}
