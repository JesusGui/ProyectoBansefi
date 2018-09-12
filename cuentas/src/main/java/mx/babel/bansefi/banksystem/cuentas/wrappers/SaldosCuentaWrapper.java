package mx.babel.bansefi.banksystem.cuentas.wrappers;

import mx.babel.bansefi.banksystem.cuentas.beans.SaldoCuentaBean;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Contiene los métodos que mapean los datos que devuele el servicio web con los
 * beans que utiliza la apliación
 * 
 * @author Aramis Oswaldo Gadea Gerónimo &lt;gadea@uanlmexico.mx&gt
 * @category Wrapper
 */
@Component
public class SaldosCuentaWrapper {
	@Autowired
	DozerBeanMapper dozerBeanMapper;

	/**
	 * Wrappea los objectos devueltos por el WS
	 * 
	 * @param <T>
	 * @param wsElement
	 *            Elemento que contiene los datos que arroja el servicio web
	 * @param mapId
	 *            Identificador del xml que mapea los datos
	 * @return un objeto de tipo {@link SaldoCuentaBean}
	 * @author Aramis Oswaldo Gadea Gerónimo &lt;gadea@uanlmexico.mx&gt;
	 */
	public <T> SaldoCuentaBean wrappBean(T wsElement, String mapId) {
		Mapper mapper = dozerBeanMapper;
		return mapper.map(wsElement, SaldoCuentaBean.class, mapId);
	}
}