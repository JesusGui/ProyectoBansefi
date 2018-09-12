package mx.babel.bansefi.banksystem.cuentas.wrappers;

import mx.babel.bansefi.banksystem.cuentas.beans.DatosCondicionTarifaBean;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultadatoscondiciontarifa.ResponseBansefi.OTRKPCONSCONDICIONTRN.TRKPCONSCONDICIONEVT.KPCDPDE;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConsultaDatosCondicionTarifaWrapper {
    @Autowired
    DozerBeanMapper dozerBeanMapper;

    public DatosCondicionTarifaBean wrappDatosCondicionTarifa(final KPCDPDE kpcdpde) {
        final Mapper mapper = dozerBeanMapper;
        return mapper.map(kpcdpde, DatosCondicionTarifaBean.class, "resultadoDatosCondicionTarifa");
    }

}
