package mx.babel.arq.catalogo.converter;


import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import mx.babel.arq.banksystem.components.CatalogoAutoComplete;
import mx.babel.arq.catalogo.beans.CatalogoBean;

/**
 * @author joseluis.pena
 *
 */
@FacesConverter("catalogoConverter")
public class CatalogoConverter implements Converter {


   @Override
   public Object getAsObject(final FacesContext fc, final UIComponent uic, final String value) {
       if(value != null && value.trim().length() > 0) {
               return ((CatalogoAutoComplete)uic).getSelectedValue(value);

       }
       else {
           return null;
       }
   }

   @Override
   public String getAsString(final FacesContext fc, final UIComponent uic, final Object object) {
       if(object != null) {
           return ((CatalogoBean) object).getClaveFila();
       }
       else {
           return null;
       }
   }
}