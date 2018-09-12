package mx.babel.bansefi.banksystem.integracionesAppWhere.controllers;

import mx.babel.bansefi.banksystem.base.enums.integracionAppWhere.NavegacionEnum;
import mx.babel.bansefi.banksystem.base.enums.integracionAppWhere.UrlModuloEnum;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import java.io.Serializable;

@ManagedBean
public class PasivoController extends GeneralController implements Serializable {

	/*
	 * Variables de clase.
	 */
	private static final long serialVersionUID = -6763427445423351750L;

	/*
	 * Iniciador del contexto manejado en Faces para poder recuperarlo.
	 */
	@PostConstruct
	private void init() {
		super.recuperarContextoFaces();
	}

	/*
	 * Metodos para renderizar vistas de Pasivo.
	 */
	public String consultaSaldos() { return NavegacionEnum.CONSULTASALDOS.getRuta(); }
	public String deposito() { return NavegacionEnum.DEPOSITO.getRuta(); }
	public String retiro() { return NavegacionEnum.RETIRO.getRuta(); }
	public String consultaMovimientos() { return NavegacionEnum.CONSULTAMOVIMIENTOSPASIVO.getRuta(); }
	public String asociarTarjeta() { return NavegacionEnum.ASOCIARTARJETAPASIVO.getRuta(); }
	public String cuentasSolicitadas() { return NavegacionEnum.CUENTASSOLICITADASPASIVO.getRuta(); }
	/*
	 * Metodos para obtener la url de los modulos de Pasivo.
	 */
	public String urlConsultaSaldos() { return UrlModuloEnum.CONSULTASALDOS.getUrl(); }
	public String urlDeposito() { return UrlModuloEnum.DEPOSITO.getUrl(); }
	public String urlRetiro() {
		return UrlModuloEnum.RETIRO.getUrl();
	}
	public String urlConsultaMovimientos() {
		return UrlModuloEnum.CONSULTAMOVIMIENTOSPASIVO.getUrl();
	}
	public String urlAsociarTarjeta() { return UrlModuloEnum.ASOCIARTARJETAPASIVO.getUrl(); }
	public String urlCuentasSolicitadas() { return UrlModuloEnum.CUENTASSOLICITADASPASIVO.getUrl(); }


}
