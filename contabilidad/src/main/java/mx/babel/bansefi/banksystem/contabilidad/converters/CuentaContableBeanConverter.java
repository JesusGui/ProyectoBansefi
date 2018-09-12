package mx.babel.bansefi.banksystem.contabilidad.converters;

import java.io.Serializable;
import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import mx.babel.bansefi.banksystem.base.backends.ConsultaCuentasContablesBackEnd;
import mx.babel.bansefi.banksystem.base.beans.CuentaContableBean;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.component.autocomplete.AutoComplete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CuentaContableBeanConverter implements Converter, Serializable{

	private static final long serialVersionUID = -4379702178679304003L;

	@Autowired
	private ConsultaCuentasContablesBackEnd consultaCuentasOperativasBackEnd;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		if( !StringUtils.isBlank(value) ) {

			CuentaContableBean cuentaContableBean = new CuentaContableBean();
			cuentaContableBean.setIdCuentaContable(value);
			
			return cuentaContableBean;
			
		}else {
			AutoComplete autoComplete = null;
			autoComplete = (AutoComplete)component;
			autoComplete.setValue(null);
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if(context != null && component != null && value != null){
			return String.valueOf(((CuentaContableBean) value).getIdCuentaContable());
		}else{
			return null;
		}
	}
	

}
