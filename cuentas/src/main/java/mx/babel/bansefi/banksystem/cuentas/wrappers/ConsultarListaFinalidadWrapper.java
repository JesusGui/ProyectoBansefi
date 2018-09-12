package mx.babel.bansefi.banksystem.cuentas.wrappers;

import mx.babel.bansefi.banksystem.cuentas.beans.FinalidadBean;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultarlistafinalidad.ResponseBansefi.OTRLISTARLTVFINTRNO.TRLISTARLTVFINEVTZ.TRLISTARLTVFINEVTV;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConsultarListaFinalidadWrapper {
	@Autowired
	DozerBeanMapper dozerMapper;
	
	public FinalidadBean wrappListaFinalidad(TRLISTARLTVFINEVTV response){
		final Mapper mapper = dozerMapper;
		return mapper.map(response, FinalidadBean.class,"resultadoListaFinalidad");
	}

}
