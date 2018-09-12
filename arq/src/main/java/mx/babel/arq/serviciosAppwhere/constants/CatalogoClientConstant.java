package mx.babel.arq.serviciosAppwhere.constants;

import mx.babel.arq.comun.utils.ProveedorMensajeSpringUtils;

public class CatalogoClientConstant extends WebServiceClientConstant {

    public static final String URICATIDENTIFICACIONES = ESBCATALOGOSTCBWEBCONTEXT +
            ProveedorMensajeSpringUtils.getValoresServiciosWeb("rest.esb.CatalogosTCBWeb.path.getCatalogoIdentificaciones");
    public static final String URICATRELACIONPERSONA = ESBPERSONASFISICASCONTEXT +
            ProveedorMensajeSpringUtils.getValoresServiciosWeb("rest.esb.PersonasFisicas.path.ConsultaRelaciones");
    public static final String URICATDOCUMENTOSIDENTIFICACION = ESBPERSONASFISICASCONTEXT +
            ProveedorMensajeSpringUtils.getValoresServiciosWeb("rest.esb.PersonasFisicas.path.DocumentosIdentificacion");
    public static final String URICATDOCUMENTOSDOMICILIO = ESBPERSONASFISICASCONTEXT +
            ProveedorMensajeSpringUtils.getValoresServiciosWeb("rest.esb.PersonasFisicas.path.DocumentosDomicilio");
    public static final String URICATCONSULTACEDULA = ESBPERSONASFISICASCONTEXT +
            ProveedorMensajeSpringUtils.getValoresServiciosWeb("rest.esb.PersonasFisicas.path.ConsultaCedula");
    public static final String URICATCONSULTARAZONESALTA = ESBPERSONASFISICASCONTEXT +
            ProveedorMensajeSpringUtils.getValoresServiciosWeb("rest.esb.PersonasFisicas.path.ConsultaRazonesAlta");
    public static final String URICATCONSULTALISTAFINALIDAD = ESBWSBSFGESTIONCUENTACONTEXT +
            ProveedorMensajeSpringUtils.getValoresServiciosWeb("rest.esb.wsBsfGestionCuenta.path.ConsultaListaFinalidad");
    public static final String URICATCONSULTALISTARELACIONES = ESBWSBSFGESTIONCUENTACONTEXT +
            ProveedorMensajeSpringUtils.getValoresServiciosWeb("rest.esb.wsBsfGestionCuenta.path.ConsultaListaRelaciones");
    public static final String URICATALOGOSVARIOSPM = ESBWSBSFPERSONASMORALESCONTEXT +
            ProveedorMensajeSpringUtils.getValoresServiciosWeb("rest.esb.wsBfsPersonasMorales.path.ConsultaCatalogo");
    public static final String URICONSULTACATALOGOPM = ASPERSONASMORALESCONTEXT +
            ProveedorMensajeSpringUtils.getValoresServiciosWeb("rest.as.PersonasMorales.path.ConsultaCatalogoPM");
    public static final String URICONSULTACATALOGOPMMONEDA = ASPERSONASMORALESCONTEXT +
            ProveedorMensajeSpringUtils.getValoresServiciosWeb("rest.as.PersonasMorales.path.Moneda");
    public static final String URICONSULTACATPEMO = ESBWSPERSONASMORALESCONTEXT +
            ProveedorMensajeSpringUtils.getValoresServiciosWeb("rest.esb.wsPersonasMorales.path.ConsultaCatalogos");
    public static final String URICATPASIVOPLAZO = ASBfSPASIVOPLAZOCONTEXT +
            ProveedorMensajeSpringUtils.getValoresServiciosWeb("rest.as.asBfsPasivoPlazo.path.ConsultaCatalogos");

}
