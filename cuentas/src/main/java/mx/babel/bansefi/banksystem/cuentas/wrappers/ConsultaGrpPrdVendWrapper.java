package mx.babel.bansefi.banksystem.cuentas.wrappers;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import mx.babel.bansefi.banksystem.base.webservices.consultagruposproductosvendibles.ResponseBansefi;
import mx.babel.bansefi.banksystem.base.webservices.consultagruposproductosvendibles.ResponseBansefi.OTRCONSLISTAPDVTRNO.TRCONSLISTAPDVEVTZ.LGLISTAV;
import mx.babel.bansefi.banksystem.base.webservices.consultagruposproductosvendibles.ResponseBansefi.OTRCONSLISTAPDVTRNO.TRCONSLISTAPDVEVTZ.PVLISTAV;
import mx.babel.bansefi.banksystem.cuentas.beans.TarifaBean;

import org.apache.commons.lang3.StringUtils;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConsultaGrpPrdVendWrapper {

	@Autowired
	DozerBeanMapper dozerBeanMapper;

	public List<TarifaBean> wrappGrpPrdVend(
			final List<ResponseBansefi.OTRCONSLISTAPDVTRNO.TRCONSLISTAPDVEVTZ.LGLISTAV> lstLglistav,
			final String linea) {
		final List<TarifaBean> resultado = new CopyOnWriteArrayList<TarifaBean>();
		TarifaBean tarifaBean = null;

		for (final LGLISTAV lglistav : lstLglistav) {
			if (StringUtils.isNotBlank(lglistav.getIDGRPPD())) {
    			tarifaBean = dozerBeanMapper.map(lglistav, TarifaBean.class,
    					"resultadoConsultaGrupo");
    			tarifaBean.setLinea(linea);
    			resultado.add(tarifaBean);
			}
		}

		return resultado;
	}

	public List<TarifaBean> wrappGrpPrdVend(final List<PVLISTAV> lstPvlistav,
			final String linea, final String grupo) {
		final List<TarifaBean> resultado = new CopyOnWriteArrayList<TarifaBean>();
		TarifaBean tarifaBean = null;

		for (final PVLISTAV data : lstPvlistav) {
		    if (StringUtils.isNotBlank(data.getIDPDV())) {
    			tarifaBean = dozerBeanMapper.map(data, TarifaBean.class,
    					"resultadoConsultaProducto");
    			tarifaBean.setLinea(linea);
    			tarifaBean.setGrupo(grupo);
    			resultado.add(tarifaBean);
		    }
		}

		return resultado;
	}

}
