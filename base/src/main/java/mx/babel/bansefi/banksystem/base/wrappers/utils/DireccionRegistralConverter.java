package mx.babel.bansefi.banksystem.base.wrappers.utils;

import java.util.ArrayList;

import mx.babel.bansefi.banksystem.base.webservices.consultadetallebien.ResponseBansefi.OTRBIENCNSTRNO.TRBIENCNSEVTZ.DRCOMPRGSTROV;

import org.apache.commons.lang3.StringUtils;
import org.dozer.DozerConverter;
import org.dozer.Mapper;
import org.dozer.MapperAware;

public class DireccionRegistralConverter extends
        DozerConverter<ArrayList, String> implements MapperAware {

    public DireccionRegistralConverter() {
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
        for (int i = 0; i < source.size(); i++) {
            DRCOMPRGSTROV drcomprgstrov = (DRCOMPRGSTROV) source.get(i);
            if (tipoAtributo.equals(drcomprgstrov.getCODCOMPRGSTRO())) {
                return StringUtils.trim(drcomprgstrov.getVALCOMPRGSTRODR());
            }
        }

        return null;
    }

    @Override
    public ArrayList convertFrom(String source, ArrayList destination) {
        // TODO Auto-generated method stub
        return null;
    }

}
