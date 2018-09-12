package mx.babel.bansefi.banksystem.base.backends;

import java.io.Serializable;

import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.beans.domain.BienBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.webservices.bajabieningresogasto.BajaBienIngresoGastoServicio;
import mx.babel.bansefi.banksystem.base.webservices.bajabieningresogasto.Ejecutar;
import mx.babel.bansefi.banksystem.base.webservices.bajabieningresogasto.Ejecutar.ITRBAJABIENINGGASTRN;
import mx.babel.bansefi.banksystem.base.webservices.bajabieningresogasto.EjecutarResult;
import mx.babel.bansefi.banksystem.base.wrappers.BienWrapper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Backend que se encarga de dar de baja un bien ingreso gasto llamando a la TRN
 * TR_BAJA_BIEN_ING_GAS_TRN
 * 
 * @author luis.gcastellano
 * 
 */
@Component
public class BajaBienIngresoGastoBackEnd extends BackEndBean implements
        Serializable {

	private static final long serialVersionUID = -7794589391663047295L;

	private static final Logger LOGGER = LogManager.getLogger(BajaBienIngresoGastoBackEnd.class
            .getName());

    @Autowired
    BienWrapper bienWrapper;
    
	@Autowired
	ServicioWebUtils servicioWebUtils;


    public int ejectuarTRN(final BienBean bien) {

        LOGGER.debug("Entra al metrodo ejectuarTRN");

        Ejecutar.ITRBAJABIENINGGASTRN contexto = this.initPeticion(bien);

        EjecutarResult respuesta = ejecutarWS(contexto);
        
        super.verificaRespuesta(respuesta);

        LOGGER.debug("Sale del metrodo ejectuarTRN");

        return respuesta.getResponseBansefi().getOTRBAJABIENINGGASTRN().getRTRNCD();
    }
    
    /**
     * Función para invocar al servicio web y obtener su respuesta.
     * 
     * @param contexto
     *            Objeto de petición al servicio web
     * @return La respuesta del servicio web.
     */
    private EjecutarResult ejecutarWS(Ejecutar.ITRBAJABIENINGGASTRN contexto)
            throws NoControlableException {
        EjecutarResult respuesta = null;

        try {
            respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(BajaBienIngresoGastoServicio.class, contexto);
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
    private ITRBAJABIENINGGASTRN initPeticion(final BienBean bien) {
        Ejecutar.ITRBAJABIENINGGASTRN contexto = new Ejecutar.ITRBAJABIENINGGASTRN();
        Ejecutar.ITRBAJABIENINGGASTRN.STDTRNIPARMV stdtrniparmv = new Ejecutar.ITRBAJABIENINGGASTRN.STDTRNIPARMV();
        Ejecutar.ITRBAJABIENINGGASTRN.TRBAJABIENINGGASEVT trbienbajaevty = new Ejecutar.ITRBAJABIENINGGASTRN.TRBAJABIENINGGASEVT();

        Ejecutar.ITRBAJABIENINGGASTRN.TRBAJABIENINGGASEVT.PEPERSRLBIENP pepersrlbienp = new Ejecutar.ITRBAJABIENINGGASTRN.TRBAJABIENINGGASEVT.PEPERSRLBIENP();

        
        stdtrniparmv.setIDINTERNOTERMTN(super.getTerminal());
        stdtrniparmv.setCODTX(CodTxConstants.COD_TX_TR_BIEN_BAJA_TRN);
        super.initialize(stdtrniparmv);

        pepersrlbienp = bienWrapper.wrappBajaBienIngresoGastoBean(bien);
        pepersrlbienp.setCODNRBEEN(super.getEntidad());
        
        pepersrlbienp.setIDINTERNOPE(bien.getIdInternoPersona());

        trbienbajaevty.setPEPERSRLBIENP(pepersrlbienp);
        contexto.setSTDTRNIPARMV(stdtrniparmv);
        contexto.setTRBAJABIENINGGASEVT(trbienbajaevty);
        super.initialize(contexto);

        return contexto;

    }
}
