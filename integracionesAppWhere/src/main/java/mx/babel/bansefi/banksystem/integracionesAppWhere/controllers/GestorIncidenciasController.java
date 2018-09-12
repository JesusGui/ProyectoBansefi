package mx.babel.bansefi.banksystem.integracionesAppWhere.controllers;

import mx.babel.bansefi.banksystem.base.enums.integracionAppWhere.NavegacionEnum;
import mx.babel.bansefi.banksystem.base.enums.integracionAppWhere.UrlModuloEnum;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import java.io.Serializable;

@ManagedBean
public class GestorIncidenciasController extends GeneralController implements Serializable {

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
	 * Metodos para renderizar vistas de Reporte Retiros.
	 */
	public String consulta() { return NavegacionEnum.CONSULTAINCIDENCIAS.getRuta(); }

	/*
	 * Metodos para obtener url de Reportes Retiros.
	 */
	public String urlConsultaIncidencias() {
		return UrlModuloEnum.CONSULTAINCIDENCIAS.getUrl();
	}
	
}
