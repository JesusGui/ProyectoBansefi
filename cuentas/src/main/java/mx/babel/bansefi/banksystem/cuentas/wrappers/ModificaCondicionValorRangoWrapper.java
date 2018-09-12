package mx.babel.bansefi.banksystem.cuentas.wrappers;

import mx.babel.bansefi.banksystem.base.beans.productossimples.condiciones.CondicionValorRangoBean;
import mx.babel.bansefi.banksystem.cuentas.webservices.modificacondicionvalorrango.Ejecutar.ITRMODIFICARKAVRGTRN.TRMODIFICARKAVRGEVTY;
import mx.babel.bansefi.banksystem.cuentas.webservices.modificacondicionvalorrangoplazo.Ejecutar.ITRMODIFICARKSVRGTRN.TRMODIFICARKSVRGEVTY;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ModificaCondicionValorRangoWrapper {


    @Autowired
    DozerBeanMapper dozerBeanMapper;

    public TRMODIFICARKAVRGEVTY wrappCondicionValorRango(final CondicionValorRangoBean condicionBean){
        final Mapper mapper = dozerBeanMapper;
        return mapper.map(condicionBean, TRMODIFICARKAVRGEVTY.class, "llamadaModificaCondicionValorRango");
    }

    public TRMODIFICARKSVRGEVTY wrappCondicionValorRangoPlazo(
            final CondicionValorRangoBean condicionBean) {
        final Mapper mapper = dozerBeanMapper;
        return mapper.map(condicionBean, TRMODIFICARKSVRGEVTY.class, "llamadaModificaCondicionValorRangoPlazo");
    }

}
