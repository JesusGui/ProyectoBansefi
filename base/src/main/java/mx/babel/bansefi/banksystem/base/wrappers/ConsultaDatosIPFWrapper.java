package mx.babel.bansefi.banksystem.base.wrappers;

import java.util.List;

import mx.babel.bansefi.banksystem.base.beans.DepositoIPFBean;
import mx.babel.bansefi.banksystem.base.beans.productossimples.ProductoSimpleBean;
import mx.babel.bansefi.banksystem.base.utils.ProductosSimplesWrapperUtils;
import mx.babel.bansefi.banksystem.base.webservices.consultadatosipf.ResponseBansefi.OTRDATOSRENIMPSCNPAG.TRDATOSRENIMPSCNPAGE;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author joseluis.pena
 *
 */
@Component
public class ConsultaDatosIPFWrapper {

    @Autowired
    DozerBeanMapper mapper;

    @Autowired
    ProductosSimplesWrapperUtils productosSimplesWrapperUtils;

    public DepositoIPFBean wrappDepositoIPF(final TRDATOSRENIMPSCNPAGE trdatosrenimpscnpage) {
        final DepositoIPFBean resultado = mapper.map(trdatosrenimpscnpage, DepositoIPFBean.class, "resultadoDatosIpfTarifa");
        if(null!=trdatosrenimpscnpage.getTRDATOSRENIMPSCNPAGE()){
            final List<ProductoSimpleBean> listado = productosSimplesWrapperUtils.
                    wrappCondicionesDeConsultaDatosIPF(trdatosrenimpscnpage.getTRDATOSRENIMPSCNPAGE());
            resultado.setProductosSimples(listado);
        }
        return resultado;
    }

}
