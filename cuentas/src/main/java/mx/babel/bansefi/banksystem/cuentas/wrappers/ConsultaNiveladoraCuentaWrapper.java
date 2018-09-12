package mx.babel.bansefi.banksystem.cuentas.wrappers;

import java.util.ArrayList;
import java.util.List;

import mx.babel.bansefi.banksystem.cuentas.beans.NivelCuentaBean;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultaniveladoracuenta.ResponseBansefi.OTRCONSUNIVELACTRNO.TRCONSUNIVELACEVTZ.ACNIVELACE;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConsultaNiveladoraCuentaWrapper {

    @Autowired
    DozerBeanMapper dozerBeanMapper;

    public List<NivelCuentaBean> wrappNivelCuentaPlazo(
            final List<ACNIVELACE> acnivelaceLista) {
        final List<NivelCuentaBean> resultado = new ArrayList<NivelCuentaBean>();
        if(null != acnivelaceLista){
            for(final ACNIVELACE acnivelace : acnivelaceLista){
                if(acnivelace.getFECHAOPRCN()>0){
                    final NivelCuentaBean ncBean = dozerBeanMapper.map(acnivelace, NivelCuentaBean.class,
                            "resultadoConsultaNiveladoraCuenta");
                    resultado.add(ncBean);
                }
            }
        }
        return resultado;
    }

}
