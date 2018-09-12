package mx.babel.bansefi.banksystem.cuentas.wrappers;

import mx.babel.bansefi.banksystem.base.beans.productossimples.condiciones.CondicionValorListaBean;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultacondicionvalorlista.ResponseBansefi.OTRCONSVALKAVLSTRNO.TRCONSVALKAVLSEVTZ;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultacondicionvalorlistaplazo.ResponseBansefi.OTRCONSVALKSVLSTRNO.TRCONSVALKSVLSEVTZ;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConsultaCondicionValorListaWrapper {


    @Autowired
    DozerBeanMapper dozerBeanMapper;

    public CondicionValorListaBean wrappCondicionValorLista(final TRCONSVALKAVLSEVTZ trconsvalkavlsevtz){
        final Mapper mapper = dozerBeanMapper;
        return mapper.map(trconsvalkavlsevtz, CondicionValorListaBean.class, "resultadoConsultaCondicionValorLista");
    }

    public CondicionValorListaBean wrappCondicionValorListaPlazo(
            final TRCONSVALKSVLSEVTZ trconsvalksvlsevtz) {
        final Mapper mapper = dozerBeanMapper;
        return mapper.map(trconsvalksvlsevtz, CondicionValorListaBean.class, "resultadoConsultaCondicionValorListaPlazo");
    }

}
