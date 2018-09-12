package mx.babel.bansefi.banksystem.base.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.dozer.DozerConverter;
import org.dozer.Mapper;
import org.dozer.MapperAware;

/**
 * Convertidor para obtener el mes (MM) o el día (dd) o el año (yyyy) de una
 * Fecha, se necesita recibir un parámetro indicando que es lo que se desea
 * obtener.
 * 
 * @author alejandro.pineda
 * 
 */
public class ObtenerParteFechaConverter extends DozerConverter<Integer, String>
		implements MapperAware {

	private DateFormat formatter = new SimpleDateFormat("yyyyMMdd");

	private Mapper mapper;

	public ObtenerParteFechaConverter() {
		super(Integer.class, String.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setMapper(Mapper mapper) {
		this.mapper = mapper;

	}

	@Override
	public String convertTo(Integer source, String destination) {
		try {
			if(source.intValue() != 11111111){
				Date date = (Date) formatter.parse(Integer.toString(source));
				SimpleDateFormat dateFormat = new SimpleDateFormat(
						super.getParameter());
				return dateFormat.format(date);	
			}else{
				return "";
			}
		} catch (ParseException e) {
			return null;
		}

	}

	@Override
	public Integer convertFrom(String source, Integer destination) {
		// TODO Auto-generated method stub
		return null;
	}

}
