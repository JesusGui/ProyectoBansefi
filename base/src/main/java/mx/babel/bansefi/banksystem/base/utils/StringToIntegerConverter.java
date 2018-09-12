package mx.babel.bansefi.banksystem.base.utils;

import org.dozer.DozerConverter;
import org.dozer.Mapper;
import org.dozer.MapperAware;

/**
 * Clase encargada de convertir una cadena a un entero.
 * 
 * @author omar.marquez
 * 
 */
public class StringToIntegerConverter extends DozerConverter<String, Integer>
		implements MapperAware {

	private Mapper mapper;

	public StringToIntegerConverter() {
		super(String.class, Integer.class);
	}

	@Override
	public void setMapper(Mapper mapper) {
		this.mapper = mapper;
	}

	/**
	 * Método que convierte un entero a una cadena.
	 */
	@Override
	public String convertFrom(Integer source, String destination) {
		return String.valueOf(source);
	}

	/**
	 * Método que convierte una cadena a un entero.
	 */
	@Override
	public Integer convertTo(String source, Integer destination) {
		try {
			return Integer.parseInt(source);
		} catch (NumberFormatException e) {
			return null;
		}
	}

}