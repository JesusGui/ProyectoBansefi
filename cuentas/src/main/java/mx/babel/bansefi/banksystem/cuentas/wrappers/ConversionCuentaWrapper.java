package mx.babel.bansefi.banksystem.cuentas.wrappers;


import mx.babel.bansefi.banksystem.cuentas.beans.ConversionCuentaBean;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

public class ConversionCuentaWrapper {

	

	@Autowired
	DozerBeanMapper dozerBeanMapper;

	public <T> ConversionCuentaBean wrappBean(T documento) {
		
		Mapper mapper = dozerBeanMapper;
		return mapper.map(documento,ConversionCuentaBean.class,
		"conversioncuenta");
	}
	
	
}
