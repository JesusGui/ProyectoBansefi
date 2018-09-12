package mx.babel.bansefi.banksystem.base.wrappers;

import mx.babel.arq.sesion.contexto.beans.UsuarioBean;
import mx.babel.bansefi.banksystem.base.webservices.login.ResponseBansefi;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Clase encargada de realizar los mapeos entre el LoginServicio y el
 * UsuarioBean.
 * 
 * @author omar.marquez
 * 
 */

@Component
public class LoginWrapper {

	@Autowired
	DozerBeanMapper dozerBeanMapper;

	/**
	 * Método encargado de mapear la respuesta con los atributos del
	 * UsuarioBean.
	 * 
	 * @param respuesta
	 * @return UsuarioBean
	 */
	public UsuarioBean wrappLogin(ResponseBansefi respuesta) {
	String fecha=	respuesta.getFECSYS().trim();
	respuesta.setFECSYS(fecha);
		Mapper mapper = dozerBeanMapper;
		return mapper.map(respuesta, UsuarioBean.class, "resultadoLogin");
	}

}