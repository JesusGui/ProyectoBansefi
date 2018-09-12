package mx.babel.bansefi.banksystem.personas.wrappers;

import java.io.Serializable;

import mx.babel.bansefi.banksystem.base.beans.AltaDomicilioRespuestaBean;
import mx.babel.bansefi.banksystem.base.beans.domain.DomicilioTipoBean;
import mx.babel.bansefi.banksystem.base.webservices.altadomicilio.Ejecutar.ITRPEALTADOMICTRNI.TRPEALTADOMICEVTY;
import mx.babel.bansefi.banksystem.base.webservices.altadomicilio.ResponseBansefi.OTRPEALTADOMICTRNO.TRPEALTADOMICEVTZ.DRDOMICP;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Clase que wrappea los datos para realizar el alta de un nuevo domiciio
 * @author manuel.nieto
 *
 */
@Component
public class AltaDomicilioPersonaWrapper implements Serializable{

	@Autowired
    DozerBeanMapper dozerBeanMapper;
	
	private static final long serialVersionUID = -7153144278730974472L;

	/**
	 * Funcion que convierte el objeto DomicilioTipoBean en TRPEALTADOMICEVTY
	 * @param domicilio
	 * @return TRPEALTADOMICEVTY
	 */
	public TRPEALTADOMICEVTY wrappAltaDomicilio(DomicilioTipoBean domicilio){
		Mapper mapper = dozerBeanMapper;
        return mapper.map(domicilio, TRPEALTADOMICEVTY.class,"altaDomicilioPersona");		
	}
	
	public AltaDomicilioRespuestaBean wrappRespuestaAlta(DRDOMICP respuesta){
		Mapper mapper = dozerBeanMapper;
		return mapper.map(respuesta, AltaDomicilioRespuestaBean.class, "respuestaAltaDomicilio");
	}
}
