package mx.babel.bansefi.banksystem.cuentas.wrappers;

import mx.babel.bansefi.banksystem.base.beans.DatosDetalleIPFBean;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultadatosdetalleipf.ResponseBansefi.OTRCONSIMPSCNINDIVTRN.TRCONSIMPSCNINDIVEVT.TRCONSIMPSCNINDIVEVT1;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConsultaDatosDetalleIPFWrapper {


    @Autowired
    DozerBeanMapper dozerBeanMapper;

    public DatosDetalleIPFBean wrappDatosDetalleIPF(final TRCONSIMPSCNINDIVEVT1 trconsimpscnindivevt1) {
        final Mapper mapper = dozerBeanMapper;
        return mapper.map(trconsimpscnindivevt1, DatosDetalleIPFBean.class, "resultadoDatosDetalleIPF");
    }

}
