package mx.babel.bansefi.banksystem.base.converter;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import mx.babel.bansefi.banksystem.base.constants.ConstantesFuncionales;

import org.apache.commons.lang3.StringUtils;
import org.dozer.DozerConverter;
import org.dozer.Mapper;
import org.dozer.MapperAware;

public class ArrayToDateConverter extends DozerConverter<ArrayList, Date>
        implements MapperAware {

    public ArrayToDateConverter() {
        super(ArrayList.class, Date.class);
        // TODO Auto-generated constructor stub
    }

    private Mapper mapper;

    @Override
    public void setMapper(Mapper mapper) {
        this.mapper = mapper;

    }

    @Override
    public Date convertTo(ArrayList source, Date destination) {
        String tipoAtributo = getParameter();
        String[] atributos = tipoAtributo.split(",");
        DateFormat df = new SimpleDateFormat(ConstantesFuncionales.GENERAL_DATE_FORMATTER);
        for (int i = 0; i < source.size(); i++) {

            Object objeto = source.get(i);
            Method metodo;
            Method metodo2;
            try {
                metodo = objeto.getClass().getMethod("get" + atributos[1]);
                metodo2 = objeto.getClass().getMethod("get" + atributos[2]);    
                String codigo = (String) metodo.invoke(objeto);
               
               
                if (atributos[0].equals(codigo)) {
                    if(StringUtils.isNoneBlank((String) metodo2.invoke(objeto))){
                        return df.parse(StringUtils.trim((String) metodo2.invoke(objeto)));
                        
                    }else{
                        return null;
                    }
                }
            } catch (NoSuchMethodException | SecurityException
                    | InvocationTargetException | IllegalAccessException | ParseException e) {
                return null;
            }
        }

        return null;
    }

    @Override
    public ArrayList convertFrom(Date source, ArrayList destination) {
        // TODO Auto-generated method stub
        return null;
    }

}
