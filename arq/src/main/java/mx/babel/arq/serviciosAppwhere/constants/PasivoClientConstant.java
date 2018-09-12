package mx.babel.arq.serviciosAppwhere.constants;

import mx.babel.arq.comun.utils.ProveedorMensajeSpringUtils;

public class PasivoClientConstant extends WebServiceClientConstant {

    public static final String URICONSULTAPENDIENTESDIARIO = ESBWSBSFPASIVOCONTEXT +
            ProveedorMensajeSpringUtils.getValoresServiciosWeb("rest.esb.wsBsfPasivo.path.ConsultaPendientesDiario");
    public static final String URIPROCESAPENDIENTESDIARIO = ESBWSBSFPASIVOCONTEXT +
            ProveedorMensajeSpringUtils.getValoresServiciosWeb("rest.esb.wsBsfPasivo.path.ProcesaPendientesDiario");

}
