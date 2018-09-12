package mx.babel.bansefi.banksystem.base.backends;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.beans.domain.BienBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.constants.ConstantesFuncionales;
import mx.babel.bansefi.banksystem.base.utils.CatalogoUtils;
import mx.babel.bansefi.banksystem.base.webservices.consultalistacrct.ConsultaListaCrctServicio;
import mx.babel.bansefi.banksystem.base.webservices.consultalistacrct.Ejecutar;
import mx.babel.bansefi.banksystem.base.webservices.consultalistacrct.Ejecutar.ITRCRCTLSTTRNI.TRCRCTLSTEVTY.BICRCTBIENP;
import mx.babel.bansefi.banksystem.base.webservices.consultalistacrct.EjecutarResult;
import mx.babel.bansefi.banksystem.base.webservices.consultalistacrct.ResponseBansefi;
import mx.babel.bansefi.banksystem.base.wrappers.CrctWrapper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConsultaListaCrctBackEnd extends BackEndBean{

    private static final long serialVersionUID = 6105813358149523435L;

    private static final Logger LOGGER = LogManager
            .getLogger(ConsultaListaCrctBackEnd.class.getName());
    
    @Autowired
    ServicioWebUtils servicioWebUtils;

    @Autowired
    CrctWrapper crctWrapper;
    
    @Autowired
    private CatalogoUtils catalogoUtils;
    
    /**
     * Se encarga de devolver los datos Crct de un bien
     * 
     * @param bien El bien sobre el que se quiere consultar sus datos
     * @return El bien con los datos ampliados
     */
    public BienBean ejecutarTRN(BienBean bien) 
            throws NoControlableException, ControlableException{
        
        LOGGER.debug("Entra al metodo ejectuarTRN");
        BienBean bienAmpliado = this
                .getListaCrctResponse(bien);
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
    public BienBean getListaCrctResponse(BienBean bien)
            throws NoControlableException, ControlableException {

        LOGGER.debug("Entra al metodo getListaCrctResponse");
        Ejecutar.ITRCRCTLSTTRNI contexto = this.initPeticion(bien);

        EjecutarResult respuesta = ejecutarWS(contexto);

        super.verificaRespuesta(respuesta);
        bien = getBienDetalle(respuesta.getResponseBansefi(), bien);
        
        LOGGER.debug("Sale del metodo getListaCrctResponse");
        return bien;
    }
    
    BienBean getBienDetalle(ResponseBansefi responseBansefi, BienBean bien){
        
        this.crctWrapper.wrappCrctBean(responseBansefi.getOTRCRCTLSTTRNO().getTRCRCTLSTEVTZ(), bien);
        
        return bien;
    }

    /**
     * Función para inicializar los atributos del objeto de peticíon al servicio
     * web.
     * 
     * @param bien
     *           
     * @return Objeto de petición al web service
     */
    private Ejecutar.ITRCRCTLSTTRNI initPeticion(BienBean bien) {
        LOGGER.debug("Entra al metodo initPeticion");
        Ejecutar.ITRCRCTLSTTRNI contexto = new Ejecutar.ITRCRCTLSTTRNI();
        
        Ejecutar.ITRCRCTLSTTRNI.STDTRNIPARMV stdtrniparmv = new Ejecutar.ITRCRCTLSTTRNI.STDTRNIPARMV();
        
        Ejecutar.ITRCRCTLSTTRNI.TRCRCTLSTEVTY trcrctlstevty = new Ejecutar.ITRCRCTLSTTRNI.TRCRCTLSTEVTY();
        
        stdtrniparmv.setCODTX(CodTxConstants.COD_TX_TR_CRCT_LST_TRN);
        stdtrniparmv.setIDINTERNOTERMTN(super.getTerminal());
        
        for(int i=0; i<ConstantesFuncionales.CODIGOS_CRCT.size(); i++){
            BICRCTBIENP bicrctbienp = new BICRCTBIENP();
            bicrctbienp.setCODNRBEEN(super.getEntidad());
            bicrctbienp.setCODCRCTBIEN(ConstantesFuncionales.CODIGOS_CRCT.get(i));
            bicrctbienp.setIDINTERNOBI(Integer.valueOf(bien.getIdInterno()));
            trcrctlstevty.getBICRCTBIENP().add(bicrctbienp);
        }
        
        contexto.setSTDTRNIPARMV(stdtrniparmv);
        contexto.setTRCRCTLSTEVTY(trcrctlstevty);
        
        LOGGER.debug("Salimos del metodo initPeticion");
        return contexto;
    }
    
    /**
     * Función para invocar al servicio web y obtener su respuesta.
     * 
     * @param contexto
     *            Objeto de petición al servicio web
     * @return La respuesta del servicio web.
     */
    private EjecutarResult ejecutarWS(Ejecutar.ITRCRCTLSTTRNI contexto)
            throws NoControlableException {
        LOGGER.debug("Entra al metodo ejecutarWS");
        EjecutarResult respuesta = null;
        Ejecutar ejecutar = new Ejecutar();
        ejecutar.setITRCRCTLSTTRNI(contexto);
        try {
            respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(ConsultaListaCrctServicio.class, contexto);
        } catch (NoControlableException e) {
            throw new NoControlableException(
                    "Error al invocar servicio web de consulta de "
                            + "entidades.", e);
        }
        LOGGER.debug("Sale del metodo ejecutarWS");
        return respuesta;
    }
}
