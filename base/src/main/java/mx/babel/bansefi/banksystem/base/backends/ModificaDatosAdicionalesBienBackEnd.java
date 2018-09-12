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
import mx.babel.bansefi.banksystem.base.webservices.modificadatosadicionalesbien.Ejecutar;
import mx.babel.bansefi.banksystem.base.webservices.modificadatosadicionalesbien.Ejecutar.ITRMODIDATOSADICTRNI;
import mx.babel.bansefi.banksystem.base.webservices.modificadatosadicionalesbien.EjecutarResult;
import mx.babel.bansefi.banksystem.base.webservices.modificadatosadicionalesbien.ModificaDatosAdicionalesBienServicio;
import mx.babel.bansefi.banksystem.base.webservices.modificadatosadicionalesbien.ResponseBansefi;

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
public class ModificaDatosAdicionalesBienBackEnd extends BackEndBean {

    private static final long serialVersionUID = 624024841763128127L;

    
    private static final Logger LOGGER = LogManager.getLogger(ModificaDatosAdicionalesBienBackEnd.class
            .getName());

    @Autowired
    ServicioWebUtils servicioWebUtils;
    
    public int ejectuarTRN(final BienBean bien) {

        LOGGER.debug("Entra al metrodo ejectuarTRN");

        ResponseBansefi response = this.getModificaDatosAdcionalesBienResponse(bien);

        LOGGER.debug("Sale del metrodo ejectuarTRN");

        return response.getOTRMODIDATOSADICTRNO().getRTRNCD();
    }
    
    /**
     * Función para invocar al servicio web y obtener su respuesta.
     * 
     * @param contexto
     *            Objeto de petición al servicio web
     * @return La respuesta del servicio web.
     */
    private EjecutarResult ejecutarWS(Ejecutar.ITRMODIDATOSADICTRNI contexto)
            throws NoControlableException {
        EjecutarResult respuesta = null;

        try {
            respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(ModificaDatosAdicionalesBienServicio.class, contexto);
        } catch (NoControlableException e) {
            throw new NoControlableException(
                    "Error al invocar servicio web de modificacion de datos adicionales de bien ", e);
        }
        return respuesta;
    }
    
    /**
     * Función para modificar los datos materiales de un bien invocando un servicio web.
     * 
     * @param bien
     *            bean con los datos de entrada
     * @return Objeto indicando se ha realizado bien la modificacion
     */
    public ResponseBansefi getModificaDatosAdcionalesBienResponse(final BienBean bien)
            throws NoControlableException, ControlableException {
        LOGGER.debug("Entra al metrodo getModificaDatosAdcionalesBienResponse");

        ResponseBansefi response = null;

        Ejecutar.ITRMODIDATOSADICTRNI contexto = this.initPeticion(bien);

        EjecutarResult respuesta = ejecutarWS(contexto);

        super.verificaRespuesta(respuesta);
        
        
        response = respuesta.getResponseBansefi();
        
        LOGGER.debug("Sale del metrodo getModificaDatosAdcionalesBienResponse");
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
    private ITRMODIDATOSADICTRNI initPeticion(final BienBean bien) {
        
        DateFormat df = new SimpleDateFormat(ConstantesFuncionales.GENERAL_DATE_FORMATTER_INT);
        
        Ejecutar.ITRMODIDATOSADICTRNI contexto = new Ejecutar.ITRMODIDATOSADICTRNI();
        Ejecutar.ITRMODIDATOSADICTRNI.STDTRNIPARMV stdtrniparmv = new Ejecutar.ITRMODIDATOSADICTRNI.STDTRNIPARMV();
        Ejecutar.ITRMODIDATOSADICTRNI.TRMODIDATOSADICEVTY traltabienmaterialese  = new Ejecutar.ITRMODIDATOSADICTRNI.TRMODIDATOSADICEVTY();
        Ejecutar.ITRMODIDATOSADICTRNI.TRMODIDATOSADICEVTY.BIDATOSADICDSV bidatosadicdsv = new Ejecutar.ITRMODIDATOSADICTRNI.TRMODIDATOSADICEVTY.BIDATOSADICDSV();
        Ejecutar.ITRMODIDATOSADICTRNI.PEPERSRLBIENP pepersrlbienp = new Ejecutar.ITRMODIDATOSADICTRNI.PEPERSRLBIENP();
        Ejecutar.ITRMODIDATOSADICTRNI.TRMODIDATOSADICEVTY.DATOSCARACTV datoscaractv = new Ejecutar.ITRMODIDATOSADICTRNI.TRMODIDATOSADICEVTY.DATOSCARACTV();
        
        super.initialize(pepersrlbienp);
        super.initialize(datoscaractv);
        datoscaractv.setVALORBI(new BigDecimal(0));
        
        
        stdtrniparmv.setIDINTERNOTERMTN(super.getTerminal());
        stdtrniparmv.setCODTX(CodTxConstants.COD_TX_TR_MODI_BIEN_ING_GAS_TRN);
        
        super.initialize(bidatosadicdsv);
        
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
        
//        //regimen
        bidatosadicdsv.setRGMNBIRLPE("");
        bidatosadicdsv.setPCTBIRLPE(new BigDecimal(0));
        bidatosadicdsv.setPCTREVALUACION(new BigDecimal(0));
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
        for(int i=0; i<50;i++){
            traltabienmaterialese.getDATOSCARACTV().add(datoscaractv);
        }
        
        
        super.initialize(stdtrniparmv);
        contexto.setSTDTRNIPARMV(stdtrniparmv);
        contexto.setTRMODIDATOSADICEVTY(traltabienmaterialese);
        contexto.setPEPERSRLBIENP(pepersrlbienp);
        
        
        return contexto;
    }
    
}
