package mx.babel.bansefi.banksystem.cuentas.wrappers;

import java.util.ArrayList;
import java.util.List;

import mx.babel.bansefi.banksystem.cuentas.beans.BaseCalculoCondicionesBean;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultalistabasecalculocondiciones.ResponseBansefi.OTRCONSLISTAPSPKPSPK.TRCONSLISTAPSPKPSPKE.PSDATOSRLPKPSV;

import org.apache.commons.lang3.StringUtils;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConsultaListaBaseCalculoCondicionesWrapper {


    @Autowired
    DozerBeanMapper dozerBeanMapper;

    public List<BaseCalculoCondicionesBean> wrappListaBaseCalculoCondiciones(final List<PSDATOSRLPKPSV> list){
        final Mapper mapper = dozerBeanMapper;
        final List<BaseCalculoCondicionesBean> resultado = new ArrayList<BaseCalculoCondicionesBean>();
        for(final PSDATOSRLPKPSV data : list){
            if(StringUtils.isNoneBlank(data.getIDPDS2(),data.getIDPARMCD2())){
                resultado.add(mapper.map(data, BaseCalculoCondicionesBean.class, "resultadoConsultaListaBaseCalculoCondiciones"));
            }
        }
        return resultado;
    }

}
