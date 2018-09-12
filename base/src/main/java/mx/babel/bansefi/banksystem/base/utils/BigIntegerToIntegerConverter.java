package mx.babel.bansefi.banksystem.base.utils;

import java.math.BigInteger;

import org.dozer.DozerConverter;
import org.dozer.Mapper;
import org.dozer.MapperAware;

/**
 * Clase que convierte un BigInteger a un Integer y viceversa.
 * 
 * @author omar.marquez
 * 
 */
public class BigIntegerToIntegerConverter extends
		DozerConverter<BigInteger, Integer> implements MapperAware {

	private Mapper mapper;

	public BigIntegerToIntegerConverter() {
		super(BigInteger.class, Integer.class);
	}

	@Override
	public void setMapper(Mapper mapper) {
		this.mapper = mapper;
	}

	/**
	 * Método que devuelve un BigInteger a partir de un Integer.
	 */
	@Override
	public BigInteger convertFrom(Integer source, BigInteger destination) {
		return new BigInteger(Integer.toString(source));
	}

	/**
	 * Método que devuelve un Integer a partir de un BigInteger.
	 */
	@Override
	public Integer convertTo(BigInteger source, Integer destination) {
		try {
			return source.intValue();
		} catch (NullPointerException e) {
			return null;
		}
	}

}