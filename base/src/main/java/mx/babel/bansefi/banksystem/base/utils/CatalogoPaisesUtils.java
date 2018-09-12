package mx.babel.bansefi.banksystem.base.utils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mx.babel.arq.catalogo.beans.CatalogoBean;
import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.task.ICatalogoPaisesLoaderTask;
import mx.babel.bansefi.banksystem.base.backends.consultapaises.ConsultaPaisesBackEnd;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**Tarea encargada de cargar el listado de centros por entidades.
 * @author javier.martinnino
 *
 */
@Component
public class CatalogoPaisesUtils implements ICatalogoPaisesLoaderTask, Serializable {

	private static final long serialVersionUID = -8874094587171068449L;

	@Autowired
	ConsultaPaisesBackEnd consultaPaisesBackEnd;
	
	private Map<String,Integer> cantidadCatalogosMap = new HashMap<String, Integer>();
	
	// Queda parametrizado para admitir la entidad de entrada o catalogo para cumplir 
	// con la interfaz y seguir el mismo patron que el resto de catalogos pero no necesita
	// datos de entrada
	public List<CatalogoBean> getCatalogo(final String entidad) {
        return this.getCatalogo();
    }
	
	public List<CatalogoBean> getCatalogo() {
        return consultaPaisesBackEnd.ejecutarTRN();
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
	                +" no encontrada entre los valores del catalogo de paises");
	    }
        throw new ControlableException("Error al consultar el catalogo", "Catalogo de paises no encontrado.");
	}
	
	public String getCatalogoDescripcion(final String claveFila){
	    return this.getCatalogoBean(claveFila).getDescripcionC();	    
	}
			
    @Override
    public void loadTask() {
    	List<CatalogoBean> lst = null;
    	lst = this.consultaPaisesBackEnd.ejecutarTRN();
    	cantidadCatalogosMap.put("Paises", (!CollectionUtils.isEmpty(lst)?lst.size():0) );
    }

	@Override
	public Map<String, Integer> getCantidadCatalogosMap() {
		return this.cantidadCatalogosMap;
	}

	public void setCantidadCatalogosMap(Map<String, Integer> cantidadCatalogosMap) {
		this.cantidadCatalogosMap = cantidadCatalogosMap;
	}

}