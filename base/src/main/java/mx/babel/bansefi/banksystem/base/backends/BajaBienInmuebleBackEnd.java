package mx.babel.bansefi.banksystem.base.backends;

import java.io.Serializable;
import java.math.BigDecimal;

import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.beans.domain.BienBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.webservices.bajabieninmueble.BajaBienInmuebleServicio;
import mx.babel.bansefi.banksystem.base.webservices.bajabieninmueble.Ejecutar;
import mx.babel.bansefi.banksystem.base.webservices.bajabieninmueble.Ejecutar.ITRBIENBAJATRNI;
import mx.babel.bansefi.banksystem.base.webservices.bajabieninmueble.EjecutarResult;
import mx.babel.bansefi.banksystem.base.wrappers.BienWrapper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Backend que se encarga de dar de baja un bien inmueble llamando a la TRN
 * TR_BIEN_BAJA_TRN
 * 
 * @author luis.gcastellano
 * 
 */
@Component
public class BajaBienInmuebleBackEnd extends BackEndBean implements
        Serializable {

	private static final long serialVersionUID = -5137347456590963672L;

	private static final Logger LOGGER = LogManager.getLogger(BajaBienInmuebleBackEnd.class
            .getName());

    @Autowired
    BienWrapper bienWrapper;
    
	@Autowired
	ServicioWebUtils servicioWebUtils;


    public int ejectuarTRN(final BienBean bien) {

        LOGGER.debug("Entra al metrodo ejectuarTRN");

        Ejecutar.ITRBIENBAJATRNI contexto = this.initPeticion(bien);

        EjecutarResult respuesta = ejecutarWS(contexto);
        
        super.verificaRespuesta(respuesta);        

        LOGGER.debug("Sale del metrodo ejectuarTRN");

        return respuesta.getResponseBansefi().getOTRBIENBAJATRNO().getRTRNCD();
    }
    
    /**
     * Función para invocar al servicio web y obtener su respuesta.
     * 
     * @param contexto
     *            Objeto de petición al servicio web
     * @return La respuesta del servicio web.
     */
    private EjecutarResult ejecutarWS(Ejecutar.ITRBIENBAJATRNI contexto)
            throws NoControlableException {
        EjecutarResult respuesta = null;

        try {
            respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(BajaBienInmuebleServicio.class, contexto);
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
    private ITRBIENBAJATRNI initPeticion(final BienBean bien) {
        Ejecutar.ITRBIENBAJATRNI contexto = new Ejecutar.ITRBIENBAJATRNI();
        Ejecutar.ITRBIENBAJATRNI.STDTRNIPARMV stdtrniparmv = new Ejecutar.ITRBIENBAJATRNI.STDTRNIPARMV();
        Ejecutar.ITRBIENBAJATRNI.TRBIENBAJAEVTY trbienbajaevty = new Ejecutar.ITRBIENBAJATRNI.TRBIENBAJAEVTY();

        Ejecutar.ITRBIENBAJATRNI.TRBIENBAJAEVTY.PEPERSRLBIENP pepersrlbienp = new Ejecutar.ITRBIENBAJATRNI.TRBIENBAJAEVTY.PEPERSRLBIENP();
        Ejecutar.ITRBIENBAJATRNI.TRBIENBAJAEVTY.BIBIENRLDIRP bibienrldirp = new Ejecutar.ITRBIENBAJATRNI.TRBIENBAJAEVTY.BIBIENRLDIRP();
        Ejecutar.ITRBIENBAJATRNI.TRBIENBAJAEVTY.PEPERSRLDIRP pepersrldirp = new Ejecutar.ITRBIENBAJATRNI.TRBIENBAJAEVTY.PEPERSRLDIRP();
        Ejecutar.ITRBIENBAJATRNI.TRBIENBAJAEVTY.NUMDIRRGSTROV numdirrgstrov = new Ejecutar.ITRBIENBAJATRNI.TRBIENBAJAEVTY.NUMDIRRGSTROV();
        Ejecutar.ITRBIENBAJATRNI.TRBIENBAJAEVTY.DATOSCARACTV datoscaractv = new Ejecutar.ITRBIENBAJATRNI.TRBIENBAJAEVTY.DATOSCARACTV();

        
        stdtrniparmv.setIDINTERNOTERMTN(super.getTerminal());
        stdtrniparmv.setCODTX(CodTxConstants.COD_TX_TR_BIEN_BAJA_TRN);
        super.initialize(stdtrniparmv);

        pepersrlbienp = bienWrapper.wrappBajaBienInmuebleBean(bien);
        pepersrlbienp.setCODNRBEEN(super.getEntidad());
        
        pepersrlbienp.setIDINTERNOPE(bien.getIdInternoPersona());

        super.initialize(bibienrldirp);
        super.initialize(pepersrldirp);
        super.initialize(numdirrgstrov);
        super.initialize(datoscaractv);
        datoscaractv.setVALORBI(new BigDecimal(0));

        trbienbajaevty.setBIBIENRLDIRP(bibienrldirp);
        trbienbajaevty.setNUMDIRRGSTROV(numdirrgstrov);
        trbienbajaevty.setPEPERSRLBIENP(pepersrlbienp);
        trbienbajaevty.setPEPERSRLDIRP(pepersrldirp);

        for (int i = 0; i < 25; i++) {
            trbienbajaevty.getDATOSCARACTV().add(datoscaractv);
        }

        contexto.setSTDTRNIPARMV(stdtrniparmv);
        contexto.setTRBIENBAJAEVTY(trbienbajaevty);
        super.initialize(contexto);

        return contexto;

    }
}
