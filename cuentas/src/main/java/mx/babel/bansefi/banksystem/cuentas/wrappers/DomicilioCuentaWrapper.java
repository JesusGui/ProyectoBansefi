package mx.babel.bansefi.banksystem.cuentas.wrappers;

import mx.babel.bansefi.banksystem.cuentas.beans.DomicilioCuentaBean;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultadomicilioscuenta.ResponseBansefi.OTRCONSUDIRACTRNO.TRCONSUDIRACEVTZ.DATOSDIRPERV;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DomicilioCuentaWrapper {

	@Autowired
    DozerBeanMapper dozerBeanMapper;

	public DomicilioCuentaBean wrappBean(DATOSDIRPERV domicilio){
		Mapper mapper = dozerBeanMapper;
		return mapper.map(domicilio, DomicilioCuentaBean.class,"consultaDomiciliosCuenta");
	}
}
