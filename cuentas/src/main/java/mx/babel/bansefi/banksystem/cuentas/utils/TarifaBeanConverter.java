package mx.babel.bansefi.banksystem.cuentas.utils;

import java.util.ArrayList;
import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.component.UISelectItem;
import javax.faces.component.UISelectItems;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import mx.babel.bansefi.banksystem.cuentas.beans.TarifaRelacionBean;

/**
 * Converter para objetos cuenta en vista de relaciòn ceunta- cuenta
 * 
 * @author mario.montesdeoca
 *
 */
@FacesConverter("tarifaConverter")
public class TarifaBeanConverter implements Converter{

	/**
	 * Mètodo que obtiene un objeto cuenta a pratir del valor del nùmero de cuenta
	 * 
	 */
	public Object getAsObject(FacesContext fc, UIComponent component, String value) {
		TarifaRelacionBean tarifa = null;
		 if (component != null) {
	            List<UIComponent> childs = component.getChildren();
	            UISelectItems items = null;
	            if (childs != null) {
	                for (UIComponent ui : childs) {
	                    if (ui instanceof UISelectItems) {
	                    	items = (UISelectItems) ui;
	                        break;
	                    } else if (ui instanceof UISelectItem) {
	                        UISelectItem item = (UISelectItem) ui;
	                        try {
	                            TarifaRelacionBean val = (TarifaRelacionBean) item.getItemValue();
	                            if (value.equals(val.getCodigoRelacion())) {
	                            	tarifa = val;
	                                break;
	                            }
	                        } catch (Exception e) {
	                        }
	                    }
	                }
	            }

	            if (items != null) {
	                List<?> values = (ArrayList<?>) items.getValue();
	                if (values != null) {
	                    for (Object val : values) {
	                        if (value.equals(((TarifaRelacionBean)val).getCodigoRelacion())) {
	                            tarifa = ((TarifaRelacionBean)val);
	                            break;
	                        }
	                    }
	                }
	            }
	        }
        return tarifa;
	}


	/**
	 * Mètodo que devuelve el nùmero de cuenta de un objeto CuentaCliente
	 */
	public String getAsString(FacesContext fc, UIComponent component, Object object) {
		String codigoRelacion = "";
        if (object != null && object instanceof TarifaRelacionBean) {
        	TarifaRelacionBean tarifa = (TarifaRelacionBean) object;
            if (tarifa != null) {
            	codigoRelacion = tarifa.getCodigoRelacion();
            }
        }
        return codigoRelacion;
	}   

}
