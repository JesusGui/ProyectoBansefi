package mx.babel.bansefi.banksystem.base.utils;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import mx.babel.arq.catalogo.beans.CatalogoBean;
import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.task.ICatalogoGruposAcuerdosInstrumentalesLoaderTask;
import mx.babel.bansefi.banksystem.base.backends.ConsultaGruposAcuerdosInstrumentalesBackEnd;
import mx.babel.bansefi.banksystem.base.enums.CatalogoEnum;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Clase que genera un catalogo para los grupos de acuerdos instrumentales.
 * 
 * @author alejandro.pineda
 * 
 */
@Component
public class CatalogoGruposAcuerdosInstrumentalesLoaderTask implements
		ICatalogoGruposAcuerdosInstrumentalesLoaderTask, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6376892127876758567L;
	
	@Autowired
	private CatalogoUtils catalogoUtils;
	
	@Autowired
	private ConsultaGruposAcuerdosInstrumentalesBackEnd consultaGruposAcuerdos;

	@Override
	public List<CatalogoBean> getCatalogo(final String codLinea) {
		return consultaGruposAcuerdos.ejecutarTRN(codLinea);
	}
	
	
	public CatalogoBean getCatalogoBean(final String codLinea, final String claveFila){
	    final List<CatalogoBean> listado = this.getCatalogo(codLinea);
	    if(null != listado){
	        for(final CatalogoBean catalogoBean: listado){
	            if(StringUtils.equalsIgnoreCase(catalogoBean.getClaveFila(), claveFila)){
	                return catalogoBean;
	            }
	        }
	        throw new ControlableException("Error al consultar el catalogo", claveFila
	                +" no encontrada entre los valores del catalogo de centros");
	    }
        throw new ControlableException("Error al consultar el catalogo", "Catalogo Catalogo de grupos no encontrado.");
	}
	
	
	public String getCatalogoDescripcion(final String codLinea, final String claveFila){
	    return this.getCatalogoBean(codLinea, claveFila).getDescripcionC();	    
	}
	
	@Override
	public void loadTask() {
		List<CatalogoBean> lineas = catalogoUtils.getCatalogo(CatalogoEnum.TP_LINEA_ASES);
    	for (int i=0;i<lineas.size();i++){        
    		consultaGruposAcuerdos.ejecutarTRN(lineas.get(i).getClaveFila());
        }
	}


	@Override
	public Map<String, Integer> getCantidadCatalogosMap() {
		// TODO Auto-generated method stub
		return null;
	}

}
