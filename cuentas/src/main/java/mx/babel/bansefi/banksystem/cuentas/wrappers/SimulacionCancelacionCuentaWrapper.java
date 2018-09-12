package mx.babel.bansefi.banksystem.cuentas.wrappers;

import java.io.Serializable;

import mx.babel.bansefi.banksystem.base.beans.domain.CuentaBean;
import mx.babel.bansefi.banksystem.cuentas.beans.SimulacionCancelacionCuentaBean;
import mx.babel.bansefi.banksystem.cuentas.webservices.simulacioncancelacioncuenta.Ejecutar.TRSIMULACTRNI.TRSIMULACEVTY;
import mx.babel.bansefi.banksystem.cuentas.webservices.simulacioncancelacioncuenta.ResponseBansefi.TRSIMULACTRNO.TRSIMULACEVTZ.TRSIMULCANCV;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SimulacionCancelacionCuentaWrapper implements Serializable {

	private static final long serialVersionUID = 7549781254296387523L;

	@Autowired
	private DozerBeanMapper dozerBeanMapper;

	/**
	 * Funcion que convierte la respuesta de la simulacion de 
	 * cancelacion de una cuenta a un objeto del sistema.
	 * @param respuesta
	 * @return
	 */
	public SimulacionCancelacionCuentaBean wrappRespuestaSimulacion(
			TRSIMULCANCV respuesta) {
		Mapper mapper = dozerBeanMapper;
		return mapper.map(respuesta, SimulacionCancelacionCuentaBean.class,
				"wrappRespuestaSimulacionCancelacionCuenta");
	}

	/**
	 * Funcion que prepara el objeto para ejecutar la consulta de simulacion
	 * de cancelacion de cuenta.
	 * @param cuentaBean
	 * @return
	 */
	public TRSIMULACEVTY wrappConsultaSimulacion(CuentaBean cuentaBean) {
		Mapper mapper = dozerBeanMapper;
		return mapper.map(cuentaBean, TRSIMULACEVTY.class,
				"wrappConsultaSimulacionCancelacionCuenta");
	}
}
