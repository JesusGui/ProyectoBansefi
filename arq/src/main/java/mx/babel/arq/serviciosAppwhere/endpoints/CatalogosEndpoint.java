package mx.babel.arq.serviciosAppwhere.endpoints;

import mx.babel.arq.serviciosAppwhere.clients.catalogos.CatalogoClient;
import mx.babel.arq.serviciosAppwhere.dto.catalogos.catPasivoPlazo.ReqCatPasivoPlazo;
import mx.babel.arq.serviciosAppwhere.dto.catalogos.catPasivoPlazo.ResCatPasivoPlazo;
import mx.babel.arq.serviciosAppwhere.dto.catalogos.catPerMor.ReqCatPerMorDTO;
import mx.babel.arq.serviciosAppwhere.dto.catalogos.catPerMor.ResCatPerMorDTO;
import mx.babel.arq.serviciosAppwhere.dto.catalogos.catalogosVariosPM.ReqCatalogoVariosPMDTO;
import mx.babel.arq.serviciosAppwhere.dto.catalogos.catalogosVariosPM.ReqConsultaCatalogoPersonaMoralDTO;
import mx.babel.arq.serviciosAppwhere.dto.catalogos.catalogosVariosPM.ResCatalogosVariosPMDTO;
import mx.babel.arq.serviciosAppwhere.dto.catalogos.catalogosVariosPM.ResConsultaCatalogosDTO;
import mx.babel.arq.serviciosAppwhere.dto.catalogos.consultaDocumentos.DatosListaDTO;
import mx.babel.arq.serviciosAppwhere.dto.catalogos.consultaDocumentos.DocumentoDigitalizarDTO;
import mx.babel.arq.serviciosAppwhere.dto.catalogos.consultaDocumentos.ReqConsultaDocumentosIdentificacionDTO;
import mx.babel.arq.serviciosAppwhere.dto.catalogos.consultaDocumentos.ResConsultaDocumentosDTO;
import mx.babel.arq.serviciosAppwhere.dto.catalogos.consultaListaFinalidad.ReqConsultaListaFinalidadDTO;
import mx.babel.arq.serviciosAppwhere.dto.catalogos.consultaListaFinalidad.ResConsultaListaFinalidadDTO;
import mx.babel.arq.serviciosAppwhere.dto.catalogos.consultaListaRelaciones.ReqConsultaListaRelacionesDTO;
import mx.babel.arq.serviciosAppwhere.dto.catalogos.consultaListaRelaciones.ResConsultaListaRelacionesDTO;
import mx.babel.arq.serviciosAppwhere.dto.catalogos.consultaRazonesAlta.ReqConsultaRazonesAltaDTO;
import mx.babel.arq.serviciosAppwhere.dto.catalogos.consultaRazonesAlta.ResConsultaRazonesAltaDTO;
import mx.babel.arq.serviciosAppwhere.dto.catalogos.consultaRelacionPersona.ReqConsultaRelacionPersonaDTO;
import mx.babel.arq.serviciosAppwhere.dto.catalogos.consultaRelacionPersona.ResConsultaRelacionPersonaDTO;
import mx.babel.arq.serviciosAppwhere.dto.catalogos.getCatalogo.ReqGetCatalogo;
import mx.babel.arq.serviciosAppwhere.dto.catalogos.getCatalogo.ResContenedorListaDTO;
import mx.babel.arq.serviciosAppwhere.dto.catalogos.getCatalogo.TipoCatalogo;
import mx.babel.arq.serviciosAppwhere.dto.catalogos.moneda.ResMonedaDTO;
import mx.babel.arq.serviciosAppwhere.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;

@Component
@Path("/catalogos")
public class CatalogosEndpoint extends SpringBeanAutowiringSupport {

    /**
     * Variables de clase
     */
    private Util util;
    private CatalogoClient catalogoClient;

    /**
     * Inyeccion de dependencias
     */
    @Autowired
    public void setUtil(Util util) {
        this.util = util;
    }
    @Autowired
    public void setCatalogoClient(CatalogoClient catalogoClient) {
        this.catalogoClient = catalogoClient;
    }

    /**
     * Metodo para consultar los catalogos de los modulos
     * @param tipo
     * @return
     */
    @GET
    @Path("identificacion")
    @Produces(MediaType.APPLICATION_JSON)
    public ResContenedorListaDTO getCatalogos(@QueryParam("tipoCatalogo") String tipo) {
        ReqGetCatalogo req = new ReqGetCatalogo();
        TipoCatalogo tipoCatalogo = new TipoCatalogo(tipo);
        req.setGetCatalogoIdentificaciones(tipoCatalogo);
        return catalogoClient.getCatalogoIdentificaciones(req);
    }

    /**
     * Metodo para consultar los catalogos de los modulos
     * @return
     */
    @GET
    @Path(value = "relacionPersona")
    @Produces(MediaType.APPLICATION_JSON)
    public String getCatalogoRelacionPersona() {
        ResConsultaRelacionPersonaDTO res =
                catalogoClient.consultaRelacionPersona(new ReqConsultaRelacionPersonaDTO());
        String ret = util.objectToJson(res);
        return ret;
    }

    /**
     * Metodo para consultar los catalogos de documentos a digitalizar
     * @return
     */
    @GET
    @Path(value = "catalogoDocumentosDigitalizar")
    @Produces(MediaType.APPLICATION_JSON)
    public String catalogoDocumentosDigitalizar() {
        ResConsultaDocumentosDTO resIdentificacion =
                catalogoClient.consultaDocumentosIdentificacion(
                        new ReqConsultaDocumentosIdentificacionDTO(new Object()));
        ResConsultaDocumentosDTO resDomicilio =
                catalogoClient.consultaDocumentosDomicilio(
                        new ReqConsultaDocumentosIdentificacionDTO(new Object()));
        ResConsultaDocumentosDTO resCedula =
                catalogoClient.consultaConsultaCedula(
                        new ReqConsultaDocumentosIdentificacionDTO(new Object()));
        ArrayList<DocumentoDigitalizarDTO> registros = new ArrayList<DocumentoDigitalizarDTO>();
        registros.addAll(resCedula.getDATOS_LISTA().getDOCUMENTOS());
        registros.addAll(resDomicilio.getDATOS_LISTA().getDOCUMENTOS());
        registros.addAll(resIdentificacion.getDATOS_LISTA().getDOCUMENTOS());

        DatosListaDTO documentos = new DatosListaDTO(registros);
        String ret = util.objectToJson(documentos);
        return ret;
    }

    /**
     * Metodo para consumir el catalogo de consulta razon alta.
     * @return
     */
    @GET
    @Path(value = "razonesAlta")
    @Produces(MediaType.APPLICATION_JSON)
    public ResConsultaRazonesAltaDTO catalogoRazonesAlta() {
        ReqConsultaRazonesAltaDTO reqConsultaRazones = new ReqConsultaRazonesAltaDTO(new Object());
        ResConsultaRazonesAltaDTO resConsultaRazones =
                catalogoClient.consultaRazonesAlta(reqConsultaRazones);
        return resConsultaRazones;
    }

    /**
     * Metodo para consumir el catalogo de consulta lista finalidad
     * @return
     */
    @GET
    @Path(value = "listaFinalidad")
    @Produces(MediaType.APPLICATION_JSON)
    public String consultaListaFinalidad() {
        ReqConsultaListaFinalidadDTO req = new ReqConsultaListaFinalidadDTO(new Object());
        ResConsultaListaFinalidadDTO res =
                catalogoClient.consultaListaFinalidad(req);
        String ret = util.objectToJson(res);
        return ret;
    }

    /**
     * Metodo para consumir el catalogo de consulta lista de relaciones
     * @return
     */
    @GET
    @Path(value = "listaRelaciones")
    @Produces(MediaType.APPLICATION_JSON)
    public String consultaListaRelaciones() {
        ReqConsultaListaRelacionesDTO req = new ReqConsultaListaRelacionesDTO(new Object());
        ResConsultaListaRelacionesDTO res =
                catalogoClient.consultaListaRelaciones(req);
        String ret = util.objectToJson(res);
        return ret;
    }

    /**
     * Metodo para consumir catalogos varios de Personas morales.
     * @param codApp
     * @param codSubApp
     * @return
     */
    @GET
    @Path(value = "catalogosVariosPM")
    @Produces(MediaType.APPLICATION_JSON)
    public String catalogosVariosPM(
            @QueryParam("codApp") String codApp,
            @QueryParam("codSubApp") String codSubApp) {

        ReqCatalogoVariosPMDTO req = new ReqCatalogoVariosPMDTO(codApp, codSubApp);
        ResCatalogosVariosPMDTO res =
                catalogoClient.catalogosVariosPM(req);
        String ret = util.objectToJson(res);
        return ret;
    }

    /**
     * Metodo para consumir servicio de catalogos para personas morales.
     * @param tblRef
     * @param aplcnSub
     * @param indPag
     * @param cveFila
     * @param httpServletRequest
     * @return
     */
    @GET
    @Path(value = "catalogosPersonaMoral")
    @Produces(MediaType.APPLICATION_JSON)
    public ResConsultaCatalogosDTO catalogosPersonaMoral(
            @QueryParam("codApp") String tblRef,
            @QueryParam("codSubApp") String aplcnSub,
            @QueryParam("indPag") String indPag,
            @QueryParam("cveFila") String cveFila,
            @Context HttpServletRequest httpServletRequest) {
        ReqConsultaCatalogoPersonaMoralDTO req
                = new ReqConsultaCatalogoPersonaMoralDTO(tblRef, aplcnSub, indPag, cveFila);
        return catalogoClient.catalogosPersonaMoral(req, httpServletRequest);
    }

    /**
     * Metodo para consumir servicio de catalogos para personas morales.
     * @param httpServletRequest
     * @return
     */
    @GET
    @Path(value = "catalogosMoneda")
    @Produces(MediaType.APPLICATION_JSON)
    public ResMonedaDTO catalogosMoneda(
            @Context HttpServletRequest httpServletRequest) {
        return catalogoClient.catalogosPersonaMoralMoneda(httpServletRequest);
    }

    /**
     * Metodo para consumir servicio de catalogos para pasivo plazo
     * @param cod
     * @param httpServletRequest
     * @return
     */
    @GET
    @Path(value = "catalogosPasivoPlazo")
    @Produces(MediaType.APPLICATION_JSON)
    public ResCatPasivoPlazo catalogosPersonaMoral(
            @QueryParam("cod") String cod,
            @Context HttpServletRequest httpServletRequest) {
        ReqCatPasivoPlazo req
                = new ReqCatPasivoPlazo(cod,"","20");
        return catalogoClient.catalogosPasivoPlazo(req, httpServletRequest);
    }

    /**
     * Metodo para consumir servicio de catalogos para personas morales2
     * @param cod
     * @param httpServletRequest
     * @return
     */
    @GET
    @Path(value = "catalogosPerMor")
    @Produces(MediaType.APPLICATION_JSON)
    public ResCatPerMorDTO catalogosPerMor(
            @QueryParam("tblRef") String tblRef,
            @QueryParam("aplcnSub") String aplcnSub,
            @Context HttpServletRequest httpServletRequest) {
        ReqCatPerMorDTO req
                = new ReqCatPerMorDTO(tblRef, aplcnSub, "", "");
        return catalogoClient.catPerMor(req, httpServletRequest);
    }

}

