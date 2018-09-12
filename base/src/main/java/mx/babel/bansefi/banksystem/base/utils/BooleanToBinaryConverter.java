package mx.babel.bansefi.banksystem.base.utils;

import org.dozer.DozerConverter;
import org.dozer.Mapper;
import org.dozer.MapperAware;

/**
 * Clase que convierte un Boolean a String 1 y viceversa.
 *
 * @author javier.martinnino
 *
 */
public class BooleanToBinaryConverter extends DozerConverter<Boolean, String> implements MapperAware{

	private final static String SI = "1";
	private final static String NO = "";

    private Mapper mapper;

    public BooleanToBinaryConverter() {
        super(Boolean.class, String.class);
    }

    @Override
    public void setMapper(final Mapper mapper) {
        this.mapper = mapper;

    }

    @Override
    public String convertTo(final Boolean source, final String destination) {
        if (source){
        	return BooleanToBinaryConverter.SI;
        }else{
        	return BooleanToBinaryConverter.NO;
        }
    }

    @Override
    public Boolean convertFrom(final String source, final Boolean destination) {
        if (BooleanToBinaryConverter.SI.equals(source)){
        	return true;
        }
        return false;
    }

}
