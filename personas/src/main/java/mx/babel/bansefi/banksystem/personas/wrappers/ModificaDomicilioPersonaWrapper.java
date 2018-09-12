package mx.babel.bansefi.banksystem.personas.wrappers;

import java.io.Serializable;

import mx.babel.bansefi.banksystem.base.beans.domain.DomicilioTipoBean;
import mx.babel.bansefi.banksystem.base.webservices.modificadomicilio.Ejecutar.ITRPEMODIDOMICTRNI.TRPEMODIDOMICEVTY;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ModificaDomicilioPersonaWrapper implements Serializable {

	private static final long serialVersionUID = -1147078075687377969L;

	@Autowired
	DozerBeanMapper dozerBeanMapper;

	public TRPEMODIDOMICEVTY wrappModificaDomicilio(
			DomicilioTipoBean domicilioBean) {
		Mapper mapper = dozerBeanMapper;
		return mapper.map(domicilioBean, TRPEMODIDOMICEVTY.class,
				"modificaDomicilioPersona");
	}

}
