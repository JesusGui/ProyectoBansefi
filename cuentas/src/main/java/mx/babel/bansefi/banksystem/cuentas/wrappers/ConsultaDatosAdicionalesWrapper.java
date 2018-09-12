package mx.babel.bansefi.banksystem.cuentas.wrappers;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.babel.bansefi.banksystem.cuentas.beans.DatoAdicionalBean;

/**
 * @author Aramis Oswaldo Gadea Gerónimo &lt;agadea@uanlmexico.mx&gt;
 * @category Wrapper
 */
@Component
public class ConsultaDatosAdicionalesWrapper {
	@Autowired
	DozerBeanMapper dozerBeanMapper;

	/**
	 * Wrapper para consulta de Datos adicionales
	 * 
	 * @param documento
	 *            Documento que contiene los datos que arroja la consulta del
	 *            WebService
	 * @return Bean de Dato adicional
	 * @author Aramis Oswaldo Gadea Gerónimo &lt;agadea@uanlmexico.mx&gt;
	 * @param <T>
	 *            Objeto que se mapean con el bean
	 */
	public <T> DatoAdicionalBean wrappBean(T documento, String mapId) {
		Mapper mapper = dozerBeanMapper;
		return mapper.map(documento, DatoAdicionalBean.class, mapId);
	}

	public <T, U> T wrappService(Class<T> entityClass, U documento, String mapId) {
		Mapper mapper = dozerBeanMapper;
		return mapper.map(documento, entityClass, mapId);
	}
}
