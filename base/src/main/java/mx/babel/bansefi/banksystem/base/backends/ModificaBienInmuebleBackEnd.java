package mx.babel.bansefi.banksystem.base.backends;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.beans.domain.BienBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.constants.ConstantesFuncionales;
import mx.babel.bansefi.banksystem.base.webservices.modificabieninmueble.Ejecutar;
import mx.babel.bansefi.banksystem.base.webservices.modificabieninmueble.Ejecutar.ITRBIENMODITRNI;
import mx.babel.bansefi.banksystem.base.webservices.modificabieninmueble.Ejecutar.ITRBIENMODITRNI.TRBIENMODIEVTY.DATOSRGSTROV;
import mx.babel.bansefi.banksystem.base.webservices.modificabieninmueble.Ejecutar.ITRBIENMODITRNI.TRBIENMODIEVTY.DATOSRGSTROV.DRCOMPRGSTROV;
import mx.babel.bansefi.banksystem.base.webservices.modificabieninmueble.EjecutarResult;
import mx.babel.bansefi.banksystem.base.webservices.modificabieninmueble.ModificaBienInmuebleServicio;
import mx.babel.bansefi.banksystem.base.wrappers.BienWrapper;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * Backend que se encarga de modificar un bien inmueble  llamando a la TRN
 * TR_BIEN_MODI_TRN
 * 
 * @author luis.gcastellano
 * 
 */
@Component
public class ModificaBienInmuebleBackEnd extends BackEndBean implements Serializable {

	private static final long serialVersionUID = 8246270809326117437L;

	private static final Logger LOGGER = LogManager.getLogger(ModificaBienInmuebleBackEnd.class
            .getName());

    @Autowired
    BienWrapper bienWrapper;
    
	@Autowired
	ServicioWebUtils servicioWebUtils;

    
    public int ejectuarTRN(final BienBean bien) {

        LOGGER.debug("Entra al metrodo ejectuarTRN");

        Ejecutar.ITRBIENMODITRNI contexto = this.initPeticion(bien);

        EjecutarResult respuesta = ejecutarWS(contexto);
        
        super.verificaRespuesta(respuesta);              
        
        LOGGER.debug("Sale del metrodo ejectuarTRN");

        return respuesta.getResponseBansefi().getOTRBIENMODITRNO().getRTRNCD();
    }

    /**
     * Función para invocar al servicio web y obtener su respuesta.
     * 
     * @param contexto
     *            Objeto de petición al servicio web
     * @return La respuesta del servicio web.
     */
    private EjecutarResult ejecutarWS(Ejecutar.ITRBIENMODITRNI contexto)
            throws NoControlableException {
        EjecutarResult respuesta = null;

        try {
            respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(ModificaBienInmuebleServicio.class, contexto);
        } catch (NoControlableException e) {
            throw new NoControlableException(
                    "Error al invocar servicio web alta de bien ", e);
        }
        return respuesta;
    }
    
public DATOSRGSTROV creaDireccionRegistral(final BienBean bien){
        
        Ejecutar.ITRBIENMODITRNI.TRBIENMODIEVTY.DATOSRGSTROV datosrgstrov = new Ejecutar.ITRBIENMODITRNI.TRBIENMODIEVTY.DATOSRGSTROV();
        
        DRCOMPRGSTROV drcomprgstrov1 = new DRCOMPRGSTROV();
        DRCOMPRGSTROV drcomprgstrov2 = new DRCOMPRGSTROV();
        DRCOMPRGSTROV drcomprgstrov3 = new DRCOMPRGSTROV();
        DRCOMPRGSTROV drcomprgstrov4 = new DRCOMPRGSTROV();
        DRCOMPRGSTROV drcomprgstrov5 = new DRCOMPRGSTROV();
        DRCOMPRGSTROV drcomprgstrov6 = new DRCOMPRGSTROV();
        DRCOMPRGSTROV drcomprgstrovRelleno = new DRCOMPRGSTROV();
        super.initialize(drcomprgstrovRelleno);
        
        drcomprgstrov1.setCODCOMPRGSTRO(ConstantesFuncionales.TP_COD_COMP_RGSTRO_NUM_REGISTRAL);
        drcomprgstrov1.setVALCOMPRGSTRODRLEN(ConstantesFuncionales.TP_VAL_COMP_RGSTRO_NUM_REGISTRAL_LEN);
        drcomprgstrov1.setVALCOMPRGSTRODR(bien.getDireccionRegistral().getNumeroRegistral());
        
        drcomprgstrov2.setCODCOMPRGSTRO(ConstantesFuncionales.TP_COD_COMP_RGSTRO_TOMO);
        drcomprgstrov2.setVALCOMPRGSTRODRLEN(ConstantesFuncionales.TP_VAL_COMP_RGSTRO_TOMO_LEN);
        drcomprgstrov2.setVALCOMPRGSTRODR(bien.getDireccionRegistral().getTomo());
        
        drcomprgstrov3.setCODCOMPRGSTRO(ConstantesFuncionales.TP_COD_COMP_RGSTRO_LIBRO);
        drcomprgstrov3.setVALCOMPRGSTRODRLEN(ConstantesFuncionales.TP_VAL_COMP_RGSTRO_LIBRO_LEN);
        drcomprgstrov3.setVALCOMPRGSTRODR(bien.getDireccionRegistral().getLibro());
        
        drcomprgstrov4.setCODCOMPRGSTRO(ConstantesFuncionales.TP_COD_COMP_RGSTRO_FOLIO);
        drcomprgstrov4.setVALCOMPRGSTRODRLEN(ConstantesFuncionales.TP_VAL_COMP_RGSTRO_FOLIO_LEN);
        drcomprgstrov4.setVALCOMPRGSTRODR(bien.getDireccionRegistral().getFolio());
        
        drcomprgstrov5.setCODCOMPRGSTRO(ConstantesFuncionales.TP_COD_COMP_RGSTRO_NUM_INSC);
        drcomprgstrov5.setVALCOMPRGSTRODRLEN(ConstantesFuncionales.TP_VAL_COMP_RGSTRO_NUM_INSC_LEN);
        drcomprgstrov5.setVALCOMPRGSTRODR(bien.getDireccionRegistral().getNumeroInscripcion());
        
        drcomprgstrov6.setCODCOMPRGSTRO(ConstantesFuncionales.TP_COD_COMP_RGSTRO_FECHA_EXP);
        drcomprgstrov6.setVALCOMPRGSTRODRLEN(ConstantesFuncionales.TP_VAL_COMP_RGSTRO_FECHA_EXP_LEN);
        
        DateFormat df = new SimpleDateFormat(ConstantesFuncionales.GENERAL_DATE_FORMATTER);
        if(bien.getDireccionRegistral().getFechaExpedicion()!=null){
            drcomprgstrov6.setVALCOMPRGSTRODR(df.format(bien.getDireccionRegistral().getFechaExpedicion()));
        }else{
            drcomprgstrov6.setVALCOMPRGSTRODR("");
        }
        
        
        
        datosrgstrov.getDRCOMPRGSTROV().add(drcomprgstrov1);
        datosrgstrov.getDRCOMPRGSTROV().add(drcomprgstrov2);
        datosrgstrov.getDRCOMPRGSTROV().add(drcomprgstrov3);
        datosrgstrov.getDRCOMPRGSTROV().add(drcomprgstrov4);
        datosrgstrov.getDRCOMPRGSTROV().add(drcomprgstrov5);
        datosrgstrov.getDRCOMPRGSTROV().add(drcomprgstrov6);
        
        for(int i=datosrgstrov.getDRCOMPRGSTROV().size(); i<ConstantesFuncionales.TP_DIRECCION_REGISTRAL_LEN ;i++){
            datosrgstrov.getDRCOMPRGSTROV().add(drcomprgstrovRelleno);
        }
        if(bien.getDireccionRegistral().getMunicipioCatGeo()!=null){
            datosrgstrov.setCODARGEO(bien.getDireccionRegistral().getMunicipioCatGeo().getCodArGeo());
            if(StringUtils.isNotBlank(bien.getDireccionRegistral().getMunicipioCatGeo().getNumArGeo())){
                datosrgstrov.setNUMARGEO(Integer.valueOf(bien.getDireccionRegistral().getMunicipioCatGeo().getNumArGeo()));
            }
        }
        datosrgstrov.setCODRGSTRO(bien.getDireccionRegistral().getTipoRegistro());
        if(StringUtils.isNotBlank(bien.getDireccionRegistral().getNumeroRegistro())){
            datosrgstrov.setNUMRGSTRO(Integer.valueOf(bien.getDireccionRegistral().getNumeroRegistro()));
        }
        return datosrgstrov;
    }
    
   
    
    /**
     * Función para inicializar los atributos del objeto de peticíon al servicio
     * web.
     * 
     * @param req
     *            bean con los datos de entrada
     * @return Objeto de petición al web service
     */
    private ITRBIENMODITRNI initPeticion(final BienBean bien) {
        Ejecutar.ITRBIENMODITRNI contexto = new Ejecutar.ITRBIENMODITRNI();
        Ejecutar.ITRBIENMODITRNI.STDTRNIPARMV stdtrniparmv = new Ejecutar.ITRBIENMODITRNI.STDTRNIPARMV();
        Ejecutar.ITRBIENMODITRNI.TRBIENMODIEVTY trbienbajaevty = new Ejecutar.ITRBIENMODITRNI.TRBIENMODIEVTY();
        Ejecutar.ITRBIENMODITRNI.PEPERSRLBIENP pepersrlbienp = new  Ejecutar.ITRBIENMODITRNI.PEPERSRLBIENP();
        
        Ejecutar.ITRBIENMODITRNI.TRBIENMODIEVTY.DATOSCARACTV datoscaractv = new Ejecutar.ITRBIENMODITRNI.TRBIENMODIEVTY.DATOSCARACTV();

        stdtrniparmv.setIDINTERNOTERMTN(super.getTerminal());
        stdtrniparmv.setCODTX(CodTxConstants.COD_TX_TR_BIEN_MODI_TRN);
        super.initialize(stdtrniparmv);
        
        trbienbajaevty = bienWrapper.wrappModificaBienInmuebleBean(bien);
        
        pepersrlbienp.setCODBIEN(bien.getTipoCodigo());
        pepersrlbienp.setCODNRBEEN(super.getEntidad());
        pepersrlbienp.setIDINTERNOBI(Integer.parseInt(bien.getIdInterno()));
        pepersrlbienp.setIDINTERNOPE(bien.getIdInternoPersona());
        
        datoscaractv.setVALORBI(new BigDecimal(0));
        
        DATOSRGSTROV datosrgstrov = this.creaDireccionRegistral(bien);
        
        for(int i=0; i<50; i++){
            trbienbajaevty.getDATOSCARACTV().add(datoscaractv);
            
        }        
        
        trbienbajaevty.setDATOSRGSTROV(datosrgstrov);        
        
        trbienbajaevty.getDATOSBIENV().setCODNUMRCOMONEDA("MXN");
        trbienbajaevty.getDATOSBIENV().setCODNRBEEN(super.getEntidad());
        trbienbajaevty.getDATOSBIENV().setINDELEM("N");
        trbienbajaevty.getDATOSBIENV().setIMPMENSUALIG(new BigDecimal(0));
        trbienbajaevty.getDATOSBIENV().setIMPANUALIG(new BigDecimal(0));
        trbienbajaevty.getDATOSBIENV().setPCTTOTBIRLBI(new BigDecimal(0));
        trbienbajaevty.getDATOSBIENV().setIMPTETOTBIRLBI(new BigDecimal(0));

        trbienbajaevty.setDATOSRGSTROV(datosrgstrov);
        contexto.setSTDTRNIPARMV(stdtrniparmv);
        contexto.setTRBIENMODIEVTY(trbienbajaevty);
        contexto.setPEPERSRLBIENP(pepersrlbienp);
//        super.initialize(contexto);

        return contexto;

    }
   
}
