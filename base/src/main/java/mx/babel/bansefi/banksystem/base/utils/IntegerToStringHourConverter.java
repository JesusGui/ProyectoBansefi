package mx.babel.bansefi.banksystem.base.utils;

import org.dozer.DozerConverter;
import org.dozer.Mapper;
import org.dozer.MapperAware;

public class IntegerToStringHourConverter extends DozerConverter<Integer, String> implements MapperAware {

private Mapper mapper;
	
	public IntegerToStringHourConverter() {
		super(Integer.class, String.class);
	}
	
	@Override
	public void setMapper(Mapper mapper) {
		this.mapper = mapper;
	}
	
	/**
	 * Conversión de entero con formato yyyyMMdd a fecha.
	 */
	public String convertTo(Integer source, String destination){
		String hora = source.toString();
		while(hora.length() < 6){
			hora = "0"+ hora;
		}
		return hora.substring(0,2)+":"+hora.substring(2,4)+":"+hora.substring(4,hora.length());
	}
	
	/**
	 * Conversión de fecha a entero.
	 */
	public Integer convertFrom(String source, Integer destination){
		try{
			return Integer.valueOf(source.replace(":", ""));
		}catch(NumberFormatException nfe){
			return null;
		}
	}
}
