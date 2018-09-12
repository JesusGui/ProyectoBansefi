package mx.babel.bansefi.banksystem.base.utils;

import mx.babel.arq.catalogo.beans.CatalogoBean;
import mx.babel.bansefi.banksystem.base.enums.CatalogoEnum;

import org.dozer.DozerConverter;
import org.dozer.Mapper;
import org.dozer.MapperAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Convertidor para obtener la descripción de un código en un catalogo.
 * 
 * @author alejandro.pineda
 * 
 */
@Component("obtenerDescripcionCatalogoConverter")
public class ObtenerDescripcionCatalogoConverter extends
		DozerConverter<String, String> implements MapperAware {

	private Mapper mapper;

	@Autowired
	CatalogoUtils catalogoUtils;

	public ObtenerDescripcionCatalogoConverter() {
		super(String.class, String.class);
	}

	@Override
	public void setMapper(Mapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public String convertTo(String source, String destination) {
		return null;
	}

	@Override
	public String convertFrom(String source, String destination) {
		CatalogoBean catalogo = catalogoUtils.getCatalogoBean(
				Enum.valueOf(CatalogoEnum.class, getParameter()), source);
		return catalogo.getDescripcionL();
	}

}
