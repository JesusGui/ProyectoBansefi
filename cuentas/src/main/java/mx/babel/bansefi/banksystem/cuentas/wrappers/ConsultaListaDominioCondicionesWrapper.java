package mx.babel.bansefi.banksystem.cuentas.wrappers;

import java.util.ArrayList;
import java.util.List;

import mx.babel.bansefi.banksystem.cuentas.beans.DominioCondicionBean;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultalistadominiocondiciones.ResponseBansefi.OTRLISTADOMPKTRNO.TRLISTADOMPKEVTZ.PKDOMINIOV;

import org.apache.commons.lang3.StringUtils;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConsultaListaDominioCondicionesWrapper {


    @Autowired
    DozerBeanMapper dozerBeanMapper;

    public List<DominioCondicionBean> wrappCatalogoLista(final List<PKDOMINIOV> lista){
        final Mapper mapper = dozerBeanMapper;
        final List<DominioCondicionBean> resultado = new ArrayList<DominioCondicionBean>();
        for(final PKDOMINIOV data : lista){
            if(StringUtils.isNotBlank(data.getCODDOMPARMCD())){
                resultado.add(mapper.map(data, DominioCondicionBean.class, "resultadoConsultaListaDominioCondiciones"));
            }
        }
        return resultado;
    }

}
