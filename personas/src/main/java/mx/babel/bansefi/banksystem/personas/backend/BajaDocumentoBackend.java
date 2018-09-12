package mx.babel.bansefi.banksystem.personas.backend;

import java.io.Serializable;

import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.DocumentoPersonaBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.personas.webservices.bajadocumento.BajaDocumentoServicio;
import mx.babel.bansefi.banksystem.personas.webservices.bajadocumento.Ejecutar;
import mx.babel.bansefi.banksystem.personas.webservices.bajadocumento.Ejecutar.ITRPEBAJADCTRNI;
import mx.babel.bansefi.banksystem.personas.webservices.bajadocumento.EjecutarResult;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Backend que se encarga de dar de baja un documento llamando a la TRN
 * TR_PE_BAJA_DC_TRN
 * 
 * @author luis.gcastellano
 * 
 */
@Component
public class BajaDocumentoBackend extends BackEndBean implements Serializable {

    private static final long serialVersionUID = -29583752324199567L;

    private static final Logger LOGGER = LogManager
            .getLogger(BajaDocumentoBackend.class.getName());

    @Autowired
    private ServicioWebUtils servicioWebUtils;

    public int ejectuarTRN(final DocumentoPersonaBean documento, final Integer idInternaPersona) {

        LOGGER.debug("Entra al metrodo ejectuarTRN");

        Ejecutar.ITRPEBAJADCTRNI contexto = this.initPeticion(documento, idInternaPersona);

        EjecutarResult respuesta = ejecutarWS(contexto);        

        super.verificaRespuesta(respuesta);
        
        return respuesta.getResponseBansefi().getOTRPEBAJADCTRNO().getRTRNCD();
    }
    
    /**
     * Función para invocar al servicio web y obtener su respuesta.
     * 
     * @param contexto
     *            Objeto de petición al servicio web
     * @return La respuesta del servicio web.
     */
    private EjecutarResult ejecutarWS(Ejecutar.ITRPEBAJADCTRNI contexto){
        EjecutarResult respuesta = null;

        try {
            respuesta = (EjecutarResult) this.servicioWebUtils.ejecutarWS(
                    BajaDocumentoServicio.class, contexto);
        } catch (NoControlableException e) {
            throw new NoControlableException(
                    "Error al invocar servicio web baja de documento ", e);
        }
        return respuesta;
    }
    
    /**
     * Función para inicializar los atributos del objeto de peticíon al servicio
     * web.
     * 
     * @param req
     *            bean con los datos de entrada
     * @return Objeto de petición al web service
     */
    private ITRPEBAJADCTRNI initPeticion(final DocumentoPersonaBean documento, final Integer idInternaPersona) {
        Ejecutar.ITRPEBAJADCTRNI contexto = new Ejecutar.ITRPEBAJADCTRNI();
        Ejecutar.ITRPEBAJADCTRNI.TRPEBAJADCEVTY trpebajadcevty = new Ejecutar.ITRPEBAJADCTRNI.TRPEBAJADCEVTY();
        Ejecutar.ITRPEBAJADCTRNI.STDTRNIPARMV stdtrniparmbv = new Ejecutar.ITRPEBAJADCTRNI.STDTRNIPARMV();
                
        Ejecutar.ITRPEBAJADCTRNI.TRPEBAJADCEVTY.BIBIENRLDOCP bibienrldocp = new Ejecutar.ITRPEBAJADCTRNI.TRPEBAJADCEVTY.BIBIENRLDOCP();
        Ejecutar.ITRPEBAJADCTRNI.TRPEBAJADCEVTY.PEPERSRLDOCP pepersrldocp = new Ejecutar.ITRPEBAJADCTRNI.TRPEBAJADCEVTY.PEPERSRLDOCP();
        
        super.initialize(pepersrldocp);
        pepersrldocp.setCODDOC(documento.getTipo());
        pepersrldocp.setCODNRBEEN(super.getEntidad());
        pepersrldocp.setIDINTERNODC(Integer.parseInt(documento.getId()));
        pepersrldocp.setIDINTERNOPE(idInternaPersona);
        super.initialize(bibienrldocp);
        
        super.initialize(stdtrniparmbv);
        stdtrniparmbv.setCODTX(CodTxConstants.COD_TX_TR_PE_BAJA_DC_TRN);
        stdtrniparmbv.setIDINTERNOTERMTN(super.getTerminal());
        
        
        trpebajadcevty.setBIBIENRLDOCP(bibienrldocp);
        trpebajadcevty.setPEPERSRLDOCP(pepersrldocp);
        
        contexto.setTRPEBAJADCEVTY(trpebajadcevty);
        contexto.setSTDTRNIPARMV(stdtrniparmbv);
        
        return contexto;
    }
    
}
