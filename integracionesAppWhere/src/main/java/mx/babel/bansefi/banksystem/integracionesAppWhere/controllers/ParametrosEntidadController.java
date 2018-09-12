package mx.babel.bansefi.banksystem.integracionesAppWhere.controllers;

import mx.babel.bansefi.banksystem.base.enums.integracionAppWhere.NavegacionEnum;
import mx.babel.bansefi.banksystem.base.enums.integracionAppWhere.UrlModuloEnum;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import java.io.Serializable;

@ManagedBean(name = "pEntidadController")
public class ParametrosEntidadController extends GeneralController implements Serializable {

	private static final long serialVersionUID = -3455681595417927817L;
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
	public String acuerdos() { return NavegacionEnum.PENTIDADACUERDOS.getRuta(); }
	public String titulares() { return NavegacionEnum.PENTIDADTITULARES.getRuta(); }
	public String cajaCent() { return NavegacionEnum.PENTIDADCAJACENT.getRuta(); }
	public String centros() { return NavegacionEnum.PENTIDADCENTROS.getRuta(); }
	public String liquidaciones() { return NavegacionEnum.PENTIDADLIQUIDACIONES.getRuta(); }
	public String pasivoGrales() { return NavegacionEnum.PENTIDADPASIVOGRALES.getRuta(); }
	public String dudosidad() { return NavegacionEnum.PENTIDADDUDOSIDAD.getRuta(); }
	public String tablasRef() { return NavegacionEnum.PENTIDADTABLASREF.getRuta(); }
	public String pasivoAport() { return NavegacionEnum.PENTIDADPASIVOAPORT.getRuta(); }
	public String pasivoPrioCar() { return NavegacionEnum.PENTIDADPASIVOPRIOCAR.getRuta(); }
	public String cheques() { return NavegacionEnum.PENTIDADCHEQUES.getRuta(); }

	public String urlAcuerdos() {
		return UrlModuloEnum.PENTIDADACUERDOS.getUrl();
	}
	public String urlTitulares() {
		return UrlModuloEnum.PENTIDADTITULARES.getUrl();
	}
	public String urlCajaCent() {
		return UrlModuloEnum.PENTIDADCAJACENT.getUrl();
	}
	public String urlCentros() {
		return UrlModuloEnum.PENTIDADCENTROS.getUrl();
	}
	public String urlLiquidaciones() {
		return UrlModuloEnum.PENTIDADLIQUIDACIONES.getUrl();
	}
	public String urlPasivoGrales() {
		return UrlModuloEnum.PENTIDADPASIVOGRALES.getUrl();
	}
	public String urlDudosidad() {
		return UrlModuloEnum.PENTIDADDUDOSIDAD.getUrl();
	}
	public String urlTablasRef() {
		return UrlModuloEnum.PENTIDADTABLASREF.getUrl();
	}
	public String urlPasivoAport() {
		return UrlModuloEnum.PENTIDADPASIVOAPORT.getUrl();
	}
	public String urlPasivoPrioCar() {
		return UrlModuloEnum.PENTIDADPASIVOPRIOCAR.getUrl();
	}
	public String urlCheques() {
		return UrlModuloEnum.PENTIDADCHEQUES.getUrl();
	}

}
