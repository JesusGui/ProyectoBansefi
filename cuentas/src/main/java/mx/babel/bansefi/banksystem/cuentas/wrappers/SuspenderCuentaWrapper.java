package mx.babel.bansefi.banksystem.cuentas.wrappers;

import java.io.Serializable;

import mx.babel.bansefi.banksystem.cuentas.beans.SuspenderCuentaBean;
import mx.babel.bansefi.banksystem.cuentas.webservices.suspendercuenta.Ejecutar.ITRSUSPENDERACTRNI.TRSUSPENDERACEVTY;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Clase wrapper para ajustar la salida y entrada para la funcionalidad
 * de suspension de cuentas
 * @author manuel.nieto
 *
 */
@Component
public class SuspenderCuentaWrapper implements Serializable{
	
	@Autowired
	private DozerBeanMapper dozerBeanMapper;
	
	/**
	 * Funcion que hace el mapeo de los datos para la suspension de la cuenta
	 * @param suspenderCuentaBean
	 * @return
	 */
	public TRSUSPENDERACEVTY wrappSupenderCuenta(SuspenderCuentaBean suspenderCuentaBean){
		Mapper mapper = dozerBeanMapper;
		return mapper.map(suspenderCuentaBean, TRSUSPENDERACEVTY.class, "suspenderCuenta");
	}
}
