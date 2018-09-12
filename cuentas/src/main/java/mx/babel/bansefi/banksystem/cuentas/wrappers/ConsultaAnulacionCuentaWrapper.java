package mx.babel.bansefi.banksystem.cuentas.wrappers;

import java.io.Serializable;

import mx.babel.bansefi.banksystem.cuentas.beans.ConsultaAnulacionCuentaBean;
import mx.babel.bansefi.banksystem.cuentas.webservices.anularconstitucionacuerdo.ResponseBansefi.OTRCONSUANULARACTRNO.TRCONSUANULARACEVTZ;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Wrapper para almacenar la salida de la consulta de anulacion de cuentas
 * TR_CONSU_ANULAR_AC_TRN
 * @author manuel.nieto
 *
 */
@Component
public class ConsultaAnulacionCuentaWrapper implements Serializable{

	private static final long serialVersionUID = 621502351252254190L;
	@Autowired
	private DozerBeanMapper dozerBeanMapper;
	
	/**
	 * Funcion que convierte la respuesta de la consulta y la wrappea
	 * al objeto ConsultaAnulacionCuentaBean
	 * @param entrada
	 * @return
	 */
	public ConsultaAnulacionCuentaBean wrapperConsultaAnulacionCuenta(TRCONSUANULARACEVTZ entrada){
		Mapper mapper = dozerBeanMapper;
		return mapper.map(entrada, ConsultaAnulacionCuentaBean.class,
				"consultaAnulacionCuenta");
	}
}
