package mx.babel.bansefi.banksystem.administracion.wrappers;

import java.io.Serializable;

import mx.babel.bansefi.banksystem.administracion.webservices.altaempleado.Ejecutar.ITRALTAEMPLTRNI.TRALTAEMPLEVTY;
import mx.babel.bansefi.banksystem.administracion.webservices.modificacionempleado.Ejecutar.ITRMODIFEMPLTRNI.TRMODIFEMPLEVTY;
import mx.babel.bansefi.banksystem.base.beans.domain.EmpleadoBean;

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
public class EmpleadosWrapper implements Serializable{

	private static final long serialVersionUID = 974702449090371195L;
	/**
	 * Asignación de bean de mapeo
	 */
	@Autowired
	DozerBeanMapper dozerBeanMapper;
	
	/**
	 * Función responsable de  mapeo entre respuesta de webservice TR_MODIF_EMPL_TRN 
	 * y EmpleadoBean
	 * 
	 * @param cliente objeto respuesta del webservice TR_MODIF_EMPL_TRN
	 * @return bean EmpleadoBean
	 */
	public TRMODIFEMPLEVTY wrappBeanModificacionEmpleado(EmpleadoBean empleado){
		Mapper mapper = dozerBeanMapper;
		return mapper.map(empleado, TRMODIFEMPLEVTY.class,"modificacionEmpleado");
	}
	
	
	/**
	 * Función responsable de  mapeo entre respuesta de webservice TR_ALTA_EMPL_TRN 
	 * y EmpleadoBean
	 * 
	 * @param cliente objeto respuesta del webservice TR_ALTA_EMPL_TRN
	 * @return bean EmpleadoBean
	 */
	public TRALTAEMPLEVTY wrappBeanAltaEmpleado(EmpleadoBean empleado){
		Mapper mapper = dozerBeanMapper;
		return mapper.map(empleado, TRALTAEMPLEVTY.class,"altaEmpleado");
	}
}
