package mx.babel.bansefi.banksystem.integracionesAppWhere.controllers;

import mx.babel.bansefi.banksystem.base.enums.integracionAppWhere.NavegacionEnum;
import mx.babel.bansefi.banksystem.base.enums.integracionAppWhere.UrlModuloEnum;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import java.io.Serializable;

@ManagedBean(name = "parametrosTCBController")
public class ParametrosTCBController extends GeneralController implements Serializable {

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
	public String definicionParametros() { return NavegacionEnum.PARAMETROSTCBDEFINICIONPARAMETROS.getRuta(); }
	public String gruposProductos() { return NavegacionEnum.PARAMETROSTCBGRUPOSPRODUCTOS.getRuta(); }
	public String parametrosCondicion() { return NavegacionEnum.PARAMETROSTCBPARAMETROSCONDICION.getRuta(); }
	public String productosSimples() { return NavegacionEnum.PARAMETROSTCBPRODUCTOSSIMPLES.getRuta(); }
	public String valorFacial() { return NavegacionEnum.PARAMETROSTCBVALORFACIAL.getRuta(); }
	public String tablasReferenciales() { return NavegacionEnum.PARAMETROSTCBTABLASREFERENCIALES.getRuta(); }

	public String urlDefinicionParametros() {
		return UrlModuloEnum.PARAMETROSTCBDEFINICIONPARAMETROS.getUrl();
	}
	public String urlGruposProductos() {
		return UrlModuloEnum.PARAMETROSTCBGRUPOSPRODUCTOS.getUrl();
	}
	public String urlParametrosCondicion() {
		return UrlModuloEnum.PARAMETROSTCBPARAMETROSCONDICION.getUrl();
	}
	public String urlProductosSimples() {
		return UrlModuloEnum.PARAMETROSTCBPRODUCTOSSIMPLES.getUrl();
	}
	public String urlValorFacial() {
		return UrlModuloEnum.PARAMETROSTCBVALORFACIAL.getUrl();
	}
	public String urlTablasReferenciales() {
		return UrlModuloEnum.PARAMETROSTCBTABLASREFERENCIALES.getUrl();
	}

}
