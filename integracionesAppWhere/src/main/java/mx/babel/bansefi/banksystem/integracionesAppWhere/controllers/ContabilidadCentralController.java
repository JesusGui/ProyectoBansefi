package mx.babel.bansefi.banksystem.integracionesAppWhere.controllers;

import mx.babel.bansefi.banksystem.base.enums.integracionAppWhere.NavegacionEnum;
import mx.babel.bansefi.banksystem.base.enums.integracionAppWhere.UrlModuloEnum;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import java.io.Serializable;

@ManagedBean(name = "serCentContaController")
public class ContabilidadCentralController extends GeneralController implements Serializable {

	private static final long serialVersionUID = 8839401458155406204L;
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
	public String apunteManual() { return NavegacionEnum.CONTABILIDADCENTRALAPUNTEMANUAL.getRuta(); }
	public String cuentas() { return NavegacionEnum.CONTABILIDADCENTRALCUENTAS.getRuta(); }
	public String estructura() { return NavegacionEnum.CONTABILIDADCENTRALESTRUCTURA.getRuta(); }
	public String partidasPendientes() { return NavegacionEnum.CONTABILIDADCENTRALPARTIDASPENDIENTES.getRuta(); }
	public String tallerContable() { return NavegacionEnum.CONTABILIDADCENTRALTALLERCONTABLE.getRuta(); }

	public String urlApunteManual() {
		return UrlModuloEnum.CONTABILIDADCENTRALAPUNTEMANUAL.getUrl();
	}
	public String urlCuentas() {
		return UrlModuloEnum.CONTABILIDADCENTRALCUENTAS.getUrl();
	}
	public String urlEstructura() {
		return UrlModuloEnum.CONTABILIDADCENTRALESTRUCTURA.getUrl();
	}
	public String urlPartidasPendientes() {
		return UrlModuloEnum.CONTABILIDADCENTRALPARTIDASPENDIENTES.getUrl();
	}
	public String urlTallerContable() {
		return UrlModuloEnum.CONTABILIDADCENTRALTALLERCONTABLE.getUrl();
	}

}
