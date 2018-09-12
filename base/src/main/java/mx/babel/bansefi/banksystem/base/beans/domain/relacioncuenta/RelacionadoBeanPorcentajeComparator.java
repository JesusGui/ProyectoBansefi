package mx.babel.bansefi.banksystem.base.beans.domain.relacioncuenta;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Comparator;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RelacionadoBeanPorcentajeComparator implements Comparator<RelacionadoBean> {
    @Override
    public int compare(RelacionadoBean a, RelacionadoBean b) {
    	BigDecimal difA = new BigDecimal(0);
    	BigDecimal difB = new BigDecimal(0);
    	if(!StringUtils.isEmpty(a.getRespaldo()) && a.getPorcentaje() != null){
    		if(getOldBean(a).getPorcentaje() != null){
    			difA = a.getPorcentaje().add(getOldBean(a).getPorcentaje().negate());
    		}
    	}
    	if(!StringUtils.isEmpty(b.getRespaldo()) && b.getPorcentaje() != null){
    		if(getOldBean(b).getPorcentaje() != null){
    			difB = b.getPorcentaje().add(getOldBean(b).getPorcentaje().negate());
    		}
    	}
        return difA.compareTo(difB);
    }
    
    public RelacionadoBean getOldBean(RelacionadoBean relacionado) {
    	RelacionadoBean oldObject = new RelacionadoBean();
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,	false);
			oldObject =	mapper.readValue(relacionado.getRespaldo(),RelacionadoBean.class);
		} catch (IOException e) {
		}
		return oldObject;
	}
}