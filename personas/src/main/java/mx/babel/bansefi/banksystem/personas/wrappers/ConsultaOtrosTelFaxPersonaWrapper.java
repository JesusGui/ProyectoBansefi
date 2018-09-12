package mx.babel.bansefi.banksystem.personas.wrappers;

import java.util.ArrayList;
import java.util.List;

import mx.babel.bansefi.banksystem.base.constants.ConstantesFuncionales;
import mx.babel.bansefi.banksystem.personas.beans.OtroValorBean;
import mx.babel.bansefi.banksystem.personas.webservices.consultaotrostelfaxpersona.ResponseBansefi.OTRDRCONSDRELCTRTRN.TRDRCONSDRELCTREVTZ.TRDRCONSDRELCTREVTV;

import org.apache.commons.lang3.StringUtils;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConsultaOtrosTelFaxPersonaWrapper {

    @Autowired
    DozerBeanMapper dozerBeanMapper;

    public List<OtroValorBean> wrappOtrosTelFax(
            final List<TRDRCONSDRELCTREVTV> list) {

        final List<OtroValorBean> resultado = new ArrayList<OtroValorBean>();
        final Mapper mapper = dozerBeanMapper;
        for(final TRDRCONSDRELCTREVTV value: list){
            if(value != null && StringUtils.isNotBlank(value.getCODDIRELCTR())){
                if(StringUtils.equalsIgnoreCase(ConstantesFuncionales.DIR_INTERNET.getClaveFila(), value.getCODDIRELCTR())){
                    resultado.add(mapper.map(value, OtroValorBean.class, "consultaOtrosTelFaxPersonaInternet"));
                }else{
                    resultado.add(mapper.map(value, OtroValorBean.class, "consultaOtrosTelFaxPersonaTelefono"));
                }
            }
        }
        return resultado;
    }

}
