package mx.babel.bansefi.banksystem.base.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mx.babel.arq.catalogo.beans.CatalogoBean;
import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.sesion.utils.ContextoUtils;
import mx.babel.arq.task.ICatalogoPerfilesTCBLoaderTask;
import mx.babel.bansefi.banksystem.base.backends.consultalistaperfilestcb.ConsultaListaPerfilesTCBBackEnd;
import mx.babel.bansefi.banksystem.base.constants.ConstantesFuncionales;
import mx.babel.bansefi.banksystem.base.enums.CatalogoEnum;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**Tarea encargada de cargar el listado de perfiles de TCB.
 * @author javier.martinnino
 *
 */
@Component
public class CatalogoPerfilesTCBLoaderTask implements ICatalogoPerfilesTCBLoaderTask, Serializable {

	private static final long serialVersionUID = 1131137853063685203L;

	@Autowired
    private ConsultaListaPerfilesTCBBackEnd consultaListaPerfilesTCBBackEnd;

    @Autowired
	private CatalogoUtils catalogoUtils;
    
    @Autowired
	private ContextoUtils contextoUtils;
    
    private Map<String,Integer> cantidadCatalogosMap = new HashMap<String, Integer>();
    
    @Override
    public List<String> getCatalogo(final String entidad) {
        return consultaListaPerfilesTCBBackEnd.ejecutarTRN(entidad);
    }
    
    public String getCatalogoBean(final String entidad, final String claveFila){
	    final List<String> listado = this.getCatalogo(entidad);
	    if(null != listado){
	        for(final String perfil: listado){
	            if(StringUtils.equalsIgnoreCase(perfil, claveFila)){
	                return perfil;
	            }
	        }
	        throw new ControlableException("Error al consultar el catalogo", claveFila
	                +" no encontrada entre los valores del catalogo de centros");
	    }
        throw new ControlableException("Error al consultar el catalogo", "Catalogo Catalogo de centros no encontrado.");
	}
	
    public List<String> getPerfiles(String query){
    	List<String> listado = new ArrayList<String>();
    	List<String> perfiles = this.getCatalogo(contextoUtils.getEntidad());
    	for (int i=0;i<perfiles.size() && listado.size()<ConstantesFuncionales.MAX_RESULTS_AUTOCOMPLETE;i++){
    		if (StringUtils.containsIgnoreCase(perfiles.get(i), query)){
    			listado.add(perfiles.get(i));
    		}  		
    	}
    	
	    return listado;	    
	}
    
    @Override
    public void loadTask() {
    	List<CatalogoBean> entidades = catalogoUtils.getCatalogo(CatalogoEnum.TP_ENTIDAD_CR);
    	List<String> lst = null;
    	for (int i=0;i<entidades.size();i++){        
    		lst = consultaListaPerfilesTCBBackEnd.ejecutarTRN(entidades.get(i).getClaveFila());
    		cantidadCatalogosMap.put(entidades.get(i).getDescripcionL(), (!CollectionUtils.isEmpty(lst)?lst.size():0) );
        }

    }

	@Override
	public Map<String, Integer> getCantidadCatalogosMap() {
		return this.cantidadCatalogosMap;
	}

}