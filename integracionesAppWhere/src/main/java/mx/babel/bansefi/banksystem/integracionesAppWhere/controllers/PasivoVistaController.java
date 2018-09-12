package mx.babel.bansefi.banksystem.integracionesAppWhere.controllers;

import mx.babel.bansefi.banksystem.base.enums.integracionAppWhere.NavegacionEnum;
import mx.babel.bansefi.banksystem.base.enums.integracionAppWhere.UrlModuloEnum;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import java.io.Serializable;

@ManagedBean
public class PasivoVistaController extends GeneralController implements Serializable {

    /*
     * Variables de clase.
     */
    private static final long serialVersionUID = -7395524529687701732L;

    /*
	 * Iniciador del contexto manejado en Faces para poder recuperarlo.
	 */
    @PostConstruct
    private void init() {
        super.recuperarContextoFaces();
    }

	/*
	 * Metodo para renderizar vistas.
	 */
    public String bloqueoCuenta() { return NavegacionEnum.BLOQUEOCUENTA.getRuta(); }
    public String retencionesCuenta() { return NavegacionEnum.RETENCIONCUENTA.getRuta(); }
    public String chequesEmision() { return NavegacionEnum.CHEQUESEMISION.getRuta(); }
    public String bcosMutuasAjenas() { return NavegacionEnum.CLTABANCOSMUTUASAJENAS.getRuta(); }
    public String cuentaBcoMov() { return NavegacionEnum.CLTACUENTASBANCOSMOVIMIENTOS.getRuta(); }

    /*
	 * Metodo para obtener url.
	 */
    public String urlBloqueoCuenta() { return UrlModuloEnum.BLOQUEOCUENTA.getUrl();}
    public String urlRetencionCuenta() { return UrlModuloEnum.RETENCIONCUENTA.getUrl();}
    public String urlChequesEmision() { return UrlModuloEnum.CHEQUESEMISION.getUrl();}
    public String urlCltaBancosMutuasAjenas() { return UrlModuloEnum.CLTABANCOSMUTUASAJENAS.getUrl();}
    public String urlCltaCuentaBancosMovimientos() { return UrlModuloEnum.CLTACUENTASBANCOSMOVIMIENTOS.getUrl();}

}
