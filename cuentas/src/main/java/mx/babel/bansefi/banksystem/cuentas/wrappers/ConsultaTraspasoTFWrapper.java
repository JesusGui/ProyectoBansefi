package mx.babel.bansefi.banksystem.cuentas.wrappers;

import java.io.Serializable;

import mx.babel.bansefi.banksystem.cuentas.beans.TraspasoTFBean;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultatraspasotf.ResponseBansefi.OTRPETCNOBTETRFRCIATR.TRPETCNOBTETRFRCIAEVT;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 
 * @author manuel.nieto
 *
 */
@Component
public class ConsultaTraspasoTFWrapper implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private DozerBeanMapper dozerBeanMapper;
	
	public TraspasoTFBean wrappRespuestaConsultaTF(TRPETCNOBTETRFRCIAEVT consultaTraspaso){
		Mapper mapper = dozerBeanMapper;
		return mapper.map(consultaTraspaso,  TraspasoTFBean.class, "consultaTraspasoTF");
	}

}
