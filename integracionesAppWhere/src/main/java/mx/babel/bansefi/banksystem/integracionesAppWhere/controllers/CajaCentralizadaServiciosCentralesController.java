package mx.babel.bansefi.banksystem.integracionesAppWhere.controllers;

import mx.babel.bansefi.banksystem.base.enums.integracionAppWhere.NavegacionEnum;
import mx.babel.bansefi.banksystem.base.enums.integracionAppWhere.UrlModuloEnum;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import java.io.Serializable;

@ManagedBean(name = "cajaCenSerCenController")
public class CajaCentralizadaServiciosCentralesController extends GeneralController implements Serializable {

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
	public String gesCierreCaja() { return NavegacionEnum.GESTIONCIERRECAJABOVEDA.getRuta(); }
	public String gesParametrosOficina() { return NavegacionEnum.GESTIONPARAMETROSOFICINABOVEDA.getRuta(); }
	public String gesReferencias() { return NavegacionEnum.GESTIONREFERENCIASBOVEDA.getRuta(); }
	public String gesRecepcionEfectivo() { return NavegacionEnum.GESTIONRECEPCIONEFECTIVOBOVEDA.getRuta(); }
	public String gesAjusteExistencias() { return NavegacionEnum.GESTIONAJUSTEEXISTENCIASBOVEDA.getRuta(); }
	public String gesAutoPeticEfectivo() { return NavegacionEnum.GESTIONAUTOPETICIONEFECTIVOBOVEDA.getRuta(); }
	public String gesAutoPeticTotales() { return NavegacionEnum.GESTIONAUTOPETICIONTOTALESBOVEDA.getRuta(); }
	public String gesAutoPeticUrgentes() { return NavegacionEnum.GESTIONAUTOPETICIONURGENTESBOVEDA.getRuta(); }
	public String gesDiferencias() { return NavegacionEnum.GESTIONDIFERENCIASBOVEDA.getRuta(); }
	public String gesEncajes() { return NavegacionEnum.GESTIONENCAJESBOVEDA.getRuta(); }
	public String conDiferencias() { return NavegacionEnum.CONSULTADIFERENCIASBOVEDA.getRuta(); }
	public String conEfectivo() { return NavegacionEnum.CONSULTAEFECTIVOBOVEDA.getRuta(); }
	public String conEncajes() { return NavegacionEnum.CONSULTAENCAJESBOVEDA.getRuta(); }
	public String conExistencias() { return NavegacionEnum.CONSULTAEXISTENCIASBOVEDA.getRuta(); }
	public String conPeticiones() { return NavegacionEnum.CONSULTAPETICIONESBOVEDA.getRuta(); }
	public String conPeticPendientes() { return NavegacionEnum.CONSULTAPETICIONESPENDIENTESBOVEDA.getRuta(); }

	/*
	 * Metodos para obtener url.
	 */
	public String urlGesCierreCaja() { return UrlModuloEnum.GESTIONCIERRECAJABOVEDA.getUrl(); }
	public String urlGesParametrosOficina() { return UrlModuloEnum.GESTIONPARAMETROSOFICINABOVEDA.getUrl(); }
	public String urlGesReferencias() { return UrlModuloEnum.GESTIONREFERENCIASBOVEDA.getUrl(); }
	public String urlGesRecepcionEfectivo() { return UrlModuloEnum.GESTIONRECEPCIONEFECTIVOBOVEDA.getUrl(); }
	public String urlGesAjusteExistencias() { return UrlModuloEnum.GESTIONAJUSTEEXISTENCIASBOVEDA.getUrl(); }
	public String urlGesAutoPeticEfectivo() { return UrlModuloEnum.GESTIONAUTOPETICIONEFECTIVOBOVEDA.getUrl(); }
	public String urlGesAutoPeticTotales() { return UrlModuloEnum.GESTIONAUTOPETICIONTOTALESBOVEDA.getUrl(); }
	public String urlGesAutoPeticUrgentes() { return UrlModuloEnum.GESTIONAUTOPETICIONURGENTESBOVEDA.getUrl(); }
	public String urlGesDifencias() { return UrlModuloEnum.GESTIONDIFERENCIASBOVEDA.getUrl(); }
	public String urlGesEncajes() { return UrlModuloEnum.GESTIONENCAJESBOVEDA.getUrl(); }
	public String urlConDiferencias() { return UrlModuloEnum.CONSULTADIFERENCIASBOVEDA.getUrl(); }
	public String urlConEfectivo() { return UrlModuloEnum.CONSULTAEFECTIVOBOVEDA.getUrl(); }
	public String urlConEncajes() { return UrlModuloEnum.CONSULTAENCAJESBOVEDA.getUrl(); }
	public String urlConExistencias() { return UrlModuloEnum.CONSULTAEXISTENCIASBOVEDA.getUrl(); }
	public String urlConPeticiones() { return UrlModuloEnum.CONSULTAPETICIONESBOVEDA.getUrl(); }
	public String urlConPeticPendientes() { return UrlModuloEnum.CONSULTAPETICIONESPENDIENTESBOVEDA.getUrl(); }

}
