package mx.babel.bansefi.banksystem.cuentas.wrappers;

import mx.babel.bansefi.banksystem.base.beans.domain.CuentaBean;
import mx.babel.bansefi.banksystem.cuentas.webservices.altacuenta.ResponseBansefi.OTRSOLICITARACTRNO.TRSOLICITARACEVTZ.ACACE;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author joseluis.pena
 *
 */
@Component
public class AltaCuentaWrapper {

    @Autowired
    DozerBeanMapper dozerBeanMapper;

    public CuentaBean wrapAltaCuenta(final ACACE acace) {
        final Mapper mapper = dozerBeanMapper;
        return mapper.map(acace, CuentaBean.class, "resultadoAltaCuenta");
    }


}
