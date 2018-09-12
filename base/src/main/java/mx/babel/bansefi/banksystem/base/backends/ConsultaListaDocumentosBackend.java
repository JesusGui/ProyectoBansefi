package mx.babel.bansefi.banksystem.base.backends;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import mx.babel.arq.catalogo.beans.CatalogoBean;
import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.beans.DocumentoPersonaBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.enums.CatalogoEnum;
import mx.babel.bansefi.banksystem.base.enums.EstadoListadosEnum;
import mx.babel.bansefi.banksystem.base.utils.CatalogoUtils;
import mx.babel.bansefi.banksystem.base.webservices.consultalistadocumentos.ConsultaListaDocumentosServicio;
import mx.babel.bansefi.banksystem.base.webservices.consultalistadocumentos.Ejecutar;
import mx.babel.bansefi.banksystem.base.webservices.consultalistadocumentos.EjecutarResult;
import mx.babel.bansefi.banksystem.base.webservices.consultalistadocumentos.ResponseBansefi;
import mx.babel.bansefi.banksystem.base.webservices.consultalistadocumentos.ResponseBansefi.OTRDCLSCNSTRNO.TRDCLSCNSEVTZ.TRDCLSCNSEVTV;
import mx.babel.bansefi.banksystem.base.wrappers.ClienteWrapper;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Se encarga de consultar los documentos asociados a un cliente
 * 
 * @author luis.gcastellano
 * 
 */

@Component
public class ConsultaListaDocumentosBackend extends BackEndBean {

    private static final long serialVersionUID = 7536502973333153862L;

    private static final Logger LOGGER = LogManager
            .getLogger(ConsultaListaDocumentosBackend.class.getName());

    @Autowired
    private ServicioWebUtils servicioWebUtils;

    @Autowired
    private CatalogoUtils catalogoUtils;

    @Autowired
    private ClienteWrapper clienteWrapper;

    public List<DocumentoPersonaBean> ejectuarTRN(int codigoInternoPersona,
            Boolean ordenaAlfabeticamente) {
        LOGGER.debug("Entra al metodo ejectuarTRN");
        List<DocumentoPersonaBean> documentosList = this
                .getListaDocumentosResponse(codigoInternoPersona,
                        ordenaAlfabeticamente);
        LOGGER.debug("Sale del metodo ejectuarTRN");
        return documentosList;
    }

    /**
     * Función para obtener una lista de documentos invocando un servicio web.
     * 
     * @param codigo
     *            intrrno de cliente
     * @return Objeto con atributos de los documentos.
     */
    public List<DocumentoPersonaBean> getListaDocumentosResponse(
            int codigoInternoPersona, Boolean ordenaAlfabeticamente)
            throws NoControlableException, ControlableException {

        LOGGER.debug("Entra al metodo getListaDocumentosResponse");
        Ejecutar.ITRDCLSCNSTRNI contexto = this
                .initPeticion(codigoInternoPersona);

        EjecutarResult respuesta = ejecutarWS(contexto);
        
        try {
            this.verificaRespuesta(respuesta);
        } catch (ControlableException ce) {
            if (ce.getRtncod() != RETURN_COD_SIN_DATOS) {
                throw ce;        
            }
        }
        
        List<DocumentoPersonaBean> documentosList = null;

        if (verificaResponseBansefi(respuesta)) {
            documentosList = getDocumentosList(respuesta.getResponseBansefi(),
                    codigoInternoPersona);
            if (ordenaAlfabeticamente && documentosList != null
                    && documentosList.size() > 0) {
                Collections.sort(documentosList,
                        new Comparator<DocumentoPersonaBean>() {
                            @Override
                            public int compare(
                                    final DocumentoPersonaBean object1,
                                    final DocumentoPersonaBean object2) {
                                return object1.getTipoDesc().compareTo(
                                        object2.getTipoDesc());
                            }
                        });
            }
        }
        LOGGER.debug("Sale del metodo getListaDocumentosResponse");
        return documentosList;
    }

    /**
     * Función encargada de obtener la lista de documentos a partir de la
     * respuesta del servicio web
     * 
     * @param response
     *            El objeto de reultado enviado por el servicio web.
     * @return La lista de documentos con los datos recibidos del servicio web
     */
    private List<DocumentoPersonaBean> getDocumentosList(
            ResponseBansefi response, int codigoInternoPersona){
        LOGGER.debug("Entra al metodo getDocumentosList");
        DocumentoPersonaBean documento = null;
        List<DocumentoPersonaBean> documentoList = null;        

        documentoList = new ArrayList<DocumentoPersonaBean>();
        for (int i = 0; i < response.getOTRDCLSCNSTRNO().getNUMBEROFRECORDS(); i++) {
            TRDCLSCNSEVTV documentoWs = response.getOTRDCLSCNSTRNO()
                    .getTRDCLSCNSEVTZ().getTRDCLSCNSEVTV().get(i);
            documento = this.clienteWrapper
                    .wrappDocumentoListaBean(documentoWs);
            try{
            	if (StringUtils.isNotBlank(documento.getTipo())) {
                    CatalogoBean catalogo = catalogoUtils.getCatalogoBean(
                            CatalogoEnum.TP_DOC, documento.getTipo());

                    documento.setTipoDesc(catalogo.getDescripcionL());
                    documento.setEstado(EstadoListadosEnum.CONSULTADO);
                }
                documentoList.add(documento);
            }catch (ControlableException ce){
            	LOGGER.debug("No existe el tipo de documento recuperado: "+documento.getTipo(),ce);
            }
            
        }

        return documentoList;
    }

    /**
     * Función para inicializar los atributos del objeto de peticíon al servicio
     * web.
     * 
     * @param object
     *            bean con los datos de entrada
     * @return Objeto de petición al web service
     */
    private Ejecutar.ITRDCLSCNSTRNI initPeticion(int codigoInternoPersona) {
        LOGGER.debug("Entra al metodo initPeticion");
        Ejecutar.ITRDCLSCNSTRNI contexto = new Ejecutar.ITRDCLSCNSTRNI();

        Ejecutar.ITRDCLSCNSTRNI.TRDCLSCNSEVTY trdclsdcnsevty = new Ejecutar.ITRDCLSCNSTRNI.TRDCLSCNSEVTY();
        Ejecutar.ITRDCLSCNSTRNI.TRDCLSCNSEVTY.PEPERSRLDOCP pepersrldocp = new Ejecutar.ITRDCLSCNSTRNI.TRDCLSCNSEVTY.PEPERSRLDOCP();

        Ejecutar.ITRDCLSCNSTRNI.TRDCLSCNSEVTY.DCLSCB dclscb = new Ejecutar.ITRDCLSCNSTRNI.TRDCLSCNSEVTY.DCLSCB();
        Ejecutar.ITRDCLSCNSTRNI.TRDCLSCNSEVTY.DCLSCB.DCCBV dccbv = new Ejecutar.ITRDCLSCNSTRNI.TRDCLSCNSEVTY.DCLSCB.DCCBV();

        Ejecutar.ITRDCLSCNSTRNI.STDAPPLPARMV stdapplparmv = new Ejecutar.ITRDCLSCNSTRNI.STDAPPLPARMV();
        Ejecutar.ITRDCLSCNSTRNI.STDTRNIPARMV stdtrniparmv = new Ejecutar.ITRDCLSCNSTRNI.STDTRNIPARMV();

        super.initialize(pepersrldocp);
        pepersrldocp.setCODNRBEEN(super.getEntidad());
        pepersrldocp.setIDINTERNOPE(codigoInternoPersona);

        super.initialize(dccbv);
        super.initialize(dclscb);

        dclscb.setDCCBV(dccbv);

        super.initialize(stdapplparmv);
        stdapplparmv.setCODTX(CodTxConstants.COD_TX_TR_DC_LS_CNS_TRN);
        stdapplparmv.setCODNRBEEN(super.getEntidad());
        stdapplparmv.setIDINTERNOTERMTN(super.getTerminal());

        super.initialize(stdtrniparmv);
        stdtrniparmv.setCODTX(CodTxConstants.COD_TX_TR_DC_LS_CNS_TRN);

        stdtrniparmv.setIDINTERNOTERMTN(super.getTerminal());

        super.initialize(contexto);

        trdclsdcnsevty.setDCLSCB(dclscb);
        trdclsdcnsevty.setPEPERSRLDOCP(pepersrldocp);

        contexto.setSTDAPPLPARMV(stdapplparmv);
        contexto.setSTDTRNIPARMV(stdtrniparmv);
        contexto.setTRDCLSCNSEVTY(trdclsdcnsevty);
        contexto.setSCROLLABLEOCCURS(BackEndBean.SCROLLABLE_OCCURS);

        return contexto;

    }

    /**
     * Función para invocar al servicio web y obtener su respuesta.
     * 
     * @param contexto
     *            Objeto de petición al servicio web
     * @return La respuesta del servicio web.
     */
    private EjecutarResult ejecutarWS(Ejecutar.ITRDCLSCNSTRNI contexto)
            throws NoControlableException {
        LOGGER.debug("Entra al metodo ejecutarWS");
        EjecutarResult respuesta = null;
        Ejecutar ejecutar = new Ejecutar();
        ejecutar.setITRDCLSCNSTRNI(contexto);
        try {
            respuesta = (EjecutarResult) this.servicioWebUtils.ejecutarWS(
                    ConsultaListaDocumentosServicio.class, contexto);
        } catch (NoControlableException e) {
            throw new NoControlableException(
                    "Error al invocar servicio web de consulta de "
                            + "documentos.", e);
        }
        LOGGER.debug("Sale del metodo ejecutarWS");
        return respuesta;
    }

    /**
     * Función que valida que la respuesta del servidor no este vacía.
     * 
     * @param respuesta
     *            Objeto respuesta del servicio web
     * @return <code>true</code> si la respuesta no esta vacía.
     */
    private Boolean verificaResponseBansefi(EjecutarResult respuesta) {
        LOGGER.debug("Entra al metodo verificaResponseBansefi");
        Boolean noNulo = false;
        if (respuesta != null && respuesta.getResponseBansefi() != null) {
            noNulo = true;
        }
        LOGGER.debug("Sale del metodo verificaResponseBansefi con valor -> "
                + noNulo);
        return noNulo;
    }

}
