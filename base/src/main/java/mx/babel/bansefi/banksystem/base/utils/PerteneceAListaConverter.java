package mx.babel.bansefi.banksystem.base.utils;

import java.util.ArrayList;

import org.dozer.DozerConverter;
import org.dozer.Mapper;
import org.dozer.MapperAware;

public class PerteneceAListaConverter extends DozerConverter<ArrayList, String> implements MapperAware{

	private final static String SI = "S";
	private final static String NO = "N";
  
    public PerteneceAListaConverter() {
        super(ArrayList.class, String.class);
    }

    private Mapper mapper;
    
    @Override
    public void setMapper(Mapper mapper) {
        this.mapper = mapper;
        
    }

    @Override
    public String convertTo(ArrayList source, String destination) {
        String tipoAtributo = getParameter();
        for(int i=0; i<source.size();i++){            
            if(tipoAtributo.equals(source.get(i))){
                return PerteneceAListaConverter.SI;
            }
        }        
        return PerteneceAListaConverter.NO;
    }

    @Override
    public ArrayList convertFrom(String source, ArrayList destination) {
    	String tipoAtributo = getParameter();
    	if (destination == null){
    		destination = new ArrayList<>();
    	}
    	if (source.equals(PerteneceAListaConverter.SI)){
    		destination.add(tipoAtributo);
    	}
    	return destination;
    }

 

}
