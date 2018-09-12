package mx.babel.bansefi.banksystem.base.backends.consultalistaentidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.busqueda.EntidadBusquedaBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.constants.ConstantesFuncionales;
import mx.babel.bansefi.banksystem.base.utils.EntidadBusquedaWrapper;
import mx.babel.bansefi.banksystem.base.webservices.consultalistaentidades.ConsultaListaEntidadesServicio;
import mx.babel.bansefi.banksystem.base.webservices.consultalistaentidades.Ejecutar;
import mx.babel.bansefi.banksystem.base.webservices.consultalistaentidades.EjecutarResult;
import mx.babel.bansefi.banksystem.base.webservices.consultalistaentidades.ResponseBansefi;
import mx.babel.bansefi.banksystem.base.webservices.consultalistaentidades.ResponseBansefi.OTRLISTAENTIDADES2TRN.TRLISTAENTIDADESEVTZ;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConsultaListaEntidadesBackend extends BackEndBean implements Serializable{


	private static final long serialVersionUID = -5064392837586559340L;

	private static final Logger LOGGER = LogManager.getLogger(ConsultaListaEntidadesBackend.class.getName());
    

    @Autowired
    EntidadBusquedaWrapper entidadWrapper;
    
	@Autowired
	ServicioWebUtils servicioWebUtils;

	public List<Object> ejectuarTRN(Object object){
        LOGGER.debug("Entra al metodo ejectuarTRN");
        List<Object> entidadBusquedaList = this.getListaEntidadesResponse(object);
        LOGGER.debug("Sale del metodo ejectuarTRN");
        return entidadBusquedaList;
    }
    
    /**
     * Función para obtener una lista de entidades invocando un 
     * servicio web.
     * 
     * @param object bean con los datos de entrada
     * @return Objeto con atributos de las entidades.
     */
    public List<Object> getListaEntidadesResponse(Object object) 
            throws NoControlableException, ControlableException{
        
        LOGGER.debug("Entra al metodo getListaEntidadesResponse");
        Ejecutar.ITRLISTAENTIDADES2TRN contexto = this.initPeticion(object);
        
        EjecutarResult respuesta = ejecutarWS(contexto);
        
        List<Object> entidadBusquedaBean = null;
        
        if(verificaResponseBansefi(respuesta)){
            entidadBusquedaBean = getEntidadBusqueda(respuesta.getResponseBansefi(), object);
        }
        LOGGER.debug("Sale del metodo getListaEntidadesResponse");
        return entidadBusquedaBean;
    }
    
    /**
     * Función encargada de obtener la lista de entidadBusqueda a partir de la respuesta del servicio web
     * 
     * @param idInterno El id interno de la persona moral
     * @param response El objeto de reultado enviado por el servicio web.
     * @return La entidad con los datos recibidos del ser web
     */
    private List<Object> getEntidadBusqueda(ResponseBansefi response, Object object)
            throws NoControlableException, ControlableException{
        LOGGER.debug("Entra al metodo getEntidadBusqueda");
        
        EntidadBusquedaBean entidad = (EntidadBusquedaBean) object;
        EntidadBusquedaBean entidadBusqueda = null;
        List<Object> entidadBusquedaList = null;
        
        if(verificaRespuestaEntidad(response)){
            
            entidadBusquedaList = new ArrayList<Object>();
            entidad.setMasDatos(response.getOTRLISTAENTIDADES2TRN()
                    .getMOREDATAIN() == 1);
                    
            for(int i=0; i<response.getOTRLISTAENTIDADES2TRN().getNUMBEROFRECORDS(); i++){
                TRLISTAENTIDADESEVTZ entidadBusquedaWs = response.getOTRLISTAENTIDADES2TRN().getTRLISTAENTIDADESEVTZ().get(i);
                if(ConstantesFuncionales.ENTIDAD_ADAN.equals(super.getEntidad()) || super.getEntidad().equals(entidadBusquedaWs.getCRENTIDADE().getCODNRBEEN())){
                    entidadBusqueda = entidadWrapper.wrappEntidadBusquedaBean(entidadBusquedaWs.getCRENTIDADE());
                    entidadBusquedaList.add(entidadBusqueda);
                    entidad.setUltimoDatoConsultaAnterior(entidadBusqueda.getCodigoEntidad());
                }
                
            }
        }
        LOGGER.debug("Sale del metodo getEntidadBusqueda");
        return entidadBusquedaList;
    }    
    
    /**
     * Función que valida que la respuesta Bansefi contenga un objeto con los datos
     * de la entidad
     * 
     * @param response Respuesta Bansefi con datos de la entidad
     * @return <code>true</code> si la respuesta Bansefi contiene una entidad
     */
    private Boolean verificaRespuestaEntidad(ResponseBansefi response){
        LOGGER.debug("Entra al metodo verificaRespuestaEntidad");
        Boolean noNulo = false;
       
        if(response != null && response.getOTRLISTAENTIDADES2TRN() != null && 
                response.getOTRLISTAENTIDADES2TRN().getTRLISTAENTIDADESEVTZ() != null){
            noNulo = true;
        }
        LOGGER.debug("Sale del metodo verificaRespuestaEntidad con valor -> " +noNulo);
        return noNulo;
    }
    
    /**
     * Función que valida que la respuesta del servidor no este vacía. 
     *  
     * @param respuesta Objeto respuesta del servicio web
     * @return <code>true</code> si la respuesta no esta vacía.
     */
    private Boolean verificaResponseBansefi(EjecutarResult respuesta){
        LOGGER.debug("Entra al metodo verificaResponseBansefi");
        Boolean noNulo = false;
        if(respuesta != null && respuesta.getResponseBansefi() != null){
            noNulo = true;
        }
        LOGGER.debug("Sale del metodo verificaResponseBansefi con valor -> " + noNulo);
        return noNulo;
    }
    
    /**
     * Función para invocar al servicio web y obtener su respuesta.
     * 
     * @param contexto Objeto de petición al servicio web
     * @return La respuesta del servicio web.
     */
    private EjecutarResult ejecutarWS(Ejecutar.ITRLISTAENTIDADES2TRN contexto) 
            throws NoControlableException{
        LOGGER.debug("Entra al metodo ejecutarWS");
        EjecutarResult respuesta = null;
        Ejecutar ejecutar = new Ejecutar();
        ejecutar.setITRLISTAENTIDADES2TRN(contexto);
        try{
            respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(ConsultaListaEntidadesServicio.class, contexto);
        }catch(NoControlableException e){
            throw new NoControlableException("Error al invocar servicio web de consulta de "
                    + "entidades.", e);
        }
        LOGGER.debug("Sale del metodo ejecutarWS");
        return respuesta;
    }
    
    /**
     * Función para inicializar los atributos del objeto de peticíon al servicio web.
     * 
     * @param object bean con los datos de entrada
     * @return Objeto de petición al web service
     */
    private Ejecutar.ITRLISTAENTIDADES2TRN initPeticion(Object object) {
        LOGGER.debug("Entra al metodo initPeticion");
        EntidadBusquedaBean entidadBusquedaBean = (EntidadBusquedaBean) object;
        
        Ejecutar.ITRLISTAENTIDADES2TRN contexto = new Ejecutar.ITRLISTAENTIDADES2TRN();
        Ejecutar.ITRLISTAENTIDADES2TRN.CRLACB crlacb =  new Ejecutar.ITRLISTAENTIDADES2TRN.CRLACB();
        Ejecutar.ITRLISTAENTIDADES2TRN.STDTRNIPARMV stdtrniparmv = new Ejecutar.ITRLISTAENTIDADES2TRN.STDTRNIPARMV();
        Ejecutar.ITRLISTAENTIDADES2TRN.TRLISTAENTIDADESEVTY trlistaentidadesevty = new Ejecutar.ITRLISTAENTIDADES2TRN.TRLISTAENTIDADESEVTY();
        Ejecutar.ITRLISTAENTIDADES2TRN.TRLISTAENTIDADESEVTY.CRENTIDADP crentidap = new Ejecutar.ITRLISTAENTIDADES2TRN.TRLISTAENTIDADESEVTY.CRENTIDADP();
        Ejecutar.ITRLISTAENTIDADES2TRN.TRLISTAENTIDADESEVTY.CRENTIDADV crentidav = new Ejecutar.ITRLISTAENTIDADES2TRN.TRLISTAENTIDADESEVTY.CRENTIDADV();
        contexto.setSCROLLABLEOCCURS(SCROLLABLE_OCCURS);

        super.initialize(crlacb);
        
        super.initialize(stdtrniparmv);
        stdtrniparmv.setCODTX(CodTxConstants.COD_TX_TR_LISTA_ENTIDADES_2_TRN);
        
        stdtrniparmv.setIDINTERNOTERMTN(super.getTerminal());
        
        stdtrniparmv.setNUMSEC(1);
        
        super.initialize(crentidap);
        if(entidadBusquedaBean.getUltimoDatoConsultaAnterior()!=null){
            crentidap.setCODNRBEEN((String)entidadBusquedaBean.getUltimoDatoConsultaAnterior());
        }
        
        super.initialize(crentidav);    
        if(StringUtils.isNotBlank(entidadBusquedaBean.getCodigoEntidad())){
            LOGGER.debug("Busqueda por codigo");
            crentidav.setCODNRBEEN(entidadBusquedaBean.getCodigoEntidad());

        }else{
            LOGGER.debug("Busqueda por nombre");
            crentidav.setNOMBENTEN(entidadBusquedaBean.getNombre());
        }
        
        
        
        trlistaentidadesevty.setCRENTIDADP(crentidap);
        trlistaentidadesevty.setCRENTIDADV(crentidav);
        contexto.setSTDTRNIPARMV(stdtrniparmv);
        contexto.setCRLACB(crlacb);
        contexto.setTRLISTAENTIDADESEVTY(trlistaentidadesevty);
       
        LOGGER.debug("Sale del metodo initPeticion");
        
        return contexto;
    
    }
    
}
