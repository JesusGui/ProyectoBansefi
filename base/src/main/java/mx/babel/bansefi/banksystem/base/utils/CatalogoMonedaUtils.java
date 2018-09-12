package mx.babel.bansefi.banksystem.base.utils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mx.babel.arq.catalogo.beans.CatalogoBean;
import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.task.ICatalogoMonedaLoaderTask;
import mx.babel.bansefi.banksystem.base.backends.CatalogoMonedaBackEnd;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CatalogoMonedaUtils implements ICatalogoMonedaLoaderTask, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4733235695765761071L;
	
	@Autowired
	private CatalogoMonedaBackEnd catalogoMonedaBackEnd;
	
	private Map<String,Integer> cantidadCatalogosMap = new HashMap<String, Integer>();

	// Queda parametrizado para admitir la entidad de entrada o catalogo para cumplir 
	// con la interfaz y seguir el mismo patron que el resto de catalogos pero no necesita
	// datos de entrada
	public List<CatalogoBean> getCatalogo(final String entidad) {
        return this.getCatalogo();
    }
	
	public List<CatalogoBean> getCatalogo() {
        return catalogoMonedaBackEnd.ejecutarTRN(null);
    }
	
	public CatalogoBean getCatalogoBean(final String claveFila){
	    final List<CatalogoBean> listado = this.getCatalogo();
	    if(null != listado){
	        for(final CatalogoBean catalogoBean: listado){
	            if(StringUtils.equalsIgnoreCase(catalogoBean.getClaveFila(), claveFila)){
	                return catalogoBean;
	            }
	        }
	        throw new ControlableException("Error al consultar el catalogo", claveFila
	                +" no encontrada entre los valores del catalogo de monedas");
	    }
        throw new ControlableException("Error al consultar el catalogo", "Catalogo de monedas no encontrado.");
	}
	
	@Override
	public void loadTask() {
		List<CatalogoBean> lst = null;
		lst = this.catalogoMonedaBackEnd.ejecutarTRN(null);
		cantidadCatalogosMap.put("Monedas", (!CollectionUtils.isEmpty(lst)?lst.size():0) );
	}

	@Override
	public Map<String, Integer> getCantidadCatalogosMap() {
		return this.cantidadCatalogosMap;
	}
	
	

}
