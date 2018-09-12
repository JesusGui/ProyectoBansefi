package mx.babel.bansefi.banksystem.base.wrappers;

import java.io.Serializable;

import mx.babel.bansefi.banksystem.base.beans.ReciboBean;
import mx.babel.bansefi.banksystem.base.webservices.consultarecibonodomiciliado.ResponseBansefi;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RecibosNoDomiciliadosWrapper implements Serializable {

	private static final long serialVersionUID = -2267484573433988119L;

	@Autowired
	private DozerBeanMapper dozerBeanMapper;

	/**
	 * Funcion que concierte la respuesta de la consulta de recibos no
	 * domiciliados
	 * 
	 * @param TRCONSULTARCBOVNTLLA6
	 * @return ResumenTransaccionBean
	 */
	public ReciboBean wrappRespuestaConsultaRecibos(
			ResponseBansefi respuesta) {
		Mapper mapper = dozerBeanMapper;
		return mapper.map(respuesta, ReciboBean.class,
				"respuestaConsultaRecibos");
	}

}
