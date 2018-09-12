package mx.babel.bansefi.banksystem.base.wrappers;

import mx.babel.arq.catalogo.beans.CatalogoGeoBean;
import mx.babel.bansefi.banksystem.base.beans.domain.DomicilioBean;
import mx.babel.bansefi.banksystem.base.beans.domain.DomicilioTipoBean;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Clase para convertir los valores de un CatalogoGeoBean a un DomicilioBean
 * 
 * @author manuel.nieto
 * 
 */
@Component
public class CatalogoGeoBeanToDomicilioBeanWrapper {

	/**
	 * Asignación de bean de mapeo
	 */
	@Autowired
	DozerBeanMapper dozerBeanMapper;

	/**
	 * Funcion que hace convierte los valores de un CatalogoGeoBean a una
	 * Clase DomicilioBean
	 * @param catalogo
	 * @return
	 */
	public DomicilioBean wrappCatalogoGeoBeanToDomicilioBean(
			CatalogoGeoBean catalogo) {
		Mapper mapper = dozerBeanMapper;
		return mapper.map(catalogo, DomicilioBean.class,
				"convierteCatalogoGeoBeanToDomicilioBean");
	}
	
	/**
	 * Funcion que convierte los valores de un CatalogoGeoBean
	 * a una clase DomicilioTipoBean, la cual extiende de DomicilioBean
	 * @param catalogo
	 * @return
	 */
	public DomicilioTipoBean wrappCatalogoGeoBeanToDomicilioTipoBean(
			CatalogoGeoBean catalogo) {
		Mapper mapper = dozerBeanMapper;
		return mapper.map(catalogo, DomicilioTipoBean.class,
				"convierteCatalogoGeoBeanToDomicilioTipoBean");
	}
	
}
