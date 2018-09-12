package mx.babel.bansefi.banksystem.cuentas.wrappers;

import java.util.ArrayList;
import java.util.List;

import mx.babel.bansefi.banksystem.base.beans.productossimples.ProductoSimpleBean;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultaproductossimples.ResponseBansefi.OTRLISTARLPVPSTRNO.TRLISTARLPVPSEVTZ;

import org.apache.commons.lang3.StringUtils;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author joseluis.pena
 *
 */
@Component
public class ConsultaProductosSimplesWrapper {

    @Autowired
    DozerBeanMapper dozerBeanMapper;

    public List<ProductoSimpleBean> wrapProductoSimple(
            final List<TRLISTARLPVPSEVTZ> trlistarlpvpsevtz) {
        final Mapper mapper = dozerBeanMapper;
        final List<ProductoSimpleBean> prodSimplesLista = new ArrayList<ProductoSimpleBean>();
        for(final TRLISTARLPVPSEVTZ prodSimple : trlistarlpvpsevtz){
            if(StringUtils.isNotBlank(prodSimple.getIDPDS())){
                prodSimplesLista.add(mapper.map(prodSimple, ProductoSimpleBean.class, "resultadoConsultaProductosSimples"));
            }
        }
        return prodSimplesLista;
    }


}
