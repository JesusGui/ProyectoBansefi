package mx.babel.bansefi.banksystem.base.backends;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.beans.domain.BienBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.constants.ConstantesFuncionales;
import mx.babel.bansefi.banksystem.base.webservices.altabieninmueble.AltaBienInmuebleServicio;
import mx.babel.bansefi.banksystem.base.webservices.altabieninmueble.Ejecutar;
import mx.babel.bansefi.banksystem.base.webservices.altabieninmueble.Ejecutar.ITRBIENALTATRNI;
import mx.babel.bansefi.banksystem.base.webservices.altabieninmueble.Ejecutar.ITRBIENALTATRNI.TRBIENALTAEVTY.DATOSRGSTROV;
import mx.babel.bansefi.banksystem.base.webservices.altabieninmueble.Ejecutar.ITRBIENALTATRNI.TRBIENALTAEVTY.DATOSRGSTROV.DRCOMPRGSTROV;
import mx.babel.bansefi.banksystem.base.webservices.altabieninmueble.EjecutarResult;
import mx.babel.bansefi.banksystem.base.webservices.altabieninmueble.ResponseBansefi;
import mx.babel.bansefi.banksystem.base.wrappers.BienWrapper;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Backend que se encarga de dar de alta un bien llamando a la TRN
 * TR_BIEN_ALTA_TRN
 * 
 * @author luis.gcastellano
 * 
 */
@Component
public class AltaBienInmuebleBackEnd extends BackEndBean implements
        Serializable {

	private static final long serialVersionUID = -8719192410232401943L;

	@Autowired
	ServicioWebUtils servicioWebUtils;
	
        private static final Logger LOGGER = LogManager.getLogger(AltaBienInmuebleBackEnd.class
            .getName());

    @Autowired
    BienWrapper bienWrapper;

    public int ejectuarTRN(final BienBean bien) {

        LOGGER.debug("Entra al metrodo ejectuarTRN");

        Ejecutar.ITRBIENALTATRNI contexto = this.initPeticion(bien);

        EjecutarResult respuesta = ejecutarWS(contexto);

        super.verificaRespuesta(respuesta);
        
        bien.setIdInterno(String.valueOf(respuesta.getResponseBansefi().getOTRBIENALTATRNO().getTRBIENALTAEVTZ().getBIOTROSBIENESP().getIDINTERNOBI()));
        
        LOGGER.debug("Sale del metrodo ejectuarTRN");

        return respuesta.getResponseBansefi().getOTRBIENALTATRNO().getRTRNCD();
    }

    /**
     * Función para dar de alta un bien invocando un servicio web.
     * 
     * @param bien
     *            bean con los datos de entrada
     * @return Objeto indicando se ha realizado bien el alta
     */
    public ResponseBansefi getAltaBienResponse(final BienBean bien)
            throws NoControlableException, ControlableException {
        LOGGER.debug("Entra al metrodo getAltaBienResponse");

        ResponseBansefi response = null;

        
        LOGGER.debug("Sale del metrodo getAltaBienResponse");
        return response;
    }

    /**
     * Función para invocar al servicio web y obtener su respuesta.
     * 
     * @param contexto
     *            Objeto de petición al servicio web
     * @return La respuesta del servicio web.
     */
    private EjecutarResult ejecutarWS(Ejecutar.ITRBIENALTATRNI contexto)
            throws NoControlableException {
        EjecutarResult respuesta = null;

        try {
            respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(AltaBienInmuebleServicio.class, contexto);
        } catch (NoControlableException e) {
            throw new NoControlableException(
                    "Error al invocar servicio web alta de bien ", e);
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
    private ITRBIENALTATRNI initPeticion(final BienBean bien) {
        Ejecutar.ITRBIENALTATRNI contexto = new Ejecutar.ITRBIENALTATRNI();
        Ejecutar.ITRBIENALTATRNI.STDTRNIPARMV stdtrniparmv = new Ejecutar.ITRBIENALTATRNI.STDTRNIPARMV();
        Ejecutar.ITRBIENALTATRNI.TRBIENALTAEVTY trbienaltavty = new Ejecutar.ITRBIENALTATRNI.TRBIENALTAEVTY();

        Ejecutar.ITRBIENALTATRNI.TRBIENALTAEVTY.DATOSCARACTV datoscaractv = new Ejecutar.ITRBIENALTATRNI.TRBIENALTAEVTY.DATOSCARACTV();

        trbienaltavty = bienWrapper.wrappAltaBienInmuebleBean(bien);

        DATOSRGSTROV datosrgstrov = this.creaDireccionRegistral(bien);
        
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

        datoscaractv.setVALORBI(new BigDecimal(0));

        for (int i = 0; i < 50; i++) {
            trbienaltavty.getDATOSCARACTV().add(datoscaractv);

        }

        trbienaltavty.setDATOSRGSTROV(datosrgstrov);

        
        stdtrniparmv.setIDINTERNOTERMTN(super.getTerminal());
        stdtrniparmv.setCODTX(CodTxConstants.COD_TX_TR_BIEN_ALTA_TRN);

        trbienaltavty.getDATOSBIENV().setCODNUMRCOMONEDA("MXN");
        trbienaltavty.getDATOSBIENV().setCODNRBEEN(super.getEntidad());
        trbienaltavty.getDATOSBIENV().setINDELEM("N");

        contexto.setSTDTRNIPARMV(stdtrniparmv);
        contexto.setTRBIENALTAEVTY(trbienaltavty);


        return contexto;

    }

    public DATOSRGSTROV creaDireccionRegistral(final BienBean bien) {

        Ejecutar.ITRBIENALTATRNI.TRBIENALTAEVTY.DATOSRGSTROV datosrgstrov = new Ejecutar.ITRBIENALTATRNI.TRBIENALTAEVTY.DATOSRGSTROV();

        DRCOMPRGSTROV drcomprgstrov1 = new DRCOMPRGSTROV();
        DRCOMPRGSTROV drcomprgstrov2 = new DRCOMPRGSTROV();
        DRCOMPRGSTROV drcomprgstrov3 = new DRCOMPRGSTROV();
        DRCOMPRGSTROV drcomprgstrov4 = new DRCOMPRGSTROV();
        DRCOMPRGSTROV drcomprgstrov5 = new DRCOMPRGSTROV();
        DRCOMPRGSTROV drcomprgstrov6 = new DRCOMPRGSTROV();
        DRCOMPRGSTROV drcomprgstrovRelleno = new DRCOMPRGSTROV();
        super.initialize(drcomprgstrovRelleno);

        drcomprgstrov1
                .setCODCOMPRGSTRO(ConstantesFuncionales.TP_COD_COMP_RGSTRO_NUM_REGISTRAL);
        drcomprgstrov1
                .setVALCOMPRGSTRODRLEN(ConstantesFuncionales.TP_VAL_COMP_RGSTRO_NUM_REGISTRAL_LEN);
        drcomprgstrov1.setVALCOMPRGSTRODR(bien.getDireccionRegistral()
                .getNumeroRegistral());

        drcomprgstrov2
                .setCODCOMPRGSTRO(ConstantesFuncionales.TP_COD_COMP_RGSTRO_TOMO);
        drcomprgstrov2
                .setVALCOMPRGSTRODRLEN(ConstantesFuncionales.TP_VAL_COMP_RGSTRO_TOMO_LEN);
        drcomprgstrov2.setVALCOMPRGSTRODR(bien.getDireccionRegistral()
                .getTomo());

        drcomprgstrov3
                .setCODCOMPRGSTRO(ConstantesFuncionales.TP_COD_COMP_RGSTRO_LIBRO);
        drcomprgstrov3
                .setVALCOMPRGSTRODRLEN(ConstantesFuncionales.TP_VAL_COMP_RGSTRO_LIBRO_LEN);
        drcomprgstrov3.setVALCOMPRGSTRODR(bien.getDireccionRegistral()
                .getLibro());

        drcomprgstrov4
                .setCODCOMPRGSTRO(ConstantesFuncionales.TP_COD_COMP_RGSTRO_FOLIO);
        drcomprgstrov4
                .setVALCOMPRGSTRODRLEN(ConstantesFuncionales.TP_VAL_COMP_RGSTRO_FOLIO_LEN);
        drcomprgstrov4.setVALCOMPRGSTRODR(bien.getDireccionRegistral()
                .getFolio());

        drcomprgstrov5
                .setCODCOMPRGSTRO(ConstantesFuncionales.TP_COD_COMP_RGSTRO_NUM_INSC);
        drcomprgstrov5
                .setVALCOMPRGSTRODRLEN(ConstantesFuncionales.TP_VAL_COMP_RGSTRO_NUM_INSC_LEN);
        drcomprgstrov5.setVALCOMPRGSTRODR(bien.getDireccionRegistral()
                .getNumeroInscripcion());

        drcomprgstrov6
                .setCODCOMPRGSTRO(ConstantesFuncionales.TP_COD_COMP_RGSTRO_FECHA_EXP);
        drcomprgstrov6
                .setVALCOMPRGSTRODRLEN(ConstantesFuncionales.TP_VAL_COMP_RGSTRO_FECHA_EXP_LEN);
        DateFormat df = new SimpleDateFormat(
                ConstantesFuncionales.GENERAL_DATE_FORMATTER);
        if (bien.getDireccionRegistral().getFechaExpedicion() != null) {
            drcomprgstrov6.setVALCOMPRGSTRODR(df.format(bien
                    .getDireccionRegistral().getFechaExpedicion()));
        } else {
            drcomprgstrov6.setVALCOMPRGSTRODR("");
        }

        datosrgstrov.getDRCOMPRGSTROV().add(drcomprgstrov1);
        datosrgstrov.getDRCOMPRGSTROV().add(drcomprgstrov2);
        datosrgstrov.getDRCOMPRGSTROV().add(drcomprgstrov3);
        datosrgstrov.getDRCOMPRGSTROV().add(drcomprgstrov4);
        datosrgstrov.getDRCOMPRGSTROV().add(drcomprgstrov5);
        datosrgstrov.getDRCOMPRGSTROV().add(drcomprgstrov6);

        for (int i = datosrgstrov.getDRCOMPRGSTROV().size(); i < ConstantesFuncionales.TP_DIRECCION_REGISTRAL_LEN; i++) {
            datosrgstrov.getDRCOMPRGSTROV().add(drcomprgstrovRelleno);
        }

        return datosrgstrov;
    }

}
