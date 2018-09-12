package mx.babel.arq.serviciosAppwhere.clients.catalogos;

import mx.babel.arq.serviciosAppwhere.constants.CatalogoClientConstant;
import mx.babel.arq.serviciosAppwhere.dto.DatosSesionDTO;
import mx.babel.arq.serviciosAppwhere.dto.catalogos.catPasivoPlazo.ReqCatPasivoPlazo;
import mx.babel.arq.serviciosAppwhere.dto.catalogos.catPasivoPlazo.ResCatPasivoPlazo;
import mx.babel.arq.serviciosAppwhere.dto.catalogos.catPerMor.RegistroCatPerMorDTO;
import mx.babel.arq.serviciosAppwhere.dto.catalogos.catPerMor.RegistrosCatPerMorDTO;
import mx.babel.arq.serviciosAppwhere.dto.catalogos.catPerMor.ReqCatPerMorDTO;
import mx.babel.arq.serviciosAppwhere.dto.catalogos.catPerMor.ResCatPerMorDTO;
import mx.babel.arq.serviciosAppwhere.dto.catalogos.catalogosVariosPM.*;
import mx.babel.arq.serviciosAppwhere.dto.catalogos.consultaDocumentos.DatosListaDTO;
import mx.babel.arq.serviciosAppwhere.dto.catalogos.consultaDocumentos.DocumentoDigitalizarDTO;
import mx.babel.arq.serviciosAppwhere.dto.catalogos.consultaDocumentos.ReqConsultaDocumentosIdentificacionDTO;
import mx.babel.arq.serviciosAppwhere.dto.catalogos.consultaDocumentos.ResConsultaDocumentosDTO;
import mx.babel.arq.serviciosAppwhere.dto.catalogos.consultaListaFinalidad.ReqConsultaListaFinalidadDTO;
import mx.babel.arq.serviciosAppwhere.dto.catalogos.consultaListaFinalidad.ResConsultaListaFinalidadDTO;
import mx.babel.arq.serviciosAppwhere.dto.catalogos.consultaListaRelaciones.ReqConsultaListaRelacionesDTO;
import mx.babel.arq.serviciosAppwhere.dto.catalogos.consultaListaRelaciones.ResConsultaListaRelacionesDTO;
import mx.babel.arq.serviciosAppwhere.dto.catalogos.consultaRazonesAlta.RazonAltaDTO;
import mx.babel.arq.serviciosAppwhere.dto.catalogos.consultaRazonesAlta.RazonesAltaDTO;
import mx.babel.arq.serviciosAppwhere.dto.catalogos.consultaRazonesAlta.ReqConsultaRazonesAltaDTO;
import mx.babel.arq.serviciosAppwhere.dto.catalogos.consultaRazonesAlta.ResConsultaRazonesAltaDTO;
import mx.babel.arq.serviciosAppwhere.dto.catalogos.consultaRelacionPersona.ReqConsultaRelacionPersonaDTO;
import mx.babel.arq.serviciosAppwhere.dto.catalogos.consultaRelacionPersona.ResConsultaRelacionPersonaDTO;
import mx.babel.arq.serviciosAppwhere.dto.catalogos.getCatalogo.CatalogoDTO;
import mx.babel.arq.serviciosAppwhere.dto.catalogos.getCatalogo.ReqGetCatalogo;
import mx.babel.arq.serviciosAppwhere.dto.catalogos.getCatalogo.ResContenedorListaDTO;
import mx.babel.arq.serviciosAppwhere.dto.catalogos.moneda.MonedaDTO;
import mx.babel.arq.serviciosAppwhere.dto.catalogos.moneda.ReqMonedaDTO;
import mx.babel.arq.serviciosAppwhere.dto.catalogos.moneda.ResMonedaDTO;
import mx.babel.arq.serviciosAppwhere.utils.Util;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * Created by AppWIns on 26/04/2017.
 */
@Component
public class CatalogoClient {

    /*
     * Se definen variables de clase.
     */
    private Util util;
    private static final Logger log = LogManager.getLogger(CatalogoClient.class);

    /*
     * Inyeccion de dependencias
     */
    @Autowired
    public void setUtil(Util util) {
        this.util = util;
    }

    /*
     * Constructor para inicializar uris de los servicios de catalogos.
     */
    public CatalogoClient() {

    }

    /*
     * Metodo para consumir servicio getCatalogoIdentificaciones
     */
    public ResContenedorListaDTO getCatalogoIdentificaciones(ReqGetCatalogo req) {
        ResContenedorListaDTO res = null;
        try {
            String jsonRes = this.util.callRestPost(req, CatalogoClientConstant.URICATIDENTIFICACIONES);
            res = new ResContenedorListaDTO();
            ArrayList<String> nodos = new ArrayList<String>();
            nodos.add("getCatalogoIdentificacionesResp");

            res = (ResContenedorListaDTO) this.util.jsonToObject(res, jsonRes, nodos);

            if (res == null) {
                nodos.add("Identificacion");
                CatalogoDTO registro = new CatalogoDTO();
                registro = (CatalogoDTO) this.util.jsonToObject(registro, jsonRes, nodos);
                ArrayList<CatalogoDTO> registros = new ArrayList<CatalogoDTO>();
                registros.add(registro);
                res = new ResContenedorListaDTO(registros);
            }

        } catch (ParseException ex) {
            log.error(ex.getMessage());
        }
        return res;
    }

    /*
     * Metodo para consumir servicio getCatalogoIdentificaciones
     */
    public ResConsultaRelacionPersonaDTO consultaRelacionPersona(ReqConsultaRelacionPersonaDTO req) {
        ResConsultaRelacionPersonaDTO res = null;
        try {
            String jsonRes = this.util.callRestPost(req, CatalogoClientConstant.URICATRELACIONPERSONA);

            res = new ResConsultaRelacionPersonaDTO();
            ArrayList<String> nodos = new ArrayList<String>();
            nodos.add("ConsultaRelacionesResponse");
            nodos.add("return");
            nodos.add("RESPONSE");

            res = (ResConsultaRelacionPersonaDTO) this.util.jsonToObject(res, jsonRes, nodos);

        } catch (ParseException ex) {
            log.error(ex.getMessage());
        }
        return res;
    }

    /*
     * Metodo para consumir servicio DocumentosIdentificacion
     */
    public ResConsultaDocumentosDTO consultaDocumentosIdentificacion(
            ReqConsultaDocumentosIdentificacionDTO req) {
        ResConsultaDocumentosDTO res = null;
        try {
            String jsonRes = this.util.callRestPost(req, CatalogoClientConstant.URICATDOCUMENTOSIDENTIFICACION);

            res = new ResConsultaDocumentosDTO();
            ArrayList<String> nodos = new ArrayList<String>();
            nodos.add("ConsultaDocumentosIdentificacionResponse");
            nodos.add("return");
            nodos.add("RESPONSE");

            res = (ResConsultaDocumentosDTO) this.util.jsonToObject(res, jsonRes, nodos);

            if (res == null) {
                res = new ResConsultaDocumentosDTO();
                nodos.add("DATOS_LISTA");
                nodos.add("DOCUMENTOS");
                DocumentoDigitalizarDTO registro = new DocumentoDigitalizarDTO();
                registro = (DocumentoDigitalizarDTO) this.util.jsonToObject(registro, jsonRes, nodos);
                ArrayList<DocumentoDigitalizarDTO> registros = new ArrayList<DocumentoDigitalizarDTO>();
                registros.add(registro);
                DatosListaDTO datosListaDTO = new DatosListaDTO(registros);
                if (registros.size() > 0)
                    res.setSTATUS(1);
                res.setDATOS_LISTA(datosListaDTO);
            }
        } catch (ParseException ex) {
            log.error(ex.getMessage());
        }
        return res;
    }

    /*
     * Metodo para consumir servicio DocumentosDomicilio
     */
    public ResConsultaDocumentosDTO consultaDocumentosDomicilio(
            ReqConsultaDocumentosIdentificacionDTO req) {
        ResConsultaDocumentosDTO res = null;
        try {
            String jsonRes = this.util.callRestPost(req, CatalogoClientConstant.URICATDOCUMENTOSDOMICILIO);

            res = new ResConsultaDocumentosDTO();
            ArrayList<String> nodos = new ArrayList<String>();
            nodos.add("ConsultaDocumentosDomicilioResponse");
            nodos.add("return");
            nodos.add("RESPONSE");

            res = (ResConsultaDocumentosDTO) this.util.jsonToObject(res, jsonRes, nodos);

            if (res == null) {
                res = new ResConsultaDocumentosDTO();
                nodos.add("DATOS_LISTA");
                nodos.add("DOCUMENTOS");
                DocumentoDigitalizarDTO registro = new DocumentoDigitalizarDTO();
                registro = (DocumentoDigitalizarDTO) this.util.jsonToObject(registro, jsonRes, nodos);
                ArrayList<DocumentoDigitalizarDTO> registros = new ArrayList<DocumentoDigitalizarDTO>();
                registros.add(registro);
                DatosListaDTO datosListaDTO = new DatosListaDTO(registros);
                if (registros.size() > 0)
                    res.setSTATUS(1);
                res.setDATOS_LISTA(datosListaDTO);
            }
        } catch (ParseException ex) {
            log.error(ex.getMessage());
        }
        return res;
    }

    /*
     * Metodo para consumir servicio ConsultaCedula
     */
    public ResConsultaDocumentosDTO consultaConsultaCedula(
            ReqConsultaDocumentosIdentificacionDTO req) {
        ResConsultaDocumentosDTO res = null;
        try {
            String jsonRes = this.util.callRestPost(req, CatalogoClientConstant.URICATCONSULTACEDULA);

            res = new ResConsultaDocumentosDTO();
            ArrayList<String> nodos = new ArrayList<String>();
            nodos.add("ConsultaCedulaResponse");
            nodos.add("return");
            nodos.add("RESPONSE");

            res = (ResConsultaDocumentosDTO) this.util.jsonToObject(res, jsonRes, nodos);

            if (res == null) {
                res = new ResConsultaDocumentosDTO();
                nodos.add("DATOS_LISTA");
                nodos.add("DOCUMENTOS");
                DocumentoDigitalizarDTO registro = new DocumentoDigitalizarDTO();
                registro = (DocumentoDigitalizarDTO) this.util.jsonToObject(registro, jsonRes, nodos);
                ArrayList<DocumentoDigitalizarDTO> registros = new ArrayList<DocumentoDigitalizarDTO>();
                registros.add(registro);
                DatosListaDTO datosListaDTO = new DatosListaDTO(registros);
                if (registros.size() > 0)
                    res.setSTATUS(1);
                res.setDATOS_LISTA(datosListaDTO);
            }
        } catch (ParseException ex) {
            log.error(ex.getMessage());
        }
        return res;
    }

    /*
     * Metodo para consumir servicio ConsultaRazonesAlta
     */
    public ResConsultaRazonesAltaDTO consultaRazonesAlta(ReqConsultaRazonesAltaDTO req) {
        ResConsultaRazonesAltaDTO res = null;
        try {
            String jsonRes = this.util.callRestPost(req, CatalogoClientConstant.URICATCONSULTARAZONESALTA);

            res = new ResConsultaRazonesAltaDTO();
            ArrayList<String> nodos = new ArrayList<String>();
            nodos.add("ConsultaRazonesAltaResponse");
            nodos.add("return");
            nodos.add("RESPONSE");

            res = (ResConsultaRazonesAltaDTO) this.util.jsonToObject(res, jsonRes, nodos);

            if (res == null) {
                res = new ResConsultaRazonesAltaDTO();
                nodos.add("DATOS_LISTA");
                nodos.add("RAZONES");
                RazonAltaDTO razon = new RazonAltaDTO();
                razon = (RazonAltaDTO) this.util.jsonToObject(razon, jsonRes, nodos);
                if (razon != null) {
                    ArrayList<RazonAltaDTO> razones = new ArrayList<RazonAltaDTO>();
                    razones.add(razon);
                    RazonesAltaDTO razonesAlta = new RazonesAltaDTO(razones);
                    if (razones.size() > 0)
                        res.setSTATUS("1");
                    else res.setSTATUS("0");
                    res.setDATOS_LISTA(razonesAlta);
                } else {
                    res.setSTATUS("0");
                }
            }
        } catch (ParseException ex) {
            log.error(ex.getMessage());
        }
        return res;
    }

    /*
     * Metodo para consumir servicio consultaListaFinalidad
     */
    public ResConsultaListaFinalidadDTO consultaListaFinalidad(ReqConsultaListaFinalidadDTO req) {
        ResConsultaListaFinalidadDTO res = null;
        try {
            String jsonRes = this.util.callRestPost(req, CatalogoClientConstant.URICATCONSULTALISTAFINALIDAD);

            res = new ResConsultaListaFinalidadDTO();
            ArrayList<String> nodos = new ArrayList<String>();
            nodos.add("ConsultaListaFinalidadResponse");
            nodos.add("return");
            nodos.add("RESPONSE");

            res = (ResConsultaListaFinalidadDTO) this.util.jsonToObject(res, jsonRes, nodos);

            if (res == null) {
                res = new ResConsultaListaFinalidadDTO("0", null, null);
            }
        } catch (ParseException ex) {
            log.error(ex.getMessage());
        }
        return res;
    }

    /*
     * Metodo para consumir servicio consultaListaFinalidad
     */
    public ResConsultaListaRelacionesDTO consultaListaRelaciones(ReqConsultaListaRelacionesDTO req) {
        ResConsultaListaRelacionesDTO res = null;
        try {
            String jsonRes = this.util.callRestPost(req, CatalogoClientConstant.URICATCONSULTALISTARELACIONES);

            res = new ResConsultaListaRelacionesDTO();
            ArrayList<String> nodos = new ArrayList<String>();
            nodos.add("ListaRelacionesResponse");
            nodos.add("return");
            nodos.add("RESPONSE");

            res = (ResConsultaListaRelacionesDTO) this.util.jsonToObject(res, jsonRes, nodos);

            if (res == null) {
                res = new ResConsultaListaRelacionesDTO("0", null, null);
            }
        } catch (ParseException ex) {
            log.error(ex.getMessage());
        }
        return res;
    }

    /*
     * Metodo para consumir catalogos varios de personas morales.
     */
    public ResCatalogosVariosPMDTO catalogosVariosPM(ReqCatalogoVariosPMDTO req) {
        ResCatalogosVariosPMDTO res = null;
        try {
            String jsonRes = this.util.callRestPost(req, CatalogoClientConstant.URICATALOGOSVARIOSPM);

            res = new ResCatalogosVariosPMDTO();
            ArrayList<String> nodos = new ArrayList<String>();
            nodos.add("RESPONSE");

            res = (ResCatalogosVariosPMDTO) this.util.jsonToObject(res, jsonRes, nodos);

        } catch (ParseException ex) {
            log.error(ex.getMessage());
        }
        return res;
    }

    /*
     * Metodo para consumir servicio de catalogos para personas morales.
     */
    public ResConsultaCatalogosDTO catalogosPersonaMoral(
            ReqConsultaCatalogoPersonaMoralDTO req,
            HttpServletRequest httpServletRequest) {
        ResConsultaCatalogosDTO res = new ResConsultaCatalogosDTO();
        if (httpServletRequest.getSession() != null) {
            //Se obtienen credenciales de RUTA_CONFIG
            DatosSesionDTO datosSesion =
                    (DatosSesionDTO) httpServletRequest.getSession().getAttribute("datosSesion");
            req.setUsuario(datosSesion.getUsuario());
            req.setPassword(datosSesion.getPassword());
            req.setTerminal(datosSesion.getVentanilla());
            req.setEntidad(datosSesion.getEntidad());
            try {
                boolean sigPag;
                res.setResponseBansefi(new ArrayList<CatalogoPersonaMoralDTO>());
                do {
                    String jsonRes = this.util.callRestPost(req, CatalogoClientConstant.URICONSULTACATALOGOPM);

                    ResConsultaCatalogosDTO resultado = new ResConsultaCatalogosDTO();

                    resultado = (ResConsultaCatalogosDTO) this.util.jsonToObject(resultado, jsonRes);

                    if (resultado.getCodigo().equals("SNTEA102")) {
                        sigPag = true;
                        req.setIndPag("F");
                        req.setCveFila(
                                resultado
                                        .getResponseBansefi()
                                        .get(resultado.getResponseBansefi().size() -1).getClave());
                    } else {
                        sigPag = false;
                        res.setEstatus(resultado.getEstatus());
                        res.setCodigo(resultado.getCodigo());
                        res.setMensaje(resultado.getMensaje().trim());
                    }
                    res.getResponseBansefi().addAll(resultado.getResponseBansefi());
                } while (sigPag);
            } catch (ParseException ex) {
                log.error(ex.getMessage());
            }
        } else {
            res.setEstatus("0");
            res.setMensaje("La sesi\u00f3n no est\u00e1 activa.");
        }

        return res;
    }


    public ResMonedaDTO catalogosPersonaMoralMoneda(
            HttpServletRequest httpServletRequest) {
        ReqMonedaDTO req = new ReqMonedaDTO();


        ResMonedaDTO res = new ResMonedaDTO();

        if (httpServletRequest.getSession() != null) {
            //Se obtienen credenciales de RUTA_CONFIG
            DatosSesionDTO datosSesion =
                    (DatosSesionDTO) httpServletRequest.getSession().getAttribute("datosSesion");
            req.setUsuario(datosSesion.getUsuario());
            req.setPassword(datosSesion.getPassword());
            req.setTerminal(datosSesion.getVentanilla());
            req.setEntidad(datosSesion.getEntidad());
            req.setCodigoMoneda("");
            try {
                boolean sigPag=true;
                res.setXm_MONEDA_E(new ArrayList<MonedaDTO>());
                do {
                    String jsonRes = this.util.callRestPost(req, CatalogoClientConstant.URICONSULTACATALOGOPMMONEDA);

                    ResMonedaDTO resultado = new ResMonedaDTO();
                    resultado = (ResMonedaDTO) this.util.jsonToObject(resultado, jsonRes);
                    res.getXm_MONEDA_E().addAll(resultado.getXm_MONEDA_E());

                    if(resultado.getXm_MONEDA_E().size()==50)
                    {
                        req.setCodigoMoneda(resultado.getXm_MONEDA_E().get(resultado.getXm_MONEDA_E().size()-1).getCod_NUMRCO_MONEDA());
                    }
                    else
                    {
                        sigPag =false;
                    }


                } while (sigPag);
                res.setEstatus("1");
                res.setMensaje("");
            } catch (ParseException ex) {
                log.error(ex.getMessage());
            }
        } else {
            res.setEstatus("0");
            res.setMensaje("La sesi\u00f3n no est\u00e1 activa.");
        }

        return res;
    }

    /*
     * Metodo para consumir catalogos de pasivo plazo
     */
    public ResCatPasivoPlazo catalogosPasivoPlazo(ReqCatPasivoPlazo req, HttpServletRequest httpServletRequest) {
        ResCatPasivoPlazo res = null;
        try {
            if (httpServletRequest.getSession() != null) {
                //Se obtienen credenciales de RUTA_CONFIG
                DatosSesionDTO datosSesion =
                        (DatosSesionDTO) httpServletRequest.getSession().getAttribute("datosSesion");
                req.setUsuario(datosSesion.getUsuario());
                req.setPassword(datosSesion.getPassword());
                req.setTerminal(datosSesion.getVentanilla());
                req.setEntidad(datosSesion.getEntidad());

                String jsonRes = this.util.callRestPost(req, CatalogoClientConstant.URICATPASIVOPLAZO);

                res = new ResCatPasivoPlazo();

                res = (ResCatPasivoPlazo) this.util.jsonToObject(res, jsonRes,  new ArrayList<String>());

            } else {
                res.setEstatus("0");
                res.setMensaje("La sesi\u00f3n no est\u00e1 activa.");
            }
        } catch (ParseException ex) {
            log.error(ex.getMessage());
        }
        return res;
    }

    /*
     * Metodo para consumir servicio de catalogos para personas morales2.
     */
    public ResCatPerMorDTO catPerMor(
            ReqCatPerMorDTO req,
            HttpServletRequest httpServletRequest) {
        ResCatPerMorDTO res = new ResCatPerMorDTO();
        if (httpServletRequest.getSession() != null) {
            //Se obtienen credenciales de RUTA_CONFIG
            DatosSesionDTO datosSesion =
                    (DatosSesionDTO) httpServletRequest.getSession().getAttribute("datosSesion");
            req.setUsuario(datosSesion.getUsuario());
            req.setPassword(datosSesion.getPassword());
            try {
                boolean sigPag;
                RegistrosCatPerMorDTO registrosCatPerMorDTO
                        = new RegistrosCatPerMorDTO(new ArrayList<RegistroCatPerMorDTO>());
                res.setResponseBansefi(registrosCatPerMorDTO);
                do {
                    String jsonRes = this.util.callRestPost(req, CatalogoClientConstant.URICONSULTACATPEMO);

                    ResCatPerMorDTO resultado = new ResCatPerMorDTO();

                    jsonRes = jsonRes.replaceAll("CLAVE-FILA", "claveFila");
                    jsonRes = jsonRes.replaceAll("DESCR-LARGA", "descrLarga");

                    ArrayList<String> nodos = new ArrayList<String>();
                    nodos.add("EjecutarResponse");
                    nodos.add("EjecutarResult");

                    resultado = (ResCatPerMorDTO) this.util.jsonToObject(resultado, jsonRes, nodos);

                    if (resultado.getCODIGO().equals("SNTEA102")) {
                        sigPag = true;
                        req.setIndPag("F");
                        req.setCveFila(
                                resultado
                                    .getResponseBansefi()
                                    .getResponseBansefi()
                                    .get(
                                        resultado
                                            .getResponseBansefi()
                                            .getResponseBansefi()
                                            .size() -1)
                                    .getClaveFila());
                    } else {
                        sigPag = false;
                        res.setSTATUS(resultado.getSTATUS());
                        res.setCODIGO(resultado.getCODIGO());
                        res.setMENSAJE(resultado.getMENSAJE().trim());
                    }
                    res.getResponseBansefi().getResponseBansefi().addAll(resultado.getResponseBansefi().getResponseBansefi());
                } while (sigPag);
            } catch (ParseException ex) {
                log.error(ex.getMessage());
            }
        } else {
            res.setSTATUS("0");
            res.setMENSAJE("La sesi\u00f3n no est\u00e1 activa.");
        }

        return res;
    }

}
