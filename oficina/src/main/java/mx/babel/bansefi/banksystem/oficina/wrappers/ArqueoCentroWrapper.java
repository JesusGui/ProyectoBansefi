package mx.babel.bansefi.banksystem.oficina.wrappers;

import mx.babel.bansefi.banksystem.oficina.beans.ArqueoCentroBean;
import mx.babel.bansefi.banksystem.oficina.webservices.arqueocentro.Ejecutar.ITRARQUEOCAJAEXTRNI.TRARQUEOOFCNAEXEVTY;
import mx.babel.bansefi.banksystem.oficina.webservices.consultaresultadoarqueo.ResponseBansefi.OTROBTEDATOSARQUEOTRN.TROBTEDATOSARQUEOEVT;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Clase encargada de realizar los mapeos para arqueos de centro
 * @author mario.montesdeoca
 *
 */
@Component
public class ArqueoCentroWrapper {
	
	@Autowired
	DozerBeanMapper dozerBeanMapper;

	/**
	 * Wrapper para consulta de arqueo de centro.
	 * @param TROBTEDATOSARQUEOEVTZ con datos de la respuesta del ws
	 * @param ArqueoCentroBean bean de arqueo de centro
	 */
	public void wrappBean(TROBTEDATOSARQUEOEVT response, ArqueoCentroBean arqueo){
		Mapper mapper = dozerBeanMapper;
		mapper.map(response, arqueo, "consultaArqueoCentro");
	}
	
	/**
	 * Wrapper para el arqueo de centro
	 * @param arqueo bean de arqueo de centro
	 * @param peticion bean de peticiòn al ws
	 */
	public void wrappBean(ArqueoCentroBean arqueo, TRARQUEOOFCNAEXEVTY peticion){
		Mapper mapper = dozerBeanMapper;
		mapper.map(arqueo,peticion,"peticionArqueoCentro");
	}
}
