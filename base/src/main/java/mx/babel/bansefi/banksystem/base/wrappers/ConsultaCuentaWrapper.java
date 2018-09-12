package mx.babel.bansefi.banksystem.base.wrappers;

import mx.babel.bansefi.banksystem.base.beans.domain.CuentaBean;
import mx.babel.bansefi.banksystem.base.beans.domain.StatusCuentaBean;
import mx.babel.bansefi.banksystem.base.webservices.consultacuenta.ResponseBansefi.OTRCONSULTAACUERDOTRN.TRCONSULTAACUERDOEVTZ;
import mx.babel.bansefi.banksystem.base.webservices.consultadatoscuenta.ResponseBansefi.OTRCONSUDATOSACTRNO.TRCONSUDATOSACEVTZ;
import mx.babel.bansefi.banksystem.base.webservices.consultastatuscuenta.ResponseBansefi.OTRCONSULDTALACCTETR.TRCONSULDTALACCTEEVT.PEINDVDTRLACV;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Clase encargada de realizar los mapeos entre el ConsultaCuentaServicio y el
 * CuentaBean.
 * 
 * @author omar.marquez
 * 
 */

@Component
public class ConsultaCuentaWrapper {

	@Autowired
	DozerBeanMapper dozerBeanMapper;

	/**
	 * Método encargado de mapear los atributos de la respuesta en un
	 * CuentaBean.
	 * 
	 * @param resultado
	 * @return CuentaBean
	 */
	public CuentaBean wrappConsultaCuenta(TRCONSULTAACUERDOEVTZ resultado) {
		Mapper mapper = dozerBeanMapper;
		return mapper.map(resultado, CuentaBean.class, "resultadoConsultaDatosCuenta");
	}
	
	/**
	 * Método encargado de mapear los atributos de un objeto tipo TRCONSUDATOSACEVTZ al
	 * tipo CuentaBean.
	 * 
	 * @param TRCONSUDATOSACEVTZ resultado
	 * @return CuentaBean
	 */
	public CuentaBean wrappConsultaDatosCuenta(TRCONSUDATOSACEVTZ resultado) {
		Mapper mapper = dozerBeanMapper;
		return mapper.map(resultado, CuentaBean.class, "consultaDatosCuenta");
	}
	
	/**
	 * MÃ©todo encargado de mapear los atributos de un objeto tipo PEINDVDTRLACV al
	 * tipo StatusCuentaBean.
	 * 
	 * @param PEINDVDTRLACV resultado
	 * @return StatusCuentaBean
	 */
	public StatusCuentaBean wrappConsultaStatusCuenta(PEINDVDTRLACV resultado) {
		Mapper mapper = dozerBeanMapper;
		return mapper.map(resultado, StatusCuentaBean.class, "consultaStatusCuenta");
	}
	
}