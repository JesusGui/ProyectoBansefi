package mx.babel.bansefi.banksystem.personas.wrappers;

import java.io.Serializable;

import mx.babel.bansefi.banksystem.base.beans.domain.DomicilioCompartidoBean;
import mx.babel.bansefi.banksystem.personas.webservices.consultadomiciliocompartido.ResponseBansefi.OTRPECOMPLACNSTRNO.TRPECOMPLACNSEVTZ.TRPECOMPLACNSEVTV;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConsultaDomiciliosCompartidosWrapper implements Serializable{

	private static final long serialVersionUID = -1807982021369522717L;
	
	@Autowired
    DozerBeanMapper dozerBeanMapper;

	public DomicilioCompartidoBean wrappRespuestaConsulta(TRPECOMPLACNSEVTV respuesta){
		Mapper mapper = dozerBeanMapper;
        return mapper.map(respuesta, DomicilioCompartidoBean.class,"consultaDomiciliosCompartidos");		
	}
	
}
