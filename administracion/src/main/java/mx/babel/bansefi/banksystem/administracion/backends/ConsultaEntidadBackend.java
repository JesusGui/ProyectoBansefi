package mx.babel.bansefi.banksystem.administracion.backends;

import java.io.Serializable;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.administracion.beans.EntidadBean;
import mx.babel.bansefi.banksystem.administracion.webservices.detalleentidad.DetalleEntidadServicio;
import mx.babel.bansefi.banksystem.administracion.webservices.detalleentidad.Ejecutar;
import mx.babel.bansefi.banksystem.administracion.webservices.detalleentidad.EjecutarResult;
import mx.babel.bansefi.banksystem.administracion.webservices.detalleentidad.ResponseBansefi;
import mx.babel.bansefi.banksystem.administracion.wrappers.EntidadWrapper;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConsultaEntidadBackend extends BackEndBean implements Serializable{

    private static final long serialVersionUID = 926428817373584425L;

	private static final Logger LOGGER = LogManager.getLogger(ConsultaEntidadBackend.class.getName());

    @Autowired
    EntidadWrapper entidadWrapper;
    
    @Autowired
	ServicioWebUtils servicioWebUtils;

    public EntidadBean ejectuarTRN(String codigoEntidad){
       LOGGER.debug("Entra al metodo ejecutarTRN");
       
       Ejecutar.ITRCONENTIDADTRNI contexto = this.initPeticion(codigoEntidad);
        
       EjecutarResult respuesta = ejecutarWS(contexto);
       
       super.verificaRespuesta(respuesta);
       
       EntidadBean entidad = null;
       
       if(verificaResponseBansefi(respuesta)){
           entidad = getEntidad(respuesta.getResponseBansefi());
       }
       
       LOGGER.debug("Sale del metodo ejecutarTRN");
       return entidad;
    }    
    
    /**
     * Función encargada de obtener la lista de entidadBusqueda a partir de la respuesta del servicio web
     * 
     * @param idInterno El id interno de la persona moral
     * @param response El objeto de reultado enviado por el servicio web.
     * @return La entidad con los datos recibidos del ser web
     */
    private EntidadBean getEntidad(ResponseBansefi response){
        EntidadBean entidad = null;        
        if(verificaRespuestaEntidad(response)){
            ResponseBansefi.OTRCONENTIDADTRNO.TRCONENTIDADEVTZ entidadWs = response.getOTRCONENTIDADTRNO().getTRCONENTIDADEVTZ();
            entidad = entidadWrapper.wrappBean(entidadWs);
        }
        return entidad;
    }
    
    
    /**
     * Función que valida que la respuesta Bansefi contenga un objeto con los datos
     * de la entidad
     * 
     * @param response Respuesta Bansefi con datos de la entidad
     * @return <code>true</code> si la respuesta Bansefi contiene una entidad
     */
    private Boolean verificaRespuestaEntidad(ResponseBansefi response){
        Boolean noNulo = false;
       
        if(response != null && response.getOTRCONENTIDADTRNO() != null && response.getOTRCONENTIDADTRNO().getTRCONENTIDADEVTZ() != null){
            noNulo = true;
        }
        return noNulo;
    }
    
    /**
     * Función para invocar al servicio web y obtener su respuesta.
     * 
     * @param contexto Objeto de petición al servicio web
     * @return La respuesta del servicio web.
     */
    private EjecutarResult ejecutarWS(Ejecutar.ITRCONENTIDADTRNI contexto){
        EjecutarResult respuesta = null;        
        try{
            respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(DetalleEntidadServicio.class, contexto);
        }catch(NoControlableException e){
            throw new NoControlableException("Error al invocar servicio web de consulta de entidad.", e);
        }
        return respuesta;
    }
    
    /**
     * Función para inicializar los atributos del objeto de peticíon al servicio web.
     * 
     * @param req bean con los datos de entrada
     * @return Objeto de petición al web service
     */
    private Ejecutar.ITRCONENTIDADTRNI initPeticion(String codigoEntidad) {
        Ejecutar.ITRCONENTIDADTRNI contexto = new Ejecutar.ITRCONENTIDADTRNI();
        Ejecutar.ITRCONENTIDADTRNI.STDTRNIPARMV stdtrniparmv =  new Ejecutar.ITRCONENTIDADTRNI.STDTRNIPARMV();
        Ejecutar.ITRCONENTIDADTRNI.TRCONENTIDADEVTY trconentidadevty = new Ejecutar.ITRCONENTIDADTRNI.TRCONENTIDADEVTY();
        Ejecutar.ITRCONENTIDADTRNI.TRCONENTIDADEVTY.CRENTIDADP crentidadp = new  Ejecutar.ITRCONENTIDADTRNI.TRCONENTIDADEVTY.CRENTIDADP();
        
        try{
            
            super.initialize(stdtrniparmv);
            stdtrniparmv.setCODTX(CodTxConstants.COD_TX_TR_CON_ENTIDAD_TRN);
            stdtrniparmv.setIDINTERNOTERMTN(super.getTerminal());
           
            crentidadp.setCODNRBEEN(codigoEntidad);
            trconentidadevty.setCRENTIDADP(crentidadp);
            contexto.setSTDTRNIPARMV(stdtrniparmv);
            contexto.setTRCONENTIDADEVTY(trconentidadevty);
        }catch(NullPointerException npe){
            throw new ControlableException("Parametros nulos", npe);
        }
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
    
}
