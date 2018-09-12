package mx.babel.bansefi.banksystem.base.wrappers;

import mx.babel.bansefi.banksystem.base.beans.domain.relacioncuenta.RelacionadoBean;
import mx.babel.bansefi.banksystem.base.webservices.consultarelacionpersonascuenta.ResponseBansefi.OTRCONSULTARPPANTTRN.TRCONSULTARPPANTEVTZ.RPPERSRLACE;
import mx.babel.bansefi.banksystem.base.webservices.consultarelacionpersonascuenta.ResponseBansefi.OTRCONSULTARPPANTTRN.TRCONSULTARPPANTEVTZ.RPPERSV;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConsultaRelacionesCuentaWrapper {

	@Autowired
	DozerBeanMapper dozerBeanMapper;
	
	
	/**
	 * Wrapper para respuesta de ws de relaciones de persona-cuenta.
	 * @param relacion objeto respuesta de relaciones persona-cuenta.
	 * @return Bean Relación de persona-cuenta.
	 */
	public RelacionadoBean wrappBean(RPPERSRLACE relacion, RPPERSV persona){
		Mapper mapper = dozerBeanMapper;
		RelacionadoBean relacionadoBean = mapper.map(relacion, RelacionadoBean.class,"relacionPersonaCuenta");
		mapper.map(persona, relacionadoBean,"relacionPersonaCuentaCliente");
		return relacionadoBean;
	}

}