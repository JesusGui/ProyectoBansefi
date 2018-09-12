package mx.babel.bansefi.banksystem.base.wrappers;

import java.util.List;

import mx.babel.bansefi.banksystem.base.beans.DepositoIPFBean;
import mx.babel.bansefi.banksystem.base.beans.productossimples.ProductoSimpleBean;
import mx.babel.bansefi.banksystem.base.utils.ProductosSimplesWrapperUtils;
import mx.babel.bansefi.banksystem.base.webservices.consultadatospeticionipf.ResponseBansefi.OTRDATOSPETIMPSCNPAG.TRDATOSPETIMPSCNPAGE;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author joseluis.pena
 *
 */
@Component
public class ConsultaDatosPeticionIPFWrapper {

    @Autowired
    DozerBeanMapper mapper;

    @Autowired
    ProductosSimplesWrapperUtils productosSimplesWrapperUtils;

    public DepositoIPFBean wrappDepositoIPF(final TRDATOSPETIMPSCNPAGE trdatospetimpscnpage) {
        final DepositoIPFBean resultado = mapper.map(trdatospetimpscnpage, DepositoIPFBean.class, "resultadoDatosPeticionIpfTarifa");
        if(null!=trdatospetimpscnpage.getTRDATOSPETIMPSCNPAGE()){
            final List<ProductoSimpleBean> listado = productosSimplesWrapperUtils.
                    wrappCondicionesDeConsultaDatosPeticionIPF(trdatospetimpscnpage.getTRDATOSPETIMPSCNPAGE());
            resultado.setProductosSimples(listado);
        }
        return resultado;
    }

}
