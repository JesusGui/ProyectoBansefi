package mx.babel.bansefi.banksystem.cuentas.wrappers;


import mx.babel.bansefi.banksystem.cuentas.beans.CentroAsociadoBean;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * @author Natalio Iván Mendoza Sánchez &lt;nmendoza@uanlmexico.mx&gt;
 * @category Wrapper
 */
@Component
public class ConsultaCentroAsociadoWrapper {

	@Autowired
	DozerBeanMapper dozerBeanMapper;

	public <T> CentroAsociadoBean wrappBean(T documento) {
		
		Mapper mapper = dozerBeanMapper;
		return mapper.map(documento,CentroAsociadoBean.class,
		"consultaCentroAsociado");
	}
	
	
	
}
