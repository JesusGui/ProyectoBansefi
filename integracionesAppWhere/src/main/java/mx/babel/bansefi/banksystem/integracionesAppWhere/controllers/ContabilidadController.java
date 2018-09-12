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
public class ContabilidadController extends GeneralController implements Serializable {

    /*
     * Variables de clase.
     */
    private static final long serialVersionUID = 6445027020463466927L;
    private ContextoUtils contextoUtils;
    private IIntegracionService integracionService;

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
     * Metodo para renderizar vistas de Contabilidad.
     */
    public String consultaMovimientos() {
        return NavegacionEnum.CONSULTAMOVIMIENTOS.getRuta();
    }
    public String registroGasto() {
        return NavegacionEnum.REGISTROGASTO.getRuta();
    }
    public String cuentas() {
        return NavegacionEnum.CUENTAS.getRuta();
    }
    public String partidasPendientes() {
        return NavegacionEnum.PARTIDASPENDIENTES.getRuta();
    }

    /*
     * Metodos para obtener url de los modulos de Contabilidad.
     */
    public String urlConsultaMovimientos() {
        return UrlModuloEnum.CONSULTAMOVIMIENTOS.getUrl();
    }
    public String urlRegistroGasto() {
        return UrlModuloEnum.REGISTROGASTO.getUrl();
    }
    public String urlCuentas() {
        return UrlModuloEnum.CUENTAS.getUrl();
    }
    public String urlPartidasPendientes() {
        return UrlModuloEnum.PARTIDASPENDIENTES.getUrl();
    }

    /*
     * Metodos para generacion de bsfoperador.
     */
    public String generarBsfOperadorMovimientos() {
        return integracionService.getBsfOperadorMovimientos(contextoUtils);
    }

}
