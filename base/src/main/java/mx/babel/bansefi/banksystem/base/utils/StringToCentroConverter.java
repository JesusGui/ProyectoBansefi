package mx.babel.bansefi.banksystem.base.utils;

import mx.babel.arq.catalogo.beans.CatalogoBean;
import mx.babel.arq.comun.exceptions.NoControlableException;

import org.apache.commons.lang.StringUtils;
import org.dozer.DozerConverter;
import org.dozer.Mapper;
import org.dozer.MapperAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Se encarga de convertir un String a un CatalogoBean y viceversa
 * @author luis.gcastellano
 *
 */
@Component("stringToCentroConverter")
public class StringToCentroConverter extends DozerConverter<String, CatalogoBean> implements MapperAware{
    
    private Mapper mapper;
    
    @Autowired
    private CatalogoCentrosLoaderTask catalogoCentros;
    
    public StringToCentroConverter() {
        super(String.class, CatalogoBean.class);
    }

    @Override
    public void setMapper(Mapper mapper) {
        this.mapper = mapper;
    }
    
    /**
     * Crea un catalogBean a partir de la clave-fila y de un parametro con el catalogo que se debe usar
     */
    @Override
    public CatalogoBean convertTo(String source, CatalogoBean destination) {
        try{
            String entidad = getParameter();
            if(StringUtils.isNotBlank(entidad) && StringUtils.isNotBlank(source)){
            	return catalogoCentros.getCatalogoBean(entidad,source);
            }
        }catch (NoControlableException nce){
            return null;
        }
        return null;
    }

    @Override
    public String convertFrom(CatalogoBean source, String destination) {
        if(source!=null){
            return source.getClaveFila();
        }
        return null;
    }

}
