package mx.babel.bansefi.banksystem.cuentas.wrappers;

import mx.babel.bansefi.banksystem.base.beans.productossimples.condiciones.CondicionInteresBean;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultacondicioninteres.ResponseBansefi.OTRCONSVALKAINTTRNO.TRCONSVALKAINTEVTZ;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultacondicioninteresplazo.ResponseBansefi.OTRCONSVALKSINTTRNO.TRCONSVALKSINTEVTZ;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultainterestarifa.ResponseBansefi.OTRKPCNSINTSMPTRNO.TRKPCNSINTSMPEVTZ.KPINTSMPLV;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConsultaCondicionInteresWrapper {


    @Autowired
    DozerBeanMapper dozerBeanMapper;

    public CondicionInteresBean wrappCondicionInteres(final TRCONSVALKAINTEVTZ object){
        final Mapper mapper = dozerBeanMapper;
        return mapper.map(object, CondicionInteresBean.class, "resultadoConsultaCondicionInteres");
    }

    public CondicionInteresBean wrappCondicionInteresPlazo(
            final TRCONSVALKSINTEVTZ object) {
        final Mapper mapper = dozerBeanMapper;
        return mapper.map(object, CondicionInteresBean.class, "resultadoConsultaCondicionInteresPlazo");
    }

    public CondicionInteresBean wrappInteresTarifa(final KPINTSMPLV kpintsmplv) {

        final Mapper mapper = dozerBeanMapper;
        return mapper.map(kpintsmplv, CondicionInteresBean.class, "resultadoConsultaInteresTarifa");
    }


}
