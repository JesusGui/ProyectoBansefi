package mx.babel.bansefi.banksystem.personas.wrappers;

import java.util.ArrayList;
import java.util.List;

import mx.babel.bansefi.banksystem.personas.beans.OtroValorBean;
import mx.babel.bansefi.banksystem.personas.webservices.consultaotrasidentificacionespersona.ResponseBansefi.OTRPECONSPEIDEXTTRN.TRPECONSPEIDEXTEVT1.TRPECONSPEIDEXTEVT;

import org.apache.commons.lang.StringUtils;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConsultaOtrasIdentificacionesPersonaWrapper {

    @Autowired
    DozerBeanMapper dozerBeanMapper;

    public List<OtroValorBean> wrappOtrasIdentificaciones(final List<TRPECONSPEIDEXTEVT> list) {
        final List<OtroValorBean> resultado = new ArrayList<OtroValorBean>();
        final Mapper mapper = dozerBeanMapper;
        for(final TRPECONSPEIDEXTEVT value: list){
            if(value != null && StringUtils.isNotBlank(value.getCODIDEXTPERS())){
                resultado.add(mapper.map(value, OtroValorBean.class, "consultaOtrasIdentificacionesPersona"));
            }
        }
        return resultado;
    }

}
