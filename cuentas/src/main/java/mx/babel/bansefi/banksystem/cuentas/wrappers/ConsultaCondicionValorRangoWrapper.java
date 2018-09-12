package mx.babel.bansefi.banksystem.cuentas.wrappers;

import mx.babel.bansefi.banksystem.base.beans.productossimples.condiciones.CondicionValorRangoBean;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultacondicionvalorrango.ResponseBansefi.OTRCONSVALKAVRGTRNO.TRCONSVALKAVRGEVTZ;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultacondicionvalorrangoplazo.ResponseBansefi.OTRCONSVALKSVRGTRNO.TRCONSVALKSVRGEVTZ;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConsultaCondicionValorRangoWrapper {


    @Autowired
    DozerBeanMapper dozerBeanMapper;

    public CondicionValorRangoBean wrappCondicionValorRango(final TRCONSVALKAVRGEVTZ trconsvalkavrgevtz){
        final Mapper mapper = dozerBeanMapper;
        return mapper.map(trconsvalkavrgevtz, CondicionValorRangoBean.class, "resultadoConsultaCondicionValorRango");
    }

    public CondicionValorRangoBean wrappCondicionValorRangoPlazo(
            final TRCONSVALKSVRGEVTZ trconsvalksvrgevtz) {
        final Mapper mapper = dozerBeanMapper;
        return mapper.map(trconsvalksvrgevtz, CondicionValorRangoBean.class, "resultadoConsultaCondicionValorRangoPlazo");
    }

}
