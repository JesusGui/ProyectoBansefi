package mx.babel.bansefi.banksystem.cuentas.wrappers;

import mx.babel.bansefi.banksystem.base.beans.productossimples.condiciones.CondicionValorListaBean;
import mx.babel.bansefi.banksystem.cuentas.webservices.modificacondicionvalorlista.Ejecutar.ITRMODIFICARKAVLSTRN.TRMODIFICARKAVLSEVTY;
import mx.babel.bansefi.banksystem.cuentas.webservices.modificacondicionvalorlistaplazo.Ejecutar.ITRMODIFICARKSVLSTRN.TRMODIFICARKSVLSEVTY;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ModificaCondicionValorListaWrapper {


    @Autowired
    DozerBeanMapper dozerBeanMapper;

    public TRMODIFICARKAVLSEVTY wrappCondicionValorLista(final CondicionValorListaBean condicionBean){
        final Mapper mapper = dozerBeanMapper;
        return mapper.map(condicionBean, TRMODIFICARKAVLSEVTY.class, "llamadaModificaCondicionValorLista");
    }

    public TRMODIFICARKSVLSEVTY wrappCondicionValorListaPlazo(
            final CondicionValorListaBean condicionBean) {
        final Mapper mapper = dozerBeanMapper;
        return mapper.map(condicionBean, TRMODIFICARKSVLSEVTY.class, "llamadaModificaCondicionValorListaPlazo");
    }

}
