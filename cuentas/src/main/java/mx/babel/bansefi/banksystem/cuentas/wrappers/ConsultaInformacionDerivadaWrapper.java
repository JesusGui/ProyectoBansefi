package mx.babel.bansefi.banksystem.cuentas.wrappers;

import mx.babel.bansefi.banksystem.cuentas.beans.InformacionDerivadaBean;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultainformacionderivada.ResponseBansefi.OTRCONSUDERIVADASACTR.TRCONSUDERIVADASACEVT.ACINFDRVDE;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultainformacionderivada.ResponseBansefi.OTRCONSUDERIVADASACTR.TRCONSUDERIVADASACEVT.LIQTAEAPERV.LIQTAEAPERLSTV.LIQTRAMOINTV.LIQTRAMOINTLSTV;
import mx.babel.bansefi.banksystem.cuentas.beans.InformacionNumericaBean;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Wrapper utilizado para realizar el mapeo entre el WS.
 * y la clase InformacionDerivadaBean 
 * @author alejandro.villegas
 *
 */

@Component
public class ConsultaInformacionDerivadaWrapper {
	
	@Autowired
	DozerBeanMapper dozerMapper;
	
	/**
	 * Funcion para realizar el mapeo entre el resultado del WS
	 * y la informacion derivada del cliente.
	 * @param response
	 * @return Informacion derivada del cliente
	 */
	public InformacionDerivadaBean wrappInformacionDerivada(final ACINFDRVDE acinfdrvde){
		final Mapper mapper = dozerMapper;
		return mapper.map(acinfdrvde, InformacionDerivadaBean.class, "resultadoInformacionDerivada");		
	}
	
	public InformacionNumericaBean wrappInformacionNumerica(final LIQTRAMOINTLSTV liqtramointlstv){
		final Mapper mapper = dozerMapper;
		return mapper.map(liqtramointlstv, InformacionNumericaBean.class, "resultadoInformacionNumerica");
	}

}
