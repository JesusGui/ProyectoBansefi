package mx.babel.bansefi.banksystem.integracionesAppWhere.controllers;

import mx.babel.bansefi.banksystem.base.enums.integracionAppWhere.NavegacionEnum;
import mx.babel.bansefi.banksystem.base.enums.integracionAppWhere.UrlModuloEnum;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import java.io.Serializable;

@ManagedBean
public class CentroAtribucionesController extends GeneralController implements Serializable {

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
    public String centros() { return NavegacionEnum.CACENTROS.getRuta(); }
    public String usuarios() { return NavegacionEnum.CAUSUARIOS.getRuta(); }
    public String entidades() { return NavegacionEnum.CAENTIDADES.getRuta(); }

    /*
	 * Metodo para obtener url.
	 */
    public String urlCentros() { return UrlModuloEnum.CACENTROS.getUrl();}
    public String urlUsuarios() { return UrlModuloEnum.CAUSUARIOS.getUrl();}
    public String urlEntidades() { return UrlModuloEnum.CAENTIDADES.getUrl();}

}
