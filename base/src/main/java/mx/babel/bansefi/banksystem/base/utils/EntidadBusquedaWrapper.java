package mx.babel.bansefi.banksystem.base.utils;

import mx.babel.bansefi.banksystem.base.beans.busqueda.EntidadBusquedaBean;
import mx.babel.bansefi.banksystem.base.webservices.consultalistaentidades.ResponseBansefi.OTRLISTAENTIDADES2TRN.TRLISTAENTIDADESEVTZ.CRENTIDADE;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * Bean encargado de realizar mapeos entre respuestas de webservices hacia beans relacionados a entidades.
 * 
 * @author mario.montesdeoca
 *
 */
@Component
public class EntidadBusquedaWrapper {

    /**
     * Asignación de bean de mapeo
     */
    @Autowired
    DozerBeanMapper dozerBeanMapper;
    
    /**
     * Función responsable de  mapeo entre respuesta de webservice ConsultaListaEntidadesServicio
     * y EntidadBusquedaBean
     * 
     * @param entidad objeto respuesta del webservice ConsultaListaEntidadesServicio
     * @return bean EntidadBusquedaBean
     */
    public EntidadBusquedaBean wrappEntidadBusquedaBean(CRENTIDADE entidadBusqueda){
        Mapper mapper = dozerBeanMapper;
        return mapper.map(entidadBusqueda, EntidadBusquedaBean.class,"entidadBusqueda");
    }
    
}
