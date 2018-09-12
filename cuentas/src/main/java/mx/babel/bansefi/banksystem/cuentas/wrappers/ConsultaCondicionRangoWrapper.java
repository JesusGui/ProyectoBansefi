package mx.babel.bansefi.banksystem.cuentas.wrappers;

import mx.babel.bansefi.banksystem.base.beans.productossimples.condiciones.CondicionRangoBean;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultacondicionrango.ResponseBansefi.OTRCONSVALKARNGTRNO.TRCONSVALKARNGEVTZ;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultacondicionrangoplazo.ResponseBansefi.OTRCONSVALKSRNGTRNO.TRCONSVALKSRNGEVTZ;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultarangotarifa.ResponseBansefi.OTRKPCNSRNGSMPTRNO.TRKPCNSRNGSMPEVTZ.KPRNGSMPLV;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConsultaCondicionRangoWrapper {


    @Autowired
    DozerBeanMapper dozerBeanMapper;

    public CondicionRangoBean wrappCondicionRango(final TRCONSVALKARNGEVTZ trconsvalkarngevtz){
        final Mapper mapper = dozerBeanMapper;
        return mapper.map(trconsvalkarngevtz, CondicionRangoBean.class, "resultadoConsultaCondicionRango");
    }

    public CondicionRangoBean wrappCondicionRangoPlazo(
            final TRCONSVALKSRNGEVTZ trconsvalkarngevtz) {
        final Mapper mapper = dozerBeanMapper;
        return mapper.map(trconsvalkarngevtz, CondicionRangoBean.class, "resultadoConsultaCondicionRangoPlazo");
    }

    public CondicionRangoBean wrappRangoTarifa(final KPRNGSMPLV kprngsmplv) {
        final Mapper mapper = dozerBeanMapper;
        return mapper.map(kprngsmplv, CondicionRangoBean.class, "resultadoConsultaRangoTarifa");
    }

}
