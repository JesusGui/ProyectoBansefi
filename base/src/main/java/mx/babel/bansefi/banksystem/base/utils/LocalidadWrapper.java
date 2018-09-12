package mx.babel.bansefi.banksystem.base.utils;

import mx.babel.arq.catalogo.beans.CatalogoGeoBean;
import mx.babel.bansefi.banksystem.base.webservices.consultalocalidad.ResponseBansefi.OTRAGLOCALIDADCNSTRN.TRAGLOCALIDADCNSEVTZ.AGLOCALIDADV;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Bean encargado de realizar mapeos entre respuestas de webservices hacia beans relacionados a localidad.
 * 
 * @author mario.montesdeoca
 *
 */
@Component
public class LocalidadWrapper {

    /**
     * Asignación de bean de mapeo
     */
    @Autowired
    DozerBeanMapper dozerBeanMapper;
    
    
    /**
     * Función responsable de  mapeo entre respuesta de webservice ConsultaLocalidadServicio
     * y CatalogoGeoBean 
     * 
     * @param localidad objeto respuesta del webservice ConsultaLocalidadServicio
     * @return bean LocalidadBean
     */
    public CatalogoGeoBean wrappLocalidadBean(AGLOCALIDADV localidad){
        Mapper mapper = dozerBeanMapper;
        return mapper.map(localidad, CatalogoGeoBean.class,"localidad");
    }
}
