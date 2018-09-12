package mx.babel.bansefi.banksystem.integracionesAppWhere.controllers;

import mx.babel.bansefi.banksystem.base.enums.integracionAppWhere.NavegacionEnum;
import mx.babel.bansefi.banksystem.base.enums.integracionAppWhere.UrlModuloEnum;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import java.io.Serializable;

@ManagedBean
public class PrestamosController extends GeneralController implements Serializable {

	private static final long serialVersionUID = 8356412382903713308L;
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
	public String cobroRecibo() { return NavegacionEnum.PRESTAMOCOBRORECIBOS.getRuta(); }
	public String cuadroAmorti() { return NavegacionEnum.PRESTAMOCUADROAMORTIZACION.getRuta(); }
	public String dispParcial() { return NavegacionEnum.PRESTAMODISPOSICIONPARCIAL.getRuta(); }
	public String amortiAnt() { return NavegacionEnum.PRESTAMOAMORTIZACIONANTICIPADA.getRuta(); }
	public String anulCobro() { return NavegacionEnum.PRESTAMOANULACIONCOBRO.getRuta(); }
	public String anulFactura() { return NavegacionEnum.PRESTAMOANULACIONFACTURACION.getRuta(); }
	public String cobroParcial() { return NavegacionEnum.PRESTAMOCOBROPARCIAL.getRuta(); }
	public String cobroTotal() { return NavegacionEnum.PRESTAMOCOBROTOTAL.getRuta(); }

	public String urlCobroRecibo() {
		return UrlModuloEnum.PRESTAMOCOBRORECIBOS.getUrl();
	}
	public String urlCuadroAmorti() {
		return UrlModuloEnum.PRESTAMOCUADROAMORTIZACION.getUrl();
	}
	public String urlDispParcial() {
		return UrlModuloEnum.PRESTAMODISPOSICIONPARCIAL.getUrl();
	}
	public String urlAmortiAnt() {
		return UrlModuloEnum.PRESTAMOAMORTIZACIONANTICIPADA.getUrl();
	}
	public String urlAnulCobro() {
		return UrlModuloEnum.PRESTAMOANULACIONCOBRO.getUrl();
	}
	public String urlAnulFactura() {
		return UrlModuloEnum.PRESTAMOANULACIONFACTURACION.getUrl();
	}
	public String urlCobroParcial() {
		return UrlModuloEnum.PRESTAMOCOBROPARCIAL.getUrl();
	}
	public String urlCobroTotal() {
		return UrlModuloEnum.PRESTAMOCOBROTOTAL.getUrl();
	}

}
