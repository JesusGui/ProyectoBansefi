package mx.babel.arq.serviciosAppwhere.constants;

import mx.babel.arq.comun.utils.ProveedorMensajeSpringUtils;

public class CommonClientConstant extends WebServiceClientConstant {

    public static final String URICONSULTADATOSCENTRO = ESBWSBSFOPERACIONESSUCURSALESCONTEXT +
            ProveedorMensajeSpringUtils.getValoresServiciosWeb("rest.esb.wsBsfOperacionesSucursales.path.DatosCentro");
    public static final String URIENCRYPT = ESBWSBSFOPERACIONESSUCURSALESCONTEXT +
            ProveedorMensajeSpringUtils.getValoresServiciosWeb("rest.esb.wsBsfOperacionesSucursales.path.encrypt");
    public static final String URICREDENCIALESENTIDAD = JBOSSREMESASNACIONALESCONTEXT +
            ProveedorMensajeSpringUtils.getValoresServiciosWeb("rest.jboss.BseRemesasNacionales.path.consultarCredencialesEntidad");

}
