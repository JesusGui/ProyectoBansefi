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
 * Clase converter que realiza una conversión de String a Date.
 * 
 * @author alejandro.pineda
 * 
 */
public class StringToDateConverter extends DozerConverter<String, Date>
		implements MapperAware {

	private Mapper mapper;

	private final DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	private final DateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd");
	private DateFormat formatter3 = new SimpleDateFormat(
			ConstantesFuncionales.GENERAL_DATE_FORMATTER_INT);
	private final DateFormat formatter4 = new SimpleDateFormat("HH:mm:ss");
	private final DateFormat formatter5 = new SimpleDateFormat("HH.mm.ss");

	public StringToDateConverter() {
		super(String.class, Date.class);
	}

	@Override
	public void setMapper(final Mapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public Date convertTo(final String source, final Date destination) {
		try {
			// yyyy-MM-dd
			if (source.matches("^[\\d]{4}-[\\d]{2}-[\\d]{2}$")) {
				return formatter2.parse(source);
			}
			// HH:mm:ss
			if (source.matches("^[\\d]{2}:[\\d]{2}:[\\d]{2}$")) {
				return formatter4.parse(source);
			}
			// HH.mm.ss
			if (source.matches("^[\\d]{2}.[\\d]{2}.[\\d]{2}$")) {
				return formatter5.parse(source);
			}
			return formatter.parse(source);

		} catch (final ParseException e) {
			return null;
		}
	}

	@Override
	public String convertFrom(final Date source, final String destination) {
		try {
			try {
				switch (getParameter()) {
				case "dd/MM/yyyy":
					return formatter.format(source);
				case "yyyy-MM-dd":
					return formatter2.format(source);
				case "HH:mm:ss":
					return formatter4.format(source);
				case "HH.mm.ss":
					return formatter5.format(source);
				}
			} catch (IllegalStateException e) {
				return formatter3.format(source);
			}

			throw new ParseException("Error en el formato de fecha", 0);
		} catch (final ParseException e) {
			return null;
		}
	}

}
