package mx.babel.bansefi.banksystem.integracionesAppWhere.controllers;

import mx.babel.bansefi.banksystem.base.enums.integracionAppWhere.NavegacionEnum;
import mx.babel.bansefi.banksystem.base.enums.integracionAppWhere.UrlModuloEnum;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import java.io.Serializable;

@ManagedBean(name = "serCenCatProducController")
public class ServiciosCentralesCatalogoProductoController extends GeneralController implements Serializable {

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
	public String condiciones() { return NavegacionEnum.SERCENTCATALOGOPRODUCTOCONDICIONES.getRuta(); }
	public String productosVendibles() { return NavegacionEnum.SERCENTCATALOGOPRODUCTOPRODUCTOSVENDIBLES.getRuta(); }
	public String tarifasVendibles() { return NavegacionEnum.SERCENTCATALOGOPRODUCTOTARIFASVENDIBLES.getRuta(); }
	public String tarifasSimples() { return NavegacionEnum.SERCENTCATALOGOPRODUCTOTARIFASSIMPLES.getRuta(); }

	public String urlCondiciones() {
		return UrlModuloEnum.SERCENTCATALOGOPRODUCTOCONDICIONES.getUrl();
	}
	public String urlProductosVendibles() {
		return UrlModuloEnum.SERCENTCATALOGOPRODUCTOPRODUCTOSVENDIBLES.getUrl();
	}
	public String urlTarifasVendibles() {
		return UrlModuloEnum.SERCENTCATALOGOPRODUCTOTARIFASVENDIBLES.getUrl();
	}
	public String urlTarifasSimples() {
		return UrlModuloEnum.SERCENTCATALOGOPRODUCTOTARIFASSIMPLES.getUrl();
	}

}
