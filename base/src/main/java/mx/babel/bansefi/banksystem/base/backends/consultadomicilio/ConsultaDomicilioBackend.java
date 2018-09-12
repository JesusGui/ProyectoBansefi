package mx.babel.bansefi.banksystem.base.backends.consultadomicilio;

import java.io.Serializable;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.backends.consultalocalidad.ConsultaLocalidadBackend;
import mx.babel.bansefi.banksystem.base.beans.domain.DomicilioTipoBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.utils.DomicilioWrapper;
import mx.babel.bansefi.banksystem.base.webservices.consultadomicilio.ConsultaDomicilioServicio;
import mx.babel.bansefi.banksystem.base.webservices.consultadomicilio.Ejecutar;
import mx.babel.bansefi.banksystem.base.webservices.consultadomicilio.EjecutarResult;
import mx.babel.bansefi.banksystem.base.webservices.consultadomicilio.ResponseBansefi;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConsultaDomicilioBackend extends BackEndBean implements Serializable {

	private static final long serialVersionUID = -3986850503879671819L;

	private static final Logger LOGGER = LogManager.getLogger(ConsultaDomicilioBackend.class.getName());
    
	@Autowired
	ServicioWebUtils servicioWebUtils;
	
    @Autowired
    private DomicilioWrapper domicilioWrapper;
    
    @Autowired
    private ConsultaLocalidadBackend consultaLocalidad;
    
    public DomicilioTipoBean ejectuarTRN(Integer idInterna, Integer numDir, String entidad) {
        
    	LOGGER.debug("Entra al metodo ejectuarTRN");
        
        Ejecutar.ITRPECONSDOMICTRNI contexto = this.initPeticion(idInterna,numDir,entidad);        
        EjecutarResult respuesta = ejecutarWS(contexto);        
        DomicilioTipoBean domicilio = null;
        
        try{
        	super.verificaRespuesta(respuesta);
        }catch (ControlableException ce){
        	if (ce.getRtncod()==RETURN_COD_SIN_DATOS){
        		return domicilio;
        	}else{
        		throw ce;
        	}
        }
        
        if(verificaResponseBansefi(respuesta)){
            domicilio = getDomicilio(respuesta.getResponseBansefi());
        }
        
        LOGGER.debug("Sale del metodo ejecutarTRN");
        return domicilio;
    }
    
    public DomicilioTipoBean ejectuarTRN(Integer idInterna, Integer numDir) {
    	return this.ejectuarTRN(idInterna, numDir, null);
    }
        
    /**
     * Función encargada de obtener la lista de entidadBusqueda a partir de la respuesta del servicio web
     * 
     * @param idInterno El id interno de la persona moral
     * @param response El objeto de reultado enviado por el servicio web.
     * @return La entidad con los datos recibidos del ser web
     */
    private DomicilioTipoBean getDomicilio(ResponseBansefi response){
    	DomicilioTipoBean domicilio = null;        
        if(verificaRespuestaDomicilio(response)){
            domicilio = domicilioWrapper.wrappDomicilioBean(response.getOTRPECONSDOMICTRNO().getTRPECONSDOMICEVTZ());
        }
        if (domicilio !=null){
        	domicilio = this.consultaLocalidad.ejecutarTRN(domicilio);        	
        }
        return domicilio;
    }
    
    
    /**
     * Función para invocar al servicio web y obtener su respuesta.
     * 
     * @param contexto Objeto de petición al servicio web
     * @return La respuesta del servicio web.
     */
    private EjecutarResult ejecutarWS(Ejecutar.ITRPECONSDOMICTRNI contexto){
        EjecutarResult respuesta = null;
        
        try{
            respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(ConsultaDomicilioServicio.class, contexto);
        }catch(NoControlableException e){
            throw new NoControlableException("Error al invocar servicio web de consulta de "
                    + "domicilio.", e);
        }
        return respuesta;
    }
    
    /**
     * Función para inicializar los atributos del objeto de peticíon al servicio web.
     * 
     * @param req bean con los datos de entrada
     * @return Objeto de petición al web service
     */
    private Ejecutar.ITRPECONSDOMICTRNI initPeticion(Integer idInterna, Integer numDir, String entidad) {
        Ejecutar.ITRPECONSDOMICTRNI contexto = new Ejecutar.ITRPECONSDOMICTRNI();
        Ejecutar.ITRPECONSDOMICTRNI.STDTRNIPARMV stdtrniparmv =  new Ejecutar.ITRPECONSDOMICTRNI.STDTRNIPARMV();
        Ejecutar.ITRPECONSDOMICTRNI.TRPECONSDOMICEVTY trpeconsdomicevty = new Ejecutar.ITRPECONSDOMICTRNI.TRPECONSDOMICEVTY();
        Ejecutar.ITRPECONSDOMICTRNI.TRPECONSDOMICEVTY.DRDOMICP drdomicp = new  Ejecutar.ITRPECONSDOMICTRNI.TRPECONSDOMICEVTY.DRDOMICP();
        Ejecutar.ITRPECONSDOMICTRNI.TRPECONSDOMICEVTY.PEPERSP pepersp = new Ejecutar.ITRPECONSDOMICTRNI.TRPECONSDOMICEVTY.PEPERSP(); 
                
        super.initialize(stdtrniparmv);
       
        stdtrniparmv.setCODTX(CodTxConstants.COD_TX_TR_PE_CONS_DOMIC_TRN);
        stdtrniparmv.setIDINTERNOTERMTN(super.getTerminal());
       
        super.initialize(drdomicp);
        
        if (entidad!=null){
        	drdomicp.setCODNRBEEN(entidad);
        }else {
        	drdomicp.setCODNRBEEN(super.getEntidad());
        }
        
        if(numDir!=null){
        	drdomicp.setNUMDIR(numDir);
        }
        
        super.initialize(pepersp);
        
        
        if(idInterna!=null){
            pepersp.setIDINTERNOPE(idInterna);
        }
        
        if (entidad!=null){
        	pepersp.setCODNRBEEN(entidad);
        }else {
        	pepersp.setCODNRBEEN(super.getEntidad());
        }
        
        trpeconsdomicevty.setDRDOMICP(drdomicp);
        trpeconsdomicevty.setPEPERSP(pepersp);
        
        contexto.setSTDTRNIPARMV(stdtrniparmv);
        contexto.setTRPECONSDOMICEVTY(trpeconsdomicevty);
        
        return contexto;
    
    }
    
    /**
     * Función que valida que la respuesta del servidor no este vacía. 
     *  
     * @param respuesta Objeto respuesta del servicio web
     * @return <code>true</code> si la respuesta no esta vacía.
     */
    private Boolean verificaResponseBansefi(EjecutarResult respuesta){
        Boolean noNulo = false;
        if(respuesta != null && respuesta.getResponseBansefi() != null){
            noNulo = true;
        }
        return noNulo;
    }
        
    /**
     * Función que valida que la respuesta Bansefi contenga un objeto con los datos
     * de la domicilio
     * 
     * @param response Respuesta Bansefi con datos de domicilio
     * @return <code>true</code> si la respuesta Bansefi contiene una entidad
     */
    private Boolean verificaRespuestaDomicilio(ResponseBansefi response){
        Boolean noNulo = false;
       
        if(response != null && response.getOTRPECONSDOMICTRNO() != null && 
                response.getOTRPECONSDOMICTRNO().getTRPECONSDOMICEVTZ() != null){
            noNulo = true;
        }
        return noNulo;
    }
    
}
