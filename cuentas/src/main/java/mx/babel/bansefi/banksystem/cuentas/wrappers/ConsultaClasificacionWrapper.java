package mx.babel.bansefi.banksystem.cuentas.wrappers;

import mx.babel.bansefi.banksystem.base.webservices.consultadatoscuenta.ResponseBansefi.OTRCONSUDATOSACTRNO.TRCONSUDATOSACEVTZ;
import mx.babel.bansefi.banksystem.base.webservices.consultadatoscuenta.ResponseBansefi.OTRCONSUDATOSACTRNO.TRCONSUDATOSACEVTZ.ACACE;
import mx.babel.bansefi.banksystem.cuentas.beans.ClasificacionBean;



import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConsultaClasificacionWrapper {
	
	@Autowired
	DozerBeanMapper dozerBeanMapper;
	
/*	public ACACE wrappConsultaClasificacionCuenta(ClasificacionBean clasificacionBean){
		Mapper mapper = dozerBeanMapper;
		return mapper.map(clasificacionBean, ACACE.class,"consultaClasificacion");
		
	}*/
	
	public ClasificacionBean wrappConsultaClasificacionCuenta(TRCONSUDATOSACEVTZ resultado ){
		Mapper mapper = dozerBeanMapper;
		return mapper.map(resultado, ClasificacionBean.class,"resultadoClasificacionBean");
		
	}
	
	

}
