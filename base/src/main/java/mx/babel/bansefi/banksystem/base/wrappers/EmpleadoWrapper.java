package mx.babel.bansefi.banksystem.base.wrappers;

import java.io.Serializable;

import mx.babel.bansefi.banksystem.base.beans.domain.EmpleadoBean;
import mx.babel.bansefi.banksystem.base.webservices.consultaempleado.ResponseBansefi.OTRCONSEMPLTRNO.TRCONSEMPLEVTZ;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Bean encargado de realizar mapeos entre respuestas de webservices hacia beans relacionados con empleados.
 * 
 * @author javier.martinnino
 *
 */
@Component
public class EmpleadoWrapper implements Serializable{

	private static final long serialVersionUID = 8867256398313445041L;

	/**
	 * Asignación de bean de mapeo
	 */
	@Autowired
	DozerBeanMapper dozerBeanMapper;
	
	/**
	 * Función responsable de  mapeo entre respuesta de webservice TR_CONS_EMPL_TRN 
	 * y EmpleadoBean
	 * 
	 * @param cliente objeto respuesta del webservice TR_CONS_EMPL_TRN
	 * @return bean EmpleadoBean
	 */
	public EmpleadoBean wrappBeanConsultaEmpleado(TRCONSEMPLEVTZ empleado){
		Mapper mapper = dozerBeanMapper;
		return mapper.map(empleado, EmpleadoBean.class,"consultaEmpleado");
	}
			
}
