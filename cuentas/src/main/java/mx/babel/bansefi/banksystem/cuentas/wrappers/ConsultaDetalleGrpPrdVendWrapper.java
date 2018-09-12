package mx.babel.bansefi.banksystem.cuentas.wrappers;

import mx.babel.bansefi.banksystem.cuentas.beans.TarifaBean;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultadetallegrpprdvend.Ejecutar;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultadetallegrpprdvend.ResponseBansefi.OTRLISTATVTRNO.TRLISTATVEVTZ.TRLISTATVEVTV;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConsultaDetalleGrpPrdVendWrapper {

    @Autowired
    DozerBeanMapper dozerBeanMapper;

    public Ejecutar.ITRLISTATVTRNI.TRLISTATVEVTY.TVTRFAPDVP wrappDetalleGrpPrdVend(final TarifaBean tarifaBean) {
        final Mapper mapper = dozerBeanMapper;
        return mapper.map(tarifaBean, Ejecutar.ITRLISTATVTRNI.TRLISTATVEVTY.TVTRFAPDVP.class, "consultaDetalleGrpPrdVend");
    }

    public TarifaBean wrappDetalleGrpPrdVend(final TRLISTATVEVTV trlistatvevtv) {
        final Mapper mapper = dozerBeanMapper;
        return mapper.map(trlistatvevtv, TarifaBean.class, "respuestaDetalleGrpPrdVend");
    }

}
