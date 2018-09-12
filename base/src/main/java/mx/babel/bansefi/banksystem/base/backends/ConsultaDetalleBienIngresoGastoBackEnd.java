package mx.babel.bansefi.banksystem.base.backends;

import java.io.Serializable;

import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.beans.domain.BienBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.webservices.consultadetallebieningresogasto.ConsultaDetalleBienIngresoGastoServicio;
import mx.babel.bansefi.banksystem.base.webservices.consultadetallebieningresogasto.Ejecutar;
import mx.babel.bansefi.banksystem.base.webservices.consultadetallebieningresogasto.Ejecutar.ITRCONSBIENTRNI;
import mx.babel.bansefi.banksystem.base.webservices.consultadetallebieningresogasto.EjecutarResult;
import mx.babel.bansefi.banksystem.base.webservices.consultadetallebieningresogasto.ResponseBansefi;
import mx.babel.bansefi.banksystem.base.webservices.consultadetallebieningresogasto.ResponseBansefi.OTRCONSBIENTRNO.TRCONSBIENEVTZ;
import mx.babel.bansefi.banksystem.base.wrappers.BienWrapper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Backend que se encarga de consultar los detalles de un bien
 * TR_BIEN_CNS_TRN
 * 
 * @author luis.gcastellano
 * 
 */
@Component
public class ConsultaDetalleBienIngresoGastoBackEnd extends BackEndBean implements Serializable {

	private static final long serialVersionUID = 2925370047440375968L;

	private static final Logger LOGGER = LogManager.getLogger(ConsultaDetalleBienIngresoGastoBackEnd.class
            .getName());
    
    @Autowired
    BienWrapper bienWrapper;
    
	@Autowired
	ServicioWebUtils servicioWebUtils;

    
    public void ejectuarTRN(final BienBean bien) {
        
        LOGGER.debug("Entra al metodo ejectuarTRN");
        this.getBienDetalleResponse(bien);
        LOGGER.debug("Sale del metodo ejectuarTRN");
       
        
    }
    
    /**
     * Se encarga de recuperar los detalles de un bien
     * @param bien Bean Bien con los datos para la consulta del bien
     * @param codigoInternoPersona Codigo de la persona a la que está relacionada el bien
     */
    public void getBienDetalleResponse(final BienBean bien){
        LOGGER.debug("Entra al metodo getBienDetalleResponse");
        
        Ejecutar.ITRCONSBIENTRNI contexto = this.initPeticion(bien);
        
        EjecutarResult respuesta = ejecutarWS(contexto);
        
        super.verificaRespuesta(respuesta);
        
        if(verificaResponseBansefi(respuesta)){
            this.getBienDetalle(respuesta.getResponseBansefi(), bien);
            
        }
        
        LOGGER.debug("Sale del metodo getBienDetalleResponse");
       
    }
    
    /**
     * Se encarga de extraer un bienBean a partir de la respuesta del WS
     * @param respuesta Respuesta del WS de detalle de Bien
     * @return Un bienBean con los datos rellenos
     */
    public void getBienDetalle(ResponseBansefi response, BienBean bien){
        LOGGER.debug("Entra al metodo getBienDetalle");       
        
        if(verificaRespuestaBien(response)){
            TRCONSBIENEVTZ bienDetalleWS = response.getOTRCONSBIENTRNO().getTRCONSBIENEVTZ();
            this.bienWrapper.wrappBienDetalleIngresoGastoBean(bienDetalleWS, bien);
        }
        
        LOGGER.debug("Sale del metodo getBienDetalle");
        
    }
    
    /**
     * Función que valida que la respuesta Bansefi contenga un objeto con los datos
     * de detale de bien
     * 
     * @param response Respuesta Bansefi con detalle de bien
     * @return <code>true</code> si la respuesta Bansefi contiene datos validos
     */
    private Boolean verificaRespuestaBien(ResponseBansefi response){
        LOGGER.debug("Entra al metodo verificaRespuestaBien");
        Boolean noNulo = false;
       
        if(response != null && response.getOTRCONSBIENTRNO() != null && 
                response.getOTRCONSBIENTRNO().getTRCONSBIENEVTZ() != null){
            noNulo = true;
        }
        LOGGER.debug("Sale del metodo verificaRespuestaBien con valor -> " +noNulo);
        return noNulo;
    }
    
    /**
     * Función para invocar al servicio web y obtener su respuesta.
     * 
     * @param contexto
     *            Objeto de petición al servicio web
     * @return La respuesta del servicio web.
     */
    private EjecutarResult ejecutarWS(Ejecutar.ITRCONSBIENTRNI contexto)
            throws NoControlableException {
        
        LOGGER.debug("Entra al metodo ejecutarWS");
        EjecutarResult respuesta = null;

        
        try {
            respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(ConsultaDetalleBienIngresoGastoServicio.class, contexto);
        } catch (NoControlableException e) {
            throw new NoControlableException(
                    "Error al invocar servicio web de detalle de bien", e);
        }
        LOGGER.debug("Salida del metodo ejecutarWS");
        return respuesta;
    }
    
    /**
     * Función para inicializar los atributos del objeto de peticíon al servicio web.
     * 
     * @param object bean con los datos de entrada
     * @return Objeto de petición al web service
     */
    public ITRCONSBIENTRNI initPeticion(final BienBean bien){
        
        LOGGER.debug("Entra al metodo initPeticion");
        Ejecutar.ITRCONSBIENTRNI contexto = new Ejecutar.ITRCONSBIENTRNI();
        Ejecutar.ITRCONSBIENTRNI.STDTRNIPARMV stdtrniparmv = new Ejecutar.ITRCONSBIENTRNI.STDTRNIPARMV();
        Ejecutar.ITRCONSBIENTRNI.TRCONSBIENEVTY trconsbienevty = new Ejecutar.ITRCONSBIENTRNI.TRCONSBIENEVTY();
        Ejecutar.ITRCONSBIENTRNI.TRCONSBIENEVTY.BIOTROSBIENESP biotrosbienesp = new  Ejecutar.ITRCONSBIENTRNI.TRCONSBIENEVTY.BIOTROSBIENESP();
        Ejecutar.ITRCONSBIENTRNI.TRCONSBIENEVTY.PEPERSRLBIENP pepersrlbienp = new Ejecutar.ITRCONSBIENTRNI.TRCONSBIENEVTY.PEPERSRLBIENP();
        Ejecutar.ITRCONSBIENTRNI.TRCONSBIENEVTY.BIINGRGTOSP biingrgtosp = new Ejecutar.ITRCONSBIENTRNI.TRCONSBIENEVTY.BIINGRGTOSP();
        Ejecutar.ITRCONSBIENTRNI.TRCONSBIENEVTY.BICRCTBIENP bicrctbienp = new Ejecutar.ITRCONSBIENTRNI.TRCONSBIENEVTY.BICRCTBIENP();
        
        
     // TODO poner la terminal real
        stdtrniparmv.setIDINTERNOTERMTN(super.getTerminal());
        stdtrniparmv.setCODTX(CodTxConstants.COD_TX_TR_BIEN_CNS_TRN);
        super.initialize(stdtrniparmv);
        
        
        super.initialize(biotrosbienesp);
        
        pepersrlbienp.setCODNRBEEN(super.getEntidad());
        pepersrlbienp.setIDINTERNOPE(bien.getIdInternoPersona());
        pepersrlbienp.setCODBIEN(bien.getTipoCodigo());
        pepersrlbienp.setIDINTERNOBI(Integer.parseInt(bien.getIdInterno()));
        super.initialize(pepersrlbienp);
        
        biingrgtosp.setCODNRBEEN(super.getEntidad());
        biingrgtosp.setIDINTERNOBI(Integer.parseInt(bien.getIdInterno()));
        super.initialize(biingrgtosp);
        
        bicrctbienp.setCODNRBEEN(super.getEntidad());
        bicrctbienp.setIDINTERNOBI(Integer.parseInt(bien.getIdInterno()));  
        //recuperar flag justificado
        bicrctbienp.setCODCRCTBIEN("998");
        super.initialize(bicrctbienp);
        
        trconsbienevty.setBIINGRGTOSP(biingrgtosp);
        trconsbienevty.setBIOTROSBIENESP(biotrosbienesp);
        trconsbienevty.setBICRCTBIENP(bicrctbienp);
        trconsbienevty.setPEPERSRLBIENP(pepersrlbienp);
        
        contexto.setSTDTRNIPARMV(stdtrniparmv);
        contexto.setTRCONSBIENEVTY(trconsbienevty);
        contexto.setACTNCD("");
        LOGGER.debug("Sale del metodo initPeticion");
        
        return contexto;
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
