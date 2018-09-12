package mx.babel.bansefi.banksystem.oficina.wrappers;


import mx.babel.bansefi.banksystem.oficina.beans.TraspasoBean;
import mx.babel.bansefi.banksystem.oficina.webservices.consultatraspasos.ResponseBansefi.OVVVTRASPASOSLISTMTRN.VVVTRASPASOSLISTMEVTZ.VVVTRASPASOST;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Clase encargada de realizar los mapeos para arqueos de puesto
 * @author mario.montesdeoca
 *
 */
@Component
public class ArqueoPuestoWrapper {

	@Autowired
	DozerBeanMapper dozerBeanMapper;

	/**
	 * Wrapper para consulta de traspasos.
	 * @param VVVTRASPASOST con datos de la respuesta del ws
	 * @param TraspasoBean bean de traspasos entre puesto
	 */
	public TraspasoBean wrappBean(VVVTRASPASOST response){
		Mapper mapper = dozerBeanMapper;
		return mapper.map(response, TraspasoBean.class, "consultaTraspaso");
	}
}
