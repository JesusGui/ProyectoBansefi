package mx.babel.bansefi.banksystem.base.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mx.babel.arq.catalogo.beans.CatalogoBean;
import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.sesion.utils.ContextoUtils;
import mx.babel.arq.task.ICatalogoCentrosLoaderTask;
import mx.babel.bansefi.banksystem.base.backends.busquedas.BusquedaCentroNombreBackEnd;
import mx.babel.bansefi.banksystem.base.constants.ConstantesFuncionales;
import mx.babel.bansefi.banksystem.base.enums.CatalogoEnum;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**Tarea encargada de cargar el listado de centros por entidades.
 * @author javier.martinnino
 *
 */
@Component
public class CatalogoCentrosLoaderTask implements ICatalogoCentrosLoaderTask, Serializable {

	private static final long serialVersionUID = -8874094587171068449L;

	@Autowired
	private BusquedaCentroNombreBackEnd busquedaCentroNombreBackEnd;
        
	@Autowired
	private CatalogoUtils catalogoUtils;
	
	@Autowired
	private ContextoUtils contextoUtils;
	
	private Map<String, Integer> cantidadCatalogosMap = new HashMap<String, Integer>();
	
	@Override
	public List<CatalogoBean> getCatalogo(final String entidad) {
        return busquedaCentroNombreBackEnd.ejecutarTRN(entidad);
    }
	
	public CatalogoBean getCatalogoBean(final String entidad, final String claveFila){
	    final List<CatalogoBean> listado = this.getCatalogo(entidad);
	    if(null != listado){
	        for(final CatalogoBean catalogoBean: listado){
	            if(StringUtils.equalsIgnoreCase(catalogoBean.getClaveFila(), claveFila)){
	                return catalogoBean;
	            }
	        }
	        throw new ControlableException("Error al consultar el catalogo", claveFila
	                +" no encontrada entre los valores del catalogo de centros");
	    }
        throw new ControlableException("Error al consultar el catalogo", "Catalogo Catalogo de centros no encontrado.");
	}
	
	public String getCatalogoDescripcion(final String entidad, final String claveFila){
	    return this.getCatalogoBean(entidad,claveFila).getDescripcionC();	    
	}
	
	public List<String> getCodigosCentros(String query){
		List<String> centros = new ArrayList<String>();
	    List<CatalogoBean> idCentros = this.getCatalogo(contextoUtils.getEntidad());
	    for (int i=0;i<idCentros.size() && centros.size()<ConstantesFuncionales.MAX_RESULTS_AUTOCOMPLETE;i++){
	    	if (idCentros.get(i).getClaveFila().contains(query)){
	    		centros.add(idCentros.get(i).getClaveFila());
	    	}	    		
	    }
	    
	    return centros;	    
	}
	
    @Override
    public void loadTask() {
    	List<CatalogoBean> entidades = catalogoUtils.getCatalogo(CatalogoEnum.TP_ENTIDAD_CR);
    	List<CatalogoBean> lst = null;
    	for (int i=0;i<entidades.size();i++){        
    		lst = busquedaCentroNombreBackEnd.ejecutarTRN(entidades.get(i).getClaveFila());
    		this.cantidadCatalogosMap.put(entidades.get(i).getDescripcionL(), (!CollectionUtils.isEmpty(lst)?lst.size():0));
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