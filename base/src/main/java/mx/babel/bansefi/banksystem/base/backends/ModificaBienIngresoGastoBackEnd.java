package mx.babel.bansefi.banksystem.base.backends;

import java.io.Serializable;
import java.math.BigDecimal;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.beans.domain.BienBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.webservices.modificabieningresogasto.Ejecutar;
import mx.babel.bansefi.banksystem.base.webservices.modificabieningresogasto.Ejecutar.ITRMODIBIENINGGASTRN;
import mx.babel.bansefi.banksystem.base.webservices.modificabieningresogasto.EjecutarResult;
import mx.babel.bansefi.banksystem.base.webservices.modificabieningresogasto.ModificaBienIngresoGastoServicio;
import mx.babel.bansefi.banksystem.base.webservices.modificabieningresogasto.ResponseBansefi;
import mx.babel.bansefi.banksystem.base.wrappers.BienWrapper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Backend que se encarga de modificar un bien inmueble  llamando a la TRN
 * TR_MODI_BIEN_ING_GAS_TRN
 * 
 * @author luis.gcastellano
 * 
 */
@Component
public class ModificaBienIngresoGastoBackEnd extends BackEndBean implements Serializable {

	private static final long serialVersionUID = -158918294045525272L;

	private static final Logger LOGGER = LogManager.getLogger(ModificaBienIngresoGastoBackEnd.class
            .getName());

    @Autowired
    BienWrapper bienWrapper;

	@Autowired
	ServicioWebUtils servicioWebUtils;
	
    public int ejectuarTRN(final BienBean bien) {

        LOGGER.debug("Entra al metrodo ejectuarTRN");

        Ejecutar.ITRMODIBIENINGGASTRN contexto = this.initPeticion(bien);

        EjecutarResult respuesta = ejecutarWS(contexto);
        
        super.verificaRespuesta(respuesta);
        
        LOGGER.debug("Sale del metrodo ejectuarTRN");

        return respuesta.getResponseBansefi().getOTRMODIBIENINGGASTRN().getRTRNCD();
    }
    
    
    /**
     * Función para dar de baja un bien inmueble invocando un servicio web.
     * 
     * @param bien
     *            bean con los datos de entrada
     * @return Objeto indicando se ha realizado bien la baja
     */
    public ResponseBansefi getModificaBienResponse(final BienBean bien)
            throws NoControlableException, ControlableException {
        LOGGER.debug("Entra al metrodo getModificaBienResponse");

        ResponseBansefi response = null;

       
        LOGGER.debug("Sale del metrodo getModificaBienResponse");
        return response;
    }
    
    /**
     * Función para invocar al servicio web y obtener su respuesta.
     * 
     * @param contexto
     *            Objeto de petición al servicio web
     * @return La respuesta del servicio web.
     */
    private EjecutarResult ejecutarWS(Ejecutar.ITRMODIBIENINGGASTRN contexto)
            throws NoControlableException {
        EjecutarResult respuesta = null;

        try {
            respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(ModificaBienIngresoGastoServicio.class, contexto);
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
    private ITRMODIBIENINGGASTRN initPeticion(final BienBean bien) {
        Ejecutar.ITRMODIBIENINGGASTRN contexto = new Ejecutar.ITRMODIBIENINGGASTRN();
        Ejecutar.ITRMODIBIENINGGASTRN.STDTRNIPARMV stdtrniparmv = new Ejecutar.ITRMODIBIENINGGASTRN.STDTRNIPARMV();
        Ejecutar.ITRMODIBIENINGGASTRN.TRMODIBIENINGGASEVT TRMODIBIENINGGASEVT = new Ejecutar.ITRMODIBIENINGGASTRN.TRMODIBIENINGGASEVT();
        Ejecutar.ITRMODIBIENINGGASTRN.PEPERSRLBIENP pepersrlbienp = new Ejecutar.ITRMODIBIENINGGASTRN.PEPERSRLBIENP();

        stdtrniparmv.setIDINTERNOTERMTN(super.getTerminal());
        stdtrniparmv.setCODTX(CodTxConstants.COD_TX_TR_MODI_BIEN_ING_GAS_TRN);
        super.initialize(stdtrniparmv);
        
        TRMODIBIENINGGASEVT = bienWrapper.wrappModificaBienIngresoGastoBean(bien);
        
        TRMODIBIENINGGASEVT.setCODNRBEEN(super.getEntidad());
        TRMODIBIENINGGASEVT.setCODNUMRCOMONEDA("MXN");
        TRMODIBIENINGGASEVT.setIDINTERNOPE(bien.getIdInternoPersona());
        TRMODIBIENINGGASEVT.setVALADQBIRLPE(new BigDecimal("0"));
        TRMODIBIENINGGASEVT.setPCTBIRLPE(new BigDecimal("0"));
        super.initialize(TRMODIBIENINGGASEVT);
        
        super.initialize(pepersrlbienp);
        
        contexto.setSTDTRNIPARMV(stdtrniparmv);
        contexto.setTRMODIBIENINGGASEVT(TRMODIBIENINGGASEVT);
        contexto.setPEPERSRLBIENP(pepersrlbienp);
        super.initialize(contexto);

        return contexto;

    }    
    
}
