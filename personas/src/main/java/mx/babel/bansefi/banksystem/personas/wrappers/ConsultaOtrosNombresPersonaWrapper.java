package mx.babel.bansefi.banksystem.personas.wrappers;

import java.util.ArrayList;
import java.util.List;

import mx.babel.bansefi.banksystem.personas.beans.OtroValorBean;
import mx.babel.bansefi.banksystem.personas.webservices.consultaotrosnombrespersona.ResponseBansefi.OTRPECONSPEOTRONOMB.TRPECONSPEOTRONOMBE1.TRPECONSPEOTRONOMBE;

import org.apache.commons.lang.StringUtils;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConsultaOtrosNombresPersonaWrapper {

    @Autowired
    DozerBeanMapper dozerBeanMapper;

    public List<OtroValorBean> wrappOtrosNombres(
            final List<TRPECONSPEOTRONOMBE> list) {
        final List<OtroValorBean> resultado = new ArrayList<OtroValorBean>();
        final Mapper mapper = dozerBeanMapper;
        for(final TRPECONSPEOTRONOMBE value: list){
            if(value != null && StringUtils.isNotBlank(value.getCODNOMBPERS())){
                resultado.add(mapper.map(value, OtroValorBean.class, "consultaOtrosNombresPersona"));
            }
        }
        return resultado;
    }

}
