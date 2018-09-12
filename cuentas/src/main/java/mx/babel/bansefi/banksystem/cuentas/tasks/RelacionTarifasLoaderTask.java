package mx.babel.bansefi.banksystem.cuentas.tasks;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.babel.arq.catalogo.beans.CatalogoBean;
import mx.babel.arq.task.IRelacionTarifasLoaderTask;
import mx.babel.bansefi.banksystem.base.backends.ConsultaGruposAcuerdosInstrumentalesBackEnd;
import mx.babel.bansefi.banksystem.base.enums.CatalogoEnum;
import mx.babel.bansefi.banksystem.base.utils.CatalogoUtils;
import mx.babel.bansefi.banksystem.cuentas.backends.ConsultaRelacionesTarifasBackEnd;
import mx.babel.bansefi.banksystem.cuentas.beans.TarifaBean;
import mx.babel.bansefi.banksystem.cuentas.beans.TarifaRelacionBean;

@Component
public class RelacionTarifasLoaderTask implements IRelacionTarifasLoaderTask {

	@Autowired
    CatalogoUtils catalogoUtils;
	
	@Autowired
	ConsultaGruposAcuerdosInstrumentalesBackEnd consultaGruposBackEnd;
	
	@Autowired
	ConsultaRelacionesTarifasBackEnd consultaRelacionesTarifasBackEnd;

	@Override
	public void loadTask() {
		final List<CatalogoBean> lineas = catalogoUtils.getCatalogo(CatalogoEnum.TP_LINEA_ASES);
		List<TarifaBean> relacionesTarifa = new ArrayList<TarifaBean>();
		for (final CatalogoBean linea : lineas) {
			final List<CatalogoBean> grupos = consultaGruposBackEnd.ejecutarTRN(linea.getClaveFila(), true);
			for (CatalogoBean grupo : grupos) {
				TarifaBean tarifa = new TarifaBean();
				tarifa.setLinea(linea.getClaveFila());
				tarifa.setGrupo(grupo.getClaveFila());
				relacionesTarifa.add(tarifa);
			}
		}
		for (TarifaBean tarifaBean : relacionesTarifa) {
			consultaRelacionesTarifasBackEnd.ejecutarTRN(tarifaBean);
		}
	}
	
	 public List<TarifaRelacionBean> getCatalogo(final TarifaBean tarifaBean) {
		 return consultaRelacionesTarifasBackEnd.ejecutarTRN(tarifaBean);
	 }
}
