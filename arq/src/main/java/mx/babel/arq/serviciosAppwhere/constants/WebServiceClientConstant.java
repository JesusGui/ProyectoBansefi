package mx.babel.arq.serviciosAppwhere.constants;

import mx.babel.arq.comun.utils.ProveedorMensajeSpringUtils;

public class WebServiceClientConstant {

    private static final String PROJECTLOCATIONSCHEME = ProveedorMensajeSpringUtils.getValoresServiciosWeb("project.location.scheme");
    private static final String WSO2LOCATIONHOST = ProveedorMensajeSpringUtils.getValorConfiguracion("wso2.location.host");
    private static final String APPLICATIONSERVERPORT = ProveedorMensajeSpringUtils.getValorConfiguracion("wso2.location.application-server.port");
    private static final String ENTERPRISESERVICEBUSPORT = ProveedorMensajeSpringUtils.getValorConfiguracion("wso2.location.enterprise-service-bus.port");
    private static final String JBOSSHOST = ProveedorMensajeSpringUtils.getValoresServiciosWeb("jboss.location.host");
    private static final String JBOSSPORT = ProveedorMensajeSpringUtils.getValoresServiciosWeb("jboss.location.port");
    private static final String APPLICATIONSERVERURI = PROJECTLOCATIONSCHEME + "://" + WSO2LOCATIONHOST + ":" + APPLICATIONSERVERPORT;
    private static final String ENTERPRISESERVICEBUSURI = PROJECTLOCATIONSCHEME + "://" + WSO2LOCATIONHOST + ":" + ENTERPRISESERVICEBUSPORT;
    private static final String JBOSSURI = PROJECTLOCATIONSCHEME + "://" + JBOSSHOST + ":" + JBOSSPORT;
    protected static final String ASOFICINABANCARIACONTEXT = APPLICATIONSERVERURI + ProveedorMensajeSpringUtils.getValoresServiciosWeb("rest.uri.asBsfOficinaBancaria");
    protected static final String ESBCATALOGOSTCBWEBCONTEXT = ENTERPRISESERVICEBUSURI + ProveedorMensajeSpringUtils.getValoresServiciosWeb("rest.esb.CatalogosTCBWeb.context");
    protected static final String ESBPERSONASFISICASCONTEXT = ENTERPRISESERVICEBUSURI + ProveedorMensajeSpringUtils.getValoresServiciosWeb("rest.esb.PersonasFisicas.context");
    protected static final String ESBWSBSFPERSONASMORALESCONTEXT = ENTERPRISESERVICEBUSURI + ProveedorMensajeSpringUtils.getValoresServiciosWeb("rest.esb.wsBfsPersonasMorales.context");
    protected static final String ESBWSBSFGESTIONCUENTACONTEXT = ENTERPRISESERVICEBUSURI + ProveedorMensajeSpringUtils.getValoresServiciosWeb("rest.esb.wsBsfGestionCuenta.context");
    protected static final String ASPERSONASMORALESCONTEXT = APPLICATIONSERVERURI + ProveedorMensajeSpringUtils.getValoresServiciosWeb("rest.as.PersonasMorales.context");
    protected static final String ASBfSPASIVOPLAZOCONTEXT = APPLICATIONSERVERURI + ProveedorMensajeSpringUtils.getValoresServiciosWeb("rest.as.asBfsPasivoPlazo.context");
    protected static final String ESBWSPERSONASMORALESCONTEXT = ENTERPRISESERVICEBUSURI + ProveedorMensajeSpringUtils.getValoresServiciosWeb("rest.esb.wsPersonasMorales.context");
    protected static final String ESBWSBSFOPERACIONESSUCURSALESCONTEXT = ENTERPRISESERVICEBUSURI + ProveedorMensajeSpringUtils.getValoresServiciosWeb("rest.esb.wsBsfOperacionesSucursales.context");
    protected static final String ESBWSBSFPASIVOCONTEXT = ENTERPRISESERVICEBUSURI + ProveedorMensajeSpringUtils.getValoresServiciosWeb("rest.esb.wsBsfPasivo.context");
    protected static final String JBOSSREMESASNACIONALESCONTEXT = JBOSSURI + ProveedorMensajeSpringUtils.getValoresServiciosWeb("rest.jboss.BseRemesasNacionales.context");

}
