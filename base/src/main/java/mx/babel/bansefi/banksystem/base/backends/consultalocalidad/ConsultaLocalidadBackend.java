package mx.babel.bansefi.banksystem.base.backends.consultalocalidad;

import mx.babel.arq.catalogo.beans.CatalogoGeoBean;
import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.domain.DomicilioBean;
import mx.babel.bansefi.banksystem.base.beans.domain.DomicilioTipoBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.utils.LocalidadWrapper;
import mx.babel.bansefi.banksystem.base.webservices.consultalocalidad.ConsultaLocalidadServicio;
import mx.babel.bansefi.banksystem.base.webservices.consultalocalidad.Ejecutar;
import mx.babel.bansefi.banksystem.base.webservices.consultalocalidad.Ejecutar.ITRAGLOCALIDADCNSTRN.TRAGLOCALIDADCNSEVTY;
import mx.babel.bansefi.banksystem.base.webservices.consultalocalidad.Ejecutar.ITRAGLOCALIDADCNSTRN.TRAGLOCALIDADCNSEVTY.AGARGEOP;
import mx.babel.bansefi.banksystem.base.webservices.consultalocalidad.Ejecutar.ITRAGLOCALIDADCNSTRN.TRAGLOCALIDADCNSEVTY.AGLOCALIDADINECBCODV;
import mx.babel.bansefi.banksystem.base.webservices.consultalocalidad.EjecutarResult;
import mx.babel.bansefi.banksystem.base.webservices.consultalocalidad.ResponseBansefi;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConsultaLocalidadBackend extends BackEndBean {

    private static final long serialVersionUID = -2726786427432231781L;

    private static final Logger logger = LogManager.getLogger(ConsultaLocalidadBackend.class.getName());

    private static final String EMPTY = "";

    @Autowired
    ServicioWebUtils servicioWebUtils;

    @Autowired
    private LocalidadWrapper localidadWrapper;

    public DomicilioTipoBean ejecutarTRN(DomicilioTipoBean domicilio) {
        logger.debug("Entra al metodo ejecutarTRN");
        
        Ejecutar.ITRAGLOCALIDADCNSTRN contexto = this.initPeticion(domicilio);
        EjecutarResult respuesta = ejecutarWS(contexto);
        
        DomicilioTipoBean domicilioCompleto = domicilio;
        
        try{
        	super.verificaRespuesta(respuesta);
        }catch (ControlableException ce){
			if (ce.getRtncod() != RETURN_COD_SIN_DATOS){
				throw ce;
			}else{
				return domicilioCompleto;
			}
		}        
        
        if (verificaResponseBansefi(respuesta)) {
        	domicilioCompleto = getLocalidadResponse(respuesta.getResponseBansefi(),domicilio);
        }
        
        logger.debug("Sale del metodo ejecutarTRN");
        return domicilioCompleto;
    }

    /**
     * Función encargada de obtener el domicilio completo con los datos de localidad mediante
     * servicio web
     * 
     * @param response El objeto de reultado enviado por el servicio web.
     * @return El domicilio Completo
     */
    private DomicilioTipoBean getLocalidadResponse(ResponseBansefi response, DomicilioTipoBean domicilio){
    	DomicilioTipoBean domicilioCompleto = domicilio;       
        
        if (verificaRespuestaLocalidad(response)) {
            domicilioCompleto.setDatosFinalesCatGeo(localidadWrapper.wrappLocalidadBean(response.getOTRAGLOCALIDADCNSTRN().getTRAGLOCALIDADCNSEVTZ().getAGLOCALIDADV()));
            
            // Actualizamos el valor de los campos que se utilizan en el renderizado del componente de domicilio
            domicilioCompleto.getDatosFinalesCatGeo().setCodigoPostal(domicilioCompleto.getCodigoPostal());
            domicilioCompleto.getDatosFinalesCatGeo().setNumArGeo(String.valueOf(domicilioCompleto.getNumArGeo()));
            domicilioCompleto.setEstado(domicilioCompleto.getDatosFinalesCatGeo().getNombreComunidadAutonoma());        	
            domicilioCompleto.setPais(domicilioCompleto.getDatosFinalesCatGeo().getNombrePais());
            
            if(domicilioCompleto.getDatosFinalesCatGeo().getNombreProvincia()!=null && !domicilioCompleto.getDatosFinalesCatGeo().getNombreProvincia().isEmpty()){
            	domicilioCompleto.getDatosFinalesCatGeo().setMunicipioLocalidad
            	(domicilioCompleto.getDatosFinalesCatGeo().getNombreMunicipio().concat("/").
            			concat(domicilioCompleto.getDatosFinalesCatGeo().getNombreProvincia()));
            }
            
            // Duplicamos el bean al de municipios y codigo postal
            domicilioCompleto.setMunicipioCatGeo((CatalogoGeoBean)domicilioCompleto.getDatosFinalesCatGeo().clone());
            domicilioCompleto.setCodigoPostalCatGeo((CatalogoGeoBean)domicilioCompleto.getDatosFinalesCatGeo().clone());
        }
        return domicilioCompleto;
    }

    /**
     * Función que valida que la respuesta Bansefi contenga un objeto con los
     * datos de la entidad
     * 
     * @param response
     *            Respuesta Bansefi con datos de la entidad
     * @return <code>true</code> si la respuesta Bansefi contiene una entidad
     */
    private Boolean verificaRespuestaLocalidad(ResponseBansefi response) {
        Boolean noNulo = false;

        if (response != null
                && response.getOTRAGLOCALIDADCNSTRN() != null
                && response.getOTRAGLOCALIDADCNSTRN().getTRAGLOCALIDADCNSEVTZ() != null
                && response.getOTRAGLOCALIDADCNSTRN().getTRAGLOCALIDADCNSEVTZ().getAGLOCALIDADV() != null) {
            noNulo = true;
        }
        return noNulo;
    }

    /**
     * Función para inicializar los atributos del objeto de peticíon al servicio
     * web.
     * 
     * @param DomicilioBean domicilio sobre el que consultar los datos de localidad
     * @return Objeto de petición al web service
     */
    private Ejecutar.ITRAGLOCALIDADCNSTRN initPeticion(DomicilioBean domicilio) {
        logger.debug("Entra al metodo initPeticion");

        Ejecutar.ITRAGLOCALIDADCNSTRN contexto = new Ejecutar.ITRAGLOCALIDADCNSTRN();
        
        Ejecutar.ITRAGLOCALIDADCNSTRN.STDTRNIPARMV stdtrniparmv = new Ejecutar.ITRAGLOCALIDADCNSTRN.STDTRNIPARMV();
       
        TRAGLOCALIDADCNSEVTY traglocalidadcnsevty = new TRAGLOCALIDADCNSEVTY();
        AGARGEOP agargeop = new AGARGEOP();
        AGLOCALIDADINECBCODV aglocalidadinecbcodv = new AGLOCALIDADINECBCODV();

        
        super.initialize(stdtrniparmv);
        stdtrniparmv.setCODTX(CodTxConstants.COD_TX_TR_AG_LOCALIDAD_CNS_TRN);
        stdtrniparmv.setIDINTERNOTERMTN(super.getTerminal());
       
        // Seteamos los datos de busqueda
        if (StringUtils.isNotBlank(domicilio.getCodArGeo())) {
            agargeop.setCODARGEO(domicilio.getCodArGeo());
        } else {
            agargeop.setCODARGEO(ConsultaLocalidadBackend.EMPTY);
        }

        agargeop.setNUMARGEO(domicilio.getNumArGeo());   
        
        traglocalidadcnsevty.setAGARGEOP(agargeop);
        traglocalidadcnsevty.setAGLOCALIDADINECBCODV(aglocalidadinecbcodv);

        // Seteamos el contexto
        contexto.setSTDTRNIPARMV(stdtrniparmv);
        contexto.setTRAGLOCALIDADCNSEVTY(traglocalidadcnsevty);

        logger.debug("Sale del metodo initPeticion");

        return contexto;

    }

    /**
     * Función para invocar al servicio web y obtener su respuesta.
     * 
     * @param contexto
     *            Objeto de petición al servicio web
     * @return La respuesta del servicio web.
     */
    private EjecutarResult ejecutarWS(Ejecutar.ITRAGLOCALIDADCNSTRN contexto)
            throws NoControlableException {
        logger.debug("Entra al metodo ejecutarWS");
        EjecutarResult respuesta = null;
        Ejecutar ejecutar = new Ejecutar();
        ejecutar.setITRAGLOCALIDADCNSTRN(contexto);
        try {
            respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(ConsultaLocalidadServicio.class, contexto);
        } catch (NoControlableException e) {
            throw new NoControlableException(
                    "Error al invocar servicio web de consulta de localidad.", e);
        }
        logger.debug("Sale del metodo ejecutarWS");
        return respuesta;
    }

    /**
     * Función que valida que la respuesta del servidor no este vacía.
     * 
     * @param respuesta
     *            Objeto respuesta del servicio web
     * @return <code>true</code> si la respuesta no esta vacía.
     */
    private Boolean verificaResponseBansefi(EjecutarResult respuesta) {
        logger.debug("Entra al metodo verificaResponseBansefi");
        Boolean noNulo = false;
        if (respuesta != null && respuesta.getResponseBansefi() != null) {
            noNulo = true;
        }
        logger.debug("Sale del metodo verificaResponseBansefi con valor -> " + noNulo);
        return noNulo;
    }

}
