package mx.babel.bansefi.banksystem.cuentas.wrappers;

import mx.babel.bansefi.banksystem.base.beans.productossimples.condiciones.CondicionComisionBean;
import mx.babel.bansefi.banksystem.cuentas.webservices.modificacondicioncomision.Ejecutar.ITRMODIFICARKACMSTRN.TRMODIFICARKACMSEVTY;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ModificaCondicionComisionWrapper {


    @Autowired
    DozerBeanMapper dozerBeanMapper;

    public TRMODIFICARKACMSEVTY wrappCondicionComision(final CondicionComisionBean condicionBean){
        final Mapper mapper = dozerBeanMapper;
        return mapper.map(condicionBean, TRMODIFICARKACMSEVTY.class, "llamadaModificaCondicionComision");
    }

}
