package mx.babel.bansefi.banksystem.personas.backend;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.arq.sesion.utils.ContextoUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.DocumentoPersonaBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.constants.ConstantesFuncionales;
import mx.babel.bansefi.banksystem.personas.webservices.modificadocumentopersona.Ejecutar;
import mx.babel.bansefi.banksystem.personas.webservices.modificadocumentopersona.Ejecutar.ITRMODIFDOCUMENTOTRNI;
import mx.babel.bansefi.banksystem.personas.webservices.modificadocumentopersona.EjecutarResult;
import mx.babel.bansefi.banksystem.personas.webservices.modificadocumentopersona.ModificaDocumentoPersonaServicio;
import mx.babel.bansefi.banksystem.personas.webservices.modificadocumentopersona.ResponseBansefi;
import mx.babel.bansefi.banksystem.personas.wrappers.DocumentoPersonaWrapper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Backend que se encarga de modificar un documento llamando a la TRN
 * TR_MODIF_DOCUMENTO_TRN
 * 
 * @author luis.gcastellano
 * 
 */
@Component
public class ModificaDocumentoPersonaBackend extends BackEndBean{

    private static final long serialVersionUID = 3564567980919350129L;

    public static final Integer MIN_FECHA= 11111111;
    
    private static final Logger LOGGER = LogManager
            .getLogger(ModificaDocumentoPersonaBackend.class.getName());
    
    @Autowired
    private ServicioWebUtils servicioWebUtils;
    
    @Autowired
    private DocumentoPersonaWrapper documentoWrapper;
    
    @Autowired
    private ContextoUtils contextoUtils;
    
    public int ejectuarTRN(final DocumentoPersonaBean documento) {

        LOGGER.debug("Entra al metrodo ejectuarTRN");

        ResponseBansefi response = this.getModificacionDocumentoResponse(documento);
        
        this.getNuevosDatosDocumento(documento, response);
        
        LOGGER.debug("Sale del metrodo ejectuarTRN");

        return response.getOTRMODIFDOCUMENTOTRNO().getRTRNCD();
    }
        
    /**
     * Función para modificar un documento invocando un servicio web.
     * 
     * @param documento
     *            bean con los datos de entrada
     * @return Objeto indicando se ha realizado bien la modificacion
     */
    public ResponseBansefi getModificacionDocumentoResponse(final DocumentoPersonaBean documento)
            throws NoControlableException, ControlableException {
        LOGGER.debug("Entra al metrodo getModificacionDocumentoResponse");

        ResponseBansefi response = null;

        Ejecutar.ITRMODIFDOCUMENTOTRNI contexto = this.initPeticion(documento);

        EjecutarResult respuesta = ejecutarWS(contexto);
        
        super.verificaRespuesta(respuesta);
        
        if (verificaResponseBansefi(respuesta)) {
            response = respuesta.getResponseBansefi();
        }
        LOGGER.debug("Sale del metrodo getModificacionDocumentoResponse");
        return response;
    }
    
    /**
     * Función para invocar al servicio web y obtener su respuesta.
     * 
     * @param contexto
     *            Objeto de petición al servicio web
     * @return La respuesta del servicio web.
     */
    private EjecutarResult ejecutarWS(Ejecutar.ITRMODIFDOCUMENTOTRNI contexto)
            throws NoControlableException {
        EjecutarResult respuesta = null;

        try {
            respuesta = (EjecutarResult) this.servicioWebUtils.ejecutarWS(
                    ModificaDocumentoPersonaServicio.class, contexto);
        } catch (NoControlableException e) {
            throw new NoControlableException(
                    "Error al invocar servicio web modificacion de documento ", e);
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
    private ITRMODIFDOCUMENTOTRNI initPeticion(final DocumentoPersonaBean documento) {
        Ejecutar.ITRMODIFDOCUMENTOTRNI contexto = new Ejecutar.ITRMODIFDOCUMENTOTRNI();
        Ejecutar.ITRMODIFDOCUMENTOTRNI.STDINDLOCALCODIFV stdindlocalcodifv = new Ejecutar.ITRMODIFDOCUMENTOTRNI.STDINDLOCALCODIFV();
        Ejecutar.ITRMODIFDOCUMENTOTRNI.STDTRNIPARMV stdtrniparmv = new Ejecutar.ITRMODIFDOCUMENTOTRNI.STDTRNIPARMV();
        Ejecutar.ITRMODIFDOCUMENTOTRNI.TRMODIFDOCUMENTOEVTY trmodifdocumentoevty = new Ejecutar.ITRMODIFDOCUMENTOTRNI.TRMODIFDOCUMENTOEVTY();
        Ejecutar.ITRMODIFDOCUMENTOTRNI.TRMODIFDOCUMENTOEVTY.DCDOCP dcdocp = new Ejecutar.ITRMODIFDOCUMENTOTRNI.TRMODIFDOCUMENTOEVTY.DCDOCP();
        
        super.initialize(dcdocp);
        dcdocp.setCODNRBEEN(super.getEntidad());
        dcdocp.setIDINTERNODC(Integer.parseInt(documento.getId()));
        
        trmodifdocumentoevty = this.documentoWrapper.wrappDocumentoModificacionBean(documento);
        
        //Calcula si el documento esta caducado dependiendo de la fecha de caducidad
        if(documento.getFechaCaducidad()!=null && contextoUtils.getFechaContableActual().compareTo(documento.getFechaCaducidad())>0){
            trmodifdocumentoevty.setINDCDCDADDC("S");
        }else {
            trmodifdocumentoevty.setINDCDCDADDC("N");
        }
        
        //Seteamos a S este indicador para que no se creen anotaciones de documentos caducados (este check no existe en la vista)
        trmodifdocumentoevty.setINDVIGENDC("S");
        trmodifdocumentoevty.setCONJUNTADCLBIEN("N");
        
        //Se deben setear estos valores de fechas por el problema de documento caducado
        if(documento.getFechaCaducidad()==null){
            trmodifdocumentoevty.setFECHACDCDADDC(ConstantesFuncionales.MAX_FECHA_FIN_INTEGER);
        }
        if(documento.getFechaProxima()==null){
            trmodifdocumentoevty.setFECHAREVDC(ModificaDocumentoPersonaBackend.MIN_FECHA);
        }
        if(documento.getFecha()==null){
            trmodifdocumentoevty.setFECHAEXPDC(ModificaDocumentoPersonaBackend.MIN_FECHA);
        }
        trmodifdocumentoevty.setDCDOCP(dcdocp);

        
        super.initialize(stdindlocalcodifv);
        super.initialize(stdtrniparmv);
        stdtrniparmv.setCODTX(CodTxConstants.COD_TX_TR_PE_ALTA_DC_TRN);
        stdtrniparmv.setIDINTERNOTERMTN(super.getTerminal());
        
        contexto.setSTDINDLOCALCODIFV(stdindlocalcodifv);
        contexto.setSTDTRNIPARMV(stdtrniparmv);
        contexto.setTRMODIFDOCUMENTOEVTY(trmodifdocumentoevty);
        return contexto;
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
        if (respuesta != null
                && respuesta.getResponseBansefi() != null) {
            noNulo = true;
        }
        return noNulo;
    }
    
    /**
     * Una vez dado de alta el documento se encarga de añadir los valores devueltos por HOST al documento
     * 
     * @param documento
     * @param response
     */
    public void getNuevosDatosDocumento(DocumentoPersonaBean documento, ResponseBansefi response){
        this.documentoWrapper.wrappDocumentoModificacionDetalleBean(response.getOTRMODIFDOCUMENTOTRNO().getTRMODIFDOCUMENTOEVTZ(), documento);
    }
}
