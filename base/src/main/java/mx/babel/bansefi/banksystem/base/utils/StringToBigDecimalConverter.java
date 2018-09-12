package mx.babel.bansefi.banksystem.base.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import org.dozer.DozerConverter;
import org.dozer.Mapper;
import org.dozer.MapperAware;

/**
 * Clase encargada de conversión entre fechas y enteros representado fechas.
 * 
 * @author mario.montesdeoca
 *
 */
public class StringToBigDecimalConverter extends DozerConverter<String, BigDecimal> implements MapperAware{

	private Mapper mapper;
	
	private DecimalFormat formatter =  new DecimalFormat("#0.##");
	
	public StringToBigDecimalConverter() {
		super(String.class, BigDecimal.class);
	}
	
	@Override
	public void setMapper(Mapper mapper) {
		this.mapper = mapper;
	}
	
	/**
	 * Conversión de entero con formato ddMMyyyy a fecha.
	 */
	public BigDecimal convertTo(String source, BigDecimal destination){
		return new BigDecimal(source.replaceAll(",","").trim());
	}
	
	/**
	 * Conversión de fecha a entero.
	 */
	public String convertFrom(BigDecimal source, String destination){
		return formatter.format(source);
	}
}
