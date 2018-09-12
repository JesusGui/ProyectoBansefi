package mx.babel.bansefi.banksystem.cuentas.wrappers;

import mx.babel.bansefi.banksystem.base.beans.productossimples.ComisionTarifaBean;
import mx.babel.bansefi.banksystem.base.beans.productossimples.condiciones.CondicionComisionBean;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultacomisiontarifa.ResponseBansefi.OTRKPCNSCMSSMPTRNO.TRKPCNSCMSSMPEVTZ.KPCMSNSMPLV;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultacondicioncomision.ResponseBansefi;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultacondicioncomisionplazo.ResponseBansefi.OTRCONSVALKSCMSTRNO.TRCONSVALKSCMSEVTZ;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConsultaCondicionComisionWrapper {


    @Autowired
    DozerBeanMapper dozerBeanMapper;

    public CondicionComisionBean wrappCondicionComision(final ResponseBansefi.OTRCONSVALKACMSTRNO.TRCONSVALKACMSEVTZ response){
        final Mapper mapper = dozerBeanMapper;
        return mapper.map(response, CondicionComisionBean.class, "resultadoConsultaCondicionComision");
    }

    public CondicionComisionBean wrappCondicionComisionPlazo(
            final TRCONSVALKSCMSEVTZ response) {
        final Mapper mapper = dozerBeanMapper;
        return mapper.map(response, CondicionComisionBean.class, "resultadoConsultaCondicionComisionPlazo");
    }

    public ComisionTarifaBean wrappComisionTarifa(final KPCMSNSMPLV response) {
        final Mapper mapper = dozerBeanMapper;
        return mapper.map(response, ComisionTarifaBean.class, "resultadoConsultaComisionTarifa");
    }

}
