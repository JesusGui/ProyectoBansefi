package mx.babel.bansefi.banksystem.cuentas.wrappers;

import java.util.ArrayList;
import java.util.List;

import mx.babel.bansefi.banksystem.base.beans.domain.CuentaRelacionPlazoBean;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultarelacionescuentaplazo.ResponseBansefi.OTRRELIMPSCNACTRNO.TRRELIMPSCNACEVTZ.TRRELIMPSCNACEVTV;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConsultaRelacionCuentaPlazoWrapper {


    @Autowired
    DozerBeanMapper dozerBeanMapper;

    public List<CuentaRelacionPlazoBean> wrappRelacionCuentaPlazo(
            final List<TRRELIMPSCNACEVTV> trrelimpscnacevtvList) {
        final List<CuentaRelacionPlazoBean> resultado = new ArrayList<CuentaRelacionPlazoBean>();
        final Mapper mapper = dozerBeanMapper;
        for(final TRRELIMPSCNACEVTV trrelimpscnacevtv : trrelimpscnacevtvList){
            if(trrelimpscnacevtv.getNUMSECAC()>0){
                resultado.add(mapper.map(trrelimpscnacevtv,
                        CuentaRelacionPlazoBean.class, "resultadoRelacionCuentaPlazo"));
            }
        }
        return resultado;
    }

}
