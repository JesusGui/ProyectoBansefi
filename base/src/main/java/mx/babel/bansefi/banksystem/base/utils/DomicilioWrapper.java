package mx.babel.bansefi.banksystem.base.utils;

import mx.babel.bansefi.banksystem.base.beans.domain.DomicilioBean;
import mx.babel.bansefi.banksystem.base.beans.domain.DomicilioTipoBean;
import mx.babel.bansefi.banksystem.base.webservices.consultadomicilio.ResponseBansefi.OTRPECONSDOMICTRNO.TRPECONSDOMICEVTZ;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Bean encargado de realizar mapeos entre respuestas de webservices hacia beans relacionados a domicilio.
 * 
 * @author luis.gonzalez
 *
 */
@Component
public class DomicilioWrapper {

    /**
     * Asignación de bean de mapeo
     */
    @Autowired
    DozerBeanMapper dozerBeanMapper;
    
    /**
     * Función responsable de  mapeo entre respuesta de webservice ConsultaDomicilioServicio
     * y DomicilioBean
     * 
     * @param domicilio objeto respuesta del webservice ConsultaDomicilioServicio
     * @return bean DomicilioBean
     */
    public DomicilioTipoBean wrappDomicilioBean(TRPECONSDOMICEVTZ domicilio){
        Mapper mapper = dozerBeanMapper;
        return mapper.map(domicilio, DomicilioTipoBean.class,"domicilio");
    }
    
    public void wrappDomicilio(DomicilioBean domicilioBeanDest, DomicilioBean domicilioBeanSource){
    	 Mapper mapper = dozerBeanMapper;
    	 mapper.map(domicilioBeanSource,domicilioBeanDest,"domicilioTipoBean");
    }
    
}
