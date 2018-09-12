package mx.babel.bansefi.banksystem.integracionesAppWhere.controllers;

import mx.babel.bansefi.banksystem.base.enums.integracionAppWhere.NavegacionEnum;
import mx.babel.bansefi.banksystem.base.enums.integracionAppWhere.UrlModuloEnum;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import java.io.Serializable;

@ManagedBean
public class OficinaController extends GeneralController implements Serializable {

	/*
	 * Variables de clase.
	 */
	private static final long serialVersionUID = 5366803570853818117L;

	/*
	 * Iniciador del contexto manejado en Faces para poder recuperarlo.
	 */
	@PostConstruct
	private void init() {
		super.recuperarContextoFaces();
	}

	/*
	 * Metodos para renderizar vistas de Oficina.
	 */
	public String diarioElectronico() { return NavegacionEnum.DIARIOELECTRONICO.getRuta(); }
	public String diarioElectronicoCriterios() { return NavegacionEnum.DIARIOELECTRONICOCRITERIOS.getRuta(); }
	public String ultimaTransaccion() { return NavegacionEnum.ULTIMATRANSACCION.getRuta(); }
	public String informacionPuesto() { return NavegacionEnum.INFORMACIONPUESTO.getRuta(); }
	public String definicionPuesto() { return NavegacionEnum.DEFINIRTIPOPUESTO.getRuta(); }
	public String cierreContable() { return NavegacionEnum.CIERRECONTABLE.getRuta(); }

	/*
	 * Metodos para obtener url de Oficina.
	 */
	public String urlDiarioElectronico() { return UrlModuloEnum.DIARIOELECTRONICO.getUrl(); }
	public String urlDiarioElectronicoCriterios() { return UrlModuloEnum.DIARIOELECTRONICOCRITERIOS.getUrl(); }
	public String urlUltimaTransaccion() { return UrlModuloEnum.ULTIMATRANSACCION.getUrl(); }
	public String urlInformacionPuesto() { return UrlModuloEnum.INFORMACIONPUESTO.getUrl(); }
	public String urlDefinirTipoPuesto() { return UrlModuloEnum.DEFINIRTIPOPUESTO.getUrl(); }
	public String urlCierreContable() { return UrlModuloEnum.CIERRECONTABLE.getUrl(); }

}
