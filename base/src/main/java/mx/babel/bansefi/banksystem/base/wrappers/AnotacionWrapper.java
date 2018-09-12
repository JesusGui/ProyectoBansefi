package mx.babel.bansefi.banksystem.base.wrappers;

import java.io.Serializable;

import mx.babel.bansefi.banksystem.base.beans.domain.AnotacionBean;
import mx.babel.bansefi.banksystem.base.webservices.anotacionesCuenta.ResponseBansefi.OTRINFORAVISOSANTRNO.STDANAVMSJV.STDANAVMSJLS;
import mx.babel.bansefi.banksystem.base.webservices.consultaanotaciones.ResponseBansefi.OTRANCONSDATOSDPTRN.TRANCONSDATOSDPEVTZ;
import mx.babel.bansefi.banksystem.base.webservices.consultalistaanotaciones.ResponseBansefi.OTRANCONSANOTMTRNO.TRANCONSANOTMEVTZ.ANANTCNE;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Bean encargado de realizar mapeos entre respuestas de webservices hacia beans relacionados con anotaciones.
 * 
 * @author javier.martinnino
 *
 */
@Component
public class AnotacionWrapper implements Serializable {

	private static final long serialVersionUID = -762563883532086342L;
	
	/**
     * Asignación de bean de mapeo
     */
    @Autowired
    DozerBeanMapper dozerBeanMapper;
        
    /**
     * Función responsable de mapeo entre objeto bien y la entrada del webservice TR_CONSUL_AN_PANT_TRN 
     * 
     * 
     * @param anotacion Objeto del tipo TR_CONSUL_AN_PANT_TRN
     * @return AnotacionBean el bean de anotaciones con todos los datos completos
     */
    public AnotacionBean wrappConsultaDetalleAnotacion(TRANCONSDATOSDPEVTZ anotacion){
        Mapper mapper = dozerBeanMapper;
        return mapper.map(anotacion, AnotacionBean.class,"consultaDetalleAnotacion");
    }
        
    public AnotacionBean wrappBean(ANANTCNE anotacion){
    	Mapper mapper = dozerBeanMapper;
        return mapper.map(anotacion, AnotacionBean.class,"listadoAnotacion");
    }
    
    public AnotacionBean wrappBean(STDANAVMSJLS anotacion){
    	Mapper mapper = dozerBeanMapper;
    	return mapper.map(anotacion, AnotacionBean.class, "anotacionesComposite");
    }
}
