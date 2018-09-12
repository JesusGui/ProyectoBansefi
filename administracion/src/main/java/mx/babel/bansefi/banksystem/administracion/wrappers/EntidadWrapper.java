package mx.babel.bansefi.banksystem.administracion.wrappers;

import java.io.Serializable;

import mx.babel.bansefi.banksystem.administracion.beans.EntidadBean;
import mx.babel.bansefi.banksystem.administracion.webservices.detalleentidad.ResponseBansefi.OTRCONENTIDADTRNO.TRCONENTIDADEVTZ;
import mx.babel.bansefi.banksystem.administracion.webservices.modificacionentidad.Ejecutar.ITRMODIENTIDADTRNI.TRMODIENTIDADEVTY;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * Bean encargado de realizar mapeos entre respuestas de webservices hacia beans relacionados a entidades.
 * 
 * @author luis.gonzalez
 *
 */
@Component
public class EntidadWrapper implements Serializable{

    private static final long serialVersionUID = -2993208361169733529L;
	
    /**
     * Asignación de bean de mapeo
     */
    @Autowired
    DozerBeanMapper dozerBeanMapper;
    
    /**
     * Función responsable de  mapeo entre respuesta de webservice TR_CON_ENTIDAD_TRN
     * y EntidadBean
     * 
     * @param entidad objeto respuesta del webservice TR_CON_ENTIDAD_TRN
     * @return bean EntidadBean
     */
    public EntidadBean wrappBean(TRCONENTIDADEVTZ entidad){
        Mapper mapper = dozerBeanMapper;
        return mapper.map(entidad, EntidadBean.class,"entidad");
    }
    
    /**
     * Función responsable de  mapeo entre respuesta de webservice TR_CON_ENTIDAD_TRN
     * y EntidadBean
     * 
     * @param entidad objeto respuesta del webservice TR_CON_ENTIDAD_TRN
     * @return bean EntidadBean
     */
    public TRMODIENTIDADEVTY wrappBeanModificarEntidad(EntidadBean entidad){
        Mapper mapper = dozerBeanMapper;
        return mapper.map(entidad, TRMODIENTIDADEVTY.class,"modificaEntidad");
    }
    
}
