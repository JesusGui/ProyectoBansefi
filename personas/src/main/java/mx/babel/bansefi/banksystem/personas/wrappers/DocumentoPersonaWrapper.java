package mx.babel.bansefi.banksystem.personas.wrappers;

import mx.babel.bansefi.banksystem.base.beans.DocumentoPersonaBean;
import mx.babel.bansefi.banksystem.personas.webservices.altadocumentospersona.Ejecutar.ITRPEALTADCTRNI.TRPEALTADCEVTY;
import mx.babel.bansefi.banksystem.personas.webservices.altadocumentospersona.ResponseBansefi.OTRPEALTADCTRNO.TRPEALTADCEVTZ;
import mx.babel.bansefi.banksystem.personas.webservices.consultadetalledocumento.ResponseBansefi.OTRCONSDOCUMENTOTRNO.TRCONSDOCUMENTOEVTZ;
import mx.babel.bansefi.banksystem.personas.webservices.modificadocumentopersona.Ejecutar.ITRMODIFDOCUMENTOTRNI.TRMODIFDOCUMENTOEVTY;
import mx.babel.bansefi.banksystem.personas.webservices.modificadocumentopersona.ResponseBansefi.OTRMODIFDOCUMENTOTRNO.TRMODIFDOCUMENTOEVTZ;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Bean encargado de realizar mapeos entre respuestas de webservices hacia beans
 * relacionados a documentos de personas.
 * 
 * @author luis.gonzalez
 * 
 */
@Component
public class DocumentoPersonaWrapper {

    /**
     * Asignación de bean de mapeo
     */
    @Autowired
    DozerBeanMapper dozerBeanMapper;


    /**
     * Se encarga de mapear la respuesta de detalle de un documento
     * 
     * @param documentoDetalle
     * @param documento
     */
    public void wrappDocumentoDetalleBean(TRCONSDOCUMENTOEVTZ documentoDetalle,
            DocumentoPersonaBean documento) {
        Mapper mapper = dozerBeanMapper;
        mapper.map(documentoDetalle, documento, "documentoPersonaDetalle");
    }

    /**
     * Se encarga de mapear la entrada para el alta de un documento
     * 
     * @param documento
     */
    public TRPEALTADCEVTY wrappDocumentoAltaBean(
            DocumentoPersonaBean documento) {
        Mapper mapper = dozerBeanMapper;
        return mapper.map(documento, TRPEALTADCEVTY.class,
                "documentoPersonaAlta");
    }
    
    /**
     * Se encarga de mapear la respuesta del alta de documento con el propio documento para completar datos
     * 
     * @param documentoDetalle
     * @param documento
     */
    public void wrappDocumentoAltaDetalleBean(TRPEALTADCEVTZ documentoDetalle,
            DocumentoPersonaBean documento) {
        Mapper mapper = dozerBeanMapper;
        mapper.map(documentoDetalle, documento, "documentoPersonaAltaDatosDevueltos");
    }
    
    /**
     * Se encarga de mapear la entrada para la modificación de un documento
     * 
     * @param documento
     */
    public TRMODIFDOCUMENTOEVTY wrappDocumentoModificacionBean(
            DocumentoPersonaBean documento) {
        Mapper mapper = dozerBeanMapper;
        return mapper.map(documento, TRMODIFDOCUMENTOEVTY.class,
                "documentoPersonaModificacion");
    }
    
    /**
     * Se encarga de mapear la respuesta de la modificacion de documento con el propio documento para completar datos
     * 
     * @param documentoDetalle
     * @param documento
     */
    public void wrappDocumentoModificacionDetalleBean(TRMODIFDOCUMENTOEVTZ documentoDetalle,
            DocumentoPersonaBean documento) {
        Mapper mapper = dozerBeanMapper;
        mapper.map(documentoDetalle, documento, "documentoPersonaModificacionDatosDevueltos");
    }
    
}
