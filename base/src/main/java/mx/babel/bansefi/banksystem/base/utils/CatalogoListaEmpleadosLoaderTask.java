package mx.babel.bansefi.banksystem.base.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mx.babel.arq.catalogo.beans.CatalogoBean;
import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.sesion.utils.ContextoUtils;
import mx.babel.arq.task.ICatalogoListaEmpleadosLoaderTask;
import mx.babel.bansefi.banksystem.base.backends.consultalistaempleados.ConsultaListaEmpleadosBackEnd;
import mx.babel.bansefi.banksystem.base.constants.ConstantesFuncionales;
import mx.babel.bansefi.banksystem.base.enums.CatalogoEnum;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**Tarea encargada de cargar el listado de tarifas vendibles en memoria
 * aquellos catalogos demasiado grandes.
 * @author joseluis.pena
 *
 */
@Component
public class CatalogoListaEmpleadosLoaderTask implements ICatalogoListaEmpleadosLoaderTask, Serializable {

	private static final long serialVersionUID = 1131137853063685203L;

	@Autowired
    private ConsultaListaEmpleadosBackEnd consultaListaEmpleadosBackEnd;

    @Autowired
    private CatalogoUtils catalogoUtils;
    
    @Autowired
    private ContextoUtils contextoUtils;
    
    private Map<String,Integer> cantidadCatalogosMap = new HashMap<String, Integer>();
    
    @Override
    public List<CatalogoBean> getCatalogo(final String entidad) {
        return consultaListaEmpleadosBackEnd.ejecutarTRN(entidad);
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
	
    public List<String> getEmpleados(String query){
    	List<String> empleados = new ArrayList<String>();
    	List<CatalogoBean> idEmpleados = this.getCatalogo(contextoUtils.getEntidad());
    	for (int i=0;i<idEmpleados.size() && empleados.size()<ConstantesFuncionales.MAX_RESULTS_AUTOCOMPLETE;i++){
    		if (StringUtils.containsIgnoreCase(idEmpleados.get(i).getDescripcionC(), query.toUpperCase())){
    			empleados.add(idEmpleados.get(i).getDescripcionC());
    		}    		
    	}
    	
	    return empleados;	    
	}
    
	public String getCatalogoDescripcion(final String entidad, final String claveFila){
	    return this.getCatalogoBean(entidad,claveFila).getDescripcionC();	    
	}
    
    @Override
    public void loadTask() {
    	List<CatalogoBean> entidades = catalogoUtils.getCatalogo(CatalogoEnum.TP_ENTIDAD_CR);
    	List<CatalogoBean> lst = null;
    	
    	for (int i=0;i<entidades.size();i++){        
    		lst = consultaListaEmpleadosBackEnd.ejecutarTRN(entidades.get(i).getClaveFila());
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