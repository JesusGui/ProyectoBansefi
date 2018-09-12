package mx.babel.bansefi.banksystem.cuentas.wrappers;

import java.util.ArrayList;
import java.util.List;

import mx.babel.bansefi.banksystem.base.beans.PreferenceItemBean;
import mx.babel.bansefi.banksystem.base.beans.productossimples.condiciones.CondicionListaBean;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultacondicionlista.ResponseBansefi.OTRCONSVALKALSTTRNO.TRCONSVALKALSTEVTZ;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultacondicionlista.ResponseBansefi.OTRCONSVALKALSTTRNO.TRCONSVALKALSTEVTZ.KAKARLPKDOMV;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultacondicionlistaplazo.ResponseBansefi.OTRCONSVALKSLSTTRNO.TRCONSVALKSLSTEVTZ;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultacondicionlistaplazo.ResponseBansefi.OTRCONSVALKSLSTTRNO.TRCONSVALKSLSTEVTZ.KSKSRLPKDOMV;

import org.apache.commons.lang3.StringUtils;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConsultaCondicionListaWrapper {


    @Autowired
    DozerBeanMapper dozerBeanMapper;

    public CondicionListaBean wrappCondicionLista(final TRCONSVALKALSTEVTZ trconsvalkalstevtz){
        final Mapper mapper = dozerBeanMapper;
        final CondicionListaBean resultado = mapper.map(trconsvalkalstevtz,
                CondicionListaBean.class, "resultadoConsultaCondicionLista");
        resultado.setLista(new ArrayList<PreferenceItemBean>());
        final List<KAKARLPKDOMV> lista = trconsvalkalstevtz.getKAKARLPKDOMV();
        for(final KAKARLPKDOMV data : lista){
            if(data != null && StringUtils.isNoneBlank(data.getCODDOMPARMCD())){
                final PreferenceItemBean item = mapper.map(data, PreferenceItemBean.class,
                        "resultadoConsultaCondicionListaItems");
                resultado.getLista().add(item);
            }
        }
        return resultado;
    }

    public CondicionListaBean wrappCondicionListaPlazo(
            final TRCONSVALKSLSTEVTZ trconsvalkslstevtz) {

        final Mapper mapper = dozerBeanMapper;
        final CondicionListaBean resultado = mapper.map(trconsvalkslstevtz,
                CondicionListaBean.class, "resultadoConsultaCondicionListaPlazo");
        resultado.setLista(new ArrayList<PreferenceItemBean>());
        final List<KSKSRLPKDOMV> lista = trconsvalkslstevtz.getKSKSRLPKDOMV();
        for(final KSKSRLPKDOMV data : lista){
            if(data != null && StringUtils.isNoneBlank(data.getCODDOMPARMCD())){
                final PreferenceItemBean item = mapper.map(data, PreferenceItemBean.class,
                        "resultadoConsultaCondicionListaPlazoItems");
                resultado.getLista().add(item);
            }
        }
        return resultado;
    }

}
