package mx.babel.bansefi.banksystem.base.wrappers.utils;

import java.util.ArrayList;

import mx.babel.bansefi.banksystem.base.webservices.consultadomicilio.ResponseBansefi.OTRPECONSDOMICTRNO.TRPECONSDOMICEVTZ.COMPDOMICV;

import org.dozer.DozerConverter;
import org.dozer.Mapper;
import org.dozer.MapperAware;

public class DomicilioConverter extends DozerConverter<ArrayList, String> implements MapperAware{

  
    public DomicilioConverter() {
        super(ArrayList.class, String.class);
        // TODO Auto-generated constructor stub
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
            COMPDOMICV compdomicv = (COMPDOMICV)source.get(i);
            if(tipoAtributo.equals(compdomicv.getCODCOMPDOMIC())){
                return compdomicv.getVALCOMPDOMICDR();
            }
        }
        
        return null;
    }

    @Override
    public  ArrayList convertFrom(String source,
            ArrayList destination) {
        // TODO Auto-generated method stub
        return null;
    }

 

}
