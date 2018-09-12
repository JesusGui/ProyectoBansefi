package mx.babel.bansefi.banksystem.base.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import mx.babel.bansefi.banksystem.base.constants.ConstantesFuncionales;

import org.dozer.DozerConverter;
import org.dozer.Mapper;
import org.dozer.MapperAware;

/**
 * Clase encargada de conversión entre fechas y enteros representado fechas.
 * 
 * @author mario.montesdeoca
 * 
 */
public class IntegerToDateConverter extends DozerConverter<Integer, Date>
		implements MapperAware {

	private Mapper mapper;

	private DateFormat formatter = new SimpleDateFormat(
			ConstantesFuncionales.GENERAL_DATE_FORMATTER_INT);
	private final DateFormat formatter2 = new SimpleDateFormat("HHmmss");

	public IntegerToDateConverter() {
		super(Integer.class, Date.class);
	}

	@Override
	public void setMapper(Mapper mapper) {
		this.mapper = mapper;
	}

	/**
	 * Conversión de entero con formato yyyyMMdd a fecha.
	 */
	public Date convertTo(Integer source, Date destination) {
		try {
			return (Date) formatter.parse(Integer.toString(source));
		} catch (ParseException e) {
			try {
				return (Date) formatter2.parse(Integer.toString(source));
			} catch (ParseException e2) {
				return null;
			}
		}
	}

	/**
	 * Conversión de fecha a entero.
	 */
	public Integer convertFrom(Date source, Integer destination) {
		try {
			return Integer.parseInt(formatter.format(source));
		} catch (NumberFormatException e) {
			try {
				return Integer.parseInt(formatter2.format(source));
			} catch (NumberFormatException e2) {
				return null;
			}
		}
	}

}