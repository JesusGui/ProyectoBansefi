package mx.babel.bansefi.banksystem.base.backends;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.beans.domain.BienBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.constants.ConstantesFuncionales;
import mx.babel.bansefi.banksystem.base.webservices.consultadatosadicionalesbien.ConsultaDatosAdicionalesBienServicio;
import mx.babel.bansefi.banksystem.base.webservices.consultadatosadicionalesbien.Ejecutar;
import mx.babel.bansefi.banksystem.base.webservices.consultadatosadicionalesbien.EjecutarResult;
import mx.babel.bansefi.banksystem.base.webservices.consultadatosadicionalesbien.ResponseBansefi;
import mx.babel.bansefi.banksystem.base.webservices.consultadatosadicionalesbien.ResponseBansefi.OTRCONSDATOSADICTRNO.TRCONSDATOSADICEVTZ;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConsultaDatosAdicionalesBienBackend extends BackEndBean{

    private static final long serialVersionUID = 8067511506524675091L;

    private static final Logger LOGGER = LogManager
            .getLogger(ConsultaDatosAdicionalesBienBackend.class.getName());
    
    @Autowired
    ServicioWebUtils servicioWebUtils;

    /**
     * Se encarga de devolver los datos adicionales de un bien
     * 
     * @param bien El bien sobre el que se quiere consultar sus datos
     * @return Una lista de CrctBienBean
     */
    public BienBean ejecutarTRN(BienBean bien) 
            throws NoControlableException, ControlableException{
        
        LOGGER.debug("Entra al metodo ejectuarTRN");
        BienBean bienAmpliado = this
                .getDatosAdicionalesResponse(bien);
        LOGGER.debug("Sale del metodo ejectuarTRN");
        return bienAmpliado;
        
    }
    
    /**
     * Función para obtener una lista de Crct invocando un servicio web.
     * 
     * @param bien
     *            
     * @return Lista con atributos de los Crct.
     */
    public BienBean getDatosAdicionalesResponse(BienBean bien)
            throws NoControlableException, ControlableException {

        LOGGER.debug("Entra al metodo getDatosAdicionalesResponse");
        Ejecutar.ITRCONSDATOSADICTRNI contexto = this.initPeticion(bien);

        EjecutarResult respuesta = ejecutarWS(contexto);

        super.verificaRespuesta(respuesta);
        bien = getBienDetalle(respuesta.getResponseBansefi(), bien);
        
        LOGGER.debug("Sale del metodo getDatosAdicionalesResponse");
        return bien;
    }
    
    BienBean getBienDetalle(ResponseBansefi responseBansefi, BienBean bien){
        
        DateFormat df = new SimpleDateFormat(ConstantesFuncionales.GENERAL_DATE_FORMATTER_INT);
        
        TRCONSDATOSADICEVTZ datosAdicionalesBien = responseBansefi.getOTRCONSDATOSADICTRNO().getTRCONSDATOSADICEVTZ();
        
        try{
            bien.getDatosValuacion().setFechaExpiracion(df.parse(Integer.toString(datosAdicionalesBien.getFECHAEXPIRACION())));
        }catch (ParseException e) {
            bien.getDatosValuacion().setFechaExpiracion(null);
        }
        
        
        
        return bien;
    }
    
    /**
     * Función para invocar al servicio web y obtener su respuesta.
     * 
     * @param contexto
     *            Objeto de petición al servicio web
     * @return La respuesta del servicio web.
     */
    private EjecutarResult ejecutarWS(Ejecutar.ITRCONSDATOSADICTRNI contexto)
            throws NoControlableException {
        LOGGER.debug("Entra al metodo ejecutarWS");
        EjecutarResult respuesta = null;
        Ejecutar ejecutar = new Ejecutar();
        ejecutar.setITRCONSDATOSADICTRNI(contexto);
        try {
            respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(ConsultaDatosAdicionalesBienServicio.class, contexto);
        } catch (NoControlableException e) {
            throw new NoControlableException(
                    "Error al invocar servicio web de consulta de "
                            + "entidades.", e);
        }
        LOGGER.debug("Sale del metodo ejecutarWS");
        return respuesta;
    }
    
    /**
     * Función para inicializar los atributos del objeto de peticíon al servicio
     * web.
     * 
     * @param bien
     *           
     * @return Objeto de petición al web service
     */
    private Ejecutar.ITRCONSDATOSADICTRNI initPeticion(BienBean bien) {
        LOGGER.debug("Entra al metodo initPeticion");
        Ejecutar.ITRCONSDATOSADICTRNI contexto = new Ejecutar.ITRCONSDATOSADICTRNI();
        
        Ejecutar.ITRCONSDATOSADICTRNI.STDTRNIPARMV stdtrniparmv = new Ejecutar.ITRCONSDATOSADICTRNI.STDTRNIPARMV();
        
        Ejecutar.ITRCONSDATOSADICTRNI.TRCONSDATOSADICEVTY trconsdatosadicevty = new Ejecutar.ITRCONSDATOSADICTRNI.TRCONSDATOSADICEVTY();
        Ejecutar.ITRCONSDATOSADICTRNI.TRCONSDATOSADICEVTY.BIOTROSBIENESP biotrosbienesp = new Ejecutar.ITRCONSDATOSADICTRNI.TRCONSDATOSADICEVTY.BIOTROSBIENESP(); 
        Ejecutar.ITRCONSDATOSADICTRNI.TRCONSDATOSADICEVTY.PEPERSRLBIENP pepersrlbienp = new Ejecutar.ITRCONSDATOSADICTRNI.TRCONSDATOSADICEVTY.PEPERSRLBIENP();
        
        pepersrlbienp.setCODNRBEEN3(super.getEntidad());
        pepersrlbienp.setIDINTERNOPE(bien.getIdInternoPersona());
        pepersrlbienp.setIDINTERNOBI3(Integer.valueOf(bien.getIdInterno()));
        pepersrlbienp.setCODBIEN(bien.getTipoCodigo());
        
        biotrosbienesp.setCODNRBEEN2(super.getEntidad());
        biotrosbienesp.setIDINTERNOBI2(Integer.valueOf(bien.getIdInterno()));
        
        trconsdatosadicevty.setPEPERSRLBIENP(pepersrlbienp);
        trconsdatosadicevty.setBIOTROSBIENESP(biotrosbienesp);
        
        stdtrniparmv.setCODTX(CodTxConstants.COD_TX_TR_CONS_DATOS_ADIC_TRN);
        stdtrniparmv.setIDINTERNOTERMTN(super.getTerminal());
        
        contexto.setSTDTRNIPARMV(stdtrniparmv);
        contexto.setTRCONSDATOSADICEVTY(trconsdatosadicevty);

        return contexto;
    }
}
