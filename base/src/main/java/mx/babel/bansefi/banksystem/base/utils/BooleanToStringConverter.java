package mx.babel.bansefi.banksystem.base.utils;

import org.dozer.DozerConverter;
import org.dozer.Mapper;
import org.dozer.MapperAware;

/**
 * Clase que convierte un Boolean a String S/N y viceversa.
 * 
 * @author javier.martinnino
 * 
 */
public class BooleanToStringConverter extends DozerConverter<Boolean, String> implements MapperAware{
	
	private final static String SI = "S";
	private final static String NO = "N";
	
    private Mapper mapper;
    
    public BooleanToStringConverter() {
        super(Boolean.class, String.class);
    }
    
    @Override
    public void setMapper(Mapper mapper) {
        this.mapper = mapper;
        
    }

    @Override
    public String convertTo(Boolean source, String destination) {
        if (source){
        	return BooleanToStringConverter.SI;
        }else{
        	return BooleanToStringConverter.NO;
        }
    }

    @Override
    public Boolean convertFrom(String source, Boolean destination) {
        if (BooleanToStringConverter.SI.equals(source)){
        	return true;
        }
        if (BooleanToStringConverter.NO.equals(source)){
        	return false;
        }
        return null;
    }

}
