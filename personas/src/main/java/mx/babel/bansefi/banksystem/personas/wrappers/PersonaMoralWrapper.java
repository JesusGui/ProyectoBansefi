package mx.babel.bansefi.banksystem.personas.wrappers;

import java.io.Serializable;

import mx.babel.bansefi.banksystem.base.beans.domain.ClientePersonaMoralBean;
import mx.babel.bansefi.banksystem.base.webservices.modificacionpersonamoral.Ejecutar.ITRPEMODIFORGTRNI.TRPEMODIFORGEVTY;
import mx.babel.bansefi.banksystem.personas.webservices.altaperfiltransaccionalmoral.Ejecutar.IPEALTAORGPERFTRANTR.PEALTAORGPERFTRANEVT;
import mx.babel.bansefi.banksystem.personas.webservices.altapersonamoral.Ejecutar.ITRPEALTAORGTRNI.TRPEALTAORGEVTY;
import mx.babel.bansefi.banksystem.personas.webservices.modificacionperfiltransaccionalmoral.Ejecutar.IPEMODIORGPERFTRANTR.PEMODIORGPERFTRANEVT;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 
 * @author alejandro.perez
 *
 */
@Component
public class PersonaMoralWrapper implements Serializable{

	private static final long serialVersionUID = -771799116125476125L;
	
	/**
	 * Asignación de bean de mapeo
	 */
	@Autowired
	DozerBeanMapper dozerBeanMapper;
	
	/**
	 * 
	 * @param personaMoralBean
	 * @return
	 */
	public TRPEALTAORGEVTY wrappBeanAltaPersonaMoral (ClientePersonaMoralBean personaMoralBean) {
		Mapper mapper = dozerBeanMapper;
		return mapper.map(personaMoralBean, TRPEALTAORGEVTY.class, "altaPersonaMoral");
	}
	
	/**
	 * 
	 * @param personaMoralBean
	 * @return
	 */
	public TRPEMODIFORGEVTY wrappModificarPersonaMoral(ClientePersonaMoralBean personaMoralBean) {
		Mapper mapper = dozerBeanMapper;
		return mapper.map(personaMoralBean, TRPEMODIFORGEVTY.class, "modificarPersonaMoral");
	}
	
	/**
	 * 
	 * @param personaMoralBean
	 * @return
	 */
	public PEALTAORGPERFTRANEVT wrappAltaPerfilTransaccional (ClientePersonaMoralBean personaMoralBean) {
		Mapper mapper = dozerBeanMapper;
		return mapper.map(personaMoralBean, PEALTAORGPERFTRANEVT.class, "altaPerfilTransaccionalmoral");
	}
	
	/**
	 * 
	 * @param personaMoralBean
	 * @return
	 */
	public PEMODIORGPERFTRANEVT wrappModificarPerfilTransaccionalMoral (ClientePersonaMoralBean personaMoralBean) {
		Mapper mapper = dozerBeanMapper;
		return mapper.map(personaMoralBean, PEMODIORGPERFTRANEVT.class, "modificarPerfilTransaccionalmoral");
	}
	    
	
}
