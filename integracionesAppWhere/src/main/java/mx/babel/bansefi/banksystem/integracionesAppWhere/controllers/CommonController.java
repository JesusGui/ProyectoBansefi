package mx.babel.bansefi.banksystem.integracionesAppWhere.controllers;

import mx.babel.arq.sesion.utils.ContextoUtils;
import mx.babel.bansefi.banksystem.base.enums.NavegacionEnum;
import mx.babel.bansefi.banksystem.integracionesAppWhere.services.IIntegracionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.servlet.ServletContext;
import java.io.Serializable;

@ManagedBean(name = "commonController")
@Component
@Scope("view")
@ViewScoped
public class CommonController implements Serializable {

    /*
	 * Variables de clase.
	 */
    private static final long serialVersionUID = -6239042713505276879L;
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
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        ServletContext servletContext = (ServletContext) externalContext.getContext();
        WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext).
                getAutowireCapableBeanFactory().
                autowireBean(this);
    }
    /*
     * Metodo para renderizar la vista principal del sistema.
     */
    public String irInicio() {
        return NavegacionEnum.INICIO.getRuta();
    }

    /*
     * Metodo para generacion de bsfoperador comun
     */
    public String generarBsfOperadorComun() {
        return integracionService.getBsfOperadorComun(contextoUtils);
    }
}
