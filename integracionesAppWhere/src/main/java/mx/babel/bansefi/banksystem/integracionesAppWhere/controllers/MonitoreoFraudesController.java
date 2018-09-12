package mx.babel.bansefi.banksystem.integracionesAppWhere.controllers;

import mx.babel.bansefi.banksystem.base.enums.integracionAppWhere.NavegacionEnum;
import mx.babel.bansefi.banksystem.base.enums.integracionAppWhere.UrlModuloEnum;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import java.io.Serializable;

@ManagedBean
public class MonitoreoFraudesController extends GeneralController implements Serializable {

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
	public String cltaIndicadores() { return NavegacionEnum.CONSULTAINDICADORES.getRuta(); }
	public String catalogoSucursales() { return NavegacionEnum.CATALOGOSUCURSALES.getRuta(); }
	public String catTransacciones() { return NavegacionEnum.CATALOGOTRANSACCIONES.getRuta(); }
	public String catCuentasAdmin() { return NavegacionEnum.CATALOGOCUENTASADMINISTRATIVAS.getRuta(); }
	public String catalogoEmpleados() { return NavegacionEnum.CATALOGOEMPLEADOS.getRuta(); }

	/*
	 * Metodos para obtener url de los modulos de Monitoreo de Fraudes.
	 */
	public String urlCatalogoCuentasAdministrativas() {
		return UrlModuloEnum.CATALOGOCUENTASADMINISTRATIVAS.getUrl();
	}
	public String urlCatalogoTransacciones() {
		return UrlModuloEnum.CATALOGOTRANSACCIONES.getUrl();
	}
	public String urlCatalogoSucursales() {
		return UrlModuloEnum.CATALOGOSUCURSALES.getUrl();
	}
	public String urlConsultaIndicadores() {
		return UrlModuloEnum.CONSULTAINDICADORES.getUrl();
	}
	public String urlCatalogoEmpleados() { return UrlModuloEnum.CATALOGOEMPLEADOS.getUrl(); }
	
}
