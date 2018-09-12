package mx.babel.bansefi.banksystem.integracionesAppWhere.controllers;

import mx.babel.bansefi.banksystem.base.enums.integracionAppWhere.NavegacionEnum;
import mx.babel.bansefi.banksystem.base.enums.integracionAppWhere.UrlModuloEnum;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import java.io.Serializable;

@ManagedBean(name = "cajaCenSucController")
public class CajaCentralSucursalController extends GeneralController implements Serializable {

	/*
	 * Variables de clase.
	 */
	private static final long serialVersionUID = -7407391829861790176L;

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
	public String gestionDiferencias() { return NavegacionEnum.GESTIONDIFERENCIAS.getRuta(); }
	public String gestionPeticionEfectivo() { return NavegacionEnum.GESTIONPETICIONEFECTIVO.getRuta(); }
	public String gestionRecepcionEfectivo() { return NavegacionEnum.GESTIONRECEPCIONEFECTIVO.getRuta(); }
	public String gestionRecogidaEfectivo() { return NavegacionEnum.GESTIONRECOGIDAEFECTIVO.getRuta(); }
	public String consultaDiferencias() { return NavegacionEnum.CONSULTADIFERENCIAS.getRuta(); }
	public String consultaEfectivo() { return NavegacionEnum.CONSULTAEFECTIVO.getRuta(); }
	public String consultaEncajes() { return NavegacionEnum.CONSULTAENCAJES.getRuta(); }
	public String consultaEncajesAsignados() { return NavegacionEnum.CONSULTAENCAJESASIGNADOS.getRuta(); }
	public String consultaExistencias() { return NavegacionEnum.CONSULTAEXISTENCIAS.getRuta(); }
	public String consultaPeticiones() { return NavegacionEnum.CONSULTAPETICIONES.getRuta(); }
	public String consultaTipoInteres() { return NavegacionEnum.CONSULTATIPOINTERES.getRuta(); }

	/*
	 * Metodos para obtener url.
	 */
	public String urlGestionDiferencias() {
		return UrlModuloEnum.GESTIONDIFERENCIAS.getUrl();
	}
	public String urlGestionPeticionEfectivo() {
		return UrlModuloEnum.GESTIONPETICIONEFECTIVO.getUrl();
	}
	public String urlGestionRecepcionEfectivo() {
		return UrlModuloEnum.GESTIONRECEPCIONEFECTIVO.getUrl();
	}
	public String urlGestionRecogidaEfectivo() {
		return UrlModuloEnum.GESTIONRECOGIDAEFECTIVO.getUrl();
	}
	public String urlConsultaDiferencias() {
		return UrlModuloEnum.CONSULTADIFERENCIAS.getUrl();
	}
	public String urlConsultaEfectivo() {
		return UrlModuloEnum.CONSULTAEFECTIVO.getUrl();
	}
	public String urlConsultaEncajes() {
		return UrlModuloEnum.CONSULTAENCAJES.getUrl();
	}
	public String urlConsultaEncajesAsignados() {
		return UrlModuloEnum.CONSULTAENCAJESASIGNADOS.getUrl();
	}
	public String urlConsultaExistencias() {
		return UrlModuloEnum.CONSULTAEXISTENCIAS.getUrl();
	}
	public String urlConsultaPeticiones() {
		return UrlModuloEnum.CONSULTAPETICIONES.getUrl();
	}
	public String urlConsultaTipoInteres() {
		return UrlModuloEnum.CONSULTATIPOINTERES.getUrl();
	}

}
