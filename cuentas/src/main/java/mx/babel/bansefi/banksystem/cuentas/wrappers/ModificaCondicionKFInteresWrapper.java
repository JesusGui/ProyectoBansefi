package mx.babel.bansefi.banksystem.cuentas.wrappers;

import mx.babel.bansefi.banksystem.base.beans.productossimples.condiciones.CondicionInteresBean;
import mx.babel.bansefi.banksystem.cuentas.webservices.modificacondicionkfinteres.Ejecutar.ITRMODIFICARKFINTTRN.TRMODIFICARKFINTEVTY;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ModificaCondicionKFInteresWrapper {


    @Autowired
    DozerBeanMapper dozerBeanMapper;

    public TRMODIFICARKFINTEVTY wrappCondicionInteres(final CondicionInteresBean condicionBean){
        final Mapper mapper = dozerBeanMapper;
        return mapper.map(condicionBean, TRMODIFICARKFINTEVTY.class, "llamadaModificaCondicionKFInteres");
    }


}
