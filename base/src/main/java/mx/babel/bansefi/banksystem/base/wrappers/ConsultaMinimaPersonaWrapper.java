package mx.babel.bansefi.banksystem.base.wrappers;

import java.io.Serializable;

import mx.babel.bansefi.banksystem.base.beans.ConsultaMinimaPersonaBean;
import mx.babel.bansefi.banksystem.base.webservices.validaidinterna.ResponseBansefi.OTRCONSMINIMAPERSONAT.TRCONSMINIMAPERSONAEV;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConsultaMinimaPersonaWrapper implements Serializable{

	private static final long serialVersionUID = 2236992931684242041L;

	@Autowired
	DozerBeanMapper dozerBeanMapper;
	
	public ConsultaMinimaPersonaBean wrappConsultaMinimaPersona(TRCONSMINIMAPERSONAEV cliente){
		Mapper mapper = dozerBeanMapper;
		return mapper.map(cliente, ConsultaMinimaPersonaBean.class,"consultaMinimaPersona");
	}
}
