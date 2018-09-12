package mx.babel.bansefi.banksystem.personas.backend;

import java.io.Serializable;

import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.arq.sesion.utils.ContextoUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.DocumentoPersonaBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.constants.ConstantesFuncionales;
import mx.babel.bansefi.banksystem.personas.webservices.altadocumentospersona.AltaDocumentosPersonaServicio;
import mx.babel.bansefi.banksystem.personas.webservices.altadocumentospersona.Ejecutar;
import mx.babel.bansefi.banksystem.personas.webservices.altadocumentospersona.Ejecutar.ITRPEALTADCTRNI;
import mx.babel.bansefi.banksystem.personas.webservices.altadocumentospersona.EjecutarResult;
import mx.babel.bansefi.banksystem.personas.webservices.altadocumentospersona.ResponseBansefi;
import mx.babel.bansefi.banksystem.personas.wrappers.DocumentoPersonaWrapper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Backend que se encarga de dar de alta un documento llamando a la TRN
 * TR_PE_ALTA_DC_TRN
 * 
 * @author luis.gcastellano
 * 
 */
@Component
public class AltaDocumentoPersonaBackend extends BackEndBean implements
        Serializable {

    private static final long serialVersionUID = 7909213239424761622L;

    public static final Integer MIN_FECHA= 11111111;
    
    private static final Logger LOGGER = LogManager
            .getLogger(AltaDocumentoPersonaBackend.class.getName());
    
    @Autowired
    private ServicioWebUtils servicioWebUtils;
    
    @Autowired
    private DocumentoPersonaWrapper documentoWrapper;
    
    @Autowired
    private ContextoUtils contextoUtils;

    public int ejectuarTRN(final DocumentoPersonaBean documento, final Integer idInternaPersona) {

        LOGGER.debug("Entra al metrodo ejectuarTRN");

        ResponseBansefi response = null;

        Ejecutar.ITRPEALTADCTRNI contexto = this.initPeticion(documento, idInternaPersona);

        EjecutarResult respuesta = ejecutarWS(contexto);

        super.verificaRespuesta(respuesta);
        
        if (verificaResponseBansefi(respuesta)) {
            response = respuesta.getResponseBansefi();
        }
                
        this.documentoWrapper.wrappDocumentoAltaDetalleBean(response.getOTRPEALTADCTRNO().getTRPEALTADCEVTZ(), documento);
        
        LOGGER.debug("Sale del metrodo ejectuarTRN");

        return response.getOTRPEALTADCTRNO().getRTRNCD();
    }    
    
    /**
     * Función que valida que la respuesta del servidor no este vacía.
     * 
     * @param respuesta
     *            Objeto respuesta del servicio web
     * @return <code>true</code> si la respuesta no esta vacía.
     */
    private Boolean verificaResponseBansefi(EjecutarResult respuesta) {
        Boolean noNulo = false;
        if (respuesta != null && respuesta.getResponseBansefi() != null) {
            noNulo = true;
        }
        return noNulo;
    }
    
    /**
     * Función para inicializar los atributos del objeto de peticíon al servicio
     * web.
     * 
     * @param req
     *            bean con los datos de entrada
     * @return Objeto de petición al web service
     */
    private ITRPEALTADCTRNI initPeticion(final DocumentoPersonaBean documento, final Integer idInternaPersona) {
        Ejecutar.ITRPEALTADCTRNI contexto = new Ejecutar.ITRPEALTADCTRNI();
        Ejecutar.ITRPEALTADCTRNI.STDINDLOCALCODIFV stdindlocalcodifv = new Ejecutar.ITRPEALTADCTRNI.STDINDLOCALCODIFV();
        Ejecutar.ITRPEALTADCTRNI.STDTRNIPARMV stdtrniparmv = new Ejecutar.ITRPEALTADCTRNI.STDTRNIPARMV();
        Ejecutar.ITRPEALTADCTRNI.TRPEALTADCEVTY trpealtadcevty = new Ejecutar.ITRPEALTADCTRNI.TRPEALTADCEVTY();
        
       
        trpealtadcevty = this.documentoWrapper.wrappDocumentoAltaBean(documento);
        
        //Calcula si el documento esta caducado dependiendo de la fecha de caducidad
        if(documento.getFechaCaducidad()!=null && contextoUtils.getFechaContableActual().compareTo(documento.getFechaCaducidad())>0){
            trpealtadcevty.setINDCDCDADDC("S");
        }else {
            trpealtadcevty.setINDCDCDADDC("N");
        }
        
        //Seteamos a S este indicador para que no se creen anotaciones de documentos caducados (este check no existe en la vista)
        trpealtadcevty.setINDVIGENDC("S");
        trpealtadcevty.setCONJUNTADCLBIEN("N");
        
        //Se deben setear estos valores de fechas por el problema de documento caducado
        if(documento.getFechaCaducidad()==null){
            trpealtadcevty.setFECHACDCDADDC(ConstantesFuncionales.MAX_FECHA_FIN_INTEGER);
        }
        if(documento.getFechaProxima()==null){
            trpealtadcevty.setFECHAREVDC(AltaDocumentoPersonaBackend.MIN_FECHA);
        }
        if(documento.getFecha()==null){
            trpealtadcevty.setFECHAEXPDC(AltaDocumentoPersonaBackend.MIN_FECHA);
        }
        
        trpealtadcevty.setCODNRBEEN(super.getEntidad());
        trpealtadcevty.setIDINTERNOPE(idInternaPersona);
        if(documento.getOficina() != null){
        	trpealtadcevty.setCODINTERNOUO(documento.getOficina().getClaveFila());
        }else{
        	trpealtadcevty.setCODINTERNOUO(super.getSucursal());
        }
        
        super.initialize(stdindlocalcodifv);
        
        super.initialize(stdtrniparmv);
        stdtrniparmv.setCODTX(CodTxConstants.COD_TX_TR_PE_ALTA_DC_TRN);
        stdtrniparmv.setIDINTERNOTERMTN(super.getTerminal());
        
        contexto.setSTDINDLOCALCODIFV(stdindlocalcodifv);
        contexto.setSTDTRNIPARMV(stdtrniparmv);
        contexto.setTRPEALTADCEVTY(trpealtadcevty);
        return contexto;
    }
    
    /**
     * Función para invocar al servicio web y obtener su respuesta.
     * 
     * @param contexto
     *            Objeto de petición al servicio web
     * @return La respuesta del servicio web.
     */
    private EjecutarResult ejecutarWS(Ejecutar.ITRPEALTADCTRNI contexto)
            throws NoControlableException {
        EjecutarResult respuesta = null;

        try {
            respuesta = (EjecutarResult) this.servicioWebUtils.ejecutarWS(
                    AltaDocumentosPersonaServicio.class, contexto);
        } catch (NoControlableException e) {
            throw new NoControlableException(
                    "Error al invocar servicio web alta de documento ", e);
        }
        return respuesta;
    }
    
}
