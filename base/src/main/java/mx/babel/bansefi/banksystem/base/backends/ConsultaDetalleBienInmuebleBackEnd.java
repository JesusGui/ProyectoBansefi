package mx.babel.bansefi.banksystem.base.backends;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.consultalocalidad.ConsultaLocalidadBackend;
import mx.babel.bansefi.banksystem.base.beans.domain.BienBean;
import mx.babel.bansefi.banksystem.base.beans.domain.DomicilioTipoBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.constants.ConstantesFuncionales;
import mx.babel.bansefi.banksystem.base.webservices.consultadetallebien.ConsultaDetalleBienServicio;
import mx.babel.bansefi.banksystem.base.webservices.consultadetallebien.Ejecutar;
import mx.babel.bansefi.banksystem.base.webservices.consultadetallebien.Ejecutar.ITRBIENCNSTRNI;
import mx.babel.bansefi.banksystem.base.webservices.consultadetallebien.EjecutarResult;
import mx.babel.bansefi.banksystem.base.webservices.consultadetallebien.ResponseBansefi;
import mx.babel.bansefi.banksystem.base.webservices.consultadetallebien.ResponseBansefi.OTRBIENCNSTRNO.TRBIENCNSEVTZ;
import mx.babel.bansefi.banksystem.base.webservices.consultadetallebien.ResponseBansefi.OTRBIENCNSTRNO.TRBIENCNSEVTZ.DRCOMPRGSTROV;
import mx.babel.bansefi.banksystem.base.wrappers.BienWrapper;

import org.apache.commons.lang3.StringUtils;
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
public class ConsultaDetalleBienInmuebleBackEnd extends BackEndBean implements Serializable {

	private static final long serialVersionUID = 2483621147632048268L;

	private static final Logger LOGGER = LogManager.getLogger(ConsultaDetalleBienInmuebleBackEnd.class
            .getName());
    
    @Autowired
    BienWrapper bienWrapper;
    
	@Autowired
	ServicioWebUtils servicioWebUtils;

    @Autowired
    ConsultaLocalidadBackend consultaLocalidad;
    
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
        
        Ejecutar.ITRBIENCNSTRNI contexto = this.initPeticion(bien);
        
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
        
        if(verificaRespuestaEntidad(response)){
            TRBIENCNSEVTZ bienDetalleWS = response.getOTRBIENCNSTRNO().getTRBIENCNSEVTZ();
            this.bienWrapper.wrappBienDetalleBean(bienDetalleWS, bien);
           
            
            DomicilioTipoBean domicilio = new DomicilioTipoBean();
            domicilio.setCodArGeo(ConstantesFuncionales.COD_AR_GEO);
            domicilio.setNumArGeo(bienDetalleWS.getDATOSBIENV().getNUMARGEO());
            bien.getDireccionRegistral().setMunicipioCatGeo(consultaLocalidad.ejecutarTRN(domicilio).getDatosFinalesCatGeo());
            
            List<DRCOMPRGSTROV> direccionRegistral= bienDetalleWS.getDRCOMPRGSTROV();
            for(DRCOMPRGSTROV drcomprgstrov:direccionRegistral ){
                if(ConstantesFuncionales.TP_COD_COMP_RGSTRO_FECHA_EXP.equals(drcomprgstrov.getCODCOMPRGSTRO())){
                    try{
                        if(StringUtils.isNotBlank(drcomprgstrov.getVALCOMPRGSTRODR())){
                            DateFormat df = new SimpleDateFormat(ConstantesFuncionales.GENERAL_DATE_FORMATTER);
                            Date fechaExpedicion = df.parse(drcomprgstrov.getVALCOMPRGSTRODR());
                            bien.getDireccionRegistral().setFechaExpedicion(fechaExpedicion);
                        }
                        //TODO Que hacer en caso de error?????
                    }catch(ParseException pe){
                        
                    }
                }
            }
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
    private Boolean verificaRespuestaEntidad(ResponseBansefi response){
        LOGGER.debug("Entra al metodo verificaRespuestaEntidad");
        Boolean noNulo = false;
       
        if(response != null && response.getOTRBIENCNSTRNO() != null && 
                response.getOTRBIENCNSTRNO().getTRBIENCNSEVTZ() != null){
            noNulo = true;
        }
        LOGGER.debug("Sale del metodo verificaRespuestaEntidad con valor -> " +noNulo);
        return noNulo;
    }
        
    /**
     * Función para invocar al servicio web y obtener su respuesta.
     * 
     * @param contexto
     *            Objeto de petición al servicio web
     * @return La respuesta del servicio web.
     */
    private EjecutarResult ejecutarWS(Ejecutar.ITRBIENCNSTRNI contexto)
            throws NoControlableException {
        
        LOGGER.debug("Entra al metodo ejecutarWS");
        EjecutarResult respuesta = null;

        
        try {
            respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(ConsultaDetalleBienServicio.class, contexto);
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
    public ITRBIENCNSTRNI initPeticion(final BienBean bien){
        
        LOGGER.debug("Entra al metodo initPeticion");
        Ejecutar.ITRBIENCNSTRNI contexto = new Ejecutar.ITRBIENCNSTRNI();
        Ejecutar.ITRBIENCNSTRNI.STDTRNIPARMV stdtrniparmv = new Ejecutar.ITRBIENCNSTRNI.STDTRNIPARMV();
        Ejecutar.ITRBIENCNSTRNI.TRBIENCNSEVTY trbiencnsevty = new Ejecutar.ITRBIENCNSTRNI.TRBIENCNSEVTY();
        Ejecutar.ITRBIENCNSTRNI.TRBIENCNSEVTY.BIOTROSBIENESP biotrosbienesp = new  Ejecutar.ITRBIENCNSTRNI.TRBIENCNSEVTY.BIOTROSBIENESP();
        Ejecutar.ITRBIENCNSTRNI.TRBIENCNSEVTY.PEPERSRLBIENP pepersrlbienp = new Ejecutar.ITRBIENCNSTRNI.TRBIENCNSEVTY.PEPERSRLBIENP();
        Ejecutar.ITRBIENCNSTRNI.TRBIENCNSEVTY.BIINGRGTOSP biingrgtosp = new Ejecutar.ITRBIENCNSTRNI.TRBIENCNSEVTY.BIINGRGTOSP();
        Ejecutar.ITRBIENCNSTRNI.TRBIENCNSEVTY.DRRGSTROP drrgstrop = new Ejecutar.ITRBIENCNSTRNI.TRBIENCNSEVTY.DRRGSTROP();
        Ejecutar.ITRBIENCNSTRNI.TRBIENCNSEVTY.BICRCTBIENP bicrctbienp = new Ejecutar.ITRBIENCNSTRNI.TRBIENCNSEVTY.BICRCTBIENP();
        Ejecutar.ITRBIENCNSTRNI.TRBIENCNSEVTY.BIVALORBIENP bivalorbienp = new Ejecutar.ITRBIENCNSTRNI.TRBIENCNSEVTY.BIVALORBIENP();
        

        stdtrniparmv.setIDINTERNOTERMTN(super.getTerminal());
        stdtrniparmv.setCODTX(CodTxConstants.COD_TX_TR_BIEN_CNS_TRN);
        super.initialize(stdtrniparmv);
        
        biotrosbienesp.setCODNRBEEN(super.getEntidad());
        biotrosbienesp.setIDINTERNOBI(Integer.parseInt(bien.getIdInterno()));
        
        pepersrlbienp.setCODNRBEEN(super.getEntidad());
        pepersrlbienp.setIDINTERNOPE(bien.getIdInternoPersona());
        pepersrlbienp.setCODBIEN(bien.getTipoCodigo());
        pepersrlbienp.setIDINTERNOBI(Integer.parseInt(bien.getIdInterno()));
        super.initialize(pepersrlbienp);
        
        super.initialize(biingrgtosp);
        
        super.initialize(drrgstrop);
        
        super.initialize(bicrctbienp);
        
        //Se debe introducir el objeto 50 veces
        for(int i=0; i<50; i++){
            trbiencnsevty.getBICRCTBIENP().add(bicrctbienp);
        }
        
        super.initialize(bivalorbienp);
        
        trbiencnsevty.setBIINGRGTOSP(biingrgtosp);
        trbiencnsevty.setBIOTROSBIENESP(biotrosbienesp);
        trbiencnsevty.setBIVALORBIENP(bivalorbienp);
        trbiencnsevty.setDRRGSTROP(drrgstrop);
        trbiencnsevty.setPEPERSRLBIENP(pepersrlbienp);
        
        contexto.setSTDTRNIPARMV(stdtrniparmv);
        contexto.setTRBIENCNSEVTY(trbiencnsevty);
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
