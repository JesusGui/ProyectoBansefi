package mx.babel.bansefi.banksystem.cuentas.tasks;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import mx.babel.arq.catalogo.beans.CatalogoBean;
import mx.babel.arq.task.ILoaderTask;
import mx.babel.bansefi.banksystem.base.enums.CatalogoEnum;
import mx.babel.bansefi.banksystem.base.utils.CatalogoUtils;
import mx.babel.bansefi.banksystem.cuentas.backends.ConsultaGrpPrdVendBackend;
import mx.babel.bansefi.banksystem.cuentas.beans.TarifaBean;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**Tarea encargada de cargar el listado de tarifas vendibles en memoria
 * aquellos catalogos demasiado grandes.
 * @author joseluis.pena
 *
 */
@Component
public class TarifasLoaderTask implements ILoaderTask {

    @Autowired
    private ConsultaGrpPrdVendBackend consultaGrpPrdVendBackend;

	@Autowired
	private CatalogoUtils catalogoUtils;
	
	private Map<String,Integer> cantidadCatalogosMap = new HashMap<String, Integer>();
	
    @Override
    public void loadTask() {
    	// Se cargan solo las 2 entidades principales
    	final List<CatalogoBean> listadoEntidades = new CopyOnWriteArrayList<CatalogoBean>();
    	List<CatalogoBean> lstLineas = null;
        List<TarifaBean> lstTarifas = null;
        Integer sumatoriaCentros = 0;
        
        listadoEntidades.add(new CatalogoBean("0166","BANSEFI"));
        listadoEntidades.add(new CatalogoBean("0521","BIENESTAR"));
        
        lstLineas = catalogoUtils.getCatalogo(CatalogoEnum.TP_LINEA_ASES);
        
        for(final CatalogoBean catalogo:listadoEntidades){
        	for(final CatalogoBean linea:lstLineas) {
        		lstTarifas = consultaGrpPrdVendBackend.ejecutarTRN(new TarifaBean(linea.getClaveFila()), catalogo.getClaveFila());
        		if( !CollectionUtils.isEmpty(lstTarifas) ){
        			sumatoriaCentros += lstTarifas.size();
        		}
            }
        	cantidadCatalogosMap.put(catalogo.getDescripcionL(), sumatoriaCentros);
        	sumatoriaCentros = 0;
        }
    }

	@Override
	public Map<String, Integer> getCantidadCatalogosMap() {
		return this.cantidadCatalogosMap;
	}

	public void setCantidadCatalogosMap(Map<String, Integer> cantidadCatalogosMap) {
		this.cantidadCatalogosMap = cantidadCatalogosMap;
	}

}