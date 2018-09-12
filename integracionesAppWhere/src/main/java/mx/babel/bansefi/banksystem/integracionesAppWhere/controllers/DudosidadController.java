package mx.babel.bansefi.banksystem.integracionesAppWhere.controllers;

import mx.babel.arq.sesion.utils.ContextoUtils;
import mx.babel.bansefi.banksystem.base.enums.integracionAppWhere.NavegacionEnum;
import mx.babel.bansefi.banksystem.base.enums.integracionAppWhere.UrlModuloEnum;
import mx.babel.bansefi.banksystem.integracionesAppWhere.services.IIntegracionService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import java.io.Serializable;

@ManagedBean
public class DudosidadController extends GeneralController implements Serializable {

	/**
	 * Variables de clase.
	 */
	private static final long serialVersionUID = -9182841585023316075L;
	private ContextoUtils contextoUtils;
	private IIntegracionService integracionService;

	/**
     * Inyeccion de dependencias.
     */
	@Autowired
	public void setContextoUtils(ContextoUtils contextoUtils) {
		this.contextoUtils = contextoUtils;
	}

	@Autowired
	public void setIntegracionService(IIntegracionService integracionService) {
		this.integracionService = integracionService;
	}

	/**
	 * Iniciador del contexto manejado en Faces para poder recuperarlo.
	 */

	@PostConstruct
	private void init() {
		super.recuperarContextoFaces();
	}

	/*
	 * Metodos para renderizar vistas
	 */
	public String consClasifCuentas() { return NavegacionEnum.DUDOSIDADCONSULTACLASIFICACIONCUENTAS.getRuta(); }
	public String defClasifCuentas() { return NavegacionEnum.DUDOSIDADDEFINICIONCLASIFICACIONCUENTAS.getRuta(); }
	public String consFormCuentas() { return NavegacionEnum.DUDOSIDADCONSULTAFORMULACUENTAS.getRuta(); }
	public String defFormCuentas() { return NavegacionEnum.DUDOSIDADDEFINICIONFORMULACUENTAS.getRuta(); }
	public String consRetroCuentas() { return NavegacionEnum.DUDOSIDADCONSULTARETROCESIONCUENTAS.getRuta(); }
	public String defRetroCuentas() { return NavegacionEnum.DUDOSIDADDEFINICIONRETROCESIONCUENTAS.getRuta(); }
	public String consDotaciones() { return NavegacionEnum.DUDOSIDADCONSULTADOTACIONES.getRuta(); }
	public String defDotaciones() { return NavegacionEnum.DUDOSIDADDEFINICIONDOTACIONES.getRuta(); }
	public String consRazValidas() { return NavegacionEnum.DUDOSIDADCONSULTARAZONESVALIDAS.getRuta(); }
	public String defRazValidas() { return NavegacionEnum.DUDOSIDADDEFINICIONRAZONESVALIDAS.getRuta(); }
	public String implParametros() { return NavegacionEnum.DUDOSIDADIMPLANTARPARAMETROS.getRuta(); }
	public String consDudCuenta() { return NavegacionEnum.DUDOSIDADCONSULTADUDOSIDADCUENTA.getRuta(); }
	public String reclasDudCuenta() { return NavegacionEnum.DUDOSIDADRECLASIFICACIONDUDOSIDADCUENTA.getRuta(); }

	public String urlConsClasifCuentas() { return UrlModuloEnum.DUDOSIDADCONSULTACLASIFICACIONCUENTAS.getUrl(); }
	public String urlDefClasifCuentas() { return UrlModuloEnum.DUDOSIDADDEFINICIONCLASIFICACIONCUENTAS.getUrl(); }
	public String urlConsFormCuentas() { return UrlModuloEnum.DUDOSIDADCONSULTAFORMULACUENTAS.getUrl(); }
	public String urlDefFormCuentas() { return UrlModuloEnum.DUDOSIDADDEFINICIONFORMULACUENTAS.getUrl(); }
	public String urlConsRetroCuentas() { return UrlModuloEnum.DUDOSIDADCONSULTARETROCESIONCUENTAS.getUrl(); }
	public String urlDefRetroCuentas() { return UrlModuloEnum.DUDOSIDADDEFINICIONRETROCESIONCUENTAS.getUrl(); }
	public String urlConsDotaciones() { return UrlModuloEnum.DUDOSIDADCONSULTADOTACIONES.getUrl(); }
	public String urlDefDotaciones() { return UrlModuloEnum.DUDOSIDADDEFINICIONDOTACIONES.getUrl(); }
	public String urlConsRazValidas() { return UrlModuloEnum.DUDOSIDADCONSULTARAZONESVALIDAS.getUrl(); }
	public String urlDefRazValidas() { return UrlModuloEnum.DUDOSIDADDEFINICIONRAZONESVALIDAS.getUrl(); }
	public String urlImplParametros() { return UrlModuloEnum.DUDOSIDADIMPLANTARPARAMETROS.getUrl(); }
	public String urlConsDudCuenta() { return UrlModuloEnum.DUDOSIDADCONSULTADUDOSIDADCUENTA.getUrl(); }
	public String urlReclasDudCuenta() { return UrlModuloEnum.DUDOSIDADRECLASIFICACIONDUDOSIDADCUENTA.getUrl(); }

	/*
     * Metodos para generacion de bsfoperador.
     */
	public String generarBsfOperadorDudosidad(String target) {
		return integracionService.getBsfOperadorDudosidad(contextoUtils, target);
	}

}
