package mx.babel.bansefi.banksystem.cuentas.utils;

import java.util.ArrayList;
import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.component.UISelectItem;
import javax.faces.component.UISelectItems;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import mx.babel.bansefi.banksystem.base.beans.domain.CuentaBean;
import mx.babel.bansefi.banksystem.base.beans.domain.CuentaClienteBean;

/**
 * Converter para objetos cuenta en vista de relaciòn ceunta- cuenta
 * 
 * @author mario.montesdeoca
 *
 */
@FacesConverter("cuentaConverter")
public class CuentaBeanConverter implements Converter{

	/**
	 * Mètodo que obtiene un objeto cuenta a pratir del valor del nùmero de cuenta
	 * 
	 */
	public Object getAsObject(FacesContext fc, UIComponent component, String value) {
		CuentaBean cuenta = null;
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
	                            CuentaBean val = (CuentaBean) item.getItemValue();
	                            if (value.equals(val.getNumeroCuenta().toString())) {
	                            	cuenta = val;
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
	                        if (value.equals(((CuentaClienteBean)val).getCuenta().getNumeroCuenta().toString())) {
	                            cuenta = ((CuentaClienteBean)val).getCuenta();
	                            break;
	                        }
	                    }
	                }
	            }
	        }
        return cuenta;
	}


	/**
	 * Mètodo que devuelve el nùmero de cuenta de un objeto CuentaCliente
	 */
	public String getAsString(FacesContext fc, UIComponent component, Object object) {
		String numeroCuenta = "";
        if (object != null && object instanceof CuentaBean) {
        	CuentaBean cuenta = (CuentaBean) object;
            if (cuenta != null) {
                Long id = cuenta.getNumeroCuenta();
                if (id != null) {
                	numeroCuenta = id.toString();
                }
            }
        }
        return numeroCuenta;
	}   

}
