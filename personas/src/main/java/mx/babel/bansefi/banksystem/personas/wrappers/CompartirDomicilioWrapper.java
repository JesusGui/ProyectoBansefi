package mx.babel.bansefi.banksystem.personas.wrappers;

import java.io.Serializable;

import mx.babel.bansefi.banksystem.personas.beans.CompartirDomicilioBean;
import mx.babel.bansefi.banksystem.personas.webservices.compartirdomicilio.Ejecutar.ITRPECOMPARTIRDOMICTR.TRPECOMPARTIRDOMICEVT;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Clase Wrapper para convertir los valores de CompartirDomicilioBean a su clase
 * correspondiente de la TRN
 * 
 * @author manuel.nieto
 * 
 */
@Component
public class CompartirDomicilioWrapper implements Serializable {

	@Autowired
	DozerBeanMapper dozerBeanMapper;

	/**
	 * Funcion que recibe prepara el objeto necesario para la ejecucion
	 * de la TRN de compartir domicilio
	 * @param compartirDomicilioBean
	 * @return
	 */
	public TRPECOMPARTIRDOMICEVT wrappCompartirDomicilio(
			CompartirDomicilioBean compartirDomicilioBean) {
		Mapper mapper = dozerBeanMapper;
		return mapper.map(compartirDomicilioBean, TRPECOMPARTIRDOMICEVT.class,
				"compartirDomicilio");
	}
}
