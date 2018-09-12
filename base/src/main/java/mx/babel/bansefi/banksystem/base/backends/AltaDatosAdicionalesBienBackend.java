package mx.babel.bansefi.banksystem.base.backends;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.beans.domain.BienBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.constants.ConstantesFuncionales;
import mx.babel.bansefi.banksystem.base.webservices.altadatosadicionalesbien.AltaDatosAdicionalesBienServicio;
import mx.babel.bansefi.banksystem.base.webservices.altadatosadicionalesbien.Ejecutar;
import mx.babel.bansefi.banksystem.base.webservices.altadatosadicionalesbien.Ejecutar.ITRALTADATOSADICTRNI;
import mx.babel.bansefi.banksystem.base.webservices.altadatosadicionalesbien.EjecutarResult;
import mx.babel.bansefi.banksystem.base.webservices.altadatosadicionalesbien.ResponseBansefi;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Backend que se encarga de dar de alta un los datos adicionales de un bien
 * 
 * @author luis.gcastellano
 * 
 */
@Component
public class AltaDatosAdicionalesBienBackend extends BackEndBean {

    private static final long serialVersionUID = 8117118959234473088L;

    private static final Logger LOGGER = LogManager.getLogger(AltaDatosAdicionalesBienBackend.class
            .getName());

    @Autowired
    ServicioWebUtils servicioWebUtils;
    
    public int ejectuarTRN(final BienBean bien) {

        LOGGER.debug("Entra al metrodo ejectuarTRN");

        ResponseBansefi response = this.getAltaDatosAdcionalesBienResponse(bien);

        LOGGER.debug("Sale del metrodo ejectuarTRN");

        return response.getOTRALTADATOSADICTRNO().getRTRNCD();
    }
    
    /**
     * Función para invocar al servicio web y obtener su respuesta.
     * 
     * @param contexto
     *            Objeto de petición al servicio web
     * @return La respuesta del servicio web.
     */
    private EjecutarResult ejecutarWS(Ejecutar.ITRALTADATOSADICTRNI contexto)
            throws NoControlableException {
        EjecutarResult respuesta = null;

        try {
            respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(AltaDatosAdicionalesBienServicio.class, contexto);
        } catch (NoControlableException e) {
            throw new NoControlableException(
                    "Error al invocar servicio web alta de datos adicionales de bien ", e);
        }
        return respuesta;
    }
    
    /**
     * Función para dar de alta los datos materiales de un bien invocando un servicio web.
     * 
     * @param bien
     *            bean con los datos de entrada
     * @return Objeto indicando se ha realizado bien el alta
     */
    public ResponseBansefi getAltaDatosAdcionalesBienResponse(final BienBean bien)
            throws NoControlableException, ControlableException {
        LOGGER.debug("Entra al metrodo getAltaDatosAdcionalesBienResponse");

        ResponseBansefi response = null;

        Ejecutar.ITRALTADATOSADICTRNI contexto = this.initPeticion(bien);

        EjecutarResult respuesta = ejecutarWS(contexto);

        super.verificaRespuesta(respuesta);        
        
        response = respuesta.getResponseBansefi();
        
        LOGGER.debug("Sale del metrodo getAltaDatosAdcionalesBienResponse");
        return response;
    }
    
    /**
     * Función para inicializar los atributos del objeto de peticíon al servicio
     * web.
     * 
     * @param req
     *            bean con los datos de entrada
     * @return Objeto de petición al web service
     */
    private ITRALTADATOSADICTRNI initPeticion(final BienBean bien) {
        
        DateFormat df = new SimpleDateFormat(ConstantesFuncionales.GENERAL_DATE_FORMATTER_INT);
        
        Ejecutar.ITRALTADATOSADICTRNI contexto = new Ejecutar.ITRALTADATOSADICTRNI();
        Ejecutar.ITRALTADATOSADICTRNI.STDTRNIPARMV stdtrniparmv = new Ejecutar.ITRALTADATOSADICTRNI.STDTRNIPARMV();
        Ejecutar.ITRALTADATOSADICTRNI.TRALTADATOSADICEVTY traltabienmaterialese  = new Ejecutar.ITRALTADATOSADICTRNI.TRALTADATOSADICEVTY();
        Ejecutar.ITRALTADATOSADICTRNI.TRALTADATOSADICEVTY.BIDATOSADICDSV bidatosadicdsv = new Ejecutar.ITRALTADATOSADICTRNI.TRALTADATOSADICEVTY.BIDATOSADICDSV();
        
        stdtrniparmv.setIDINTERNOTERMTN(super.getTerminal());
        stdtrniparmv.setCODTX(CodTxConstants.COD_TX_TR_ALTA_BIEN_MATERIALES_TRN);
        
        bidatosadicdsv.setCODBIEN(bien.getTipoCodigo());
        bidatosadicdsv.setIDINTERNOBI(Integer.valueOf(bien.getIdInterno()));
        bidatosadicdsv.setCODNRBEEN(super.getEntidad());
        bidatosadicdsv.setIDINTERNOPE(bien.getIdInternoPersona());
        //Fecha expiracion
        if(bien.getDatosValuacion().getFechaExpiracion()!=null){
            String fechaExpiracionString = df.format(bien.getDatosValuacion().getFechaExpiracion());
            Integer fechaExpiracionInt = Integer.valueOf(fechaExpiracionString);
            bidatosadicdsv.setFECHAEXPIRACION(fechaExpiracionInt);
        }
        
        //Valor declarado
        if(bien.getDatosValuacion().getValorDeclarado()!=null){
            bidatosadicdsv.setVALORBISUBJET(BigDecimal.valueOf(bien.getDatosValuacion().getValorDeclarado()));
        }
        
        //Valor valuado
        if(bien.getDatosValuacion().getValorValuado()!=null){
            bidatosadicdsv.setVALORBITASAC(BigDecimal.valueOf(bien.getDatosValuacion().getValorValuado()));
        }
        
        //Valor adq
        if(bien.getDatosValuacion().getValor()!=null){
            bidatosadicdsv.setVALADQBIRLPE(BigDecimal.valueOf(bien.getDatosValuacion().getValor()));
            
        }
        
        //fecha declarado
        if(bien.getDatosValuacion().getFechaDeclaracion()!=null){
            String fechaDeclaracionString = df.format(bien.getDatosValuacion().getFechaDeclaracion());
            Integer fechaDeclaracionInt = Integer.valueOf(fechaDeclaracionString);
            bidatosadicdsv.setFECHAVALSUBJET(fechaDeclaracionInt);
        }
        
        //fecha valuacion
        if(bien.getDatosValuacion().getFechaValuacion()!=null){
            String fechaValuacionString = df.format(bien.getDatosValuacion().getFechaValuacion());
            Integer fechaValuacionInt = Integer.valueOf(fechaValuacionString);
            bidatosadicdsv.setFECHAVALTASAC(fechaValuacionInt);
        }
        
        //fecha adquisicion
        if(bien.getDatosValuacion().getFechaAdquisicion()!=null){
            String fechaAdquisionString = df.format(bien.getDatosValuacion().getFechaAdquisicion());
            Integer fechaAdquisionInt = Integer.valueOf(fechaAdquisionString);
            bidatosadicdsv.setFECADQUIBIRLPE(fechaAdquisionInt);
        }
        
        //Uso bien
        if(StringUtils.isNotBlank(bien.getDatosValuacion().getUsoBien())){
            bidatosadicdsv.setUSOBIRLPE(bien.getDatosValuacion().getUsoBien());
        }
        
        //Origen bien
        if(StringUtils.isNotBlank(bien.getDatosValuacion().getOrigenAdquisicion())){
            bidatosadicdsv.setORGNBIRLPE(bien.getDatosValuacion().getOrigenAdquisicion());
        }
        
        //Domiciliacion
        if(bien.getDatosValuacion().getDomiciliacion()!=null){
            bidatosadicdsv.setPCTBIRLPE(BigDecimal.valueOf(bien.getDatosValuacion().getDomiciliacion()));
        }
        
        //Razon alta
        if(StringUtils.isNotBlank(bien.getDatosValuacion().getRazonAlta())){
            bidatosadicdsv.setCODRZNALTABIEN(bien.getDatosValuacion().getRazonAlta());
        }
     
        //Descripcion
        bidatosadicdsv.setTXTOTROSBI(bien.getDatosGenerales().getDescripcion());
        
        traltabienmaterialese.setBIDATOSADICDSV(bidatosadicdsv);
        contexto.setSTDTRNIPARMV(stdtrniparmv);
        contexto.setTRALTADATOSADICEVTY(traltabienmaterialese);
        
        return contexto;
    }
}
