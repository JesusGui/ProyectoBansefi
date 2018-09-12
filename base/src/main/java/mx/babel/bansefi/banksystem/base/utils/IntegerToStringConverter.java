package mx.babel.bansefi.banksystem.base.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dozer.DozerConverter;
import org.dozer.Mapper;
import org.dozer.MapperAware;

public class IntegerToStringConverter extends DozerConverter<Integer, String>
		implements MapperAware {

	private static final Logger LOGGER = LogManager.getLogger(IntegerToStringConverter.class);
	private static final String FORMATO_FECHA = "dd/MM/yyyy";

	private DateFormat formatter = new SimpleDateFormat("yyyyMMdd");

	private Mapper mapper;

	public IntegerToStringConverter() {
		super(Integer.class, String.class);
	}

	@Override
	public void setMapper(Mapper mapper) {
		this.mapper = mapper;

	}

	@Override
	public String convertTo(Integer source, String destination) {
		try {
			Date date = (Date) formatter.parse(Integer.toString(source));
			SimpleDateFormat sdf = new SimpleDateFormat(
					IntegerToStringConverter.FORMATO_FECHA);
			return sdf.format(date);
		} catch (ParseException e) {
			return null;
		}
	}

	@Override
	public Integer convertFrom(String source, Integer destination) {
		Integer i = 0;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(
					IntegerToStringConverter.FORMATO_FECHA);
			Date date = (Date) sdf.parse(source);
			String valor = formatter.format(date);
			i = Integer.valueOf(valor);
		} catch (ParseException e) {
			LOGGER.error("Error al parsear.",e);
		}
		return i;
	}

}
