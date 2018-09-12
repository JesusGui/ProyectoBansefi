package mx.babel.bansefi.banksystem.personas.wrappers;

import java.io.Serializable;

import mx.babel.bansefi.banksystem.base.beans.domain.ClientePersonaFisicaBean;
import mx.babel.bansefi.banksystem.base.beans.domain.CuentaClienteBean;
import mx.babel.bansefi.banksystem.base.webservices.consultacuentascliente.ResponseBansefi.OTRCONSULTARPPANT5TR.TRCONSULTARPPANT5EVT.RPPERSRLACE;
import mx.babel.bansefi.banksystem.personas.webservices.altapersona.Ejecutar.ITRPEALTAINDVTRNI.TRPEALTAINDVEVTY;
import mx.babel.bansefi.banksystem.personas.webservices.modificacionpersonafisica.Ejecutar.ITRPEMODIFINDVTRNI.TRPEMODIFINDVEVTY;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Bean encargado de realizar mapeos entre respuestas de webservices hacia beans relacionados a personas.
 * 
 * @author mario.montesdeoca
 *
 */
@Component
public class PersonasWrapper implements Serializable{

	private static final long serialVersionUID = -1119987123622836289L;
	
	/**
	 * Asignación de bean de mapeo
	 */
	@Autowired
	DozerBeanMapper dozerBeanMapper;

	/**
	 * Función responsable del mapeo entre respuesta de webservice TR_CONSULTA_RP_PANT_5_TRN 
	 * y CuentaBusquedaBean
	 * 
	 * @param cuenta objeto respuesta del webservice TR_CONS_DOMIC_PPAL_TRNO
	 * @return bean CuentaBusquedaBean
	 */
	public CuentaClienteBean wrappbean(RPPERSRLACE cuenta){
		Mapper mapper = dozerBeanMapper;
		return mapper.map(cuenta, CuentaClienteBean.class, "cuenta");
	}
	
	/**
	 * Función responsable de mapeo desde ClientePersonaFisicaBean a objetos de servicio y TR_PE_ALTA_INDV_TRN
	 * 
	 * @param cliente bean respuesta - ClientePersonaFisicaBean
	 * @return cliente objeto entrada del webservice TR_PE_ALTA_INDV_TRN
	 */
	public TRPEALTAINDVEVTY wrappBeanAltaCliente(ClientePersonaFisicaBean cliente){
		Mapper mapper = dozerBeanMapper;
		return mapper.map(cliente, TRPEALTAINDVEVTY.class,"altaCliente");
	}
	
	/**
	 * Función responsable de mapeo desde ClientePersonaFisicaBean a objetos de servicio y TR_PE_MODIF_INDV_TRN
	 * 
	 * @param cliente bean respuesta - ClientePersonaFisicaBean
	 * @return cliente objeto entrada del webservice TR_PE_MODIF_INDV_TRN
	 */
	public TRPEMODIFINDVEVTY wrappBeanModificacionCliente(ClientePersonaFisicaBean cliente){
		Mapper mapper = dozerBeanMapper;
		return mapper.map(cliente, TRPEMODIFINDVEVTY.class,"modificacionCliente");
	}
	
}
