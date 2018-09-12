package mx.babel.bansefi.banksystem.base.utils;

import java.math.BigInteger;

import org.dozer.DozerConverter;
import org.dozer.Mapper;
import org.dozer.MapperAware;

/**
 * Clase que convierte la prioridad de una notificación (BigInteger) a un tipo
 * String. Clase exclusiva para realizar la conversión: 1 --> 'SI' y 2 --> 'NO'
 * y viceversa.
 * 
 * @author omar.marquez
 * 
 */
public class PrioridadNotificacionConverter extends
		DozerConverter<BigInteger, String> implements MapperAware {

	private final static String SI = "SI";
	private final static String NO = "NO";

	@SuppressWarnings("unused")
	private Mapper mapper;

	public PrioridadNotificacionConverter() {
		super(BigInteger.class, String.class);
	}

	@Override
	public void setMapper(Mapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public String convertTo(BigInteger source, String destination) {
		if (source.intValue() == 1) {
			return PrioridadNotificacionConverter.SI;
		} else {
			return PrioridadNotificacionConverter.NO;
		}
	}

	@Override
	public BigInteger convertFrom(String source, BigInteger destination) {
		if (source.equalsIgnoreCase(PrioridadNotificacionConverter.SI)) {
			return new BigInteger("1");
		} else {
			return new BigInteger("2");
		}
	}

}