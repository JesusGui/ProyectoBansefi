package mx.babel.bansefi.banksystem.personas.backend;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import mx.babel.arq.catalogo.beans.CatalogoBean;
import mx.babel.arq.catalogo.beans.CatalogoGeoBean;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.DocumentoPersonaBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.constants.ConstantesFuncionales;
import mx.babel.bansefi.banksystem.base.enums.CatalogoEnum;
import mx.babel.bansefi.banksystem.base.utils.CatalogoCentrosLoaderTask;
import mx.babel.bansefi.banksystem.base.utils.CatalogoGeoUtils;
import mx.babel.bansefi.banksystem.base.utils.CatalogoUtils;
import mx.babel.bansefi.banksystem.personas.webservices.consultadetalledocumento.ConsultaDetalleDocumentoServicio;
import mx.babel.bansefi.banksystem.personas.webservices.consultadetalledocumento.Ejecutar;
import mx.babel.bansefi.banksystem.personas.webservices.consultadetalledocumento.Ejecutar.ITRCONSDOCUMENTOTRNI;
import mx.babel.bansefi.banksystem.personas.webservices.consultadetalledocumento.EjecutarResult;
import mx.babel.bansefi.banksystem.personas.webservices.consultadetalledocumento.ResponseBansefi;
import mx.babel.bansefi.banksystem.personas.webservices.consultadetalledocumento.ResponseBansefi.OTRCONSDOCUMENTOTRNO.TRCONSDOCUMENTOEVTZ;
import mx.babel.bansefi.banksystem.personas.wrappers.DocumentoPersonaWrapper;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Backend que se encarga de consultar los detalles de un documento
 * TR_CONS_DOCUMENTO_TRN
 * 
 * @author luis.gcastellano
 * 
 */
@Component
public class ConsultaDetalleDocumentoBackend extends BackEndBean implements
        Serializable {

    private static final long serialVersionUID = 5121177249258327546L;

    private static final String FECHA_DEFECTO = "11/11/1111";
    private static final String FECHA_DEFECTO_CADUCIDAD = "31/12/9999";
    
    private static final Logger LOGGER = LogManager
            .getLogger(ConsultaDetalleDocumentoBackend.class.getName());

    @Autowired
    private CatalogoUtils catalogoUtils;
    
    @Autowired
    private CatalogoCentrosLoaderTask catalogoCentros;
    
    @Autowired
    private ServicioWebUtils servicioWebUtils;
    
    @Autowired
    DocumentoPersonaWrapper documentoPersonaWrapper;

    @Autowired
    private CatalogoGeoUtils catalogoGeoUtils;
    
    public void ejectuarTRN(final DocumentoPersonaBean documento) {

        LOGGER.debug("Entra al metodo ejectuarTRN");

        Ejecutar.ITRCONSDOCUMENTOTRNI contexto = this.initPeticion(documento);

        EjecutarResult respuesta = ejecutarWS(contexto);
        
        super.verificaRespuesta(respuesta);
        
        if (verificaResponseBansefi(respuesta)) {
            this.getDocumentoDetalle(respuesta.getResponseBansefi(), documento);

        }

        LOGGER.debug("Sale del metodo ejectuarTRN");

    }
    
    /**
     * Se encarga de extraer un DocumentoPersonaBean a partir de la respuesta del WS
     * @param respuesta Respuesta del WS de detalle de Documento
     * @return Un DocumentoPersonaBean con los datos rellenos
     */
    public void getDocumentoDetalle(ResponseBansefi response, DocumentoPersonaBean documento){
        LOGGER.debug("Entra al metodo getDocumentoDetalle");
       
        if(verificaRespuestaDocumento(response)){
            TRCONSDOCUMENTOEVTZ documentoDetalleWS = response.getOTRCONSDOCUMENTOTRNO().getTRCONSDOCUMENTOEVTZ();
            this.documentoPersonaWrapper.wrappDocumentoDetalleBean(documentoDetalleWS, documento);
            if (StringUtils.isNotBlank(documento.getTipo())) {
                CatalogoBean catalogo = catalogoUtils.getCatalogoBean(
                        CatalogoEnum.TP_DOC, documento.getTipo());
                if(documento!=null && documento.getFecha()!=null && documento.getFecha().after(new Date())){
                    documento.setFecha(null);
                }
                documento.setTipoDesc(catalogo.getDescripcionL());
            }
            
            if (documentoDetalleWS.getCODARGEO()!= null && !("").equalsIgnoreCase(documentoDetalleWS.getCODARGEO().trim())){
                boolean encontrado = false;
                List<CatalogoGeoBean> candidatos = catalogoGeoUtils.getCatalogoGeoBean();
                
                for (int i=0; i<candidatos.size() && !encontrado;i++){
                    if (candidatos.get(i).getCodigoMunicipio() != null && candidatos.get(i).getCodigoMunicipio().equals(documentoDetalleWS.getCODMUNICIPIOAG().trim())
                            && candidatos.get(i).getMunicipioLocalidad().trim().toUpperCase().contains(documentoDetalleWS.getNOMBLOCALIDADAG().trim().toUpperCase())){
                        documento.setMunicipioCatGeo(candidatos.get(i));
                        encontrado = true;
                    }
                }
            }
            
            SimpleDateFormat sdf = new SimpleDateFormat(ConstantesFuncionales.GENERAL_DATE_FORMATTER);
            Date fechaDefecto;
            Date fechaDefectoCaducidad;
            try {
                
                fechaDefectoCaducidad = sdf.parse(ConsultaDetalleDocumentoBackend.FECHA_DEFECTO_CADUCIDAD);
                fechaDefecto = sdf.parse(ConsultaDetalleDocumentoBackend.FECHA_DEFECTO);
                if(documento.getFecha()!=null && fechaDefecto.compareTo(documento.getFecha())==0){
                    documento.setFecha(null);
                }
                if(documento.getFechaCaducidad()!=null && fechaDefectoCaducidad.compareTo(documento.getFechaCaducidad())==0){
                    documento.setFechaCaducidad(null);
                }
                if(documento.getFechaEntrega()!=null && fechaDefecto.compareTo(documento.getFechaEntrega())==0){
                    documento.setFechaEntrega(null);
                }
                if(documento.getFechaProxima()!=null && fechaDefecto.compareTo(documento.getFechaProxima())==0){
                    documento.setFechaProxima(null);
                }
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                
            }
            
            
        }
        
        LOGGER.debug("Sale del metodo getDocumentoDetalle");
        
    }
    
    /**
     * Función que valida que la respuesta Bansefi contenga un objeto con los datos
     * de detale de bien
     * 
     * @param response Respuesta Bansefi con detalle de bien
     * @return <code>true</code> si la respuesta Bansefi contiene datos validos
     */
    private Boolean verificaRespuestaDocumento(ResponseBansefi response){
        LOGGER.debug("Entra al metodo verificaRespuestaDocumento");
        Boolean noNulo = false;
       
        if(response != null && response.getOTRCONSDOCUMENTOTRNO() != null && 
                response.getOTRCONSDOCUMENTOTRNO().getTRCONSDOCUMENTOEVTZ() != null){
            noNulo = true;
        }
        LOGGER.debug("Sale del metodo verificaRespuestaDocumento con valor -> " +noNulo);
        return noNulo;
    }
    

    /**
     * Función para inicializar los atributos del objeto de peticíon al servicio web.
     * 
     * @param object bean con los datos de entrada
     * @return Objeto de petición al web service
     */
    public ITRCONSDOCUMENTOTRNI initPeticion(final DocumentoPersonaBean documento){
        
        LOGGER.debug("Entra al metodo initPeticion");
        Ejecutar.ITRCONSDOCUMENTOTRNI contexto = new Ejecutar.ITRCONSDOCUMENTOTRNI();
        
        Ejecutar.ITRCONSDOCUMENTOTRNI.TRCONSDOCUMENTOEVTY trconsdocumentoevty = new Ejecutar.ITRCONSDOCUMENTOTRNI.TRCONSDOCUMENTOEVTY();
        Ejecutar.ITRCONSDOCUMENTOTRNI.TRCONSDOCUMENTOEVTY.DCDOCP dcdocp = new Ejecutar.ITRCONSDOCUMENTOTRNI.TRCONSDOCUMENTOEVTY.DCDOCP();
        Ejecutar.ITRCONSDOCUMENTOTRNI.STDTRNIPARMV stdtrniparmv = new Ejecutar.ITRCONSDOCUMENTOTRNI.STDTRNIPARMV();
        
        
        super.initialize(dcdocp);
        dcdocp.setCODNRBEEN(super.getEntidad());
        dcdocp.setIDINTERNODC(Integer.parseInt(documento.getId()));
        
        trconsdocumentoevty.setDCDOCP(dcdocp);
        
        super.initialize(stdtrniparmv);
        stdtrniparmv.setCODTX(CodTxConstants.COD_TR_CONS_DOCUMENTO_TRN);
      
        stdtrniparmv.setIDINTERNOTERMTN(super.getTerminal());
        
        contexto.setSCROLLABLEOCCURS(BackEndBean.SCROLLABLE_OCCURS);
        contexto.setTRCONSDOCUMENTOEVTY(trconsdocumentoevty);
        contexto.setSTDTRNIPARMV(stdtrniparmv);
        
        
        return contexto;
    }
    
    /**
     * Función para invocar al servicio web y obtener su respuesta.
     * 
     * @param contexto
     *            Objeto de petición al servicio web
     * @return La respuesta del servicio web.
     */
    private EjecutarResult ejecutarWS(Ejecutar.ITRCONSDOCUMENTOTRNI contexto)
            throws NoControlableException {
        
        LOGGER.debug("Entra al metodo ejecutarWS");
        EjecutarResult respuesta = null;

        
        try {
            respuesta = (EjecutarResult) this.servicioWebUtils.ejecutarWS(
                    ConsultaDetalleDocumentoServicio.class, contexto);
        } catch (NoControlableException e) {
            throw new NoControlableException(
                    "Error al invocar servicio web de detalle de documento", e);
        }
        LOGGER.debug("Salida del metodo ejecutarWS");
        return respuesta;
    }
    
    /**
     * Función que valida que la respuesta del servidor no este vacía. 
     *  
     * @param respuesta Objeto respuesta del servicio web
     * @return <code>true</code> si la respuesta no esta vacía.
     */
    private Boolean verificaResponseBansefi(EjecutarResult respuesta){
        LOGGER.debug("Entra al metodo verificaResponseBansefi");
        Boolean noNulo = false;
        if(respuesta != null && respuesta.getResponseBansefi() != null){
            noNulo = true;
        }
        LOGGER.debug("Sale del metodo verificaResponseBansefi con valor -> " + noNulo);
        return noNulo;
    }
    
}
