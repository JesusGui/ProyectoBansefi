package mx.babel.bansefi.banksystem.cuentas.wrappers;

import mx.babel.bansefi.banksystem.base.beans.productossimples.condiciones.CondicionTramoBean;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultacondiciontramo.ResponseBansefi.OTRCONSVALKAMTZTRNO.TRCONSVALKAMTZEVTZ;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultacondiciontramoplazo.ResponseBansefi.OTRCONSVALKSMTZTRNO.TRCONSVALKSMTZEVTZ;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConsultaCondicionTramoWrapper {


    @Autowired
    DozerBeanMapper dozerBeanMapper;

    public CondicionTramoBean wrappCondicionTramo(final TRCONSVALKAMTZEVTZ trconsvalkamtzevtz){
        final Mapper mapper = dozerBeanMapper;
        return mapper.map(trconsvalkamtzevtz, CondicionTramoBean.class, "resultadoConsultaCondicionTramo");
    }

    public CondicionTramoBean wrappCondicionTramoPlazo(
            final TRCONSVALKSMTZEVTZ trconsvalksmtzevtz) {
        final Mapper mapper = dozerBeanMapper;
        return mapper.map(trconsvalksmtzevtz, CondicionTramoBean.class, "resultadoConsultaCondicionTramoPlazo");
    }

}
