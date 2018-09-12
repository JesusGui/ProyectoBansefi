package mx.babel.bansefi.banksystem.base.converter;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.util.ArrayList;

import org.apache.commons.lang3.StringUtils;
import org.dozer.DozerConverter;
import org.dozer.Mapper;
import org.dozer.MapperAware;

public class ArrayToBigIntegerConverter extends DozerConverter<ArrayList, BigInteger>
        implements MapperAware {

    public ArrayToBigIntegerConverter() {
        super(ArrayList.class, BigInteger.class);
        // TODO Auto-generated constructor stub
    }

    private Mapper mapper;

    @Override
    public void setMapper(Mapper mapper) {
        this.mapper = mapper;

    }

    @Override
    public BigInteger convertTo(ArrayList source, BigInteger destination) {
        String tipoAtributo = getParameter();
        String[] atributos = tipoAtributo.split(",");
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
                        return new BigInteger(StringUtils.trim((String) metodo2.invoke(objeto)));
                    }else{
                        return null;
                    }
                }
            } catch (NoSuchMethodException | SecurityException
                    | InvocationTargetException | IllegalAccessException e) {
                return null;
            }
        }

        return null;
    }

    @Override
    public ArrayList convertFrom(BigInteger source, ArrayList destination) {
        // TODO Auto-generated method stub
        return null;
    }

}
