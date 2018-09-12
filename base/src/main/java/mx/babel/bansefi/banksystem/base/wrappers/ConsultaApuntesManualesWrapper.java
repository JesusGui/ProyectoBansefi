package mx.babel.bansefi.banksystem.base.wrappers;

import java.io.Serializable;

import mx.babel.bansefi.banksystem.base.beans.busqueda.ApunteManualBusquedaBean;
import mx.babel.bansefi.banksystem.base.webservices.consultaapuntesmanuales.ResponseBansefi;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConsultaApuntesManualesWrapper implements Serializable{

	private static final long serialVersionUID = -7322410094578605400L;
	
	@Autowired
	private DozerBeanMapper dozerBeanMapper;

	/**
	 * Funcion que convierte el objeto raiz de apuntes manuales y lo 
	 * convierte al objeto de aplicacion AppunteManualBean
	 * @param apunte
	 * @return
	 */
	public ApunteManualBusquedaBean wrappApunteManualBean(ResponseBansefi.OTRAMCONSAPNTELSTTRN.TRAMCONSAPNTELSTEVT.AMAPNTEMANUALE apunte){
		Mapper mapper = dozerBeanMapper;
		return mapper.map(apunte, ApunteManualBusquedaBean.class, "consultaApunteManual");
	}

}
