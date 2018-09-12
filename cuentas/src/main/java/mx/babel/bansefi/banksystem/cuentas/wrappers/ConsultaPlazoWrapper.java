package mx.babel.bansefi.banksystem.cuentas.wrappers;

import java.util.ArrayList;
import java.util.List;

import mx.babel.bansefi.banksystem.base.beans.DepositoIPFBean;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultaplazo.ResponseBansefi.OTRCONSULTAPLAZOTRNO.TRCONSULTAPLAZOEVTZ.TRCONSULTAPLAZOEVTV;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConsultaPlazoWrapper {

    @Autowired
    DozerBeanMapper dozerBeanMapper;

    public List<DepositoIPFBean> wrappPlazo(
            final List<TRCONSULTAPLAZOEVTV> trconsultaplazoevtvList) {
        final List<DepositoIPFBean> respuesta = new ArrayList<>();
        final Mapper mapper = dozerBeanMapper;
        for(final TRCONSULTAPLAZOEVTV trconsultaplazoevtv : trconsultaplazoevtvList){
            if(trconsultaplazoevtv.getNUMSUBAC()>0){
                respuesta.add(mapper.map(trconsultaplazoevtv, DepositoIPFBean.class,
                        "resultadoConsultaPlazo"));
            }
        }
        return respuesta;
    }

}
