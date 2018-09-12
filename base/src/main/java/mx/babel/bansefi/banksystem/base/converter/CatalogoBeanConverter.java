package mx.babel.bansefi.banksystem.base.converter;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.component.autocomplete.AutoComplete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.babel.arq.catalogo.beans.CatalogoGeoBean;
import mx.babel.bansefi.banksystem.base.utils.CatalogoGeoUtils;

@Component
public class CatalogoBeanConverter implements Converter {
	
	@Autowired
	private CatalogoGeoUtils catalogoGeoUtils;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		List<CatalogoGeoBean> lst = null;
		CatalogoGeoBean bean = null;
		CatalogoGeoBean cbSelected = null;
		String filter = null;
		Boolean codificada = null;
				
		if( !StringUtils.isBlank(value) ) {
			filter = (String)component.getAttributes().get("filter");
			lst = catalogoGeoUtils.getCatalogoGeoBean();
			
			for(int i=0 ; i<lst.size() ; i++){
				bean = lst.get(i);
				if(CatalogoGeoUtils.CATALOGO_CP.equalsIgnoreCase(filter)){
					if(bean.getCodigoPostal().equalsIgnoreCase(value)){
						cbSelected = bean;
						break;
					}
				}else if(CatalogoGeoUtils.CATALOGO_MUNICIPIO.equalsIgnoreCase(filter)){
					if(bean.getMunicipioLocalidad().equalsIgnoreCase(value)){
						cbSelected = bean;
						break;
					}
				}else if(CatalogoGeoUtils.ESTADOS.equalsIgnoreCase(filter)){
					if(bean.getNombreProvincia().equalsIgnoreCase(value)){
						cbSelected = bean;
						break;
					}
				}
			}
			if (cbSelected == null){
				if(component.getAttributes().get("codificada")!=null)
					codificada = Boolean.valueOf((String)component.getAttributes().get("codificada"));
				
				if (codificada != null && !codificada){
					cbSelected = new CatalogoGeoBean();
					cbSelected.setNombreMunicipio(value);
					cbSelected.setMunicipioLocalidad(value);
					return cbSelected;
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
		String filter = null;
 		String selected = null;
 		Boolean codificada = null;
 		
		if (value != null) {
			filter = (String)component.getAttributes().get("filter");

			if(CatalogoGeoUtils.CATALOGO_CP.equalsIgnoreCase(filter)){
				selected = ((CatalogoGeoBean) value).getCodigoPostal();
			}else if(CatalogoGeoUtils.CATALOGO_MUNICIPIO.equalsIgnoreCase(filter)){
				selected = ((CatalogoGeoBean) value).getMunicipioLocalidad();
			}else if(CatalogoGeoUtils.ESTADOS.equalsIgnoreCase(filter)){
				selected = ((CatalogoGeoBean) value).getNombreProvincia();
			}else{
				selected = null;
			}
		} else {
			selected = null;
		}
		
		if (selected == null){
			if(component.getAttributes().get("codificada")!=null)
				codificada = Boolean.valueOf((String)component.getAttributes().get("codificada"));
			
			if (codificada != null && !codificada){
				selected = ((CatalogoGeoBean) value).getMunicipioLocalidad()!=null ? ((CatalogoGeoBean) value).getMunicipioLocalidad() :  ((CatalogoGeoBean) value).getNombreMunicipio();
			}
		}
		if(selected == null){
			AutoComplete autoComplete = null;
			autoComplete = (AutoComplete)component;
			autoComplete.setValue(null);
		}
		
		return selected;
	}
}
