package mx.babel.bansefi.banksystem.base.validadores;

import java.math.BigDecimal;
import java.text.NumberFormat;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("importeValidator")
public class ImporteValidator implements Validator{

	@Override
	public void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {
		// TODO Auto-generated method stub
		
		if (value == null) {
            return;
        }
		
		Object valorDenominacion = component.getAttributes().get("valor");
		if (valorDenominacion==null) {
			return;
		}
		
		BigDecimal valor = (BigDecimal) valorDenominacion;
		BigDecimal importe = new BigDecimal(value.toString());
		
		if(importe.compareTo(BigDecimal.ZERO) == 0){
			 throw new ValidatorException(
                     new FacesMessage(FacesMessage.SEVERITY_ERROR, ""
                            , "El importe no puede ser cero "
                            + NumberFormat.getCurrencyInstance().format(valor)));
		}
		

		
	}

}
