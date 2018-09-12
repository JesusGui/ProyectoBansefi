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
public class LimitesAutorizacionesController extends GeneralController implements Serializable {

    /*
     * Variables de clase.
     */
    private static final long serialVersionUID = 578119432234783992L;
    private IIntegracionService integracionService;
    private ContextoUtils contextoUtils;


    /*
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

    /*
     * Iniciador del contexto manejado en Faces para poder recuperarlo.
     */
    @PostConstruct
    private void init() {
        super.recuperarContextoFaces();
    }

    /*
     * Metodos para renderizar vistas de Limites Autorizaciones.
     */
    public String nivelesAtr() {
        return NavegacionEnum.NIVELESATRIBUCION.getRuta();
    }
    public String autSucursal() { return NavegacionEnum.AUTORIZADORESSUCURSAL.getRuta(); }
    public String adminUsuarios() { return NavegacionEnum.ADMINISTRACIONUSUARIOS.getRuta(); }
    public String solicitudes() {
        return NavegacionEnum.SOLICITUDES.getRuta();
    }

    /*
     * Metodos para obtener la url de los modulos de Limites Autorizaciones.
     */
    public String urlAutorizadoresSucursal() {
        return UrlModuloEnum.AUTORIZADORESSUCURSAL.getUrl();
    }
    public String urlNivelesAtribucion() {
        return UrlModuloEnum.NIVELESATRIBUCION.getUrl();
    }
    public String urlAdministracionUsuarios() {
        return UrlModuloEnum.ADMINISTRACIONUSUARIOS.getUrl();
    }
    public String urlSolicitudes() {
        return UrlModuloEnum.SOLICITUDES.getUrl();
    }

    /*
     * Metodos para generacion de bsfoperador.
     */
    public String generarBsfOperadorSolicitudesLimites() {
        return integracionService.getBsfOperadorSolicitudesLimites(contextoUtils);
    }

}
