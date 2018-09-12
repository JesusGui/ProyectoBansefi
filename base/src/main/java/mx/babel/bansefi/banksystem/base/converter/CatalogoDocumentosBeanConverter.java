package mx.babel.bansefi.banksystem.base.converter;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import mx.babel.arq.catalogo.beans.CatalogoBean;
import mx.babel.bansefi.banksystem.base.enums.CatalogoEnum;
import mx.babel.bansefi.banksystem.base.utils.CatalogoUtils;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.component.autocomplete.AutoComplete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CatalogoDocumentosBeanConverter implements Converter {
	
    @Autowired
    private CatalogoUtils catalogoUtils;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		List<CatalogoBean> lst = null;
		CatalogoBean bean = null;
		CatalogoBean cbSelected = null;
				
		if( !StringUtils.isBlank(value) ) {
			
			lst = catalogoUtils.getCatalogo(CatalogoEnum.TP_DOC);
			
			for(int i=0 ; i<lst.size() ; i++){
				bean = lst.get(i);
				
				if(bean.getClaveFila().equalsIgnoreCase(value)){
					cbSelected = bean;
					break;
				}
			}
			
			return cbSelected;
			
		} else {
			AutoComplete autoComplete = null;
			autoComplete = (AutoComplete)component;
			autoComplete.setValue(null);
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		
 		String selected = null;
 		
		if (value != null) {
			
		    selected = ((CatalogoBean) value).getClaveFila();
			
		} else {
			selected = null;
		}
		
		
		if(selected == null){
			AutoComplete autoComplete = null;
			autoComplete = (AutoComplete)component;
			autoComplete.setValue(null);
		}
		
		return selected;
	}
}
