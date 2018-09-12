package mx.babel.bansefi.banksystem.base.backends;

import java.io.Serializable;
import java.math.BigDecimal;

import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.beans.domain.BienBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.webservices.altabieningresogasto.AltaBienIngresoGastoServicio;
import mx.babel.bansefi.banksystem.base.webservices.altabieningresogasto.Ejecutar;
import mx.babel.bansefi.banksystem.base.webservices.altabieningresogasto.Ejecutar.ITRALTABIENINGGASTRN;
import mx.babel.bansefi.banksystem.base.webservices.altabieningresogasto.EjecutarResult;
import mx.babel.bansefi.banksystem.base.wrappers.BienWrapper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Backend que se encarga de dar de alta un bien de ingreso gasto llamando a la
 * TRN TR_ALTA_BIEN_ING_GAS_TRN
 * 
 * @author luis.gcastellano
 * 
 */
@Component
public class AltaBienIngresoGastoBackEnd extends BackEndBean implements
        Serializable {

	private static final long serialVersionUID = -8737611961409165502L;

	private static final Logger LOGGER = LogManager.getLogger(AltaBienIngresoGastoBackEnd.class
            .getName());

    @Autowired
    BienWrapper bienWrapper;
    
	@Autowired
	ServicioWebUtils servicioWebUtils;


    public int ejectuarTRN(final BienBean bien) {

        LOGGER.debug("Entra al metrodo ejectuarTRN");

        Ejecutar.ITRALTABIENINGGASTRN contexto = this.initPeticion(bien);

        EjecutarResult respuesta = ejecutarWS(contexto);

        super.verificaRespuesta(respuesta);
        
        bien.setIdInterno(String.valueOf(respuesta.getResponseBansefi().getOTRALTABIENINGGASTRN().getTRALTABIENINGGASEVT().getBIINGRGTOSP().getIDINTERNOBI()));

        LOGGER.debug("Sale del metrodo ejectuarTRN");

        return respuesta.getResponseBansefi().getOTRALTABIENINGGASTRN().getRTRNCD();
    }    

    /**
     * Función para invocar al servicio web y obtener su respuesta.
     * 
     * @param contexto
     *            Objeto de petición al servicio web
     * @return La respuesta del servicio web.
     */
    private EjecutarResult ejecutarWS(Ejecutar.ITRALTABIENINGGASTRN contexto)
            throws NoControlableException {
        EjecutarResult respuesta = null;

        try {
            respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(AltaBienIngresoGastoServicio.class, contexto);
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
    private ITRALTABIENINGGASTRN initPeticion(final BienBean bien) {
        Ejecutar.ITRALTABIENINGGASTRN contexto = new Ejecutar.ITRALTABIENINGGASTRN();
        Ejecutar.ITRALTABIENINGGASTRN.STDTRNIPARMV stdtrniparmv = new Ejecutar.ITRALTABIENINGGASTRN.STDTRNIPARMV();
        Ejecutar.ITRALTABIENINGGASTRN.TRALTABIENINGGASEVT TRALTABIENINGGASEVT = new Ejecutar.ITRALTABIENINGGASTRN.TRALTABIENINGGASEVT();

        stdtrniparmv.setIDINTERNOTERMTN(super.getTerminal());
        stdtrniparmv.setIDEMPLAUT("");
        stdtrniparmv.setCODTX(CodTxConstants.COD_TX_TR_ALTA_BIEN_ING_GAS_TRN);
        stdtrniparmv.setCODTXDI("");

        TRALTABIENINGGASEVT = bienWrapper.wrappAltaBienIngresoGastoBean(bien);
        TRALTABIENINGGASEVT.setCODNRBEEN(super.getEntidad());
        TRALTABIENINGGASEVT.setCODNUMRCOMONEDA("MXN");
        
        TRALTABIENINGGASEVT.setIDINTERNOPE(bien.getIdInternoPersona());
        TRALTABIENINGGASEVT.setVALADQBIRLPE(new BigDecimal("0"));
        TRALTABIENINGGASEVT.setPCTBIRLPE(new BigDecimal("0"));
        super.initialize(TRALTABIENINGGASEVT);

        contexto.setSTDTRNIPARMV(stdtrniparmv);
        contexto.setTRALTABIENINGGASEVT(TRALTABIENINGGASEVT);
        return contexto;

    }
    
}
